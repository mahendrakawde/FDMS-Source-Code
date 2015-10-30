package fdms.ui.struts.action;

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
import org.apache.struts.config.ModuleConfig;

import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.cache.SpeedDataCache;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.speeddata.bean.SpeedDataManagerBean;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.TableListForm;
import fdms.ui.struts.form.TableRowForm;

public class SaveTableRow extends Action {
    
    private Logger logger = Logger.getLogger(SaveTableRow.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
			HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
    	
        logger.debug("SaveTableRow action starting.");
        
        TableRowForm 		form 		= (TableRowForm) actionForm;
        ActionErrors 		errors 		= new ActionErrors();
        //ActionForward 	actionForward= forwardShowSelectedTableGlobal(mapping);
        HttpSession 		session 	= request.getSession();
        DbUserSession 		sessionUser	= SessionHelpers.getUserSession(request);
        DatabaseTransaction t 			= null;
        DbSpeedData	 		speeddata 	= null;
        
        try {
            //AppLog.trace("SaveTableRow directive:"+ form.getSubmitbutton()+ " RowID:"+form.getRowID());
            if( form.getSubmitbutton().equals("cancel") ){
                // go back to case status unless no vitals-id then show introduciton
                //return (forwardShowSelectedTableGlobal(mapping));
                // drop thru without doing anything
            } else if( form.getSubmitbutton().equals("help") ){
                return mapping.findForward("usingHelp");
            } else {
                // get data strings from session
                //tokenlist = (ArrayList)session.getAttribute("tableData");
                //AppLog.trace("SaveTableRow String List:"+tokenlist);
                //if (tokenlist==null){
                //	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.session","SaveTableRow tableData"));
                //	saveErrors(request, errors );
                //	return (new ActionForward(mapping.getInput() ));
                //}
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                
                
                // check if adding this line, denoted by rowID = -1
                if (form.getRowID()<0){
                    //AppLog.trace("SaveTableRow add processing.");
                    speeddata = new DbSpeedData();
                    speeddata.setNew();
                    speeddata.setCategory(form.getCategory());
                    speeddata.setLocale(sessionUser.getRegion());
                    speeddata.setLocationId(FormatNumber.parseInteger(form.getLocationId()));
                    t.addPersistent(speeddata);
                } else {
                    speeddata = FdmsDb.getInstance().getSpeedDataRow(t,form.getRowID());
                    if (speeddata==null){
                        //AppLog.warning("SaveTableRow unable to retrieve speed data row:"+form.getRowID());
                        errors.add(
                        		ActionErrors.GLOBAL_ERROR, 
								new ActionError(
										"error.session",
										"SaveTableRow speed data rowID:"+form.getRowID()
										)
								);
                        saveErrors(request, errors );
                        return (new ActionForward(mapping.getInput() ));
                    }
                }
                
                // update data property of speed-data row
        /*		java.util.Iterator mylist = tokenlist.iterator();
                        StringBuffer data = new StringBuffer();
                        int count=0;
                        while (mylist.hasNext()){
                                if (count>0){
                                        data.append(",");
                                }
                                data.append(mylist.next().toString());
                                count++;
                        }
                        AppLog.trace("SaveTableRow data:"+data.toString());
                        speeddata.setData(data.toString());
         */
                //AppLog.trace("SaveTableRow data:"+form.getListValue());
                StringBuffer sb = new StringBuffer();
                sb.append(FormatString.blankNullToken(form.getCol0()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol1()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol2()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol3()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol4()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol5()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol6()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol7()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol8()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol9()) + ",");
                sb.append(FormatString.blankNullToken(form.getCol10()));
                
                SpeedDataManagerBean speedDataManagerBean = new SpeedDataManagerBean();
                String speedDataValue = speedDataManagerBean
						.getSpeedDataCSVString(form.getCategory(), 
								sb.toString(), sessionUser.getDbLookup() );
                speeddata.setData(speedDataValue);
                speeddata.setSortSequence( FormatNumber.parseInteger(form.getSortOrder()));
                // finish up
                t.save();
                SpeedDataCache.getInstance().setRefresh(sessionUser.getDbLookup());
            }
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in SaveTableRow do.Perform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  SaveTableRow .doPerform. ", pe);           
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
        //Action Forward Logic
        if( !errors.isEmpty() )  {
            //AppLog.info("Process Services Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput()));
        }
        // Since we are forwarding to another ACTION, need to go through this exercise

        String returnPath = mapping.findForward("showSelectedTableGlobal").getPath();
        int periodpos = returnPath.indexOf(".do");
        returnPath = returnPath.substring(0,periodpos);
        
        ModuleConfig modconf =  mapping.getModuleConfig();
        Action finalAction = null;
        ActionMapping finalAC = (ActionMapping) modconf.findActionConfig(returnPath);
        
        try {
            Class clazz = Class.forName(finalAC.getType());
            finalAction = (Action) clazz.newInstance();
            TableListForm oldform = (TableListForm)session.getAttribute("tableListForm");
            return finalAction.execute(finalAC,oldform,request,response);
            // AppLog.trace("chaining to:"+finalAction.toString());
        } catch (Exception e) {
            // AppLog.warning("Could not find chained action: " + e.getMessage());
        	logger.error("Could not find chained action: ", e);
            return mapping.findForward("globalCancel");
        }
        
    }
}
