// $Id: DisicalInvitation.java,v 1.24 2002/03/22 18:13:45 stepn Exp $
package de.cwrose.disical.corba;

/**
 * CORBA Implementation for the Invitation-Object of the Calendar
 * 
 * void invite (User)
 * Invited[] getAllInvited()
 * Invited[] getAllNotifiedInv()
 * (set|get)FromUser (disiorb.User)
 * (set|get)StartTime (long)
 * (set|get)EndTime (long)
 * (set|get)Location (String)
 * (set|get)Subject (String)
 * (set|get)Description (String)
 * (set|get)Login (String)
 * boolean do_persist()
 * void delete();
 * void destroy();
 *
 * @author deafman
 * @version $Revision: 1.24 $
 */
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.DbManager;
import de.cwrose.disical.db.DbDate;
import de.cwrose.disical.db.DbInvitation;
import de.cwrose.disical.db.DbInvited;
import de.cwrose.disical.db.EmptySeqException;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;

import java.sql.Timestamp;

public class DisicalInvitation extends InvitationPOA {

	public final static String Id = "Invitation";
	public final static String Kind = "";

	private long startTime;
	private long endTime;
	private String location;
	private String subject;
	private String description;
	private String login;

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

	/* writes this.object to the db (wrapper)
	 */
	public void do_persist(Database db) 
		throws org.exolab.castor.jdo.PersistenceException
	{
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

	/* invite a collegue 
	 */
	public void invite (User u)
		throws jdoPersistenceEx
	{
		try	{
			System.out.println ("D-U:"+(Object)u+" "+u.getLogin());
			DbInvitation inv = getBubble();
			DbInvited.createInvited (inv.getIndex (), u.getLogin ());
		}
		catch (PersistenceException e) {
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	/* get the invited buddies 
	 */
	public Invited[] getAllInvited()
		throws jdoPersistenceEx, emptySeqEx {

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
		catch (EmptySeqException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new emptySeqEx(e.getMessage());
		}
			
		return invitedList;
	}
	
	/*  get all invited buddies where the notify-flag is set
	 */
	public Invited[] getAllNotifiedInv()
		throws jdoPersistenceEx, emptySeqEx {
		
		
		try {
			return getBubble().getAllNotifiedInv ();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
		catch (EmptySeqException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new emptySeqEx(e.getMessage());
		}
			
	}

	/* delete this.object from the db
	 */
	public void delete()
 		throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
			
			try
				{
					Invited[] inv =  getBubble().getAllInvited();
					for (int i = 0; i < inv.length; i++)
						try
							{
								inv[i].delete ();
							}
						catch (Exception e)
							{
								System.err.println 
									("Error deleting invited person.");
							}
				}
			catch (EmptySeqException e)
				{
					// Just go on
				}
			getBubble().delete(db);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}

		this.destroy ();
	}

	/* set/get the inviting user
	 */
	public User getFromUser() 
	throws jdoPersistenceEx {
		try	{
			return de.cwrose.disical.db.DbUser.lookupUser (this.getLogin ());
		}
		catch (PersistenceException pex) {
			throw new jdoPersistenceEx(pex.getMessage());
		}
	}

	public void setFromUser(User fromUser) {
		this.setLogin (fromUser.getLogin ());
	}

	/* set/get the starttime
	 */
	public void setStartTime(long Time) {
		this.startTime = Time;
	}

	public long getStartTime() {
		return this.startTime;
	}

	/* set/get the endtime
	 */
	public void setEndTime(long Time) {
		this.endTime = Time;
	}

	public long getEndTime() {
		return this.endTime;
	}

	/* set/get the location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}

	/* set/get the subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return this.subject;
	}

	/* set/get a more detailes description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	/* set/get the login-user
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	/* destroys this.object in the poa
	 */
	public void destroy() {
		POA poa = _default_POA();
		try {
			byte[] id = poa.servant_to_id(this); 
			poa.deactivate_object(id);
		}
		catch (org.omg.CORBA.UserException ex) {}
	}
}
