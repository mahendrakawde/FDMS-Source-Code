package com.aldorsolutions.fdms.to.service;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.fdms.to.common.*;
import com.aldorsolutions.fdms.to.financial.*;

public class FdmsPreNeedFinanceRequest {
	
	private int vitalsId;
	private List<Charge> charges = new ArrayList<Charge> ();
	private FinancialInfo financialInfo;
	private DepositInfo depositInfo;
	
	public FdmsPreNeedFinanceRequest(){}

	public int getVitalsId() {
		return vitalsId;
	}

	public void setVitalsId(int vitalsId) {
		this.vitalsId = vitalsId;
	}

	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}
	
	public void addCharge(Charge charge){
		charges.add(charge);
	}
	public FinancialInfo getFinancialInfo() {
		return financialInfo;
	}

	public void setFinancialInfo(FinancialInfo financialInfo) {
		this.financialInfo = financialInfo;
	}

	public DepositInfo getDepositInfo() {
		return depositInfo;
	}

	public void setDepositInfo(DepositInfo depositInfo) {
		this.depositInfo = depositInfo;
	}
}
