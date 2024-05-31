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

package kr.co.kcd.shared.spring.common.response.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

/**
 * create on 2023/01/31.
 * create by IntelliJ IDEA.
 *
 * <p> This class contains about success response code. </p>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 * @see ResponseCode
 */

@ToString
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SuccessResponseCode implements ResponseCode {
  // Success
  OK(200, "S00", "OK"),
  CREATED(201, "S01", "CREATED"),
  ACCEPTED(202, "S02", "ACCEPTED"),
  NO_CONTENT(204, "S03", "NO CONTENT"),

  ;

  private final int statusCode;
  private final String code;
  private final String message;
//  @JsonIgnore private final HttpStatus httpStatus;

  @JsonCreator
  SuccessResponseCode(int statusCode, String code, String message
      ) {
    this.statusCode = statusCode;
    this.code = code;
    this.message = message;
//    this.httpStatus = httpStatus;
  }

  /**
   * return {@code SuccessResponseCode} by code.
   *
   * @param code code like S00, S01,
   * @return {@code SuccessResponseCode} that matched by code.
   */
  public static SuccessResponseCode getEnumByCodeValue(String code) {
    return (SuccessResponseCode) ResponseCode
        .getEnumByCodeValue(code, SuccessResponseCode.values());
  }
}
