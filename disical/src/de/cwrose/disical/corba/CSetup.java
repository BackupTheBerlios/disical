package de.cwrose.disical.corba;

import de.cwrose.disical.corba.Exceptions.*;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class CSetup {

	private static Properties props = System.getProperties();

	public static ORB orb;

	private org.omg.CORBA.Object poaObj = null;
	private POA rootPOA = null;
	private POAManager manager = null;

	private org.omg.CORBA.Object corbaObj = null;

	private org.omg.CORBA.Object nsObj = null;
	public NamingContext nc = null;
	public NameComponent[] ncName;
	private int objCount;

	private org.omg.CORBA.Object serverObj = null;

	public CSetup() {

		props.put("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
		props.put("org.omg.CORBA.ORBSingletonClass", "com.ooc.CORBA.ORBSingleton");
		props.put("ooc.config", "/etc/OB.conf");
	}

	public ORB getORB(String[] args) {
		orb = ORB.init(args, props);
		return this.orb;
	}

	public void orbRun() {
		orb.run();
	}
    
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

	public void setObjCount(int oc) {
		this.objCount = oc;
	}

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
