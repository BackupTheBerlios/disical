package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.*;

public class Client {

	public static void main(String[] args) {

		DisicalCli client = new DisicalCli(args);

		try {
		Server server = client.getServer();
		User myuser = server.createUser("carsten", "Carsten Rose", "hello", "carsten@cwrose.de");

		User newUser = null;

		myuser.destroy();

		DisicalCli newClient = new DisicalCli(args);	
		Server newServer = newClient.getServer();
		newUser = server.login("carsten", "hello");
		System.out.println(newUser.getLogin() + " " + newUser.getName() + " " + newUser.getEmail());

		long fstTime = System.currentTimeMillis();

		Date myDate = newUser.createDate(fstTime, fstTime+1234, "Mensa", "ESSEN!!!", "Isch haaaabe Hungor");
		System.out.println (myDate.getLogin () == newUser);
		//newDate = myDate.getDate

		Invitation invitation = newUser.createInvitation(fstTime+1000, fstTime+61000, "zuhause", "schlafen", "ich will...");
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
