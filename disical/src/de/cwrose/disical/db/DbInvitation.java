package de.cwrose.disical.db;

import de.cwrose.disical.db.DbUser;
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
	private Vector toUsers = new Vector ();
	private int index = 0;

	public DbInvitation ()
	throws PersistenceException
	{
		super ();

		DisicalInvitation servant = new DisicalInvitation ();
		super.setServant (servant);
		servant.setBubble (this);
	}

	private DisicalInvitation getInvitationServant ()
	{
		return (DisicalInvitation)this.getServant ();
	}

	private Invitation getInvitationSkel ()
	{
		return this.getInvitationServant ()._this (DisicalSrv.orb);
	}

	public static Invitation createInvitation(String login,  Timestamp start, 
	Timestamp stop, String subject, String location, String descr)
		throws PersistenceException
	{
		DbInvitation di = new DbInvitation ();
		DisicalInvitation servant = di.getInvitationServant ();

		servant.setSubject (subject);
		servant.setLocation (location);
		servant.setStartTime (start.getTime ());
		servant.setEndTime (stop.getTime ());
		servant.setDescription (descr);
		servant.setLogin (login);

		System.out.println ("STARTTIME:"+start);
		System.out.println ("STOPTTIME:"+stop);

		Invitation i = di.getInvitationSkel ();
		i.persist ();
		di.growOld();
		return i;
	}




	/* Property: Login */

	public String getLogin () 
	throws PersistenceException
    {
		return getInvitationServant ().getLogin ();
	}

	public void setLogin (String login) {
		getInvitationServant ().setLogin (login);
	}




	/* Property: Subject */

	public String getSubject () {
		return getInvitationServant().getSubject ();
	}

	public void setSubject (String subject) {
		getInvitationServant().setSubject (subject);
	}




	/* Property: Location */

	public String getLocation () {
		return getInvitationServant().getLocation ();
	}

	public void setLocation (String location) {
		getInvitationServant().setLocation (location);
	}




	/* Property: Description */

	public String getDescription () {
		return getInvitationServant().getDescription ();
	}

	public void setDescription (String location) {
		getInvitationServant().setDescription (location);
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
		getInvitationServant().setStartTime (t.getTime ());
	}

	public java.sql.Timestamp getStartTime ()
	{
		return new java.sql.Timestamp (getInvitationServant().getStartTime ());
	}



	/* Property: endTime */

	public void setEndTime (java.sql.Timestamp t)
	{
		getInvitationServant().setEndTime (t.getTime ());
	}

	public java.sql.Timestamp getEndTime ()
	{
		return new java.sql.Timestamp (getInvitationServant().getEndTime ());
	}


	public Invited[] getAllInvited ()
		throws org.exolab.castor.jdo.PersistenceException, EmptySeqException
	{
		Database  db = DbManager.getConnection ();

		// OQL 
		OQLQuery oql = db.getOQLQuery 
			("SELECT i FROM de.cwrose.disical.db.DbInvited i "+
			 "WHERE i.login=$1 and i.invitation=$2");
		oql.bind (this.getLogin());
		oql.bind (this.getIndex ());

		// Get Results
		db.begin();
		QueryResults res = oql.execute();
		db.commit();
		return (Invited [])DbInvited.enum2array(res);
	}

	public  Invited[] getAllNotifiedInv ()
		throws org.exolab.castor.jdo.PersistenceException, EmptySeqException
	{
		Database  db = DbManager.getConnection ();

		// OQL 
		short s = 1;
		OQLQuery oql = db.getOQLQuery 
			("SELECT i FROM de.cwrose.disical.db.DbInvited i "+
			 "WHERE i.login=$1 and i.notify=$2 and i.invitation=$3");
		oql.bind (this.getLogin());
		oql.bind (s);
		oql.bind (this.getIndex ());

		// Get Results
		db.begin();
		QueryResults res = oql.execute();
		db.commit();
		return (Invited [])DbInvited.enum2array(res);
	}

	public static Invitation lookupInvitation (int invId)
		throws org.exolab.castor.jdo.PersistenceException,
			   IllegalArgumentException, IllegalStateException
	{
		DbInvitation bubble;

		/* OQL */
		Database     db  = DbManager.getConnection ();
		OQLQuery     oql = db.getOQLQuery 
			("SELECT i FROM de.cwrose.disical.db.DbInvitation i WHERE i.id=$1");
		oql.bind (invId);
		db.begin();
		QueryResults res = oql.execute();
		if (!res.hasMore ())
			throw new IllegalArgumentException 
				("You don't have the right password!  Go away!");
		
		bubble = ((DbInvitation) res.next ());
		bubble.growOld ();
		db.commit();

		return bubble.getInvitationSkel ();
	}

	protected final static Invitation [] enum2array (Enumeration enum)
		throws EmptySeqException
	{
		Vector v = new Vector ();

		if (!enum.hasMoreElements ())
			throw new EmptySeqException ("Invitation");

		while (enum.hasMoreElements ())
			{
				DbInvitation o = (DbInvitation)enum.nextElement ();
				o.growOld ();
				v.addElement (o.getInvitationSkel ());
			}

		return (Invitation [])v.toArray ();
	}
}
