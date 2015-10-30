-- Add securuty for item description and price  change on finacial page 
alter table usersecurity add column priceDescriptionFinancial tinyint(1) not null default 1; -- priceDescriptionFinancial option added 
