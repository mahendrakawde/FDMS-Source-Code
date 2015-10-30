package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.model.SpeedDataRuleDTO;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.cache.SpeedDataCache;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.speeddata.bean.SpeedDataManagerBean;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.TableContentsForm;
import fdms.ui.struts.form.TableRowForm;

public class ShowTableRow extends Action {
    
    private Logger logger = Logger.getLogger(ShowTableRow.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(
    	ActionMapping mapping,
	    ActionForm actionForm,
	    HttpServletRequest request, 
		HttpServletResponse response)
	    throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        TableContentsForm form = (TableContentsForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        // Retrieve user information
        
        logger.debug("ShowTableRow doPerform");
        logger.debug("ShowTableRow SubmitButton:"+form.getSubmitbutton());
        logger.debug("ShowTableRow Category:"+form.getCategory());        
        
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);

        // Get row selected by user
        int rowid=-1;
        String[] rowID = form.getSpeedDataContents();
        if ((rowID != null) && (rowID.length > 0)) {
			try {
				rowid = Integer.parseInt(rowID[0]);
			} catch (NumberFormatException e) {
				// unable to parse int from string
			}
		}
        
        try {
            if (!form.getSubmitbutton().equalsIgnoreCase("add")){
                logger.debug("ShowTableRow Selected:"+rowid);
                if (rowid<1){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
                    formErrors.add("speedDataContents");
                }
            }
        } catch (NumberFormatException e){
            logger.debug("ShowTableRow: row selected number format error");
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
            formErrors.add("speedDataContents");
        }
        
        // Report any errors we have discovered back to the original form
        if (!errors.isEmpty()) {
            logger.debug("ShowTableRow: Invoking forward mapping getInput");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput()));
        }
        
        // Instantiate form and collection needed for jsp
        TableRowForm 	rowform = new TableRowForm();
        //ArrayList		tokenlist= new ArrayList();
        // try just a simple String[]
        OptionsList[] tokenlist = new OptionsList[15];
        int tokenindex = 0;
        DbSpeedData		row;
        // Retrieve rows for category
        DatabaseTransaction t=null;
        logger.debug("TableContentsForm rowId:"+rowid);
        try {
         	
        	rowform.setLocationId(form.getLocationId());
            rowform.setCategory(form.getCategory());
            logger.debug("ShowTableRow Category:"+rowform.getCategory());
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            if (form.getSubmitbutton().equalsIgnoreCase("change")){
                // Get row data
                row = FdmsDb.getInstance().getSpeedDataRow(t,rowid);
                logger.debug("SpeedData row data:"+row.getData());
                rowform.setRowID(	rowid);
                populateColumns(row.getData(), rowform);
                rowform.setListValue(row.getData());
                rowform.setSortOrder(String.valueOf(row.getSortSequence()));
                java.util.StringTokenizer parser = new java.util.StringTokenizer(row.getData(),",");
                // if empty, make sure there is at least one empty string
                
                SpeedDataManagerBean speedDataManagerBean = new SpeedDataManagerBean();
                SpeedDataRuleDTO speedDataRuleDTO = 
                	speedDataManagerBean.getSpeedDataRule(
                			form.getCategory(),
                			sessionUser.getDbLookup() );
                
                ArrayList labels = new ArrayList();
                
                if (speedDataRuleDTO != null) {
                	labels.addAll( speedDataRuleDTO.getLabels() );
                }
                
                if ( labels.size() == 0 ) {
                	labels.add("No Speed Data Rules Label");
                	request.setAttribute("speedDataColumns", labels);
                } 
                
                request.setAttribute("speedDataColumns", labels );
                
                
                if (!parser.hasMoreTokens()){
                    //tokenlist.add(new fdms.ui.struts.OptionsList(" "));
                    tokenlist[0]=new OptionsList("      ");
                }
                while (parser.hasMoreTokens()){
                    String atoken = parser.nextToken();
                    logger.debug("SpeedData cols: "+atoken);
                    atoken = atoken.trim();
                    tokenlist[tokenindex]= new OptionsList(atoken);
                    tokenindex++;
                    //tokenlist.add(new fdms.ui.struts.OptionsList( atoken));
                }
            }
            else if (form.getSubmitbutton().equalsIgnoreCase("add")){
                logger.debug("ShowTableRow Add.");
                rowform.setRowID(-1);
                SpeedDataManagerBean speedDataManagerBean = new SpeedDataManagerBean();
                SpeedDataRuleDTO speedDataRuleDTO = 
                	speedDataManagerBean.getSpeedDataRule(
                			form.getCategory(),
                			sessionUser.getDbLookup() );
                
                ArrayList labels = new ArrayList();
                
                if (speedDataRuleDTO != null) {
                	labels.addAll( speedDataRuleDTO.getLabels() );
                }
                
                if ( labels.size() == 0 ) {
                	labels.add("No Speed Data Rules Label");
                	request.setAttribute("speedDataColumns", labels);
                } 
                
                request.setAttribute("speedDataColumns", labels );
                
                //tokenlist.add (new fdms.ui.struts.OptionsList(" "));
                tokenlist[0]=new OptionsList("       ");
            }
            else if (form.getSubmitbutton().equalsIgnoreCase("delete")){
            	logger.debug("executing delete action");
            	
                if ((rowID != null) && (rowID.length > 0)) {
                	logger.debug("number of rowids deleting : " + rowID.length);
                	
                	for (int i = 0; i < rowID.length; i++){
                		
	                	try {
	                		rowid = Integer.parseInt(rowID[i]);
		            		logger.debug("rowid deleting : " + rowid );
			                row = FdmsDb.getInstance().getSpeedDataRow(t,rowid);
			                row.remove();
			                                		
	                	} catch(NumberFormatException e) {
	                		// unable to parse int from string
	                	}
                	}
                }           

                t.save();
                SpeedDataCache.getInstance().setRefresh(sessionUser.getDbLookup());
                return mapping.findForward("showSelectedTableGlobal");
                
            } else {
                // invalid action
                logger.debug("ShowTableRow No Action Found!");
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
            }
            logger.debug("ShowTableRow finished. Row:"+rowform.getRowID());

        } catch (PersistenceException e){
            logger.error("ShowTableRow Persistence Exception. " + e);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",e.getCause()));            
        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        if( !errors.isEmpty() )  {
            logger.debug("ShowTableRow Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput()));
        }
        
        // Remove collections and form for previous page
        // needed for looping back after saving a row
        // move to a "cancel" section of this action
        //session.removeAttribute("displayContents");
        //session.removeAttribute("tableContentsForm");
        
        // store collections in request
        session.setAttribute("tableData",tokenlist);
        // store form beans in request
        session.setAttribute("tableRowForm",rowform);
        
        return mapping.findForward("displayEditTableRow");
    }
    
    private void populateColumns(String tabData, TableRowForm form) {
    	StringTokenizer st = new StringTokenizer(tabData, ",");
    	String token = null;
    	if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol0(token);
		}
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol1(token);
		}    	
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol2(token);
		} 		
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol3(token);
		} 
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol4(token);
		} 
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol5(token);
		} 
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol6(token);
		} 
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol7(token);
		} 
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol8(token);
		} 
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol9(token);
		} 
		if (st.hasMoreTokens()) {
			token = st.nextToken();
			form.setCol10(token);
		} 		
    }
}
