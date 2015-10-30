package fdms.ui.struts.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.custom.PaymentComponentListItem;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.info.FinancialInformation;
import fdms.info.FinancialSuper;
import fdms.info.FirstCallInformation;
import fdms.info.PallBearerInformation;
import fdms.info.PaymentInformation;
import fdms.info.PreNeedInformation;
import fdms.info.ReadWriteExcel;
import fdms.info.ServicesInformation;
import fdms.info.ServicesVisitationInformation;
import fdms.info.SurvivorsInformation;
import fdms.info.VeteransInformation;
import fdms.info.VitalsInformation;
import fdms.ui.struts.form.FinancialAddMerchandiseForm;
import fdms.ui.struts.form.FinancialAddServicesForm;
import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.FinancialSelectPackagesForm;
import fdms.ui.struts.form.FirstCallInformationForm;
import fdms.ui.struts.form.ImportDataForm;
import fdms.ui.struts.form.PallBearersForm;
import fdms.ui.struts.form.PaymentAddChangeForm;
import fdms.ui.struts.form.PreNeedForm;
import fdms.ui.struts.form.ServicesForm;
import fdms.ui.struts.form.ServicesVisitationForm;
import fdms.ui.struts.form.SurvivorsForm;
import fdms.ui.struts.form.VaBenefitsForm;
import fdms.ui.struts.form.VaFlagForm;
import fdms.ui.struts.form.VaStoneForm;
import fdms.ui.struts.form.VerifyFinancialForm;
import fdms.ui.struts.form.VitalsForm;
import fdms.ui.struts.form.pnstatus;

// Modified by :QPRIME (JO) for SOW# F030601A - Cemetery Management
//  Added logic to possibly access Cemetery Management screen .

public class ProcessLogonTest extends Action {
    
    private Logger logger = Logger.getLogger(ProcessLogonTest.class.getName());
    
    private static final String IMPORT_DATA_DIRECTORY = "import.data.directory";
    private static final String ACCESS_URL = "import.data.file.access.url";
    private static final String FORM_ERRORS = "formErrors";
    private static final String SLASH = "/";
    private ModuleConfig modconf = null;
    
    /**
     * Called from login.jsp, this action either verifies a user has access to the system
     * or logs into demo database or allows setting up a free trial.
     */
    @SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
    	modconf =  mapping.getModuleConfig();
    	
    	MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
    	FormFile formFile = null;
        ImportDataForm fileUploadForm = (ImportDataForm) form;
        FileOutputStream fileOutputStream = null;

