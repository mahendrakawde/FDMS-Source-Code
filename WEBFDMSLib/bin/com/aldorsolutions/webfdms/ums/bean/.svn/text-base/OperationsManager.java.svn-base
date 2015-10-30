package com.aldorsolutions.webfdms.ums.bean;

import java.util.ArrayList;

import com.aldorsolutions.webfdms.ums.dao.OperationsDAO;
import com.aldorsolutions.webfdms.ums.model.OperationsDTO;

public class OperationsManager {

	private static OperationsManager operationMgr = null;
	
	private ArrayList <OperationsDTO> operations = null;
	
	static {
		operationMgr = new OperationsManager();
	}
	/**
	 * 
	 */
	private OperationsManager() {
		super();
		operations = new ArrayList <OperationsDTO> ();
		OperationsDAO dao = new OperationsDAO();
		operations = dao.getOperations(true);
	}
	
	public static OperationsManager getInstance () {
		return ( operationMgr );
	}
	
	public ArrayList <OperationsDTO> getOperations () {
		return ( operations );
	}
	
	public OperationsDTO getOperation (long operationID) {
		
		if ( operations == null ) {
			return ( null );
		}
		
		for ( OperationsDTO operation : operations ) {
			if ( operation.getOperationID() == operationID ) {
				return ( operation );
			}
		}
		
		return ( null );
	}
	
	public OperationsDTO getOperation (long operationID, String token) {
		
		if ( operations == null ) {
			return ( null );
		}
		
		for (OperationsDTO operation: operations) {
			
			if ( (operation.getOperationID() == operationID) && (operation.getToken() != null) )
			{
				if (operation.getToken().equals(token)) {	return operation; }
			}			
//			if ( (operation.getOperationID() == operationID) && (operation.getToken() != null) )
//			{
//				operation.getToken().equals(token);
//				return operation;
//			}
			
		}
		
		return ( null );
	}
	
	public OperationsDTO getOperation (String token) {
		
		if ( operations == null ) {
			return ( null );
		}
		
		for (OperationsDTO operation: operations) {
			
			if ( operation.getToken() != null )
			{
				operation.getToken().equals(token);
				return operation;
			}
			
		}
		
		return ( null );
	}
	
}
