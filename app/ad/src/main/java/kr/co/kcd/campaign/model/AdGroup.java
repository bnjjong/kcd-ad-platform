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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import kr.co.kcd.campaign.dto.CampaignDto.AdGroupCondition;
import kr.co.kcd.shared.enumshared.YN;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdGroup {

  /**
   * id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  /**
   * publish status, save only Y or N.
   */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private YN publishYn = YN.Y;

  /**
   * 게재 기간 시작일.
   */
  @Column
  private LocalDate startDate;

  /**
   * 게재 기간 종료일.
   */
  @Column
  private LocalDate endDate;

  @Column
  @Digits(integer=3, fraction=1) // 정수는 최대 3자리, 소수는 1자리로 제한
  private BigDecimal priority;

  // ============== parent ==============
  /**
   * Campaign.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "campaign_id", nullable = false)
  @ToString.Exclude
  private Campaign campaign;
  // ============== parent ==============


  // ============== child ==============
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "adGroup")
  @ToString.Exclude
  private List<AudienceCondition> conditions;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "adGroup")
  @ToString.Exclude
  private List<Creative> creatives;
  // ============== child ==============


  AdGroup(YN publishYn, LocalDate startDate, LocalDate endDate, BigDecimal priority,
      Campaign campaign) {
    this.publishYn = publishYn;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priority = priority;
    this.campaign = campaign;
  }

  void addConditions(List<AdGroupCondition> dtoConditions) {
    if (conditions == null) {
      conditions = new ArrayList<>();
    }
    for (AdGroupCondition c : dtoConditions) {
      conditions.add(new AudienceCondition(this, c.getColumn(), c.getOperator(), c.getValue()));
    }
  }
}
