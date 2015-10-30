package com.aldorsolutions.webfdms.accounting.bean;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.dao.AccountingInterfaceDAO;
import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.accounting.model.CustomerDTO;
import com.aldorsolutions.webfdms.accounting.model.ItemDTO;
import com.aldorsolutions.webfdms.accounting.model.SaleInfoDTO;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.webservice.QBVitalsRecordInfo;


public class AccountingInterfaceQuickbooksXML implements AccountingInterface {
	
	final private static Logger logger = Logger.getLogger(AccountingInterfaceQuickbooksXML.class.getName());
	
	public String [] getInterfaceFileName(
    		int ACCOUNTING_INTERFACE, 
    		int locationSelected, 
    		DbUser dbUser) {
		
		String [] fileNames = new String [1]; 
		
		fileNames[0] = generateFileName(ACCOUNTING_INTERFACE, locationSelected, dbUser);
		return ( fileNames );
		
	}
	
    private String generateFileName(
    		int ACCOUNTING_INTERFACE, 
    		int locationSelected, 
    		DbUser dbUser) {
    	
        StringBuffer filename = new StringBuffer();
        StringBuffer filepath = new StringBuffer();
        // Get root directory for GL transaction file
        // filepath.append( getFileBaseDir(user));
        
        // Construct file name
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd_Hmmss");
        filename.append( formatter.format(new Date()));
        filename.append("_");
        // add location name to file name
        filename.append(AccountingInterfaceManagerBean.getLocationDescriptor(locationSelected, dbUser));
        
        filename.append(".");
        filename.append(locationSelected);
        filename.append(".");
        filename.append(ACCOUNTING_INTERFACE);
        filename.append(".xml");
        
        // Construct the file FILE
        filepath.append(filename);
        
        return filepath.toString();
    }	
    
	public String getStartString() {
		return ( "<?xml version=\"1.0\" ?>\n" +
				"<?qbxml version=\"6.0\"?>\n" +
				"<QBXML><QBXMLMsgsRq onError = \"stopOnError\">\n" );
	}
    
	public boolean initializeExport(String intFile, int intType) {
		return AccountingInterfaceUtil.addTranToDiskFile( intFile, getStartString() );
	}	
	

    public boolean writeSummaryTran(
    		DbUser dbUser,
    		AccountingInterfaceDTO accountingInterfaceDTO,
			ActionErrors errors) {
    	
    	boolean success = true;
    	
    	try {
    		generateQuickbooksCustomerSummaryTrans (dbUser, accountingInterfaceDTO, errors);
    	} catch (Exception e) {
    		logger.error("Exception in writeSummaryTran() : ", e);
    		success = false;
    	}
    	
    	return ( success );
    	
    }
	
