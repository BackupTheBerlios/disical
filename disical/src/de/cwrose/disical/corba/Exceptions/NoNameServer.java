package de.cwrose.disical.corba.Exceptions;

import java.io.*;

/*
 * 
 * @author Carsten Rose <deafman@users.berlios.de>
 * @version $Revision: 1.1 $
 * @see de.cwrose.disical.corba.CSetup
 * 
 */
public class NoNameServer extends java.lang.Exception
{
	 public NoNameServer()
	 {
		  super ("Server can not find NameServer!");
	 }
}
