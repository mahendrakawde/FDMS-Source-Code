package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.util.FormatNumber;


/**
 * DbVeteran - This class represents Veteran data associated with one case.
 * Creation date: (12/22/2001 5:42:35 PM)
 * @author:
 */
public class DbVeteran extends com.aldorsolutions.webfdms.database.Persistent {
    
    static Logger logger2 = Logger.getLogger(DbVeteran.class.getName());
    static private final DbVeteranPeer peer = new DbVeteranPeer();
    
    private int	lMilitaryMainKey;
    private String	flag_VetName;	//  Converted from char[50]    
    private short	flag_ArmyBox;
    private short	flag_NavyBox;
    private short	flag_AirForceBox;
    private short	flag_MarineBox;
    private short	flag_CoastGaurdBox;
    private short	flag_OtherBranchBox;
    private short	flag_SpanishAmerBox;
    private short	flag_WWIBox;
    private short	flag_WWIIBox;
    private short	flag_KoreanBox;
    private short	flag_After55Box;
    private short	flag_VietnamBox;
    private short	flag_OtherServiceBox;
    private short	flag_Condition1Box;
    private short	flag_Condition2Box;
    private short	flag_Condition3Box;
    private short	flag_Condition4Box;
    private String	flag_PersonReceiveFlag;	//  Converted from char[50]
    private String	flag_ReceiveRelation;	//  Converted from char[20]
    private String	flag_ReceiveAddr1;	//  Converted from char[30]
    private String	flag_ReceiveAddr2;	//  Converted from char[30]
    private String	flag_VAfileNumber;	//  Converted from char[20]
    private String	flag_SocSecNo;	//  Converted from char[10]
    private String	flag_SerialNo;	//  Converted from char[20]
    private String	flag_EnlistmentDate;	//  Converted from char[8]
    private String	flag_DischargeDate;	//  Converted from char[8]
    private String	flag_BirthDate;	//  Converted from char[8]
    private String	flag_DeathDate;	//  Converted from char[8]
    private String	flag_BurialDate;	//  Converted from char[8]
    private String	flag_BurialPlace;	//  Converted from char[80]
    
    private String	flag_BurialStreet;	//  Converted from char[30]
    private String	flag_BurialCity;	//  Converted from char[30]
    private String	flag_BurialState;	//  Converted from char[10]
    private String	flag_BurialZipCode;	//  Converted from char[10]
    private String flag_DocShowEligibility;
    
    
    
    
    
    
    private String	flag_Remarks;	//  Converted from char[80]
    private String	flag_ApplicantAddr1;	//  Converted from char[30]
    private String	flag_ApplicantAddr2;	//  Converted from char[30]
    private String	flag_ApplicantRelation;	//  Converted from char[20]
    private String	flag_ApplicantDate;	//  Converted from char[8]
    private String	ben1_ClaimantName;	//  Converted from char[50]
    private String	ben1_ClaimantStreet;	//  Converted from char[30]
    private String	ben1_ClaimantCityStZip;	//  Converted from char[30]
    private String	ben1_PlaceBirth;	//  Converted from char[80]
    private String	ben1_PlaceDeath;	//  Converted from char[80]
    private String	ben1_BurialDate;	//  Converted from char[8]
    private String	ben1_DateEntered1;	//  Converted from char[8]
    private String	ben1_PlaceEntered1;	//  Converted from char[50]
    private String	ben1_ServiceNo1;	//  Converted from char[20]
    private String	ben1_SepSrvcDate1;	//  Converted from char[8]
    private String	ben1_SepSrvcPlace1;	//  Converted from char[50]
    private String	ben1_GradeRank1;	//  Converted from char[80]
    private String	ben1_DateEntered2;	//  Converted from char[8]
    private String	ben1_PlaceEntered2;	//  Converted from char[50]
    private String	ben1_ServiceNo2;	//  Converted from char[20]
    private String	ben1_SepSrvcDate2;	//  Converted from char[8]
    private String	ben1_SepSrvcPlace2;	//  Converted from char[50]
    private String	ben1_GradeRank2;	//  Converted from char[80]
    private String	ben1_DateEntered3;	//  Converted from char[8]
    private String	ben1_PlaceEntered3;	//  Converted from char[50]
    private String	ben1_ServiceNo3;	//  Converted from char[20]
    private String	ben1_SepSrvcDate3;	//  Converted from char[8]
    private String	ben1_SepSrvcPlace3;	//  Converted from char[50]
    private String	ben1_GradeRank3;	//  Converted from char[80]
    private String	ben1_Box8OtherName;	//  Converted from char[80]
    private short	ben1_Box9Yes;
    private short	ben1_Box9No;
    private String	ben1_Box10BurialPlace;	//  Converted from char[80]
    private short	ben1_Box11Yes;
    private short	ben1_Box11No;
    private short	ben1_Box12Yes;
    private short	ben1_Box12No;
    private short	ben1_Box13OtherPerson;
    private short	ben1_Box13DueFD;
    private short	ben1_Box13DueCemetery;
    private short	ben1_Box13PaidClaimant;
    private short	ben1_Box13None;
    private String	ben1_Box14NameAddr;	//  Converted from char[80]
    private int		ben1_Box16TotBurialExp;
    private int		ben1_Box16AmountPaid;
    private String	ben1_Box17WhoseFunds;	//  Converted from char[80]
    private short	ben1_Box18aYes;
    private short	ben1_Box18aNo;
    private int		ben1_Box18bAmtReimb;
    private String	ben1_Box18cSourceReimb;	//  Converted from char[80]
    private short	ben1_Box19aYes;
    private short	ben1_Box19aNo;
    private int		ben1_Box19bAmount;
    private String	ben1_Box19cSource;	//  Converted from char[80]
    private short	ben1_Box20Yes;
    private short	ben1_Box20No;
    private String	ben2_Box21;	//  Converted from char[10]
    private String	ben2_Box22Place1;	//  Converted from char[50]
    private String	ben2_Box22Place2;	//  Converted from char[50]
    private int		ben2_Box23aCost;
    private String	ben2_Box23bDatePurch;	//  Converted from char[8]
    private String	ben2_Box23cDatePayment;	//  Converted from char[8]
    private short	ben2_Box24aYes;
    private short	ben2_Box24aNo;
    private int		ben2_Box24bAmtPaid;
    private String	ben2_Box25WhoseFunds;	//  Converted from char[50]
    private short	ben2_Box26aYes;
    private short	ben2_Box26aNo;
    private int		ben2_Box26bAmount;
    private String	ben2_Box26cSource;	//  Converted from char[50]
    private short	ben2_Box27aYes;
    private short	ben2_Box27aNo;
    private int		ben2_Box27bAmount;
    private String	ben2_Box27cSource;	//  Converted from char[50]
    private String	ben2_Box28bOfficialPos;	//  Converted from char[50]
    private String	ben2_Box29Agency;	//  Converted from char[80]
    private String	ben2_Box30bName;	//  Converted from char[70]
    private String	ben2_Box31Address;	//  Converted from char[80]
    private String	ben2_Box32date;	//  Converted from char[8]
    private String	ben2_Box33Relationship;	//  Converted from char[50]
    private String	head_Fname;	//  Converted from char[15]
    private String	head_Mname;	//  Converted from char[15]
    private String	head_Lname;	//  Converted from char[25]
    private short	head_Box2Yes;
    private short	head_Box2No;
    private short	head_BranchAR;
    private short	head_BranchNA;
    private short	head_BranchAF;
    private short	head_BranchMC;
    private short	head_BranchCG;
    private short	head_BranchAC;
    private short	head_BranchOther;
    private String	head_BranchSpecify;	//  Converted from char[40]
    private short	head_ValorMOH;
    private short	head_ValorDSC;
    private short	head_ValorNC;
    private short	head_ValorAFC;
    private short	head_ValorSS;
    private short	head_ValorPH;
    private short	head_WarWWI;
    private short	head_WarWWII;
    private short	head_WarKO;
    private short	head_WarVN;
    private short	head_WarOther;
    private String	head_WarSpecify;	//  Converted from char[30]
    private short	head_StoneB;
    private short	head_StoneG;
    private short	head_StoneU;
    private short	head_StoneF;
    private short	head_Belief1;
    private short	head_Belief2;
    private short	head_Belief3;
    private short	head_Belief4;
    private String	head_BeliefSpecify;	//  Converted from char[40]
    private String	head_ApplicantNameAddr;	//  Converted from char[80]
    private String	head_Relationship;	//  Converted from char[20]
    private String	head_ApplicantPhone;	//  Converted from char[20]
    private String	head_GraveID;	//  Converted from char[40]
    private String	head_GraveSection;	//  Converted from char[40]
    private String	head_GraveNumber;	//  Converted from char[40]
    private String	head_PrivCemeterID;	//  Converted from char[40]
    private String	head_ConsigneeNameAddr;	//  Converted from char[80]
    private String	head_CemPhone;	//  Converted from char[20]
    private String	head_CemNameAddr;	//  Converted from char[80]
    private String	head_Remarks;	//  Converted from char[80]
    private String	head_HighestRank;	//  Converted from char[20]
    private short	head_StoneZ;
    private short	head_StoneV;
    private String	ben1_dayPhone;	//  Converted from char[20]
    private String	ben1_nightPhone;	//  Converted from char[20]
    
    private short head_BranchMM;
    private short head_ValorBSM;
    private short head_WarPG;
    private short head_BeliefNone;
    
       /* for new Flag form 21-2008 May 2003
       Added by Adri Roberts Feb2004 */
    private String flag_VetOtherName;
    private short flag_SelReserveBox;
    private String flag_OtherBranchOfService;
    private short flag_GulfBox;
    private String flag_OtherVetService;
    private short flag_Condition5Box;
    
