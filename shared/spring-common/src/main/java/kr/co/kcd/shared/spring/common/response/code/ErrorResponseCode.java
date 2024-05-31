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
 *
 * <p> This class contains about error response code. </p>
 *
 * @author Jongsang Han (Henry)
 * @version 1.0
 * @since 1.0
 * @see ResponseCode
 */
@Getter
@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorResponseCode implements ResponseCode {
  // common
  INVALID_INPUT_VALUE(400, "I001",
      " Input value is not valid."),
  MANDATORY_INPUT_VALUE(400, "I002",
      " Mandatory field is not valid."),
  DATA_NOT_FOUND(400, "I003",
      " Data is not found by your request."),
  NOT_SUPPORTED_FEATURE(400, "I004",
      " The feature is not supported yet."),
  DUPLICATE_VALUE(400, "I005",
      "The input value has been duplicated."),
  VIOLATION_RULE_VALUE(400, "I006",
      "The rule is violated."),

  // security
  INVALID_CREDENTIALS(401, "C001",
      " Credentials is not valid."),
  INVALID_TOKEN(401, "C002",
      " Your token is empty or not valid."),
  EXPIRE_TOKEN(403, "C003",
      " Token is expired."),
  FORBIDDEN(403, "C004",
      " You need authorization to access."),
  CLAIMS_EMPTY_TOKEN(401, "C005",
      " Claims string is null or empty or only whitespace."),
  ACCOUNT_LOCK(403, "C006",
      " Your account is locked."),
  ACCOUNT_DISABLED(403, "C007",
      " Your account is disabled."),
  ACCOUNT_EXPIRED(403, "C008",
      " Your account is expired."),
  ACCOUNT_CREDENTIALS_EXPIRED(403, "C009",
      " Your account' credentials is expired."),

  // rule
  EXPIRE_PLAN(401, "C010",
      "Plan is expired"),

  // resource
  NOT_FOUND(404, "R002",
      " Resource is not found."),
  INVALID_METHOD(405, "R001",
      " Resource is not available."),
  DAILY_LIMIT_REQUEST(
      429, "R003",
      " Your request exceed limit per day."),
  AMOUNT_TIME_LIMIT_REQUEST(429, "R004",
      " Your request exceed limit per amount time."),

  // system
  NETWORK_ERROR(500, "E001",
      " Network has some problems."),
  PERSISTENCE_ERROR(500, "E002",
      " Persistence has some problems."),
  UNKNOWN_SERVER_ERROR(500, "E003",
      "Occur unknown error on server."),
  EXTERNAL_API_ERROR(500, "E004",
      "External API error. check external API."),
  EXTERNAL_SYSTEM_ERROR(500, "E006",
      "External system error. check external system."),
  INTERNAL_IO_ERROR(500, "E007",
      "Internal io error. try again later."),
  UNCAUGHT_SERVER_ERROR(500, "E005",
      "Occur uncaught error on server."),

  ;

  private final int statusCode;
  private final String code;
  private final String message;


  @JsonCreator
  ErrorResponseCode(int statusCode, String code, String message) {
    this.statusCode = statusCode;
    this.code = code;
    this.message = message;
//    this.httpStatus = httpStatus;
  }

  /**
   * return {@code ErrorResponseCode} by code.
   *
   * @param code code like E002, I001,
   * @return {@code ErrorResponseCode} that matched by code.
   */
  public static ErrorResponseCode getEnumByCodeValue(String code) {
    return (ErrorResponseCode) ResponseCode.getEnumByCodeValue(code, ErrorResponseCode.values());
  }
}
