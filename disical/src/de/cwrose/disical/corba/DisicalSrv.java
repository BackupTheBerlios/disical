// $Id: DisicalSrv.java,v 1.8 2002/03/20 11:06:19 deafman Exp $
package de.cwrose.disical.corba;

/**
 * The main()-class for the server, initialises the ORB and gives the
 * OR of the disiorb.Server-Obj to the NC
 *
 * @author deafman
 * @version $Revision: 1.8 $
 */
import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.Server;
import de.cwrose.disical.corba.Exceptions.*;

import org.omg.CORBA.ORB;

import java.lang.reflect.*;

public class DisicalSrv {

	public static ORB orb = null;

	public static void main(String[] args) throws Exception /* lots of */{

		/* setup the ORB */
		CSetup disisetup = new CSetup();
		/* get the created ORB */
		orb = disisetup.getORB(args); 

		/* create the initial object... */
		DisicalServer serverImpl = new DisicalServer();
		/* ...and anounce it to the nameservice */
		Server server = serverImpl._this(orb);
		disisetup.setCORBAObj(server);
		disisetup.setObjCount(1);
		try {
			disisetup.setNC(serverImpl.Id, serverImpl.Kind, 0);
		}
		catch (NoNameServer e) {
			System.err.println( e.toString() );
		}
		catch (NoBindNC e) {
			System.err.println( e.toString() );
		} // anounce done
		
		/* initialize the POA */
		try {
			disisetup.initPOA();
		}
		catch (NoPOA e) {
			System.err.println( e.toString() );
		}
		try {
			disisetup.orbRun();
		}
		catch (Exception e) {
			if (e instanceof InvocationTargetException)
				{
					Throwable t = ((InvocationTargetException)e).getTargetException();
					System.out.println(e.getMessage());
					e.printStackTrace(System.out);
				}
			else
				throw e;
		}
		
		/* when the server is done, unbind from the nc */
		try {
			disisetup.nc.unbind(disisetup.ncName);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
