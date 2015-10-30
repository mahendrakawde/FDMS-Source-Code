package com.aldorsolutions.fdms.service;

import java.sql.SQLException;
import java.util.List;

import com.aldorsolutions.fdms.exception.InvalidFdmsContextException;
import com.aldorsolutions.fdms.exception.InvalidFdmsUserException;
import com.aldorsolutions.fdms.exception.InvalidInfoRequestException;
import com.aldorsolutions.fdms.exception.UserExpiredException;
import com.aldorsolutions.fdms.exception.UserLockedException;
import com.aldorsolutions.fdms.to.common.Options;
import com.aldorsolutions.fdms.to.common.VitalsIdInfos;
import com.aldorsolutions.fdms.to.service.FdmsCaseRequest;
import com.aldorsolutions.fdms.to.service.FdmsPreNeedFinanceRequest;
import com.aldorsolutions.fdms.to.service.FdmsUserInfo;
import com.aldorsolutions.fdms.to.service.FdmsVitalsRequest;
import com.aldorsolutions.fdms.to.vitals.VitalsIdInfo;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.UserLocation;
import com.aldorsolutions.webservice.xsd.fdde.service.CriteriaType;
import com.aldorsolutions.webservice.xsd.fdde.service.DebugType;
import com.aldorsolutions.webservice.xsd.fdde.service.FilterType;
import com.aldorsolutions.webservice.xsd.fdde.service.ResultType;

public interface FdmsService {
	
	public FdmsUserInfo getUserInfo(DbUserSession user);
				
	public DbUserSession checkValidUser(String userName, String password)
			throws InvalidFdmsUserException, UserLockedException, UserExpiredException;
	
	public boolean validateLocaleAndLocation(int localeId, int locationId, DbUserSession user);
	
	public void validateLocaleLocationAndArranger(int localeId, int locationId, int directorId, DbUserSession user) throws InvalidFdmsContextException;
	
	public boolean validateLocaleAndArranger(int localeId,int directorId, DbUserSession user);
	
	public int processCase(DbUserSession user, FdmsCaseRequest request);

	public boolean processFinance(DbUserSession user,	FdmsPreNeedFinanceRequest fdmsPreNeedFinanceRequest);

	public int validateVitalsId(int vitalsId, DbUserSession user) ;
	
	public boolean processVitals(DbUserSession user, FdmsVitalsRequest fdmsVitalsRequest);
	
	public Options getDirectors(DbUserSession user, int localeId) throws InvalidInfoRequestException;

	public Options getComboServiceType(DbUserSession user);

	public VitalsIdInfos getVitalsIdInfo(DbUserSession user, VitalsIdInfo vitalsIdInfo);
	
	public void saveGreeting(String greeting); 
	
	public List<UserLocation> getUserLocations(DbUserSession user)throws SQLException, Exception ;
	
	public ResultType getData(DbUserSession user, String requestedData, CriteriaType criteria, FilterType filter, DebugType debug, boolean isSci) ;
	
}
