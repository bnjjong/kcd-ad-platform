/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/5/27
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

package kr.co.kcd.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import kr.co.kcd.shared.enumshared.Classification;
import kr.co.kcd.shared.enumshared.Gender;
import kr.co.kcd.shared.enumshared.KoreaRegion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  /**
   * 아이디.
   */
  @Id
  @UuidGenerator
  private String id;

  /**
   * 이름
   */
  @Column(length = 50, nullable = false)
  private String name;

  /**
   * 나이
   */
  @Column(nullable = false)
  @Min(value = 0, message = "A \"age\" field is not less than 0")
  private Integer age;


  /**
   * 성별
   */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;


  /**
   * 업종
   */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Classification classification;


  /**
   * 지역(시도)
   */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private KoreaRegion koreaRegion;

  /**
   * 사업정 월 매출
   */
  @Column(nullable = false)
  @Min(value = 0, message = "A \"age\" field is not less than 0")
  private double monthlySales;

  public User(String name, Integer age, Gender gender, Classification classification,
      KoreaRegion koreaRegion, double monthlySales) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.classification = classification;
    this.koreaRegion = koreaRegion;
    this.monthlySales = monthlySales;
  }
}
