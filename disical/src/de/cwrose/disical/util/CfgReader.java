package de.cwrose.disical.util;

import java.io.*;
import java.util.*;

/*
 * Static singleton used to access configuration files of the disical
 * project.  As soon as CfgReader is initialized with local property
 * cfg dir LOCAL_DIR and default cfg dir MAIN_DIR it will live as long
 * as the java vm.
 * 
 * @author Stefan Plantikow <stepn@users.berlios.de>
 * @version $Revision: 1.2 $
 * @see java.lang.System#getProperty
 * 
 */
public final class CfgReader 
{
	 public final static String MAIN_DIR = "disical.cfg";
	 public final static String LOCAL_DIR = "disical.cfg.local";
	 public final static String CFG_EXT = ".properties";

	 static Hashtable cfgs;
	 static File mainDir;
	 static File localDir;

	 static 
	 {
                 /* Verstgeh ich nicht, gibt meiner Meinung nach immer null zurück, da es  
                  * Da es das System Property "disical.cfg" nicht gibt.
                  *
                  * Oder versteh ich System.getProperty falsch?
                  * Soweit ich das verstehe kann man damit nur ganz bestimmte System Properties, 
                  * wie die jvm (java.vm.name) oder das Working Directory bestimmen 
                  * (user.dir).
                  *
                  * Soweit ich weiss gibt es auch ein Config File fuer die JVM, um 
                  * solche properties zu setzen, aber dazu finde ich gerade nichts im
                  * javadoc und die Frage ist auch ob wir das wollen
                  *
                  * Ansonsten finde ich die Idee einer Zentralen Classe zum verwalten
                  * der Configuration sehr gut, auch dass man die Configuration ueber 
                  * eine statische Methode bekommt.
                  * Ich habe mir darueber auch schon gedanken gemacht und mir einige 
                  * Compliziertere möglichkeiten angschaut, ueber jndi (PASX Framework)
                  * , aber da ich da bis jetzt noch nicht so ganz durchsteige, glaube 
                  * ich langsam, das man sich damit noch mehr ärger macht als man sich 
                  * vorteile verschafft.
                  *
                  * Ein vorschlag von mir wäre noch, dem CfgReader eine Statische 
                  * Methode hinzuzufuegen, die ein fuer DiSiCal configuriertes Castor 
                  * Database Object zurückgibt. Das wuerde es ermöglichen, dass Objekte
                  * selber auf das Persistenz layer zugreifen koennen, um sich selbst 
                  * zu persistieren. Überhaupt haette man so einen einfachen zugriff 
                  * auf Castor. Das löst  meiner Erfahrung nach manche Probleme mit Castor, 
                  * insbesondere beim erzeugen des Database Objekts und dem Einlesen der
                  * xml cofigurations Dateien database.xml oder mapping.xml.
                  *
                  * Ups, eigentlich sollte das nur eine Frage zum Code werden, ich glaube
                  * ich verlege weitere Design & Planungsaktivitäten auf das Forum.
                  * bitte löscht diesen commentar einfach wieder, wenn er nicht mehr 
                  * aktuell ist.
                  *
                  * Gruss Conny
                  *
                  */
                  
                  String mainStr = System.getProperty (MAIN_DIR);
		  mainDir = new File (mainStr);

		  String localStr = System.getProperty (LOCAL_DIR);
		  if (localStr == null)
				localStr = mainStr + File.separator + "local";
		  localDir = new File (localStr);

		  checkDir (mainDir);
		  checkDir (localDir);
		  cfgs = new Hashtable ();
	 }

	 static void checkDir (File fd)
	 {
		  try 
		  {
				if (! fd.isDirectory ())  
				{
					 System.err.println (fd.getAbsolutePath () 
                                    + " is not a directory !");
					 System.exit (1);
				}
				if (! fd.canRead ())  
				{
					 System.err.println (fd.getAbsolutePath () 
                                    + " is not readable !");
					 System.exit (1);
				}
		  }
		  catch (SecurityException e)  
		  {
				System.err.println 
				("Can't acces required configuration directory: "
				 + fd.getAbsolutePath () + "(Reason: " + e.getMessage () + ")");
				System.exit (1);
		  }
	 }

    /*
     * @return Properties for configuration with name cfg
     * @throws MissingCfgException if there is no cfg file in
     * LOCAL_DIR or MAIN_DIR
     */
	 public static Properties readCfg (String cfg)
	 throws CfgMissingException
	 {
		  Properties props = (Properties) cfgs.get (cfg);
		  if (props != null)
				return props;

		  props = readProps (mainDir, cfg, null);
		  props = readProps (localDir, cfg, props);
		  
		  if (props == null)
				throw new CfgMissingException (cfg);

		  cfgs.put (cfg, props);
		  return props;
	 }

	 static Properties readProps (File parent, String cfg, Properties pred)
	 {
		  File fd = new File (parent, cfg + CFG_EXT);
		  Properties props = (pred == null) ? 
				new Properties () : new Properties (pred);

		  try 
		  {
				props.load (new FileInputStream (fd));
		  }
		  catch (IOException e)
		  {
				return pred;
		  }
		  
		  return props;
	 }  
} 

