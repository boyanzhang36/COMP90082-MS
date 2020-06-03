-- Adminer 4.7.3 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

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
(1,	1,	'Day Oncology Unit',	'2018-05-16 05:23:41',	'2018-05-16 05:23:41',	'2018-06-12 10:30:00',	60,	'Education Session',	'Looking after yourself during chemotherapy - Watch\nPatient Health History Sheet - Please fill in and email back to daychemo.wrD@ramsayhealth.com.au\nPharmacy Medication Sheet - Please fill in and email back to daychemo.wrp@ramsayhealth.com.au\nParking Information - ReadQuestions Sheet - Read',	'Remember to bring scan result.',	'CONFIRMED'),
(2,	1,	'Warringal Private Hospital / Epworth Eastern',	'2018-05-14 10:17:40',	'2018-05-14 10:17:40',	'2018-06-08 08:00:00',	10,	'Inflisaport Insertion',	'Warringal Private Hospital will contact you the day before to confirm admission and fasting times.\r\nInfusaport Questionnaire - Please fill in and send back to reception@.66darebinst.com.au\r\nDoctor Information - Read\r\nProcedure Information - Read\r\nAnaesthetists Information - Read',	NULL,	'CONFIRMED'),
(3,	1,	'Warringal Private Hospital / Epworth Eastern',	'2018-05-14 10:17:40',	'2018-05-14 10:17:40',	'2018-06-08 08:00:00',	10,	NULL,	NULL,	NULL,	'UNCONFIRMED'),
(4,	5,	'Appoinment',	'2019-09-20 08:26:10',	NULL,	'2019-09-20 08:26:10',	30,	'test',	NULL,	NULL,	'UNCONFIRMED'),
(11,	1,	'Day Oncology Unit',	'2018-05-16 05:23:41',	'2018-05-16 05:23:41',	'2018-06-11 14:02:00',	60,	'Education session',	'Looking after yourself during chemotherapy - Watch Patient Health History Sheet - Please fill in and email back to daychemo.wrp@ramsayhealth.com.au Parking Information - ReadQuestions Sheet - Read',	NULL,	'UNCONFIRMED');

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

INSERT INTO `File` (`id`, `title`, `link`, `apptid`) VALUES
(1,	'File-sample-1.pdf',	'/result/1/File-sample-1.pdf',	1);

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
(45,	5,	'dYdFwGvckS4:APA91bFsMvl_sNQ4Wd9_DQITMVWNwHjhN9BFCtzi1NwvuQOjn4bz_vwpHgyPKlWnF3PbGWohzasSOdfD0CkA8gdCYLlaFvkS6F_8QgVEbDRfQhZpaCJLcUKYeRFAVxlTfqIMQk6X2i8i'),
(47,	6,	'dYdFwGvckS4:APA91bFsMvl_sNQ4Wd9_DQITMVWNwHjhN9BFCtzi1NwvuQOjn4bz_vwpHgyPKlWnF3PbGWohzasSOdfD0CkA8gdCYLlaFvkS6F_8QgVEbDRfQhZpaCJLcUKYeRFAVxlTfqIMQk6X2i8i'),
(79,	7,	'c7YzpQUQNS0:APA91bFMJ_3BRweXo__qhrQQ5pwXwnJbtlhtqJbu9HAU6LBrFyT6IO7TcQB1HNfvbUDMvt0hkk5WHEqcDrzUFORISASpZh34hPpUCVQ5Od0XEfiv31NAR3Ub0BfmOxdbvPk5xt01ia7a');

