package com.aldorsolutions.fdms.service;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.fdms.exception.*;
import com.aldorsolutions.fdms.service.extracter.*;
import com.aldorsolutions.fdms.to.common.*;
import com.aldorsolutions.fdms.to.fdmscase.DeceasedPerson;
import com.aldorsolutions.fdms.to.financial.Charge;
import com.aldorsolutions.fdms.to.service.FdmsAtNeedFinanceRequest;
import com.aldorsolutions.fdms.to.service.FdmsAtNeedRequest;
import com.aldorsolutions.fdms.to.service.FdmsCaseRequest;
import com.aldorsolutions.fdms.to.service.FdmsPreNeedFinanceRequest;
import com.aldorsolutions.fdms.to.service.FdmsPreNeedRequest;
import com.aldorsolutions.fdms.to.service.FdmsUserInfo;
import com.aldorsolutions.fdms.to.service.FdmsVitalsRequest;
import com.aldorsolutions.fdms.to.vitals.VitalsIdInfo;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.GeneralQueryDAO;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbCasePeer;
import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsExecutor;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.DbVitalsMedical;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.DbVitalsSchedule;
import com.aldorsolutions.webfdms.beans.DbVitalsSpouse;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
//import com.aldorsolutions.webfdms.beans.WSGreeting;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webservice.*;
import com.aldorsolutions.webservice.xsd.comm.StateCodeType;
import com.aldorsolutions.webservice.xsd.comm.StrMax10Len;
import com.aldorsolutions.webservice.xsd.comm.fdde.*;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciARDetailDataType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciCashType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractLineItemType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractType;
import com.aldorsolutions.webservice.xsd.fdde.service.*;


public class FdmsServiceImpl implements FdmsService {
	
	private static FdmsService instance = new FdmsServiceImpl();
	private FdmsServiceImpl(){};
	private Logger logger = Logger.getLogger(FdmsServiceImpl.class.getName());

	public static FdmsService getInstance(){
		return instance;
	}
	@Override
	public FdmsUserInfo getUserInfo(DbUserSession user) {
		
		FdmsUserInfo userInfo =  new FdmsUserInfo(user);
		populateLocalesandLocations(user, userInfo);
		return userInfo;
	}
	
