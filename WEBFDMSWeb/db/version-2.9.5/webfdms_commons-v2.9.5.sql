-- Added by David Rollins 9/7/2007
ALTER TABLE `commondata`.`zipcodedata` ADD INDEX `Index_Read`(`Deleted`, `Country`, `ZipCode`);