package com.aldorsolutions.webfdms.beans;


/**
 * DbVitalsFirstCallPeer supplies SQL for Persistent Class
 * Creation date: (01/06/2002 4:13:09 PM)
 * @author: 
 */
public class DbVitalsFirstCallPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "VitalsMasterKey";
	static public final String CASE_ID    	= "VitalsMasterKey";
	static public final String ARRNGDATE	= "PreneedArrngDate";
	static public final String ARRNGTIME   	= "AppointmentTime";
	static public final String ORIGPNDATE 	= "OriginalPNDate";
	static public final String LOCDEATHADDR	= "LocOfDeathStreet";
	static public final String SOURCE	 	= "PNSource";
	static public final String REASON	 	= "EmbalmingReason";
	static public final String EMBALMREQ    = "EmbalmingChargeReq";
	static public final String LOCDEATH 	= "LocationOfDeath";
	static public final String LOCDEATHZIP 	= "LoctnOfDeathZip";
	static public final String LOCDEATHCITY	= "CityOfDeath";
	static public final String LOCDEATHSTATE= "StateOfDeath";
	static public final String LOCDEATHINSIDE= "DeathInsideCity";
	static public final String DISPDATE 	= "DispositionDate";
	static public final String AGEYEARS 	= "AgeYears";
	static public final String FACILITYNAME	= "FacilityName";
	static public final String FACILITYADDR	= "FacilityStreet";
	static public final String FACILITYCITY	= "FacilityCityStZip";
	static public final String FACILITYLICNO= "FacilityLicenseNo";
	static public final String FACILITYPHONE= "FacilityPhone";
	static public final String CHAPELNAME	= "ChapelLocation";
	//static public final String LOCALENUMBER	= "LocaleNumber";
	static public final String ARRANGERID	= "ArrangerId";
	static public final String ARRANGERNAME = "DirectorName";
	static public final String SHIPPINGINFO = "ShippingData";
	
	static public final String AGEMONTHS	= "AgeMonths";
	static public final String AGEDAYS		= "AgeDays";
	static public final String AGEHOURS		= "AgeHours";
	static public final String AGEMINUTES	= "AgeMinutes";
	static public final String DISPMETHOD	= "DispositionMethod";
	static public final String DISPPLACE	= "DispositionPlace";
	static public final String DISPSTREET	= "DispositionStreet";
	static public final String DISPCITY		= "DispositionCity";
	static public final String DISPSTATE	= "DispositionState";
	static public final String DISPZIP		= "DispositionZip";
	static public final String DISPCOUNTY	= "DispositionCounty";
	static public final String DISPCODE		= "DispositionCode";
	static public final String COUNTYDEATH  = "CountyOfDeath";
	static public final String INPATIENTDOA = "InpatientDOA";
	static public final String MARKETPLAN   = "MarketingPlan";
	static public final String INTFROMDATE  = "InterestFromDate";
	static public final String DIRECTORLICENSE = "DirectorLicenseNo";
	static public final String EMBALMERID 		= "EmbalmerID";
	static public final String EMBALMERNAME		= "EmbalmerName";
	static public final String EMBALMERLICENSE	= "EmbalmerLicenseNo";
	
	static public final String FUNERALDIRECTORDATESIGNED= "FuneralDirectorDateSigned";
	static public final String BURIALPERMITISSUER 		= "BurialPermitIssuer";
	static public final String PLACEOFISSUE 			= "PlaceOfIssue";
	static public final String BURIALISSUEDDATE 		= "BurialIssuedDate";
	static public final String REGISTRATIONNUMBER 		= "RegistrationNumber";
	static public final String REGCODENUMBER 			= "RegCodeNumber";
	static public final String REGISTRARFILEDATE		= "RegistrarFileDate";
	
	static public final String CALLINFONOTE		= "CallInfoNote";
	
	static public final String DATEOFNOTIFYTODIRECTOR		= "DateOfNotifyToDirector";
	static public final String TIMEOFNOTIFYTODIRECTOR		= "TimeOfNotifyToDirectory";
	static public final String DISPOSITIONPLACETYPE = "DispositionPlaceType";
	static public final String LOCDEATHLICENSENUMBER = "LocDeathLicenseNumber";
	static public final String FACILITYSAMEASCHAPEL = "FacilitySameAsChapel";
	public static final String ADMITTEDFACILITYDATEOFPLACEOFDEATH = "AdmittedFacilityDateOfPlaceOfDeath";
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsFirstCall.Insert: Inserts must be done through DbVitalsDeceased.");
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsFirstCall.Remove: Deletions must be done through DbVitalsDeceased.");
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
			"SELECT 	 "+CASE_ID		+","+
						CHAPELNAME		+","+
