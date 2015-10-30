-- line(s) below added by Kiersten Heyl on 09/30/2005
alter table `vitalstats` ,add column `DecBirthPlaceCSV` varchar (50)   NULL  after `DecBirthPlace`;
UPDATE vitalstats SET DecBirthPlaceCSV = DecBirthPlace;

-- line(s) below added by Kiersten Heyl on 10/08/2005
ALTER TABLE vitalstats ADD COLUMN Aboriginal CHAR(1) DEFAULT "N";
ALTER TABLE vitalstats ADD COLUMN LivedOnReserve CHAR(1) DEFAULT "N";

-- line(s) below added by Kiersten Heyl on 10/20/2005
ALTER TABLE locations ADD COLUMN Division CHAR(4) DEFAULT "" AFTER CompanyNumber;
ALTER TABLE locales ADD COLUMN AutoFillDateOfDeath TINYINT(1) DEFAULT 1;
ALTER TABLE locales ADD COLUMN AutoFillArrangeDate TINYINT(1) DEFAULT 1;

-- line(s) below added by Kiersten Heyl on 10/28/2005
select @highest_old_survivor := vitalsmasterkey from survivors where SequenceNumber < 0 order by vitalsmasterkey desc limit 1;
insert into survivors select vitalsmasterkey, casecode, deceasedlastname, "", "", "", "", "", deceasedfirstname, "", "", "", "", "", null, null, null, -1, "", 0, "", "", "", null, null from vitalstats where vitalsmasterkey > @highest_old_survivor and PNPreneedStatus != 'A' and PNPreneedStatus != 'C';
insert into survivors select vitalsmasterkey, contractcode, deceasedlastname, "", "", "", "", "", deceasedfirstname, "", "", "", "", "", null, null, null, -2, "", 0, "", "", "", null, null from vitalstats where vitalsmasterkey > @highest_old_survivor and PNPreneedStatus != 'A' and PNPreneedStatus != 'C';

-- line(s) below added by Guadalupe Garcia on 11/03/2005
CREATE TABLE preneeddisbursement (
	DisbursementId		INT(12)		NOT NULL	AUTO_INCREMENT,
	VitalsMasterKey		INT(12)		DEFAULT '0',
	Label				VARCHAR(30),
	Value				DECIMAL(10,2)	DEFAULT '0.00',
	PRIMARY KEY(DisbursementId)
);

-- line(s) below added by Guadalupe Garcia on 11/03/2005
-- ALTER statement to be ran against webfdmsusers db
ALTER TABLE usersecurity ADD COLUMN StateId CHAR(2);
ALTER TABLE usersecurity ADD COLUMN CaseListDisplayPreneed CHAR(1) DEFAULT 'Y';
ALTER TABLE usersecurity ADD COLUMN CaseListDisplayVoided CHAR(1) DEFAULT 'Y';
ALTER TABLE usersecurity ADD COLUMN TOS CHAR(1) DEFAULT 'N';

-- line(s) below added by Guadalupe Garcia on 11/07/2005
ALTER TABLE vitalstats ADD COLUMN GSTAmt INT(11) AFTER PNUrnAmt;

-- line(s) below added by Ranando King on 11/23/2005
ALTER TABLE arrangers ADD COLUMN EmbalmerLicenseNumber VARCHAR(16);
ALTER TABLE arrangers ADD COLUMN IsCounselor TINYINT(1) DEFAULT 1;

-- line(s) below added by Guadalupe Garcia on 11/18/2005
CREATE TABLE speeddatarule (
	TabCategory		VARCHAR(15)		NOT NULL,
	Col0			VARCHAR(30),
	Col1			VARCHAR(30),
	Col2			VARCHAR(30),
	Col3			VARCHAR(30),
	Col4			VARCHAR(30),
	Col5			VARCHAR(30),
	Col6			VARCHAR(30),
	Col7			VARCHAR(30),
	Col8			VARCHAR(30),
	Col9			VARCHAR(30),	
	Col10			VARCHAR(30),						
	UNIQUE(TabCategory)
);

