package fdms.ui.struts.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.aldorsolutions.webfdms.beans.display.FuneralSyncListBean;

public class FuneralSyncListForm extends ActionForm {

	private ArrayList<FuneralSyncListBean> funeralSyncCases;
	private String [] submitCases;
	private String processType;

	public ArrayList<FuneralSyncListBean> getFuneralSyncCases() {
		return funeralSyncCases;
	}

	public void setFuneralSyncCases(ArrayList<FuneralSyncListBean> funeralSyncCases) {
		this.funeralSyncCases = funeralSyncCases;
	}

	public String[] getSubmitCases() {
		return submitCases;
	}

	public void setSubmitCases(String[] submitCases) {
		this.submitCases = submitCases;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	
}
