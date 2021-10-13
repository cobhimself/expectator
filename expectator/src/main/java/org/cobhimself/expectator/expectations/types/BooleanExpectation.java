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
 * An {@link Expectation} which performs expectations against <code>Boolean</code> values.
 */
public class BooleanExpectation extends Expectation<Boolean> {

  /**
   * Construct a <code>BooleanExpectation</code> with a known expected value up front.
   *
   * @param expectedValue the value our expectation expects
   * @param name          the name of the <code>Expectation</code>
   */
  public BooleanExpectation(Boolean expectedValue, String name) {
    super(expectedValue, name);
  }

  /**
   * Construct a <code>BooleanExpectation</code> without specifying the expected value up front.
   *
   * @param name the name of the <code>BooleanExpectation</code>
   */
  public BooleanExpectation(String name) {
    super(name);
  }

  /**
   * Expect our expected value will be true upon confirmation.
   */
  public BooleanExpectation expectTrue() {
    this.expectEquals(true);

    return this;
  }

  /**
   * Expect our expected value will be false upon confirmation.
   */
  public BooleanExpectation expectFalse() {
    this.expectEquals(false);

    return this;
  }
}
