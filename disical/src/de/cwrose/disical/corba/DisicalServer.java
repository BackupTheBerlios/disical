package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.*;

class DisicalServer extends ServerPOA {

	public final static String Id = "Server";
	public final static String Kind = "";

	public void createUser(String login, String name, String pwd, String email) {
		DisicalUser newUserImpl = new DisicalUser();
		User newUser = newUserImpl._this(DisicalSrv.orb);
		newUser.setLogin(login);
		newUser.setName(name);
		newUser.setPasswd(pwd);
		newUser.setEmail(email);

	//	dbCreateUser(newUser);
	}

	public User login(String login, String pwd) {

		DisicalUser newUserImpl = new DisicalUser();
		User newUser = newUserImpl._this(DisicalSrv.orb);
		newUser = null; //dbLogin(login, pwd);

		return newUser;
	}
}