//						LOCALENUMBER	+","+
						ARRANGERID		+","+
						ARRANGERNAME	+","+
						ARRNGDATE		+","+
						ARRNGTIME		+","+
						ORIGPNDATE		+","+
						LOCDEATHADDR	+","+
						SOURCE			+","+
						REASON			+","+
						LOCDEATH		+","+
						LOCDEATHZIP		+","+
						LOCDEATHCITY	+","+
						LOCDEATHSTATE	+","+
						DISPDATE		+","+
						AGEYEARS		+","+
						FACILITYNAME	+","+
						FACILITYADDR	+","+
						FACILITYCITY	+","+
						FACILITYLICNO	+","+
						FACILITYPHONE   +","+
						SHIPPINGINFO    +","+
						AGEMONTHS		+","+
						AGEDAYS			+","+
						AGEHOURS		+","+
						AGEMINUTES		+","+
						DISPMETHOD		+","+
						DISPPLACE		+","+
						DISPSTREET		+","+
						DISPCITY		+","+
						DISPSTATE		+","+
						DISPZIP			+","+
						DISPCOUNTY		+","+
						DISPCODE		+","+
						COUNTYDEATH		+","+
						INPATIENTDOA	+","+
						MARKETPLAN		+","+
						//INTFROMDATE	+" "+
						INTFROMDATE		+","+
						DIRECTORLICENSE +","+
						EMBALMERID 		+","+
						EMBALMREQ		+","+
						LOCDEATHINSIDE  +","+
						FUNERALDIRECTORDATESIGNED +","+
						BURIALPERMITISSUER	+","+
						PLACEOFISSUE 		+","+
						BURIALISSUEDDATE 	+","+
						REGISTRATIONNUMBER 	+","+
						REGISTRARFILEDATE	+","+
						REGCODENUMBER 		+","+
						EMBALMERNAME		+","+
						EMBALMERLICENSE		+", "+
						CALLINFONOTE		+", "+
						DATEOFNOTIFYTODIRECTOR +", "+
						TIMEOFNOTIFYTODIRECTOR +", "+
						DISPOSITIONPLACETYPE +", "+
						LOCDEATHLICENSENUMBER +", "+
						FACILITYSAMEASCHAPEL +", "+
						ADMITTEDFACILITYDATEOFPLACEOFDEATH +" "+
						"from vitalstats WHERE VitalsMasterKey = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		//AppLog.trace("DbVitalsFirstCallPeer.getUpdateSql");
		try {
			DbVitalsFirstCall vitals=(DbVitalsFirstCall)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE vitalstats SET "+
						CHAPELNAME		+"=?,"+
	//					LOCALENUMBER	+"=?,"+
						ARRANGERID		+"=?,"+
						ARRANGERNAME	+"=?,"+
						ARRNGDATE		+"=?,"+
						ARRNGTIME		+"=?,"+
						ORIGPNDATE		+"=?,"+
						LOCDEATHADDR	+"=?,"+
						SOURCE			+"=?,"+
						REASON			+"=?,"+
						LOCDEATH		+"=?,"+
						LOCDEATHZIP		+"=?,"+
						LOCDEATHCITY	+"=?,"+
						LOCDEATHSTATE	+"=?,"+
						DISPDATE		+"=?,"+
						AGEYEARS		+"=?,"+
						FACILITYNAME	+"=?,"+
						FACILITYADDR	+"=?,"+
						FACILITYCITY	+"=?,"+
						FACILITYPHONE  +"=?,"+
						FACILITYLICNO	+"=?,"+
						SHIPPINGINFO   +"=?,"+
						AGEMONTHS		+"=?,"+
						AGEDAYS			+"=?,"+
						AGEHOURS		+"=?,"+
						AGEMINUTES		+"=?,"+
						DISPMETHOD		+"=?,"+
						DISPPLACE		+"=?,"+
						DISPSTREET		+"=?,"+
						DISPCITY		+"=?,"+
						DISPSTATE		+"=?,"+
						DISPZIP			+"=?,"+
						DISPCOUNTY		+"=?,"+
						DISPCODE		+"=?,"+
						COUNTYDEATH		+"=?,"+
						INPATIENTDOA	+"=?,"+
						MARKETPLAN		+"=?,"+
						//INTFROMDATE	+"=? "+
						INTFROMDATE	+"=?, "+
						DIRECTORLICENSE +"=?,"+
						LOCDEATHINSIDE +"=?,"+
						EMBALMERID     +"=?,"+
						EMBALMREQ		+"=?,"+
						
						FUNERALDIRECTORDATESIGNED +"=?,"+
						BURIALPERMITISSUER	+"=?,"+
						PLACEOFISSUE 		+"=?,"+
						BURIALISSUEDDATE 	+"=?,"+
						REGISTRATIONNUMBER 	+"=?,"+
						REGCODENUMBER 		+"=?,"+
						REGISTRARFILEDATE 	+"=?,"+
						EMBALMERNAME		+"=?,"+
						EMBALMERLICENSE		+"=?, "+
						CALLINFONOTE		+"=?, "+
						DATEOFNOTIFYTODIRECTOR +"=?, "+
						TIMEOFNOTIFYTODIRECTOR +"=?, "+
						DISPOSITIONPLACETYPE +"=?, "+
						LOCDEATHLICENSENUMBER+"=?, "+
						FACILITYSAMEASCHAPEL+"=?, "+
						ADMITTEDFACILITYDATEOFPLACEOFDEATH+"=? "+
						"WHERE VitalsMasterKey = ?"
			);		
			pstmt.setString(1, vitals.getChapelName());	
