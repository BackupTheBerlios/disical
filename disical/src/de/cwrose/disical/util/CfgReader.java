package de.cwrose.disical.util;

import java.io.*;
import java.util.*;
import java.io.StringWriter;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.app.VelocityEngine;

/***
 * Utility class that implements Properties as VelocityContext.
 * While all Properties Objects handled by CfgReader are in fact
 * CfgProperties-Objects, this is invisible to CfgReader-users.
 *
 * @author stepn
 * @revision $Revision: 1.3 $
 */
final class CfgProperties 
extends Properties 
implements org.apache.velocity.context.Context
{
	 public CfgProperties () {
		  super ();
	 }

	 public CfgProperties (Properties parent) {
		  super ();

		  this.putAll (parent);
	 }

	 public Object put (java.lang.String key,java.lang.Object value) {
		  return this.put (key, value);
	 }

	 public Object get (java.lang.String key) {
		  return super.get (key);
	 }

	 public Object remove (java.lang.String key) {
		  return super.remove (key);
	 }

	 public Object [] getKeys () {
		  return super.keySet ().toArray ();
	 }

	 public boolean containsKey (String key) {
		  return super.containsKey (key);
	 }
}


/*
 * Static singleton used to access configuration files of the disical
 * project.  As soon as CfgReader is initialized with local property
 * cfg dir LOCAL_DIR and default cfg dir MAIN_DIR it will live as long
 * as the java vm.
 * 
 * @author Stefan Plantikow <stepn@users.berlios.de>
 * @version $Revision: 1.3 $
 * @see java.lang.System#getProperty
 * 
 */
public final class CfgReader 
{
	 public final static String MAIN_DIR = "disical.cfg";
	 public final static String LOCAL_DIR = "disical.cfg.local";
	 public final static String CFG_EXT = ".properties";
	 public final static String XML_EXT = ".xml";
	 public final static String CFG_LOG = "CfgReader:";

	 static Hashtable cfgs;
	 static File mainDir;
	 static File localDir;

	 static 
	 {                  
        String mainStr = System.getProperty (MAIN_DIR);
		  mainDir = new File (mainStr);

		  String localStr = System.getProperty (LOCAL_DIR);
		  if (localStr == null)
				localStr = mainStr + File.separator + "local";
		  localDir = new File (localStr);

		  checkDir (mainDir);
		  checkDir (localDir);
		  cfgs = new Hashtable ();
	 }

	 static void checkDir (File fd)
	 {
		  try 
		  {
				if (! fd.isDirectory ())  
				{
					 System.err.println (fd.getAbsolutePath () 
                                    + " is not a directory !");
					 System.exit (1);
				}
				if (! fd.canRead ())  
				{
					 System.err.println (fd.getAbsolutePath () 
                                    + " is not readable !");
					 System.exit (1);
				}
		  }
		  catch (SecurityException e)  
		  {
				System.err.println 
				("Can't acces required configuration directory: "
				 + fd.getAbsolutePath () + "(Reason: " + e.getMessage () + ")");
				System.exit (1);
		  }
	 }

    /*
     * @return Properties for configuration with name cfg
     * @throws MissingCfgException if there is no cfg file in
     * LOCAL_DIR or MAIN_DIR
     */
	 public static Properties readCfg (String cfg) 
	 throws CfgMissingException
	 {
		  return (Properties) _readCfg (cfg);
	 }

	 private static CfgProperties _readCfg (String cfg)
	 throws CfgMissingException
	 {
		  CfgProperties props = (CfgProperties) cfgs.get (cfg);
		  if (props != null)
				return props;

		  props = readProps (localDir, cfg, readProps (mainDir, cfg, null));
		  
		  if (props == null)
				throw new CfgMissingException (cfg);

		  cfgs.put (cfg, props);
		  return props;
	 }

	 static CfgProperties readProps (File parent, 
												String cfg, CfgProperties pred)
	 {
		  File fd = new File (parent, cfg + CFG_EXT);
		  CfgProperties props = (pred == null) ? 
				new CfgProperties () : new CfgProperties (pred);

		  try 
		  {
				props.load (new FileInputStream (fd));
		  }
		  catch (IOException e)
		  {
				return pred;
		  }
		  
		  return props;
	 }  

    /**
     * @param readLocal if true, read file from local cfg directory
     * @return Buffered File Reader to cfg.xml.
     */
	 public static Reader getXmlFileReader (String cfg, boolean readLocal) 
    throws FileNotFoundException {
		  return new BufferedReader (new FileReader 
			   (new File (readLocal ? localDir : mainDir, cfg + XML_EXT)));
	 }

    /**
     * Calls getXmlFileReader(cfg,false).
     * @see getXmlFileReader(String,boolean)
     */
	 public static Reader getXmlFileReader (String cfg) 
    throws FileNotFoundException {
		  return getXmlFileReader (cfg, false);
	 }

    /**
     * Parses reader using Velocity Template Enginge with VelocityContext
     * containing all String values from getProps (cfg) and writes the result
     * to writer.
     */
	 public static boolean evaluateTemplate (String cfg, 
														  java.io.Writer writer, 
														  java.io.Reader reader) 
		  throws 
		  ResourceNotFoundException, MethodInvocationException, 
		  ParseErrorException, CfgMissingException, Exception 
	 {
		  CfgProperties props = _readCfg (cfg);
		  VelocityEngine ve = new VelocityEngine ();
		  ve.init (readCfg ("velocity"));
		  return ve.evaluate (props, writer, CFG_LOG+cfg, reader);
	 }
} 



