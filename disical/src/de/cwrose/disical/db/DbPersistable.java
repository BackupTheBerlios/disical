package de.cwrose.disical.db;

import org.exolab.castor.jdo.*;

public class DbPersistable {
	private boolean create_persistable = true;

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
