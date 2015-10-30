package com.aldorsolutions.webservice.util;

import com.aldorsolutions.fdms.to.common.*;
import com.aldorsolutions.fdms.to.common.Options.IntOption;
import com.aldorsolutions.fdms.to.common.Options.StringOption;
import com.aldorsolutions.fdms.to.service.FdmsUserInfo;
import com.aldorsolutions.webservice.ConvertorUtil;
import com.aldorsolutions.webservice.xsd.comm.*;
import com.aldorsolutions.webservice.xsd.comm.fdms.*;
import com.aldorsolutions.webservice.xsd.fdms.info.FdmsInfo;
import com.aldorsolutions.webservice.xsd.fdms.info.IntOptionType;
import com.aldorsolutions.webservice.xsd.fdms.info.OptionsType;
import com.aldorsolutions.webservice.xsd.fdms.info.StringOptionType;
import com.aldorsolutions.webservice.xsd.fdms.info.UserInfo;
import com.aldorsolutions.webservice.xsd.fdms.info.VitalsIdInfo;
import com.aldorsolutions.webservice.xsd.fdms.info.VitalsIdListInfo;


public class ResponseConverter {
	
	public static UserInfo getUserInfo(FdmsUserInfo fdmsUserInfo){
		UserInfo userInfo = new UserInfo();
		
		userInfo.setId(fdmsUserInfo.getId());
		userInfo.setUserName(fdmsUserInfo.getUserName());
		userInfo.setFirstName(fdmsUserInfo.getFirstName());
		userInfo.setLastame(fdmsUserInfo.getLastName());
		userInfo.setCompanyId(fdmsUserInfo.getCompanyId());
		userInfo.setRegionId(fdmsUserInfo.getRegionId());
		userInfo.setLocationId(fdmsUserInfo.getLocationId());
		if(fdmsUserInfo.getLocales().size()>0){
			Locale[] localArry = new Locale[fdmsUserInfo.getLocales().size()];
			int i= 0;
			for(FdmsLocale fdmsLocale : fdmsUserInfo.getLocales()){
				localArry[i++] = getLocale(fdmsLocale);
			}
			
			LocalesType locales = new LocalesType();
			locales.setLocale(localArry);
			userInfo.setLocales(locales);
		}

		
		return userInfo;
	}
	
	public static Locale getLocale(FdmsLocale fdmsLocale){
		
		Locale locale = new Locale();
		locale.setId(fdmsLocale.getId());
		locale.setName(fdmsLocale.getName());
		if(fdmsLocale.getLocations().size()>0){
			Location[] locationsArry = new Location[fdmsLocale.getLocations().size()];
			int i= 0;
			for(FdmsLocation fdmsLocation : fdmsLocale.getLocations()){
				locationsArry[i++] = getLocation(fdmsLocation);
			}
			
			LocationsType locations = new LocationsType();
			locations.setLocation(locationsArry);
			locale.setLocations(locations);
		}
		return locale;
	}
	
	public static Location getLocation(FdmsLocation fdmsLocation){
		
		Location location = new Location();
		location.setId(fdmsLocation.getId());
		location.setName(ConvertorUtil.getInstance().getStrMax50Len(fdmsLocation.getName()));
		
		return location;
	}
	
	public static Arranger getArranger(FdmsArranger fdmsDirector){
		
		Arranger arranger = new Arranger();
		arranger.setId(fdmsDirector.getId());
		arranger.setName(fdmsDirector.getName());
		
		return arranger;
	}
	
	public static FdmsInfo getFdmsInfo(Options options){
		FdmsInfo info = new FdmsInfo();
		info.setOptions(getOptionsType(options));
		return info;
	}
	
	public static OptionsType getOptionsType(Options options){
		OptionsType optionsType = null;
		
		if(options != null){
			optionsType = new OptionsType();
			
			if(options.isStringOptions()){
				for(StringOption option : options.getStringOptions() ){
					optionsType.addStringOption(getStringOptionType(option) );
				}
			}else if(options.isIntOptions()){
				for(IntOption option : options.getIntOptions() ){
					optionsType.addIntOption(getIntOptionType(option) );
				}
			}
		}
		return optionsType;
	}

	private static IntOptionType getIntOptionType(IntOption option) {
		
		IntOptionType optionType = new IntOptionType();
		
		optionType.setOption(option.getOption());
		optionType.setValue(option.getValue());
		return optionType;
	}

	private static StringOptionType getStringOptionType(StringOption option) {
		
		StringOptionType optionType = new StringOptionType();
		
		optionType.setOption(option.getOption());
		optionType.setValue(option.getValue());
		return optionType;
	}

	public static FdmsInfo getFdmsInfo(FdmsUserInfo fdmsUserInfo) {
		FdmsInfo info = new FdmsInfo();
		info.setUserInfo(getUserInfo(fdmsUserInfo));
		return info;
	}

	public static FdmsInfo getFdmsInfo(VitalsIdInfos infos) {
		FdmsInfo info = new FdmsInfo();
		info.setVitalsIds(getVitalsList(infos));
		return info;
	}

	private static VitalsIdListInfo getVitalsList(VitalsIdInfos infos) {
		VitalsIdListInfo vitalsIdListInfo = new VitalsIdListInfo();
		
		
		if(infos.getInfos().size()>0){
			for (int i= 0; i< infos.getInfos().size(); i++){
				VitalsIdInfo vitalsIdInfo = new VitalsIdInfo();
				vitalsIdInfo.setFirstName(ConvertorUtil.getInstance().getStrMax50Len(infos.getInfos().get(i).getFirstName()));
				vitalsIdInfo.setLastName(ConvertorUtil.getInstance().getStrMax50Len(infos.getInfos().get(i).getLastName()));
				vitalsIdInfo.setMiddleName(ConvertorUtil.getInstance().getStrMax50Len(infos.getInfos().get(i).getMiddleName()));
				vitalsIdInfo.setCaseCode(ConvertorUtil.getInstance().getStrMax20Len(infos.getInfos().get(i).getCaseCode()));
				vitalsIdInfo.setContractCode(ConvertorUtil.getInstance().getStrMax20Len(infos.getInfos().get(i).getContractCode()));
				vitalsIdInfo.setVitalsmasterkey(infos.getInfos().get(i).getVitalsMasterKey());
				vitalsIdInfo.setLocaleId(infos.getInfos().get(i).getLocaleId());
				vitalsIdInfo.setLocationId(infos.getInfos().get(i).getLocationId());
				vitalsIdListInfo.addVitals(vitalsIdInfo);
			}
		}
		return vitalsIdListInfo;
	}
}
