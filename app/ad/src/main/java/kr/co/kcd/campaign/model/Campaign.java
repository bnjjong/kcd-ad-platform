/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/5/30
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package kr.co.kcd.campaign.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kr.co.kcd.campaign.config.EhcacheConfing;
import kr.co.kcd.campaign.constant.AdConstants;
import kr.co.kcd.campaign.dto.AdResponseDto.SendAd.CreativeDto;
import kr.co.kcd.campaign.dto.CampaignRequestDto;
import kr.co.kcd.campaign.event.IncreaseCreativeViewEvent;
import kr.co.kcd.shared.enumshared.CommonColumnType;
import kr.co.kcd.shared.enumshared.ProductType;
import kr.co.kcd.shared.enumshared.SegmentOperator;
import kr.co.kcd.shared.enumshared.YN;
import kr.co.kcd.shared.spring.common.event.Events;
import kr.co.kcd.shared.spring.common.exception.DataNotFoundException;
import kr.co.kcd.shared.spring.common.exception.UnexpectedApplicationException;
import kr.co.kcd.user.dto.UserDto;
import kr.co.kcd.user.dto.UserDto.Retrieve;
import kr.co.kcd.user.model.UserColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.util.CollectionUtils;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Campaign {
  /** 아이디. */
  @Id @UuidGenerator private String id;

  /** 캠페인 상품. */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ProductType productType;

  /** 지면 명. */
  @Column(nullable = false)
  private String placement;

  // ============== child ==============
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "campaign")
  @ToString.Exclude
  private List<AdGroup> adGroupList;

  // ============== child ==============



  public Campaign(ProductType productType, String placement) {
    this.productType = productType;
    this.placement = placement;
  }

  public void appendAdGroup(
      YN publishYn,
      double priority,
      LocalDate startDate,
      LocalDate endDate,
      List<CampaignRequestDto.AdGroupCondition> conditions) {
    AdGroup adGroup =
        new AdGroup(publishYn, startDate, endDate, BigDecimal.valueOf(priority), this);

    adGroup.addConditions(conditions);
    if (adGroupList == null) {
      adGroupList = new ArrayList<>();
    }
    adGroupList.add(adGroup);
  }

  public void appendCreative(
      long adGroupId,
      String title,
      String description,
      String textColor,
      String backgroundColor,
      String backgroundImage,
      String url,
      int limitExposure) {
    AdGroup adGroup = findAdGroupById(adGroupId);

    adGroup.addCreative(
        title, description, textColor, backgroundColor, backgroundImage, url, limitExposure);
  }

  public AdGroup findAdGroupById(long adGroupId) {
    return this.adGroupList.stream()
        .filter(ag -> ag.getId().equals(adGroupId))
        .findFirst()
        .orElseThrow(() -> new DataNotFoundException("ad group is not found by : " + adGroupId));
  }

  public List<CreativeDto> retrieveCreatives(UserDto.Retrieve user) {

    List<AdGroup> adGroups =
        getGroupList(user).stream()
            .filter(ag -> ag.getPublishYn() == YN.Y)
            .filter(
                ag ->
                    ag.getStartDate() == null
                        || ag.getStartDate().isEqual(LocalDate.now())
                        || !ag.getStartDate().isAfter(LocalDate.now()))
            .filter(
                ag ->
                    ag.getEndDate() == null
                        || ag.getEndDate().isEqual(LocalDate.now())
                        || !ag.getEndDate().isBefore(LocalDate.now()))
            .filter(ag -> ag.getConditions().stream().allMatch(c -> mappingCondition(c, user)))
            .sorted(Comparator.comparing(AdGroup::getPriority))
            .toList();

    // dto 변환 작업 및 creative 필터 처리.
    List<CreativeDto> creatives = new ArrayList<>();
    for (AdGroup ag : adGroups) {
      creatives.addAll(ag.getCreatives()
          .stream()
          .filter(c -> c.getLimitExposure() == 0 || c.getViewCount() < c.getLimitExposure())
          .peek(c -> Events.raise(new IncreaseCreativeViewEvent(c.getId()) ))
          .map(c -> new CreativeDto(
              ag.getId(),
              c.getId(),
              c.getTitle(),
              c.getDescription(),
              c.getTextColor(),
              c.getBackgroundColor(),
              c.getBackgroundImage(),
              c.getUrl(),
              ag.getPriority().doubleValue()
          ))
          .toList());
      }

      if (creatives.isEmpty()) {
        creatives.add(AdConstants.alternativeCreative);
      }
    return creatives;
  }

  private List<AdGroup> getGroupList(Retrieve user) {
    Cache<String, List<AdGroup>> adGroupsCache = EhcacheConfing.getInstance().getAdGroupsCache();
    String key = this.getPlacement() + "-" + user.getId();
    List<AdGroup> adGroups = adGroupsCache.get(key);
    if (CollectionUtils.isEmpty(adGroups)) {
      // 불변 리스트로 변환
      adGroups = Collections.unmodifiableList(this.getAdGroupList());
      adGroupsCache.put(key, adGroups);
      log.info("ad groups loaded from db");
    } else {
      log.info("ad groups loaded from cache");
    }
    return adGroups;
  }

  private boolean mappingCondition(AudienceCondition c, UserDto.Retrieve user) {
    UserColumn column = c.getColumn();
    SegmentOperator operator = c.getOperator();
    String value = c.getValue();

    String audienceValue = "";
    if (column == UserColumn.AGE) {
      audienceValue = String.valueOf(user.getAge());
    } else if (column == UserColumn.GENDER) {
      audienceValue = String.valueOf(user.getGender());
    } else if (column == UserColumn.CLASSIFICATION) {
      audienceValue = String.valueOf(user.getClassification());
    } else if (column == UserColumn.KOREA_REGION) {
      audienceValue = String.valueOf(user.getKoreaRegion());
    } else if (column == UserColumn.MONTHLY_SALES) {
      audienceValue = String.valueOf(user.getMonthlySales());
    } else {
      log.error("AudienceCondition : {}, user : {}", c, user);
      throw new UnexpectedApplicationException("This error should not occur. Please check.");
    }
    return compareValue(operator, value, audienceValue, column.getColumnType());
  }

  private boolean compareValue(
      SegmentOperator operator, String value, String value1, CommonColumnType columnType) {
    if (operator == SegmentOperator.EQ) {
      return value.equals(value1);
    } else if (operator == SegmentOperator.IN) {
      return Arrays.asList(value.split(",")).contains(value1);
    } else if (operator == SegmentOperator.NOT_IN) {
      return !Arrays.asList(value.split(",")).contains(value1);
    } else if (operator == SegmentOperator.FALSE || operator == SegmentOperator.TRUE) {
      return value.equalsIgnoreCase(value1);
    } else if (operator == SegmentOperator.GOE) {
      if (columnType == CommonColumnType.INTEGER || columnType == CommonColumnType.LONG) {
        // value1 이 크거나 같은지.
        return new BigDecimal(value1).compareTo(new BigDecimal(value)) >= 0;
      } else {
        return value1.compareToIgnoreCase(value) >= 0;
      }
    } else if (operator == SegmentOperator.LOE) {
      if (columnType == CommonColumnType.INTEGER || columnType == CommonColumnType.LONG) {
        // value 이 크거나 같은지.
        return new BigDecimal(value).compareTo(new BigDecimal(value1)) >= 0;
      } else {
        return value.compareToIgnoreCase(value1) >= 0;
      }
    } else if (operator == SegmentOperator.LIKE) {
      return value1.contains(value);
    }

    return false;
  }

  public void update(ProductType productType, String placement) {
    this.productType = productType;
    this.placement = placement;
  }
}
