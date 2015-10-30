package com.aldorsolutions.webfdms.util;

/**
 * Holds an FDMS error condition. Btrieve or other run time error
 * Creation date: (7/10/00 11:10:56 AM)
 * @author: 
 */
import java.io.FileWriter;
import java.util.Date;
public class FdmsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7212160989750517249L;
	private int btrvStatus = 0;
/**
 * FdmsException constructor comment.
 */
public FdmsException() {
	super();
}
/**
 * FdmsException constructor comment.
 * @param s java.lang.String
 */
public FdmsException(String s) {
	super(s);
}
/**
 * Insert the method's description here.
 * Creation date: (7/10/00 11:16:25 AM)
 * @return int
 */
public int getBtrvStatus() {
	return btrvStatus;
}
/**
 * Insert the method's description here.
 * Creation date: (7/10/00 11:16:25 AM)
 * @param newBtrvStatus int
 */
public void setBtrvStatus(int newBtrvStatus) {
	btrvStatus = newBtrvStatus;
}
/**
 * Write msg to console and append to disk file
 * @param msg java.lang.String
 */
public void write(java.lang.String  msg) {
	System.out.println(msg);
	try {
		java.text.SimpleDateFormat myFormat= new java.text.SimpleDateFormat("yyyy/mm/dd,H:mm");
		String timeStamp = myFormat.format(new Date());

		FileWriter log = new FileWriter("\\fdmslog.csv",true);
		log.write(timeStamp+","+msg+","+getBtrvStatus()+"\n");
		log.flush();
		log.close();
	}
	catch(java.io.IOException e){
		System.err.println("Error opening log file in LogMessage");
		System.err.println(e);
		System.err.println("Original error:"+msg+getBtrvStatus());
	}
}
}
