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

package kr.co.kcd.shared.spring.common.response.error;

import kr.co.kcd.shared.spring.common.response.ResponseData;
import kr.co.kcd.shared.spring.common.response.code.ErrorResponseCode;
import lombok.ToString;

/**
 * create on 2023/01/31.
 * create by IntelliJ IDEA.
 *
 * <pre>
 *  Implement class of {@code ResponseData}.
 *  Use this when response is Error(5XX).
 * </pre>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 * @see ResponseData
 */
@ToString(callSuper = true)
public class ResponseError<T> extends ResponseData<T> {

  public ResponseError(ErrorResponseCode errorResponseCode, String message) {
    super(errorResponseCode, message, null);
  }

  public ResponseError(ErrorResponseCode errorResponseCode, String message, T data) {
    super(errorResponseCode, message, data);
  }
}
