package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * This class represents name, address, date, information specific to one deceased.
 * Creation date: (11/14/2001 3:55:17 PM)
 * @author:
 */
public class DbVitalsDeceased extends com.aldorsolutions.webfdms.database.Persistent implements com.aldorsolutions.webfdms.beans.DbVitalsDeceasedI {
    static private final DbVitalsDeceasedPeer peer = new DbVitalsDeceasedPeer();

    private Logger logger = Logger.getLogger(DbVitalsDeceased.class.getName());
    
    private String birthDate;
    private String burialDate;
    private String ServiceDateKey;
    private String burialPermitNumber;
    private String deathDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String maidenName;
    private String maritalStatus;
    private String mrmrs;
    private String cityTwpName;
    private String cityTwpBox;
    private String county;
    private String insideCity;
    private String timeAtAddress;
    private String streetAddr;
    private String mailingCity;
    private String state;
    private String zip;
    private String country;
    private String sex;
    private String ssno;
    private String stateOfDeath;
    private String timeOfDeath;
    private String timeOfDeathStatus;
    
    private String occupation;
    private String kindBusiness;
    private int yearsAtOccupation;
    private String birthPlace;
    private String birthPlaceCSV;
    private String veteranYN;
    private String wasPeaceOfficerYN;
    private String ancestry;
    private String race;
    private String hispanicYN;
    private String elementaryEducation;
    private String collegeEducation;
    private String fatherFullName;
    private String fatherFirstName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String motherFullName;
    private String motherFirstName;
    private String motherMiddleName;
    private String motherLastName;
    private String motherBirthCity;
    private String motherBirthday;
    private String motherStreetAddress;
    private String fatherBirthday;
    private String motherBirthState;
    private String motherBirthCountry;
    private String fatherBirthCity;
    private String fatherBirthState;
    private String fatherBirthCountry;
    private String DeceasedPrefix;
    private String suffix;
    private String aliasFirst;
    private String aliasMiddle;
    private String aliasLast;
    private String aliasPrefix;
    private String aliasSuffix;
    private String crematoryName;
    private String militaryOrganizatn;
    private String warFromDate;
    private String warToDate;
    private String decAptNo;
    private String citizenship;
    
    private String LocalityUnincorporated;
    private String DecEducation;
    private String LoctnOfDeathZip;
    private String TribalReservation;
    private String DecSpecifyHispanic;
    private String cremationAuthorizer;
    
    private String customerName;
    
    private String phone;
    private String fatherForename;
    private String fatherSurnameBirth;
    private String fatherOtherSurname;
    private String fatherOccupation;
    private String motherForename;
    private String motherSurnameBirth;
    private String motherOtherSurname;
    private String motherOccupation;
    private String stillBirthDesc;
    private int pregnancyDuration;
    private int numberChildren;
    private int numberLiveborn;
    private int numberStillborn;
    private String childWeight;
    private String birthType;
    private String birthOrder;
    private String childNameAgreed;
    private int fatherAge;
    private int motherAge;
    
    private String deceasedSame;	// whether the deceased's address is the same as the informant (Y/N)
    
    private String aboriginal;
    private String livedOnReserve;
    private String employer;
    private String countyOfBirth;
    
    private String countyOfIssue;
    private String issueDate;
    
    private String motherMaidenName;
    private String fatherMaidenName;
    private String identificationMarks;
    
    private String tribalMember;
    private String tribalName;
    private String locOfDeathCVT;
    
    private String transferredLocationName;
    private String transferredLocationAddr;
    private String transferredLocationCity;
    private String transferredLocationState;
    private String transferredLocationZip;
    private String transferredLocationPhone;
    
    private String hospitalNameOfBirthPlaceOfDeceasedUnder1YearOld;
    
    private String employerAddr;
    private String residentWithinCityVillageLimit;
    private String residentLocalityLimitName;
    
    private String hospitalizedLast2Months;
    private String pregnantDeliveryDate;
  
	public String getHospitalizedLast2Months() {
		return hospitalizedLast2Months;
	}
	public void setHospitalizedLast2Months(String hospitalizedLast2Months) {
		this.hospitalizedLast2Months = hospitalizedLast2Months;
	}
	public String getPregnantDeliveryDate() {
		return pregnantDeliveryDate;
	}
	public void setPregnantDeliveryDate(String pregnantDeliveryDate) {
		this.pregnantDeliveryDate = pregnantDeliveryDate;
	}
	public String getResidentWithinCityVillageLimit() {
		return residentWithinCityVillageLimit;
	}
	public void setResidentWithinCityVillageLimit(
			String residentWithinCityVillageLimit) {
		this.residentWithinCityVillageLimit = residentWithinCityVillageLimit;
	}
	
