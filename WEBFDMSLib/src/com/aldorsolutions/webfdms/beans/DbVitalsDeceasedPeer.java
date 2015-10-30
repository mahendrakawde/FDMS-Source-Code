package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * This class supplies constants and SQL for DbVitalsDeceased.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author:
 */
public class DbVitalsDeceasedPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

  static public final String CASE_ID      = "VitalsMasterKey";
  static public final String DATEOFBIRTH  = "DateOfBirth";
  static public final String DATEOFBURIAL = "BurialDate";
  static public final String SERVICEDATEKEY = "ServiceDateKey";
  static public final String BURIALPERMITNUMBER = "BurialPermitNumber";
  static public final String DATEOFDEATH  = "DateOfDeath";
  static public final String FIRSTNAME  = "DeceasedFirstName";
  static public final String MIDNAME    = "DeceasedMidName";
  static public final String LASTNAME   = "DeceasedLastName";
  static public final String FULLNAME   = "DeceasedFullName";
  static public final String MAIDENNAME = "DeceasedMaidenName";
  static public final String MARITALSTATUS    = "DecMarried";
  static public final String MRMRS      = "DecMrMrs";
  static public final String ADDRSAMEASINF	= "DecAddrSameAsInf";
  static public final String RESCITYTWP   = "DecCityTwpName";
  static public final String RESCITYTWPBOX  = "DecCityTwpBox";
  static public final String RESCOUNTY    = "DecResCounty";
  static public final String RESINSIDECITY  = "DecResInsideCity";
//  static public final String STREETADDR       = "DecResStreet";
  static public final String RESLENGTHOFTIME  = "TimeAtResidence";
  static public final String RESMAILCITY    = "DecResMailCity";
  static public final String RESSTATE     = "DecResState";
  static public final String RESSTREET    = "DecResStreet";
  static public final String RESZIP     = "DecResZip";
  static public final String RESCOUNTRY = "DecResCountry";
  static public final String SEX        = "DeceasedSex";
  static public final String SSNO       = "SocialSecurityNo";
  static public final String STATEDEATH   = "StateOfDeath";
  static public final String TIMEDEATH    = "TimeOfDeath";
  static public final String TIMEDEATHSTATUS    = "TimeOfDeathStatus";
  static public final String OCCUPATION = "Occupation";
  static public final String KINDBUSINESS = "KindOfBusiness";
  static public final String YEARSATOCCUPATION = "YearsAtOccupation";
  static public final String EMPLOYER = "Employer";
  static public final String BIRTHPLACE = "DecBirthPlace";
  static public final String BIRTHPLACECSV = "DecBirthPlaceCSV";
  static public final String VETERAN    = "InArmedForces";
  static public final String PEACEOFFICER    = "WasPeaceOfficer";
  static public final String MILITARYORG  = "MilitaryOrganizatn";
  static public final String WARFROM    = "WarFromDate";
  static public final String WARTO    = "WarToDate";
  static public final String ANCESTRY   = "Ancestry";
  static public final String RACE     = "Race";
  static public final String HISPANIC   = "HispanicOriginBox";
  static public final String EDUCELEM   = "ElementaryEduc";
  static public final String EDUCCOLL   = "CollegeEducation";
  static public final String FATHERFULLNAME = "FathersName";
  static public final String MOTHERFULLNAME = "MothersName";
  static public final String FATHERFIRST    = "FatherFirstName";
  static public final String FATHERMIDDLE   = "FatherMiddleName";
  static public final String FATHERLAST   = "FatherLastName";
  static public final String MOTHERFIRST    = "MotherFirstName";
  static public final String MOTHERMIDDLE   = "MotherMiddleName";
  static public final String MOTHERLAST   = "MotherLastName";
  static public final String MOTHERBIRTHCITY  = "MotherBirthCity";
  static public final String FATHERBIRTHCITY  = "FatherBirthCity";
  static public final String MOTHERBIRTHSTATE = "MotherBirthState";
  static public final String MOTHERBIRTHCOUNTRY = "MotherBirthCountry";
  static public final String FATHERBIRTHSTATE = "FatherBirthState";
  static public final String FATHERBIRTHCOUNTRY = "FatherBirthCountry";
  static public final String DECEASEDPREFIX = "DeceasedPrefix";
  static public final String SUFFIX     = "DeceasedSuffix";
  static public final String ALIASFIRST   = "DecAliasFirst";
  static public final String ALIASMIDDLE    = "DecAliasMiddle";
  static public final String ALIASLAST    = "DecAliasLast";
  static public final String ALIASPREFIX    = "DecAliasPrefix";
  static public final String ALIASSUFFIX    = "DecAliasSuffix";
  static public final String CREMATORYNAME  = "crematoryName";
  static public final String DECAPTNO     = "DecAptNo";
  static public final String CITIZENSHIP    = "Citizenship";
  static public final String LOCALITYUNINCORPORATED   = "LocalityUnincorporated";
  static public final String DECEDUCATION   = "DecEducation";
  static public final String LOCTNOFDEATHZIP  = "LoctnOfDeathZip";
  static public final String TRIBALRESERVATION    = "TribalReservation";
  static public final String ABORIGINAL			= "Aboriginal";
  static public final String LIVEDONRESERVE		= "LivedOnReserve";
  static public final String DECSPECIFYHISPANIC   = "DecSpecifyHispanic";
  static public final String CREMATIONAUTHORIZER    = "CremationAuthorizer";
  static public final String CUSTOMERNAME = "CustomerName";
  static public final String PHONE = "Phone";
  static public final String FATHERFORENAME = "FatherForename";
  static public final String FATHERSURNAMEBIRTH = "FatherSurnameBirth";
  static public final String FATHEROTHERSURNAME = "FatherOtherSurname";
  static public final String FATHEROCCUPATION = "FatherOccupation";
  static public final String MOTHERFORENAME = "MotherForename";
  static public final String MOTHERSURNAMEBIRTH = "MotherSurnameBirth";
  static public final String MOTHEROTHERSURNAME = "MotherOtherSurname";
  static public final String MOTHEROCCUPATION = "MotherOccupation";
  static public final String STILLBIRTHDESC = "StillBirthDesc";
  static public final String PREGNANCYDURATION = "PregnancyDuration";
  static public final String NUMBERCHILDREN = "NumberChildren";
  static public final String NUMBERLIVEBORN = "NumberLiveborn";
  static public final String NUMBERSTILLBORN = "NumberStillborn";
  static public final String CHILDWEIGHT = "ChildWeight";
  static public final String BIRTHTYPE = "BirthType";
  static public final String BIRTHORDER = "BirthOrder";
  static public final String CHILDNAMEAGREED = "ChildNameAgreed";
  static public final String FATHERBIRTHDAY = "FatherBirthday";
  static public final String MOTHERBIRTHDAY = "MotherBirthday";
  static public final String MOTHERSTREETADDRESS = "MotherStreetAddress";
  static public final String FATHERAGE = "FatherAge";
  static public final String MOTHERAGE = "MotherAge";
  static public final String COUNTYOFBIRTH = "CountyOfBirth";
  static public final String COUNTYOFISSUE = "CountyOfIssue";
  static public final String ISSUEDATE = "IssueDate";
  static public final String MOTHERMAIDENNAME = "MotherMaidenName";
  static public final String FATHERMAIDENNAME = "FatherMaiDenName";
  static public final String IDENTIFICATIONMARKS = "IdentificationMarks";
  static public final String TRIBALMEMBER = "TribalMember";
  static public final String TRIBALNAME = "TribalName";
  static public final String LOCOFDEATHCVT = "LocOfDeathCVT";
  public static final String TRANSFERREDLOCATIONNAME = "TransferredLocationName";
  public static final String TRANSFERREDLOCATIONADDR = "TransferredLocationAddr";
  public static final String TRANSFERREDLOCATIONCITY = "TransferredLocationCity";
  public static final String TRANSFERREDLOCATIONSTATE = "TransferredLocationState";
  public static final String TRANSFERREDLOCATIONZIP = "TransferredLocationZip";
  public static final String TRANSFERREDLOCATIONPHONE = "TransferredLocationPhone";
  public static final String HOSPITALNAMEOFBIRTHPLACEOFDECEASEDUNDER1YEAROLD = "HospitalNameOfBirthPlaceOfDeceasedUnder1YearOld";
