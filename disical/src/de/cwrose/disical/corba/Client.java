package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.*;

public class Client {

	public static void main(String[] args) {

		DisicalCli client = new DisicalCli(args);

		Server server = client.getServer();
		User myuser = server.createUser("carsten", "Carsten Rose", "hello", "carsten@cwrose.de");

		myuser.destroy();
		System.out.println("here");
		//server.destroy();


		DisicalCli newClient = new DisicalCli(args);	
		Server newServer = newClient.getServer();
		try {
			User newUser = server.login("carsten", "hello");
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
