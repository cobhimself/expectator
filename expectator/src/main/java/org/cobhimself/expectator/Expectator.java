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

/**
 * An <code>Expectator</code> is simply a <code>BiPredicate&lt;T, Object&gt;</code> functional
 * interface whose arguments are the expected value (<code>T</code>) and actual value (the
 * <code>Object</code>) to be compared in some way.
 * <p>
 * Being a <code>BiPredicate</code>, the return value will be a <code>Boolean</code> which
 * represents the result of testing the expected vs actual value returned by the lambda.
 *
 * @param <T> the type of the expected value the expectator will hold and will be compared to the
 *            actual value (of any <code>Object</code> type)
 */
public interface Expectator<T> extends BiPredicate<T, Object> {
}
