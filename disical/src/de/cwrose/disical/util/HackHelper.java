package de.cwrose.disical.util;
import java.util.Vector;
import java.util.Enumeration;
import java.io.PrintStream;

public final class HackHelper
{
	public final static void printStackTrace (PrintStream out, String msg)
	{
		Exception e = new IllegalStateException (msg);
		e.printStackTrace (out);
		out.println ("TRACED STACK. CAUSE: "+msg);
	}

   	public final static void printEx (Throwable e, PrintStream out)
	{
		out.println(e.getMessage());
		e.printStackTrace (out);
	}

	public final static void printObj (PrintStream out, String s, org.omg.CORBA.Object o)
	{
		out.println (s + "Class: "+o.getClass().toString() + " Ref:" + o);
	}
}
