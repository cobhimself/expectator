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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.cobhimself.expectator.exceptions.ExpectatorException;

public interface BaseExpectationTest<E extends Expectation<T>, T> {

  /**
   * Test an <code>Expectation</code>'s constructor with both the expected value and name arguments.
   *
   * @param expectation the expectation whose constructor we are testing
   * @param expected    the expected value we should use in our checks
   * @param name        the name of the expectation to confirm
   */
  default void testExpectedValueAndNameConstructor(E expectation, T expected, String name) {
    assertEquals(expected, expectation.getExpectedValue());
    assertEquals(name, expectation.getName());
    assertDoesNotThrow(() -> expectation.expectEquals(expected).confirm());
  }

  /**
   * Test an <code>Expectation</code>'s constructor with just the name argument.
   *
   * @param expectation the expectation whose constructor we are testing
   * @param name        the name of the expectation to confirm
   */
  default void testNameOnlyConstructor(E expectation, String name) {
    Exception exception = assertThrows(ExpectatorException.class, expectation::confirm);
    assertEquals(
        name + ": " + Expectation.NO_EXPECTATOR_ENTRIES_FOUND,
        exception.getMessage()
    );
  }
}
