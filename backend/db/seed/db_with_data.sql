-- Adminer 4.7.3 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

CREATE DATABASE `medsec` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `medsec`;

DROP TABLE IF EXISTS `Appointment`;
CREATE TABLE `Appointment` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `date_create` datetime NOT NULL,
  `date_change` datetime DEFAULT NULL,
  `date` datetime NOT NULL,
  `duration` int(45) NOT NULL,
  `detail` longtext DEFAULT NULL,
  `note` longtext DEFAULT NULL,
  `user_note` longtext DEFAULT NULL,
  `status` enum('UNCONFIRMED','CONFIRMED','CANCELLED') DEFAULT 'UNCONFIRMED',
  PRIMARY KEY (`id`),
  KEY `fk_Appointment_Patient1_idx` (`uid`),
  CONSTRAINT `Appointment_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Appointment` (`id`, `uid`, `title`, `date_create`, `date_change`, `date`, `duration`, `detail`, `note`, `user_note`, `status`) VALUES
(1,	1,	'Day Oncology Unit',	'2020-05-16 05:23:41',	'2020-05-16 05:23:41',	'2020-06-11 14:02:00',	60,	'Education session',	'Looking after yourself during chemotherapy - Watch Patient Health History Sheet - Please fill in and email back to daychemo.wrp@ramsayhealth.com.au Parking Information - ReadQuestions Sheet - Read',	NULL,	'CONFIRMED'),
(2,	1,	'Day Oncology Unit',	'2020-05-16 05:23:41',	'2020-05-16 05:23:41',	'2020-07-08 14:02:00',	60,	'Education session',	'Looking after yourself during chemotherapy - Watch Patient Health History Sheet - Please fill in and email back to daychemo.wrp@ramsayhealth.com.au Parking Information - ReadQuestions Sheet - Read',	NULL,	'UNCONFIRMED'),
(3,	1,	'Pathology',	'2020-05-16 05:23:41',	'2018-05-16 05:23:41',	'2020-06-08 14:02:00',	60,	'Education session',	'Education.',	NULL,	'UNCONFIRMED'),
(4,	5,	'Appoinment',	'2020-04-20 08:26:10',	NULL,	'2020-07-20 08:26:10',	30,	'Test',	'Test.',	NULL,	'UNCONFIRMED');

