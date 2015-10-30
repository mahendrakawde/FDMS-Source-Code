package fdms.messenger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ServiceInfo")
public class MilitaryServiceInfo {

	private String branchOfService;
	private String serialNumber;
	private String organizationAndRank;
	private String dateEntered;
	private String dateDischarged;
	private String stationsWithinUS;
	private String staionsOutsideUS;
	private String decorationsAndCitations;

	
	public MilitaryServiceInfo() {
		super();
		this.branchOfService = null;
		this.serialNumber = null;
		this.organizationAndRank = null;
		this.dateEntered = null;
		this.dateDischarged = null;
		this.stationsWithinUS = null;
		this.staionsOutsideUS = null;
		this.decorationsAndCitations = null;
	}

	
	public MilitaryServiceInfo(String branchOfService, String serialNumber,
			String organizationAndRank, String dateEntered, String dateDischarged,
			String stationsWithinUS, String staionsOutsideUS,
			String decorationsAndCitations) {
		super();
		this.branchOfService = branchOfService;
		this.serialNumber = serialNumber;
		this.organizationAndRank = organizationAndRank;
		this.dateEntered = dateEntered;
		this.dateDischarged = dateDischarged;
		this.stationsWithinUS = stationsWithinUS;
		this.staionsOutsideUS = staionsOutsideUS;
		this.decorationsAndCitations = decorationsAndCitations;
	}

	@XmlElement(name = "BranchOfService")
	public String getBranchOfService() {
		return branchOfService;
	}

	public void setBranchOfService(String branchOfService) {
		this.branchOfService = branchOfService;
	}

	@XmlElement(name = "SerialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@XmlElement(name = "OrganizationAndRank")
	public String getOrganizationAndRank() {
		return organizationAndRank;
	}

	public void setOrganizationAndRank(String organizationAndRank) {
		this.organizationAndRank = organizationAndRank;
	}

	@XmlElement(name = "DateEntered")
	public String getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(String dateEntered) {
		this.dateEntered = Messenger.createXMLDateString(dateEntered);
	}

	@XmlElement(name = "DateDischarged")
	public String getDateDischarged() {
		return dateDischarged;
	}

	public void setDateDischarged(String dateDischarged) {
		this.dateDischarged = Messenger.createXMLDateString(dateDischarged);
	}

	@XmlElement(name = "StationsWithinUS")
	public String getStationsWithinUS() {
		return stationsWithinUS;
	}

	public void setStationsWithinUS(String stationsWithinUS) {
		this.stationsWithinUS = stationsWithinUS;
	}

	@XmlElement(name = "StaionsOutsideUS")
	public String getStaionsOutsideUS() {
		return staionsOutsideUS;
	}

	public void setStaionsOutsideUS(String staionsOutsideUS) {
		this.staionsOutsideUS = staionsOutsideUS;
	}

	@XmlElement(name = "DecorationsAndCitations")
	public String getDecorationsAndCitations() {
		return decorationsAndCitations;
	}

	public void setDecorationsAndCitations(String decorationsAndCitations) {
		this.decorationsAndCitations = decorationsAndCitations;
	}
}
