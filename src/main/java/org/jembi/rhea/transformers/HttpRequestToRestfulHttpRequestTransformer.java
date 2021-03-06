/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.jembi.rhea.transformers;

import org.jembi.rhea.RestfulHttpRequest;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class HttpRequestToRestfulHttpRequestTransformer extends
		AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage msg, String enc) throws TransformerException {
		
		RestfulHttpRequest restMsg = new RestfulHttpRequest();
		
		String url = (String) msg.getInboundProperty("http.request");
		restMsg.setPath(url);
		String httpMethod = (String) msg.getInboundProperty("http.method");
		restMsg.setHttpMethod(httpMethod);
		
		try {
			String body = msg.getPayloadAsString();
			if (body != url) {
				restMsg.setBody(body);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return restMsg;
	}

}
