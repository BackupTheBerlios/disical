import org.omg.CORBA.*;

class DisicalUser extends disiorb.UserPOA {

	public final static String Id = "User";
	public final static String Kind = "";

	private static String Login;
	private static String Name;
	private static String Email;
	private static String Passwd;

	public String Login() { return Login; }
	public String Name() { return Name; }
	public String Email() { return Email; }
	public String Passwd() { return Passwd; }

	public void changePW(String newPW) {
		//dbSetPW(newPW);
		Passwd = newPW;
	}

	public void getUserInfo(org.omg.CORBA.StringHolder name,
							org.omg.CORBA.StringHolder pwd,
							org.omg.CORBA.StringHolder email) {

		//name = dbGetName();
		//pwd = dbGetPWD();
		//email = dbGetEmail();

		Name = name;
		Passwd = pwd;
		Email = email;

	}

	public void setUserInfo(String name, String pwd, String email);

		//name = dbSetName();
		//pwd = dbSetPWD();
		//email = dbSetEmail();

		Name = name;
		Passwd = pwd;
		Email = email;

	}

	public void deleteUser() {
		//dbDeleteUser(this);
		//delete(this);
		System.out.println("comes next");
	}

	public Date createDate(String start, String end, String location, String subject) {

		Date newDate = new Date();

		newDate.setStartTime(start);
		newDate.setEndTime(end);
		newDate.setLocation(location);
		newDate.setSubject(subject);

		//dbCreateDate(Date);

		return newDate;

	}

	public Date selectDate(short index) {

		//return dbGetDate(index);
		return null;

	}

	public Date findDate(String start, String end, String location,
             				String subject, short index) {

		//return dbFindDate(start, end, location, subject, index);
		return null;

	}

}
