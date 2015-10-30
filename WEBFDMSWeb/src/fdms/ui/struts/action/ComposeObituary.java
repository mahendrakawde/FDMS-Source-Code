package fdms.ui.struts.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

import com.aldorsolutions.asimas.webfdms.obituary.delegate.ObituaryManagerDelegate;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbSurvivorPeer;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.bean.CrystalReportManagerBean;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;
import com.asimas.asimasBeans.Obituary;

import fdms.reporting.crystal.ExportReport;
import fdms.ui.struts.form.ObituaryForm;
import fdms.ui.struts.form.ObituaryImageForm;
import fdms.ui.struts.form.PrintForm;


public class ComposeObituary extends Action {

    private Logger logger = Logger.getLogger(ComposeObituary.class.getName());

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("ComposeObituary action starting.");
        
        PrintForm form = (PrintForm) actionForm;
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward = mapping.findForward("showComposedObituary");
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        int formID = FormatNumber.parseInteger(form.getFormName());
        int vitalsid = 0;
        String fileName = new String();
        String thisLine = new String();
        String newObitText = new String();
    	long asimasDeceasedId = 0L;
    	String externalAppId = "0";
    	ObituaryImageForm obituaryImageForm = new ObituaryImageForm();
    	
    	StringBuffer newObitContent = new StringBuffer();

        if (formID < 1) {
            logger.debug("ComposeObituary. Invalid formID to process : " + formID);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
        }

        // See if vitalsId is set in form
        if (form.getVitalsId() != null && !form.getVitalsId().trim().equals("")) {
            try {                
                vitalsid = Integer.parseInt(form.getVitalsId());
                SessionHelpers.setVitalsIdInRequest(request, vitalsid);
            } catch (NumberFormatException e) {
                logger.error("Unable to parse int from String : ", e);
            }
        }

