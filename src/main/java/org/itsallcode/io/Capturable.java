package org.itsallcode.io;

/*-
 * #%L
 * JUnit5 System Extensions
 * %%
 * Copyright (C) 2018 itsallcode.org
 * %%
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v2.0 which accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 * #L%
 */

public interface Capturable
{
    /**
     * Activate capturing
     */
    public void capture();

    /**
     * Get the data that was captured.
     *
     * @return captured data.
     */
    public String getCapturedData();
}