       /* for new Headstone form JAN 2003(RS)
       Added by Adri Roberts March2004 */
    private String	head_Suffix;
    private String	head_ApplicantFax;
    private String	head_ApplicantEmail;
    private short    head_RequestInitial;
    private short    head_RequestSecond;
    private short    head_RequestCorrected;
    private short    head_ValorOther;
    private String   head_ValorOtherSpecify;
    private short    head_RelOther;
    private String   head_RelOtherSpecify;
    private short    head_GraveMarked;
   
    
    /*  #ticket : #5425 added by Bhavesh Shah */
    private String deathPlace;
    private String deathPlaceOther;
    private String email;
    private String ben1EmplopyerID;
    /* Finish */
    
    public String getBen1_Box10BurialPlace() {
        return ben1_Box10BurialPlace;
    }
    public short getBen1_Box11No() {
        return ben1_Box11No;
    }
    public short getBen1_Box11Yes() {
        return ben1_Box11Yes;
    }
    public short getBen1_Box12No() {
        return ben1_Box12No;
    }
    public short getBen1_Box12Yes() {
        return ben1_Box12Yes;
    }
    public short getBen1_Box13DueCemetery() {
        return ben1_Box13DueCemetery;
    }
    public short getBen1_Box13DueFD() {
        return ben1_Box13DueFD;
    }
    public short getBen1_Box13None() {
        return ben1_Box13None;
    }
    public short getBen1_Box13OtherPerson() {
        return ben1_Box13OtherPerson;
    }
    public short getBen1_Box13PaidClaimant() {
        return ben1_Box13PaidClaimant;
    }
    public String getBen1_Box14NameAddr() {
        return ben1_Box14NameAddr;
    }
    public int getBen1_Box16AmountPaid() {
        return ben1_Box16AmountPaid;
    }
    public int getBen1_Box16TotBurialExp() {
        return ben1_Box16TotBurialExp;
    }
    public String getBen1_Box17WhoseFunds() {
        return ben1_Box17WhoseFunds;
    }
    public short getBen1_Box18aNo() {
        return ben1_Box18aNo;
    }
    public short getBen1_Box18aYes() {
        return ben1_Box18aYes;
    }
    public int getBen1_Box18bAmtReimb() {
        return ben1_Box18bAmtReimb;
    }
    public String getBen1_Box18cSourceReimb() {
        return ben1_Box18cSourceReimb;
    }
    public short getBen1_Box19aNo() {
        return ben1_Box19aNo;
    }
    public short getBen1_Box19aYes() {
        return ben1_Box19aYes;
    }
    public int getBen1_Box19bAmount() {
        return ben1_Box19bAmount;
    }
    public String getBen1_Box19cSource() {
        return ben1_Box19cSource;
    }
    public short getBen1_Box20No() {
        return ben1_Box20No;
    }
    public short getBen1_Box20Yes() {
        return ben1_Box20Yes;
    }
    public String getBen1_Box8OtherName() {
        return ben1_Box8OtherName;
    }
    public short getBen1_Box9No() {
        return ben1_Box9No;
    }
    public short getBen1_Box9Yes() {
        return ben1_Box9Yes;
    }
    public String getBen1_BurialDate() {
        return ben1_BurialDate;
    }
    public String getBen1_ClaimantCityStZip() {
        return ben1_ClaimantCityStZip;
    }
    public String getBen1_ClaimantName() {
        return ben1_ClaimantName;
    }
    public String getBen1_ClaimantStreet() {
        return ben1_ClaimantStreet;
    }
    public String getBen1_DateEntered1() {
        return ben1_DateEntered1;
    }
    public String getBen1_DateEntered2() {
        return ben1_DateEntered2;
    }
    public String getBen1_DateEntered3() {
        return ben1_DateEntered3;
    }
    public String getBen1_dayPhone() {
        return ben1_dayPhone;
    }
    public String getBen1_GradeRank1() {
        return ben1_GradeRank1;
    }
    public String getBen1_GradeRank2() {
        return ben1_GradeRank2;
    }
    public String getBen1_GradeRank3() {
        return ben1_GradeRank3;
    }
    public String getBen1_nightPhone() {
        return ben1_nightPhone;
    }
    public String getBen1_PlaceBirth() {
        return ben1_PlaceBirth;
    }
    public String getBen1_PlaceDeath() {
        return ben1_PlaceDeath;
    }
    public String getBen1_PlaceEntered1() {
        return ben1_PlaceEntered1;
    }
    public String getBen1_PlaceEntered2() {
        return ben1_PlaceEntered2;
    }
    public String getBen1_PlaceEntered3() {
        return ben1_PlaceEntered3;
    }
    public String getBen1_SepSrvcDate1() {
        return ben1_SepSrvcDate1;
    }
    public String getBen1_SepSrvcDate2() {
        return ben1_SepSrvcDate2;
    }
    public String getBen1_SepSrvcDate3() {
        return ben1_SepSrvcDate3;
    }
    public String getBen1_SepSrvcPlace1() {
        return ben1_SepSrvcPlace1;
    }
    public String getBen1_SepSrvcPlace2() {
        return ben1_SepSrvcPlace2;
    }
    public String getBen1_SepSrvcPlace3() {
        return ben1_SepSrvcPlace3;
    }
    public String getBen1_ServiceNo1() {
        return ben1_ServiceNo1;
    }
    public String getBen1_ServiceNo2() {
        return ben1_ServiceNo2;
    }
    public String getBen1_ServiceNo3() {
        return ben1_ServiceNo3;
    }
    public String getBen2_Box21() {
        return ben2_Box21;
    }
    public String getBen2_Box22Place1() {
        return ben2_Box22Place1;
    }
    public String getBen2_Box22Place2() {
        return ben2_Box22Place2;
    }
    public int getBen2_Box23aCost() {
        return ben2_Box23aCost;
    }
    public String getBen2_Box23bDatePurch() {
        return ben2_Box23bDatePurch;
    }
    public String getBen2_Box23cDatePayment() {
        return ben2_Box23cDatePayment;
    }
    public short getBen2_Box24aNo() {
        return ben2_Box24aNo;
    }
    public short getBen2_Box24aYes() {
        return ben2_Box24aYes;
    }
    public int getBen2_Box24bAmtPaid() {
        return ben2_Box24bAmtPaid;
    }
    public String getBen2_Box25WhoseFunds() {
        return ben2_Box25WhoseFunds;
    }
    public short getBen2_Box26aNo() {
        return ben2_Box26aNo;
    }
    public short getBen2_Box26aYes() {
        return ben2_Box26aYes;
    }
    public int getBen2_Box26bAmount() {
        return ben2_Box26bAmount;
    }
    public String getBen2_Box26cSource() {
        return ben2_Box26cSource;
    }
    public short getBen2_Box27aNo() {
        return ben2_Box27aNo;
    }
    public short getBen2_Box27aYes() {
        return ben2_Box27aYes;
    }
    public int getBen2_Box27bAmount() {
        return ben2_Box27bAmount;
    }
    public String getBen2_Box27cSource() {
        return ben2_Box27cSource;
    }
    public String getBen2_Box28bOfficialPos() {
        return ben2_Box28bOfficialPos;
    }
    public String getBen2_Box29Agency() {
        return ben2_Box29Agency;
    }
    public String getBen2_Box30bName() {
        return ben2_Box30bName;
    }
    public String getBen2_Box31Address() {
        return ben2_Box31Address;
    }
    public String getBen2_Box32date() {
        return ben2_Box32date;
    }
    public String getBen2_Box33Relationship() {
        return ben2_Box33Relationship;
    }
    /**
     * Calculate the brance of service from FLAG appliation check boxes.
     * Creation date: (1/15/2002 7:24:41 PM)
     * @return java.lang.String
     */
    public String getBranchOfService() {
        if (getFlag_AirForceBox()!=0)
            return "Air Force";
        if (getFlag_ArmyBox()!=0)
            return "Army";
        if (getFlag_CoastGaurdBox()!=0)
            return "Coast Guard";
        if (getFlag_MarineBox()!=0)
            return "Marines";
        if (getFlag_NavyBox()!=0)
            return "Navy";
        if (getFlag_SelReserveBox()!=0)
            return "Selected Reserve";        
        
        return "";
    }
    public short getFlag_After55Box() {
        return flag_After55Box;
    }
    public short getFlag_AirForceBox() {
        return flag_AirForceBox;
    }
    public String getFlag_ApplicantAddr1() {
        return flag_ApplicantAddr1;
    }
    public String getFlag_ApplicantAddr2() {
        return flag_ApplicantAddr2;
    }
    public String getFlag_ApplicantDate() {
        return flag_ApplicantDate;
    }
    public String getFlag_ApplicantRelation() {
        return flag_ApplicantRelation;
    }
    public short getFlag_ArmyBox() {
        return flag_ArmyBox;
    }
    public String getFlag_BirthDate() {
        return flag_BirthDate;
    }
    public String getFlag_BurialDate() {
        return flag_BurialDate;
    }
    public String getFlag_BurialPlace() {
        return flag_BurialPlace;
    }
    public short getFlag_CoastGaurdBox() {
        return flag_CoastGaurdBox;
    }
    public short getFlag_Condition1Box() {
        return flag_Condition1Box;
    }
    public short getFlag_Condition2Box() {
        return flag_Condition2Box;
    }
    public short getFlag_Condition3Box() {
        return flag_Condition3Box;
    }
    public short getFlag_Condition4Box() {
        return flag_Condition4Box;
    }
    public String getFlag_DeathDate() {
        return flag_DeathDate;
    }
    public String getFlag_DischargeDate() {
        return flag_DischargeDate;
    }
    public String getFlag_EnlistmentDate() {
        return flag_EnlistmentDate;
    }
    public short getFlag_KoreanBox() {
        return flag_KoreanBox;
    }
    public short getFlag_MarineBox() {
        return flag_MarineBox;
    }
    public short getFlag_NavyBox() {
        return flag_NavyBox;
    }
    public short getFlag_OtherBranchBox() {
        return flag_OtherBranchBox;
    }
    public short getFlag_OtherServiceBox() {
        return flag_OtherServiceBox;
    }
    public String getFlag_PersonReceiveFlag() {
        return flag_PersonReceiveFlag;
    }
    public String getFlag_ReceiveAddr1() {
        return flag_ReceiveAddr1;
    }
    public String getFlag_ReceiveAddr2() {
        return flag_ReceiveAddr2;
    }
    public String getFlag_ReceiveRelation() {
        return flag_ReceiveRelation;
    }
    public String getFlag_Remarks() {
        return flag_Remarks;
    }
    public String getFlag_SerialNo() {
        return flag_SerialNo;
    }
    public String getFlag_SocSecNo() {
        return flag_SocSecNo;
    }
    public short getFlag_SpanishAmerBox() {
        return flag_SpanishAmerBox;
    }
    public String getFlag_VAfileNumber() {
        return flag_VAfileNumber;
    }
    public String getFlag_VetName() {
        return flag_VetName;
    }
    
