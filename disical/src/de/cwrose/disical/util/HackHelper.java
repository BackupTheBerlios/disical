package de.cwrose.disical.util;

import java.io.PrintStream;

public final class HackHelper
{
	public final static void printStackTrace (PrintStream out, String msg)
	{
		Exception e = new IllegalStateException (msg);
		try
			{
				throw e;
			}
		catch (Exception x)
			{
				x.printStackTrace (out);
				out.println ("TRACED STACK. CAUSE: "+msg);
			}
	}

}
