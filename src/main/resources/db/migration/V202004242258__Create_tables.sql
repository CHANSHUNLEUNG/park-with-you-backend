CREATE TABLE IF NOT EXSIST `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `bank_account` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `parking_place_id` int DEFAULT NULL,
  `order_time` timestamp NULL DEFAULT NULL,
  `start_parking_time` timestamp NULL DEFAULT NULL,
  `parking_duration` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_fk_customer_idx` (`customer_id`),
  KEY `order_fk_parking_place_idx` (`parking_place_id`),
  CONSTRAINT `order_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `order_fk_parking_place` FOREIGN KEY (`parking_place_id`) REFERENCES `parking_place` (`id`)
);

CREATE TABLE `parking_lot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `available_count` int DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `parking_place` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `parking_lot_id` int NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parking_lot_id_idx` (`parking_lot_id`),
  CONSTRAINT `parking_place_fk_parking_lot` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`)
);
