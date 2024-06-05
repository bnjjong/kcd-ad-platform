/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/6/4
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

package kr.co.kcd.campaign.fixture;

import java.time.LocalDate;
import java.util.List;
import kr.co.kcd.campaign.dto.CampaignRequestDto;
import kr.co.kcd.campaign.dto.CampaignRequestDto.AdGroupCondition;
import kr.co.kcd.shared.enumshared.ProductType;
import kr.co.kcd.shared.enumshared.SegmentOperator;
import kr.co.kcd.shared.enumshared.YN;
import kr.co.kcd.user.model.UserColumn;

public interface CampaignDtoFixture {
  static CampaignRequestDto.Create bankingTop() {
    return new CampaignRequestDto.Create(ProductType.BANNER, "banking_top");
  }

  static CampaignRequestDto.Create bankingBottom() {
    return new CampaignRequestDto.Create(ProductType.BANNER, "banking_bottom");
  }

  static CampaignRequestDto.Create communityTop() {
    return new CampaignRequestDto.Create(ProductType.BANNER, "community_top");
  }

  static CampaignRequestDto.Create communityBottom() {
    return new CampaignRequestDto.Create(ProductType.BANNER, "community_bottom");
  }

  static CampaignRequestDto.AppendAdGroup adGroup1() {
    return new CampaignRequestDto.AppendAdGroup(
        YN.Y,
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 12, 31),
        1.0d,
        List.of(
            new AdGroupCondition(UserColumn.AGE, SegmentOperator.GOE, "30"),
            new AdGroupCondition(UserColumn.AGE, SegmentOperator.LOE, "50")));
  }

  static CampaignRequestDto.AppendAdGroup adGroup2() {
    return new CampaignRequestDto.AppendAdGroup(
        YN.Y,
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 12, 31),
        1.2d,
        List.of(new AdGroupCondition(UserColumn.MONTHLY_SALES, SegmentOperator.GOE, "30000000")));
  }

  static CampaignRequestDto.AppendAdGroup adGroup3() {
    return new CampaignRequestDto.AppendAdGroup(
        YN.Y,
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 12, 31),
        1.2d,
        List.of(new AdGroupCondition(UserColumn.KOREA_REGION, SegmentOperator.EQ, "SEOUL")));
  }

  static CampaignRequestDto.AppendAdGroup adGroup4() {
    return new CampaignRequestDto.AppendAdGroup(
        YN.Y,
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 12, 31),
        1.4d,
        List.of(new AdGroupCondition(UserColumn.GENDER, SegmentOperator.EQ, "MAN")));
  }

  static CampaignRequestDto.AppendAdGroup adGroup5() {
    return new CampaignRequestDto.AppendAdGroup(
        YN.Y,
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 12, 31),
        1.4d,
        List.of(new AdGroupCondition(UserColumn.CLASSIFICATION, SegmentOperator.EQ, "CAFE")));
  }

  static CampaignRequestDto.AppendAdGroup adGroup6() {
    return new CampaignRequestDto.AppendAdGroup(
        YN.Y,
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 5, 30),
        1.6d,
        List.of(new AdGroupCondition(UserColumn.MONTHLY_SALES, SegmentOperator.GOE, "10000000")));
  }




  static CampaignRequestDto.AppendCreative creative1() {
    return new CampaignRequestDto.AppendCreative(
        "k2뱅크 특판",
        "30-40대 사장님들을 위한 2%대 금리",
        "#292929",
        "#D9E8FF",
        "https://bluebird-asset.cashnote.kr/uploads/image/image/36714/resized_IBKBOX_360x360_20240227.png",
        "https://finance-bridge.cashnote.kr/?companyLink=https%3A%2F%2F365.ibkbox.net%3Futm_source%3Dcashnote%26utm_medium%3Daffiliate%26utm_campaign%3DLOAN_2402_MO%26utm_content%3D1&companyName=IBK%EA%B8%B0%EC%97%85%EC%9D%80%ED%96%89");
  }

  static CampaignRequestDto.AppendCreative creative2() {
    return new CampaignRequestDto.AppendCreative(
        "k뱅크 특판",
        "30대 사장님들을 위한 4%대 금리",
        "#292929",
        "#D9E8FF",
        "https://bluebird-asset.cashnote.kr/uploads/image/image/36714/resized_IBKBOX_360x360_20240227.png",
        "https://finance-bridge.cashnote.kr/?companyLink=https%3A%2F%2F365.ibkbox.net%3Futm_source%3Dcashnote%26utm_medium%3Daffiliate%26utm_campaign%3DLOAN_2402_MO%26utm_content%3D1&companyName=IBK%EA%B8%B0%EC%97%85%EC%9D%80%ED%96%89"
    );
  }

  static CampaignRequestDto.AppendCreative creative3() {
    return new CampaignRequestDto.AppendCreative(
        "중소기업청 지원",
        "조건없이 2% 금리",
        "#292929",
        "#D9E8FF",
        "https://bluebird-asset.cashnote.kr/uploads/image/image/36714/resized_IBKBOX_360x360_20240227.png",
        "https://finance-bridge.cashnote.kr/?companyLink=https%3A%2F%2F365.ibkbox.net%3Futm_source%3Dcashnote%26utm_medium%3Daffiliate%26utm_campaign%3DLOAN_2402_MO%26utm_content%3D1&companyName=IBK%EA%B8%B0%EC%97%85%EC%9D%80%ED%96%89"
    );
  }


  static CampaignRequestDto.AppendCreative creative4() {
    return new CampaignRequestDto.AppendCreative(
        "갈아타기 우대 금리 지원",
        "2금융권 최저 신용도 가능",
        "#292929",
        "#D9E8FF",
        "https://bluebird-asset.cashnote.kr/uploads/image/image/36714/resized_IBKBOX_360x360_20240227.png",
        "https://finance-bridge.cashnote.kr/?companyLink=https%3A%2F%2F365.ibkbox.net%3Futm_source%3Dcashnote%26utm_medium%3Daffiliate%26utm_campaign%3DLOAN_2402_MO%26utm_content%3D1&companyName=IBK%EA%B8%B0%EC%97%85%EC%9D%80%ED%96%89"
    );
  }
}
