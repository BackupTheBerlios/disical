// $Id: DisicalCli.java,v 1.6 2002/01/29 14:16:42 deafman Exp $
package de.cwrose.disical.corba;

/**
 * Sets up the DisicalClient, that it can find the Objects 
 * in the Nameserver. Gets the initial disiorb.Server-Object
 *
 * disiorb.Server getServer();
 * ORB getORB();
 *
 * @author deafman
 * @version $Revision: 1.6 $
 */
import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.corba.Exceptions.*;
import org.omg.CORBA.ORB;

public class DisicalCli {

	private CSetup cliSetup;
	private Server disicalServer = null;
	private ORB orb;
	private org.omg.CORBA.Object disicalServerImpl = null;

	public DisicalCli(String[] args) {

		cliSetup = new CSetup();
		orb = cliSetup.getORB(args);

		cliSetup.setObjCount(1);
		try {
			disicalServerImpl = cliSetup.getNC("Server","",0);
		}
		catch (NoNameServer e) {}
		catch (NoResolve e) {}			

		disicalServer = ServerHelper.narrow(disicalServerImpl);
	}

	public Server getServer() {
		return disicalServer;
	}

	public ORB getORB() {
		return orb;
	}
}