        try {
        	formFile = fileUploadForm.getFile();
        	FileUtils.forceMkdir(new File(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId()));
        	File uploadedFile = new File(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
        	fileOutputStream = new FileOutputStream(uploadedFile);
            fileOutputStream.write(formFile.getFileData());
        } catch (Exception e) {
        	logger.error("Exception in Import Data execute() : ", e);
        	return mapping.findForward("testShowImportData");
		} finally {
        	if(fileOutputStream != null) {
        		fileOutputStream.close();
        	}
        }

		request.setAttribute("rejectedCase", true);
		request.setAttribute("rejectedFilePath", resources.getMessage(ACCESS_URL) +  request.getSession().getId() + SLASH + formFile.getFileName());
		
		// At-need/Pre-need case
		boolean preNeed = (BooleanUtils.toBoolean(request.getParameter("ctype"))) ? true : false;
		
		try {
			ReadWriteExcel excel = new ReadWriteExcel(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
			
			for(int startRow = 2; startRow <= excel.getSheet().getLastRowNum(); startRow++ ) {
				XSSFRow row = excel.getSheet().getRow(startRow);
				
				if(preNeed) {
					try { 
						PreNeedInformation preNeedInformation = new PreNeedInformation();
						Object preNeedForm = preNeedInformation.getPreNeedData(excel, row);
				
				        ActionMapping processPreNeedMapping = getActionMapping(mapping, "testProcessPreNeed");
				        ActionMapping processPreNeedStatusMapping = getActionMapping(mapping, "testProcessPnStatus");
				        
				        try {
				        	Class clazz = Class.forName(processPreNeedMapping.getType());
				        	Action processPreNeedAction = (Action) clazz.newInstance();
				        	
				        	Class clazzPNStatus = Class.forName(processPreNeedStatusMapping.getType());
				        	Action processPreNeedStatusAction = (Action) clazzPNStatus.newInstance();
				        	
			        		try {
				        		if(preNeedForm instanceof pnstatus) {
				        			processPreNeedStatusAction.execute(processPreNeedStatusMapping, (pnstatus) preNeedForm, request, response);
				        		} else {
				        			processPreNeedAction.execute(processPreNeedMapping, (PreNeedForm) preNeedForm, request, response);
				        		}
				        		//preNeedInformation.updateSheet(index, ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
				        		
				        		List<String> formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
								ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
								
								try {
									if(errors != null && errors.size() > 0) {
										excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request));
										continue;
									} else
										excel.updateSheet(startRow, 67, ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
								} catch (Exception e) {
									System.err.println("Error in Pre Need - " + e.getMessage() + " at row : " + startRow);
									logger.error("Pre Need - Field Error : " + formErrors != null ? formErrors.toString() : "");
									logger.error("Exception in Import Data execute() : ", e);
								}
								
			        		} catch (Exception e) {
								System.err.println("Error in Pre Need - " + e.getMessage() + " at row : " + startRow);
								logger.error("Exception in Import Data execute() : ", e);
							}
				        		
				        	// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
				        } catch (Exception e) {
				        	logger.error("Exception in Import Data execute() : ", e);
						}
					} catch (Exception e) {
						logger.error("Exception in Import Data execute() : ", e);
					}
				} else {
					try {
						FirstCallInformation firstCallInformation = new FirstCallInformation();
						FirstCallInformationForm firstCallInformationForm = firstCallInformation.getAtNeedCases(excel, row);
						
						ActionMapping processFirstCallMapping = getActionMapping(mapping, "testProcessFirstCallInformation");
						
						Class clazz = Class.forName(processFirstCallMapping.getType());
			        	Action processFirstCallAction = (Action) clazz.newInstance();
			        	
						if(firstCallInformationForm == null)
							continue;
							
						processFirstCallAction.execute(processFirstCallMapping, firstCallInformationForm, request, response);
						
						List<String> formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
						ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
						
						try {
							if(errors != null && errors.size() > 0) {
								excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
								continue;
							} else
								excel.updateSheet(startRow, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
						} catch (Exception e) {
							System.err.println("Error in At Need - " + e.getMessage() + " at row : " + startRow);
							logger.error("At Need - Field Error : " + formErrors != null ? formErrors.toString() : "");
							logger.error("Exception in Import Data execute() : ", e);
						}
						
						request.setAttribute(FORM_ERRORS, null);
						request.setAttribute(Globals.ERROR_KEY, null);
							
						//excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
					} catch (Exception e) {
						logger.error("Exception in Import Data execute() : ", e);
					}
				}
				
				// Financial
		        ActionMapping showFinancialInformationMapping = getActionMapping(mapping, "testShowFinancialInformation"); // Show Financial
		        ActionMapping finalAC = getActionMapping(mapping, "testSaveFinancial"); // Save Financial
		        ActionMapping processFinancialAddMerchandiseMapping = getActionMapping(mapping, "testProcessFinancialAddMerchandise"); // Process Financial Add Merchandise
		        ActionMapping processFinancialAddServiceMapping = getActionMapping(mapping, "testProcessFinancialAddServices"); // Process Financial Add Service
		        ActionMapping processInsertPackageMapping = getActionMapping(mapping, "testProcessInsertPackage"); // Process Financial Add Service
		        
		        try {
		        	Class clazzSFI = Class.forName(showFinancialInformationMapping.getType());
		        	Action showFinancialInformationAction = (Action) clazzSFI.newInstance();
		            
		            Class clazz = Class.forName(finalAC.getType());
		            Action processLogonAction = (Action) clazz.newInstance();
		            
		            Class clazzPFAM = Class.forName(processFinancialAddMerchandiseMapping.getType());
		            Action processFinancialAddMerchandiseAction = (Action) clazzPFAM.newInstance();
		            
		            Class clazzPFS = Class.forName(processFinancialAddServiceMapping.getType());
		            Action processFinancialAddServiceAction = (Action) clazzPFS.newInstance();
		            
		            Class clazzPIP = Class.forName(processInsertPackageMapping.getType());
		            Action processInsertPackageAction = (Action) clazzPIP.newInstance();
		            
		            FinancialInformation fie = new FinancialInformation();
		            FinancialSuper financialSuper = fie.readInfoFromExcel(excel, row, preNeed ? -20 : 0);
		          
	            	if(financialSuper.getFinancialForm().getVitalsId() > 0) {
			            FinancialInformationForm pForm = new FinancialInformationForm();
			            request.setAttribute("myVitalsId", financialSuper.getFinancialForm().getVitalsId());
			            
			            FinancialAddMerchandiseForm famForm = financialSuper.getMerchandiseForm();
			            famForm.setSubmitButton("save");
			            
			            FinancialAddServicesForm fasForm = financialSuper.getServiceForm();
			            fasForm.setSubmitButton("save");
			            
			            FinancialSelectPackagesForm[] fspForm = financialSuper.getPackageForm();
		
			            showFinancialInformationAction.execute(showFinancialInformationMapping, pForm, request, response);
			            pForm = set(pForm, financialSuper.getFinancialForm());
			            
			            if(famForm.getListValue().length > 0) {
			            	System.out.println("Add merchandise action - start");
			            	processFinancialAddMerchandiseAction.execute(processFinancialAddMerchandiseMapping, famForm, request, response);
			            	System.out.println("Add merchandise action - end");
			            }
			            
			            if(fasForm.getListValue().length > 0) {
			            	System.out.println("Add service action - start");
			            	processFinancialAddServiceAction.execute(processFinancialAddServiceMapping, fasForm, request, response);
			            	System.out.println("Add service action - end");
			            }
		
			            if(fspForm != null && fspForm.length > 0) {
			            	for(int i = 0; i < fspForm.length; i++) {
				            	System.out.println("Add package action - start");
				            	processInsertPackageAction.execute(processInsertPackageMapping, fspForm[i], request, response);
				            	System.out.println("Add package action - end");
			            	}
			            }
			            
			            pForm.setDirective("");
			            pForm.setLineItems(((FinancialInformationForm) request.getSession().getAttribute("financialInformation")).getLineItems());
			            processLogonAction.execute(finalAC,pForm,request,response);
			            
			            List<String> formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
						ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
						
						try {
							if(errors != null && errors.size() > 0) {
								//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
							} else {
								//fie.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
								
								ActionMapping financialSpecifyComponentsMapping = getActionMapping(mapping, "financialSpecifyComponents"); // Save Financial
								Class clazzFinancialSpecifyComponents = Class.forName(financialSpecifyComponentsMapping.getType());
					        	Action financialSpecifyComponentsAction = (Action) clazzFinancialSpecifyComponents.newInstance();
					        	
					        	financialSpecifyComponentsAction.execute(financialSpecifyComponentsMapping, null, request, response);
					        	List<PaymentComponentListItem> tmpList = (List<PaymentComponentListItem>) request.getSession().getAttribute("specifyPaymentComponentsList");
					        	
					        	if(tmpList != null && tmpList.size() > 0) {
					        		excel.updateSheet(startRow, 771 + (preNeed ? -20 : 0), tmpList.get(0).getRecID());
					        	}
							}
						} catch (Exception e) {
							System.err.println("Error in Financial - " + e.getMessage() + " at row : " + startRow);
							logger.error("Financial - Field Error : " + formErrors != null ? formErrors.toString() : "");
							logger.error("Exception in Import Data execute() : ", e);
						}
						
						request.setAttribute(FORM_ERRORS, null);
						request.setAttribute(Globals.ERROR_KEY, null);
						
			            ((FinancialInformationForm) request.getSession().getAttribute("financialInformation")).setLineItems(new TreeMap());
	            	}

	            	//excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
		            request.setAttribute("myVitalsId", null);
		            //return mapping.findForward("testShowImportData");
		        } catch (Exception e) {
		        	logger.error("Could not find chained action: ", e);
		        } 
		        
		     	// Vitals
				ActionMapping processVitalsMapping = getActionMapping(mapping, "testProcessVitals"); 
				
				try {
		        	Class clazz = Class.forName(processVitalsMapping.getType());
		        	Action processVitalsAction = (Action) clazz.newInstance();
		        	
		        	VitalsInformation vitalsInformation = new VitalsInformation();
		        	VitalsForm vitalsForm = vitalsInformation.getVitalForms(excel, row, preNeed ? -20 : 0);
		        	
					if(vitalsForm == null) {
						continue;
					}
					
					request.setAttribute("myVitalsId", vitalsForm.getVitalsid());
					processVitalsAction.execute(processVitalsMapping, vitalsForm, request, response);
					
					List<String> formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
					ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
					
					try {
						if(formErrors != null && formErrors.size() > 0) {
							//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
						} else {
							//vitalsInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
						}
					} catch (Exception e) {
						System.err.println("Error in Vital - " + e.getMessage() + " at row : " + startRow);
						logger.error("Vitals - Field Error : " + formErrors != null ? formErrors.toString() : "");
						logger.error("Exception in Import Data execute() : ", e);
					}
					
					request.setAttribute(FORM_ERRORS, null);
					request.setAttribute(Globals.ERROR_KEY, null);
					request.setAttribute("myVitalsId", null);
						
					//excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
				} catch (Exception e) {
					logger.error("Exception in Import Data execute() : ", e);
				}
				
				// Services
				ActionMapping processServicesMapping = getActionMapping(mapping, "testProcessServices");
				ActionMapping pallBearersMapping = getActionMapping(mapping, "testProcessPallBearers");
				ActionMapping servicesVisitationMapping = getActionMapping(mapping, "testProcessServicesVisitation");
				
				try {
					Class clazz = Class.forName(processServicesMapping.getType());
		        	Action servicesAction = (Action) clazz.newInstance();
		        	
					Class pallBearersClazz = Class.forName(pallBearersMapping.getType());
		        	Action pallBearersAction = (Action) pallBearersClazz.newInstance();
		        	
		        	Class servicesVisitationClazz = Class.forName(servicesVisitationMapping.getType());
		        	Action servicesVisitationAction = (Action) servicesVisitationClazz.newInstance();
		        	
		        	ServicesInformation servicesInformation = new ServicesInformation();
		        	ServicesForm servicesForm = servicesInformation.getServicesForms(excel, row, preNeed ? -20 : 0);
		        	
					if(servicesForm == null) {
						continue;
					}
					
					String data[] = servicesForm.getDirective().split("=");
					servicesForm.setDirective(data[0]);
					request.setAttribute("myVitalsId", data[1]);
					servicesAction.execute(processServicesMapping, servicesForm, request, response);
					
					List<String> formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
					ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
					
					try {
						if(formErrors != null && formErrors.size() > 0) {
							//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
						}//else
						//	pallBearerInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
					} catch (Exception e) {
						System.err.println("Error in Service - " + e.getMessage() + " at row : " + startRow);
						logger.error("Services - Field Error : " + formErrors != null ? formErrors.toString() : "");
						logger.error("Exception in Import Data execute() : ", e);
					}
					
					request.setAttribute(FORM_ERRORS, null);
					request.setAttribute(Globals.ERROR_KEY, null);
					request.setAttribute("myVitalsId", null);
						
					// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
					
		        	PallBearerInformation pallBearerInformation = new PallBearerInformation();
		        	PallBearersForm pallBearersForm = pallBearerInformation.getPallBearersForms(excel, row, preNeed ? -20 : 0);
		        	
					if(pallBearersForm == null) {
						continue;
					}
					request.setAttribute("myVitalsId", pallBearersForm.getVitalsId());
					pallBearersAction.execute(pallBearersMapping, pallBearersForm, request, response);
					
					formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
					errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
					
					try {
						if(formErrors != null && formErrors.size() > 0) {
							//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
						} //else
						//	pallBearerInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
					} catch (Exception e) {
						System.err.println("Error in Pall Bearer - " + e.getMessage() + " at row : " + startRow);
						logger.error("Pall Bearer - Field Error : " + formErrors != null ? formErrors.toString() : "");
						logger.error("Exception in Import Data execute() : ", e);
					}
					
					request.setAttribute(FORM_ERRORS, null);
					request.setAttribute(Globals.ERROR_KEY, null);
					request.setAttribute("myVitalsId", null);
						
					// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
					
					ServicesVisitationInformation servicesVisitationInformation = new ServicesVisitationInformation();
					ServicesVisitationForm[] servicesVisitationForm = servicesVisitationInformation.getServicesVisitationForms(excel, row, startRow, preNeed ? -20 : 0);
					
					if(servicesVisitationForm == null) {
						continue;
					}
					
					try {
						for(int jIndex = 0; jIndex < servicesVisitationForm.length; jIndex++) {
							request.setAttribute("myVitalsId", servicesVisitationForm[jIndex].getVitalsMasterKey());
							servicesVisitationAction.execute(servicesVisitationMapping, servicesVisitationForm[jIndex], request, response);
							
							formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
							errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
							
							try {
								if(formErrors != null && formErrors.size() > 0) {
									//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
								} //else
								//	servicesVisitationInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
							} catch (Exception e) {
								logger.error("Visitation - Field Error : " + formErrors != null ? formErrors.toString() : "");
							}
							request.setAttribute(FORM_ERRORS, null);
							request.setAttribute(Globals.ERROR_KEY, null);
							request.setAttribute("myVitalsId", null);
						}
					} catch (Exception e) {
						System.err.println("Error in Visitation - " + e.getMessage() + " at row : " + startRow);
						logger.error("Exception in Import Data execute() : ", e);
					}
					// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
				} catch (Exception e) {
					logger.error("Exception in Import Data execute() : ", e);
				}
				
				// Relatives
				ActionMapping survivorsAddChangeMapping = getActionMapping(mapping, "testProcessSurvivorsAddChange");
				
				try {
					Class clazz = Class.forName(survivorsAddChangeMapping.getType());
		        	Action survivorsAddChangeMappingAction = (Action) clazz.newInstance();
		        	
		        	SurvivorsInformation survivorsInformation = new SurvivorsInformation();
		        	SurvivorsForm[] survivorsForm = survivorsInformation.getSurvivorsForms(excel, row, preNeed ? -20 : 0);
		        	
					if(survivorsForm == null) {
						continue;
					}
					
					ArrayList<DbSurvivor> list = new ArrayList<DbSurvivor>();
					
					try {
						for(int jIndex = 0; jIndex < survivorsForm.length && survivorsForm[jIndex] != null; jIndex++) {
							String data[] = survivorsForm[jIndex].getSubmitbutton().split("=");
							
							if(data.length != 2)
								continue;
							
							survivorsForm[jIndex].setSubmitbutton(data[0]);
							((DbSurvivor) survivorsForm[jIndex].getRelativesList().get(0)).setId(((ProcessSurvivorsAddChange)survivorsAddChangeMappingAction).getNewId(list));
							((DbSurvivor) survivorsForm[jIndex].getRelativesList().get(0)).setISeqKey(((ProcessSurvivorsAddChange)survivorsAddChangeMappingAction).getNextSortNumber(list));
							list.add((DbSurvivor) survivorsForm[jIndex].getRelativesList().get(0));
							request.setAttribute("myVitalsId", data[1]);
							survivorsAddChangeMappingAction.execute(survivorsAddChangeMapping, survivorsForm[jIndex], request, response);
							
							List<String> formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
							ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
							
							try {
								if(formErrors != null && formErrors.size() > 0) {
									//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
								} //else
								//	servicesVisitationInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
							} catch (Exception e) {
								logger.error("Relatives - Field Error : " + formErrors != null ? formErrors.toString() : "");
							}
							request.setAttribute(FORM_ERRORS, null);
							request.setAttribute(Globals.ERROR_KEY, null);
							request.setAttribute("myVitalsId", null);
						}
					} catch (Exception e) {
						System.err.println("Error in Relative - " + e.getMessage() + " at row : " + startRow);
						logger.error("Exception in Import Data execute() : ", e);
					}
					// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
				} catch (Exception e) {
					logger.error("Exception in Import Data execute() : ", e);
				}
				
				// Payment
				ActionMapping verifyFinancialMapping = getActionMapping(mapping, "testVerifyFinancial");
				ActionMapping processPaymentAddChangeMapping = getActionMapping(mapping, "testProcessPaymentAddChange");
				
				try {
					Class clazz = Class.forName(verifyFinancialMapping.getType());
		        	Action verifyFinancialAction = (Action) clazz.newInstance();
		        	
		        	Class processPaymentAddChangeClazz = Class.forName(processPaymentAddChangeMapping.getType());
		        	Action processPaymentAddChangeAction = (Action) processPaymentAddChangeClazz.newInstance();
		        	
		        	PaymentInformation paymentInformation = new PaymentInformation();
		        	PaymentAddChangeForm[] paymentAddChangeForm = paymentInformation.getPaymentAddChangeForms(excel, row, preNeed ? -20 : 0);
		        	
	        		if(paymentAddChangeForm == null) {
						continue;
					}

	        		try {
		        		for(int jIndex = 0; jIndex < paymentAddChangeForm.length; jIndex++) {
		        			if(paymentAddChangeForm[jIndex].getPayment() == null || paymentAddChangeForm[jIndex].getPayment().trim().equals("0"))
		        				continue;
			        		String data[] = paymentAddChangeForm[jIndex].getDirective().split("=");
			        		paymentAddChangeForm[jIndex].setDirective(data[0]);
			        		request.setAttribute("myVitalsId", data[1]);
							
			        		VerifyFinancialForm verifyFinancialForm = new VerifyFinancialForm();
			        		verifyFinancialAction.execute(verifyFinancialMapping, verifyFinancialForm, request, response);
			        		
			        		if(Boolean.valueOf(verifyFinancialForm.getResult())) {
				        		processPaymentAddChangeAction.execute(processPaymentAddChangeMapping, paymentAddChangeForm[jIndex], request, response);
				        		
				        		List<String> formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
								ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
								
								try {
									if(formErrors != null && formErrors.size() > 0) {
										//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
									} //else
									//	paymentInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
								} catch (Exception e) {
									logger.error("Payment - Field Error : " + formErrors != null ? formErrors.toString() : "");
								}
								request.setAttribute(FORM_ERRORS, null);
								request.setAttribute(Globals.ERROR_KEY, null);
								request.setAttribute("myVitalsId", null);
			        		} else {
			        			System.out.println("Not verfied");
			        		}
		        		}
	        		} catch (Exception e) {
						System.err.println("Error in Payment - " + e.getMessage() + " at row : " + startRow);
						logger.error("Exception in Import Data execute() : ", e);
					}
		        	// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
				} catch (Exception e) {
					logger.error("Exception in Import Data execute() : ", e);
				}
				
				// Veterans
				ActionMapping processVaFlagMapping = getActionMapping(mapping, "testProcessVaFlag");
				ActionMapping processVaBenefitsMapping = getActionMapping(mapping, "testProcessVaBenefits");
				ActionMapping processVaHeadstoneMapping = getActionMapping(mapping, "testProcessVaHeadstone");
				
				try {
					Class processVaFlagClazz = Class.forName(processVaFlagMapping.getType());
		        	Action processVaFlagAction = (Action) processVaFlagClazz.newInstance();
		        	
		        	Class processVaBenefitsClazz = Class.forName(processVaBenefitsMapping.getType());
		        	Action processVaBenefitsAction = (Action) processVaBenefitsClazz.newInstance();
		        	
					Class clazz = Class.forName(processVaHeadstoneMapping.getType());
		        	Action processVaHeadstoneAction = (Action) clazz.newInstance();
		        	
		        	VeteransInformation veteransInformation = new VeteransInformation();
		        	VaFlagForm vaFlagForm = veteransInformation.getVaFlagForms(excel, row, preNeed ? -20 : 0);
		        	
					if(vaFlagForm == null || vaFlagForm.getVitalsMasterKey().equals("") || vaFlagForm.getVitalsMasterKey().equals("0")) {
						continue;
					}
						
					request.setAttribute("myVitalsId", vaFlagForm.getVitalsMasterKey());
					processVaFlagAction.execute(processVaFlagMapping, vaFlagForm, request, response);
					
					List<String> formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
					ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
					
					try {
						if(formErrors != null && formErrors.size() > 0) {
							//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
						} else {
							//veteransInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
						}
					} catch (Exception e) {
						System.err.println("Error in VaFlag - " + e.getMessage() + " at row : " + startRow);
						logger.error("Va Flag - Field Error : " + formErrors != null ? formErrors.toString() : "");
						logger.error("Exception in Import Data execute() : ", e);
					}
					
					request.setAttribute(FORM_ERRORS, null);
					request.setAttribute(Globals.ERROR_KEY, null);
					request.setAttribute("myVitalsId", null);
						
					// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
					
					VaBenefitsForm vaBenefitsForm = veteransInformation.getVaBenefitsForms(excel, row, preNeed ? -20 : 0);
					
					if(vaBenefitsForm == null || vaBenefitsForm.getVitalsMasterKey().equals("") || vaBenefitsForm.getVitalsMasterKey().equals("0")) {
						continue;
					}
					request.setAttribute("myVitalsId", vaBenefitsForm.getVitalsMasterKey());
					processVaBenefitsAction.execute(processVaBenefitsMapping, vaBenefitsForm, request, response);
					
					formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
					errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
					
					try {
						if(formErrors != null && formErrors.size() > 0) {
							//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
						} else {
							//veteransInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
						}
					} catch (Exception e) {
						System.err.println("Error in VaBenefits - " + e.getMessage() + " at row : " + startRow);
						logger.error("Va Benefit - Field Error : " + formErrors != null ? formErrors.toString() : "");
						logger.error("Exception in Import Data execute() : ", e);
					}
					
					request.setAttribute(FORM_ERRORS, null);
					request.setAttribute(Globals.ERROR_KEY, null);
					request.setAttribute("myVitalsId", null);
					
					// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
					
					VaStoneForm vaStoneForm = veteransInformation.getVaStoneForms(excel, row, preNeed ? -20 : 0);
					if(vaStoneForm == null || vaStoneForm.getVitalsMasterKey().equals("") || vaStoneForm.getVitalsMasterKey().equals("0")) {
						continue;
					}
					request.setAttribute("myVitalsId", vaStoneForm.getVitalsMasterKey());
					processVaHeadstoneAction.execute(processVaHeadstoneMapping, vaStoneForm, request, response);
					
					formErrors = (List<String>) request.getAttribute(FORM_ERRORS);
					errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
					
					try {
						if(formErrors != null && formErrors.size() > 0) {
							//excel.markErrorOnCell(startRow, formErrors, errors.get(), getResources(request), preNeed);
						} else {
							// veteransInformation.updateSheet(index, 87 + (preNeed ? -20 : 0), ((DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER)).getCurrentCaseID());
						}
					} catch (Exception e) {
						System.err.println("Error in VaHeadstone - " + e.getMessage() + " at row : " + startRow);
						logger.error("Va Stone - Field Error : " + formErrors != null ? formErrors.toString() : "");
						logger.error("Exception in Import Data execute() : ", e);
					}
					
					request.setAttribute(FORM_ERRORS, null);
					request.setAttribute(Globals.ERROR_KEY, null);
					request.setAttribute("myVitalsId", null);
						
					// excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
				} catch (Exception e) {
					logger.error("Exception in Import Data execute() : ", e);
				}
			}
			
			try {
				//excel.updateWorkBook(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
				//ReadWriteExcel readWriteExcel = new ReadWriteExcel(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName());
				Integer[] records = excel.close(resources.getMessage(IMPORT_DATA_DIRECTORY) +  request.getSession().getId() + SLASH + formFile.getFileName(), preNeed ? -20 : 0);
				request.setAttribute("records", records);
				//request.setAttribute("records", new Integer[] {1, 0});
			} catch (Exception e) {
				logger.error("Exception in Import Data execute() : ", e);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return mapping.findForward("testShowImportData");
	}
    
    private FinancialInformationForm set(FinancialInformationForm pForm, FinancialInformationForm tForm) {
		pForm.setShowFinancingSection(tForm.isShowFinancingSection());
		pForm.setApplyFinanceCharges(tForm.getApplyFinanceCharges());
		pForm.setInterestRate(tForm.getInterestRate());
		pForm.setInterestFromDate(tForm.getInterestFromDate());
		pForm.setCustomerName(tForm.getCustomerName());
		pForm.setSendToAccounting(tForm.getSendToAccounting());
		pForm.setShowFinancingSection(tForm.isShowFinancingSection());
		pForm.setSaleDate(tForm.getSaleDate());
		pForm.setDueDate(tForm.getDueDate());
		pForm.setStmtDate(tForm.getStmtDate());
		pForm.setPaymentSource(tForm.getPaymentSource());
		pForm.setDateOfPayment(tForm.getDateOfPayment());
		pForm.setReceiptNumber(tForm.getReceiptNumber());
		pForm.setManualReceiptNumber(tForm.getManualReceiptNumber());
		pForm.setNonCashAdjustment(tForm.getNonCashAdjustment());
		pForm.setMethodOfPayment(tForm.getMethodOfPayment());
		pForm.setSalesDescCDID(tForm.getSalesDescCDID());
		pForm.setSaleType(tForm.getSaleType());
		pForm.setSource(tForm.getSource());
		pForm.setDisposition(tForm.getDisposition());
		pForm.setServicePlan(tForm.getServicePlan());
		pForm.setProvidedServices(tForm.getProvidedServices());
		pForm.setPreviousFuneralHomeUsed(tForm.getPreviousFuneralHomeUsed());
		pForm.setAdvertisingSource(tForm.getAdvertisingSource());
		pForm.setMiscNotes(tForm.getMiscNotes());
		
		return pForm;
    }
    
    private ActionMapping getActionMapping(ActionMapping mapping, String forwardString) {
    	ActionForward actionForward = mapping.findForward(forwardString);
        String returnPath = actionForward.getPath();
        int periodpos = returnPath.indexOf(".do");
        returnPath = returnPath.substring(0,periodpos);

        return (ActionMapping) modconf.findActionConfig(returnPath);
    }
}