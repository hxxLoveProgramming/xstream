/*
 * Copyright (C) 2004, 2005 Joe Walnes.
 * Copyright (C) 2006, 2007, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 *
 * Created on 09. September 2004 by Joe Walnes
 */
package com.thoughtworks.xstream.io.xml;

import org.jdom.Element;


public class JDomWriterTest extends AbstractDocumentWriterTest<Element> {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        writer = new JDomWriter();
    }

    @Override
    protected DocumentReader createDocumentReaderFor(final Element node) {
        return new JDomReader(node);
    }

    // inherits tests from superclass
}
