// $Id: DisicalInvited.java,v 1.12 2002/02/13 23:39:23 stepn Exp $
package de.cwrose.disical.corba;

/**
 * CORBA-Object to set the Invitation-Status
 *
 * 
 *
 * @author deafman
 * @version $Revision: 1.12 $
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

	public void setInvitationIndex(int invId) {
		this.invId = invId;
	}

	public int getInvitationIndex() {
		return this.invId;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

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

	public User getUser() 
	throws jdoPersistenceEx {
		try {
			return DbUser.lookupUser (this.getLogin ());
		}
		catch (PersistenceException pex) {
			throw new jdoPersistenceEx(pex.getMessage());
		}
	}

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

	public void reject() 
	throws jdoPersistenceEx {
		if (status () != 3) 
			{
				setStatus ((short)3);
				if (!this.persist ())
					throw new jdoPersistenceEx ("Reject failed!");
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

	public void destroy() {

		POA poa = _default_POA();
		try {
			byte[] id = poa.servant_to_id(this);
			poa.deactivate_object(id);
		}
		catch (org.omg.CORBA.UserException ex) {}
	}

}
