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

/**
 * An {@link Expectation} which performs expectations against <code>Integer</code> values.
 */
public class IntExpectation extends Expectation<Integer> {

  /**
   * Construct an <code>IntExpectation</code> with a known expected value up front.
   *
   * @param expectedValue the value our expectation expects
   * @param name          the name of the <code>IntExpectation</code>
   */
  public IntExpectation(Integer expectedValue, String name) {
    super(expectedValue, name);
  }

  /**
   * Construct a <code>IntExpectation</code> without specifying the expected value up front.
   *
   * @param name the name of the <code>IntExpectation</code>
   */
  public IntExpectation(String name) {
    super(name);
  }

  /**
   * Expect our expected value to be greater than the given value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public IntExpectation expectGreaterThan(Integer actual) {
    this.expect(
        (e, a) -> e > a,
        actual,
        "expected {expected} to be greater than {actual}"
    );

    return this;
  }

  /**
   * Expect our expected value to be greater than or equal to the given value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public IntExpectation expectGreaterThanOrEqualTo(Integer actual) {
    this.expect(
        (e, a) -> e >= a,
        actual,
        "expected {expected} to be greater than or equal to {actual}"
    );

    return this;
  }

  /**
   * Expect our expected value to be less than the given value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public IntExpectation expectLessThan(Integer actual) {
    this.expect(
        (e, a) -> e < a,
        actual,
        "expected {expected} to be less than {actual}"
    );

    return this;
  }

  /**
   * Expect our expected value to be less than or equal to the given value.
   *
   * @param actual the actual value
   *
   * @return self
   */
  public IntExpectation expectLessThanOrEqualTo(Integer actual) {
    this.expect(
        (e, a) -> e <= a,
        actual,
        "expected {expected} to be less than or equal to {actual}"
    );

    return this;
  }
}
