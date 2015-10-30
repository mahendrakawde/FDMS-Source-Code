package com.aldorsolutions.fdms.service.extracter;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.GenericThread;
import com.aldorsolutions.webservice.SciCashExtractThread;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciCashType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;
import com.aldorsolutions.webservice.xsd.fdde.service.InvalidRowDetails;

public class SciCashExtracter extends DataExtracter<SciCashType> {

	public SciCashExtracter(String name, DbUserSession user,
			List<FuneralHomeType> funeralHomes, CaseFilterType caseFilter,
			boolean globalExtract, boolean reportInvalidRowIds,
			boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows) {
		super(name, user, funeralHomes, caseFilter, globalExtract, reportInvalidRowIds,
				includeInvalidRowIds, invalidRows);
	}

	@Override
	protected List<SciCashType> getBlankDataList() {
		return new ArrayList<SciCashType>();
	}

	@Override
	protected List<SciCashType> getDataFromThread(GenericThread thread) {
		return ((SciCashExtractThread)thread).getSciCash();
	}

	@Override
	protected GenericThread getNewThread(FuneralHomeType[] fhArray) {
		return new SciCashExtractThread(fhArray, globalExtract, user, caseFilter, reportInvalidRowIds, includeInvalidRowIds);
	}

}


