package de.cwrose.disical.corba;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.Server;
import de.cwrose.disical.corba.Exceptions.*;
import org.omg.CORBA.ORB;

public class DisicalSrv {

	public static ORB orb = null;

	public static void main(String[] args) {


		CSetup disisetup = new CSetup();
		orb = disisetup.getORB(args);
		
		DisicalServer serverImpl = new DisicalServer();
		Server server = serverImpl._this(orb);
		
		disisetup.setObjCount(1);
		try {
			disisetup.setNC(serverImpl.Id, serverImpl.Kind, 0);
		}
		catch (NoNameServer e) {
			System.err.println( e.toString() );
		}
		catch (NoBindNC e) {
			System.err.println( e.toString() );
		}
		
		try {
			disisetup.initPOA();
		}
		catch (NoPOA e) {
			System.err.println( e.toString() );
		}
		
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
