package de.cwrose.disical.db;

import org.exolab.castor.jdo.*;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import de.cwrose.disical.util.HackHelper;
import java.lang.reflect.InvocationTargetException;
import org.exolab.castor.jdo.DataObjectAccessException;

public class DbPersistable implements org.exolab.castor.jdo.TimeStampable{
	/* Flag that stores whether a DbPersistable must be created 
	   or updated when persisted to the db */
	private boolean create_persistable = true;

	/* TimeStamp for Castor Long TRansactions */
	private long jdoTimeStamp = 
		org.exolab.castor.jdo.TimeStampable.NO_TIMESTAMP;

	/* The skel */
	private org.omg.PortableServer.Servant servant = null;

	public DbPersistable () {
		jdoSetTimeStamp (System.currentTimeMillis());
	}

	protected final void setServant (org.omg.PortableServer.Servant servant) {
		this.servant = servant;
	}

	protected final org.omg.PortableServer.Servant getServant ()
	{
		return this.servant;
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

	public long jdoGetTimeStamp () { 
		return this.jdoTimeStamp; 
	}

	public void jdoSetTimeStamp (long jdoTimeStamp) {
		this.jdoTimeStamp = jdoTimeStamp;
	}
}
