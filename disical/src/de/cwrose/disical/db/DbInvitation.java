package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.Date;
import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.DisicalDate;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalInvitation;
import de.cwrose.disical.corba.DisicalInvited;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;
import java.sql.Timestamp;

public final class DbInvitation extends DbPersistable
{
	private Invitation skel;
	private DisicalInvitation stub;

	public DbInvitation ()
	throws PersistenceException
	{
		super ();
		stub = new DisicalInvitation ();
		stub.setBubble (this);
		skel = stub._this (DisicalSrv.orb);
		putBubble (skel, this);
	}

	public DbInvitation getInvitation ()
	{
		return this.skel;
	}



	public static Date createInvitation(User u,  Timestamp start, 
	Timestamp stop, String subject, String location, String descr)
		throws PersistenceException
	{
		DbInvitation di = new DbInvitation ();
		di.setSubject (subject);
		di.setLocation (location);
		di.setStartTime (start);
		di.setEndTime (stop);
		di.setDescription (descr);

		// Fill up external references
		System.out.println (u.getLogin ());
		di.setLogin ((DbUser)lookupBubble(u));

		Invitation i = di.getDate ();
		i.persist ();
		di.growOld();
		return i;
	}




	/* Property: Login */

	public DbUser getUser () 
	throws PersistenceException
    {
		return (DbUser)lookupBubble(skel.getFromUser ());
	}

	public void setUser (DbUser login) {
		skel.setFromUser (login.getUser ());
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
