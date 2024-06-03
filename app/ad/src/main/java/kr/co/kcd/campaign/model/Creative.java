/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/5/30
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

package kr.co.kcd.campaign.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Creative {
  /**
   * id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  // ============== parent ==============
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ad_group_id", nullable = false)
  @ToString.Exclude
  private AdGroup adGroup;
  // ============== parent ==============

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column
  private String textColor;

  @Column
  private String backgroundColor;

  @Column
  private String backgroundImage;

  @Column(name = "landing_url")
  private String url;

  Creative(AdGroup adGroup, String title, String description, String textColor,
      String backgroundColor, String backgroundImage, String url) {
    this.adGroup = adGroup;
    this.title = title;
    this.description = description;
    this.textColor = textColor;
    this.backgroundColor = backgroundColor;
    this.backgroundImage = backgroundImage;
    this.url = url;
  }

  public void update(String title, String description, String textColor, String backgroundColor, String backgroundImage, String url) {
    this.title = title;
    this.description = description;
    this.textColor = textColor;
    this.backgroundColor = backgroundColor;
    this.backgroundImage = backgroundImage;
    this.url = url;
  }
}
