// $Id: DisicalInvited.java,v 1.7 2002/01/31 04:46:23 stepn Exp $
package de.cwrose.disical.corba;

/**
 * CORBA-Object to set the Invitation-Status
 *
 * 
 *
 * @author deafman
 * @version $Revision: 1.7 $
 */
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.DbDate;
import de.cwrose.disical.db.DbManager;
import de.cwrose.disical.db.DbInvited;
import de.cwrose.disical.db.DbUser;
import de.cwrose.disical.db.DbInvitation;

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
	
	public void setBubble( DbInvited bubble) {
		if (this.bubble != null)
			throw new IllegalStateException ("Don't burst my bubble, fool!");
		this.bubble = bubble;
	}

	public DbInvited getBubble () {
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
		throws jdoPersistenceEx 
	{
		try	{
		    DbInvitedbubble = getBubble();				
			if (bubble.getState () != 2) {
				bubble.setState (2);
				Invitation i = bubble.getInvitation().getInvitation();
				bubble.setDate 
					(DbDate.createDate 
					 (this.getUser(), 
					  i.getStartTime (), i.geEndTime (), 
					  i.getSubject (), i.getLocation (), i.getDescription ()));
				this.persist ();
			}
		}
		catch (PersistenceException e) {
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	public void reject()
		throws jdoPersistenceEx {
		try	{
			DbInvited bubble = getBubble().setDate();
			if (bubble.getState () != 2) {
				bubble.setState (2);
				bubble.getDate ().delete ();
				bubble.setDate (null);
				this.persist ();
			}
		}
		catch (PersistenceException e) {
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	public short status() {
		return this.status;
	}


	public void setStatus(short s)
	{
		this.status = s;
	}
   
	public void setNotify(boolean notify) {
		getBubble().setNotify(notify);
	}

	public boolean getNotify() {
		return getBubble().getNotify();
	}
}
