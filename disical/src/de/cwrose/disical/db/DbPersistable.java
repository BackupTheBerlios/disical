package de.cwrose.disical.db;

import org.exolab.castor.jdo.*;

public class DbPersistable {

	public boolean persist ()
	{
		try {
			Database db = DbManager.getConnection ();
			db.begin ();
			db.create (this);
			db.commit ();
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}
}
