package com.aldorsolutions.webfdms.util;

import org.apache.log4j.Logger;

/**
 * Container for the call to the Native method and the static System.loadLibrary().
 * Necessary to separate it so it can go in a jar file
 * within the system class-path. This prevents it from
 * being reloaded when Resin resets -- which causes
 * an UnsatisfiedLinkError
 * Creation date: (9/25/2002 10:40:14 AM)
 * @author:
 */
public class NativeCall {
    
  private static Logger logger = Logger.getLogger(NativeCall.class.getName());
	
  static{
  	  String dll = "FdmsCrpe";
      System.loadLibrary(dll);
  }
  
  private static NativeCall instance = new NativeCall();
/**
 * NativeCall constructor comment.
 */
  
private NativeCall() {
  super();
}
/**
 * To make this class a "singleton" where this is only one instance for the whole session,
 * we make the constructor private and this method provides the only access to the class.
 * Creation date: (3/24/2003 11:07:50 AM)
 * @return fdms.reporting.crystal.NativeCall
 */
public static NativeCall getInstance() {
  return instance;
}
/**
 * This main method allows testing the CRPE.DLL Wrapper from a command line.
 * @param args an array of command-line arguments
 * parameters:
 * 1 report name
 * 2 serverDSN
 * 3 database name
 * 4 user name
 * 5 password
 * 6 report descr
 * 7 selection formula
 * 8 export disk file name
 */
public static void main(java.lang.String[] args) {
  if (args.length < 8){
	logger.debug("Parameters: rporttName, serverDSN, dbName, userName, password, rptDescr, selectionFormula, exportFileName");
    return;
  }

  NativeCall rpt = new NativeCall();

  rpt.prepareReport(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);

}
/**
 * Call Native Method to generat a Crystal Report
 * Creation date: (2/13/2002 10:40:43 AM)
 * @param rptName java.lang.String
 * @param server java.lang.String
 * @param dbName java.lang.String
 * @param userName java.lang.String
 * @param password java.lang.String
 * @param rptDescription java.lang.String
 * @param selectionFormula java.lang.String
 * @param exportFileName java.lang.String
 */
private native void prepareReport(String rptName, String server, String dbName, String userName, String password, String rptDescr, String selectionFormula, String exportFileName);
/**
 * Call method that uses Native Method to generat a Crystal Report
 * Creation date: (3/24/2003 10:40:43 AM)
 * @param rptName java.lang.String
 * @param server java.lang.String
 * @param dbName java.lang.String
 * @param userName java.lang.String
 * @param password java.lang.String
 * @param rptDescription java.lang.String
 * @param selectionFormula java.lang.String
 * @param exportFileName java.lang.String
 */
public synchronized void previewReport(String rptName, String server, String dbName, String userName, String password, String rptDescr, String selectionFormula, String exportFileName){
  prepareReport(rptName, server, dbName, userName, password, rptDescr, selectionFormula, exportFileName);
}
}
