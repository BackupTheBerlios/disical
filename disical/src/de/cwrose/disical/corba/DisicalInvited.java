// $Id: DisicalInvited.java,v 1.15 2002/03/22 18:13:45 stepn Exp $
package de.cwrose.disical.corba;

/**
 * CORBA-Object to set the Invitation-Status
 *
 * (set|get)IntitationIndex (int)
 * (set|get)Login (String)
 * Invitation getInvitation()
 * User getUser()
 * void accept()
 * void reject()
 * short status()
 * void setStatus (short)
 * (set|get)Notify (boolean)
 * void destroy()
 * void do_persist()
 *
 * @author deafman
 * @version $Revision: 1.15 $
 */
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.DbDate;
import de.cwrose.disical.db.DbManager;
import de.cwrose.disical.db.DbInvited;
import de.cwrose.disical.db.DbUser;
import de.cwrose.disical.db.DbInvitation;
import de.cwrose.disical.util.HackHelper;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;

import java.sql.Timestamp;

public class DisicalInvited extends InvitedPOA {

	private DbInvited bubble = null;
	private int invId = -1;
	private String login = null;
	private short status;
	
	public void setBubble( DbInvited bubble) {
		if (this.bubble != null)
			throw new IllegalStateException ("DisicalInvited: "
											 +"Don't burst my bubble, fool!");
		this.bubble = bubble;
	}

	public DbInvited getBubble () {
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

	/* set/get the Invitation-Index in the db
	 */
	public void setInvitationIndex(int invId) {
		this.invId = invId;
	}

	public int getInvitationIndex() {
		return this.invId;
	}

	/* sets/gets the loginname of the object
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	/* looks up an invitation, that matches to this.object
	 */
	public Invitation getInvitation()
		throws jdoPersistenceEx
	{
		try {
			return DbInvitation.lookupInvitation (getInvitationIndex ());
		}
		catch (PersistenceException pex) {
			throw new jdoPersistenceEx(pex.getMessage());
		}
	}

	/* get the user (invitor) to this.object
	 */
	public User getUser() 
	throws jdoPersistenceEx {
		try {
			return DbUser.lookupUser (this.getLogin ());
		}
		catch (PersistenceException pex) {
			throw new jdoPersistenceEx(pex.getMessage());
		}
	}

	/* accepts a invitation (setStatus...)
	 */
	public void accept()
		throws jdoPersistenceEx 
	{
		try	{
		    DbInvited bubble = getBubble();				
			if (bubble.getState () != 2) {
				bubble.setState ((short)2);
				Invitation i = getInvitation ();
				DbDate.createDate 
					(bubble.getLogin (),
					 new Timestamp(i.getStartTime ()),
					 new Timestamp(i.getEndTime ()), 
					 i.getSubject (), i.getLocation (), i.getDescription ());
				this.persist ();
				// FIXME: DESTROY i
			}
		}
		catch (PersistenceException e) {
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	/* rejects an invitation (setStatus...)
	 */
	public void reject() 
	throws jdoPersistenceEx {
		if (status () != 3) 
			{
				setStatus ((short)3);
				if (!this.persist ())
					throw new jdoPersistenceEx ("Reject failed!");
			}
	}

	/* returns the current status
	 */
	public short status() {
		return this.status;
	}

	/* sets the status (needed by accept and reject)
	 */
	public void setStatus(short s)
	{
		this.status = s;
	}
   
	/* sets/gets the notify-flag
	 */
	public void setNotify(boolean notify) {
		getBubble().setNotify(notify ? 1 : 0);
	}

	public boolean getNotify() {
		return (getBubble().getNotify() == 1);
	}

	/* delete this.object from the db
	 */
	public void delete()
 		throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
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

	/* destroys this.pbject in the POA
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
