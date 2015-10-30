package com.aldorsolutions.webservice;

import java.util.Date;

import com.aldorsolutions.webservice.xsd.comm.*;

public class ConvertorUtil {
	
	private static ConvertorUtil instance = new ConvertorUtil();
	private ConvertorUtil(){}
	
	public static ConvertorUtil getInstance(){
		return instance;
	}
	
	public String getString(GenderType gender){
		return gender != null ? gender.toString() : null;
	}
	
	public GenderType getGenderType(String param){
		
		GenderType gender = null;
		
		if(!isStringNullOrNill(param)){
			
			if(GenderType._Male.equalsIgnoreCase(param)){
				gender = GenderType.Male;
			}else if(GenderType._Female.equalsIgnoreCase(param)){
				gender = GenderType.Female;
			}
		}
		return gender;
	}
	
	public String getString(DispositionPlaceType dispositionPlaceType){
		return dispositionPlaceType != null ? dispositionPlaceType.toString() : null;
	}
	
	public DispositionPlaceType getDispositionInfoType(String param){
		
		DispositionPlaceType gender = null;
		
		if(!isStringNullOrNill(param)){
			
			if(DispositionPlaceType._Cemetery.equalsIgnoreCase(param)){
				gender = DispositionPlaceType.Cemetery;
			}else if(DispositionPlaceType._Crematory.equalsIgnoreCase(param)){
				gender = DispositionPlaceType.Crematory;
			}else if(DispositionPlaceType._Other.equalsIgnoreCase(param)){
				gender = DispositionPlaceType.Other;
			}
		}
		return gender;
	}
	
	public String getString(CompleteAnswerType answer){
		return answer != null ? answer.toString() : null;
	}
	
	public CompleteAnswerType getCompleteAnswer(String param){
		
		CompleteAnswerType answer = null;
		
		if(!isStringNullOrNill(param)){
			param = param.trim();
			if(CompleteAnswerType._Yes.equalsIgnoreCase(param)){
				answer = CompleteAnswerType.Yes;
			}else if(CompleteAnswerType._No.equalsIgnoreCase(param)){
				answer = CompleteAnswerType.No;
			}
			else if(CompleteAnswerType._Probably.equalsIgnoreCase(param)){
				answer = CompleteAnswerType.Probably;
			}
			else if(CompleteAnswerType._Unknown.equalsIgnoreCase(param)){
				answer = CompleteAnswerType.Unknown;
			}
		}
		return answer;
	}
	
	public CompleteAnswerType getCompleteAnswerByCode(String param){
		
		CompleteAnswerType answer = null;
		
		if(!isStringNullOrNill(param)){
			param = param.trim();
			if("Y".equalsIgnoreCase(param)){
				answer = CompleteAnswerType.Yes;
			}else if("N".equalsIgnoreCase(param)){
				answer = CompleteAnswerType.No;
			}
			else if("P".equalsIgnoreCase(param)){
				answer = CompleteAnswerType.Probably;
			}
			else if("U".equalsIgnoreCase(param)){
				answer = CompleteAnswerType.Unknown;
			}
		}
		return answer;
	}	
	
	public String getString(SimpleAnswerType answer){
		return answer != null ? answer.toString() : null;
	}
	
	public SimpleAnswerType getSimpleAnswerByCode(String param){
		if("Y".equalsIgnoreCase(param)){
			return SimpleAnswerType.Yes;
		}else if("N".equalsIgnoreCase(param)){
			return SimpleAnswerType.No;
		}else if("U".equalsIgnoreCase(param)){
			return SimpleAnswerType.Unknown;
		}
		return null;
	}
	
	public SimpleAnswerType getSimpleAnswer(String param){

		SimpleAnswerType answer = null;

		if(!isStringNullOrNill(param)){

			if(SimpleAnswerType._Yes.equalsIgnoreCase(param)){
				answer = SimpleAnswerType.Yes;
			}
			else if(SimpleAnswerType._No.equalsIgnoreCase(param)){
				answer = SimpleAnswerType.No;
			}
			else if(SimpleAnswerType._Unknown.equalsIgnoreCase(param)){
				answer = SimpleAnswerType.Unknown;
			}
		}
		return answer;
	}	
	
