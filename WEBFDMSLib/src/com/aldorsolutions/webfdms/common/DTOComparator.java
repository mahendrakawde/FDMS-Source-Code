package com.aldorsolutions.webfdms.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;


/**
 * Comparator to sort DTO objects
 * @author Administrator
 *
 */
public class DTOComparator implements Comparator {

	private Logger logger = Logger.getLogger(DTOComparator.class.getName());
	
	String method = null;
	String type = null;
	
	public DTOComparator(String method, String type){
		this.method = method;
		this.type = type;
	}
	
	public int compare(Object o1, Object o2) {
		try {
			if (Constants.ASCE_SORT_ORDER.equals(type)) {
				if (BeanUtils.getProperty(o1, method) != null
						&& BeanUtils.getProperty(o2, method) != null) {

					return BeanUtils.getProperty(o1, method).compareToIgnoreCase(
							BeanUtils.getProperty(o2, method));
				} else
					return 0;
			} else {
				if (BeanUtils.getProperty(o2, method) != null
						&& BeanUtils.getProperty(o1, method) != null) {

					return BeanUtils.getProperty(o2, method).compareToIgnoreCase(
							BeanUtils.getProperty(o1, method));
				} else
					return 0;
			}

		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage());
		}
		return 0;
	
	}

}
