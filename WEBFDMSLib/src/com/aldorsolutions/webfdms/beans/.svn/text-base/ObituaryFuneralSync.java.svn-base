package com.aldorsolutions.webfdms.beans;

import java.sql.Date;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * A DbLocale represents one region location. The peer class provides the class
 * name and SQL for restoring Creation date: (11/26/2001 11:14:33 AM)
 * 
 * @author:
 */
public class ObituaryFuneralSync extends com.aldorsolutions.webfdms.database.Persistent {
	
//	private Logger logger = Logger.getLogger(DbLocaleConfig.class.getName());

	final static ObituaryFuneralSyncPeer peer = new ObituaryFuneralSyncPeer();

	private int id;
	private String ObitID; 
	private String LocationID; 
	private String DecTitle; 
	private String DecFirstName; 
	private String DecMiddleName; 
	private String DecLastName; 
	private String DecNickName; 
	private String DecMaidenName; 
	private Date DateOfBirth; 
	private String PlaceOfBirth; 
	private Date DateOfDeath; 
	private String PlaceOfDeath; 
	private String ViewType; 
	private String ViewNoBodyPresent; 
	private Date ViewDate; 
	private String ViewTime; 
	private String ViewAtHome; 
	private String ViewLocation; 
	private String OtherViewAtHome; 
	private String ViewOtherLocation; 
	private Date ViewOtherDate; 
	private String ViewOtherTime; 
	private String BurialType; 
	private Date BurialDate; 
	private String BurialTime; 
	private String CemeteryName; 
	private String ServiceType; 
	private Date ServiceDate; 
	private String ServiceTime; 
	private String ServiceAtHome; 
	private String ServiceOtherLocation; 
	private String ExtraServices; 
	private String FlowersType; 
	private Date FlowersDate; 
	private String FlowersTime; 
	private Date FlowersOtherDate; 
	private String FlowersOtherTime; 
	private String FlowersOtherLocation; 
	private String ContributionName; 
	private String Eulogy; 
	private String Survivors; 
	private Date DateEntered; 
	private Date DateModified; 
	private Date ArchiveDate; 
	private String ClientId; 
	private String PostedYN; 
	private long PostedDateTime;


