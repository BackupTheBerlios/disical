package de.cwrose.disical.util;

import java.io.*;
import java.util.*;

/*
 * Static singleton used to access configuration files of the disical
 * project.  As soon as CfgReader is initialized with local property
 * cfg dir LOCAL_DIR and default cfg dir MAIN_DIR it will live as long
 * as the java vm.
 * 
 * @author Stefan Plantikow <stepn@users.berlios.de>
 * @version $Revision: 1.1 $
 * @see java.lang.System#getProperty
 * 
 */
public final class CfgReader 
{
	 public final static String MAIN_DIR = "disical.cfg";
	 public final static String LOCAL_DIR = "disical.cfg.local";
	 public final static String CFG_EXT = ".properties";

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
		  Properties props = (Properties) cfgs.get (cfg);
		  if (props != null)
				return props;

		  props = readProps (mainDir, cfg, null);
		  props = readProps (localDir, cfg, props);
		  
		  if (props == null)
				throw new CfgMissingException (cfg);

		  cfgs.put (cfg, props);
		  return props;
	 }

	 static Properties readProps (File parent, String cfg, Properties pred)
	 {
		  File fd = new File (parent, cfg + CFG_EXT);
		  Properties props = (pred == null) ? 
				new Properties () : new Properties (pred);

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
} 

