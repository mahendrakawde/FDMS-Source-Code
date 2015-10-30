package com.aldorsolutions.webservice;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciARDetailDataType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;

public class SciARDetailDataExtractThread extends GenericThread {

	CaseFilterType caseFilter;
	List<SciARDetailDataType> sciARDetail = new ArrayList<SciARDetailDataType>();
	
	public SciARDetailDataExtractThread(FuneralHomeType[] funeralHomes,
			boolean globalExtract, DbUserSession user,CaseFilterType caseFilter,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds) {
		super(funeralHomes, globalExtract, user, reportInvalidRowIds,
				includeInvalidRowIds);
		this.caseFilter = caseFilter;
	}
	
	public List<SciARDetailDataType> getsciARDetail(){
		return sciARDetail;
	}

	@Override
	public boolean hasData() {
		return sciARDetail != null && sciARDetail.size()>0;
	}

	@Override
	protected void processLocation(FuneralHomeType funeralHome) {

		List<SciARDetailDataType> arDetails = null;		
		arDetails = dao.getSciARDetails(funeralHome.getId(), null, caseFilter, 
										reportInvalidRowIds, includeInvalidRowIds, 
										invalidRows);
		if(arDetails != null){
			sciARDetail.addAll(arDetails);
		}
	}
	@Override
	protected void extractFuneralHomeSpecificData() {}

}
