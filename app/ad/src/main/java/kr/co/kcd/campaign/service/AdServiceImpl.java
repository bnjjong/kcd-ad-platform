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
import kr.co.kcd.campaign.dto.AdRequestDto.RetrieveAd;
import kr.co.kcd.campaign.dto.AdResponseDto.SendAd;
import kr.co.kcd.campaign.dto.AdResponseDto.SendAd.AdInfoDto;
import kr.co.kcd.campaign.dto.AdResponseDto.SendAd.CreativeDto;
import kr.co.kcd.campaign.model.AdGroup;
import kr.co.kcd.campaign.model.AudienceCondition;
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

  @Override
  public SendAd sendAd(AdRequestDto.RetrieveAd dto) {
    UserDto.Retrieve user = getAudience(dto.getId());
    List<Campaign> campaigns = campaignService.retrieveEntityByPlacements(dto.getPlacements());

    List<AdInfoDto> adInfoDtos = new ArrayList<>();
    for (Campaign c : campaigns) {
      List<AdGroup> adGroups = c.findAdGroupByAudience(user);
      List<CreativeDto> creatives = new ArrayList<>();
      for (AdGroup ag : adGroups) {
        for (Creative ct : ag.getCreatives()) {
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
      adInfoDtos.add(new AdInfoDto(c.getPlacement(), creatives));
    }

    return new SendAd(adInfoDtos);


    // list to dto



  }

  private UserDto.Retrieve getAudience(String id) {
    return userService.retrieveById(id);
  }
}
