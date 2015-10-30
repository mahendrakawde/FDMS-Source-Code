package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbLocations - This class contains one chapel location data.
 * Creation date: (11/27/2001 2:42:35 PM)
 * @author:
 */
public class DbLocation extends com.aldorsolutions.webfdms.database.Persistent {
    static private final DbLocationPeer peer = new DbLocationPeer();
    
    private String name;
    private String locationNumber;
    private String companyNumber;
    private String division;
    private int stdServiceCharge;
    private String packageVersion;
    private String inactiveCode;
    private String priceListVersion;
    private int localeNumber;
    private String cashAcct;
    private String arAcct;
    private String financeChargeAcct;
    private String apAcct;
    private String discountAcct;
    private long nextCheckNumber;
    private String discountHandlingCode;
    private int cashBalance;
    private String addr1;
    private String addr2;
    private String addr3;
    private String city;
    private String state;
    private String zip;
    private String county;
    private String phone;
    private String phoneAlternate;
    private String licenseNumber;
    private String website;
    private String email;
    private String preferenceGenericVitals;
    private String managerName;
    private String undepositedFundsAcct;
    private boolean useUndepositedFundsAcct;
    private long accountantUserID;
	private double externalVendorLimit;
	private double internalVendorLimit;
	private String oneTimeVendorCode;
	private String funeralSyncLocationId;
	private int merchandiseId;
	private String turnOnApplyFinanceCharge;
	private String monthlyInterestRate;
	private String eRegisterPage;
	private String eRegisterTargetPage;
	
