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

		DisicalDate servant = new DisicalDate ();
		super.setServant (servant);
		servant.setBubble (this);
	}


	private DisicalDate getDateServant ()
	{
		return (DisicalDate)this.getServant ();
	}

	private Date getDateSkel ()
	{
		return this.getDateServant ()._this (DisicalSrv.orb);
	}

	public static Date createDate
		(String login,  
		 Timestamp start, Timestamp stop, 
		 String subject, String location, String descr)
		throws PersistenceException
	{
		DbDate dd = new DbDate ();
		DisicalDate servant = dd.getDateServant ();
		servant.setSubject (subject);
		servant.setLocation (location);
		servant.setStartTime (start.getTime ());
		servant.setEndTime (stop.getTime ());
		servant.setDescription (descr);
		servant.setLogin (login);

		Date d = dd.getDateSkel ();
		d.persist ();
		dd.growOld();

		return d;
	}




	/* Property: Login */

	public String getLogin () 
	throws PersistenceException
    {
		return getDateServant().getLogin();
	}

	public void setLogin (String login) {
		getDateServant().setLogin (login);
	}




	/* Property: Subject */

	public String getSubject () {
		return getDateServant().getSubject ();
	}

	public void setSubject (String subject) {
		getDateServant().setSubject (subject);
	}




	/* Property: Location */

	public String getLocation () {
		return getDateServant().getLocation ();
	}

	public void setLocation (String location) {
		getDateServant().setLocation (location);
	}



	/* Property: Description */

	public String getDescription () {
		return getDateServant().getDescription ();
	}

	public void setDescription (String location) {
		getDateServant().setDescription (location);
	}



	/* Property: Index */

	public int getIndex () {
		return getDateServant().getIndex ();
	}

	public void setIndex (int index) {
		getDateServant().setIndex (index);
	}



	/* Property: startTime */

	public void setStartTime (java.sql.Timestamp t)
	{
		getDateServant().setStartTime (t.getTime ());
	}

	public java.sql.Timestamp getStartTime ()
	{
		return new java.sql.Timestamp (getDateServant().getStartTime ());
	}



	/* Property: endTime */

	public void setEndTime (java.sql.Timestamp t)
	{
		getDateServant().setEndTime (t.getTime ());
	}

	public java.sql.Timestamp getEndTime ()
	{
		return new java.sql.Timestamp (getDateServant().getEndTime ());
	}


	protected final static Date [] qres2array (QueryResults res)
		throws EmptySeqException, PersistenceException 
	{
		Vector v = new Vector ();

		if (!res.hasMore ())
			throw new EmptySeqException ("Date");
		else
			do
				v.addElement (res.next ());
			while (res.hasMore ());

		Date [] ret = new Date [v.size()];
		Enumeration enum = v.elements ();
		for (int i=0;  i<ret.length; i++)
			{
				DbDate elem = (DbDate) enum.nextElement ();
				elem.growOld ();
				ret [i] = elem.getDateSkel ();
			}
		return ret;
	}
}
