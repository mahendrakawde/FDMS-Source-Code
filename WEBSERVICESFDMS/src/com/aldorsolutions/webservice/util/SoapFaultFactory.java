package com.aldorsolutions.webservice.util;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPFault;
import org.apache.axiom.soap.SOAPFaultCode;
import org.apache.axiom.soap.SOAPFaultDetail;
import org.apache.axiom.soap.SOAPFaultReason;
import org.apache.axiom.soap.SOAPFaultText;
import org.apache.axiom.soap.SOAPFaultValue;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;

import com.aldorsolutions.webservice.services.fddeservice.FddeFault;
import com.aldorsolutions.webservice.services.fddeservice.FddeFaultException0;
import com.aldorsolutions.webservice.services.fdmsservice.FdmsFault;
import com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException0;
import com.aldorsolutions.webservice.xsd.fdde.service.ErrorCodeType;

public class SoapFaultFactory {
	
	private static SoapFaultFactory instance = new SoapFaultFactory();
	
	public static SoapFaultFactory getInstance(){
		return instance;
	}
	
	public static FdmsFaultException0 createFdmsSoapFault(String code, String Reason) {
		
		FdmsFault fdmsFaultMsg = new FdmsFault();
		fdmsFaultMsg.setFaultCode(code);
		fdmsFaultMsg.setFaultReason(Reason);
		
		FdmsFaultException0 soapFault = new FdmsFaultException0();
		soapFault.setFaultMessage(fdmsFaultMsg);
		return soapFault;
		
	}
	
	public static FddeFaultException0 createFddeSoapFault(ErrorCodeType code, String cause) {
		
		FddeFault fddeFaultMsg = new FddeFault();
		fddeFaultMsg.setErrorCode(code);
		fddeFaultMsg.setCause(cause);
		
		FddeFaultException0 soapFault = new FddeFaultException0();
		soapFault.setFaultMessage(fddeFaultMsg);
		return soapFault;
		
	}
	
	/*public static AxisFault createSoapFault() throws AxisFault{
		MessageContext inMessageContext = MessageContext.getCurrentMessageContext();

        SOAPFactory soapFactory;
        if (inMessageContext.isSOAP11()) {
            soapFactory = OMAbstractFactory.getSOAP11Factory();
        } else {
            soapFactory = OMAbstractFactory.getSOAP12Factory();
        }
        
		SOAPFaultCode soapFaultCode= soapFactory.createSOAPFaultCode();
		SOAPFaultReason soapFaultReason = soapFactory.createSOAPFaultReason();
		SOAPFaultDetail soapFaultDetail = soapFactory.createSOAPFaultDetail();
		
        initFaultInformation(inMessageContext, soapFactory, soapFaultCode, soapFaultReason, soapFaultDetail);
        inMessageContext.setProperty(SOAP12Constants.SOAP_FAULT_CODE_LOCAL_NAME, soapFaultCode);
        inMessageContext
                .setProperty(SOAP12Constants.SOAP_FAULT_REASON_LOCAL_NAME, soapFaultReason);
        inMessageContext
                .setProperty(SOAP12Constants.SOAP_FAULT_DETAIL_LOCAL_NAME, soapFaultDetail);
        SOAPFault soapFault = soapFactory.createSOAPFault();
        soapFault.setCode(soapFaultCode);
        soapFault.setReason(soapFaultReason);
        soapFault.setDetail(soapFaultDetail);
        return new AxisFault(soapFault);

	}
	
	private static void initFaultInformation(MessageContext inMessageContext, SOAPFactory soapFactory, SOAPFaultCode soapFaultCode, SOAPFaultReason soapFaultReason, SOAPFaultDetail soapFaultDetail) {
        
        SOAPFaultValue soapFaultValue = soapFactory.createSOAPFaultValue(soapFaultCode);
        soapFaultValue.setText(new QName("http://test.org", "TestFault", "test"));
        
        SOAPFaultText soapFaultText = soapFactory.createSOAPFaultText(soapFaultReason);
        soapFaultText.setText("This is some FaultReason");
        
        QName qName = new QName("http://someuri.org", "FaultException");
        OMElement detail = soapFactory.createOMElement(qName, soapFaultDetail);
        qName = new QName("http://someuri.org", "ExceptionMessage");
        Throwable e = new Exception("This is a test Exception");
        while (e != null) {
            OMElement exception = soapFactory.createOMElement(qName, null);
            exception.setText(e.getMessage());
            detail.addChild(exception);
            e = e.getCause();
        }
    }*/

}
