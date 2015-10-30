package com.aldorsolutions.webfdms.beans.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.aldorassist.webfdms.dao.SpeedDataDAO;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.database.PersistenceException;


public class SpeedDataCache extends Cache {

	private static SpeedDataCache instance = null;

	

	/**
	 * default CTOR (hidden)
	 */
	protected SpeedDataCache() {
		super();
	}

	/**
	 * the singleton instance
	 * 
	 * @return - the instance of this class
	 */
	public static SpeedDataCache getInstance() {
		if (instance == null) {
			instance = new SpeedDataCache();
		}
		return instance;
	}

	/**
	 * convenience method for creating dynamic filters based category
	 * 
	 * @param category -
	 *            the  category for the filter
	 * @return - the filter array
	 */
	public Entry makeCategoryFilter(String category) {
		
		if ( category != null ) {
			category = category.toUpperCase();
		}
		
		return new Entry("category", category) ;
	}

	public Entry makeLocaleFilter(String locale) {
		return  new Entry("locale", locale, "0") ;
	}
	
	public Entry makeLocationFilter(String location) {
		return  new Entry("locationId", location, "0") ;
	}
	public Entry makeDataFilter(String data) {
		return  new Entry("data", data) ;
	}
	
	/**
	 * retrieves the cache key name for the EJB
	 * 
	 * @returns - the cache key name
	 */
	protected String getCacheName() {
		return "SpeedData";
	}
	
	/**
	 * checks if the object specified matches the specifed filters
	 * 
	 * @param obj -
	 *            the object to match against the filter
	 * @param filter -
	 *            the array of filters to match against
	 * @return - true if there is a match
	 */
	protected boolean matchFilter(Object obj, Entry[] filter) {

        boolean match = false;

        // we assume a null filter means a match
        if (filter == null || filter.length == 0)
            return true;

        if ((obj instanceof DbSpeedData) == false ) {
        	return ( false );
        }

       	DbSpeedData bean = (DbSpeedData) obj;

		// this is the AND map conditions
		HashMap<Object, Boolean> filterMap = new HashMap<Object, Boolean>();

		Entry categoryKey = null;

		for (Entry filterKey : filter) {
			if (((String) filterKey.key).equals("category")) {
				if (isEquals(filterKey.value, bean.getCategory())) {
					categoryKey = filterKey;
					break;
				}
			}
		}
		
		if (categoryKey == null) {
			return ( false );
		}
		
		// for filters we assume same key field filters specify OR
		// conditions
		// and differing key field filters specify AND conditions
		for (int i = 0; i < filter.length; i++) {
			boolean hit = false;

			if (((String) filter[i].key).equals("category")) {
				if (isEquals(filter[i].value, bean.getCategory())) {
					hit = true;
				}
			} else if (((String) filter[i].key).equals("locale")) {
				if (isEquals(filter[i].value, Integer.toString(bean.getLocale()))) {
					hit = true;
				} else if (isEquals(filter[i].defaultValue, filter[i].value)) {
					hit = true;
				}
			} else if (((String) filter[i].key).equals("locationId")) {
				if (isEquals(filter[i].value, Integer.toString(bean.getLocationId()))) {
					hit = true;
				} else if (isEquals(filter[i].defaultValue, filter[i].value)) {
					hit = true;
				}
			} else if (((String) filter[i].key).equals("data")) {
				if (startsWith(filter[i].value, bean.getData())) {
					hit = true;
				}
			} else if (((String) filter[i].key).equals("sortSequence")) {
				if (isEquals(filter[i].value, new Integer(bean
						.getSortSequence()).toString())) {
					hit = true;
				}
			}

			Boolean hitBool = (Boolean) filterMap.get(filter[i].key);
			if (hitBool == null) {
				// first time just add the boolean
				filterMap.put(filter[i].key, (hit ? Boolean.TRUE : Boolean.FALSE));
			} else {
				// if same field we assume OR i.e. any matches will make it
				// true
				if (!hitBool.booleanValue() && hit)
					filterMap.put(filter[i].key, Boolean.TRUE);
			}
		}


		match = true;

		// now we iterate over all the AND conditions
		Iterator<Boolean> iter = filterMap.values().iterator();
		while (iter.hasNext()) {
			if ( iter.next().booleanValue() == false ) {
				// any false, all false
				match = false;
				break;
			}
		}
		
        return match;
    }

	protected Collection <Object> updateCache(String dblookup ) throws Exception {
		
		List <Object> list = null;
		List <Object> cacheList = (List<Object>) cache.get(dblookup);
		Boolean refreshObj = (Boolean)refreshMap.get(dblookup);
		boolean refresh = false;
		if(refreshObj != null){
			refresh = refreshObj.booleanValue();
		}
		
		if ( cacheList == null || refresh )
		{
			try {
				SpeedDataDAO sdDao = new SpeedDataDAO (dblookup);
				ArrayList <DbSpeedData> dataList = sdDao.getAllSpeedData(dblookup);
				list = new ArrayList <Object> ();
				list.addAll(dataList);
				
				if(refresh){
					refreshMap.put(dblookup, new Boolean(false));
				}
			} catch (PersistenceException e) {
				logger.error("FdmsDb::getSpeedData Persistence Exception:" + e.getMessage() );
				return null;
			}
		}
		
		return list;
	}

	public static boolean isEquals(Object o1, Object o2) {
		
		String s1 = null;
		String s2 = null;
		
		Integer i1 = null;
		Integer i2 = null;
		
		boolean isString = false;
		boolean isInteger = false;
		
		if ( o1 instanceof String) {
			s1 = (String) o1;
			s1 = s1.toUpperCase();
			isString = true;
		} else if ( o1 instanceof Integer) {
			i1 = (Integer) o1;
			isInteger = true;	
		}
		
		if ( o2 instanceof String) {
			s2 = (String) o2;
			s2 = s2.toUpperCase();
			isString = true;
		} else if ( o2 instanceof Integer) {
			i2 = (Integer) o2;
			isInteger = true;	
		}
		
		if ( isString ) {
			if (s1 == null || s2 == null) {
				return s1 == s2;
			} else {
				return s1.equals(s2);
			}
		} else if ( isInteger ) {
			if (i1 == null || i2 == null) {
				return i1 == i2;
			} else {
				return i1.equals(i2);
			}
		} else {
			return ( false );
		}
		 
	}
	
	public static boolean startsWith(Object o1, Object o2) {
		
		String s1 = null;
		String s2 = null;
		
		if ( o1 instanceof String) {
			s1 = (String) o1;
			s1 = s1.toUpperCase();
		} 
		
		if ( o2 instanceof String) {
			s2 = (String) o2;
			s2 = s2.toUpperCase();
		}
		
		if (s1 == null || s2 == null)
			return s1 == s2;
		else {
			return s2.startsWith(s1); 
		}
			
	}
	
	/**
	 * @return the refresh
	 */
	public boolean isRefresh(String dblookup) {
		return ((Boolean)refreshMap.get(dblookup)).booleanValue();
	}

	/**
	 * @param refresh
	 *            the refresh to set
	 */
	public void setRefresh(String  dblookup) {
		refreshMap.put(dblookup, new Boolean(true));
		
	}
}
