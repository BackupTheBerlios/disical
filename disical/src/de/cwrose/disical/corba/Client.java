package de.cwrose.disical.corba;

/**
 * A litle sampleclient for testing the facilities of our nice
 * server
 */
import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.*;

import de.cwrose.disical.util.HackHelper;
public class Client {

	private static void printDate(Date date) 
	throws jdoPersistenceEx {
		System.out.println("User : "+date.getUser());
		System.out.println("Start: "+date.getStartTime());
		System.out.println("Ende : "+date.getEndTime());
		System.out.println("Betr : "+date.getSubject());
		System.out.println("Locat: "+date.getLocation());
		System.out.println("Discr: "+date.getDescription()+"\n");
	}

	private static void printInvited(Invited[] invited) 
	throws jdoPersistenceEx {

		for (int i = 0; i < invited.length; i++){
			System.out.print("CC "+(i+1)+":"+invited[i]);
		}
		System.out.println("\n");
	}

	private static void printInvitation(Invitation inv) 
	throws jdoPersistenceEx {
		System.out.println("Von  : "+inv.getFromUser());
		System.out.println("Start: "+inv.getStartTime());
		System.out.println("Ende : "+inv.getEndTime());
		System.out.println("Betr : "+inv.getSubject());
		System.out.println("Locat: "+inv.getLocation());
		System.out.println("Discr: "+inv.getDescription()+"\n");
	}

	public static void main(String[] args) {

		DisicalCli client = new DisicalCli(args);

		try {

			/* create new user at the db */
			Server server = client.getServer();
			User myuser = 
				server.createUser("carsten", "Carsten Rose", 
								  "hello", "carsten@cwrose.de");

			User newUser = null;
			
			myuser.destroy(); /* Logout :-) */

			DisicalCli newClient = new DisicalCli(args);	
			Server newServer = newClient.getServer();
			newUser = server.login("carsten", "hello"); /* relogin */
			System.out.println(newUser.getLogin() + " " 
							   + newUser.getName() + " " + newUser.getEmail());
			
			long fstTime = System.currentTimeMillis();
			
			Date[] myDate = new Date[5]; /* my first date */
			for (int j = 0; j < 5; j++) {
				myDate[j] = 
					newUser.createDate(fstTime+j*234, fstTime+1234+j*500, 
									   "Mensa"+Integer.toString(j), 
									   "ESSEN!!!", "Isch haaaabe Hungor");
			}

			//			System.out.println (myDate.getLogin () == newUser);
			
			Invitation invitation /* create a new invitation-date */
				= newUser.createInvitation(fstTime, fstTime+1234, 
										   "zuhause", "schlafen", "ich will...");
			/* create the other users - normaly they are stil there */
			try {
				User stepn = 
					server.createUser("stepn", "Stefan Plantikow", 
									  "foo", "foo@bar.com");
				User fabian = 
					server.createUser("fabian", "Fabian Mueller", 
									  "hello", "baltarzar@t-online.de");
				User conny = 
					server.createUser("conny", "Cornelius Keller", 
									  "start", "ckeller@informatik.hu-berlin.de");
				System.out.println ("S-U:"+(Object)stepn+" "+stepn.getLogin());

				stepn.setEmail("plantiko@informatik.hu-berlin.de");
				System.out.println(stepn.getLogin() + " " 
								   + stepn.getName() + " " + stepn.getEmail());

				stepn.persist();

				/* invite my pals */
				invitation.invite(stepn);
				invitation.invite(fabian);
				invitation.invite(conny);

			}
			catch (jdoPersistenceEx e) {
				HackHelper.printEx(e, System.out);
			}

			try {				
				
				for (int j=0; j<5; j++) { /* get all my dates */
					Date[] locdate = newUser.listDatesByLocation("Mensa1");
					printDate(locdate[0]);	 /* by selected location */
					Date[] subdate = newUser.listDatesBySubject("ESSEN!!!");
					printDate(subdate[0]);   // by subject 
					Date[] timedate = newUser.listDatesByTime(fstTime,
														  fstTime+1234);
					printDate(timedate[0]);  
				}
				
				Invitation[] newInv = newUser.getInvitations();
				printInvitation(newInv[0]);
				for (int k=0; k<newInv.length; k++) {
					printInvited(newInv[k].getAllInvited()); 
					/* now all my invitations with status aso */
				}
			} catch (emptySeqEx e){
				System.err.println(e.getMessage());
				e.printStackTrace(System.err);
			}
			
		}
		catch (wrongPwEx e) {
			System.out.println(e.toString());
		}	
		catch (jdoPersistenceEx e) {
			System.out.println(e.toString());
			e.printStackTrace(System.out);

		}


	}
}	
