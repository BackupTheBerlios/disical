package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.*;

public class Client {

	public static void main(String[] args) {

		DisicalCli client = new DisicalCli(args);

		Server server = client.getServer();
		User myuser = server.createUser("carsten", "Carsten Rose", "hello", "carsten@cwrose.de");
		
		}

}	
