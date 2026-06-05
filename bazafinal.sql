/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - nst2025
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nst2025` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `nst2025`;

/*Table structure for table `databasechangelog` */

DROP TABLE IF EXISTS `databasechangelog`;

CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `databasechangelog` */

insert  into `databasechangelog`(`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`,`DEPLOYMENT_ID`) values 
('add-pokrivenostNastave','MarijaMilenkovic','liquibase/mysql/setup/db-changelog-setup.xml','2026-01-29 22:27:49',1,'EXECUTED','9:a0db9590fe0c6941b5c01002c4641b86','sqlFile path=dml/add-pokrivenostNastave.sql','',NULL,'4.27.0',NULL,NULL,'9722069722');

/*Table structure for table `databasechangeloglock` */

DROP TABLE IF EXISTS `databasechangeloglock`;

CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` tinyint(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `databasechangeloglock` */

insert  into `databasechangeloglock`(`ID`,`LOCKED`,`LOCKGRANTED`,`LOCKEDBY`) values 
(1,0,NULL,NULL);

/*Table structure for table `korisnickiprofil` */

DROP TABLE IF EXISTS `korisnickiprofil`;

CREATE TABLE `korisnickiprofil` (
  `korisnickiProfilID` bigint(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `lozinka` varchar(100) NOT NULL,
  `ulogaID` bigint(10) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `nastavnikID` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`korisnickiProfilID`),
  KEY `fk_korisnik_uloga` (`ulogaID`),
  KEY `fk_nastavnik` (`nastavnikID`),
  CONSTRAINT `fk_korisnik_uloga` FOREIGN KEY (`ulogaID`) REFERENCES `uloga` (`ulogaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_nastavnik` FOREIGN KEY (`nastavnikID`) REFERENCES `nastavnik` (`nastavnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `korisnickiprofil` */

insert  into `korisnickiprofil`(`korisnickiProfilID`,`email`,`lozinka`,`ulogaID`,`enabled`,`nastavnikID`) values 
(1,'ilija.antovic@fon.bg.ac.rs','Ilija88!',2,1,1),
(2,'marija.boricic.joksimovic@fon.bg.ac.rs','Marija88!',2,1,2),
(3,'devedzic.vladan@fon.bg.ac.rs','Vladan88!',2,1,3),
(4,'dzamic.dusan@fon.bg.ac.rs','Dusan88!',2,1,4),
(5,'sinisa.vlajic@fon.bg.ac.rs','Sinisa88!',2,1,5),
(6,'dusan.savic@fon.bg.ac.rs','Dusan88!',1,1,6),
(12,'jovanovic.jelena@fon.bg.ac.rs','Jelena88!',1,1,12),
(13,'lazarevic.sasa@fon.bg.ac.rs','Sasa888!',2,1,13),
(14,'mihic.olivera@fon.bg.ac.rs','Olivera88!',2,1,15),
(21,'milos.milic@fon.bg.ac.rs','Milos88!',2,1,16),
(22,'stojanovic.milica@fon.bg.ac.rs','Milica88!',2,1,17),
(24,'todorcevic.vesna@fon.bg.ac.rs','Vesna88!',2,1,18),
(26,'djuric.dragan@fon.bg.ac.rs','Dragan88!',2,1,24),
(27,'sevarac.zoran@fon.bg.ac.rs','Zoran88!',2,1,25),
(28,'tomic.bojan@fon.bg.ac.rs','Bojan88!',2,1,27);

/*Table structure for table `nastavnik` */

DROP TABLE IF EXISTS `nastavnik`;

CREATE TABLE `nastavnik` (
  `nastavnikID` bigint(10) NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `zvanjeID` bigint(10) NOT NULL,
  `korisnickiProfilID` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`nastavnikID`),
  KEY `fk_nastavnik_zvanje` (`zvanjeID`),
  KEY `fk_nastavnik_korisnik` (`korisnickiProfilID`),
  CONSTRAINT `fk_nastavnik_korisnik` FOREIGN KEY (`korisnickiProfilID`) REFERENCES `korisnickiprofil` (`korisnickiProfilID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_nastavnik_zvanje` FOREIGN KEY (`zvanjeID`) REFERENCES `zvanje` (`zvanjeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `nastavnik` */

insert  into `nastavnik`(`nastavnikID`,`ime`,`prezime`,`zvanjeID`,`korisnickiProfilID`) values 
(1,'Ilija','Antovic',4,1),
(2,'Marija','Boricic',4,2),
(3,'Vladan','Devedzic',4,3),
(4,'Dusan','Dzamic',4,4),
(5,'Sinisa','Vlajic',4,5),
(6,'Dusan','Savic',3,6),
(12,'Jelena','Jovanovic',4,12),
(13,'Sasa','Lazarevic',4,13),
(15,'Olivera','Mihic',4,14),
(16,'Milos','Milic',1,21),
(17,'Milica','Stojanovic',1,22),
(18,'Vesna','Todorcevic',1,24),
(24,'Dragan','Djuric',4,26),
(25,'Zoran','Sevarac',4,27),
(27,'Bojan','Tomic',4,28);

/*Table structure for table `nastavnikpredmet` */

DROP TABLE IF EXISTS `nastavnikpredmet`;

CREATE TABLE `nastavnikpredmet` (
  `nastavnikPredmetID` bigint(10) NOT NULL AUTO_INCREMENT,
  `predmetID` bigint(10) DEFAULT NULL,
  `nastavnikID` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`nastavnikPredmetID`),
  KEY `fk_predmet2` (`predmetID`),
  KEY `fk_nastavnik2` (`nastavnikID`),
  CONSTRAINT `fk_nastavnik2` FOREIGN KEY (`nastavnikID`) REFERENCES `nastavnik` (`nastavnikID`),
  CONSTRAINT `fk_predmet2` FOREIGN KEY (`predmetID`) REFERENCES `predmet` (`predmetID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `nastavnikpredmet` */

insert  into `nastavnikpredmet`(`nastavnikPredmetID`,`predmetID`,`nastavnikID`) values 
(8,1,1),
(9,1,6),
(10,1,13),
(11,4,24),
(12,4,25),
(13,4,27),
(14,2,15),
(15,2,5),
(16,3,27),
(17,5,13),
(18,11,2),
(19,11,17),
(20,11,15),
(21,6,25),
(22,6,24),
(23,12,15),
(24,12,4),
(25,7,12),
(26,7,3),
(27,7,25),
(28,8,6),
(29,8,12),
(30,9,5),
(31,9,16),
(32,21,17),
(33,21,18),
(34,10,13),
(35,10,16);

/*Table structure for table `obliknastave` */

DROP TABLE IF EXISTS `obliknastave`;

CREATE TABLE `obliknastave` (
  `oblikNastaveID` bigint(10) NOT NULL AUTO_INCREMENT,
  `tip` varchar(100) NOT NULL,
  PRIMARY KEY (`oblikNastaveID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `obliknastave` */

insert  into `obliknastave`(`oblikNastaveID`,`tip`) values 
(1,'Predavanja'),
(2,'Vezbe'),
(3,'Laboratorijske vezbe');

/*Table structure for table `pokrivenostnastave` */

DROP TABLE IF EXISTS `pokrivenostnastave`;

CREATE TABLE `pokrivenostnastave` (
  `pokrivenostNastaveID` bigint(10) NOT NULL AUTO_INCREMENT,
  `brojSatiNastave` int(11) NOT NULL,
  `predmetID` bigint(10) NOT NULL,
  `nastavnikID` bigint(10) NOT NULL,
  `oblikNastaveID` bigint(10) NOT NULL,
  `skolskaGodinaID` bigint(10) NOT NULL,
  PRIMARY KEY (`pokrivenostNastaveID`),
  UNIQUE KEY `uk_nastavnik_predmet_oblik_godina` (`nastavnikID`,`predmetID`,`oblikNastaveID`,`skolskaGodinaID`),
  KEY `fk_pn_predmet` (`predmetID`),
  KEY `fk_pn_oblik` (`oblikNastaveID`),
  KEY `fk_pn_godina` (`skolskaGodinaID`),
  CONSTRAINT `fk_pn_godina` FOREIGN KEY (`skolskaGodinaID`) REFERENCES `skolskagodina` (`skolskaGodinaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pn_nastavnik` FOREIGN KEY (`nastavnikID`) REFERENCES `nastavnik` (`nastavnikID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pn_oblik` FOREIGN KEY (`oblikNastaveID`) REFERENCES `obliknastave` (`oblikNastaveID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pn_predmet` FOREIGN KEY (`predmetID`) REFERENCES `predmet` (`predmetID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `pokrivenostnastave` */

insert  into `pokrivenostnastave`(`pokrivenostNastaveID`,`brojSatiNastave`,`predmetID`,`nastavnikID`,`oblikNastaveID`,`skolskaGodinaID`) values 
(21,4,1,1,1,1),
(22,6,1,6,1,1);

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `predmetID` bigint(10) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `brojEspb` int(10) NOT NULL,
  `fondPredavanja` int(11) NOT NULL DEFAULT 0,
  `fondVezbi` int(11) NOT NULL DEFAULT 0,
  `fondLabVezbi` int(11) NOT NULL DEFAULT 0,
  `aktivan` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`predmetID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `predmet` */

insert  into `predmet`(`predmetID`,`naziv`,`brojEspb`,`fondPredavanja`,`fondVezbi`,`fondLabVezbi`,`aktivan`) values 
(1,'Napredne softverske tehnologije',6,30,15,10,1),
(2,'Automatizacija razvoja softvera',6,25,20,5,1),
(3,'Ekspertni sistemi',6,30,20,0,1),
(4,'Alati i metode vestacke inteligencije i softverskog inzenjerstva',6,35,15,10,1),
(5,'Implementacioni idiomi',6,30,15,0,1),
(6,'Napredne neuronske mreze i duboko ucenje',6,25,20,5,1),
(7,'Primena vestacke inteligencije',6,30,20,5,1),
(8,'Softverski zahtevi',6,25,25,0,1),
(9,'Softverski proces',6,30,15,5,1),
(10,'Testiranje i performanse softvera',6,35,20,10,1),
(11,'Matematicke osnove vestacke inteligencije',6,30,15,5,1),
(12,'Numericka linearna algebra',6,30,25,5,1),
(21,'Teorija algoritama',6,60,30,0,1);

/*Table structure for table `skolskagodina` */

DROP TABLE IF EXISTS `skolskagodina`;

CREATE TABLE `skolskagodina` (
  `skolskaGodinaID` bigint(10) NOT NULL AUTO_INCREMENT,
  `godina` varchar(100) NOT NULL,
  PRIMARY KEY (`skolskaGodinaID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `skolskagodina` */

insert  into `skolskagodina`(`skolskaGodinaID`,`godina`) values 
(1,'2025/2026'),
(2,'2026/2027');

/*Table structure for table `uloga` */

DROP TABLE IF EXISTS `uloga`;

CREATE TABLE `uloga` (
  `ulogaID` bigint(10) NOT NULL AUTO_INCREMENT,
  `tip` varchar(100) NOT NULL,
  PRIMARY KEY (`ulogaID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `uloga` */

insert  into `uloga`(`ulogaID`,`tip`) values 
(1,'Administrator'),
(2,'Korisnik');

/*Table structure for table `verifikacija` */

DROP TABLE IF EXISTS `verifikacija`;

CREATE TABLE `verifikacija` (
  `verifikacijaID` bigint(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `kod` varchar(100) NOT NULL,
  `vreme` datetime NOT NULL,
  PRIMARY KEY (`verifikacijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `verifikacija` */

insert  into `verifikacija`(`verifikacijaID`,`email`,`kod`,`vreme`) values 
(2,'milena@fon.bg.ac.rs','a56dacc4-d54f-44de-8383-c7f9ccee3b63','2026-01-29 23:05:38'),
(4,'milena123@fon.bg.ac.rs','d198c9e1-f21f-44a7-b74c-c21cc9430ae4','2026-02-03 09:32:37');

/*Table structure for table `zvanje` */

DROP TABLE IF EXISTS `zvanje`;

CREATE TABLE `zvanje` (
  `zvanjeID` bigint(10) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  PRIMARY KEY (`zvanjeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `zvanje` */

insert  into `zvanje`(`zvanjeID`,`naziv`) values 
(1,'Asistent'),
(2,'Docent'),
(3,'Vanredni profesor'),
(4,'Redovni profesor');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
