package de.cwrose.disical.db;

import org.exolab.castor.jdo.*;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import de.cwrose.disical.util.HackHelper;

public class DbPersistable {
	private boolean create_persistable = true;

	private static Hashtable reverseMapping;

	static
	{
		reverseMapping = new Hashtable ();
	}

	public DbPersistable () {
		HackHelper.printStackTrace (System.out, "Constructing Persistable");
	}

	protected static void putBubble(org.omg.CORBA.Object o, DbPersistable p)
	throws PersistenceException
    {
		System.out.println ("DbPersistable.putBubble: "+o+"("+o.getClass().toString()+")"+"="+p);
		if (reverseMapping.containsKey (o))
			throw new PersistenceException 
				("Attempt to overwrite bubble for CORBA object");
		reverseMapping.put (o, p);
	}

	protected static DbPersistable lookupBubble(org.omg.CORBA.Object o)
	throws PersistenceException
	{
		DbPersistable p = null;
		System.out.println ("DbPersistable.lookupBubble: "+o+"("+o.getClass().toString()+")");
		if (!reverseMapping.containsKey (o))
		{
			Exception e = new PersistenceException 
				("Bubble not found for exsiting	CORBA object");
			e.printStackTrace(System.out);
		}
		try
		{
			p = (DbPersistable)reverseMapping.get(o);
		    System.out.println ("DbPersistable.retBubble: "+p);
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
		db.create (this);
		this.growOld ();
	}

	public final void update (Database db)
		throws org.exolab.castor.jdo.PersistenceException
	{
		db.update (this);
	}

	public final void persist (Database db)
		throws org.exolab.castor.jdo.PersistenceException
	{
		if (isNew ())
			this.create (db);
		else
			this.update (db);
	}

	public final void delete (Database db)
		throws org.exolab.castor.jdo.PersistenceException
	{
		if (!isNew ())
			db.remove (this);
		else
			throw new org.exolab.castor.jdo.PersistenceException
				("Can't delete object that is not persistent !");
	}

}
