package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

public class DisicalUser extends UserPOA {

	public final static String Id = "User";
	public final static String Kind = "";

	private String login = null;
	private String name = null;
	private String email = null;
	private String passwd = null;

	private static final int dateLimit = 1000;
	private static final int inviteLimit = 100;

	public void setLogin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPasswd(String newPW) {
		passwd = newPW;
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

	public String getPasswd() {
		return passwd;
	}

	public boolean persist() {
		DisicalUser newUserImpl = new DisicalUser();

		newUserImpl.setLogin(login);
		newUserImpl.setName(name);
		newUserImpl.setEmail(email);
		newUserImpl.setPasswd(passwd);

		//User newUser = newUserImpl._this(DisicalSrv.orb);
		boolean success = true; //dbPersistUser(newUserImpl);

		return success;
	}

	public void deleteUser() {
		DisicalUser newUserImpl = new DisicalUser();

		newUserImpl.setLogin(login);
		newUserImpl.setName(name);
		newUserImpl.setEmail(email);
		newUserImpl.setPasswd(passwd);

		// dbDeleteUser(newUserImpl);
	}

	public Date createDate(String start, String end, String location, String subject) {

		DisicalDate newDateImpl = new DisicalDate();
		
		newDateImpl.setStartTime(start);
		newDateImpl.setEndTime(end);
		newDateImpl.setLocation(location);
		newDateImpl.setSubject(subject);

		//dbCreateDate(newDateImpl);
		Date newDate = newDateImpl._this(DisicalSrv.orb);

		return newDate;

	}

	public Date selectDate(int index) {

		DisicalDate selDateImpl = new DisicalDate();
		selDateImpl.setIndex(index);
		selDateImpl = null; //dbGetDate(setDateImpl);

		Date selDate = selDateImpl._this(DisicalSrv.orb);

		return selDate;
	}

	public Date[] listDatesByTime(String start, String end) {

		DisicalDate selDateImpl = new DisicalDate();
		DisicalDate[] listDateImpl = new DisicalDate[dateLimit];

		selDateImpl.setStartTime(start);
		selDateImpl.setEndTime(end);
		listDateImpl = null; //dbGetDatesByTime(selDateImpl);

		Date[] dateList = new Date[dateLimit];

		for ( int i = 0; i < dateLimit; i++) {
			dateList[i] = listDateImpl[i]._this(DisicalSrv.orb);
		}

		return dateList;
	}

	public Date[] listDatesByLocation(String location) {

		DisicalDate selDateImpl = new DisicalDate();
		DisicalDate[] listDateImpl = new DisicalDate[dateLimit];

		selDateImpl.setLocation(location);
		listDateImpl = null; //dbGetDatesByLocation(selDateImpl);

		Date[] dateList = new Date[dateLimit];

		for ( int i = 0; i < dateLimit; i++) {
			dateList[i] = listDateImpl[i]._this(DisicalSrv.orb);
		}

		return dateList;
	}

	public Date[] listDatesBySubject(String subject) {

		DisicalDate selDateImpl = new DisicalDate();
		DisicalDate[] listDateImpl = new DisicalDate[dateLimit];

		selDateImpl.setSubject(subject);
		listDateImpl = null; //dbGetDatesBySubject(selDateImpl);

		Date[] dateList = new Date[dateLimit];

		for ( int i = 0; i < dateLimit; i++) {
			dateList[i] = listDateImpl[i]._this(DisicalSrv.orb);
		}

		return dateList;
	}

	public Invitation[] getInvitations() {

		DisicalDate selDateImpl = new DisicalDate();
		selDateImpl.setLogin(login);

		DisicalInvitation[] listInvitationImpl = 
				new DisicalInvitation[inviteLimit];

		listInvitationImpl = null; //dbGetInvitations(this.login);

		Invitation[] listInvitation = new Invitation[inviteLimit];

		for (int i = 0; i < inviteLimit; i++) {
			listInvitation[i] = listInvitationImpl[i]._this(DisicalSrv.orb);
		}

		return listInvitation;
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
