package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import de.cwrose.disical.db.DbUser;

public class DisicalUser extends UserPOA {

	public final static String Id = "User";
	public final static String Kind = "";

	private String login = null;
	private String name = null;
	private String email = null;

	private static  int dateLimit = 1000;
	private static int inviteLimit = 100;

	/* Client doesnt see the bubble.. ooh */

	private DbUser bubble = null;

	public void setBubble (DbUser bubble) {
		this.bubble = bubble;
	}

	public DbUser getBubble () {
		return this.bubble;
	}




	public void setLogin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPasswd(String newPW) {
		bubble.setPassword (newPW);
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswd() {
		return bubble.getPassword ();
	}

	public boolean persist() {
		return bubble.persist ();
	}

	public void deleteUser() {
		DisicalUser newUserImpl = new DisicalUser();

		newUserImpl.setLogin(login);
		newUserImpl.setName(name);
		newUserImpl.setEmail(email);
		newUserImpl.setPasswd(this.getPasswd ());

		// dbDeleteUser(newUserImpl);
	}

	public Date createDate(long start, long end, String location, String subject) {

		DisicalDate newDateImpl = new DisicalDate();
		
		newDateImpl.setStartTime(start);
		newDateImpl.setEndTime(end);
		newDateImpl.setLocation(location);
		newDateImpl.setSubject(subject);

		//dbCreateDate(newDateImpl);
		Date newDate = newDateImpl._this(DisicalSrv.orb);

		return newDate;

	}

	public Date selectDate(int index) {

		DisicalDate selDateImpl = new DisicalDate();
		selDateImpl.setIndex(index);
		selDateImpl = null; //dbGetDate(setDateImpl);

		Date selDate = selDateImpl._this(DisicalSrv.orb);

		return selDate;
	}

	public Date[] listDatesByTime(long start, long end) {

		DisicalDate selDateImpl = new DisicalDate();
		DisicalDate[] listDateImpl;

		selDateImpl.setStartTime(start);
		selDateImpl.setEndTime(end);
		listDateImpl = null; //dbGetDatesByTime(selDateImpl);

		dateLimit = listDateImpl.length;
		Date[] dateList = new Date[dateLimit];

		for ( int i = 0; i < dateLimit; i++) {
			dateList[i] = listDateImpl[i]._this(DisicalSrv.orb);
		}

		return dateList;
	}

	public Date[] listDatesByLocation(String location) {

		DisicalDate selDateImpl = new DisicalDate();
		DisicalDate[] listDateImpl;

		selDateImpl.setLocation(location);
		listDateImpl = null; //dbGetDatesByLocation(selDateImpl);

		dateLimit = listDateImpl.length;
		Date[] dateList = new Date[dateLimit];

		for ( int i = 0; i < dateLimit; i++) {
			dateList[i] = listDateImpl[i]._this(DisicalSrv.orb);
		}

		return dateList;
	}

	public Date[] listDatesBySubject(String subject) {

		DisicalDate selDateImpl = new DisicalDate();
		DisicalDate[] listDateImpl;

		selDateImpl.setSubject(subject);
		listDateImpl = null; //dbGetDatesBySubject(selDateImpl);

		dateLimit = listDateImpl.length;
		Date[] dateList = new Date[dateLimit];

		for ( int i = 0; i < dateLimit; i++) {
			dateList[i] = listDateImpl[i]._this(DisicalSrv.orb);
		}

		return dateList;
	}

	public Invitation[] getInvitations() {

		DisicalDate selDateImpl = new DisicalDate();
		selDateImpl.setLogin(login);

		DisicalInvitation[] listInvitationImpl = 
				new DisicalInvitation[inviteLimit];

		Invitation[] listInvitation = new Invitation[inviteLimit];

		DisicalInvStatus invStatus = new DisicalInvStatus();
		DisicalUser fromUserImpl = new DisicalUser();
		DisicalUser[] toUserImpl = new DisicalUser[inviteLimit];
		User[] toUser = new User[inviteLimit];
		DisicalDate invDateImpl = new DisicalDate();

		for (int i = 0; i < inviteLimit; i++) {
			invStatus = null; //dbGetInvStatus();
			listInvitation[i].setStatus(invStatus.getStatus());

			fromUserImpl = null; //dbGetInvFromUser();
			User fromUser = fromUserImpl._this(DisicalSrv.orb);
			listInvitation[i].setFromUser(fromUser);
			
			toUserImpl = null; //dbGetInvToUser();
			for (int j = 0; j < inviteLimit; j++) {
				toUser[j] = toUserImpl[j]._this(DisicalSrv.orb);
			}
			listInvitation[i].setToUser(toUser);

			invDateImpl = null; //dbGetInvDate();
			Date invDate = invDateImpl._this(DisicalSrv.orb);
			listInvitation[i].setInvitationDate(invDate);
		}

		return listInvitation;
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