	public String getTurnOnApplyFinanceCharge() {
		return turnOnApplyFinanceCharge;
	}
	public void setTurnOnApplyFinanceCharge(String turnOnApplyFinanceCharge) {
		this.turnOnApplyFinanceCharge = turnOnApplyFinanceCharge;
		modify();
	}
	public String getMonthlyInterestRate() {
		return monthlyInterestRate;
	}
	public void setMonthlyInterestRate(String monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
		modify();
	}
	public String getERegisterPage() {
		return eRegisterPage;
	}
	public void setERegisterPage(String registerPage) {
		this.eRegisterPage = registerPage;
		modify();
	}
	public String getERegisterTargetPage() {
		return eRegisterTargetPage;
	}
	public void setERegisterTargetPage(String registerTargetPage) {
		this.eRegisterTargetPage = registerTargetPage;
		modify();
	}
	public int getMerchandiseId() {
		return merchandiseId;
	}
	public void setMerchandiseId(int merchandiseId) {
		this.merchandiseId = merchandiseId;
		modify();
	}
	public String getFuneralSyncLocationId() {
		return funeralSyncLocationId;
	}
	public void setFuneralSyncLocationId(String funeralSyncLocationId) {
		this.funeralSyncLocationId = funeralSyncLocationId;
		modify();
	}
	public String getOneTimeVendorCode() {
		return oneTimeVendorCode;
	}
	public void setOneTimeVendorCode(String oneTimeVendorCode) {
		this.oneTimeVendorCode = oneTimeVendorCode;
		modify();
	}
	/**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:06 AM)
     * @return String
     */
    public String getAddr1() {
        return addr1;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:15 AM)
     * @return String
     */
    public String getAddr2() {
        return addr2;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:26 AM)
     * @return String
     */
    public String getAddr3() {
        return addr3;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:26 AM)
     * @return String
     */
    public String getWebsite() {
        return website;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:26 AM)
     * @return String
     */
    public String getEmail() {
        return email;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:10:52 PM)
     * @return String
     */
    public String getApAcct() {
        return apAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 5:11:52 PM)
     * @return String
     */
    public String getArAcct() {
        return arAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 5:11:38 PM)
     * @return String
     */
    public String getCashAcct() {
        return cashAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:12:06 PM)
     * @return int
     */
    public int getCashBalance() {
        return cashBalance;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:37 AM)
     * @return String
     */
    public String getCity() {
        return city;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:29:16 PM)
     * @return String
     */
    public String getCompanyNumber() {
        return companyNumber;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:11:03 PM)
     * @return String
     */
    public String getDiscountAcct() {
        return discountAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:11:41 PM)
     * @return String
     */
    public String getDiscountHandlingCode() {
        return discountHandlingCode;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 5:12:14 PM)
     * @return String
     */
    public String getFinanceChargeAcct() {
        return financeChargeAcct;
    }
    public String getUndepositedFundsAcct() {
        return undepositedFundsAcct;
    }
    public boolean getUseUndepositedFundsAcct() {
        return useUndepositedFundsAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:32:20 PM)
     * @return char
     */
    public String getInactiveCode() {
        return inactiveCode;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:56:48 AM)
     * @return String
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/24/2001 3:03:13 PM)
     * @return int
     */
    public int getLocaleNumber() {
        return localeNumber;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/24/2003 10:25:25 AM)
     * @return String
     */
    public String getManagerName() {
        return managerName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:28:58 PM)
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:28:58 PM)
     * @return String
     */
    public String getDivision() {
        return division;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:11:26 PM)
     * @return int
     */
    public long getNextCheckNumber() {
        return nextCheckNumber;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:32:08 PM)
     * @return String
     */
    public String getPackageVersion() {
        return packageVersion;
    }
    /**
     * getPersistentPeer method comment.
     */
    protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
        return peer;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:56:22 AM)
     * @return String
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:56:31 AM)
     * @return String
     */
    public String getPhoneAlternate() {
        return phoneAlternate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/26/2002 2:31:54 PM)
     * @return String
     */
    public String getPreferenceGenericVitals() {
        return preferenceGenericVitals;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:35:46 PM)
     * @return String
     */
    public String getPriceListVersion() {
        return priceListVersion;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:47 AM)
     * @return String
     */
    public String getState() {
        return state;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:29:38 PM)
     * @return int
     */
    public int getStdServiceCharge() {
        return stdServiceCharge;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:56:11 AM)
     * @return String
     */
    public String getZip() {
        return zip;
    }
    
    public String getCounty() {
        return county;
    }
    /**
     * isLocked method comment.
     */
    public boolean isLocked() {
        return false;
    }
    /**
     * Move data from hashtable and copy into class variables
     * Peer object restores from database to hashtable.
     */
    public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
        setId(  FormatNumber.parseInteger(data.get(DbLocationPeer.IDENTITY).toString()));
        name        = data.get(DbLocationPeer.NAME).toString();
        companyNumber   = data.get(DbLocationPeer.COMPANYNO).toString();
        locationNumber  = data.get(DbLocationPeer.LOCATIONNUM).toString();
        division		= data.get(DbLocationPeer.DIVISION).toString();
        stdServiceCharge  = FormatNumber.parseInteger(data.get(DbLocationPeer.SERVICEFEE).toString());
        priceListVersion  = data.get(DbLocationPeer.PRICELIST).toString();
        packageVersion    = data.get(DbLocationPeer.PACKAGELIST).toString();
        inactiveCode    = data.get(DbLocationPeer.INACTIVE).toString();
        localeNumber    = FormatNumber.parseInteger(data.get(DbLocationPeer.LOCALE).toString());
        cashAcct      = data.get(DbLocationPeer.CASHACCT).toString();
        arAcct        = data.get(DbLocationPeer.ARACCT).toString();
        financeChargeAcct = data.get(DbLocationPeer.FINCHRGACCT).toString();
        apAcct        = data.get(DbLocationPeer.APACCT).toString();
        discountAcct    = data.get(DbLocationPeer.DISCOUNTACCT).toString();
        nextCheckNumber   = FormatNumber.parseLong(data.get(DbLocationPeer.NEXTCHECKNO).toString());
        discountHandlingCode= data.get(DbLocationPeer.DISCOUNTCODE).toString();
        cashBalance     = FormatNumber.parseInteger(data.get(DbLocationPeer.CASHBALANCE).toString());
        addr1       = data.get(DbLocationPeer.ADDR1).toString();
        addr2       = data.get(DbLocationPeer.ADDR2).toString();
        addr3       = data.get(DbLocationPeer.ADDR3).toString();
        city        = data.get(DbLocationPeer.CITY).toString();
        state       = data.get(DbLocationPeer.STATE).toString();
        zip         = data.get(DbLocationPeer.ZIPCODE).toString();
        county      = data.get(DbLocationPeer.COUNTY).toString();
        phone       = data.get(DbLocationPeer.PHONE).toString();
        phoneAlternate    = data.get(DbLocationPeer.ALTPHONE).toString();
        licenseNumber   = data.get(DbLocationPeer.LICENSENO).toString();
        website		= data.get(DbLocationPeer.WEBSITE).toString();
        email		= data.get(DbLocationPeer.EMAIL).toString();
        preferenceGenericVitals = data.get(DbLocationPeer.PREFVITALS).toString();
        managerName     = data.get(DbLocationPeer.MANAGERNAME).toString();
        undepositedFundsAcct = data.get(DbLocationPeer.UNDEPOSITED_FUNDS_ACCT).toString();
        useUndepositedFundsAcct = FormatNumber.parseInteger(data.get(DbLocationPeer.USE_UNDEPOSITED_FUNDS_ACCT).toString()) == 1;
        accountantUserID = FormatNumber.parseInteger(data.get(DbLocationPeer.ACCTUSERID).toString());
    	externalVendorLimit = Double.parseDouble( data.get(DbLocationPeer.EXTERNALVENDORLIMIT).toString() ) ;
    	internalVendorLimit = Double.parseDouble( data.get(DbLocationPeer.INTERNALVENDORLIMIT).toString() ) ;
    	oneTimeVendorCode = data.get(DbLocationPeer.ONETIMEVENDORCODE).toString();
    	funeralSyncLocationId = data.get(DbLocationPeer.FUNERALSYNCLOCATIONID).toString();
    	merchandiseId    = FormatNumber.parseInteger(data.get(DbLocationPeer.MERCHANDISEID).toString());
    	turnOnApplyFinanceCharge = data.get(DbLocationPeer.TURNONAPPLYFINANCECHARGE).toString();
    	monthlyInterestRate = data.get(DbLocationPeer.MONTHLYINTERESTRATE).toString();
    	eRegisterPage = data.get(DbLocationPeer.EREGISTERPAGE).toString();
    	eRegisterTargetPage = data.get(DbLocationPeer.EREGISTERTARGETPAGE).toString();
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:06 AM)
     * @param newAddr1 String
     */
    public void setAddr1(String newAddr1) {
        addr1 = newAddr1;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:15 AM)
     * @param newAddr2 String
     */
    public void setAddr2(String newAddr2) {
        addr2 = newAddr2;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:26 AM)
     * @param newAddr3 String
     */
    public void setAddr3(String newAddr3) {
        addr3 = newAddr3;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:26 AM)
     * @param newAddr3 String
     */
    public void setWebsite(String newWebsite) {
        website = newWebsite;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:26 AM)
     * @param newAddr3 String
     */
    public void setEmail(String newEmail) {
        email = newEmail;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:10:52 PM)
     * @param newApAcct String
     */
    public void setApAcct(String newApAcct) {
        apAcct = newApAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 5:11:52 PM)
     * @param newArAcct String
     */
    public void setArAcct(String newArAcct) {
        arAcct = newArAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 5:11:38 PM)
     * @param newCashAcct String
     */
    public void setCashAcct(String newCashAcct) {
        cashAcct = newCashAcct;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:12:06 PM)
     * @param newCashBalance int
     */
    public void setCashBalance(int newCashBalance) {
        cashBalance = newCashBalance;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:37 AM)
     * @param newCity String
     */
    public void setCity(String newCity) {
        city = newCity;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:29:16 PM)
     * @param newCompanyNumber String
     */
    public void setCompanyNumber(String newCompanyNumber) {
        companyNumber = newCompanyNumber;
        modify();
    }
    public void setDivision(String newDivision) {
        division = newDivision;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:11:03 PM)
     * @param newDiscountAcct String
     */
    public void setDiscountAcct(String newDiscountAcct) {
        discountAcct = newDiscountAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:11:41 PM)
     * @param newDiscountHandlingCode String
     */
    public void setDiscountHandlingCode(String newDiscountHandlingCode) {
        discountHandlingCode = newDiscountHandlingCode;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 5:12:14 PM)
     * @param newFinanceChargeAcct String
     */
    public void setFinanceChargeAcct(String newFinanceChargeAcct) {
        financeChargeAcct = newFinanceChargeAcct;
    }
    public void setUndepositedFundsAcct(String newUndepositedFundsAcct) {
        undepositedFundsAcct = newUndepositedFundsAcct;
    }
    public void setUseUndepositedFundsAcct(boolean newUseUndepositedFundsAcct) {
        useUndepositedFundsAcct = newUseUndepositedFundsAcct;
    }
    /**
     * Get the ID key for this object from the hashtable.
     * DbUser objects can be accessed by "Name"
     * So, first see if "Name" is being used for restoring
     * or if ID# is being used.
     */
    public void setId(java.util.Hashtable h) {
        //if (h.containsKey(DbLocationPeer.NAME)){
        //  getPersistentPeer().restore(this, t);
        //}
        setId(((Integer)h.get(DbLocationPeer.IDENTITY)).intValue());
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:32:20 PM)
     * @param newInactiveCode char
     */
    public void setInactiveCode(String newInactiveCode) {
        inactiveCode = newInactiveCode;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:56:48 AM)
     * @param newLicenseNumber String
     */
    public void setLicenseNumber(String newLicenseNumber) {
        licenseNumber = newLicenseNumber;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/24/2001 3:03:13 PM)
     * @param newLocaleNumber int
     */
    public void setLocaleNumber(int newLocaleNumber) {
        localeNumber = newLocaleNumber;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/24/2003 10:25:25 AM)
     * @param newManagerName String
     */
    public void setManagerName(String newManagerName) {
        managerName = newManagerName;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:28:58 PM)
     * @param newName String
     */
    public void setName(String newName) {
        name = newName;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/29/2002 5:11:26 PM)
     * @param newNextCheckNumber int
     */
    public void setNextCheckNumber(long newNextCheckNumber) {
        nextCheckNumber = newNextCheckNumber;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:32:08 PM)
     * @param newPackageVersion String
     */
    public void setPackageVersion(String newPackageVersion) {
        packageVersion = newPackageVersion;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:56:22 AM)
     * @param newPhone String
     */
    public void setPhone(String newPhone) {
        phone = newPhone;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:56:31 AM)
     * @param newPhoneAlternate String
     */
    public void setPhoneAlternate(String newPhoneAlternate) {
        phoneAlternate = newPhoneAlternate;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/26/2002 2:31:54 PM)
     * @param newPreferenceGenericVitals String
     */
    public void setPreferenceGenericVitals(String newPreferenceGenericVitals) {
        preferenceGenericVitals = newPreferenceGenericVitals;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:35:46 PM)
     * @param newPriceListVersion String
     */
    public void setPriceListVersion(String newPriceListVersion) {
        priceListVersion = newPriceListVersion;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:55:47 AM)
     * @param newState String
     */
    public void setState(String newState) {
        state = newState;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/28/2001 8:29:38 PM)
     * @param newStdServiceCharge int
     */
    public void setStdServiceCharge(int newStdServiceCharge) {
        stdServiceCharge = newStdServiceCharge;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/30/2002 8:56:11 AM)
     * @param newZip String
     */
    public void setZip(String newZip) {
        zip = newZip;
        modify();
    }
    
    public void setCounty(String county) {
        this.county = county;
        modify();
    }
	/**
	 * @return the locationNumber
	 */
	public String getLocationNumber() {
		return locationNumber;
	}
	
	/**
	 * @param locationNumber the locationNumber to set
	 */
	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
		modify();
	}

	/**
	 * @return the accountantUserID
	 */
	public long getAccountantUserID() {
		return accountantUserID;
	}

	/**
	 * @param accountantUserID
	 *            the accountantUserID to set
	 */
	public void setAccountantUserID(long accountantUserID) {
		this.accountantUserID = accountantUserID;
		modify();
	}

	/**
	 * @return the externalVendorLimit
	 */
	public double getExternalVendorLimit() {
		return externalVendorLimit;
	}

	/**
	 * @return the internalVendorLimit
	 */
	public double getInternalVendorLimit() {
		return internalVendorLimit;
	}

	/**
	 * @param externalVendorLimit
	 *            the externalVendorLimit to set
	 */
	public void setExternalVendorLimit(double externalVendorLimit) {
		this.externalVendorLimit = externalVendorLimit;
		modify();
	}

	/**
	 * @param internalVendorLimit
	 *            the internalVendorLimit to set
	 */
	public void setInternalVendorLimit(double internalVendorLimit) {
		this.internalVendorLimit = internalVendorLimit;
		modify();
	}
	
}