	public String getString(YesNoType answer){
		return answer != null ? answer.toString() : null;
	}
	
	public YesNoType getYesNoAnswerByCode(String param){
		param = param !=null ? param.trim() : null;
		if("Y".equalsIgnoreCase(param)){
			return YesNoType.Yes;
		}else if("N".equalsIgnoreCase(param)){
			return YesNoType.No;
		}
		return null;
	}

	public YesNoType getYesNoAnswer(String param){

		YesNoType answer = null;

		if(!isStringNullOrNill(param)){
			param = param.trim();
			if(YesNoType._Yes.equalsIgnoreCase(param)){
				answer = YesNoType.Yes;
			}
			else if(YesNoType._No.equalsIgnoreCase(param)){
				answer = YesNoType.No;
			}
		}
		return answer;
	}
	
	public String getString(LocalityType locality){
		return locality != null ? locality.toString() : null;
	}
	
	public LocalityType getLocalityType(String param){
		
		LocalityType locality = null;
		
		if(!isStringNullOrNill(param)){
			
			if(LocalityType._value1.equalsIgnoreCase(param)){
				locality = LocalityType.value1;
			}else if(LocalityType._value2.equalsIgnoreCase(param)){
				locality = LocalityType.value2;
			}else if(LocalityType._value3.equalsIgnoreCase(param)){
				locality = LocalityType.value3;
			}else if(LocalityType._value4.equalsIgnoreCase(param)){
				locality = LocalityType.value4;
			}else if(LocalityType._value5.equalsIgnoreCase(param)){
				locality = LocalityType.value5;
			}
		}
		return locality;
	}
	
	public LocalityType getLocalityTypeByCode(String param){
		
		LocalityType locality = null;
		
		if(!isStringNullOrNill(param)){
			param = param.trim();
			if("C".equalsIgnoreCase(param)){
				//city
				locality = LocalityType.value1;
			}else if("V".equalsIgnoreCase(param)){
				//vilage
				locality = LocalityType.value2;
			}else if("T".equalsIgnoreCase(param)){
				//twp - township
				locality = LocalityType.value3;
			}else if("I".equalsIgnoreCase(param)){
				//unincorporated
				locality = LocalityType.value4;
			}else if("U".equalsIgnoreCase(param)){
				//unknown
				locality = LocalityType.value5;
			}
		}
		return locality;
	}
	
	public String getLocalityTypeStringByCode(String param){
		
		String locality = null;
		
		if(!isStringNullOrNill(param)){
			param = param.trim();
			if("C".equalsIgnoreCase(param)){
				//city
				locality = LocalityType._value1;
			}else if("V".equalsIgnoreCase(param)){
				//vilage
				locality = LocalityType._value2;
			}else if("T".equalsIgnoreCase(param)){
				//twp - township
				locality = LocalityType._value3;
			}else if("I".equalsIgnoreCase(param)){
				//unincorporated
				locality = LocalityType._value4;
			}else if("U".equalsIgnoreCase(param)){
				//unknown
				locality = LocalityType._value5;
			}
		}
		return locality;
	}
	
	public  String getString(String param){
		return param;
	}
	
	public  String getString(StrMax03Len param){
		String result = getStringFromStrMax03Len(param);
		return result;
	}
	
	public  String getString(StrMax08Len param){
		String result = getStringFromStrMax08Len(param);
		return result;
	}
	
	public  String getString(StrMax10Len param){
		String result = getStringFromStrMax10Len(param);
		return result;
	}
	
	public  String getString(StrMax14Len param){
		String result = getStringFromStrMax14Len(param);
		return result;
	}
	
	public  String getString(StrMax15Len param){
		String result = getStringFromStrMax15Len(param);
		return result;
	}
	
	public  String getString(StrMax16Len param){
		String result = getStringFromStrMax16Len(param);
		return result;
	}

	public  String getString(StrMax20Len param){
		String result = getStringFromStrMax20Len(param);
		return result;
	}
	
	public  String getString(StrMax24Len param){
		String result = getStringFromStrMax24Len(param);
		return result;
	}
	
