package de.cwrose.disical.corba.Exceptions;

import java.io.*;

/*
 * 
 * @author Carsten Rose <deafman@users.berlios.de>
 * @version $Revision: 1.1 $
 * @see de.cwrose.disical.corba.CSetup
 * 
 */
public class NoResolve extends java.lang.Exception
{
	public NoResolve()
	{
	    super ("Server can not resolve Object!");
	}
	 
	public NoResolve(String objName)
	{
	    super ("Server can not resolve Object " + objName + "!");
	}
}
