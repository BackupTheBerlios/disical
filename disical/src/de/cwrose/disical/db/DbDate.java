package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.Date;
import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.DisicalDate;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Vector;

public final class DbDate extends DbPersistable
{
	private Date skel;

	public DbDate ()
	throws PersistenceException
	{
		super ();
		DisicalDate stub = new DisicalDate ();
		stub.setBubble (this);
		skel = stub._this (DisicalSrv.orb);
		//		putBubble (skel, this);
	}

	public Date getDate ()
	{
		return this.skel;
	}



	public static Date createDate(User u,  Timestamp start, Timestamp stop, 
			String subject, String location, String descr)
		throws PersistenceException
	{
		DbDate dd = new DbDate ();
		dd.setSubject (subject);
		dd.setLocation (location);
		dd.setStartTime (start);
		dd.setEndTime (stop);
		dd.setDescription (descr);

		// Fill up external references
		System.out.println (u.getLogin ());
		dd.setLogin ((DbUser)lookupBubble(u));

		Date d = dd.getDate ();
		d.persist ();
		dd.growOld();
		return d;
	}




	/* Property: Login */

	public DbUser getLogin () 
	throws PersistenceException
    {
		return (DbUser)lookupBubble(skel.getLogin ());
	}

	public void setLogin (DbUser login) {
		skel.setLogin (login.getUser ());
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



	/* Property: Description */

	public String getDescription () {
		return skel.getDescription ();
	}

	public void setDescription (String location) {
		skel.setDescription (location);
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
		skel.setStartTime (t.getTime ());
	}

	public java.sql.Timestamp getStartTime ()
	{
		return new java.sql.Timestamp (skel.getStartTime ());
	}



	/* Property: endTime */

	public void setEndTime (java.sql.Timestamp t)
	{
		skel.setEndTime (t.getTime ());
	}

	public java.sql.Timestamp getEndTime ()
	{
		return new java.sql.Timestamp (skel.getEndTime ());
	}


	protected final static Date [] enum2array (Enumeration enum)
	{
		Vector v = new Vector ();

		if (!enum.hasMoreElements ())
				return null;

		while (enum.hasMoreElements ())
			{
				DbDate o = (DbDate)enum.nextElement ();
				o.growOld ();
				v.addElement (o.getDate ());
			}

		return (Date [])v.toArray ();
	}
}
