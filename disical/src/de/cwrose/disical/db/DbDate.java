package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.Date;
import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.DisicalDate;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;


public final class DbDate extends DbPersistable
{
	private Date skel;
	private DbUser dbLogin;

	public DbDate ()
	{
		super ();
		DisicalDate stub = new DisicalDate ();
		stub.setBubble (this);
		skel = stub._this (DisicalSrv.orb);
		dbLogin = null;
	}

	public Date getDate ()
	{
		return this.skel;
	}




	/* Property: Login */

	public DbUser getLogin () {
		return dbLogin;
	}

	public void setLogin (DbUser login) {
		/* Wir haben ein Problem... */

		dbLogin = login;
		skel.setLogin (dbLogin.getUser ());
	}




	/* Property: Subject */

	public String getSubject () {
		return skel.getSubject ();
	}

	public void setSubject (String subject) {
		skel.setSubject (subject);
	}




	/* Property: Location */

	public String getLocation () {
		return skel.getLocation ();
	}

	public void setLocation (String location) {
		skel.setLocation (location);
	}




	/* Property: Index */

	public int getIndex () {
		return skel.getIndex ();
	}

	public void setIndex (int index) {
		skel.setIndex (index);
	}


	/* Property: startTime */

	public void setStartTime (java.sql.Timestamp t)
	{
		skel.setStartTime (t.getNanos ());
	}

	public java.sql.Timestamp getStartTime ()
	{
		return new java.sql.Timestamp (skel.getStartTime ());
	}



	/* Property: endTime */

	public void setEndTime (java.sql.Timestamp t)
	{
		skel.setEndTime (t.getNanos ());
	}

	public java.sql.Timestamp getEndTime ()
	{
		return new java.sql.Timestamp (skel.getEndTime ());
	}
}
