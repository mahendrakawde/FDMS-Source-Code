package fdms.jsppatterns;

import javax.servlet.http.HttpServletRequest;
/**
 * Base class for JSP page actions. The function of each JSP page equates to
 * one or more commands that get processed in the execute() method.
 * Creation date: (11/1/00 4:36:01 PM)
 * @author: R. Davidson (from WDJSP chapter 9)
 */
public interface Command {
/**
 * Execute the command
 * Creation date: (11/1/00 4:39:10 PM)
 * @return java.lang.String Next JSP page to forward request to
 * @param req javax.servlet.http.HttpServletRequest
 * @exception com.aldorsolutions.webfdms.util.CommandException The exception description.
 */
String execute(HttpServletRequest req) throws com.aldorsolutions.webfdms.util.CommandException;
}
