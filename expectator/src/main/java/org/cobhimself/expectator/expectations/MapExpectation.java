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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A <code>MapExpectation</code> is used to provide expectations for objects which implement
 * the <code>Map</code> interface.
 */
public class MapExpectation extends Expectation<Map<?,?>> {

  /**
   * Construct a <code>MapExpectation</code> with a known expected value up front.
   *
   * @param expectedValue the value our expectation expects
   * @param name          the name of the <code>MapExpectation</code>
   */
  protected MapExpectation(Map<?,?> expectedValue, String name) {
    super(expectedValue, name);
  }

  /**
   * Construct a <code>MapExpectation</code> without specifying the expected value up front.
   *
   * @param name the name of the <code>MapExpectation</code>
   */
  protected MapExpectation(String name) {
    super(name);
  }

  /**
   * Expect our expected map's keys to contain the provided actual value.
   *
   * @param actual the key we expect to be contained within our expected map's keys collection
   *
   * @return self
   */
  public MapExpectation expectKeysContain(Object actual) {
    this.expect(
        Map::containsKey,
        actual,
        "expected {expected} to have keys which contain {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map's values to contain the provided actual value.
   *
   * @param actual the value we expect to be contained within our expected map's value collection
   *
   * @return self
   */
  public MapExpectation expectValuesContain(Object actual) {
    this.expect(
        Map::containsValue,
        actual,
        "expected {expected} to have values which contain {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map's keys do not match the provided actual key.
   *
   * @param actual the key we expect to not be contained within our expected map's key collection
   *
   * @return self
   */
  public MapExpectation expectKeysDoNotContain(Object actual) {
    this.expect(
        (e, a) -> !e.containsKey(a),
        actual,
        "expected {expected} to not have keys which contain {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map's values do not match the provided actual value.
   *
   * @param actual the value we expect to not be contained within our expected map's value
   *               collection
   *
   * @return self
   */
  public MapExpectation expectValuesDoNotContain(Object actual) {
    this.expect(
        (e, a) -> !e.containsValue(a),
        actual,
        "expected {expected} to not have values which contain {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map's keys to match the given list of actual keys.
   *
   * @param actual the collection of keys we expect to be contained within our expected map
   *
   * @return self
   */
  public MapExpectation expectKeysContainAll(List<?> actual) {
    this.expect(
        (e, a) -> ((List<?>) a).stream().allMatch(e::containsKey),
        actual,
        "expected {expected} keys to contain all of {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map's keys to contain all the keys from the given actual map.
   *
   * @param actual the map whose keys we expect to be in our expected map's keys collection
   *
   * @return self
   */
  public MapExpectation expectKeysContainAll(Map<?, ?> actual) {
    this.expectKeysContainAll(Arrays.asList(actual.keySet().toArray()));

    return this;
  }

  /**
   * Expect our expected map to contain all the provided actual value.
   *
   * @param actual the collection of values we expect to be contained within our expected map
   *
   * @return self
   */
  public MapExpectation expectValuesContainAll(List<?> actual) {
    this.expect(
        (e, a) -> ((List<?>) a).stream().allMatch(e::containsValue),
        actual,
        "expected {expected} values to contain all of {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map's values to contain all the values from the given actual map.
   *
   * @param actual the map whose values we expect to be in our expected map's values collection
   *
   * @return self
   */
  public MapExpectation expectValuesContainAll(Map<?, ?> actual) {
    this.expectValuesContainAll(Arrays.asList(actual.values().toArray()));

    return this;
  }

  /**
   * Expect our expected map's keys to contain none of the keys in the provided actual list.
   *
   * @param actual the collection of keys we expect to not be contained within our expected
   *               map's key collection
   *
   * @return self
   */
  public MapExpectation expectKeysDoNotContainAny(List<?> actual) {
    this.expect(
        (e, a) -> ((List<?>) a).stream().noneMatch(e::containsKey),
        actual,
        "expected the keys of {expected} to contain none of these keys: {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map's keys to contain none of the keys in the provided map's key
   * list.
   *
   * @param actual a map whose keys we expect should not be found within our expected map's key
   *               collection.
   *
   * @return self
   */
  public MapExpectation expectKeysDoNotContainAny(Map<?, ?> actual) {
    this.expectKeysDoNotContainAny(Arrays.asList(actual.keySet().toArray()));

    return this;
  }

  /**
   * Expect our expected map's values to contain none of the items in the provided actual list.
   *
   * @param actual the collection of values we expect to not be contained within our expected
   *               map's value collection
   *
   * @return self
   */
  public MapExpectation expectValuesDoNotContainAny(List<?> actual) {
    this.expect(
        (e, a) -> ((List<?>) a).stream().noneMatch(e::containsValue),
        actual,
        "expected the values in {expected} to contain none of these values: {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map's values to contain none of the values in the provided map's value
   * list.
   *
   * @param actual a map whose values we expect should not be found within our expected map's value
   *               collection.
   *
   * @return self
   */
  public MapExpectation expectValuesDoNotContainAny(Map<?, ?> actual) {
    this.expectValuesDoNotContainAny(Arrays.asList(actual.values().toArray()));

    return this;
  }

  /**
   * Expect our expected map to be the given size.
   *
   * @param actual the size we expect
   *
   * @return self
   */
  public MapExpectation expectSize(Integer actual) {
    this.expect(
        (e, a) -> e.size() == (Integer) a,
        actual,
        "expected {expected} to have a size of {actual}"
    );

    return this;
  }

  /**
   * Expect our expected map to be empty.
   *
   * @return self
   */
  public MapExpectation expectEmpty() {
    this.expectSize(0);

    return this;
  }
}
