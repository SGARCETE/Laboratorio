CREATE DATABASE  IF NOT EXISTS `wildsoft` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wildsoft`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: wildsoft
-- ------------------------------------------------------
-- Server version	5.7.4-m14

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
-- Table structure for table `categoria_mp`
--

DROP TABLE IF EXISTS `categoria_mp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_mp` (
  `CA_id` int(11) NOT NULL AUTO_INCREMENT,
  `CA_nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CA_id`),
  UNIQUE KEY `CA_nombre` (`CA_nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_mp`
--

LOCK TABLES `categoria_mp` WRITE;
/*!40000 ALTER TABLE `categoria_mp` DISABLE KEYS */;
INSERT INTO `categoria_mp` VALUES (3,'Almacen'),(2,'canicería'),(1,'Verdulería');
/*!40000 ALTER TABLE `categoria_mp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `CL_id` int(11) NOT NULL AUTO_INCREMENT,
  `CL_direccion` varchar(50) DEFAULT NULL,
  `CL_telefono` varchar(50) DEFAULT NULL,
  `CL_nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CL_id`),
  UNIQUE KEY `CL_direccion` (`CL_direccion`,`CL_telefono`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Vergara 1567','4456-9874','Javier Pérez'),(2,'Belgrano 100','4456-9292','Daniel Osvaldo'),(3,'Zapiola 1455','4555-2356','Pedro Suarez'),(4,'Zapiola 1454','4555-2356','Martin Safe');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ent_estado`
--

DROP TABLE IF EXISTS `ent_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ent_estado` (
  `ENTE_id` int(11) NOT NULL AUTO_INCREMENT,
  `ENTE_nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ENTE_id`),
  UNIQUE KEY `ENTE_nombre` (`ENTE_nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ent_estado`
--

LOCK TABLES `ent_estado` WRITE;
/*!40000 ALTER TABLE `ent_estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `ent_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrega` (
  `ENT_fecha_salida` datetime DEFAULT NULL,
  `ENT_estado` int(11) DEFAULT NULL,
  `ENT_repartidor` int(11) DEFAULT NULL,
  `ENT_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ENT_id`),
  KEY `ENT_estado` (`ENT_estado`),
  KEY `ENT_repartidor` (`ENT_repartidor`),
  CONSTRAINT `entrega_ibfk_1` FOREIGN KEY (`ENT_estado`) REFERENCES `ent_estado` (`ENTE_id`),
  CONSTRAINT `entrega_ibfk_2` FOREIGN KEY (`ENT_repartidor`) REFERENCES `repartidor` (`RE_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pe_estado`
--

DROP TABLE IF EXISTS `pe_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pe_estado` (
  `PEST_id` int(11) NOT NULL AUTO_INCREMENT,
  `PEST_nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`PEST_id`),
  UNIQUE KEY `PEST_nombre` (`PEST_nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pe_estado`
--

LOCK TABLES `pe_estado` WRITE;
/*!40000 ALTER TABLE `pe_estado` DISABLE KEYS */;
INSERT INTO `pe_estado` VALUES (5,'Cancelado'),(4,'Cobrado'),(3,'Enviado'),(1,'Pendiente'),(2,'Preparado');
/*!40000 ALTER TABLE `pe_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `PD_id` int(11) NOT NULL AUTO_INCREMENT,
  `PD_fecha_pedido` date DEFAULT NULL,
  `PD_estado` int(11) DEFAULT NULL,
  `PD_cliente` int(11) DEFAULT NULL,
  `PD_entrega` int(11) DEFAULT NULL,
  PRIMARY KEY (`PD_id`),
  KEY `PD_entrega` (`PD_entrega`),
  KEY `PD_estado` (`PD_estado`),
  KEY `PD_cliente` (`PD_cliente`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`PD_entrega`) REFERENCES `entrega` (`ENT_id`),
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`PD_estado`) REFERENCES `pe_estado` (`PEST_id`),
  CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`PD_cliente`) REFERENCES `cliente` (`CL_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2015-10-03',1,NULL,NULL),(2,'2015-10-03',1,NULL,NULL),(3,'2015-10-03',1,NULL,NULL),(4,'2015-10-03',1,NULL,NULL),(5,'2015-10-03',1,NULL,NULL),(6,'2015-10-03',1,NULL,NULL),(7,'2015-10-03',1,NULL,NULL),(8,'2015-10-03',1,NULL,NULL),(9,'2015-10-03',1,NULL,NULL),(10,'2015-10-03',1,NULL,NULL),(11,'2015-10-03',1,NULL,NULL),(12,'2015-10-03',1,NULL,NULL),(13,'2015-10-03',1,NULL,NULL),(14,'2015-10-03',1,NULL,NULL),(15,'2015-10-03',1,NULL,NULL),(16,'2015-10-03',1,NULL,NULL),(17,'2015-10-03',1,NULL,NULL),(18,'2015-10-04',1,NULL,NULL),(19,'2015-10-04',1,NULL,NULL),(20,'2015-10-04',1,NULL,NULL),(21,'2015-10-04',1,NULL,NULL);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `PR_id` int(11) NOT NULL AUTO_INCREMENT,
  `PR_nombre` varchar(30) DEFAULT NULL,
  `PR_precio` double DEFAULT NULL,
  `PR_observacion` varchar(300) DEFAULT NULL,
  `PR_tipo_producto` int(11) DEFAULT NULL,
  PRIMARY KEY (`PR_id`),
  UNIQUE KEY `PR_nombre` (`PR_nombre`,`PR_tipo_producto`),
  KEY `PR_tipo_producto` (`PR_tipo_producto`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`PR_tipo_producto`) REFERENCES `tipo_producto` (`TP_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Carne',12,NULL,1),(2,'Verdura',12,NULL,1),(3,'Humita',12,NULL,1),(4,'Pollo',10,NULL,1),(5,'Muzzarella',70,NULL,2),(6,'Jamón y queso',85,NULL,2),(7,'Jamón y morrones',105,NULL,2),(8,'Cochina',120,NULL,2),(9,'Coca Cola',40,NULL,3),(10,'Napolitana',90,NULL,2),(11,'Manaos',45,NULL,3),(12,'Sprite',95,NULL,3),(13,'Pepsi',70,NULL,3),(14,'Cocacola',45,NULL,3);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_pedidos`
--

DROP TABLE IF EXISTS `producto_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto_pedidos` (
  `PP_pedidoid` int(11) DEFAULT NULL,
  `PP_productoid` int(11) DEFAULT NULL,
  `PP_producto_cantidad` int(11) DEFAULT NULL,
  `PP_Observacion` varchar(300) DEFAULT NULL,
  `PP_precio` double DEFAULT NULL,
  KEY `PP_pedidoid` (`PP_pedidoid`),
  KEY `PP_productoid` (`PP_productoid`),
  CONSTRAINT `producto_pedidos_ibfk_1` FOREIGN KEY (`PP_pedidoid`) REFERENCES `pedido` (`PD_id`),
  CONSTRAINT `producto_pedidos_ibfk_2` FOREIGN KEY (`PP_productoid`) REFERENCES `pedido` (`PD_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_pedidos`
--

LOCK TABLES `producto_pedidos` WRITE;
/*!40000 ALTER TABLE `producto_pedidos` DISABLE KEYS */;
INSERT INTO `producto_pedidos` VALUES (3,1,1,' ',12),(5,1,1,' ',12),(6,1,1,' ',12),(7,5,1,' ',70),(7,5,1,' ',70),(9,1,1,' ',12),(9,1,1,' ',12),(9,1,1,' ',12),(12,1,1,' ',12),(12,1,1,' ',12),(13,2,1,' ',12),(13,8,1,' ',120),(13,8,1,' ',120),(14,12,1,' ',95),(14,12,1,' ',95),(15,9,1,' ',40),(16,8,1,' ',120),(17,5,1,' ',70),(18,7,1,' ',105),(18,4,1,' ',10),(18,4,1,' ',10),(19,7,1,' ',105),(20,9,1,' ',40),(20,1,1,' ',12),(20,1,1,' ',12),(20,1,1,' ',12),(20,1,1,' ',12),(20,1,1,' ',12),(21,5,1,' ',70),(21,12,1,' ',95);
/*!40000 ALTER TABLE `producto_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repartidor`
--

DROP TABLE IF EXISTS `repartidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repartidor` (
  `RE_id` int(11) NOT NULL AUTO_INCREMENT,
  `RE_nombre` varchar(50) DEFAULT NULL,
  `RE_vehiculo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`RE_id`),
  UNIQUE KEY `RE_nombre` (`RE_nombre`,`RE_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repartidor`
--

LOCK TABLES `repartidor` WRITE;
/*!40000 ALTER TABLE `repartidor` DISABLE KEYS */;
INSERT INTO `repartidor` VALUES (1,'Santiago','Moto'),(2,'ac ','b');
/*!40000 ALTER TABLE `repartidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_producto` (
  `TP_id` int(11) NOT NULL AUTO_INCREMENT,
  `TP_nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`TP_id`),
  UNIQUE KEY `TP_nombre` (`TP_nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (3,'Bebida'),(1,'Empanada'),(2,'Pizza');
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-04 16:28:16
