package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.*;

import de.cwrose.disical.util.HackHelper;
public class Client {

	private static void printDate(Date date) {
		System.out.println("User : "+date.getLogin());
		System.out.println("Start: "+date.getStartTime());
		System.out.println("Ende : "+date.getEndTime());
		System.out.println("Betr : "+date.getSubject());
		System.out.println("Locat: "+date.getLocation());
		System.out.println("Discr: "+date.getDescription()+"\n");
	}

	private static void printInvited(Invited[] invited) {

		for (int i = 0; i < invited.length; i++){
			System.out.print("CC "+(i+1)+":"+invited[i]);
		}
		System.out.println("\n");
	}

	private static void printInvitation(Invitation inv) {
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

			Server server = client.getServer();
			User myuser = 
				server.createUser("carsten", "Carsten Rose", 
								  "hello", "carsten@cwrose.de");

			User newUser = null;
			
			myuser.destroy();

			DisicalCli newClient = new DisicalCli(args);	
			Server newServer = newClient.getServer();
			newUser = server.login("carsten", "hello");
			System.out.println(newUser.getLogin() + " " 
							   + newUser.getName() + " " + newUser.getEmail());
			
			long fstTime = System.currentTimeMillis();
			
			Date[] myDate = new Date[5];
			for (int j = 0; j < 5; j++) {
				myDate[j] = 
					newUser.createDate(fstTime+j*234, fstTime+1234+j*500, 
									   "Mensa"+Integer.toString(j), 
									   "ESSEN!!!", "Isch haaaabe Hungor");
			}

			//			System.out.println (myDate.getLogin () == newUser);
			
			Invitation invitation 
				= newUser.createInvitation(fstTime, fstTime+1234, 
										   "zuhause", "schlafen", "ich will...");
			try {
				User stepn = 
					server.createUser("stepn", "Stefan Plantikow", 
									  "foo", "foo@bar.com");
				User fabian = 
					server.createUser("fabian", "Fabian Müller", 
									  "hello", "baltarzar@t-online.de");
				User conny = 
					server.createUser("conny", "Cornelius Keller", 
									  "start", "ckeller@informatik.hu-berlin.de");
				System.out.println ("S-U:"+(Object)stepn+" "+stepn.getLogin());

			invitation.invite(stepn);
			//invitation.invite(fabian);
			//invitation.invite(conny);

			}
			catch (jdoPersistenceEx e) {
				HackHelper.printEx(e, System.out);
			}
			
			/*			for (int j=0; j<5; j++) {

				Date[] locdate = newUser.listDatesByLocation("Mensa1");
				printDate(locdate[0]);		
				Date[] subdate = newUser.listDatesBySubject("ESSEN!!!");
				printDate(subdate[0]);
				Date[] timedate = newUser.listDatesByTime(fstTime,
														  fstTime+1234);
				printDate(timedate[0]);
			}
			*/
			//			Invitation[] newInv = newUser.getInvitations();
			//printInvitation(newInv[0]);
			/*			
			for (int k=0; k<newInv.length; k++) {
				printInvited(newInv[k].getAllInvited());
				
				}*/
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
