package de.cwrose.disical.util;

import java.io.*;

/*
 * Thrown whenever CfgReader can not find any configuration file for an
 * accessed configuration.
 * 
 * @author Stefan Plantikow <stepn@users.berlios.de>
 * @version $Revision: 1.1 $
 * @see CfgReader
 * 
 */
public class CfgMissingException extends java.lang.Exception
{
	 public CfgMissingException ()
	 {
		  super ("Could not read configuration properties file !");
	 }

	 public CfgMissingException (String cfg)
	 {
		  super 
				("Could not read configuration properties file for " + cfg + ".");
	 }
}