    public QBVitalsRecordInfo generateQuickbooksCustomerSummaryTrans(
    		DbUser dbUser, AccountingInterfaceDTO accountingInterfaceDTO, ActionErrors errors) throws Exception {
    	
    	StringBuilder transaction = null;
    	QBVitalsRecordInfo sessionInfo = new QBVitalsRecordInfo();
    	
   		AccountingInterfaceDAO accountingInterfaceDAO = new AccountingInterfaceDAO();

		transaction = new StringBuilder();
		// start transaction set for vitals case
		SaleInfoDTO saleInfoDTO = accountingInterfaceDAO.getSaleInfo(dbUser.getDbLookup(), accountingInterfaceDTO.getCaseId());

		if (saleInfoDTO == null) {
			saleInfoDTO = new SaleInfoDTO();
			saleInfoDTO.setVitalsMasterKey(accountingInterfaceDTO.getCaseId());
		}

		CustomerDTO customerDTO = accountingInterfaceDAO.getCustomer(dbUser
				.getDbLookup(), accountingInterfaceDTO.getCaseId());

		if (customerDTO == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.quickbooks.nocontractsigner", saleInfoDTO
							.getContractCode()));
			throw new Exception("Item name missing on case: "
					+ saleInfoDTO.getContractCode());
		}

		ArrayList items = accountingInterfaceDAO.getTransactions(dbUser
				.getDbLookup(), accountingInterfaceDTO.getCaseId());

		ArrayList<ItemDTO> salesItems = new ArrayList<ItemDTO>();
		ArrayList<ItemDTO> paymentItems = new ArrayList<ItemDTO>();
		ArrayList<Integer> invoiceIDs = new ArrayList<Integer>();
		ArrayList<Integer> paymentIDs = new ArrayList<Integer>();

		if (items != null) {
			Iterator it = items.iterator();
			while (it.hasNext()) {
				ItemDTO itemDTO = (ItemDTO) it.next();

				if (itemDTO.getSpfCode().compareTo(DbHistory.HIST_SPF_PAYMENT) == 0
						|| itemDTO.getSpfCode().compareTo(
								DbHistory.HIST_SPF_VOIDCNTRT) == 0) {
					paymentItems.add(itemDTO);
					paymentIDs.add(itemDTO.getTranHistId());
				}
				// if this is an item or a credit
				else if (itemDTO.getSpfCode()
						.compareTo(DbHistory.HIST_SPF_SALE) == 0
						|| itemDTO.getSpfCode().compareTo(
								DbHistory.HIST_SPF_FINCHRG) == 0
						|| itemDTO.getSpfCode().compareTo(
								DbHistory.HIST_SPF_PRICEADJ) == 0) {
					// if this is a new item
					salesItems.add(itemDTO);
					invoiceIDs.add(itemDTO.getTranHistId());
				}
			}
		}

		String customerSB = generateCustomerQBXML(customerDTO);
		String invoiceSB = generateSalesInfoQBXML(customerDTO, saleInfoDTO,
				salesItems);
		String paymentSB = generateItemPaymentQBXML(customerDTO, paymentItems);

		sessionInfo.setCustomerXML(customerSB);
		sessionInfo.setInvoicesXML(invoiceSB);
		sessionInfo.setPaymentsXML(paymentSB);
		sessionInfo.setInvoiceTransactionIds(invoiceIDs);
		sessionInfo.setPaymentTransactionIds(paymentIDs);
		sessionInfo.setStartOfTrans(getStartString());
		sessionInfo.setEndOfTrans(getEndString());

		transaction.append(customerSB);
		transaction.append(invoiceSB);
		transaction.append(paymentSB);

		// end transaction set for vitals case
		AccountingInterfaceUtil.addTranToDiskFile(accountingInterfaceDTO
				.getFileName(), transaction.toString());
			
		
    	return sessionInfo;
    }
    
	public boolean writeSalesTran(
			DbHistory dbHistory,
			DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	
	public boolean writeAdjustment(
			DbHistory hist, 
			String csvFileName, 
			int ACCOUNTING_INTERFACE, 
			DbUser dbUser,
      AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	
	public boolean writeMiscCashReceipt(
            DbHistory hist,
            String fileName,
            int ACCOUNTING_INTERFACE,
            DbUser dbUser,
            AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	
	public boolean writeApExpense(
            DbApDistributionHistory check,
            String fileName,
            int ACCOUNTING_INTERFACE,
            DbUser dbUser,
            AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	
	public boolean writeApInvoiceExpense(
			DbInvoiceTransHist invoiceTransHist,
      String fileName,
      int ACCOUNTING_INTERFACE,
      DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	

	public String getEndString() {
		return "</QBXMLMsgsRq></QBXML>\n";
	}
	
	public boolean closeFile(String fileName) {
		return AccountingInterfaceUtil.addTranToDiskFile(fileName, getEndString() );
	}
	
	private static String formatXML ( String string ) {
		return ( FormatString.blankNull(FormatString.escapeXML(string)).trim() );
	}
		
	private static String formatDollarAmount ( double dblValue ) {
		return ( formatDollarAmount (dblValue, false) );
	}
	
	private static String formatDollarAmount ( double dblValue, boolean absoluteValue ) {
		String amountFormatted = "0.00";
		try {
			
			if ( dblValue < 0 ) {
				dblValue = -dblValue;
			}
			
			amountFormatted = FormatCurrency.toCurrencyFormatted(dblValue);
			
			
		} catch (Exception e) {
		}
		
		return ( amountFormatted );
	}
	
	private String generateCustomerQBXML (CustomerDTO customer) {
		
		StringBuilder sb = new StringBuilder();

		//<!-- CustomerAddRq contains 1 optional attribute: 'requestID' -->
		sb.append("<CustomerAddRq>\n");
		sb.append("  <CustomerAdd>\n");
		sb.append("    <Name>" + formatXML(customer.getFirstName()) + " " + formatXML(customer.getLastName()) + "</Name>\n");  // <!-- max length = 41 for QBD|QBCA|QBUK, max length = 100 for QBOE -->
		sb.append("    <IsActive>true</IsActive>\n");                            // <!-- opt, not in QBOE -->
//		sb.append("    <ParentRef>\n");                                          // <!-- opt -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt -->
//		sb.append("    </ParentRef>\n");
//		sb.append("    <CompanyName>STRTYPE</CompanyName>\n");                   // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 50 for QBOE -->
		sb.append("    <Salutation>" + formatXML(customer.getHonorific()) + "</Salutation>\n");  // <!-- opt, max length = 15 -->
		sb.append("    <FirstName>" + formatXML(customer.getFirstName()) + "</FirstName>\n");    // <!-- opt, max length = 25 -->
//		sb.append("    <MiddleName>STRTYPE</MiddleName>\n");                     // <!-- opt, max length = 5 for QBD|QBCA|QBUK, max length = 25 for QBOE -->
		sb.append("    <LastName>" + formatXML(customer.getLastName()) + "</LastName>\n");// <!-- opt, max length = 25 -->
//		sb.append("    <Suffix>STRTYPE</Suffix>\n");                             // <!-- opt, max length = 10 for QBOE, not in QBD|QBCA|QBUK -->
		sb.append("    <BillAddress>\n");                                        // <!-- opt -->
		sb.append("      <Addr1>" + formatXML(customer.getStreetAddr1()) + "</Addr1>\n"); // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
		sb.append("      <Addr2>" + formatXML(customer.getStreetAddr2()) + "</Addr2>\n"); // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
		sb.append("      <Addr3>" + formatXML(customer.getStreet3()) + "</Addr3>\n");     // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
		sb.append("      <Addr4>" + formatXML(customer.getStreet4()) + "</Addr4>\n");     // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE, v2.0 -->
//		sb.append("      <Addr5>STRTYPE</Addr5>\n");                             // <!-- opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
		sb.append("      <City>" + formatXML(customer.getCity()) + "</City>\n");          // <!-- opt, max length = 31 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
		sb.append("      <State>" + formatXML(customer.getState()) + "</State>\n");       // <!-- opt, max length = 21 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
		sb.append("      <PostalCode>" + formatXML(customer.getZip()) + "</PostalCode>\n");      // <!-- opt, max length = 13 for QBD|QBCA|QBUK, max length = 30 for QBOE -->
//		sb.append("      <Country>STRTYPE</Country>\n");                         // <!-- opt, max length = 31 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
//		sb.append("      <Note>STRTYPE</Note>\n");                               // <!-- opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
		sb.append("    </BillAddress>\n");
		sb.append("    <ShipAddress>\n");                                        // <!-- opt -->
		sb.append("      <Addr1>" + formatXML(customer.getStreetAddr1()) + "</Addr1>\n"); // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
		sb.append("      <Addr2>" + formatXML(customer.getStreetAddr2()) + "</Addr2>\n"); // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
		sb.append("      <Addr3>" + formatXML(customer.getStreet3()) + "</Addr3>\n");     // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
		sb.append("      <Addr4>" + formatXML(customer.getStreet4()) + "</Addr4>\n");     // <!-- opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE, v2.0 -->
//		sb.append("      <Addr5>STRTYPE</Addr5>\n");                             // <!-- opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
		sb.append("      <City>" + formatXML(customer.getCity()) + "</City>\n");          // <!-- opt, max length = 31 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
		sb.append("      <State>" + formatXML(customer.getState()) + "</State>\n");       // <!-- opt, max length = 21 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
		sb.append("      <PostalCode>" + formatXML(customer.getZip()) + "</PostalCode>\n");                   // <!-- opt, max length = 13 for QBD|QBCA|QBUK, max length = 30 for QBOE -->
//		sb.append("      <Country>STRTYPE</Country>\n");                         // <!-- opt, max length = 31 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
//		sb.append("      <Note>STRTYPE</Note>\n");                               // <!-- opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
		sb.append("    </ShipAddress>\n");
//		sb.append("    <PrintAs>STRTYPE</PrintAs>\n");                           // <!-- opt, max length = 110 for QBOE, not in QBD|QBCA|QBUK -->
		sb.append("    <Phone>" + formatXML(customer.getHomePhone()) + "</Phone>\n");                               // <!-- opt, max length = 21 -->
		sb.append("    <Mobile>" + formatXML(customer.getCellPhone()) + "</Mobile>\n");                             // <!-- opt, max length = 21 for QBOE, not in QBD|QBCA|QBUK -->
//		sb.append("    <Pager>STRTYPE</Pager>\n");                               // <!-- opt, max length = 21 for QBOE, not in QBD|QBCA|QBUK -->
		sb.append("    <AltPhone>" + formatXML(customer.getWorkPhone()) + "</AltPhone>\n");                         // <!-- opt, max length = 21 -->
//		sb.append("    <Fax>STRTYPE</Fax>\n");                                   // <!-- opt, max length = 21 -->
		sb.append("    <Email>" + formatXML(customer.getEmailAddress()) + "</Email>\n");                               // <!-- opt, max length = 1023 for QBD|QBCA|QBUK, max length = 100 for QBOE -->
//		sb.append("    <Contact>STRTYPE</Contact>\n");                           // <!-- opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE -->
//		sb.append("    <AltContact>STRTYPE</AltContact>\n");                     // <!-- opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE -->
//		sb.append("    <CustomerTypeRef>\n");                                    // <!-- opt, not in QBOE -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt, max length = 159 for QBD|QBCA|QBUK -->
//		sb.append("    </CustomerTypeRef>\n");
//		sb.append("    <TermsRef>\n");                                           // <!-- opt -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt, max length = 31 for QBD|QBCA|QBUK, max length = 100 for QBOE -->
//		sb.append("    </TermsRef>\n");
//		sb.append("    <SalesRepRef>\n");                                        // <!-- opt, not in QBOE -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt, max length = 5 for QBD|QBCA|QBUK -->
//		sb.append("    </SalesRepRef>\n");
//		sb.append("    <OpenBalance>AMTTYPE</OpenBalance>\n");                   // <!-- opt -->
//		sb.append("    <OpenBalanceDate>DATETYPE</OpenBalanceDate>\n");          // <!-- opt -->
//		sb.append("    <SalesTaxCodeRef>\n");                                    // <!-- opt, not in QBOE -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt, max length = 3 for QBD|QBCA|QBUK -->
//		sb.append("    </SalesTaxCodeRef>\n");
//		sb.append("    <ItemSalesTaxRef>\n");                                    // <!-- opt, not in QBOE -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt, max length = 31 for QBD|QBCA|QBUK -->
//		sb.append("    </ItemSalesTaxRef>\n");
//		sb.append("    <SalesTaxCountry>STRTYPE</SalesTaxCountry>\n");           // <!-- opt, max length = 31 for QBCA|QBUK, not in QBD|QBOE, v6.0 -->
//		sb.append("    <ResaleNumber>STRTYPE</ResaleNumber>\n");                 // <!-- opt, max length = 15 for QBD, max length = 21 for QBCA|QBUK, max length = 16 for QBOE -->
		sb.append("    <AccountNumber>" + customer.getVitalsId() + "</AccountNumber>\n");               // <!-- opt, max length = 99 for QBD|QBCA|QBUK, not in QBOE -->
//		sb.append("    <CreditLimit>AMTTYPE</CreditLimit>\n");                   // <!-- opt, not in QBOE -->
//		sb.append("    <PreferredPaymentMethodRef>\n");                          // <!-- opt, not in QBOE, v3.0 -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt, max length = 31 for QBD|QBCA|QBUK -->
//		sb.append("    </PreferredPaymentMethodRef>\n");
//		sb.append("    <CreditCardInfo>\n");                                     // <!-- opt, not in QBOE, v3.0 -->
//		sb.append("      <CreditCardNumber>STRTYPE</CreditCardNumber>\n");       // <!-- opt, max length = 25 for QBD|QBCA|QBUK -->
//		sb.append("      <ExpirationMonth>INTTYPE</ExpirationMonth>\n");         // <!-- opt, min value = 1, max value = 12 -->
//		sb.append("      <ExpirationYear>INTTYPE</ExpirationYear>\n");           // <!-- opt -->
//		sb.append("      <NameOnCard>STRTYPE</NameOnCard>\n");                   // <!-- opt, max length = 41 for QBD|QBCA|QBUK -->
//		sb.append("      <CreditCardAddress>STRTYPE</CreditCardAddress>\n");     // <!-- opt, max length = 41 for QBD|QBCA|QBUK -->
//		sb.append("      <CreditCardPostalCode>STRTYPE</CreditCardPostalCode>\n");  // <!-- opt, max length = 41 for QBD|QBCA|QBUK -->
//		sb.append("    </CreditCardInfo>\n");
		// <!-- JobStatus may have one of the following values: Awarded, Closed, InProgress, None [DEFAULT], NotAwarded, Pending -->
//		sb.append("    <JobStatus>ENUMTYPE</JobStatus>\n");                      // <!-- opt, not in QBOE -->
//		sb.append("    <JobStartDate>DATETYPE</JobStartDate>\n");                // <!-- opt, not in QBOE -->
//		sb.append("    <JobProjectedEndDate>DATETYPE</JobProjectedEndDate>\n");  // <!-- opt, not in QBOE -->
//		sb.append("    <JobEndDate>DATETYPE</JobEndDate>\n");                    // <!-- opt, not in QBOE -->
//		sb.append("    <JobDesc>STRTYPE</JobDesc>\n");                           // <!-- opt, max length = 99 for QBD|QBCA|QBUK, not in QBOE -->
//		sb.append("    <JobTypeRef>\n");                                         // <!-- opt, not in QBOE -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt, max length = 159 for QBD|QBCA|QBUK -->
//		sb.append("    </JobTypeRef>\n");
//		sb.append("    <Notes>STRTYPE</Notes>\n");                               // <!-- opt, max length = 4095 for QBD|QBCA|QBUK, not in QBOE, v3.0 -->
//		sb.append("    <IsStatementWithParent>BOOLTYPE</IsStatementWithParent>\n");  // <!-- opt, not in QBD|QBCA|QBUK -->
		// <!-- DeliveryMethod may have one of the following values: Email, Fax, Print [DEFAULT] -->
//		sb.append("    <DeliveryMethod>ENUMTYPE</DeliveryMethod>\n");            // <!-- opt, not in QBD|QBCA|QBUK -->
//		sb.append("    <PriceLevelRef>\n");                                      // <!-- opt, not in QBOE, v4.0 -->
//		sb.append("      <ListID>IDTYPE</ListID>\n");                            // <!-- opt -->
//		sb.append("      <FullName>STRTYPE</FullName>\n");                       // <!-- opt, max length = 31 for QBD|QBCA|QBUK -->
//		sb.append("    </PriceLevelRef>\n");
		sb.append("  </CustomerAdd>\n");
//		sb.append("  <IncludeRetElement>STRTYPE</IncludeRetElement>\n");         // <!-- opt, may rep, max length = 50 for QBD|QBCA|QBUK, not in QBOE, v4.0 -->
		sb.append("</CustomerAddRq>\n");
		
		return ( sb.toString() );
	}
	

	private String generateSalesInfoQBXML (CustomerDTO customer, SaleInfoDTO salesInfo, ArrayList <ItemDTO> salesItems) {
		StringBuilder sb = new StringBuilder();
		
		if ( salesItems == null || salesItems != null && salesItems.size() == 0 ) {
			return ( sb.toString() );
		}
		
//		sb.append("// <!-- InvoiceAddRq contains 1 optional attribute: 'requestID' -->\n");
		sb.append("<InvoiceAddRq>\n");
//		sb.append("// <!-- InvoiceAdd contains 1 optional attribute: 'defMacro' -->\n");
		sb.append("   <InvoiceAdd>\n");
		
		if ( customer != null ) {
			sb.append("   <CustomerRef>\n");
			sb.append("     <FullName>" + formatXML(customer.getFirstName()) + " " + formatXML(customer.getLastName()) + "</FullName>\n");                       // <!--opt, max length = 209 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->
			sb.append("   </CustomerRef>\n");
		}
		
		String locationClassCode = salesInfo.getLocationCode();
		
		if ( locationClassCode != null && locationClassCode.trim().length() > 0 ) {
			sb.append("   <ClassRef>\n");                                           // <!--opt -->
//			sb.append("     <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
			sb.append("     <FullName>" + locationClassCode.trim() + "</FullName>\n");                       // <!--opt, max length = 159 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->
			sb.append("   </ClassRef>\n");	
		}
		
//		sb.append("<ARAccountRef>\n");                                       // <!--opt -->
//		sb.append("  <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
//		sb.append("  <FullName>STRTYPE</FullName>\n");                       // <!--opt, max length = 159 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->
//		sb.append("</ARAccountRef>\n");
//		sb.append("<TemplateRef>\n");                                        // <!--opt, not in QBOE, v3.0 -->
//		sb.append("  <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
//		sb.append("  <FullName>STRTYPE</FullName>\n");                       // <!--opt, max length = 31 for QBD|QBCA|QBUK -->
//		sb.append("</TemplateRef>\n");
		sb.append("   <TxnDate>" + FormatDate.MDYtoYYYY_MM_DD(salesInfo.getSaleDate()) + "</TxnDate>\n"); // <!--opt -->
		sb.append("   <RefNumber>" + salesInfo.getVitalsMasterKey() + "</RefNumber>\n"); // <!--opt, max length = 11 for QBD|QBCA|QBUK, max length = 21 for QBOE -->
//		sb.append("<BillAddress>\n");                                        // <!--opt -->
//		sb.append("  <Addr1>STRTYPE</Addr1>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
//		sb.append("  <Addr2>STRTYPE</Addr2>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
//		sb.append("  <Addr3>STRTYPE</Addr3>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
//		sb.append("  <Addr4>STRTYPE</Addr4>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE, v2.0 -->
//		sb.append("  <Addr5>STRTYPE</Addr5>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
//		sb.append("  <City>STRTYPE</City>\n");                               // <!--opt, max length = 31 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
//		sb.append("  <State>STRTYPE</State>\n");                             // <!--opt, max length = 21 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
//		sb.append("  <PostalCode>STRTYPE</PostalCode>\n");                   // <!--opt, max length = 13 for QBD|QBCA|QBUK, max length = 30 for QBOE -->
//		sb.append("  <Country>STRTYPE</Country>\n");                         // <!--opt, max length = 31 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
//		sb.append("  <Note>STRTYPE</Note>\n");                               // <!--opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
//		sb.append("</BillAddress>\n");
//		sb.append("<ShipAddress>\n");                                        // <!--opt -->
//		sb.append("  <Addr1>STRTYPE</Addr1>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
//		sb.append("  <Addr2>STRTYPE</Addr2>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
//		sb.append("  <Addr3>STRTYPE</Addr3>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE -->
//		sb.append("  <Addr4>STRTYPE</Addr4>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, max length = 500 for QBOE, v2.0 -->
//		sb.append("  <Addr5>STRTYPE</Addr5>\n");                             // <!--opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
//		sb.append("  <City>STRTYPE</City>\n");                               // <!--opt, max length = 31 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
//		sb.append("  <State>STRTYPE</State>\n");                             // <!--opt, max length = 21 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
//		sb.append("  <PostalCode>STRTYPE</PostalCode>\n");                   // <!--opt, max length = 13 for QBD|QBCA|QBUK, max length = 30 for QBOE -->
//		sb.append("  <Country>STRTYPE</Country>\n");                         // <!--opt, max length = 31 for QBD|QBCA|QBUK, max length = 255 for QBOE -->
//		sb.append("  <Note>STRTYPE</Note>\n");                               // <!--opt, max length = 41 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
//		sb.append("</ShipAddress>\n");
//		sb.append("<IsPending>BOOLTYPE</IsPending>\n");                      // <!--opt, not in QBOE -->
//		sb.append("<PONumber>STRTYPE</PONumber>\n");                         // <!--opt, max length = 25 for QBD|QBCA|QBUK, not in QBOE -->
//		sb.append("<TermsRef>\n");                                           // <!--opt -->
//		sb.append("  <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
//		sb.append("  <FullName>STRTYPE</FullName>\n");                       // <!--opt, max length = 31 for QBD|QBCA|QBUK, max length = 100 for QBOE -->
//		sb.append("</TermsRef>\n");
//		sb.append("<DueDate>DATETYPE</DueDate>\n");                          // <!--opt -->
//		sb.append("<SalesRepRef>\n");                                        // <!--opt, not in QBOE -->
//		sb.append("  <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
//		sb.append("  <FullName>STRTYPE</FullName>\n");                       // <!--opt, max length = 5 for QBD|QBCA|QBUK -->
//		sb.append("</SalesRepRef>\n");
//		sb.append("<FOB>STRTYPE</FOB>\n");                                   // <!--opt, max length = 13 for QBD|QBCA|QBUK, not in QBOE -->
//		sb.append("<ShipDate>DATETYPE</ShipDate>\n");                        // <!--opt -->
//		sb.append("<ShipMethodRef>\n");                                      // <!--opt, not in QBOE -->
//		sb.append("  <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
//		sb.append("  <FullName>STRTYPE</FullName>\n");                       // <!--opt, max length = 15 for QBD|QBCA|QBUK -->
//		sb.append("</ShipMethodRef>\n");
//		sb.append("<ItemSalesTaxRef>\n");                                    // <!--opt, not in QBOE -->
//		sb.append("  <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
//		sb.append("  <FullName>STRTYPE</FullName>\n");                       // <!--opt, max length = 31 for QBD|QBCA|QBUK -->
//		sb.append("</ItemSalesTaxRef>\n");
//		sb.append("<Memo>STRTYPE</Memo>\n");                                 // <!--opt, max length = 4095 for QBD|QBCA|QBUK, max length = 4000 for QBOE -->
//		sb.append("<CustomerMsgRef>\n");                                     // <!--opt, not in QBOE -->
//		sb.append("  <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
//		sb.append("  <FullName>STRTYPE</FullName>\n");                       // <!--opt, max length = 101 for QBD|QBCA|QBUK -->
//		sb.append("</CustomerMsgRef>\n");
//		sb.append("<IsToBePrinted>BOOLTYPE</IsToBePrinted>\n");              // <!--opt -->
//		sb.append("<IsToBeEmailed>BOOLTYPE</IsToBeEmailed>\n");              // <!--opt, not in QBOE, v6.0 -->
//		sb.append("<IsTaxIncluded>BOOLTYPE</IsTaxIncluded>\n");              // <!--opt, not in QBD|QBOE, v6.0 -->
//		sb.append("<CustomerSalesTaxCodeRef>\n");                            // <!--opt, not in QBOE -->
//		sb.append("  <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
//		sb.append("  <FullName>STRTYPE</FullName>\n");                       // <!--opt, max length = 3 for QBD|QBCA|QBUK -->
//		sb.append("</CustomerSalesTaxCodeRef>\n");
//		sb.append("<Other>STRTYPE</Other>\n");                               // <!--opt, max length = 29 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
//		sb.append("<LinkToTxnID>IDTYPE</LinkToTxnID>\n");                    // <!--opt, may rep, not in QBOE, v6.0 -->
//		sb.append("\n"); // <!--BEGIN OR: You may have 0 or more InvoiceLineAdd OR InvoiceLineGroupAdd -->
//		sb.append("\n"); // <!--InvoiceLineAdd contains 1 optional attribute: 'defMacro' -->
		
		double salesAmount = 0.0;
		
		for ( ItemDTO salesItem : salesItems ) {

			sb.append("   <InvoiceLineAdd>\n");
			
			String invItemName = formatXML(salesItem.getInventoryItemName());
			String invItemID = formatXML(salesItem.getGlAcct());
						
			if ( invItemName != null && invItemName.length() > 0 ) {
				invItemID = null;
			} else {
				DecimalFormat df = new DecimalFormat("0.##");
				try {
					invItemID = df.parse(invItemID).toString();
					invItemName = null;
				} catch ( ParseException nfe ) {
					invItemID = "FDMS_UNKNOWN_ITEMID";
				}
			}
			
			sb.append("     <ItemRef>\n");                                          // <!--opt -->
			if ( invItemID != null ) {
//				sb.append("       <ListID>"+invItemID+"</ListID>\n");                     // <!--opt -->
				sb.append("       <FullName>"+invItemID+"</FullName>\n");                     // <!--opt -->
			}
			else if ( invItemName != null ) {
				sb.append("       <FullName>"+invItemName+"</FullName>\n");                    // <!--opt -->
			}
			sb.append("     </ItemRef>\n");
			
			
			sb.append("     <Desc>" + formatXML(salesItem.getDescription()) + "</Desc>\n");  // <!--opt, max length = 4095 for QBD|QBCA|QBUK, max length = 4000 for QBOE -->
//			sb.append("     <Quantity>1</Quantity>\n");                      // <!--opt -->
			// <!--BEGIN OR: You may optionally have Rate OR RatePercent OR PriceLevelRef -->
//			sb.append("  <Rate>PRICETYPE</Rate>\n");
//			sb.append("\n");   // <!--OR -->
//			sb.append("  <RatePercent>PERCENTTYPE</RatePercent>\n");
//			sb.append("\n");   // <!--OR -->
//			sb.append("  <PriceLevelRef>\n");                                    // <!--not in QBOE, v4.0 -->
//			sb.append("    <ListID>IDTYPE</ListID>\n");                          // <!--opt -->
//			sb.append("    <FullName>STRTYPE</FullName>\n");                     // <!--opt, max length = 31 for QBD|QBCA|QBUK -->
//			sb.append("  </PriceLevelRef>\n");
			// <!--END OR -->

			if ( locationClassCode != null && locationClassCode.trim().length() > 0 ) {
				sb.append("   <ClassRef>\n");                                           // <!--opt -->
//				sb.append("     <ListID>IDTYPE</ListID>\n");                            // <!--opt -->
				sb.append("     <FullName>" + locationClassCode.trim() + "</FullName>\n");                       // <!--opt, max length = 159 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->
				sb.append("   </ClassRef>\n");	
			}
			
			sb.append("     <Amount>" + formatDollarAmount(salesItem.getAmountOfTran()) + "</Amount>\n");                           // <!--opt -->
			sb.append("     <ServiceDate>" + FormatDate.convertDateToYYYY_MM_DD(salesItem.getDateOfTran()) + "</ServiceDate>\n");                // <!--opt -->
			
			boolean useSalesTax = (salesItem.getTaxAmount() > 0);
			
			if ( useSalesTax ) {
				salesAmount += salesItem.getTaxAmount();
				
				String taxCode = formatXML(salesItem.getTaxCode());
				
				if ( taxCode != null && taxCode.trim().length() > 0 ) {
					sb.append("    <SalesTaxCodeRef>\n");                                  // <!--opt, not in QBOE -->
					//sb.append("    <ListID>IDTYPE</ListID>\n");                          // <!--opt -->
					sb.append("      <FullName>" + salesItem.getTaxCode() + "</FullName>\n");  // <!--opt, max length = 3 for QBD|QBCA|QBUK -->
					sb.append("    </SalesTaxCodeRef>\n");
				}
				
				if ( useSalesTax ) {
					sb.append("     <IsTaxable>true</IsTaxable>\n");                    // <!--opt, not in QBD|QBCA|QBUK, v4.0 -->
				} else {
					sb.append("     <IsTaxable>false</IsTaxable>\n");                    // <!--opt, not in QBD|QBCA|QBUK, v4.0 -->
				}
			}
			
			String glAcct = formatXML(salesItem.getGlAcct());
			
			if ( glAcct != null && glAcct.trim().length() > 0 ) {
//				sb.append("  <OverrideItemAccountRef>\n");                   // <!--opt, v2.0 -->
////				sb.append("    <ListID>"+glAcct.trim()+"</ListID>\n");       // <!--opt -->
//				sb.append("    <FullName>"+glAcct+"</FullName>\n");         	 // <!--opt, max length = 159 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->
//				sb.append("  </OverrideItemAccountRef>\n");
			}
			
//			sb.append("  <Other1>STRTYPE</Other1>\n");                           // <!--opt, max length = 29 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
//			sb.append("  <Other2>STRTYPE</Other2>\n");                           // <!--opt, max length = 29 for QBD|QBCA|QBUK, not in QBOE, v6.0 -->
//			sb.append("  <LinkToTxn>\n");                                        // <!--opt, not in QBOE, v6.0 -->
//			sb.append("    <TxnID>IDTYPE</TxnID>\n");
//			sb.append("    <TxnLineID>IDTYPE</TxnLineID>\n");
//			sb.append("  </LinkToTxn>\n");
//			sb.append("  <DataExt>\n");                                          // <!--opt, may rep, not in QBOE, v5.0 -->
//			sb.append("    <OwnerID>GUIDTYPE</OwnerID>\n");
//			sb.append("    <DataExtName>STRTYPE</DataExtName>\n");               // <!--max length = 31 for QBD|QBCA|QBUK -->
//			sb.append("    <DataExtValue>STRTYPE</DataExtValue>\n");
//			sb.append("  </DataExt>\n");
			sb.append("  </InvoiceLineAdd>\n");
			
		}

		if ( salesAmount > 0 ) {
			sb.append("  <SalesTaxLineAdd>\n");                                    // <!--opt, not in QBD|QBCA|QBUK, v4.0 -->
			// <!--BEGIN OR: You may optionally have Amount OR RatePercent -->
			sb.append("     <Amount>" + formatDollarAmount(salesAmount) + "</Amount>\n");
			// <!--OR -->
//			sb.append("  <RatePercent>PERCENTTYPE</RatePercent>\n");
			// <!--END OR -->
//			sb.append("  <AccountRef>\n");                                       // <!--opt -->
//			sb.append("    <ListID>IDTYPE</ListID>\n");                          // <!--opt -->
//			sb.append("    <FullName>STRTYPE</FullName>\n");                     // <!--opt, max length = 1000 for QBOE -->
//			sb.append("  </AccountRef>\n");
			sb.append("  </SalesTaxLineAdd>\n");
		}
		
		/*
		sb.append("\n"); // <!--OR -->
		sb.append("<InvoiceLineGroupAdd>\n");                                // <!--not in QBOE -->
		sb.append("  <ItemGroupRef>\n");
		sb.append("    <ListID>IDTYPE</ListID>\n");                          // <!--opt -->
		sb.append("    <FullName>STRTYPE</FullName>\n");                     // <!--opt, max length = 31 for QBD|QBCA|QBUK -->
		sb.append("  </ItemGroupRef>\n");
		sb.append("  <Desc>STRTYPE</Desc>\n");                               // <!--opt, not in QBD|QBCA|QBUK -->
		sb.append("  <Quantity>QUANTYPE</Quantity>\n");                      // <!--opt -->
		sb.append("  <ServiceDate>DATETYPE</ServiceDate>\n");                // <!--opt, not in QBD|QBCA|QBUK -->
		sb.append("  <DataExt>\n");                                          // <!--opt, may rep, v5.0 -->
		sb.append("    <OwnerID>GUIDTYPE</OwnerID>\n");
		sb.append("    <DataExtName>STRTYPE</DataExtName>\n");               // <!--max length = 31 for QBD|QBCA|QBUK -->
		sb.append("    <DataExtValue>STRTYPE</DataExtValue>\n");
		sb.append("  </DataExt>\n");
		sb.append("</InvoiceLineGroupAdd>\n");
		sb.append("\n"); // <!--END OR -->
		*/
		
		/*
		sb.append("<DiscountLineAdd>\n");                                    // <!--opt, not in QBD|QBCA|QBUK, v4.0 -->
		sb.append("\n");   // <!--BEGIN OR: You may optionally have Amount OR RatePercent -->
		sb.append("  <Amount>AMTTYPE</Amount>\n");
		sb.append("\n");   // <!--OR -->
		sb.append("  <RatePercent>PERCENTTYPE</RatePercent>\n");
		sb.append("\n");   // <!--END OR -->
		sb.append("  <IsTaxable>BOOLTYPE</IsTaxable>\n");                    // <!--opt -->
		sb.append("  <AccountRef>\n");                                       // <!--opt -->
		sb.append("    <ListID>IDTYPE</ListID>\n");                          // <!--opt -->
		sb.append("    <FullName>STRTYPE</FullName>\n");                     // <!--opt, max length = 1000 for QBOE -->
		sb.append("  </AccountRef>\n");
		sb.append("</DiscountLineAdd>\n");
		*/
		
		/*
		sb.append("<SalesTaxLineAdd>\n");                                    // <!--opt, not in QBD|QBCA|QBUK, v4.0 -->
		sb.append("\n");   // <!--BEGIN OR: You may optionally have Amount OR RatePercent -->
		sb.append("  <Amount>AMTTYPE</Amount>\n");
		sb.append("\n");   // <!--OR -->
		sb.append("  <RatePercent>PERCENTTYPE</RatePercent>\n");
		sb.append("\n");   // <!--END OR -->
		sb.append("  <AccountRef>\n");                                       // <!--opt -->
		sb.append("    <ListID>IDTYPE</ListID>\n");                          // <!--opt -->
		sb.append("    <FullName>STRTYPE</FullName>\n");                     // <!--opt, max length = 1000 for QBOE -->
		sb.append("  </AccountRef>\n");
		sb.append("</SalesTaxLineAdd>\n");
		*/
		
		/*
		sb.append("<ShippingLineAdd>\n");                                    // <!--opt, not in QBD|QBCA|QBUK, v4.0 -->
		sb.append("  <Amount>AMTTYPE</Amount>\n");
		sb.append("  <AccountRef>\n");                                       // <!--opt -->
		sb.append("    <ListID>IDTYPE</ListID>\n");                          // <!--opt -->
		sb.append("    <FullName>STRTYPE</FullName>\n");                     // <!--opt, max length = 1000 for QBOE -->
		sb.append("  </AccountRef>\n");
		sb.append("</ShippingLineAdd>\n");
		*/
		
		sb.append("</InvoiceAdd>\n");
//		sb.append("<IncludeRetElement>STRTYPE</IncludeRetElement>\n");         // <!--opt, may rep, max length = 50 for QBD|QBCA|QBUK, not in QBOE, v4.0 -->
		sb.append("</InvoiceAddRq>\n");

		return ( sb.toString() );
	}	
	
	private String generateItemPaymentQBXML  (CustomerDTO customer, ArrayList <ItemDTO> paymentItems) {
		
		StringBuilder sb = new StringBuilder();
		
		for ( ItemDTO paymentItem : paymentItems ) {

			sb.append("<ReceivePaymentAddRq>\n");  // <!-- v1.1 -->
//			<!-- ReceivePaymentAdd contains 1 optional attribute: 'defMacro' -->
			sb.append("<ReceivePaymentAdd>\n");

			if ( customer != null ) {
				sb.append("<CustomerRef>\n");
				sb.append("  <FullName>" + formatXML(customer.getFirstName()) + " " + formatXML(customer.getLastName()) + "</FullName>\n");                       // <!--opt, max length = 209 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->
				sb.append("</CustomerRef>\n");
			}
			
			String arAcct = paymentItem.getArAcct();
			
			if ( paymentItem.getAmountOfTran() > 0 ) { 
				arAcct="FDMS_VOIDED_PAYMENT";
			}
				
			if ( arAcct != null && arAcct.trim().length() > 0 ) {
				sb.append("<ARAccountRef>\n"); //                 <!-- opt -->");
				//sb.append("  <ListID>" + formatXML(paymentItem.getArAcct()) + "</ListID>"); //        <!-- opt -->");
				sb.append("  <FullName>"+arAcct+"</FullName>\n"); // <!-- opt, max length = 159 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->");
				sb.append("</ARAccountRef>\n");
			}
			
			sb.append("<TxnDate>" + FormatDate.convertDateToYYYY_MM_DD(paymentItem.getDateOfTran()) + "</TxnDate>\n"); // <!--opt -->
			sb.append("<RefNumber>" + paymentItem.getVitalsMasterKey() + "</RefNumber>\n");
			sb.append("<TotalAmount>" + formatDollarAmount(paymentItem.getAmountOfTran(), true) + "</TotalAmount>\n"); //                  <!-- opt -->");
			
			String payMeth = formatXML(paymentItem.getPaymentMethod());
			
			if ( payMeth == null || (payMeth != null && payMeth.length() == 0) ) {
				payMeth = "FDMS_PAYMETHOD_UNKNOWN";
			} 
			
			sb.append("<PaymentMethodRef>\n"); //                                <!-- opt -->");
//			sb.append("  <ListID>IDTYPE</ListID>"); //                           <!-- opt -->");
			sb.append("  <FullName>"+ payMeth + "</FullName>\n"); //             <!-- opt, max length = 31 for QBD|QBCA|QBUK, max length = 100 for QBOE -->");
			sb.append("</PaymentMethodRef>\n");
			sb.append("<Memo></Memo>\n"); //                                <!-- opt, max length = 4095 for QBD|QBCA|QBUK -->");
//			sb.append("<DepositToAccountRef>"); //                               <!-- opt -->");
//			sb.append("  <ListID>IDTYPE</ListID>"); //                           <!-- opt -->");
//			sb.append("  <FullName>STRTYPE</FullName>"); //                      <!-- opt, max length = 159 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->");
//			sb.append("</DepositToAccountRef>");
			
			
			/*
			sb.append("<CreditCardTxnInfo>"); //                                 <!-- opt, not in QBOE, v4.1 -->");
			sb.append("  <CreditCardTxnInputInfo>");
			sb.append("    <CreditCardNumber>STRTYPE</CreditCardNumber>"); //    <!-- max length = 25 for QBD|QBCA|QBUK -->");
			sb.append("    <ExpirationMonth>INTTYPE</ExpirationMonth>"); //      <!-- min value = 1, max value = 12 -->");
			sb.append("    <ExpirationYear>INTTYPE</ExpirationYear>");
			sb.append("    <NameOnCard>STRTYPE</NameOnCard>"); //                <!-- max length = 41 for QBD|QBCA|QBUK -->");
			sb.append("    <CreditCardAddress>STRTYPE</CreditCardAddress>"); //  <!-- opt, max length = 41 for QBD|QBCA|QBUK -->");
			sb.append("    <CreditCardPostalCode>STRTYPE</CreditCardPostalCode>"); // <!-- opt, max length = 18 for QBD|QBCA|QBUK -->");
			sb.append("    <CommercialCardCode>STRTYPE</CommercialCardCode>"); // <!-- opt, max length = 24 for QBD|QBCA|QBUK -->");
//			    <!-- TransactionMode may have one of the following values: CardNotPresent [DEFAULT], CardPresent -->");
			sb.append("    <TransactionMode>ENUMTYPE</TransactionMode>"); //     <!-- opt, v6.0 -->");
			sb.append("  </CreditCardTxnInputInfo>");
			sb.append("  <CreditCardTxnResultInfo>");
			sb.append("    <ResultCode>INTTYPE</ResultCode>");
			sb.append("    <ResultMessage>STRTYPE</ResultMessage>"); //          <!-- max length = 60 for QBD|QBCA|QBUK -->");
			sb.append("    <CreditCardTransID>STRTYPE</CreditCardTransID>"); //  <!-- max length = 24 for QBD|QBCA|QBUK -->");
			sb.append("    <MerchantAccountNumber>STRTYPE</MerchantAccountNumber>"); // <!-- max length = 32 for QBD|QBCA|QBUK -->");
			sb.append("    <AuthorizationCode>STRTYPE</AuthorizationCode>"); //  <!-- opt, max length = 12 for QBD|QBCA|QBUK -->");
//			    <!-- AVSStreet may have one of the following values: Pass, Fail, NotAvailable -->");
			sb.append("    <AVSStreet>ENUMTYPE</AVSStreet>"); //                 <!-- opt -->");
//			    <!-- AVSZip may have one of the following values: Pass, Fail, NotAvailable -->");
			sb.append("    <AVSZip>ENUMTYPE</AVSZip>"); //                       <!-- opt -->");
//			    <!-- CardSecurityCodeMatch may have one of the following values: Pass, Fail, NotAvailable -->");
			sb.append("    <CardSecurityCodeMatch>ENUMTYPE</CardSecurityCodeMatch>"); // <!-- opt, v6.0 -->");
			sb.append("    <ReconBatchID>STRTYPE</ReconBatchID>"); //            <!-- max length = 84 for QBD|QBCA|QBUK -->");
			sb.append("    <PaymentGroupingCode>INTTYPE</PaymentGroupingCode>");
//			    <!-- PaymentStatus may have one of the following values: Unknown, Completed -->");
			sb.append("    <PaymentStatus>ENUMTYPE</PaymentStatus>");
			sb.append("    <TxnAuthorizationTime>DATETIMETYPE</TxnAuthorizationTime>");
			sb.append("    <TxnAuthorizationStamp>INTTYPE</TxnAuthorizationStamp>");
			sb.append("    <ClientTransID>STRTYPE</ClientTransID>"); //          <!-- opt, max length = 16 for QBD|QBCA|QBUK, v6.0 -->");
			sb.append("  </CreditCardTxnResultInfo>");
			sb.append("</CreditCardTxnInfo>");
			*/
//			     <!-- BEGIN OR: You may have IsAutoApply OR AppliedToTxnAdd -->");
			sb.append("<IsAutoApply>true</IsAutoApply>"); //                 <!-- not in QBOE -->");
//			 <!-- OR -->");
//			sb.append("<AppliedToTxnAdd>\n"); //                                   <!-- rep (1 or more) -->");
//			sb.append("  <TxnID>" + paymentItem.getTranHistId() + "</TxnID>"); //                             <!-- may be macro value -->");
//			sb.append("  <PaymentAmount>" + formatDollarAmount(paymentItem.getAmountOfTran(), true) + "</PaymentAmount>\n"); //            <!-- opt -->");
//			sb.append("  <TxnLineDetail>"); //                                   <!-- opt, may rep, not in QBD|QBCA|QBUK -->");
//			sb.append("    <TxnLineID>IDTYPE</TxnLineID>");
//			sb.append("    <Amount>" + formatDollarAmount(paymentItem.getAmountOfTran()) + "</Amount>");
//			sb.append("  </TxnLineDetail>");
//			sb.append("  <SetCredit>"); //                                       <!-- opt, may rep -->");
//			sb.append("    <CreditTxnID>IDTYPE</CreditTxnID>"); //               <!-- may be macro value -->");
//			sb.append("    <TxnLineID>IDTYPE</TxnLineID>"); //                   <!-- opt, not in QBD|QBCA|QBUK -->");
//			sb.append("    <AppliedAmount>AMTTYPE</AppliedAmount>");
//			sb.append("  </SetCredit>");
//			sb.append("  <DiscountAmount>AMTTYPE</DiscountAmount>"); //          <!-- opt -->");
//			sb.append("  <DiscountAccountRef>"); //                              <!-- opt -->");
//			sb.append("    <ListID>IDTYPE</ListID>"); //                         <!-- opt -->");
//			sb.append("    <FullName>STRTYPE</FullName>"); //                    <!-- opt, max length = 159 for QBD|QBCA|QBUK, max length = 1000 for QBOE -->");
//			sb.append("  </DiscountAccountRef>");
//			sb.append("</AppliedToTxnAdd>\n");
//			sb.append("<!-- END OR -->");
			sb.append("</ReceivePaymentAdd>\n");
//			sb.append("<IncludeRetElement>STRTYPE</IncludeRetElement>"); //        <!-- opt, may rep, max length = 50 for QBD|QBCA|QBUK, not in QBOE, v4.0 -->");
			sb.append("</ReceivePaymentAddRq>\n");

		}
		
	    return ( sb.toString() );
	}
	
}
