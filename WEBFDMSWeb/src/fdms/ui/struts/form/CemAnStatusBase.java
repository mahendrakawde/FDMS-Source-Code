package fdms.ui.struts.form;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class CemAnStatusBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -5024676318254272985L;

private Logger logger = Logger.getLogger(FirstCallInformationFormBase.class.getName());
	
  private String directive;
  private String vitalsId;
  private String preneedDate ;  
  private String informantRelation ;  
  private String informantCity ;  
  private String informantLast ;  
  private String director ;  
  private String age ;  
  private String facilityStreet ;  
  private String placeDeathState ;  
  private String facilityName ;  
  private String facilityCity ;  
  private String placeDeathZip ;  
  private String nextKinState ;  
  private String chapel ;  
  private String informantStreet ;  
  private String source ;  
  private String birthDate ;  
  private String contractNumber ;  
  private String nextKinStreet ;    
  private String serviceDate ;  
  private String nextKinStreet3 ;  
  private String nextKinStreet2 ;  
  private String facilityState ;  
  private String informantMiddle ;  
  private String dispDate ;  
  private String memorialName ;  
  private String facilityZip ;  
  private String nextKinPhone ;  
  private String caseNumber ;  
  private String nextKinMiddle ;
  private String nextKinSalutation ;
  private String nextKinFirst ;  
  private String placeDeathCity ;  
  private String fromPage ;  
  private String locationDeceased ;  
  private String middleName ;  
  private String informantSalutation ;  
  private boolean nextKinSame ;  
  private String nextKinCity ;  
  private String nextKinLast ;  
  private int y ;  
  private String informantState ;  
  private int x ;  
  private String prefix;
  private String suffix;
  private String firstName ;  
  private String lastName ;  
  private String maidenName;
  private String informantStreet3 ;  
  private String informantStreet2 ;  
  private String facilityPhone ;  
  private String arrangeDate ;  
  private String nextKinZip ;  
  private boolean deceasedSame ;
  private boolean informantContractSigner ;
  private boolean showInformantContractSigner ;
  private String time ;  
  private String placeDeath ;  
  private String nextKinRelation ;  
  private String embalming ;  
  private String deathDate ;  
  private String informantPhone ;  
  private String informantFirst ;  
  private String informantZip ;
  
  private java.lang.String shippingInfo;
  private java.lang.String facilityLicense;
  private java.lang.String informantEmail;
  private java.lang.String mailToButton;
  private java.lang.String nextContractNumber;
  
  
  
  private boolean executorSame;
  private java.lang.String executorPersonId;
  private java.lang.String executorFirstName;
  private java.lang.String executorLastName;
  private java.lang.String executorPhone;
  private java.lang.String executorRelation;
  private java.lang.String executorStreet;
  private java.lang.String executorStreet2;
  private java.lang.String executorStreet3;
  private java.lang.String executorCity;
  private java.lang.String executorState;
  private java.lang.String executorZip;
  private java.lang.String executorEmail;
  
  private String cem_plottype;
  private String cem_section;
  private String cem_block;
  private String cem_lot_tier;
  private String cem_grave_row;
  private int cem_Amount;
  private String cem_ANBuyerAptNo ;  
  private String cem_ANBuyerCity ;  
  private String cem_ANBuyerMidName ;  
  private String cem_ANBuyerTitle ;
  private String cem_ANBuyerPhone ;
  private String cem_ANBuyerState ;
  private String cem_ANBuyerStreet;
  private String cem_ANBuyerFirstName;
  private String cem_ANBuyerLastName;
  private String cem_ANBuyerZip;
  private String cem_MapID ;
  private String cem_Record ;
  private String cem_ContractDate ;
  private String cem_MiscDesc ;
  private int cem_MiscAmount ;
  private String beneficiaryFirst ;
  private String beneficiaryMiddle ;
  private String beneficiaryLast ;
  private String beneficiaryStreet ;
  private String beneficiaryCity ;
  private String beneficiaryState ;
  private String beneficiaryZipCode ;
  private String beneficiaryPhone ;
  private String benneficiarySocialSecurityNumber ;
  private String beneficiaryTitle ;
  private String beneficiaryAptno ;
  
        
      
  public String getAge () {
	return age ;
  }  
  public String getArrangeDate () {
	return arrangeDate ;
  }  
  public String getBirthDate () {
	return birthDate ;
  }  
  public String getCaseNumber () {
	return caseNumber ;
  }  
  public String getChapel () {
	return chapel ;
  }  
  public String getContractNumber () {
	return contractNumber ;
  }  
  public String getDeathDate () {
	return deathDate ;
  }  
  public String getDirective () {
	return directive ;
  }  
  public String getDirector () {
	return director ;
  }  
  public String getDispDate () {
	return dispDate ;
  }  
  public String getEmbalming () {
	return embalming ;
  }  
  public String getFacilityCity () {
	return facilityCity ;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (3/6/2002 4:40:13 PM)
   * @return java.lang.String
   */
  public java.lang.String getFacilityLicense() {
	return facilityLicense;
  }
  public String getFacilityName () {
	return facilityName ;
  }  
  public String getFacilityPhone () {
	return facilityPhone ;
  }  
  public String getFacilityState () {
	return facilityState ;
  }  
  public String getFacilityStreet () {
	return facilityStreet ;
  }  
  public String getFacilityZip () {
	return facilityZip ;
  }  
  public String getPrefix () {
  	return prefix ;
  }
  public String getSuffix () {
  	return suffix ;
  }
  public String getFirstName () {
	return firstName ;
  }  
  public String getMaidenName() {
        return maidenName;
  }
  public String getFromPage () {
	return fromPage ;
  }  
  public String getInformantCity () {
	return informantCity ;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (8/21/2002 2:29:44 PM)
   * @return java.lang.String
   */
  public java.lang.String getInformantEmail() {
	return informantEmail;
  }
  public String getInformantFirst () {
	return informantFirst ;
  }  
  public String getInformantLast () {
	return informantLast ;
  }  
  public String getInformantMiddle () {
	return informantMiddle ;
  }  
  public String getInformantPhone () {
	return informantPhone ;
  }  
  public String getInformantRelation () {
	return informantRelation ;
  }  
  public String getInformantSalutation () {
	return informantSalutation ;
  }  
  public String getInformantState () {
	return informantState ;
  }  
  public String getInformantStreet () {
	return informantStreet ;
  }  
  public String getInformantStreet2 () {
	return informantStreet2 ;
  }  
  public String getInformantStreet3 () {
	return informantStreet3 ;
  }  
  public String getInformantZip () {
	return informantZip ;
  }  
  public String getLastName () {
	return lastName ;
  }  
  public String getLocationDeceased () {
	return locationDeceased ;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (8/21/2002 3:28:52 PM)
   * @return java.lang.String
   */
  public java.lang.String getMailToButton() {
	return mailToButton;
  }
  public String getMemorialName () {
	return memorialName ;
  }  
  public String getMiddleName () {
	return middleName ;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (10/25/2002 2:50:23 PM)
   * @return java.lang.String
   */
  public java.lang.String getNextContractNumber() {
	return nextContractNumber;
  }
  public String getNextKinCity () {
	return nextKinCity ;
  }  
  public String getNextKinSalutation () {
	return nextKinSalutation ;
  }  
  public String getNextKinFirst () {
	return nextKinFirst ;
  }  
  public String getNextKinLast () {
	return nextKinLast ;
  }  
  public String getNextKinMiddle () {
	return nextKinMiddle ;
  }  
  public String getNextKinPhone () {
	return nextKinPhone ;
  }  
  public String getNextKinRelation () {
	return nextKinRelation ;
  }  
  public boolean getNextKinSame () {
	return nextKinSame ;
  }  
  public String getNextKinState () {
	return nextKinState ;
  }  
  public String getNextKinStreet () {
	return nextKinStreet ;
  }  
  public String getNextKinStreet2 () {
	return nextKinStreet2 ;
  }  
  public String getNextKinStreet3 () {
	return nextKinStreet3 ;
  }  
  public String getNextKinZip () {
	return nextKinZip ;
  }  
  public boolean getDeceasedSame () {
	return deceasedSame ;
  }  
  public String getPlaceDeath () {
	return placeDeath ;
  }  
  public String getPlaceDeathCity () {
	return placeDeathCity ;
  }  
  public String getPlaceDeathState () {
	return placeDeathState ;
  }  
  public String getPlaceDeathZip () {
	return placeDeathZip ;
  }  
  public String getPreneedDate () {
	return preneedDate ;
  }  
  public String getServiceDate () {
	return serviceDate ;
  }  
  public String getBeneficiaryFirst () {
	return beneficiaryFirst ;
  }  
  public String getBeneficiaryMiddle () {
	return beneficiaryMiddle ;
  }  
  public String getBeneficiaryLast () {
	return beneficiaryLast ;
  }  
  public String getBeneficiaryStreet () {
	return beneficiaryStreet ;
  }  
  public String getBeneficiaryCity () {
	return beneficiaryCity ;
  }
  public String getBeneficiaryState () {
	return beneficiaryState ;
  }
  public String getBeneficiaryZipCode () {
	return beneficiaryZipCode ;
  }  
  public String getBeneficiaryPhone () {
	return beneficiaryPhone ;
  }
  public String getBeneficiarySocialSecurityNumber () {
	return benneficiarySocialSecurityNumber ;
  }
  public String getBeneficiaryTitle () {
	return beneficiaryTitle ;
  }  
  
  public String getBeneficiaryAptno () {
	return beneficiaryAptno ;
  }  
   
  /**
   * Insert the method's description here.
   * Creation date: (3/4/2002 3:30:21 PM)
   * @return java.lang.String
   */
  public java.lang.String getShippingInfo() {
	return shippingInfo;
  }
  public String getSource () {
	return source ;
  }  
  public String getTime () {
	return time ;
  }  
  public String getVitalsId () {
	return vitalsId ;
  }  
  public int getX () {
	return x ;
  }  
  public int getY () {
	return y ;
  }	
  public String getCem_plottype () {
	return cem_plottype ;
  }  
  public String getCem_section () {
	return cem_section ;
  }  
  public String getCem_block () {
	return cem_block ;
  }  
  public String getCem_lot_tier() {
	return cem_lot_tier ;
  }  
  public String getCem_grave_row () {
	return cem_grave_row ;
  }  
  public int getCem_Amount () {
	return cem_Amount ;
  }
  public String getCem_ANBuyerAptNo () {
	return cem_ANBuyerAptNo ;
  }  
  public String getCem_ANBuyerCity () {
	return cem_ANBuyerCity ;
  }  
  public String getCem_ANBuyerFirstName () {
	return cem_ANBuyerFirstName ;
  }  
  public String getCem_ANBuyerLastName () {
	return cem_ANBuyerLastName ;
  }  
  public String getCem_ANBuyerMidName () {
	return cem_ANBuyerMidName ;
  }  
  public String getCem_ANBuyerState () {
	return cem_ANBuyerState ;
  }  
  public String getCem_ANBuyerStreet () {
	return cem_ANBuyerStreet ;
  }  
  public String getCem_ANBuyerTitle () {
	return cem_ANBuyerTitle ;
  }  
  public String getCem_ANBuyerZip () {
	return cem_ANBuyerZip ;
  }	
  public String getCem_ANBuyerPhone() {
		return cem_ANBuyerPhone;
  }  
  public String getCem_MapID () {
	return cem_MapID ;
  }	
  public String getCem_Record () {
	return cem_Record ;
  }
  public String getCem_ContractDate () {
	return cem_ContractDate ;
  }
  public String getCem_MiscDesc () {
	return cem_MiscDesc ;
  }
  public int getCem_MiscAmount () {
	return cem_MiscAmount ;
  }
  public void setAge (String in) {
	age = in;
  }  
  public void setArrangeDate (String in) {
	arrangeDate = in;
  }  
  public void setBirthDate (String in) {
	birthDate = in;
  }  
  public void setCaseNumber (String in) {
	caseNumber = in;
  }  
  public void setChapel (String in) {
	chapel = in;
  }  
  public void setContractNumber (String in) {
	contractNumber = in;
  }  
  public void setDeathDate (String in) {
	deathDate = in;
  }  
  public void setDirective (String in) {
	directive = in;
  }  
  public void setDirector (String in) {
	director = in;
  }  
  public void setDispDate (String in) {
	dispDate = in;
  }  
  public void setEmbalming (String in) {
	embalming = in;
  }  
  public void setFacilityCity (String in) {
	facilityCity = in;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (3/6/2002 4:40:13 PM)
   * @param newFacilityLicense java.lang.String
   */
  public void setFacilityLicense(java.lang.String newFacilityLicense) {
	facilityLicense = newFacilityLicense;
  }
  public void setFacilityName (String in) {
	facilityName = in;
  }  
  public void setFacilityPhone (String in) {
	facilityPhone = in;
  }  
  public void setFacilityState (String in) {
	facilityState = in;
  }  
  public void setFacilityStreet (String in) {
	facilityStreet = in;
  }  
  public void setFacilityZip (String in) {
	facilityZip = in;
  }  
  public void setPrefix (String in) {
  	prefix = in;
  }
  public void setSuffix (String in) {
  	suffix = in;
  }
  public void setFirstName (String in) {
	firstName = in;
  }  
  public void setMaidenName(String maidenName) {
      this.maidenName = maidenName;
  }
  public void setFromPage (String in) {
	fromPage = in;
  }  
  public void setInformantCity (String in) {
	informantCity = in;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (8/21/2002 2:29:44 PM)
   * @param newInformantEmail java.lang.String
   */
  public void setInformantEmail(java.lang.String newInformantEmail) {
	informantEmail = newInformantEmail;
  }
  public void setInformantFirst (String in) {
	informantFirst = in;
  }  
  public void setInformantLast (String in) {
	informantLast = in;
  }  
  public void setInformantMiddle (String in) {
	informantMiddle = in;
  }  
  public void setInformantPhone (String in) {
	informantPhone = in;
  }  
  public void setInformantRelation (String in) {
	informantRelation = in;
  }  
  public void setInformantSalutation (String in) {
	informantSalutation = in;
  }  
  public void setInformantState (String in) {
	informantState = in;
  }  
  public void setInformantStreet (String in) {
	informantStreet = in;
  }  
  public void setInformantStreet2 (String in) {
	informantStreet2 = in;
  }  
  public void setInformantStreet3 (String in) {
	informantStreet3 = in;
  }  
  public void setInformantZip (String in) {
	informantZip = in;
  }  
  public void setLastName (String in) {
	lastName = in;
  }  
  public void setLocationDeceased (String in) {
	locationDeceased = in;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (8/21/2002 3:28:52 PM)
   * @param newMailToButton java.lang.String
   */
  public void setMailToButton(java.lang.String newMailToButton) {
	mailToButton = newMailToButton;
  }
  public void setMemorialName (String in) {
	memorialName = in;
  }  
  public void setMiddleName (String in) {
	middleName = in;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (10/25/2002 2:50:23 PM)
   * @param newNextContractNumber java.lang.String
   */
  public void setNextContractNumber(java.lang.String newNextContractNumber) {
	nextContractNumber = newNextContractNumber;
  }
  public void setNextKinCity (String in) {
	nextKinCity = in;
  }  
  public void setNextKinSalutation (String in) {
	nextKinSalutation = in;
  } 
  public void setNextKinFirst (String in) {
	nextKinFirst = in;
  }  
  public void setNextKinLast (String in) {
	nextKinLast = in;
  }  
  public void setNextKinMiddle (String in) {
	nextKinMiddle = in;
  }  
  public void setNextKinPhone (String in) {
	nextKinPhone = in;
  }  
  public void setNextKinRelation (String in) {
	nextKinRelation = in;
  }  
  public void setNextKinSame (boolean in) {
	nextKinSame = in;
  }  
  public void setNextKinState (String in) {
	nextKinState = in;
  }  
  public void setNextKinStreet (String in) {
	nextKinStreet = in;
  }  
  public void setNextKinStreet2 (String in) {
	nextKinStreet2 = in;
  }  
  public void setNextKinStreet3 (String in) {
	nextKinStreet3 = in;
  }  
  public void setNextKinZip (String in) {
	nextKinZip = in;
  }  
  public void setDeceasedSame (boolean in) {
	deceasedSame = in;
  }  
  public void setPlaceDeath (String in) {
	placeDeath = in;
  }  
  public void setPlaceDeathCity (String in) {
	placeDeathCity = in;
  }  
  public void setPlaceDeathState (String in) {
	placeDeathState = in;
  }  
  public void setPlaceDeathZip (String in) {
	placeDeathZip = in;
  }  
  public void setPreneedDate (String in) {
	preneedDate = in;
  }  
  public void setServiceDate (String in) {
	serviceDate = in;
  }  
  /**
   * Insert the method's description here.
   * Creation date: (3/4/2002 3:30:21 PM)
   * @param newShippingInfo java.lang.String
   */
  public void setShippingInfo(java.lang.String newShippingInfo) {
	shippingInfo = newShippingInfo;
  }
  public void setSource (String in) {
	source = in;
  }  
  public void setTime (String in) {
	time = in;
  }  
  public void setVitalsId (String in) {
	vitalsId = in;
  }  
  public void setX (int in) {
	x = in;
  }  
  public void setY (int in) {
	y = in;
  }  
  /**
   * @return Returns the executorCity.
   */
  public java.lang.String getExecutorCity() {
	return executorCity;
  }
  /**
   * @param executorCity The executorCity to set.
   */
  public void setExecutorCity(java.lang.String executorCity) {
	this.executorCity = executorCity;
  }
  /**
   * @return Returns the executorFirst.
   */
  public java.lang.String getExecutorFirstName() {
	return executorFirstName;
  }
  /**
   * @param executorFirst The executorFirst to set.
   */
  public void setExecutorFirstName(java.lang.String executorFirst) {
	this.executorFirstName = executorFirst;
  }
  /**
   * @return Returns the executorLast.
   */
  public java.lang.String getExecutorLastName() {
	return executorLastName;
  }
  /**
   * @param executorLast The executorLast to set.
   */
  public void setExecutorLastName(java.lang.String executorLast) {
	this.executorLastName = executorLast;
  }
  /**
   * @return Returns the executorPhone.
   */
  public java.lang.String getExecutorPhone() {
	return executorPhone;
  }
  /**
   * @param executorPhone The executorPhone to set.
   */
  public void setExecutorPhone(java.lang.String executorPhone) {
	this.executorPhone = executorPhone;
  }
  /**
   * @return Returns the executorRelation.
   */
  public java.lang.String getExecutorRelation() {
	return executorRelation;
  }
  /**
   * @param executorRelation The executorRelation to set.
   */
  public void setExecutorRelation(java.lang.String executorRelation) {
	this.executorRelation = executorRelation;
  }
  /**
   * @return Returns the executorSame.
   */
  public boolean isExecutorSame() {
	return executorSame;
  }
  /**
   * @param executorSame The executorSame to set.
   */
  public void setExecutorSame(boolean executorSame) {
	
	logger.debug("firstcallinformationformbase setexecutorsame " + executorSame);
	this.executorSame = executorSame;
  }
  /**
   * @return Returns the executorState.
   */
  public java.lang.String getExecutorState() {
	return executorState;
  }
  /**
   * @param executorState The executorState to set.
   */
  public void setExecutorState(java.lang.String executorState) {
	this.executorState = executorState;
  }
  /**
   * @return Returns the executorStreet.
   */
  public java.lang.String getExecutorStreet() {
	return executorStreet;
  }
  /**
   * @param executorStreet The executorStreet to set.
   */
  public void setExecutorStreet(java.lang.String executorStreet) {
	this.executorStreet = executorStreet;
  }
  /**
   * @return Returns the executorStreet2.
   */
  public java.lang.String getExecutorStreet2() {
	return executorStreet2;
  }
  /**
   * @param executorStreet2 The executorStreet2 to set.
   */
  public void setExecutorStreet2(java.lang.String executorStreet2) {
	this.executorStreet2 = executorStreet2;
  }
  /**
   * @return Returns the executorStreet3.
 */
  public java.lang.String getExecutorStreet3() {
	return executorStreet3;
  }
  /**
   * @param executorStreet3 The executorStreet3 to set.
   */
  public void setExecutorStreet3(java.lang.String executorStreet3) {
	this.executorStreet3 = executorStreet3;
  }
  /**
   * @return Returns the executorZip.
   */
  public java.lang.String getExecutorZip() {
	return executorZip;
  }
  /**
   * @param executorZip The executorZip to set.
   */
  public void setExecutorZip(java.lang.String executorZip) {
	this.executorZip = executorZip;
  }
  /**
   * @return Returns the executorEmail.
   */
  public java.lang.String getExecutorEmail() {
	return executorEmail;
  }
  /**
   * @param executorEmail The executorEnauk to set.
   */
  public void setExecutorEmail(java.lang.String executorEmail) {
	this.executorEmail = executorEmail;
  }
  /**
   * @return Returns the executorPersonId.
   */
  public java.lang.String getExecutorPersonId() {
	return executorPersonId;
  }
  /**
   * @param executorPersonId The executorPersonId to set.
   */
  public void setExecutorPersonId(java.lang.String executorPersonId) {
	this.executorPersonId = executorPersonId;
  }
	
  public boolean getInformantContractSigner() {
	return informantContractSigner;
  }
	
  public void setInformantContractSigner(boolean in) {
	informantContractSigner = in;
  }
	
  public boolean getShowInformantContractSigner() {
	return showInformantContractSigner;
  }
	
  public void setShowInformantContractSigner(boolean in) {
	showInformantContractSigner = in;
  }
	
  public void setCem_plottype (String in) {
	cem_plottype = in;
  }  
	
  public void setCem_section (String in) {
	cem_section = in;
  }  
	  
  public void setCem_block (String in) {
	cem_block = in;
  }  
	  
  public void setCem_lot_tier (String in) {
	cem_lot_tier = in;
  } 
	  
  public void setCem_grave_row (String in) {
	cem_grave_row = in;
  }   
	
  public void setCem_Amount (int in) {
	cem_Amount = in;
  }
  public void setCem_ANBuyerAptNo (String in) {
	cem_ANBuyerAptNo = in ;
  }  
  public void setCem_ANBuyerCity (String in) {
	cem_ANBuyerCity = in ;
  }  
  public void setCem_ANBuyerFirstName (String in) {
	cem_ANBuyerFirstName = in ;
  }  
  public void setCem_ANBuyerLastName (String in) {
	cem_ANBuyerLastName  = in;
  }  
  public void setCem_ANBuyerMidName (String in) {
	cem_ANBuyerMidName = in ;
  }  
  public void setCem_ANBuyerState (String in) {
	cem_ANBuyerState = in ;
  }  
  public void setCem_ANBuyerStreet (String in) {
	cem_ANBuyerStreet = in ;
  }  
  public void setCem_ANBuyerTitle (String in) {
	cem_ANBuyerTitle = in ;
  }  
  public void setCem_ANBuyerZip (String in) {
	cem_ANBuyerZip = in ;
  }	
  public void setCem_ANBuyerPhone(String in) {
	cem_ANBuyerPhone = in;
  }  
  public void  setCem_MapID (String in) {
	cem_MapID = in ;
  }	
  public void setCem_Record (String in) {
	cem_Record = in ;
  }
  public void setCem_ContractDate (String in) {
	cem_ContractDate = in ;
  }
  public void setCem_MiscDesc (String in) {
	cem_MiscDesc = in ;
  }
  public void setCem_MiscAmount (int in) {
	cem_MiscAmount = in ;
  }
  public void setBeneficiaryFirst (String in) {
	beneficiaryFirst = in ;
  }  
  public void setBeneficiaryMiddle (String in) {
	  beneficiaryMiddle = in ;
  }  
  public void setBeneficiaryLast (String in) {
	  beneficiaryLast = in ;
  }  
  public void setBeneficiaryStreet (String in) {
	  beneficiaryStreet = in ;
  }  
  public void setBeneficiaryCity (String in) {
	  beneficiaryCity = in ;
  }
  public void setBeneficiaryState (String in) {
	  beneficiaryState = in ;
  }
  public void setBeneficiaryZipCode (String in) {
	  beneficiaryZipCode = in ;
  }  
  public void setBeneficiaryPhone (String in) {
	  beneficiaryPhone = in ;
  }
  public void setBeneficiarySocialSecurityNumber (String in) {
	  benneficiarySocialSecurityNumber = in ;
  }
  public void setBeneficiaryTitle (String in) {
	  beneficiaryTitle = in ;
  }  
	  
  public void setBeneficiaryAptno (String in) {
	  beneficiaryAptno = in ;
  }  

}
