package com.aldorsolutions.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractLineItemType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;

public class SciLineItemsExtractThread extends GenericThread {

	CaseFilterType caseFilter;
	Map<String, String> fdmsToSciItemMap;
	List<SciContractLineItemType> sciLineItems = new ArrayList<SciContractLineItemType>();
	
	public SciLineItemsExtractThread(FuneralHomeType[] funeralHomes,
			boolean globalExtract, DbUserSession user,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			CaseFilterType caseFilter, Map<String, String> fdmsToSciItemMap) {
		super(funeralHomes, globalExtract, user, reportInvalidRowIds,
				includeInvalidRowIds);
		this.caseFilter = caseFilter;
		this.fdmsToSciItemMap = fdmsToSciItemMap;
	}

	@Override
	public boolean hasData() {
		return sciLineItems != null && sciLineItems.size()>0;
	}

	@Override
	protected void processLocation(FuneralHomeType funeralHome) {
		List<SciContractLineItemType> lineItems = dao.getSciLineItems(funeralHome.getId(), funeralHome.getNumber(), 
				funeralhomeToSciLocationMap.get(funeralHome), 
				null, caseFilter, fdmsToSciItemMap,
				reportInvalidRowIds, includeInvalidRowIds, invalidRows);

		if(lineItems != null){
		sciLineItems.addAll(lineItems);
		}
	}

	public List<SciContractLineItemType> getSciLineItems() {
		return sciLineItems;
	}

}
