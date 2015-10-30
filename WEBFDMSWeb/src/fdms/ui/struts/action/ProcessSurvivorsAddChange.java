package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.SurvivorDAO;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.SurvivorsForm;


public class ProcessSurvivorsAddChange extends Action {

	private Logger logger = Logger.getLogger(ProcessSurvivorsAddChange.class.getName());
	private ArrayList formErrors;

	public ActionForward execute(ActionMapping mapping,
			ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
	throws javax.servlet.ServletException, java.io.IOException {

		logger.debug("ProcessSurvivorsAddChange.");

		formErrors = new ArrayList();

		SurvivorsForm survivorsForm =  ((SurvivorsForm)actionForm);
		ActionErrors errors = new ActionErrors();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbSurvivor survivor = null;
		int vitalsid = 0;

		vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		if (vitalsid < 1) {
			logger.debug("ProcessSurvivorsAddChange. Invalid VitalsID to process  :" + vitalsid);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
		}

		String directive = survivorsForm.getSubmitbutton();
	//	survivorsForm.setSubmitbutton(null);

	//	if(directive == null){
	//		directive =	(String)request.getParameter("target");
	//	}
		logger.debug("ProcessSurvivorsAddChange processing: " + vitalsid);
		logger.debug("Directive : " + directive);
		
		

		if (directive.equals("cancel")) {
			return mapping.findForward("showSurvivors");
		}
		validateData(request, survivorsForm, errors);
		if (!errors.isEmpty()) {
			
			logger.debug("ProcessSurvivorsAddChange Invoking forward mapping getInput() ");
			return (new ActionForward(mapping.getInput() ));
		}
		if(directive.equals("addnewrow")){
			
			DbSurvivor newsurvivor = new DbSurvivor();
			int id = getNewId(survivorsForm.getRelativesList());
			newsurvivor.setId(id);
			newsurvivor.setISeqKey(getNextSortNumber(survivorsForm.getRelativesList()));
			survivorsForm.getRelativesList().add(0,newsurvivor);
			return (new ActionForward(mapping.getInput() ));
		}

		if(directive.equals("deleterow")){
		//	int id = new Integer(request.getParameter("id")).intValue();
			int id = new Integer(survivorsForm.getDeleteRow()).intValue();
			deleteSurvivor(survivorsForm, id, request, errors );
			//return mapping.findForward("survivors");
			return (new ActionForward(mapping.getInput() ));
		}
		if(directive.equals("changeorder")) {
			try {
				if(request.getParameter("sequanceAI") != null) {
					String[] autoIndexes = request.getParameter("sequanceAI").split(",");
					String[] sortOrder = request.getParameter("sortOrder").split(",");
					
					SurvivorDAO survivorDAO = new SurvivorDAO();
					survivorDAO.updateRelativesSequanceNumber(autoIndexes, sortOrder, sessionUser.getDbLookup());
				}
			} catch (Exception e) {
				logger.error("Error while chaging relatives order", e);
			}
		}

		if(directive.equals("save")){


			try {
				t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

				ArrayList list  = survivorsForm.getRelativesList();
				for(int i=0; i<list.size(); i++ ){
					survivor = (DbSurvivor)list.get(i);
					if(survivor.getId() < 0 ){
						survivor.setNew();
						survivor.setLSurvivorMainKey(vitalsid);
						t.addPersistent(survivor);
						t.save();
						t.removePersistent(survivor);
					}else{
						ArrayList dbList  =	survivorsForm.getDbRelativesList();
						DbSurvivor dbSurvivor = getDbSurvivorFromList(dbList,survivor.getId());
						if(dbSurvivor != null){
							if(needsUpdate(dbSurvivor, survivor)){
								// no change on informant records.
								if ( (survivor.getCGroupType().compareToIgnoreCase("INF") !=0 && survivor.getCGroupType().compareToIgnoreCase("NK") !=0)
									&& !(survivor.getCSurvivorRelated().compareToIgnoreCase("Informant") ==0&&
											survivor.getISeqKey() == 1)
								) {
									t.addPersistent(survivor);
									
									if (survivor.getCGroupType().compareToIgnoreCase("NK")==0){
										DbVitalsNextKin nextkin = null;
										nextkin = FdmsDb.getInstance().getVitalsNextKin(t, vitalsid);
										
										
										nextkin.setFirstname(survivor.getCSurvivorFName());
										nextkin.setMiddleName(survivor.getCSurvivorMName());
										nextkin.setLastname(survivor.getCSurvivorLName());
										nextkin.setStreet(survivor.getCSurvivorAddr());
									
										nextkin.setCity(survivor.getCSurvivorCity());
										if ( survivor.getCSurvivorState().length() == 2) {
											nextkin.setState(survivor.getCSurvivorState().toUpperCase());
										}
										else {
											nextkin.setState(survivor.getCSurvivorState());   	
										}
										
										nextkin.setZip(survivor.getCSurvivorZip().toUpperCase());
										nextkin.setPhone(FormatString.formatPhone(survivor.getCSurvivorPhone()));
										nextkin.setCellPhone(FormatString.formatPhone(survivor.getCSurvivorPhone2()));
										nextkin.setRelation(survivor.getCSurvivorRelated());
									}
									
									
									t.save();
									t.removePersistent(survivor);
								}
							}
						}

					}
				}

			} catch(PersistenceException pe) {
				logger.error("Persistence Exception in ProcessServices do.Perform. " + pe);
				errors.add(ActionErrors.GLOBAL_ERROR,
						new ActionError("error.PersistenceException",pe.getCause()));
			} catch(Exception pe) {
				logger.error("Exception in  ProcessServices .doPerform. " + pe);
				errors.add(ActionErrors.GLOBAL_ERROR,
						new ActionError("error.GeneralException",pe.getMessage()));
			} finally {
				if ( t != null ) {
					t.closeConnection();
				}
			}

		}
	
		return mapping.findForward("showSurvivors");
	}

	public void deleteSurvivor(SurvivorsForm survivorsForm, int id, HttpServletRequest request, ActionErrors errors ){


		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbSurvivor dbSurvivor = null;
		ArrayList list  = survivorsForm.getRelativesList();

		dbSurvivor = getDbSurvivorFromList( list,  id);
		try {
			if(id>0){
				t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

				dbSurvivor = FdmsDb.getInstance().getSurvivor(t,id);
				//only can be delete if not informant and next of kin data.
				if (dbSurvivor.getCGroupType().compareToIgnoreCase("INF") != 0 && dbSurvivor.getCGroupType().compareToIgnoreCase("NK") != 0 && dbSurvivor.getCSurvivorRelated().compareToIgnoreCase("Informant") !=0) {
					dbSurvivor.remove();
				}
				
				t.save();
			}
		} catch(PersistenceException pe) {
			logger.error("Persistence Exception in ProcessServices do.Perform. " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.PersistenceException",pe.getCause()));
		} catch(Exception pe) {
			logger.error("Exception in  ProcessServices .doPerform. " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.GeneralException",pe.getMessage()));
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

		list  = survivorsForm.getRelativesList();
		for(int i=0; i<list.size(); i++ ){
			dbSurvivor = (DbSurvivor)list.get(i);
			if(dbSurvivor.getId()== id){
				if(dbSurvivor.getId()==-1){
					survivorsForm.getRelativesList().remove(i);
					break;
				}
				if (dbSurvivor.getCGroupType().compareToIgnoreCase("INF") != 0 && dbSurvivor.getCGroupType().compareToIgnoreCase("NK") != 0) {
					survivorsForm.getRelativesList().remove(i);
				}
				break;
			}

		}
	}


	/**
	 * Insert the method's description here.
	 * Creation date: (10/30/2002 1:54:31 PM)
	 * @param form fdms.ui.struts.form.SurvivorsAddChangeForm
	 * @param errors org.apache.struts.action.ActionErrors
	 */
	public void validateData(HttpServletRequest request,SurvivorsForm form, ActionErrors errors) {

		ArrayList relatives = form.getRelativesList();
		for(int i=0; i < relatives.size(); i++ ){
			DbSurvivor survivor = (DbSurvivor)relatives.get(i);
			if(survivor.getCSurvivorFName() == null || survivor.getCSurvivorFName().trim().length() == 0){
	
					if("deleterow".equals(form.getSubmitbutton()) && 
							form.getDeleteRow().equals(new Integer(survivor.getId()).toString())){
						continue;
					}
					errors.add(ActionErrors.GLOBAL_ERROR,
							new ActionError("error.registration.nullFirstName"));
					formErrors.add("informantFirstName");		
			}
			if("save".equals(form.getSubmitbutton()) && survivor.getISeqKey() <= 0 ){
				errors.add(ActionErrors.GLOBAL_ERROR,
						new ActionError("error.int", "Sort Order"));
				formErrors.add("Sort Order");
			}
		}
		saveErrors(request, errors);
		return;

	}
	
	/**
	 * Checks DbServivor need database update or not
	 * @param dbSurvivor from Database
	 * @param webSurvivor from web
	 * @return true if database update is required else false
	 */
	public boolean needsUpdate(DbSurvivor dbSurvivor, DbSurvivor webSurvivor){

		if(dbSurvivor.getISeqKey() != webSurvivor.getISeqKey()){
			return true;
		}
		if (dbSurvivor.getCSurvivorRelated() == null) {
			if (webSurvivor.getCSurvivorRelated() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorRelated().equals(webSurvivor.getCSurvivorRelated()))
			return true;

		if (dbSurvivor.getCSurvivorLName() == null) {
			if (webSurvivor.getCSurvivorLName() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorLName().equals(webSurvivor.getCSurvivorLName()))
			return true;

		if (dbSurvivor.getCSurvivorFName() == null) {
			if (webSurvivor.getCSurvivorFName() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorFName().equals(webSurvivor.getCSurvivorFName()))
			return true;

		if (dbSurvivor.getCSurvivorMName() == null) {
			if (webSurvivor.getCSurvivorMName() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorMName().equals(webSurvivor.getCSurvivorMName()))
			return true;

		if (dbSurvivor.getCSurvivorState() == null) {
			if (webSurvivor.getCSurvivorState() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorState().equals(webSurvivor.getCSurvivorState()))
			return true;

		if (dbSurvivor.getCSurvivorCity() == null) {
			if (webSurvivor.getCSurvivorCity() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorCity().equals(webSurvivor.getCSurvivorCity()))
			return true;

		if (dbSurvivor.getCSurvivorAddr() == null) {
			if (webSurvivor.getCSurvivorAddr() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorAddr().equals(webSurvivor.getCSurvivorAddr()))
			return true;

		if (dbSurvivor.getCSurvivorLiving() == null) {
			if (webSurvivor.getCSurvivorLiving() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorLiving().equals(webSurvivor.getCSurvivorLiving()))
			return true;
		
		if (dbSurvivor.getCSurvivorEmail() == null) {
			if (webSurvivor.getCSurvivorEmail() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorEmail().equals(webSurvivor.getCSurvivorEmail()))
			return true;
		if (dbSurvivor.getCPreferConmunicate() == null) {
			if (webSurvivor.getCPreferConmunicate() != null)
				return true;
		} else if (!dbSurvivor.getCPreferConmunicate().equals(webSurvivor.getCPreferConmunicate()))
			return true;
		if (dbSurvivor.getCSurvivorPhone() == null) {
			if (webSurvivor.getCSurvivorPhone() != null)
				return true;
		} else if (!dbSurvivor.getCSurvivorPhone().equals(webSurvivor.getCSurvivorPhone()))
			return true;
		
		return false;
	}

	/**
	 * Gets DbServivor from List for a given id
	 * @param list of DbServivor Objects
	 * @param id of DbServivor
	 * @return DbServivor 
	 */
	public DbSurvivor getDbSurvivorFromList(ArrayList list, int id){

		DbSurvivor dbSurvivor = null;
		for(int i=0; i<list.size(); i++ ){
			dbSurvivor = (DbSurvivor)list.get(i);
			if(dbSurvivor.getId()== id){
				break;
			}
		}
		return dbSurvivor;
	}
	/**
	 * Gets temporary Id vaue for DbServivor object
	 * @param list of DbServivor objects
	 * @return id value
	 */
	public int getNewId(ArrayList list){
		int id = 0;
		for(int i=0; i < list.size(); i++){
			if (((DbSurvivor)list.get(i)).getId() < 0){
				id = id -1 ;
			}
			
		}	
		return id-1;
	}
	
	/**
	 * Gets next available sort number
	 * @param list of survivors
	 * @return next available sort number
	 */
	public short getNextSortNumber(List list){
		short sortOrder = 0;
		DbSurvivor dbSurvivor = null;
		for(int i=0; list != null && i<list.size();i++){
			dbSurvivor = (DbSurvivor)list.get(i);
			if(dbSurvivor.getISeqKey() > sortOrder){
				sortOrder = dbSurvivor.getISeqKey();
			}
		}
		
		return (++sortOrder);
	}
}
