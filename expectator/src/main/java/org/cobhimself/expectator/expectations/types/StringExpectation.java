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

package org.cobhimself.expectator.expectations.types;

public class StringExpectation extends Expectation<String> {

  /**
   * Construct a <code>StringExpectation</code> without specifying the expected value up front.
   *
   * @param expectedValue the value we expect against
   * @param name          the name of the <code>StringExpectation</code>
   */
  protected StringExpectation(String expectedValue, String name) {
    super(expectedValue, name);
  }

  /**
   * Construct a <code>StringExpectation</code> without specifying the expected value up front.
   *
   * @param name the name of the <code>StringExpectation</code>
   */
  protected StringExpectation(String name) {
    super(name);
  }

  /**
   * Expect our expected value will be the same length as the given actual value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public StringExpectation expectSameLength(String actual) {
    this.expect(
        (e, a) -> e.length() == ((String) a).length(),
        actual,
        "expected '{expected}' to be the same length as '{actual}' but they are not"
    );

    return this;
  }

  /**
   * Expect our expected value will start with the given actual value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public StringExpectation expectStartsWith(String actual) {
    this.expect(
        (e, a) -> e.startsWith(actual),
        actual,
        "expected '{expected}' to start with '{actual}' but it does not"
    );

    return this;
  }

  /**
   * Expect our expected value will end with the given actual value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public StringExpectation expectEndsWith(String actual) {
    this.expect(
        (e, a) -> e.endsWith(actual),
        actual,
        "expected '{expected}' to end with '{actual}' but it does not"
    );

    return this;
  }

  /**
   * Expect our expected value will contain the given actual value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public StringExpectation expectContains(String actual) {
    this.expect(
        (e, a) -> e.contains(actual),
        actual,
        "expected '{expected}' to contain '{actual}' but it does not"
    );

    return this;
  }

  /**
   * Expect our expected value will not contain the given actual value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public StringExpectation expectDoesNotContain(String actual) {
    this.expect(
        (e, a) -> !e.contains((String) a),
        actual,
        "expected '{expected}' to not contain '{actual}' but it does"
    );

    return this;
  }

  /**
   * Expect our expected value is empty.
   *
   * @return self
   */
  public StringExpectation expectEmpty() {
    this.expectEquals("");

    return this;
  }

  /**
   * Expect our expected value is empty.
   *
   * @return self
   */
  public StringExpectation expectNotEmpty() {
    this.expectNotEquals("");

    return this;
  }
}
