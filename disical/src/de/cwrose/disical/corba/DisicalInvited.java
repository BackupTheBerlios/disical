// $Id: DisicalInvited.java,v 1.2 2002/01/30 21:18:10 deafman Exp $
package de.cwrose.disical.corba;

/**
 * CORBA-Object to set the Invitation-Status
 *
 * 
 *
 * @author deafman
 * @version $Revision: 1.2 $
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
	private Invitation invitation = null;
	private User user = null;
	private Date date = null;
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

	public void setUser(User user) {
		this.user = user;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}

	public Invitation getInvitation() {
		return this.invitation;
	}

	public User getUser() {
		return this.user;
	}

	public Date getDate() {
		return this.date;
	}

	public void accept()
		throws jdoPersistenceEx {

		this.status = 2;
		this.persist();
	}

	public void reject()
		throws jdoPersistenceEx {

		this.status = 3;
		this.persist();
	}

	public short status() {
		return this.status;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	public boolean getNotify() {
		return this.notify;
	}
}
