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
import org.cobhimself.expectator.expectations.Expectation;

/**
 * An <code>ExpectatorEntry</code> is the container in which an <code>Expectator</code> is stored
 * alongside an actual value to be confirmed against an {@link Expectation}'s expected value at
 * some point in the future. The <code>Expectator</code> within is NOT run until the
 * <code>confirm</code> method is called on the <code>ExpectatorEntry</code>. It can be combined
 * with other <code>ExpectatorEntry</code> instances in {@link ExpectatorEntries} objects.
 *
 * @param <T> the type of value the <code>Expectator</code> stores in its expected value.
 */
public class ExpectatorEntry<T> {
  private final Expectator<T> expectator;
  private final Object actual;
  private final String message;
  private final Expectation<T> parentExpectation;

  /**
   * Constructor which establishes the necessary properties for this entry.
   * <p>
   * The <code>message</code> sent in is passed to a {@link FailureMessageBuilder} and should have
   * two named placeholders; the first is the expected value (specified by {expected}) and the
   * second is the actual value (specified by {actual}) of an <code>Expectation</code>.
   * <p>
   * Example message: "The value {expected} does not equal {actual}"
   *
   * @param expectation the parent {@link Expectation} this entry is associated with
   * @param expectator  the <code>Expectator</code> which confirms our expectations
   * @param actual      the actual value we are confirming our expectations against
   * @param message     the failure message we will use when providing additional details as to why
   *                    our expectations were not met.
   */
  public ExpectatorEntry(
      Expectation<T> expectation,
      Expectator<T> expectator,
      Object actual,
      String message
  ) {
    this.parentExpectation = expectation;
    this.expectator = expectator;
    this.actual = actual;
    this.message = message;
  }

  /**
   * Get the <code>Expectator</code> associated with this entry.
   *
   * @return the expectator associated with this entry
   */
  public Expectator<T> getExpectator() {
    return expectator;
  }

  /**
   * Get the actual value associated with this entry.
   *
   * @return the actual value associated with this entry
   */
  public Object getActual() {
    return actual;
  }

  /**
   * Get the failure message template used when providing failure details.
   *
   * @return the failure message template used when providing failure details
   */
  public String getMessage() {
    return message;
  }

  /**
   * Get the parent <code>Expectation</code> this <code>ExpectatorEntry</code> is associated with.
   * <p>
   * This allows us to obtain the expected value for use by the <code>Expectator</code>.
   *
   * @return the parent <code>Expectation</code>
   */
  public Expectation<T> getParentExpectation() {
    return parentExpectation;
  }

  /**
   * Mark the parent <code>Expectation</code> as having failed.
   */
  private void fail() {
    throw new ExpectatorException(
        this.getParentExpectation(),
        this.outcomeDetails()
    );
  }

  /**
   * Get specific details about the expected vs actual values for failure messages.
   *
   * @return the outcome details as to why a failure occurred.
   */
  public String outcomeDetails() {
    return new FailureMessageBuilder()
        .setExpected(this.getParentExpectation().getExpectedValue().toString())
        .setActual(actual.toString())
        .build(this.getMessage());
  }

  /**
   * Confirm the <code>Expectator</code> associated with this <code>ExpectatorEntry</code>.
   * <p>
   * The <code>Expectator</code> is not run until this method is called!
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
