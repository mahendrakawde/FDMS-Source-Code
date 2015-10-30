-- Added by David Rollins 01/17/2007
ALTER TABLE `webfdmsusers`.`usersecurity` ADD COLUMN `dbLookup` VARCHAR(90);
ALTER TABLE `webfdmsusers`.`companies` ADD COLUMN `dbLookup` VARCHAR(90);



-- Added by David Rollins 03/09/2007
ALTER TABLE `webfdmsusers`.`userlog` MODIFY COLUMN `user_id` INT(12) UNSIGNED ZEROFILL, ADD INDEX `UserIDIndex`(`user_id`);
ALTER TABLE `webfdmsusers`.`usersecurity` ADD INDEX `DeletedIndex`(`DeleteCode`);
ALTER TABLE `webfdmsusers`.`userlocations` ADD INDEX `UserIDIndex`(`user_id`);
ALTER TABLE `webfdmsusers`.`userlocations` ADD INDEX `LocaleIDIndex`(`locale_Id`);
