// $Id: DisicalUser.java,v 1.32 2002/03/22 16:30:49 stepn Exp $
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
 * disiorb.User[] listAllUsers();
 * disiorb.Date[] selectDatesByTime(long start, long end);
 * disiorb.Date[] selectDatesByLocation(String location);
 * disiorb.Date[] selectDatesBySubject(String subject);
 * disiorb.Invitation[] getInvitations();
 * Invitation createInvitation(User user, long start, long end, 
 *         String subject, String location, String description);
 * void destroy();
 *
 * @author deafman
 * @version $Revision: 1.32 $
 */
import de.cwrose.disical.corba.disiorb.UserPOA;
import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.disiorb.Date;
import de.cwrose.disical.corba.disiorb.Invitation;

import de.cwrose.disical.corba.disiorb.wrongPwEx;
import de.cwrose.disical.corba.disiorb.jdoPersistenceEx;
import de.cwrose.disical.corba.disiorb.emptySeqEx;

import de.cwrose.disical.util.HackHelper;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import de.cwrose.disical.db.DbUser;
import de.cwrose.disical.db.DbDate;
import de.cwrose.disical.db.DbManager;
import de.cwrose.disical.db.DbInvitation;
import de.cwrose.disical.db.EmptySeqException;

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

	private DbUser bubble = null;

	/* sets reference to the DB-User-Interface
	 */
	public void setBubble (DbUser bubble) {
		if (this.bubble != null)
			throw new IllegalStateException ("DisicalUser: "
											 +"Don't burst my bubble, fool!");
		this.bubble = bubble;
	}

	/* returns the User-Object that came from the DB
	 */
	public DbUser getBubble () {
		return this.bubble;
	}

	/* sets the Login-User-Flag
	 */
	private boolean isLoginUser ()
	{
		return bubble.isLoginUser ();
	}

	/* sets the loginname
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/* sets the canonial name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* sets email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/* sets passwd directly on the db
	 */
	public void setPasswd(String oldPW, String newPW) 
		throws wrongPwEx {
		String bubblePW = bubble.getPassword();
		if (!bubblePW.equals(oldPW)) 
			throw new wrongPwEx("You've entered an INVALID Passwd!");
		bubble.setPassword (newPW);
	}

	/* get login, name and email
	 */
	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	/* wrapper to the persist-routine
	 */
	protected void do_persist(Database db) 
		throws org.exolab.castor.jdo.PersistenceException
	{
		bubble.persist (db);
	}

	/* wtites the maipulated user-obj. to the db
	 */
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

	/* deletes this.object on the db
	 */
	public void deleteUser() 
	throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
			this.getBubble().delete (db);
			db.commit();
		}
		catch (org.exolab.castor.jdo.PersistenceException e)
		{
			System.err.println (e.getMessage ());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	/* writes a date with the given attributes to the db
	 */
	public Date createDate (long start, long end, String location, 
							String subject, String description)
		throws jdoPersistenceEx {

		try {
			return DbDate.createDate
				(getLogin (), 
				 new Timestamp(start), new Timestamp (end), 
				 subject, location, description);
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	/* selects a date with a given index (obsolete)
	 */
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

	/* returns lists of all users in the db
	 */
	public User[] listAllUsers()
		throws jdoPersistenceEx, emptySeqEx
	{
		try {
			return getBubble().listAllUsers ();
		}
		catch (PersistenceException e) {
			throw new jdoPersistenceEx(e.getMessage());
		}
		catch (EmptySeqException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new emptySeqEx(e.getMessage());
		}
			
	}

	/* returns all Dates of the login-user, that matches 
	 * in the given period of time
	*/
	public Date[] listDatesByTime(long start, long end)
		throws jdoPersistenceEx, emptySeqEx {

		Date[] dateList = null;

		try {
			dateList = getBubble().listDatesByTime(new Timestamp(start), new Timestamp(end));
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

		return dateList;
	}

	/* returns all Dates of the login-user, that matches 
	 * in the given period of time
	*/
	public Date[] listAllDatesByTime()
		throws jdoPersistenceEx, emptySeqEx {

		Date[] dateList = null;

		try {
			dateList = getBubble().listAllDatesByTime();
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

		return dateList;
	}



	/* returns all dates of the login-user
	 * that matches to the given location
	 */
	public Date[] listDatesByLocation(String location)
		throws jdoPersistenceEx , emptySeqEx {

		Date[] dateList = null;
		
		try {
			dateList = getBubble().listDatesByLocation(location);
			System.out.println(dateList);
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

		return dateList;
		
	}

	/* returns all dates of the login-user
	 * that matches to the given subject
	 */
	public Date[] listDatesBySubject(String subject)
		throws jdoPersistenceEx , emptySeqEx {
			
			Date[] dateList = null;

		try {
			dateList = getBubble().listDatesBySubject(subject);
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

		return dateList;
	}

	/* returns all invitations of the login-user
	 */
	public Invitation[] getInvitations()
		throws jdoPersistenceEx , emptySeqEx {
			
		Invitation[] invitationList = null;
		
		try {
			invitationList = getBubble().listAllInvitations();
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

		return invitationList;
	}

	/* creates an Invitation without setting the invited users
	 * (will be done with the invitation-object by using invite() )
	 */
	public Invitation createInvitation(long start, long end, 
								String subject, String location, 
								String description) 
		throws jdoPersistenceEx {

		Invitation invitation = null;

		try {
			invitation = 
				DbInvitation.createInvitation(this.getLogin (),
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
