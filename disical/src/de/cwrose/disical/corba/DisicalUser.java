// $Id: DisicalUser.java,v 1.20 2002/01/31 03:49:26 deafman Exp $
package de.cwrose.disical.corba;

/**
 * CORBA Implemetation for the User-Object of the Calendar
 * 
 * (set|get)Login (String)
 * (set|get)Name (String);
 * (set|get)Email (String);
 * setPasswd (String oldPW, String newPW);
 * persist();
 * DeleteUser();
 * disiorb.Date createDate 
 * 		(long start, long end, String location, String subject);
 * disiorb.Date selectDate(int index) ??? (obsolete)
 * disiorb.Date[] selectDatesByTime(long start, long end);
 * disiorb.Date[] selectDatesByLocation(String location);
 * disiorb.Date[] selectDatesBySubject(String subject);
 * disiorb.Invitation[] getInvitations();
 * Invitation createInvitation(User user, long start, long end, 
 *         String subject, String location, String description);
 * void destroy();
 *
 * @author deafman
 * @version $Revision: 1.20 $
 */
import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import de.cwrose.disical.db.DbUser;
import de.cwrose.disical.db.DbDate;
import de.cwrose.disical.db.DbManager;
import de.cwrose.disical.db.DbInvitation;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;
import java.sql.Timestamp;


public class DisicalUser extends UserPOA {

	public final static String Id = "User";
	public final static String Kind = "";

	private String login = null;
	private String name = null;
	private String email = null;

	private static int dateLimit   = 1000;
	private static int inviteLimit =  100;

	/* Client doesnt see the bubble.. ooh */

	private DbUser bubble = null;

	public void setBubble (DbUser bubble) {
		if (this.bubble != null)
			throw new IllegalStateException ("Don't burst my bubble, fool!");
		this.bubble = bubble;
	}

	public DbUser getBubble () {
		return this.bubble;
	}

	private boolean isLoginUser ()
	{
		return bubble.isLoginUser ();
	}

	
	public void setLogin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPasswd(String oldPW, String newPW) 
		throws wrongPwEx {
		String bubblePW = bubble.getPassword();
		if (!bubblePW.equals(oldPW)) 
			throw new wrongPwEx("You've entered an INVALID Passwd!");
		bubble.setPassword (newPW);
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	protected void do_persist(Database db) 
		throws org.exolab.castor.jdo.PersistenceException
	{

		//		System.out.println ("PERSIST: "+getLogin ()+" "+((Object)this)+" via "+((Object)(bubble.getUser ()))+"/"+(Object)bubble);
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

	public void deleteUser() 
	throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
			User u = this.getBubble().getUser();
			// DbUser.deleteUser(u);
			db.commit();
		}
		catch (org.exolab.castor.jdo.PersistenceException e)
		{
			System.err.println (e.getMessage ());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	public Date createDate (long start, long end, String location, 
							String subject, String description)
		throws jdoPersistenceEx {

		try {
			return DbDate.createDate(getBubble().getUser(), new Timestamp(start), new Timestamp (end), subject, location, description);
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	public Date selectDate(int index) 
		throws jdoPersistenceEx {

		Date selDate = null;

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
	
		return selDate;
	}

	public Date[] listDatesByTime(long start, long end)
		throws jdoPersistenceEx {

		Date[] dateList = null;

		try {
			Database db = DbManager.getConnection();
			db.begin();
			//dateList = getBubble().selectDateByTime(new Timestamp(start), new Timestamp(end));
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());			
		}
		return dateList;
	}

	public User[] listAllUsers()
		throws jdoPersistenceEx
	{
		try {
			return DbUser.listAllUsers ();
		}
		catch (PersistenceException e) {
			throw new jdoPersistenceEx(e.getMessage());
		}
			
	}

	public Date[] listDatesByLocation(String location)
		throws jdoPersistenceEx {

		Date[] dateList = null;
		
		try {
			Database db = DbManager.getConnection();
			db.begin();
			//dateList = getBubble().selectDateByLocation(location);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());			
		}
		return dateList;
		
	}

	public Date[] listDatesBySubject(String subject)
		throws jdoPersistenceEx {
			
			Date[] dateList = null;

		try {
			Database db = DbManager.getConnection();
			db.begin();
			//dateList = getBubble().selectDateBySubject(subject);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());			
		}
		return dateList;
	}

	public Invitation[] getInvitations()
		throws jdoPersistenceEx {
			
		Invitation[] invitationList = null;
		
		try {
			Database db = DbManager.getConnection();
			db.begin();
			//invitationList = getBubble().selectInvitations();
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());			
		}
		return invitationList;
	}

	public Invitation createInvitation(long start, long end, 
								String subject, String location, 
								String description) 
		throws jdoPersistenceEx {

		Invitation invitation = null;

		try {
			invitation = 
				DbInvitation.createInvitation(getBubble().getUser(),
											  new java.sql.Timestamp(start),
											  new java.sql.Timestamp(end),
											  subject, location, description);
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());			
		}
		return invitation;
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
