package de.cwrose.disical.corba;

import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.*;
import org.omg.CORBA.ORB;

public class DisicalServer extends ServerPOA {

	public final static String Id = "Server";
	public final static String Kind = "";

	public User createUser(String login, String name, String pwd, String email) {
		
		DbUser dbUser = new DbUser();
		
		return dbUser.createUser(login, pwd, name, email);
	}

	public User login(String login, String pwd) 
		throws wrongPwEx, jdoPersistenceEx {

		DbUser dbUser = new DbUser();
		User newUser;

		try {
			newUser = dbUser.login(login,pwd);
		} 
		catch (IllegalArgumentException e) {
			throw new wrongPwEx("You've entered an INVALID Password!");
		}
		catch (org.exolab.castor.jdo.PersistenceException e) {
			throw new jdoPersistenceEx("jdo-Persistence Error");
		}		
		return newUser;
	}
}