DROP TABLE IF EXISTS `Doctor`;
CREATE TABLE `Doctor` (
  `id` int(11) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `fax` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `website` varchar(45) DEFAULT NULL,
  `expertise` mediumtext NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Doctor` (`id`, `contact`, `address`, `fax`, `email`, `website`, `expertise`, `name`) VALUES
(1,	'555',	'14 Fake st',	'',	'doctor@doctor.com',	NULL,	'Radiology',	'Callum'),
(2,	'555',	'16 tardis street',	'555',	'timelord_01@Gallifrey',	'test.com',	'Electronic screwdriver',	'Dr. Who'),
(3,	'655',	'Bond st ',	'555',	'Drno@gmail.com',	'Www.no.com',	'Lasers, sharks',	'Dr. No'),
(5,	'55',	'Fgjgd',	'555',	'Gdshh',	'Gfdf',	'Cgjh',	'Gfdh');

DROP TABLE IF EXISTS `File`;
CREATE TABLE `File` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `link` varchar(45) NOT NULL,
  `apptid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `apptid` (`apptid`),
  CONSTRAINT `File_ibfk_2` FOREIGN KEY (`apptid`) REFERENCES `Appointment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `Hospital`;
CREATE TABLE `Hospital` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `fax` varchar(45) NOT NULL,
  `website` varchar(45) DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Hospital` (`id`, `name`, `contact`, `address`, `fax`, `website`, `type`) VALUES
(1,	'Harry',	'123456',	'Melbourne',	'123456',	'www.unimelb.edu.au',	'doctor');

DROP TABLE IF EXISTS `NotificationToken`;
CREATE TABLE `NotificationToken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `fcm_token` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid_fcm_token` (`uid`,`fcm_token`),
  KEY `fk_NotificationToken_User_idx` (`uid`),
  CONSTRAINT `NotificationToken_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `NotificationToken` (`id`, `uid`, `fcm_token`) VALUES
(48,	1,	'c7YzpQUQNS0:APA91bFMJ_3BRweXo__qhrQQ5pwXwnJbtlhtqJbu9HAU6LBrFyT6IO7TcQB1HNfvbUDMvt0hkk5WHEqcDrzUFORISASpZh34hPpUCVQ5Od0XEfiv31NAR3Ub0BfmOxdbvPk5xt01ia7a'),
(60,	1,	'cCGT0Ormh00:APA91bEa2lO4G4ZuV2YUWetwBl_rdFXtz1PT5PEj-nK02ddFArfH_B0ikwRbrFbniX-xvIvTqaw3973u5G_knjhzDt_aUBr2jG0gMBOLKA8sL73PisBJciqx0rs3dQ4KVqL-FkrcOJ5T'),
(80,	1,	'cMTb4GEv1aI:APA91bEptgtlZKCrzse3-3pxPxeRtuDG60SdQ3uDOEKiodBMp93HbmXBVBCr350DoBthwJam3SPWVC5cgheKpV_JW3ZkSXSndVWT9RgUp1YDX4Fb07OOVa5M5Mlnj0Ylzdby6cjWwaP6'),
(81,	5,	'cMTb4GEv1aI:APA91bEptgtlZKCrzse3-3pxPxeRtuDG60SdQ3uDOEKiodBMp93HbmXBVBCr350DoBthwJam3SPWVC5cgheKpV_JW3ZkSXSndVWT9RgUp1YDX4Fb07OOVa5M5Mlnj0Ylzdby6cjWwaP6'),
(45,	5,	'dYdFwGvckS4:APA91bFsMvl_sNQ4Wd9_DQITMVWNwHjhN9BFCtzi1NwvuQOjn4bz_vwpHgyPKlWnF3PbGWohzasSOdfD0CkA8gdCYLlaFvkS6F_8QgVEbDRfQhZpaCJLcUKYeRFAVxlTfqIMQk6X2i8i'),
(47,	6,	'dYdFwGvckS4:APA91bFsMvl_sNQ4Wd9_DQITMVWNwHjhN9BFCtzi1NwvuQOjn4bz_vwpHgyPKlWnF3PbGWohzasSOdfD0CkA8gdCYLlaFvkS6F_8QgVEbDRfQhZpaCJLcUKYeRFAVxlTfqIMQk6X2i8i');

DROP TABLE IF EXISTS `Pathology`;
CREATE TABLE `Pathology` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `fax` varchar(45) NOT NULL,
  `website` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Pathology` (`id`, `name`, `contact`, `address`, `fax`, `website`) VALUES
(1,	'Dorevitch Pathology',	'0394572200',	'66 Darebin Street Heidelberg VIC 3084',	'',	'www.dorevitch.com.au/patients/find-a-collection-centre/'),
(2,	'Mack',	'Carlton',	'123',	'123',	'www.google.com'),
(3,	'Jack',	'North Melbourne',	'33333',	'333',	'www.google.com');

DROP TABLE IF EXISTS `Radiology`;
CREATE TABLE `Radiology` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `fax` varchar(45) NOT NULL,
  `website` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Radiology` (`id`, `name`, `contact`, `address`, `fax`, `website`) VALUES
(1,	'1-MED',	'0394501800',	'level1/10 Martin St, Heigelberg VIC 3084',	'0394501888',	'i-med.com.au/clinics/clinic/Heidelberg');

DROP TABLE IF EXISTS `Resource`;
CREATE TABLE `Resource` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `website` varchar(45) NOT NULL,
  KEY `uid` (`uid`),
  CONSTRAINT `Resource_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Resource` (`id`, `uid`, `name`, `website`) VALUES
(1,	1,	'Download',	'www.google.com');

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `password` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `middlename` varchar(45) DEFAULT NULL,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `street` varchar(45) DEFAULT NULL,
  `suburb` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_expire_date` datetime DEFAULT NULL,
  `token_valid_from` datetime DEFAULT NULL,
  `role` enum('PATIENT','ADMIN') DEFAULT 'PATIENT',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `User` (`password`, `id`, `surname`, `firstname`, `middlename`, `dob`, `email`, `street`, `suburb`, `state`, `token`, `token_expire_date`, `token_valid_from`, `role`) VALUES
('123',	1,	'Williamson',	'Alex',	'Mileston',	'1986-07-22',	'williamson@example.com',	'97 Masthead Drive',	'ROCKHAMPTON',	'QLD',	'eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUEFUSUVOVCIsImp0aSI6ImlxYXM0bWNwanM3bG90bnM1YXJxbG1wZnJqIiwiZXhwIjoxNTkxMzM2MzAwLCJpYXQiOjE1OTEyNDk5MDAsInN1YiI6IjEifQ.vPNi7SOX-VKpZAzg04EXIYtmZqiqC6vnJngDHe4C0fA5jQXXwjgkLh-P0-Uog50GQUdyssTMHhqumLd6hAp8Ug',	'2020-06-05 05:51:40',	'2020-06-04 05:51:40',	'PATIENT'),
('1230',	2,	'Maggard',	'Arnold',	'Logan',	'1968-02-10',	'arnold@example.com',	'42 Edgewater Close',	'HUSKISSON',	'NSW',	NULL,	NULL,	'2018-08-09 13:09:29',	'ADMIN'),
(NULL,	3,	'Sharpe',	'Chad',	NULL,	'1979-08-03',	'chad@example.com',	'41 Ross Street',	NULL,	NULL,	NULL,	NULL,	NULL,	'PATIENT'),
(NULL,	4,	'Haggerty',	'Susan',	NULL,	'1994-01-08',	'susan@example.com',	NULL,	NULL,	NULL,	NULL,	NULL,	'2018-08-04 14:05:19',	'PATIENT'),
('1',	5,	'Dowling',	'Callum',	NULL,	'1991-01-25',	'callum.dowling@gmail.com',	NULL,	NULL,	NULL,	'eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUEFUSUVOVCIsImp0aSI6InRpdHRnYWQ2c2NuOHJhMXYxOG1yN25qYjE3IiwiZXhwIjoxNTkxMzE1ODYyLCJpYXQiOjE1OTEyMjk0NjIsInN1YiI6IjUifQ.c3ljk1av8pClXVedfjF4Lxd9weoWX6Hvl-PTV-hubNp19NszhKiH-afb43q2cCe0RhLDLj_yBpbfnP5O-wtl_A',	'2020-06-05 00:11:02',	'2020-06-04 00:11:02',	'PATIENT'),
('ggg',	6,	'test',	'test',	'test',	'2019-10-09',	'test@test.com',	NULL,	NULL,	NULL,	'eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUEFUSUVOVCIsImp0aSI6ImRnMmJyZjRvbnFhZ3JhMW9tc2VxNDNkYzlrIiwiZXhwIjoxNTcwNzIwMDQ2LCJpYXQiOjE1NzA2MzM2NDYsInN1YiI6IjYifQ.tJNNP26dCI4k8jyEYytaZVhQr2OxpP6h354DZZ_yvmsDPeF1rx-itajViUHarT0X6V_r14QiQqz-_xzGYesL1Q',	'2019-10-10 15:07:26',	'2019-10-09 15:07:26',	'PATIENT');

-- 2020-06-10 16:55:50