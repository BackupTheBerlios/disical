package de.cwrose.disical.db;

import de.cwrose.disical.corba.disiorb.User;
import de.cwrose.disical.corba.DisicalUser;
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

	public static User createInvited (Invitation i, User u)

		throws PersistenceException
	{
		DbInvited bubble = new DbInvited ();
		DisicalInvited inv = bubble.getDisicalInvited ();
		inv.setInvitation (i);
		inv.setFromUser (u);
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
		stub.setInvitation (login.getUser ());
	}



	/* Property: Date */

	public DbDate getDate () 
	throws PersistenceException
    {
		return (DbDate)lookupBubble(skel.getDate ());
	}

	public void setDate (DbDate date) {
		stub.setDate (login.getDate ());
	}



	/* Property: State */

	public short getState ()
	{
		return skel.status ();
	}

	public void setState (short i)
						 
	{
		stub.setState (s);
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