    public short getFlag_VietnamBox() {
        return flag_VietnamBox;
    }
    public short getFlag_WWIBox() {
        return flag_WWIBox;
    }
    public short getFlag_WWIIBox() {
        return flag_WWIIBox;
    }
    
    /*new form */
    public String getFlag_VetOtherName() {
        return flag_VetOtherName;
    }
    
    public short getFlag_SelReserveBox() {
        return flag_SelReserveBox;
    }
    
    public short getFlag_GulfBox() {
        return flag_GulfBox;
    }
    
    public short getFlag_Condition5Box() {
        return flag_Condition5Box;
    }
    
    public String getFlag_OtherBranchOfService() {
        return flag_OtherBranchOfService;
    }
    
    public String getFlag_OtherVetService() {
        return flag_OtherVetService;
    }
    /* headstone fields */
    
    public short getHead_RequestInitial() {
        return head_RequestInitial;
    }
    
    public short getHead_RequestSecond() {
        return head_RequestSecond;
    }
    
    public short getHead_RequestCorrected() {
        return head_RequestCorrected;
    }
    
    public short getHead_ValorOther() {
        return head_ValorOther;
    }
    
    public String getHead_ValorOtherSpecify() {
        return head_ValorOtherSpecify;
    }
    
    
    
    public short getHead_RelOther() {
        return head_RelOther;
    }
    
    public String getHead_RelOtherSpecify() {
        return head_RelOtherSpecify;
    }
    
    public short getHead_GraveMarked() {
        return head_GraveMarked;
    }
    
