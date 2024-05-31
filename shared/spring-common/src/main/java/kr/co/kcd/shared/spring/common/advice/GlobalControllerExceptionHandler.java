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

package kr.co.kcd.shared.spring.common.advice;


import jakarta.validation.ConstraintViolationException;
import java.net.BindException;
import kr.co.kcd.shared.spring.common.exception.BusinessException;
import kr.co.kcd.shared.spring.common.exception.ResourceException;
import kr.co.kcd.shared.spring.common.exception.SystemException;
import kr.co.kcd.shared.spring.common.response.ResponseCommonEntity;
import kr.co.kcd.shared.spring.common.response.code.ErrorResponseCode;
import kr.co.kcd.shared.spring.common.response.error.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 *
 * <pre>
 * Spring controller exception advice handler.
 * Most of all exception that client call wrong apis is handled here.
 * </pre>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

  /**
   * <pre>
   *  occur error by Valid annotation.
   * </pre>
   *
   * @param e {@code MethodArgumentNotValidException} exception.
   * @return {@code ResponseCommonEntity} with error code and message.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleValidException(MethodArgumentNotValidException e) {
    _printError(e);
    String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    return new ResponseCommonEntity<String>(
        new ResponseError(ErrorResponseCode.INVALID_INPUT_VALUE, errorMsg));
  }

  /**
   * <pre>
   *   occur error when binding model by @ModelAttribute.
   * </pre>
   *
   * <a
   * href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args">
   * reference </a>
   *
   * @param e {@code BindException} exception.
   * @return {@code ResponseCommonEntity} with error code and message.
   */
  @ExceptionHandler(BindException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleBindException(BindException e) {
    _printError(e);
    return new ResponseCommonEntity<String>(
        new ResponseError(ErrorResponseCode.INVALID_INPUT_VALUE, e.getMessage()));
  }


  /**
   * {@code @RequestMapping}으로 지정한 필수 request parameter가 누락되었을 때.
   *
   * @param e {@link MissingServletRequestParameterException}
   * @see <a
   *     href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/MissingServletRequestParameterException.html">MissingServletRequestParameterException</a>
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleRequestParamException(
      MissingServletRequestParameterException e) {
    _printError(e);

    return new ResponseCommonEntity<String>(
        new ResponseError(ErrorResponseCode.MANDATORY_INPUT_VALUE, e.getMessage()));
  }


  /**
   * {@code @RequestMapping}으로 지정한 필수 request header가 누락되었을 때.
   *
   * @param e {@link MissingRequestHeaderException}
   * @return {@link ResponseCommonEntity}
   * @see <a
   *     href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/index.html?org/springframework/web/bind/MissingServletRequestParameterException.html">MissingRequestHeaderException</a>
   */
  @ExceptionHandler(MissingRequestHeaderException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleRequestHeaderException(MissingRequestHeaderException e) {
    _printError(e);

    return new ResponseCommonEntity<String>(
        new ResponseError(ErrorResponseCode.MANDATORY_INPUT_VALUE, e.getMessage()));
  }

  /**
   * {@link javax.validation.constraints} package의 annotation으로 지정한 request의 validation이 통과되지 못할 때.
   *
   * @param e {@link ConstraintViolationException}
   * @return {@link ResponseCommonEntity}
   * @see <a
   *     href="https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html">javax.validation.constraints</a>
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleConstraintViolationException(
      ConstraintViolationException e) {
    _printError(e);
    return new ResponseCommonEntity<String>(
        new ResponseError(ErrorResponseCode.INVALID_INPUT_VALUE, e.getMessage()));
  }

  /**
   *
   * @param e
   * @return
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
    _printError(e);
    return new ResponseCommonEntity<String>(
        new ResponseError(ErrorResponseCode.NOT_FOUND, e.getMessage()));
  }




  @ExceptionHandler(BusinessException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleBusinessException(BusinessException e) {
    _printError(e);
    final ErrorResponseCode errorCode = (ErrorResponseCode) e.getResponseCode();

    return new ResponseCommonEntity<String>(
        new ResponseError(errorCode, e.getMessage()));
  }


  @ExceptionHandler(ResourceException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleResourceException(ResourceException e) {
    _printError(e);
    final ErrorResponseCode errorCode = (ErrorResponseCode) e.getResponseCode();
    return new ResponseCommonEntity<String>(
        new ResponseError(errorCode, e.getMessage()));
  }

  @ExceptionHandler(SystemException.class)
  @Order(1)
  protected ResponseCommonEntity<String> handleSystemException(SystemException e) {
    _printError(e);
    final ErrorResponseCode errorCode = (ErrorResponseCode) e.getResponseCode();
    return new ResponseCommonEntity<String>(
        new ResponseError(errorCode, e.getMessage()));
  }


  @ExceptionHandler(Exception.class)
  @Order(9)
  protected ResponseCommonEntity<String> handleUncaughtException(Exception e) {
    _printError(e);
    final ErrorResponseCode errorCode = ErrorResponseCode.UNCAUGHT_SERVER_ERROR;
    return new ResponseCommonEntity<String>(
        new ResponseError(errorCode, e.getMessage()));
  }


  private void _printError(Exception e) {
    log.error("GlobalException : {}", e.getClass().getName());
    e.printStackTrace();
  }

}
