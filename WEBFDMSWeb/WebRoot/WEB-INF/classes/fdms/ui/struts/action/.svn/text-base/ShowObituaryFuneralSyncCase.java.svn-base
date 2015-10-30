package fdms.ui.struts.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.ObituaryFuneralSyncDAO;
import com.aldorsolutions.webfdms.beans.display.FuneralSyncListBean;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.BankAccountForm;
import fdms.ui.struts.form.ObituaryFuneralSyncCaseForm;

public class ShowObituaryFuneralSyncCase extends Action {

	private Logger logger = Logger.getLogger(ShowMovingCase.class.getName());
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		ActionErrors errors = new ActionErrors();
//		ObituaryFuneralSyncCaseForm form = new ObituaryFuneralSyncCaseForm();
		ObituaryFuneralSyncCaseForm form = (ObituaryFuneralSyncCaseForm) actionForm;
		FuneralSyncListBean aCase = new FuneralSyncListBean();
		String FUNERALSYNC_JNDI = UtilSingleton.getInstance().getFuneralSyncDBLookup();
		ObituaryFuneralSyncDAO OFSyncDao = new ObituaryFuneralSyncDAO (FUNERALSYNC_JNDI);
		
		int caseId = Integer.valueOf(request.getParameter("id")); 
		try {
			aCase = OFSyncDao.getObituaryFuneralSync(FUNERALSYNC_JNDI,caseId);	
			
			setForm(form,aCase);
			
			
		} catch(PersistenceException pe) {
		     logger.error("Persistence Exception in ShowObituaryFuneralSyncCase.doPerform. " + pe);
		     errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
		} 
		request.setAttribute("obituaryFuneralSyncCaseForm",form);
		return ( mapping.findForward("showObituaryFuneralSyncCaseJsp") );
	}

	private void setForm(ObituaryFuneralSyncCaseForm form,	FuneralSyncListBean aCase) {
		form.setId(aCase.getId());
		form.setObitID(aCase.getObitID()); 
		form.setLocationID(aCase.getLocationID()); 
		form.setDecTitle(aCase.getDecTitle()); 
		form.setDecFirstName(aCase.getDecFirstName()); 
		form.setDecMiddleName(aCase.getDecMiddleName()); 
		form.setDecLastName(aCase.getDecLastName()); 
		form.setDecNickName(aCase.getDecNickName()); 
		form.setDecMaidenName(aCase.getDecMaidenName()); 
		form.setDateOfBirth(aCase.getDateOfBirth()); 
		form.setPlaceOfBirth(aCase.getPlaceOfBirth()); 
		form.setDateOfDeath(aCase.getDateOfDeath()); 
		form.setPlaceOfDeath(aCase.getPlaceOfDeath()); 
		form.setViewType(aCase.getViewType()); 
		form.setViewNoBodyPresent(aCase.getViewNoBodyPresent()); 
		form.setViewDate(aCase.getViewDate()); 
		form.setViewTime(aCase.getViewTime()); 
		form.setViewAtHome(aCase.getViewAtHome()); 
		form.setViewLocation(aCase.getViewLocation()); 
		form.setOtherViewAtHome(aCase.getOtherViewAtHome()); 
		form.setViewOtherLocation(aCase.getViewOtherLocation()); 
		form.setViewOtherDate(aCase.getViewOtherDate()); 
		form.setViewOtherTime(aCase.getViewOtherTime()); 
		form.setBurialType(aCase.getBurialType()); 
		form.setBurialDate(aCase.getBurialDate()); 
		form.setBurialTime(aCase.getBurialTime()); 
		form.setCemeteryName(aCase.getCemeteryName()); 
		form.setServiceType(aCase.getServiceType()); 
		form.setServiceDate(aCase.getServiceDate()); 
		form.setServiceTime(aCase.getServiceTime()); 
		form.setServiceAtHome(aCase.getServiceAtHome()); 
		form.setServiceOtherLocation(aCase.getServiceOtherLocation()); 
		form.setExtraServices(aCase.getExtraServices()); 
		form.setFlowersType(aCase.getFlowersType()); 
		form.setFlowersDate(aCase.getFlowersDate()); 
		form.setFlowersTime(aCase.getFlowersTime()); 
		form.setFlowersOtherDate(aCase.getFlowersOtherDate()); 
		form.setFlowersOtherTime(aCase.getFlowersOtherTime()); 
		form.setFlowersOtherLocation(aCase.getFlowersOtherLocation()); 
		form.setContributionName(aCase.getContributionName()); 
		form.setEulogy(aCase.getEulogy()); 
		form.setSurvivors(aCase.getSurvivors()); 
		form.setDateEntered(aCase.getDateEntered()); 
		form.setDateModified(aCase.getDateModified()); 
		form.setArchiveDate(aCase.getArchiveDate()); 
		form.setClientId(aCase.getClientId()); 
		form.setPostedYN(aCase.getPostedYN()); 
		form.setPostedDateTime(aCase.getPostedDateTime());
		
	}
	
}
