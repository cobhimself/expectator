package org.cobhimself.expectator.expectations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.cobhimself.expectator.exceptions.ExpectatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CollectionExpectationTest
    implements BaseExpectationTest<CollectionExpectation, Collection<?>> {

  final static List<String> STRING_LIST_EXPECTED = List.of("one", "two", "three");
  final static List<Integer> INTEGER_LIST_EXPECTED = List.of(4, 5, 6);
  final static Set<String> SET_EXPECTED = Set.of("seven", "eight", "nine");

  final static String NAME = "Collection expectation";

  static CollectionExpectation getExpectation(List<?> list) {
    return new CollectionExpectation(list, NAME);
  }

  static CollectionExpectation getExpectation(Set<?> set) {
    return new CollectionExpectation(set, NAME);
  }

  @Test
  void mainTest() {
    assertEquals(
        STRING_LIST_EXPECTED,
        getExpectation(STRING_LIST_EXPECTED)
            .getExpectedValue()
    );

    assertEquals(
        INTEGER_LIST_EXPECTED,
        getExpectation(INTEGER_LIST_EXPECTED)
            .getExpectedValue()
    );

    assertEquals(
        SET_EXPECTED,
        getExpectation(SET_EXPECTED)
            .getExpectedValue()
    );
  }

  @Test
  void testConstructors() {
    this.testExpectedValueAndNameConstructor(
        new CollectionExpectation(STRING_LIST_EXPECTED, NAME),
        STRING_LIST_EXPECTED,
        NAME
    );

    this.testNameOnlyConstructor(new CollectionExpectation(NAME), NAME);
  }

  @ParameterizedTest()
  @MethodSource("parameters")
  void testExpectations(
      Collection<?> expected,
      List<CollectionExpectation> willPass,
      List<CollectionExpectation> willFail
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
            STRING_LIST_EXPECTED,
            //Will Pass
            List.of(
                getExpectation(STRING_LIST_EXPECTED).expectEquals(List.of("one", "two", "three")),
                getExpectation(STRING_LIST_EXPECTED).expectContains("one"),
                getExpectation(STRING_LIST_EXPECTED).expectContains("two"),
                getExpectation(STRING_LIST_EXPECTED).expectContainsAll(List.of("one", "two")),
                getExpectation(STRING_LIST_EXPECTED).expectSize(3),
                getExpectation(STRING_LIST_EXPECTED).expectDoesNotContain(3),
                getExpectation(STRING_LIST_EXPECTED).expectDoesNotContainAny(List.of("four", "five"))
            ),
            //Will Fail
            List.of(
                getExpectation(STRING_LIST_EXPECTED).expectEmpty(),
                getExpectation(STRING_LIST_EXPECTED).expectEquals(Set.of("one", "two", "three")),
                getExpectation(STRING_LIST_EXPECTED).expectContains("four"),
                getExpectation(STRING_LIST_EXPECTED).expectContains(1),
                getExpectation(STRING_LIST_EXPECTED).expectContainsAll(List.of("one", 1)),
                getExpectation(STRING_LIST_EXPECTED).expectContainsAll(List.of("one", "two", "three", "four")),
                getExpectation(STRING_LIST_EXPECTED).expectSize(0),
                getExpectation(STRING_LIST_EXPECTED).expectDoesNotContain("one"),
                getExpectation(STRING_LIST_EXPECTED).expectDoesNotContainAny(List.of("one", "three"))
            )
        ),
        Arguments.of(
            INTEGER_LIST_EXPECTED,
            //Will Pass
            List.of(
                getExpectation(INTEGER_LIST_EXPECTED).expectEquals(INTEGER_LIST_EXPECTED),
                getExpectation(INTEGER_LIST_EXPECTED).expectContains(4),
                getExpectation(INTEGER_LIST_EXPECTED).expectContainsAll(Set.of(4, 6)),
                getExpectation(INTEGER_LIST_EXPECTED).expectSize(3),
                getExpectation(INTEGER_LIST_EXPECTED).expectDoesNotContain(3),
                getExpectation(INTEGER_LIST_EXPECTED).expectDoesNotContainAny(List.of(1, 2))
            ),
            //Will Fail
            List.of(
                getExpectation(INTEGER_LIST_EXPECTED).expectEmpty(),
                getExpectation(INTEGER_LIST_EXPECTED).expectEquals(SET_EXPECTED),
                getExpectation(INTEGER_LIST_EXPECTED).expectContains(1),
                getExpectation(INTEGER_LIST_EXPECTED).expectContainsAll(Set.of(3, 4)),
                getExpectation(INTEGER_LIST_EXPECTED).expectSize(2),
                getExpectation(INTEGER_LIST_EXPECTED).expectDoesNotContain(4),
                getExpectation(INTEGER_LIST_EXPECTED).expectDoesNotContainAny(List.of(4, 6))
            )
        ),
        Arguments.of(
            SET_EXPECTED,
            //Will Pass
            List.of(
                getExpectation(SET_EXPECTED).expectContains("seven"),
                getExpectation(SET_EXPECTED).expectContainsAll(Set.of("seven", "nine")),
                getExpectation(SET_EXPECTED).expectSize(3),
                getExpectation(SET_EXPECTED).expectDoesNotContain(3),
                getExpectation(SET_EXPECTED).expectDoesNotContainAny(List.of("one", "two"))
            ),
            //Will Fail
            List.of(
                getExpectation(SET_EXPECTED).expectEmpty(),
                getExpectation(SET_EXPECTED).expectContains(1),
                getExpectation(SET_EXPECTED).expectContainsAll(Set.of(3, 4)),
                getExpectation(SET_EXPECTED).expectSize(2),
                getExpectation(SET_EXPECTED).expectDoesNotContain("seven"),
                getExpectation(SET_EXPECTED).expectDoesNotContainAny(List.of("seven", "nine"))
            )
        ),
        Arguments.of(
            Collections.emptyList(),
            //Will Pass
            List.of(
                getExpectation(Collections.emptyList()).expectEmpty()
            ),
            //Will Fail
            List.of()
        )
    );
  }
}