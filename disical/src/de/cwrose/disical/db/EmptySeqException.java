package de.cwrose.disical.db;

import java.io.*;

/*
 * Thrown by db layer methods returnings corba sequences to indicate the
 * empty sequence.  This is kind of a hack because returning an empty seq
 * would be much better, but Java can't handle empy arrays so...
 * 
 * @author Stefan Plantikow <stepn@users.berlios.de>
 * @version $Revision: 1.1 $
 * @see CfgReader
 * 
 */
public class EmptySeqException extends java.lang.Exception
{
	 public EmptySeqException ()
	 {
		  super ("You got a result: An empty sequence.");
	 }

	 public EmptySeqException (String msg)
	 {
		  super 
			  ("You got a result: An empty sequence: "+"'"+msg+"'.");
	 }
}
