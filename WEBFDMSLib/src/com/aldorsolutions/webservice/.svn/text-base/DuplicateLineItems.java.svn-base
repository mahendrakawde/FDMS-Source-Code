package com.aldorsolutions.webservice;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webservice.xsd.comm.fdde.sci.LineItemType;

public class DuplicateLineItems {
	int vitalId;
	int invMasterId;
	List<LineItemType> lineItems = new ArrayList<LineItemType>();
	
	public DuplicateLineItems(int vitalId, int invMasterId) {
		super();
		this.vitalId = vitalId;
		this.invMasterId = invMasterId;
	}
	
	public void addLineItems(LineItemType lineItem){
		if(lineItem != null && lineItem.getInvHistoryId()==invMasterId){
			lineItems.add(lineItem);
		}
	}
}
