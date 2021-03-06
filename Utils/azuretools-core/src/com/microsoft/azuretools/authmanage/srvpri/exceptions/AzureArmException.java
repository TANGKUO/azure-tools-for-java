/*
 * Copyright (c) Microsoft Corporation
 *   <p/>
 *  All rights reserved.
 *   <p/>
 *  MIT License
 *   <p/>
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 *  to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *  <p/>
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 *  the Software.
 *   <p/>
 *  THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 *  THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 *  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.microsoft.azuretools.authmanage.srvpri.exceptions;

import com.microsoft.azuretools.authmanage.srvpri.entities.AzureErrorArm;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vlashch on 10/25/16.
 */
public class AzureArmException extends AzureException {
    private final static Logger LOGGER = Logger.getLogger(AzureArmException.class.getName());
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AzureErrorArm azureError;

    public AzureArmException(String json){
        super(json);
        try {
            ObjectMapper mapper = new ObjectMapper();
            azureError = mapper.readValue(json, AzureErrorArm.class);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "c-tor", e);
        }
    }

    @Override
    public String getCode() {
        String desc = "";
        if (azureError != null) {
            desc = azureError.error.code;
        }
        return desc;

    }

    @Override
    public String getDescription() {
        String desc = "";
        if (azureError != null) {
            desc = azureError.error.message;
        }
        return desc;
    }
}
