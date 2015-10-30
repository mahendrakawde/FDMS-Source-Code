package fdms.util.resin;

/*
 * ResinPing.java
 *
 * Created on December 17, 2004, 8:38 AM
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 *
 * @author  Guadalupe Garcia
 */

public final class ResinServiceMonitor extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5897146022443562094L;
	private Logger logger = Logger.getLogger(ResinServiceMonitor.class.getName());
    
    /**
     * @param args the command line arguments
     */

    public void service(ServletRequest request, ServletResponse response)
        throws ServletException, IOException {  
           
        logger.debug("Executing ResinServiceMonitor");
        
        URL url = null;
        URLConnection conn = null;        
        String emails = getServletConfig().getInitParameter("emails");
        String smtp = getServletConfig().getInitParameter("smtp");
        
        try {
            
            String urls = getServletConfig().getInitParameter("urls");
            logger.debug("Values string : " + urls);
            StringTokenizer st = new StringTokenizer(urls, ",");
            
            while (st.hasMoreTokens()) {
                url = new URL(st.nextToken());
                System.out.println("URL " + url);
                
                try {
                    conn = url.openConnection();
                    conn.setDoInput(true);
                    conn.setDoOutput(false);
                    conn.connect();
                    
                    String status = conn.getHeaderField(null);
                    logger.debug("Status : " + status);
                } catch (IOException e) {
                    logger.error("Error in connecting : " + e);
                    logger.error("URL Trying to connect to : " + conn.getURL());
                    
                    sendAlert(conn.getURL().toString(), emails, smtp);
                }
            }
            
        } catch (MalformedURLException e) {
            logger.error("URL Error in reading in values : ", e);
        } catch (Exception e) {
            logger.error("Error in reading in values : ", e);
        }
    }
    
    public void sendAlert(String url, String emails, String smtp) {
        
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtp);
            Session session = Session.getDefaultInstance(props, null);
            
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("guad@fx-ds.com"));            
            msg.setSubject("WEBFDMS Server Not Running");            
            msg.setSentDate(new Date());
            msg.setText("Unable to connect to " + url);
            msg.setFlag(Flags.Flag.FLAGGED,true);
            
            StringTokenizer st = new StringTokenizer(emails, ",");
            InternetAddress[] email = new InternetAddress[st.countTokens()];
            int i = 0;            
            while (st.hasMoreTokens())
                email[i++] = new InternetAddress(st.nextToken());
            msg.setRecipients(Message.RecipientType.TO, email);
            
            Transport.send(msg);
            
        } catch (AddressException e) {
            logger.error("AddressException in sendAlert() : ", e);
        } catch (MessagingException e) {
            logger.error("MessagingException in sendAlert() : ", e);
        } catch (Exception e) {
            logger.error("Exception in sendAlert() : ", e);
        }
                
    }
    
}