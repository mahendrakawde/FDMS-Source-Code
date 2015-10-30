package fdms.info;

import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.PreNeedForm;
import fdms.ui.struts.form.pnstatus;

public class PreNeedInformation {
	public Object getPreNeedData(ReadWriteExcel excel, XSSFRow row) {
		try {
			if(excel.getCellValue(row, 0).equals("A"))					
				return getPNStatusForm(excel, row);
			else if(excel.getCellValue(row, 0).equals("C"))
				return getPreNeedForm(excel, row);
		} catch (Exception e) {
			e.printStackTrace();
			//preNeedFormList.add(null);
		}
		return null;
	}
	
	public PreNeedForm getPreNeedForm(ReadWriteExcel excel, XSSFRow row) {
		PreNeedForm form = new PreNeedForm();
		form.setDirective("add");
		
		form.setBuyerTitle(excel.getCellValue(row,1));
		form.setBuyerFirst(excel.getCellValue(row,2));
		form.setBuyerMiddle(excel.getCellValue(row,3));
		form.setBuyerLast(excel.getCellValue(row,4));
		form.setBuyerStreet(excel.getCellValue(row,5));
		form.setBuyerCity(excel.getCellValue(row,7));
		form.setBuyerState(excel.getCellValue(row,8));
		form.setBuyerZipCode(excel.getCellValue(row,9));
		form.setBuyerPhone(excel.getCellValue(row,10));
		form.setBuyerSocialSecurityNumber(excel.getCellValue(row,11));
		form.setCoBuyerName(excel.getCellValue(row,12));
		
		form.setBeneficiaryFirst(excel.getCellValue(row,14));
		form.setBeneficiaryMiddle(excel.getCellValue(row,15));
		form.setBeneficiaryLast(excel.getCellValue(row,16));
		form.setBeneficiaryStreet(excel.getCellValue(row, 17));
		form.setBeneficiaryCity(excel.getCellValue(row, 19));
		form.setBeneficiaryState(excel.getCellValue(row,20));
		form.setBeneficiaryZipCode(excel.getCellValue(row,21));
		form.setBeneficiarySocialSecurityNumber(excel.getCellValue(row,23));
		form.setBeneficiaryDOB(excel.getCellValue(row,24));
		
		form.setSaleDate(excel.getCellValue(row,25));
		form.setCaseCode(excel.getCellValue(row,26));
		form.setCasket(excel.getCellValue(row,27));
		form.setUrn(excel.getCellValue(row,28));
		form.setCasketSale(excel.getCellValue(row,29));
		form.setVaultSale(excel.getCellValue(row,30));
		form.setGstAmt(excel.getCellValue(row,31));
		form.setMortuaryLocation(excel.getChapelLocations().get(excel.getCellValue(row,32)));
		form.setService(excel.getCellValue(row,33));
		form.setVault(excel.getCellValue(row,34));
		form.setServiceSale(excel.getCellValue(row,35));
		form.setUrnSale(excel.getCellValue(row,36));
		form.setOtherSale(excel.getCellValue(row,37));
		form.setTotalSale(excel.getCellValue(row,38));
		
		form.setFundDepositoryType(excel.getCellValue(row,39));
		form.setFundsWith(excel.getCellValue(row,40));
		form.setFundsStreet(excel.getCellValue(row,41));
		form.setFundsCity(excel.getCellValue(row,42));
		form.setFundsState(excel.getCellValue(row,43));
		form.setFundsZip(excel.getCellValue(row,44));
		form.setDateStarted(excel.getCellValue(row,45));
		form.setMaturity(excel.getCellValue(row,46));
		form.setAccountNumber(excel.getCellValue(row,47));
		form.setEstIntRate(excel.getCellValue(row,48));
		form.setFundsDepositedDate(excel.getCellValue(row,49));
		form.setLastPaymentDate(excel.getCellValue(row,50));
		form.setFaceValue(excel.getCellValue(row,51));
		form.setContractAmount(excel.getCellValue(row,52));
		form.setYtdPaid(excel.getCellValue(row,53));
		form.setTotalPaid(excel.getCellValue(row,54));
		form.setYtdInterest(excel.getCellValue(row,55));
		form.setTotalInterest(excel.getCellValue(row,56));
		form.setManagementFee(excel.getCellValue(row,57));
		form.setCommission(excel.getCellValue(row,58));
		form.setLastPaymentAmount(excel.getCellValue(row,59));
		
		form.setComments(excel.getCellValue(row,60));
		
		form.setPreneedStatus(excel.getCellValue(row,61));
		form.setCounselor(excel.getDirectors().get(excel.getCellValue(row,62)));
		form.setSource(excel.getCellValue(row,64));
		
		return form;
	}
	
	public pnstatus getPNStatusForm(ReadWriteExcel excel, XSSFRow row) {
		pnstatus form = new pnstatus();
		form.setDirective("change");
		
		form.setBuyerTitle(excel.getCellValue(row,1));
		form.setBuyerFirst(excel.getCellValue(row,2));
		form.setBuyerMiddle(excel.getCellValue(row,3));
		form.setBuyerLast(excel.getCellValue(row,4));
		form.setBuyerStreet(excel.getCellValue(row,5));
		form.setBuyerAptno(excel.getCellValue(row,6));
		form.setBuyerCity(excel.getCellValue(row,7));
		form.setBuyerState(excel.getCellValue(row,8));
		form.setBuyerZipCode(excel.getCellValue(row,9));
		form.setBuyerPhone(excel.getCellValue(row,10));
		form.setBuyerSsNo(excel.getCellValue(row,11));
		form.setCoBuyerName(excel.getCellValue(row,12));
		
		form.setBeneficiaryTitle(excel.getCellValue(row,13));
		form.setBeneficiaryFirst(excel.getCellValue(row,14));
		form.setBeneficiaryMiddle(excel.getCellValue(row,15));
		form.setBeneficiaryLast(excel.getCellValue(row,16));
		form.setBeneficiaryStreet(excel.getCellValue(row, 17));
		form.setBeneficiaryAptno(excel.getCellValue(row, 18));
		form.setBeneficiaryCity(excel.getCellValue(row, 19));
		form.setBeneficiaryState(excel.getCellValue(row,20));
		form.setBeneficiaryZipCode(excel.getCellValue(row,21));
		form.setBeneficiaryPhone(excel.getCellValue(row,22));
		form.setBeneficiarySocialSecurityNumber(excel.getCellValue(row,23));
		
		form.setMortuaryLocation(excel.getChapelLocations().get(excel.getCellValue(row,32)));
		form.setPreneedStatus(excel.getCellValue(row,61));
		form.setCounselor(excel.getDirectors().get(excel.getCellValue(row,62)));
		form.setArrangementDate(excel.getCellValue(row,63));
		form.setSource(excel.getCellValue(row,64));
		form.setEmbalmReason(excel.getCellValue(row,65));
		form.setEmbalmReason2(excel.getCellValue(row,66));
		
		return form;
	}
}