package com.aldorsolutions.webservice;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciCashType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;

public class SciCashExtractThread extends GenericThread {

	CaseFilterType caseFilter;
	List<SciCashType> sciCash = new ArrayList<SciCashType>();
	
	public SciCashExtractThread(FuneralHomeType[] funeralHomes,
			boolean globalExtract, DbUserSession user,CaseFilterType caseFilter,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds) {
		super(funeralHomes, globalExtract, user, reportInvalidRowIds,
				includeInvalidRowIds);
		this.caseFilter = caseFilter;
	}
	
	public List<SciCashType> getSciCash() {
		return sciCash;
	}

	@Override
	public boolean hasData() {
		return sciCash != null && sciCash.size()>0;
	}

	@Override
	protected void processLocation(FuneralHomeType funeralHome) {

		List<SciCashType> cash = null;		
			cash = dao.getSciCash(funeralHome.getId(), 
												funeralHome.getNumber(),
												funeralhomeToSciLocationMap.get(funeralHome),  
												null, caseFilter,
												reportInvalidRowIds, includeInvalidRowIds, invalidRows);
		
		if(cash != null){
			sciCash.addAll(cash);
		}
	}

}
