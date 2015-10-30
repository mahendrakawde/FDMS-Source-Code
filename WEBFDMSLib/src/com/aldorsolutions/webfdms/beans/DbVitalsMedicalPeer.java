package com.aldorsolutions.webfdms.beans;

/**
 * DbVitalsMedicalPeer supplies Table Column Names and SQL for Persistent Class.
 * Creation date: (03/12/2002 4:13:09 PM) modify 030309
 * @author: 
 */
public class DbVitalsMedicalPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	
	// meCheckBox
	// 1 = certifying physician signed (MD)
	// 2 = medical examiner signed (ME)
	// 3 = Both certifying and medical signed
	
	static public final String IDENTITY    	= "VitalsMasterKey";
	static public final String CAUSE1		= "CauseOfDeath1";
	static public final String CAUSE2		= "CauseOfDeath2";
	static public final String CAUSE3		= "CauseOfDeath3";
	static public final String CAUSE4		= "CauseOfDeath4";
	static public final String INTERVAL1	= "CauseInterval1";
	static public final String INTERVAL2	= "CauseInterval2";
	static public final String INTERVAL3	= "CauseInterval3";
	static public final String INTERVAL4	= "CauseInterval4";
	static public final String OTHERCOND	= "OtherConditions";
	static public final String AUTOPSY		= "Autopsy";
	static public final String FINDINGS		= "AutopsyFindings";
	static public final String ACTUALPLACE	= "ActualPlaceDeath";
	static public final String REFERREDTOME	= "ReferredToME";
	static public final String MECHECKBOX	= "MEcheckBox";
	static public final String MEDATESIGNED	= "MEdateSigned";
	static public final String MECASENUMBER	= "MEcaseNumber";
	static public final String MEDEATHDATE	= "MEpronounDeadDate";
	static public final String MEDEATHTIME	= "MEpronounDeadTime";
	static public final String MELICENSE	= "MELicenseNumber";
	static public final String ATTENDINGPHY	= "AttendingPhysician";
	static public final String DATESIGNED	= "DateSigned";
	static public final String DRNAME		= "DoctorName";
	static public final String DRSTREET		= "DoctorStreet";
	static public final String DRCITY		= "DoctorCity";
	static public final String DRSTATE		= "DoctorState";
	static public final String DRZIP		= "DoctorZip";
	static public final String DRPHONE		= "DoctorPhone";
    static public final String DRFAX    	= "DoctorFax";
    static public final String DRJUSTICEOFTHEPEACE = "DrJusticeOfThePeace";    
	static public final String DRLICENSENO	= "DoctorLicenseNumber";
	static public final String ACCIDENTSUICIDE	= "AccidSuicideNaturl";
	static public final String INJURYDATE	= "InjuryDate";
	static public final String INJURYTIME	= "InjuryTime";
	static public final String INJURYDESCR	= "InjuryDescription";
	static public final String INJURYATWORK	= "InjuryAtWork";
	static public final String INJURYPLACE	= "InjuryPlace";
	static public final String INJURYTRANSPORTATION	= "InjuryTransportation";
	static public final String INJURYSTREET	= "InjuryStreet";
	static public final String INJURYCITY	= "InjuryCityState";
	static public final String INJURYSTATE	= "InjuryState";
	static public final String INJURYCOUNTY	= "InjuryCounty";
	static public final String INJURYAPTNO	= "InjuryAptNo";
	static public final String INJURYZIPCODE	= "InjuryZipCode";
	static public final String DR2NAME		= "Doctor2Name";
	static public final String DRTITLE		= "DoctorTitle";
	static public final String ATTENDFROM	= "DoctorAttendFrom";
	static public final String ATTENDTO		= "DoctorAttendTo";
	static public final String ATTENDLAST	= "DoctorAttendLast";
	static public final String PREGATDEATH	= "PregnantAtDeath";
	static public final String PREG12MOS	= "PregnantLast12mos";
	static public final String DRVIEWEDBODY	= "DoctorViewedBody";
	static public final String TOBACCO		= "Tobacco";
	static public final String ORGANDONCON	= "OrganDonationConside";
	static public final String ORGANDONGRANT= "OrganDonationGranted";
	static public final String TIMENOTIFIED = "TimeFDnotified";
	static public final String DATECERTIFIED = "DateCertified";
	static public final String DIAGNOSISDEFERRED = "DiagnosisDeferred";
	static public final String INJURYOCCURRED 	= "InjuryOccurred";
    static public final String DATEEMBALMED 	= "DateEmbalmed";
    static public final String DOCTOROCCUPATION = "DoctorOccupation";
    static public final String BIOPSYYN			= "BiopsyYN";
    static public final String XOPSYTOFINDCAUSE	= "XopsyToFindCause";
    static public final String OPERATIONTYPE	= "OperationType";
    static public final String OPERATIONDATE	= "OperationDate";
    static public final String OPERATIONPRFRMD	= "OperationPerformed";
    static public final String NPCHECKBOX	= "NPCheckBox";
    static public final String NPDATESIGNED	= "NPDateSigned";
    static public final String NPLICENSENUMBER	= "NPLicenseNumber";
    static public final String BODYFOUNDMORE24HR	= "BodyFoundMore24Hr";
    static public final String NOTIFICATIONOFEXAMINERREQUIRED	= "NotificationOfExaminerRequired";
    static public final String HOSPICESTATUS	= "HospiceStatus";
    static public final String INJURYONLYCITY	= "InjuryCity";
    public static final String CORONERSPHYSICIANTITLE = "CoronerSPhysicianTitle";
    public static final String CORONERSPHYSICIAN = "CoronerSPhysician";
    public static final String CORONERSPHYSICIANLICENSE = "CoronerSPhysicianLicense";
    public static final String CORONERSPHYSICIANDATESIGN = "CoronerSPhysicianDateSign";
    public static final String ATTENDINGPHYSICIANTITLE = "AttendingPhysicianTitle";
    public static final String ATTENDINGPHYSICIANLICENSE = "AttendingPhysicianLicense";
    public static final String ATTENDINGPHYSICIANADDR = "AttendingPhysicianAddr";
    public static final String ATTENDINGPHYSICIANCITY = "AttendingPhysicianCity";
    public static final String ATTENDINGPHYSICIANSTATE = "AttendingPhysicianState";
    public static final String ATTENDINGPHYSICIANZIP = "AttendingPhysicianZip";
    public static final String ATTENDINGPHYSICIANPHONE = "AttendingPhysicianPhone";
    public static final String REFERRALNUMBER = "ReferralNumber";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsMedical.Insert: Inserts must be done through DbVitalsDeceased.");
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsMedical.Remove: Deletions must be done through DbVitalsDeceased.");
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
			"SELECT "+IDENTITY		+","+
						CAUSE1			+","+
						CAUSE2			+","+
						CAUSE3			+","+
						CAUSE4			+","+
						INTERVAL1		+","+
						INTERVAL2		+","+
						INTERVAL3		+","+
						INTERVAL4		+","+
						OTHERCOND		+","+
						AUTOPSY		+","+
						FINDINGS		+","+
						ACTUALPLACE	+","+
						REFERREDTOME	+","+
						MECHECKBOX		+","+
						MEDATESIGNED	+","+
						MECASENUMBER	+","+
						MEDEATHDATE	+","+
						MEDEATHTIME	+","+
						MELICENSE		+","+
						ATTENDINGPHY	+","+
						DATESIGNED		+","+
						DRNAME		   	+","+
						DRSTREET		+","+
						DRCITY			+","+
						DRSTATE		+","+
						DRZIP			+","+
						DRPHONE		+","+
						DRFAX           + ", "+
						DRLICENSENO	+","+
						ACCIDENTSUICIDE+","+
						INJURYDATE		+","+
						INJURYTIME		+","+
						INJURYDESCR	+","+
						INJURYATWORK	+","+
						INJURYPLACE		+","+
						INJURYSTREET	+","+
						INJURYCITY		+","+
						INJURYZIPCODE	+","+
						INJURYSTATE		+","+
						INJURYCOUNTY	+","+
						INJURYAPTNO		+","+
						DR2NAME	+","+
						DRTITLE	+","+
						ATTENDFROM	+","+
						ATTENDTO	+","+
						ATTENDLAST	+","+
						PREGATDEATH		+","+
						PREG12MOS	+","+
						DRVIEWEDBODY+","+
						TOBACCO		+","+
						ORGANDONCON	+","+
						ORGANDONGRANT+","+
						TIMENOTIFIED +","+
						INJURYTRANSPORTATION		+","+
						DATECERTIFIED +","+
						DIAGNOSISDEFERRED +","+
						INJURYOCCURRED +", "+
                        DATEEMBALMED + ", " +
						DRJUSTICEOFTHEPEACE + ", " +
						DOCTOROCCUPATION + ", " +
						BIOPSYYN + ", " +
						XOPSYTOFINDCAUSE + ", " +
						OPERATIONTYPE + ", " +
						OPERATIONDATE + ", " +
						OPERATIONPRFRMD + ", " +
						NPCHECKBOX + ", " +
					    NPDATESIGNED + ", " +
					    NPLICENSENUMBER + ", " +
					    BODYFOUNDMORE24HR + ", " +
					    NOTIFICATIONOFEXAMINERREQUIRED + ", " +
					    HOSPICESTATUS + ", " +
					    INJURYONLYCITY + ", "+
					    CORONERSPHYSICIANTITLE +", "+
						CORONERSPHYSICIAN +", "+
						CORONERSPHYSICIANLICENSE +", "+
						CORONERSPHYSICIANDATESIGN +", "+
						ATTENDINGPHYSICIANTITLE +", "+
						ATTENDINGPHYSICIANLICENSE +", "+
						ATTENDINGPHYSICIANADDR +", "+
						ATTENDINGPHYSICIANCITY +", "+
						ATTENDINGPHYSICIANSTATE +", "+
						ATTENDINGPHYSICIANZIP +", "+
						ATTENDINGPHYSICIANPHONE +", "+
						REFERRALNUMBER +" "+
						"from vitalstats WHERE VitalsMasterKey = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		//AppLog.trace("DbVitalsMedicalPeer.getUpdateSql");
		try {
                    
			DbVitalsMedical vitals=(DbVitalsMedical)p;
                        
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE vitalstats SET "+
						CAUSE1		+"=?,"+
						CAUSE2		+"=?,"+
						CAUSE3		+"=?,"+
						CAUSE4		+"=?,"+
						INTERVAL1		+"=?,"+
						INTERVAL2		+"=?,"+
						INTERVAL3		+"=?,"+
						INTERVAL4		+"=?,"+
						OTHERCOND		+"=?,"+
						AUTOPSY		+"=?,"+
						FINDINGS		+"=?,"+
						ACTUALPLACE	+"=?,"+
						REFERREDTOME	+"=?,"+
						MECHECKBOX		+"=?,"+
						MEDATESIGNED	+"=?,"+
						MECASENUMBER	+"=?,"+
						MEDEATHDATE	+"=?,"+
						MEDEATHTIME	+"=?,"+
						MELICENSE		+"=?,"+
						ATTENDINGPHY  	+"=?,"+
						DATESIGNED		+"=?,"+
						DRNAME			+"=?,"+
						DRSTREET		+"=?,"+
						DRCITY			+"=?,"+
						DRSTATE		+"=?,"+
						DRZIP			+"=?,"+
						DRPHONE		+"=?,"+
						DRFAX + "=?,"+
						ACCIDENTSUICIDE+"=?,"+
						INJURYDATE		+"=?,"+
						INJURYTIME		+"=?,"+
						INJURYDESCR	+"=?,"+
						INJURYATWORK	+"=?,"+
						INJURYPLACE	+"=?,"+
						INJURYSTREET	+"=?,"+
						INJURYCITY   	+"=?,"+
						INJURYZIPCODE  	+"=?,"+
						INJURYSTATE		+"=?,"+
						INJURYCOUNTY	+"=?,"+
						INJURYAPTNO		+"=?,"+
						DRLICENSENO	+"=?,"+
						DR2NAME	+"=?,"+
						DRTITLE	+"=?,"+
						ATTENDFROM	+"=?,"+
						ATTENDTO	+"=?,"+
						ATTENDLAST	+"=?,"+
						PREGATDEATH	+"=?,"+
						PREG12MOS	+"=?,"+
						DRVIEWEDBODY+"=?,"+
						TOBACCO		+"=?,"+
						ORGANDONCON	+"=?,"+
						ORGANDONGRANT+"=?,"+
						TIMENOTIFIED +"=?,"+
						INJURYTRANSPORTATION	+"=?,"+
						DATECERTIFIED +"=?,"+
						DIAGNOSISDEFERRED +"=?,"+
						INJURYOCCURRED +"=?, "+
						DATEEMBALMED + "=?, " +
						DRJUSTICEOFTHEPEACE + "=?, " +
						DOCTOROCCUPATION + "=?, " + 
						BIOPSYYN + "=?, " +
						XOPSYTOFINDCAUSE + "=?, " +
						OPERATIONTYPE + "=?, " +
						OPERATIONDATE + "=?, " +
						OPERATIONPRFRMD + "=?, " +
						NPCHECKBOX + "=?, " +
					    NPDATESIGNED + "=?, " +
					    NPLICENSENUMBER + "=?, " +	
					    BODYFOUNDMORE24HR + "=?, " +	
					    NOTIFICATIONOFEXAMINERREQUIRED + "=?, " +	
					    HOSPICESTATUS + "=?, " +
					    INJURYONLYCITY + "=?, "+
					    CORONERSPHYSICIANTITLE +"=?, "+
						CORONERSPHYSICIAN +"=?, "+
						CORONERSPHYSICIANLICENSE +"=?, "+
						CORONERSPHYSICIANDATESIGN +"=?, "+
						ATTENDINGPHYSICIANTITLE +"=?, "+
						ATTENDINGPHYSICIANLICENSE +"=?, "+
						ATTENDINGPHYSICIANADDR +"=?, "+
						ATTENDINGPHYSICIANCITY +"=?, "+
						ATTENDINGPHYSICIANSTATE +"=?, "+
						ATTENDINGPHYSICIANZIP +"=?, "+
						ATTENDINGPHYSICIANPHONE +"=?, "+
						REFERRALNUMBER +"=? "+
						"WHERE VitalsMasterKey = ?"
			);		
			pstmt.setString(1, vitals.getCause1());	
			pstmt.setString(2, vitals.getCause2());
			pstmt.setString(3, vitals.getCause3());
			pstmt.setString(4, vitals.getCause4());
			pstmt.setString(5, vitals.getInterval1());
			pstmt.setString(6, vitals.getInterval2());
			pstmt.setString(7, vitals.getInterval3());
			pstmt.setString(8, vitals.getInterval4());
			pstmt.setString(9, vitals.getOtherConditions());
			pstmt.setString(10,vitals.getAutopsy());
			pstmt.setString(11,vitals.getFindings());
			pstmt.setString(12,vitals.getActualPlaceDeath());
			pstmt.setString(13,vitals.getReferredToME() );
			pstmt.setShort( 14,(short)vitals.getMeCheckBox() );
			pstmt.setString(15,vitals.getMeDateSigned() );
			pstmt.setString(16,vitals.getMeCaseNumber() );
			pstmt.setString(17,vitals.getMeDateDeath() );
			pstmt.setString(18,vitals.getMeTimeDeath() );
			pstmt.setString(19,vitals.getMeLicense() );
			pstmt.setString(20,vitals.getAttendingPhysician() );
			pstmt.setString(21,vitals.getDateSignedCertifier() );
			pstmt.setString(22,vitals.getDrName() );
			pstmt.setString(23,vitals.getDrStreet());
			pstmt.setString(24,vitals.getDrCity());
			pstmt.setString(25,vitals.getDrState());
			pstmt.setString(26,vitals.getDrZip());
			pstmt.setString(27,vitals.getDrPhone());
            pstmt.setString(28,vitals.getDrFax());
			pstmt.setString(29,vitals.getAccidentSuicide());
			pstmt.setString(30,vitals.getInjuryDate());
			pstmt.setString(31,vitals.getInjuryTime());
			pstmt.setString(32,vitals.getInjuryDescription());
			pstmt.setString(33,vitals.getInjuryAtWork());
			pstmt.setString(34,vitals.getInjuryPlace());
			pstmt.setString(35,vitals.getInjuryAddress());
			pstmt.setString(36,vitals.getInjuryCityState());
			pstmt.setString(37,vitals.getInjuryZipCode());
			pstmt.setString(38,vitals.getInjuryState());
			pstmt.setString(39,vitals.getInjuryCounty());
			pstmt.setString(40,vitals.getInjuryAptNo());
			pstmt.setString(41,vitals.getDrLicense());
			pstmt.setString(42,vitals.getDr2Name());
			pstmt.setString(43,vitals.getDrTitle());            
			if (vitals.getDrAttendDateFrom()!=null){
                            pstmt.setDate(44, new java.sql.Date(vitals.getDrAttendDateFrom().getTime()));
			} else { pstmt.setDate(44, null); }
                        
			if (vitals.getDrAttendDateTo()!=null){
                            pstmt.setDate(45, new java.sql.Date(vitals.getDrAttendDateTo().getTime()));
			} else { pstmt.setDate(45, null); }
                        
			if (vitals.getDrAttendDateLast()!=null){
                            pstmt.setDate(46, new java.sql.Date(vitals.getDrAttendDateLast().getTime()));
			} else { pstmt.setDate(46, null); }
                        
			pstmt.setString(47,vitals.getPregnantAtDeath());
			pstmt.setString(48,vitals.getPregnantLast12mos());
			pstmt.setString(49,vitals.getDoctorViewedBody());		
			pstmt.setString(50,vitals.getTobacco());
			pstmt.setString(51,vitals.getOrganDonationConsidered());
			pstmt.setString(52,vitals.getOrganDonationGranted());
			pstmt.setString(53,vitals.getTimeFdNotified());
			pstmt.setString(54,vitals.getInjuryTransportation());
			pstmt.setString(55,vitals.getDateCertified());
			pstmt.setString(56,vitals.getDiagnosisDeferred());
			pstmt.setString(57,vitals.getInjuryOccurred());
            pstmt.setString(58, vitals.getDateEmbalmed());
            pstmt.setString(59,vitals.getDrJusticeOfThePeace());
            pstmt.setString(60, vitals.getDoctorOccupation());
			pstmt.setString(61,vitals.getBiopsyYN());			
			pstmt.setString(62,vitals.getXopsyToFindCause());			
			pstmt.setString(63,vitals.getOperationType());
			pstmt.setString(64,vitals.getOperationDate());
			pstmt.setString(65,vitals.getOperationPerformed());	
			pstmt.setInt(66,vitals.getNpCheckBox());
			pstmt.setString(67,vitals.getNpDateSigned());
			pstmt.setString(68,vitals.getNpLicenseNumber());
			pstmt.setString(69,vitals.getBodyFoundMore24Hr());
			pstmt.setString(70,vitals.getNotificationOfExaminerRequired());
			pstmt.setString(71,vitals.getHospiceStatus());
			pstmt.setString(72,vitals.getInjuryCity());
			pstmt.setString(73,vitals.getCoronerSPhysicianTitle());
		    pstmt.setString(74,vitals.getCoronerSPhysician());
		    pstmt.setString(75,vitals.getCoronerSPhysicianLicense());
		    pstmt.setString(76,vitals.getCoronerSPhysicianDateSign());
		    pstmt.setString(77,vitals.getAttendingPhysicianTitle());
		    pstmt.setString(78,vitals.getAttendingPhysicianLicense());
		    pstmt.setString(79,vitals.getAttendingPhysicianAddr());
		    pstmt.setString(80,vitals.getAttendingPhysicianCity());
		    pstmt.setString(81,vitals.getAttendingPhysicianState());
		    pstmt.setString(82,vitals.getAttendingPhysicianZip());
		    pstmt.setString(83,vitals.getAttendingPhysicianPhone());
		    pstmt.setString(84,vitals.getReferralNumber());
		    pstmt.setInt   (85,vitals.getId());			
            
			return pstmt;
		} catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsMedicalPeer.Update:"+e.getMessage(),e);
		}
}
}
