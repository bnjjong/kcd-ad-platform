/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/1/15
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

package kr.co.kcd.shared.spring.common.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kr.co.kcd.shared.spring.common.response.code.ErrorResponseCode;
import kr.co.kcd.shared.spring.common.response.code.ResponseCode;
import kr.co.kcd.shared.spring.common.response.code.SuccessResponseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * <p> Common response data class. </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @since 1.0
 * @see ResponseCommonEntity
 * @see ErrorResponseCode
 * @see SuccessResponseCode
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData<T> {

  /**
   * Response Code 이지만 객체 이다.
   */
//  @JsonProperty("response_code")
  private ResponseCode responseCode;

  /**
   * Response 할 때 메세지 정보.
   */
  private String message;

  /**
   * 데이터 조회일 경우 조회된 데이터.
   */
  @JsonInclude(Include.NON_NULL)
  @Setter(AccessLevel.PACKAGE)
  private T data;

  /**
   * construct by all argument.
   *
   * @param responseCode response code.
   * @param message response message.
   * @param data additional data of response.
   */

  public ResponseData(@NonNull ResponseCode responseCode, @NonNull String message, T data) {
    this.responseCode = responseCode;
    this.message = message;
    this.data = data;
  }

  /**
   * construct by response code, data.
   *
   * @param responseCode response code.
   * @param data additional data of response.
   */
  public ResponseData(@NonNull ResponseCode responseCode, T data) {
    this.responseCode = responseCode;
    this.message = responseCode.getMessage();
    this.data = data;
  }


}
