package com.aldorsolutions.webfdms.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;

/**
 * Utility function intended for the auditting system to generate a string representation of 
 * objects.  Primarily used against transfer objects with getters and setters. 
 * 
 * July 25, 2006
 * 
 * @author David Rollins
 *
 */
public class ReflectionUtil {

	static private Logger logger = Logger.getLogger(ReflectionUtil.class.getName());


	public static Object getNewInstance(Object object) {
		
		Object newObj = null;
		
		try {
			Class classObj = object.getClass();
			
			Constructor construct = classObj.getConstructor(null);
			
			newObj = construct.newInstance(null);
				
						
		} catch (Throwable e) {
			logger.error("Throwable exception in getNewInstance; object: " + object.getClass().getName(), e);
	    }
		
		return (newObj);
	}
	
	/**
	 * This method is intended to take a transfer object and pole it's 
	 * public getter methods to return a String representation of 
	 * of the object.  
	 * 
	 * @param object Expected to be a transfer object
	 * @return Returns a string representation of the object that 
	 * was passed in and it's values
	 */
	public static String getFieldValues(Object object) {
		StringBuffer strBuf = new StringBuffer();
		char delimiter = ',';
		
		// if we did a Class.forName we could only call static methods
		Class classObj = object.getClass();

		Method[] methods = classObj.getMethods();

		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];

			Class[] parameters = method.getParameterTypes();
			/*
			logger.debug("method.getName(): " + method.getName() + ";  Type= " + 
					method.getReturnType() + "  Modifiers=" + method.getModifiers() );
			*/
			
			if ((parameters.length == 0)
					&& (method.getReturnType() != void.class)
					&& (Modifier.isPublic(method.getModifiers()))) {

				strBuf.append("[");
				strBuf.append("fieldName=" + method.getName());
				strBuf.append(delimiter);
				strBuf.append("fieldType=" + method.getReturnType());
				strBuf.append(delimiter);
				strBuf.append("fieldValue=" + invokeMethod(object, method));
				strBuf.append("]");

			}
		}
			
		return strBuf.toString();
	}
	
	/**
	 * This function takes the original object with the method that 
	 * you want to call and invoke the method.   
	 * 
	 * @param object Object on which to invoke the method
	 * @param meth method that you want to invoke
	 * @return String representation of the value of that object
	 */
	public static String invokeMethod (Object object, Method meth) {
		String value = null;
		
		try {
			Object retobj = meth.invoke(object, null);
			
			if ( retobj != null ) 
			{
				value = retobj.toString(); 
			}
						
		} catch (Throwable e) {
			logger.error("Throwable exception in invokeMethod; method: " + meth.toString(), e);
	    }

		return ( value );
	}
	
}
