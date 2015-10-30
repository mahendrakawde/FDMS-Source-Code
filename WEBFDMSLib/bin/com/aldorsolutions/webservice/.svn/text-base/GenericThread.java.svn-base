package com.aldorsolutions.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aldorsolutions.fdms.exception.FDDEException;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractLineItemType;
import com.aldorsolutions.webservice.xsd.fdde.service.InvalidRowDetails;

public abstract class GenericThread extends Thread {
	
	FuneralHomeType[] funeralHomes;
	boolean globalExtract;
	SCIFddeServiceDAO dao;
	private boolean processed;
	private boolean sucess;
	boolean reportInvalidRowIds;
	boolean includeInvalidRowIds;
	List<InvalidRowDetails> invalidRows;
	Map<FuneralHomeType, Integer> funeralhomeToSciLocationMap;
	
	Map<FuneralHomeType, RuntimeException> exceptions;
	
	public GenericThread(FuneralHomeType[] funeralHomes, boolean globalExtract,	DbUserSession user,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds){
		
		super();
		if(funeralHomes == null || funeralHomes.length==0){
			throw new IllegalArgumentException("funeralHomes can not be null or nill...");
		}
		for(FuneralHomeType funeralHome: funeralHomes){
			if(funeralHome == null){
				throw new IllegalArgumentException("funeralHome can not be null or nill...");
			}
		}
		this.funeralHomes = funeralHomes;
		this.reportInvalidRowIds = reportInvalidRowIds;
		this.includeInvalidRowIds = includeInvalidRowIds;
		if(reportInvalidRowIds || includeInvalidRowIds){
			invalidRows = new ArrayList<InvalidRowDetails>();
		}
		dao = new SCIFddeServiceDAO(user);
		this.globalExtract = globalExtract;
	}
	
	protected void extractFuneralHomeSpecificData(){
		funeralhomeToSciLocationMap = dao.getSciLocation(funeralHomes);
		if(invalidRows != null && funeralhomeToSciLocationMap != null && funeralhomeToSciLocationMap.size()>0){
			for(FuneralHomeType funeralHome: funeralhomeToSciLocationMap.keySet()){
				if(funeralhomeToSciLocationMap.get(funeralHome).intValue()==0){
					invalidRows.add(dao.getInvalidRowDetails(funeralHome.getId().getFuneralHomeIdType(), "Missing SCI Location", null, null));
				}
			}
		}
	}


	public Map<FuneralHomeType, Integer> getFuneralhomeToSciLocationMap() {
		return funeralhomeToSciLocationMap;
	}
	protected void process(){
		for(FuneralHomeType funeralHome: funeralHomes){
			try{
				processLocation(funeralHome);
			}catch(FDDEException ex){
				addException(funeralHome, ex);
			}
		}
		
		if(hasExceptions()){
			throw new FDDEException("Some locations have problems in retrieving contracts . . .");
		}
	}

	protected abstract void processLocation(FuneralHomeType funeralHome) ;

	public boolean isProcessed() {
		return processed;
	}

	@Override
	public void run() {
		try{
			extractFuneralHomeSpecificData();
			process();
			sucess = true;
		}catch(RuntimeException runtimeEx){
			
			sucess = false;
			if(!hasExceptions()){
				addException(null, runtimeEx);
			}else{
				runtimeEx.printStackTrace();
			}
		}catch(Exception ex){
			
			sucess = false;
			if(!hasExceptions()){
				addException(null, new FDDEException(ex.getMessage(), ex));
			}else{
				ex.printStackTrace();
			}
		}
		processed = true;
	}
	
	protected void addException(FuneralHomeType funeralHome, RuntimeException ex){
		if(exceptions == null){
			exceptions = new HashMap<FuneralHomeType, RuntimeException>();
		}
		exceptions.put(funeralHome, ex);
	}

	public Map<FuneralHomeType, RuntimeException> getExceptions() {
		return exceptions;
	}
	
	public boolean hasExceptions(){
		return exceptions != null && exceptions.size()>0;
	}

	public boolean isSucess() {
		return sucess;
	}

	public List<InvalidRowDetails> getInvalidRows() {
		return invalidRows;
	}
	public abstract boolean hasData();
}