	public String getResidentLocalityLimitName() {
		return residentLocalityLimitName;
	}
	public void setResidentLocalityLimitName(String residentLocalityLimitName) {
		this.residentLocalityLimitName = residentLocalityLimitName;
	}
	public String getEmployerAddr() {
		return employerAddr;
	}
	public void setEmployerAddr(String employerAddr) {
		this.employerAddr = employerAddr;
	}
	public String getHospitalNameOfBirthPlaceOfDeceasedUnder1YearOld() {
		return hospitalNameOfBirthPlaceOfDeceasedUnder1YearOld;
	}
	public void setHospitalNameOfBirthPlaceOfDeceasedUnder1YearOld(
			String hospitalNameOfBirthPlaceOfDeceasedUnder1YearOld) {
		this.hospitalNameOfBirthPlaceOfDeceasedUnder1YearOld = hospitalNameOfBirthPlaceOfDeceasedUnder1YearOld;
	}
	public String getTransferredLocationName() {
		return transferredLocationName;
	}
	public void setTransferredLocationName(String transferredLocationName) {
		this.transferredLocationName = transferredLocationName;
	}
	public String getTransferredLocationAddr() {
		return transferredLocationAddr;
	}
	public void setTransferredLocationAddr(String transferredLocationAddr) {
		this.transferredLocationAddr = transferredLocationAddr;
	}
	public String getTransferredLocationCity() {
		return transferredLocationCity;
	}
	public void setTransferredLocationCity(String transferredLocationCity) {
		this.transferredLocationCity = transferredLocationCity;
	}
	public String getTransferredLocationState() {
		return transferredLocationState;
	}
	public void setTransferredLocationState(String transferredLocationState) {
		this.transferredLocationState = transferredLocationState;
	}
	public String getTransferredLocationZip() {
		return transferredLocationZip;
	}
	public void setTransferredLocationZip(String transferredLocationZip) {
		this.transferredLocationZip = transferredLocationZip;
	}
	public String getTransferredLocationPhone() {
		return transferredLocationPhone;
	}
	public void setTransferredLocationPhone(String transferredLocationPhone) {
		this.transferredLocationPhone = transferredLocationPhone;
	}
	public String getTimeOfDeathStatus() {
		return timeOfDeathStatus;
	}
	public void setTimeOfDeathStatus(String timeOfDeathStatus) {
		this.timeOfDeathStatus = timeOfDeathStatus;
	}
	public String getTribalMember() {
		return tribalMember;
	}
	public void setTribalMember(String tribalMember) {
		this.tribalMember = tribalMember;
	}
	public String getTribalName() {
		return tribalName;
	}
	public void setTribalName(String tribalName) {
		this.tribalName = tribalName;
	}
	/**
	 * @return the motherMaidenName
	 */
	public String getMotherMaidenName() {
		return motherMaidenName;
	}
	/**
	 * @param motherMaidenName the motherMaidenName to set
	 */
	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}
	/**
	 * @return the fatherMaidenName
	 */
	public String getFatherMaidenName() {
		return fatherMaidenName;
	}
	/**
	 * @param fatherMaidenName the fatherMaidenName to set
	 */
	public void setFatherMaidenName(String fatherMaidenName) {
		this.fatherMaidenName = fatherMaidenName;
	}
	/**
     * DbVitalsDeceased constructor comment.
     */
    public DbVitalsDeceased() {
        super();
    }
    /**
     * Split father's full name into first, middle, last
     * Creation date: (9/19/2002 10:41:12 AM)
     */
    public void convertFatherFullToSplit() {
        if (getFatherFullName() == null) return;
        
        java.util.StringTokenizer names = new java.util.StringTokenizer(getFatherFullName() );
        
        int namecount = names.countTokens();
        if (namecount < 1) return;
        
        if (namecount ==1){
            setFatherFirstName(getFatherFullName());
            return;
        }
        // since more than one name, set first name to first token
        setFatherFirstName(names.nextToken());
        
        // if exactly two names set last name to 2nd and return
        if (namecount==2){
            setFatherLastName(names.nextToken());
            return;
        }
        // otherwise put 2nd token in middle name
        setFatherMiddleName(names.nextToken());
        // put remaininder in last name
        StringBuffer lastname = new StringBuffer();
        boolean addspace = false;
        
        while (names.hasMoreTokens()){
            if (addspace) lastname.append(" ");
            else addspace=true;
            
            lastname.append(names.nextToken());
        }
        setFatherLastName(lastname.toString());
    }
    /**
     * Split Mother's full name into first, middle, last
     * Creation date: (9/19/2002 10:41:12 AM)
     */
    public void convertMotherFullToSplit() {
        if (getMotherFullName() == null) return;
        
        java.util.StringTokenizer names = new java.util.StringTokenizer(getMotherFullName() );
        
        int namecount = names.countTokens();
        if (namecount < 1) return;
        
        if (namecount ==1){
            setMotherFirstName(getMotherFullName());
            return;
        }
        // since more than one name, set first name to first token
        setMotherFirstName(names.nextToken());
        
        // if exactly two names set last name to 2nd and return
        if (namecount==2){
            setMotherLastName(names.nextToken());
            return;
        }
        // otherwise put 2nd token in middle name
        setMotherMiddleName(names.nextToken());
        // put remaininder in last name
        StringBuffer lastname = new StringBuffer();
        boolean addspace = false;
        
        while (names.hasMoreTokens()){
            if (addspace) lastname.append(" ");
            else addspace=true;
            
            lastname.append(names.nextToken());
        }
        setMotherLastName(lastname.toString());
    }
    public String getCustomerName() {
        return customerName;
    }
	public String getBirthDate() {
		return birthDate;
	}
	public String getBirthOrder() {
		return birthOrder;
	}
	public String getBirthType() {
		return birthType;
	}
	public String getBurialDate() {
		return burialDate;
	}
	public String getBurialPermitNumber() {
		return burialPermitNumber;
	}
	public int getYearsAtOccupation() {
		return yearsAtOccupation;
	}
	public String getChildNameAgreed() {
		return childNameAgreed;
	}
	public String getChildWeight() {
		return childWeight;
	}
	public String getCityTwpBox() {
		return cityTwpBox;
	}
	public String getCityTwpName() {
		return cityTwpName;
	}
	public String getCountry() {
		return country;
	}
	public String getCounty() {
		return county;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public String getFatherForename() {
		return fatherForename;
	}
	public String getFatherOccupation() {
		
		return fatherOccupation;
				
	}
	public String getFatherOtherSurname() {
		return fatherOtherSurname;
	}
	public String getFatherSurnameBirth() {
		return fatherSurnameBirth;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getFullName() {
		return fullName;
	}
	public String getInsideCity() {
		return insideCity;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMaidenName() {
		return maidenName;
	}
	public String getMailingCity() {
		return mailingCity;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getMotherForename() {
		return motherForename;
	}
	public String getMotherOccupation() {
		return motherOccupation;
	}
	public String getMotherOtherSurname() {
		return motherOtherSurname;
	}
	public String getMotherSurnameBirth() {
		return motherSurnameBirth;
	}
	public String getMrmrs() {
		return mrmrs;
	}
	public int getNumberChildren() {
		return numberChildren;
	}
	public int getNumberLiveborn() {
		return numberLiveborn;
	}
	public int getNumberStillborn() {
		return numberStillborn;
	}
	public String getPhone() {
		return phone;
	}
	public int getPregnancyDuration() {
		return pregnancyDuration;
	}
	public String getSsno() {
		return ssno;
	}
	public String getState() {
		return state;
	}
	public String getStateOfDeath() {
		return stateOfDeath;
	}
	public String getStillBirthDesc() {
		return stillBirthDesc;
	}
	public String getStreetAddr() {
		return streetAddr;
	}
	public String getTimeAtAddress() {
		return timeAtAddress;
	}
	public String getZip() {
		return zip;
	}
	public String getCountyOfIssue() {
		return countyOfIssue;
	}
	public String getIssueDate() {
		return issueDate;
	}	
	
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
        modify();
	}
	public void setBirthOrder(String birthOrder) {
		this.birthOrder = birthOrder;
		modify();
	}
	public void setBirthType(String birthType) {
		this.birthType = birthType;
		modify();
	}
	public void setBurialDate(String burialDate) {
		this.burialDate = burialDate;
		modify();
	}
	public void setBurialPermitNumber(String input) {
		modify();
		burialPermitNumber = input;
	}
	public void setYearsAtOccupation(int input) {
		modify();
		yearsAtOccupation = input;
	}
	public void setChildNameAgreed(String childNameAgreed) {
		this.childNameAgreed = childNameAgreed;
		modify();
	}
	public void setChildWeight(String childWeight) {
		this.childWeight = childWeight;
		modify();
	}
	public void setCityTwpBox(String cityTwpBox) {
		this.cityTwpBox = cityTwpBox;
		modify();
	}
	public void setCityTwpName(String cityTwpName) {
		this.cityTwpName = cityTwpName;
		modify();
	}
	public void setCountry(String country) {
		this.country = country;
		modify();
	}
	public void setCounty(String county) {
		this.county = county;
		modify();
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
		modify();
	}
	public void setFatherForename(String fatherForename) {
		this.fatherForename = fatherForename;
		modify();
	}
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
		modify();
	}
	public void setFatherOtherSurname(String fatherOtherSurname) {
		this.fatherOtherSurname = fatherOtherSurname;
		modify();
	}
	public void setFatherSurnameBirth(String fatherSurnameBirth) {
		this.fatherSurnameBirth = fatherSurnameBirth;
		modify();
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		modify();
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
		modify();
	}
	public void setInsideCity(String insideCity) {
		this.insideCity = insideCity;
		modify();
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
		modify();
	}
	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
		modify();
	}
	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
		modify();
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
		modify();
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
		modify();
	}
	public void setMotherForename(String motherForename) {
		this.motherForename = motherForename;
		modify();
	}
	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
		modify();
	}
	public void setMotherOtherSurname(String motherOtherSurname) {
		this.motherOtherSurname = motherOtherSurname;
		modify();
	}
	public void setMotherSurnameBirth(String motherSurnameBirth) {
		this.motherSurnameBirth = motherSurnameBirth;
		modify();
	}
	public void setMrmrs(String mrmrs) {
		this.mrmrs = mrmrs;
		modify();
	}
	public void setNumberChildren(int numberChildren) {
		this.numberChildren = numberChildren;
		modify();
	}
	public void setNumberLiveborn(int numberLiveborn) {
		this.numberLiveborn = numberLiveborn;
		modify();
	}
	public void setNumberStillborn(int numberStillborn) {
		this.numberStillborn = numberStillborn;
		modify();
	}
	public void setPhone(String phone) {
		this.phone = phone;
		modify();
	}
	public void setPregnancyDuration(int pregnancyDuration) {
		this.pregnancyDuration = pregnancyDuration;
		modify();
	}
	public void setSsno(String ssno) {
		this.ssno = ssno;
		modify();
	}
	public void setState(String state) {
		this.state = state;
		modify();
	}
	public void setStateOfDeath(String stateOfDeath) {
		this.stateOfDeath = stateOfDeath;
		modify();
	}
	public void setStillBirthDesc(String stillBirthDesc) {
		this.stillBirthDesc = stillBirthDesc;
		modify();
	}
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
		modify();
	}
	public void setTimeAtAddress(String timeAtAddress) {
		this.timeAtAddress = timeAtAddress;
		modify();
	}
	public void setZip(String zip) {
		this.zip = zip;
		modify();
	}
	public void setCountyOfIssue(String countyOfIssue) {
		this.countyOfIssue = countyOfIssue;
		modify();
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
		modify();
	}	
	
	
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:02:27 AM)
     * @return String
     */
    public String getAliasFirst() {
        return aliasFirst;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:02:52 AM)
     * @return String
     */
    public String getAliasLast() {
        return aliasLast;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:02:39 AM)
     * @return String
     */
    public String getAliasMiddle() {
        return aliasMiddle;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:03:06 AM)
     * @return String
     */
    public String getAliasPrefix() {
        return aliasPrefix;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:03:16 AM)
     * @return String
     */
    public String getAliasSuffix() {
        return aliasSuffix;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:45 PM)
     * @return String
     */
    public String getAncestry() {
        return ancestry;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:19 PM)
     * @return String
     */
    public String getBirthPlace() {
        return birthPlace;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:19 PM)
     * @return String
     */
    public String getBirthPlaceCSV() {
        return birthPlaceCSV;
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/11/2003 10:28:36 AM)
     * @return String
     */
    public String getCitizenship() {
        return citizenship;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:35:48 PM)
     * @return String
     */
    public String getCollegeEducation() {
        return collegeEducation;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:03:41 AM)
     * @return String
     */
    public String getCrematoryName() {
        return crematoryName;
    }
    /**
     * getDateOfBirth method comment.
     */
    public String getDateOfBirth() {
        return birthDate;
    }
    /**
     * getDateOfBurial method comment.
     */
    public String getDateOfBurial() {
        return burialDate;
    }
    /**
     * getDateOfDeath method comment.
     */
    public String getDateOfDeath() {
        return deathDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/4/2003 2:54:42 PM)
     * @return String
     */
    public String getDecAptNo() {
        return decAptNo;
    }
    /**
     * getDecFName method comment.
     */
    public String getDecFName() {
        return firstName;
    }
    /**
     * getDecFullName method comment.
     */
    public String getDecFullName() {
        return fullName;
    }
    /**
     * getDecLName method comment.
     */
    public String getDecLName() {
        return lastName;
    }
    /**
     * getDecMaiden method comment.
     */
    public String getDecMaiden() {
        return maidenName;
    }
    /**
     * getDecMarried method comment.
     */
    public String getDecMarried() {
        return maritalStatus;
    }
    /**
     * getDecMName method comment.
     */
    public String getDecMName() {
        return middleName;
    }
    /**
     * getDecmrmrs method comment.
     */
    public String getDecmrmrs() {
    	logger.debug("GETDECMRMRS REACHED!: " + mrmrs);
        return mrmrs;
    }
    /**
     * getDecResCityTWP method comment.
     */
    public String getDecResCityTWP() {
        return cityTwpName;
    }
    /**
     * getDecResCityTWPBox method comment.
     */
    public String getDecResCityTWPBox() {
        return cityTwpBox;
    }
    /**
     * getDecResCounty method comment.
     */
    public String getDecResCounty() {
        return county;
    }
    /**
     * getDecResInsideCity method comment.
     */
    public String getDecResInsideCity() {
        return insideCity;
    }
    /**
     * getDecResLengthOfTime method comment.
     */
    public String getDecResLengthOfTime() {
        return timeAtAddress;
    }
    /**
     * getDecResMailCity method comment.
     */
    public String getDecResMailCity() {
        return mailingCity;
    }
    /**
     * getDecResState method comment.
     */
    public String getDecResState() {
        return state;
    }
    /**
     * getDecResStreet method comment.
     */
    public String getDecResStreet() {
        return streetAddr;
    }
    /**
     * getDecResZip method comment.
     */
    public String getDecResZip() {
        return zip;
    }
    
    public String getDecResCountry() {
        return country;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:35:37 PM)
     * @return String
     */
    public String getElementaryEducation() {
        return elementaryEducation;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:31:50 PM)
     * @return String
     */
    public String getFatherBirthCity() {
        return fatherBirthCity;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:32:01 PM)
     * @return String
     */
    public String getFatherBirthState() {
        return fatherBirthState;
    }
    
    public String getFatherBirthCountry() {
        return fatherBirthCountry;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:29:16 PM)
     * @return String
     */
    public String getFatherFirstName() {
        return fatherFirstName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:35:59 PM)
     * @return String
     */
    public String getFatherFullName() {
        return fatherFullName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:29:44 PM)
     * @return String
     */
    public String getFatherLastName() {
        return fatherLastName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:29:33 PM)
     * @return String
     */
    public String getFatherMiddleName() {
        return fatherMiddleName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:35:11 PM)
     * @return String
     */
    public String getHispanicYN() {
        return hispanicYN;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:05 PM)
     * @return String
     */
    public String getKindBusiness() {
        return kindBusiness;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/22/2003 3:38:40 PM)
     * @return String
     */
    public String getMilitaryOrganizatn() {
        return militaryOrganizatn;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:31:08 PM)
     * @return String
     */
    public String getMotherBirthCity() {
        return motherBirthCity;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:31:36 PM)
     * @return String
     */
    public String getMotherBirthState() {
        return motherBirthState;
    }
    
    public String getMotherBirthCountry() {
        return motherBirthCountry;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:30:14 PM)
     * @return String
     */
    public String getMotherFirstName() {
        return motherFirstName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:29:59 PM)
     * @return String
     */
    public String getMotherFullName() {
        return motherFullName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:30:37 PM)
     * @return String
     */
    public String getMotherLastName() {
        return motherLastName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:30:27 PM)
     * @return String
     */
    public String getMotherMiddleName() {
        return motherMiddleName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:32:52 PM)
     * @return String
     */
    public String getOccupation() {
        return occupation;
    }
    /**
     * getPersistentPeer method comment.
     */
    protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
        return peer;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:34:04 PM)
     * @return String
     */
    public String getRace() {
        return race;
    }
    /**
     * getSalutation method comment.
     */
    public String getSalutation() {
        return mrmrs;
    }
    /**
     * getSex method comment.
     */
    public String getSex() {
        return sex;
    }
    /**
     * getSSNo method comment.
     */
    public String getSSNo() {
        return ssno;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:02:14 AM)
     * @return String
     */
    public String getSuffix() {
        return suffix;
    }
    public String getDeceasedPrefix() {
        return DeceasedPrefix;
    }
    /**
     * getTimeOfDeath method comment.
     */
    public String getTimeOfDeath() {
        return timeOfDeath;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:35 PM)
     * @return String
     */
    public String getVeteranYN() {
        return veteranYN;
    }
    
    public String getWasPeaceOfficerYN() {
        return wasPeaceOfficerYN;
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (1/22/2003 3:38:59 PM)
     * @return String
     */
    public String getWarFromDate() {
        return warFromDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/22/2003 3:39:16 PM)
     * @return String
     */
    public String getWarToDate() {
        return warToDate;
    }
    
    
    
    public String getLocalityUnincorporated() {
        return LocalityUnincorporated;
    }
    public String getDecEducation() {
        return DecEducation;
    }
    public String getLoctnOfDeathZip() {
        return LoctnOfDeathZip;
    }
    
    public String getTribalReservation() {
        return TribalReservation;
    }
    
    public String getDecSpecifyHispanic() {
        return DecSpecifyHispanic;
    }
    public String getCremationAuthorizer() {
        return cremationAuthorizer;
    }
    
    public String getDecResPhone() {
    	return phone;
    }
    
    public String getDeceasedSame () {
    	return deceasedSame;
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
        setId(  Integer.parseInt(data.get(DbVitalsDeceasedPeer.CASE_ID).toString()));
        birthDate     = data.get(DbVitalsDeceasedPeer.DATEOFBIRTH).toString();
        burialDate      = data.get(DbVitalsDeceasedPeer.DATEOFBURIAL).toString();
    	burialPermitNumber	= data.get(DbVitalsDeceasedPeer.BURIALPERMITNUMBER).toString();
        deathDate     = data.get(DbVitalsDeceasedPeer.DATEOFDEATH).toString();
        firstName     = data.get(DbVitalsDeceasedPeer.FIRSTNAME).toString();
        fullName      = data.get(DbVitalsDeceasedPeer.FULLNAME).toString();
        lastName      = data.get(DbVitalsDeceasedPeer.LASTNAME).toString();
        maidenName      = data.get(DbVitalsDeceasedPeer.MAIDENNAME).toString();
        maritalStatus   = data.get(DbVitalsDeceasedPeer.MARITALSTATUS).toString();
        middleName      = data.get(DbVitalsDeceasedPeer.MIDNAME).toString();
        mrmrs       = data.get(DbVitalsDeceasedPeer.MRMRS).toString();
        cityTwpName     = data.get(DbVitalsDeceasedPeer.RESCITYTWP).toString();
        cityTwpBox      = data.get(DbVitalsDeceasedPeer.RESCITYTWPBOX).toString();
        county        = data.get(DbVitalsDeceasedPeer.RESCOUNTY).toString();
        insideCity      = data.get(DbVitalsDeceasedPeer.RESINSIDECITY).toString();
        timeAtAddress   = data.get(DbVitalsDeceasedPeer.RESLENGTHOFTIME).toString();
        mailingCity     = data.get(DbVitalsDeceasedPeer.RESMAILCITY).toString();
        state       = data.get(DbVitalsDeceasedPeer.RESSTATE).toString();
        streetAddr      = data.get(DbVitalsDeceasedPeer.RESSTREET).toString();
        zip         = data.get(DbVitalsDeceasedPeer.RESZIP).toString();
        country = data.get(DbVitalsDeceasedPeer.RESCOUNTRY).toString();        
        sex         = data.get(DbVitalsDeceasedPeer.SEX).toString();
        ssno        = data.get(DbVitalsDeceasedPeer.SSNO).toString();
        stateOfDeath    = data.get(DbVitalsDeceasedPeer.STATEDEATH).toString();
        timeOfDeath     = data.get(DbVitalsDeceasedPeer.TIMEDEATH).toString();
        timeOfDeathStatus     = data.get(DbVitalsDeceasedPeer.TIMEDEATHSTATUS).toString();
        occupation      = data.get(DbVitalsDeceasedPeer.OCCUPATION).toString();
        kindBusiness    = data.get(DbVitalsDeceasedPeer.KINDBUSINESS).toString();
        yearsAtOccupation = FormatNumber.parseInteger(data.get(DbVitalsDeceasedPeer.YEARSATOCCUPATION).toString());
        employer		= data.get(DbVitalsDeceasedPeer.EMPLOYER).toString();
        birthPlaceCSV   = data.get(DbVitalsDeceasedPeer.BIRTHPLACECSV).toString();
        birthPlace		= data.get(DbVitalsDeceasedPeer.BIRTHPLACE).toString();
        veteranYN     = data.get(DbVitalsDeceasedPeer.VETERAN).toString();
        wasPeaceOfficerYN = data.get(DbVitalsDeceasedPeer.PEACEOFFICER).toString();
        ancestry      = data.get(DbVitalsDeceasedPeer.ANCESTRY).toString();
        race        = data.get(DbVitalsDeceasedPeer.RACE).toString();
        tribalMember        = data.get(DbVitalsDeceasedPeer.TRIBALMEMBER).toString();
        tribalName        = data.get(DbVitalsDeceasedPeer.TRIBALNAME).toString();     
        hispanicYN      = data.get(DbVitalsDeceasedPeer.HISPANIC).toString();
        elementaryEducation = data.get(DbVitalsDeceasedPeer.EDUCELEM).toString();
        collegeEducation  = data.get(DbVitalsDeceasedPeer.EDUCCOLL).toString();
        fatherFullName    = data.get(DbVitalsDeceasedPeer.FATHERFULLNAME).toString();
        motherFullName    = data.get(DbVitalsDeceasedPeer.MOTHERFULLNAME).toString();
        fatherFirstName   = data.get(DbVitalsDeceasedPeer.FATHERFIRST).toString();
        fatherMiddleName  = data.get(DbVitalsDeceasedPeer.FATHERMIDDLE).toString();
        fatherLastName    = data.get(DbVitalsDeceasedPeer.FATHERLAST).toString();
        motherFirstName   = data.get(DbVitalsDeceasedPeer.MOTHERFIRST).toString();
        motherMiddleName  = data.get(DbVitalsDeceasedPeer.MOTHERMIDDLE).toString();
        motherLastName    = data.get(DbVitalsDeceasedPeer.MOTHERLAST).toString();
        motherBirthCity   = data.get(DbVitalsDeceasedPeer.MOTHERBIRTHCITY).toString();
        motherBirthState  = data.get(DbVitalsDeceasedPeer.MOTHERBIRTHSTATE).toString();
        motherBirthCountry = data.get(DbVitalsDeceasedPeer.MOTHERBIRTHCOUNTRY).toString();
        fatherBirthCity   = data.get(DbVitalsDeceasedPeer.FATHERBIRTHCITY).toString();
        fatherBirthState  = data.get(DbVitalsDeceasedPeer.FATHERBIRTHSTATE).toString();
        fatherBirthCountry = data.get(DbVitalsDeceasedPeer.FATHERBIRTHCOUNTRY).toString();
        fatherBirthday = data.get(DbVitalsDeceasedPeer.FATHERBIRTHDAY).toString();
        motherBirthday = data.get(DbVitalsDeceasedPeer.MOTHERBIRTHDAY).toString();
        motherStreetAddress = data.get(DbVitalsDeceasedPeer.MOTHERSTREETADDRESS).toString();
        
        //DeceasedPrefix    = data.get(DbVitalsDeceasedPeer.DECEASEDPREFIX).toString();
        suffix        = data.get(DbVitalsDeceasedPeer.SUFFIX).toString();
        aliasFirst      = data.get(DbVitalsDeceasedPeer.ALIASFIRST).toString();
        aliasMiddle     = data.get(DbVitalsDeceasedPeer.ALIASMIDDLE).toString();
        aliasLast     = data.get(DbVitalsDeceasedPeer.ALIASLAST).toString();
        aliasPrefix     = data.get(DbVitalsDeceasedPeer.ALIASPREFIX).toString();
        aliasSuffix     = data.get(DbVitalsDeceasedPeer.ALIASSUFFIX).toString();
        crematoryName   = data.get(DbVitalsDeceasedPeer.CREMATORYNAME).toString();
        militaryOrganizatn  = data.get(DbVitalsDeceasedPeer.MILITARYORG).toString();
        warFromDate     = data.get(DbVitalsDeceasedPeer.WARFROM).toString();
        warToDate     = data.get(DbVitalsDeceasedPeer.WARTO).toString();
        decAptNo      = data.get(DbVitalsDeceasedPeer.DECAPTNO).toString();
        citizenship     = data.get(DbVitalsDeceasedPeer.CITIZENSHIP).toString();
        locOfDeathCVT = data.get(DbVitalsDeceasedPeer.LOCOFDEATHCVT).toString();
        LocalityUnincorporated  = data.get(DbVitalsDeceasedPeer.LOCALITYUNINCORPORATED).toString();
        DecEducation      = data.get(DbVitalsDeceasedPeer.DECEDUCATION).toString();
        LoctnOfDeathZip     = data.get(DbVitalsDeceasedPeer.LOCTNOFDEATHZIP).toString();
        TribalReservation   = data.get(DbVitalsDeceasedPeer.TRIBALRESERVATION).toString();
        aboriginal			= data.get(DbVitalsDeceasedPeer.ABORIGINAL).toString();
        livedOnReserve		= data.get(DbVitalsDeceasedPeer.LIVEDONRESERVE).toString();
        DecSpecifyHispanic    = data.get(DbVitalsDeceasedPeer.DECSPECIFYHISPANIC).toString();
        cremationAuthorizer   = data.get(DbVitalsDeceasedPeer.CREMATIONAUTHORIZER).toString();
        phone = data.get(DbVitalsDeceasedPeer.PHONE).toString();
        
        customerName = data.get(DbVitalsDeceasedPeer.CUSTOMERNAME).toString();
        
        fatherForename = data.get(DbVitalsDeceasedPeer.FATHERFORENAME).toString();
        fatherSurnameBirth = data.get(DbVitalsDeceasedPeer.FATHERSURNAMEBIRTH).toString();
        fatherOtherSurname = data.get(DbVitalsDeceasedPeer.FATHEROTHERSURNAME).toString();
        fatherOccupation = data.get(DbVitalsDeceasedPeer.FATHEROCCUPATION).toString();
        motherForename = data.get(DbVitalsDeceasedPeer.MOTHERFORENAME).toString();
        motherSurnameBirth = data.get(DbVitalsDeceasedPeer.MOTHERSURNAMEBIRTH).toString();
        motherOtherSurname = data.get(DbVitalsDeceasedPeer.MOTHEROTHERSURNAME).toString();
        motherOccupation = data.get(DbVitalsDeceasedPeer.MOTHEROCCUPATION).toString();
        stillBirthDesc = data.get(DbVitalsDeceasedPeer.STILLBIRTHDESC).toString();
        pregnancyDuration = FormatNumber.parseInteger(data.get(DbVitalsDeceasedPeer.PREGNANCYDURATION).toString());
        numberChildren = FormatNumber.parseInteger(data.get(DbVitalsDeceasedPeer.NUMBERCHILDREN).toString());
        numberLiveborn = FormatNumber.parseInteger(data.get(DbVitalsDeceasedPeer.NUMBERLIVEBORN).toString());
        numberStillborn = FormatNumber.parseInteger(data.get(DbVitalsDeceasedPeer.NUMBERSTILLBORN).toString());
        childWeight = data.get(DbVitalsDeceasedPeer.CHILDWEIGHT).toString();
        birthType = data.get(DbVitalsDeceasedPeer.BIRTHTYPE).toString();
        birthOrder = data.get(DbVitalsDeceasedPeer.BIRTHORDER).toString();
        childNameAgreed = data.get(DbVitalsDeceasedPeer.CHILDNAMEAGREED).toString();
        deceasedSame = data.get(DbVitalsDeceasedPeer.ADDRSAMEASINF).toString();
        countyOfBirth = data.get(DbVitalsDeceasedPeer.COUNTYOFBIRTH).toString();
        ServiceDateKey  = data.get(DbVitalsDeceasedPeer.SERVICEDATEKEY).toString();
   
        countyOfIssue = data.get(DbVitalsDeceasedPeer.COUNTYOFISSUE).toString();
        issueDate  = data.get(DbVitalsDeceasedPeer.ISSUEDATE).toString();  
        
        motherMaidenName = data.get(DbVitalsDeceasedPeer.MOTHERMAIDENNAME).toString();
        fatherMaidenName = data.get(DbVitalsDeceasedPeer.FATHERMAIDENNAME).toString();
        
        // Initializ Mr/Mrs if not present
        
        // Code commented by Bhavesh for Ticket #5516	Decease Salutation (Honorific)

       /* if (mrmrs.compareTo(" ")<=0){
            if (sex.compareToIgnoreCase("M")==0)
                mrmrs = "Mr.";
            if (sex.compareToIgnoreCase("F")==0) {
                if (maritalStatus.compareToIgnoreCase("Married")==0){
                    mrmrs = "Mrs.";
                } else {
                    mrmrs = "Ms.";
                }
            }
        }*/ 
        
        fatherAge = FormatNumber.parseInteger(data.get(DbVitalsDeceasedPeer.FATHERAGE).toString());
        motherAge = FormatNumber.parseInteger(data.get(DbVitalsDeceasedPeer.MOTHERAGE).toString());
        identificationMarks = data.get(DbVitalsDeceasedPeer.IDENTIFICATIONMARKS).toString();
        
        transferredLocationName = data.get(DbVitalsDeceasedPeer.TRANSFERREDLOCATIONNAME).toString();
        transferredLocationAddr = data.get(DbVitalsDeceasedPeer.TRANSFERREDLOCATIONADDR).toString();
        transferredLocationCity = data.get(DbVitalsDeceasedPeer.TRANSFERREDLOCATIONCITY).toString();
        transferredLocationState = data.get(DbVitalsDeceasedPeer.TRANSFERREDLOCATIONSTATE).toString();
        transferredLocationZip = data.get(DbVitalsDeceasedPeer.TRANSFERREDLOCATIONZIP).toString();
        transferredLocationPhone = data.get(DbVitalsDeceasedPeer.TRANSFERREDLOCATIONPHONE).toString();
        
        hospitalNameOfBirthPlaceOfDeceasedUnder1YearOld = data.get(DbVitalsDeceasedPeer.HOSPITALNAMEOFBIRTHPLACEOFDECEASEDUNDER1YEAROLD).toString();
        employerAddr = data.get(DbVitalsDeceasedPeer.EMPLOYERADDR).toString();
        residentWithinCityVillageLimit = data.get(DbVitalsDeceasedPeer.RESIDENTWITHINCITYVILLAGELIMIT).toString();
        residentLocalityLimitName = data.get(DbVitalsDeceasedPeer.RESIDENTLOCALITYLIMITNAME).toString();
        
        hospitalizedLast2Months= data.get(DbVitalsDeceasedPeer.HOSPITALIZEDLAST2MONTHS).toString();
        pregnantDeliveryDate= data.get(DbVitalsDeceasedPeer.PREGNANTDELIVERYDATE).toString();
      
        
    }
    public void setCustomerName(String newCustomerName) {
        customerName = newCustomerName;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:02:27 AM)
     * @param newAliasFirst String
     */
    public void setAliasFirst(String newAliasFirst) {
        aliasFirst = newAliasFirst;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:02:52 AM)
     * @param newAliasLast String
     */
    public void setAliasLast(String newAliasLast) {
        aliasLast = newAliasLast;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:02:39 AM)
     * @param newAliasMiddle String
     */
    public void setAliasMiddle(String newAliasMiddle) {
        aliasMiddle = newAliasMiddle;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:03:06 AM)
     * @param newAliasPrefix String
     */
    public void setAliasPrefix(String newAliasPrefix) {
        aliasPrefix = newAliasPrefix;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:03:16 AM)
     * @param newAliasSuffix String
     */
    public void setAliasSuffix(String newAliasSuffix) {
        aliasSuffix = newAliasSuffix;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:45 PM)
     * @param newAncestry String
     */
    public void setAncestry(String newAncestry) {
        modify();
        ancestry = newAncestry;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:19 PM)
     * @param newBirthPlace String
     */
    public void setBirthPlace(String newBirthPlace) {
        birthPlace = newBirthPlace;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:19 PM)
     * @param newBirthPlace String
     */
    public void setBirthPlaceCSV(String newBirthPlace) {
        birthPlaceCSV = newBirthPlace;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/11/2003 10:28:36 AM)
     * @param newCitizenship String
     */
    public void setCitizenship(String newCitizenship) {
        citizenship = newCitizenship;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:35:48 PM)
     * @param newCollegeEducation String
     */
    public void setCollegeEducation(String newCollegeEducation) {
        collegeEducation = newCollegeEducation;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:03:41 AM)
     * @param newCrematoryName String
     */
    public void setCrematoryName(String newCrematoryName) {
        crematoryName = newCrematoryName;
        modify();
    }
    /**
     * setDateOfBirth method comment.
     */
    public void setDateOfBirth(String aBirthDate) {
        birthDate = aBirthDate;
        modify();
    }
    /**
     * setDateOfBurial method comment.
     */
    public void setDateOfBurial(String aBurialDate) {
        burialDate = aBurialDate;
        modify();
    }
    /**
     * setDateOfDeath method comment.
     */
    public void setDateOfDeath(String aDeathDate) {
        deathDate = aDeathDate;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/4/2003 2:54:42 PM)
     * @param newDecAptNo String
     */
    public void setDecAptNo(String newDecAptNo) {
        decAptNo = newDecAptNo;
    }
    /**
     * setDecFName method comment.
     */
    public void setDecFName(String fname) {
        firstName = fname;
        modify();
    }
    /**
     * setDecFullName method comment.
     */
    public void setDecFullName(String aName) {
        fullName = aName;
        modify();
    }
    /**
     * setDecLName method comment.
     */
    public void setDecLName(String lname) {
        lastName = lname;
        modify();
    }
    /**
     * setDecMaiden method comment.
     */
    public void setDecMaiden(String aName) {
        maidenName = aName;
        modify();
    }
    /**
     * setDecMarried method comment.
     */
    public void setDecMarried(String status) {
        maritalStatus = status;
        modify();
    }
    /**
     * setDecMName method comment.
     */
    public void setDecMName(String mname) {
        middleName = mname;
        modify();
    }
    /**
     * setDecmrmrs method comment.
     */
    public void setDecmrmrs(String title) {
        mrmrs = title;
    	logger.debug("SETDECMRMRS REACHED!: " + title);
        modify();
    }
    /**
     * setDecResCityTWP method comment.
     */
    public void setDecResCityTWP(String city) {
        cityTwpName = city;
        modify();
    }
    /**
     * setDecResCityTWPBox method comment.
     */
    public void setDecResCityTWPBox(String box) {
        if (box != null && box.length()>0){
            cityTwpBox = box;
            modify();
        }
    }
    /**
     * setDecResCounty method comment.
     */
    public void setDecResCounty(String aCounty) {
        county = aCounty;
        modify();
    }
    /**
     * setDecResInsideCity method comment.
     */
    public void setDecResInsideCity(String inside) {
        if (inside != null && inside.length()>0){
            insideCity  = inside.substring(0,1);
            modify();
        }
    }
    /**
     * setDecResLengthOfTime method comment.
     */
    public void setDecResLengthOfTime(String time) {
        timeAtAddress = time;
        modify();
    }
    /**
     * setDecResMailCity method comment.
     */
    public void setDecResMailCity(String cityname) {
        mailingCity = cityname;
        modify();
    }
    /**
     * setDecResState method comment.
     */
    public void setDecResState(String aState) {
        state = aState;
        modify();
    }
    /**
     * setDecResStreet method comment.
     */
    public void setDecResStreet(String astreet) {
        streetAddr = astreet;
        modify();
    }
    /**
     * setDecResZip method comment.
     */
    public void setDecResZip(String azip) {
        zip = azip;
        modify();
    }
    
    public void setDecResCountry(String country) {
        this.country = country;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:35:37 PM)
     * @param newElementaryEducation String
     */
    public void setElementaryEducation(String newElementaryEducation) {
        modify();
        elementaryEducation = newElementaryEducation;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:31:50 PM)
     * @param newFatherBirthCity String
     */
    public void setFatherBirthCity(String newFatherBirthCity) {
        modify();
        fatherBirthCity = newFatherBirthCity;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:32:01 PM)
     * @param newFatherBirthState String
     */
    public void setFatherBirthState(String newFatherBirthState) {
        modify();
        fatherBirthState = newFatherBirthState;
    }
    
    public void setFatherBirthCountry(String fatherBirthCountry) {
        modify();
        this.fatherBirthCountry = fatherBirthCountry;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:29:16 PM)
     * @param newFatherFirstName String
     */
    public void setFatherFirstName(String newFatherFirstName) {
        modify();
        fatherFirstName = newFatherFirstName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:35:59 PM)
     * @param newFatherFullName String
     */
    public void setFatherFullName(String newFatherFullName) {
        modify();
        fatherFullName = newFatherFullName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:29:44 PM)
     * @param newFatherLastName String
     */
    public void setFatherLastName(String newFatherLastName) {
        modify();
        fatherLastName = newFatherLastName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:29:33 PM)
     * @param newFatherMiddleName String
     */
    public void setFatherMiddleName(String newFatherMiddleName) {
        modify();
        fatherMiddleName = newFatherMiddleName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:35:11 PM)
     * @param newHispanicYN String
     */
    public void setHispanicYN(String newHispanicYN) {
        if (newHispanicYN != null && newHispanicYN.length()>0){
            modify();
            hispanicYN = newHispanicYN.substring(0,1);
        }
    }
    /**
     * Get the ID key for this object from the hashtable.
     * DbUser objects can be accessed by "Name"
     * So, first see if "Name" is being used for restoring
     * or if ID# is being used.
     */
    public void setId(java.util.Hashtable h) {
        //if (h.containsKey(DbVitalsDeceasedPeer.NAME)){
        //  getPersistentPeer().restore(this, t);
        //}
        setId(((Integer)h.get(DbVitalsDeceasedPeer.CASE_ID)).intValue());
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:05 PM)
     * @param newKindBusiness String
     */
    public void setKindBusiness(String newKindBusiness) {
        modify();
        kindBusiness = newKindBusiness;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/22/2003 3:38:40 PM)
     * @param newMilitaryOrganizatn String
     */
    public void setMilitaryOrganizatn(String newMilitaryOrganizatn) {
        militaryOrganizatn = newMilitaryOrganizatn;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:31:08 PM)
     * @param newMotherBirthCity String
     */
    public void setMotherBirthCity(String newMotherBirthCity) {
        modify();
        motherBirthCity = newMotherBirthCity;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:31:36 PM)
     * @param newMotherBirthState String
     */
    public void setMotherBirthState(String newMotherBirthState) {
        modify();
        motherBirthState = newMotherBirthState;
    }
    
    public void setMotherBirthCountry(String motherBirthCountry) {
        this.motherBirthCountry = motherBirthCountry;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:30:14 PM)
     * @param newMotherFirstName String
     */
    public void setMotherFirstName(String newMotherFirstName) {
        modify();
        motherFirstName = newMotherFirstName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:29:59 PM)
     * @param newMotherFullName String
     */
    public void setMotherFullName(String newMotherFullName) {
        modify();
        motherFullName = newMotherFullName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:30:37 PM)
     * @param newMotherLastName String
     */
    public void setMotherLastName(String newMotherLastName) {
        modify();
        motherLastName = newMotherLastName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 3:30:27 PM)
     * @param newMotherMiddleName String
     */
    public void setMotherMiddleName(String newMotherMiddleName) {
        modify();
        motherMiddleName = newMotherMiddleName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:32:52 PM)
     * @param newOccupation String
     */
    public void setOccupation(String newOccupation) {
        modify();
        occupation = newOccupation;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:34:04 PM)
     * @param newRace String
     */
    public void setRace(String newRace) {
        modify();
        race = newRace;
    }
    /**
     * setSalutation method comment.
     */
    public void setSalutation(String sal) {
        mrmrs = sal;
        modify();
    }
    /**
     * setSex method comment.
     */
    public void setSex(String aSex) {
        if (aSex!=null && aSex.length()>0){
            sex = aSex.substring(0,1);
            modify();
        }
    }
    /**
     * setSSNo method comment.
     */
    public void setSSNo(String aSsn) {
        ssno = aSsn;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/16/2003 11:02:14 AM)
     * @param newSuffix String
     */
    public void setSuffix(String newSuffix) {
        suffix = newSuffix;
    }
    public void setDeceasedPrefix(String newDeceasedPrefix) {
        DeceasedPrefix = newDeceasedPrefix;
    }
    /**
     * setTimeOfDeath method comment.
     */
    public void setTimeOfDeath(String time) {
        timeOfDeath = time;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/13/2002 2:33:35 PM)
     * @param newVeteranYN String
     */
    public void setVeteranYN(String newVeteranYN) {
        if (newVeteranYN != null && newVeteranYN.length()>0){
            modify();
            veteranYN = newVeteranYN.substring(0,1);
        }
    }
    
    public void setWasPeaceOfficerYN(String newPeaceOfficerYN) {
        if (newPeaceOfficerYN != null && newPeaceOfficerYN.length()>0){
            modify();
            wasPeaceOfficerYN = newPeaceOfficerYN.substring(0,1);
        }
    }
    
    
    /**
     * Insert the method's description here.
     * Creation date: (1/22/2003 3:38:59 PM)
     * @param newWarFromDate String
     */
    public void setWarFromDate(String newWarFromDate) {
        warFromDate = newWarFromDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/22/2003 3:39:16 PM)
     * @param newWarToDate String
     */
    public void setWarToDate(String newWarToDate) {
        warToDate = newWarToDate;
    }
    
    
    
    public void setLocalityUnincorporated(String newLocalityUnincorporated) {
        LocalityUnincorporated = newLocalityUnincorporated;
    }
    public void setDecEducation(String newDecEducation) {
        DecEducation = newDecEducation;
    }
    public void setLoctnOfDeathZip(String newLoctnOfDeathZip) {
        LoctnOfDeathZip = newLoctnOfDeathZip;
    }
    public void setTribalReservation(String newTribalReservation) {
        TribalReservation = newTribalReservation;
    }
    public void setDecSpecifyHispanic(String newDecSpecifyHispanic) {
        DecSpecifyHispanic = newDecSpecifyHispanic;
    }
    public void setCremationAuthorizer(String input) {
        cremationAuthorizer = input;
    }
    
    public void setDecResPhone(String phone) {
    	this.phone = phone;
    }

	/**
	 * @return Sets whether or not the deceased's address is the same as the informant's.
	 */
    public void setDeceasedSame(String in) {
    	deceasedSame = in;
    }
	/**
	 * @return Returns the fatherBirthday.
	 */
	public String getFatherBirthday() {
		return fatherBirthday;
	}
	/**
	 * @param fatherBirthday The fatherBirthday to set.
	 */
	public void setFatherBirthday(String fatherBirthday) {
		this.fatherBirthday = fatherBirthday;
	}
	/**
	 * @return Returns the motherBirthday.
	 */
	public String getMotherBirthday() {
		return motherBirthday;
	}
	/**
	 * @param motherBirthday The motherBirthday to set.
	 */
	public void setMotherBirthday(String motherBirthday) {
		this.motherBirthday = motherBirthday;
	}
	/**
	 * @return Returns the motherStreetAddress.
	 */
	public String getMotherStreetAddress() {
		return motherStreetAddress;
	}
	/**
	 * @param motherStreetAddress The motherStreetAddress to set.
	 */
	public void setMotherStreetAddress(String motherStreetAddress) {
		this.motherStreetAddress = motherStreetAddress;
	}
	public int getFatherAge() {
		return fatherAge;
	}
	public void setFatherAge(int fatherAge) {
		this.fatherAge = fatherAge;
	}
	public int getMotherAge() {
		return motherAge;
	}
	public void setMotherAge(int motherAge) {
		this.motherAge = motherAge;
	}
	
	public String getAboriginal() {
		return aboriginal;
	}
	public void setAboriginal(String aboriginal) {
		this.aboriginal = aboriginal;
	}
	public String getLivedOnReserve() {
		return livedOnReserve;
	}
	public void setLivedOnReserve(String livedOnReserve) {
		this.livedOnReserve = livedOnReserve;
	}
	
	public void setEmployer(String in) {
		employer = in;
	}
	
	public String getEmployer() {
		return employer;
	}
	/**
	 * @return the serviceDateKey
	 */
	public String getServiceDateKey() {
		return ServiceDateKey;
	}
	/**
	 * @param serviceDateKey the serviceDateKey to set
	 */
	public void setServiceDateKey(String serviceDateKey) {
		ServiceDateKey = serviceDateKey;
	}
	
	public String getCountyOfBirth(){
		return countyOfBirth;
	}
	
	public void setCountyOfBirth(String countyOfBirth) {
		this.countyOfBirth = countyOfBirth;
	}
	/**
	 * @return the identificationMarks
	 */
	public String getIdentificationMarks() {
		return identificationMarks;
	}
	/**
	 * @param identificationMarks the identificationMarks to set
	 */
	public void setIdentificationMarks(String identificationMarks) {
		this.identificationMarks = identificationMarks;
	}
	public String getLocOfDeathCVT() {
		return locOfDeathCVT;
	}
	public void setLocOfDeathCVT(String locOfDeathCVT) {
		this.locOfDeathCVT = locOfDeathCVT;
	}
	
}