	/**
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */

	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(ObituaryFuneralSyncPeer.ID).toString()));
		ObitID = data.get(ObituaryFuneralSyncPeer.OBITID).toString();
		LocationID = data.get(ObituaryFuneralSyncPeer.LOCATIONID).toString(); 
		DecTitle = data.get(ObituaryFuneralSyncPeer.DECTITLE).toString(); 
		DecFirstName = data.get(ObituaryFuneralSyncPeer.DECFIRSTNAME).toString(); 
		DecMiddleName = data.get(ObituaryFuneralSyncPeer.DECMIDDLENAME).toString(); 
		DecLastName = data.get(ObituaryFuneralSyncPeer.DECLASTNAME).toString(); 
		DecNickName = data.get(ObituaryFuneralSyncPeer.DECNICKNAME).toString(); 
		DecMaidenName = data.get(ObituaryFuneralSyncPeer.DECMAIDENNAME).toString(); 
		try {
			DateOfBirth = (Date) data.get(ObituaryFuneralSyncPeer.DATEOFBIRTH);
		} catch (ClassCastException e) {
			DateOfBirth = null;
		}
		PlaceOfBirth = data.get(ObituaryFuneralSyncPeer.PLACEOFBIRTH).toString(); 

		try {
			DateOfDeath = (Date) data.get(ObituaryFuneralSyncPeer.DATEOFDEATH);
		} catch (ClassCastException e) {
			DateOfDeath = null;
		}
		PlaceOfDeath = data.get(ObituaryFuneralSyncPeer.PLACEOFDEATH).toString(); 
		ViewType = data.get(ObituaryFuneralSyncPeer.VIEWTYPE).toString(); 
		ViewNoBodyPresent = data.get(ObituaryFuneralSyncPeer.VIEWNOBODYPRESENT).toString(); 

		try {
			ViewDate = (Date) data.get(ObituaryFuneralSyncPeer.VIEWDATE);
		} catch (ClassCastException e) {
			ViewDate = null;
		}
		ViewTime = data.get(ObituaryFuneralSyncPeer.VIEWTIME).toString(); 
		ViewAtHome = data.get(ObituaryFuneralSyncPeer.VIEWATHOME).toString(); 
		ViewLocation = data.get(ObituaryFuneralSyncPeer.VIEWLOCATION).toString(); 
		OtherViewAtHome = data.get(ObituaryFuneralSyncPeer.OTHERVIEWATHOME).toString(); 
		ViewOtherLocation = data.get(ObituaryFuneralSyncPeer.VIEWOTHERLOCATION).toString(); 

		try {
			ViewOtherDate = (Date) data.get(ObituaryFuneralSyncPeer.VIEWOTHERDATE);
		} catch (ClassCastException e) {
			ViewOtherDate = null;
		}
		ViewOtherTime = data.get(ObituaryFuneralSyncPeer.VIEWOTHERTIME).toString(); 
		BurialType = data.get(ObituaryFuneralSyncPeer.BURIALTYPE).toString(); 

		try {
			BurialDate = (Date) data.get(ObituaryFuneralSyncPeer.BURIALDATE);
		} catch (ClassCastException e) {
			BurialDate = null;
		}
		BurialTime = data.get(ObituaryFuneralSyncPeer.BURIALTIME).toString(); 
		CemeteryName = data.get(ObituaryFuneralSyncPeer.CEMETERYNAME).toString(); 
		ServiceType = data.get(ObituaryFuneralSyncPeer.SERVICETYPE).toString(); 

		try {
			ServiceDate = (Date) data.get(ObituaryFuneralSyncPeer.SERVICEDATE);
		} catch (ClassCastException e) {
			ServiceDate = null;
		}
		ServiceTime = data.get(ObituaryFuneralSyncPeer.SERVICETIME).toString(); 
		ServiceAtHome = data.get(ObituaryFuneralSyncPeer.SERVICEATHOME).toString(); 
		ServiceOtherLocation = data.get(ObituaryFuneralSyncPeer.SERVICEOTHERLOCATION).toString(); 
		ExtraServices = data.get(ObituaryFuneralSyncPeer.EXTRASERVICES).toString(); 
		FlowersType = data.get(ObituaryFuneralSyncPeer.FLOWERSTYPE).toString(); 

		try {
			FlowersDate = (Date) data.get(ObituaryFuneralSyncPeer.FLOWERSDATE);
		} catch (ClassCastException e) {
			FlowersDate = null;
		}
		FlowersTime = data.get(ObituaryFuneralSyncPeer.FLOWERSTIME).toString(); 

		try {
			FlowersOtherDate = (Date) data.get(ObituaryFuneralSyncPeer.FLOWERSOTHERDATE);
		} catch (ClassCastException e) {
			FlowersOtherDate = null;
		}
		FlowersOtherTime = data.get(ObituaryFuneralSyncPeer.FLOWERSOTHERTIME).toString(); 
		FlowersOtherLocation = data.get(ObituaryFuneralSyncPeer.FLOWERSOTHERLOCATION).toString(); 
		ContributionName = data.get(ObituaryFuneralSyncPeer.CONTRIBUTIONNAME).toString(); 
		Eulogy = data.get(ObituaryFuneralSyncPeer.EULOGY).toString(); 
		Survivors = data.get(ObituaryFuneralSyncPeer.SURVIVORS).toString(); 

		try {
			DateEntered = (Date) data.get(ObituaryFuneralSyncPeer.DATEENTERED);
		} catch (ClassCastException e) {
			DateEntered = null;
		}

		try {
			DateModified = (Date) data.get(ObituaryFuneralSyncPeer.DATEMODIFIED);
		} catch (ClassCastException e) {
			DateModified = null;
		}

		try {
			ArchiveDate = (Date) data.get(ObituaryFuneralSyncPeer.ARCHIVEDATE);
		} catch (ClassCastException e) {
			ArchiveDate = null;
		}
		ClientId = data.get(ObituaryFuneralSyncPeer.CLIENTID).toString(); 
		PostedYN = data.get(ObituaryFuneralSyncPeer.POSTEDYN).toString(); 
		PostedDateTime = FormatNumber.parseLong(data.get(ObituaryFuneralSyncPeer.POSTEDDATETIME).toString());
	}
	
	public void setId(java.util.Hashtable h) {
		setId(((Integer) h.get(ObituaryFuneralSyncPeer.ID)).intValue());
	}
	
	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}
	
	/**
	 * isLocked method comment.
	 */
	public boolean isLocked() {
		return locked;
	}


	public String getObitID() {
		return ObitID;
	}

	public void setObitID(String obitID) {
		ObitID = obitID;
		modify();
	}

	public String getLocationID() {
		return LocationID;
	}

	public void setLocationID(String locationID) {
		LocationID = locationID;
		modify();
	}

	public String getDecTitle() {
		return DecTitle;
	}

	public void setDecTitle(String decTitle) {
		DecTitle = decTitle;
		modify();
	}

	public String getDecFirstName() {
		return DecFirstName;
	}

	public void setDecFirstName(String decFirstName) {
		DecFirstName = decFirstName;
		modify();
	}

	public String getDecMiddleName() {
		return DecMiddleName;
	}

	public void setDecMiddleName(String decMiddleName) {
		DecMiddleName = decMiddleName;
		modify();
	}

	public String getDecLastName() {
		return DecLastName;
	}

	public void setDecLastName(String decLastName) {
		DecLastName = decLastName;
		modify();
	}

	public String getDecNickName() {
		return DecNickName;
	}

	public void setDecNickName(String decNickName) {
		DecNickName = decNickName;
		modify();
	}

	public String getDecMaidenName() {
		return DecMaidenName;
	}

	public void setDecMaidenName(String decMaidenName) {
		DecMaidenName = decMaidenName;
		modify();
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
		modify();
	}

	public String getPlaceOfBirth() {
		return PlaceOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		PlaceOfBirth = placeOfBirth;
		modify();
	}

	public Date getDateOfDeath() {
		return DateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		DateOfDeath = dateOfDeath;
		modify();
	}

	public String getPlaceOfDeath() {
		return PlaceOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		PlaceOfDeath = placeOfDeath;
		modify();
	}

	public String getViewType() {
		return ViewType;
	}

	public void setViewType(String viewType) {
		ViewType = viewType;
		modify();
	}

	public String getViewNoBodyPresent() {
		return ViewNoBodyPresent;
	}

	public void setViewNoBodyPresent(String viewNoBodyPresent) {
		ViewNoBodyPresent = viewNoBodyPresent;
		modify();
	}

	public Date getViewDate() {
		return ViewDate;
	}

	public void setViewDate(Date viewDate) {
		ViewDate = viewDate;
		modify();
	}

	public String getViewTime() {
		return ViewTime;
	}

	public void setViewTime(String viewTime) {
		ViewTime = viewTime;
		modify();
	}

	public String getViewAtHome() {
		return ViewAtHome;
	}

	public void setViewAtHome(String viewAtHome) {
		ViewAtHome = viewAtHome;
		modify();
	}

	public String getViewLocation() {
		return ViewLocation;
	}

	public void setViewLocation(String viewLocation) {
		ViewLocation = viewLocation;
		modify();
	}

	public String getOtherViewAtHome() {
		return OtherViewAtHome;
	}

	public void setOtherViewAtHome(String otherViewAtHome) {
		OtherViewAtHome = otherViewAtHome;
		modify();
	}

	public String getViewOtherLocation() {
		return ViewOtherLocation;
	}

	public void setViewOtherLocation(String viewOtherLocation) {
		ViewOtherLocation = viewOtherLocation;
		modify();
	}

	public Date getViewOtherDate() {
		return ViewOtherDate;
	}

	public void setViewOtherDate(Date viewOtherDate) {
		ViewOtherDate = viewOtherDate;
		modify();
	}

	public String getViewOtherTime() {
		return ViewOtherTime;
	}

	public void setViewOtherTime(String viewOtherTime) {
		ViewOtherTime = viewOtherTime;
		modify();
	}

	public String getBurialType() {
		return BurialType;
	}

	public void setBurialType(String burialType) {
		BurialType = burialType;
		modify();
	}

	public Date getBurialDate() {
		return BurialDate;
	}

	public void setBurialDate(Date burialDate) {
		BurialDate = burialDate;
		modify();
	}

	public String getBurialTime() {
		return BurialTime;
	}

	public void setBurialTime(String burialTime) {
		BurialTime = burialTime;
		modify();
	}

	public String getCemeteryName() {
		return CemeteryName;
	}

	public void setCemeteryName(String cemeteryName) {
		CemeteryName = cemeteryName;
		modify();
	}

	public String getServiceType() {
		return ServiceType;
	}

	public void setServiceType(String serviceType) {
		ServiceType = serviceType;
		modify();
	}

	public Date getServiceDate() {
		return ServiceDate;
	}

	public void setServiceDate(Date serviceDate) {
		ServiceDate = serviceDate;
		modify();
	}

	public String getServiceTime() {
		return ServiceTime;
	}

	public void setServiceTime(String serviceTime) {
		ServiceTime = serviceTime;
		modify();
	}

	public String getServiceAtHome() {
		return ServiceAtHome;
	}

	public void setServiceAtHome(String serviceAtHome) {
		ServiceAtHome = serviceAtHome;
		modify();
	}

	public String getServiceOtherLocation() {
		return ServiceOtherLocation;
	}

	public void setServiceOtherLocation(String serviceOtherLocation) {
		ServiceOtherLocation = serviceOtherLocation;
		modify();
	}

	public String getExtraServices() {
		return ExtraServices;
	}

	public void setExtraServices(String extraServices) {
		ExtraServices = extraServices;
		modify();
	}

	public String getFlowersType() {
		return FlowersType;
	}

	public void setFlowersType(String flowersType) {
		FlowersType = flowersType;
		modify();
	}

	public Date getFlowersDate() {
		return FlowersDate;
	}

	public void setFlowersDate(Date flowersDate) {
		FlowersDate = flowersDate;
		modify();
	}

	public String getFlowersTime() {
		return FlowersTime;
	}

	public void setFlowersTime(String flowersTime) {
		FlowersTime = flowersTime;
		modify();
	}

	public Date getFlowersOtherDate() {
		return FlowersOtherDate;
	}

	public void setFlowersOtherDate(Date flowersOtherDate) {
		FlowersOtherDate = flowersOtherDate;
		modify();
	}

	public String getFlowersOtherTime() {
		return FlowersOtherTime;
	}

	public void setFlowersOtherTime(String flowersOtherTime) {
		FlowersOtherTime = flowersOtherTime;
		modify();
	}

	public String getFlowersOtherLocation() {
		return FlowersOtherLocation;
	}

	public void setFlowersOtherLocation(String flowersOtherLocation) {
		FlowersOtherLocation = flowersOtherLocation;
		modify();
	}

	public String getContributionName() {
		return ContributionName;
	}

	public void setContributionName(String contributionName) {
		ContributionName = contributionName;
		modify();
	}

	public String getEulogy() {
		return Eulogy;
	}

	public void setEulogy(String eulogy) {
		Eulogy = eulogy;
		modify();
	}

	public String getSurvivors() {
		return Survivors;
	}

	public void setSurvivors(String survivors) {
		Survivors = survivors;
		modify();
	}

	public Date getDateEntered() {
		return DateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		DateEntered = dateEntered;
		modify();
	}

	public Date getDateModified() {
		return DateModified;
	}

	public void setDateModified(Date dateModified) {
		DateModified = dateModified;
		modify();
	}

	public Date getArchiveDate() {
		return ArchiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		ArchiveDate = archiveDate;
		modify();
	}

	public String getClientId() {
		return ClientId;
	}

	public void setClientId(String clientId) {
		ClientId = clientId;
		modify();
	}

	public String getPostedYN() {
		return PostedYN;
	}

	public void setPostedYN(String postedYN) {
		PostedYN = postedYN;
		modify();
	}

	public long getPostedDateTime() {
		return PostedDateTime;
	}

	public void setPostedDateTime(long postedDateTime) {
		PostedDateTime = postedDateTime;
		modify();
	}

	
}
