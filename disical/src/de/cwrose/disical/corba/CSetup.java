//package de.cwrose.disical.corba;

import java.io.*;
import java.util.Properties;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;

public class CSetup {

    private static Properties props = System.getProperties();

    public static ORB orb;

    private static org.omg.CORBA.Object poaObj = null;
    private static POA rootPOA = null;
    private static POAManager manager = null;

    private static org.omg.CORBA.Object corbaObj = null;

    private static org.omg.CORBA.Object nsObj = null;
    public static NamingContext nc = null;
    public static NameComponent[] ncName;
    private static int objCount;
    
    CSetup(String[] args) {

    	props.put("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
    	props.put("org.omg.CORBA.ORBSingletonClass", "com.ooc.CORBA.ORBSingleton");
    	props.put("ooc.config", "OB.conf");

		orb = ORB.init(args, props);
    }

    public void orbRun() {

		orb.run();

    }
    
    public void initPOA() {

		try {
	    	poaObj = orb.resolve_initial_references("RootPOA");
			rootPOA = POAHelper.narrow(poaObj);
			manager = rootPOA.the_POAManager();
	    	manager.activate();
		}
		catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
			throw new RuntimeException();
		}
		catch (org.omg.PortableServer.POAManagerPackage.AdapterInactive ex) {
			throw new RuntimeException();
		}
    }

    public void setObjCount(int oc) {

		this.objCount = oc;

    }

    public void setNC(String NCId, String NCKind, int objNo) {

    	try {
			nsObj = orb.resolve_initial_references("NamingService");
    	}
    	catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
			throw new RuntimeException();
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
		}
    }
}
