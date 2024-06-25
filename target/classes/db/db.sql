CREATE SCHEMA `mercado` ;
CREATE TABLE `mercado`.`posts` (
  `id` INT NULL AUTO_INCREMENT,
  `Titulo` VARCHAR(500) NOT NULL,
  `Descricao` VARCHAR(1000) NOT NULL,
  `img` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`, `Titulo`));
