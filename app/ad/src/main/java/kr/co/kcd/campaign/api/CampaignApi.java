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

package kr.co.kcd.campaign.api;

import jakarta.validation.Valid;
import java.util.List;
import kr.co.kcd.campaign.dto.CampaignRequestDto;
import kr.co.kcd.campaign.dto.CampaignResponseDto;
import kr.co.kcd.campaign.dto.CampaignResponseDto.RetrieveAdGroup;
import kr.co.kcd.campaign.dto.CampaignResponseDto.RetrieveCreative;
import kr.co.kcd.campaign.service.CampaignService;
import kr.co.kcd.shared.spring.common.response.ResponseCommonEntity;
import kr.co.kcd.shared.spring.common.response.success.ResponseOK;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/campaign")
@RestController
@RequiredArgsConstructor
public class CampaignApi {
  private final CampaignService campaignService;

  @PostMapping("/by-user")
  public ResponseCommonEntity<CampaignRequestDto.RetrieveAd> retrieveCampaign(@RequestBody CampaignRequestDto.RetrieveAd dto) {
    return new ResponseCommonEntity<>(
        new ResponseOK<>("", null)
    );
  }

  @PostMapping("")
  public ResponseCommonEntity<String> create(@RequestBody CampaignRequestDto.Create dto) {
    String id = campaignService.create(dto);
    return new ResponseCommonEntity<>(new ResponseOK<>("", id));
  }

  @PutMapping("/{campaignId}")
  public ResponseCommonEntity<String> create(@PathVariable String campaignId, @RequestBody CampaignRequestDto.Create dto) {
    campaignService.update(campaignId, dto);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }

  @DeleteMapping("/{campaignId}")
  public ResponseCommonEntity<String> delete(@PathVariable String campaignId) {
    campaignService.delete(campaignId);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }

  @GetMapping("/{campaignId}")
  public ResponseCommonEntity<CampaignResponseDto.Retrieve> retrieve(@PathVariable String campaignId) {
    CampaignResponseDto.Retrieve dto = campaignService.retrieveById(campaignId);
    return new ResponseCommonEntity<>(new ResponseOK<>("", dto));
  }




  @PostMapping("/{campaignId}/ad-group")
  public ResponseCommonEntity<Void> appendAdGroup(@PathVariable String campaignId,
      @RequestBody @Valid CampaignRequestDto.AppendAdGroup dto) {
    campaignService.appendAdGroup(campaignId, dto);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }

  @PutMapping("/{campaignId}/{adGroupId}")
  public ResponseCommonEntity<Void> updateAdGroup(@PathVariable String campaignId,
      @PathVariable Long adGroupId,
      @RequestBody @Valid CampaignRequestDto.UpdateAdGroup dto) {
    campaignService.updateAdGroup(campaignId, adGroupId, dto);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }

  @DeleteMapping("/{campaignId}/{adGroupId}")
  public ResponseCommonEntity<Void> deleteAdGroup(@PathVariable String campaignId,
      @PathVariable Long adGroupId) {
    campaignService.deleteAdGroup(campaignId, adGroupId);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }


  @GetMapping("/{campaignId}/ad-groups")
  public ResponseCommonEntity<List<RetrieveAdGroup>> retrieveAdGroups(@PathVariable String campaignId) {
    List<CampaignResponseDto.RetrieveAdGroup> dto = campaignService.retrieveAdGroups(campaignId);
    return new ResponseCommonEntity<>(new ResponseOK<>("", dto));
  }










  @PostMapping("/{campaignId}/{adGroupId}/creative")
  public ResponseCommonEntity<Void> appendCreative(
      @PathVariable String campaignId,
      @PathVariable Long adGroupId,
      @RequestBody @Valid CampaignRequestDto.AppendCreative dto) {
    campaignService.appendCreative(campaignId, adGroupId, dto);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }

  @PutMapping("/{campaignId}/{adGroupId}/creative/{creativeId}")
  public ResponseCommonEntity<Void> updateCreative(
      @PathVariable String campaignId,
      @PathVariable Long adGroupId,
      @PathVariable Long creativeId,
      @RequestBody @Valid CampaignRequestDto.AppendCreative dto) {
    campaignService.updateCreative(campaignId, adGroupId, creativeId, dto);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }

  @DeleteMapping("/{campaignId}/{adGroupId}/creative/{creativeId}")
  public ResponseCommonEntity<Void> deleteCreative(
      @PathVariable String campaignId,
      @PathVariable Long adGroupId,
      @PathVariable Long creativeId) {
    campaignService.deleteCreative(campaignId, adGroupId, creativeId);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }

  @GetMapping("/{campaignId}/{adGroupId}/creatives")
  public ResponseCommonEntity<List<RetrieveCreative>> retrieveCreative(
      @PathVariable String campaignId, @PathVariable long adGroupId) {
    List<CampaignResponseDto.RetrieveCreative> dto = campaignService.retrieveCreatives(campaignId, adGroupId);
    return new ResponseCommonEntity<>(new ResponseOK<>("", dto));
  }




}
