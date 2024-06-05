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

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import kr.co.kcd.shared.enumshared.ProductType;
import kr.co.kcd.shared.enumshared.SegmentOperator;
import kr.co.kcd.shared.enumshared.YN;
import kr.co.kcd.user.model.UserColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CampaignRequestDto {


  // =========================== inner ===========================

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class Create  {
    private ProductType productType;
    private String placement;

    public Create(@NonNull ProductType productType, @NonNull String placement) {
      this.productType = productType;
      this.placement = placement;
    }
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class Update {
    @NotNull(message = "\"productType\" is not null.")
    private ProductType productType;
    @NotNull(message = "\"placement\" is not null.")
    private String placement;

    public Update(ProductType productType, String placement) {
      this.productType = productType;
      this.placement = placement;
    }
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class AppendAdGroup {
    @NotNull(message = "\"publishYn\" is not null.")
    private YN publishYn;
    private LocalDate startDate;
    private LocalDate endDate;

    @Min(value = 0, message = "The priority value must be at least 0")
    private double priority;
    @Size(min = 1, max = 10, message = "The number of items must be between 1 and 10")
    private List<AdGroupCondition> conditions;

    public AppendAdGroup(YN publishYn, LocalDate startDate, LocalDate endDate, double priority,
        List<AdGroupCondition> conditions) {
      this.publishYn = publishYn;
      this.startDate = startDate;
      this.endDate = endDate;
      this.priority = priority;
      this.conditions = conditions;
    }
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class UpdateAdGroup {
    @NotNull(message = "\"publishYn\" is not null.")
    private YN publishYn;
    private LocalDate startDate;
    private LocalDate endDate;
    @Min(value = 0, message = "The priority value must be at least 0")
    private double priority;

    public UpdateAdGroup(YN publishYn, LocalDate startDate, LocalDate endDate, double priority) {
      this.publishYn = publishYn;
      this.startDate = startDate;
      this.endDate = endDate;
      this.priority = priority;
    }
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class AdGroupCondition {
    @NotNull(message = "\"column of condition\" is not null.")
    private UserColumn column;
    @NotNull(message = "\"operator of condition\" is not null.")
    private SegmentOperator operator;
    @NotNull(message = "\"value of condition\" is not null.")
    private String value;

    public AdGroupCondition(UserColumn column, SegmentOperator operator, String value) {
      this.column = column;
      this.operator = operator;
      this.value = value;
    }
  }


  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class AppendCreative extends CampaignRequestDto {
    private String title;
    private String description;
    private String textColor;
    private String backgroundColor;
    private String backgroundImage;
    private String url;

    public AppendCreative(String title, String description, String textColor,
        String backgroundColor, String backgroundImage, String url) {
      this.title = title;
      this.description = description;
      this.textColor = textColor;
      this.backgroundColor = backgroundColor;
      this.backgroundImage = backgroundImage;
      this.url = url;
    }
  }

}