	private void populateLocalesandLocations(DbUserSession user,
			FdmsUserInfo userInfo) {

		UserDAO userDao = new UserDAO();
		// locales that belong to a user
		ArrayList<UserLocaleDTO> locales = userDao.getUserLocales(user.getId());
		// all locales on dataDb.
		ArrayList<LocaleDTO> userLocales = FdmsDb.getInstance()
				.getLocalesForCompany(user.getDbLookup(), user.getCompanyID());

		UserManagerBean userManagerBean = new UserManagerBean();
		String[] locationIds = userManagerBean.getUserLocationIds(user.getId());

		/*
		 * get only IDs of the locale and locations that belong to a user.
		 * editform.setLocationIds( userManagerBean.getUserLocationIds( userID )
		 * ); editform.setLocaleIds( userManagerBean.getUserLocaleIds( userID )
		 * );
		 */

		DatabaseTransaction t = null;
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			for (LocaleDTO locale : userLocales) {
				for (UserLocaleDTO alocale : locales) {
					if (Integer.parseInt(alocale.getLocaleId()) == locale
							.getLocaleID()) {
						FdmsLocale fdmsLocale = new FdmsLocale(locale);
						userInfo.addLocale(fdmsLocale);
						DbLocation[] userLocations = FdmsDb.getInstance()
								.getLocationsForRegion(t, locale.getLocaleID());
						for (DbLocation location : userLocations) {
							for (int i = 0; i < locationIds.length; i++) {
								if (Integer.parseInt(locationIds[i]) == location
										.getId()) {
									FdmsLocation fdmsLocation = new FdmsLocation(
											location);
									fdmsLocale.addLocation(fdmsLocation);
								}
							}
						}
					}
				}

			}
		} catch (PersistenceException pe) {

		} catch (Exception pe) {

		} finally {
			t.closeConnection();
		}

	}

	public DbUserSession checkValidUser(String userName, String password)
			throws InvalidFdmsUserException, UserLockedException, UserExpiredException{
		boolean result = false;
		
		DbUserSession user = null;
		user = DbUser.findUserByUserName(userName);
		
		if (user != null) {
			
			if(user.isLocked()){
				throw new UserLockedException();
			}
			
			int expirationInterval = FdmsDb.getInstance().getExpirationInterval(user);
			if (expirationInterval < 0) {
				throw new UserExpiredException();
			}
			
            result = SecurityManagerBean.isValidPassword(password, user.getPassword());
            if (result) {
				return user;
			}	
		}
		throw new InvalidFdmsUserException();
	}

	@Override
	public int processCase(DbUserSession user, FdmsCaseRequest request) {
		
		int vitalsId = 0;
		if(request != null){
				if(request instanceof FdmsAtNeedRequest){
					vitalsId = createUpdateAtNeed(user,(FdmsAtNeedRequest)request);
				}else{
					vitalsId = createUpdatePreNeed(user,(FdmsPreNeedRequest) request);
				}
			}
		return vitalsId;
	}
	
	private int createUpdatePreNeed(DbUserSession user,
			FdmsPreNeedRequest preNeedRequest) {

		DatabaseTransaction t = null;
		DbPreneed dbPreNeed = null;
		DbVitalsDeceased vitals = null;
		DbCase acase = null;
		DbVitalsFirstCall firstCall = null;
		DbArrangers dbArranger = null;
		DbLocation dbLocation = null;
		DbVitalsSchedule sched = null;
		FdmsDb fdmsdb = null;
		int vitalsid = 0;
		String saledateymd = null;
		boolean isDuplicateCase = false;

		// TreeMap disbursementMap = (TreeMap)
		// session.getAttribute(Constants.PRENEED_DISBURSEMENT_MAP);
		// int disbursementMapSize = (disbursementMap != null) ?
		// disbursementMap.size() : 0;

		// check if it is duplicate we will not save.
		if (preNeedRequest.getCaseInfo() != null) {
			if (preNeedRequest.getCaseInfo().getDecedentCaseNumber() != null
					&& preNeedRequest.getCaseInfo().getDecedentCaseNumber()
							.trim().length() > 0) {
				// && (!form.getCaseCode().equals(form.getNextCaseNumber()))) {

				DbCase checkCase = new DbCase();
				checkCase.setNew();
				checkCase.setLocale(preNeedRequest.getLocaleId());

				// this is create a new vitalsid.
				if (preNeedRequest.getVitalsId() > 0) {
					checkCase.setId(preNeedRequest.getVitalsId());
				} else {
					checkCase.setId(0);

				}
				checkCase.setCaseCode(preNeedRequest.getCaseInfo()
						.getDecedentCaseNumber());
				DatabaseTransaction tmp = null;

				try {
					tmp = (DatabaseTransaction) DatabaseTransaction
							.getTransaction(user);
					if (FdmsDb.getInstance().checkCaseExists(tmp, checkCase,
							DbCasePeer.CASECODE)) {
						isDuplicateCase = true;
						logger.error("duplicate case.");
						// formErrors.add("caseNumber");
					}
				} catch (PersistenceException pe) {
					isDuplicateCase = true;
					logger.error("PersistenceException in doPerform() : ", pe);
					// errors.add(ActionErrors.GLOBAL_ERROR, new
					// ActionError("error.PersistenceException",
					// pe.getCause()));
				} catch (Exception pe) {
					isDuplicateCase = true;
					logger.error("Error in doPerform() : ", pe);
					// errors.add(ActionErrors.GLOBAL_ERROR, new
					// ActionError("error.GeneralException", pe.getMessage()));
				} finally {
					if (tmp != null) {
						try {
							tmp.closeConnection();
							tmp = null;
						} catch (Exception e) {
							logger.error("Error in closeConnection() : ", e);
						}
					}
				}
			}
		}
		
		
		if (!isDuplicateCase) {
			// Sale Date is required.
			saledateymd = convertDateToYYYYMMDD(preNeedRequest.getSalesInfo()
					.getSaleDate());

			try {

				t = (DatabaseTransaction) DatabaseTransaction
						.getTransaction(user);
				fdmsdb = FdmsDb.getInstance();
				// AppLog.trace("ProcessPreNeed: directive:"+form.getDirective());
				dbArranger = com.aldorsolutions.webfdms.beans.FdmsDb
						.getInstance().getArranger(t,
								preNeedRequest.getDirectorId());
				dbLocation = com.aldorsolutions.webfdms.beans.FdmsDb
						.getInstance().getLocation(t,
								preNeedRequest.getLocationId());

				// create a new preneed.
				boolean create = true;
				
				if (preNeedRequest.getVitalsId() > 0) {

					vitalsid = preNeedRequest.getVitalsId();
					dbPreNeed = fdmsdb.getPreneed(t, vitalsid);
					String prevStatus = dbPreNeed.getStatus();

					// Populate the dbPreNeed object from the form object
					populateDbPreNeedFromForm(dbArranger, dbPreNeed,
							preNeedRequest, !create);

					// Update vitals object for this new case-id
					vitals = fdmsdb.getVitalsDeceased(t, vitalsid);
					populateDbVitalsFromForm(vitals, preNeedRequest, !create);

					firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
					firstCall.setArrangerName(dbArranger.getName());
					firstCall.setArrangerID(preNeedRequest.getDirectorId());
					firstCall.setSource(preNeedRequest.getSource());

					firstCall.setArrangeDate(saledateymd);
					firstCall.setOriginalPnDate(saledateymd);

					// Update case object for new ID
					acase = fdmsdb.getCase(t, vitalsid);
					populateDbCaseFromForm(user, saledateymd, dbLocation,
							acase, preNeedRequest, !create);
					// Check if converting a preneed from ACTIVE to SERVICED
					if (prevStatus.equals(DbPreneed.ACTIVE)
							&& dbPreNeed.getStatus().equals(DbPreneed.SERVICED)) {
						sched = fdmsdb.getVitalsSchedule(t, vitalsid);
						sched.setDefaultAtNeedCheckList(preNeedRequest
								.getLocaleId(), user.getDbLookup());
					}
					// clean up
					t.save();

				} else {
					
					dbPreNeed = new DbPreneed();
					dbPreNeed.setNew();
					populateDbPreNeedFromForm(dbArranger, dbPreNeed,
							preNeedRequest, create);

					t.addPersistent(dbPreNeed);
					t.save();
//					t.closeConnection();
					t.removePersistent(dbPreNeed);

					// Update case object for this new case-id
					vitalsid = dbPreNeed.getId();

					// Set the Case Id in the DbUserSession Object
					user.setCurrentCaseID(vitalsid);

					// Update vitals object for this new case-id
//					t = (DatabaseTransaction) DatabaseTransaction
//							.getTransaction(user);
					vitals = fdmsdb.getVitalsDeceased(t, vitalsid);
					populateDbVitalsFromForm(vitals, preNeedRequest, create);
					
					t.save();

					// update firstcall
					firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
					firstCall.setArrangerName(dbArranger.getName());
					firstCall.setArrangerID(preNeedRequest.getDirectorId());
					// Note ArrangeDate is defined in both DbPreNeed and
					// DbVitalsFirstCall.
					// So, both fields in both Db clasess must be updated from
					// form.getSaleDate()
					firstCall.setArrangeDate(saledateymd);
					firstCall.setOriginalPnDate(saledateymd);
					
					t.save();

					sched = fdmsdb.getVitalsSchedule(t, vitalsid);
					sched.setDefaultPreNeedCheckList(preNeedRequest
							.getLocaleId(), user.getDbLookup());

					// Update case object for new ID
					acase = fdmsdb.getCase(t, vitalsid);
					populateDbCaseFromForm(user, saledateymd, dbLocation,
							acase, preNeedRequest, create);
					t.save();
					/*// Update survivor table
					String relation = "Deceased";

					relation = "Preneed";

					DbSurvivor.addUpdateSurvivor(t, vitalsid,
							DbSurvivor.DECEASED, "", vitals.getDecFName(),
							vitals.getDecMName(), vitals.getDecLName(), "", "",
							"", vitals.getDecResStreet() + " "
									+ vitals.getDecAptNo(), "", vitals
									.getDecResMailCity(), vitals
									.getDecResState(), vitals.getDecResZip(),
							"", "", "", relation, "", "");
					String street = "";
					String street1 = "";
					String street2 = "";
					if (dbPreNeed.getBuyerStreet() == null
							|| dbPreNeed.getBuyerStreet().length() < 1) {
						street1 = "";
					} else {
						street1 = dbPreNeed.getBuyerStreet();
					}
					if (dbPreNeed.getBuyerAptNo() == null
							|| dbPreNeed.getBuyerAptNo().length() < 1) {
						street2 = "";
					} else {
						street2 = dbPreNeed.getBuyerAptNo();
					}
					if (street1.length() > 0 || street2.length() > 0) {
						street = street1 + " " + street2;
					} else {
						street = "";
					}
					DbSurvivor.addUpdateSurvivor(t, vitalsid,
							DbSurvivor.INFORMANT, "",
							dbPreNeed.getBuyerFirst(), dbPreNeed
									.getBuyerMiddle(),
							dbPreNeed.getBuyerLast(), "", "", "", street, "",
							dbPreNeed.getBuyerCity(),
							dbPreNeed.getBuyerState(), dbPreNeed.getBuyerZip(),
							dbPreNeed.getBuyerPhone(), "", "", "Informant", "",
							"");*/
					
					
				}

			} catch (PersistenceException pe) {
				logger.error("ProcessPreNeed: Persistence Exception. " + pe);
			} catch (Exception pe) {
				logger.error("ProcessPreNeed Exception. ", pe);
			} finally {
				if (t != null)
					t.closeConnection();
			}

		}
		return vitalsid;
	}
	
	private void populateDbCaseFromForm(DbUserSession user, String saledateymd,
			DbLocation location, DbCase acase,
			FdmsPreNeedRequest preNeedRequest, boolean isCreate)
			throws Exception {

		setStringArgument("setChapelLocation", acase, location.getName(), isCreate);

		acase.setLocale(preNeedRequest.getLocaleId());

		acase.setChapelNumber(preNeedRequest.getLocationId());
		acase.setSaleDate(saledateymd); // sale date key

		if(preNeedRequest.getCaseInfo() != null){
			setStringArgument("setCaseCode", acase, preNeedRequest.getCaseInfo()
				.getDecedentCaseNumber(), isCreate);
		}

	}
	private void populateDbVitalsFromForm(DbVitalsDeceased vitals,
			FdmsPreNeedRequest preNeedRequest, boolean isCreate) throws Exception {

		Person insured = preNeedRequest.getInsured();
		setStringArgument("setDecmrmrs", vitals, insured.getName()
				.getSalutation(), isCreate);
		setStringArgument("setDecFName", vitals, insured.getName()
				.getFirstName(), isCreate);
		setStringArgument("setDecLName", vitals, insured.getName()
				.getLastName(), isCreate);
		setStringArgument("setDecMName", vitals, insured.getName()
				.getMiddleName(), isCreate);

		// vitals.setDecResStreet(form.getBeneficiaryStreet());
		// vitals.setDecResMailCity(form.getBeneficiaryCity());
		// if (form.getBeneficiaryState().length() == 2) {
		// vitals.setDecResState(form.getBeneficiaryState().toUpperCase());
		// }
		// else {
		// vitals.setDecResState(form.getBeneficiaryState());
		// }
		// vitals.setDecResZip(form.getBeneficiaryZipCode().toUpperCase());
		// vitals.setSSNo(FormatString.removeDashes(form.getBeneficiarySocialSecurityNumber()));
		// vitals.setDecFullName(form.getBeneficiaryFirst()+" "+form.getBeneficiaryLast());
		// try {
		// vitals.setDateOfBurial(FormatDate.convertToDateMMDDYYYY(form.getServiceDate()));
		// vitals.setServiceDateKey(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
		// vitals.setDateOfBirth(FormatDate.convertToDateMMDDYYYY(form.getBeneficiaryDOB()));
		// } catch (Exception e) {
		// errors.add(ActionErrors.GLOBAL_ERROR, new
		// ActionError("error.ui.setData"));
		// }

	}
	private void populateDbPreNeedFromForm(
	        DbArrangers dbArranger,
	        DbPreneed dbPreNeed,
	        FdmsPreNeedRequest preNeedRequest, boolean isCreate)
			throws Exception {

		// set Buyer.
		RelatedPerson buyer = preNeedRequest.getBuyer();

		setStringArgument("setBuyerFirst", dbPreNeed, buyer.getName()
				.getFirstName(), isCreate);
		setStringArgument("setBuyerTitle", dbPreNeed, buyer.getName()
				.getSalutation(), isCreate);
		setStringArgument("setBuyerMiddle", dbPreNeed, buyer.getName()
				.getMiddleName(), isCreate);
		setStringArgument("setBuyerLast", dbPreNeed, buyer.getName()
				.getLastName(), isCreate);
		
		if (buyer.getAddress() != null) {
			if(buyer.getAddress().getStreets() != null && buyer.getAddress().getStreets().size() > 0){
				setStringArgument("setBuyerStreet", dbPreNeed, buyer.getAddress().getStreets().get(0), isCreate);
			}
			setStringArgument("setBuyerCity", dbPreNeed, buyer.getAddress().getCity(), isCreate);
		}
		

		// if (form.getBuyerState().toUpperCase().length() == 2) {
		// dbPreNeed.setBuyerState(form.getBuyerState().toUpperCase() );
		// }
		// else {
		// dbPreNeed.setBuyerState(form.getBuyerState());
		// }
		// dbPreNeed.setBuyerZip(form.getBuyerZipCode().toUpperCase() );
		// dbPreNeed.setCobuyer(form.getCoBuyerName());
		// dbPreNeed.setBuyerPhone(form.getBuyerPhone());
		// dbPreNeed.setBuyerSsNo(FormatString.removeDashes(form.getBuyerSocialSecurityNumber()));

		dbPreNeed.setCounselor(dbArranger.getName());
		// dbPreNeed.setSource(form.getSource());
		// dbPreNeed.setComments(form.getComments());

		// Note ArrangeDate is defined in both DbPreNeed and DbVitalsFirstCall.
		// So, both fields in both Db clasess must be updated from
		// form.getSaleDate()

		String saledateymd = convertDateToYYYYMMDD(preNeedRequest.getSalesInfo()
				.getSaleDate());

		dbPreNeed.setArrangeDate(saledateymd);

		// dbPreNeed.setServiceType(form.getService());
		// dbPreNeed.setCasketName(form.getCasket());
		// dbPreNeed.setVaultName(form.getVault());
		// dbPreNeed.setUrnName(form.getUrn());

		// try {
		// dbPreNeed.setServiceAmt(FormatCurrency.convertToCurrency(form.getServiceSale()));
		// } catch(Exception e){
		// errors.add("serviceSale",new
		// ActionError("error.ui.preneed.serviceamt"));
		// formErrors.add("serviceSale");
		// }
		//	        
		// try {
		// dbPreNeed.setCasketAmt(FormatCurrency.convertToCurrency(form.getCasketSale()));
		// } catch(Exception e){
		// errors.add("casketSale",new
		// ActionError("error.ui.preneed.casketamt"));
		// formErrors.add("casketSale");
		// }
		//	        
		// try {
		// dbPreNeed.setVaultAmt(FormatCurrency.convertToCurrency(form.getVaultSale()));
		// } catch(Exception e){
		// errors.add("vaultSale",new ActionError("error.ui.preneed.vaultamt"));
		// formErrors.add("vaultSale");
		// }
		//	        
		// try {
		// dbPreNeed.setUrnAmt(FormatCurrency.convertToCurrency(form.getUrnSale()));
		// } catch(Exception e){
		// errors.add("urnSale",new ActionError("error.ui.preneed.urnamt"));
		// formErrors.add("urnSale");
		// }
		//	        
		// try {
		// dbPreNeed.setGSTAmt(FormatCurrency.convertToCurrency(form.getGstAmt()));
		// } catch(Exception e){
		// errors.add("gstAmt",new ActionError("error.ui.preneed.gstamt"));
		// formErrors.add("gstAmt");
		// }
		//	        
		// try {
		// dbPreNeed.setOtherAmt(FormatCurrency.convertToCurrency(form.getOtherSale()));
		// } catch(Exception e){
		// errors.add("otherSale",new ActionError("error.ui.preneed.otheramt"));
		// formErrors.add("otherSale");
		// }
		//	        
		// dbPreNeed.setFundType(form.getFundDepositoryType());
		// dbPreNeed.setDepository(form.getFundsWith());
		//	        
		// try {
		// dbPreNeed.setFaceValue(FormatCurrency.convertToCurrency(form.getFaceValue()));
		// } catch(Exception e){
		// errors.add("faceValue",new
		// ActionError("error.ui.preneed.facevalue"));
		// formErrors.add("faceValue");
		// }
		//	        
		// try {
		// dbPreNeed.setContractAmt(FormatCurrency.convertToCurrency(form.getContractAmount()));
		// } catch(Exception e){
		// errors.add("contractAmount",new
		// ActionError("error.ui.preneed.contractamt"));
		// formErrors.add("contractAmount");
		// }
		//	        
		// try {
		// dbPreNeed.setContractDate(FormatDate.convertToDateMMDDYYYY(form.getDateStarted()));
		// } catch(Exception e){
		// errors.add("dateStarted",new
		// ActionError("error.ui.preneed.date.started"));
		// formErrors.add("dateStarted");
		// }
		//	        
		// try {
		// dbPreNeed.setMaturityDate(FormatDate.convertToDateMMDDYYYY(form.getMaturity()));
		// } catch(Exception e){
		// errors.add("maturity",new
		// ActionError("error.ui.preneed.date.maturity"));
		// formErrors.add("maturity");
		// }

		// try {
		// dbPreNeed.setPaidYTD(FormatCurrency.convertToCurrency(form.getYtdPaid()));
		// } catch(Exception e){
		// errors.add("ytdPaid",new ActionError("error.ui.preneed.ytdpaid"));
		// formErrors.add("ytdPaid");
		// }
		//	        
		// try {
		// dbPreNeed.setPaidTotal(FormatCurrency.convertToCurrency(form.getTotalPaid()));
		// } catch(Exception e){
		// errors.add("totalPaid",new
		// ActionError("error.ui.preneed.totalpaid"));
		// formErrors.add("totalPaid");
		// }
		//	        
		// try {
		// dbPreNeed.setInterestYtd(FormatCurrency.convertToCurrency(form.getYtdInterest()));
		// } catch(Exception e){
		// errors.add("ytdInterest",new
		// ActionError("error.ui.preneed.ytdinterest"));
		// formErrors.add("ytdInterest");
		// }
		//	        
		// try {
		// dbPreNeed.setInterestTotal(FormatCurrency.convertToCurrency(form.getTotalInterest()));
		// } catch(Exception e){
		// errors.add("totalInterest",new
		// ActionError("error.ui.preneed.totalInterest"));
		// formErrors.add("totalInterest");
		// }
		//	        
		// dbPreNeed.setFundAcctNo(form.getAccountNumber());
		// dbPreNeed.setInterestRate(FormatNumber.parseFloat(form.getEstIntRate()));
		//	        
		// try {
		// dbPreNeed.setMgmtFee(FormatCurrency.convertToCurrency(form.getManagementFee()));
		// } catch(Exception e){
		// errors.add("managementFee",new
		// ActionError("error.ui.preneed.managementfee"));
		// formErrors.add("managementFee");
		// }
		//	        
		// try {
		// dbPreNeed.setCommission(FormatCurrency.convertToCurrency(form.getCommission()));
		// } catch(Exception e){
		// errors.add("commission",new
		// ActionError("error.ui.preneed.commission"));
		// formErrors.add("commission");
		// }
		//	        
		// try {
		// dbPreNeed.setLastPmtDate(FormatDate.convertToDateMMDDYYYY(form.getLastPaymentDate()));
		// } catch(Exception e){
		// errors.add("lastPaymentDate",new
		// ActionError("error.ui.preneed.lastpmtdate"));
		// formErrors.add("lastPaymentDate");
		// }
		//	        
		// try {
		// dbPreNeed.setLastPmtAmt(FormatCurrency.convertToCurrency(form.getLastPaymentAmount()));
		// } catch(Exception e){
		// errors.add("lastPaymentAmount",new
		// ActionError("error.ui.preneed.lastpmtamt"));
		// formErrors.add("lastPaymentAmount");
		// }
		//	                
		// if (form.getPreneedStatus() != null){
		// dbPreNeed.setStatus(form.getPreneedStatus().substring(0,1));
		// } else {
		// errors.add(ActionErrors.GLOBAL_ERROR,new
		// ActionError("error.ui.preneed.status"));
		// formErrors.add("preneedStatus");
		// }

	}
	
	
	
	
	private int createUpdateAtNeed(DbUserSession user, FdmsAtNeedRequest request) {
		int vitalsId = 0;
		DatabaseTransaction t = null;

		FdmsDb fdmsdb = null;
		DbVitalsDeceased deceased = null;
		DbVitalsInformant informant = null;
		DbVitalsFirstCall firstCall = null;
		DbCase caseinfo = null;
		DbVitalsNextKin nextkin = null;
		DbPreneed preneed = null;
		DbVitalsSchedule sched = null;
		DbVitalsExecutor executor = null;
		DbServices srv = null;
		boolean addmode = false;
		int vitalsid = 0;

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			fdmsdb = FdmsDb.getInstance();
			if (request.getVitalsId() > 0) {
				deceased = fdmsdb.getVitalsDeceased(t, request.getVitalsId());
			} else {
				deceased = new DbVitalsDeceased();
				deceased.setNew();
				addmode = true;
			}

			setDeceased(request, deceased);
			t.addPersistent(deceased);
			t.save();
			t.removePersistent(deceased);

			vitalsId = deceased.getId();
			caseinfo = FdmsDb.getInstance().getCase(t, vitalsId);
			setCaseInfo(t, request, caseinfo);
			t.save();

			firstCall = FdmsDb.getInstance().getVitalsFirstCall(t, vitalsId);
			setFirstCall(t, request, firstCall);
			t.save();

		} catch (PersistenceException pe) {
			logger.error("PersistenceException in doPerform() : ", pe);
			// errors.add(ActionErrors.GLOBAL_ERROR, new
			// ActionError("error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Error in doPerform() : ", pe);
			// errors.add(ActionErrors.GLOBAL_ERROR, new
			// ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				try {
					t.closeConnection();
					t = null;
				} catch (Exception e) {
					logger.error("Error in closeConnection() : ", e);
				}
			}
		}
		return vitalsId;
	}
	
	

	private void setFirstCall(DatabaseTransaction t, FdmsCaseRequest request,
			DbVitalsFirstCall firstCall) {
		
		DbArrangers dbArranger = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getArranger(t,request.getDirectorId());
		firstCall.setArrangerName(dbArranger.getName());
		firstCall.setArrangerID(dbArranger.getId());
		firstCall.setDirectorLicense(dbArranger.getLicenseNumber());
		
	}
	private void setCaseInfo(DatabaseTransaction t, FdmsCaseRequest request, DbCase caseinfo ) {
		DbLocation dbLocation = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getLocation(t, request.getLocationId());
		caseinfo.setChapelLocation(dbLocation.getName());
		caseinfo.setChapelNumber(dbLocation.getId());
		caseinfo.setLocale(dbLocation.getLocaleNumber());
		if(request.getCaseInfo()!= null){
			String contractCode = request.getCaseInfo().getDecedentContractNumber()!= null ? request.getCaseInfo().getDecedentContractNumber() : "";
			caseinfo.setContractCode(contractCode);
			String caseCode = request.getCaseInfo().getDecedentCaseNumber()!= null ? request.getCaseInfo().getDecedentCaseNumber() : "";
			caseinfo.setCaseCode(caseCode);
		}
	}
	private void setDeceased(FdmsAtNeedRequest request, DbVitalsDeceased deceased) {
		Name name = request.getDeceased().getName();
		deceased.setFirstName(name.getFirstName());
		deceased.setLastName(name.getLastName());
		deceased.setDecFullName(request.getDeceased().getMemorialName());
		deceased.setServiceDateKey(convertDateToYYYYMMDD(request.getDeceased().getServiceDate()));
		deceased.setDateOfBurial(convertDateToYYYYMMDD(request.getDeceased().getServiceDate()));
	}
	@Override
	public boolean validateLocaleAndLocation(int localeId, int locationId, DbUserSession user) {
		
		UserDAO userDao = new UserDAO();
		boolean result = userDao.verifyUserLocaleAndLocation(localeId, locationId, user.getId());

		return result;
	}
	@Override
	public boolean processFinance(DbUserSession user,FdmsPreNeedFinanceRequest fdmsPreNeedFinanceRequest) {
		boolean status = false;
		
		if ( (fdmsPreNeedFinanceRequest != null ) && (fdmsPreNeedFinanceRequest instanceof FdmsAtNeedFinanceRequest) ){
			status = createAtNeedFinance(user, (FdmsAtNeedFinanceRequest)fdmsPreNeedFinanceRequest);
		}
		
		return status;
	}
	
	
	private boolean createAtNeedFinance(DbUserSession user,	FdmsAtNeedFinanceRequest fdmsAtNeedFinanceRequest) {
		
		
		boolean status = false;
		DatabaseTransaction t = null;
		DbFinancialSummary finan = null;
	    DbVitalsFirstCall firstcall = null;
	    DbCase aCase = null;
	    DbPreneed preneed = null;
	    DbVitalsDeceased vitals = null;
		
		
		
		int vitalsid = fdmsAtNeedFinanceRequest.getVitalsId();
		try {
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
			finan = FdmsDb.getInstance().getFinancial(t,vitalsid);
            firstcall = FdmsDb.getInstance().getVitalsFirstCall(t,vitalsid);
            preneed = FdmsDb.getInstance().getPreneed(t, vitalsid);
            aCase = FdmsDb.getInstance().getCase(t, vitalsid);
            vitals = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
			
            if(finan == null ){
                // add to financial summary table for this case
                finan = new DbFinancialSummary();
                finan.setNew();
                t.addPersistent(finan);
                finan.setId(vitalsid );
            }
            short checkbox = 0;
            
            finan.setCAdvertisingSource(fdmsAtNeedFinanceRequest.getFinancialInfo().getSource());
            PriceListManager plm = new PriceListManager();
            finan.setCPriceListTable(plm.getDefaultPriceList(t, vitalsid));
            
            if (fdmsAtNeedFinanceRequest.getFinanceChargeOption() != null) {
            	finan.setIFinServiceChargesBox((short) 1);
            	firstcall.setInterestFromDate( (convertDateToYYYYMMDD(fdmsAtNeedFinanceRequest.getFinanceChargeOption().getInterestFromDate())));
            	finan.setFFinServiceChargeRate( fdmsAtNeedFinanceRequest.getFinanceChargeOption().getMonthlyInterestRate());
            	
            }
            firstcall.setDispositionCode(fdmsAtNeedFinanceRequest.getFinancialInfo().getDisposition());
            vitals.setCustomerName(vitals.getDecFullName());
            
            user.setRegion(aCase.getLocale());
            user.setLocationId(aCase.getChapelNumber());
            
            LocaleDTO locale = FdmsDb.getInstance().getLocale(user.getDbLookup(), aCase.getLocale());
            finan.setCFinDueDate(convertDateToYYYYMMDD(fdmsAtNeedFinanceRequest.getFinancialInfo().getDueDate()));
            finan.setCFinSaleDate(convertDateToYYYYMMDD(fdmsAtNeedFinanceRequest.getFinancialInfo().getInvoiceDate()));
            finan.setCFinDateInvoiceSent(convertDateToYYYYMMDD(fdmsAtNeedFinanceRequest.getFinancialInfo().getLastStatementDate()));
            finan.setCPreviousFhUsed(fdmsAtNeedFinanceRequest.getFinancialInfo().getWhatFuneralHomeDidFamilyPreviouslyUse());
            finan.setCFamilyPreviouslyServed( fdmsAtNeedFinanceRequest.getFinancialInfo().isHaveWeEverProvidedServicesForThisFamily());
            
            //SalesType for everybody
            finan.setSalesDescCDID(Long.parseLong(fdmsAtNeedFinanceRequest.getFinancialInfo().getSalesType()));
            //ServiceType. for keystone
            finan.setCFinSaleType(fdmsAtNeedFinanceRequest.getFinancialInfo().getServiceType());	
            
            int sent=0;
            int action = FdmsDb.FINANCIAL_SAVE_UNPOSTED;
            finan.setIARsentBox(        (short)sent);
            finan.setCFinMiscNotes(fdmsAtNeedFinanceRequest.getFinancialInfo().getMiscNotes());
            firstcall.setMarketingPlan(fdmsAtNeedFinanceRequest.getFinancialInfo().getServicePlan());
            preneed.setSource(fdmsAtNeedFinanceRequest.getFinancialInfo().getSource());
            
            aCase.setSaleDate(FormatDate.convertDateToYYYYMMDD(fdmsAtNeedFinanceRequest.getFinancialInfo().getInvoiceDate()));
            
            int depositId = FdmsDb.getInstance().getDepositIdForCase(t, vitalsid);
            
            int amountpaid = 0;

            Date depositDate = null;
            Date datePaid = null;
            
            if (fdmsAtNeedFinanceRequest.getDepositInfo() != null) {
            	amountpaid = FormatCurrency.convertToCurrency(String.valueOf(fdmsAtNeedFinanceRequest.getDepositInfo().getDepositAmount()));
            	//Adjustment Type
                String ncaValue = fdmsAtNeedFinanceRequest.getDepositInfo().getAdjustmentType();
                depositDate = fdmsAtNeedFinanceRequest.getDepositInfo().getDepositDate();
                datePaid = fdmsAtNeedFinanceRequest.getDepositInfo().getDatePaid();
                
            }
            
            
            if (fdmsAtNeedFinanceRequest.getCharges() != null && fdmsAtNeedFinanceRequest.getCharges().size() > 0) {
            	//set Charges to lineItems
               	TreeMap<Integer,FinancialInformationLineItem> financialInformationMap = setChargesForCase(fdmsAtNeedFinanceRequest, fdmsAtNeedFinanceRequest.getVitalsId(),user);
            	FdmsDb.getInstance().postFinancial( t, action, finan, financialInformationMap, firstcall, locale,user.getDbLookup());
            }
            t.save();
            status = true;
	
		} catch(PersistenceException pe) {
            logger.error("Persistence Exception in SaveFinancial do.Perform. " + pe, pe);
                     
        } catch(Exception pe) {
            logger.error("Exception in  SaveFinancial .doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
		
		return status;
	}
	
	private TreeMap<Integer,FinancialInformationLineItem> setChargesForCase(FdmsAtNeedFinanceRequest fdmsAtNeedFinanceRequest, int vitalsId, DbUserSession user) {
		TreeMap <Integer,FinancialInformationLineItem> chargeSet = new TreeMap <Integer,FinancialInformationLineItem>();
		List<Charge> charges = fdmsAtNeedFinanceRequest.getCharges();
		for(Charge aCharge : charges) {	
			setCharge( fdmsAtNeedFinanceRequest, aCharge, vitalsId, user, chargeSet);
		}
		return chargeSet;
	}
	
	private void setCharge(FdmsAtNeedFinanceRequest fdmsAtNeedFinanceRequest, Charge charge, int vitalsId, DbUserSession user, 
			TreeMap <Integer,FinancialInformationLineItem> chargeSet) {

		DatabaseTransaction t = null;
		
		DbChargeItem dbChargeItem = null;
		
		int asequenceNumber = charge.getSequenceNo();
		String adescription = charge.getDescription();
		int aamount = (int) (charge.getAmount()*100);
		String ataxCode = charge.getTaxCode();
		boolean aFromPackage = charge.isFromPackage();
		
		int aListID = 0;
		int ainvSeqNo = 0;
		int atype = 0;
		int arecordNumber = 0;
		String aGlAcct = "";
		int aexemptAmount = 0;
		char aspfCode;
		String ainvItemName = "";
		String aitemCategoryType = "";
		int aorigPrice = 0;
		int aPackageId = 0;
		
		if (charge.getMarchandiseItem() != null){
			//charge is merchandise item.
			ainvSeqNo = charge.getMarchandiseItem().getInvMasterKey();
			try {
				t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
				DbInvMaster priceList = FdmsDb.getInstance().getInvMaster(t, ainvSeqNo);
				
				 atype = (int)priceList.getContractLineNo();
		            aGlAcct = priceList.getCGLsalesAcct();
		            aexemptAmount = (int)((priceList.getFTaxExempt()*100)+.005);
		            aspfCode = 'S';
		            ainvItemName = priceList.getCItemName();
		            
		            String priceCategory = "!";
		            priceCategory = FdmsDb.getInstance().getSpeedDataIgnoreCase(
	                        user.getDbLookup(), 
	                        user.getRegion(),
	                        "PRODLINE",
	                        priceList.getCProductLine());
		            
		            
		            aitemCategoryType = priceCategory;
		            aorigPrice = priceList.getLPrice();
		            
		            dbChargeItem = new DbChargeItem(vitalsId, asequenceNumber,  arecordNumber, 
					   				 atype, adescription, aamount,
									 aGlAcct,  aexemptAmount, ataxCode,
									 aspfCode,  ainvItemName,  aitemCategoryType,
									ainvSeqNo,  aorigPrice, aListID, aFromPackage, aPackageId);
		            
		            
		            FinancialInformationLineItem collectionItem = new FinancialInformationLineItem(dbChargeItem);
		            collectionItem.setNewItem(true); 
		            
		            if (priceList.getCStockType().equalsIgnoreCase(DbInvMaster.STOCK_TYPE_SERIAL)){                            
                        collectionItem.setStockType(DbInvMaster.STOCK_TYPE_SERIAL);
                        collectionItem.setSerialNumber("- Select -");
                        collectionItem.setSerialNumberModifiable(1);// it is modifiable
                    }
		            InventorySold soldData = null;
		            if (dbChargeItem.getGlAcct() == null
                            || dbChargeItem.getGlAcct().compareTo("        ") <= 0) {
  
                            soldData = new InventorySold();
                            FdmsDb.getInstance().getDefaultGlSalesAccts(
                                t,
                                soldData,
                                user.getRegion(), 
                                user.getLocationId(),  
                                String.valueOf(dbChargeItem.getItemCategoryType()), 
                                fdmsAtNeedFinanceRequest.getFinancialInfo().getSalesType(), 
                                fdmsAtNeedFinanceRequest.getFinancialInfo().getDisposition());
                            collectionItem.setItemGLAccount(soldData.getAcctRevenue());
                        } else {
                            // set display GL account got DbCharge object's gl account
                            collectionItem.setItemGLAccount( dbChargeItem.getGlAcct());
                        }
                        
                       chargeSet.put(asequenceNumber, collectionItem);
     
		            
			} catch(PersistenceException pe) {
                //AppLog.criticalError("Persistence Exception in ProcessFinancialAddServices do.Perform. "+pe.getCause());
                logger.error("PersistenceException in setCharge() : " + pe);
               
            } catch(Exception pe) {
                //AppLog.criticalError("Exception in  ProcessFinancialAddServices .doPerform. "+pe);
                //pe.printStackTrace();
                logger.error("Error in setCharge() : ", pe);
               
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
			
		}else {
			//Charge is a service item.
			aListID = charge.getServiceItemId();
			com.aldorsolutions.webfdms.beans.DbPriceList priceList = null;
			try {
				t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
				PriceListManager plm = new PriceListManager();
	            priceList = plm.getPriceListItem(t,aListID);
	            
	            atype = priceList.getContrLine();
	            aGlAcct = priceList.getGlAcctNo();
	            aexemptAmount = (int)priceList.getTaxExempt();
	            aspfCode = 'S';
	            ainvItemName = "";
	            aitemCategoryType = priceList.getCategory();
	            ainvSeqNo = 0;
	            aorigPrice = (int)priceList.getPrice();
	            
	            dbChargeItem = new DbChargeItem(vitalsId, asequenceNumber,  arecordNumber, 
		   				 atype, adescription, aamount,
						 aGlAcct,  aexemptAmount, ataxCode,
						 aspfCode,  ainvItemName,  aitemCategoryType,
						ainvSeqNo,  aorigPrice, aListID, aFromPackage, aPackageId);
	            
	            FinancialInformationLineItem collectionItem = new FinancialInformationLineItem(dbChargeItem);
	            InventorySold soldData = null;
	            if (dbChargeItem.getGlAcct() == null || dbChargeItem.getGlAcct().compareTo("        ") <= 0){
                    soldData = new InventorySold();
                    FdmsDb.getInstance().getDefaultGlSalesAccts(
                        t,
                        soldData,
                        user.getRegion(),
                        user.getLocationId(),
                        String.valueOf(dbChargeItem.getItemCategoryType()),
                        fdmsAtNeedFinanceRequest.getFinancialInfo().getSalesType(),
                        fdmsAtNeedFinanceRequest.getFinancialInfo().getDisposition());
                    collectionItem.setItemGLAccount( soldData.getAcctRevenue() );
                } else {
                    // set display GL account got DbCharge object's gl account
                    collectionItem.setItemGLAccount( dbChargeItem.getGlAcct());
                }
	            
	            collectionItem.setModifiedItem(true); // modified this session
            	collectionItem.setNewItem(true); // added this session
            	chargeSet.put(asequenceNumber, collectionItem);
	            
			 } catch(PersistenceException pe) {
	                //AppLog.criticalError("Persistence Exception in ProcessFinancialAddServices do.Perform. "+pe.getCause());
	                logger.error("PersistenceException in setCharge() : " + pe);
	               
	            } catch(Exception pe) {
	                //AppLog.criticalError("Exception in  ProcessFinancialAddServices .doPerform. "+pe);
	                //pe.printStackTrace();
	                logger.error("Error in setCharge() : ", pe);
	               
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
		}
	}
	
	
	@Override
	public int validateVitalsId(int vitalsId, DbUserSession user) {
		
		UserDAO userDao = new UserDAO();
		int result = userDao.verifyVitalsId( vitalsId,user);
		return result;
	}
	
	@Override
	public boolean processVitals(DbUserSession user, FdmsVitalsRequest fdmsVitalsRequest) {
		
		boolean status = false;
		if ( (fdmsVitalsRequest != null ) && (fdmsVitalsRequest instanceof FdmsVitalsRequest)  ){
			status = updateVitals(user, (FdmsVitalsRequest)fdmsVitalsRequest);
		}
		return status;
	}
	
	private boolean updateVitals(DbUserSession user, FdmsVitalsRequest fdmsVitalsRequest) {
		boolean status = false;
		
		DatabaseTransaction t = null;
        DbVitalsDeceased  	deceased 	= null;
        DbVitalsInformant 	informant 	= null;
        DbVitalsFirstCall 	firstCall 	= null;
        DbCase 			    caseinfo 	= null;
        DbVitalsNextKin		nextkin		= null;
        DbVitalsSpouse		spouse		= null;
        DbVitalsMedical		medical		= null;
        DbArrangers			arranger	= null;
        DbServices 			srv 		= null;
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            
        int vitalsid = fdmsVitalsRequest.getVitalsid();
        
        // initial data to the object from DB.
        deceased  = FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
        informant = FdmsDb.getInstance().getVitalsInformant(t,vitalsid);
        firstCall = FdmsDb.getInstance().getVitalsFirstCall(t,vitalsid);
        caseinfo  = FdmsDb.getInstance().getCase(t, vitalsid);
        nextkin	  = FdmsDb.getInstance().getVitalsNextKin(t, vitalsid);
        spouse	  = FdmsDb.getInstance().getVitalsSpouse(t, vitalsid);
        medical	  = FdmsDb.getInstance().getVitalsMedical(t, vitalsid);
        srv		  = FdmsDb.getInstance().getServices(t, vitalsid);
        
        // If this is a new case create a services record so that data can be pushed forward
        if(srv == null ){
            // add to services table for this case
            srv = new DbServices();
            srv.setNew();
            t.addPersistent(srv);
            srv.setLSrvMainKey(vitalsid );
        }
        updateDatabase(deceased, informant,firstCall,caseinfo,nextkin,spouse,medical,srv,fdmsVitalsRequest, false);
        
        t.save();
        status = true;
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in updateVitals " + pe, pe);
        } catch(Exception pe) {
            logger.error("Exception in  updateVitals ", pe);
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
		return status;
	}
	private void updateDatabase(DbVitalsDeceased deceased,
			DbVitalsInformant informant, DbVitalsFirstCall firstCall,
			DbCase caseinfo, DbVitalsNextKin nextkin, DbVitalsSpouse spouse,
			DbVitalsMedical medical, DbServices srv,
			FdmsVitalsRequest fdmsVitalsRequest, boolean isCreate) throws Exception{
		
			
		setStringArgument("setIdentificationMarks", deceased, fdmsVitalsRequest.getIdTagNumber(), isCreate);	
		
		if(fdmsVitalsRequest.getDeceased() != null){
			DeceasedPerson decasedRequest = fdmsVitalsRequest.getDeceased();
			if(decasedRequest.getName() != null){
				Name name = decasedRequest.getName();
				
				setStringArgument("setDecFName", deceased, name.getFirstName(), isCreate);
				setStringArgument("setDecMName", deceased, name.getMiddleName(), isCreate);
				setStringArgument("setDecLName", deceased, name.getLastName(), isCreate);
			}
			
//	     deceased.setCountyOfBirth( vitalform.getCountyOfBirth() );  
//	     deceased.setCountyOfIssue( vitalform.getCountyOfIssue()); 
	     setStringArgument("setSex", deceased, decasedRequest.getGender(), isCreate);
	     
		}

	}
	
	//use this one for regular date store in FDMS DB.
	//DeathDateKey,ServiceDateKey,SaleDateKey
	private String convertDateToYYYYMMDD(Date date){
		return FormatDate.convertDateToYYYYMMDD(date);
	}
	
	//use this one for DOB, DOD
	private String convertDateToMMDDYYYY(Date date){
		return FormatDate.convertDateToMMDDYYYY(date);
	}
	
	private String getYesNoString (boolean value){
		return value ? "Yes" : "No";
	}
	
	private void setStringArgument(String methodName, Object object, String value, boolean create) throws Exception{
		if(create){
			value = value != null ? value : "";
		}
		if(value != null){
			Method method = object.getClass().getMethod(methodName, String.class);
			method.invoke(object, value.trim());
		}
	}
	
	private boolean validateLocale(int localeId, DbUserSession user) {
		UserDAO userDao = new UserDAO();
		return userDao.verifyLocale(user.getId(), localeId);
	}
	
	@Override
	public boolean validateLocaleAndArranger(int localeId, int directorId, DbUserSession user) {
		UserDAO userDao = new UserDAO();
		boolean result = userDao.verifyLocaleAndArranger( localeId, directorId ,user);
		return result;
	}
	@Override
	public void validateLocaleLocationAndArranger(int localeId, int locationId,
			int directorId, DbUserSession user)
			throws InvalidFdmsContextException {
		if(!validateLocaleAndLocation(localeId, locationId, user)){
			throw new InvalidFdmsContextException("Given localeId and locationId are not related.");
		}else if(!validateLocaleAndArranger(localeId, directorId, user)){
			throw new InvalidFdmsContextException("Given localeId and arrangerId are not related.");

		}
		
	}
	@Override
	public Options getDirectors(DbUserSession user, int localeId)throws InvalidInfoRequestException {
		DatabaseTransaction t = null;
		
		if (validateLocale(localeId, user)) {

			Options options = new Options();

			try {
				t = (DatabaseTransaction) DatabaseTransaction
						.getTransaction(user);
				DbArrangers[] dirlist = FdmsDb.getInstance().getArrangers(t,
						localeId);
				if (dirlist != null) {
					for (DbArrangers arranger : dirlist) {
						options.addIntOption(arranger.getName(), arranger
								.getId());
					}
				}

			} catch (PersistenceException pe) {
				logger.error("Persistence Exception in updateVitals " + pe, pe);
			} catch (Exception pe) {
				logger.error("Exception in  updateVitals ", pe);
			} finally {
				if (t != null) {
					t.closeConnection();
				}
			}

			return options;
		}
		throw new InvalidInfoRequestException("localeId is not valid.");
	}
	@Override
	public Options getComboServiceType(DbUserSession user) {
		DatabaseTransaction t = null;
		
		Options options = new Options();

			try {
				t = (DatabaseTransaction) DatabaseTransaction
						.getTransaction(user);
				
				GeneralQueryDAO generalQueryDAO = new GeneralQueryDAO();
				options = generalQueryDAO.getComboServiceType( user);

			} catch (PersistenceException pe) {
				logger.error("Persistence Exception in updateVitals " + pe, pe);
			} catch (Exception pe) {
				logger.error("Exception in  updateVitals ", pe);
			} finally {
				if (t != null) {
					t.closeConnection();
				}
			}

			return options;
		
	}
	@Override
	public VitalsIdInfos getVitalsIdInfo(DbUserSession user, VitalsIdInfo vitalsIdInfo ) {
		
		VitalsIdInfos infos = populateVitalsIdInfo( user,	 vitalsIdInfo);
		
		return infos;
	}
	private VitalsIdInfos populateVitalsIdInfo(DbUserSession user, VitalsIdInfo vitalsIdInfo ) {
		DatabaseTransaction t = null;
		VitalsIdInfos infos = new VitalsIdInfos();
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			
			GeneralQueryDAO generalQueryDAO = new GeneralQueryDAO();
			infos = generalQueryDAO.getVitalsIdInfo(user,vitalsIdInfo);

		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in updateVitals " + pe, pe);
		} catch (Exception pe) {
			logger.error("Exception in  updateVitals ", pe);
		} finally {
			if (t != null) {
				t.closeConnection();
			}
		}
		return infos;
	}
	@Override
	public void saveGreeting(String greeting) {
		
		DatabaseTransaction t = null;
		try{
			/*DbUserSession user = checkValidUser("webservice", "iamnuts");
			String dbLookup = UtilSingleton.getInstance().getCommonDBLookup();
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
			WSGreeting dbGreeting = new WSGreeting();
			dbGreeting.setDateTime(new Timestamp(System.currentTimeMillis()));
			dbGreeting.setGreeting(greeting);
			dbGreeting.setNew();
			t.addPersistent(dbGreeting);
			t.save();*/
			GreetinsDAO dao = new GreetinsDAO(DAO.DB_FDMSCOMMON);
			dao.insertGreetings(greeting);
		}catch(Exception e){
			logger.error("Persistence Exception in FdmsServiceImpl.saveGreeting ", e);
		} finally {
			if (t != null) {
				t.closeConnection();
			}
		}
	}
	@Override
	public ResultType getData(DbUserSession user, String requestedData,
			CriteriaType criteria, FilterType filter, DebugType debug, boolean isSci) {
		
		boolean reportInvalidRowIds = debug != null ? 
				DebugType._value1.equals(debug.getValue())
				:false;
				
		boolean includeInvalidRowIds = debug != null ? 
				!DebugType._value1.equals(debug.getValue())
				:false;
				
		List<InvalidRowDetails> invalidRows = (reportInvalidRowIds || includeInvalidRowIds)? 
				new ArrayList<InvalidRowDetails>()
				: null;
				
		ResultType result = null;
		
		ResultTypeChoice_type0 choice = null;
		if(isSci){
			
			choice = getSCIData(user, requestedData, criteria, filter,
					reportInvalidRowIds, includeInvalidRowIds, invalidRows);
			
		}else{
			
			choice = getData(user, requestedData, criteria, filter,
									reportInvalidRowIds, includeInvalidRowIds, invalidRows);	
		}
		
		if((reportInvalidRowIds && invalidRows.isEmpty()) 
				|| (includeInvalidRowIds && invalidRows.isEmpty() && choice == null)
				|| (!reportInvalidRowIds && !includeInvalidRowIds && choice == null)){
			
			StringBuffer errorMsg = new StringBuffer(256);
			errorMsg.append("Database has no ").append(reportInvalidRowIds?"invalid data for ":"data for ");
			errorMsg.append(requestedData);
			
			if(criteria != null){
				
				if(criteria.getCaseId() != null){
					
					errorMsg.append(" with given case id: ");
					errorMsg.append(criteria.getCaseId().getCaseIdType());
					
				}else if(criteria.getFuneralHomeId()!=null){
					
					errorMsg.append(" with given funeral home id: ");
					errorMsg.append(criteria.getFuneralHomeId().getFuneralHomeIdType());
					
				}else if(criteria.getState()!= null && criteria.getState().length>0){
					
					if(criteria.getState().length==1){
						errorMsg.append(" with given state : ");
					}else{
						errorMsg.append(" with given states: ");
					}
					for(int i=0; i<criteria.getState().length; i++){
						errorMsg.append(criteria.getState()[i].getStateCodeType());
						if((i+1)<criteria.getState().length){
							errorMsg.append(",");
						}
					}
				}
				
			}else{
				
				errorMsg.append(" to the user ");
				errorMsg.append(user.getUserName());
			}
			
			throw new FDDEException( ErrorCodeType.NoData,errorMsg.toString());
		}
		
		result = new ResultType();
		
		if(choice != null){
			result.setResultTypeChoice_type0(choice);
		}
		
		if(invalidRows != null && invalidRows.size()>0){
			result.setInvalidRow(invalidRows.toArray(new InvalidRowDetails[invalidRows.size()]));
		}
		
		return result;
	}
	
	private ResultTypeChoice_type0 getSCIData(DbUserSession user,
			String requestedData, CriteriaType criteria, FilterType filter,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
		
		ResultTypeChoice_type0 choice = null;
		SCIFddeServiceDAO dao = new SCIFddeServiceDAO(user);
		CaseFilterType caseFilter= filter != null ? filter.getCaseFilter() : null;
		
		if(DataInterestType._sciContracts.equals(requestedData)){
			List<SciContractType> sciContracts = null;
			
			boolean globalExtract = false;
			if(criteria != null && (criteria.getFuneralHomeId() != null || criteria.getCaseId() != null)){
				StrMax10Len locCode = getFuneralHomeLocCode(dao, criteria, reportInvalidRowIds, includeInvalidRowIds,invalidRows);
				Integer sciLocation = dao.getSciLocation(criteria.getFuneralHomeId());
				if(invalidRows!=null && sciLocation.intValue()==0){
					invalidRows.add(dao.getInvalidRowDetails(criteria.getFuneralHomeId().getFuneralHomeIdType(), "Missing SCI Location", null, null));
				}
				sciContracts = dao.getSciContracts(criteria.getFuneralHomeId(), locCode, sciLocation, 
						criteria.getCaseId(), caseFilter, globalExtract,
						reportInvalidRowIds, includeInvalidRowIds, invalidRows);
			}else{
				List<FuneralHomeType> funeralHomes = null ;
				String extracterName = "Global SCI Contract extract";
				if(criteria != null && criteria.getState() != null){
					extracterName = "State wide SCI Contract extract";
					funeralHomes = dao.getFuneralHomes(null, criteria.getState(), false, (includeInvalidRowIds || reportInvalidRowIds), invalidRows);
				}else{
					globalExtract = true;
					funeralHomes = dao.getFuneralHomes(null, null, false, (includeInvalidRowIds || reportInvalidRowIds), invalidRows);
				}
				
				if(funeralHomes != null && funeralHomes.size() >0 ){
					SciContractsExtracter extracter = new SciContractsExtracter(extracterName, user, funeralHomes, 
															caseFilter, globalExtract, reportInvalidRowIds, 
															includeInvalidRowIds, invalidRows, false);
					sciContracts = extracter.extractData();
					}
			}
			
			if(sciContracts != null && sciContracts.size()>0){
				dao.generateSequenceNumbers(sciContracts, globalExtract);
				choice = new ResultTypeChoice_type0();
				choice.setSciContract(sciContracts.toArray(new SciContractType[sciContracts.size()]));
			}
			
		}else if(DataInterestType._sciLineItem.equals(requestedData)){			
			List<SciContractLineItemType> sciContractItem = null;
			Map<String, String> fdmsToSciItemMap = dao.getFdmsItemToSciItem();
			if(criteria != null && (criteria.getFuneralHomeId()!=null ||criteria.getCaseId() != null)){
				StrMax10Len locCode = getFuneralHomeLocCode(dao, criteria, reportInvalidRowIds, 
										includeInvalidRowIds,invalidRows);
				Integer sciLocation = dao.getSciLocation(criteria.getFuneralHomeId());
				sciContractItem = dao.getSciLineItems(criteria.getFuneralHomeId(), locCode, sciLocation, 
										criteria.getCaseId(), caseFilter, fdmsToSciItemMap,
										reportInvalidRowIds, includeInvalidRowIds, invalidRows);
			}else{
				// code for global extract
				boolean globalExtract = false;
				List<FuneralHomeType> funeralHomes = null;
				String extracterName = "Global SCI Contract Line Item Extract";
				if(criteria != null && criteria.getState() != null){
					extracterName = "State wide SCI Contract Line Item Extract ";
					funeralHomes = dao.getFuneralHomes(null, criteria.getState(), false, (includeInvalidRowIds || reportInvalidRowIds), invalidRows);
				}else{
					globalExtract = true;
					funeralHomes = dao.getFuneralHomes(null, null, false, (includeInvalidRowIds || reportInvalidRowIds), invalidRows);
				}
				
				if(funeralHomes != null && funeralHomes.size() >0 ){
					SciLineItemsExtracter extracter = new SciLineItemsExtracter(extracterName, user, funeralHomes, 
							caseFilter, globalExtract, reportInvalidRowIds, 
							includeInvalidRowIds, invalidRows, fdmsToSciItemMap);
					sciContractItem = extracter.extractData();
				}
			}			
			if(sciContractItem != null && sciContractItem.size()>0){
				dao.generateSequenceNumbers(sciContractItem, false);
				choice = new ResultTypeChoice_type0();
				choice.setSciContractLineItem(sciContractItem.toArray(new SciContractLineItemType[sciContractItem.size()]));
			}
			
		}else if(DataInterestType._sciCash.equals(requestedData)){
			
			List<SciCashType> sciCash = null;
			boolean globalExtract = false;
			if(criteria != null && (criteria.getFuneralHomeId() != null || criteria.getCaseId() != null)){
				StrMax10Len locCode = getFuneralHomeLocCode(dao, criteria, reportInvalidRowIds, includeInvalidRowIds,invalidRows);
				Integer sciLocation = dao.getSciLocation(criteria.getFuneralHomeId());
				sciCash = dao.getSciCash(criteria.getFuneralHomeId(), locCode, sciLocation, criteria.getCaseId(),
						caseFilter, reportInvalidRowIds, includeInvalidRowIds, invalidRows);
			}else{
				// code for global extract
				List<FuneralHomeType> funeralHomes = null;
				String extracterName = "Global SCI Cash Extract";
				if(criteria != null && criteria.getState() != null){
					extracterName = "State wide SCI Cash Extract ";
					funeralHomes = dao.getFuneralHomes(null, criteria.getState(), false, (includeInvalidRowIds || reportInvalidRowIds), invalidRows);
				}else{
					globalExtract = true;
					funeralHomes = dao.getFuneralHomes(null, null, false, (includeInvalidRowIds || reportInvalidRowIds), invalidRows);
				}
				
				if(funeralHomes != null && funeralHomes.size() >0 ){
					SciCashExtracter extracter = new SciCashExtracter(extracterName, user, funeralHomes, 
							caseFilter, globalExtract, reportInvalidRowIds, 
							includeInvalidRowIds, invalidRows);
					sciCash = extracter.extractData();
				}
			}
			
			if(sciCash != null && sciCash.size()>0){
				dao.generateSequenceNumbers(sciCash, false);
				choice = new ResultTypeChoice_type0();
				choice.setSciCash(sciCash.toArray(new SciCashType[sciCash.size()]));
			}
			
		}else if(DataInterestType._sciARDetailData.equals(requestedData)){
			List<SciARDetailDataType> sciArDetailData = null;
			boolean globalExtract = false;
			if(criteria != null && (criteria.getFuneralHomeId() != null || criteria.getCaseId() != null)){
				sciArDetailData = dao.getSciARDetails(criteria.getFuneralHomeId(), criteria.getCaseId(),
														caseFilter, 
														reportInvalidRowIds, includeInvalidRowIds, 
														invalidRows);
			}else{
				// code for global extract
				List<FuneralHomeType> funeralHomes = null;
				String extracterName = "Global SCI ARDetailData Extract";
				if(criteria != null && criteria.getState() != null){
					extracterName = "State wide SCI ARDetailData Extract ";
					funeralHomes = dao.getFuneralHomes(null, criteria.getState(), false, (includeInvalidRowIds || reportInvalidRowIds), invalidRows);
				}else{
					globalExtract = true;
					funeralHomes = dao.getFuneralHomes(null, null, false, (includeInvalidRowIds || reportInvalidRowIds), invalidRows);
				}
				
				if(funeralHomes != null && funeralHomes.size() >0 ){
					SciARDetailDataExtracter extracter = new SciARDetailDataExtracter(extracterName, user, funeralHomes, 
							caseFilter, globalExtract, reportInvalidRowIds, 
							includeInvalidRowIds, invalidRows);
					sciArDetailData = extracter.extractData();
				}
			}
			if(sciArDetailData != null && sciArDetailData.size() > 0){
				choice = new ResultTypeChoice_type0();
				choice.setSciARDetailData(sciArDetailData.toArray(new SciARDetailDataType[sciArDetailData.size()]));
			}
		}else if(DataInterestType._sciGenerateContractNos.equals(requestedData)){
			List<FuneralHomeType> funeralHomes = dao.getFuneralHomes(null, null, false, 
														(includeInvalidRowIds || reportInvalidRowIds), invalidRows);
			SciContractsExtracter extracter = new SciContractsExtracter("Global SCI contract number extract", user, 
														funeralHomes, caseFilter, true, reportInvalidRowIds, 
														includeInvalidRowIds, invalidRows, true);
			List<SciContractType> sciContracts = extracter.extractData();
			if(sciContracts != null && sciContracts.size()>0){
				dao.generateSequenceNumbers(sciContracts, true);
				choice = new ResultTypeChoice_type0();
				choice.setSuccess(true);
			}else{
				choice = new ResultTypeChoice_type0();
				choice.setSuccess(false);
			}
		}
		return choice;
	}
	private ResultTypeChoice_type0 getData(DbUserSession user, String requestedData, CriteriaType criteria, FilterType filter,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){
		
		ResultTypeChoice_type0 choice = null;
		FddeServiceDAO dao = new FddeServiceDAO(user);
		
		if(DataInterestType._funeralHomes.equals(requestedData)){
			FuneralHomeIdType funeralHomeId = criteria != null ? criteria.getFuneralHomeId() : null;
			StateCodeType[] states = criteria != null ? criteria.getState(): null;
			List<FuneralHomeType> funeralHomes = dao.getFuneralHomes(funeralHomeId, states, reportInvalidRowIds, includeInvalidRowIds, invalidRows);
			
			if(funeralHomes != null && funeralHomes.size()>0){
				choice = new ResultTypeChoice_type0();
				choice.setFuneralHome(funeralHomes.toArray(new FuneralHomeType[funeralHomes.size()]));
			}
			
		}else if(DataInterestType._casesByFuneralHome.equals(requestedData) ||
				DataInterestType._caseById.equals(requestedData)){
			
			CaseFilterType caseFilter= filter != null ? filter.getCaseFilter() : null;
			List<CaseType> cases = dao.getCases(criteria, caseFilter, reportInvalidRowIds
					, includeInvalidRowIds, invalidRows);
			
			if(cases != null && cases.size()>0){
				choice = new ResultTypeChoice_type0();
				choice.set_case(cases.toArray(new CaseType[cases.size()]));
			}
				
		}else if(DataInterestType._financialInfoByFuneralHome.equals(requestedData)||
					DataInterestType._caseSaleByCaseId.equals(requestedData)){
			
			FinancialInfoType financialInfo = null;
			if(DataInterestType._financialInfoByFuneralHome.equals(requestedData)){
				
				FinancialFilterType financialFilter= filter != null ? filter.getFinancialFilter() : null;
				financialInfo = dao.getFinancialInfo(criteria, financialFilter, reportInvalidRowIds
						, includeInvalidRowIds, invalidRows);
				
			}else if(DataInterestType._caseSaleByCaseId.equals(requestedData)){
				
				financialInfo = dao.getCaseSales(criteria, null, reportInvalidRowIds
						, includeInvalidRowIds, invalidRows);
			}
			if(financialInfo != null ){
				choice = new ResultTypeChoice_type0();
				choice.setFinancialInfo(financialInfo);
			}
		}else if(DataInterestType._invOnHandByFuneralHome.equals(requestedData)){
			
			List<InvItemType> invItems = dao.getInventoryItems(criteria, reportInvalidRowIds
					, includeInvalidRowIds, invalidRows);
			
			if(invItems != null && invItems.size()>0){
				choice = new ResultTypeChoice_type0();
				choice.setInvItem(invItems.toArray(new InvItemType[invItems.size()]));
			}
			
		}else if(DataInterestType._accountsReceivable.equals(requestedData)){
			
			List<AccountsRecievable> report = dao.getARReport(criteria, reportInvalidRowIds,
					includeInvalidRowIds, invalidRows);
			
			if(report != null && report.size()>0){
				choice = new ResultTypeChoice_type0();
				choice.setArFields(report.toArray(new AccountsRecievable[report.size()]));
			}
			
		}
		
		return choice;
	}
	@Override
	public List<UserLocation> getUserLocations(DbUserSession user)
			throws SQLException, Exception {
		FddeServiceDAO dao = new FddeServiceDAO(user);
		return dao.getUserLocations();
	}
	
	private StrMax10Len getFuneralHomeLocCode(SCIFddeServiceDAO dao, CriteriaType criteria, 
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows){
		if(criteria != null){
			return getFuneralHomeLocCode(dao, criteria.getFuneralHomeId(), reportInvalidRowIds, includeInvalidRowIds,invalidRows);
		}
		return null;
	}
	
	private StrMax10Len getFuneralHomeLocCode(SCIFddeServiceDAO dao, FuneralHomeIdType funeralHomeId, 
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows){
		
		StrMax10Len locCode = null;
		if(funeralHomeId != null){
			locCode = dao.getFuneralHomeNumber(funeralHomeId, reportInvalidRowIds, includeInvalidRowIds,invalidRows);
		}
		return locCode;
	}
}
