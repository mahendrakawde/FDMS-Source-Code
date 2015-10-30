package fdms.jsppatterns;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This gives some base functionality that most JSP's can use.
 * 
 * @author	Donald S. Bell
 */
public class JspBase extends javax.servlet.http.HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = -5875509279302815331L;
/**
 * JspBase constructor comment.
 */
public JspBase() {
	super();
}
/**
*	This will build the print out the list of <option> tags
*	that are needed for a SELECT control.
*
*	@params java.lang.String[] A two deminsion array of all the values
*		for the options to be displayed.  The first element is the value
*		the second element is the text to be displayed.
*
*	@params java.lang.String	The value of the selected option.
*/
public static String buildSelect(String[][] listOfOptions, String selectedValue)
{
	StringBuffer sbTemp = new StringBuffer();
	for (int i = 0; i < listOfOptions.length; i++)
	{
		sbTemp.append("<option value=\"" + listOfOptions[i][0] + "\"");
		if (selectedValue.equals(listOfOptions[i][0]) == true)
		{
			sbTemp.append(" SELECTED");
		}
		sbTemp.append(">" + listOfOptions[i][1] + "\n");
	}
	return sbTemp.toString();
}
public static String displayErrors(Hashtable errors)
{
	StringBuffer sbRV = new StringBuffer();
	if (errors != null && errors.isEmpty() == false)
	{
		sbRV.append("<font color=red>");
		Enumeration enuTemp = errors.elements();
		while (enuTemp.hasMoreElements() == true)
		{
			sbRV.append("<li>" + (String) enuTemp.nextElement() + "</li>");
		}
		sbRV.append("</font>");
	}
	return sbRV.toString();
}
public static String highLightErrors(String key, Hashtable errors)
{
	String sRV = new String();
	if(errors != null && errors.containsKey(key) == true)
	{
		sRV = "<font color=red>**</font>";
	}
	return sRV;
}
}
