package de.cwrose.disical.corba;

import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.corba.disiorb.UserPackage.*;
import org.omg.CORBA.*;

class DisicalUser extends UserPOA {

	public final static String Id = "User";
	public final static String Kind = "";

	private static String Login = null;
	private static String Name = null;
	private static String Email = null;
	private static String Passwd = null;

	public String Login() { return Login; }
	public String Name() { return Name; }
	public String Email() { return Email; }
	public String Passwd() { return Passwd; }

	public void changePW(String newPW) {
		//dbSetPW(newPW);
		Passwd = newPW;
	}

	public sUser getUserInfo() {

		sUser user = new sUser();

		//Login = dbGetLogin();
		//Name = dbGetName();
		//Passwd = dbGetPWD();
		//Email = dbGetEmail();

		user.login = Login;
		user.name = Name;
		user.pwd = Passwd;
		user.email = Email;

		return user;
	}

	public void setUserInfo(sUser user) {

		Name = user.name;
		Passwd = user.pwd;
		Email = user.email;

		//dbSetLogin(login);
		//dbSetName(name);
		//dbSetPWD(pwd);
		//dbSetEmail(email);
	}

	public void deleteUser() {
		//dbDeleteUser(this);
		//delete(this);
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

	public Date selectDate(short index) {

		DisicalDate selDateImpl = new DisicalDate();
		Date selDate = selDateImpl._this(DisicalSrv.orb);
		
		//return dbGetDate(index);
		return selDate;

	}

	public Date findDate(String start, String end, String location,
             				String subject, short index) {

		DisicalDate findDateImpl = new DisicalDate();
		Date findDate = findDateImpl._this(DisicalSrv.orb);
		
		//return dbFindDate(start, end, location, subject, index);
		return findDate;

	}

}
