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
 * <p> Use this Exception when logically data that is needed is not exist on your code. </p>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 * @see BusinessException
 */
public class DataNotFoundException extends BusinessException {

  public DataNotFoundException(String message, Throwable cause) {
    super(message, cause, ErrorResponseCode.DATA_NOT_FOUND);
  }

  public DataNotFoundException(String message) {
    super(message, ErrorResponseCode.DATA_NOT_FOUND);
  }

  public DataNotFoundException(Throwable cause) {
    super(cause, ErrorResponseCode.DATA_NOT_FOUND);
  }
}
