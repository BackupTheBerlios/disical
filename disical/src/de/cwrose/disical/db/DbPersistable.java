package de.cwrose.disical.db;

import org.exolab.castor.jdo.*;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import de.cwrose.disical.util.HackHelper;
import java.lang.reflect.InvocationTargetException;
import org.exolab.castor.jdo.DataObjectAccessException;

public class DbPersistable 
implements org.exolab.castor.jdo.TimeStampable {
	/* Flag that stores whether a DbPersistable must be created 
	   or updated when persisted to the db */
	private boolean create_persistable = true;

	/* Maps CORBA.Object -> bubble */
	private static Hashtable reverseMapping;

	private long jdoTimeStamp = 
		org.exolab.castor.jdo.TimeStampable.NO_TIMESTAMP;

	static
	{
		// Initialize Hashes 
		reverseMapping = new Hashtable ();
	}
	
	public DbPersistable () {
	}




	// ------------------------------------------------------------------------

	/**
	 * Put bubble p under entry o 
     * @throws PersistenceException on overwrite
     */
	protected static void putBubble(org.omg.CORBA.Object o, DbPersistable p)
	throws PersistenceException
    {
		if (reverseMapping.containsKey (o))
			throw new PersistenceException 
				("Attempt to overwrite bubble for CORBA object");
		reverseMapping.put (o, p);
	}



	/**
     * Update bubble p under entry p
     * @throws PersistenceException if not updateabnle 
	 */
	protected static void updateBubble(org.omg.CORBA.Object o, DbPersistable p)
    {
		reverseMapping.put (o, p);
    }



	/**
     * Lookup bubble for entry o
     * @throws PersistenceException if not found
	 */
    protected static DbPersistable lookupBubble(org.omg.CORBA.Object o)
	throws PersistenceException {
		DbPersistable p = null;

		if (!reverseMapping.containsKey (o)) {
			Exception e = new PersistenceException 
				("Bubble not found for exsiting	CORBA object");
			e.printStackTrace(System.out);
		}
		try	{
			p = (DbPersistable)reverseMapping.get(o);
			//System.out.println ("DbPersistable.retBubble: "+p);
			return p;
		}
		catch (ClassCastException e) {
			throw new PersistenceException 
				("Non-DbPersistable registered as bubble for CORBA " +
				 "object.  Ouch.");
		}
	}




	// ------------------------------------------------------------------------

    public void blow (org.omg.CORBA.Object obj) 
    throws PersistenceException 
    {
		putBubble (obj, this);
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
				this.jdoTimeStamp = System.currentTimeMillis ();
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


	public long jdoGetTimeStamp () { 
		return this.jdoTimeStamp; 
	}

	public void jdoSetTimeStamp (long jdoTimeStamp) {
		this.jdoTimeStamp = jdoTimeStamp;
	}
	
}
