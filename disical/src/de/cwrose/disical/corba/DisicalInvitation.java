// $Id: DisicalInvitation.java,v 1.6 2002/01/29 22:57:12 deafman Exp $
package de.cwrose.disical.corba;

/**
 * CORBA Implementation for the Invitation-Object of the Calendar
 * 
 * (set|get)FromUser (disiorb.User)
 * (set|get)ToUser (disiorb.User[])
 * (set|get)InvitationDate (disical.Date)
 * boolean persist();
 * void setInvitation(disical.User, disical.User[], disical.Date, short);
 * void deleteInvitation();
 * void destroy();
 *
 * @author deafman
 * @version $Revision: 1.6 $
 */
import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
//import de.cwrose.disical.db.DbInvitation;
import org.exolab.castor.jdo.Database;
import de.cwrose.disical.db.DbManager;
import org.exolab.castor.jdo.PersistenceException;

public class DisicalInvitation extends InvitationPOA {

	public final static String Id = "Invitation";
	public final static String Kind = "";

	private int _index;
	private User fromuser;
	private User[] touser;
	private Date date;
	private short status;

	private DbInvitation bubble = null;

	public void setBubble( DbInvitation bubble) {
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
		//		System.out.println ("PERSIST: "+getLogin().getLogin ()+" "+((Object)this)+" via "+((Object)(bubble.getDate ()))+"/"+((Object)bubble));
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

	public int getIndex() {
		return _index;
	}

	public User getFromUser() {
		return fromuser;
	}

	public User[] getToUser() {
		return touser;
	}

	public Date getInvitationDate() {
		return date;
	}

	public short getStatus() {
		return status;
	}

	public void setIndex(int index) {
		_index = index;
	}

	public void setFromUser(User fromUser) {
		fromuser = fromUser;
	}

	public void setToUser(User[] toUser) {
		touser = toUser;
	}

	public void setInvitationDate(Date date) {
		this.date = date;
	}

	public void setStatus(short newStatus) {
		status = newStatus;
	}

	public void setInvitation(User[] toUser, Date invitationDate)
		throws jdoPersistenceEx {
		
		try {
			Database db = DbManager.getConnection();
			db.begin();
			//selDate = getBubble().selectDate(index);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	public void deleteInvitation()
{ 		throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
			//selDate = getBubble().selectDate(index);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}

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
