package fdms.messenger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="CaseProfile")
public class CaseProfile {
	
  private DeceasedAndFamilyInfo deceasedAndFamilyInfo;
  private ServiceInfo serviceInfo;
  private String catholicOnlyInfo;
  private MilitaryServiceInfo militaryServiceInfo;
	
  
  public CaseProfile(DeceasedAndFamilyInfo deceasedAndFamilyInfo,
			ServiceInfo serviceInfo, String catholicOnlyInfo,
			MilitaryServiceInfo militaryServiceInfo) {
		super();
		this.deceasedAndFamilyInfo = deceasedAndFamilyInfo;
		this.serviceInfo = serviceInfo;
		this.catholicOnlyInfo = catholicOnlyInfo;
		this.militaryServiceInfo = militaryServiceInfo;
	}


	public CaseProfile() {
  	deceasedAndFamilyInfo = null;
  	serviceInfo = null;
  	this.catholicOnlyInfo = null;
  	militaryServiceInfo = null;;
  }


	@XmlElement(name="CatholicOnlyInfo")
	public String getCatholicOnlyInfo() {
		return catholicOnlyInfo;
	}

	public void setCatholicOnlyInfo(String catholicOnlyInfo) {
		this.catholicOnlyInfo = catholicOnlyInfo;
	}


	@XmlElement(name="DeceasedAndFamilyInfo")
	public DeceasedAndFamilyInfo getDeceasedAndFamilyInfo() {
		return deceasedAndFamilyInfo;
	}


	public void setDeceasedAndFamilyInfo(DeceasedAndFamilyInfo deceasedAndFamilyInfo) {
		this.deceasedAndFamilyInfo = deceasedAndFamilyInfo;
	}


	@XmlElement(name="MilitaryServiceInfo")
	public MilitaryServiceInfo getMilitaryServiceInfo() {
		return militaryServiceInfo;
	}


	public void setMilitaryServiceInfo(MilitaryServiceInfo militaryServiceInfo) {
		this.militaryServiceInfo = militaryServiceInfo;
	}


	@XmlElement(name="ServiceInfo")
	public ServiceInfo getServiceInfo() {
		return serviceInfo;
	}


	public void setServiceInfo(ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

}
