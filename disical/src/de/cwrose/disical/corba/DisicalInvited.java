// $Id: DisicalInvited.java,v 1.1 2002/01/30 17:11:44 deafman Exp $
package de.cwrose.disical.corba;

/**
 * CORBA-Object to set the Invitation-Status
 *
 * 
 *
 * @author deafman
 * @version $Revision: 1.1 $
 */
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.DbDate;
import de.cwrose.disical.db.DbManager;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;

public class DisicalInvited extends InvitedPOA {

	private DbInvited bubble = null;
	private short status;
	private boolean notify;
	
	public void setBubble( DbInvited bubble) {
		if (this.bubble != null)
			throw new IllegalStateException ("Don't burst my bubble, fool!");
		this.bubble = bubble;
	}

	public DbDate getBubble () {
		return this.bubble;
	}


	public void do_persist(Database db) 
		throws org.exolab.castor.jdo.PersistenceException
	{
		//		System.out.println ("PERSIST: "+getFromUser().getLogin ()+" "+((Object)this)+" via "+((Object)(bubble.getDate ()))+"/"+((Object)bubble));
		bubble.persist (db);
	}

	public boolean persist ()
	{
		try 
		{ 
			Database db = DbManager.getConnection ();
			db.begin ();
			this.do_persist (db);
			db.commit ();
		}
		catch (org.exolab.castor.jdo.PersistenceException e)
		{
			System.err.println (e.getMessage ());
			e.printStackTrace (System.err);
			return false;
		}
		return true;
	}


	public Invitation getInvitation() 
		throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
			//getBubble().setInvitation(toUser, invitationDate);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}

		return null;

	}

	public User getUser() 
		throws jdoPersistenceEx {

		return null;

	}

	public Date getDate()
		throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
			//getBubble().setInvitation(toUser, invitationDate);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}

		return null;

	}

	public void accept()
		throws jdoPersistenceEx {

		this.status = 2;

	}

	public void reject()
		throws jdoPersistenceEx {

		this.status = 3;

	}

	public short status()
		throws jdoPersistenceEx {

		
		try {
			Database db = DbManager.getConnection();
			db.begin();
			//getBubble().setInvitation(toUser, invitationDate);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}

	}
}
