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

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdResponseDto {


  // =========================== inner ===========================
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  @Getter
  public static class SendAd {
    private List<AdInfoDto> placements;

    public SendAd(List<AdInfoDto> placements) {
      this.placements = placements;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    @Getter
    public static class AdInfoDto {
      private String name;
      private List<CreativeDto> creatives;

      public AdInfoDto(String name, List<CreativeDto> creatives) {
        this.name = name;
        this.creatives = creatives;
      }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    @Getter
    public static class CreativeDto {
      private long ad_group_id;
      private long creative_id;
      private String title;
      private String description;
      private String textColor;
      private String backgroundColor;
      private String backgroundImage;
      private String url;
      private double priority;

      public CreativeDto(long ad_group_id, long creative_id, String title, String description,
          String textColor, String backgroundColor, String backgroundImage, String url,
          double priority) {
        this.ad_group_id = ad_group_id;
        this.creative_id = creative_id;
        this.title = title;
        this.description = description;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.backgroundImage = backgroundImage;
        this.url = url;
        this.priority = priority;
      }
    }
  }
}