	public  String getString(StrMax25Len param){
		String result = getStringFromStrMax25Len(param);
		return result;
	}
	
	public  String getString(StrMax29Len param){
		String result = getStringFromStrMax29Len(param);
		return result;
	}
	
	public  String getString(StrMax27Len param){
		String result = getStringFromStrMax27Len(param);
		return result;
	}
	
	public  String getString(StrMax30Len param){
		String result = getStringFromStrMax30Len(param);
		return result;
	}
	
	public  String getString(StrMax35Len param){
		String result = getStringFromStrMax35Len(param);
		return result;
	}

	public  String getString(StrMax40Len param){
		String result = getStringFromStrMax40Len(param);
		return result;
	}
	
	public  String getString(StrMax49Len param){
		String result = getStringFromStrMax49Len(param);
		return result;
	}
	
	public  String getString(StrMax50Len param){
		String result = getStringFromStrMax50Len(param);
		return result;
	}
	
	public  String getString(StrMax60Len param){
		String result = getStringFromStrMax60Len(param);
		return result;
	}
	
	public  String getString(StrMax100Len param){
		String result = getStringFromStrMax100Len(param);
		return result;
	}
	
	public  String getString(StrMax150Len param){
		String result = getStringFromStrMax150Len(param);
		return result;
	}
	
	public  String getString(StrMax200Len param){
		String result = getStringFromStrMax200Len(param);
		return result;
	}
	
