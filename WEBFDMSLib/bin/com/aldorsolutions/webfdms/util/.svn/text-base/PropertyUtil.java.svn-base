package com.aldorsolutions.webfdms.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;


public final class PropertyUtil {
   static Map allProperties = new HashMap();

   private static Logger logger = Logger.getLogger(PropertyUtil.class);


   /**
    * Returns the value for the property inside a propertyFile
    *
    * @param propertyName
    * @return
    */
   public static String getProperty(String propertyName, String propertyType) throws Exception {
       if (!"".equals(propertyName)) {
           
    	   Map map = getAllProperties( propertyType);
    	   
           return String.valueOf((Properties) map.get(propertyName));

       } else {
           logger.error("No such property exists in the property file");
           throw new Exception("No such property exists in the property file");
       }
   }
   
   /**
    * Returns all properties inside a propertyFile
    *
    * @param propertyType
    * @return Map
    */
   public static Properties getAllProperties(String propertyType) throws Exception {
	   if (!allProperties.containsKey(propertyType)) {
           Properties properties = new Properties();
           ClassLoader cl = Thread.currentThread().getContextClassLoader();
           InputStream is = cl.getResourceAsStream( propertyType + ".properties");
           try {
               if(is!=null){
                   properties.load(is);
                   allProperties.put(propertyType, properties);
               }else{
                   logger.error("Cannot open Properties file");
               }
           } catch (Exception e) {
               logger.error("Cannot Load properties ", e);
               throw new Exception("No such property exists in the property file", e);
           }
       }
       return  (Properties)allProperties.get(propertyType);

   }
}