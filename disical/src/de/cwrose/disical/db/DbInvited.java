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
	private int index = 0;
	private int notify = 0;

	public DbInvited ()
	throws PersistenceException
	{
		super ();

		DisicalInvited servant = new DisicalInvited ();
		super.setServant (servant);
		servant.setBubble (this);
	}

	private DisicalInvited getInvitedServant ()
	{
		return (DisicalInvited) this.getServant ();
	}

	private Invited getInvitedSkel ()
	{
		return getInvitedServant ()._this (DisicalSrv.orb);
	}

	public static Invited createInvited (int invId, String toUser)

		throws PersistenceException
	{
		DbInvited bubble = new DbInvited ();
		DisicalInvited inv = bubble.getInvitedServant ();

		inv.setInvitationIndex (invId);
		inv.setLogin (toUser);		

		Invited i = bubble.getInvitedSkel ();
		i.persist ();
		bubble.growOld();

		return i;
    }




	/* Property: Login */

	public String getLogin () 
	throws PersistenceException
    {
		return getInvitedServant().getLogin ();
	}

	public void setLogin (String login) {
		getInvitedServant ().setLogin (login);
	}



	/* Property: Invitation */

	public int getInvitation () 
	throws PersistenceException
    {
		return getInvitedServant ().getInvitationIndex ();
	}

	public void setInvitation (int invId) {
		getInvitedServant ().setInvitationIndex (invId);
	}


	/* Property: State */

	public int getState ()
	{
		return getInvitedServant().status ();
	}

	public void setState (int s)
						 
	{
		getInvitedServant ().setStatus ((short)s);
	}



	/* Property: Notify */

	public int getNotify ()
	{
		return notify;
	}

	public void setNotify (int b)
	{
		this.notify = b;
	}




	/* Property: Index */

	public int getIndex () {
		return index;
	}

	public void setIndex (int index) {
		this.index = index;
	}




	protected final static Invited [] enum2array (Enumeration enum)
		throws EmptySeqException
	{
		Vector v = new Vector ();

		if (!enum.hasMoreElements ())
			throw new EmptySeqException ("Invited");

		while (enum.hasMoreElements ())
			{
				DbInvited o = (DbInvited)enum.nextElement ();
				o.growOld ();
				v.addElement (o.getInvitedSkel ());
			}

		return (Invited [])v.toArray ();
	}
}

