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

package kr.co.kcd.campaign.init;

import jakarta.annotation.PostConstruct;
import java.util.List;
import kr.co.kcd.campaign.dto.CampaignResponseDto.RetrieveAdGroup;
import kr.co.kcd.campaign.fixture.CampaignDtoFixture;
import kr.co.kcd.campaign.service.CampaignService;
import kr.co.kcd.user.fixture.UserEntityFixture;
import kr.co.kcd.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"local-dev"})
@RequiredArgsConstructor
@Slf4j
public class InitData {
  private final UserRepository userRepository;
  private final CampaignService campaignService;

  @PostConstruct
  public void init() {
    try {
      // user
      for (int i=0; i<20; i++) {
        userRepository.save(UserEntityFixture.randomUser());
      }

      // campaign 1 setting
      String bankingTopId = campaignService.create(CampaignDtoFixture.bankingTop());


      campaignService.appendAdGroup(bankingTopId, CampaignDtoFixture.adGroup1());
      campaignService.appendAdGroup(bankingTopId, CampaignDtoFixture.adGroup2());
      campaignService.appendAdGroup(bankingTopId, CampaignDtoFixture.adGroup3());

      // insert creative
      List<RetrieveAdGroup> retrieveAdGroups = campaignService.retrieveAdGroups(bankingTopId);
      campaignService.appendCreative(bankingTopId, retrieveAdGroups.get(0).getId(),
          CampaignDtoFixture.creative1());
      campaignService.appendCreative(bankingTopId, retrieveAdGroups.get(0).getId(),
          CampaignDtoFixture.creative2());
      campaignService.appendCreative(bankingTopId, retrieveAdGroups.get(1).getId(),
          CampaignDtoFixture.creative3());
      campaignService.appendCreative(bankingTopId, retrieveAdGroups.get(1).getId(),
          CampaignDtoFixture.creative4());
      campaignService.appendCreative(bankingTopId, retrieveAdGroups.get(2).getId(),
          CampaignDtoFixture.creative1());
      campaignService.appendCreative(bankingTopId, retrieveAdGroups.get(2).getId(),
          CampaignDtoFixture.creative2());




      // campaign 2 setting
      String bankingBottomId = campaignService.create(CampaignDtoFixture.bankingBottom());
      campaignService.appendAdGroup(bankingBottomId, CampaignDtoFixture.adGroup4());
      campaignService.appendAdGroup(bankingBottomId, CampaignDtoFixture.adGroup5());
      campaignService.appendAdGroup(bankingBottomId, CampaignDtoFixture.adGroup6());
      retrieveAdGroups = campaignService.retrieveAdGroups(bankingBottomId);
      campaignService.appendCreative(bankingBottomId, retrieveAdGroups.get(0).getId(),
          CampaignDtoFixture.creative1());
      campaignService.appendCreative(bankingBottomId, retrieveAdGroups.get(0).getId(),
          CampaignDtoFixture.creative2());
      campaignService.appendCreative(bankingBottomId, retrieveAdGroups.get(1).getId(),
          CampaignDtoFixture.creative3());
      campaignService.appendCreative(bankingBottomId, retrieveAdGroups.get(1).getId(),
          CampaignDtoFixture.creative4());
      campaignService.appendCreative(bankingBottomId, retrieveAdGroups.get(2).getId(),
          CampaignDtoFixture.creative3());
      campaignService.appendCreative(bankingBottomId, retrieveAdGroups.get(2).getId(),
          CampaignDtoFixture.creative4());




      String CommunityTopId = campaignService.create(CampaignDtoFixture.communityTop());
      String CommunityBottomId = campaignService.create(CampaignDtoFixture.communityBottom());


    } catch (Exception e) {

    }
  }
}
