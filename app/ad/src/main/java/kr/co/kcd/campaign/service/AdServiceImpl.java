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

package kr.co.kcd.campaign.service;

import java.util.ArrayList;
import java.util.List;
import kr.co.kcd.campaign.dto.AdRequestDto;
import kr.co.kcd.campaign.dto.AdResponseDto;
import kr.co.kcd.campaign.dto.AdResponseDto.SendAd.AdInfoDto;
import kr.co.kcd.campaign.dto.AdResponseDto.SendAd.CreativeDto;
import kr.co.kcd.campaign.model.AdGroup;
import kr.co.kcd.campaign.model.Campaign;
import kr.co.kcd.campaign.model.Creative;
import kr.co.kcd.user.dto.UserDto;
import kr.co.kcd.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdServiceImpl implements AdService {
  private final UserFacade userService;
  private final CampaignService campaignService;
  private static final CreativeDto alternativeCreative;
  static {
    // todo 배열로 처리 하거나 따로 디비에 넣어 두는 것도 좋을 듯.
    alternativeCreative = new CreativeDto(
        999,
        999,
        "대체 광고",
        "노출할게 없을 경우 노출",
        "#292929",
        "#D9E8FF",
        "https://bluebird-asset.cashnote.kr/uploads/image/image/36714/resized_IBKBOX_360x360_20240227.png",
        "https://finance-bridge.cashnote.kr/?companyLink=https%3A%2F%2F365.ibkbox.net%3Futm_source%3Dcashnote%26utm_medium%3Daffiliate%26utm_campaign%3DLOAN_2402_MO%26utm_content%3D1&companyName=IBK%EA%B8%B0%EC%97%85%EC%9D%80%ED%96%89",
        99.9d
    );
  }

  @Override
  @Transactional
  public AdResponseDto.SendAd sendAd(AdRequestDto.RetrieveAd dto) {
    UserDto.Retrieve user = getAudience(dto.getId());
    List<Campaign> campaigns = campaignService.retrieveEntityByPlacements(dto.getPlacements());

    List<AdInfoDto> adInfoDtos = new ArrayList<>();
    for (Campaign c : campaigns) {
      List<AdGroup> adGroups = c.targetAdGroupByAudience(user);
      List<CreativeDto> creatives = new ArrayList<>();
      for (AdGroup ag : adGroups) {
        for (Creative ct : ag.getCreatives()) {
          // filter view count
          // todo Campaign 안에서 필터되게 하면 더 좋았을 것 같음.
          if (ct.getLimitExposure() == 0 || ct.getViewCount() < ct.getLimitExposure()) {
            // 노출 수 증가.
            ct.increaseViewCount();
            creatives.add(
                new CreativeDto(
                    ag.getId(),
                    ct.getId(),
                    ct.getTitle(),
                    ct.getDescription(),
                    ct.getTextColor(),
                    ct.getBackgroundColor(),
                    ct.getBackgroundImage(),
                    ct.getUrl(),
                    ag.getPriority().doubleValue()
                )
            );
          }
        }
      }
      // 대체 이미지
      if (creatives.isEmpty()) {
        creatives.add(alternativeCreative);
      }
      adInfoDtos.add(new AdInfoDto(c.getPlacement(), creatives));
    }

    return new AdResponseDto.SendAd(adInfoDtos);


    // list to dto



  }

  private UserDto.Retrieve getAudience(String id) {
    return userService.retrieveById(id);
  }
}
