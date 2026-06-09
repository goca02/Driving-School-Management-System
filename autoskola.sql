/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`autoskola` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `autoskola`;



DROP TABLE IF EXISTS `Instruktor`;

CREATE TABLE `Instruktor` (
  `InstruktorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `KorisnickoIme` VARCHAR(30) NOT NULL,
  `Sifra` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`InstruktorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Instruktor` VALUES 
(1,'Gordana','Rakic','goca', 'goca');



DROP TABLE IF EXISTS `TipAutomobila`;

CREATE TABLE `TipAutomobila` (
  `TipAutomobilaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Marka` VARCHAR(50) NOT NULL,
  `Model` VARCHAR(50) NOT NULL,
  `Gorivo` VARCHAR(50) NOT NULL,
  `Menjac` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`TipAutomobilaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `TipAutomobila` VALUES
(1, 'Volkswagen', 'Golf 7', 'Dizel', 'Manuelni'),
(2, 'Toyota', 'Yaris', 'Benzin', 'Automatski'),
(3, 'Renault', 'Clio', 'Benzin', 'Manuelni'),
(4, 'Opel', 'Corsa', 'Dizel', 'Manuelni'),
(5, 'Hyundai', 'i20', 'Benzin', 'Automatski');



DROP TABLE IF EXISTS `InstruktorTipAutomobila`;

CREATE TABLE `InstruktorTipAutomobila` (
  `TipAutomobilaID` BIGINT(10) UNSIGNED NOT NULL,
  `InstruktorID` BIGINT(10) UNSIGNED NOT NULL,
  `DatumIzdavanja` DATE NOT NULL,
  PRIMARY KEY (`TipAutomobilaID`, `InstruktorID`),
  CONSTRAINT `fk_tipID_id` FOREIGN KEY (`TipAutomobilaID`) REFERENCES `TipAutomobila` (`TipAutomobilaID`),
  CONSTRAINT `fk_instr_id2` FOREIGN KEY (`InstruktorID`) REFERENCES `Instruktor` (`InstruktorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `InstruktorTipAutomobila` VALUES
(2, 1, '2024-01-15');


DROP TABLE IF EXISTS `KategorijaVozackeDozvole`;

CREATE TABLE `KategorijaVozackeDozvole` (
  `KategorijaVozackeDozvoleID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  `TipVozila` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`KategorijaVozackeDozvoleID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `KategorijaVozackeDozvole` VALUES
(1, 'B', 'Putnicko vozilo'),
(2, 'C', 'Kamion'),
(3, 'A', 'Motocikl');


DROP TABLE IF EXISTS `Ucenik`;

CREATE TABLE `Ucenik` (
  `UcenikID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(30) NOT NULL,
  `KategorijaVozackeDozvoleID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`UcenikID`),
  CONSTRAINT `fk_kat_id` FOREIGN KEY (`KategorijaVozackeDozvoleID`) REFERENCES `KategorijaVozackeDozvole` (`KategorijaVozackeDozvoleID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Ucenik` VALUES 
(1,'Dejana','Savatic','dejana@gmail.com', '0623721283', 1),
(2,'Hristina','Ristic','ina@gmail.com', '0662721823', 2),
(3,'Milica','Vuckovic','milica@gmail.com', '0621738219', 3);



DROP TABLE IF EXISTS `PlanCasa`;

CREATE TABLE `PlanCasa` (
  `PlanCasaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Opis` VARCHAR(200) NOT NULL,
  `TrajanjeUSatima` DECIMAL(10,2) NOT NULL,
  `Lokacija` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`PlanCasaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `PlanCasa` VALUES
(1, 'Upoznavanje sa vozilom i osnovama vožnje', 1.50, 'Beograd - Zvezdara'),
(2, 'Vožnja u gradskoj vožnji', 2.00, 'Beograd - Novi Beograd'),
(3, 'Noćna vožnja i autoput', 2.50, 'Beograd - Autoput');



DROP TABLE IF EXISTS `EvidencijaVoznje`;

CREATE TABLE `EvidencijaVoznje` (
  `EvidencijaVoznjeID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `UkupanIznos` DECIMAL(10,2) NOT NULL,
  `Polozeno` TINYINT(1) NOT NULL,
  `BrojCasova` INT(7) NOT NULL,
  `Zavrseno` TINYINT(1) NOT NULL,
  `UcenikID` BIGINT(10) UNSIGNED NOT NULL,
  `InstruktorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`EvidencijaVoznjeID`),
  CONSTRAINT `fk_ucenik_id` FOREIGN KEY (`UcenikID`) REFERENCES `Ucenik` (`UcenikID`),
  CONSTRAINT `fk_instruktor_id` FOREIGN KEY (`InstruktorID`) REFERENCES `Instruktor` (`InstruktorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT INTO `EvidencijaVoznje` (`EvidencijaVoznjeID`, `UkupanIznos`, `Polozeno`, `BrojCasova`, `Zavrseno`, `UcenikID`, `InstruktorID`) VALUES
(1, 6000.00, 0, 2, 0, 1, 1),
(2, 9500.00, 1, 3, 1, 2, 1),
(3, 16000.00, 1, 4, 1, 3, 1);



DROP TABLE IF EXISTS `StavkaEvidencije`;

CREATE TABLE `StavkaEvidencije` (
  `EvidencijaVoznjeID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `DatumCasa` DATE NOT NULL,
  `TrajanjeUSatima` DECIMAL(10,2) NOT NULL,
  `TipVoznje` VARCHAR(50) NOT NULL,
  `CenaCasa` DECIMAL(10,2) NOT NULL,
  `CenaPoSatu` DECIMAL(10,2) NOT NULL,
  `PlanCasaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`EvidencijaVoznjeID`,`Rb`),
  CONSTRAINT `fk_ev_id` FOREIGN KEY (`EvidencijaVoznjeID`) REFERENCES `EvidencijaVoznje` (`EvidencijaVoznjeID`) ON DELETE CASCADE,
  CONSTRAINT `fk_pc_id` FOREIGN KEY (`PlanCasaID`) REFERENCES `PlanCasa` (`PlanCasaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `StavkaEvidencije` (`EvidencijaVoznjeID`, `Rb`, `DatumCasa`, `TrajanjeUSatima`, `TipVoznje`, `CenaCasa`, `CenaPoSatu`, `PlanCasaID`) VALUES
(1, 1, 20240701, 1.00, 'Uvodni čas', 2000.00, 2000.00, 1),
(1, 2, 20240703, 1.00, 'Osnovna vožnja', 2000.00, 2000.00, 2),
(2, 1, 20240628, 1.00, 'Gradska vožnja', 2500.00, 2500.00, 2),
(2, 2, 20240701, 1.00, 'Gradska vožnja - nastava', 2500.00, 2500.00, 2),
(2, 3, 20240705, 1.00, 'Parkiranje i veštine', 4500.00, 4500.00, 3),
(3, 1, 20240701, 1.00, 'Vožnja autoput', 4000.00, 4000.00, 3),
(3, 2, 20240703, 1.00, 'Noćna vožnja', 4000.00, 4000.00, 3),
(3, 3, 20240705, 1.00, 'Gradska gužva', 4000.00, 4000.00, 2),
(3, 4, 20240706, 1.00, 'Završna proba', 4000.00, 4000.00, 1);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
