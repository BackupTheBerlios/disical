package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.*;

class DisicalServer extends ServerPOA {

	public final static String Id = "Server";
	public final static String Kind = "";

	public void createUser(String login, String name, String pwd, String email) {
	//	dbCreateUser(String login, String name, String pwd, String email);
		System.out.println("Creating new User on DB");
	}

	public User login(String login, String pwd) {
		
		DisicalUser newUserImpl = new DisicalUser();
		newUserImpl = null; // dbUser();		
		User newUser = newUserImpl._this(DisicalSrv.orb);

		return newUser;
	}
}
