package fdms.util.resin;

/**
 * @author Guadalupe Garcia
 * Date Created: Jan 28, 2004 - 1:03:53 PM
 * Project: WEBFDMS
 * Class deletes temp report files that are dated longer than two weeks.
*/


import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public final class DeleteTempFiles extends HttpServlet {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7324022045513254757L;
	private final static Logger logger = Logger.getLogger(DeleteTempFiles.class.getName());
    
    public void service(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    	
    	logger.error("Executing DeleteTempFiles");
        
        try {
//            long currentMillisec = Calendar.getInstance().getTimeInMillis();
//            long twoWeeks = currentMillisec - (86400000L * 14L);
            
            //System.out.println("Current Millisecond value : " + currentMillisec);
            //System.out.println("Two Weeks Ago Millisecond value : " + twoWeeks);
            
            String reportDir = getServletConfig().getInitParameter("reportDir");
            //System.out.println("Report Dir : " + reportDir);
                       
            
            if (reportDir != null) {
                File tempDir = new File(reportDir);
                
                File[] files = tempDir.listFiles();
                //System.out.println("Files Found in Report Dir : " + files.length);
                
                if ((files != null) && (files.length > 0)) {
	                for (int i = 0; i < files.length; i++) {
	                	/*
	                    long millisec = files[i].lastModified();
	                    
	                    if (millisec < twoWeeks) {
	                        files[i].delete();
	                        //System.out.println("File : " + files[i].getName());
	                    }
	                    */
	                	files[i].delete();
	                }
	                
	                // get email config info from servlet config
	                String smtp = getServletConfig().getInitParameter("smtp");
	                String server = getServletConfig().getInitParameter("server");
	                String emails = getServletConfig().getInitParameter("emails");
	                StringTokenizer st = new StringTokenizer(emails,",");
	                
	                InternetAddress[] emailToArray = new InternetAddress[st.countTokens()];
	                int i = 0;
	                while (st.hasMoreTokens())
	                	emailToArray[i++] = new InternetAddress(st.nextToken());
	                
	                String subject = server + " Delete Report Temp Cron";
	                String msg = "Files deleted from " + server +  reportDir 
	                	+ " directory: " + files.length;     
	                
	                Properties props = new Properties();
	                props.put("mail.smtp.host", smtp);
	                Session session = Session.getDefaultInstance(props, null);          
	                Message message = new MimeMessage(session);
	
	                message.setSubject(subject);
	                message.setFrom(new InternetAddress("info@webfdms.com"));
	                message.setRecipients(Message.RecipientType.TO, emailToArray);
	                message.setSentDate(new java.util.Date());
	                message.setText(msg);
	
	                Transport.send(message);    
                }
            }
            
        } catch (Exception e) {
            logger.error("Error in DeleteTempFiles ", e);
        }
        
    }

}
