package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.POA;

class DisicalInvitation extends InvitationPOA {

	public final static String Id = "Invitation";
	public final static String Kind = "";

	private User fromuser;
	private User[] touser;
	private Date date;
	private short status;

	public User getFromUser() {
		return fromuser;
	}

	public User[] getToUser() {
		return touser;
	}

	public Date getInvitationDate() {
		return date;
	}

	public short getStatus() {
		return status;
	}

	public void setFromUser(User fromUser) {
		fromuser = fromUser;
	}

	public void setToUser(User[] toUser) {
		touser = toUser;
	}

	public void setInvitationDate(Date date) {
		this.date = date;
	}

	public void setStatus(short newStatus) {
		status = newStatus;
	}

	public void setInvitation(User[] toUser, Date invitationDate) {

		touser = toUser;
		date = invitationDate;

	}

	public void delete() {
		System.out.println("comes later");
	}

	public boolean persist() {
	
		DisicalInvitation invitationImpl = new DisicalInvitation();

		invitationImpl.setFromUser(fromuser);
		invitationImpl.setToUser(touser);
		invitationImpl.setInvitationDate(date);
		invitationImpl.setStatus(status);

		Invitation invitation = invitationImpl._this(DisicalSrv.orb);

		boolean success = true; //dbPersistinvitation(invitation);
		
		return success;
	}

	public void destroy() {
		POA poa = _default_POA();
        try {
            byte[] id = poa.servant_to_id(this);
            poa.deactivate_object(id);
        }
            catch (org.omg.CORBA.UserException ex) {}
    }
}
