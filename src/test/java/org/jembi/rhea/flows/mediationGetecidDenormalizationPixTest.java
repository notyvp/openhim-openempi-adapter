package org.jembi.rhea.flows;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class mediationGetecidDenormalizationPixTest extends
		FunctionalTestCase {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Override
	protected void doSetUp() throws Exception {
		Logger.getRootLogger().setLevel(Level.INFO);
		super.doSetUp();
	}
	
	@Override
	protected void doTearDown() throws Exception {
		Logger.getRootLogger().setLevel(Level.WARN);
		super.doTearDown();
	}

	@Override
	protected String getConfigResources() {
		return "src/main/app/getecid-denormalization-pix.xml";
	}
	
	@Test
	public void testSendPixQuery() throws Exception {
		log.info("Starting test");
	    MuleClient client = new MuleClient(muleContext);
	    
	    Map<String, String> idMap = new HashMap<String, String>();
		
		// Mohawk
	    //idMap.put("id", "994620-002229-1988L");
	    //idMap.put("idType", "W2012CATOID");
	    
	    // OpenEMPI - cr.jembi.org
	    idMap.put("id", "123456789");
	    idMap.put("idType", "NID");
	    
	    //IHE Facility
	    //idMap.put("id", "IHEFACILITY-997");
	    //idMap.put("idType", "IHEFACILITY");
	    
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://getecid-pix", idMap, properties);
	    
	    assertNotNull(result.getPayload());
	    
	    // Mohawk
	    //assertEquals("756", result.getPayloadAsString());

	    log.info(result.getPayloadAsString());
	    
	    log.info("Test completed");
	}

}
