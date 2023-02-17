CREATE DATABASE  IF NOT EXISTS `rbc_stock` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rbc_stock`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rbc_stock
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `stock_prediction_data`
--

DROP TABLE IF EXISTS `stock_prediction_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_prediction_data` (
  `quarter` int(11) DEFAULT NULL,
  `stock` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `open` decimal(10,0) DEFAULT NULL,
  `high` decimal(10,0) DEFAULT NULL,
  `low` decimal(10,0) DEFAULT NULL,
  `close` decimal(10,0) DEFAULT NULL,
  `volume` bigint(20) DEFAULT NULL,
  `percent_change_price` double DEFAULT NULL,
  `percent_change_volume_over_last_week` double DEFAULT NULL,
  `previous_weeks_volume` bigint(20) DEFAULT NULL,
  `next_weeks_open` decimal(10,0) DEFAULT NULL,
  `next_weeks_close` decimal(10,0) DEFAULT NULL,
  `percent_change_next_weeks_price` double DEFAULT NULL,
  `days_to_next_dividend` int(11) DEFAULT NULL,
  `percent_return_next_dividend` double DEFAULT NULL,
  PRIMARY KEY (`stock`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16 17:38:04
