/**
 * easyWSDL - easyWSDL toolbox Platform.
 * Copyright (c) 2008,  eBM Websourcing
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of California, Berkeley nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.ow2.easywsdl.wsdl.api.binding;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Nicolas Salatge - eBM WebSourcing
 */
public interface BindingProtocol {

    /**
     * Constants for the Message Exchange Patterns.
     * 
     */
    public enum SOAPMEPConstants {
        ONE_WAY("http://www.w3.org/2006/08/soap/mep/one-way/"), REQUEST_RESPONSE(
                "http://www.w3.org/2003/05/soap/mep/request-response"), SOAP_RESPONSE(
                "http://www.w3.org/2003/05/soap/mep/soap-response/");

        /**
         * 
         * @param pattern
         * @return
         */
        public static SOAPMEPConstants valueOf(final URI pattern) {
            SOAPMEPConstants result = null;
            if (pattern != null) {
                for (final SOAPMEPConstants mep : SOAPMEPConstants.values()) {
                    if (mep.nameSpace.equals(pattern.toString())) {
                        result = mep;
                    }
                }
            }
            return result;
        }

        private final String nameSpace;

        private final URI mepURI;

        /**
         * Creates a new instance of {@link SOAPMEPConstants}
         * 
         * @param nameSpace
         */
        private SOAPMEPConstants(final String nameSpace) {
            this.nameSpace = nameSpace;
            try {
                this.mepURI = new URI(nameSpace);
            } catch (final URISyntaxException e) {
                throw new Error("Unexpected Error in URI namespace syntax", e); 
            }
        }

        /**
         * 
         * @return
         */
        public URI value() {
            return this.mepURI;
        }

        /**
         * Please use this equals method instead of using :<code>
         * value().equals(mep)
         * </code> which is
         * almost 10 times slower...
         * 
         * @param mep
         * @return
         */
        public boolean equals(final URI mep) {
            return this.toString().equals(mep.toString());
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return this.nameSpace;
        }
    }

}
