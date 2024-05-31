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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *
 * <pre>
 *   base code interface.
 *   it is implemented by Enum class.
 *   see below down {@link ErrorResponseCode}, {@link SuccessResponseCode}.
 * </pre>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 * @see ErrorResponseCode
 * @see SuccessResponseCode
 */
@JsonDeserialize(using = ResponseCodeDeserialize.class)

public interface ResponseCode {

  /**
   * return message.
   *
   * @return {@code String} of message.
   */
  String getMessage();

  /**
   * return code.
   *
   * @return {@code String} of code.
   */
  String getCode();

  /**
   * return status code.
   *
   * @return return status code.
   */
  int getStatusCode();


  /**
   * <p> Find Object that implemented ResponseCode by String type of code. </p>
   *
   * @param codeValue {@code String} value of code.
   * @param values All Enum Class array.
   * @return return {@code Enum} class that implemented ResponseCode.
   * @throws IllegalArgumentException When input code is not valid.
   */
  static ResponseCode getEnumByCodeValue(String codeValue, ResponseCode[] values) {
    for (ResponseCode e : values) {
      if (e.getCode().equals(codeValue)) {
        return e;
      }
    }
    throw new IllegalArgumentException("Your code value is not valid.>>>>" + codeValue);
  }
}
