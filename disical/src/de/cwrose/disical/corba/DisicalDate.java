package de.cwrose.disical.corba;

import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.POA;

class DisicalDate extends DatePOA {

	public final static String Id = "Date";
	public final static String Kind = "";

	private static String startTime;
	private static String endTime;
	private static String location;
	private static String subject;
	private static short _index;

	public void deleteDate(){

		//dbDeleteDate(_index);

		POA poa = _default_POA();
		try {
			byte[] id = poa.servant_to_id(this);
			poa.deactivate_object(id);
		}
		catch (org.omg.CORBA.UserException ex) {}	
		
	}

	public void setStartTime(String Time) {
		//dbSetStartTime(Time, _index);
		startTime = Time;
	}

	public void setEndTime(String Time) {
		//dbSetEndTime(Time, _index);
		endTime = Time;
	}

	public void setLocation(String location) {
		//dbSetLocation(location, _index);
		this.location = location;
	}

	public void setSubject(String subject) {
		//dbSetLocation(subject, _index);
		this.subject = subject;
	}

	public String getStartTime() {
		return startTime;
		//return dbGetStartTime(_index);
	}

	public String getEndTime() {
		return endTime;
		//return dbGetEndTime(_index);
	}

	public String getLocation() {
		return location;
		//return dbGetLocation(_index);
	}

	public String getSubject() {
		return subject;
		//return dbGetSubject(_index);
	}

	public short getIndex() {
		return	_index;
	}

	public void changeDate(String start, String end, String location, String subject) {
		startTime = start;
		endTime = end;
		this.location = location;
		this.subject = subject;
	}

}
