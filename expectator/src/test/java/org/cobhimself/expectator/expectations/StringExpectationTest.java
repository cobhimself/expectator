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
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.cobhimself.expectator.exceptions.ExpectatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringExpectationTest implements BaseExpectationTest<StringExpectation, String> {
  private static final String NAME = "Expectation name";
  private static final String EXPECTED = "expected";

  static StringExpectation getExpectation() {
    return new StringExpectation(EXPECTED, NAME);
  }

  @Test
  void testConstructors() {
    this.testExpectedValueAndNameConstructor(
        new StringExpectation(EXPECTED, NAME),
        EXPECTED,
        NAME
    );

    this.testNameOnlyConstructor(new StringExpectation(NAME), NAME);
  }

  @ParameterizedTest()
  @MethodSource("parameters")
  void testExpectations(
      String expected, List<Expectation<String>> willPass, List<Expectation<String>> willFail
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
            "minimum",
            //Will Pass
            List.of(
                getExpectation().expectNotEmpty(),
                getExpectation().expectSameLength("maximum"),
                getExpectation().expectStartsWith("min"),
                getExpectation().expectEndsWith("mum"),
                getExpectation().expectContains("min"),
                getExpectation().expectContains("mum"),
                getExpectation().expectDoesNotContain("max"),
                getExpectation().expectDoesNotContain("cat"),
                getExpectation().expectStartsWith("min").expectEndsWith("mum")
            ),
            //Will Fail
            List.of(
                getExpectation().expectEmpty(),
                getExpectation().expectSameLength("really, long phrase"),
                getExpectation().expectSameLength("short"),
                getExpectation().expectStartsWith("mum"),
                getExpectation().expectEndsWith("min"),
                getExpectation().expectContains("max"),
                getExpectation().expectContains("cat"),
                getExpectation().expectDoesNotContain("min"),
                getExpectation().expectDoesNotContain("mum"),
                getExpectation().expectStartsWith("min").expectEndsWith("cat")
            )
        )
    );
  }
}