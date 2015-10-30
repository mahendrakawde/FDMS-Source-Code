-- cjongs 06/11/10
ALTER TABLE reportscheduling ADD COLUMN RunTimeHH INT(11) DEFAULT 0 AFTER RunDate;
ALTER TABLE reportscheduling ADD COLUMN RunTimeMM INT(11) DEFAULT 0 AFTER RunTimeHH;

-- cjongs 06/25/10
ALTER TABLE vitalstats ADD COLUMN AdmittedFacilityDateOfPlaceOfDeath VARCHAR(8) DEFAULT '' AFTER VoidedDate;

ALTER TABLE vitalstats ADD COLUMN transferredLocationName VARCHAR(59) DEFAULT '' AFTER AdmittedFacilityDateOfPlaceOfDeath;
ALTER TABLE vitalstats ADD COLUMN transferredLocationAddr VARCHAR(50) DEFAULT '' AFTER transferredLocationName;
ALTER TABLE vitalstats ADD COLUMN transferredLocationCity VARCHAR(50) DEFAULT '' AFTER transferredLocationAddr;
ALTER TABLE vitalstats ADD COLUMN transferredLocationState VARCHAR(50) DEFAULT '' AFTER transferredLocationCity;
ALTER TABLE vitalstats ADD COLUMN transferredLocationZip VARCHAR(10) DEFAULT '' AFTER transferredLocationState;
ALTER TABLE vitalstats ADD COLUMN transferredLocationPhone VARCHAR(15) DEFAULT '' AFTER transferredLocationZip;

ALTER TABLE vitalstats ADD COLUMN HospitalNameOfBirthPlaceOfDeceasedUnder1YearOld VARCHAR(80) DEFAULT '' AFTER transferredLocationPhone;

ALTER TABLE vitalstats ADD COLUMN ResidentWithinCityVillageLimit VARCHAR(1) DEFAULT '' AFTER HospitalNameOfBirthPlaceOfDeceasedUnder1YearOld;
ALTER TABLE vitalstats ADD COLUMN ResidentLocalityLimitName VARCHAR(50) DEFAULT '' AFTER ResidentWithinCityVillageLimit;

ALTER TABLE vitalstats ADD COLUMN CoronerSPhysicianTitle VARCHAR(50) DEFAULT '' AFTER ResidentLocalityLimitName;
ALTER TABLE vitalstats ADD COLUMN CoronerSPhysician VARCHAR(50) DEFAULT '' AFTER CoronerSPhysicianTitle;
ALTER TABLE vitalstats ADD COLUMN CoronerSPhysicianLicense VARCHAR(30) DEFAULT '' AFTER CoronerSPhysician;
ALTER TABLE vitalstats ADD COLUMN CoronerSPhysicianDateSign VARCHAR(30) DEFAULT '' AFTER CoronerSPhysicianLicense;
ALTER TABLE vitalstats ADD COLUMN AttendingPhysicianTitle VARCHAR(30) DEFAULT '' AFTER CoronerSPhysicianDateSign;
ALTER TABLE vitalstats ADD COLUMN AttendingPhysicianLicense VARCHAR(30) DEFAULT '' AFTER AttendingPhysicianTitle;

ALTER TABLE vitalstats ADD COLUMN AttendingPhysicianAddr VARCHAR(50) DEFAULT '' AFTER AttendingPhysicianLicense;
ALTER TABLE vitalstats ADD COLUMN AttendingPhysicianCity VARCHAR(50) DEFAULT '' AFTER AttendingPhysicianAddr;
ALTER TABLE vitalstats ADD COLUMN AttendingPhysicianState VARCHAR(50) DEFAULT '' AFTER AttendingPhysicianCity;
ALTER TABLE vitalstats ADD COLUMN AttendingPhysicianZip VARCHAR(10) DEFAULT '' AFTER AttendingPhysicianState;
ALTER TABLE vitalstats ADD COLUMN AttendingPhysicianPhone VARCHAR(15) DEFAULT '' AFTER AttendingPhysicianZip;

