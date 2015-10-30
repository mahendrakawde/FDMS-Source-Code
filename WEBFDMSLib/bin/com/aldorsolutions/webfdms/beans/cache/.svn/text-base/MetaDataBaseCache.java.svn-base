package com.aldorsolutions.webfdms.beans.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;

public class MetaDataBaseCache extends Cache {
	
private static MetaDataBaseCache instance = null;

	

	/**
	 * default CTOR (hidden)
	 */
	protected MetaDataBaseCache() {
		super();
	}

	/**
	 * the singleton instance
	 * 
	 * @return - the instance of this class
	 */
	public static MetaDataBaseCache getInstance() {
		if (instance == null) {
			instance = new MetaDataBaseCache();
		}
		return instance;
	}
	
	/**
	 * convenience method for creating dynamic filters 
	 * based database name
	 * 
	 * @param name -
	 *            the  name for the filter
	 * @return - the filter array
	 */
	public Entry makeNameFilter(String name) {
		return new Entry("", name) ;
	}
	
	/**
	 * retrieves the cache key name 
	 * 
	 * @returns - the cache key name
	 */
	protected String getCacheName() {
		return "MetaDataBase";
	}

	@Override
	protected boolean matchFilter(Object obj, Entry[] filter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Collection <Object> updateCache(String dblookup) throws Exception {
		
		List <Object> list = null;
		List <Object> cacheList = (List <Object>)cache.get(dblookup);
		Boolean refreshObj = (Boolean)refreshMap.get(dblookup);
		boolean refresh = false;
		if(refreshObj != null){
			refresh = refreshObj.booleanValue();
		}
		
		  // Now check and see if the list is empty or we need a refresh.  If either then
		  // Load the data.
		if ( cacheList == null || refresh )
		{
			try {
				CompanyManagerBean bean = new CompanyManagerBean();
				list = new ArrayList <Object> ();
				list.addAll( bean.getCompanies(true) );		 
				if(refresh){
					refreshMap.put(dblookup, new Boolean(false));
				}
			} catch (Exception e) {
				logger.error("MetaDataBaseCache::updateCache Persistence Exception:" + e.getMessage() );
				return null;
			}
		}
		return list;
	}
	/**
	 * @param refresh
	 *            the refresh to set
	 */
	public void setRefresh(String  dblookup) {
		refreshMap.put(dblookup, new Boolean(true));
		
	}
	/**
	 * @return the refresh
	 */
	public boolean isRefresh(String dblookup) {
		return ((Boolean) refreshMap.get(dblookup)).booleanValue();
	}
}
