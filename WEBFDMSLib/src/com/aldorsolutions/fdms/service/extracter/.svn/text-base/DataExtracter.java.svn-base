package com.aldorsolutions.fdms.service.extracter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.aldorsolutions.fdms.exception.FDDEException;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.GenericThread;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;
import com.aldorsolutions.webservice.xsd.fdde.service.InvalidRowDetails;

public abstract class DataExtracter<D> {
	
	private String name;
	private Logger logger ;
	protected DbUserSession user;
	private List<FuneralHomeType> funeralHomes;
	protected CaseFilterType caseFilter;
	protected boolean globalExtract;
	protected boolean reportInvalidRowIds;
	protected boolean includeInvalidRowIds;
	protected List<InvalidRowDetails> invalidRows;
	
	public DataExtracter(String name, DbUserSession user, List<FuneralHomeType> funeralHomes, 
			CaseFilterType caseFilter, boolean globalExtract,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows){
		if(name == null || user == null || funeralHomes == null || funeralHomes.isEmpty()){
			throw new FDDEException(this.getClass().getSimpleName()+" is not intialized correctly, can not extract the data.");
		}
		logger = Logger.getLogger(this.getClass().getName());
		this.name = name;
		this.user = user;
		this.funeralHomes = funeralHomes;
		this.caseFilter = caseFilter;
		this.globalExtract = globalExtract;
		this.reportInvalidRowIds = reportInvalidRowIds;
		this.includeInvalidRowIds = includeInvalidRowIds;
		this.invalidRows = invalidRows;
	}
	
	public List<D> extractData(){
		
		List<D> dataList = null;
		List<FuneralHomeType[]> funeralHomeArrayList = getFuneralHomeArrayList(funeralHomes);
		if(funeralHomeArrayList!=null && funeralHomeArrayList.size()>0){
			List<GenericThread> threads = new ArrayList<GenericThread>(funeralHomeArrayList.size());
			for(FuneralHomeType[] fhArray: funeralHomeArrayList){
				threads.add(getNewThread(fhArray));
			}
			executeAllThreads(threads, name);
			if(!reportInvalidRowIds){
				dataList = getBlankDataList();
			}
			Map<FuneralHomeType, RuntimeException> exceptions = null;
			for(GenericThread thread: threads){
				if(thread.isProcessed() && thread.isSucess()){
					
					if(!reportInvalidRowIds && thread.hasData()){
						dataList.addAll(getDataFromThread(thread));
					}
					if(invalidRows != null && thread.getInvalidRows() != null && thread.getInvalidRows().size()>0){
						invalidRows.addAll(thread.getInvalidRows());
					}
					
				}else{
					
					if(thread.isAlive()){
						
						logger.error(name + " not finish all its threads");
						throw new FDDEException(name + " not finish all its threads");
						
					}else if(thread.isProcessed() && !thread.isSucess()){
						
						if(thread.hasExceptions()){
							if(thread.getExceptions().containsKey(null)){
								logger.error(name + " failed to excute",thread.getExceptions().get(null) );
								throw new FDDEException(name + " failed to excute", thread.getExceptions().get(null));
							}else{
								if(exceptions == null){
									exceptions = new HashMap<FuneralHomeType, RuntimeException>();
								}
								exceptions.putAll(thread.getExceptions());
							}
						}
					}
					
				}
			}
			
			if(exceptions != null && exceptions.size()>0){
				StringBuffer funeralHomeList = new StringBuffer(exceptions.size()*10);
				for(FuneralHomeType funeralHome: exceptions.keySet()){
					if(funeralHome != null && funeralHome.getId() != null){
						if(funeralHomeList.length()>0){
							funeralHomeList.append(", ");
						}
						funeralHomeList.append(funeralHome.getId().getFuneralHomeIdType());
					}
				}
				String errorMsg = name + " failed for funeral homes " + funeralHomeList.toString();
				throw new FDDEException(errorMsg);
			}
		}
		
		return dataList;
		
	}
	protected abstract List<D> getDataFromThread(GenericThread thread) ;

	protected abstract GenericThread getNewThread(FuneralHomeType[] fhArray) ;

	protected abstract List<D> getBlankDataList() ;

	private void executeAllThreads(List<? extends GenericThread> threads, String name){
		
		long startTime = System.currentTimeMillis(); 
		for(GenericThread thread: threads){
			thread.start();
		}
		for(GenericThread thread: threads){
			int count = 0;
			while(thread.isAlive()){
				try {
					thread.join();
				} catch (InterruptedException e) {
					if(++count>3){
						int index = threads.indexOf(thread);
						logger.error(name + index +" thread has an issue stopped process");
						throw new FDDEException(name + index +" thread has an issue stopped process",e);
					}
//					e.printStackTrace();
				}
			}
		}
		long endTime = System.currentTimeMillis();
		logger.debug(name + " excuted in " + (endTime-startTime) + " milliseconds.");
		
	}
	private List<FuneralHomeType[]> getFuneralHomeArrayList(List<FuneralHomeType> funeralHomes){

		if(funeralHomes!=null && funeralHomes.size()>0){
			int[] numOfThreads = getNumberOfThreads(funeralHomes.size());
			if(numOfThreads[0]>0){
				List<FuneralHomeType[]> funeralHomeArrayList = new ArrayList<FuneralHomeType[]>(numOfThreads[0]);
				int noThreadsToCreate = numOfThreads[0];
				int fhIndex = 0;
				while(noThreadsToCreate>0){
					List<FuneralHomeType> funeralHomesPerThread = null;
					int noFuneralHomes = 0;
					if(noThreadsToCreate==1 && numOfThreads[2]>0){
						noFuneralHomes = numOfThreads[2];
					}else{
						noFuneralHomes = numOfThreads[1];
					}
					funeralHomesPerThread = new ArrayList<FuneralHomeType>(noFuneralHomes);
					while(noFuneralHomes>0 && fhIndex<funeralHomes.size()){
						funeralHomesPerThread.add(funeralHomes.get(fhIndex++));
						noFuneralHomes--;
					}
					if(funeralHomesPerThread.size()>0){
						funeralHomeArrayList.add(
								funeralHomesPerThread.toArray(new FuneralHomeType[funeralHomesPerThread.size()]));
					}
					noThreadsToCreate--;
				}
				return funeralHomeArrayList;
			}
		}
		return null;
	}
	
	private int[] getNumberOfThreads(int numberOfLocations){
		int[] threadNos = {0,0,0};
		if(numberOfLocations>0){
			int noLocsPerThread = 10;
			int numOfThreads = numberOfLocations/noLocsPerThread;
			int numOfFuneralHomesInFinalThread = numberOfLocations%noLocsPerThread;
			if(numOfFuneralHomesInFinalThread>0){
				numOfThreads++;
			}
			threadNos[0]=numOfThreads;
			threadNos[1]=noLocsPerThread;
			threadNos[2]=numOfFuneralHomesInFinalThread;
		}
		return threadNos;
	}

}
