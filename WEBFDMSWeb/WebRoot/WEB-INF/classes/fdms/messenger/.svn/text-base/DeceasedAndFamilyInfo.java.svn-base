package fdms.messenger;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;



public class DeceasedAndFamilyInfo {

	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private String fullName;
	private String dateBorn;
	private String placeOfBirth;
	private String dateDied;
	private String placeOfDeath;
	private int ageYears;
	private int ageMonths;
	private int ageDays;
	private String father;
	private String fatherDeceased;
	private String dateFatherBorn;
	private String dateFatherDied;
	private String mother;
	private String motherDeceased;
	private String dateMotherBorn;
	private String dateMotherDied;
	private String spouse;
	private List<String> familyMembers;

	
	public DeceasedAndFamilyInfo() {
		this.title = null;
		this.firstName = null;
		this.middleName = null;
		this.lastName = null;
		this.suffix = null;
		this.fullName = null;
		this.dateBorn = null;
		this.placeOfBirth = null;
		this.dateDied = null;
		this.placeOfDeath = null;
		this.ageYears = 0;
		this.ageMonths = 0;
		this.ageDays = 0;
		this.father = null;
		this.fatherDeceased = null;
		this.dateFatherBorn = null;
		this.dateFatherDied = null;
		this.mother = null;
		this.motherDeceased = null;
		this.dateMotherBorn = null;
		this.dateMotherDied = null;
		this.spouse = null;
		this.familyMembers = null;
	}

	
	public DeceasedAndFamilyInfo(String title, String firstName,
			String middleName, String lastName, String suffix, String fullName,
			String dateBorn, String placeOfBirth, String dateDied, String placeOfDeath,
			int ageYears, int ageMonths, int ageDays, String father,
			String fatherDeceased, String dateFatherBorn, String dateFatherDied,
			String mother, String motherDeceased, String dateMotherBorn,
			String dateMotherDied, String spouse, List<String> familyMembers) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix = suffix;
		this.fullName = fullName;
		this.dateBorn = dateBorn;
		this.placeOfBirth = placeOfBirth;
		this.dateDied = dateDied;
		this.placeOfDeath = placeOfDeath;
		this.ageYears = ageYears;
		this.ageMonths = ageMonths;
		this.ageDays = ageDays;
		this.father = father;
		this.fatherDeceased = fatherDeceased;
		this.dateFatherBorn = dateFatherBorn;
		this.dateFatherDied = dateFatherDied;
		this.mother = mother;
		this.motherDeceased = motherDeceased;
		this.dateMotherBorn = dateMotherBorn;
		this.dateMotherDied = dateMotherDied;
		this.spouse = spouse;
		this.familyMembers = familyMembers;
	}

	@XmlElement(name="Title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name="FirstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement(name="MiddleName")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@XmlElement(name="LastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name="Suffix")
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@XmlElement(name="FullName")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@XmlElement(name="DateBorn")
	public String getDateBorn() {
		return this.dateBorn;
	}

	public void setDateBorn(String dateBorn) {
		this.dateBorn = Messenger.createXMLDateString(dateBorn);
	}

	@XmlElement(name="PlaceOfBirth")
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	@XmlElement(name="DateDied")
	public String getDateDied() {
		return dateDied;
	}

	public void setDateDied(String dateDied) {
		this.dateDied = Messenger.createXMLDateString(dateDied);
	}

	@XmlElement(name="PlaceOfDeath")
	public String getPlaceOfDeath() {
		return placeOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		this.placeOfDeath = placeOfDeath;
	}

	@XmlElement(name="AgeYears")
	public int getAgeYears() {
		return ageYears;
	}

	public void setAgeYears(int ageYears) {
		this.ageYears = ageYears;
	}

	@XmlElement(name="AgeMonths")
	public int getAgeMonths() {
		return ageMonths;
	}

	public void setAgeMonths(int ageMonths) {
		this.ageMonths = ageMonths;
	}

	@XmlElement(name="AgeDays")
	public int getAgeDays() {
		return ageDays;
	}

	public void setAgeDays(int ageDays) {
		this.ageDays = ageDays;
	}

	@XmlElement(name="Father")
	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	@XmlElement(name="FatherDeceased")
	public String getFatherDeceased() {
		return fatherDeceased;
	}

	public void setFatherDeceased(String fatherDeceased) {
		this.fatherDeceased = fatherDeceased;
	}

	@XmlElement(name="FatherBorn")
	public String getDateFatherBorn() {
		return dateFatherBorn;
	}

	public void setDateFatherBorn(String dateFatherBorn) {
		this.dateFatherBorn = Messenger.createXMLDateString(dateFatherBorn);
	}

	@XmlElement(name="FatherDied")
	public String getDateFatherDied() {
		return dateFatherDied;
	}

	public void setDateFatherDied(String dateFatherDied) {
		this.dateFatherDied =Messenger.createXMLDateString(dateFatherDied);
	}

	@XmlElement(name="Mother")
	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	@XmlElement(name="MotherDeceased")
	public String getMotherDeceased() {
		return motherDeceased;
	}

	public void setMotherDeceased(String motherDeceased) {
		this.motherDeceased = motherDeceased;
	}

	@XmlElement(name="MotherBorn")
	public String getDateMotherBorn() {
		return dateMotherBorn;
	}

	public void setDateMotherBorn(String dateMotherBorn) {
		this.dateMotherBorn = Messenger.createXMLDateString(dateMotherBorn);
	}

	@XmlElement(name="MotherDied")
	public String getDateMotherDied() {
		return dateMotherDied;
	}

	public void setDateMotherDied(String dateMotherDied) {
		this.dateMotherDied = Messenger.createXMLDateString(dateMotherDied);
	}

	@XmlElement(name="Spouse")
	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}


  @XmlElementWrapper(name="FamilyMembers")
	@XmlElement(name="string")
	public List<String> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(List<String> familyMembers) {
		this.familyMembers = familyMembers;
	}
}
