
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:41 LKT)
 */

            package com.aldorsolutions.webservice.services.fddeservice;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://aldorsolutions.com/webservice/xsd/fdde/service".equals(namespaceURI) &&
                  "resultType".equals(typeName)){
                   
                            return  com.aldorsolutions.webservice.xsd.fdde.service.ResultType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://aldorsolutions.com/webservice/xsd/fdde/service".equals(namespaceURI) &&
                  "errorCodeType".equals(typeName)){
                   
                            return  com.aldorsolutions.webservice.xsd.fdde.service.ErrorCodeType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://aldorsolutions.com/webservice/xsd/comm/fdde".equals(namespaceURI) &&
                  "funeralHomeIdType".equals(typeName)){
                   
                            return  com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeIdType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://aldorsolutions.com/webservice/xsd/fdde/service".equals(namespaceURI) &&
                  "dataRequestType".equals(typeName)){
                   
                            return  com.aldorsolutions.webservice.xsd.fdde.service.DataRequestType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://aldorsolutions.com/webservice/xsd/fdde/service".equals(namespaceURI) &&
                  "criteriaType".equals(typeName)){
                   
                            return  com.aldorsolutions.webservice.xsd.fdde.service.CriteriaType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://aldorsolutions.com/webservice/xsd/comm/fdde".equals(namespaceURI) &&
                  "funeralHomeType".equals(typeName)){
                   
                            return  com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://aldorsolutions.com/webservice/xsd/fdde/service".equals(namespaceURI) &&
                  "dataInterestType".equals(typeName)){
                   
                            return  com.aldorsolutions.webservice.xsd.fdde.service.DataInterestType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://aldorsolutions.com/webservice/xsd/fdde/service".equals(namespaceURI) &&
                  "dataResponseType".equals(typeName)){
                   
                            return  com.aldorsolutions.webservice.xsd.fdde.service.DataResponseType.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    