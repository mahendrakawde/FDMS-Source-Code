package com.aldorsolutions.fdms.service.extracter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.GenericThread;
import com.aldorsolutions.webservice.SciLineItemsExtractThread;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractLineItemType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;
import com.aldorsolutions.webservice.xsd.fdde.service.InvalidRowDetails;

public class SciLineItemsExtracter extends DataExtracter<SciContractLineItemType> {

	private Map<String, String> fdmsToSciItemMap;
	
	public SciLineItemsExtracter(String name, DbUserSession user,
			List<FuneralHomeType> funeralHomes, CaseFilterType caseFilter,
			boolean globalExtract, boolean reportInvalidRowIds,
			boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows, Map<String, String> fdmsToSciItemMap) {
		super(name, user, funeralHomes, caseFilter, globalExtract, reportInvalidRowIds,
				includeInvalidRowIds, invalidRows);
		this.fdmsToSciItemMap = fdmsToSciItemMap;
	}

	@Override
	protected List<SciContractLineItemType> getBlankDataList() {
		return new ArrayList<SciContractLineItemType>();
	}

	@Override
	protected List<SciContractLineItemType> getDataFromThread(
			GenericThread thread) {
		 return ((SciLineItemsExtractThread)thread).getSciLineItems();
	}

	@Override
	protected GenericThread getNewThread(FuneralHomeType[] fhArray) {
		 return new SciLineItemsExtractThread(fhArray, globalExtract, user, reportInvalidRowIds, includeInvalidRowIds, caseFilter, fdmsToSciItemMap);
		
	}

}