        // Get Vitals Case ID
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid < 1) {
            logger.debug("ComposeObituary. Invalid VitalsID to process : " + vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }

        // If errors found, bail out and return to input page
        if (!errors.isEmpty()) {
            logger.debug("ComposeObituary Invoking forward mapping getInput().");
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }

        // Run the report
        logger.debug("ComposeObituary. processing Format: " + formID);
        logger.debug("VitalsId: " + vitalsid);
        
        //
        // Native call no long used
        // ExportReport crystal = new ExportReport();        
        // String pageName = crystal.printForm(
        //         sessionUser, formID,"","",null,"", request, response, servlet.getServletContext());

        String pageName = " ";
        
        // Update FormsPrinted to show this one has been done
        FdmsDb.getInstance().setFormPrintedForCase(sessionUser,vitalsid,formID);

        // Get the base file system directory
        
        InputStream is = null;
        
        // Commented by Parth: compose part is now not going to Crystal report.
        
        
        try {
            fileName = fileName +pageName;
            fileName.replace('\\','/');
            logger.debug("FileName = " +fileName);
            
            String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");

            if ( crystalFlag != null && "true".equals(crystalFlag) ) {
                CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());                
                pageName = crystalServerReport.printReport(sessionUser, formID, "", "", null, "", "", false);
            }
            else
            {
            	ExportReport crystal = new ExportReport();
                pageName = crystal.printForm(sessionUser, formID, "", "", null, "", request, response, servlet.getServletContext());
            }
                      
            logger.debug("FILE NAME : " + pageName);
            
            URL url = new URL(pageName);
            is = url.openStream();
            	
            BufferedReader myInput = new BufferedReader(new InputStreamReader(is));
            
            while ((thisLine = myInput.readLine()) != null) {
                newObitText = newObitText.concat(thisLine);
            }
            
        } catch (IOException e) {
            logger.error("ComposeObituary. Can not access Compose Obituary file: "+fileName);
            logger.error("Error in doPerform() : ", e);
        } finally {
            if (is != null) is.close();
        }

        
        newObitText = "Mr. Charles M Scott, Age 65, of Coppell, Texas, passed away August 17, 2010 at "+
        "Promise Hospital.  He was born August 17, 1945 in Coppell, TX, USA. .    Mr. Charles "+
        "M Scott was survived by  Mary Jones, his Daughter, Marvin Smith, his father.  Mr. " +
        "Charles M Scott was preceded by   Funeral service will take place 11:00 " +
        "AM-Wednesday at Abundant Life, 9900 Hayne Blvd.., New Orleans.  Name of " +
        "Minister,minister@church.org will officiate with burial in Name of Cemetery.  Friends "+
        "may call at Eastside Funeral Home, 888 West St., Anytown, MI, 77777.  Those "+
        "planning an expression of sympathy may wish to consider memorials to American "+
        "Cancer Society.";
        
        logger.debug("ComposeObituary creating new Obituary form for vitalsid: " + vitalsid);
        ObituaryForm obitForm = new ObituaryForm();
        
        DatabaseTransaction t = null;
        DbObituary obituary = null;
        DbVitalsFirstCall firstCall = null;
        DbVitalsDeceased deceased = null;
        DbSurvivor[] survivorarray = null;
        
        ArrayList formsList = new ArrayList();
        ArrayList formatsList = new ArrayList();

        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            obituary = FdmsDb.getInstance().getObituary(t, vitalsid);
            deceased = FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
            firstCall = FdmsDb.getInstance().getVitalsFirstCall(t, vitalsid);
            survivorarray = FdmsDb.getInstance().getSurvivorsForID(t, vitalsid, DbSurvivorPeer.SEQNUMBER);
            
            System.out.println("obituary: " + obituary);
            
            if(obituary != null) {
            	obitForm.setWebsite(obituary.getObituaryLink());
            }
            
            obitForm.setDeceasedFullName(deceased.getDecFullName());
            
            newObitContent.append(deceased.getMrmrs()).append(deceased.getFirstName()).append(" ").append(deceased.getLastName());
            // newObitContent.append(deceased.getFullName());
            
            if(firstCall.getAgeYears() > 0) {
            	newObitContent.append(", Age ").append(firstCall.getAgeYears());
            }
            
            newObitContent.append(", passed away ");
            
            if(deceased.getDateOfDeath() != null && deceased.getDateOfDeath().length() > 0) {
            	newObitContent.append("on ");
            	
            	String dodMMDDYYYY = FormatDate.MDYtoMMDDYYYY(deceased.getDateOfDeath());
				
				String descriptiveDate = FormatDate.convertDateToMonthString(FormatDate.convertToDate(dodMMDDYYYY)) + " " + 
										FormatDate.getDayFromMMDDYYYY(deceased.getDateOfDeath()) + ", " + 
										FormatDate.getYearFromMMDDYYYY(deceased.getDateOfDeath());
				
				newObitContent.append(descriptiveDate);
            }
            
            if(firstCall.getPlaceDeath() != null & firstCall.getPlaceDeath().length() > 0) {
            	newObitContent.append(", at ").append(firstCall.getPlaceDeath());
            }
            
            newObitContent.append(". ");
            
            if (survivorarray != null) {
            	
            	newObitContent.append(deceased.getMrmrs()).append(deceased.getFirstName()).append(" ").append(deceased.getLastName());
                
				//Iterate Through result DB object and store as a Session Variable
            	
            	DbVitalsNextKin nextKin = FdmsDb.getInstance().getVitalsNextKin(t, vitalsid);
            	
            	StringBuffer survivedBy = new StringBuffer();
            	StringBuffer preceadedBy = new StringBuffer();
            	
            	String relation = "";
            	
            	Set<String> relationList = new HashSet<String>();
            	
				for (int i = 0; i < survivorarray.length; i++) {
					DbSurvivor dbs = (DbSurvivor) survivorarray[i];
					if (dbs.getISeqKey() > 0 && dbs.getISeqKey() < 1000) {
						
						if(dbs.getCSurvivorLiving().equalsIgnoreCase("Y")) {
							
							if(!relationList.contains(dbs.getCSurvivorRelated().toLowerCase())) {
								if(dbs.getCGroupType().equals("INF")) {
									
									if(survivedBy.toString().length() > 0) {
										survivedBy.append(", ");
									}
									
									survivedBy.append(dbs.getCSurvivorFName()).append(" ").append(dbs.getCSurvivorLName());
										
									survivedBy.append(" (").append(dbs.getCSurvivorRelated().toLowerCase()).append(")");
								} else if(dbs.getCGroupType().equals("NK") && nextKin.getSameAsInformant().equals("N")) {
									
									if(survivedBy.toString().length() > 0) {
										survivedBy.append(", ");
									}
									survivedBy.append(dbs.getCSurvivorFName()).append(" ").append(dbs.getCSurvivorLName());
									
									survivedBy.append(" (").append(dbs.getCSurvivorRelated().toLowerCase()).append(")");
								} else if(dbs.getCGroupType() == null || (dbs.getCGroupType() != null && !dbs.getCGroupType().equals("NK") && !dbs.getCGroupType().equals("INF")) ) {
									
									if(survivedBy.toString().length() > 0) {
										survivedBy.append(", ");
									}
									
									survivedBy.append(dbs.getCSurvivorFName()).append(" ").append(dbs.getCSurvivorLName());
									
									if(!dbs.getCSurvivorRelated().equalsIgnoreCase("informant")) {
										survivedBy.append(" (").append(dbs.getCSurvivorRelated().toLowerCase()).append(")");
									}
								}
								
								relationList.add(dbs.getCSurvivorRelated().toLowerCase());
							}
						} else {
							if(!relationList.contains(dbs.getCSurvivorRelated().toLowerCase())) {
								if(dbs.getCGroupType().equals("INF")) {
									
									if(preceadedBy.toString().length() > 0) {
										preceadedBy.append(", ");
									}
									
									preceadedBy.append(dbs.getCSurvivorFName()).append(" ").append(dbs.getCSurvivorLName());
									
									preceadedBy.append(" (").append(dbs.getCSurvivorRelated().toLowerCase()).append(")");
								} else if(dbs.getCGroupType().equals("NK") && nextKin.getSameAsInformant().equals("N")) {
									
									if(preceadedBy.toString().length() > 0) {
										preceadedBy.append(", ");
									}
									
									preceadedBy.append(dbs.getCSurvivorFName()).append(" ").append(dbs.getCSurvivorLName());
									
									preceadedBy.append(" (").append(dbs.getCSurvivorRelated().toLowerCase()).append(")");
								} else if(dbs.getCGroupType() == null || (dbs.getCGroupType() != null && !dbs.getCGroupType().equals("NK") && !dbs.getCGroupType().equals("INF")) ) {
									
									if(preceadedBy.toString().length() > 0) {
										preceadedBy.append(", ");
									}
									
									preceadedBy.append(dbs.getCSurvivorFName()).append(" ").append(dbs.getCSurvivorLName());
									
									if(!dbs.getCSurvivorRelated().equalsIgnoreCase("informant")) {
										preceadedBy.append(" (").append(dbs.getCSurvivorRelated().toLowerCase()).append(")");
									}
								}
							}
						}
						
						relationList.add(dbs.getCSurvivorRelated().toLowerCase());
					}
				}
				
				if(survivedBy.toString().trim().length() > 0) {
					newObitContent.append(" is survived by ");
					newObitContent.append(survivedBy.toString());
					
					if(preceadedBy.toString().trim().length() > 0) {
						newObitContent.append(" and");
						newObitContent.append(" is preceded by ");
						newObitContent.append(preceadedBy.toString());
					}
				} else if(preceadedBy.toString().trim().length() > 0) {
					newObitContent.append(" is preceded by ");
					newObitContent.append(preceadedBy.toString());
				}
				
				newObitContent.append(". ");
			}
            
            newObitText = newObitContent.toString();
            
            
            
            

            if (newObitText != null && newObitText.trim().length() > 0) {
                obitForm.setTextValue(newObitText);                
            } else if (obituary != null){
                obitForm.setTextValue(obituary.getObitNotice());           
            }
            
            String obitText = obitForm.getTextValue();
            int bodyStart = obitText.indexOf("<BODY") + 5;
            int bodyEnd = obitText.indexOf("</BODY>");
            if (bodyStart > 6 && bodyEnd > 6)
                obitText = obitText.substring(bodyStart, bodyEnd);                                             
            
            int index = 0;
            for (int i = 0; i < obitText.length(); i++) {
              char ch = obitText.charAt(index++);
//              logger.debug("CH : " + ch);
              if (ch == '>') break;
            }
            
            logger.debug("Index : " + index);
            /*if (index > 0)
            	obitText = obitText.substring(index);*/     
            
            logger.debug("Obit text : " + obitText);
            obitForm.setTextValue(obitText);  
            
            // get list of obit forms
            DbFormsAvailable[] list =
                    FdmsDb.getInstance().getFormsAvailableForLocale(
                        t,sessionUser.getRegion(),DbFormsAvailable.OBITUARY_TYPE);
            
            for (int i=0; i < list.length; i++){
                int formid = list[i].getFormId();
                formsList.add(
                        new OptionsList(Integer.toString(formid) ,list[i].getDescription()));
            }

            // get list of Obituary Compose Formats
            DbFormsAvailable[] formats =
                    FdmsDb.getInstance().getFormsAvailableForLocale(
                        t, sessionUser.getRegion(),DbFormsAvailable.OBITUARY_FORMAT);
            
            for (int i=0; i < formats.length; i++) {
                int formid = formats[i].getFormId();
                formatsList.add(new OptionsList(Integer.toString(formid), formats[i].getDescription()));
            }
            
            // put empty report previews in request
            ShowCaseStatusForms.addEmptyReportPreviews(request);

            logger.debug("Ending ComposeObituary.doPerform()");
            
			HashMap externalConfigMap = sessionUser.getExternalAppConfigMap();
			if ((externalConfigMap != null) 
					&& (externalConfigMap.containsKey(Constants.ASIMAS_APPLICATION_ID))) {
				
				logger.debug("ASIMAS Configured");
				
				externalAppId = Constants.ASIMAS_APPLICATION_ID;	
								
				long domainId = 
					((Long) externalConfigMap.get(Constants.ASIMAS_APPLICATION_ID)).longValue();
				
				asimasDeceasedId = 
					FdmsDb.getInstance().getExternalVitalsId(
							t, Constants.ASIMAS_APPLICATION_ID, vitalsid);
				ObituaryManagerDelegate obituaryManagerDelegate = new ObituaryManagerDelegate();
				
				HashMap obitMap = 
					obituaryManagerDelegate.readObituary(asimasDeceasedId, domainId);
				if (obitMap != null) {
					Obituary asimasObituary = (Obituary) obitMap.get("obituary");
					if (asimasObituary != null) {
						String obituaryImageUrl = asimasObituary.getImageURL();
						logger.debug("ASIMAS obituary image url : " + obituaryImageUrl);
						if ((obituaryImageUrl != null) && (!"".equals(obituaryImageUrl))) {
							obituaryImageForm.setHasImage("Y");
							obituaryImageForm.setObitImageUrl(obituaryImageUrl);
						} else obituaryImageForm.setObitImageUrl("images/noAsimasImage.gif");
						
						// add link to ASIMAS obituary on website into obitForm
						String asimasObituaryUrl = asimasObituary.getObituaryURL();
						logger.debug("ASIMAS obituary url : " + asimasObituaryUrl);
						obitForm.setAsimasObitUrl(asimasObituaryUrl);
					}
				}
			}
			
			// if asimas deceasedId found, set send to asimas to true
			if (asimasDeceasedId > 0L) obitForm.setSendToAsimas(true);
			
			// if asimas is configured, set asimas domainId
			obitForm.setAsimasId(externalAppId);	            

        } catch(PersistenceException pe) {
            logger.error("PersisistenceException Error in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
					t.closeConnection();
					t = null;
				}  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }

        // If errors found, bail out and return to input page
        if (!errors.isEmpty()) {
            logger.debug("ComposeObituary Invoking forward mapping getInput().");
            saveErrors(request, errors);
            //return (new ActionForward(mapping.getInput()));
        }
        
     	obituaryImageForm.setVitalsId(Integer.toString(vitalsid));
     	obituaryImageForm.setDeceasedId(Long.toString(asimasDeceasedId));
     	request.setAttribute("obituaryImage", obituaryImageForm);
     	
     	obitForm.setAsimasDeceasedId(Long.toString(asimasDeceasedId));        

        request.setAttribute("obituary", obitForm);
        session.setAttribute("obituaryForms", formsList);
        session.setAttribute("obituaryFormats", formatsList);

        return actionForward;
    }
}
