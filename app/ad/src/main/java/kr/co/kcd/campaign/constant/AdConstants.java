/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/6/9
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

package kr.co.kcd.campaign.constant;

import kr.co.kcd.campaign.dto.AdResponseDto.SendAd.CreativeDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AdConstants {
  public static final int ALTERNATIVE_CREATIVE_ID = 999;
  public static final int ALTERNATIVE_ADGROUP_ID = 999;
  public static final CreativeDto alternativeCreative;


  static {
    alternativeCreative =
        new CreativeDto(
            ALTERNATIVE_ADGROUP_ID,
            ALTERNATIVE_CREATIVE_ID,
            "대체 광고",
            "노출할게 없을 경우 노출",
            "#292929",
            "#D9E8FF",
            "https://bluebird-asset.cashnote.kr/uploads/image/image/36714/resized_IBKBOX_360x360_20240227.png",
            "https://finance-bridge.cashnote.kr/?companyLink=https%3A%2F%2F365.ibkbox.net%3Futm_source%3Dcashnote%26utm_medium%3Daffiliate%26utm_campaign%3DLOAN_2402_MO%26utm_content%3D1&companyName=IBK%EA%B8%B0%EC%97%85%EC%9D%80%ED%96%89",
            99.9d);
  }
}
