/*
 * Created on Mar 29, 2006
 */
package fdms.dwr.speeddata;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.aldorassist.webfdms.dao.ZipLookupDAO;
import com.aldorsolutions.webfdms.common.Constants;


/**
 * @author Ranando
 *
 * This file contains the code for the AJAX ZipLookup class.
 */
public class ZipLookup {

	final private static Logger logger = Logger.getLogger(ZipLookup.class.getName());
	
	public String[] getByZipCode(String zip, String fieldName) {
		String[] retval = null;
		
		WebContext wctx = WebContextFactory.get();
		HttpServletRequest request = wctx.getHttpServletRequest();
		
		HttpSession session = request.getSession();
		Locale locale = (Locale) session.getAttribute(Constants.INTERNATIONAL_LOCALE);
		
		ZipLookupDAO zipLookup = new ZipLookupDAO();
		ArrayList <String> list = zipLookup.getByZipCode(zip, locale.getCountry());
		
        retval = new String[list.size() + 1];
		retval[0] = fieldName;

		for (int i = 0; i < list.size(); i++) {
			retval[i + 1] = list.get(i);
		}
            
        return retval;
	}
	
}
