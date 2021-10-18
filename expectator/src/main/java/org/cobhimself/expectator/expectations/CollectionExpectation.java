package org.cobhimself.expectator.expectations;

import java.util.Collection;

/**
 * A <code>CollectionExpectation</code> is used to provide expectations for objects which implement
 * the <code>Collection</code> interface.
 */
public class CollectionExpectation extends Expectation<Collection<?>> {

  /**
   * Construct a <code>CollectionExpectation</code> with a known expected value up front.
   *
   * @param expectedValue the value our expectation expects
   * @param name          the name of the <code>CollectionExpectation</code>
   */
  protected CollectionExpectation(Collection<?> expectedValue, String name) {
    super(expectedValue, name);
  }

  /**
   * Construct a <code>CollectionExpectation</code> without specifying the expected value up front.
   *
   * @param name the name of the <code>CollectionExpectation</code>
   */
  protected CollectionExpectation(String name) {
    super(name);
  }

  /**
   * Expect our expected collection contains the provided actual value.
   *
   * @param actual the value we expect to be contained within our expected collection
   *
   * @return self
   */
  public CollectionExpectation expectContains(Object actual) {
    this.expect(
        Collection::contains,
        actual,
        "expected {expected} to contain {actual}"
    );

    return this;
  }

  /**
   * Expect our expected collection does not contain the provided actual value.
   *
   * @param actual the value we expect to not be contained within our expected collection
   *
   * @return self
   */
  public CollectionExpectation expectDoesNotContain(Object actual) {
    this.expect(
        (e, a) -> !e.contains(a),
        actual,
        "expected {expected} to not contain {actual}"
    );

    return this;
  }

  /**
   * Expect our expected collection to contain all the provided actual value.
   *
   * @param actual the collection of values we expect to be contained within our expected collection
   *
   * @return self
   */
  public CollectionExpectation expectContainsAll(Collection<?> actual) {
    //noinspection SimplifyStreamApiCallChains
    this.expect(
        //We can't rely on Collection.containsAll because of the wildcard capture, manually
        //loop through and use Collection.contains
        (e, a) -> ((Collection<?>) a).stream().allMatch(e::contains),
        actual,
        "expected {expected} to contain all of {actual}"
    );

    return this;
  }

  /**
   * Expect our expected collection to contain none of the items in the provided actual collection.
   *
   * @param actual the collection of values we expect to not be contained within our expected
   *               collection
   *
   * @return self
   */
  public CollectionExpectation expectDoesNotContainAny(Collection<?> actual) {
    this.expect(
        //We can't rely on Collection.containsAll because of the wildcard capture, manually
        //loop through and use Collection.contains
        (e, a) -> ((Collection<?>) a).stream().noneMatch(e::contains),
        actual,
        "expected {expected} to contain none of {actual}"
    );

    return this;
  }

  /**
   * Expect our expected collection to be the given size.
   *
   * @param actual the size we expect
   *
   * @return self
   */
  public CollectionExpectation expectSize(Integer actual) {
    this.expect(
        (e, a) -> e.size() == (Integer) a,
        actual,
        "expected {expected} to have a size of {actual}"
    );

    return this;
  }

  /**
   * Expect our expected collection to be empty.
   *
   * @return self
   */
  public CollectionExpectation expectEmpty() {
    this.expectSize(0);

    return this;
  }

}
