//package de.cwrose.disical.corba;

import java.io.*;
import java.util.Properties;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;

class CSetup {

    private static Properties props = System.getProperties();

    private static ORB orb;

    private static org.omg.CORBA.Object poaObj = null;
    private static POA rootPOA = null;
    private static POAManager manager = null;

    private static org.omg.CORBA.Object corbaObj = null;

    private static org.omg.CORBA.Object nsObj = null;
    private static NamingContext nc = null;
    private static NameComponent[] ncName;
    
    CSetup(String Id, String Kind) {

    	props.put("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
    	props.put("org.omg.CORBA.ORBSingletonClass", "com.ooc.CORBA.ORBSingleton");
    	props.put("ooc.config", "OB.conf");

	initPOA();
	//initORB();
	//initNS(Id, Kind);
	
	try {
	    manager.activate();
	}
	catch(org.omg.PortableServer.POAManagerPackage.AdapterInactive ex) {
	    
	    throw new RuntimeException();
	    
	}
	
	orb.run();

	try {
		nc.unbind(ncName);
	}
	catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}

	System.out.println("Server is exiting... \n");
    }
    
    public void initPOA() {

	try {
	    poaObj = orb.resolve_initial_references("RootPOA");
	}
	catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
	    throw new RuntimeException();
	}
	
	rootPOA = POAHelper.narrow(poaObj);
	manager = rootPOA.the_POAManager();

    }
    
/*    public void initORB() {
 
	ORBContainer OC = new ORBContainer();
	OC.setORB(orb);   
	corbaObj = OC._this(orb);
	
    }
*/    
    public void initNS(String NCId, String NCKind) {

    	try {
		nsObj = orb.resolve_initial_references("NamingService");
    	}
    	catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
		throw new RuntimeException();
    	}
    	nc = NamingContextHelper.narrow(nsObj);
	
	ncName = new NameComponent[1];
	ncName[0] = new NameComponent();
	ncName[0].id = NCId;
	ncName[0].kind = NCKind;

	try {	
		nc.rebind(ncName, corbaObj);
	}
	catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }
}
