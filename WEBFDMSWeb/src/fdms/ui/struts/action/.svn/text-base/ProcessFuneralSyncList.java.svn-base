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

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.dao.ObituaryFuneralSyncDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
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

public class ProcessFuneralSyncList extends Action {

	private Logger logger = Logger.getLogger(ShowMovingCase.class.getName());
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		
//		FuneralSyncListForm funeralSyncListForm = new FuneralSyncListForm();
		FuneralSyncListForm funeralSyncListForm = (FuneralSyncListForm) actionForm;

		
		FuneralSyncListBean funeralSyncListBean = new FuneralSyncListBean();
		ArrayList<FuneralSyncListBean> listBean = new ArrayList<FuneralSyncListBean>();
		FuneralSyncListBean[] listBeant;
		HttpSession session = request.getSession();
		DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
		ActionErrors errors = new ActionErrors();
		String FUNERALSYNC_JNDI = UtilSingleton.getInstance().getFuneralSyncDBLookup();
		int clientId = 0;
		
		ArrayList<FuneralSyncListBean> cases = null;
		String[] submittedCases = funeralSyncListForm.getSubmitCases();
		ObituaryFuneralSyncDAO OFSyncDao = new ObituaryFuneralSyncDAO (FUNERALSYNC_JNDI);
		cases = OFSyncDao.getObituaryFuneralSync(FUNERALSYNC_JNDI,submittedCases);	
		
		DbFuneralSyncToVitals  	deceased 	= null;
		DbFuneralSyncToServicedata service = null;
	
		SecurityManagerBean smBean = new SecurityManagerBean();
		SecurityConfigDTO security = smBean.getSecurityConfig(sessionUser.getCompanyID());
		clientId = security.getFuneralSyncId();
		
		
		for (FuneralSyncListBean aCase: cases){
			
			if (funeralSyncListForm.getProcessType() != null && funeralSyncListForm.getProcessType().compareToIgnoreCase("save") == 0){
				DatabaseTransaction t =null;
				try{
			        t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
					deceased = new DbFuneralSyncToVitals();
					deceased.setNew();
					
					setDeceased(deceased,aCase,sessionUser,errors);
					t.addPersistent(deceased);
					t.save(); //save it to get the vitalsmasterkey to insert in servicedata
					
	
					service = new DbFuneralSyncToServicedata();
					service.setNew();
					setServicedata(service,aCase, deceased);
					t.removePersistent(deceased); // remove it from the dbtran if not it will cause duplication problem.
					t.addPersistent(service);
					t.save();
					
					
					setObituaryFuneralSync(aCase,true);
					OFSyncDao.updateObituaryFuneralSync(aCase);
					
				} catch(PersistenceException pe) {
					     logger.error("Persistence Exception in ProcessFuneralSyncList.doPerform. " + pe);
					     errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
				} finally {
					     if (t != null) t.closeConnection();
				}
			}
			else if (funeralSyncListForm.getProcessType() != null && funeralSyncListForm.getProcessType().compareToIgnoreCase("delete") == 0){
				setObituaryFuneralSync(aCase,false);
				OFSyncDao.updateObituaryFuneralSync(aCase);
			}
			
		}

		
		
        FdmsDb fdms = FdmsDb.getInstance();
        listBean =fdms.getFuneralSyncCases(FUNERALSYNC_JNDI,clientId, true);
		funeralSyncListForm.setFuneralSyncCases(listBean);
		request.setAttribute("funeralSyncListForm",funeralSyncListForm);
		return ( mapping.findForward("showFuneralSyncListJsp") );
	}

	private void setObituaryFuneralSync(FuneralSyncListBean aCase, boolean postToFDMS) {
		if (postToFDMS) {
			aCase.setPostedYN("Y");
		}
		else {
			aCase.setPostedYN("D");
		}
		long postedDateTime = System.currentTimeMillis();
		aCase.setPostedDateTime(postedDateTime);
	}

	private void setServicedata(DbFuneralSyncToServicedata service, FuneralSyncListBean aCase, DbFuneralSyncToVitals deceased) {
		service.setCaseId(deceased.getId());
		service.setDayofweek("Tuesday");
		service.setTimeOfService(aCase.getServiceTime());
		
	}

	private void setDeceased(DbFuneralSyncToVitals deceased,
			FuneralSyncListBean aCase, DbUserSession sessionUser,ActionErrors errors) {
		deceased.setDecMrMrs(aCase.getDecTitle());
		deceased.setDeceasedFirstName(aCase.getDecFirstName());
		deceased.setDeceasedMidName(aCase.getDecMiddleName());
		deceased.setDeceasedLastName(aCase.getDecLastName());
		deceased.setDeceasedFullName(aCase.getDecFirstName()+" "+aCase.getDecMiddleName()+" "+aCase.getDecLastName());
		deceased.setDeceasedMaidenName(aCase.getDecMaidenName());
		java.util.Date tDate;
		
		
		try {
			tDate = new java.util.Date(aCase.getDateOfBirth().getTime());
            deceased.setDateOfBirth(FormatDate.convertDateToMMDDYYYY(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("DateOfBirth"));
        }
		deceased.setDecBirthPlace(aCase.getPlaceOfBirth());
		
		
		try {
			tDate = new java.util.Date(aCase.getDateOfDeath().getTime());
            deceased.setDateOfDeath(FormatDate.convertDateToMMDDYYYY(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("DateOfDeath"));
        }

        
		try {
			tDate = new java.util.Date(aCase.getDateOfDeath().getTime());
            deceased.setDeathDateKey(FormatDate.convertDateToYYYYMMDD(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("DeathDateKey"));
        }
        
		deceased.setLocationOfDeath(aCase.getPlaceOfDeath());
		
		
		try {
			tDate = new java.util.Date(aCase.getBurialDate().getTime());
            deceased.setBurialDate(FormatDate.convertDateToMMDDYYYY(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("BurialDate"));
        }

		deceased.setBurialDate("");
		
        
		try {
			tDate = new java.util.Date(aCase.getServiceDate().getTime());
            deceased.setServiceDateKey(FormatDate.convertDateToYYYYMMDD(tDate));
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ServiceDateKey"));
        }

		try {
			LocationDAO locationDao = new LocationDAO(sessionUser);
			LocationDTO location = locationDao.getLocationByFuneralSyncLocationId(aCase.getLocationID());
			
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
