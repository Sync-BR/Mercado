CREATE SCHEMA `mercado` ;
CREATE TABLE `mercado`.`posts` (
  `id` INT NULL AUTO_INCREMENT,
  `Titulo` VARCHAR(500) NOT NULL,
  `Descricao` VARCHAR(1000) NOT NULL,
  `img` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`, `Titulo`));


CREATE TABLE `mercado`.`products` (
  `idProducts` INT NOT NULL AUTO_INCREMENT,
  `productName` VARCHAR(60) NOT NULL,
  `productDescription` VARCHAR(100) NOT NULL,
  `productAmount` INT NOT NULL,
  `productStock` INT NOT NULL,
  `productSupplier` VARCHAR(45) NOT NULL,
  `productBarcode` VARCHAR(15) NOT NULL,
  `productImages` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idProducts`, `productName`));

ALTER TABLE `mercado`.`products` 
CHANGE COLUMN `productAmount` `productValue` DOUBLE NOT NULL ;
