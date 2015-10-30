package fdms.ui.struts.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.bean.UserPermissionsBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.ActionClassLogDTO;
import com.aldorsolutions.webfdms.admin.user.model.DuplicateLoginLogDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbSecurityConfig;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.comparators.LocaleNameComparator;
import com.aldorsolutions.webfdms.beans.comparators.LocationNameComparator;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.util.ActionClassLogger;
import com.aldorsolutions.webfdms.util.DuplicateLoginLogger;
import com.aldorsolutions.webfdms.util.LoginLogger;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.ProcessLogonForm;

// Modified by :QPRIME (JO) for SOW# F030601A - Cemetery Management
//  Added logic to possibly access Cemetery Management screen .


public class ProcessLogon extends Action {
    
    private Logger logger = Logger.getLogger(ProcessLogon.class.getName());

    private String userName = null;
    private int userID = 0;
    private int companyID = 0;
    private int regionID = 0;
    private String logonStatus = "F";
    
    /**
     * Called from login.jsp, this action either verifies a user has access to the system
     * or logs into demo database or allows setting up a free trial.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
            
    	ActionForward forward = null;
    	logger.debug("*** Entering perform() in ProcessLogon ***");
    	ProcessLogonForm pForm = (ProcessLogonForm) form;
    	this.userName = pForm.getUsername();
        
    	try {
    		forward = performLogon ( mapping, pForm, request, response );
    	} catch ( Throwable t ) {
    		logger.error(" Error ProcessLogon: " + t.getMessage() );
    		t.printStackTrace();
    	}
    	finally {
    		String ip = request.getRemoteAddr();
    		String browser = pForm.getBrowser();
            String userAgent = pForm.getUserAgent();
            
            LoginLogger loginLog = new LoginLogger(userID, companyID, regionID);        
        	
            loginLog.logUser( userName, userID, regionID, System.currentTimeMillis(), ip, browser, 
            		userAgent, logonStatus );
            
    	}
    	
    	return ( forward );
    	
    }
    
    private ActionForward performLogon ( ActionMapping mapping,
    		ProcessLogonForm pForm, HttpServletRequest request,
            HttpServletResponse response ) {
    	

        ActionForward actionForward = null;
        HttpSession session = request.getSession();
        DbUserSession user = null;
        logonStatus = "F";
        
        String submitType = new String(pForm.getSubmitButton());
        
        // If 'submit' instead of 'login' - Display the UserRegistration form
        if (submitType == null) {
            // Make 'em hit a button
            return (new ActionForward(mapping.getInput()));
        }
        
        if (submitType.equals("membership") || submitType.equals("abbit")) {
            // The user isn't trying to log in, the user is trying to register
            //AppLog.trace("ProcessLogon calling User Registration Form.");
        	
        	//chai comment- use dblookup of user not webfdmsdata1.
            java.util.ArrayList stateList = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getStateList();
//        	java.util.ArrayList stateList = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getStateList(user.getDbLookup());
        	
            session.setAttribute("stateList",stateList);
            fdms.ui.struts.form.UserRegistration userRegistration = new fdms.ui.struts.form.UserRegistration();
            userRegistration.setActionType(submitType);
            session.setAttribute("userRegistration",userRegistration);
            actionForward = mapping.findForward("showUserRegistration");
            return actionForward;
            
        } else if (submitType.equals("freeTrial")) {
            //AppLog.trace("ProcessLogon calling Free Trial Form");
        	//chai comment- use dblookup of user not webfdmsdata1.
            java.util.ArrayList stateList = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getStateList();
//        	java.util.ArrayList stateList = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getStateList(user.getDbLookup());
        	
            session.setAttribute("stateList",stateList);
            fdms.ui.struts.form.FreeTrialRegistration freetrialRegistration = new fdms.ui.struts.form.FreeTrialRegistration();
            freetrialRegistration.setActionType(submitType);
            session.setAttribute("freetrialRegistration",freetrialRegistration);
            actionForward = mapping.findForward("showFreeTrialRegistration");
            return actionForward;
        }
        
        //AppLog.trace("Attempting to log the user in.");
        
        if (submitType.equals("guest")) {
            pForm.setUsername("Guest");
            pForm.setPassword("guest");
        }
        
        // Validate the request parameters specified by the user
        ActionErrors errors = new ActionErrors();
        String username = pForm.getUsername();
        String password = pForm.getPassword();

        boolean isCemetery = false;
        boolean isFuneral  = false;
        
        user = DbUser.findUserByUserName(username);
        
        if (user != null) {
        	
        	ActionForward forward = invalidUserLockout ( request, user, mapping, errors );
			
			if (!errors.isEmpty()) {
	            saveErrors(request, errors);
			}
			
			if ( forward != null ) {
	        	return ( forward );
	        }
			
			boolean validPassword = SecurityManagerBean.isValidPassword(password, user.getPassword());
        	
			if (validPassword == false) {
				user = null;
			}
			else
			{
				user.setLoggedIn(true);
			}
			
        	
        }
        
        if ( user == null ) {
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.mismatch"));
        	saveErrors(request, errors);
        	userID = 0;
        	return (new ActionForward(mapping.getInput()));
    	}
        else if ( user.getCompanyID () > 0 ) {
        	CompanyManagerBean mbCompany = new CompanyManagerBean ();
            CompanyDTO dtoCompany = mbCompany.getCompany(user.getCompanyID());
            
            if ( dtoCompany != null ) {
            	
             UserDAO userDao = new UserDAO();
           	 ArrayList <UserLocaleDTO> userLocales = userDao.getUserLocales(user.getId());
           	 ArrayList  <UserLocationDTO> userLocations = userDao.getUserLocations(user.getId()); 
           	 
	           	if( userLocales.isEmpty() && userLocations.isEmpty() ) {     //user can login if he has locale and location to operate 	
	           		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.userinfo.require.locallocation"));
	                   saveErrors(request, errors);
		            	
	           	}
	           	else {
	            	logger.info("User logging into: " + dtoCompany.getName() );
	                session.setAttribute(SessionValueKeys.DB_COMPANY, dtoCompany);
	                isCemetery = dtoCompany.isCemeteryClient();
	                isFuneral  = dtoCompany.isFuneralClient();
	                user.setConfigID ( dtoCompany.getConfigID () );
	           	}
            }
            else {
            	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.companyInvalid"));
                saveErrors(request, errors);
            }

        }
        else {
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.companyMissing"));
            saveErrors(request, errors);
        }
        
        // Report any errors we have discovered back to the original form
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        
        ActionForward forward = userCurrentlyLoggedOnStatus (user, pForm, mapping, request, session);
        if ( forward != null ) {
        	return ( forward );
        }
        
        // Check User's Expiration Date
		if (!submitType.equals("warning") && (!user.getUserName().equals("Guest"))) {
			int expirationInterval = FdmsDb.getInstance().getExpirationInterval(user);
			
			if (expirationInterval >= 0 && expirationInterval < 30) {
				// Warn 30-Day users of Expiration Date
				pForm.setExpirationDateWarning(true);
				pForm.setExpirationInterval(expirationInterval);
				return (new ActionForward(mapping.getInput()));
			} else if (expirationInterval < 0) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.expired"));
					saveErrors(request, errors);
			}
		}
        
        // Report any errors we have discovered back to the original form
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        
        UserPermissionsBean permissions = new UserPermissionsBean (user, isFuneral, isCemetery);
        
        // Save our logged-in user in the session
        session.setAttribute(SessionValueKeys.DB_USER, user);
        session.setAttribute(SessionValueKeys.DB_PERMISSIONS, permissions);
        
        this.userID = user.getId();
        this.companyID = user.getCompanyID();
        this.regionID = user.getRegion();
        this.logonStatus = "T";
        
        // Save our logged-in user in the application scope.
        if (!user.getUserName().equals("Guest")) {
            servlet.getServletContext().setAttribute(user.getUserName(),session);
        }
        
        if (session.getAttribute(SessionValueKeys.DB_USER) !=null) {
        	
        	DatabaseTransaction t = null;
    		
    		try {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
                
	        	setUserLocationMap ( t, user, session, errors );
	        	
	        	// Remove the obsolete form bean
	            if (mapping.getAttribute() != null) {
	                if ("request".equals(mapping.getScope())) {
	                    request.removeAttribute(mapping.getAttribute());
	                } else {
	                    session.removeAttribute(mapping.getAttribute());
	                }
	            }
	            
	            SessionHelpers.setExternalAppConfigMap(request);
	            
	            DbSecurityConfig secureConfig = null;
	            secureConfig = FdmsDb.getInstance().getSecurityConfig(t, user.getCompanyID());
	            
	            ActionForward userSecurity = checkUserSecurity(user, mapping, password, errors, 
	            		request, response, secureConfig);
	            
	            if ( userSecurity != null ) {
	            	
	            	if ( !errors.isEmpty() ) {
	            		saveErrors(request, errors);
	            	}
	            	
	            	return (userSecurity);
	            }

    		}
    		catch(PersistenceException pe) {
                logger.error("Persistence Exception in SecurityManagerBean updatePassword " + pe);
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.PersistenceException",pe.getCause()));
            } 
    		catch(Exception pe) {
                logger.error("Exception in  SecurityManagerBean updatePassword ",  pe);
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.GeneralException",pe.getMessage()));
            }
    		finally { 
            	t.closeConnection();
            }
    		
    		if (permissions.isFdmsNetworkGranted()== false){
    			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.userinfo.nopermission"));
            	saveErrors(request, errors);
            	userID = 0;
    			return (new ActionForward(mapping.getInput()));
            }
            
            if ((permissions.isCemeteryGranted() == true) && (permissions.isFuneralGranted() == false)){
                return mapping.findForward("introcemetery");
            }
            else {
                 return mapping.findForward("introduction");
            }
            
        }   
        else {
            return mapping.findForward("logon");
        }
    	
    }
    
    /***
     * 
     * @param user
     * @param pForm
     * @param mapping
     * @param request
     * @param session
     * @return
     */
    private ActionForward userCurrentlyLoggedOnStatus ( DbUserSession user, ProcessLogonForm pForm, 
    		ActionMapping mapping, HttpServletRequest request, HttpSession session) {

        // See if this user is already logged into the system.
        if (pForm.getSubmitButton().equals("login")) {            
        	
        	String warnMultiLogins = UtilSingleton.getInstance().getProperty(user.getConfigID(), "WebApp.warnMultipleLogins");
        	
        	if ( warnMultiLogins != null && warnMultiLogins.equals("false") ) {
        		return ( null );
        	}

        	if (!user.getUserName().equals("Guest")) {
                logger.debug("Checking if other user is logged in with same account");
                
                HttpSession otherUserSession = (HttpSession)servlet.getServletContext().getAttribute(user.getUserName());
                // This tests for an expired session.
                // Is there an easier way?
                boolean isValidDate=true;
                SimpleDateFormat timestamp = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
                
                try{
                    long time = otherUserSession.getCreationTime();
                    logger.debug("Previous login time : " + time);
                    long timeDiff = System.currentTimeMillis() - time;                    
                    logger.debug("Milliseconds diff : " + timeDiff);
                    long time30min = 1000 * 60 * 30;
                    
                    if (timeDiff > time30min) isValidDate = false;
                    
                    logger.debug("previous login:"
                                + timestamp.format(new java.util.Date(otherUserSession.getCreationTime())));
                } catch(Exception e){
                    isValidDate=false;
                    pForm.setSubmitButton("proceed");
                }
                
                // Give the user a warning that we will invalidate the other session.
                if (otherUserSession != null && isValidDate && pForm.getUsername().toLowerCase().compareTo("demo") != 0) {
                    
                    pForm.setDuplicateUserError(true);
                    pForm.setSubmitButton(" ");
                    return (new ActionForward(mapping.getInput()));
                }
            }
        }

        // Coming back from login.jsp after the user was warned of a duplicate user logged in.
        // If he elected to proceed, the submit button will equal "proceed".  If he elected
        // to not proceed, the submit button will equal "terminate" and login will be
        // reinitialized.
        if (pForm.getSubmitButton().equals("proceed")) {                 
            
            HttpSession otherUserSession = (HttpSession)servlet.getServletContext().getAttribute(user.getUserName());
            if (otherUserSession != null ) {
                try {
                    // try removing the old session object which effectively logs out the old/other user
                    otherUserSession.removeAttribute(SessionValueKeys.DB_USER);
                  
                    //insert a data to log user duplicate login.
                    CompanyOptionsManager coMgr = new CompanyOptionsManager ();
	                int loginAttemptOption = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_LOGINATTEMPT_LOG);
	                
	                if (loginAttemptOption == 1) {
	                    DuplicateLoginLogDTO duplicateLoginLog = new DuplicateLoginLogDTO();
	                    long loginTime = System.currentTimeMillis(); 
	                    duplicateLoginLog.setUserID(user.getId());
	                    duplicateLoginLog.setUserName(user.getUserName());
	                    duplicateLoginLog.setLoginTime(loginTime);
	                	
	                    DuplicateLoginLogger duplicateLoginLogger = new DuplicateLoginLogger(user.getId());        
	                    duplicateLoginLogger.logDuplicateLogin( duplicateLoginLog );
	                }
                    //end log
                    
                    
                    servlet.getServletContext().removeAttribute(user.getUserName());
                } catch(Exception e){
                    // logger.error("ProcessLogon. Probably expired session exception:", e);
                    // don't need this if session no longer valid
                    servlet.getServletContext().removeAttribute(user.getUserName());
                }
            }
        } else {
            if (pForm.getSubmitButton().equals("terminate")) {
                if ("request".equals(mapping.getScope())) {
                    request.removeAttribute(mapping.getAttribute());
                } else {
                    session.removeAttribute(mapping.getAttribute());
                }
                return (new ActionForward(mapping.getInput()));
            }
        }
        
