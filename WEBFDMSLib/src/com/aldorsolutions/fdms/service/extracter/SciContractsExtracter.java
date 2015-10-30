package com.aldorsolutions.fdms.service.extracter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.GenericThread;
import com.aldorsolutions.webservice.SciContractsExtractThread;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;
import com.aldorsolutions.webservice.xsd.fdde.service.InvalidRowDetails;

public class SciContractsExtracter extends DataExtracter<SciContractType> {
	
	private boolean contractNumbersOnly;
	
	public SciContractsExtracter(String name, DbUserSession user,
			List<FuneralHomeType> funeralHomes, CaseFilterType caseFilter,
			boolean globalExtract, boolean reportInvalidRowIds,
			boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows,boolean contractNumbersOnly) {
		
		super(name, user, funeralHomes, caseFilter, globalExtract, reportInvalidRowIds,
				includeInvalidRowIds, invalidRows);
		this.contractNumbersOnly = contractNumbersOnly;
	}

	@Override
	protected List<SciContractType> getBlankDataList() {
		return new ArrayList<SciContractType>();
	}

	@Override
	protected List<SciContractType> getDataFromThread(GenericThread thread) {
		return ((SciContractsExtractThread)thread).getSciContracts();
	}

	@Override
	protected GenericThread getNewThread(FuneralHomeType[] fhArray) {
		return new SciContractsExtractThread(fhArray, globalExtract, user, reportInvalidRowIds, includeInvalidRowIds, caseFilter, contractNumbersOnly);
	}

}
