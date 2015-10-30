package fdms.info;

import fdms.ui.struts.form.FinancialAddMerchandiseForm;
import fdms.ui.struts.form.FinancialAddServicesForm;
import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.FinancialSelectPackagesForm;

public class FinancialSuper {
	
	private FinancialInformationForm financialForm = new FinancialInformationForm();
	private FinancialAddMerchandiseForm merchandiseForm = new FinancialAddMerchandiseForm();
	private FinancialAddServicesForm serviceForm = new FinancialAddServicesForm();
	private FinancialSelectPackagesForm[] packageForm = null;
	
	public FinancialInformationForm getFinancialForm() {
		return financialForm;
	}
	public void setFinancialForm(FinancialInformationForm financialForm) {
		this.financialForm = financialForm;
	}
	public FinancialAddMerchandiseForm getMerchandiseForm() {
		return merchandiseForm;
	}
	public void setMerchandiseForm(FinancialAddMerchandiseForm merchandiseForm) {
		this.merchandiseForm = merchandiseForm;
	}
	public FinancialAddServicesForm getServiceForm() {
		return serviceForm;
	}
	public void setServiceForm(FinancialAddServicesForm serviceForm) {
		this.serviceForm = serviceForm;
	}
	public FinancialSelectPackagesForm[] getPackageForm() {
		return packageForm;
	}
	public void setPackageForm(FinancialSelectPackagesForm[] packageForm) {
		this.packageForm = packageForm;
	}
	
	
	
}
