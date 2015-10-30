package fdms.ui.struts.action;

import java.util.ArrayList;

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

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.dao.ObituaryFuneralSyncDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.beans.DbFuneralSyncToServicedata;
import com.aldorsolutions.webfdms.beans.DbFuneralSyncToVitals;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.display.FuneralSyncListBean;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.aldorsolutions.webfdms.util.UtilSingleton;


import fdms.ui.struts.form.FuneralSyncListForm;
import fdms.ui.struts.form.ObituaryFuneralSyncCaseForm;

public class ProcessObituaryFuneralSyncCase extends Action {

	private Logger logger = Logger.getLogger(ShowMovingCase.class.getName());
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		

		ObituaryFuneralSyncCaseForm form = (ObituaryFuneralSyncCaseForm) actionForm;
		
		HttpSession session = request.getSession();
		DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
		ActionErrors errors = new ActionErrors();
		String FUNERALSYNC_JNDI = UtilSingleton.getInstance().getFuneralSyncDBLookup();

		ObituaryFuneralSyncDAO OFSyncDao = new ObituaryFuneralSyncDAO (FUNERALSYNC_JNDI);
		
		DbFuneralSyncToVitals  	deceased 	= null;
		DbFuneralSyncToServicedata service = null;
	
		SecurityManagerBean smBean = new SecurityManagerBean();
		SecurityConfigDTO security = smBean.getSecurityConfig(sessionUser.getCompanyID());
			
			if (form.getProcessType() != null && form.getProcessType().compareToIgnoreCase("save") == 0){
				DatabaseTransaction t =null;
				try{
			        t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
					deceased = new DbFuneralSyncToVitals();
					deceased.setNew();
					
					setDeceased(deceased,form, sessionUser,errors);
					t.addPersistent(deceased);
					t.save(); //save it to get the vitalsmasterkey to insert in servicedata
					
	
					service = new DbFuneralSyncToServicedata();
					service.setNew();
					setServicedata(service,form, deceased);
					t.removePersistent(deceased); // remove it from the dbtran if not it will cause duplication problem.
					t.addPersistent(service);
					t.save();
					
					
					setObituaryFuneralSync(form,true);
					OFSyncDao.updateObituaryFuneralSync(form.getPostedYN(),form.getPostedDateTime(),form.getId());
					
				} catch(PersistenceException pe) {
					     logger.error("Persistence Exception in ProcessObituaryFuneralSyncCase.doPerform. " + pe);
					     errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
				} finally {
					     if (t != null) t.closeConnection();
				}
			}
			else if (form.getProcessType() != null && form.getProcessType().compareToIgnoreCase("delete") == 0) {
				setObituaryFuneralSync(form,false);
				OFSyncDao.updateObituaryFuneralSync(form.getPostedYN(),form.getPostedDateTime(),form.getId());
			}
			

		
		int clientId = 0;
		clientId = security.getFuneralSyncId();
        FdmsDb fdms = FdmsDb.getInstance();
    	ArrayList<FuneralSyncListBean> listBean = new ArrayList<FuneralSyncListBean>();
        listBean =fdms.getFuneralSyncCases(FUNERALSYNC_JNDI,clientId, true);
        FuneralSyncListForm funeralSyncListForm = new FuneralSyncListForm();
		funeralSyncListForm.setFuneralSyncCases(listBean);
		request.setAttribute("funeralSyncListForm",funeralSyncListForm);
		return ( mapping.findForward("showFuneralSyncListJsp") );
	}

	private void setObituaryFuneralSync(ObituaryFuneralSyncCaseForm form, boolean postToFDMS) {
		if (postToFDMS) {
			form.setPostedYN("Y");
		}
		else {
			form.setPostedYN("D");
		}
		long postedDateTime = System.currentTimeMillis();
		form.setPostedDateTime(postedDateTime);
	}

	private void setServicedata(DbFuneralSyncToServicedata service, ObituaryFuneralSyncCaseForm form, DbFuneralSyncToVitals deceased) {
		service.setCaseId(deceased.getId());
		service.setDayofweek("Tuesday");
		service.setTimeOfService(form.getServiceTime());
		
	}

	private void setDeceased(DbFuneralSyncToVitals deceased,
			ObituaryFuneralSyncCaseForm form, DbUserSession sessionUser,ActionErrors errors) {
		deceased.setDecMrMrs(form.getDecTitle());
		deceased.setDeceasedFirstName(form.getDecFirstName());
		deceased.setDeceasedMidName(form.getDecMiddleName());
		deceased.setDeceasedLastName(form.getDecLastName());
		deceased.setDeceasedFullName(form.getDecFirstName()+" "+form.getDecMiddleName()+" "+form.getDecLastName());
		deceased.setDeceasedMaidenName(form.getDecMaidenName());
		java.util.Date tDate;
		
		try {
			tDate = new java.util.Date(form.getDateOfBirth().getTime());
            deceased.setDateOfBirth(FormatDate.convertDateToMMDDYYYY(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("DateOfBirth"));
        }
		deceased.setDecBirthPlace(form.getPlaceOfBirth());
		try {
			tDate = new java.util.Date(form.getDateOfDeath().getTime());
            deceased.setDateOfDeath(FormatDate.convertDateToMMDDYYYY(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("DateOfDeath"));
        }
        try {
			tDate = new java.util.Date(form.getDateOfDeath().getTime());
            deceased.setDeathDateKey(FormatDate.convertDateToYYYYMMDD(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("DeathDateKey"));
        }
		deceased.setLocationOfDeath(form.getPlaceOfDeath());
		try {
			tDate = new java.util.Date(form.getBurialDate().getTime());
            deceased.setBurialDate(FormatDate.convertDateToMMDDYYYY(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("BurialDate"));
        }
        try {
			tDate = new java.util.Date(form.getServiceDate().getTime());
            deceased.setServiceDateKey(FormatDate.convertDateToYYYYMMDD(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ServiceDateKey"));
        }
		try {
			LocationDAO locationDao = new LocationDAO(sessionUser);
			LocationDTO location = locationDao.getLocationByFuneralSyncLocationId(form.getLocationID());
			
//			LocaleDAO localeDao = new LocaleDAO(sessionUser);
//			LocaleDTO locale = localeDao.getLocale(location.getLocaleID(), sessionUser.getDbLookup());
			deceased.setChapelLocation(location.getName());
			deceased.setLocaleNumber((int)location.getLocaleID());
			deceased.setChapelNumber((int)location.getLocationID());
		} catch (Exception e) {
            logger.error("Exception in perform() : ", e);            
        }
		deceased.setCaseCode("");
	}
	
}
