package de.cwrose.disical.util;

import java.io.*;
import java.util.*;

/*
 * 
 * Test class for cfg reader velocity evaluation service.
 *
 * @author Stefan Plantikow <stepn@users.berlios.de>
 * @version $Revision: 1.1 $
 * @see java.lang.System#getProperty
 * 
 */
public class VeloTester
{
	 public final static void main (String argv[]) throws IOException
	 {
		  OutputStreamWriter writer = new OutputStreamWriter (System.out);

		  if (argv.length < 2) 
		  {
				System.err.println ("VeloTester cfg xmlfile1 xmlfile2 ...");
				System.exit (1);
		  }

		  for (int i=1; i < argv.length; i++)
				try
					 {
						  CfgReader.evaluateTemplate 
								(argv [0], 
								 writer, 
								 CfgReader.getXmlFileReader (argv[i]));
					 }
				catch (Exception e)
					 {
						  System.err.println (e.toString ());
					 }

		  writer.flush ();
	 }
}



