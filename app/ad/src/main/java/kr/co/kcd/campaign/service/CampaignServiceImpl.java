/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/6/1
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

import kr.co.kcd.campaign.dto.CampaignDto;
import kr.co.kcd.campaign.dto.CampaignDto.AppendAdGroup;
import kr.co.kcd.campaign.model.Campaign;
import kr.co.kcd.campaign.repository.CampaignRepository;
import kr.co.kcd.shared.spring.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampaignServiceImpl implements CampaignService {
  private final CampaignRepository campaignRepository;

  @Transactional
  @Override
  public String create(CampaignDto.Create dto) {
    Campaign campaign = new Campaign(dto.getProductType(), dto.getPlacement());
    Campaign savedCampaign = campaignRepository.save(campaign);
    return savedCampaign.getId();
  }

  @Transactional
  @Override
  public void appendAdGroup(String campaignId, AppendAdGroup dto) {
    Campaign campaign = retrieveById(campaignId);
    campaign.appendAdGroup(
        dto.getPublishYn(),
        dto.getPriority(),
        dto.getStartDate(),
        dto.getEndDate(),
        dto.getConditions()
    );

  }


  private Campaign retrieveById(String id) {
    return campaignRepository.findById(id).orElseThrow(
        () -> new DataNotFoundException("campaign is not found by "+ id)
    );
  }
}
