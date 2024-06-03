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

import java.util.List;
import kr.co.kcd.campaign.dto.CampaignRequestDto;
import kr.co.kcd.campaign.dto.CampaignRequestDto.AdGroupCondition;
import kr.co.kcd.campaign.dto.CampaignRequestDto.AppendCreative;
import kr.co.kcd.campaign.dto.CampaignRequestDto.Create;
import kr.co.kcd.campaign.dto.CampaignRequestDto.UpdateAdGroup;
import kr.co.kcd.campaign.dto.CampaignResponseDto;
import kr.co.kcd.campaign.dto.CampaignResponseDto.Retrieve;
import kr.co.kcd.campaign.dto.CampaignResponseDto.RetrieveAdGroup;
import kr.co.kcd.campaign.dto.CampaignResponseDto.RetrieveCreative;
import kr.co.kcd.campaign.model.AdGroup;
import kr.co.kcd.campaign.model.Campaign;
import kr.co.kcd.campaign.model.Creative;
import kr.co.kcd.campaign.repository.AdGroupRepository;
import kr.co.kcd.campaign.repository.CampaignRepository;
import kr.co.kcd.campaign.repository.CreativeRepository;
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
  private final AdGroupRepository adGroupRepository;
  private final CreativeRepository creativeRepository;


  @Transactional
  @Override
  public String create(CampaignRequestDto.Create dto) {
    Campaign campaign = new Campaign(dto.getProductType(), dto.getPlacement());
    Campaign savedCampaign = campaignRepository.save(campaign);
    return savedCampaign.getId();
  }

  @Transactional
  @Override
  public void appendAdGroup(String campaignId, CampaignRequestDto.AppendAdGroup dto) {
    Campaign campaign = retrieveEntityById(campaignId);
    campaign.appendAdGroup(
        dto.getPublishYn(),
        dto.getPriority(),
        dto.getStartDate(),
        dto.getEndDate(),
        dto.getConditions());
  }

  @Transactional
  @Override
  public void appendCreative(String campaignId, Long adGroupId, CampaignRequestDto.AppendCreative dto) {
    Campaign campaign = retrieveEntityById(campaignId);
    campaign.appendCreative(
        adGroupId,
        dto.getTitle(),
        dto.getDescription(),
        dto.getTextColor(),
        dto.getBackgroundColor(),
        dto.getBackgroundImage(),
        dto.getUrl());
  }

  @Override
  public CampaignResponseDto.Retrieve retrieveById(String campaignId) {
    Campaign campaign = retrieveEntityById(campaignId);
    return new Retrieve(campaign.getProductType(), campaign.getPlacement());
  }

  @Override
  public List<RetrieveAdGroup> retrieveAdGroups(String campaignId) {
    Campaign campaign = retrieveEntityById(campaignId);
    return campaign.getAdGroupList()
        .stream()
        .map(ag -> new RetrieveAdGroup(
            ag.getId(),
            ag.getPublishYn(),
            ag.getPriority().doubleValue(),
            ag.getStartDate(),
            ag.getEndDate(),
            ag.getConditions().stream()
                .map(c -> new AdGroupCondition(
                    c.getColumn(), c.getOperator(), c.getValue()
                )).toList()
        )).toList();
  }

  @Override
  public List<RetrieveCreative> retrieveCreatives(String campaignId, long adGroupId) {
    Campaign campaign = retrieveEntityById(campaignId);
    AdGroup adGroup = campaign.findAdGroupById(adGroupId);
    return adGroup.getCreatives()
        .stream()
        .map(c -> new RetrieveCreative(
            c.getId(),
            c.getTitle(),
            c.getDescription(),
            c.getTextColor(),
            c.getBackgroundColor(),
            c.getBackgroundImage(),
            c.getUrl())
        ).toList();
  }

  @Transactional
  @Override
  public void update(String campaignId, Create dto) {
    Campaign campaign = retrieveEntityById(campaignId);
    campaign.update(dto.getProductType(), dto.getPlacement());
  }

  @Transactional
  @Override
  public void updateAdGroup(String campaignId, Long adGroupId, UpdateAdGroup dto) {
    Campaign campaign = retrieveEntityById(campaignId);
    AdGroup adGroup = campaign.findAdGroupById(adGroupId);
    adGroup.update(
        dto.getPublishYn(),
        dto.getStartDate(),
        dto.getEndDate(),
        dto.getPriority()
    );

  }

  @Transactional
  @Override
  public void updateCreative(String campaignId, Long adGroupId, Long creativeId,
      AppendCreative dto) {
    Campaign campaign = retrieveEntityById(campaignId);
    AdGroup adGroup = campaign.findAdGroupById(adGroupId);
    Creative creative = adGroup.findCreativeById(creativeId);
    creative.update(
        dto.getTitle(),
        dto.getDescription(),
        dto.getTextColor(),
        dto.getBackgroundColor(),
        dto.getBackgroundImage(),
        dto.getUrl()
        );

  }

  @Transactional
  @Override
  public void delete(String campaignId) {
    Campaign campaign = retrieveEntityById(campaignId);
    campaignRepository.delete(campaign);

  }

  @Transactional
  @Override
  public void deleteAdGroup(String campaignId, Long adGroupId) {
    AdGroup adGroup = adGroupRepository.findById(adGroupId)
        .orElseThrow(() -> new DataNotFoundException("ad group is not found by " + adGroupId));
    adGroupRepository.delete(adGroup);
//    Campaign campaign = retrieveEntityById(campaignId);
//    campaign.deleteAdGroup(adGroupId);
//    campaignRepository.save(campaign);
  }

  @Override
  public void deleteCreative(String campaignId, Long adGroupId, Long creativeId) {
    Creative creative = creativeRepository.findById(creativeId)
        .orElseThrow(() -> new DataNotFoundException("creative is not found by " + creativeId));
    creativeRepository.delete(creative);
  }

  private Campaign retrieveEntityById(String id) {
    return campaignRepository
        .findById(id)
        .orElseThrow(() -> new DataNotFoundException("campaign is not found by " + id));
  }
}