INSERT INTO speeddatarule VALUES ('Accident','Accident','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('AdditionalSrv','Additional Service Name','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Adsource','Advertising Source Name','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Ancestry','Ancestry','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('ATSOURCE','At Need Source','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('BirthOrder','Birth Order','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('BirthType','Birth Type','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('CASHTYPE','General Bank Account','Account Code','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('CASKETEXT','Casket Exterior (Abbreviated)','Casket Exterior','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('CASKETINT','Casket Interior','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('CASKETTYPE','Casket Type','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('CASKETUSE','Casket Use','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Caskets','Manufacturer','Description','Price','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Cemetery','Name of Cemetery','Street Address','City','State','Zip','County','Phone\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Charities','','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('CHARITY','Charity Name','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('CheckListAN','Sequence Number','Checklist At-Need Item','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Churches','Name of Church','Address','City','State','Zip\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('CITY_STATE','City','State (Spelled Out)','Zip','County','State (Abbreviated)\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Counselors','Counselor Name','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('County','County Name','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Country','Country Name','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Crdstyle','Lena Liu/Window Box','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Cremains','Disposition of Cremated Remain','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Crematory','Crematory Name','Street Address','City','State','Zip\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_41','Heading 41','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_42','Heading 42','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_43','Heading 43','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_44','Heading 44','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_45','Heading 45','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_46','Heading 46','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_47','Heading 47','','','','\r',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_48','Heading 48\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_49','Heading 49\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_50','Heading 50\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_51','Heading 51\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_52','Heading 52\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_53','Heading 53\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_54','Heading 54\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_55','Heading 55\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_56','Heading 56\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_57','Heading 57\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_58','Heading 58\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_59','Heading 59\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_60','Heading 60\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_61','Heading 61\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_62','Heading 62\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_63','Heading 63\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_64','Heading 64\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_65','Heading 65\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_66','Heading 66\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_67','Heading 67\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_68','Heading 68\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_69','Heading 69\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_70','Heading 70\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_71','Heading 71\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_72','Heading 72\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_73','Heading 73\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_74','Heading 74\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_75','Heading 75\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_76','Heading 76\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_77','Heading 77\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_78','Heading 78\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_79','Heading 79\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('cust_hd_80','Heading 80\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom1','Heading 1\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom10','Heading 10\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom11','Heading 11\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom12','Heading 12\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom13','Heading 13\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom14','Heading 14\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom15','Heading 15\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom16','Heading 16\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom17','Heading 17\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom18','Heading 18\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom19','Heading 19\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom2','Heading 2\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom20','Heading 20\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom21','Heading 21\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom22','Heading 22\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom23','Heading 23\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom24','Heading 24\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom25','Heading 25\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom26','Heading 26\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom27','Heading 27\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom28','Heading 28\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom29','Heading 29\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom3','Heading 3\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom30','Heading 30\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom31','Heading 31\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom32','Heading 32\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom33','Heading 33\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom34','Heading 34\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom35','Heading 35\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom36','Heading 36\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom37','Heading 37\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom38','Heading 38','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom39','Heading 39','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom4','Heading 4','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom40','Heading 40','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom5','Heading 5','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom6','Heading 6','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom7','Heading 7','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom8','Heading 8','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('custom9','Heading 9','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('DecEducation','Decedent Education','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('DISPCODE','Disposition Code','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Dispostn','Disposition','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Doctor','Doctor Name','Street Address','City','State','Zip','Phone','ID\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('EMBALM','Embalming Status','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('industry','Work Industry','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('inpatdoa','Inpatient Option','','','','','','\r',NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('INVLOCTN','Inventory Location','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('LOCDEATH','Name of Location','Street Address','City','State (Spelled Out)','County','State (Abbreviated)','Zip','','','','\r');
INSERT INTO speeddatarule VALUES ('Marital','Marital Status','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('Memstyle','Memorial Style','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('Minister','Name of Minister','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('MKTPLAN','Marketing Plan','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('Occupation','Occupation','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('Organist','Name of Organist','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('PAYCOMP','Code','Display Name','Type','Assignment Fee %','Assignment Fee Max','Assignment Fee Min','GPL Key','Charge Modifiable','Can Payments Be Applied','Diff. Default','Can Finance Charges Be Applied');
INSERT INTO speeddatarule VALUES ('PAYMETHOD','Payment Method','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('PAYTYPES','Payment Type','Account Code','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('Placedth','Place of Death','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('PLCATGRY','Price List Category (Letter)','Price List Category','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('PnBanks','Pre Need Bank Name','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('PnCasket','Pre Need Casket','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('PnDepository','Pre Need Dispository','','','','','','','','','','\r');
INSERT INTO speeddatarule VALUES ('PnFundType','Pre Need Fund Type','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('PnSource','Pre Need Source','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('PnUrn','Pre Need Urn','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('PnVault','Pre Need Vault','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('PRODLINE','Item Category','Item Category Initial','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('Race','Race','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('Relation','Relationship','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('REVTYPE','Account','Account Code','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('Rivals','Funeral Home Name','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('SaleType','Sale Type','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('Soloist','Name of Soloist','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('Srvplace','Name of Service Place','Street Address','City','State','Zip','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('States','State (Spelled Out)','State (Abbreviated)','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('SUPPLIER','Supplier Name','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('TaxCode','Tax Code','Description','Local Tax','State Tax','Local Limit','State Limit','Article Rate','Article Lower Limit','Article Upper Limit\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('TimeOfService','Time Value','','','','','','','','\r',NULL,NULL);
INSERT INTO speeddatarule VALUES ('Township','Name of Township','','\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Vaults','Manufacturer','Description','Price\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO speeddatarule VALUES ('Versions','Price List','Price List Description','\r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

ALTER TABLE transactionhistory ADD COLUMN TaxAmount int(10) default '0';
