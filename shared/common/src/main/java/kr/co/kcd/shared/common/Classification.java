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

package kr.co.kcd.shared.common;

public enum Classification {
  CAFE("커피숍"),
  CONVENIENCE_STORE("편의점"),
  LAUNDRY("세탁소"),
  FLORIST("꽃집"),
  BOOKSTORE("서점"),
  BAKERY("빵집"),
  FASHION_STORE("패션 의류점"),
  NAIL_SALON("네일숍"),
  FITNESS_CENTER("피트니스 센터"),
  HEALTH_CLINIC("헬스 클리닉"),
  VETERINARY("수의사"),
  PHOTO_STUDIO("사진관"),
  OPTICIAN("안경점"),
  STATIONERY_STORE("문구점"),
  ELECTRONICS_REPAIR("전자제품 수리점");

  private final String koreanName;

  Classification(String koreanName) {
    this.koreanName = koreanName;
  }

  public String getKoreanName() {
    return koreanName;
  }

  public static Classification fromKoreanName(String koreanName) {
    for (Classification type : Classification.values()) {
      if (type.getKoreanName().equals(koreanName)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Invalid Korean name: " + koreanName);
  }
}
