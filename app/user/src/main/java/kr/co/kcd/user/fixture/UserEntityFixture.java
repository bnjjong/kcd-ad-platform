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

package kr.co.kcd.user.fixture;

import java.math.BigDecimal;
import java.util.Random;
import kr.co.kcd.shared.enumshared.Classification;
import kr.co.kcd.shared.enumshared.Gender;
import kr.co.kcd.shared.enumshared.KoreaRegion;
import kr.co.kcd.user.model.User;

public interface UserEntityFixture {
//  static User cafe1() {
//    return new User("강백호", 25, Gender.MAN, Classification.CAFE, KoreaRegion.SEOUL, 50000000d);
//  }

  Random random = new Random();

  String[] LAST_NAMES = {"김", "박", "이", "정", "최", "황", "류", "한", "조", "강"};
  String[] FIRST_NAMES_MALE = {"민수", "준호", "태형", "지훈", "석진", "윤기", "호석", "남준", "진", "정국"};
  String[] FIRST_NAMES_FEMALE = {"서연", "지수", "다현", "채영", "정연", "나연", "소미", "민아", "유나", "쯔위"};

  static User randomUser() {
    String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    String firstName;
    Gender gender = random.nextBoolean() ? Gender.MAN : Gender.WOMAN;
    if (gender == Gender.MAN) {
      firstName = FIRST_NAMES_MALE[random.nextInt(FIRST_NAMES_MALE.length)];
    } else {
      firstName = FIRST_NAMES_FEMALE[random.nextInt(FIRST_NAMES_FEMALE.length)];
    }
    int age = random.nextInt(47) + 18; // 18 ~ 65
    Classification classification =
        Classification.values()[random.nextInt(Classification.values().length)];
    KoreaRegion region = KoreaRegion.values()[random.nextInt(KoreaRegion.values().length)];
    long income = (long) ((random.nextDouble() + 0.1) * 100000000);
    return new User(lastName + " " + firstName, age, gender, classification, region, new BigDecimal(income));
  }
}