public static final String EMPLOYERADDR = "EmployerAddress";
public static final String RESIDENTWITHINCITYVILLAGELIMIT = "ResidentWithinCityVillageLimit";
public static final String RESIDENTLOCALITYLIMITNAME = "ResidentLocalityLimitName";
public static final String HOSPITALIZEDLAST2MONTHS = "hospitalizedLast2Months";
public static final String PREGNANTDELIVERYDATE = "pregnantDeliveryDate";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
    java.sql.Connection connection = null;
    java.sql.PreparedStatement pstmt=null;
    try {
      DbVitalsDeceased vitals=(DbVitalsDeceased)p;
      connection = t.getConnection();
      pstmt = connection.prepareStatement(
      "INSERT INTO vitalstats (" +
            "VitalsMasterKey,"+
            "DateOfBirth,"+
            "BurialDate,"+
            SERVICEDATEKEY+","+
            "DateOfDeath,"+
            "DeceasedFirstName,"+
            "DeceasedMidName,"+
            "DeceasedLastName,"+
            "DeceasedFullName,"+
            "DeceasedMaidenName,"+
            "DecMarried,"+
            "DecMrMrs,"+
            "DecCityTwpName,"+
            "DecCityTwpBox,"+
            "DecResCounty,"+
            "DecResInsideCity,"+
            "TimeAtResidence,"+
            "DecResMailCity,"+
            "DecResState,"+
            "DecResStreet,"+
            "DecResZip,"+            
            "DeceasedSex,"+
            "SocialSecurityNo,"+
            "StateOfDeath,"+
            "TimeOfdeath,"+
            OCCUPATION+","+
            KINDBUSINESS+","+
            BIRTHPLACE+","+
            BIRTHPLACECSV+","+
            VETERAN+","+
            ANCESTRY+","+
            RACE+","+
            HISPANIC+","+
            EDUCELEM+","+
            EDUCCOLL+","+
            FATHERFULLNAME+","+
            MOTHERFULLNAME+","+
            FATHERFIRST+","+
            FATHERMIDDLE+","+
            FATHERLAST+","+
            MOTHERFIRST+","+
            MOTHERMIDDLE+","+
            MOTHERLAST+","+
            MOTHERBIRTHCITY+","+
            MOTHERBIRTHSTATE+","+
            FATHERBIRTHCITY+","+
            FATHERBIRTHSTATE+","+
            SUFFIX+","+
            ALIASFIRST+","+
            ALIASMIDDLE+","+
            ALIASLAST+","+
            ALIASPREFIX+","+
            ALIASSUFFIX+","+
            CREMATORYNAME+","+
            MILITARYORG+","+
            WARFROM+","+
            WARTO+","+
            DECAPTNO+","+
            CITIZENSHIP+","+
            LOCALITYUNINCORPORATED+","+
            DECEDUCATION+","+
            LOCTNOFDEATHZIP+","+
            DECSPECIFYHISPANIC+","+
            TRIBALRESERVATION+","+
            CREMATIONAUTHORIZER+","+
            CUSTOMERNAME+", "+
            MOTHERBIRTHCOUNTRY+", "+
            FATHERBIRTHCOUNTRY+", "+
            RESCOUNTRY+", "+
			PHONE+", "+
			FATHERFORENAME+", "+
			FATHERSURNAMEBIRTH+", "+
			FATHEROTHERSURNAME+", "+
			FATHEROCCUPATION+", "+
			MOTHERFORENAME+", "+
			MOTHERSURNAMEBIRTH+", "+
			MOTHEROTHERSURNAME+", "+
			MOTHEROCCUPATION+", "+
			STILLBIRTHDESC+", "+
			PREGNANCYDURATION+", "+
			NUMBERCHILDREN+", "+
			NUMBERLIVEBORN+", "+
			NUMBERSTILLBORN+", "+
			CHILDWEIGHT+", "+
			BIRTHTYPE+", "+
			BIRTHORDER+", "+
			CHILDNAMEAGREED+", "+
			MOTHERBIRTHDAY+", "+
			FATHERBIRTHDAY+", "+
			MOTHERSTREETADDRESS+", "+
			ADDRSAMEASINF+", "+
			FATHERAGE+", "+
			MOTHERAGE+", "+
			ABORIGINAL+", "+
			LIVEDONRESERVE+", "+
			BURIALPERMITNUMBER+", "+
			YEARSATOCCUPATION+", "+
			EMPLOYER+", "+
			PEACEOFFICER+", "+
			COUNTYOFBIRTH+", "+
			COUNTYOFISSUE+", "+
			ISSUEDATE+", "+
			MOTHERMAIDENNAME+", "+
			FATHERMAIDENNAME+", "+
			IDENTIFICATIONMARKS+", "+
			TRIBALMEMBER+", "+
			TRIBALNAME+", "+
			LOCOFDEATHCVT+", "+
			TIMEDEATHSTATUS+", "+
			TRANSFERREDLOCATIONNAME+", "+
			TRANSFERREDLOCATIONADDR+", "+
			TRANSFERREDLOCATIONCITY+", "+
			TRANSFERREDLOCATIONSTATE+", "+
			TRANSFERREDLOCATIONZIP+", "+
			TRANSFERREDLOCATIONPHONE+", "+
			HOSPITALNAMEOFBIRTHPLACEOFDECEASEDUNDER1YEAROLD+", "+
			EMPLOYERADDR+", "+
			RESIDENTWITHINCITYVILLAGELIMIT+", "+
			RESIDENTLOCALITYLIMITNAME+", "+
			HOSPITALIZEDLAST2MONTHS+", "+
			PREGNANTDELIVERYDATE+" "+
			") VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
      );
      pstmt.setString(1, vitals.getDateOfBirth());
      pstmt.setString(2, vitals.getDateOfBurial());
      pstmt.setString(3,vitals.getServiceDateKey());
      pstmt.setString(4, vitals.getDateOfDeath());
      pstmt.setString(5, vitals.getDecFName());
      pstmt.setString(6, vitals.getDecMName());
      pstmt.setString(7, vitals.getDecLName());
      pstmt.setString(8, vitals.getDecFullName());
      pstmt.setString(9,vitals.getDecMaiden());
      pstmt.setString(10,vitals.getDecMarried());
      pstmt.setString(11,vitals.getDecmrmrs());
      pstmt.setString(12,vitals.getDecResCityTWP());
      pstmt.setString(13,vitals.getDecResCityTWPBox());
      pstmt.setString(14,vitals.getDecResCounty());
      pstmt.setString(15,vitals.getDecResInsideCity());
      pstmt.setString(16,vitals.getDecResLengthOfTime());
      pstmt.setString(17,vitals.getDecResMailCity());
      pstmt.setString(18,vitals.getDecResState());
      pstmt.setString(19,vitals.getDecResStreet());
      pstmt.setString(20,vitals.getDecResZip());
      pstmt.setString(21,vitals.getSex());
      pstmt.setString(22,vitals.getSSNo());
      pstmt.setString(23,vitals.getStateOfDeath());
      pstmt.setString(24,vitals.getTimeOfDeath());
      pstmt.setString(25,vitals.getOccupation());
      pstmt.setString(26,vitals.getKindBusiness());
      pstmt.setString(27,vitals.getBirthPlace());
      pstmt.setString(28,vitals.getBirthPlaceCSV());
      pstmt.setString(29,vitals.getVeteranYN());
      pstmt.setString(30,vitals.getAncestry());
      pstmt.setString(31,vitals.getRace());
      pstmt.setString(32,vitals.getHispanicYN());
      pstmt.setString(33,vitals.getElementaryEducation());
      pstmt.setString(34,vitals.getCollegeEducation());
      pstmt.setString(35,vitals.getFatherFullName());
      pstmt.setString(36,vitals.getMotherFullName());
      pstmt.setString(37,vitals.getFatherFirstName());
      pstmt.setString(38,vitals.getFatherMiddleName());
      pstmt.setString(39,vitals.getFatherLastName());
      pstmt.setString(40,vitals.getMotherFirstName());
      pstmt.setString(41,vitals.getMotherMiddleName());
      pstmt.setString(42,vitals.getMotherLastName());
      pstmt.setString(43,vitals.getMotherBirthCity());
      pstmt.setString(44,vitals.getMotherBirthState());
      pstmt.setString(45,vitals.getFatherBirthCity());
      pstmt.setString(46,vitals.getFatherBirthState());
      pstmt.setString(47,vitals.getSuffix());
      pstmt.setString(48,vitals.getAliasFirst());
      pstmt.setString(49,vitals.getAliasMiddle());
      pstmt.setString(50,vitals.getAliasLast());
      pstmt.setString(51,vitals.getAliasPrefix());
      pstmt.setString(52,vitals.getAliasSuffix());
      pstmt.setString(53,vitals.getCrematoryName());
      pstmt.setString(54,vitals.getMilitaryOrganizatn());
      pstmt.setString(55,vitals.getWarFromDate());
      pstmt.setString(56,vitals.getWarToDate());
      pstmt.setString(57,vitals.getDecAptNo());
      pstmt.setString(58,vitals.getCitizenship());
      pstmt.setString(59,vitals.getLocalityUnincorporated());
      pstmt.setString(60,vitals.getDecEducation());
      pstmt.setString(61,vitals.getLoctnOfDeathZip());
      pstmt.setString(62,vitals.getDecSpecifyHispanic());
      pstmt.setString(63,vitals.getTribalReservation());
      pstmt.setString(64,vitals.getCremationAuthorizer());
      pstmt.setString(65,vitals.getCustomerName());
      pstmt.setString(66,vitals.getMotherBirthCountry());
      pstmt.setString(67,vitals.getFatherBirthCountry());
      pstmt.setString(68,vitals.getDecResCountry());
      pstmt.setString(69,vitals.getDecResPhone());
      pstmt.setString(70,vitals.getFatherForename());
      pstmt.setString(71,vitals.getFatherSurnameBirth());
      pstmt.setString(72,vitals.getFatherOtherSurname());
      pstmt.setString(73,vitals.getFatherOccupation());
      pstmt.setString(74,vitals.getMotherForename());
      pstmt.setString(75,vitals.getMotherSurnameBirth());
      pstmt.setString(76,vitals.getMotherOtherSurname());
      pstmt.setString(77,vitals.getMotherOccupation());
      pstmt.setString(78,vitals.getStillBirthDesc());
      pstmt.setInt(79,vitals.getPregnancyDuration());
      pstmt.setInt(80,vitals.getNumberChildren());
      pstmt.setInt(81,vitals.getNumberLiveborn());
      pstmt.setInt(82,vitals.getNumberStillborn());
      pstmt.setString(83,vitals.getChildWeight());
      pstmt.setString(84,vitals.getBirthType());
      pstmt.setString(85,vitals.getBirthOrder());
      pstmt.setString(86,vitals.getChildNameAgreed());
      
      pstmt.setString(87,vitals.getMotherBirthday());
      pstmt.setString(88,vitals.getFatherBirthday());
      pstmt.setString(89,vitals.getMotherStreetAddress());
      pstmt.setString(90,vitals.getDeceasedSame());
      pstmt.setInt(91,vitals.getFatherAge());
      pstmt.setInt(92,vitals.getMotherAge());
      pstmt.setString(93,vitals.getAboriginal());
      pstmt.setString(94,vitals.getLivedOnReserve());
      pstmt.setString(95,vitals.getBurialPermitNumber());
      pstmt.setInt(96,vitals.getYearsAtOccupation());
      pstmt.setString(97,vitals.getEmployer());
      pstmt.setString(98,vitals.getWasPeaceOfficerYN());
      pstmt.setString(99,vitals.getCountyOfBirth());
      pstmt.setString(100,vitals.getCountyOfIssue());
      pstmt.setString(101,vitals.getIssueDate());     
      pstmt.setString(102,vitals.getMotherMaidenName());
      pstmt.setString(103,vitals.getFatherMaidenName());
      pstmt.setString(104,vitals.getIdentificationMarks());
      pstmt.setString(105,vitals.getTribalMember());
      pstmt.setString(106,vitals.getTribalName());
      pstmt.setString(107,vitals.getLocOfDeathCVT());
      pstmt.setString(108,vitals.getTimeOfDeathStatus());
      pstmt.setString(109,vitals.getTransferredLocationName());
      pstmt.setString(110,vitals.getTransferredLocationAddr());
      pstmt.setString(111,vitals.getTransferredLocationCity());
      pstmt.setString(112,vitals.getTransferredLocationState());
      pstmt.setString(113,vitals.getTransferredLocationZip());
      pstmt.setString(114,vitals.getTransferredLocationPhone());
      pstmt.setString(115,vitals.getHospitalNameOfBirthPlaceOfDeceasedUnder1YearOld());
      pstmt.setString(116,vitals.getEmployerAddr());
      pstmt.setString(117,vitals.getResidentWithinCityVillageLimit());
      pstmt.setString(118,vitals.getResidentLocalityLimitName());
      pstmt.setString(119,vitals.getHospitalizedLast2Months());
      pstmt.setString(120,vitals.getPregnantDeliveryDate());
      return pstmt;
      
      
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalDeceasedPeer.Insert",e);
    }
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
    java.sql.PreparedStatement pstmt=null;
    java.sql.Connection connection = null;
    try {
      connection = t.getConnection();
      pstmt = connection.prepareStatement("DELETE FROM vitalstats WHERE VitalsMasterKey=?");
      pstmt.setInt(1,p.getId());
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalDeceasedPeer.Remove",e);
    }
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
    	
	String sql = "SELECT  VitalsMasterKey,"+
            "DateOfBirth,"+
            "BurialDate,"+
            SERVICEDATEKEY+","+
            "DateOfDeath,"+
            "DeceasedFirstName,"+
            "DeceasedMidName,"+
            "DeceasedLastName,"+
            "DeceasedFullName,"+
            "DeceasedMaidenName,"+
            "DecMarried,"+
            "DecMrMrs,"+
            "DecCityTwpName,"+
            "DecCityTwpBox,"+
            "DecResCounty,"+
            "DecResInsideCity,"+
            "DecResStreet,"+
            "TimeAtResidence,"+
            "DecResMailCity,"+
            "DecResState,"+
            "DecResStreet,"+
            "DecResZip,"+
            "DeceasedSex,"+
            "SocialSecurityNo,"+
            "StateOfDeath,"+
            "TimeOfDeath,"+
            OCCUPATION+","+
            KINDBUSINESS+","+
            BIRTHPLACE+","+
            BIRTHPLACECSV+","+
            VETERAN+","+
            ANCESTRY+","+
            RACE+","+
            HISPANIC+","+
            EDUCELEM+","+
            EDUCCOLL+","+
            FATHERFULLNAME+","+
            MOTHERFULLNAME+","+
            FATHERFIRST+","+
            FATHERMIDDLE+","+
            FATHERLAST+","+
            MOTHERFIRST+","+
            MOTHERMIDDLE+","+
            MOTHERLAST+","+
            MOTHERBIRTHCITY+","+
            MOTHERBIRTHSTATE+","+
            FATHERBIRTHCITY+","+
            FATHERBIRTHSTATE+","+
            SUFFIX+","+
            ALIASFIRST+","+
            ALIASMIDDLE+","+
            ALIASLAST+","+
            ALIASPREFIX+","+
            ALIASSUFFIX+","+
            CREMATORYNAME+","+
            MILITARYORG+","+
            WARFROM+","+
            WARTO+","+
            DECAPTNO+","+
            CITIZENSHIP+","+
            LOCALITYUNINCORPORATED+","+
            DECEDUCATION+","+
            LOCTNOFDEATHZIP+","+
            DECSPECIFYHISPANIC+","+
            TRIBALRESERVATION+","+
            CREMATIONAUTHORIZER+","+
            CUSTOMERNAME+", "+
            FATHERBIRTHCOUNTRY+", " +
            MOTHERBIRTHCOUNTRY+"," +
            RESCOUNTRY + "," +
			PHONE+","+			
			FATHERFORENAME+", "+
			FATHERSURNAMEBIRTH+", "+
			FATHEROTHERSURNAME+", "+
			FATHEROCCUPATION+", "+
			MOTHERFORENAME+", "+
			MOTHERSURNAMEBIRTH+", "+
			MOTHEROTHERSURNAME+", "+
			MOTHEROCCUPATION+", "+
			STILLBIRTHDESC+", "+
			PREGNANCYDURATION+", "+
			NUMBERCHILDREN+", "+
			NUMBERLIVEBORN+", "+
			NUMBERSTILLBORN+", "+
			CHILDWEIGHT+", "+
			BIRTHTYPE+", "+
			BIRTHORDER+", "+
			CHILDNAMEAGREED+", "+
			MOTHERBIRTHDAY+", "+
			FATHERBIRTHDAY+", "+
			MOTHERSTREETADDRESS+", "+
			ADDRSAMEASINF+", "+
			FATHERAGE+", "+
			MOTHERAGE+", "+
			ABORIGINAL+", "+
			LIVEDONRESERVE+", "+
			BURIALPERMITNUMBER+", "+
			YEARSATOCCUPATION+", "+
			EMPLOYER+", "+
			PEACEOFFICER+", "+
			COUNTYOFBIRTH+", "+
			COUNTYOFISSUE+", "+
			ISSUEDATE+", "+
			MOTHERMAIDENNAME+", "+
			FATHERMAIDENNAME+", "+
			IDENTIFICATIONMARKS+", "+
			TRIBALMEMBER+", "+
			TRIBALNAME+", "+
			LOCOFDEATHCVT+", "+
			TIMEDEATHSTATUS+", "+
			TRANSFERREDLOCATIONNAME+", "+
			TRANSFERREDLOCATIONADDR+", "+
			TRANSFERREDLOCATIONCITY+", "+
			TRANSFERREDLOCATIONSTATE+", "+
			TRANSFERREDLOCATIONZIP+", "+
			TRANSFERREDLOCATIONPHONE+", "+
			HOSPITALNAMEOFBIRTHPLACEOFDECEASEDUNDER1YEAROLD+", "+
			EMPLOYERADDR+", "+
			RESIDENTWITHINCITYVILLAGELIMIT+", "+
			RESIDENTLOCALITYLIMITNAME+", "+
			HOSPITALIZEDLAST2MONTHS+", "+
			PREGNANTDELIVERYDATE+" "+
            "from vitalstats WHERE VitalsMasterKey = " +
    p.getId();
    
    return sql;


}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
    java.sql.Connection connection = null;
    try {
      DbVitalsDeceased vitals=(DbVitalsDeceased)p;
      connection = t.getConnection();
      java.sql.PreparedStatement pstmt = connection.prepareStatement(
      "UPDATE vitalstats SET "+
            "DateOfBirth=?,"+
            "BurialDate=?,"+
            "DateOfDeath=?,"+
            "DeceasedFirstName=?,"+
            "DeceasedMidName=?,"+
            "DeceasedLastName=?,"+
            "DeceasedFullName=?,"+
            "DeceasedMaidenName=?,"+
            "DecMarried=?,"+
            "DecMrMrs=?,"+
            "DecCityTwpName=?,"+
            "DecCityTwpBox=?,"+
            "DecResCounty=?,"+
            "DecResInsideCity=?,"+
            "DecResStreet=?,"+
            "TimeAtResidence=?,"+
            "DecResMailCity=?,"+
            "DecResState=?,"+
            "DecResZip=?,"+
            "DeceasedSex=?,"+
            "SocialSecurityNo=?,"+
            "StateOfDeath=?,"+
            "TimeOfDeath=?,"+
            OCCUPATION+"=?,"+
            KINDBUSINESS+"=?,"+
            BIRTHPLACE+"=?,"+
            BIRTHPLACECSV+"=?,"+
            VETERAN+"=?,"+
            ANCESTRY+"=?,"+
            RACE+"=?,"+
            HISPANIC+"=?,"+
            EDUCELEM+"=?,"+
            EDUCCOLL+"=?,"+
            FATHERFULLNAME+"=?,"+
            MOTHERFULLNAME+"=?,"+
            FATHERFIRST+"=?,"+
            FATHERMIDDLE+"=?,"+
            FATHERLAST+"=?,"+
            MOTHERFIRST+"=?,"+
            MOTHERMIDDLE+"=?,"+
            MOTHERLAST+"=?,"+
            MOTHERBIRTHCITY+"=?,"+
            MOTHERBIRTHSTATE+"=?,"+            
            FATHERBIRTHCITY+"=?,"+
            FATHERBIRTHSTATE+"=?,"+            
            SUFFIX+"=?,"+
            ALIASFIRST+"=?,"+
            ALIASMIDDLE+"=?,"+
            ALIASLAST+"=?,"+
            ALIASPREFIX+"=?,"+
            ALIASSUFFIX+"=?,"+
            CREMATORYNAME+"=?,"+
            MILITARYORG+"=?,"+
            WARFROM+"=?,"+
            WARTO+"=?,"+
            DECAPTNO+"=?,"+
            CITIZENSHIP+"=?,"+
            LOCALITYUNINCORPORATED+"=?,"+
            DECEDUCATION+"=?,"+
            LOCTNOFDEATHZIP+"=?,"+
            DECSPECIFYHISPANIC+"=?,"+
            TRIBALRESERVATION+"=?,"+
            CREMATIONAUTHORIZER+"=?,"+
            CUSTOMERNAME+"=?, "+
            MOTHERBIRTHCOUNTRY+"=?, "+
            FATHERBIRTHCOUNTRY+"=?, "+
            RESCOUNTRY+"=?, "+
			PHONE+"=?, "+
			FATHERFORENAME+"=?, "+
			FATHERSURNAMEBIRTH+"=?, "+
			FATHEROTHERSURNAME+"=?, "+
			FATHEROCCUPATION+"=?, "+
			MOTHERFORENAME+"=?, "+
			MOTHERSURNAMEBIRTH+"=?, "+
			MOTHEROTHERSURNAME+"=?, "+
			MOTHEROCCUPATION+"=?, "+
			STILLBIRTHDESC+"=?, "+
			PREGNANCYDURATION+"=?, "+
			NUMBERCHILDREN+"=?, "+
			NUMBERLIVEBORN+"=?, "+
			NUMBERSTILLBORN+"=?, "+
			CHILDWEIGHT+"=?, "+
			BIRTHTYPE+"=?, "+
			BIRTHORDER+"=?, "+
			CHILDNAMEAGREED+"=?, "+
			MOTHERBIRTHDAY+"=?, "+
			FATHERBIRTHDAY+"=?, "+
			MOTHERSTREETADDRESS+"=?, "+
			ADDRSAMEASINF+"=?, "+
			FATHERAGE+"=?, "+
			MOTHERAGE+"=?, "+
			ABORIGINAL+"=?, "+
			LIVEDONRESERVE+"=?, "+
			BURIALPERMITNUMBER+"=?, "+
			YEARSATOCCUPATION+"=?, "+
			EMPLOYER+"=?, "+
			PEACEOFFICER+"=?, "+
			SERVICEDATEKEY+"=?, "+
			COUNTYOFBIRTH+"=?, "+
			COUNTYOFISSUE+"=?, "+
			ISSUEDATE+"=?, "+
			MOTHERMAIDENNAME+"=?, "+
			FATHERMAIDENNAME+"=?, "+
			IDENTIFICATIONMARKS+"=?, "+
			TRIBALMEMBER+"=?, "+
			TRIBALNAME+"=?, "+
			LOCOFDEATHCVT+"=?, "+
			TIMEDEATHSTATUS+"=?, "+
			TRANSFERREDLOCATIONNAME+"=?, "+
			TRANSFERREDLOCATIONADDR+"=?, "+
			TRANSFERREDLOCATIONCITY+"=?, "+
			TRANSFERREDLOCATIONSTATE+"=?, "+
			TRANSFERREDLOCATIONZIP+"=?, "+
			TRANSFERREDLOCATIONPHONE+"=?, "+
			HOSPITALNAMEOFBIRTHPLACEOFDECEASEDUNDER1YEAROLD+"=?, "+
			EMPLOYERADDR+"=?, "+
			RESIDENTWITHINCITYVILLAGELIMIT+"=?, "+
			RESIDENTLOCALITYLIMITNAME+"=?, "+
			HOSPITALIZEDLAST2MONTHS+"=?, "+
			PREGNANTDELIVERYDATE+"=? "+
            "WHERE VitalsMasterKey = ?",
      java.sql.ResultSet.TYPE_SCROLL_SENSITIVE, java.sql.ResultSet.CONCUR_UPDATABLE);
      pstmt.setString(1, vitals.getDateOfBirth());
      pstmt.setString(2, vitals.getDateOfBurial());
      pstmt.setString(3, vitals.getDateOfDeath());
      pstmt.setString(4, vitals.getDecFName());
      pstmt.setString(5, vitals.getDecMName());
      pstmt.setString(6, vitals.getDecLName());
      pstmt.setString(7, vitals.getDecFullName());
      pstmt.setString(8,vitals.getDecMaiden());
      pstmt.setString(9,vitals.getDecMarried());
      pstmt.setString(10,vitals.getDecmrmrs());
      pstmt.setString(11,vitals.getDecResCityTWP());
      pstmt.setString(12,vitals.getDecResCityTWPBox());
      pstmt.setString(13,vitals.getDecResCounty());
      pstmt.setString(14,vitals.getDecResInsideCity());
      pstmt.setString(15,vitals.getDecResStreet());
      pstmt.setString(16,vitals.getDecResLengthOfTime());
      pstmt.setString(17,vitals.getDecResMailCity());
      pstmt.setString(18,vitals.getDecResState());
      pstmt.setString(19,vitals.getDecResZip());
      pstmt.setString(20,vitals.getSex());
      pstmt.setString(21,vitals.getSSNo());
      pstmt.setString(22,vitals.getStateOfDeath());
      pstmt.setString(23,vitals.getTimeOfDeath());
      pstmt.setString(24,vitals.getOccupation());
      pstmt.setString(25,vitals.getKindBusiness());
      pstmt.setString(26,vitals.getBirthPlace());
      pstmt.setString(27,vitals.getBirthPlaceCSV());
      pstmt.setString(28,vitals.getVeteranYN());
      pstmt.setString(29,vitals.getAncestry());
      pstmt.setString(30,vitals.getRace());
      pstmt.setString(31,vitals.getHispanicYN());
      pstmt.setString(32,vitals.getElementaryEducation());
      pstmt.setString(33,vitals.getCollegeEducation());
      pstmt.setString(34,vitals.getFatherFullName());
      pstmt.setString(35,vitals.getMotherFullName());
      pstmt.setString(36,vitals.getFatherFirstName());
      pstmt.setString(37,vitals.getFatherMiddleName());
      pstmt.setString(38,vitals.getFatherLastName());
      pstmt.setString(39,vitals.getMotherFirstName());
      pstmt.setString(40,vitals.getMotherMiddleName());
      pstmt.setString(41,vitals.getMotherLastName());
      pstmt.setString(42,vitals.getMotherBirthCity());
      pstmt.setString(43,vitals.getMotherBirthState());
      pstmt.setString(44,vitals.getFatherBirthCity());
      pstmt.setString(45,vitals.getFatherBirthState());
      pstmt.setString(46,vitals.getSuffix());
      pstmt.setString(47,vitals.getAliasFirst());
      pstmt.setString(48,vitals.getAliasMiddle());
      pstmt.setString(49,vitals.getAliasLast());
      pstmt.setString(50,vitals.getAliasPrefix());
      pstmt.setString(51,vitals.getAliasSuffix());
      pstmt.setString(52,vitals.getCrematoryName());
      pstmt.setString(53,vitals.getMilitaryOrganizatn());
      pstmt.setString(54,vitals.getWarFromDate());
      pstmt.setString(55,vitals.getWarToDate());
      pstmt.setString(56,vitals.getDecAptNo());
      pstmt.setString(57,vitals.getCitizenship());
      pstmt.setString(58,vitals.getLocalityUnincorporated());
      pstmt.setString(59,vitals.getDecEducation());
      pstmt.setString(60,vitals.getLoctnOfDeathZip());
      pstmt.setString(61,vitals.getDecSpecifyHispanic());
      pstmt.setString(62,vitals.getTribalReservation());
      pstmt.setString(63,vitals.getCremationAuthorizer());
      pstmt.setString(64,vitals.getCustomerName());
      pstmt.setString(65,vitals.getMotherBirthCountry());
      pstmt.setString(66,vitals.getFatherBirthCountry());
      pstmt.setString(67,vitals.getDecResCountry());
      pstmt.setString(68,vitals.getDecResPhone());
      pstmt.setString(69,vitals.getFatherForename());
      pstmt.setString(70,vitals.getFatherSurnameBirth());
      pstmt.setString(71,vitals.getFatherOtherSurname());
      pstmt.setString(72,vitals.getFatherOccupation());
      pstmt.setString(73,vitals.getMotherForename());
      pstmt.setString(74,vitals.getMotherSurnameBirth());
      pstmt.setString(75,vitals.getMotherOtherSurname());
      pstmt.setString(76,vitals.getMotherOccupation());
      pstmt.setString(77,vitals.getStillBirthDesc());
      pstmt.setInt(78,vitals.getPregnancyDuration());
      pstmt.setInt(79,vitals.getNumberChildren());
      pstmt.setInt(80,vitals.getNumberLiveborn());
      pstmt.setInt(81,vitals.getNumberStillborn());
      pstmt.setString(82,vitals.getChildWeight());
      pstmt.setString(83,vitals.getBirthType());
      pstmt.setString(84,vitals.getBirthOrder());      
      pstmt.setString(85,vitals.getChildNameAgreed());
      
      pstmt.setString(86,vitals.getMotherBirthday());
      pstmt.setString(87,vitals.getFatherBirthday());
      pstmt.setString(88,vitals.getMotherStreetAddress());
      pstmt.setString(89,vitals.getDeceasedSame());
      pstmt.setInt(90,vitals.getFatherAge());
      pstmt.setInt(91,vitals.getMotherAge());
      pstmt.setString(92,vitals.getAboriginal());
      pstmt.setString(93,vitals.getLivedOnReserve());
      pstmt.setString(94,vitals.getBurialPermitNumber());
      pstmt.setInt(95,vitals.getYearsAtOccupation());
      pstmt.setString(96,vitals.getEmployer());
      pstmt.setString(97,vitals.getWasPeaceOfficerYN());
      pstmt.setString(98, vitals.getServiceDateKey());
      pstmt.setString(99,vitals.getCountyOfBirth());
      pstmt.setString(100, vitals.getCountyOfIssue());
      pstmt.setString(101,vitals.getIssueDate());     
      pstmt.setString(102,vitals.getMotherMaidenName());
      pstmt.setString(103,vitals.getFatherMaidenName());
      pstmt.setString(104,vitals.getIdentificationMarks());
      pstmt.setString(105,vitals.getTribalMember());
      pstmt.setString(106,vitals.getTribalName());
      pstmt.setString(107,vitals.getLocOfDeathCVT());
      pstmt.setString(108,vitals.getTimeOfDeathStatus());
      pstmt.setString(109,vitals.getTransferredLocationName());
      pstmt.setString(110,vitals.getTransferredLocationAddr());
      pstmt.setString(111,vitals.getTransferredLocationCity());
      pstmt.setString(112,vitals.getTransferredLocationState());
      pstmt.setString(113,vitals.getTransferredLocationZip());
      pstmt.setString(114,vitals.getTransferredLocationPhone());
      pstmt.setString(115,vitals.getHospitalNameOfBirthPlaceOfDeceasedUnder1YearOld());
      pstmt.setString(116,vitals.getEmployerAddr());
      pstmt.setString(117,vitals.getResidentWithinCityVillageLimit());
      pstmt.setString(118,vitals.getResidentLocalityLimitName());
      pstmt.setString(119,vitals.getHospitalizedLast2Months());
      pstmt.setString(120,vitals.getPregnantDeliveryDate());
      pstmt.setInt(121,vitals.getId());
          
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsDeceasedPeer.Update",e);
    }
}
}
