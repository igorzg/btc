CREATE TABLE IF NOT EXISTS `BTCOrders`
(
  `order_id`    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_id`  BIGINT          NOT NULL,
  `details`     VARCHAR(80)     NOT NULL,
  `price_limit` DECIMAL(16, 8) DEFAULT 0.00000000,
  `processed`   TINYINT(1)     DEFAULT 0,
  PRIMARY KEY (`order_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX `idx_price_limit`
  ON `BTCOrders` (`price_limit`);
