package de.cwrose.disical.corba;

import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.*;
import org.omg.CORBA.ORB;

public class DisicalServer extends ServerPOA {

	public final static String Id = "Server";
	public final static String Kind = "";

	public User createUser(String login, String name, String pwd, String email) {
/*		DisicalUser newUser = new DisicalUser();
		newUser.setLogin(login);
		newUser.setName(name);
		newUser.setPasswd(pwd);
		newUser.setEmail(email);
*/
		DbUser dbUser = new DbUser();
		
		return dbUser.createUser(login, pwd, name, email);
	}

	public User login(String login, String pwd) {

		DisicalUser newUserImpl = new DisicalUser();
		newUserImpl = null; //dbLogin(login, pwd);
		User newUser = newUserImpl._this(DisicalSrv.orb);

		return newUser;
	}
}