ALTER TABLE vitalstats ADD COLUMN HospitalizedLast2Months VARCHAR(1) DEFAULT '' AFTER AttendingPhysicianPhone;
ALTER TABLE vitalstats ADD COLUMN PregnantDeliveryDate VARCHAR(8) DEFAULT '' AFTER HospitalizedLast2Months;

ALTER TABLE survivors ADD COLUMN GroupType VARCHAR(5) DEFAULT '' AFTER PNLead;
ALTER TABLE billtoparties ADD COLUMN GroupType VARCHAR(5) DEFAULT '' AFTER CellPhone;

-- 07/21/10 CJongs
ALTER TABLE vitalstats MODIFY FacilityName VARCHAR(70) DEFAULT '';

-- 08/10/10 CJongs
ALTER TABLE vitalstats MODIFY COLUMN Active TINYINT(1) DEFAULT 1;
ALTER TABLE vitalstats MODIFY COLUMN VoidedCode CHAR(1) DEFAULT '';
ALTER TABLE billtoparties MODIFY COLUMN FileVersion SMALLINT(6) DEFAULT '0';

-- 08/24/10 CJongs
CREATE TABLE `eden` (
  `EdenId` int(11) NOT NULL AUTO_INCREMENT,
  `State` varchar(5) DEFAULT '',
  `SequenceNumber` smallint(6) DEFAULT '0',
  `Length` smallint(6) DEFAULT '0',
  `Description` varchar(100) DEFAULT '',
  `ColumnDesc` varchar(20) DEFAULT '',
  `UnknowDesc` varchar(1) DEFAULT '',
  `CommentDesc` varchar(150) DEFAULT '',
  `TableName` varchar(100) DEFAULT '',
  `FieldName` varchar(100) DEFAULT '',
  `FieldFormat` varchar(15) DEFAULT '',
  PRIMARY KEY (`EdenId`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=latin1;

/*Data for the table `eden` */

insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (1,'UT',1,50,'Deceased Last','0001-0050','Y','\r','vitalstats','DeceasedLastName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (2,'UT',2,50,'Deceased First','0051-0100','Y','\r','vitalstats','DeceasedFirstName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (3,'UT',3,50,'Deceased Middle','0101-0150','Y','\r','vitalstats','DeceasedMidName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (4,'UT',4,4,'Deceased Suffix','0151-0154','N','Values: Jr Sr I II III IV V VI VII VIII IX X\r','vitalstats','DeceasedSuffix','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (5,'UT',5,50,'Alias 1 Last','0155-0204','N','\r','vitalstats','DecAliasLast','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (6,'UT',6,50,'Alias 1 First','0205-0254','N','\r','vitalstats','DecAliasFirst','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (7,'UT',7,50,'Alias 1 Middle','0255-0304','N','\r','vitalstats','DecAliasMiddle','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (8,'UT',8,50,'Alias 2 Last','0305-0354','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (9,'UT',9,50,'Alias 2 First','0355-0404','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (10,'UT',10,50,'Alias 2 Middle','0405-0454','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (11,'UT',11,20,'Medical Record Number','0455-0474','N','Decedent?s Hospital Medical Record Number\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (12,'UT',12,11,'SSN','0475-0485','Y','Format:999-99-9999\r','vitalstats','SocialSecurityNo','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (13,'UT',13,1,'Gender M','486','N','Checkbox\r','vitalstats','DeceasedSex','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (14,'UT',14,1,'Gender F','487','N','Checkbox\r','vitalstats','DeceasedSex','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (15,'UT',15,1,'Gender U','488','N','Checkbox\r','vitalstats','DeceasedSex','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (16,'UT',16,35,'Birth City','0489-0523','Y','\r','vitalstats','DecBirthPlaceCSV','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (17,'UT',17,10,'Birth County','0524-0533','Y','\r','vitalstats','CountyOfBirth','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (18,'UT',18,25,'Birth State','0534-0558','Y','State or Canadian Province\r','vitalstats','DecBirthPlaceCSV','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (19,'UT',19,35,'Birth Country','0559-0593','Y','\r','vitalstats','DecBirthPlaceCSV','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (20,'UT',20,4,'Birth CCYY','0594-0597','Y','Century and Year of Birth\r','vitalstats','DateOfBirth','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (21,'UT',21,2,'Birth MM','0598-0599','Y','Month of Birth\r','vitalstats','DateOfBirth','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (22,'UT',22,2,'Birth DD','0600-0601','Y','Day of Birth\r','vitalstats','DateOfBirth','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (23,'UT',23,3,'Age In Years','0602-0604','Y','Calculate if over 1 year otherwise set to 000 Values:000 if < 1 year\r','vitalstats','AgeYears','numeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (24,'UT',24,2,'Age In Months','0605-0606','Y','Blank if > 1 year\r','vitalstats','AgeMonths','numeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (25,'UT',25,2,'Age In Days','0607-0608','Y','If under 1 month Blank if > 1 month\r','vitalstats','AgeDays','numeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (26,'UT',26,2,'Age In Hours','0609-0610','Y','If under 24 hours Blank if > 1 day\r','vitalstats','AgeHours','numeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (27,'UT',27,2,'Age In Minutes','0611-0612','Y','If under 1 hour Blank if > 1 hour\r','vitalstats','AgeMinutes','numeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (28,'UT',28,1,'Race White','613','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (29,'UT',29,1,'Race Black','614','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (30,'UT',30,1,'Race Am Indian','615','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (31,'UT',31,60,'Race Tribe Spec','0616-0675','N','Name of tribe if above field checked\r','vitalstats','TribalName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (32,'UT',32,1,'Race Chinese','676','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (33,'UT',33,1,'Race Japanese','677','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (34,'UT',34,1,'Race Hawaiian','678','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (35,'UT',35,1,'Race Filipino','679','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (36,'UT',36,1,'Race Other Asian','680','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (37,'UT',37,60,'Race Asian Spec','0681-0740','N','Specify if above field checked\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (38,'UT',38,1,'Race Asian Indian','741','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (39,'UT',39,1,'Race Korean','742','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (40,'UT',40,1,'Race Samoan','743','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (41,'UT',41,1,'Race Vietnamese','744','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (42,'UT',42,1,'Race Guamanian','745','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (43,'UT',43,1,'Race Pac Islander','746','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (44,'UT',44,60,'Race Islander Spec','0747-0806','N','Specify if above field checked\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (45,'UT',45,1,'Race Other','807','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (46,'UT',46,60,'Race Other Spec','0808-0867','N','Specify if above field checked\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (47,'UT',47,1,'Race Unknown','868','N','Checkbox\r','vitalstats','Race','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (48,'UT',48,1,'Hispanic Origin Y','869','N','Checkbox\r','vitalstats','HispanicOriginBox','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (49,'UT',49,1,'Hispanic Origin N','870','N','Checkbox\r','vitalstats','HispanicOriginBox','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (50,'UT',50,1,'Hispanic Unknown','871','N','Checkbox\r','vitalstats','HispanicOriginBox','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (51,'UT',51,1,'Hispanic Mexican','872','N','Checkbox\r','vitalstats','DecSpecifyHispanic','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (52,'UT',52,1,'Hispanic Cuban','873','N','Checkbox\r','vitalstats','DecSpecifyHispanic','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (53,'UT',53,1,'Hispanic Puerto Rico','874','N','Checkbox \r','vitalstats','DecSpecifyHispanic','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (54,'UT',54,1,'Hispanic Other','875','N','Checkbox\r','vitalstats','DecSpecifyHispanic','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (55,'UT',55,60,'Hispanic Other Spec','0876-0935','N','Specify if above field checked\r','vitalstats','DecSpecifyHispanic','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (56,'UT',56,1,'Education None','936','N','Checkbox Education Fields\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (57,'UT',57,1,'Ed 8th Grade','937','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (58,'UT',58,1,'Ed 9th-12th Grade','938','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (59,'UT',59,1,'Ed High School','939','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (60,'UT',60,1,'Ed Some College','940','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (61,'UT',61,1,'Ed Associate','941','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (62,'UT',62,1,'Ed Bachelor','942','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (63,'UT',63,1,'Ed Master','943','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (64,'UT',64,1,'Ed Doctorate','944','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (65,'UT',65,1,'Ed Unknown','945','N','Checkbox\r','vitalstats','DecEducation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (66,'UT',66,50,'Occupation','0946-0995','Y','\r','vitalstats','Occupation','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (67,'UT',67,50,'Industry','0996-1045','Y','\r','vitalstats','KindOfBusiness','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (68,'UT',68,1,'Armed Forces Y','1046','N','Checkbox\r','vitalstats','InArmedForces','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (69,'UT',69,1,'Armed Forces N','1047','N','Checkbox\r','vitalstats','InArmedForces','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (70,'UT',70,1,'Armed Forces U','1048','N','Checkbox\r','vitalstats','InArmedForces','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (71,'UT',71,1,'Pregnant No','1049','N','Checkbox Not pregnant within past year \r','vitalstats','PregnantAtDeath','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (72,'UT',72,1,'Pregnant Yes','1050','N','Checkbox Pregnant at time of death\r','vitalstats','PregnantAtDeath','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (73,'UT',73,1,'Pregnant 42 Days','1051','N','Checkbox  Not pregnant but pregnant within 42 days of death\r','vitalstats','PregnantAtDeath','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (74,'UT',74,1,'Pregnant One Year','1052','N','Checkbox Not pregnant but pregnant 43 days to 1 year before death\r','vitalstats','PregnantAtDeath','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (75,'UT',75,1,'Pregnant Unknown','1053','N','Checkbox Unknown if pregnant within the past year\r','vitalstats','PregnantAtDeath','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (76,'UT',76,50,'Father Last','1054-1103','Y','\r','vitalstats','FatherLastName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (77,'UT',77,50,'Father First','1104-1153','Y','\r','vitalstats','FatherFirstName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (78,'UT',78,50,'Father Middle','1154-1203','Y','\r','vitalstats','FatherMiddleName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (79,'UT',79,4,'Father Suffix','1204-1207','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (80,'UT',80,50,'Mother Maiden Name','1208-1257','Y','\r','vitalstats','MotherLastName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (81,'UT',81,50,'Mother First','1258-1307','Y','\r','vitalstats','MotherFirstName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (82,'UT',82,50,'Mother Middle','1308-1357','Y','\r','vitalstats','MotherMiddleName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (83,'UT',83,4,'Mother Suffix','1358-1361','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (84,'UT',84,1,'MS Never Married','1362','N','Checkbox Marital Status fields\r','vitalstats','DecMarried','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (85,'UT',85,1,'MS Married','1363','N','Checkbox\r','vitalstats','DecMarried','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (86,'UT',86,1,'MS Widowed','1364','N','Checkbox\r','vitalstats','DecMarried','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (87,'UT',87,1,'MS Divorced','1365','N','Checkbox\r','vitalstats','DecMarried','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (88,'UT',88,1,'MS Separated','1366','N','Checkbox\r','vitalstats','DecMarried','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (89,'UT',89,1,'MS Unknown','1367','N','Checkbox\r','vitalstats','DecMarried','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (90,'UT',90,50,'Spouse Last','1368-1417','Y','If surviving spouse is female enter maiden name\r','vitalstats','SpouseLastName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (91,'UT',91,50,'Spouse First','1418-1467','Y','\r','vitalstats','SpouseFirstName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (92,'UT',92,50,'Spouse Middle','1468-1517','Y','\r','vitalstats','SpouseMiddleName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (93,'UT',93,4,'Spouse Suffix','1518-1521','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (94,'UT',94,50,'Informant Last','1522-1571','N','\r','vitalstats','InformantLastName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (95,'UT',95,50,'Informant First','1572-1621','N','\r','vitalstats','InformantFirstName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (96,'UT',96,50,'Informant Middle','1622-1671','N','\r','vitalstats','InformantMiddleNam','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (97,'UT',97,4,'Informant Suffix','1672-1675','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (98,'UT',98,50,'Informant Address 1','1676-1725','N','\r','vitalstats','InformantStreet','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (99,'UT',99,50,'Informant Address 2','1726-1775','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (100,'UT',100,35,'Informant City','1776-1810','N','\r','vitalstats','InformantCity','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (101,'UT',101,10,'Informant County','1811-1820','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (102,'UT',102,25,'Informant State','1821-1845','N','\r','vitalstats','InformantState','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (103,'UT',103,35,'Informant Country','1846-1880','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (104,'UT',104,5,'Informant Zip 5','1881-1885','N','\r','vitalstats','InformantZip','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (105,'UT',105,4,'Informant Zip 4','1886-1889','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (106,'UT',106,40,'Informant Relation','1890-1929','N','\r','vitalstats','InformantRelation','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (107,'UT',107,50,'Res Street Address 1','1930-1979','Y','Residence of deceased\r','vitalstats','DecResStreet','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (108,'UT',108,50,'Res Street Address 2','1980-2029','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (109,'UT',109,35,'Res City','2030-2064','Y','\r','vitalstats','DecResMailCity','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (110,'UT',110,10,'Res County','2065-2074','Y','\r','vitalstats','DecResCounty','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (111,'UT',111,25,'Res State','2075-2099','Y','\r','vitalstats','DecResState','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (112,'UT',112,35,'Res Country','2100-2134','Y','\r','vitalstats','DecResCountry','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (113,'UT',113,5,'Res Zip 5','2135-2139','Y','\r','vitalstats','DecResZip','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (114,'UT',114,4,'Res Zip 4','2140-2143','N','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (115,'UT',115,1,'Res In City Limits Y','2144','N','Checkbox\r','vitalstats','DecResInsideCity','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (116,'UT',116,1,'Res In City Limits N','2145','N','Checkbox\r','vitalstats','DecResInsideCity','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (117,'UT',117,1,'Res In City Limits U','2146','N','Checkbox\r','vitalstats','DecResInsideCity','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (118,'UT',118,4,'Death CCYY','2147-2150','N','Date of Death\r','vitalstats','DeathDateKey','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (119,'UT',119,2,'Death MM','2151-2152','N','\r','vitalstats','DeathDateKey','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (120,'UT',120,2,'Death DD','2153-2154','N','\r','vitalstats','DeathDateKey','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (121,'UT',121,1,'FoundDate','2155','N','Checkbox ?Check if Found on this Date?\r','vitalstats','','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (122,'UT',122,2,'Time Of Death HH','2156-2157','Y','\r','vitalstats','TimeOfDeath','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (123,'UT',123,2,'Time Of Death MM','2158-2159','Y','\r','vitalstats','TimeOfDeath','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (124,'UT',124,1,'FoundTime','2160','N','Checkbox Check if ?Found at this Time?\r','vitalstats','','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (125,'UT',125,1,'POD Inpatient','2161','N','Checkbox Place of Death\r','vitalstats','InpatientDOA','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (126,'UT',126,1,'POD ER','2162','N','Checkbox\r','vitalstats','InpatientDOA','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (127,'UT',127,1,'POD DOA','2163','N','Checkbox\r','vitalstats','InpatientDOA','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (128,'UT',128,60,'Facility Name','2164-2223','N','\r','vitalstats','','');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (129,'UT',129,1,'POD Dec Home','2224','N','Checkbox Deceased?s home\r','vitalstats','InpatientDOA','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (130,'UT',130,1,'POD Nursing Home','2225','N','Checkbox\r','vitalstats','InpatientDOA','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (131,'UT',131,60,'Nursing Home Name','2226-2285','N','\r','vitalstats','','');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (132,'UT',132,1,'POD Other','2286','N','Checkbox\r','vitalstats','InpatientDOA','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (133,'UT',133,60,'POD Other Spec','2287-2346','N','\r','vitalstats','InpatientDOA','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (134,'UT',134,1,'POD Unknown','2347','N','Checkbox\r','vitalstats','ActualPlaceDeath','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (135,'UT',135,60,'Non Facility Location','2348-2407','N','Location (usually address) if death did not occur at a facility (POD is Dec Home or Other)  \r','vitalstats','LocationOfDeath','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (136,'UT',136,10,'County Of Death','2408-2417','Y','\r','vitalstats','CountyOfDeath','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (137,'UT',137,35,'City Of Death','2418-2452','Y','\r','vitalstats','CityOfDeath','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (138,'UT',138,104,'Certifier Full Name','2453-2556','N','Last First Middle Initial\r','vitalstats','DoctorName','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (139,'UT',139,1,'ME Contacted Y','2557','N','Checkbox The Medical Examiner has been contacted\r','vitalstats','ReferredToME','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (140,'UT',140,1,'ME Contacted N','2558','N','Checkbox The Medical Examiner has not been contacted\r','vitalstats','ReferredToME','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (141,'UT',141,1,'ME Contacted U','2559','N','Checkbox Unknown if Medical Examiner Contacted\r','vitalstats','ReferredToME','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (142,'UT',142,15,'ME Case Number','2560-2574','Y','\r','vitalstats','MEcaseNumber','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (143,'UT',143,1,'Autopsy Done Y','2575','N','Checkbox\r','vitalstats','Autopsy','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (144,'UT',144,1,'Autopsy Done N','2576','N','Checkbox\r','vitalstats','Autopsy','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (145,'UT',145,1,'Autopsy Available Y','2577','N','Checkbox\r','vitalstats','AutopsyFindings','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (146,'UT',146,1,'Autopsy Available N','2578','N','Checkbox\r','vitalstats','AutopsyFindings','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (147,'UT',147,1,'Never Seen Alive','2579','N','Checkbox\r','vitalstats','','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (148,'UT',148,4,'Last Seen CCYY','2580-2583','Y','Date deceased was last attended by physician\r','vitalstats','DateCertified','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (149,'UT',149,2,'Last Seen MM','2584-2585','Y','\r','vitalstats','DateCertified','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (150,'UT',150,2,'Last Seen DD','2586-2587','Y','\r','vitalstats','DateCertified','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (151,'UT',151,1,'Disposition Entombment','2588','N','Checkbox Disposition Method\r','vitalstats','DispositionMethod','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (152,'UT',152,1,'Disposition Donation','2589','N','Checkbox\r','vitalstats','DispositionMethod','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (153,'UT',153,1,'Disposition Burial','2590','N','Checkbox\r','vitalstats','DispositionMethod','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (154,'UT',154,1,'Disposition Cremation','2591','N','Checkbox\r','vitalstats','DispositionMethod','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (155,'UT',155,1,'Disposition Removal','2592','N','Checkbox\r','vitalstats','DispositionMethod','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (156,'UT',156,1,'Disposition Other','2593','N','Checkbox\r','vitalstats','DispositionMethod','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (157,'UT',157,60,'Disposition Other Spec','2594-2653','N','Specify field when ?other? selected from disposition table\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (158,'UT',158,4,'Disposition CCYY','2654-2657','Y','Date of Disposition\r','vitalstats','DispositionDate','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (159,'UT',159,2,'Disposition MM','2658-2659','Y','\r','vitalstats','DispositionDate','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (160,'UT',160,2,'Disposition DD','2660-2661','Y','\r','vitalstats','DispositionDate','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (161,'UT',161,60,'Immediate Cause','2662-2721','N','Immediate Cause of Death\r','vitalstats','CauseOfDeath1','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (162,'UT',162,3,'Immediate Interval','2722-2724','Y','\r','vitalstats','CauseInterval1','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (163,'UT',163,7,'Immediate Interval Unit','2725-2731','N','Values: Years Months Weeks Days Hours Minutes\r','vitalstats','CauseInterval1','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (164,'UT',164,60,'Additional Cause 1','2732-2791','N','\r','vitalstats','CauseOfDeath2','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (165,'UT',165,3,'Additional Interval 1','2792-2794','Y','\r','vitalstats','CauseInterval2','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (166,'UT',166,7,'Additional Interval Unit1','2795-2801','N','\r','vitalstats','CauseInterval2','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (167,'UT',167,60,'Additional Cause 2','2802-2861','N','\r','vitalstats','CauseOfDeath3','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (168,'UT',168,3,'Additional Interval 2','2862-2864','Y','\r','vitalstats','CauseInterval3','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (169,'UT',169,7,'Additional Interval Unit2','2865-2871','N','\r','vitalstats','CauseInterval3','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (170,'UT',170,60,'Underlying Cause','2872-2931','N','\r','vitalstats','CauseOfDeath4','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (171,'UT',171,3,'Underlying Interval','2932-2934','Y','\r','vitalstats','CauseInterval4','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (172,'UT',172,7,'Underlying Interval Unit','2935-2941','N','\r','vitalstats','CauseInterval4','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (173,'UT',173,60,'Other Conditions','2942-3001','N','\r','vitalstats','OtherConditions','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (174,'UT',174,1,'Manner Natural','3002','N','Checkbox\r','vitalstats','AccidSuicideNaturl','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (175,'UT',175,1,'Manner Accident','3003','N','Checkbox\r','vitalstats','AccidSuicideNaturl','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (176,'UT',176,1,'Manner Suicide','3004','N','Checkbox\r','vitalstats','AccidSuicideNaturl','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (177,'UT',177,1,'Manner Homicide','3005','N','Checkbox\r','vitalstats','AccidSuicideNaturl','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (178,'UT',178,1,'Manner Not Determined','3006','N','Checkbox\r','vitalstats','AccidSuicideNaturl','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (179,'UT',179,1,'Manner Pending','3007','N','Checkbox\r','vitalstats','AccidSuicideNaturl','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (180,'UT',180,1,'Tobacco Probable','3008','N','Checkbox Tobacco probably contributed to the cause of death\r','vitalstats','Tobacco','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (181,'UT',181,1,'Tobacco Underlying','3009','N','Checkbox Tobacco was the underlying cause of death\r','vitalstats','Tobacco','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (182,'UT',182,1,'Tobacco Not Contribute','3010','N','Checkbox Tobacco did not contribute to cause of death\r','vitalstats','Tobacco','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (183,'UT',183,1,'Tobacco Unknown','3011','N','Checkbox Tobacco is unknown in relation to the cause of death\r','vitalstats','Tobacco','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (184,'UT',184,1,'Tobacco Non User','3012','N','Checkbox Non user of tobacco\r','vitalstats','Tobacco','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (185,'UT',185,1,'Tobacco Unknown User','3013','N','Checkbox Unknown if user of tobacco\r','vitalstats','Tobacco','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (186,'UT',186,1,'Injury Y','3014','N','Checkbox Injury data are included in this record\r','vitalstats','InjuryOccurred','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (187,'UT',187,1,'Injury N','3015','N','Checkbox\r','vitalstats','InjuryOccurred','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (188,'UT',188,4,'Injury CCYY','3016-3019','Y','Date of Injury\r','vitalstats','InjuryDate','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (189,'UT',189,2,'Injury MM','3020-3021','Y','\r','vitalstats','InjuryDate','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (190,'UT',190,2,'Injury DD','3022-3023','Y','\r','vitalstats','InjuryDate','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (191,'UT',191,2,'Injury Time HH','3024-3025','Y','Time of Injury ? Hour\r','vitalstats','InjuryTime','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (192,'UT',192,2,'Injury Time MM','3026-3027','Y','Minute\r','vitalstats','InjuryTime','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (193,'UT',193,40,'Injury Street','3028-3067','Y','\r','vitalstats','InjuryStreet','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (194,'UT',194,35,'Injury City','3068-3102','Y','\r','vitalstats','InjuryCity','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (195,'UT',195,10,'Injury County','3103-3112','Y','\r','vitalstats','InjuryCounty','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (196,'UT',196,25,'Injury State','3113-3137','Y','\r','vitalstats','InjuryState','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (197,'UT',197,35,'Injury Country','3138-3172','Y','\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (198,'UT',198,60,'Injury Place','3173-3232','Y','Home construction site restaurant wooded area vacant lot farm street factory office building etc.\r','vitalstats','InjuryPlace','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (199,'UT',199,1,'Injury At Work Y','3233','N','Checkbox\r','vitalstats','InjuryAtWork','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (200,'UT',200,1,'Injury At Work N','3234','N','Checkbox\r','vitalstats','InjuryAtWork','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (201,'UT',201,1,'Injury At Work U','3235','N','Checkbox Unknown if injury occurred at work\r','vitalstats','InjuryAtWork','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (202,'UT',202,1,'Injury Motor Driver','3236','N','Checkbox\r','vitalstats','InjuryTransportation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (203,'UT',203,1,'Injury Motor Passenger','3237','N','Checkbox\r','vitalstats','InjuryTransportation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (204,'UT',204,1,'Injury Motor Pedestrian','3238','N','Checkbox\r','vitalstats','InjuryTransportation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (205,'UT',205,1,'Injury Motor Other','3239','N','Checkbox\r','vitalstats','InjuryTransportation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (206,'UT',206,1,'Injury Motor Unknown','3240','N','Checkbox\r','vitalstats','InjuryTransportation','checkbox');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (207,'UT',207,60,'Injury Motor Spec','3241-3300','N','Specify field for ?Unknown?\r','vitalstats','','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (208,'UT',208,60,'Injury Description 1','3301-3360','N','\r','vitalstats','InjuryDescription','alphanumeric');
insert  into `eden`(`EdenId`,`State`,`SequenceNumber`,`Length`,`Description`,`ColumnDesc`,`UnknowDesc`,`CommentDesc`,`TableName`,`FieldName`,`FieldFormat`) values (209,'UT',209,60,'Injury Description 2','3361-3420','N','','vitalstats','','alphanumeric');

-- CJongs 08/25/10	
ALTER TABLE financialdata ADD COLUMN FinChargePreviousCalc VARCHAR(8) DEFAULT '' AFTER SalesDescCDID;
ALTER TABLE financialdata ADD COLUMN PreviousFinanceCharge INT(11) DEFAULT 0 AFTER FinChargePreviousCalc;

ALTER TABLE vitalstats ADD COLUMN BuyerId VARCHAR(15) DEFAULT '' AFTER PregnantDeliveryDate;

INSERT INTO speeddata 
	( 
	TabCategory, 
	Locale, 
	LocationId, 
	TabData, 
	SortSequence
	)
	VALUES
	( 
	'PRODLINE', 
	'1', 
	'0', 
	'Merchandise, M', 
	'0'
	);

ALTER TABLE vitalstats ADD BirthPlaceCounty VARCHAR(30) DEFAULT '';

ALTER TABLE reportscheduling ADD COLUMN UserId INT(11) DEFAULT 0 AFTER ReportType;
ALTER TABLE reportscheduling ADD COLUMN StartDateTime DATETIME AFTER DATETIME;
ALTER TABLE reportscheduling ADD COLUMN StopDateTime DATETIME AFTER StartDateTime;

-- cjongs 11/19/10
UPDATE speeddatarule SET col5='County' WHERE TabCategory LIKE 'crematory';
UPDATE speeddatarule SET col6='Phone1' WHERE TabCategory LIKE 'crematory';
UPDATE speeddatarule SET col7='Phone2' WHERE TabCategory LIKE 'crematory';


ALTER TABLE invonhand MODIFY Notes VARCHAR(200) DEFAULT '';
