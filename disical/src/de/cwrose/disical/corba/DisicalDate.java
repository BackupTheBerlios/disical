// $Id: DisicalDate.java,v 1.23 2002/03/06 13:51:21 deafman Exp $
package de.cwrose.disical.corba;

/**
 * CORBA Implementation for the Date-Object of the Calendar
 *
 * (set|get)StartTime (long)
 * (set|get)EndTime (long)
 * (set|get)Location (String)
 * (set|get)Subject (String)
 * (set|get)Description (String)
 * (set|get)Login (String)
 * (set|get)Index (int)
 * boolean persist();
 * void DeleteDate ();
 * void changeDate(long, long, String, String);
 * void destroy();
 *
 * @author deafman
 * @version $Revision: 1.23 $
 */

import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.DbDate;
import de.cwrose.disical.db.DbUser;
import de.cwrose.disical.db.DbManager;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;

import java.sql.Timestamp;

public class DisicalDate extends DatePOA {

	public final static String Id = "Date";
	public final static String Kind = "";

  	private long startTime;
	private long endTime;
	private String location = null;
	private String subject = null;
	private String description = null;
	private String login = null;
	private int _index;

	/* Client doesnt see the bubble.. ooh */

	private DbDate bubble = null;

	/* the reference to the Db-Object */
	public void setBubble (DbDate bubble) {
		if (this.bubble != null)
			throw new IllegalStateException ("DisicalDate: "
											 +"Don't burst my bubble, fool!");
		this.bubble = bubble;
	}

	public DbDate getBubble () {
		return this.bubble;
	}

	public void do_persist(Database db) 
		throws org.exolab.castor.jdo.PersistenceException
	{
		//System.out.println ("PERSIST: "+getLogin().getLogin ()+" "+((Object)this)+" via "+((Object)(bubble.getDate ()))+"/"+((Object)bubble));
		bubble.persist (db);
	}

	/* write this object to the DB */
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

	/* delete the obj in the obj */
	public void deleteDate()
		throws jdoPersistenceEx {

		try {
			Database db = DbManager.getConnection();
			db.begin();
			getBubble().delete (db);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}

	}

	/* some dates need a change - directly on the DB */
	public void changeDate(long start, long end, 
						   String location, String subject) 
		throws jdoPersistenceEx {
		
		try {
			Database db = DbManager.getConnection();
			db.begin();
			//getBubble().updateDate(new Timestamp(start), new Timestamp(stop), subject, location);
			db.commit();
		}
		catch (PersistenceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new jdoPersistenceEx(e.getMessage());
		}
	}

	/* get/set-routines for Castor - and of course for the client*/
	public void setStartTime(long Time) {
		startTime = Time;
	}

	public void setEndTime(long Time) {
		endTime = Time;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public User getUser()
		throws jdoPersistenceEx
	{
		try {
			return DbUser.lookupUser(this.getLogin());
		}
		catch (PersistenceException pex)
			{
				throw new jdoPersistenceEx(pex.getMessage ());
			}
	}

	public void setUser(User usr)
	{
		setLogin (usr.getLogin ());
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public String getLocation() {
		return location;
	}

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}

	public void setIndex(int _index) {
		this._index = _index;
	}

	public int getIndex() {
		return	_index;
	}

	/* destroy the obj in the poa */                // check if ok !!!
	public void destroy() {

		POA poa = _default_POA();
		try {
			byte[] id = poa.servant_to_id(this);
			poa.deactivate_object(id);
		}
		catch (org.omg.CORBA.UserException ex) {}
	}

}
