package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;


public final class DbUser extends DbPersistable
{
	private User skel;
	private String pwd;

	private boolean isLoginUser;

	public DbUser ()
	throws PersistenceException
	{
		super ();
		DisicalUser stub = new DisicalUser ();
		stub.setBubble (this);
		skel = stub._this (DisicalSrv.orb);
		this.isLoginUser = false;
		putBubble (skel, this);
	}

	public User getUser ()
	{
		return this.skel;
	}

	public boolean isLoginUser ()
	{
		return this.isLoginUser;
	}

	public void beLoginUser ()
	{
		this.isLoginUser = true;
	}



	public static User login (String login, String passwd)
		throws org.exolab.castor.jdo.PersistenceException,
			   IllegalArgumentException, IllegalStateException
	{
		DbUser bubble;

		/* OQL */
		Database     db  = DbManager.getConnection ();
		OQLQuery     oql = db.getOQLQuery 
			("SELECT u FROM de.cwrose.disical.db.DbUser u WHERE u.login=$1");
		oql.bind (login);
		db.begin();
		QueryResults res = oql.execute();
		if (!res.hasMore ())
			throw new IllegalArgumentException 
				("You don't have the right password!  Go away!");
		
		bubble = ((DbUser) res.next ());
		bubble.growOld ();
		if (bubble.isLoginUser ())
			throw new IllegalStateException 
				("You are already logged in. Don't do that!");
		bubble.beLoginUser ();
		db.commit();
		putBubble (bubble.getUser (), bubble);
		return bubble.getUser ();
	}

	public static User createUser (String login, String pwd, 
								   String name, String email)
		throws PersistenceException
	{
		DbUser bubble = new DbUser ();
		User usr = bubble.getUser ();
		usr.setLogin (login); 
		bubble.setPassword (pwd);
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