//			pstmt.setInt   (2, vitals.getLocaleNumber());
			pstmt.setInt   (2, vitals.getArrangerID());
			pstmt.setString(3, vitals.getArrangerName());
			pstmt.setString(4, vitals.getArrangeDate());
			pstmt.setString(5, vitals.getArrangeTime());
			pstmt.setString(6, vitals.getOriginalPnDate());
			pstmt.setString(7, vitals.getPlaceDeathAddr());
			pstmt.setString(8, vitals.getSource());
			pstmt.setString(9,vitals.getEmbalmingReason());
			pstmt.setString(10,vitals.getPlaceDeath());
			pstmt.setString(11,vitals.getPlaceDeathZip());
			pstmt.setString(12,vitals.getPlaceDeathCity() );
			pstmt.setString(13,vitals.getPlaceDeathState() );
			pstmt.setString(14,vitals.getDispositionDate() );
			pstmt.setShort (15,(short)vitals.getAgeYears() );
			pstmt.setString(16,vitals.getFacilityName() );
			pstmt.setString(17,vitals.getFacilityStreet() );
			pstmt.setString(18,vitals.getFacilityCityStZip() );
			pstmt.setString(19,vitals.getFacilityPhone() );
			pstmt.setString(20,vitals.getFacilityLicenseNo() );
			pstmt.setString(21,vitals.getShippingInfo() );
			pstmt.setShort (22,(short)vitals.getAgeMonths());
			pstmt.setShort (23,(short)vitals.getAgeDays());
			pstmt.setShort (24,(short)vitals.getAgeHours());
			pstmt.setShort (25,(short)vitals.getAgeMinutes());
			pstmt.setString(26,vitals.getDispositionMethod());
			pstmt.setString(27,vitals.getDispositionPlace());
			pstmt.setString(28,vitals.getDispositionStreet());
			pstmt.setString(29,vitals.getDispositionCity());
			pstmt.setString(30,vitals.getDispositionState());
			pstmt.setString(31,vitals.getDispositionZip());
			pstmt.setString(32,vitals.getDispositionCounty());
			pstmt.setString(33,vitals.getDispositionCode());
			pstmt.setString(34,vitals.getCountyOfDeath());
			pstmt.setString(35,vitals.getInpatientDOA());
			pstmt.setString(36,vitals.getMarketingPlan());
			pstmt.setString(37,vitals.getInterestFromDate());
			pstmt.setString(38, vitals.getDirectorLicense());
			pstmt.setString(39, vitals.getPlaceDeathInside());
			pstmt.setInt   (40,vitals.getEmbalmerID());
			pstmt.setString(41,vitals.getEmbalmingChargeReq());
			pstmt.setString(42,vitals.getFuneralDirectorDateSigned());
			pstmt.setString(43,vitals.getBurialPermitIssuer());
			pstmt.setString(44,vitals.getPlaceOfIssue());
			pstmt.setString(45,vitals.getBurialIssuedDate());
			pstmt.setString(46,vitals.getRegistrationNumber());
			pstmt.setString(47,vitals.getRegCodeNumber());
			pstmt.setString(48,vitals.getRegistrarFileDate());
			pstmt.setString(49,vitals.getEmbalmerName());
			pstmt.setString(50,vitals.getEmbalmerLicense());
			pstmt.setString(51,vitals.getCallInfoNote());
			pstmt.setString(52,vitals.getDateOfNotifyToDirector());
			pstmt.setString(53,vitals.getTimeOfNotifyToDirectory());
			pstmt.setString(54,vitals.getDispositionPlaceType());
			pstmt.setString(55,vitals.getLocDeathLicenseNumber());
			pstmt.setString(56,vitals.getFacilitySameAsChapel());
			pstmt.setString(57,vitals.getAdmittedFacilityDateOfPlaceOfDeath());
			pstmt.setInt(58, vitals.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsFirstCallPeer.Update:"+e.getMessage(),e);
		}
}
}
