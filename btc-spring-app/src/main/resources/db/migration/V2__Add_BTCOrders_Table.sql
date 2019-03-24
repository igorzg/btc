CREATE TABLE IF NOT EXISTS `BTCOrders`
(
  `order_id`    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_id`  BIGINT UNSIGNED NOT NULL,
  `details`     VARCHAR(80)     NOT NULL,
  `price_limit` DECIMAL(16, 8) DEFAULT 0.00000000,
  `processed`   TINYINT(1)     DEFAULT 0,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `BTCOrders_account_id` FOREIGN KEY (`account_id`) REFERENCES BTCAccount (`account_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX `idx_price_limit`
  ON `BTCOrders` (`price_limit`);

INSERT INTO `BTCOrders` (`order_id`, `account_id`, `details`, `price_limit`, `processed`)
VALUES (1, 1, 'process limit on acc_one', 30.00, 0);
