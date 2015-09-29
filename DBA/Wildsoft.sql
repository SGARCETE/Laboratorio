CREATE DATABASE  IF NOT EXISTS `wildsoft2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wildsoft2`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: wildsoft2
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_mp`
--

LOCK TABLES `categoria_mp` WRITE;
/*!40000 ALTER TABLE `categoria_mp` DISABLE KEYS */;
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
  PRIMARY KEY (`CL_id`),
  UNIQUE KEY `CL_direccion` (`CL_direccion`,`CL_telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo`
--

DROP TABLE IF EXISTS `combo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combo` (
  `CO_id` int(11) NOT NULL AUTO_INCREMENT,
  `CO_nombre` varchar(200) DEFAULT NULL,
  `CO_precio` int(11) DEFAULT NULL,
  PRIMARY KEY (`CO_id`),
  UNIQUE KEY `CO_nombre` (`CO_nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo`
--

LOCK TABLES `combo` WRITE;
/*!40000 ALTER TABLE `combo` DISABLE KEYS */;
/*!40000 ALTER TABLE `combo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo_pedido`
--

DROP TABLE IF EXISTS `combo_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combo_pedido` (
  `CP_pedidoid` int(11) NOT NULL AUTO_INCREMENT,
  `CP_comboid` int(11) DEFAULT NULL,
  KEY `CP_pedidoid` (`CP_pedidoid`),
  KEY `CP_comboid` (`CP_comboid`),
  CONSTRAINT `combo_pedido_ibfk_1` FOREIGN KEY (`CP_pedidoid`) REFERENCES `pedido` (`PD_id`),
  CONSTRAINT `combo_pedido_ibfk_2` FOREIGN KEY (`CP_comboid`) REFERENCES `combo` (`CO_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo_pedido`
--

LOCK TABLES `combo_pedido` WRITE;
/*!40000 ALTER TABLE `combo_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `combo_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra_materiaprima`
--

