package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;


public final class DbUser extends DbPersistable
{
	private User skel;
	private String pwd;

	public DbUser ()
	{
		DisicalUser stub = new DisicalUser ();
		stub.setBubble (this);
		skel = stub._this (DisicalSrv.orb);
	}

	public User getUser ()
	{
		return this.skel;
	}

	public static User queryUserByLogin (String login)
	{
		DbUser bubble = null;
		
		/* OQL */

		User skel = bubble.getUser ();
		return skel;
	}


	public static User createUser (String login, String pwd, String name, String email)
	{
		DbUser bubble = new DbUser ();
		User usr = bubble.getUser ();
		usr.setLogin (login); 
		usr.setPasswd (pwd);
		usr.setName (name);
		usr.setEmail (email);
		usr.persist ();

		return usr;
    }

	/* Property: Login */

	public String getLogin () {
		return skel.getLogin ();
	}

	public void setLogin (String login) {
		skel.setLogin (login);
	}




	/* Property: Password */

	public String getPassword () {
		return this.pwd;
	}

	public void setPassword (String password) {
		this.pwd = password;
	}




	/* Property: Name */

	public String getName () {
		return skel.getName ();
	}

	public void setName (String name) {
		skel.setName (name);
	}




	/* Property: Email */

	public String getEmail () {
		return skel.getEmail ();
	}

	public void setEmail (String email) {
		skel.setEmail (email);
	}

}
