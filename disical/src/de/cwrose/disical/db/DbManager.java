package de.cwrose.disical.db;

import java.io.StringWriter;
import de.cwrose.disical.util.CfgReader;
import de.cwrose.disical.util.CfgEntityResolver;
import org.exolab.castor.jdo.*;
import java.io.*;
import org.xml.sax.EntityResolver;
import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;

/**
 * Static class for Castor JDO -> DB Initialization and Connection management
 * @author stepn
 * @version $Revision: 1.5 $
 */
public class DbManager {
	private static JDO jdo;
	private static String dbname;
	private static java.util.Properties props;
	
	static 
	{
		try
		{
			props = CfgReader.readCfg("server");
			jdo = new JDO ();
			dbname = props.getProperty("db-name");
			if (dbname == null)
				throw new IllegalArgumentException 
					("Can't get server-property 'db-name'");
			jdo.setDatabaseName (dbname);
			{
				Writer writer = new StringWriter ();
				String m_props = props.getProperty ("db-mgr-props");
				String m_jdocfg = props.getProperty ("db-mgr-jdocfg");

				if (m_props == null)
					throw new IllegalArgumentException 
						("Can't get server-property 'db-mgr-props'");
				if (m_jdocfg == null)
					throw new IllegalArgumentException 
						("Can't get server-property 'db-mgr-jdocfg'");

				CfgReader.evaluateTemplate 
					(m_props, writer, CfgReader.getXmlFileReader (m_jdocfg));
				writer.flush ();
				Reader reader = new StringReader (writer.toString());
				EntityResolver resolver = new CfgEntityResolver ();
				jdo.loadConfiguration 
					(new org.xml.sax.InputSource (reader), resolver, null);
			}
			jdo.setClassLoader (jdo.getClass ().getClassLoader ());
			jdo.setDatabasePooling 
				(props.getProperty("db-mgr-pooling") == "enabled");
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
			System.err.println (e.getLocalizedMessage());
			System.err.println ("Can't initialize Castor JDO. I die.");
			System.exit (1);
		}			
	}
	
	static Database getConnection() 
		throws DatabaseNotFoundException, PersistenceException
	{
		return jdo.getDatabase ();
	}	
	
} /* Class */

