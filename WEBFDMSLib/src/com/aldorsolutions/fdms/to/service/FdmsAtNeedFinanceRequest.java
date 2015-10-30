package com.aldorsolutions.fdms.to.service;

import com.aldorsolutions.fdms.to.financial.FinanceChargeOption;

public class FdmsAtNeedFinanceRequest extends FdmsPreNeedFinanceRequest {
	
	private FinanceChargeOption financeChargeOption;
	private String SendChargesToAccounting;
	
	public FdmsAtNeedFinanceRequest(){}

	public FinanceChargeOption getFinanceChargeOption() {
		return financeChargeOption;
	}

	public void setFinanceChargeOption(FinanceChargeOption financeChargeOption) {
		this.financeChargeOption = financeChargeOption;
	}

	public String isSendChargesToAccounting() {
		return SendChargesToAccounting;
	}

	public void setSendChargesToAccounting(String sendChargesToAccounting) {
		SendChargesToAccounting = sendChargesToAccounting;
	}
}
