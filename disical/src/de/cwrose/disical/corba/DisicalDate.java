package de.cwrose.disical.corba;

import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

public class DisicalDate extends DatePOA {

	public final static String Id = "Date";
	public final static String Kind = "";

  	private String startTime = null;
	private String endTime = null;
	private String location = null;
	private String subject = null;
	private String login = null;
	private int _index;
  
	public void setStartTime(String Time) {
		startTime = Time;
	}

	public void setEndTime(String Time) {
		endTime = Time;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public String getEndTime() {
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

	public void changeDate(String start, String end, 
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
