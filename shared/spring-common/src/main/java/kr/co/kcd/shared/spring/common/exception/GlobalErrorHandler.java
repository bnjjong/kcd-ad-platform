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
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 */
@Deprecated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalErrorHandler {

  public static Exception getExceptionByErrorCode(String code, String msg) {
    if (code.equals(ErrorResponseCode.INVALID_INPUT_VALUE.getCode())) {
      return new InvalidInputException(msg);
    } else if(code.equals(ErrorResponseCode.MANDATORY_INPUT_VALUE.getCode())) {
      return new MandatoryInputException(msg);
    } else if(code.equals(ErrorResponseCode.DATA_NOT_FOUND.getCode())){
      return new DataNotFoundException(msg);
    } else if(code.equals(ErrorResponseCode.DUPLICATE_VALUE.getCode())) {
      return new DuplicationValueException(msg);
    } else if(code.equals(ErrorResponseCode.FORBIDDEN.getCode())) {
      return new ForbiddenException(msg);
    } else if(code.equals(ErrorResponseCode.NOT_FOUND.getCode())) {
      return new ResourceNotFoundException(msg);
    } else if(code.equals(ErrorResponseCode.AMOUNT_TIME_LIMIT_REQUEST.getCode())) {
      return new AmountRequestLimitException(msg);
    } else if(code.equals(ErrorResponseCode.PERSISTENCE_ERROR.getCode())) {
      return new PersistenceException(msg);
    } else if(code.equals(ErrorResponseCode.UNKNOWN_SERVER_ERROR.getCode())) {
      return new UnexpectedApplicationException(msg);
    } else if (code.equals(ErrorResponseCode.UNCAUGHT_SERVER_ERROR.getCode())) {
      return new UnexpectedApplicationException(msg);
    } else {
      return new UnexpectedApplicationException(msg);
    }
  }

}
