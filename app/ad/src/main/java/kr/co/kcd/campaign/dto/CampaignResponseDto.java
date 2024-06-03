/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/5/31
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

package kr.co.kcd.campaign.dto;

import java.time.LocalDate;
import java.util.List;
import kr.co.kcd.campaign.dto.CampaignRequestDto.AdGroupCondition;
import kr.co.kcd.shared.enumshared.ProductType;
import kr.co.kcd.shared.enumshared.YN;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CampaignResponseDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class Retrieve {
    private ProductType productType;
    private String placement;

    public Retrieve(ProductType productType, String placement) {
      this.productType = productType;
      this.placement = placement;
    }
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class RetrieveAdGroup {
    private long id;
    private YN publishYn;
    private double priority;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<AdGroupCondition> conditions;

    public RetrieveAdGroup(long id, YN publishYn, double priority, LocalDate startDate,
        LocalDate endDate, List<AdGroupCondition> conditions) {
      this.id = id;
      this.publishYn = publishYn;
      this.priority = priority;
      this.startDate = startDate;
      this.endDate = endDate;
      this.conditions = conditions;
    }
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class RetrieveCreative {
    private long id;
    private String title;
    private String description;
    private String textColor;
    private String backgroundColor;
    private String backgroundImage;
    private String url;

    public RetrieveCreative(long id, String title, String description, String textColor,
        String backgroundColor, String backgroundImage, String url) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.textColor = textColor;
      this.backgroundColor = backgroundColor;
      this.backgroundImage = backgroundImage;
      this.url = url;
    }
  }
}
