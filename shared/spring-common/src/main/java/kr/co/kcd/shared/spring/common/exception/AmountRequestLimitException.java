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

package kr.co.kcd.shared.spring.common.exception;

import kr.co.kcd.shared.spring.common.response.code.ErrorResponseCode;

/**
 *
 * <p> 일정 시간 동안 호출량을 초과 했을 경우. </p>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 * @see ResourceException
 */
public class AmountRequestLimitException extends ResourceException {

  public AmountRequestLimitException(String message) {
    super(message, ErrorResponseCode.AMOUNT_TIME_LIMIT_REQUEST);
  }

  public AmountRequestLimitException(String message, Throwable cause) {
    super(message, cause, ErrorResponseCode.AMOUNT_TIME_LIMIT_REQUEST);
  }

  public AmountRequestLimitException(Throwable cause) {
    super(cause, ErrorResponseCode.AMOUNT_TIME_LIMIT_REQUEST);
  }
}
