package de.cwrose.disical.util;

import java.io.*;
import java.util.*;

/*
 * 
 * Test class for cfg reader.  Lists all accessible properties for a
 * given set of configurations.
 *
 * @author Stefan Plantikow <stepn@users.berlios.de>
 * @version $Revision: 1.1 $
 * @see java.lang.System#getProperty
 * 
 */
public class CfgTester
{
	 public final static void main (String argv[])
	 {
		  System.out.println ("#");
		  System.out.println ("# CfgTester: Properties currently in use");
		  System.out.println ("#");
		  System.out.println ("# " + CfgReader.MAIN_DIR + " was '" 
									 + System.getProperty(CfgReader.MAIN_DIR) + "'");
		  System.out.println ("# " + CfgReader.LOCAL_DIR + " was '" 
									 + System.getProperty(CfgReader.LOCAL_DIR) + "'");
		  System.out.println ("#");
		  System.out.println (" ");

		  for (int i=0; i < argv.length; i++)
				try
					 {
						  System.out.println ("#");
						  System.out.println 
								("# Properties for '" + argv [i] + "'");
						  CfgReader.readCfg (argv [i]).store (System.out, "");
						  System.out.println (" ");
					 }
				catch (CfgMissingException e)
					 {
						  System.err.println (e.toString ());
					 }
		      catch (IOException e)
					 {
						  System.err.println (e.toString ());
					 }					 
	 }
}
