package de.cwrose.disical.db;

import org.exolab.castor.jdo.*;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import de.cwrose.disical.util.HackHelper;
import java.lang.reflect.InvocationTargetException;
import org.exolab.castor.jdo.DataObjectAccessException;

public class DbPersistable {
	private boolean create_persistable = true;

	private static Hashtable reverseMapping;

	static
	{
		reverseMapping = new Hashtable ();
	}

	public DbPersistable () {
		//		HackHelper.printStackTrace (System.out, "Constructing Persistable");
	}

	protected static void putBubble(org.omg.CORBA.Object o, DbPersistable p)
	throws PersistenceException
    {
		//		System.out.println ("DbPersistable.putBubble: "+o+"("+o.getClass().toString()+")"+"="+p);
		//HackHelper.printStackTrace (System.out, "PUT_BUBBLE");
		if (reverseMapping.containsKey (o))
			throw new PersistenceException 
				("Attempt to overwrite bubble for CORBA object");
		reverseMapping.put (o, p);
	}

	protected static void updateBubble(org.omg.CORBA.Object o, DbPersistable p)
    {
		//System.out.println ("DbPersistable.updBubble: "+o+"("+o.getClass().toString()+")"+"="+p);
		//HackHelper.printStackTrace (System.out, "PUT_BUBBLE");
		reverseMapping.put (o, p);
		//HackHelper.printStackTrace (System.out, "UPDATE_BUBBLE");
	}

	protected static DbPersistable lookupBubble(org.omg.CORBA.Object o)
	throws PersistenceException
	{
		DbPersistable p = null;
		//System.out.println ("DbPersistable.lookupBubble: "+o+"("+o.getClass().toString()+")");
		//HackHelper.printStackTrace (System.out, "PUT_BUBBLE");
		if (!reverseMapping.containsKey (o))
		{
			Exception e = new PersistenceException 
				("Bubble not found for exsiting	CORBA object");
			e.printStackTrace(System.out);
		}
		try
		{
			p = (DbPersistable)reverseMapping.get(o);
		    //System.out.println ("DbPersistable.retBubble: "+p);
			return p;
		}
		catch (ClassCastException e)
		{
			throw new PersistenceException 
		("Non-DbPersistable registered as bubble for CORBA object.  Ouch.");
		}
	}

	public final void growOld ()
	{
		create_persistable = false;
	}

	public final boolean isNew ()
	{
		return create_persistable;
	}

	public final void create (Database db)
		throws org.exolab.castor.jdo.PersistenceException
	{
		try {
			db.create (this);
		}
		catch (Exception e) {
			Throwable f = filterEx (e);
			HackHelper.printEx (f, System.out);
			throw new PersistenceException (f.getMessage());
		}
		this.growOld ();
	}

	public final void update (Database db)
		throws org.exolab.castor.jdo.PersistenceException
	{
		try {
			db.update (this);
		}
		catch (Exception e) {
			Throwable f = filterEx (e);
			HackHelper.printEx (f, System.out);
			throw new PersistenceException (f.getMessage());
		}
	}

	public final void persist (Database db)
		throws org.exolab.castor.jdo.PersistenceException
	{
		if (isNew ())
			{
				this.create (db);
				this.growOld ();
			}
		else
			this.update (db);
	}

	public final void delete (Database db)
		throws org.exolab.castor.jdo.PersistenceException
	{
		if (!isNew ())
			try {
				db.remove (this);
			}
			catch (Exception e) {
				Throwable f = filterEx (e);
				HackHelper.printEx (f, System.out);
				throw new PersistenceException (f.getMessage());
			}
		else
			throw new org.exolab.castor.jdo.PersistenceException
				("Can't delete object that is not persistent !");
	}


	public static Throwable filterEx(Throwable t)
	{
		if (t instanceof DataObjectAccessException)
			{
				return 
					filterEx (((DataObjectAccessException)t).getException());
			}

		if (t instanceof InvocationTargetException)
			{
				return
					filterEx (((InvocationTargetException)t)
							  .getTargetException());
			}
		return t;
	}


}
