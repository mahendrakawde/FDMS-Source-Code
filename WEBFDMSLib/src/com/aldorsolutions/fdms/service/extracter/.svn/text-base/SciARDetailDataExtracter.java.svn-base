package com.aldorsolutions.fdms.service.extracter;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.GenericThread;
import com.aldorsolutions.webservice.SciARDetailDataExtractThread;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciARDetailDataType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;
import com.aldorsolutions.webservice.xsd.fdde.service.InvalidRowDetails;

public class SciARDetailDataExtracter extends DataExtracter<SciARDetailDataType> {

	public SciARDetailDataExtracter(String name, DbUserSession user,
			List<FuneralHomeType> funeralHomes, CaseFilterType caseFilter,
			boolean globalExtract, boolean reportInvalidRowIds,
			boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows) {
		super(name, user, funeralHomes, caseFilter, globalExtract, reportInvalidRowIds,
				includeInvalidRowIds, invalidRows);
	}

	@Override
	protected List<SciARDetailDataType> getBlankDataList() {
		return new ArrayList<SciARDetailDataType>();
	}

	@Override
	protected List<SciARDetailDataType> getDataFromThread(GenericThread thread) {
		return ((SciARDetailDataExtractThread)thread).getsciARDetail();
	}

	@Override
	protected GenericThread getNewThread(FuneralHomeType[] fhArray) {
		return new SciARDetailDataExtractThread(fhArray, globalExtract, user, caseFilter, reportInvalidRowIds, includeInvalidRowIds);
	}

}
