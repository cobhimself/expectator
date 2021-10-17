/*
 * MIT License
 *
 * Copyright (c) 2021 Collin D. Brooks
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.cobhimself.expectator;

import com.google.common.base.Strings;

/**
 * Class which aids in the construction of failure messages by replacing {expected} and {value}
 * tokens in the failure message with the expected and actual values provided.
 */
public class FailureMessageBuilder {

  String expected;
  String actual;

  /**
   * Get the expected value.
   *
   * @return the expected value string if available or an empty string otherwise
   */
  public String getExpected() {
    return this.getValue(this.expected);
  }

  /**
   * Set the expected value to be used when generating the failure message.
   *
   * @param expected the expected value
   *
   * @return self
   */
  public FailureMessageBuilder setExpected(String expected) {
    this.expected = expected;

    return this;
  }

  /**
   * Get the actual value.
   *
   * @return the actual value string if available or an empty string otherwise
   */
  public String getActual() {
    return this.getValue(this.actual);
  }

  public FailureMessageBuilder setActual(String actual) {
    this.actual = actual;

    return this;
  }

  /**
   * Get the value if it is not null or empty; otherwise, get an empty string.
   *
   * @param value the value we are attempting to get
   *
   * @return the value or an empty string if the value was null or empty
   */
  private String getValue(String value) {
    return Strings.isNullOrEmpty(value) ? "" : value;
  }

  /**
   * Build the failure message.
   * <p>
   * Our failure message should contain one or more {expected} or {actual} tokens to be replaced by
   * the expected and actual values provided to this builder.
   * <p>
   * The above tokens are not required if either of them do not need to be replaced with their
   * final values.
   *
   * @param failureMessage the failure message template we are building from
   *
   * @return the final failure message
   */
  public String build(String failureMessage) {
    return failureMessage
        .replace("{expected}", this.getExpected())
        .replace("{actual}", this.getActual());
  }
}
