package com.aldorsolutions.webfdms.ums.bean;

import java.util.ArrayList;

import com.aldorsolutions.webfdms.ums.dao.RolesDAO;
import com.aldorsolutions.webfdms.ums.model.RolesDTO;
import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;

public class RolesManager {

	private static RolesManager rolesMgr = null;
	private ArrayList <RolesDTO> roles = null;
	
	static {
		rolesMgr = new RolesManager();
	}
	/**
	 * 
	 */
	private RolesManager() {
		super();
		roles = new ArrayList <RolesDTO> ();
		RolesDAO dao = new RolesDAO();
		roles = dao.getRoles(true);
	}
	
	public static RolesManager getInstance () {
		return ( rolesMgr );
	}
	
	public ArrayList <RolesDTO> getAllRoles () {
		return ( roles );
	}
	
	public RolesDTO getRole (long roleID) {
		
		if ( roles == null ) {
			return ( null );
		}
		
		for ( RolesDTO role : roles ) {
			if ( role.getRoleID() == roleID ) {
				return ( role );
			}
		}
		
		return ( null );
	}
	
	public boolean isUserAssignedRole (long userID, long roleID) {
		ArrayList <RolesDTO> roles = getRolesByUser ( userID );
		
		return ( isUserAssignedRole (roles, roleID) ); 
	}
	
	public boolean isUserAssignedRole (ArrayList <RolesDTO> roles, long roleID) {
		
		for ( RolesDTO role : roles ) {
			if ( role.getRoleID() == roleID ) {
   				return(true); 
    		}
		}
		
		return ( false );
	}
	
	public ArrayList <RolesDTO> getRolesByUser ( long userID ) {
		RolesMembershipManager rmMgr = new RolesMembershipManager();
		ArrayList list = rmMgr.getRoleMembershipByUser (userID);
		ArrayList <RolesDTO> roles = new ArrayList <RolesDTO> ();
		
		if ( list != null ) {
			int listLength = list.size();
			
			RolesMembershipDTO rmDTO = null;
			
			for ( int i = 0; i < listLength; i++ ) {
				rmDTO = (RolesMembershipDTO) list.get(i);
				RolesDTO role = getRole(rmDTO.getRoleID());
				
				roles.add(role);
			}
			
		} 

		return ( roles );
	}
}
