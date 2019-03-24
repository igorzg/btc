CREATE TABLE IF NOT EXISTS `BTCAccount`
(
  `account_id`  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(80)     NOT NULL,
  `usd_balance` DECIMAL(36, 8) DEFAULT 0.00000000,
  `btc_balance` DECIMAL(16, 8) DEFAULT 0.00000000,
  PRIMARY KEY (`account_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX `idx_name`
  ON `BTCAccount` (`name`);

CREATE INDEX `idx_usd_balance`
  ON `BTCAccount` (`usd_balance`);

CREATE INDEX `idx_btc_balance`
  ON `BTCAccount` (`btc_balance`);

INSERT INTO `BTCAccount` (`account_id`, `name`, `usd_balance`, `btc_balance`) VALUES
  (1, 'acc_one',  20.00, 0.0),
  (2, 'acc_two',  10.00, 0.0);


