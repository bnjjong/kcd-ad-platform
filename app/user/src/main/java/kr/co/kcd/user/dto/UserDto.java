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

package kr.co.kcd.user.dto;


import kr.co.kcd.shared.enumshared.Classification;
import kr.co.kcd.shared.enumshared.Gender;
import kr.co.kcd.shared.enumshared.KoreaRegion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class UserDto {
  private String id;
  private String name;
  private int age;
  private Gender gender;
  private Classification classification;
  private KoreaRegion koreaRegion;
  private long monthlySales;


  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  public static class Create extends UserDto{
    public Create(String name, int age, Gender gender, Classification classification,
        KoreaRegion koreaRegion, long monthlySales) {
      super(null, name, age, gender, classification, koreaRegion, monthlySales);
    }

    @Override
    public String getName() {
      return super.getName();
    }

    @Override
    public int getAge() {
      return super.getAge();
    }

    @Override
    public Gender getGender() {
      return super.getGender();
    }

    @Override
    public Classification getClassification() {
      return super.getClassification();
    }

    @Override
    public KoreaRegion getKoreaRegion() {
      return super.getKoreaRegion();
    }

    @Override
    public long getMonthlySales() {
      return super.getMonthlySales();
    }
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @ToString
  public static class Retrieve extends UserDto {

    public Retrieve(String id, String name, int age, Gender gender, Classification classification,
        KoreaRegion koreaRegion, long monthlySales) {
      super(id, name, age, gender, classification, koreaRegion, monthlySales);
    }

    @Override
    public String getId() {
      return super.getId();
    }

    @Override
    public String getName() {
      return super.getName();
    }

    @Override
    public int getAge() {
      return super.getAge();
    }

    @Override
    public Gender getGender() {
      return super.getGender();
    }

    @Override
    public Classification getClassification() {
      return super.getClassification();
    }

    @Override
    public KoreaRegion getKoreaRegion() {
      return super.getKoreaRegion();
    }

    @Override
    public long getMonthlySales() {
      return super.getMonthlySales();
    }
  }
}
