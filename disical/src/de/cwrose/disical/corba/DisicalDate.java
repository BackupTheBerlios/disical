// $Id: DisicalDate.java,v 1.11 2002/01/28 11:53:55 deafman Exp $
package de.cwrose.disical.corba;

/**
 * CORBA Implementation for the Date-Object of the Calendar
 * (set|get)StartTime (long)
 * (set|get)EndTime (long)
 * (set|get)Location (String)
 * (set|get)Subject (String)
 * (set|get)Login (String)
 * (set|get)Index (int)
 * persist (boolean)
 * DeleteDate (this)
 * changeDate (long, long, String, String)
 * destroy (void)
 *
 * @author deafman
 * @version $Revision: 1.11 $
 */

import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import de.cwrose.disical.db.DbDate;

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
		this.bubble = bubble;
	}

	public DbDate getBubble () {
		return this.bubble;
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

	public boolean persist() {
		DisicalDate dateImpl = new DisicalDate();

		dateImpl.setStartTime(startTime);
		dateImpl.setEndTime(endTime);
		dateImpl.setLocation(location);
		dateImpl.setSubject(subject);

		Date date = dateImpl._this(DisicalSrv.orb);
		boolean success = true; //dbPersistDate(date);

		return success;
	}
	
	public void deleteDate() {
		DisicalDate dateImpl = new DisicalDate();

		dateImpl.setStartTime(startTime);
		dateImpl.setEndTime(endTime);
		dateImpl.setLocation(location);
		dateImpl.setSubject(subject);

		Date date = dateImpl._this(DisicalSrv.orb);
		//dbDeleteDate(date);

	}

	public void changeDate(long start, long end, 
					String location, String subject) {
		startTime = start;
		endTime = end;
		this.location = location;
		this.subject = subject;
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
