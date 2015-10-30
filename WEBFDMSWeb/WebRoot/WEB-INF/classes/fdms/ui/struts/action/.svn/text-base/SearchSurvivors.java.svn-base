package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.DbSurvivorDisplay;
import fdms.ui.struts.form.SearchSurvivorsForm;

public class SearchSurvivors extends Action {

    private Logger logger = Logger.getLogger(SearchSurvivors.class.getName());

    /**
     * This method is called by the "SEARCH" button from SEARCH.JSP
     * and the SearchSurvivorsForm.
     * Note there are two forms on this JSP.
     * The 2nd form, OpenCase links to the OpenCase action.
     * This method uses the search characters entered by the user to look
     * for matching names in DbSurvivor.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {

        logger.debug("SearchSurvivors starting");
        
        SearchSurvivorsForm form = (SearchSurvivorsForm) actionForm;
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbSurvivor[] survivorList = null;
        ArrayList <OptionsList> displayList = new ArrayList <OptionsList> ();
        
        if ( "Cancel".equals(form.getDirective()) && form.isCheckWriterSearch() ) {
        	return ( mapping.findForward("showApCheckWriterJsp") );
        }
        

        // determine max number of names to display
        int maxnames = FormatNumber.parseInteger(form.getMaxLimit());
        if (maxnames < 1){
        	maxnames = 99;
        }
        
        int maxsearch = 9999;

        // Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            FdmsDb fdmsdb = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance();
            
            int locationId = FormatNumber.parseInteger(form.getLocationId());
            survivorList =
                    fdmsdb.getSurvivorsForName(
                        t,
                        form.getSearchText(),
                        locationId,
                        sessionUser.getRegion(),maxsearch);
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception. " + pe);
        } finally {
            if (t != null) t.closeConnection();
        }

        int count = 0;
        boolean checkCase = true;
        //Iterate Through result DB object and store as a Session Variable
        for (int i =0; i < survivorList.length && count < maxnames; i++) {
            DbSurvivor dbs = (DbSurvivor)survivorList[i];
            String additionalText = "";
            
            //depend on user setup display on not display the void case
            if (sessionUser.getCaseListDisplayVoided().compareToIgnoreCase("Y")==0 ) {
            	checkCase = (!dbs.getCSurvivorVoided().equals("P"));
            } else {
            	checkCase = (!dbs.getCSurvivorVoided().equals("P") && (!dbs.getCSurvivorVoided().equals("V")));
            }
            
            if (checkCase) {
                // match survivor type to types the operator wants
                if ( (form.getContractNo() && dbs.getISeqKey()== -2)
                ||  (form.getCaseCode()   && dbs.getISeqKey()== -1)
                ||  (form.getDeceased()   && (dbs.getISeqKey()== 0 || dbs.getCSurvivorRelated().equalsIgnoreCase("Deceased") || dbs.getCSurvivorRelated().equalsIgnoreCase("Self")))
                ||  (form.getInformant()  && dbs.getCSurvivorRelated().equalsIgnoreCase("Informant"))
                ||  (form.getPreneed()    && dbs.getCSurvivorRelated().equalsIgnoreCase("Preneed"))
                //||  (form.getSurvivors()  && dbs.getISeqKey() > 1)
                ||  (form.getSurvivors()  && dbs.getISeqKey() > 0 && !dbs.getCSurvivorRelated().equalsIgnoreCase("Informant"))
                ){
                    //Populate  A Display Collection For Presentation
                    DbSurvivorDisplay dssd = null;
                    dssd = new DbSurvivorDisplay(dbs);
                    count = count +1;
                    
                    if (dbs.getISeqKey()== -2) {
                    	additionalText = ", Contract#";
                    }
                    if (dbs.getISeqKey()== -1) {
                    	additionalText = ", CaseCode#";
                    }
                    
                    
                    //logger.debug("Adding : " + dssd.getId() + " to list.");
                    displayList.add(new OptionsList(dssd.getId(), dssd.getDisplay()+additionalText));
                }
            }
        }
        
        if (displayList.size() == 0) {
            displayList.add(new OptionsList(" ","No records found matching the search criteria."));
        }
        
        //Set request and scope session attributes
        request.setAttribute("fromSearch","true");
        request.setAttribute("checkWriterSearch", form.isCheckWriterSearch());
        // doesn't work request.setAttribute("searchText",form.getSearchText());
        session.setAttribute("displayList", displayList);
        
        return mapping.findForward("search");
    }
}
