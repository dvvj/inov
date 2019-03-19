
CREATE TABLE customers (
  uid VARCHAR(255) NOT NULL,
  name VARCHAR(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  gender CHAR(1) NOT NULL,
  PRIMARY KEY (uid)
);
