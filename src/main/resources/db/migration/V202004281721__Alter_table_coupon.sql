ALTER TABLE `coupon`
ADD COLUMN `discount_amount` DOUBLE NULL DEFAULT 10 AFTER `used_order_id`;