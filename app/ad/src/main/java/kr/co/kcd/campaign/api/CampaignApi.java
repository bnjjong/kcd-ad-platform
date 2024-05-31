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

import kr.co.kcd.campaign.dto.CampaignDto;
import kr.co.kcd.campaign.service.CampaignService;
import kr.co.kcd.shared.spring.common.response.ResponseCommonEntity;
import kr.co.kcd.shared.spring.common.response.success.ResponseOK;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/campaign")
@RestController
@RequiredArgsConstructor
public class CampaignApi {
  private final CampaignService campaignService;

  @PostMapping("/by-user")
  public ResponseCommonEntity<CampaignDto.Retrieve> retrieveCampaign(@RequestBody CampaignDto.Retrieve dto) {
    return new ResponseCommonEntity<>(
        new ResponseOK<>("", null)
    );
  }

  @PostMapping("")
  public ResponseCommonEntity<String> create(@RequestBody CampaignDto.Create dto) {
    String id = campaignService.create(dto);
    return new ResponseCommonEntity<>(new ResponseOK<>("", id));
  }

  @PostMapping("/{campaignId}/ad-group")
  public ResponseCommonEntity<Void> appendAdGroup(@PathVariable String campaignId,
      @RequestBody CampaignDto.AppendAdGroup dto) {
    campaignService.appendAdGroup(campaignId, dto);
    return new ResponseCommonEntity<>(new ResponseOK<>(""));
  }


}
