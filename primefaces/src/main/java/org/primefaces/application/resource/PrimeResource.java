/*
 * The MIT License
 *
 * Copyright (c) 2009-2025 PrimeTek Informatics
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.application.resource;

import org.primefaces.context.PrimeApplicationContext;
import org.primefaces.context.PrimeRequestContext;

import jakarta.faces.application.Resource;
import jakarta.faces.application.ResourceWrapper;
import jakarta.faces.context.FacesContext;

/**
 * {@link ResourceWrapper} which appends the version of PrimeFaces to the URL.
 */
public class PrimeResource extends ResourceWrapper {

    private String version;

    public PrimeResource(Resource wrapped) {
        super(wrapped);

        FacesContext context = FacesContext.getCurrentInstance();
        version = PrimeRequestContext.getCurrentInstance(context).isHideResourceVersion()
                ? null
                : "&v=" + PrimeApplicationContext.getCurrentInstance(context).getEnvironment().getBuildVersion();
    }

    @Override
    public String getRequestPath() {
        if (version == null) {
            return super.getRequestPath();
        }
        return super.getRequestPath() + version;
    }

    @Override
    public String getContentType() {
        return getWrapped().getContentType();
    }

    @Override
    public String getLibraryName() {
        return getWrapped().getLibraryName();
    }

    @Override
    public String getResourceName() {
        return getWrapped().getResourceName();
    }

    @Override
    public void setContentType(final String contentType) {
        getWrapped().setContentType(contentType);
    }

    @Override
    public void setLibraryName(final String libraryName) {
        getWrapped().setLibraryName(libraryName);
    }

    @Override
    public void setResourceName(final String resourceName) {
        getWrapped().setResourceName(resourceName);
    }

    @Override
    public String toString() {
        return getWrapped().toString();
    }
}
