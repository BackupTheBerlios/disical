package de.cwrose.disical.corba;

import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.corba.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.POA;

class DisicalUser extends UserPOA {

	public final static String Id = "User";
	public final static String Kind = "";

	private String login = null;
	private String name = null;
	private String email = null;
	private String passwd = null;

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

		User newUser = newUserImpl._this(DisicalSrv.orb);
		boolean success = true; //dbPersistUser(newUser);

		return success;
	}

	public void changePW(String newPW) {
		passwd = newPW;
	}

	public User getUserInfo() {

		DisicalUser userImpl = new DisicalUser();
		userImpl.setLogin(login);
		userImpl.setName(name);
		userImpl.setEmail(email);
		userImpl.setPasswd(passwd);

		User user = userImpl._this(DisicalSrv.orb);

		return user;
	}

	public void setUserInfo(User user) {
		//dbSetUser(user);
		System.out.println("comes next");
	}

	public void deleteUser() {
		// dbDeleteUser();
		System.out.println("comes next");
	}

	public Date createDate(String start, String end, String location, String subject) {

		DisicalDate newDateImpl = new DisicalDate();
		Date newDate = newDateImpl._this(DisicalSrv.orb);
		
		newDate.setStartTime(start);
		newDate.setEndTime(end);
		newDate.setLocation(location);
		newDate.setSubject(subject);

		//dbCreateDate(newDate);

		return newDate;

	}

	public Date selectDate(int index) {

		DisicalDate selDateImpl = new DisicalDate();
		Date selDate = selDateImpl._this(DisicalSrv.orb);
		selDate = null; //dbGetDate(index);

		return selDate;

	}

	public Date[] listDatesByTime(String start, String end) {

		Date[] dateList = new Date[1000];
		dateList = null; //dbGetDatesByTime(String start, String end);
		return dateList;

	}

	public Date[] listDatesByLocation(String location) {

		Date[] dateList = new Date[1000];
		dateList = null; //dbGetDatesByLocation(String location);
		return dateList;

	}

	public Date[] listDatesBySubject(String subject) {

		Date[] dateList = new Date[1000];
		dateList = null; //dbGetDatesBySubject(String subject);
		return dateList;

	}

	public Invitation[] getInvitations() {

		Invitation[] invitationList = new Invitation[100];
		invitationList = null; //dbGetInvitations(this.login);
		return invitationList;

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