    public String getHead_ApplicantNameAddr() {
        return head_ApplicantNameAddr;
    }
    public String getHead_ApplicantPhone() {
        return head_ApplicantPhone;
    }
    public String getHead_ApplicantFax() {
        return head_ApplicantFax;
    }
    public String getHead_ApplicantEmail() {
        return head_ApplicantEmail;
    }
    public short getHead_Belief1() {
        return head_Belief1;
    }
    public short getHead_Belief2() {
        return head_Belief2;
    }
    public short getHead_Belief3() {
        return head_Belief3;
    }
    public short getHead_Belief4() {
        return head_Belief4;
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 10:05:29 AM)
     * @return short
     */
    public short getHead_BeliefNone() {
        return head_BeliefNone;
    }
    public String getHead_BeliefSpecify() {
        return head_BeliefSpecify;
    }
    public short getHead_Box2No() {
        return head_Box2No;
    }
    public short getHead_Box2Yes() {
        return head_Box2Yes;
    }
    public short getHead_BranchAC() {
        return head_BranchAC;
    }
    public short getHead_BranchAF() {
        return head_BranchAF;
    }
    public short getHead_BranchAR() {
        return head_BranchAR;
    }
    public short getHead_BranchCG() {
        return head_BranchCG;
    }
    public short getHead_BranchMC() {
        return head_BranchMC;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 10:04:25 AM)
     * @return short
     */
    public short getHead_BranchMM() {
        return head_BranchMM;
    }
    public short getHead_BranchNA() {
        return head_BranchNA;
    }
    public short getHead_BranchOther() {
        return head_BranchOther;
    }
    public String getHead_BranchSpecify() {
        return head_BranchSpecify;
    }
    public String getHead_CemNameAddr() {
        return head_CemNameAddr;
    }
    public String getHead_CemPhone() {
        return head_CemPhone;
    }
    public String getHead_ConsigneeNameAddr() {
        return head_ConsigneeNameAddr;
    }
    public String getHead_Fname() {
        return head_Fname;
    }
    public String getHead_GraveID() {
        return head_GraveID;
    }
    public String getHead_GraveNumber() {
        return head_GraveNumber;
    }
    public String getHead_GraveSection() {
        return head_GraveSection;
    }
    public String getHead_HighestRank() {
        return head_HighestRank;
    }
    public String getHead_Lname() {
        return head_Lname;
    }
    public String getHead_Suffix() {
        return head_Suffix;
    }
    public String getHead_Mname() {
        return head_Mname;
    }
    public String getHead_PrivCemeterID() {
        return head_PrivCemeterID;
    }
    public String getHead_Relationship() {
        return head_Relationship;
    }
    public String getHead_Remarks() {
        return head_Remarks;
    }
    public short getHead_StoneB() {
        return head_StoneB;
    }
    public short getHead_StoneF() {
        return head_StoneF;
    }
    public short getHead_StoneG() {
        return head_StoneG;
    }
    public short getHead_StoneU() {
        return head_StoneU;
    }
    public short getHead_StoneV() {
        return head_StoneV;
    }
    public short getHead_StoneZ() {
        return head_StoneZ;
    }
    public short getHead_ValorAFC() {
        return head_ValorAFC;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 10:04:51 AM)
     * @return short
     */
    public short getHead_ValorBSM() {
        return head_ValorBSM;
    }
    public short getHead_ValorDSC() {
        return head_ValorDSC;
    }
    public short getHead_ValorMOH() {
        return head_ValorMOH;
    }
    public short getHead_ValorNC() {
        return head_ValorNC;
    }
    public short getHead_ValorPH() {
        return head_ValorPH;
    }
    public short getHead_ValorSS() {
        return head_ValorSS;
    }
    public short getHead_WarKO() {
        return head_WarKO;
    }
    public short getHead_WarOther() {
        return head_WarOther;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 10:05:07 AM)
     * @return short
     */
    public short getHead_WarPG() {
        return head_WarPG;
    }
    public String getHead_WarSpecify() {
        return head_WarSpecify;
    }
    public short getHead_WarVN() {
        return head_WarVN;
    }
    public short getHead_WarWWI() {
        return head_WarWWI;
    }
    public short getHead_WarWWII() {
        return head_WarWWII;
    }
    public int getLMilitaryMainKey() {
        return lMilitaryMainKey;
    }
    /**
     * getPersistentPeer method comment.
     */
    protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
        return peer;
    }
    /**
     * Determine war served in from flag application check boxes.
     * Creation date: (1/15/2002 7:30:49 PM)
     * @return java.lang.String
     */
    public String getWar() {
        if (getFlag_SpanishAmerBox()!=0)
            return "Spanish American War";
        if (getFlag_KoreanBox()!=0)
            return "Korean War";
        if (getFlag_VietnamBox()!=0)
            return ("VietNam War");
        if (getFlag_WWIBox()!=0)
            return ("World War I");
        if (getFlag_WWIIBox()!=0)
            return ("World War II");
        if (getFlag_GulfBox()!=0)
            return ("Gulf War");
        
        return "";
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
    public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data) throws com.aldorsolutions.webfdms.database.PersistenceException {
        
        try {            
            
            setId(Integer.parseInt(data.get(DbVeteranPeer.IDENTITY).toString()));
            setLMilitaryMainKey(
                FormatNumber.parseInteger(data.get(DbVeteranPeer.CASE_ID).toString())
                );            
            setFlag_VetName(
                nullStr(((String)data.get(DbVeteranPeer.FLAGVETNAME)))
                );            
            setFlag_ArmyBox(
                FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGARMYBOX).toString())
                );
            setFlag_NavyBox(
                FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGNAVYBOX).toString())
                );
            setFlag_AirForceBox(
                FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGAIRFORCEBOX).toString())
                );
            setFlag_MarineBox(
                FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGMARINEBOX).toString())
                );
            setFlag_CoastGaurdBox(
                FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGCOASTGUARDBOX).toString())
                );
            setFlag_OtherBranchBox(
                FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGOTHERBRANCHBOX).toString())
                );
            setFlag_SpanishAmerBox(
                FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGSPANAMERBOX).toString())
                );
            /*new form */ 
            //logger2.error("New form");
                        
            setFlag_VetOtherName(nullStr((String)data.get(DbVeteranPeer.FLAGVETOTHERNAME)));
            //logger2.error("set vet other name : " + (String)data.get(DbVeteranPeer.FLAGVETOTHERNAME));
                           
            setFlag_SelReserveBox(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGSELSERVICEBOX).toString()));
            //logger2.error("set reserve box : " + data.get(DbVeteranPeer.FLAGSELSERVICEBOX).toString());         
            
            setFlag_GulfBox(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGGULFBOX).toString()));
            //logger2.error("set gulf box : " + data.get(DbVeteranPeer.FLAGGULFBOX).toString());     
            
            setFlag_Condition5Box(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGCOND5BOX).toString()));
            //logger2.error("set condition 5box : " + data.get(DbVeteranPeer.FLAGCOND5BOX).toString());     
            
            setFlag_OtherVetService(data.get(DbVeteranPeer.FLAGOTHERVETSERVICE).toString());
            //logger2.error("set other vet service : " + data.get(DbVeteranPeer.FLAGOTHERVETSERVICE));
            
            setFlag_OtherBranchOfService(nullStr((String)data.get(DbVeteranPeer.FLAGOTHERBRANCHOFSERVICE)));
            //logger2.error("End of new form");
            /*end ***/
            setFlag_WWIBox(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGWWIBOX).toString()));
            setFlag_WWIIBox(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGWWIIBOX).toString()));
            setFlag_KoreanBox(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGKOREANBOX).toString()));
            setFlag_After55Box(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGAFTER55BOX).toString()));
            setFlag_VietnamBox(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGVIETNAMBOX).toString()));
            setFlag_OtherServiceBox(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGOTHERSERVBOX).toString()));
            setFlag_Condition1Box(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGCOND1BOX).toString()));
            setFlag_Condition2Box(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGCOND2BOX).toString()));
            setFlag_Condition3Box(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGCOND3BOX).toString()));
            setFlag_Condition4Box(FormatNumber.parseShort(data.get(DbVeteranPeer.FLAGCOND4BOX).toString()));
            setFlag_PersonReceiveFlag(nullStr((String)data.get(DbVeteranPeer.FLAGPERSONREC)));
            setFlag_ReceiveRelation(nullStr((String)data.get(DbVeteranPeer.FLAGRECEIVREL)));
            setFlag_ReceiveAddr1(nullStr((String)data.get(DbVeteranPeer.FLAGRECEIVADDR1)));
            setFlag_ReceiveAddr2(nullStr((String)data.get(DbVeteranPeer.FLAGRECEIVADDR2)));
            setFlag_VAfileNumber(nullStr((String)data.get(DbVeteranPeer.FLAGVAFILENO)));
            setFlag_SocSecNo(nullStr((String)data.get(DbVeteranPeer.FLAGSOCSECNO)));
            setFlag_SerialNo(nullStr((String)data.get(DbVeteranPeer.FLAGSERIALNO)));
            setFlag_EnlistmentDate((String)data.get(DbVeteranPeer.FLAGENLISTDATE));
            setFlag_DischargeDate((String)data.get(DbVeteranPeer.FLAGDISCHARGEDATE));
            setFlag_BirthDate((String)data.get(DbVeteranPeer.FLAGBIRTHDATE));
            setFlag_DeathDate(nullStr((String)data.get(DbVeteranPeer.FLAGDEATHDATE)));
            setFlag_BurialDate(nullStr((String)data.get(DbVeteranPeer.FLAGBURIALDATE)));
            setFlag_BurialPlace(nullStr((String)data.get(DbVeteranPeer.FLAGBURIALPLACE)));
            setFlag_BurialStreet(nullStr((String)data.get(DbVeteranPeer.FLAGBURIALSTREET)));
            setFlag_BurialCity(nullStr((String)data.get(DbVeteranPeer.FLAGBURIALCITY)));
            setFlag_BurialState(nullStr((String)data.get(DbVeteranPeer.FLAGBURIALSTATE)));
            setFlag_BurialZipCode(nullStr((String)data.get(DbVeteranPeer.FLAGBURIALZIPCODE)));
            setFlag_Remarks(nullStr((String)data.get(DbVeteranPeer.FLAGREMARKS)));
            setFlag_DocShowEligibility(nullStr((String)data.get(DbVeteranPeer.FLAGDOCSHOWELIGIBILITY)));
            setFlag_ApplicantAddr1(nullStr((String)data.get(DbVeteranPeer.FLAGAPPADDR1)));
            setFlag_ApplicantAddr2(nullStr((String)data.get(DbVeteranPeer.FLAGAPPADDR2)));
            setFlag_ApplicantRelation(nullStr((String)data.get(DbVeteranPeer.FLAGAPPRELATION)));
            setFlag_ApplicantDate(nullStr((String)data.get(DbVeteranPeer.FLAGAPPDATE)));
            setBen1_ClaimantName(nullStr((String)data.get(DbVeteranPeer.BEN1CLAIMNAME)));
            setBen1_ClaimantStreet(nullStr((String)data.get(DbVeteranPeer.BEN1CLAIMMADDR)));
            setBen1_ClaimantCityStZip(nullStr((String)data.get(DbVeteranPeer.BEN1CLAIMCITY)));
            setBen1_PlaceBirth(nullStr((String)data.get(DbVeteranPeer.BEN1PLACEBIRTH)));
            setBen1_PlaceDeath(nullStr((String)data.get(DbVeteranPeer.BEN1PLACEDEATH)));
            setBen1_BurialDate(nullStr((String)data.get(DbVeteranPeer.BEN1BURIALDATE)));
            setBen1_DateEntered1((String)data.get(DbVeteranPeer.BEN1DATEENTER1));
            setBen1_PlaceEntered1(nullStr((String)data.get(DbVeteranPeer.BEN1PLACEENTER1)));
            setBen1_ServiceNo1(nullStr((String)data.get(DbVeteranPeer.BEN1SERVICENO1)));
            setBen1_SepSrvcDate1((String)data.get(DbVeteranPeer.BEN1SEPSRVCDATE1));
            setBen1_SepSrvcPlace1(nullStr((String)data.get(DbVeteranPeer.BEN1SEPSRVCPLACE1)));
            setBen1_GradeRank1(nullStr((String)data.get(DbVeteranPeer.BEN1GRADERANK1)));
            setBen1_DateEntered2((String)data.get(DbVeteranPeer.BEN1DATEENTER2));
            setBen1_PlaceEntered2(nullStr((String)data.get(DbVeteranPeer.BEN1PLACEENTER2)));
            setBen1_ServiceNo2(nullStr((String)data.get(DbVeteranPeer.BEN1SERVICENO2)));
            setBen1_SepSrvcDate2((String)data.get(DbVeteranPeer.BEN1SEPSRVCDATE2));
            setBen1_SepSrvcPlace2(nullStr((String)data.get(DbVeteranPeer.BEN1SEPSRVCPLACE2)));
            setBen1_GradeRank2(nullStr((String)data.get(DbVeteranPeer.BEN1GRADERANK2)));
            setBen1_DateEntered3((String)data.get(DbVeteranPeer.BEN1DATEENTER3));
            setBen1_PlaceEntered3(nullStr((String)data.get(DbVeteranPeer.BEN1PLACEENTER3)));
            setBen1_ServiceNo3(nullStr((String)data.get(DbVeteranPeer.BEN1SERVICENO3)));
            setBen1_SepSrvcDate3(		(String)data.get(DbVeteranPeer.BEN1SEPSRVCDATE3));
            setBen1_SepSrvcPlace3(nullStr((String)data.get(DbVeteranPeer.BEN1SEPSRVCPLACE3)));
            setBen1_GradeRank3(nullStr((String)data.get(DbVeteranPeer.BEN1GRADERANK3)));
            setBen1_Box8OtherName(nullStr((String)data.get(DbVeteranPeer.BEN1BOX8)));
            setBen1_Box9Yes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX9YES).toString()));
            setBen1_Box9No(				FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX9NO).toString()));
            setBen1_Box10BurialPlace(nullStr((String)data.get(DbVeteranPeer.BEN1BURIALPLACE)));
            setBen1_Box11Yes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX11YES).toString()));
            setBen1_Box11No(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX11NO).toString()));
            setBen1_Box12Yes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX12YES).toString()));
            setBen1_Box12No(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX12NO).toString()));
            setBen1_Box13OtherPerson(	FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX13OTHER).toString()));
            setBen1_Box13DueFD(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX13FD).toString()));
            setBen1_Box13DueCemetery(	FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX13CEMET).toString()));
            setBen1_Box13PaidClaimant(	FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX13PAID).toString()));
            setBen1_Box13None(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX13NONE).toString()));
            setBen1_Box14NameAddr(nullStr((String)data.get(DbVeteranPeer.BEN1BOX14NAME)));
            setBen1_Box16TotBurialExp(	FormatNumber.parseInteger(data.get(DbVeteranPeer.BEN1BOX15).toString()));
            setBen1_Box16AmountPaid(	FormatNumber.parseInteger(data.get(DbVeteranPeer.BEN1BOX16).toString()));
            setBen1_Box17WhoseFunds(nullStr((String)data.get(DbVeteranPeer.BEN1BOX17)));
            setBen1_Box18aYes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX18AYES).toString()));
            setBen1_Box18aNo(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX18ANO).toString()));
            setBen1_Box18bAmtReimb(		FormatNumber.parseInteger(data.get(DbVeteranPeer.BEN1BOX18B).toString()));
            setBen1_Box18cSourceReimb(nullStr((String)data.get(DbVeteranPeer.BEN1BOX18C)));
            setBen1_Box19aYes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX19AYES).toString()));
            setBen1_Box19aNo(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX19ANO).toString()));
            setBen1_Box19bAmount(		FormatNumber.parseInteger(data.get(DbVeteranPeer.BEN1BOX19B).toString()));
            setBen1_Box19cSource(nullStr((String)data.get(DbVeteranPeer.BEN1BOX19C)));
            setBen1_Box20Yes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX20YES).toString()));
            setBen1_Box20No(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN1BOX20NO).toString()));
            setBen2_Box21(				(String)data.get(DbVeteranPeer.BEN2BOX21));
            setBen2_Box22Place1(nullStr((String)data.get(DbVeteranPeer.BEN2BOX22PLACE1)));
            setBen2_Box22Place2(nullStr((String)data.get(DbVeteranPeer.BEN2BOX22PLACE2)));
            setBen2_Box23aCost(			FormatNumber.parseInteger(data.get(DbVeteranPeer.BEN2BOX23A).toString()));
            setBen2_Box23bDatePurch(nullStr((String)data.get(DbVeteranPeer.BEN2BOX23B)));
            setBen2_Box23cDatePayment(nullStr((String)data.get(DbVeteranPeer.BEN2BOX23C)));
            setBen2_Box24aYes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN2BOX24AYES).toString()));
            setBen2_Box24aNo(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN2BOX24ANO).toString()));
            setBen2_Box24bAmtPaid(		FormatNumber.parseInteger(data.get(DbVeteranPeer.BEN2BOX24B).toString()));
            setBen2_Box25WhoseFunds(nullStr((String)data.get(DbVeteranPeer.BEN2BOX25)));
            setBen2_Box26aYes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN2BOX26AYES).toString()));
            setBen2_Box26aNo(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN2BOX26ANO).toString()));
            setBen2_Box26bAmount(		FormatNumber.parseInteger(data.get(DbVeteranPeer.BEN2BOX26B).toString()));
            setBen2_Box26cSource(nullStr((String)data.get(DbVeteranPeer.BEN2BOX26C)));
            setBen2_Box27aYes(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN2BOX27AYES).toString()));
            setBen2_Box27aNo(			FormatNumber.parseShort(data.get(DbVeteranPeer.BEN2COS27ANO).toString()));
            setBen2_Box27bAmount(		FormatNumber.parseInteger(data.get(DbVeteranPeer.BEN2BOX27B).toString()));
            setBen2_Box27cSource(nullStr((String)data.get(DbVeteranPeer.BEN2BOX27C)));
            setBen2_Box28bOfficialPos(nullStr((String)data.get(DbVeteranPeer.BEN2BOX28B)));
            setBen2_Box29Agency(nullStr((String)data.get(DbVeteranPeer.BEN2BOX29)));
            setBen2_Box30bName(nullStr((String)data.get(DbVeteranPeer.BEN2BOX30B)));
            setBen2_Box31Address(nullStr((String)data.get(DbVeteranPeer.BEN2BOX31)));
            setBen2_Box32date(			(String)data.get(DbVeteranPeer.BEN2BOX32));
            setBen2_Box33Relationship(nullStr((String)data.get(DbVeteranPeer.BEN2BOX33)));
            /* headstone fields */
            setHead_Fname(nullStr((String)data.get(DbVeteranPeer.HEADFIRSTNAME)));
            setHead_Mname(nullStr((String)data.get(DbVeteranPeer.HEADMIDDLENAME)));
            setHead_Lname(nullStr((String)data.get(DbVeteranPeer.HEADLASTNAME)));
            setHead_Suffix(nullStr((String)data.get(DbVeteranPeer.HEADSUFFIX)));
            setHead_RequestInitial(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADREQUESTINITIAL).toString()));
            setHead_RequestSecond(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADREQUESTSECOND).toString()));
            setHead_RequestCorrected(		FormatNumber.parseShort(data.get(DbVeteranPeer.HEADREQUESTCORRECTED).toString()));
            setHead_ValorOther(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADVALOROTHER).toString()));
            setHead_ValorOtherSpecify(nullStr((String)data.get(DbVeteranPeer.HEADVALOROTHERSPECIFY)));
            setHead_RelOther(                       FormatNumber.parseShort(data.get(DbVeteranPeer.HEADRELOTHER).toString()));
            setHead_RelOtherSpecify(nullStr((String)data.get(DbVeteranPeer.HEADRELOTHERSPECIFY)));
            setHead_GraveMarked(                    FormatNumber.parseShort(data.get(DbVeteranPeer.HEADGRAVEMARKED).toString()));
            
            setHead_Box2Yes(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBOX2YES).toString()));
            setHead_Box2No(				FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBOX2NO).toString()));
            setHead_BranchAR(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBRANCHARMY).toString()));
            setHead_BranchNA(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBRANCHNAVY).toString()));
            setHead_BranchAF(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBRANCHAIR).toString()));
            setHead_BranchMC(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBRANCHMARINES).toString()));
            setHead_BranchCG(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBRANCHCG).toString()));
            setHead_BranchAC(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBRANCHARMYAIR).toString()));
            setHead_BranchOther(		FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBRANCHOTHER).toString()));
            setHead_BranchSpecify(nullStr((String)data.get(DbVeteranPeer.HEADBRANCHSPECIFY)));
            setHead_ValorMOH(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADVALORMOH).toString()));
            setHead_ValorDSC(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADVALORDSC).toString()));
            setHead_ValorNC(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADVALORNC).toString()));
            setHead_ValorAFC(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADVALORAFC).toString()));
            setHead_ValorSS(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADVALORSS).toString()));
            setHead_ValorPH(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADVALORPH).toString()));
            setHead_WarWWI(				FormatNumber.parseShort(data.get(DbVeteranPeer.HEADWARWWI).toString()));
            setHead_WarWWII(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADWARWWII).toString()));
            setHead_WarKO(				FormatNumber.parseShort(data.get(DbVeteranPeer.HEADWARKOREAN).toString()));
            setHead_WarVN(				FormatNumber.parseShort(data.get(DbVeteranPeer.HEADWARVIETNAM).toString()));
            setHead_WarOther(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADWAROTHER).toString()));
            setHead_WarSpecify(nullStr((String)data.get(DbVeteranPeer.HEADWARSPECIFY)));
            setHead_StoneB(				FormatNumber.parseShort(data.get(DbVeteranPeer.HEADSTONEBRONZE).toString()));
            setHead_StoneG(				FormatNumber.parseShort(data.get(DbVeteranPeer.HEADSTONEGRANITE).toString()));
            setHead_StoneU(				FormatNumber.parseShort(data.get(DbVeteranPeer.HEADSTONEUPRIGHT).toString()));
            setHead_StoneF(				FormatNumber.parseShort(data.get(DbVeteranPeer.HEADSTONEFLAT).toString()));
            setHead_Belief1(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBELIEFCHRIST).toString()));
            setHead_Belief2(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBELIEFBUDDHIST).toString()));
            setHead_Belief3(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBELIEFJEW).toString()));
            setHead_Belief4(			FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBELIEFOTHER).toString()));
            setHead_BeliefSpecify(nullStr((String)data.get(DbVeteranPeer.HEADBELIEFSPECIFY)));
            setHead_ApplicantNameAddr(nullStr((String)data.get(DbVeteranPeer.HEADAPPNAME)));
            setHead_Relationship(nullStr((String)data.get(DbVeteranPeer.HEADRELATIONSHIP)));
            setHead_ApplicantPhone(nullStr((String)data.get(DbVeteranPeer.HEADAPPPHONE)));
            setHead_ApplicantFax(nullStr((String)data.get(DbVeteranPeer.HEADAPPFAX)));
            setHead_ApplicantEmail(nullStr((String)data.get(DbVeteranPeer.HEADAPPEMAIL)));
            setHead_GraveID(nullStr((String)data.get(DbVeteranPeer.HEADGRAVEID)));
            setHead_GraveSection(nullStr((String)data.get(DbVeteranPeer.HEADGRAVESECTION)));
            setHead_GraveNumber(nullStr((String)data.get(DbVeteranPeer.HEADGRAVENO)));
            setHead_PrivCemeterID(nullStr((String)data.get(DbVeteranPeer.HEADPRIVCEMETERY)));
            setHead_ConsigneeNameAddr(nullStr((String)data.get(DbVeteranPeer.HEADCONSIGNNAME)));
            setHead_CemPhone(nullStr((String)data.get(DbVeteranPeer.HEADCEMETERYPHONE)));
            setHead_CemNameAddr(nullStr((String)data.get(DbVeteranPeer.HEADCEMNAME)));
            setHead_Remarks(nullStr((String)data.get(DbVeteranPeer.HEADREMARKS)));
            setHead_HighestRank(nullStr((String)data.get(DbVeteranPeer.HEADHIGHESTRANK)));
            setHead_StoneZ(				FormatNumber.parseShort(data.get(DbVeteranPeer.STONENICHE).toString()));
            setHead_StoneV(				FormatNumber.parseShort(data.get(DbVeteranPeer.STONEUPRIGHT).toString()));
            setBen1_dayPhone(nullStr((String)data.get(DbVeteranPeer.BEN1DAYPHONE)));
            setBen1_nightPhone(nullStr((String)data.get(DbVeteranPeer.BEN1NIGHTPHONE)));
            head_BranchMM				= FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBRANCHMM).toString());
            head_ValorBSM				= FormatNumber.parseShort(data.get(DbVeteranPeer.HEADVALORBSM).toString());
            head_WarPG					= FormatNumber.parseShort(data.get(DbVeteranPeer.HEADWARPG).toString());
            head_BeliefNone				= FormatNumber.parseShort(data.get(DbVeteranPeer.HEADBELIEFNONE).toString());
            
            /* #ticket : #5425 added by Bhavesh Shah */
            setDeathPlace((String)data.get(DbVeteranPeer.BEN1DEATHOCCUR));
            setDeathPlaceOther((String)data.get(DbVeteranPeer.BEN1DEATHOCCUROTHER));
            setEmail((String)data.get(DbVeteranPeer.BEN1CLAIMANTEMAIL));
            setBen1EmplopyerID((String)data.get(DbVeteranPeer.BEN1EMPLOYERID));

            
        } catch (Exception e) {
            logger2.error("Error in restore() : ", e);
            throw new com.aldorsolutions.webfdms.database.PersistenceException(e);
        }
        
    }
    public void setBen1_Box10BurialPlace(String lcl_arg0) {
        ben1_Box10BurialPlace = lcl_arg0;
        modify();
    }
    public void setBen1_Box11No(short lcl_arg0) {
        ben1_Box11No = lcl_arg0;
        modify();
    }
    public void setBen1_Box11Yes(short lcl_arg0) {
        ben1_Box11Yes = lcl_arg0;
        modify();
    }
    public void setBen1_Box12No(short lcl_arg0) {
        ben1_Box12No = lcl_arg0;
        modify();
    }
    public void setBen1_Box12Yes(short lcl_arg0) {
        ben1_Box12Yes = lcl_arg0;
        modify();
    }
    public void setBen1_Box13DueCemetery(short lcl_arg0) {
        ben1_Box13DueCemetery = lcl_arg0;
        modify();
    }
    public void setBen1_Box13DueFD(short lcl_arg0) {
        ben1_Box13DueFD = lcl_arg0;
        modify();
    }
    public void setBen1_Box13None(short lcl_arg0) {
        ben1_Box13None = lcl_arg0;
        modify();
    }
    public void setBen1_Box13OtherPerson(short lcl_arg0) {
        ben1_Box13OtherPerson = lcl_arg0;
        modify();
    }
    public void setBen1_Box13PaidClaimant(short lcl_arg0) {
        ben1_Box13PaidClaimant = lcl_arg0;
        modify();
    }
    public void setBen1_Box14NameAddr(String lcl_arg0) {
        ben1_Box14NameAddr = lcl_arg0;
        modify();
    }
    public void setBen1_Box16AmountPaid(int lcl_arg0) {
        modify();
        ben1_Box16AmountPaid = lcl_arg0;
    }
    public void setBen1_Box16TotBurialExp(int lcl_arg0) {
        ben1_Box16TotBurialExp = lcl_arg0;
        modify();
    }
    public void setBen1_Box17WhoseFunds(String lcl_arg0) {
        ben1_Box17WhoseFunds = lcl_arg0;
        modify();
    }
    public void setBen1_Box18aNo(short lcl_arg0) {
        ben1_Box18aNo = lcl_arg0;
        modify();
    }
    public void setBen1_Box18aYes(short lcl_arg0) {
        ben1_Box18aYes = lcl_arg0;
        modify();
    }
    public void setBen1_Box18bAmtReimb(int lcl_arg0) {
        ben1_Box18bAmtReimb = lcl_arg0;
        modify();
    }
    public void setBen1_Box18cSourceReimb(String lcl_arg0) {
        ben1_Box18cSourceReimb = lcl_arg0;
        modify();
    }
    public void setBen1_Box19aNo(short lcl_arg0) {
        ben1_Box19aNo = lcl_arg0;
        modify();
    }
    public void setBen1_Box19aYes(short lcl_arg0) {
        ben1_Box19aYes = lcl_arg0;
        modify();
    }
    public void setBen1_Box19bAmount(int lcl_arg0) {
        ben1_Box19bAmount = lcl_arg0;
        modify();
    }
    public void setBen1_Box19cSource(String lcl_arg0) {
        ben1_Box19cSource = lcl_arg0;
        modify();
    }
    public void setBen1_Box20No(short lcl_arg0) {
        ben1_Box20No = lcl_arg0;
        modify();
    }
    public void setBen1_Box20Yes(short lcl_arg0) {
        ben1_Box20Yes = lcl_arg0;
        modify();
    }
    public void setBen1_Box8OtherName(String lcl_arg0) {
        ben1_Box8OtherName = lcl_arg0;
        modify();
    }
    public void setBen1_Box9No(short lcl_arg0) {
        ben1_Box9No = lcl_arg0;
        modify();
    }
    public void setBen1_Box9Yes(short lcl_arg0) {
        ben1_Box9Yes = lcl_arg0;
        modify();
    }
    public void setBen1_BurialDate(String lcl_arg0) {
        ben1_BurialDate = lcl_arg0;
        modify();
    }
    public void setBen1_ClaimantCityStZip(String lcl_arg0) {
        ben1_ClaimantCityStZip = lcl_arg0;
        modify();
    }
    public void setBen1_ClaimantName(String lcl_arg0) {
        ben1_ClaimantName = lcl_arg0;
        modify();
    }
    public void setBen1_ClaimantStreet(String lcl_arg0) {
        ben1_ClaimantStreet = lcl_arg0;
        modify();
    }
    public void setBen1_DateEntered1(String lcl_arg0) {
        ben1_DateEntered1 = lcl_arg0;
        modify();
    }
    public void setBen1_DateEntered2(String lcl_arg0) {
        ben1_DateEntered2 = lcl_arg0;
        modify();
    }
    public void setBen1_DateEntered3(String lcl_arg0) {
        ben1_DateEntered3 = lcl_arg0;
        modify();
    }
    public void setBen1_dayPhone(String lcl_arg0) {
        ben1_dayPhone = lcl_arg0;
        modify();
    }
    public void setBen1_GradeRank1(String lcl_arg0) {
        ben1_GradeRank1 = lcl_arg0;
        modify();
    }
    public void setBen1_GradeRank2(String lcl_arg0) {
        ben1_GradeRank2 = lcl_arg0;
        modify();
    }
    public void setBen1_GradeRank3(String lcl_arg0) {
        ben1_GradeRank3 = lcl_arg0;
        modify();
    }
    public void setBen1_nightPhone(String lcl_arg0) {
        ben1_nightPhone = lcl_arg0;
        modify();
    }
    public void setBen1_PlaceBirth(String lcl_arg0) {
        ben1_PlaceBirth = lcl_arg0;
        modify();
    }
    public void setBen1_PlaceDeath(String lcl_arg0) {
        ben1_PlaceDeath = lcl_arg0;
        modify();
    }
    public void setBen1_PlaceEntered1(String lcl_arg0) {
        ben1_PlaceEntered1 = lcl_arg0;
        modify();
    }
    public void setBen1_PlaceEntered2(String lcl_arg0) {
        ben1_PlaceEntered2 = lcl_arg0;
        modify();
    }
    public void setBen1_PlaceEntered3(String lcl_arg0) {
        ben1_PlaceEntered3 = lcl_arg0;
        modify();
    }
    public void setBen1_SepSrvcDate1(String lcl_arg0) {
        ben1_SepSrvcDate1 = lcl_arg0;
        modify();
    }
    public void setBen1_SepSrvcDate2(String lcl_arg0) {
        ben1_SepSrvcDate2 = lcl_arg0;
        modify();
    }
    public void setBen1_SepSrvcDate3(String lcl_arg0) {
        ben1_SepSrvcDate3 = lcl_arg0;
        modify();
    }
    public void setBen1_SepSrvcPlace1(String lcl_arg0) {
        ben1_SepSrvcPlace1 = lcl_arg0;
        modify();
    }
    public void setBen1_SepSrvcPlace2(String lcl_arg0) {
        ben1_SepSrvcPlace2 = lcl_arg0;
        modify();
    }
    public void setBen1_SepSrvcPlace3(String lcl_arg0) {
        ben1_SepSrvcPlace3 = lcl_arg0;
        modify();
    }
    public void setBen1_ServiceNo1(String lcl_arg0) {
        ben1_ServiceNo1 = lcl_arg0;
        modify();
    }
    public void setBen1_ServiceNo2(String lcl_arg0) {
        ben1_ServiceNo2 = lcl_arg0;
        modify();
    }
    public void setBen1_ServiceNo3(String lcl_arg0) {
        modify();
        ben1_ServiceNo3 = lcl_arg0;
    }
    public void setBen2_Box21(String lcl_arg0) {
        ben2_Box21 = lcl_arg0;
        modify();
    }
    public void setBen2_Box22Place1(String lcl_arg0) {
        ben2_Box22Place1 = lcl_arg0;
        modify();
    }
    public void setBen2_Box22Place2(String lcl_arg0) {
        ben2_Box22Place2 = lcl_arg0;
        modify();
    }
    public void setBen2_Box23aCost(int lcl_arg0) {
        modify();
        ben2_Box23aCost = lcl_arg0;
    }
    public void setBen2_Box23bDatePurch(String lcl_arg0) {
        ben2_Box23bDatePurch = lcl_arg0;
        modify();
    }
    public void setBen2_Box23cDatePayment(String lcl_arg0) {
        ben2_Box23cDatePayment = lcl_arg0;
        modify();
    }
    public void setBen2_Box24aNo(short lcl_arg0) {
        ben2_Box24aNo = lcl_arg0;
        modify();
    }
    public void setBen2_Box24aYes(short lcl_arg0) {
        ben2_Box24aYes = lcl_arg0;
        modify();
    }
    public void setBen2_Box24bAmtPaid(int lcl_arg0) {
        ben2_Box24bAmtPaid = lcl_arg0;
        modify();
    }
    public void setBen2_Box25WhoseFunds(String lcl_arg0) {
        ben2_Box25WhoseFunds = lcl_arg0;
        modify();
    }
    public void setBen2_Box26aNo(short lcl_arg0) {
        ben2_Box26aNo = lcl_arg0;
        modify();
    }
    public void setBen2_Box26aYes(short lcl_arg0) {
        ben2_Box26aYes = lcl_arg0;
        modify();
    }
    public void setBen2_Box26bAmount(int lcl_arg0) {
        ben2_Box26bAmount = lcl_arg0;
        modify();
    }
    public void setBen2_Box26cSource(String lcl_arg0) {
        ben2_Box26cSource = lcl_arg0;
        modify();
    }
    public void setBen2_Box27aNo(short lcl_arg0) {
        ben2_Box27aNo = lcl_arg0;
        modify();
    }
    public void setBen2_Box27aYes(short lcl_arg0) {
        ben2_Box27aYes = lcl_arg0;
        modify();
    }
    public void setBen2_Box27bAmount(int lcl_arg0) {
        ben2_Box27bAmount = lcl_arg0;
        modify();
    }
    public void setBen2_Box27cSource(String lcl_arg0) {
        ben2_Box27cSource = lcl_arg0;
        modify();
    }
    public void setBen2_Box28bOfficialPos(String lcl_arg0) {
        ben2_Box28bOfficialPos = lcl_arg0;
        modify();
    }
    public void setBen2_Box29Agency(String lcl_arg0) {
        ben2_Box29Agency = lcl_arg0;
        modify();
    }
    public void setBen2_Box30bName(String lcl_arg0) {
        ben2_Box30bName = lcl_arg0;
        modify();
    }
    public void setBen2_Box31Address(String lcl_arg0) {
        ben2_Box31Address = lcl_arg0;
        modify();
    }
    public void setBen2_Box32date(String lcl_arg0) {
        modify();
        ben2_Box32date = lcl_arg0;
    }
    public void setBen2_Box33Relationship(String lcl_arg0) {
        ben2_Box33Relationship = lcl_arg0;
        modify();
    }
    public void setFlag_After55Box(short lcl_arg0) {
        flag_After55Box = lcl_arg0;
        modify();
    }
    public void setFlag_AirForceBox(short lcl_arg0) {
        flag_AirForceBox = lcl_arg0;
        modify();
    }
    public void setFlag_ApplicantAddr1(String lcl_arg0) {
        flag_ApplicantAddr1 = lcl_arg0;
        modify();
    }
    public void setFlag_ApplicantAddr2(String lcl_arg0) {
        flag_ApplicantAddr2 = lcl_arg0;
        modify();
    }
    public void setFlag_ApplicantDate(String lcl_arg0) {
        flag_ApplicantDate = lcl_arg0;
        modify();
    }
    public void setFlag_ApplicantRelation(String lcl_arg0) {
        flag_ApplicantRelation = lcl_arg0;
        modify();
    }
    public void setFlag_ArmyBox(short lcl_arg0) {
        flag_ArmyBox = lcl_arg0;
        modify();
    }
    public void setFlag_BirthDate(String lcl_arg0) {
        flag_BirthDate = lcl_arg0;
        modify();
    }
    public void setFlag_BurialDate(String lcl_arg0) {
        modify();
        flag_BurialDate = lcl_arg0;
    }
    public void setFlag_BurialPlace(String lcl_arg0) {
        modify();
        flag_BurialPlace = lcl_arg0;
    }
    public void setFlag_CoastGaurdBox(short lcl_arg0) {
        modify();
        flag_CoastGaurdBox = lcl_arg0;
    }
    public void setFlag_Condition1Box(short lcl_arg0) {
        modify();
        flag_Condition1Box = lcl_arg0;
    }
    public void setFlag_Condition2Box(short lcl_arg0) {
        flag_Condition2Box = lcl_arg0;
        modify();
    }
    public void setFlag_Condition3Box(short lcl_arg0) {
        flag_Condition3Box = lcl_arg0;
        modify();
    }
    public void setFlag_Condition4Box(short lcl_arg0) {
        flag_Condition4Box = lcl_arg0;
        modify();
    }
    public void setFlag_DeathDate(String lcl_arg0) {
        flag_DeathDate = lcl_arg0;
        modify();
    }
    public void setFlag_DischargeDate(String lcl_arg0) {
        flag_DischargeDate = lcl_arg0;
        modify();
    }
    public void setFlag_EnlistmentDate(String lcl_arg0) {
        flag_EnlistmentDate = lcl_arg0;
        modify();
    }
    public void setFlag_KoreanBox(short lcl_arg0) {
        modify();
        flag_KoreanBox = lcl_arg0;
    }
    public void setFlag_MarineBox(short lcl_arg0) {
        flag_MarineBox = lcl_arg0;
        modify();
    }
    public void setFlag_NavyBox(short lcl_arg0) {
        flag_NavyBox = lcl_arg0;
        modify();
    }
    public void setFlag_OtherBranchBox(short lcl_arg0) {
        flag_OtherBranchBox = lcl_arg0;
        modify();
    }
    public void setFlag_OtherServiceBox(short lcl_arg0) {
        flag_OtherServiceBox = lcl_arg0;
        modify();
    }
    public void setFlag_PersonReceiveFlag(String lcl_arg0) {
        flag_PersonReceiveFlag = lcl_arg0;
        modify();
    }
    public void setFlag_ReceiveAddr1(String lcl_arg0) {
        modify();
        flag_ReceiveAddr1 = lcl_arg0;
    }
    public void setFlag_ReceiveAddr2(String lcl_arg0) {
        flag_ReceiveAddr2 = lcl_arg0;
        modify();
    }
    public void setFlag_ReceiveRelation(String lcl_arg0) {
        flag_ReceiveRelation = lcl_arg0;
        modify();
    }
    public void setFlag_Remarks(String lcl_arg0) {
        flag_Remarks = lcl_arg0;
        modify();
    }
    public void setFlag_SerialNo(String lcl_arg0) {
        flag_SerialNo = lcl_arg0;
        modify();
    }
    public void setFlag_SocSecNo(String lcl_arg0) {
        flag_SocSecNo = lcl_arg0;
        modify();
    }
    public void setFlag_SpanishAmerBox(short lcl_arg0) {
        modify();
        flag_SpanishAmerBox = lcl_arg0;
    }
    public void setFlag_VAfileNumber(String lcl_arg0) {
        modify();
        flag_VAfileNumber = lcl_arg0;
    }
    public void setFlag_VetName(String lcl_arg0) {
        modify();
        flag_VetName = lcl_arg0;
    }
    public void setFlag_VietnamBox(short lcl_arg0) {
        modify();
        flag_VietnamBox = lcl_arg0;
    }
    public void setFlag_WWIBox(short lcl_arg0) {
        modify();
        flag_WWIBox = lcl_arg0;
    }
    public void setFlag_WWIIBox(short lcl_arg0) {
        flag_WWIIBox = lcl_arg0;
        modify();
    }
    
    /* headstone fields */
    public void setHead_RequestInitial(short lcl_arg0) {
        head_RequestInitial= lcl_arg0;
        modify();
    }
    
    public void setHead_RequestSecond(short lcl_arg0) {
        head_RequestSecond = lcl_arg0;
        modify();
    }
    
    public void setHead_RequestCorrected(short lcl_arg0) {
        head_RequestCorrected= lcl_arg0;
        modify();
    }
    
    public void setHead_ValorOther(short lcl_arg0) {
        head_ValorOther= lcl_arg0;
        modify();
    }
    
    public void setHead_ValorOtherSpecify(String lcl_arg0) {
        head_ValorOtherSpecify= lcl_arg0;
        modify();
    }
    public void setHead_RelOther(short lcl_arg0) {
        head_RelOther = lcl_arg0;
        modify();
    }
    
    public void setHead_RelOtherSpecify(String lcl_arg0) {
        head_RelOtherSpecify = lcl_arg0;
        modify();
    }
    
    public void setHead_GraveMarked(short lcl_arg0) {
        head_GraveMarked = lcl_arg0;
        modify();
    }
    
    public void setHead_ApplicantNameAddr(String lcl_arg0) {
        modify();
        head_ApplicantNameAddr = lcl_arg0;
    }
    
    public void setHead_ApplicantPhone(String lcl_arg0) {        
        head_ApplicantPhone = lcl_arg0;
        modify();
    }
    public void setHead_ApplicantFax(String lcl_arg0) {        
        head_ApplicantFax = lcl_arg0;
        modify();
    }
    public void setHead_ApplicantEmail(String lcl_arg0) {        
        head_ApplicantEmail = lcl_arg0;
        modify();
    }
    
    public void setHead_Belief1(short lcl_arg0) {        
        head_Belief1 = lcl_arg0;
        modify();
    }
    public void setHead_Belief2(short lcl_arg0) {        
        head_Belief2 = lcl_arg0;
        modify();
    }
    public void setHead_Belief3(short lcl_arg0) {
        head_Belief3 = lcl_arg0;
        modify();
    }
    public void setHead_Belief4(short lcl_arg0) {
        head_Belief4 = lcl_arg0;
        modify();
    }
    
    /* New additions for Flag Application */
    public void setFlag_VetOtherName(String lcl_arg0) {        
        flag_VetOtherName = lcl_arg0;
        modify();
    }
    public void setFlag_OtherBranchOfService(String lcl_arg0) {        
        flag_OtherBranchOfService = lcl_arg0;
        modify();
    }    
    public void setFlag_OtherVetService(String lcl_arg0) {        
        flag_OtherVetService = lcl_arg0;    
        modify();
    }    
    public void setFlag_SelReserveBox(short lcl_arg0) {        
        flag_SelReserveBox = lcl_arg0;
        modify();
    }    
    public void setFlag_GulfBox(short lcl_arg0) {        
        flag_GulfBox = lcl_arg0;        
        modify();
    }    
    public void setFlag_Condition5Box(short lcl_arg0) {        
        flag_Condition5Box = lcl_arg0;
        modify();
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 10:05:29 AM)
     * @param newHead_beliefNone short
     */
    public void setHead_BeliefNone(short newHead_beliefNone) {
        head_BeliefNone = newHead_beliefNone;
        modify();
    }
    public void setHead_BeliefSpecify(String lcl_arg0) {
        head_BeliefSpecify = lcl_arg0;
        modify();
    }
    public void setHead_Box2No(short lcl_arg0) {
        head_Box2No = lcl_arg0;
        modify();
    }
    public void setHead_Box2Yes(short lcl_arg0) {
        modify();
        head_Box2Yes = lcl_arg0;
    }
    public void setHead_BranchAC(short lcl_arg0) {
        head_BranchAC = lcl_arg0;
        modify();
    }
    public void setHead_BranchAF(short lcl_arg0) {
        head_BranchAF = lcl_arg0;
        modify();
    }
    public void setHead_BranchAR(short lcl_arg0) {
        modify();
        head_BranchAR = lcl_arg0;
    }
    public void setHead_BranchCG(short lcl_arg0) {
        modify();
        head_BranchCG = lcl_arg0;
    }
    public void setHead_BranchMC(short lcl_arg0) {
        modify();
        head_BranchMC = lcl_arg0;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 10:04:25 AM)
     * @param newHead_BranchMM short
     */
    public void setHead_BranchMM(short newHead_BranchMM) {
        head_BranchMM = newHead_BranchMM;
    }
    public void setHead_BranchNA(short lcl_arg0) {        
        head_BranchNA = lcl_arg0;
        modify();
    }
    public void setHead_BranchOther(short lcl_arg0) {        
        head_BranchOther = lcl_arg0;
        modify();
    }
    public void setHead_BranchSpecify(String lcl_arg0) {        
        head_BranchSpecify = lcl_arg0;
        modify();
    }
    public void setHead_CemNameAddr(String lcl_arg0) {
        modify();
        head_CemNameAddr = lcl_arg0;
    }
    public void setHead_CemPhone(String lcl_arg0) {        
        head_CemPhone = lcl_arg0;
        modify();
    }
    public void setHead_ConsigneeNameAddr(String lcl_arg0) {        
        head_ConsigneeNameAddr = lcl_arg0;
        modify();
    }
    public void setHead_Fname(String lcl_arg0) {
        head_Fname = lcl_arg0;        
        modify();
    }
    public void setHead_GraveID(String lcl_arg0) {        
        head_GraveID = lcl_arg0;
        modify();
    }
    public void setHead_GraveNumber(String lcl_arg0) {        
        head_GraveNumber = lcl_arg0;
        modify();
    }
    public void setHead_GraveSection(String lcl_arg0) {
        head_GraveSection = lcl_arg0;
        modify();
    }
    public void setHead_HighestRank(String lcl_arg0) {
        head_HighestRank = lcl_arg0;
        modify();
    }
    public void setHead_Lname(String lcl_arg0) {        
        head_Lname = lcl_arg0;
        modify();
    }
    
    public void setHead_Suffix(String lcl_arg0) {        
        head_Suffix = lcl_arg0;
        modify();
    }
    
    public void setHead_Mname(String lcl_arg0) {        
        head_Mname = lcl_arg0;
        modify();
    }
    public void setHead_PrivCemeterID(String lcl_arg0) {        
        head_PrivCemeterID = lcl_arg0;
        modify();
    }
    public void setHead_Relationship(String lcl_arg0) {
        modify();
        head_Relationship = lcl_arg0;
    }
    public void setHead_Remarks(String lcl_arg0) {
        modify();
        head_Remarks = lcl_arg0;
    }
    public void setHead_StoneB(short lcl_arg0) {
        modify();
        head_StoneB = lcl_arg0;
    }
    public void setHead_StoneF(short lcl_arg0) {
        modify();
        head_StoneF = lcl_arg0;
    }
    public void setHead_StoneG(short lcl_arg0) {
        modify();
        head_StoneG = lcl_arg0;
    }
    public void setHead_StoneU(short lcl_arg0) {
        modify();
        head_StoneU = lcl_arg0;
    }
    public void setHead_StoneV(short lcl_arg0) {
        head_StoneV = lcl_arg0;
        modify();
    }
    public void setHead_StoneZ(short lcl_arg0) {
        modify();
        head_StoneZ = lcl_arg0;
    }
    public void setHead_ValorAFC(short lcl_arg0) {
        modify();
        head_ValorAFC = lcl_arg0;
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 10:04:51 AM)
     * @param newHead_valorBSM short
     */
    public void setHead_ValorBSM(short newHead_valorBSM) {
        head_ValorBSM = newHead_valorBSM;
        modify();
    }
    public void setHead_ValorDSC(short lcl_arg0) {
        modify();
        head_ValorDSC = lcl_arg0;
    }
    public void setHead_ValorMOH(short lcl_arg0) {
        head_ValorMOH = lcl_arg0;
        modify();
    }
    public void setHead_ValorNC(short lcl_arg0) {
        head_ValorNC = lcl_arg0;
        modify();
    }
    public void setHead_ValorPH(short lcl_arg0) {
        modify();
        head_ValorPH = lcl_arg0;
    }
    public void setHead_ValorSS(short lcl_arg0) {
        modify();
        head_ValorSS = lcl_arg0;
    }
    public void setHead_WarKO(short lcl_arg0) {
        modify();
        head_WarKO = lcl_arg0;
    }
    public void setHead_WarOther(short lcl_arg0) {
        modify();
        head_WarOther = lcl_arg0;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 10:05:07 AM)
     * @param newHead_warPG short
     */
    public void setHead_WarPG(short newHead_warPG) {
        head_WarPG = newHead_warPG;
        modify();
    }
    public void setHead_WarSpecify(String lcl_arg0) {
        modify();
        head_WarSpecify = lcl_arg0;
    }
    public void setHead_WarVN(short lcl_arg0) {
        modify();
        head_WarVN = lcl_arg0;
    }
    public void setHead_WarWWI(short lcl_arg0) {
        modify();
        head_WarWWI = lcl_arg0;
    }
    public void setHead_WarWWII(short lcl_arg0) {
        modify();
        head_WarWWII = lcl_arg0;
    }
    /**
     * Get the ID key for this object from the hashtable.
     * DbUser objects can be accessed by "Name"
     * So, first see if "Name" is being used for restoring
     * or if ID# is being used.
     */
    public void setId(java.util.Hashtable h) {
        //if (h.containsKey(DbVeteranPeer.NAME)){
        //	getPersistentPeer().restore(this, t);
        //}
        setId(((Integer)h.get(DbVeteranPeer.CASE_ID)).intValue());
    }
    public void setLMilitaryMainKey(int lcl_arg0) {
        lMilitaryMainKey = lcl_arg0;
        modify();
        setId(lcl_arg0);
    }
    
    public String nullStr(String str) {
        return (str != null) ? str : "";
    }
	/**
	 * @return the flag_BurialCity
	 */
	public String getFlag_BurialCity() {
		return flag_BurialCity;
	}
	/**
	 * @param flag_BurialCity the flag_BurialCity to set
	 */
	public void setFlag_BurialCity(String flag_BurialCity) {
		this.flag_BurialCity = flag_BurialCity;
        modify();
	}
	/**
	 * @return the flag_BurialState
	 */
	public String getFlag_BurialState() {
		return flag_BurialState;
	}
	/**
	 * @param flag_BurialState the flag_BurialState to set
	 */
	public void setFlag_BurialState(String flag_BurialState) {
		this.flag_BurialState = flag_BurialState;
        modify();
	}
	/**
	 * @return the flag_BurialStreet
	 */
	public String getFlag_BurialStreet() {
		return flag_BurialStreet;
	}
	/**
	 * @param flag_BurialStreet the flag_BurialStreet to set
	 */
	public void setFlag_BurialStreet(String flag_BurialStreet) {
		this.flag_BurialStreet = flag_BurialStreet;
        modify();
	}
	/**
	 * @return the flag_BurialZipCode
	 */
	public String getFlag_BurialZipCode() {
		return flag_BurialZipCode;
	}
	/**
	 * @param flag_BurialZipCode the flag_BurialZipCode to set
	 */
	public void setFlag_BurialZipCode(String flag_BurialZipCode) {
		this.flag_BurialZipCode = flag_BurialZipCode;
        modify();
	}
	public String getFlag_DocShowEligibility() {
		return flag_DocShowEligibility;
	}
	public void setFlag_DocShowEligibility(String flag_DocShowEligibility) {
		this.flag_DocShowEligibility = flag_DocShowEligibility;
	}
	/**
	 * @return the deathPlace
	 */
	public String getDeathPlace() {
		return deathPlace;
	}
	/**
	 * @param deathPlace the deathPlace to set
	 */
	public void setDeathPlace(String deathPlace) {
		this.deathPlace = deathPlace;
	}
	/**
	 * @return the deathPlaceOther
	 */
	public String getDeathPlaceOther() {
		return deathPlaceOther;
	}
	/**
	 * @param deathPlaceOther the deathPlaceOther to set
	 */
	public void setDeathPlaceOther(String deathPlaceOther) {
		this.deathPlaceOther = deathPlaceOther;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the ben1EmplopyerID
	 */
	public String getBen1EmplopyerID() {
		return ben1EmplopyerID;
	}
	/**
	 * @param ben1EmplopyerID the ben1EmplopyerID to set
	 */
	public void setBen1EmplopyerID(String ben1EmplopyerID) {
		this.ben1EmplopyerID = ben1EmplopyerID;
	}
    
	
    
}
