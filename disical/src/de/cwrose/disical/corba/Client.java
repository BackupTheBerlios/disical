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

		Date myDate = newUser.createDate(fstTime, fstTime*10, "Mensa", "ESSEN!!!");
		//newDate = myDate.getDate
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
