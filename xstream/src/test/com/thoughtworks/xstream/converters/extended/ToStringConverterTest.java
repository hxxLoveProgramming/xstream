/*
 * Copyright (C) 2006 Joe Walnes.
 * Copyright (C) 2006, 2007, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 *
 * Created on 07. July 2006 by Joe Walnes
 */
package com.thoughtworks.xstream.converters.extended;

import java.util.Map;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

import junit.framework.TestCase;


/**
 * @author Paul Hammant
 */
public class ToStringConverterTest extends TestCase {

    public void testClaimsCanConvertRightType() throws NoSuchMethodException {
        final SingleValueConverter converter = new ToStringConverter(Foo.class);
        assertTrue(converter.canConvert(Foo.class));
    }

    public void testClaimsCantConvertWrongType() throws NoSuchMethodException {
        final SingleValueConverter converter = new ToStringConverter(Foo.class);
        assertFalse(converter.canConvert(Map.class));
    }

    public void testClaimsCantConvertWrongType2() {
        try {
            new ToStringConverter(Map.class);
            fail("shoulda barfed");
        } catch (final NoSuchMethodException e) {
            // expected.
        }
    }

    public void testCanConvertRightType() throws NoSuchMethodException {
        final SingleValueConverter converter = new ToStringConverter(Foo.class);
        assertTrue(converter.fromString("hello") instanceof Foo);
        assertEquals("hello", ((Foo)converter.fromString("hello")).foo);
    }

    public void testCanInnocentlyConvertWrongTypeToString() throws NoSuchMethodException {
        final SingleValueConverter converter = new ToStringConverter(Foo.class);
        assertEquals("whoa", converter.toString("whoa"));
    }

    public void testCantConvertWrongType() throws NoSuchMethodException {
        final SingleValueConverter converter = new ToStringConverter(BadFoo1.class);
        try {
            converter.fromString("whoa");
            fail("shoulda barfed");
        } catch (final ConversionException e) {
            assertTrue(e.getMessage().startsWith("Unable to target single String param constructor"));
            assertTrue(e.getCause() instanceof NullPointerException);
        }
    }

    public static class Foo {
        final String foo;

        public Foo(final String foo) {
            this.foo = foo;
        }

        @Override
        public String toString() {
            return foo;
        }
    }

    public static class BadFoo1 {
        public BadFoo1(final String string) {
            throw new NullPointerException("abc");
        }
    }

}
