UPDATE `customer`
set password = SHA2(password, 256);