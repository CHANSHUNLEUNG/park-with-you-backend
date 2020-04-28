CREATE TABLE `coupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `gen_order_id` int DEFAULT NULL,
  `used_order_id` int DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'inactive',
  PRIMARY KEY (`id`),
  KEY `coupon_fk_customer_idx` (`customer_id`),
  KEY `coupon_fk_gen_order_idx` (`gen_order_id`),
  KEY `coupon_fk_used_order_idx` (`used_order_id`),
  CONSTRAINT `coupon_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `coupon_fk_gen_order` FOREIGN KEY (`gen_order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `coupon_fk_used_order` FOREIGN KEY (`used_order_id`) REFERENCES `order` (`id`)
)