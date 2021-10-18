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
import org.cobhimself.expectator.ExpectatorEntries;
import org.cobhimself.expectator.ExpectatorEntry;
import org.cobhimself.expectator.FailureMessageBuilder;
import org.cobhimself.expectator.exceptions.ExpectatorException;
import org.cobhimself.expectator.expectations.ExpectationInterface;

/**
 * Base expectation class which implements the {@link ExpectationInterface}.
 * <p>
 * In addition to the <code>expect</code> method required by the <code>ExpectationInterface</code>,
 * this class provides an <code>expectEquals</code> and <code>expectNotEquals</code> pair of
 * methods which can help the extending classes provide basic functionality.
 * <p>
 * Failures result in a {@link ExpectatorException} being thrown.
 * <p>
 * Expectators are collected through the use of {@link ExpectatorEntries} so the class can contain
 * multiple expectators to confirm. This allows <code>Expectation</code> classes to become compound
 * expectations through the chaining of multiple <code>expect</code> calls.
 * <p>
 * <code>Expectation</code> names are provided through the constructor and help distinguish this
 * <code>Expectation</code> from others in output.
 *
 * @param <T> the type of value expectators will work with.
 */
public class Expectation<T> implements ExpectationInterface<T> {
  public static final String NO_EXPECTATOR_ENTRIES_FOUND =
      "Cannot confirm expectator when no expectations have been specified!";

  /**
   * The value this expectation expects during confirmation.
   */
  T expectedValue;

  /**
   * The name of this <code>Expectation</code>; used to separate this expectation from others
   * when output.
   */
  final String name;

  /**
   * A set of <code>Expectator</code>s this <code>Expectation</code> will utilize to confirm our
   * expected value against an actual value.
   */
  final ExpectatorEntries<T> expectatorEntries = new ExpectatorEntries<>();

  /**
   * Construct an <code>Expectation</code> with a known expected value up front.
   *
   * @param expectedValue the value our expectation expects
   * @param name          the name of the <code>Expectation</code>
   */
  protected Expectation(T expectedValue, String name) {
    this.expectedValue = expectedValue;
    this.name = name;
  }

  /**
   * Construct an <code>Expectation</code> without specifying the expected value up front.
   *
   * @param name the name of the <code>Expectation</code>
   */
  protected Expectation(String name) {
    this.name = name;
  }

  /**
   * Expect the value stored by this <code>Expectation</code> is equal to the given value.
   *
   * @param actual the value we expect to be equal to our expected value
   *
   * @return self
   */
  public ExpectationInterface<T> expectEquals(T actual) {
    this.expect(
        Object::equals,
        actual,
        "expected {expected} to equal {actual}"
    );

    return this;
  }

  /**
   * Expect the value stored by this <code>Expectation</code> is not equal to the given value.
   *
   * @param actual the value we expect to be equal to our expected value
   *
   * @return self
   */
  public ExpectationInterface<T> expectNotEquals(T actual) {
    this.expect(
        (e, a) -> !e.equals(a),
        actual,
        "expected {expected} to not equal {actual}"
    );

    return this;
  }

  /**
   * Expect our expected value to not be null.
   *
   * @return self
   */
  public ExpectationInterface<T> expectNotNull() {
    this.expect(
        (e, a) -> e != null,
        null,
        "expected {expected} value to not equal null"
    );

    return this;
  }

  /**
   * Expect our expected value to not be null.
   *
   * @return self
   */
  public ExpectationInterface<T> expectNull() {
    this.expect(
        (e, a) -> e == null,
        null,
        "expected {expected} to be null"
    );

    return this;
  }

  /**
   * Establish an {@link Expectator} lambda expression which receives two arguments (the expected
   * value and the actual value) and returns whether the expectation passes.
   * <p>
   * The given <code>actual</code> value is used when performing the final expectation check and
   * the message string is used when reporting back the failure message.
   *
   * @param expectator the <code>Expectator</code> to call when confirming our expectation
   * @param actual     the actual value to compare our <code>Expectation</code>'s expected value
   *                   against
   * @param message    the failure message string passed to {@link FailureMessageBuilder} provided
   *                   the expected value and actual value
   *
   * @return self
   */
  @Override public ExpectationInterface<T> expect(
      Expectator<T> expectator,
      Object actual,
      String message
  ) {
    var expectationEntry = new ExpectatorEntry<>(
        this,
        expectator,
        actual,
        message
    );

    this.expectatorEntries.add(expectationEntry);

    return this;
  }

  /**
   * Set the expected value to be used when this <code>Expectation</code>'s expectators perform
   * their expectations.
   *
   * @param value the expected value expectators will perform expectations against
   */
  @Override public void setExpectedValue(T value) {
    this.expectedValue = value;
  }

  /**
   * Get the expected value expectators will perform their expectations against.
   *
   * @return the expected value
   */
  @Override public T getExpectedValue() {
    return this.expectedValue;
  }

  /**
   * Get the name of this <code>Expectation</code>.
   *
   * @return the name of this <code>Expectation</code>
   */
  @Override public String getName() {
    return name;
  }

  /**
   * Perform confirmation of all expectators associated with this <code>Expectation</code>.
   */
  @Override public void confirm() {
    if (this.expectatorEntries.isEmpty()) {
      throw new ExpectatorException(
          this,
          NO_EXPECTATOR_ENTRIES_FOUND
      );
    }

    this.expectatorEntries.confirm();
  }
}