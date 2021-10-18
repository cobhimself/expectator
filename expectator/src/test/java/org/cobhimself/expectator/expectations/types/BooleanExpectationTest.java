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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cobhimself.expectator.exceptions.ExpectatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BooleanExpectationTest implements BaseExpectationTest<BooleanExpectation, Boolean> {
  final String NAME = "Boolean Expectation Name";
  final Boolean EXPECTED = true;

  private BooleanExpectation expectation;

  @BeforeEach
  void beforeEach() {
    this.expectation = new BooleanExpectation(true, NAME);
  }

  @Test
  void testConstructors() {
    this.testExpectedValueAndNameConstructor(
        new BooleanExpectation(EXPECTED, NAME),
        EXPECTED,
        NAME
    );

    this.testNameOnlyConstructor(new BooleanExpectation(NAME), NAME);
  }

  @Test
  void mainTest() {
    assertTrue(this.expectation.getExpectedValue());
    this.expectation.setExpectedValue(false);
    assertFalse(this.expectation.getExpectedValue());
  }

  @Test
  void expectTrue() {
    //Set the value our expectation is working with to false, so we fail
    this.expectation.setExpectedValue(false);

    Exception exception = assertThrows(
        ExpectatorException.class,
        () -> this.expectation.expectTrue().confirm()
    );

    assertEquals(NAME + ": expected false to equal true", exception.getMessage());
  }

  @Test
  void expectFalse() {
    //Set the value our expectation is working with to false, so we fail
    this.expectation.setExpectedValue(true);

    Exception exception = assertThrows(
        ExpectatorException.class,
        () -> this.expectation.expectFalse().confirm()
    );

    assertEquals(NAME + ": expected true to equal false", exception.getMessage());
  }
}