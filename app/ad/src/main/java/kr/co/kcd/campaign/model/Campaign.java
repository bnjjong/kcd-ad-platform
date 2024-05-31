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
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import kr.co.kcd.campaign.dto.CampaignDto.AdGroupCondition;
import kr.co.kcd.shared.enumshared.ProductType;
import kr.co.kcd.shared.enumshared.YN;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Campaign {
  /**
   * 아이디.
   */
  @Id
  @UuidGenerator
  private String id;

  /**
   * 캠페인 상품.
   */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ProductType productType;

  /**
   * 지면 명.
   */
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
      List<AdGroupCondition> conditions) {
    AdGroup adGroup = new AdGroup(publishYn, startDate, endDate, BigDecimal.valueOf(priority),
        this);

    adGroup.addConditions(conditions);
    if (adGroupList == null) {
      adGroupList = new ArrayList<>();
    }
    adGroupList.add(adGroup);
  }


}
