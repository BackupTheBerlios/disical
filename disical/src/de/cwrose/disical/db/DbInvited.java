package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.disiorb.Date;
import de.cwrose.disical.corba.disiorb.Invited;
import de.cwrose.disical.corba.disiorb.Invitation;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalInvited;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;
import java.util.Enumeration;
import java.util.Vector;


public final class DbInvited extends DbPersistable
{
	private Invited skel;
	private DisicalInvited stub;
	private int index = 0;
	private boolean notify = false;

	public DbInvited ()
	throws PersistenceException
	{
		super ();
		stub = new DisicalInvited ();
		stub.setBubble (this);
		skel = stub._this (DisicalSrv.orb);
		putBubble (skel, this);
	}

	public Invited getInvited ()
	{
		return this.skel;
	}

	protected DisicalInvited getDisicalInvited ()
	{
		return this.stub;
	}

	public static Invited createInvited (Invitation i, User u)

		throws PersistenceException
	{
		DbInvited bubble = new DbInvited ();
		DisicalInvited inv = bubble.getDisicalInvited ();
		inv.setInvitation (i);
		System.out.println ("U-U:"+(Object)u+" "+u.getLogin());
		inv.setUser (u);
		
		inv.persist ();
		bubble.growOld();
		return bubble.getInvited ();
    }

	/* Property: User */

	public DbUser getUser () 
	throws PersistenceException
    {
		return (DbUser)lookupBubble(stub.getUser ());
	}

	public void setUser (DbUser login) {
		updateBubble (login.getUser (), login);
		stub.setUser (login.getUser ());
	}



	/* Property: Invitation */

	public DbInvitation getInvitation () 
	throws PersistenceException
    {
		return (DbInvitation)lookupBubble(skel.getInvitation ());
	}

	public void setInvitation (DbInvitation inv) {
		updateBubble (inv.getInvitation (), inv);
		stub.setInvitation (inv.getInvitation ());
	}



	/* Property: Date */

	public DbDate getDate () 
	throws PersistenceException
    {
		Date date = stub.getDate ();
		if (date == null)
			return (DbDate)lookupBubble(date);
		else
			return null;
	}

	public void setDate (DbDate date) {
		updateBubble (date.getDate (), date);
		stub.setDate (date.getDate ());
	}



	/* Property: State */

	public short getState ()
	{
		return skel.status ();
	}

	public void setState (short s)
						 
	{
		stub.setStatus (s);
	}



	/* Property: Notify */

	public boolean getNotify ()
	{
		return stub.getNotify ();
	}

	public void setNotify (boolean b)
	{
		stub.setNotify (b);
	}

	protected final static Invited [] enum2array (Enumeration enum)
	{
		Vector v = new Vector ();

		if (!enum.hasMoreElements ())
				return null;

		while (enum.hasMoreElements ())
			{
				DbInvited o = (DbInvited)enum.nextElement ();
				o.growOld ();
				v.addElement (o.getInvited ());
			}

		return (Invited [])v.toArray ();
	}

	/* Property: Index */

	public int getIndex () {
		return index;
	}

	public void setIndex (int index) {
		this.index = index;
	}

}
