package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.disiorb.Date;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Vector;


public final class DbUser extends DbPersistable
{
	private String pwd;
	private boolean isLoginUser;

	public DbUser ()
	throws PersistenceException
	{
		super ();

		DisicalUser servant = new DisicalUser ();
		super.setServant (servant);
		servant.setBubble (this);
		this.isLoginUser = false;
	}

	private DisicalUser getUserServant ()
	{
		return (DisicalUser)this.getServant ();
	}

	private User getUserSkel ()
	{
		return this.getUserServant ()._this (DisicalSrv.orb);
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

		return bubble.getUserSkel ();
	}

	public static User lookupUser (String login)
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
		db.commit();

		return bubble.getUserSkel ();
	}

	public  Date[] listDatesByLocation (String location)
		throws org.exolab.castor.jdo.PersistenceException
	{
		Database  db = DbManager.getConnection ();

		// OQL 
		OQLQuery oql = db.getOQLQuery 
			("SELECT d FROM de.cwrose.disical.db.DbDate d "+
			 "WHERE d.login=$1 and d.location=$2");
		oql.bind (getLogin());
		oql.bind (location);

		// Get Results
		db.begin();
		QueryResults res = oql.execute();
		db.commit();
		return (Date [])DbDate.enum2array(res);
	}
	
	public  User[] listAllUsers ()
		throws org.exolab.castor.jdo.PersistenceException
	{
		Database  db = DbManager.getConnection ();

		// OQL 
		OQLQuery oql = db.getOQLQuery 
			("SELECT u FROM de.cwrose.disical.db.DbUser u; ");

		// Get Results
		db.begin();
		QueryResults res = oql.execute();
		db.commit();
		return (User [])DbUser.enum2array(res);
	}

	public  Date[] listDatesBySubject (String subject)
		throws org.exolab.castor.jdo.PersistenceException
	{
		Database  db = DbManager.getConnection ();

		// OQL 
		OQLQuery oql = db.getOQLQuery 
			("SELECT d FROM de.cwrose.disical.db.DbDate d "+
			 "WHERE d.login=$1 and d.subject=$2");
		oql.bind (getLogin());
		oql.bind (subject);

		// Get Results
		db.begin();
		QueryResults res = oql.execute();
		db.commit();
		return (Date [])DbDate.enum2array(res);
	}

	public  Date[] listDatesByTime (Timestamp startTime, Timestamp stopTime)
		throws org.exolab.castor.jdo.PersistenceException
	{
		Database  db = DbManager.getConnection ();

		// OQL 
		OQLQuery oql = db.getOQLQuery 
			("SELECT d FROM de.cwrose.disical.db.DbDate d WHERE d.login=$1"+
			 "d.login=$1 and $2<=d.startTime  and d.stopTime<=$3"+
			 "ORDER BY startTime ASC");
		oql.bind (getLogin());
		oql.bind (startTime);
		oql.bind (stopTime);

		// Get Results
		db.begin();
		QueryResults res = oql.execute();
		db.commit();
		return (Date [])DbDate.enum2array(res);
	}

	public static User createUser (String login, String pwd, 
								   String name, String email)
		throws PersistenceException
	{
		DbUser bubble = new DbUser ();
		DisicalUser servant = bubble.getUserServant ();
		servant.setLogin (login); 
		bubble.setPassword (pwd);
		servant.setName (name);
		servant.setEmail (email);
		servant.persist ();

		return bubble.getUserSkel ();
    }

	/* Property: Login */

	public String getLogin () {
		return getUserServant().getLogin ();
	}

	public void setLogin (String login) {
		getUserServant ().setLogin (login);
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
		return getUserServant().getName ();
	}

	public void setName (String name) {
		getUserServant().setName (name);
	}




	/* Property: Email */

	public String getEmail () {
		return getUserServant().getEmail ();
	}

	public void setEmail (String email) {
		getUserServant().setEmail (email);
	}

	protected final static User [] enum2array (Enumeration enum)
	{
		Vector v = new Vector ();

		if (!enum.hasMoreElements ())
				return null;

		while (enum.hasMoreElements ())
			{
				DbUser o = (DbUser)enum.nextElement ();
				o.growOld ();
				v.addElement (o.getUserSkel ());
			}
		return (User [])v.toArray ();
	}
}
