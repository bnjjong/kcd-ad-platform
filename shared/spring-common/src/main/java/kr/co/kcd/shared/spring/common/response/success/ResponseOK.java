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

package kr.co.kcd.shared.spring.common.response.success;

import kr.co.kcd.shared.spring.common.response.ResponseData;
import kr.co.kcd.shared.spring.common.response.code.ResponseCode;
import kr.co.kcd.shared.spring.common.response.code.SuccessResponseCode;
import lombok.ToString;

/**
 *
 * <pre>
 *   Implement class of {@code ResponseData}.
 *   Use this when response is Ok(200).
 * </pre>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 * @see ResponseData
 */
@ToString(callSuper = true)
public class ResponseOK<T> extends ResponseData<T> {

  public ResponseOK(String message) {
    super(SuccessResponseCode.OK, message, null);
  }

  public ResponseOK(String message, T data) {
    super(SuccessResponseCode.OK, message, data);
  }

  private ResponseOK(ResponseCode responseCode, String message, T data) {
    super(responseCode, message, data);
  }
}
