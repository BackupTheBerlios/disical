// $Id: DisicalSrv.java,v 1.7 2002/01/31 03:08:04 deafman Exp $
package de.cwrose.disical.corba;

/**
 * The main()-class for the server, initialises the ORB and gives the
 * OR of the disiorb.Server-Obj to the NC
 *
 * @author deafman
 * @version $Revision: 1.7 $
 */
import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.Server;
import de.cwrose.disical.corba.Exceptions.*;

import org.omg.CORBA.ORB;

import java.lang.reflect.*;

public class DisicalSrv {

	public static ORB orb = null;

	public static void main(String[] args) throws Exception /* lots of */{


		CSetup disisetup = new CSetup();
		orb = disisetup.getORB(args);

		DisicalServer serverImpl = new DisicalServer();
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
		}
		
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
		
		try {
			disisetup.nc.unbind(disisetup.ncName);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
