package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import java.io.*;
import java.util.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;


public class DisicalSrv {

	public static ORB orb = null;

	public static void main(String[] args) {


		CSetup disisetup = new CSetup(args);
		orb = disisetup.getORB();
		
		DisicalServer serverImpl = new DisicalServer();
		Server server = serverImpl._this(orb);
		
		disisetup.setObjCount(1);
		disisetup.setNC(serverImpl.Id, serverImpl.Kind, 0);

		disisetup.initPOA();
		disisetup.orbRun();
		
		try {
			disisetup.nc.unbind(disisetup.ncName);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
