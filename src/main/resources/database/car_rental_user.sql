-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: car_rental
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nid` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'123 Main St, Dhaka','1998-05-15','johndoe@example.com','Male','John Doe','1234567890123','password123','01712345678','Client'),(2,'Banani, Dhaka','2000-04-07','nusrat@gmail.com','Female','Nusrat Sharmin','1234567890124','password123','01832354239','Client'),(3,'124 Main St','1990-01-01','johnsmith@example.com','Male','John Smith','987654321','password980','1234567892','Server'),(4,'Jamalkhan, Chittagong','2002-05-20','isnatsamiims7@gmail.com','female','Isnat Mehrin Sami','1234567890456','abcdefgh','01858050856','client'),(5,'Ashkar Dighi, Chittagong','2003-01-17','priyo@gmail.com','female','Priyontee ','1234567890789','ABCDEFGH','01782090457','driver'),(6,'Chawkbazar, Chittagong','2002-06-04','ruhi@gmail.com','female','Shahrin Afroz','534561230097','RUHIruhi','01874509459','client'),(7,'Kazir Dewri, Chittagong','2002-06-03','abir@gmail.com','male','Abir Mahabub','534561230099','therider123','01768090678','server'),(8,'Panchlaish, Chittagong','2000-09-09','sharmin@gmail.com','female','Nusrat Sharmin','524751220098','queueINtheline123','01842113759','client'),(9,'Lalkhan Bazar, Chittagong','1999-07-08','janet22@yahoo.com','female','Janet Van Dyne','1234567890456','thisiscarrental123','01785385500','client'),(10,'Halishahar, Chittagong','2000-02-05','fatima345@yahoo.com','female','Fatima Hossain','764561330199','carRental12389','01311157838','server');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-31 18:03:56
