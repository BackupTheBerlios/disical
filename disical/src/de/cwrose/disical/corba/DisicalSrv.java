import java.io.*;
import java.util.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;


public class DisicalSrv {

	public static void main(String[] args) {

		CSetup disisetup = new CSetup(args);

		DisicalDate dates = new DisicalDate();

		disisetup.setObjCount(3);
		disisetup.setNC(DisicalUser.Id, DisicalUser.Kind, 0);
		disisetup.setNC(DisicalServer.Id, DisicalServer.Kind, 1);
		disisetup.setNC(DisicalDate.Id, DisicalDate.Kind, 2);

		disisetup.initPOA();
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
