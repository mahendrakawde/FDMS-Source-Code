package fdms.jsppatterns;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 *	The purpose of the Translator is that it gives the base functionality
 *	that is needed for each subclass Translator.
 *
 *	The reason for having an Translator is that it encapsulates most of the
 *	java code that would otherwise go into the jsp document.  With this
 *	encapsulation of the java code the jsp is easier to read and it allows
 *	non java programmers that only know HTML to work with the jsps.
 */
public abstract class Translator implements Serializable {
    
    private Hashtable a_htErrors = null;
    // This is here just for optimization.
    private static SimpleDateFormat c_sdfFormatter = null;
    private int nextErrorLogKey = 0;
    private Logger logger = Logger.getLogger(Translator.class.getName());
    
    // ACTION is the request tag
    public static final String ACTION = "action";
    // ACTION_NEW means to add a new persistent object
    public static final String ACTION_NEW = "new";
    // any other ACTION value is the index key for the object to be updated
    
    protected int vitalsKey;
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
    public static String buildSelect(String[][] listOfOptions, String selectedValue) {
        StringBuffer sbTemp = new StringBuffer();
        for (int i = 0; i < listOfOptions.length; i++) {
            sbTemp.append("<option value=\"" + listOfOptions[i][0] + "\"");
            if (selectedValue.equals(listOfOptions[i][0]) == true) {
                sbTemp.append(" SELECTED");
            }
            sbTemp.append(">" + listOfOptions[i][1] + "\n");
        }
        return sbTemp.toString();
    }
    /**
     * This returns a BigDecimal that it parsed out of the passed
     *	in string.  This method will throw an exception if it can
     *	not convert the String into a BigDecimal.  The exceptions
     *	message is formatted nicely so that the message can be displayed
     *	directly to a GUI interface.
     *
     * @author	Donald S. Bell
     *
     * @params	java.lang.String
     *					The string that is wanting to be parsed into a
     *					BigDecimal.
     *
     * @return	java.math.BigDecimal
     *					A new BigDecimal that was parsed out of the passed
     *					in String.
     *
     * @exception	java.lang.Exception
     *						An exception with a nicely formatted message so
     *						the message can be displayed directly to a user
     *						interface.
     *
     */
    protected static BigDecimal convertToBigDecimal(String theString) throws Exception {
        BigDecimal bdRV = null;
        if (theString != null) {
            try {
                bdRV = new BigDecimal(theString);
            }
            catch (Exception e) {
                Exception niceException = new Exception("Please enter a numeric value");
                throw niceException;
            }
        }
        return bdRV;
    }
    /**
     * Attempt to parse the string containing a dollar amount and
     * convert to an integer the way FDMS stores currency.
     * Creation date: (2/12/01 5:02:48 PM)
     * @return int
     * @param currencyString java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public static int convertToCurrency(String currencyString) throws java.lang.Exception {
        NumberFormat numform;
        DecimalFormat decform = null;
        // Check for '$' currency symbol. Must be present for parse() to work
        if (currencyString.indexOf('$')!= 0)
            currencyString = '$'+currencyString;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        // Reformat amount to currency
        Number result = decform.parse(currencyString);
        
        return (int)(result.doubleValue() * 100);
    }
    /**
     *
     *
     * @author	Donald S. Bell
     *
     * @params
     *
     * @return
     *
     * @exception
     */
    protected static java.util.Date convertToDate(String theString) throws Exception {
        java.util.Date datRV = null;
        if (theString != null) {
            try {
                datRV = getDateFormatter().parse(theString);
            }
            catch (Exception e) {
                Exception niceException = new Exception("Please enter a valid date like mm/dd/yyyy");
                throw niceException;
            }
        }
        // Now make sure the date is after 1990.
        java.util.Date dateMin = getDateFormatter().parse("1/1/1990");
        if (datRV.before(dateMin) == true) {
            throw new Exception("Date must be after 1/1/1990");
        }
        return datRV;
    }
    /**
     * Parse and convert a MM/DD/YY date to the style used in FDMS
     * which is MMDDYYYY
     *
     * @params String dateToParse
     *
     * @returns String dateMMDDYYYY
     *
     * @exception
     */
    public static String convertToDateMMDDYYYY(String theString) throws Exception {
        String mydate = null;
        java.util.Date datRV = null;
        if (theString != null) {
            try {
                datRV = getDateFormatter().parse(theString);
                SimpleDateFormat mmddyyyy = new SimpleDateFormat("MMddyyyy");
                mmddyyyy.setLenient(false);
                mydate = mmddyyyy.format(datRV);
            }
            catch (Exception e) {
                Exception niceException = new Exception("Please enter a valid date like mm/dd/yyyy");
                throw niceException;
            }
        }
        // Now make sure the date is after 1800.
        java.util.Date dateMin = getDateFormatter().parse("1/1/1800");
        if (datRV.before(dateMin) == true) {
            throw new Exception("Date must be after 1/1/1800");
        }
        
        return mydate;
    }
    /**
     * This returns a int that it parsed out of the passed in
     *	string.  This method will throw an exception if it can
     *	not convert the String into a int.  The exceptions
     *	message is formatted nicely so that the message can
     *	be displayed directly to a GUI interface.
     *
     * @author	Donald S. Bell
     *
     * @params	java.lang.String
     *					The string that is wanting to be parsed into
     *					a int.
     *
     * @return	java.lang.int
     *					A new int that was parsed out of the passed
     *					in String.
     *
     * @exception	java.lang.Exception
     *						An exception with a nicely formatted message so
     *						the message can be displayed directly to a user
     *						interface.
     *
     */
    protected static int convertToInteger(String theString) throws Exception {
        int iRV = 0;
        if (theString != null) {
            try {
                iRV = Integer.parseInt(theString);
            }
            catch (Exception e) {
                Exception niceException = new Exception("Please enter a numeric value");
                throw niceException;
            }
        }
        return iRV;
    }
    public static String displayErrors(Hashtable errors) {
        StringBuffer sbRV = new StringBuffer();
        if (errors != null && errors.isEmpty() == false) {
            sbRV.append("<font color=red>");
            String key;
            Enumeration enuTemp = errors.keys(); // was elements();
            while (enuTemp.hasMoreElements() == true) {
                key = (String) enuTemp.nextElement();
                sbRV.append("<li>" + key +": "+(String)errors.get(key) +"</li>");
            }
            sbRV.append("</font>");
        }
        return sbRV.toString();
    }
    /**
     *	This will return a String value of the passed in float.
     */
    public static String format(float value) {
        return Float.toString(value);
    }
    /**
     *	This will return a String value of the passed in int.
     */
    public static String format(int value) {
        return Integer.toString(value);
    }
    /**
     *	This will return a String value of the passed in int.
     */
    public static String format(long value) {
        return Long.toString(value);
    }
    /**
     *	This returns a String for the passed in String.
     *	If the passed in String is null it returns a ""
     *	otherwise it just returns the passed in String.
     */
    public static String format(String string) {
        return string != null ? string : "";
    }
    /**
     *	This returns a String for the passed in String.
     *	If the passed in String is null it returns a ""
     *	otherwise it returns the passed in String truncated to maxlength.
     */
    public static String format(String string, int maxlength) {
        if (string==null || maxlength<1  || string.length()<1)
            return "";
        
        if ( maxlength>string.length())
            return string;
        
        return string.substring(0,maxlength);
    }
    public static String format(BigDecimal bigDecimal) {
        return bigDecimal != null ? bigDecimal.toString() : "";
    }
    public static String format(Date date) {
        String sRV = null;
        if (date != null) {
            sRV = getDateFormatter().format(date);
        }
        else {
            sRV = new String();
        }
        return sRV;
    }
    /**
     *	This will return a String value of the passed in short.
     */
    public static String format(short value) {
        return Integer.toString((int)value);
    }
    /**
     * Return HTML check box code
     * Creation date: (12/28/00 12:13:14 PM)
     * @return java.lang.String
     * @param yn char Y=Yes/Checked
     */
    public static String formatCheckBox(byte yn) {
        if (yn == 'Y' || yn == 'y')
            return "checked";
        
        return "";
    }
    /**
     * Return String "Male" or "Female" from character parameter
     * Creation date: (12/28/00 12:13:14 PM)
     * @return java.lang.String
     * @param yn char M=Male F=Female
     */
    public static String formatMaleFemale(byte yn) {
        if (yn == 'M' || yn == 'm')
            return "Male";
        if (yn == 'F' || yn == 'f')
            return "Female";
        
        return "";
    }
    /**
     *	This returns a String formatted as MM/DD/YYYY.
     *   The passed in string must be 8 characters long
     *	If the passed string is not 8 charactes long,
     *   it is returned instead of the formatted string.
     */
    public static String formatMMDDYYYY(String inDate) {
        StringBuffer outDate = new StringBuffer(10);
        if (inDate == null) return "";
        if (inDate.length()<8) return inDate;
        outDate.append(inDate.charAt(0) );
        outDate.append(inDate.charAt(1) );
        outDate.append("/");
        outDate.append(inDate.charAt(2) );
        outDate.append(inDate.charAt(3) );
        outDate.append("/");
        outDate.append(inDate.charAt(4) );
        outDate.append(inDate.charAt(5) );
        outDate.append(inDate.charAt(6) );
        outDate.append(inDate.charAt(7) );
        
        return outDate.toString();
    }
    /**
     * Return String "yes" or "no" from character parameter
     * Creation date: (12/28/00 12:13:14 PM)
     * @return java.lang.String
     * @param yn char Y=Yes
     */
    public static String formatYesNo(byte yn) {
        if (yn == 'Y' || yn == 'y')
            return "Yes";
        
        return "No";
    }
    /**
     *	This returns the SimpleDateFormat that is used by
     *	the format(Date) and convertToDate(String) methods.
     */
    protected static SimpleDateFormat getDateFormatter() {
        if (c_sdfFormatter == null) {
            c_sdfFormatter = new SimpleDateFormat("MM/dd/yyyy");
            c_sdfFormatter.setLenient(false);
        }
        return c_sdfFormatter;
    }
    /**
     *	This returns a Hashtable of logged errors in the Translator.
     *	The Translator stores all the errors that are going to be
     *	displayed back to the web site user.
     */
    public Hashtable getErrors() {
        return a_htErrors;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/22/99 2:51:55 PM)
     * @return int
     */
    public String getNextErrorLogKey() {
        return new Integer(nextErrorLogKey++).toString();
    }
    /**
     * Convenience method that returns empty string instead of NULL for
     * parameters not in the request.
     * from WDJSP book chapter 11.4 (Sticky Widgets)
     * Creation date: (11/1/00 2:51:55 PM)
     * @return String parameter value or empty string
     */
    public String getParam(HttpServletRequest request, String param) {
        if (request.getParameter(param) == null)
            return "";
        else
            return request.getParameter(param);
    }
    /**
     * For parameters with multiple values assigned. Returns one string with
     * commas separating values.
     * From WDJSP book chapter 11.4 (Sticky Widgets)
     * Creation date: (11/1/00 2:51:55 PM)
     * @return String parameter values or empty string
     */
    public String getParamValues(HttpServletRequest request, String param) {
        String values[] = request.getParameterValues(param);
        if (values == null) return "";
        int count = values.length;
        switch (count) {
            case 1:
                return values[0];
            default:
                StringBuffer result = new StringBuffer(values[0]);
                int stop = count - 1;
                if (stop > 1) result.append(", ");
                for (int i = 1; i < stop; ++i) {
                    result.append(values[i]);
                    result.append(", ");
                }
                result.append(" and ");
                result.append(values[stop]);
                return result.toString();
        }
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/7/00 11:49:52 AM)
     * @return int
     */
    public int getVitalsKey() {
        return vitalsKey;
    }
    public static String highLightErrors(String key, Hashtable errors) {
        String sRV = new String();
        if(errors != null && errors.containsKey(key) == true) {
            sRV = "<font color=red>**</font>";
        }
        return sRV;
    }
    /**
     * Checks for matching value of request to "test"
     * from WDJSP book chapter 11.4 (Sticky Widgets)
     * Creation date: (11/1/00 2:51:55 PM)
     * @return String "checked" for HTML input checkbox statement
     */
    public String isChecked(HttpServletRequest request,
    String param, String test) {
        if (requestContains(request, param, test))
            return "checked";
        else
            return "";
    }
    /**
     * Determines if "test" value is the currently selected one in the request
     * from WDJSP book chapter 11.4 (Sticky Widgets)
     * Creation date: (11/1/00 2:51:55 PM)
     * @return String "selected" or empty string
     */
    public String isSelected(HttpServletRequest request,
    String param, String test) {
        if (requestContains(request, param, test))
            return "selected";
        else
            return "";
    }
    /**
     *	This logs an Exception without an error id into the Facade's
     *	Error Hashtable.  This method will first try to put the
     *	Exceptions message into the hashtable, but if it does not
     *	have a message then it will call the Exception's toString().
     *
     *	@params	java.lang.Exception
     *					The Exception that is wanting to be logged into
     *					the Error Hashtable.
     */
    public void logError(Exception exception) {
        logError(this.getNextErrorLogKey(), exception);
    }
    /**
     *	This logs a String message without an error id into the
     * Translator's Error Hashtable.
     */
    public void logError(String message) {
        logError(this.getNextErrorLogKey(), message);
    }
    /**
     *	This logs an Exception with an error id into the Translator's
     *	Error Hashtable.  This method will first try to put the
     *	Exceptions message into the hashtable, but if it does not
     *	have a message then it will call the Exception's toString().
     *
     *	@params	java.lang.String
     *					The Id that this error should be stored under in
     *					the Error Hashtable.
     *
     *	@params	java.lang.Exception
     *					The Exception that is wanting to be logged into
     *					the Error Hashtable.
     */
    public void logError(String errorId, Exception exception) {
        
        if (exception.getMessage() != null) {
            logError(errorId, exception.getMessage());
        }
        else {
            logError(errorId, exception.toString());
        }
    }
    /**
     *	This logs a String message with an error id into the Translator's
     *	Error Hashtable.
     *
     *	@params	java.lang.String
     *					The Id that this error should be stored under in
     *					the Error Hashtable.
     *
     *	@params	java.lang.String
     *					The error message that is wanting to be logged into
     *					the Error Hashtable.
     */
    public void logError(String errorId, String errorMessage) {
        Hashtable htErrors = getErrors();
        // Make sure that there is a hashtable.
        if (htErrors == null) {
            // Since there  was not then create on.
            htErrors = new Hashtable();
            setErrors(htErrors);
        }
        // Log the error into the Hashtable.
        htErrors.put(errorId, errorMessage);
        //AppLog.setDefaultLoggingLevel();
        //AppLog.info("UI Error:"+errorId+";"+errorMessage);
    }
    /**
     *	This logs a Throwable without an error id into the Translator's
     *	Error Hashtable.  This method will first try to put the
     *	Throwable's message into the hashtable, but if it does not
     *	have a message then it will call the Throwable's toString().
     *
     *	@params	java.lang.Throwable
     *					The Throwable that is wanting to be logged into
     *					the Error Hashtable.
     */
    public void logError(Throwable throwable) {
        if (throwable.getMessage() != null) {
            logError(getNextErrorLogKey(), throwable.getMessage());
        }
        else {
            logError(this.getNextErrorLogKey(), throwable.toString());
        }
    }
    /**
     *	Debugging method to list headers and params in request
     *	The headers are logged to the AppLog
     *
     *	@params	javax.servlet.http.HttpServletRequest
     *					This is the request object that has the submitted form.
     */
    public static void logRequestParams(HttpServletRequest request){
        
    	Logger logger = Logger.getLogger(Translator.class.getName());
    	
        Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            logger.debug(name+"="+ request.getHeader(name));
        }
        
