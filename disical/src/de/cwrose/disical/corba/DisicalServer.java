// $Id: DisicalServer.java,v 1.12 2002/03/20 11:06:19 deafman Exp $
package de.cwrose.disical.corba;

/**
 * The DisicalServer is the Implementation of the first Object
 * the ORB gives to the User - It is known by the CORBA NameServer
 * as the disiorb.Server-Object
 *
 * User createUser(String login, String name, String pwd, String email);
 * User login(String login, String pwd);
 * void destroy();
 *
 * @author deafman
 * @version $Revision: 1.12 $
 */
import de.cwrose.disical.corba.DisicalUser;
import de.cwrose.disical.corba.disiorb.*;
import de.cwrose.disical.db.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.exolab.castor.jdo.PersistenceException;


public class DisicalServer extends ServerPOA {

	public final static String Id = "Server";
	public final static String Kind = "";

	/* creates a new login-user 
	 */
	public User createUser(String login, String name, String pwd, String email) 
		throws jdoPersistenceEx
    {
	 	try
		{	
			return DbUser.createUser(login, pwd, name, email);
		}
		catch (PersistenceException e)
		{
			System.out.println("JDO> "+e.getMessage());
			e.printStackTrace(System.out);
			throw 
				new jdoPersistenceEx ("jdo-Persistence Error:"+e.getMessage());
		}
	}

	/* allows a user to login to the server
	 */
	public User login(String login, String pwd) 
		throws wrongPwEx, jdoPersistenceEx 
	{
		User newUser = null;

		try {
			newUser= DbUser.login(login,pwd);
		} 
		catch (IllegalArgumentException e) {
			throw new wrongPwEx("You've entered an INVALID Password!");
		}
		catch (PersistenceException e) {
			System.out.println("JDO> "+e.getMessage());
			e.printStackTrace(System.out);
			throw 
				new jdoPersistenceEx("jdo-Persistence Error: "+e.getMessage());
		}		
		return newUser;
	}
	
	/* destroys the initial object in the poa
	 */
	public void destroy() {
	
	    POA poa = _default_POA();
	    try {
		byte[] id = poa.servant_to_id(this);
		poa.deactivate_object(id);
	    }
	    catch (org.omg.CORBA.UserException ex) {
	    
	    }
	}
}
