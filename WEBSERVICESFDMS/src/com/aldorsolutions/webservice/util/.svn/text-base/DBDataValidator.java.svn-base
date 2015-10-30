package com.aldorsolutions.webservice.util;

import com.aldorsolutions.fdms.exception.InvalidFdmsContextException;
import com.aldorsolutions.fdms.service.*;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException0;

public class DBDataValidator {
	
	private static DBDataValidator instance = new DBDataValidator();
	private DBDataValidator(){}
	
	public static DBDataValidator getInstance(){
		return instance;
	}

	public void validateLocaleLocationAndDirector(int localeId, int locationId, int directorId, DbUserSession user) throws InvalidDataException, FdmsFaultException0 {
		if (localeId > 0 && locationId > 0 && directorId > 0) {
			try{
				FdmsServiceImpl.getInstance().validateLocaleLocationAndArranger(localeId, locationId, directorId,user);
				return ;
			}catch (InvalidFdmsContextException ex){
				throw SoapFaultFactory.createFdmsSoapFault("InvalidRequestContext", ex.getMessage());
			}
		}

		String msg = "";
		if(localeId <= 0){
			msg = "localeId";
		}
		if(locationId <= 0){
			if(msg.length()>0){
				msg += ", locationId ";
			}else{
				msg = "locationId";
			}
		}
		if(directorId <= 0){
			if(msg.length()>0){
				msg += ", directorId ";
			}else{
				msg = "directorId";
			}
		}
		throw new InvalidDataException(msg + " missing.");
	}

	public boolean validateLocaleAndLocation(int localeId, int locationId, DbUserSession user) throws InvalidDataException, FdmsFaultException0 {
		if (localeId > 0 && locationId > 0) {
			
			if(!FdmsServiceImpl.getInstance().validateLocaleAndLocation(localeId, locationId, user)) {
				throw SoapFaultFactory.createFdmsSoapFault("InvalidRequestContext", "Given localeId and locationId are not related.");
			}
			return true;
		}

		String msg = "";
		if(localeId <= 0){
			msg = "localeId";
		}
		if(locationId <= 0){
			if(msg.length()>0){
				msg += ", locationId ";
			}else{
				msg = "locationId";
			}
		}
		throw new InvalidDataException(msg + " missing.");
	}

	public boolean validateLocaleAndDirector(int localeId, int directorId, DbUserSession user) throws InvalidDataException, FdmsFaultException0 {
		if (localeId > 0 && directorId > 0) {
			
			if(!FdmsServiceImpl.getInstance().validateLocaleAndArranger(localeId, directorId, user)) {
				throw SoapFaultFactory.createFdmsSoapFault("InvalidRequestContext", "Given localeId and directorId are not related.");
			}
			return true;
		}
		String msg = "";
		if(localeId <= 0){
			msg = "localeId";
		}
		if(directorId <= 0){
			if(msg.length()>0){
				msg += ", directorId ";
			}else{
				msg = "directorId";
			}
		}
		throw new InvalidDataException(msg + " missing.");
	}
	
	public int validateVitalsId(int vitalsId, DbUserSession user) throws InvalidDataException {
		if(vitalsId > 0){
			
			return FdmsServiceImpl.getInstance().validateVitalsId(vitalsId, user);
		}
			throw new InvalidDataException("VitalsID is missing.");
	}
}
