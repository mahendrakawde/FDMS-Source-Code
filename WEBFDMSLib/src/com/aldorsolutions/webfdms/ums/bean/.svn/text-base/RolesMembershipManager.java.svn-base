package com.aldorsolutions.webfdms.ums.bean;

import java.util.ArrayList;

import com.aldorsolutions.webfdms.ums.dao.RolesMembershipDAO;
import com.aldorsolutions.webfdms.ums.model.RolesDTO;
import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;

public class RolesMembershipManager {

	private static RolesMembershipManager rolesMgr = null;
	
	static {
		rolesMgr = new RolesMembershipManager();
	}
	/**
	 * 
	 */
	public RolesMembershipManager() {
		super();
	}
	
	public static RolesMembershipManager getInstance () {
		return ( rolesMgr );
	}
		
	public ArrayList <RolesMembershipDTO> getRoleMembershipByUser (long userID) {
		RolesMembershipDAO rolesDao = new RolesMembershipDAO();
		return ( rolesDao.getRolesMembership(userID) );
	}
	
	public String [] getRolesByUserAsArray ( long userID ) {
		
		ArrayList list = getRoleMembershipByUser (userID);
		
		if ( list != null ) {
			int listLength = list.size();
			
			String [] strArray = new String [list.size()];
			RolesMembershipDTO rmDTO = null;
			
			for ( int i = 0; i < listLength; i++ ) {
				rmDTO = (RolesMembershipDTO) list.get(i);
				strArray [i] = Long.toString( rmDTO.getRoleID() ); 
			}
			
			return ( strArray );
			
		} 
		else {
			return ( new String [0] );
		}
		
	}
	
	public boolean isUserAssignedRole (long userID, long roleID) {
		RolesMembershipDAO rolesDao = new RolesMembershipDAO();
		
		ArrayList roles = rolesDao.getRolesMembership(userID, roleID);
		if ( roles != null && roles.size() > 0 ) {
			return (true);
		}
		
		return (false);
	}
	
	public ArrayList <RolesMembershipDTO> getMembershipByRole (long companyID, long roleID) {
		RolesMembershipDAO rolesDao = new RolesMembershipDAO();
		
		return ( rolesDao.getMembershipByRole(companyID, roleID) );
	}
	
	public void addRoleMembership (long userID, ArrayList <RolesDTO> roles ) {
		RolesMembershipDAO rolesDao = new RolesMembershipDAO();
		
		for ( int i = 0; i < roles.size(); i++ ) {
			RolesDTO role = roles.get(i);
			rolesDao.addRolesMembership(userID, role.getRoleID());
		}
		
	}
	
	public void removeRoleMembership (long userID, ArrayList <RolesMembershipDTO> roles ) {
		RolesMembershipDAO rolesDao = new RolesMembershipDAO();
		
		for ( int i = 0; i < roles.size(); i++ ) {
			RolesMembershipDTO role = roles.get(i);
			rolesDao.deleteRolesMembership(userID, role.getRoleID());
		}
		
	}
	
}
