-- cjongs added 12/06/10

-- don't for get to create db and jndi/jdbc connection.
ALTER TABLE obituary ADD COLUMN ClientId INT DEFAULT 0 AFTER ArchiveDate;
ALTER TABLE obituary ADD COLUMN PostedYN VARCHAR(1) DEFAULT '' AFTER ClientId;
ALTER TABLE obituary ADD COLUMN PostedDateTime DATETIME AFTER PostedYN;