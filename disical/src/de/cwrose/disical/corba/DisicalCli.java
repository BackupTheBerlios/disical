// $Id: DisicalCli.java,v 1.7 2002/03/06 13:51:21 deafman Exp $
package de.cwrose.disical.corba;

/**
 * Sets up the DisicalClient, that it can find the Objects 
 * in the Nameserver. Gets the initial disiorb.Server-Object
 *
 * disiorb.Server getServer();
 * ORB getORB();
 *
 * @author deafman
 * @version $Revision: 1.7 $
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

		/* get the starting "server"-object from the nc 
		 * it will be stored in this object
		 */
		cliSetup.setObjCount(1);
		try {
			disicalServerImpl = cliSetup.getNC("Server","",0);
		}
		catch (NoNameServer e) {}
		catch (NoResolve e) {}			

		disicalServer = ServerHelper.narrow(disicalServerImpl);
	}

	/* gert the initial server-obj */
	public Server getServer() {
		return disicalServer;
	}

	/* sometimes you need the ORB */
	public ORB getORB() {
		return orb;
	}
}
