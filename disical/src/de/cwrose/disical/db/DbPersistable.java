package de.cwrose.disical.db;

import org.exolab.castor.jdo.*;
import java.util.Hashtable;

public class DbPersistable {
	private boolean create_persistable = true;

	private static Hashtable reverseMapping;

	static
	{
		reverseMapping = new Hashtable ();
	}

	protected static void putBubble(org.omg.CORBA.Object o, DbPersistable p)
	throws PersistenceException
    {
		if (reverseMapping.contains (o))
			throw new PersistenceException 
				("Attempt to overwrite bubble for CORBA object");
		reverseMapping.put (o, p);
	}

	protected static DbPersistable lookupBubble(org.omg.CORBA.Object o)
	throws PersistenceException
	{
		DbPersistable p = null;
		if (!reverseMapping.contains (o))
		{
			Exception e = new PersistenceException 
				("Bubble not found for exsiting	CORBA object");
			e.printStackTrace(System.out);
		}
		try
		{
			p = (DbPersistable)reverseMapping.get(o);
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