DROP TABLE IF EXISTS `compra_materiaprima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra_materiaprima` (
  `CM_compra` int(11) DEFAULT NULL,
  `CM_materia_prima` int(11) DEFAULT NULL,
  KEY `CM_compra` (`CM_compra`),
  KEY `CM_materia_prima` (`CM_materia_prima`),
  CONSTRAINT `compra_materiaprima_ibfk_1` FOREIGN KEY (`CM_compra`) REFERENCES `solicitud_compra` (`SD_id`),
  CONSTRAINT `compra_materiaprima_ibfk_2` FOREIGN KEY (`CM_materia_prima`) REFERENCES `materia_prima` (`MP_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra_materiaprima`
--

LOCK TABLES `compra_materiaprima` WRITE;
/*!40000 ALTER TABLE `compra_materiaprima` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra_materiaprima` ENABLE KEYS */;
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
-- Table structure for table `materia_prima`
--

DROP TABLE IF EXISTS `materia_prima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materia_prima` (
  `MP_id` int(11) NOT NULL AUTO_INCREMENT,
  `MP_nombre` varchar(50) DEFAULT NULL,
  `MP_fecha_vencimiento` date DEFAULT NULL,
  `MP_categoria` int(11) DEFAULT NULL,
  `MP_Proveedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`MP_id`),
  UNIQUE KEY `MP_nombre` (`MP_nombre`),
  KEY `MP_categoria` (`MP_categoria`),
  CONSTRAINT `materia_prima_ibfk_1` FOREIGN KEY (`MP_categoria`) REFERENCES `categoria_mp` (`CA_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia_prima`
--

LOCK TABLES `materia_prima` WRITE;
/*!40000 ALTER TABLE `materia_prima` DISABLE KEYS */;
/*!40000 ALTER TABLE `materia_prima` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pe_estado`
--

LOCK TABLES `pe_estado` WRITE;
/*!40000 ALTER TABLE `pe_estado` DISABLE KEYS */;
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
  `PD_total` float DEFAULT NULL,
  `PD_subtotal` float DEFAULT NULL,
  `PD_fecha_pedido` datetime DEFAULT NULL,
  `PD_observacion` varchar(250) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
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
  `PR_precio` int(11) DEFAULT NULL,
  `PR_tipo_producto` int(11) DEFAULT NULL,
  PRIMARY KEY (`PR_id`),
  UNIQUE KEY `PR_nombre` (`PR_nombre`),
  KEY `PR_tipo_producto` (`PR_tipo_producto`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`PR_tipo_producto`) REFERENCES `tipo_producto` (`TP_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `producto_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `PV_id` int(11) NOT NULL AUTO_INCREMENT,
  `PV_nombre` varchar(30) DEFAULT NULL,
  `PV_direccion` varchar(50) DEFAULT NULL,
  `PV_mail` varchar(30) DEFAULT NULL,
  `PV_telefono` varchar(30) DEFAULT NULL,
  `PV_categoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`PV_id`),
  UNIQUE KEY `PV_nombre` (`PV_nombre`,`PV_categoria`),
  KEY `PV_categoria` (`PV_categoria`),
  CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`PV_categoria`) REFERENCES `categoria_mp` (`CA_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor_materia_prima`
--

DROP TABLE IF EXISTS `proveedor_materia_prima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor_materia_prima` (
  `PM_Proveedor` int(11) DEFAULT NULL,
  `PM_materia_prima` int(11) DEFAULT NULL,
  KEY `PM_Proveedor` (`PM_Proveedor`),
  KEY `PM_materia_prima` (`PM_materia_prima`),
  CONSTRAINT `proveedor_materia_prima_ibfk_1` FOREIGN KEY (`PM_Proveedor`) REFERENCES `proveedor` (`PV_id`),
  CONSTRAINT `proveedor_materia_prima_ibfk_2` FOREIGN KEY (`PM_materia_prima`) REFERENCES `materia_prima` (`MP_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor_materia_prima`
--

LOCK TABLES `proveedor_materia_prima` WRITE;
/*!40000 ALTER TABLE `proveedor_materia_prima` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor_materia_prima` ENABLE KEYS */;
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
  `RE_estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`RE_id`),
  UNIQUE KEY `RE_nombre` (`RE_nombre`,`RE_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repartidor`
--

LOCK TABLES `repartidor` WRITE;
/*!40000 ALTER TABLE `repartidor` DISABLE KEYS */;
/*!40000 ALTER TABLE `repartidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_compra`
--

DROP TABLE IF EXISTS `solicitud_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitud_compra` (
  `SD_id` int(11) NOT NULL AUTO_INCREMENT,
  `SD_estado` int(11) DEFAULT NULL,
  `SD_proveedor` int(11) DEFAULT NULL,
  `SD_fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`SD_id`),
  KEY `SD_proveedor` (`SD_proveedor`),
  KEY `SD_estado` (`SD_estado`),
  CONSTRAINT `solicitud_compra_ibfk_1` FOREIGN KEY (`SD_proveedor`) REFERENCES `proveedor` (`PV_id`),
  CONSTRAINT `solicitud_compra_ibfk_2` FOREIGN KEY (`SD_estado`) REFERENCES `solicitud_estado` (`SEST_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_compra`
--

LOCK TABLES `solicitud_compra` WRITE;
/*!40000 ALTER TABLE `solicitud_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitud_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_estado`
--

DROP TABLE IF EXISTS `solicitud_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitud_estado` (
  `SEST_id` int(11) NOT NULL AUTO_INCREMENT,
  `SEST_nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`SEST_id`),
  UNIQUE KEY `SEST_nombre` (`SEST_nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_estado`
--

LOCK TABLES `solicitud_estado` WRITE;
/*!40000 ALTER TABLE `solicitud_estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitud_estado` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'Bebida');
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

-- Dump completed on 2015-09-29 19:24:19
