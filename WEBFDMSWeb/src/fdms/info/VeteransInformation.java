package fdms.info;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.VaBenefitsForm;
import fdms.ui.struts.form.VaFlagForm;
import fdms.ui.struts.form.VaStoneForm;

public class VeteransInformation {

	public VaStoneForm getVaStoneForms(ReadWriteExcel excel, XSSFRow row, int val) {
		// read data from excel rows
		VaStoneForm form = new VaStoneForm();
		
		try {
			form.setVitalsMasterKey(String.valueOf((int) NumberUtils.toDouble(excel.getCellValue(row, 87 + val))));
			form.setDirective("save");
			
			form.setHeadRequestType(excel.getCellValue(row, 1024 +  val));
			 
			form.setHeadFirstName(excel.getCellValue(row, 1025 +  val));
			form.setHeadMiddleName(excel.getCellValue(row, 1026 +  val));
			form.setHeadLastName(excel.getCellValue(row, 1027 +  val));
			form.setHeadSuffix(excel.getCellValue(row, 1028 +  val));
			 
			form.setSsn(excel.getCellValue(row, 1029 +  val));
			form.setSerialNumber(excel.getCellValue(row, 1030 +  val));
			form.setBirthMonth(excel.getCellValue(row, 1031 +  val));
			form.setBirthDay(excel.getCellValue(row, 1032 +  val));
			form.setBirthYear(excel.getCellValue(row, 1033 +  val));
			form.setDeathMonth(excel.getCellValue(row, 1034 +  val));
			form.setDeathDay(excel.getCellValue(row, 1035 +  val));
			form.setDeathYear(excel.getCellValue(row, 1036 +  val));
			 
			form.setHeadBox2(excel.getCellValue(row, 1037 +  val).equals("Y") ? true : false);
			 
			form.setEnteredMonth1(excel.getCellValue(row, 1038 +  val));
			form.setEnteredDay1(excel.getCellValue(row, 1039 +  val));
			form.setEnteredYear1(excel.getCellValue(row, 1040 +  val));
			form.setEnteredMonth2(excel.getCellValue(row, 1041 +  val));
			form.setEnteredDay2(excel.getCellValue(row, 1042 +  val));
			form.setEnteredYear2(excel.getCellValue(row, 1043 +  val));
			 
			form.setSeparatedMonth1(excel.getCellValue(row, 1044 +  val));
			form.setSeparatedDay1(excel.getCellValue(row, 1045 +  val));
			form.setSeparatedYear1(excel.getCellValue(row, 1046 +  val));
			form.setSeparatedMonth2(excel.getCellValue(row, 1047 +  val));
			form.setSeparatedDay2(excel.getCellValue(row, 1048 +  val));
			form.setSeparatedYear2(excel.getCellValue(row, 1049 +  val));
			 
			form.setHeadBranchArmy(excel.getCellValue(row, 1050 +  val).equals("Y") ? true : false);
			form.setHeadBranchNavy(excel.getCellValue(row, 1051 +  val).equals("Y") ? true : false);
			form.setHeadBranchMarines(excel.getCellValue(row, 1052 +  val).equals("Y") ? true : false);
			form.setHeadBranchCoastGua(excel.getCellValue(row, 1053 +  val).equals("Y") ? true : false);
			form.setHeadBranchAirForce(excel.getCellValue(row, 1054 +  val).equals("Y") ? true : false);
			form.setHeadBranchArmyAir(excel.getCellValue(row, 1055 +  val).equals("Y") ? true : false);
			form.setHeadBranchMerchantMarines(excel.getCellValue(row, 1056 +  val).equals("Y") ? true : false);
			form.setHeadBranchSpecify(excel.getCellValue(row, 1057 +  val));
			form.setHeadBranchOther(!excel.getCellValue(row, 1058 +  val).equals("") ? true : false);
			form.setHeadHighestRank(excel.getCellValue(row, 1058 +  val));
			 
			form.setHeadWarWWII(excel.getCellValue(row, 1059 +  val).equals("Y") ? true : false);
			form.setHeadWarKorean(excel.getCellValue(row, 1060 +  val).equals("Y") ? true : false);
			form.setHeadWarVietnam(excel.getCellValue(row, 1061 +  val).equals("Y") ? true : false);
			form.setHeadWarPersianGulf(excel.getCellValue(row, 1062 +  val).equals("Y") ? true : false);
			form.setHeadWarSpecify(excel.getCellValue(row, 1063 +  val));
			form.setHeadWarOther(!excel.getCellValue(row, 1063 +  val).equals("") ? true : false);
			 
			form.setHeadValorMOH(excel.getCellValue(row, 1064 +  val).equals("Y") ? true : false);
			form.setHeadValorDSC(excel.getCellValue(row, 1065 +  val).equals("Y") ? true : false);
			form.setHeadValorNC(excel.getCellValue(row, 1066 +  val).equals("Y") ? true : false);
			form.setHeadValorAFC(excel.getCellValue(row, 1067 +  val).equals("Y") ? true : false);
			form.setHeadValorSS(excel.getCellValue(row, 1068 +  val).equals("Y") ? true : false);
			form.setHeadValorBSM(excel.getCellValue(row, 1069 +  val).equals("Y") ? true : false);
			form.setHeadValorPH(excel.getCellValue(row, 1070 +  val).equals("Y") ? true : false);
			form.setHeadValorOther(!excel.getCellValue(row, 1071 +  val).equals("") ? true : false);
			form.setHeadValorOtherSpecify(excel.getCellValue(row, 1071 +  val));
			 
			form.setStoneType(excel.getCellValue(row, 1072 +  val));
			 
			form.setHeadBeliefNone(excel.getCellValue(row, 1073 +  val).equals("Y") ? true : false);
			form.setHeadBeliefChrist(excel.getCellValue(row, 1074 +  val).equals("Y") ? true : false);
			form.setHeadBeliefBuddhist(excel.getCellValue(row, 1075 +  val).equals("Y") ? true : false);
			form.setHeadBeliefJewish(excel.getCellValue(row, 1076 +  val).equals("Y") ? true : false);
			form.setHeadBeliefOther(!excel.getCellValue(row, 1077 +  val).equals("") ? true : false);
			form.setHeadBeliefSpecify(excel.getCellValue(row, 1077 +  val));
			 
			form.setHeadAppNameAddress(excel.getCellValue(row, 1078 +  val));
			form.setHeadAppPhoneNumber(excel.getCellValue(row, 1079 +  val));
			form.setHeadAppEmail(excel.getCellValue(row, 1080 +  val));
			form.setHeadAppFax(excel.getCellValue(row, 1081 +  val));
			form.setHeadRelationship(excel.getCellValue(row, 1082 +  val));
			form.setHeadRelOtherSpecify(excel.getCellValue(row, 1083 +  val));
			form.setHeadRelOther(!excel.getCellValue(row, 1083 +  val).equals("") ? true : false);
			 
			form.setHeadConsignNameAddr(excel.getCellValue(row, 1084 +  val));
			form.setHeadCemeteryPhone(excel.getCellValue(row, 1085 +  val));
			form.setHeadCemNameAddress(excel.getCellValue(row, 1086 +  val));
			 
			form.setHeadGraveID(excel.getCellValue(row, 1087 +  val));
			form.setHeadGraveSection(excel.getCellValue(row, 1088 +  val));
			form.setHeadGraveNumber(excel.getCellValue(row, 1089 +  val));
			 
			form.setHeadGraveMarkedType(excel.getCellValue(row, 1090 +  val));
			 
			form.setHeadRemarks(excel.getCellValue(row, 1091 +  val));
			form.setAppDate(excel.getCellValue(row, 1092 +  val));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}
	
	public VaBenefitsForm getVaBenefitsForms(ReadWriteExcel excel, XSSFRow row, int val) {
		// read data from excel rows
		VaBenefitsForm form = new VaBenefitsForm();
		try {
			form.setVitalsMasterKey(String.valueOf((int) NumberUtils.toDouble(excel.getCellValue(row, 87 + val))));
			form.setDirective("save");
			
			form.setBen1VetFirstName(excel.getCellValue(row, 953 +  val));
			form.setBen1VetMiddleName(excel.getCellValue(row, 954 +  val));
			form.setBen1VetLastName(excel.getCellValue(row, 955 +  val));
			form.setBen1SSN(excel.getCellValue(row, 956 +  val));
			form.setBen1FileNumber(excel.getCellValue(row, 957 +  val));
			
			form.setBen1ClaimantName(excel.getCellValue(row, 958 +  val));
			form.setBen1ClaimantStreet(excel.getCellValue(row, 959 +  val));
			form.setBen1ClaimCityStZip(excel.getCellValue(row, 960 +  val));
			form.setBen1DayPhone(excel.getCellValue(row, 961 +  val));
			form.setBen1NightPhone(excel.getCellValue(row, 962 +  val));
			
			form.setBen1BirthDate(excel.getCellValue(row, 963 +  val));
			form.setBen1DeathDate(excel.getCellValue(row, 964 +  val));
			form.setBen1BurialDate(excel.getCellValue(row, 965 +  val));
			form.setBen1PlaceBirth(excel.getCellValue(row, 966 +  val));
			form.setBen1PlaceDeath(excel.getCellValue(row, 967 +  val));
			
			form.setBen1DateEntered1(excel.getCellValue(row, 968 +  val));
			form.setBen1PlaceEntered1(excel.getCellValue(row, 969 +  val));
			form.setBen1SepSrvcDate1(excel.getCellValue(row, 970 +  val));
			form.setBen1SepSrvcPlace1(excel.getCellValue(row, 971 +  val));
			form.setBen1ServiceNumber1(excel.getCellValue(row, 972 +  val));
			form.setBen1GradeRank1(excel.getCellValue(row, 973 +  val));
			
			form.setBen1DateEntered2(excel.getCellValue(row, 974 +  val));
			form.setBen1PlaceEntered2(excel.getCellValue(row, 975 +  val));
			form.setBen1SepSrvcDate2(excel.getCellValue(row, 976 +  val));
			form.setBen1SepSrvcPlace2(excel.getCellValue(row, 977 +  val));
			form.setBen1ServiceNumber2(excel.getCellValue(row, 978 +  val));
			form.setBen1GradeRank2(excel.getCellValue(row, 979 +  val));
			
			form.setBen1DateEntered3(excel.getCellValue(row, 980 +  val));
			form.setBen1PlaceEntered3(excel.getCellValue(row, 981 +  val));
			form.setBen1SepSrvcDate3(excel.getCellValue(row, 982 +  val));
			form.setBen1SepSrvcPlace3(excel.getCellValue(row, 983 +  val));
			form.setBen1ServiceNumber3(excel.getCellValue(row, 984 +  val));
			form.setBen1GradeRank3(excel.getCellValue(row, 985 +  val));
			
			form.setBen1Box8OtherName(excel.getCellValue(row, 986 +  val));
			
			form.setBen1Box9(excel.getCellValue(row, 987 +  val));
			
			form.setBen1BurialPlace(excel.getCellValue(row, 988 +  val));
			form.setBen1Box11(excel.getCellValue(row, 989 +  val));
			form.setBen1Box12(excel.getCellValue(row, 990 +  val));
			form.setBen1Box13(excel.getCellValue(row, 991 +  val));
			form.setBen1Box14NameAddr(excel.getCellValue(row, 992 +  val));
			
			form.setBen1Box15TotBurExp(excel.getCellValue(row, 993 +  val));
			form.setBen1Box16AmtPaid(excel.getCellValue(row, 994 +  val));
			form.setBen1Box17WhoseFund(excel.getCellValue(row, 995 +  val));
			form.setBen1Box18(excel.getCellValue(row, 996 +  val));
			form.setBen1Box18bAmtReimb(excel.getCellValue(row, 997 +  val));
			form.setBen1Box18cSrcReimb(excel.getCellValue(row, 998 +  val));
			form.setBen1Box19(excel.getCellValue(row, 999 +  val));
			form.setBen1Box19bAmount(excel.getCellValue(row, 1000 +  val));
			form.setBen1Box19cSource(excel.getCellValue(row, 1001 +  val));
			form.setBen1Box20(excel.getCellValue(row, 1002 +  val));
			
			form.setBen2Box21(excel.getCellValue(row, 1003 +  val));
			form.setBen2Box22Place1(excel.getCellValue(row, 1004 +  val));
			form.setBen2Box22Place2(excel.getCellValue(row, 1005 +  val));
			form.setBen2Box23aCost(excel.getCellValue(row, 1006 +  val));
			form.setBen2Box23bDatePurc(excel.getCellValue(row, 1007 +  val));
			form.setBen2Box23cDatePay(excel.getCellValue(row, 1008 +  val));
			form.setBen2Box24(excel.getCellValue(row, 1009 +  val));
			form.setBen2Box24bAmtPaid(excel.getCellValue(row, 1010 +  val));
			form.setBen2Box25WhoseFund(excel.getCellValue(row, 1011 +  val));
			form.setBen2Box26(excel.getCellValue(row, 1012 +  val));
			form.setBen2Box26bAmount(excel.getCellValue(row, 1013 +  val));
			form.setBen2Box26cSource(excel.getCellValue(row, 1014 +  val));
			form.setBen2Box27(excel.getCellValue(row, 1015 +  val));
			form.setBen2Box27bAmount(excel.getCellValue(row, 1016 +  val));
			form.setBen2Box27cSource(excel.getCellValue(row, 1017 +  val));
			form.setBen2Box28bOfficPos(excel.getCellValue(row, 1018 +  val));
			
			form.setBen2Box29Agency(excel.getCellValue(row, 1019 +  val));
			form.setBen2Box30bName(excel.getCellValue(row, 1020 +  val));
			form.setBen2Box31Address(excel.getCellValue(row, 1021 +  val));
			form.setBen2Box32Date(excel.getCellValue(row, 1022 +  val));
			form.setBen2Box33Relation(excel.getCellValue(row, 1023 +  val));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}
	
	public VaFlagForm getVaFlagForms(ReadWriteExcel excel, XSSFRow row, int val) {
		// read data from excel rows
		VaFlagForm form = new VaFlagForm();
		
		try {
			form.setVitalsMasterKey(String.valueOf((int) NumberUtils.toDouble(excel.getCellValue(row, 87 + val))));
			form.setDirective("save");
			
			form.setVetName(excel.getCellValue(row, 907 +  val));
			form.setVetOtherName(excel.getCellValue(row, 908 +  val));
			form.setArmyBox(excel.getCellValue(row, 909 +  val).equals("Y") ? true : false);
			form.setNavyBox(excel.getCellValue(row, 910 +  val).equals("Y") ? true : false);
			form.setAirForceBox(excel.getCellValue(row, 911 +  val).equals("Y") ? true : false);
			form.setMarineBox(excel.getCellValue(row, 912 +  val).equals("Y") ? true : false);
			form.setCoastGuardBox(excel.getCellValue(row, 913 +  val).equals("Y") ? true : false);
			form.setSelReserveBox(excel.getCellValue(row, 914 +  val).equals("Y") ? true : false);
			form.setOtherBranchBox(!excel.getCellValue(row, 915 +  val).equals("") ? true : false);
			form.setOtherBranchOfService(excel.getCellValue(row, 915 +  val));
			form.setSpanAmerBox(excel.getCellValue(row, 916 +  val).equals("Y") ? true : false);
			form.setWwiiBox(excel.getCellValue(row, 917 +  val).equals("Y") ? true : false);
			form.setAfter55Box(excel.getCellValue(row, 918 +  val).equals("Y") ? true : false);
			form.setGulfBox(excel.getCellValue(row, 919 +  val).equals("Y") ? true : false);
			form.setWwiBox(excel.getCellValue(row, 920 +  val).equals("Y") ? true : false);
			form.setKoreanBox(excel.getCellValue(row, 921 +  val).equals("Y") ? true : false);
			form.setVietnamBox(excel.getCellValue(row, 922 +  val).equals("Y") ? true : false);
			form.setOtherServBox(!excel.getCellValue(row, 923 +  val).equals("") ? true : false);
			form.setOtherVetService(excel.getCellValue(row, 923 +  val));
			form.setCondition1Box(excel.getCellValue(row, 924 +  val).equals("Y") ? true : false);
			form.setCondition2Box(excel.getCellValue(row, 925 +  val).equals("Y") ? true : false);
			form.setCondition3Box(excel.getCellValue(row, 926 +  val).equals("Y") ? true : false);
			form.setCondition4Box(excel.getCellValue(row, 927 +  val).equals("Y") ? true : false);
			form.setCondition5Box(excel.getCellValue(row, 928 +  val).equals("Y") ? true : false);
			form.setPersonReceive(excel.getCellValue(row, 929 +  val));
			form.setReceivAddress1(excel.getCellValue(row, 930 +  val));
			form.setReceivAddress2(excel.getCellValue(row, 931 +  val));
			form.setReceivRelation(excel.getCellValue(row, 932 +  val));
			form.setVaFileNumber(excel.getCellValue(row, 933 +  val));
			form.setSocSecNumber(excel.getCellValue(row, 934 +  val));
			form.setSerialNumber(excel.getCellValue(row, 935 +  val));
			form.setEnlistmentDate(excel.getCellValue(row, 936 +  val));
			form.setDischargeDate(excel.getCellValue(row, 937 +  val));
			form.setBirthDate(excel.getCellValue(row, 938 +  val));
			form.setDeathDate(excel.getCellValue(row, 939 +  val));
			form.setBurialDate(excel.getCellValue(row, 940 +  val));
			form.setBurialPlace(excel.getCellValue(row, 941 +  val));
			form.setBurialStreet(excel.getCellValue(row, 942 +  val));
			form.setBurialCity(excel.getCellValue(row, 943 +  val));
			form.setBurialState(excel.getCellValue(row, 944 +  val));
			form.setBurialZipCode(excel.getCellValue(row, 945 +  val));
			form.setDocShowEligibility(excel.getCellValue(row, 946 +  val));
			form.setRemarks(excel.getCellValue(row, 947 +  val));
			form.setAppAddress1(excel.getCellValue(row, 948 +  val));
			form.setAppAddress2(excel.getCellValue(row, 949 +  val));
			form.setAppRelation(excel.getCellValue(row, 950 +  val));
			form.setAppDate(excel.getCellValue(row, 951 +  val));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}
}