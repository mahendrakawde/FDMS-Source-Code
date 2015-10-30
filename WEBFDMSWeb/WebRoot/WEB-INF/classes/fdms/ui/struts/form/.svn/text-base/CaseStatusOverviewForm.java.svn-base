package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CaseStatusOverviewForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6706442047457743589L;

	private String payment;

	private String charges;

	private String donation;

	private String survivor;
	
	private String predecease;

	private String customSelect;

	private String contractLiteral;

	private String vitalsId;

	private String minister;

	private String deceasedFullName;

	private String dateOfBirth;

	private String dateOfDeath;

	private String informantName;

	private String relation;

	private String contractCode;

	private String caseCode;

	private String buyerName;

	private String counselorName;

	private String preNeedStatus;

	private String chapel;

	private String director;

	private String arrangementDateTime;

	private String placeOfDeath;

	private String residenceAddress;

	private String maritalStatus;

	private String spouse;

	private String staffAutos;

	private String visitation;

	private String timeAndPlaceOfService;

	private String cemeteryAndPlotInformation;

	private String branchOfService;

	private String war;

	private String veteranServiceFromDate;

	private String veteranServiceToDate;

	private String saleType;

	private String totalCharges;

	private String balance;

	private String statementLastSent;

	private String obituary;

	private String decResStreet;

	private String decResCityStateZip;

	private String cemeteryStreet;

	private String cemeteryCitystate;

	private boolean voidContract;

	private boolean locked;

	private String lockedByWhom;

	private String submitButton;

	private String abbitPreneed;
	
	private boolean archive;



	public String getPredecease() {
		return predecease;
	}

	public void setPredecease(String predecease) {
		this.predecease = predecease;
	}

	/**
	 * @return the archive
	 */
	public boolean isArchive() {
		return archive;
	}

	/**
	 * @param archive the archive to set
	 */
	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public String getAbbitPreneed() {
		return abbitPreneed;
	}

	public String getArrangementDateTime() {
		return arrangementDateTime;
	}

	public String getBalance() {
		return balance;
	}

	public String getBranchOfService() {
		return branchOfService;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public String getCemeteryAndPlotInformation() {
		return cemeteryAndPlotInformation;
	}

	public String getCemeteryCitystate() {
		return cemeteryCitystate;
	}

	public String getCemeteryStreet() {
		return cemeteryStreet;
	}

	public String getChapel() {
		return chapel;
	}

	public String getContractCode() {
		return contractCode;
	}

	public String getCounselorName() {
		return counselorName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public String getDeceasedFullName() {
		return deceasedFullName;
	}

	public String getDecResCityStateZip() {
		return decResCityStateZip;
	}

	public String getDecResStreet() {
		return decResStreet;
	}

	public String getDirector() {
		return director;
	}

	public String getInformantName() {
		return informantName;
	}

	public String getLockedByWhom() {
		return lockedByWhom;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public String getMinister() {
		return minister;
	}

	public String getObituary() {
		return obituary;
	}

	public String getPlaceOfDeath() {
		return placeOfDeath;
	}

	public String getPreNeedStatus() {
		return preNeedStatus;
	}

	public String getRelation() {
		return relation;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public String getSaleType() {
		return saleType;
	}

	public String getSpouse() {
		return spouse;
	}

	public String getStaffAutos() {
		return staffAutos;
	}

	public String getStatementLastSent() {
		return statementLastSent;
	}

	public String getSubmitButton() {
		return submitButton;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:58:41
	 * PM)
	 * 
	 * @return String
	 */
	public String getTimeAndPlaceOfService() {
		return timeAndPlaceOfService;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:01:57
	 * PM)
	 * 
	 * @return String
	 */
	public String getTotalCharges() {
		return totalCharges;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:00:55
	 * PM)
	 * 
	 * @return String
	 */
	public String getVeteranServiceFromDate() {
		return veteranServiceFromDate;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:01:14
	 * PM)
	 * 
	 * @return String
	 */
	public String getVeteranServiceToDate() {
		return veteranServiceToDate;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:58:20
	 * PM)
	 * 
	 * @return String
	 */
	public String getVisitation() {
		return visitation;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:00:42
	 * PM)
	 * 
	 * @return String
	 */
	public String getWar() {
		return war;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2002 1:49:32
	 * PM)
	 * 
	 * @return boolean
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2002 1:41:35
	 * PM)
	 * 
	 * @return boolean
	 */
	public boolean isVoidContract() {
		return voidContract;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
		this.setVoidContract(false);
		this.setLocked(false);
	}

	/**
	 * Insert the method's description here. Creation date: (2/24/2003 3:44:44
	 * PM)
	 * 
	 * @param newAbbitPreneed
	 *            String
	 */
	public void setAbbitPreneed(String newAbbitPreneed) {
		abbitPreneed = newAbbitPreneed;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:53:31
	 * PM)
	 * 
	 * @param newArrangementDateTime
	 *            String
	 */
	public void setArrangementDateTime(String newArrangementDateTime) {
		arrangementDateTime = newArrangementDateTime;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:02:11
	 * PM)
	 * 
	 * @param newBalance
	 *            String
	 */
	public void setBalance(String newBalance) {
		balance = newBalance;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:00:25
	 * PM)
	 * 
	 * @param newBranchOfService
	 *            String
	 */
	public void setBranchOfService(String newBranchOfService) {
		branchOfService = newBranchOfService;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:48:32
	 * PM)
	 * 
	 * @param newBuyerName
	 *            String
	 */
	public void setBuyerName(String newBuyerName) {
		buyerName = newBuyerName;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:43:05
	 * PM)
	 * 
	 * @param newCaseCode
	 *            String
	 */
	public void setCaseCode(String newCaseCode) {
		caseCode = newCaseCode;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:59:00
	 * PM)
	 * 
	 * @param newCemeteryAndPlotInformation
	 *            String
	 */
	public void setCemeteryAndPlotInformation(String newCemeteryAndPlotInformation) {
		cemeteryAndPlotInformation = newCemeteryAndPlotInformation;
	}

	/**
	 * Insert the method's description here. Creation date: (3/22/2002 11:37:50
	 * AM)
	 * 
	 * @param newCemeteryCitystate
	 *            String
	 */
	public void setCemeteryCitystate(String newCemeteryCitystate) {
		cemeteryCitystate = newCemeteryCitystate;
	}

	/**
	 * Insert the method's description here. Creation date: (3/22/2002 11:37:34
	 * AM)
	 * 
	 * @param newCemeteryStreet
	 *            String
	 */
	public void setCemeteryStreet(String newCemeteryStreet) {
		cemeteryStreet = newCemeteryStreet;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:49:29
	 * PM)
	 * 
	 * @param newChapel
	 *            String
	 */
	public void setChapel(String newChapel) {
		chapel = newChapel;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:42:46
	 * PM)
	 * 
	 * @param newContractCode
	 *            String
	 */
	public void setContractCode(String newContractCode) {
		contractCode = newContractCode;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:48:51
	 * PM)
	 * 
	 * @param newCounselorName
	 *            String
	 */
	public void setCounselorName(String newCounselorName) {
		counselorName = newCounselorName;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:03:21
	 * PM)
	 * 
	 * @param newDateOfBirth
	 *            String
	 */
	public void setDateOfBirth(String newDateOfBirth) {
		dateOfBirth = newDateOfBirth;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:03:37
	 * PM)
	 * 
	 * @param newDateOfDeath
	 *            String
	 */
	public void setDateOfDeath(String newDateOfDeath) {
		dateOfDeath = newDateOfDeath;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:03:08
	 * PM)
	 * 
	 * @param newDeceasedFullName
	 *            String
	 */
	public void setDeceasedFullName(String newDeceasedFullName) {
		deceasedFullName = newDeceasedFullName;
	}

	/**
	 * Insert the method's description here. Creation date: (3/22/2002 9:49:08
	 * AM)
	 * 
	 * @param newDecResCityStateZip
	 *            String
	 */
	public void setDecResCityStateZip(String newDecResCityStateZip) {
		decResCityStateZip = newDecResCityStateZip;
	}

	/**
	 * Insert the method's description here. Creation date: (3/22/2002 9:48:50
	 * AM)
	 * 
	 * @param newDecResStreet
	 *            String
	 */
	public void setDecResStreet(String newDecResStreet) {
		decResStreet = newDecResStreet;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:49:54
	 * PM)
	 * 
	 * @param newDirector
	 *            String
	 */
	public void setDirector(String newDirector) {
		director = newDirector;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:05:00
	 * PM)
	 * 
	 * @param newInformantName
	 *            String
	 */
	public void setInformantName(String newInformantName) {
		informantName = newInformantName;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2002 1:49:32
	 * PM)
	 * 
	 * @param newLocked
	 *            boolean
	 */
	public void setLocked(boolean newLocked) {
		locked = newLocked;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2002 1:49:46
	 * PM)
	 * 
	 * @param newLockedByWhom
	 *            String
	 */
	public void setLockedByWhom(String newLockedByWhom) {
		lockedByWhom = newLockedByWhom;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:55:03
	 * PM)
	 * 
	 * @param newMaritalStatus
	 *            String
	 */
	public void setMaritalStatus(String newMaritalStatus) {
		maritalStatus = newMaritalStatus;
	}

	public void setMinister(String minister) {
		this.minister = minister;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 7:15:27
	 * PM)
	 * 
	 * @param newObituary
	 *            String
	 */
	public void setObituary(String newObituary) {
		obituary = newObituary;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:53:54
	 * PM)
	 * 
	 * @param newPlaceOfDeath
	 *            String
	 */
	public void setPlaceOfDeath(String newPlaceOfDeath) {
		placeOfDeath = newPlaceOfDeath;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:49:09
	 * PM)
	 * 
	 * @param newPreNeedStatus
	 *            String
	 */
	public void setPreNeedStatus(String newPreNeedStatus) {
		preNeedStatus = newPreNeedStatus;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:06:10
	 * PM)
	 * 
	 * @param newRelation
	 *            String
	 */
	public void setRelation(String newRelation) {
		relation = newRelation;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:54:46
	 * PM)
	 * 
	 * @param newResidenceAddress
	 *            String
	 */
	public void setResidenceAddress(String newResidenceAddress) {
		residenceAddress = newResidenceAddress;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:01:42
	 * PM)
	 * 
	 * @param newSaleType
	 *            String
	 */
	public void setSaleType(String newSaleType) {
		saleType = newSaleType;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:55:18
	 * PM)
	 * 
	 * @param newSpouse
	 *            String
	 */
	public void setSpouse(String newSpouse) {
		spouse = newSpouse;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:57:58
	 * PM)
	 * 
	 * @param newStaffAutos
	 *            String
	 */
	public void setStaffAutos(String newStaffAutos) {
		staffAutos = newStaffAutos;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:02:46
	 * PM)
	 * 
	 * @param newStatementLastSent
	 *            String
	 */
	public void setStatementLastSent(String newStatementLastSent) {
		statementLastSent = newStatementLastSent;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2002 2:15:41
	 * PM)
	 * 
	 * @param newSubmitButton
	 *            String
	 */
	public void setSubmitButton(String newSubmitButton) {
		submitButton = newSubmitButton;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:58:41
	 * PM)
	 * 
	 * @param newTimeAndPlaceOfService
	 *            String
	 */
	public void setTimeAndPlaceOfService(String newTimeAndPlaceOfService) {
		timeAndPlaceOfService = newTimeAndPlaceOfService;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:01:57
	 * PM)
	 * 
	 * @param newTotalCharges
	 *            String
	 */
	public void setTotalCharges(String newTotalCharges) {
		totalCharges = newTotalCharges;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:00:55
	 * PM)
	 * 
	 * @param newVeteranServiceFromDate
	 *            String
	 */
	public void setVeteranServiceFromDate(String newVeteranServiceFromDate) {
		veteranServiceFromDate = newVeteranServiceFromDate;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:01:14
	 * PM)
	 * 
	 * @param newVeteranServiceToDate
	 *            String
	 */
	public void setVeteranServiceToDate(String newVeteranServiceToDate) {
		veteranServiceToDate = newVeteranServiceToDate;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 4:58:20
	 * PM)
	 * 
	 * @param newVisitation
	 *            String
	 */
	public void setVisitation(String newVisitation) {
		visitation = newVisitation;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2002 1:41:35
	 * PM)
	 * 
	 * @param newVoidContract
	 *            boolean
	 */
	public void setVoidContract(boolean newVoidContract) {
		voidContract = newVoidContract;
	}

	/**
	 * Insert the method's description here. Creation date: (1/15/2002 5:00:42
	 * PM)
	 * 
	 * @param newWar
	 *            String
	 */
	public void setWar(String newWar) {
		war = newWar;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	public String getCharges() {
		return charges;
	}

	public String getContractLiteral() {
		return contractLiteral;
	}

	public String getCustomSelect() {
		return customSelect;
	}

	public String getDonation() {
		return donation;
	}

	public String getPayment() {
		return payment;
	}

	public String getSurvivor() {
		return survivor;
	}

	public String getVitalsId() {
		return vitalsId;
	}

	public void setCharges(String in) {
		charges = in;
	}

	public void setContractLiteral(String newContractLiteral) {
		contractLiteral = newContractLiteral;
	}

	public void setCustomSelect(String in) {
		customSelect = in;
	}

	public void setDonation(String in) {
		donation = in;
	}

	public void setPayment(String in) {
		payment = in;
	}

	public void setSurvivor(String in) {
		survivor = in;
	}

	public void setVitalsId(String newVitalsId) {
		vitalsId = newVitalsId;
	}
}
