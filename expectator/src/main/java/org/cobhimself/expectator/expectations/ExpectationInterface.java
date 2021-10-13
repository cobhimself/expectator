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

package org.cobhimself.expectator.expectations;

import org.cobhimself.expectator.Expectator;

/**
 * This interface describes an "expectation" which can house one or more <code>BiPredicate</code>
 * instances (which this interface refers to as "expectators"). These expectators are used to
 * confirm expected vs actual values whose types are specified by <code>T</code>.
 * <p>
 * Expectators are added through this interface's <code>expect</code> method. Multiple expectators
 * can be built up for future confirmation when the interface's confirm method is run.
 * <p>
 * No attempt is made to block conflicting expectators from being added to this expectation; this
 * allows the built-up set of expectators to become compound expectations which must pass all
 * together.
 * <p>
 * Expectations can be named in order to make it easier to distinguish them against others.
 * Implementing classes should accept the name in their constructor or implement a
 * <code>setName</code>; this interface does not force either implementation.
 * <p>
 * The general flow this interface provides is: expectation -> one or more expectators -> confirm.
 * This interface does not enforce the mechanism by which the <code>confirm</code> method reports
 * failed expectators. Possible solutions would be to throw an exception and catch it to build up
 * a list of failures; the choice is the implementor's.
 *
 * @param <T> the type of value expectators will work with (both expected and actual).
 */
public interface ExpectationInterface<T> {

  /**
   * Get the name of this expectation.
   */
  String getName();

  /**
   * Set the expected value to be used when expectators perform their expectations.
   * <p>
   * @param value The expected value expectators will perform expectations against.
   */
  void setExpectedValue(T value);

  /**
   * Get the expected value expectators will perform their expectations against.
   */
  T getExpectedValue();

  /**
   * Confirm the expectation.
   */
  void confirm();

  /**
   * Establish an expectator (<code>BiPredicate</code>) lambda expression which receives two
   * arguments, the expected value and the actual value, and returns whether the expectation passes.
   * <p>
   * The given <code>actual</code> value is used when performing the final expectation check and
   * the message string is used when reporting back the failure message.
   *
   * @param expectator the <code>BiPredicate</code> to call when confirming our expectation
   * @param actual     the actual value to compare our expected value against
   * @param message    the failure message string passed to <code>String.format</code> and
   *                   provided the expected value and actual value
   */
  ExpectationInterface<T> expect(
      Expectator<T> expectator,
      T actual,
      String message
  );
}
