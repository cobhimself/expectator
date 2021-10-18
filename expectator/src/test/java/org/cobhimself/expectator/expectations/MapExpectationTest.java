package org.cobhimself.expectator.expectations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.cobhimself.expectator.exceptions.ExpectatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MapExpectationTest
    implements BaseExpectationTest<MapExpectation, Map<?,?>> {

  final static Map<Integer, String> MAP_1_EXPECTED = Map.of(1, "one", 2, "two", 3, "three");
  final static Map<String, Integer> MAP_2_EXPECTED = Map.of("four", 4, "five", 5, "six", 6);

  final static String NAME = "Map expectation";

  static MapExpectation getExpectation(Map<?, ?> map) {
    return new MapExpectation(map, NAME);
  }

  @Test
  void mainTest() {
    assertEquals(
        MAP_1_EXPECTED,
        getExpectation(MAP_1_EXPECTED)
            .getExpectedValue()
    );

    assertEquals(
        MAP_2_EXPECTED,
        getExpectation(MAP_2_EXPECTED)
            .getExpectedValue()
    );
  }

  @Test
  void testConstructors() {
    this.testExpectedValueAndNameConstructor(
        new MapExpectation(MAP_1_EXPECTED, NAME),
        MAP_1_EXPECTED,
        NAME
    );

    this.testNameOnlyConstructor(new MapExpectation(NAME), NAME);
  }

  @ParameterizedTest()
  @MethodSource("parameters")
  void testExpectations(
      Map<?,?> expected,
      List<MapExpectation> willPass,
      List<MapExpectation> willFail
  ) {
    var index = new AtomicInteger(1);
    willPass.forEach(expectation -> {
      expectation.setExpectedValue(expected);
      assertDoesNotThrow(
          expectation::confirm,
          "pass example " + index.getAndIncrement() + ": "
      );
    });
    index.set(1);
    willFail.forEach(expectation -> {
      expectation.setExpectedValue(expected);
      assertThrows(
          ExpectatorException.class,
          expectation::confirm, "fail example " + index.getAndIncrement() + ": "
      );
    });
  }

  static Stream<Arguments> parameters() {
    return Stream.of(
        Arguments.of(
            MAP_1_EXPECTED,
            //Will Pass
            List.of(
                getExpectation(MAP_1_EXPECTED).expectValuesContain("one"),
                getExpectation(MAP_1_EXPECTED).expectValuesContain("two"),
                getExpectation(MAP_1_EXPECTED).expectKeysContain(1),
                getExpectation(MAP_1_EXPECTED).expectKeysContain(2),
                getExpectation(MAP_1_EXPECTED).expectValuesContainAll(List.of("one", "two")),
                getExpectation(MAP_1_EXPECTED).expectValuesContainAll(Map.of(1, "one", 2, "two")),
                getExpectation(MAP_1_EXPECTED).expectKeysContainAll(List.of(1, 2)),
                getExpectation(MAP_1_EXPECTED).expectKeysContainAll(Map.of(1, "one", 2, "two")),
                getExpectation(MAP_1_EXPECTED).expectSize(3),
                getExpectation(MAP_1_EXPECTED).expectValuesDoNotContain(3),
                getExpectation(MAP_1_EXPECTED).expectKeysDoNotContain("three"),
                getExpectation(MAP_1_EXPECTED).expectValuesDoNotContainAny(List.of("four", "five")),
                getExpectation(MAP_1_EXPECTED).expectValuesDoNotContainAny(
                    Map.of(4, "four", 5, "five")
                ),
                getExpectation(MAP_1_EXPECTED).expectKeysDoNotContainAny(List.of(4, 5)),
                getExpectation(MAP_1_EXPECTED).expectKeysDoNotContainAny(
                    Map.of("four", 4, "five", 5)
                )
            ),
            //Will Fail
            List.of(
                getExpectation(MAP_1_EXPECTED).expectEmpty(),
                getExpectation(MAP_1_EXPECTED).expectEquals(Map.of(4, "one", 5, "two", 6, "three")),
                getExpectation(MAP_1_EXPECTED).expectValuesContain("four"),
                getExpectation(MAP_1_EXPECTED).expectKeysContain(4),
                getExpectation(MAP_1_EXPECTED).expectValuesContain(1),
                getExpectation(MAP_1_EXPECTED).expectKeysContain("one"),
                getExpectation(MAP_1_EXPECTED).expectValuesContainAll(List.of("one", 1)),
                getExpectation(MAP_1_EXPECTED).expectKeysContainAll(List.of("two", 4)),
                getExpectation(MAP_1_EXPECTED).expectValuesContainAll(List.of("one", "two", "three", "four")),
                getExpectation(MAP_1_EXPECTED).expectSize(0),
                getExpectation(MAP_1_EXPECTED).expectValuesDoNotContain("one"),
                getExpectation(MAP_1_EXPECTED).expectValuesDoNotContainAny(List.of("one", "three")),
                getExpectation(MAP_1_EXPECTED).expectKeysDoNotContain(1),
                getExpectation(MAP_1_EXPECTED).expectKeysDoNotContainAny(List.of(2, 3))
            )
        ),
        Arguments.of(
            MAP_2_EXPECTED,
            //Will Pass
            List.of(
                getExpectation(MAP_2_EXPECTED).expectValuesContain(4),
                getExpectation(MAP_2_EXPECTED).expectValuesContain(5),
                getExpectation(MAP_2_EXPECTED).expectKeysContain("four"),
                getExpectation(MAP_2_EXPECTED).expectKeysContain("five"),
                getExpectation(MAP_2_EXPECTED).expectValuesContainAll(List.of(5, 6)),
                getExpectation(MAP_2_EXPECTED).expectValuesContainAll(Map.of("four", 4, "five", 5)),
                getExpectation(MAP_2_EXPECTED).expectKeysContainAll(List.of("four", "five")),
                getExpectation(MAP_2_EXPECTED).expectKeysContainAll(Map.of("four", 4, "five", 5)),
                getExpectation(MAP_2_EXPECTED).expectSize(3),
                getExpectation(MAP_2_EXPECTED).expectValuesDoNotContain("three"),
                getExpectation(MAP_2_EXPECTED).expectKeysDoNotContain(3),
                getExpectation(MAP_2_EXPECTED).expectValuesDoNotContainAny(List.of(1, 2)),
                getExpectation(MAP_2_EXPECTED).expectValuesDoNotContainAny(
                    Map.of("one", 2, "two", 3)
                ),
                getExpectation(MAP_2_EXPECTED).expectKeysDoNotContainAny(List.of("one", "two")),
                getExpectation(MAP_2_EXPECTED).expectKeysDoNotContainAny(
                    Map.of(4, "four", 5, "five")
                )
            ),
            //Will Fail
            //MAP_2_EXPECTED = Map.of("four", 4, "five", 5, "six", 6);
            List.of(
                getExpectation(MAP_2_EXPECTED).expectEmpty(),
                getExpectation(MAP_2_EXPECTED).expectEquals(Map.of(4, "four", 5, "five", 6, "six")),
                getExpectation(MAP_2_EXPECTED).expectValuesContain(1),
                getExpectation(MAP_2_EXPECTED).expectKeysContain("one"),
                getExpectation(MAP_2_EXPECTED).expectValuesContain(1),
                getExpectation(MAP_2_EXPECTED).expectKeysContain("one"),
                getExpectation(MAP_2_EXPECTED).expectValuesContainAll(List.of("one", 1)),
                getExpectation(MAP_2_EXPECTED).expectKeysContainAll(List.of("two", 4)),
                getExpectation(MAP_2_EXPECTED).expectValuesContainAll(List.of("one", "two", "three", "four")),
                getExpectation(MAP_2_EXPECTED).expectSize(0),
                getExpectation(MAP_2_EXPECTED).expectValuesDoNotContain(4),
                getExpectation(MAP_2_EXPECTED).expectValuesDoNotContainAny(List.of(5, 6)),
                getExpectation(MAP_2_EXPECTED).expectKeysDoNotContain("four"),
                getExpectation(MAP_2_EXPECTED).expectKeysDoNotContainAny(List.of("five", "six"))
            )
        ),
        Arguments.of(
            Collections.emptyMap(),
            //Will Pass
            List.of(
                getExpectation(Collections.emptyMap()).expectEmpty()
            ),
            //Will Fail
            List.of()
        )
    );
  }
}