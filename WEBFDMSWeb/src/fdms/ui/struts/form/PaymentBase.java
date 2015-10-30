package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class PaymentBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 4355280280836415367L;
private String PaymentBalance ;  
  private String PaymentAmtDueTotal ;  
  private String PaymentAmtPaid ;
  
  public String getPaymentAmtDueTotal () {
	return PaymentAmtDueTotal ;
  }  
  public String getPaymentAmtPaid () {
	return PaymentAmtPaid ;
  }  
  public String getPaymentBalance () {
	return PaymentBalance ;
  }  
  public void setPaymentAmtDueTotal (String in) {
	PaymentAmtDueTotal = in;
  }  
  public void setPaymentAmtPaid (String in) {
	PaymentAmtPaid = in;
  }  
  public void setPaymentBalance (String in) {
	PaymentBalance = in;
  }  
}
