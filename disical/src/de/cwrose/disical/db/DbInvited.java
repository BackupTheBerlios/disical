package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.disiorb.Date;
import de.cwrose.disical.corba.disiorb.Invited;
import de.cwrose.disical.corba.disiorb.Invitation;
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.DisicalInvited;
import de.cwrose.disical.corba.DisicalSrv;
import org.exolab.castor.jdo.*;


public final class DbInvited extends DbPersistable
{
	private Invited skel;
	private DisicalInvited stub;

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
		inv.setUser (u);
		
		Invited ret = bubble.getInvited ();
		ret.persist ();

		return ret;
    }

	/* Property: User */

	public DbUser getUser () 
	throws PersistenceException
    {
		return (DbUser)lookupBubble(skel.getUser ());
	}

	public void setUser (DbUser login) {
		stub.setUser (login.getUser ());
	}



	/* Property: Invitation */

	public DbInvitation getInvitation () 
	throws PersistenceException
    {
		return (DbInvitation)lookupBubble(skel.getInvitation ());
	}

	public void setInvitation (DbInvitation inv) {
		stub.setInvitation (inv.getInvitation ());
	}



	/* Property: Date */

	public DbDate getDate () 
	throws PersistenceException
    {
		return (DbDate)lookupBubble(skel.getDate ());
	}

	public void setDate (DbDate date) {
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
}
