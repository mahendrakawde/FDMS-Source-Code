package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
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
import org.apache.struts.util.LabelValueBean;

import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVisitations;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.visitation.bean.VisitationManagerBean;

import fdms.ui.struts.form.ServicesVisitationForm;


public class ShowServicesVisitation extends Action {
    
    private Logger logger = Logger.getLogger(ShowServicesVisitation.class.getName());
    
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
    		HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward = null;
        HttpSession session	= request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        ServicesVisitationForm servform = new ServicesVisitationForm();
        DbVisitations visitationDO = null;
        DatabaseTransaction t = null;
        
        VisitationManagerBean visitationManager = new VisitationManagerBean();
        
        //Check for a DbUserSessioin
        if(sessionUser==null){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        int vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        String visitationID = (String) request.getParameter("visitationID");
        
        int id = FormatNumber.parseInteger(visitationID);
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        
        servform.setVitalsMasterKey(vitalsid);
        servform.setPrivateVisitation("No");
        
        //Database Access Logic
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // get the visitations for the case if they exist
            visitationDO = visitationManager.getVisitationsByPK(id, sessionUser);
            
            //Populate The Form Bean
            if(visitationDO != null)	{
            	servform.setVisitationID(visitationDO.getId());
            	servform.setAddress(visitationDO.getAddress());
            	servform.setAddress2(visitationDO.getAddress2());
            	servform.setCity(visitationDO.getCity());
            	servform.setDate(visitationDO.getDate());
            	servform.setEndTime(visitationDO.getEndTime());
            	servform.setNotes(visitationDO.getNotes());
        		servform.setPlace(visitationDO.getPlace());
        		servform.setPrivateVisitation(visitationDO.getPrivateVisitation());
        		servform.setRoom(visitationDO.getRoom());
        		servform.setStartTime(visitationDO.getStartTime());
        		servform.setState(visitationDO.getState());
        		servform.setStatus('U');
        		servform.setZip(visitationDO.getZip());
            }
            
            FdmsDb fdmsDb = FdmsDb.getInstance();
            
            DbSpeedData[] speedDataOptions = fdmsDb.getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "Srvplace");
            
            DbSpeedData[] speedDataOptionsLocation = fdmsDb.getSpeedDataLocation(sessionUser.getDbLookup(), sessionUser.getRegion(), 
            		sessionUser.getLocationId(), "Srvplace", null);
            
            speedDataOptions = getSpeedData(speedDataOptions, speedDataOptionsLocation);

			ArrayList places = new ArrayList();
			
			for ( int i = 0; i < speedDataOptions.length; i++ ) {
				DbSpeedData speedData = (DbSpeedData) speedDataOptions[i];
				
				String str = speedData.getData();
				
				if ( str.indexOf(",") > 0 ) {
					str = str.substring(0, str.indexOf(",") );
				}
				
				places.add( new LabelValueBean(str, str) );
			}
			
			session.setAttribute("Srvplace", places);
                        
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowServices.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Add form to session
        session.setAttribute("servicesVisitation",servform);
        //Action Forward Logic
        actionForward = mapping.findForward("servicesVisitationAddChange");
        
        if( !errors.isEmpty() )  {
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        
        return  actionForward;
    }
    
    public DbSpeedData[] getSpeedData(DbSpeedData[] speedDataOptions, DbSpeedData[] speedDataOptionsLocation) {

		if (speedDataOptionsLocation.length > 0) {
			return speedDataOptionsLocation;
		} else {
			return speedDataOptions;
		}
    }
		
		
}
