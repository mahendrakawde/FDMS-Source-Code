package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbVeteranPeer supplies SQL for Persistent Class CreationDate: (12/22/2001
 * 6:13:09 PM)
 * 
 * @author:
 */
public class DbVeteranPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY = "VitalsMasterKey";

	static public final String CASE_ID = "VitalsMasterKey";

	static public final String FLAGVETNAME = "FlagVetName";

	static public final String FLAGARMYBOX = "FlagArmyBox";

	static public final String FLAGNAVYBOX = "FlagNavyBox";

	static public final String FLAGAIRFORCEBOX = "FlagAirForceBox";

	static public final String FLAGMARINEBOX = "FlagMarineBox";

	static public final String FLAGCOASTGUARDBOX = "FlagCoastGuardBox";

	static public final String FLAGOTHERBRANCHBOX = "FlagOtherBranchBox";

	static public final String FLAGSPANAMERBOX = "FlagSpanAmerBox";

	static public final String FLAGWWIBOX = "FlagWWIBox";

	static public final String FLAGWWIIBOX = "FlagWWIIBox";

	static public final String FLAGKOREANBOX = "FlagKoreanBox";

	static public final String FLAGAFTER55BOX = "FlagAfter55Box";

	static public final String FLAGVIETNAMBOX = "FlagVietnamBox";

	static public final String FLAGOTHERSERVBOX = "FlagOtherServBox";

	static public final String FLAGCOND1BOX = "FlagCondition1Box";

	static public final String FLAGCOND2BOX = "FlagCondition2Box";

	static public final String FLAGCOND3BOX = "FlagCondition3Box";

	static public final String FLAGCOND4BOX = "FlagCondition4Box";

	static public final String FLAGPERSONREC = "FlagPersonReceive";

	static public final String FLAGRECEIVREL = "FlagReceivRelation";

	static public final String FLAGRECEIVADDR1 = "FlagReceivAddress1";

	static public final String FLAGRECEIVADDR2 = "FlagReceivAddress2";

	static public final String FLAGVAFILENO = "FlagVAFileNumber";

	static public final String FLAGSOCSECNO = "FlagSocSecNumber";

	static public final String FLAGSERIALNO = "FlagSerialNumber";

	static public final String FLAGENLISTDATE = "FlagEnlistmentDate";

	static public final String FLAGDISCHARGEDATE = "FlagDischargeDate";

	static public final String FLAGBIRTHDATE = "FlagBirthDate";

	static public final String FLAGDEATHDATE = "FlagDeathDate";

	static public final String FLAGBURIALDATE = "FlagBurialDate";

	static public final String FLAGBURIALPLACE = "FlagBurialPlace";

	static public final String FLAGBURIALSTREET = "FlagBurialStreet";

	static public final String FLAGBURIALCITY = "FlagBurialCity";

	static public final String FLAGBURIALSTATE = "FlagBurialState";

	static public final String FLAGBURIALZIPCODE = "FlagBurialZipCode";

	static public final String FLAGREMARKS = "FlagRemarks";

	static public final String FLAGAPPADDR1 = "FlagAppAddress1";

	static public final String FLAGAPPADDR2 = "FlagAppAddress2";

	static public final String FLAGAPPRELATION = "FlagAppRelation";

	static public final String FLAGAPPDATE = "FlagAppDate";

	static public final String BEN1CLAIMNAME = "Ben1ClaimantName";

	static public final String BEN1CLAIMMADDR = "Ben1ClaimantStreet";

	static public final String BEN1CLAIMCITY = "Ben1ClaimCityStZip";

	static public final String BEN1PLACEBIRTH = "Ben1PlaceBirth";

	static public final String BEN1PLACEDEATH = "Ben1PlaceDeath";

	static public final String BEN1BURIALDATE = "Ben1BurialDate";

	static public final String BEN1DATEENTER1 = "Ben1DateEntered1";

	static public final String BEN1PLACEENTER1 = "Ben1PlaceEntered1";

	static public final String BEN1SERVICENO1 = "Ben1ServiceNumber1";

	static public final String BEN1SEPSRVCDATE1 = "Ben1SepSrvcDate1";

	static public final String BEN1SEPSRVCPLACE1 = "Ben1SepSrvcPlace1";

	static public final String BEN1GRADERANK1 = "Ben1GradeRank1";

	static public final String BEN1DATEENTER2 = "Ben1DateEntered2";

	static public final String BEN1PLACEENTER2 = "Ben1PlaceEntered2";

	static public final String BEN1SERVICENO2 = "Ben1ServiceNumber2";

	static public final String BEN1SEPSRVCDATE2 = "Ben1SepSrvcDate2";

	static public final String BEN1SEPSRVCPLACE2 = "Ben1SepSrvcPlace2";

	static public final String BEN1GRADERANK2 = "Ben1GradeRank2";

	static public final String BEN1DATEENTER3 = "Ben1DateEntered3";

	static public final String BEN1PLACEENTER3 = "Ben1PlaceEntered3";

	static public final String BEN1SERVICENO3 = "Ben1ServiceNumber3";

	static public final String BEN1SEPSRVCDATE3 = "Ben1SepSrvcDate3";

	static public final String BEN1SEPSRVCPLACE3 = "Ben1SepSrvcPlace3";

	static public final String BEN1GRADERANK3 = "Ben1GradeRank3";

	static public final String BEN1BOX8 = "Ben1Box8OtherName";

	static public final String BEN1BOX9YES = "Ben1Box9Yes";

	static public final String BEN1BOX9NO = "Ben1Box9No";

	static public final String BEN1BURIALPLACE = "Ben1BurialPlace";

	static public final String BEN1BOX11YES = "Ben1Box11Yes";

	static public final String BEN1BOX11NO = "Ben1Box11No";

	static public final String BEN1BOX12YES = "Ben1Box12Yes";

	static public final String BEN1BOX12NO = "Ben1Box12No";

	static public final String BEN1BOX13OTHER = "Ben1Box13OtherPers";

	static public final String BEN1BOX13FD = "Ben1Box13DueFD";

	static public final String BEN1BOX13CEMET = "Ben1Box13DueCemet";

	static public final String BEN1BOX13PAID = "Ben1Box13PaidClaim";

	static public final String BEN1BOX13NONE = "Ben1Box13None";

	static public final String BEN1BOX14NAME = "Ben1Box14NameAddr";

	static public final String BEN1BOX15 = "Ben1Box15TotBurExp";

	static public final String BEN1BOX16 = "Ben1Box16AmtPaid";

	static public final String BEN1BOX17 = "Ben1Box17WhoseFund";

	static public final String BEN1BOX18AYES = "Ben1Box18aYes";

	static public final String BEN1BOX18ANO = "Ben1Box18aNo";

	static public final String BEN1BOX18B = "Ben1Box18bAmtReimb";

	static public final String BEN1BOX18C = "Ben1Box18cSrcReimb";

	static public final String BEN1BOX19AYES = "Ben1Box19aYes";

	static public final String BEN1BOX19ANO = "Ben1Box19aNo";

	static public final String BEN1BOX19B = "Ben1Box19bAmount";

	static public final String BEN1BOX19C = "Ben1Box19cSource";

	static public final String BEN1BOX20YES = "Ben1Box20Yes";

	static public final String BEN1BOX20NO = "Ben1Box20No";

	static public final String BEN2BOX21 = "Ben2Box21";

	static public final String BEN2BOX22PLACE1 = "Ben2Box22Place1";

	static public final String BEN2BOX22PLACE2 = "Ben2Box22Place2";

	static public final String BEN2BOX23A = "Ben2Box23aCost";

	static public final String BEN2BOX23B = "Ben2Box23bDatePurc";

	static public final String BEN2BOX23C = "Ben2Box23cDatePay";

	static public final String BEN2BOX24AYES = "Ben2Box24aYes";

	static public final String BEN2BOX24ANO = "Ben2Box24aNo";

	static public final String BEN2BOX24B = "Ben2Box24bAmtPaid";

	static public final String BEN2BOX25 = "Ben2Box25WhoseFund";

	static public final String BEN2BOX26AYES = "Ben2Box26aYes";

	static public final String BEN2BOX26ANO = "Ben2Box26aNo";

	static public final String BEN2BOX26B = "Ben2Box26bAmount";

	static public final String BEN2BOX26C = "Ben2Box26cSource";

	static public final String BEN2BOX27AYES = "Ben2Box27aYes";

	static public final String BEN2COS27ANO = "Ben2Box27aNo";

	static public final String BEN2BOX27B = "Ben2Box27bAmount";

	static public final String BEN2BOX27C = "Ben2Box27cSource";

	static public final String BEN2BOX28B = "Ben2Box28bOfficPos";

	static public final String BEN2BOX29 = "Ben2Box29Agency";

	static public final String BEN2BOX30B = "Ben2Box30bName";

	static public final String BEN2BOX31 = "Ben2Box31Address";

	static public final String BEN2BOX32 = "Ben2Box32Date";

	static public final String BEN2BOX33 = "Ben2Box33Relation";

	/* headstone fields */

	static public final String HEADFIRSTNAME = "HeadFirstName";

	static public final String HEADMIDDLENAME = "HeadMiddleName";

	static public final String HEADLASTNAME = "HeadLastName";

	static public final String HEADSUFFIX = "HeadSuffix";

	static public final String HEADBOX2YES = "HeadBox2Yes";

	static public final String HEADBOX2NO = "HeadBox2No";

	static public final String HEADBRANCHARMY = "HeadBranchArmy";

	static public final String HEADBRANCHNAVY = "HeadBranchNavy";

	static public final String HEADBRANCHAIR = "HeadBranchAirForce";

	static public final String HEADBRANCHMARINES = "HeadBranchMarines";

	static public final String HEADBRANCHCG = "HeadBranchCoastGua";

	static public final String HEADBRANCHARMYAIR = "HeadBranchArmyAir";

	static public final String HEADBRANCHOTHER = "HeadBranchOther";

	static public final String HEADBRANCHSPECIFY = "HeadBranchSpecify";

	static public final String HEADVALORMOH = "HeadValorMOH";

	static public final String HEADVALORDSC = "HeadValorDSC";

	static public final String HEADVALORNC = "HeadValorNC";

	static public final String HEADVALORAFC = "HeadValorAFC";

	static public final String HEADVALORSS = "HeadValorSS";

	static public final String HEADVALORPH = "HeadValorPH";

	static public final String HEADWARWWI = "HeadWarWWI";

	static public final String HEADWARWWII = "HeadWarWWII";

	static public final String HEADWARKOREAN = "HeadWarKorean";

	static public final String HEADWARVIETNAM = "HeadWarVietnam";

	static public final String HEADWAROTHER = "HeadWarOther";

	static public final String HEADWARSPECIFY = "HeadWarSpecify";

	static public final String HEADSTONEBRONZE = "HeadStoneBronze";

	static public final String HEADSTONEGRANITE = "HeadStoneGranite";

	static public final String HEADSTONEUPRIGHT = "HeadStoneUpright";

	static public final String HEADSTONEFLAT = "HeadStoneFlat";

	static public final String HEADBELIEFCHRIST = "HeadBeliefChrist";

	static public final String HEADBELIEFBUDDHIST = "HeadBeliefBuddhist";

	static public final String HEADBELIEFJEW = "HeadBeliefJewish";

	static public final String HEADBELIEFOTHER = "HeadBeliefOther";

	static public final String HEADBELIEFSPECIFY = "HeadBeliefSpecify";

	static public final String HEADAPPNAME = "HeadAppNameAddress";

	static public final String HEADRELATIONSHIP = "HeadRelationship";

	static public final String HEADAPPPHONE = "HeadAppPhoneNumber";

	/* new fields headstone */
	static public final String HEADAPPFAX = "HeadAppFax";

	static public final String HEADAPPEMAIL = "HeadAppEmail";

	static public final String HEADREQUESTINITIAL = "HeadRequestInitial";

	static public final String HEADREQUESTSECOND = "HeadRequestSecond";

	static public final String HEADREQUESTCORRECTED = "HeadRequestCorrected";

	static public final String HEADVALOROTHER = "HeadValorOther";

	static public final String HEADVALOROTHERSPECIFY = "HeadValorOtherSpecify";

	static public final String HEADRELOTHER = "HeadRelOther";

	static public final String HEADRELOTHERSPECIFY = "HeadRelOtherSpecify";

	static public final String HEADGRAVEMARKED = "HeadGraveMarked";

	/* end new fields */
	static public final String HEADGRAVEID = "HeadGraveID";

	static public final String HEADGRAVESECTION = "HeadGraveSection";

	static public final String HEADGRAVENO = "HeadGraveNumber";

	static public final String HEADPRIVCEMETERY = "HeadPrivCemeteryID";

	static public final String HEADCONSIGNNAME = "HeadConsignNameAddr";

	static public final String HEADCEMETERYPHONE = "HeadCemeteryPhone";

	static public final String HEADCEMNAME = "HeadCemNameAddress";

	static public final String HEADREMARKS = "HeadRemarks";

	static public final String HEADHIGHESTRANK = "HeadHighestRank";

	static public final String STONENICHE = "StoneNiche";

	static public final String STONEUPRIGHT = "StoneUprightGranite";

	static public final String BEN1DAYPHONE = "Ben1DayPhone";

	static public final String BEN1NIGHTPHONE = "Ben1NightPhone";

	static public final String HEADBRANCHMM = "HeadBranchMerchMarine";

	static public final String HEADVALORBSM = "HeadValorBSM";

	static public final String HEADWARPG = "HeadWarPG";

	static public final String HEADBELIEFNONE = "HeadBeliefNone";

	/* New Flag Form */
	static public final String FLAGVETOTHERNAME = "FlagVetOtherName";

	static public final String FLAGSELSERVICEBOX = "FlagSelServiceBox";

	static public final String FLAGGULFBOX = "FlagGulfBox";

	static public final String FLAGCOND5BOX = "FlagCond5Box";

	static public final String FLAGOTHERVETSERVICE = "FlagOtherVetService";

	static public final String FLAGOTHERBRANCHOFSERVICE = "FlagOtherBranchOfService";
	
	static public final String FLAGDOCSHOWELIGIBILITY = "FlagDocShowEligibility";
	
	 /* #ticket : #5425 added by Bhavesh Shah */
	 static public final String BEN1DEATHOCCUR = "Ben1DeathOccur";
	 
	 static public final String BEN1DEATHOCCUROTHER = "Ben1DeathOccurOther";
	 
	 static public final String BEN1CLAIMANTEMAIL = "Ben1ClaimantEmail";
	 
	 static public final String BEN1EMPLOYERID = "Ben1EmployerID";
	

	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t,
			com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbVeteran rec = (DbVeteran) p;
			connection = t.getConnection();
			StringBuffer mystmtbuff = new StringBuffer("INSERT INTO veteransinfo (" + FLAGVETNAME + ","
					+ FLAGARMYBOX + "," + FLAGNAVYBOX + "," + FLAGAIRFORCEBOX + "," + FLAGMARINEBOX + ","
					+ FLAGCOASTGUARDBOX + "," + FLAGOTHERBRANCHBOX + "," + FLAGSPANAMERBOX + "," + FLAGWWIBOX
					+ "," + FLAGWWIIBOX + "," + FLAGKOREANBOX + "," + FLAGAFTER55BOX + "," + FLAGVIETNAMBOX
					+ "," + FLAGOTHERSERVBOX + "," + FLAGCOND1BOX + "," + FLAGCOND2BOX + "," + FLAGCOND3BOX
					+ "," + FLAGCOND4BOX + "," + FLAGPERSONREC + "," + FLAGRECEIVREL + "," + FLAGRECEIVADDR1
					+ "," + FLAGRECEIVADDR2 + "," + FLAGVAFILENO + "," + FLAGSOCSECNO + "," + FLAGSERIALNO
					+ "," + FLAGENLISTDATE + "," + FLAGDISCHARGEDATE + "," + FLAGBIRTHDATE + ","
					+ FLAGDEATHDATE + "," + FLAGBURIALDATE + "," + FLAGBURIALPLACE + "," + FLAGREMARKS + ","
					+ FLAGAPPADDR1 + "," + FLAGAPPADDR2 + "," + FLAGAPPRELATION + "," + FLAGAPPDATE + ","
					+ BEN1CLAIMNAME + "," + BEN1CLAIMMADDR + "," + BEN1CLAIMCITY + "," + BEN1PLACEBIRTH + ","
					+ BEN1PLACEDEATH + "," + BEN1BURIALDATE + "," + BEN1DATEENTER1 + "," + BEN1PLACEENTER1
					+ "," + BEN1SERVICENO1 + "," + BEN1SEPSRVCDATE1 + "," + BEN1SEPSRVCPLACE1 + ","
					+ BEN1GRADERANK1 + "," + BEN1DATEENTER2 + "," + BEN1PLACEENTER2 + "," + BEN1SERVICENO2
					+ "," + BEN1SEPSRVCDATE2 + "," + BEN1SEPSRVCPLACE2 + "," + BEN1GRADERANK2 + ","
					+ BEN1DATEENTER3 + "," + BEN1PLACEENTER3 + "," + BEN1SERVICENO3 + "," + BEN1SEPSRVCDATE3
					+ "," + BEN1SEPSRVCPLACE3 + "," + BEN1GRADERANK3 + "," + BEN1BOX8 + "," + BEN1BOX9YES
					+ "," + BEN1BOX9NO + "," + BEN1BURIALPLACE + "," + BEN1BOX11YES + "," + BEN1BOX11NO + ","
					+ BEN1BOX12YES + "," + BEN1BOX12NO + "," + BEN1BOX13OTHER + "," + BEN1BOX13FD + ","
					+ BEN1BOX13CEMET + "," + BEN1BOX13PAID + "," + BEN1BOX13NONE + "," + BEN1BOX14NAME + ","
					+ BEN1BOX15 + "," + BEN1BOX16 + "," + BEN1BOX17 + "," + BEN1BOX18AYES + ","
					+ BEN1BOX18ANO + "," + BEN1BOX18B + "," + BEN1BOX18C + "," + BEN1BOX19AYES + ","
					+ BEN1BOX19ANO + "," + BEN1BOX19B + "," + BEN1BOX19C + "," + BEN1BOX20YES + ","
					+ BEN1BOX20NO + "," + BEN2BOX21 + "," + BEN2BOX22PLACE1 + "," + BEN2BOX22PLACE2 + ","
					+ BEN2BOX23A + "," + BEN2BOX23B + "," + BEN2BOX23C + "," + BEN2BOX24AYES + ","
					+ BEN2BOX24ANO + "," + BEN2BOX24B + "," + BEN2BOX25 + "," + BEN2BOX26AYES + ","
					+ BEN2BOX26ANO + "," + BEN2BOX26B + "," + BEN2BOX26C + "," + BEN2BOX27AYES + ","
					+ BEN2COS27ANO + "," + BEN2BOX27B + "," + BEN2BOX27C + "," + BEN2BOX28B + "," + BEN2BOX29
					+ "," + BEN2BOX30B + "," + BEN2BOX31 + "," + BEN2BOX32 + "," + BEN2BOX33 + ","
					+ HEADFIRSTNAME + "," + HEADMIDDLENAME + "," + HEADLASTNAME + "," + HEADBOX2YES + ","
					+ HEADBOX2NO + "," + HEADBRANCHARMY + "," + HEADBRANCHNAVY + "," + HEADBRANCHAIR + ","
					+ HEADBRANCHMARINES + "," + HEADBRANCHCG + "," + HEADBRANCHARMYAIR + ","
					+ HEADBRANCHOTHER + "," + HEADBRANCHSPECIFY + "," + HEADVALORMOH + "," + HEADVALORDSC
					+ "," + HEADVALORNC + "," + HEADVALORAFC + "," + HEADVALORSS + "," + HEADVALORPH + ","
					+ HEADWARWWI + "," + HEADWARWWII + "," + HEADWARKOREAN + "," + HEADWARVIETNAM + ","
					+ HEADWAROTHER + "," + HEADWARSPECIFY + "," + HEADSTONEBRONZE + "," + HEADSTONEGRANITE
					+ "," + HEADSTONEUPRIGHT + "," + HEADSTONEFLAT + "," + HEADBELIEFCHRIST + ","
					+ HEADBELIEFBUDDHIST + "," + HEADBELIEFJEW + "," + HEADBELIEFOTHER + ","
					+ HEADBELIEFSPECIFY + "," + HEADAPPNAME + "," + HEADRELATIONSHIP + "," + HEADAPPPHONE
					+ "," + HEADGRAVEID + "," + HEADGRAVESECTION + "," + HEADGRAVENO + "," + HEADPRIVCEMETERY
					+ "," + HEADCONSIGNNAME + "," + HEADCEMETERYPHONE + "," + HEADCEMNAME + "," + HEADREMARKS
					+ "," + HEADHIGHESTRANK + "," + STONENICHE + "," + STONEUPRIGHT + "," + BEN1DAYPHONE
					+ "," + BEN1NIGHTPHONE + "," + HEADBRANCHMM + "," + HEADVALORBSM + "," + HEADWARPG + ","
					+ HEADBELIEFNONE + "," + IDENTITY + "," + FLAGVETOTHERNAME + "," + FLAGSELSERVICEBOX
					+ "," + FLAGGULFBOX + "," + FLAGCOND5BOX + "," + FLAGOTHERVETSERVICE + ","
					+ FLAGOTHERBRANCHOFSERVICE + "," + HEADSUFFIX + " ," + HEADAPPFAX + " ," + HEADAPPEMAIL
					+ ", " + HEADREQUESTINITIAL + " ," + HEADREQUESTSECOND + " ," + HEADREQUESTCORRECTED
					+ " ," + HEADVALOROTHER + " ," + HEADVALOROTHERSPECIFY + " ," + HEADRELOTHER + " ,"
					+ HEADRELOTHERSPECIFY + " ," + HEADGRAVEMARKED + " ," + FLAGBURIALSTREET  + " ," 
					+ FLAGBURIALCITY + " ," + FLAGBURIALSTATE + " ," + FLAGBURIALZIPCODE + " ," + FLAGDOCSHOWELIGIBILITY + " ,"
					+ BEN1DEATHOCCUR +"," + BEN1DEATHOCCUROTHER +", " +BEN1CLAIMANTEMAIL +","+ BEN1EMPLOYERID +") VALUES (?");
						
			for (int i = 0; i < 191; i++) {
				mystmtbuff.append(",?");
			}
			mystmtbuff.append(" )");
			java.sql.PreparedStatement pstmt = connection.prepareStatement(mystmtbuff.toString(), Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, rec.getFlag_VetName());
			pstmt.setShort(2, rec.getFlag_ArmyBox());
			pstmt.setShort(3, rec.getFlag_NavyBox());
			pstmt.setShort(4, rec.getFlag_AirForceBox());
			pstmt.setShort(5, rec.getFlag_MarineBox());
			pstmt.setShort(6, rec.getFlag_CoastGaurdBox());
			pstmt.setShort(7, rec.getFlag_OtherBranchBox());
			pstmt.setShort(8, rec.getFlag_SpanishAmerBox());
			pstmt.setShort(9, rec.getFlag_WWIBox());
			pstmt.setShort(10, rec.getFlag_WWIIBox());
			pstmt.setShort(11, rec.getFlag_KoreanBox());
			pstmt.setShort(12, rec.getFlag_After55Box());
			pstmt.setShort(13, rec.getFlag_VietnamBox());
			pstmt.setShort(14, rec.getFlag_OtherServiceBox());
			pstmt.setShort(15, rec.getFlag_Condition1Box());
			pstmt.setShort(16, rec.getFlag_Condition2Box());
			pstmt.setShort(17, rec.getFlag_Condition3Box());
			pstmt.setShort(18, rec.getFlag_Condition4Box());
			pstmt.setString(19, rec.getFlag_PersonReceiveFlag());
			pstmt.setString(20, rec.getFlag_ReceiveRelation());
			pstmt.setString(21, rec.getFlag_ReceiveAddr1());
			pstmt.setString(22, rec.getFlag_ReceiveAddr2());
			pstmt.setString(23, rec.getFlag_VAfileNumber());
			pstmt.setString(24, rec.getFlag_SocSecNo());
			pstmt.setString(25, rec.getFlag_SerialNo());
			pstmt.setString(26, rec.getFlag_EnlistmentDate());
			pstmt.setString(27, rec.getFlag_DischargeDate());
			pstmt.setString(28, rec.getFlag_BirthDate());
			pstmt.setString(29, rec.getFlag_DeathDate());
			pstmt.setString(30, rec.getFlag_BurialDate());
			pstmt.setString(31, rec.getFlag_BurialPlace());
			pstmt.setString(32, rec.getFlag_Remarks());
			pstmt.setString(33, rec.getFlag_ApplicantAddr1());
			pstmt.setString(34, rec.getFlag_ApplicantAddr2());
			pstmt.setString(35, rec.getFlag_ApplicantRelation());
			pstmt.setString(36, rec.getFlag_ApplicantDate());
			pstmt.setString(37, rec.getBen1_ClaimantName());
			pstmt.setString(38, rec.getBen1_ClaimantStreet());
			pstmt.setString(39, rec.getBen1_ClaimantCityStZip());
			pstmt.setString(40, rec.getBen1_PlaceBirth());
			pstmt.setString(41, rec.getBen1_PlaceDeath());
			pstmt.setString(42, rec.getBen1_BurialDate());
			pstmt.setString(43, rec.getBen1_DateEntered1());
			pstmt.setString(44, rec.getBen1_PlaceEntered1());
			pstmt.setString(45, rec.getBen1_ServiceNo1());
			pstmt.setString(46, rec.getBen1_SepSrvcDate1());
			pstmt.setString(47, rec.getBen1_SepSrvcPlace1());
			pstmt.setString(48, rec.getBen1_GradeRank1());
			pstmt.setString(49, rec.getBen1_DateEntered2());
			pstmt.setString(50, rec.getBen1_PlaceEntered2());
			pstmt.setString(51, rec.getBen1_ServiceNo2());
			pstmt.setString(52, rec.getBen1_SepSrvcDate2());
			pstmt.setString(53, rec.getBen1_SepSrvcPlace2());
			pstmt.setString(54, rec.getBen1_GradeRank2());
			pstmt.setString(55, rec.getBen1_DateEntered3());
			pstmt.setString(56, rec.getBen1_PlaceEntered3());
			pstmt.setString(57, rec.getBen1_ServiceNo3());
			pstmt.setString(58, rec.getBen1_SepSrvcDate3());
			pstmt.setString(59, rec.getBen1_SepSrvcPlace3());
			pstmt.setString(60, rec.getBen1_GradeRank3());
			pstmt.setString(61, rec.getBen1_Box8OtherName());
			pstmt.setShort(62, rec.getBen1_Box9Yes());
			pstmt.setShort(63, rec.getBen1_Box9No());
			pstmt.setString(64, rec.getBen1_Box10BurialPlace());
			pstmt.setShort(65, rec.getBen1_Box11Yes());
			pstmt.setShort(66, rec.getBen1_Box11No());
			pstmt.setShort(67, rec.getBen1_Box12Yes());
			pstmt.setShort(68, rec.getBen1_Box12No());
			pstmt.setShort(69, rec.getBen1_Box13OtherPerson());
			pstmt.setShort(70, rec.getBen1_Box13DueFD());
			pstmt.setShort(71, rec.getBen1_Box13DueCemetery());
			pstmt.setShort(72, rec.getBen1_Box13PaidClaimant());
			pstmt.setShort(73, rec.getBen1_Box13None());
			pstmt.setString(74, rec.getBen1_Box14NameAddr());
			pstmt.setInt(75, rec.getBen1_Box16TotBurialExp());
			pstmt.setInt(76, rec.getBen1_Box16AmountPaid());
			pstmt.setString(77, rec.getBen1_Box17WhoseFunds());
			pstmt.setShort(78, rec.getBen1_Box18aYes());
			pstmt.setShort(79, rec.getBen1_Box18aNo());
			pstmt.setInt(80, rec.getBen1_Box18bAmtReimb());
			pstmt.setString(81, rec.getBen1_Box18cSourceReimb());
			pstmt.setShort(82, rec.getBen1_Box19aYes());
			pstmt.setShort(83, rec.getBen1_Box19aNo());
			pstmt.setInt(84, rec.getBen1_Box19bAmount());
			pstmt.setString(85, rec.getBen1_Box19cSource());
			pstmt.setShort(86, rec.getBen1_Box20Yes());
			pstmt.setShort(87, rec.getBen1_Box20No());
			pstmt.setString(88, rec.getBen2_Box21());
			pstmt.setString(89, rec.getBen2_Box22Place1());
			pstmt.setString(90, rec.getBen2_Box22Place2());
			pstmt.setInt(91, rec.getBen2_Box23aCost());
			pstmt.setString(92, rec.getBen2_Box23bDatePurch());
			pstmt.setString(93, rec.getBen2_Box23cDatePayment());
			pstmt.setShort(94, rec.getBen2_Box24aYes());
			pstmt.setShort(95, rec.getBen2_Box24aNo());
			pstmt.setInt(96, rec.getBen2_Box24bAmtPaid());
			pstmt.setString(97, rec.getBen2_Box25WhoseFunds());
			pstmt.setShort(98, rec.getBen2_Box26aYes());
			pstmt.setShort(99, rec.getBen2_Box26aNo());
			pstmt.setInt(100, rec.getBen2_Box26bAmount());
			pstmt.setString(101, rec.getBen2_Box26cSource());
			pstmt.setShort(102, rec.getBen2_Box27aYes());
			pstmt.setShort(103, rec.getBen2_Box27aNo());
			pstmt.setInt(104, rec.getBen2_Box27bAmount());
			pstmt.setString(105, rec.getBen2_Box27cSource());
			pstmt.setString(106, rec.getBen2_Box28bOfficialPos());
			pstmt.setString(107, rec.getBen2_Box29Agency());
			pstmt.setString(108, rec.getBen2_Box30bName());
			pstmt.setString(109, rec.getBen2_Box31Address());
			pstmt.setString(110, rec.getBen2_Box32date());
			pstmt.setString(111, rec.getBen2_Box33Relationship());
			pstmt.setString(112, rec.getHead_Fname());
			pstmt.setString(113, rec.getHead_Mname());
			pstmt.setString(114, rec.getHead_Lname());
			pstmt.setShort(115, rec.getHead_Box2Yes());
			pstmt.setShort(116, rec.getHead_Box2No());
			pstmt.setShort(117, rec.getHead_BranchAR());
			pstmt.setShort(118, rec.getHead_BranchNA());
			pstmt.setShort(119, rec.getHead_BranchAF());
			pstmt.setShort(120, rec.getHead_BranchMC());
			pstmt.setShort(121, rec.getHead_BranchCG());
			pstmt.setShort(122, rec.getHead_BranchAC());
			pstmt.setShort(123, rec.getHead_BranchOther());
			pstmt.setString(124, rec.getHead_BranchSpecify());
			pstmt.setShort(125, rec.getHead_ValorMOH());
			pstmt.setShort(126, rec.getHead_ValorDSC());
			pstmt.setShort(127, rec.getHead_ValorNC());
			pstmt.setShort(128, rec.getHead_ValorAFC());
			pstmt.setShort(129, rec.getHead_ValorSS());
			pstmt.setShort(130, rec.getHead_ValorPH());
			pstmt.setShort(131, rec.getHead_WarWWI());
			pstmt.setShort(132, rec.getHead_WarWWII());
			pstmt.setShort(133, rec.getHead_WarKO());
			pstmt.setShort(134, rec.getHead_WarVN());
			pstmt.setShort(135, rec.getHead_WarOther());
			pstmt.setString(136, rec.getHead_WarSpecify());
			pstmt.setShort(137, rec.getHead_StoneB());
			pstmt.setShort(138, rec.getHead_StoneG());
			pstmt.setShort(139, rec.getHead_StoneU());
			pstmt.setShort(140, rec.getHead_StoneF());
			pstmt.setShort(141, rec.getHead_Belief1());
			pstmt.setShort(142, rec.getHead_Belief2());
			pstmt.setShort(143, rec.getHead_Belief3());
			pstmt.setShort(144, rec.getHead_Belief4());
			pstmt.setString(145, rec.getHead_BeliefSpecify());
			pstmt.setString(146, rec.getHead_ApplicantNameAddr());
			pstmt.setString(147, rec.getHead_Relationship());
			pstmt.setString(148, rec.getHead_ApplicantPhone());
			pstmt.setString(149, rec.getHead_GraveID());
			pstmt.setString(150, rec.getHead_GraveSection());
			pstmt.setString(151, rec.getHead_GraveNumber());
			pstmt.setString(152, rec.getHead_PrivCemeterID());
			pstmt.setString(153, rec.getHead_ConsigneeNameAddr());
			pstmt.setString(154, rec.getHead_CemPhone());
			pstmt.setString(155, rec.getHead_CemNameAddr());
			pstmt.setString(156, rec.getHead_Remarks());
			pstmt.setString(157, rec.getHead_HighestRank());
			pstmt.setShort(158, rec.getHead_StoneZ());
			pstmt.setShort(159, rec.getHead_StoneV());
			pstmt.setString(160, rec.getBen1_dayPhone());
			pstmt.setString(161, rec.getBen1_nightPhone());
			pstmt.setShort(162, rec.getHead_BranchMM());
			pstmt.setShort(163, rec.getHead_ValorBSM());
			pstmt.setShort(164, rec.getHead_WarPG());
			pstmt.setShort(165, rec.getHead_BeliefNone());
			pstmt.setInt(166, rec.getLMilitaryMainKey());

			pstmt.setString(167, rec.getFlag_VetOtherName());
			pstmt.setShort(168, rec.getFlag_SelReserveBox());
			pstmt.setShort(169, rec.getFlag_GulfBox());
			pstmt.setShort(170, rec.getFlag_Condition5Box());
			pstmt.setString(171, rec.getFlag_OtherBranchOfService());
			pstmt.setString(172, rec.getFlag_OtherVetService());
			pstmt.setString(173, rec.getHead_Suffix());
			pstmt.setString(174, rec.getHead_ApplicantFax());
			pstmt.setString(175, rec.getHead_ApplicantEmail());
			pstmt.setShort(176, rec.getHead_RequestInitial());
			pstmt.setShort(177, rec.getHead_RequestSecond());
			pstmt.setShort(178, rec.getHead_RequestCorrected());
			pstmt.setShort(179, rec.getHead_ValorOther());
			pstmt.setString(180, rec.getHead_ValorOtherSpecify());
			pstmt.setShort(181, rec.getHead_RelOther());
			pstmt.setString(182, rec.getHead_RelOtherSpecify());
			pstmt.setShort(183, rec.getHead_GraveMarked());
			pstmt.setString(184, rec.getFlag_BurialStreet());
			pstmt.setString(185, rec.getFlag_BurialCity());
			pstmt.setString(186, rec.getFlag_BurialState());
			pstmt.setString(187, rec.getFlag_BurialZipCode());
			pstmt.setString(188, rec.getFlag_DocShowEligibility());
			
			pstmt.setString(189, rec.getDeathPlace());
			pstmt.setString(190, rec.getDeathPlaceOther());
			pstmt.setString(191, rec.getEmail());
			pstmt.setString(192, rec.getBen1EmplopyerID());
			
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVeteranPeer.Insert:" + e.getMessage(), e);
		}
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t,
			com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.PreparedStatement pstmt = null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM veteransinfo WHERE VitalsMasterKey=?");
			pstmt.setInt(1, p.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVeteransPeer.Remove", e);
		}
	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT * from veteransinfo WHERE VitalsMasterKey = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t,
			com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbVeteran rec = (DbVeteran) p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement("UPDATE veteransinfo SET "
					+ FLAGVETNAME + "=?," + FLAGARMYBOX + "=?," + FLAGNAVYBOX + "=?," + FLAGAIRFORCEBOX
					+ "=?," + FLAGMARINEBOX + "=?," + FLAGCOASTGUARDBOX + "=?," + FLAGOTHERBRANCHBOX + "=?,"
					+ FLAGSPANAMERBOX + "=?," + FLAGWWIBOX + "=?," + FLAGWWIIBOX + "=?," + FLAGKOREANBOX
					+ "=?," + FLAGAFTER55BOX + "=?," + FLAGVIETNAMBOX + "=?," + FLAGOTHERSERVBOX + "=?,"
					+ FLAGCOND1BOX + "=?," + FLAGCOND2BOX + "=?," + FLAGCOND3BOX + "=?," + FLAGCOND4BOX
					+ "=?," + FLAGPERSONREC + "=?," + FLAGRECEIVREL + "=?," + FLAGRECEIVADDR1 + "=?,"
					+ FLAGRECEIVADDR2 + "=?," + FLAGVAFILENO + "=?," + FLAGSOCSECNO + "=?," + FLAGSERIALNO
					+ "=?," + FLAGENLISTDATE + "=?," + FLAGDISCHARGEDATE + "=?," + FLAGBIRTHDATE + "=?,"
					+ FLAGDEATHDATE + "=?," + FLAGBURIALDATE + "=?," + FLAGBURIALPLACE + "=?," + FLAGREMARKS
					+ "=?," + FLAGAPPADDR1 + "=?," + FLAGAPPADDR2 + "=?," + FLAGAPPRELATION + "=?,"
					+ FLAGAPPDATE + "=?," + BEN1CLAIMNAME + "=?," + BEN1CLAIMMADDR + "=?," + BEN1CLAIMCITY
					+ "=?," + BEN1PLACEBIRTH + "=?," + BEN1PLACEDEATH + "=?," + BEN1BURIALDATE + "=?,"
					+ BEN1DATEENTER1 + "=?," + BEN1PLACEENTER1 + "=?," + BEN1SERVICENO1 + "=?,"
					+ BEN1SEPSRVCDATE1 + "=?," + BEN1SEPSRVCPLACE1 + "=?," + BEN1GRADERANK1 + "=?,"
					+ BEN1DATEENTER2 + "=?," + BEN1PLACEENTER2 + "=?," + BEN1SERVICENO2 + "=?,"
					+ BEN1SEPSRVCDATE2 + "=?," + BEN1SEPSRVCPLACE2 + "=?," + BEN1GRADERANK2 + "=?,"
					+ BEN1DATEENTER3 + "=?," + BEN1PLACEENTER3 + "=?," + BEN1SERVICENO3 + "=?,"
					+ BEN1SEPSRVCDATE3 + "=?," + BEN1SEPSRVCPLACE3 + "=?," + BEN1GRADERANK3 + "=?,"
					+ BEN1BOX8 + "=?," + BEN1BOX9YES + "=?," + BEN1BOX9NO + "=?," + BEN1BURIALPLACE + "=?,"
					+ BEN1BOX11YES + "=?," + BEN1BOX11NO + "=?," + BEN1BOX12YES + "=?," + BEN1BOX12NO + "=?,"
					+ BEN1BOX13OTHER + "=?," + BEN1BOX13FD + "=?," + BEN1BOX13CEMET + "=?," + BEN1BOX13PAID
					+ "=?," + BEN1BOX13NONE + "=?," + BEN1BOX14NAME + "=?," + BEN1BOX15 + "=?," + BEN1BOX16
					+ "=?," + BEN1BOX17 + "=?," + BEN1BOX18AYES + "=?," + BEN1BOX18ANO + "=?," + BEN1BOX18B
					+ "=?," + BEN1BOX18C + "=?," + BEN1BOX19AYES + "=?," + BEN1BOX19ANO + "=?," + BEN1BOX19B
					+ "=?," + BEN1BOX19C + "=?," + BEN1BOX20YES + "=?," + BEN1BOX20NO + "=?," + BEN2BOX21
					+ "=?," + BEN2BOX22PLACE1 + "=?," + BEN2BOX22PLACE2 + "=?," + BEN2BOX23A + "=?,"
					+ BEN2BOX23B + "=?," + BEN2BOX23C + "=?," + BEN2BOX24AYES + "=?," + BEN2BOX24ANO + "=?,"
					+ BEN2BOX24B + "=?," + BEN2BOX25 + "=?," + BEN2BOX26AYES + "=?," + BEN2BOX26ANO + "=?,"
					+ BEN2BOX26B + "=?," + BEN2BOX26C + "=?," + BEN2BOX27AYES + "=?," + BEN2COS27ANO + "=?,"
					+ BEN2BOX27B + "=?," + BEN2BOX27C + "=?," + BEN2BOX28B + "=?," + BEN2BOX29 + "=?,"
					+ BEN2BOX30B + "=?," + BEN2BOX31 + "=?," + BEN2BOX32 + "=?," + BEN2BOX33 + "=?,"
					+ HEADFIRSTNAME + "=?," + HEADMIDDLENAME + "=?," + HEADLASTNAME + "=?," + HEADBOX2YES
					+ "=?," + HEADBOX2NO + "=?," + HEADBRANCHARMY + "=?," + HEADBRANCHNAVY + "=?,"
					+ HEADBRANCHAIR + "=?," + HEADBRANCHMARINES + "=?," + HEADBRANCHCG + "=?,"
					+ HEADBRANCHARMYAIR + "=?," + HEADBRANCHOTHER + "=?," + HEADBRANCHSPECIFY + "=?,"
					+ HEADVALORMOH + "=?," + HEADVALORDSC + "=?," + HEADVALORNC + "=?," + HEADVALORAFC
					+ "=?," + HEADVALORSS + "=?," + HEADVALORPH + "=?," + HEADWARWWI + "=?," + HEADWARWWII
					+ "=?," + HEADWARKOREAN + "=?," + HEADWARVIETNAM + "=?," + HEADWAROTHER + "=?,"
					+ HEADWARSPECIFY + "=?," + HEADSTONEBRONZE + "=?," + HEADSTONEGRANITE + "=?,"
					+ HEADSTONEUPRIGHT + "=?," + HEADSTONEFLAT + "=?," + HEADBELIEFCHRIST + "=?,"
					+ HEADBELIEFBUDDHIST + "=?," + HEADBELIEFJEW + "=?," + HEADBELIEFOTHER + "=?,"
					+ HEADBELIEFSPECIFY + "=?," + HEADAPPNAME + "=?," + HEADRELATIONSHIP + "=?,"
					+ HEADAPPPHONE + "=?," + HEADGRAVEID + "=?," + HEADGRAVESECTION + "=?," + HEADGRAVENO
					+ "=?," + HEADPRIVCEMETERY + "=?," + HEADCONSIGNNAME + "=?," + HEADCEMETERYPHONE + "=?,"
					+ HEADCEMNAME + "=?," + HEADREMARKS + "=?," + HEADHIGHESTRANK + "=?," + STONENICHE
					+ "=?," + STONEUPRIGHT + "=?," + BEN1DAYPHONE + "=?," + BEN1NIGHTPHONE + "=?,"
					+ HEADBRANCHMM + "=?," + HEADVALORBSM + "=?," + HEADWARPG + "=?," + HEADBELIEFNONE
					+ "=? ," + FLAGVETOTHERNAME + "=? ," + FLAGSELSERVICEBOX + "=? ," + FLAGGULFBOX + "=? ,"
					+ FLAGCOND5BOX + "=? ," + FLAGOTHERVETSERVICE + "=? ," + FLAGOTHERBRANCHOFSERVICE
					+ "=? ," + HEADSUFFIX + "=? ," + HEADAPPFAX + "=? ," + HEADAPPEMAIL + "=? ,"
					+ HEADREQUESTINITIAL + "=? ," + HEADREQUESTSECOND + "=? ," + HEADREQUESTCORRECTED
					+ "=? ," + HEADVALOROTHER + "=? ," + HEADVALOROTHERSPECIFY + "=? ," + HEADRELOTHER
					+ "=? ," + HEADRELOTHERSPECIFY + "=? ," + HEADGRAVEMARKED + "=? " 
					+ " ," + FLAGBURIALSTREET   + "=? "
					+ " ," + FLAGBURIALCITY + "=? "
					+ " ," + FLAGBURIALSTATE + "=? "
					+ " ," + FLAGBURIALZIPCODE + "=? "
					+ " ," + FLAGDOCSHOWELIGIBILITY + "=? "
					+ " ," + BEN1DEATHOCCUR + "=? " 
					+ " ," + BEN1DEATHOCCUROTHER + "=? " 
					+ " ," + BEN1CLAIMANTEMAIL + "=? " 
					+ " ," + BEN1EMPLOYERID + "=? "
					+ " WHERE " + IDENTITY + " = ?");
			
			pstmt.setString(1, rec.getFlag_VetName());
			pstmt.setShort(2, rec.getFlag_ArmyBox());
			pstmt.setShort(3, rec.getFlag_NavyBox());
			pstmt.setShort(4, rec.getFlag_AirForceBox());
			pstmt.setShort(5, rec.getFlag_MarineBox());
			pstmt.setShort(6, rec.getFlag_CoastGaurdBox());
			pstmt.setShort(7, rec.getFlag_OtherBranchBox());
			pstmt.setShort(8, rec.getFlag_SpanishAmerBox());
			pstmt.setShort(9, rec.getFlag_WWIBox());
			pstmt.setShort(10, rec.getFlag_WWIIBox());
			pstmt.setShort(11, rec.getFlag_KoreanBox());
			pstmt.setShort(12, rec.getFlag_After55Box());
			pstmt.setShort(13, rec.getFlag_VietnamBox());
			pstmt.setShort(14, rec.getFlag_OtherServiceBox());
			pstmt.setShort(15, rec.getFlag_Condition1Box());
			pstmt.setShort(16, rec.getFlag_Condition2Box());
			pstmt.setShort(17, rec.getFlag_Condition3Box());
			pstmt.setShort(18, rec.getFlag_Condition4Box());
			pstmt.setString(19, rec.getFlag_PersonReceiveFlag());
			pstmt.setString(20, rec.getFlag_ReceiveRelation());
			pstmt.setString(21, rec.getFlag_ReceiveAddr1());
			pstmt.setString(22, rec.getFlag_ReceiveAddr2());
			pstmt.setString(23, rec.getFlag_VAfileNumber());
			pstmt.setString(24, rec.getFlag_SocSecNo());
			pstmt.setString(25, rec.getFlag_SerialNo());
			pstmt.setString(26, rec.getFlag_EnlistmentDate());
			pstmt.setString(27, rec.getFlag_DischargeDate());
			pstmt.setString(28, rec.getFlag_BirthDate());
			pstmt.setString(29, rec.getFlag_DeathDate());
			pstmt.setString(30, rec.getFlag_BurialDate());
			pstmt.setString(31, rec.getFlag_BurialPlace());
			pstmt.setString(32, rec.getFlag_Remarks());
			pstmt.setString(33, rec.getFlag_ApplicantAddr1());
			pstmt.setString(34, rec.getFlag_ApplicantAddr2());
			pstmt.setString(35, rec.getFlag_ApplicantRelation());
			pstmt.setString(36, rec.getFlag_ApplicantDate());
			pstmt.setString(37, rec.getBen1_ClaimantName());
			pstmt.setString(38, rec.getBen1_ClaimantStreet());
			pstmt.setString(39, rec.getBen1_ClaimantCityStZip());
			pstmt.setString(40, rec.getBen1_PlaceBirth());
			pstmt.setString(41, rec.getBen1_PlaceDeath());
			pstmt.setString(42, rec.getBen1_BurialDate());
			pstmt.setString(43, rec.getBen1_DateEntered1());
			pstmt.setString(44, rec.getBen1_PlaceEntered1());
			pstmt.setString(45, rec.getBen1_ServiceNo1());
			pstmt.setString(46, rec.getBen1_SepSrvcDate1());
			pstmt.setString(47, rec.getBen1_SepSrvcPlace1());
			pstmt.setString(48, rec.getBen1_GradeRank1());
			pstmt.setString(49, rec.getBen1_DateEntered2());
			pstmt.setString(50, rec.getBen1_PlaceEntered2());
			pstmt.setString(51, rec.getBen1_ServiceNo2());
			pstmt.setString(52, rec.getBen1_SepSrvcDate2());
			pstmt.setString(53, rec.getBen1_SepSrvcPlace2());
			pstmt.setString(54, rec.getBen1_GradeRank2());
			pstmt.setString(55, rec.getBen1_DateEntered3());
			pstmt.setString(56, rec.getBen1_PlaceEntered3());
			pstmt.setString(57, rec.getBen1_ServiceNo3());
			pstmt.setString(58, rec.getBen1_SepSrvcDate3());
			pstmt.setString(59, rec.getBen1_SepSrvcPlace3());
			pstmt.setString(60, rec.getBen1_GradeRank3());
			pstmt.setString(61, rec.getBen1_Box8OtherName());
			pstmt.setShort(62, rec.getBen1_Box9Yes());
			pstmt.setShort(63, rec.getBen1_Box9No());
			pstmt.setString(64, rec.getBen1_Box10BurialPlace());
			pstmt.setShort(65, rec.getBen1_Box11Yes());
			pstmt.setShort(66, rec.getBen1_Box11No());
			pstmt.setShort(67, rec.getBen1_Box12Yes());
			pstmt.setShort(68, rec.getBen1_Box12No());
			pstmt.setShort(69, rec.getBen1_Box13OtherPerson());
			pstmt.setShort(70, rec.getBen1_Box13DueFD());
			pstmt.setShort(71, rec.getBen1_Box13DueCemetery());
			pstmt.setShort(72, rec.getBen1_Box13PaidClaimant());
			pstmt.setShort(73, rec.getBen1_Box13None());
			pstmt.setString(74, rec.getBen1_Box14NameAddr());
			pstmt.setInt(75, rec.getBen1_Box16TotBurialExp());
			pstmt.setInt(76, rec.getBen1_Box16AmountPaid());
			pstmt.setString(77, rec.getBen1_Box17WhoseFunds());
			pstmt.setShort(78, rec.getBen1_Box18aYes());
			pstmt.setShort(79, rec.getBen1_Box18aNo());
			pstmt.setInt(80, rec.getBen1_Box18bAmtReimb());
			pstmt.setString(81, rec.getBen1_Box18cSourceReimb());
			pstmt.setShort(82, rec.getBen1_Box19aYes());
			pstmt.setShort(83, rec.getBen1_Box19aNo());
			pstmt.setInt(84, rec.getBen1_Box19bAmount());
			pstmt.setString(85, rec.getBen1_Box19cSource());
			pstmt.setShort(86, rec.getBen1_Box20Yes());
			pstmt.setShort(87, rec.getBen1_Box20No());
			pstmt.setString(88, rec.getBen2_Box21());
			pstmt.setString(89, rec.getBen2_Box22Place1());
			pstmt.setString(90, rec.getBen2_Box22Place2());
			pstmt.setInt(91, rec.getBen2_Box23aCost());
			pstmt.setString(92, rec.getBen2_Box23bDatePurch());
			pstmt.setString(93, rec.getBen2_Box23cDatePayment());
			pstmt.setShort(94, rec.getBen2_Box24aYes());
			pstmt.setShort(95, rec.getBen2_Box24aNo());
			pstmt.setInt(96, rec.getBen2_Box24bAmtPaid());
			pstmt.setString(97, rec.getBen2_Box25WhoseFunds());
			pstmt.setShort(98, rec.getBen2_Box26aYes());
			pstmt.setShort(99, rec.getBen2_Box26aNo());
			pstmt.setInt(100, rec.getBen2_Box26bAmount());
			pstmt.setString(101, rec.getBen2_Box26cSource());
			pstmt.setShort(102, rec.getBen2_Box27aYes());
			pstmt.setShort(103, rec.getBen2_Box27aNo());
			pstmt.setInt(104, rec.getBen2_Box27bAmount());
			pstmt.setString(105, rec.getBen2_Box27cSource());
			pstmt.setString(106, rec.getBen2_Box28bOfficialPos());
			pstmt.setString(107, rec.getBen2_Box29Agency());
			pstmt.setString(108, rec.getBen2_Box30bName());
			pstmt.setString(109, rec.getBen2_Box31Address());
			pstmt.setString(110, rec.getBen2_Box32date());
			pstmt.setString(111, rec.getBen2_Box33Relationship());
			pstmt.setString(112, rec.getHead_Fname());
			pstmt.setString(113, rec.getHead_Mname());
			pstmt.setString(114, rec.getHead_Lname());
			pstmt.setShort(115, rec.getHead_Box2Yes());
			pstmt.setShort(116, rec.getHead_Box2No());
			pstmt.setShort(117, rec.getHead_BranchAR());
			pstmt.setShort(118, rec.getHead_BranchNA());
			pstmt.setShort(119, rec.getHead_BranchAF());
			pstmt.setShort(120, rec.getHead_BranchMC());
			pstmt.setShort(121, rec.getHead_BranchCG());
			pstmt.setShort(122, rec.getHead_BranchAC());
			pstmt.setShort(123, rec.getHead_BranchOther());
			pstmt.setString(124, rec.getHead_BranchSpecify());
			pstmt.setShort(125, rec.getHead_ValorMOH());
			pstmt.setShort(126, rec.getHead_ValorDSC());
			pstmt.setShort(127, rec.getHead_ValorNC());
			pstmt.setShort(128, rec.getHead_ValorAFC());
			pstmt.setShort(129, rec.getHead_ValorSS());
			pstmt.setShort(130, rec.getHead_ValorPH());
			pstmt.setShort(131, rec.getHead_WarWWI());
			pstmt.setShort(132, rec.getHead_WarWWII());
			pstmt.setShort(133, rec.getHead_WarKO());
			pstmt.setShort(134, rec.getHead_WarVN());
			pstmt.setShort(135, rec.getHead_WarOther());
			pstmt.setString(136, rec.getHead_WarSpecify());
			pstmt.setShort(137, rec.getHead_StoneB());
			pstmt.setShort(138, rec.getHead_StoneG());
			pstmt.setShort(139, rec.getHead_StoneU());
			pstmt.setShort(140, rec.getHead_StoneF());
			pstmt.setShort(141, rec.getHead_Belief1());
			pstmt.setShort(142, rec.getHead_Belief2());
			pstmt.setShort(143, rec.getHead_Belief3());
			pstmt.setShort(144, rec.getHead_Belief4());
			pstmt.setString(145, rec.getHead_BeliefSpecify());
			pstmt.setString(146, rec.getHead_ApplicantNameAddr());
			pstmt.setString(147, rec.getHead_Relationship());
			pstmt.setString(148, rec.getHead_ApplicantPhone());
			pstmt.setString(149, rec.getHead_GraveID());
			pstmt.setString(150, rec.getHead_GraveSection());
			pstmt.setString(151, rec.getHead_GraveNumber());
			pstmt.setString(152, rec.getHead_PrivCemeterID());
			pstmt.setString(153, rec.getHead_ConsigneeNameAddr());
			pstmt.setString(154, rec.getHead_CemPhone());
			pstmt.setString(155, rec.getHead_CemNameAddr());
			pstmt.setString(156, rec.getHead_Remarks());
			pstmt.setString(157, rec.getHead_HighestRank());
			pstmt.setShort(158, rec.getHead_StoneZ());
			pstmt.setShort(159, rec.getHead_StoneV());
			pstmt.setString(160, rec.getBen1_dayPhone());
			pstmt.setString(161, rec.getBen1_nightPhone());
			pstmt.setShort(162, rec.getHead_BranchMM());
			pstmt.setShort(163, rec.getHead_ValorBSM());
			pstmt.setShort(164, rec.getHead_WarPG());
			pstmt.setShort(165, rec.getHead_BeliefNone());
			pstmt.setString(166, rec.getFlag_VetOtherName());
			pstmt.setShort(167, rec.getFlag_SelReserveBox());
			pstmt.setShort(168, rec.getFlag_GulfBox());
			pstmt.setShort(169, rec.getFlag_Condition5Box());
			pstmt.setString(170, rec.getFlag_OtherVetService());
			pstmt.setString(171, rec.getFlag_OtherBranchOfService());
			pstmt.setString(172, rec.getHead_Suffix());
			pstmt.setString(173, rec.getHead_ApplicantFax());
			pstmt.setString(174, rec.getHead_ApplicantEmail());
			pstmt.setShort(175, rec.getHead_RequestInitial());
			pstmt.setShort(176, rec.getHead_RequestSecond());
			pstmt.setShort(177, rec.getHead_RequestCorrected());
			pstmt.setShort(178, rec.getHead_ValorOther());
			pstmt.setString(179, rec.getHead_ValorOtherSpecify());
			pstmt.setShort(180, rec.getHead_RelOther());
			pstmt.setString(181, rec.getHead_RelOtherSpecify());
			pstmt.setShort(182, rec.getHead_GraveMarked());
			pstmt.setString(183, rec.getFlag_BurialStreet());
			pstmt.setString(184, rec.getFlag_BurialCity());
			pstmt.setString(185, rec.getFlag_BurialState());
			pstmt.setString(186, rec.getFlag_BurialZipCode());
			pstmt.setString(187, rec.getFlag_DocShowEligibility());
			pstmt.setString(188, rec.getDeathPlace());
			pstmt.setString(189, rec.getDeathPlaceOther());
			pstmt.setString(190, rec.getEmail());
			pstmt.setString(191, rec.getBen1EmplopyerID());

			pstmt.setInt(192, rec.getId());
			
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVeteranPeer.Update:" + e.getMessage(), e);
		}
	}
}