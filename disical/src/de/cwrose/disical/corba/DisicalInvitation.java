// $Id: DisicalInvitation.java,v 1.11 2002/01/30 22:39:50 stepn Exp $
package de.cwrose.disical.corba;

/**
 * CORBA Implementation for the Invitation-Object of the Calendar
 * 
 * (set|get)FromUser (disiorb.User)
 * (set|get)
 * boolean persist();
 * void delete();
 * void destroy();
 *
 * @author deafman
 * @version $Revision: 1.11 $
 */
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.DbManager;
import de.cwrose.disical.db.DbDate;

import de.cwrose.disical.db.DbInvitation;
import de.cwrose.disical.db.DbInvited;
import de.cwrose.disical.db.DbInvitation;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;

public class DisicalInvitation extends InvitationPOA {

	public final static String Id = "Invitation";
	public final static String Kind = "";

	private User fromuser;
	private long startTime;
	private long endTime;
	private String location;
	private String subject;
	private String description;

	private DbInvitation bubble = null;

	public void setBubble( DbInvitation bubble) {
		if (this.bubble != null)
			throw new IllegalStateException ("Don't burst my bubble, fool!");
		this.bubble = bubble;
	}

	public DbInvitation getBubble () {
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

	public Invited[] getAllInvited()
		throws jdoPersistenceEx {

		Invited[] invitedList = null;

		try {
			Database db = DbManager.getConnection();
			db.begin();
			//
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
			
		return invitedList;
	}

	public Invited[] getAllNotifiedInv()
		throws jdoPersistenceEx {

		Invited[] invitedList = null;

		try {
			Database db = DbManager.getConnection();
			db.begin();
			//
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
			
		return invitedList;
	}

	public void delete()
 		throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
			//getBubble().delete(db);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	public User getFromUser() {
		return fromuser;
	}

	public void setFromUser(User fromUser) {
		fromuser = fromUser;
	}

	public void setStartTime(long Time) {
		this.startTime = Time;
	}

	public long getStartTime() {
		return this.startTime;
	}

	public void setEndTime(long Time) {
		this.endTime = Time;
	}

	public long getEndTime() {
		return this.endTime;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void destroy() {
		POA poa = _default_POA();
		try {
			byte[] id = poa.servant_to_id(this); 
			poa.deactivate_object(id);
		}
		catch (org.omg.CORBA.UserException ex) {}
	}
}