        return ( null );
        
    }
    
    /**
     * 
     * @param user
     * @param session
     */
    private void setUserLocationMap (DatabaseTransaction t, DbUserSession user, HttpSession session, ActionErrors errors) {
    	
    	UserManagerBean umb = new UserManagerBean();
        HashMap userLocationMap = umb.getUserLocations(new Integer(user.getId()).longValue());
        
        if (userLocationMap != null) {
            ArrayList userLocations = (ArrayList) userLocationMap.get("userLocations");
            
            FdmsDb.getInstance().getUserLocationNames(t, userLocations, user);
            
            ArrayList <LocaleDTO> locales = FdmsDb.getInstance().getLocalesForCompany(user.getDbLookup(), user.getCompanyID());
            
            String [] locationIds = umb.getUserLocationIds(user.getId());
            String [] localeIds = umb.getUserLocaleIds(user.getId());
            session.setAttribute(Constants.USER_LOCALE_IDS, localeIds);
            session.setAttribute(Constants.USER_LOCATION_IDS, locationIds);
            
            ArrayList <LocaleDTO> userLocales = new ArrayList <LocaleDTO> ();
            LocaleDTO localeSel = null;
            	
            for ( int i = 0; i < locales.size(); i++ ) {
            	LocaleDTO loc = locales.get(i);
            	int locID = loc.getLocaleID();
            	
            	for ( int x = 0; x < localeIds.length; x++ ) {
            		int localeID = Integer.parseInt(localeIds[x]);
            		
            		if ( locID == localeID ) {
            			userLocales.add(loc);
            		}
            		
            		if ( locID == user.getRegion() ) {
            			localeSel = loc; 
            		}
            	}
            }
            
    		if ( localeSel != null ) {
    			user.setLocalizedSpeedData(localeSel.isLocalizedSpeedData());
    			user.setLocaleCountry(localeSel.getCountry());
    			
    			Locale myLocale = new Locale("en", localeSel.getCountry());
    			
    			session.setAttribute( Constants.INTERNATIONAL_LOCALE, myLocale );
    		}
    		
            
            Collections.sort(userLocales, new LocaleNameComparator() ); 
            session.setAttribute(Constants.USER_LOCALES, userLocales);

            String defaultLocationId = (String) userLocationMap.get("defaultLocation");
            if ((defaultLocationId == null) || (defaultLocationId.trim().equals(""))) {
                UserLocationDTO userLocationDto = (UserLocationDTO) userLocations.get(0);
                defaultLocationId = String.valueOf(userLocationDto.getLocationId());
            }
            
            int dfltLocID = Integer.parseInt(defaultLocationId);
            boolean locIDFound = false;
                        
            DbLocation [] dbLocations = FdmsDb.getInstance().getLocationsForRegion(t, user.getRegion());
            
            ArrayList <DbLocation> userLocaleLocs = new ArrayList <DbLocation> ();
            
            for ( int i = 0; i < dbLocations.length; i++ ) {
            	DbLocation loc = dbLocations[i];
            	int locID = loc.getId();
            	
            	for ( int x = 0; x < locationIds.length; x++ ) {
            		int aLocID = Integer.parseInt(locationIds[x]);
            		
            		if ( aLocID == locID ) {
                    	userLocaleLocs.add(loc);
                    	
                    	if ( aLocID == dfltLocID ) {
                    		locIDFound = true;
                    	}
            		}
            	}
            	
            }
            
            if ( !locIDFound && (locationIds.length > 0) ) {
            	defaultLocationId = "-1";
            }
            
            Collections.sort(userLocaleLocs, new LocationNameComparator() ); 
            session.setAttribute(Constants.USER_LOCATIONS, userLocaleLocs);
            
            logger.debug("Location(s) found : " + userLocaleLocs.size());
            
            // set value = 0 to show active cases
            session.setAttribute(Constants.SHOW_ACTIVE_CASES, "0");
            session.setAttribute(Constants.SHOW_PRENEED_ONLY, "N");
                        
            logger.debug("Setting defaultLocationId : " + defaultLocationId);
            user.setLocationId(com.aldorsolutions.webfdms.util.FormatNumber.parseInteger(defaultLocationId));
            
            session.setAttribute(Constants.USER_START_INDEX, new Integer(1));            
        }          
	
    }
    
    private ActionForward checkUserSecurity ( DbUserSession user, ActionMapping mapping, String password, 
    		ActionErrors errors, HttpServletRequest request, HttpServletResponse response, 
    		DbSecurityConfig secureConfig )  
    {
        
    	SecurityManagerBean mbSecurity = new SecurityManagerBean();
		boolean warnExpiration = false;
		
		if (user.isChangePassword()) {
			return (mapping.findForward("changePassword"));
		}

		logger.debug("Expiration enforced = " + secureConfig.isPasswordExpirationEnforced());
            
		if (secureConfig.isPasswordExpirationEnforced()) {
			Timestamp passwordSetDate = user.getPasswordTimestamp();
			int expireDays = secureConfig.getPasswordExpirationInDays();
			int warnDays = secureConfig.getPasswordExpirationWarningInDays();

			long passwordExpiresInDays = SecurityManagerBean.calculatePasswordExpirationInDays(expireDays,
					passwordSetDate);
			logger.debug("Expiration interval = " + passwordExpiresInDays);
			logger.debug("Expiration interval Expire Days = " + expireDays);
			logger.debug("Expiration interval Warn Days = " + warnDays);

			if (passwordExpiresInDays < 0) {
				return (mapping.findForward("changePassword"));
			}

			if (passwordExpiresInDays <= warnDays) {
				warnExpiration = true;
			}

		}
		
		if (mbSecurity.isPasswordComplexityValid(user.getUserName(), password, secureConfig) == false) {
			return (mapping.findForward("changePassword"));
		}
		
		if (warnExpiration) {
			return (mapping.findForward("passwordExpiration"));
		}
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        
    	return ( null );
    }
    
    private ActionForward invalidUserLockout ( HttpServletRequest request, DbUserSession user, ActionMapping mapping, 
    		ActionErrors errors ) {

    	DatabaseTransaction t = null;
    	ActionForward userSecurity = null;
		
		try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            
            DbSecurityConfig secureConfig = null;
            secureConfig = FdmsDb.getInstance().getSecurityConfig(t, user.getCompanyID());
            
            if (secureConfig == null) {
            	logger.error("Persistence Exception in DbSecurityConfig is null ");
                errors.add(ActionErrors.GLOBAL_ERROR, 
                		new ActionError("error.PersistenceException", 
                		this.getResources(request).getMessage("securityForm.securityConfigMissing")	) );
            }
            else if (secureConfig.isFailedLoginLockout()) {
        		userSecurity = isUserLocked(user, secureConfig, mapping, errors);
        	}
        	
		}
		catch(PersistenceException pe) {
            logger.error("Persistence Exception in SecurityManagerBean updatePassword " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.PersistenceException",pe.getCause()));
        } 
		catch(Exception pe) {
            logger.error("Exception in  SecurityManagerBean updatePassword ",  pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.GeneralException",pe.getMessage()));
        }
		finally { 
        	t.closeConnection();
        }
		
		return (userSecurity);
    	
    }
    
    private ActionForward isUserLocked ( DbUserSession user, DbSecurityConfig secureConfig, 
    		ActionMapping mapping, ActionErrors errors ) 
    {
    	
    	boolean lockedOut = false;
    	boolean changeUser = false;
    	Timestamp lockedTmstmp = user.getLockedTimestamp();
		
    	if ( user.isUserLocked() ) {
    		int duration = secureConfig.getLockoutDuration();
    		
    		long multiplier = (1000 * 60 * 60);
    		long lockedTime =  lockedTmstmp.getTime();
    		long currTime =  System.currentTimeMillis();
    		
    		long lockedHour = (lockedTime / multiplier);
    		long currentHour = (currTime / multiplier);
    		long hourDif = (currentHour - lockedHour);
    		lockedOut = true;
    		
    		if ( duration == DbSecurityConfig.LOCKOUT_DURATION_1_HOUR ) {
    			if ( hourDif > 1 ) {
    				lockedOut = false;
    				changeUser = true;
    			}
    			
    		} else if ( duration == DbSecurityConfig.LOCKOUT_DURATION_2_HOURS ) {
    			if ( hourDif > 2 ) {
    				lockedOut = false;
    				changeUser = true;
    			}
    			
    		} else if ( duration == DbSecurityConfig.LOCKOUT_DURATION_4_HOURS ) {
    			if ( hourDif > 4 ) {
    				lockedOut = false;
    				changeUser = true;
    			}
    			
    		} else if ( duration == DbSecurityConfig.LOCKOUT_DURATION_8_HOURS ) {
    			if ( hourDif > 8 ) {
    				lockedOut = false;
    				changeUser = true;
    			}
    			
    		} else if ( duration == DbSecurityConfig.LOCKOUT_DURATION_24_HOURS ) {
    			if ( hourDif > 24 ) {
    				lockedOut = false;
    				changeUser = true;
    			}
    			
    		} else if ( duration == DbSecurityConfig.LOCKOUT_DURATION_MIDNIGHT ) {
    			
    			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
    			
    			int hourOfDay = gc.get( GregorianCalendar.HOUR_OF_DAY );
    			int midnightDif = (24-hourOfDay);
    			
    			if ( hourDif > midnightDif ) {
    				lockedOut = false;
    				changeUser = true;
    			}
    			
    		} else if ( duration == DbSecurityConfig.LOCKOUT_DURATION_ADMIN_REQUIRED ) {
    			// No automatic activation
    		}
    		
    	} else {

        	LoginLogger loginLog = new LoginLogger(userID, companyID, regionID);
            int failedAttempts = loginLog.getPreviousFailedAttempts (user.getUserName());

            if ( failedAttempts > secureConfig.getFailedLoginAttemptsAllowed() ) {
            	lockedOut = true;
            	changeUser = true;
            	lockedTmstmp = new Timestamp ( System.currentTimeMillis() );
            }
            
    	}
    	
        if ( changeUser ) {
			DbUser dbUser = (DbUser) user; 
			updateUserLockout ( dbUser, lockedOut, lockedTmstmp, errors, user );
		}
        
        if ( lockedOut ) {
			this.logonStatus = "L";
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.locked"));
			return (new ActionForward(mapping.getInput()));
		}
        
        return ( null );
    }
    

	public ActionErrors updateUserLockout ( DbUser user, boolean isLocked, Timestamp tmstmp,  
			ActionErrors errors, DbUserSession userSession ) {
		
		DatabaseTransaction t = null;
		
		try {
            String usersLookup = UtilSingleton.getInstance().getUserDBLookup();
            
            t = (DatabaseTransaction) DatabaseTransaction.getTransaction(userSession, usersLookup);
    		
    		user.setUserLocked(isLocked);
    		user.setLockedTimestamp(tmstmp);
    		
    		t.addPersistent(user);
    		t.save();
		}
		catch(PersistenceException pe) {
            logger.error("Persistence Exception in SecurityManagerBean updateUserLockout " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  SecurityManagerBean updateUserLockout ",  pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.GeneralException",pe.getMessage()));
        }
        finally {
        	if ( t != null ) {
        		t.closeConnection();
        	}
        }
		
		return ( errors );
	}
    
}
