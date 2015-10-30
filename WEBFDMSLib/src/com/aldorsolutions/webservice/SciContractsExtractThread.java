package com.aldorsolutions.webservice;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;

public class SciContractsExtractThread extends GenericThread {
	
	CaseFilterType caseFilter;
	boolean contractNumbersOnly;
	List<SciContractType> sciContracts = new ArrayList<SciContractType>();
	
	public SciContractsExtractThread(FuneralHomeType[] funeralHomes, boolean globalExtract,
			DbUserSession user,boolean reportInvalidRowIds, boolean includeInvalidRowIds, 
			CaseFilterType caseFilter, boolean contractNumbersOnly) {
		super(funeralHomes, globalExtract, user, reportInvalidRowIds, includeInvalidRowIds);
		this.caseFilter = caseFilter;
		this.contractNumbersOnly = contractNumbersOnly;
	}

	public List<SciContractType> getSciContracts() {
		return sciContracts;
	}

	@Override
	public boolean hasData() {
		return sciContracts != null && sciContracts.size()>0;
	}

	@Override
	protected void processLocation(FuneralHomeType funeralHome) {

		List<SciContractType> contracts = null;
		if(contractNumbersOnly){
			contracts = dao.getSciContractsFromVitalstats(funeralHome.getId(), 
												funeralHome.getNumber(), 
												funeralhomeToSciLocationMap.get(funeralHome), 
												null, caseFilter, globalExtract, true, 
												reportInvalidRowIds, includeInvalidRowIds, invalidRows, 
												null, null, null, null);
		}else{
				contracts = dao.getSciContracts(funeralHome.getId(), 
												funeralHome.getNumber(), 
												funeralhomeToSciLocationMap.get(funeralHome), 
												null, caseFilter, globalExtract,
												reportInvalidRowIds, includeInvalidRowIds, invalidRows);
		}
		if(contracts != null){
			sciContracts.addAll(contracts);
		}
		
	}
	
}
