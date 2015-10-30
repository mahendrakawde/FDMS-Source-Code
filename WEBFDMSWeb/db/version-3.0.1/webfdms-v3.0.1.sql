-- added ReferralNumber field for ticket# 5553	CA DC
use fdmsus_demo;

ALTER TABLE vitalstats ADD COLUMN `ReferralNumber` VARCHAR(15) DEFAULT '';

-- Added in VA info Benifits page Ticket #5425
ALTER TABLE `veteransinfo` ADD COLUMN `Ben1DeathOccur` VARCHAR(50) NULL ; 
ALTER TABLE `veteransinfo` ADD COLUMN `Ben1DeathOccurOther` VARCHAR(70) NULL ; 
ALTER TABLE `veteransinfo` ADD COLUMN `Ben1ClaimantEmail` VARCHAR(30) NULL ; 
ALTER TABLE `veteransinfo` ADD COLUMN `Ben1EmployerID` VARCHAR(30) NULL ; 


-- alter table vitalstats drop column ReferralNumber;
-- ALTER TABLE `veteransinfo` DROP COLUMN `Ben1DeathOccur`; 
-- ALTER TABLE `veteransinfo` DROP COLUMN `Ben1DeathOccurOther`; 
-- ALTER TABLE `veteransinfo` DROP COLUMN `Ben1ClaimantEmail`; 
-- ALTER TABLE `veteransinfo` DROP COLUMN `Ben1EmployerID`;
