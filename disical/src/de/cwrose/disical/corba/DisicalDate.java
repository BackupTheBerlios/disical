// $Id: DisicalDate.java,v 1.14 2002/01/29 22:57:12 deafman Exp $
package de.cwrose.disical.corba;

/**
 * CORBA Implementation for the Date-Object of the Calendar
 *
 * (set|get)StartTime (long)
 * (set|get)EndTime (long)
 * (set|get)Location (String)
 * (set|get)Subject (String)
 * (set|get)Login (String)
 * (set|get)Index (int)
 * boolean persist();
 * void DeleteDate ();
 * void changeDate(long, long, String, String);
 * void destroy();
 *
 * @author deafman
 * @version $Revision: 1.14 $
 */

import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import de.cwrose.disical.db.DbDate;
import org.exolab.castor.jdo.Database;
import de.cwrose.disical.db.DbManager;
import org.exolab.castor.jdo.PersistenceException;

public class DisicalDate extends DatePOA {

	public final static String Id = "Date";
	public final static String Kind = "";

  	private long startTime;
	private long endTime;
	private String location = null;
	private String subject = null;
	private User login = null;
	private int _index;

	/* Client doesnt see the bubble.. ooh */

	private DbDate bubble = null;

	public void setBubble (DbDate bubble) {
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
		//System.out.println ("PERSIST: "+getLogin().getLogin ()+" "+((Object)this)+" via "+((Object)(bubble.getDate ()))+"/"+((Object)bubble));
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

	public void setLogin(User login) {
		this.login = login;
	}

	public User getLogin() {
		return login;
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

	public void setIndex(int _index) {
		this._index = _index;
	}

	public int getIndex() {
		return	_index;
	}

	public void deleteDate()
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

	public void changeDate(long start, long end, 
						   String location, String subject) 
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

	public void destroy() {

		POA poa = _default_POA();
		try {
			byte[] id = poa.servant_to_id(this);
			poa.deactivate_object(id);
		}
		catch (org.omg.CORBA.UserException ex) {}
	}	
}
