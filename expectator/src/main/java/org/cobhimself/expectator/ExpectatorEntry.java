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

import org.cobhimself.expectator.exceptions.ExpectatorException;
import org.cobhimself.expectator.expectations.types.Expectation;

/**
 * Class which holds an expectator, an actual value to perform expectations against, and the failure
 * message template to use when expectations fail.
 *
 * @param <T> the type of value expectators will work with (both expected and actual).
 */
public class ExpectatorEntry<T> {
  private final Expectator<T> expectator;
  private final T actual;
  private final String message;
  private final Expectation<T> parentExpectation;

  /**
   * Constructor which establishes the necessary properties for this entry.
   * <p>
   * The <code>message</code> sent in is passed to <code>String.format</code> and should have two
   * placeholders; the first is the expected value (obtained from the parent expectation) and the
   * second is the actual value (provided to this constructor).
   *
   * @param expectation the parent {@link Expectation} this entry is associated with
   * @param expectator  the expectator which confirms our expectations
   * @param actual      the actual value we are confirming our expectations against
   * @param message     the failure message we will use when providing additional details as to why
   *                    our expectations were not met.
   */
  public ExpectatorEntry(
      Expectation<T> expectation,
      Expectator<T> expectator,
      T actual,
      String message
  ) {
    this.parentExpectation = expectation;
    this.expectator = expectator;
    this.actual = actual;
    this.message = message;
  }

  /**
   * Get the expectator associated with this entry.
   */
  public Expectator<T> getExpectator() {
    return expectator;
  }

  /**
   * Get the actual value associated with this entry.
   */
  public T getActual() {
    return actual;
  }

  /**
   * Get the failure format message used when providing failure details.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Get the parent expectation this entry is associated with.
   * <p>
   * This allows us to obtain the expected value to be used by our expectator entry.
   */
  public Expectation<T> getParentExpectation() {
    return parentExpectation;
  }

  /**
   * Mark the parent expectation as having failed.
   */
  private void fail() {
    throw new ExpectatorException(
        this.getParentExpectation(),
        this.outcomeDetails()
    );
  }

  /**
   * Get specific details about the expected vs actual values for failure messages.
   */
  public String outcomeDetails() {
    return new FailureMessageBuilder()
        .setExpected(this.getParentExpectation().getExpectedValue().toString())
        .setActual(actual.toString())
        .build(this.getMessage());
  }

  /**
   * Confirm the expectator associated with this entry.
   */
  public void confirm() {
    if (!this.getExpectator().test(
        this.getParentExpectation().getExpectedValue(),
        this.getActual()
    )) {
      this.fail();
    }
  }
}