DROP TABLE IF EXISTS `Pathology`;
CREATE TABLE `Pathology` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `fax` varchar(45) NOT NULL,
  `website` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Pathology` (`id`, `name`, `contact`, `address`, `fax`, `website`) VALUES
(1,	'Harry',	'123456',	'Melbourne',	'123456',	'www.unimelb.edu.au'),
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
(1,	'Harry',	'123456',	'Melbourne',	'123456',	'www.unimelb.edu.au');

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
('123',	1,	'Williamson',	'Alex',	'Mileston',	'1986-07-22',	'williamson@example.com',	'97 Masthead Drive',	'ROCKHAMPTON',	'QLD',	'eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUEFUSUVOVCIsImp0aSI6IjRtcG9sZDVmcnVoM2o0aDMwbTAxbmhiYnN0IiwiZXhwIjoxNTkxMTY1NjQyLCJpYXQiOjE1OTEwNzkyNDIsInN1YiI6IjEifQ.0yISp-oKZpuezNVMBU0QqNf4biKTJt5N-YRI86Gso2jPuutQ73BfAN8h9OqDDHOL15f9KDPdEBmdKufPjrpXKQ',	'2020-06-03 06:27:22',	'2020-06-02 06:27:22',	'PATIENT'),
('1230',	2,	'Maggard',	'Arnold',	'Logan',	'1968-02-10',	'arnold@example.com',	'42 Edgewater Close',	'HUSKISSON',	'NSW',	NULL,	NULL,	'2018-08-09 13:09:29',	'ADMIN'),
(NULL,	3,	'Sharpe',	'Chad',	NULL,	'1979-08-03',	'chad@example.com',	'41 Ross Street',	NULL,	NULL,	NULL,	NULL,	NULL,	'PATIENT'),
(NULL,	4,	'Haggerty',	'Susan',	NULL,	'1994-01-08',	'susan@example.com',	NULL,	NULL,	NULL,	NULL,	NULL,	'2018-08-04 14:05:19',	'PATIENT'),
('1',	5,	'Dowling',	'Callum',	NULL,	'1991-01-25',	'callum.dowling@gmail.com',	NULL,	NULL,	NULL,	'eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUEFUSUVOVCIsImp0aSI6Ijd0YmNtbnBhMWk4dGdqODUzNnJydjUwYmJnIiwiZXhwIjoxNTcwNzEzODEwLCJpYXQiOjE1NzA2Mjc0MTAsInN1YiI6IjUifQ.TwtoG1UZGOwOdTGYvY3a6RbCe1czXrAW9uIcNvTgEWuVLkNuOKD3iqx_TJozMQnsQi6M5hlW_vkXNGRe2DaBVw',	'2019-10-10 13:23:30',	'2019-10-09 13:23:30',	'PATIENT'),
('ggg',	6,	'test',	'test',	'test',	'2019-10-09',	'test@test.com',	NULL,	NULL,	NULL,	'eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUEFUSUVOVCIsImp0aSI6ImRnMmJyZjRvbnFhZ3JhMW9tc2VxNDNkYzlrIiwiZXhwIjoxNTcwNzIwMDQ2LCJpYXQiOjE1NzA2MzM2NDYsInN1YiI6IjYifQ.tJNNP26dCI4k8jyEYytaZVhQr2OxpP6h354DZZ_yvmsDPeF1rx-itajViUHarT0X6V_r14QiQqz-_xzGYesL1Q',	'2019-10-10 15:07:26',	'2019-10-09 15:07:26',	'PATIENT'),
('111',	7,	'Mack',	'Li',	NULL,	'1968-10-22',	'mack@example.com',	'608 Swantston st',	'Carlton',	'VIC',	'eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUEFUSUVOVCIsImp0aSI6InFybTZsdWxtYzdwODZxNDJyZ2k0dmNtYXYiLCJleHAiOjE1OTExNjIzNTksImlhdCI6MTU5MTA3NTk1OSwic3ViIjoiNyJ9.TQQEqmCOel56m0Q_FUzig45jDCTqAOp54clSaSFjKARFi6Ow8vtmqutJCmP1YjhXjyUk_7OWqXjew85HOV18Kg',	'2020-06-03 05:32:39',	'2020-06-02 05:32:39',	'PATIENT'),
(NULL,	8,	'Jack',	'Cheng',	NULL,	'1950-02-20',	'Jack@example.com',	'666 Kings st',	'Melbourne',	'VIC',	NULL,	NULL,	NULL,	'PATIENT');

-- 2020-06-02 09:33:10