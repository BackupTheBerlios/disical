// $Id: DisicalInvitation.java,v 1.17 2002/02/13 21:28:34 deafman Exp $
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
 * @version $Revision: 1.17 $
 */
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.DbManager;
import de.cwrose.disical.db.DbDate;

import de.cwrose.disical.db.DbInvitation;
import de.cwrose.disical.db.DbInvited;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;

import java.sql.Timestamp;

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
			throw new IllegalStateException ("DisicalInvitation: "
											 +"Don't burst my bubble, fool!");
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

	public void invite (User u)
		throws jdoPersistenceEx
	{
		try	{
			System.out.println ("D-U:"+(Object)u+" "+u.getLogin());
			DbInvitation inv = getBubble();
			DbInvited.createInvited (inv.getInvitation(),u);
		}
		catch (PersistenceException e) {
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	public Invited[] getAllInvited()
		throws jdoPersistenceEx {

		Invited[] invitedList = null;

		try {
			invitedList = getBubble().getAllInvited();
			
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
		catch (IllegalStateException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
			
		return invitedList;
	}
	
	public Invited[] getAllNotifiedInv()
		throws jdoPersistenceEx {
		
		
		try {
			return getBubble().getAllNotifiedInv ();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
			
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

	public Invitation _this(org.omg.CORBA.ORB orb) {
		Invitation obj = super._this(orb);
		try {
			bubble.blow(obj);
		}
		catch(PersistenceException e) {
			System.out.println("Ups, cannot blow my bubble!");
		}

		return obj;
	}
}
