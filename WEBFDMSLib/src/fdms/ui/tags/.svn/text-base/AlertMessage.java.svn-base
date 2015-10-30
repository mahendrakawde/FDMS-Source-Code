/*
 * AlertMessage.java
 *
 * Created on February 3, 2005, 5:32 PM
 */

package fdms.ui.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import fdms.admin.alert.bean.AlertManagerBean;
import fdms.admin.alert.model.AlertDTO;

public class AlertMessage extends BodyTagSupport {
    
	private Logger logger = Logger.getLogger(AlertMessage.class.getName());
	
	private static final long serialVersionUID = 6599151778134454094L;
	public static String MESSAGE_TYPE_REBOOT = "R";
    public static String MESSAGE_TYPE_MESSAGE = "M";
    private String messageType = MESSAGE_TYPE_MESSAGE;
    private String backgroundColor = "#FF0000";
    private String fontColor = "#FFFFFF";
    
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
    
    public int doStartTag() throws JspException {
        AlertDTO alert = null;
        StringBuffer html = new StringBuffer();
        
        if (messageType.equals(MESSAGE_TYPE_REBOOT)) alert = AlertManagerBean.ALERT_REBOOT;
        else alert = AlertManagerBean.ALERT_MESSAGE;
        
        if ((alert != null)
				&& (((alert.getViewable().equals("Y") && (messageType
						.equals(MESSAGE_TYPE_MESSAGE)))) || (messageType
						.equals(MESSAGE_TYPE_REBOOT)))) {
        		
            int seconds = 0;
                           
            html.append("<div style=\"padding: 5px;\" id=\"alertMessage_" + messageType + "\">");
            html.append("  <table border=\"0\" cellspacing=\"0\" ");
            html.append("cellpadding=\"0\" align=\"center\">\n");
            html.append("    <tr>\n");
            html.append("      <td style=\"border: solid 1px #990000; ");
            html.append("background-color: " + backgroundColor + "; color: " + fontColor + "; ");
            html.append("padding: 5px; font-family: Arial; font-size: 12px;\">");            
            
            if (alert.getMessageType().equals(MESSAGE_TYPE_MESSAGE)) {
                html.append(alert.getMessage());
            } else {
                seconds = new Long((alert.getEndTime() - System.currentTimeMillis()) / 1000).intValue();
                int hours = (int) seconds/3600;                
                int minutes = (int) (seconds % 3600) / 60;
                int sec = (int) (seconds % 3600 % 60);
                String hrStr = (hours < 10) ? "0" + Integer.toString(hours) : Integer.toString(hours);
                String minStr = (minutes < 10) ? "0" + Integer.toString(minutes) : Integer.toString(minutes);
                String secStr = (sec < 10) ? "0" + Integer.toString(sec) : Integer.toString(sec);
            
                html.append(alert.getMessage());
                html.append(" <span id=\"clock\" style=\"font-weight: bold;\">");
                html.append(hrStr + ":" + minStr + ":" + secStr + "</span>");
            }
            
            html.append("</td>\n");
            html.append("    </tr>\n");
            html.append("  </table>\n");
            html.append("</div>\n");
            
            if (alert.getMessageType().equals(MESSAGE_TYPE_REBOOT)) {
                                
                html.append("<script type=\"text/javascript\">\n");
                html.append("function newXMLHttpRequest() {\n");
                html.append(" var xmlreq = false;\n");
                html.append(" if (window.XMLHttpRequest) {\n");
                html.append("  xmlreq = new XMLHttpRequest();\n");
                html.append(" } else if (window.ActiveXObject) {\n");
                html.append("    try {\n");
                html.append("      xmlreq = new ActiveXObject(\"Msxml2.XMLHTTP\");\n");
                html.append("    } catch (e1) {\n");
                html.append("      try {\n");
                html.append("        xmlreq = new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
                html.append("      } catch (e2) {\n");
                html.append("    }\n");
                html.append("   }\n");
                html.append(" }\n");
                html.append("return xmlreq;\n");
                html.append("}\n\n");
                html.append("function getReadyStateHandler(req, responseXmlHandler) {\n");
                html.append("  return function () {\n");
                html.append("    // If the request's status is \"complete\"\n");
                html.append("    if (req.readyState == 4) {\n");
                html.append("      // Check that we received a successful response from the server\n");
                html.append("      if (req.status == 200) {\n");
                html.append("        // Pass the XML payload of the response to the handler function.\n");
                html.append("        responseXmlHandler(req.responseXML);\n");
                html.append("      } else {\n");
                html.append("        // An HTTP problem has occurred\n");
                html.append("        alert(\"HTTP error \"+req.status+\": \"+req.statusText);\n");
                html.append("      }\n");
                html.append("    }\n");
                html.append("  }\n");
                html.append("}\n\n");
                html.append("function findDOM(objectID) {\n");
                html.append("  if (document.getElementById)\n");
                html.append("    return (document.getElementById(objectID));\n");
                html.append("  else if (document.all)\n");
                html.append("    return (document.all[objectID]);\n");
                html.append("  else if (document.layers)\n");
                html.append("    return (document.layers[objectID]);\n");
                html.append("}\n\n");
                html.append("var alertMessageDiv = findDOM('alertMessage_" + messageType + "');\n");
                html.append("alertMessageDiv.style.visibility = 'hidden';\n");
                html.append("alertMessageDiv.style.display = 'none';\n");
                html.append("alertMessageDiv.style.position = 'absolute';\n\n");
                html.append("var refreshXML = true;\n");
                html.append("var sec = 0;\n");
                html.append("function parseRebootMessageXML(messageXML) {\n");                
                html.append("  sec = messageXML.getElementsByTagName(\"rebootAlert\")[0].getAttribute(\"seconds\");\n");
                html.append(" //alert('seconds ' + sec)\n");
                html.append("  if (sec > 0) {\n");
                html.append("    refreshXML = false;\n");
                html.append("    alertMessageDiv.style.visibility = 'visible';\n");
                html.append("    alertMessageDiv.style.display = 'block';\n");
                html.append("    alertMessageDiv.style.position = 'relative';\n\n"); 
                html.append("    showTime();\n");
                html.append("  }\n");
                html.append("  if (refreshXML) setTimeout(\"getMessageXML()\", 60000);\n");
                html.append("}\n\n");
                html.append("function getMessageXML() {\n");
                html.append("  var req = newXMLHttpRequest();\n");
                html.append("  req.onreadystatechange = getReadyStateHandler(req, parseRebootMessageXML);\n");
                html.append("  req.open(\"POST\", \"servlet/rebootMessageXML\", true);\n");
                html.append("  req.send(null);\n");
                html.append("}\n\n");
                html.append("setTimeout(\"getMessageXML()\", 1000);\n");
                html.append("function showTime () {\n");
                html.append("  sec = sec - 1;\n");
                html.append("  var hours = parseInt(sec/3600);\n");
                html.append("  var minutes = parseInt((sec%3600)/60);\n");
                html.append("  var seconds = parseInt(sec%3600%60);\n");
                html.append("  var timeValue = ((hours < 10) ? \"0\" : \"\") + hours;\n");
                html.append("  timeValue += ((minutes < 10) ? \":0\" : \":\") + minutes;\n");
                html.append("  timeValue += ((seconds < 10) ? \":0\" : \":\") + seconds;\n");
                html.append("  document.getElementById('clock').innerHTML = timeValue;\n");
                html.append("  if (sec > 0) setTimeout(\"showTime()\", 1000);\n");
                html.append("}\n\n");
                html.append("</script>\n");
            }
        }
        
        try {
            pageContext.getOut().write(html.toString());
        } catch (IOException e) {
        	logger.error("Error in AlertMessage : " + e.getMessage());
        }
        
        return SKIP_BODY;
    }
    
}
