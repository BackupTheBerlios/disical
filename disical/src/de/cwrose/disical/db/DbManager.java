package de.cwrose.disical.db;

import java.io.StringWriter;
import de.cwrose.disical.util.CfgReader;
import de.cwrose.disical.util.CfgEntityResolver;
import org.exolab.castor.jdo.*;
import java.io.*;
import org.xml.sax.EntityResolver;
import de.cwrose.disical.corba.*;

/**
 * Static class for Castor JDO -> DB Initialization and Connection management
 * @author stepn
 * @version $Revision: 1.2 $
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
			jdo.setDatabaseName (dbname);
			{
				Writer writer = new StringWriter ();
				CfgReader.evaluateTemplate 
					(props.getProperty ("db-mgr-props"), 
					writer, CfgReader.getXmlFileReader(props.getProperty("db-mgr-jdocfg")));
				writer.flush ();
				Reader reader = new StringReader (writer.toString());
				EntityResolver resolver = new CfgEntityResolver ();
				jdo.loadConfiguration (new org.xml.sax.InputSource (reader), resolver, null);
			}
			jdo.setClassLoader (jdo.getClass ().getClassLoader ());
			jdo.setDatabasePooling (props.getProperty("db-mgr-pooling") == "enabled");
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
			System.err.println (e.getLocalizedMessage());
			System.err.println ("Can't initialize Castor JDO. I die.");
			System.exit (1);
		}			
	}
	
	static Database getConnection() throws DatabaseNotFoundException, PersistenceException
	{
		return jdo.getDatabase ();
	}	
	
	/**
	 * Little tester. Initializes JDO, gets a db connection and quits after rollback.
	 */
	public static void main (String argv []) throws DatabaseNotFoundException, PersistenceException
	{
		System.out.println ("Hello Database !");
		Database db = DbManager.getConnection ();
		System.out.println ("Hello Castor !");
		db.begin ();
		DisicalUser u = new DisicalUser ();
		u.setLogin ("carsten");
		u.setName ("Carsten Rose");
		u.setPasswd ("foobar");
		u.setEmail ("carsten@localhost");
		db.create (u);
		db.commit();
		System.out.println ("Ciao !");
	}
}
