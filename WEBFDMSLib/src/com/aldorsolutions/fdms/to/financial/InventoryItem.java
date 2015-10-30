package com.aldorsolutions.fdms.to.financial;

public class InventoryItem {
	
	private int invMasterKey;
	private int invOnHandId;
	private String serialNo = "";
	
	public InventoryItem(){}

	public int getInvMasterKey() {
		return invMasterKey;
	}

	public void setInvMasterKey(int invMasterKey) {
		this.invMasterKey = invMasterKey;
	}

	public int getInvOnHandId() {
		return invOnHandId;
	}

	public void setInvOnHandId(int invOnHandId) {
		this.invOnHandId = invOnHandId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
}
