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

import java.util.List;
import java.util.stream.Stream;
import org.cobhimself.expectator.exceptions.ExpectatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class IntExpectationTest implements BaseExpectationTest<IntExpectation, Integer> {

  private static final String NAME = "Expectation name";
  private static final Integer EXPECTED = 2;

  static IntExpectation getExpectation() {
    return new IntExpectation(2, NAME);
  }

  @Test
  void mainTest() {
    var expectation = getExpectation();
    assertEquals(EXPECTED, expectation.getExpectedValue());
  }

  @Test
  void testConstructors() {
    this.testExpectedValueAndNameConstructor(
        new IntExpectation(EXPECTED, NAME),
        EXPECTED,
        NAME
    );

    this.testNameOnlyConstructor(new IntExpectation(NAME), NAME);
  }

  @ParameterizedTest()
  @MethodSource("parameters")
  void testExpectations(
      Integer expected, List<Expectation<Integer>> willPass, List<Expectation<Integer>> willFail
  ) {
    willPass.forEach(expectation -> {
      expectation.setExpectedValue(expected);
      assertDoesNotThrow(expectation::confirm);
    });
    willFail.forEach(expectation -> {
      expectation.setExpectedValue(expected);
      assertThrows(ExpectatorException.class, expectation::confirm);
    });
  }

  static Stream<Arguments> parameters() {
    return Stream.of(
        Arguments.of(
            2,
            //Will Pass
            List.of(
                getExpectation().expectGreaterThanOrEqualTo(2),
                getExpectation().expectGreaterThanOrEqualTo(1)
            ),
            //Will Fail
            List.of(
                getExpectation().expectGreaterThanOrEqualTo(3),
                getExpectation().expectGreaterThanOrEqualTo(4)
            )
        ),
        Arguments.of(
            2,
            //Will Pass
            List.of(
                getExpectation().expectGreaterThan(1),
                getExpectation().expectGreaterThan(0)
            ),
            //Will Fail
            List.of(
                getExpectation().expectGreaterThan(2),
                getExpectation().expectGreaterThan(3)
            )
        ),
        Arguments.of(
            2,
            //Will Pass
            List.of(
                getExpectation().expectLessThanOrEqualTo(3),
                getExpectation().expectLessThanOrEqualTo(2)
            ),
            //Will Fail
            List.of(
                getExpectation().expectLessThanOrEqualTo(1),
                getExpectation().expectLessThanOrEqualTo(0)
            )
        ),
        Arguments.of(
            2,
            //Will Pass
            List.of(
                getExpectation().expectLessThan(3),
                getExpectation().expectLessThan(4)
            ),
            //Will Fail
            List.of(
                getExpectation().expectLessThan(1),
                getExpectation().expectLessThan(2)
            )
        ),
        //Multiple expectations
        Arguments.of(
            2,
            //Will Pass
            List.of(
                getExpectation().expectLessThan(3).expectLessThanOrEqualTo(2),
                getExpectation().expectLessThan(4).expectLessThanOrEqualTo(3)
            ),
            //Will Fail
            List.of(
                getExpectation().expectLessThan(3).expectLessThanOrEqualTo(1),
                getExpectation().expectLessThan(3).expectLessThanOrEqualTo(0)
            )
        )
    );
  }
}