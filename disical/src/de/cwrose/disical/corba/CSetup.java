// $Id: CSetup.java,v 1.13 2002/03/06 13:51:21 deafman Exp $
package de.cwrose.disical.corba;

/**
 * CORBA setup-class
 * * initializes properties
 * * starts ORB and POA
 * * handles NameService
 * 
 * void setORB(ORB orb);
 * ORB getORB();
 * void orbRun();
 * void initPOA();
 * void setObjCount(int no);
 * CORBA.Object getNC(String id, String key, int objNo);
 * void setNC(String id, String key, int objNo);
 * 
 * @author deafman
 * @version $Revision: 1.13 $
 */
import de.cwrose.disical.corba.Exceptions.*;
import de.cwrose.disical.util.*;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class CSetup {
	public static org.omg.CORBA.ORB orb;

	private org.omg.CORBA.Object poaObj = null;
	private POA rootPOA = null;
	private POAManager manager = null;

	private org.omg.CORBA.Object corbaObj = null;
    private Properties props = System.getProperties ();

	private org.omg.CORBA.Object nsObj = null;
	public NamingContext nc = null;
	public NameComponent[] ncName;
	private int objCount;

	private org.omg.CORBA.Object serverObj = null;

	/* read in my local CORBA-config */
	public CSetup() {
		String o_cls = null;
		String o_sgl = null;
		String o_cfg = null;

		try {
			Properties cfg = CfgReader.readCfg ("corba");
			o_cls = cfg.getProperty ("org.omg.CORBA.ORBClass");
			o_sgl = cfg.getProperty ("org.omg.CORBA.ORBSingletonClass");
			o_cfg = cfg.getProperty ("ooc.config");
		} 
		catch (CfgMissingException e)
		{
			System.err.println ("Can't access corba cfg ! I die !");
			System.err.println (e.toString ());
			e.printStackTrace (System.err);
			System.exit (1);
		}
        props.put ("org.omg.CORBA.ORBClass", o_cls);
        props.put ("org.omg.CORBA.ORBSingletonClass", o_sgl);
        props.put ("ooc.config", o_cfg);
		System.out.println ("Running orb with:");
		System.out.println ("ORBClass      >"+o_cls+"<");
		System.out.println ("ORBSingleton  >"+o_sgl+"<");
		System.out.println ("config        >"+o_cfg+"<");
		System.setProperties (props);

	}

	/* associate a given object to tis orb */
	public void setCORBAObj(org.omg.CORBA.Object obj) {
		this.corbaObj = obj;
	}

	/* gives the local ORB */
	public ORB getORB(String[] args) {
		try {
			orb = org.omg.CORBA.ORB.init(args, props);
		}
		catch (Exception e)
		{
			System.err.println (e.toString ());
			e.printStackTrace (System.err);
			System.exit (1);
		}
		return orb;
	}

	/* starts the ORB after init */
	public void orbRun() {
		orb.run();
	}
    
	/* initializes the root-POA */
	public void initPOA()
	throws NoPOA {
		try {
			poaObj = orb.resolve_initial_references("RootPOA");
			rootPOA = POAHelper.narrow(poaObj);
			manager = rootPOA.the_POAManager();
			manager.activate();
		}
		catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
			throw new NoPOA();
		}
		catch (org.omg.PortableServer.POAManagerPackage.AdapterInactive ex) {
			throw new NoPOA();
		}
	}

	/* how many objects do you want to put into the NameService */
	public void setObjCount(int oc) {
		this.objCount = oc;
	}

	/* inits the nameservice for a apropiriate no of Objects 
	 * and anounces them
	 */
	public void setNC(String NCId, String NCKind, int objNo)
	throws NoNameServer, NoBindNC {
		try {
		nsObj = orb.resolve_initial_references("NameService");
		}
		catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
		System.out.println("Cannot find NameService");
		    throw new NoNameServer();
		}

		nc = NamingContextHelper.narrow(nsObj);

		ncName = new NameComponent[objCount];
		ncName[objNo] = new NameComponent();
		ncName[objNo].id = NCId;
		ncName[objNo].kind = NCKind;

		try {	
			nc.rebind(ncName, corbaObj);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		    throw new NoBindNC();
		}
	}

	/* get a reference of a CORBA-Object from the NS */
	public org.omg.CORBA.Object getNC(String NCId, 
		String NCKind, int objNo)
		throws NoNameServer, NoResolve {
		try {
			nsObj = orb.resolve_initial_references("NameService");
		}
		catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
			System.out.println("Cannot find NameService");
			throw new NoNameServer();
		}

		nc = NamingContextHelper.narrow(nsObj);

		ncName = new NameComponent[objCount];
		ncName[objNo] = new NameComponent();
		ncName[objNo].id = NCId;
		ncName[objNo].kind = NCKind;

		try {
			serverObj = nc.resolve(ncName);
		}
		catch (NotFound ex) {
			System.out.println(NCId + "can not be found");
	    
			switch (ex.why.value()) {
			case NotFoundReason._missing_node:
				System.out.println("Node not found");
				break;

			case NotFoundReason._not_context:
				System.out.println("Unkown context");
				break;
		
			case NotFoundReason._not_object:
				System.out.println("Object unknown");
				break;
			}
			ex.printStackTrace();
			throw new NoResolve();
		}
		catch (CannotProceed ex) {
			System.out.println("Cannot Proceed");
			ex.printStackTrace();
			throw new NoResolve();
		}
		catch (InvalidName ex) {
			System.out.println("Name is not valid");
			ex.printStackTrace();
			throw new NoResolve();
		}

		return serverObj;
	}    
}
