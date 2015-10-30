/*
 * UserPermissionsBean.java
 *
 */

package com.aldorsolutions.webfdms.admin.user.bean;

/**
 *
 * @author David Rollins
 */

import java.util.ArrayList;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.ums.bean.RolesMembershipManager;
import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;



public class UserPermissionsBean {
    
    private DbUserSession user = null;
    private boolean isFuneralHome = false;
    private boolean isCemetery = false;
    private ArrayList <RolesMembershipDTO> rolesAssigned = null;
    
    public static int DENY = 0;
    public static int GRANTED = 1;
    
    public UserPermissionsBean ( DbUserSession user, CompanyDTO company ) {
    	this ( user, company.isFuneralClient(), company.isCemeteryClient() );
    }

    public UserPermissionsBean ( DbUserSession user, boolean isFuneralHome, boolean isCemetery ) {
    	this.user = user;
    	this.isFuneralHome = isFuneralHome;
    	this.isCemetery = isCemetery;
    	rolesAssigned = new ArrayList <RolesMembershipDTO> ();
    	
    	RolesMembershipManager rolesMgr = RolesMembershipManager.getInstance();
    	rolesAssigned = rolesMgr.getRoleMembershipByUser(user.getId());
    	this.user.setRoles(rolesAssigned);
    }
    
    private boolean isAdministrator () {
    	boolean admin = false;
    	
    	if ( user.getSecurityAdmin() == 1 ) {
    		admin = true;
    	}
    	
    	return ( admin );
    }
    
    private boolean isBank () {
    	boolean bank = false;
    	
    	if ( user.getSecurityBank() == 1 ) {
    		bank = true;
    	}
    	
    	return ( bank );
    }
    
    private boolean isEnableFinancialChange () {
    	boolean enableFinancialChange = false;
    	
    	if ( user.getSecurityEnableFinancialChange() == 1 ) {
    		enableFinancialChange = true;
    	}
    	
    	return ( enableFinancialChange );
    }
    
    private boolean isDashboardReport () {
    	boolean dashboardReport = false;
    	
    	if ( user.getSecurityDashboardReport() == 1 ) {
    		dashboardReport = true;
    	}
    	
    	return ( dashboardReport );
    }
    
    private boolean isFdmsNetwork () {
    	boolean fdmsNetwork = false;
    	
    	if ( user.getSecurityFdmsNetwork() == 1 ) {
    		fdmsNetwork = true;
    	}
    	
    	return ( fdmsNetwork );
    }
    private boolean isFdmsDashboard () {
    	boolean fdmsDashboard = false;
    	
    	if ( user.getSecurityFdmsDashboard() == 1 ) {
    		fdmsDashboard = true;
    	}
    	
    	return ( fdmsDashboard );
    }
    private boolean isFdmsWebservice () {
    	boolean fdmsWebservice = false;
    	
    	if ( user.getSecurityFdmsWebservice() == 1 ) {
    		fdmsWebservice = true;
    	}
    	
    	return ( fdmsWebservice );
    }
    private boolean isFddeWebservice () {
    	boolean fddeWebservice = false;
    	
    	if ( user.getSecurityFddeWebservice() == 1 ) {
    		fddeWebservice = true;
    	}
    	
    	return ( fddeWebservice );
    }
    private boolean isFdmsAdmin () {
    	boolean fdmsAdmin = false;
    	
    	if ( user.getSecurityFdmsAdmin() == 1 ) {
    		fdmsAdmin = true;
    	}
    	
    	return ( fdmsAdmin );
    }
    
    private boolean isReadOnly () {
    	boolean readOnly = false;
    	
    	if ( user.getSecurityViewOnly() == 1 ) {
    		readOnly = true;
    	}
    	
    	return ( readOnly );
    }
    

    public boolean isAdministratorGranted () {
    	return ( isAdministrator() );
    }

    public boolean isBankGranted () {
    	return ( isBank() );
    }
    public boolean isFinancialChangeGranted () {
    	return ( isEnableFinancialChange() );
    }
    public boolean isDashboardReportGranted () {
    	return ( isDashboardReport() );
    }
    
    public boolean isFdmsNetworkGranted () {
    	return ( isFdmsNetwork() );
    }
    public boolean isFdmsDashboardGranted () {
    	return ( isFdmsDashboard() );
    }
    public boolean isFdmsWebserviceGranted () {
    	return ( isFdmsWebservice() );
    }
    public boolean isFddeWebserviceGranted () {
    	return ( isFddeWebservice() );
    }
    
    public boolean isFdmsAdminGranted () {
    	return ( isFdmsAdmin() );
    }
    
    public boolean isReadOnlyGranted () {
    	return ( isReadOnly() );
    }
    
    public boolean isPaymentGranted () {
    	boolean payment = false;
    	
    	if ( user.getSecurityPayments() == 1 ) {
    		payment = true;
    	}
    	
    	if ( !payment ) {
    		payment = isAdministrator();
    	}
    	    	
    	return ( payment );
    }
    
