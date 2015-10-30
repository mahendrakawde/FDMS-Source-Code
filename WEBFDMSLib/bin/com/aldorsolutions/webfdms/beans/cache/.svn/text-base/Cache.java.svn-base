package com.aldorsolutions.webfdms.beans.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public abstract class Cache {

	/** the cache as a Map */
	protected Map <String, Collection<Object> > cache = new HashMap <String, Collection<Object> > ();
	
	/* refreshFlag Map*/
	protected Map <String, Boolean> refreshMap = new HashMap <String, Boolean>();

	/** the cached filtered sub lists */
	protected Map <Collection <Entry>, Collection<Object> > filteredCache = null;

	/** our logger */
	protected Logger logger = Logger.getLogger(this.getClass());

	protected Object[] staticFilters = new Object[0];

	/**
	 * resets the cache
	 */
	public void resetCache() {
		if (cache != null) {
			cache = null;
			filteredCache = null;
		}
	}

	/**
	 * returns the cache
	 * 
	 * @return - the cache as a List
	 */
	public List <Object> getCache(String dblookup) {
		Collection <Object> updates;
		
			try {
				// update the cache
				 updates = updateCache(dblookup);
				if (updates != null)
					cache.put(dblookup, updates);
			} catch (Exception e) {
				logger.error("Could not initialize the cache, the error was", e);
			}

		

		return (List <Object>) cache.get(dblookup);

	}
	
	/**
     * returns the cache with the filtered elements
     *
     * @param filter - an array of filter entries
     * @return - the cache as a List
     */
    public List <Object> getCache(Entry[] filter, String dblookup) {

        // just run to make sure we have an updated version
        List <Object> list = getCache(dblookup);
        if(list != null) {
        	cache.put(dblookup, list);
        }
        
        if (filter == null) {
            return (List <Object>) cache.get(dblookup);
        }

        if (filteredCache == null){
            filteredCache = new HashMap <Collection <Entry>, Collection<Object> >();
        }

        List <Object> filteredList = (List <Object> ) filteredCache.get(Arrays.asList(filter));
        if (filteredList == null || filteredList.size() == 0) {
            filteredList = filterListBy( (List <Object>) cache.get(dblookup), filter);
            
            saveFilteredCache(filter, filteredList);
        }

        return filteredList;

    }
    
    /**
     * filters the supplied List with the supplied filter and returns
     * the newly filtered sublist
     *
     * @param cache  - the cache to filter
     * @param filter - the filters to apply
     * @return - the filtered sublist
     */
    public List <Object> filterListBy(List <Object> cache, Entry[] filter) {

        ArrayList <Object> filteredList = new ArrayList<Object> ();

        if (cache != null) {

            int size = cache.size();
            for (int i = 0; i < size; i++) {
                if (matchFilter(cache.get(i), filter)) {
                    filteredList.add(cache.get(i));
                }
            }

        }

        return filteredList;

    }

       protected abstract boolean matchFilter(Object obj, Entry[] filter);
    
	/**
	 * 
	 * initializes, builds the cache
	 * 
	 * @throws Exception -
	 *             if there was an exception initializing the cache
	 */
	protected abstract Collection <Object> updateCache(String dblookup) throws Exception;
	
	/**
     * subclasses should override to add other filters
     * @param filter - the filter to persist
     * @param filteredList - filteredList as cache
     */
    protected void saveFilteredCache(Entry[] filter, List <Object> filteredList) {
        for (int i = 0; i < staticFilters.length; i++) {
            if (staticFilters[i].equals(filter)) {
                filteredCache.put(Arrays.asList(filter), filteredList);
            }
        }
    }
	
	/**
     * sets the staticFilter array
     * @param filters - an array of filter objects
     */
    protected void setStaticFilters(Object[] filters) {
        if (filters == null) {
            staticFilters = new Object[0];
        } else {
            staticFilters = filters;
        }
    }

    public static boolean isEquals(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			return o1 == o2;
		else
			return o1.equals(o2);
	}
        

        
	/**
	 * Simple class for filtered key value entries for the cache and the lookups
	 */
	public static class Entry {

		/** the key */
		public Object key = null;

		/** the value */
		public Object value = null;
		
		public Object defaultValue = null;

		/**
		 * default ctor
		 * 
		 * @param key -
		 *            a key holder
		 * @param value -
		 *            a value holder
		 */
		public Entry(Object key, Object value) {
			this.key = key;
			this.value = value;
		}
		
		public Entry(Object key, Object value, Object defaultValue) {
			this.key = key;
			this.value = value;
			this.defaultValue = defaultValue;
		}
		
		

		/**
		 * returns the hash code for this object
		 * 
		 * @return - a unique integer hash code for this object
		 */
		public int hashCode() {

			int hash = 0xFF;
			hash += (key == null ? 0 : key.hashCode());
			hash = (17 * hash) + (value == null ? 0 : value.hashCode());

			return hash;

		}
		
	}
}