	public  StrMax03Len getStrMax03Len(String param){
		StrMax03Len result = null;
		if(param != null){
			result = new StrMax03Len();
			result.setStrMax03Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax03Len(StrMax03Len strMax03Len){
		return strMax03Len != null ? strMax03Len.toString() : null;
	}
	
	public  StrMax08Len getStrMax08Len(String param){
		StrMax08Len result = null;
		if(!isStringNullOrNill(param)){
			result = new StrMax08Len();
			result.setStrMax08Len(param.trim());
		}
		return result;
	}
	
	public  String getStringFromStrMax08Len(StrMax08Len strMax08Len){
		return strMax08Len != null ? strMax08Len.toString() : null;
	}
	
	public  StrMax10Len getStrMax10Len(String param){
		StrMax10Len result = null;
		if(param != null){
			result = new StrMax10Len();
			result.setStrMax10Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax10Len(StrMax10Len strMax10Len){
		return strMax10Len != null ? strMax10Len.toString() : null;
	}
	
	public  StrMax14Len getStrMax14Len(String param){
		StrMax14Len result = null;
		if(param != null){
			result = new StrMax14Len();
			result.setStrMax14Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax14Len(StrMax14Len strMax14Len){
		return strMax14Len != null ? strMax14Len.toString() : null;
	}

	public  StrMax15Len getStrMax15Len(String param){
		StrMax15Len result = null;
		if(param != null){
			result = new StrMax15Len();
			result.setStrMax15Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax15Len(StrMax15Len strMax15Len){
		return strMax15Len != null ? strMax15Len.toString() : null;
	}

	public  StrMax16Len getStrMax16Len(String param){
		StrMax16Len result = null;
		if(param != null){
			result = new StrMax16Len();
			result.setStrMax16Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax16Len(StrMax16Len strMax16Len){
		return strMax16Len != null ? strMax16Len.toString() : null;
	}

	public  StrMax20Len getStrMax20Len(String param){
		StrMax20Len result = null;
		if(param != null){
			result = new StrMax20Len();
			result.setStrMax20Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax20Len(StrMax20Len strMax20Len){
		return strMax20Len != null ? strMax20Len.toString() : null;
	}
	
	public  StrMax24Len getStrMax24Len(String param){
		StrMax24Len result = null;
		if(param != null){
			result = new StrMax24Len();
			result.setStrMax24Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax24Len(StrMax24Len strMax24Len){
		return strMax24Len != null ? strMax24Len.toString() : null;
	}

	public  StrMax25Len getStrMax25Len(String param){
		StrMax25Len result = null;
		if(param != null){
			result = new StrMax25Len();
			result.setStrMax25Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax25Len(StrMax25Len strMax25Len){
		return strMax25Len != null ? strMax25Len.toString() : null;
	}

	public  StrMax27Len getStrMax27Len(String param){
		StrMax27Len result = null;
		if(param != null){
			result = new StrMax27Len();
			result.setStrMax27Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax27Len(StrMax27Len strMax27Len){
		return strMax27Len != null ? strMax27Len.toString() : null;
	}
	
	public  StrMax29Len getStrMax29Len(String param){
		StrMax29Len result = null;
		if(param != null){
			result = new StrMax29Len();
			result.setStrMax29Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax29Len(StrMax29Len strMax29Len){
		return strMax29Len != null ? strMax29Len.toString() : null;
	}

	public  StrMax30Len getStrMax30Len(String param){
		StrMax30Len result = null;
		if(param != null){
			result = new StrMax30Len();
			result.setStrMax30Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax30Len(StrMax30Len strMax30Len){
		return strMax30Len != null ? strMax30Len.toString() : null;
	}

	public  StrMax35Len getStrMax35Len(String param){
		StrMax35Len result = null;
		if(param != null){
			result = new StrMax35Len();
			result.setStrMax35Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax35Len(StrMax35Len strMax35Len){
		return strMax35Len != null ? strMax35Len.toString() : null;
	}
	
	public  StrMax40Len getStrMax40Len(String param){
		StrMax40Len result = null;
		if(param != null){
			result = new StrMax40Len();
			result.setStrMax40Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax40Len(StrMax40Len strMax40Len){
		return strMax40Len != null ? strMax40Len.toString() : null;
	}
	
	public  StrMax49Len getStrMax49Len(String param){
		StrMax49Len result = null;
		if(param != null){
			result = new StrMax49Len();
			result.setStrMax49Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax49Len(StrMax49Len strMax49Len){
		return strMax49Len != null ? strMax49Len.toString() : null;
	}

	public  StrMax50Len getStrMax50Len(String param){
		StrMax50Len result = null;
		if(param != null){
			result = new StrMax50Len();
			result.setStrMax50Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax50Len(StrMax50Len strMax50Len){
		return strMax50Len != null ? strMax50Len.toString() : null;
	}

	public  StrMax60Len getStrMax60Len(String param){
		StrMax60Len result = null;
		if(param != null){
			result = new StrMax60Len();
			result.setStrMax60Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax60Len(StrMax60Len strMax60Len){
		return strMax60Len != null ? strMax60Len.toString() : null;
	}

	public  StrMax100Len getStrMax100Len(String param){
		StrMax100Len result = null;
		if(param != null){
			result = new StrMax100Len();
			result.setStrMax100Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax100Len(StrMax100Len strMax100Len){
		return strMax100Len != null ? strMax100Len.toString() : null;
	}
	
	public  StrMax150Len getStrMax150Len(String param){
		StrMax150Len result = null;
		if(param != null){
			result = new StrMax150Len();
			result.setStrMax150Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax150Len(StrMax150Len strMax150Len){
		return strMax150Len != null ? strMax150Len.toString() : null;
	}
	
	public  StrMax200Len getStrMax200Len(String param){
		StrMax200Len result = null;
		if(param != null){
			result = new StrMax200Len();
			result.setStrMax200Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax200Len(StrMax200Len strMax200Len){
		return strMax200Len != null ? strMax200Len.toString() : null;
	}
	
	public  StrMax500Len getStrMax500Len(String param){
		StrMax500Len result = null;
		if(param != null){
			result = new StrMax500Len();
			result.setStrMax500Len(param);
		}
		return result;
	}
	
	public  String getStringFromStrMax500Len(StrMax500Len strMax500Len){
		return strMax500Len != null ? strMax500Len.toString() : null;
	}
	
	private boolean isStringNullOrNill(String string){
		return string == null || string.trim().length() == 0;
	}

	public CVTType getCVTTypeByCode(String param) {
		if("C".equalsIgnoreCase(param)){
			return CVTType.City;
		}else if("V".equalsIgnoreCase(param)){
			return CVTType.Village;
		}else if("T".equalsIgnoreCase(param)){
			return CVTType.Township;
		}else if("U".equalsIgnoreCase(param)){
			return CVTType.Unknown;
		}
		return null;
	}

	public CVTType getCVTType(String param) {
		CVTType cvtType = null;
		if(!isStringNullOrNill(param)){
			param = param.trim();
			if(CVTType._City.equalsIgnoreCase(param)){
				cvtType = CVTType.City;
			}else if(CVTType._Village.equalsIgnoreCase(param)){
				cvtType = CVTType.Village;
			}else if(CVTType._Township.equalsIgnoreCase(param)){
				cvtType = CVTType.Township;
			}else if(CVTType._Unknown.equalsIgnoreCase(param)){
				cvtType = CVTType.Unknown;
			}
		}
		return cvtType;
	}

	public String getString(CVTType cvtType) {
		
		return cvtType != null ? cvtType.getValue() : null ;
	}
	
	public OccupationalHistoryType getOccupationalHistoryTypeByCode(String wasDecedentEverInUSArmedForcesByCode, 
			Date armedForcesEntryDate, Date armedForcesDepartureDate, String usualOccupation,
			String kindOfBusinessOrIndustry ,String currentEmployer){
		
		OccupationalHistoryType occupationalHistoryType = null;
		
		if(!isStringNullOrNill(wasDecedentEverInUSArmedForcesByCode) || armedForcesEntryDate != null ||
				armedForcesDepartureDate != null || !isStringNullOrNill(usualOccupation) || 
				!isStringNullOrNill(kindOfBusinessOrIndustry) ||!isStringNullOrNill(currentEmployer)  ){
			
			occupationalHistoryType = new OccupationalHistoryType();
			occupationalHistoryType.setWasDecedentEverInUSArmedForces(getYesNoAnswerByCode(wasDecedentEverInUSArmedForcesByCode));
			occupationalHistoryType.setArmedForcesEntryDate(armedForcesEntryDate);
			occupationalHistoryType.setArmedForcesDepartureDate(armedForcesDepartureDate);
			occupationalHistoryType.setUsualOccupation(getStrMax100Len(usualOccupation));
			occupationalHistoryType.setKindOfBusinessOrIndustry(getStrMax49Len(kindOfBusinessOrIndustry));
			occupationalHistoryType.setCurrentEmployer(getStrMax100Len(currentEmployer));
		}
		
		return occupationalHistoryType;
	}

	public OccupationalHistoryType getOccupationalHistoryType(String wasDecedentEverInUSArmedForces, 
			Date armedForcesEntryDate, Date armedForcesDepartureDate, String usualOccupation,
			String kindOfBusinessOrIndustry ,String currentEmployer){
		
		OccupationalHistoryType occupationalHistoryType = null;
		
		if(!isStringNullOrNill(wasDecedentEverInUSArmedForces) || armedForcesEntryDate != null ||
				armedForcesDepartureDate != null || !isStringNullOrNill(usualOccupation) || 
				!isStringNullOrNill(kindOfBusinessOrIndustry) ||!isStringNullOrNill(currentEmployer)  ){
			
			occupationalHistoryType = new OccupationalHistoryType();
			occupationalHistoryType.setWasDecedentEverInUSArmedForces(getYesNoAnswer(wasDecedentEverInUSArmedForces));
			occupationalHistoryType.setArmedForcesEntryDate(armedForcesEntryDate);
			occupationalHistoryType.setArmedForcesDepartureDate(armedForcesDepartureDate);
			occupationalHistoryType.setUsualOccupation(getStrMax100Len(usualOccupation));
			occupationalHistoryType.setKindOfBusinessOrIndustry(getStrMax49Len(kindOfBusinessOrIndustry));
			occupationalHistoryType.setCurrentEmployer(getStrMax100Len(currentEmployer));
		}
		
		return occupationalHistoryType;
	}

	public String getString(StateCodeType stateAbbreviation) {
		return stateAbbreviation!= null ? stateAbbreviation.toString():null;
	}
	
	public StateCodeType getStateCodeType(String param){
		StateCodeType state = null;
		if(!isStringNullOrNill(param)){
			state = new StateCodeType();
			state.setStateCodeType(param);
		}
		return state;
	}
}
