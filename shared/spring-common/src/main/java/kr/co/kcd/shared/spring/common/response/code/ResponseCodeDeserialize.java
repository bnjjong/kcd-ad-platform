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

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

/**
 *
 * <p>클래스 설명
 * <a href="https://d2.naver.com/helloworld/0473330">link</a>
 * <a href="https://www.baeldung.com/jackson-deserialization">link</a>
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @since 1.0
 */
public class ResponseCodeDeserialize extends StdDeserializer<Enum<? extends ResponseCode>>
    implements ContextualDeserializer {

  public ResponseCodeDeserialize() {
    this(null);
  }

  public ResponseCodeDeserialize(Class<?> vc) {
    super(vc);
  }

  @Override
  public Enum<? extends ResponseCode> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {
    JsonNode node = p.getCodec().readTree(p);
    String code = node.get("code").asText();
    String name;

    Class<? extends Enum> enumType;
    try {
      name = SuccessResponseCode.getEnumByCodeValue(code).name();
      enumType = SuccessResponseCode.class;

    } catch (IllegalArgumentException e) {
      name = ErrorResponseCode.getEnumByCodeValue(code).name();
      enumType = ErrorResponseCode.class;
    }

    return Enum.valueOf(
        enumType,
        name
    );
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
      throws JsonMappingException {
    return new ResponseCodeDeserialize(property.getType().getRawClass());
  }
}
