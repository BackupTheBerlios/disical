package de.cwrose.disical.util;

import java.io.FileNotFoundException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * This entity resolver redirects all cfg://XXX urls to 
 * CfgReader.getXmlFileReader ("XXX");
 * 
 * @author stepn
 * @version $Revision: 1.1 $
 * @see CfgReader.getXmlFileReader
 */
public class CfgEntityResolver implements EntityResolver
{
	public final String CFG_URL_PREFIX = "cfg://";
	
	public InputSource resolveEntity (String publicId, String systemId)
    {
    	if (systemId.startsWith (CFG_URL_PREFIX))
    	{
    		String cfg = systemId.substring (CFG_URL_PREFIX.length());
    		try
    		{
    			return new InputSource (CfgReader.getXmlFileReader (cfg));
    		}
    		catch (FileNotFoundException e)
    		{
    			return null;
    		}
    	}
		else
	    	return null;
    }
}