        //
        // The query property returns form values.
        //
        e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            String[] paramValues = request.getParameterValues(name);
            if (paramValues.length == 1){
            	logger.debug(name+"="+paramValues[0]);
            }
            if (paramValues.length > 1){
                for (int i=1; i<paramValues.length; i++){
                	logger.debug(name+"="+ paramValues[i]);
                }
            }
        }
    }
    /**
     *	This gives a nice central location for the parsing of the values out of an
     * HTML form or query string.
     * 12/12/00 RD Changed to return empty string instead of null
     */
    protected static String parseString(HttpServletRequest request, String parameterId) {
        String sTemp[];
        sTemp = request.getParameterValues(parameterId);
        return sTemp != null ? sTemp[0].trim() : "";
    }
    protected static String[] parseStringArray(HttpServletRequest request, String parameterId) {
        return request.getParameterValues(parameterId);
    }
    /**
     *	This method should handle the pulling out of the values
     *	from the submitted form.  It should place these values
     *	inside of the Translator's internal holding variables.
     *
     *	@params	javax.servlet.http.HttpServletRequest
     *					This is the request object that has the submitted form.
     */
    abstract public void processForm(HttpServletRequest request);
    /**
     * Determins wheter or not a specific value has been specified
     *  for a request parameter.
     * from WDJSP book chapter 11.4 (Sticky Widgets)
     * Creation date: (11/1/00 2:51:55 PM)
     * @return boolean true if request does contain specified "param"
     */
    public boolean requestContains(HttpServletRequest request,
    String param, String test) {
        String rp[] = request.getParameterValues(param);
        if (rp == null)
            return false;
        for (int i=0; i < rp.length; i++)
            if (test.equals(rp[i]))
                return true;
        return false;
    }
    public void setErrors(Hashtable errors) {
        a_htErrors = errors;
        if (errors == null)
            this.setNextErrorLogKey(0);
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/22/99 2:51:55 PM)
     * @param newNextErrorLogKey int
     */
    public void setNextErrorLogKey(int newNextErrorLogKey) {
        nextErrorLogKey = newNextErrorLogKey;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/7/00 11:49:52 AM)
     * @param newVitalsKey int
     */
    public void setVitalsKey(int newVitalsKey) {
        vitalsKey = newVitalsKey;
    }
}
