ALTER TABLE arrangers ADD COLUMN PnLicenseNumber VARCHAR(15);
ALTER TABLE arrangers ADD COLUMN BurialLicenseNumber VARCHAR(15);

ALTER TABLE servicedata ADD COLUMN AddnlService VARCHAR(30);
ALTER TABLE servicedata ADD COLUMN AddnlServiceDate VARCHAR(8);
ALTER TABLE servicedata ADD COLUMN AddnlServiceTime VARCHAR(10);
ALTER TABLE servicedata ADD COLUMN AddnlServiceDay VARCHAR(9);

ALTER TABLE pncontracts ADD COLUMN CustomContractNumber VARCHAR(25) AFTER ContractNumber;
ALTER TABLE pncontracts ADD COLUMN CertificateNumber INT(11) AFTER CustomContractNumber;

ALTER TABLE vitalstats ADD COLUMN PNBuyerPhone VARCHAR(15);
ALTER TABLE vitalstats ADD COLUMN Phone VARCHAR(15);
ALTER TABLE vitalstats ADD COLUMN FatherForename VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN FatherSurnameBirth VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN FatherOtherSurname VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN FatherOccupation VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN MotherForename VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN MotherSurnameBirth VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN MotherOtherSurname VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN MotherOccupation VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN StillBirthDesc VARCHAR(50);
ALTER TABLE vitalstats ADD COLUMN PregnancyDuration INT(2);
ALTER TABLE vitalstats ADD COLUMN NumberChildren INT(2);
ALTER TABLE vitalstats ADD COLUMN NumberLiveborn INT(2);
ALTER TABLE vitalstats ADD COLUMN NumberStillborn INT(2);
ALTER TABLE vitalstats ADD COLUMN ChildWeight VARCHAR(15);
ALTER TABLE vitalstats ADD COLUMN BirthType VARCHAR(15);
ALTER TABLE vitalstats ADD COLUMN BirthOrder VARCHAR(15);
ALTER TABLE vitalstats ADD COLUMN ChildNameAgreed CHAR(1) DEFAULT 'N';
ALTER TABLE vitalstats CHANGE ActualPlaceDeath ActualPlaceDeath VARCHAR(60);
ALTER TABLE vitalstats ADD COLUMN SurvivingSpouseStreet VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN SurvivingSpouseCity VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN SurvivingSpouseState VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN SurvivingSpouseZip VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN MotherBirthday VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN FatherBirthday VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN MotherStreetAddress VARCHAR(30);
ALTER TABLE vitalstats ADD COLUMN DrJusticeOfThePeace VARCHAR(30);

ALTER TABLE speeddata ADD COLUMN LocationId INT(5) NOT NULL DEFAULT 0 AFTER LOCALE;

ALTER TABLE locales ADD COLUMN LocalizedSpeedData CHAR(1) DEFAULT 'N';

CREATE TABLE person (
	PersonId 		INT(12) 	NOT NULL 	AUTO_INCREMENT,
	VitalsMasterKey 	INT(12) 	DEFAULT '0',
	PersonTypeId 		INT(3) 		DEFAULT '0',
	ExecutorFirstName 	VARCHAR(30),
	ExecutorLastName 	VARCHAR(30),
	ExecutorStreet 		VARCHAR(30),
	ExecutorStreet2 	VARCHAR(30),
	ExecutorStreet3 	VARCHAR(30),
	ExecutorCity 		VARCHAR(30),
	ExecutorState 		VARCHAR(30),
	ExecutorZip 		VARCHAR(12),
	ExecutorPhone 		VARCHAR(15),
	ExecutorRelation 	VARCHAR(30), 
	ExecutorSame 		VARCHAR(1),
	PRIMARY KEY(PersonId)
);

CREATE TABLE persontype (
	PersonTypeId 		INT(12) 	NOT NULL 	AUTO_INCREMENT, 
	Title 			VARCHAR(30),
	PRIMARY KEY(PersonTypeId)
);