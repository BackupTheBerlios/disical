package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.Date;
import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.disiorb.Invitation;
import de.cwrose.disical.corba.disiorb.Invited;
import de.cwrose.disical.corba.DisicalDate;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalInvitation;
import de.cwrose.disical.corba.DisicalInvited;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Vector;

public final class DbInvitation extends DbPersistable
{
	private Invitation skel;
	private DisicalInvitation stub;
	private Vector toUsers = new Vector ();
	private int index = 0;

	public DbInvitation ()
	throws PersistenceException
	{
		super ();
		stub = new DisicalInvitation ();
		stub.setBubble (this);
		skel = stub._this (DisicalSrv.orb);
		putBubble (skel, this);
	}

	public Invitation getInvitation ()
	{
		return this.skel;
	}



	public static Invitation createInvitation(User u,  Timestamp start, 
	Timestamp stop, String subject, String location, String descr)
		throws PersistenceException
	{
		DbInvitation di = new DbInvitation ();
		
		di.setSubject (subject);
		di.setLocation (location);
		di.setStartTime (start);
		di.setEndTime (stop);
		di.setDescription (descr);
		di.setUser ((DbUser)lookupBubble(u));

		System.out.println ("STARTTIME:"+start);
		System.out.println ("STOPTTIME:"+stop);
		Invitation i = di.getInvitation ();
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
		return index;
	}

	public void setIndex (int index) {
		this.index = index;
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


	/* Property: toUsers */

	public DbInvited createToUser ()
		throws PersistenceException
	{
		return new DbInvited ();
	}

	public Vector getToUsers ()
	{
		return this.toUsers;
	}
	
	public void addToUser (DbInvited inv)
	{
		toUsers.addElement (inv);
	}

	public Invited[] getAllInvited ()
	{
		return (Invited [])getToUsers().toArray();
	}

	public  Date[] getAllNotifiedInv ()
		throws org.exolab.castor.jdo.PersistenceException
	{
		Database  db = DbManager.getConnection ();

		// OQL 
		OQLQuery oql = db.getOQLQuery 
			("SELECT i FROM de.cwrose.disical.db.DbInvited i "+
			 "WHERE i.login=$1 and i.notify=true and i.invitation=$2");
		oql.bind (this.getUser().getLogin());
		oql.bind (this.getIndex ());

		// Get Results
		db.begin();
		QueryResults res = oql.execute();
		db.commit();
		return (Date [])DbDate.enum2array(res);
	}

	protected final static Invitation [] enum2array (Enumeration enum)
	{
		Vector v = new Vector ();

		if (!enum.hasMoreElements ())
				return null;

		while (enum.hasMoreElements ())
			{
				DbInvitation o = (DbInvitation)enum.nextElement ();
				o.growOld ();
				v.addElement (o.getInvitation ());
			}

		return (Invitation [])v.toArray ();
	}
}
