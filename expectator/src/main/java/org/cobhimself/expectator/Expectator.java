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

import java.util.function.BiPredicate;
import org.cobhimself.expectator.expectations.Expectation;

/**
 * An <code>Expectator</code> is simply a <code>BiPredicate&lt;T, Object&gt;</code> functional
 * interface whose first argument is an {@link Expectation}'s expected value and whose second
 * argument is a supplemental <code>Object</code> value to be used within the
 * <code>BiPredicate</code> in some way.
 * <p>
 * Usually, the second argument represents some "actual" value used to compare against the expected
 * value. By convention, most <code>Expectator</code>s use <code>e</code> (expected) as the
 * first argument and <code>a</code> (actual) as the second argument.
 * <p>
 * Being a <code>BiPredicate</code>, the return value will be a <code>Boolean</code> which
 * represents the result of testing the expected vs actual value returned by the lambda.
 * <p>
 * For example, an <code>Expectator</code> could be defined as such:
 * <code>
 *   var expectator = (e, a) -&gt; e = a;
 * </code>
 * <p>
 * The above <code>Expectator</code> represents an expectation for the <code>e</code> argument
 * (the "expected" value) to be equal to the <code>a</code> argument (the "actual"
 * value).
 * <p>
 * The name of the second argument is arbitrary, but it is encouraged to always name the
 * first argument <code>expected</code> or <code>e</code> for short.
 *
 * @param <T> the type of the expected value the expectator will hold and will be compared in some
 *           way to the actual value <code>Object</code>
 */
public interface Expectator<T> extends BiPredicate<T, Object> {
}