    public boolean isDeleteCaseGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityDelete() == 1 ) {
    		access = true;
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	    	
    	return ( access );
    }
    
    public boolean isAtNeedGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityAtneed() == 1 ) {
    		access = true;
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	    	
    	return ( access );
    }
    
    public boolean isApModuleGranted () {
  	boolean access = false;
    CompanyOptionsManager coMgr = new CompanyOptionsManager ();
    // Now check to see if this options is turned on for this customer
    int apModule = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_APMODULE);

  	if ( apModule == 1 ) {
  		access = true;
  	}
//
// The administrator setting has no affect on weather the AP Module is turned on or off
// so I commented out this code.  	
//  	if ( !access ) {
//  		access = isAdministrator();
//  	}
  	    	
  	return ( access );
  }


    public boolean isPreNeedGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityPreneed() == 1 ) {
    		access = true;
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	    	
    	return ( access );
    }

    public boolean isCemeteryGranted () {
    	boolean access = false;
    	
    	if ( isCemetery && user.getSecurityCemetery() == 1 ) {
    		
    		access = true;
    	}
    	
    	return ( access );
    }
    
    public boolean isFuneralGranted () {
    	boolean access = false;
    	if ( isFuneralHome && user.getSecurityFuneral() == 1 ) {
    		
    		access = true;
    	}
    	
    	return ( access );
    }

    public boolean isCaseAccessGranted () {
    	
    	boolean access = isAtNeedGranted();

    	if ( !access ) {
    		access = isPreNeedGranted();
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	
    	return ( access );
    }

    public boolean isAcctsRecievableGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityAcctsRec() == 1 ) {
    		access = true;
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	    	
    	return ( access );
    }

    
    /**
     * Used to turn on the accounting Interface
     * @return
     */
    public boolean isAccountingInterfaceGranted () {
  		boolean access = false;
  	
  		if ( user.getSecurityAccountingInterface() == 1 ) {
  			access = true;
  		}
  	
//  		if ( !access ) {
//  			access = isAdministrator();
//  		}
  	    	
  		return ( access );
    }

    /**
     * Used to turn on the accounting Interface
     * @return
     */
    public boolean isEasyPaymentGranted () {
  		boolean access = false;
  	
  		if ( user.getSecurityEasyPayment() == 1 ) {
  			access = true;
  		}
  	
//  		if ( !access ) {
//  			access = isAdministrator();
//  		}
  	    	
  		return ( access );
    }
    
    /**
     * Used to turn on Speed Data
     * @return
     */
    public boolean isSpeedDataGranted () {
  		boolean access = false;
  	
  		if ( user.getSecuritySpeedData() == 1 ) {
  			access = true;
  		}
  	
  		if ( !access ) {
  			access = isAdministrator();
  		}
  	    	
  		return ( access );
    }

    /**
     * Used to thrun on the Arranger Manager
     * @return
     */                 
    public boolean isArrangerManagerGranted() {
			boolean access = false;
		
			if (user.getSecurityArrangerManagement() == 1) {
				access = true;
			}
		
			if (!access) {
				access = isAdministrator();
			}
		
			return (access);
		}

    /**
     * Used to thrun on the GLSalesAccount
     * @return
     */                 
    public boolean isGLSalesAccountGranted() {
			boolean access = false;
		
			if (user.getSecurityGLSalesAccount() == 1) {
				access = true;
			}
		
			if (!access) {
				access = isAdministrator();
			}
		
			return (access);
		}

    /** 
     * Used to turn on the Forms Avaialble
     * @return
     */
		public boolean isFormsAvailableGranted() {
			boolean access = false;
		
			if (user.getSecurityFormsAvaialble() == 1) {
				access = true;
			}
			// we are changing the rule here admin cannot do the formsavailable.
//			if (!access) {
//				access = isAdministrator();
//			}
		
			return (access);
		}

    public boolean isInventoryGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityInventory() == 1 ) {
    		access = true;
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	    	
    	return ( access );
    }

    public boolean isFormsAccessGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityForms() == 1 ) {
    		access = true;
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	    	
    	return ( access );
    }

    public boolean isFinancialGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityFinancial() == 1 ) {
    		access = true;
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	    	
    	return ( access );
    }

    public boolean isReportsGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityReports() == 1 ) {
    		access = true;
    	}
    	
    	if ( !access ) {
    		access = isAdministrator();
    	}
    	    	
    	return ( access );
    }  
    public boolean isAdjustFinancialGranted () {
    	boolean access = false;
    	
    	if ( user.getSecurityAdjustFinancial() == 1 ) {
    		access = true;
    	}
    	
    	    	
    	return ( access );
    } 
    //added by haranesh patel
    public boolean isEregisterbookGranted () {
  		boolean access = false;
  	
  		if ( user.getSecurityEregisterbook() == 1 ) {
  			access = true;
  		}
  	
//  		if ( !access ) {
//  			access = isAdministrator();
//  		}
  	    	
  		return ( access );
    }

    public boolean isPriceDescriptionGranted () {
  		boolean access = false;
  	
  		if ( user.getSecurityPriceDescriptionFinancial() == 1 ) {
  			access = true;
  		}
  		return ( access );
    }

}
