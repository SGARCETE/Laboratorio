-- MySQL dump 10.13  Distrib 5.7.4-m14, for Win64 (x86_64)
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
-- Current Database: `wildsoft`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `wildsoft` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wildsoft`;

--
-- Table structure for table `categoria_mp`
--

DROP TABLE IF EXISTS `categoria_mp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_mp` (
  `CA_id` int(11) NOT NULL AUTO_INCREMENT,
  `CA_nombre` varchar(50) DEFAULT NULL,
  `CA_vigente` tinyint(1) DEFAULT NULL,
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
  `CL_nombre` varchar(50) DEFAULT NULL,
  `CL_detalle` varchar(100) DEFAULT NULL,
  `CL_vigente` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`CL_id`),
  UNIQUE KEY `CL_direccion` (`CL_direccion`,`CL_telefono`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,NULL,NULL,'','',NULL),(2,'Real del Gasmi No. 635','45871020','Lucas Guaycochea','No anda timbre, golpear',NULL),(3,'Avenida Curquejo No. 190','49871120','Agustina De Napoli','Timbre A1 planta baja',NULL),(4,'Real del Mahdi No. 286','41984315','Maria Virginia Brassesco','Es en una empresa, dejar en recepcion',NULL),(5,'Boulevard Hayward No. 13','48135494','Alfonso Avila','',NULL),(6,'Privada Foradada No. 36','43169753','Marino Z. Sanchez','Es una oficina',NULL),(7,'Calle Boudlal No. 792','41585936','Enrique Sanchez Rico','',NULL),(8,'Yermolay No. 265','48135494','Samuel Mormeneo','Timbre de arriba, 2do piso',NULL),(9,'Comsa No. 375','48135494','Stella Maris','',NULL),(10,'Cerrada Orgiles No. 823','48135494','Sebastian Perez','',NULL),(11,'Santo del Ajbar No. 289','48135494','Jorge Bianchini','',NULL);
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
  `CO_precio` double DEFAULT NULL,
  `CO_vigente` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`CO_id`),
  UNIQUE KEY `CO_nombre` (`CO_nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo`
--

LOCK TABLES `combo` WRITE;
/*!40000 ALTER TABLE `combo` DISABLE KEYS */;
INSERT INTO `combo` VALUES (1,'Muzza + Coca',60,NULL),(2,'12 Emp. Carne + 1 muzza',145,NULL),(3,'Hamburguesa con fritas',35,NULL),(4,'Napolitana+Heineken',80,NULL),(5,'Fugazzeta+Imperial',75,NULL),(6,'Mila pollo+Fritas',35,NULL),(7,'Mila Carne+Fritas',35,NULL);
/*!40000 ALTER TABLE `combo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo_productos`
--

DROP TABLE IF EXISTS `combo_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combo_productos` (
  `COPRO_combo_id` int(11) DEFAULT NULL,
  `COPRO_producto_id` int(11) DEFAULT NULL,
  `COPRO_cantidad` int(11) DEFAULT NULL,
  `COPRO_Precio` double DEFAULT NULL,
  `COPRO_observacion` varchar(30) DEFAULT NULL,
  KEY `COPRO_combo_id` (`COPRO_combo_id`),
  KEY `COPRO_producto_id` (`COPRO_producto_id`),
  CONSTRAINT `combo_productos_ibfk_1` FOREIGN KEY (`COPRO_combo_id`) REFERENCES `combo` (`CO_id`),
  CONSTRAINT `combo_productos_ibfk_2` FOREIGN KEY (`COPRO_producto_id`) REFERENCES `producto` (`PR_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo_productos`
--

LOCK TABLES `combo_productos` WRITE;
/*!40000 ALTER TABLE `combo_productos` DISABLE KEYS */;
INSERT INTO `combo_productos` VALUES (1,20,1,NULL,NULL),(1,34,1,NULL,NULL),(2,1,12,NULL,NULL),(2,20,1,NULL,NULL),(3,60,1,NULL,NULL),(3,63,1,NULL,NULL),(4,21,1,NULL,NULL),(4,36,1,NULL,NULL),(5,15,1,NULL,NULL),(5,38,1,NULL,NULL),(6,57,1,NULL,NULL),(6,63,1,NULL,NULL),(7,56,1,NULL,NULL),(7,63,1,NULL,NULL);
/*!40000 ALTER TABLE `combo_productos` ENABLE KEYS */;
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
  `CM_cantidad_mp` int(11) DEFAULT NULL,
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
-- Table structure for table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrega` (
  `ENT_id` int(11) NOT NULL AUTO_INCREMENT,
  `ENT_fecha_salida` datetime DEFAULT NULL,
  `ENT_repartidor` int(11) DEFAULT NULL,
  PRIMARY KEY (`ENT_id`),
  KEY `ENT_repartidor` (`ENT_repartidor`),
  CONSTRAINT `entrega_ibfk_1` FOREIGN KEY (`ENT_repartidor`) REFERENCES `repartidor` (`RE_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
INSERT INTO `entrega` VALUES (1,'2015-11-29 00:00:00',1);
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega_pedido`
--

DROP TABLE IF EXISTS `entrega_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrega_pedido` (
  `EP_entrega_id` int(11) DEFAULT NULL,
  `EP_pedido_id` int(11) DEFAULT NULL,
  KEY `EP_entrega_id` (`EP_entrega_id`),
  KEY `EP_pedido_id` (`EP_pedido_id`),
  CONSTRAINT `entrega_pedido_ibfk_1` FOREIGN KEY (`EP_entrega_id`) REFERENCES `entrega` (`ENT_id`),
  CONSTRAINT `entrega_pedido_ibfk_2` FOREIGN KEY (`EP_pedido_id`) REFERENCES `pedido` (`PD_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega_pedido`
--

LOCK TABLES `entrega_pedido` WRITE;
/*!40000 ALTER TABLE `entrega_pedido` DISABLE KEYS */;
INSERT INTO `entrega_pedido` VALUES (1,30),(1,31);
/*!40000 ALTER TABLE `entrega_pedido` ENABLE KEYS */;
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
  `MP_vigente` tinyint(1) DEFAULT NULL,
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
  `PD_numero` int(11) DEFAULT NULL,
  `PD_fecha_pedido` datetime DEFAULT NULL,
  `PD_estado` int(11) DEFAULT NULL,
  `Pd_entrega` int(11) DEFAULT NULL,
  `PD_cliente` int(11) DEFAULT NULL,
  `PD_Delivery` tinyint(1) DEFAULT NULL,
  `PD_precio` double DEFAULT NULL,
  PRIMARY KEY (`PD_id`),
  KEY `Pd_entrega` (`Pd_entrega`),
  KEY `PD_estado` (`PD_estado`),
  KEY `PD_cliente` (`PD_cliente`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`Pd_entrega`) REFERENCES `entrega` (`ENT_id`),
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`PD_estado`) REFERENCES `pe_estado` (`PEST_id`),
  CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`PD_cliente`) REFERENCES `cliente` (`CL_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,1,'2015-12-01 00:00:00',1,NULL,5,1,NULL),(2,2,'2015-12-01 00:00:00',1,NULL,9,1,NULL),(3,3,'2015-12-01 00:00:00',1,NULL,3,1,NULL),(4,4,'2015-12-01 00:00:00',1,NULL,7,0,NULL),(5,5,'2015-12-01 00:00:00',1,NULL,11,1,NULL),(6,6,'2015-12-01 00:00:00',1,NULL,2,0,NULL),(7,7,'2015-12-01 00:00:00',1,NULL,1,0,NULL),(8,1,'2015-11-30 00:00:00',4,NULL,11,0,NULL),(9,2,'2015-11-30 00:00:00',4,NULL,7,0,NULL),(10,3,'2015-11-30 00:00:00',4,NULL,8,1,NULL),(11,4,'2015-11-30 00:00:00',4,NULL,1,0,NULL),(12,5,'2015-11-30 00:00:00',4,NULL,6,0,NULL),(13,6,'2015-11-30 00:00:00',4,NULL,1,0,NULL),(14,7,'2015-11-30 00:00:00',4,NULL,3,0,NULL),(15,8,'2015-11-30 00:00:00',4,NULL,1,0,NULL),(16,9,'2015-11-30 00:00:00',5,NULL,9,1,NULL),(17,10,'2015-11-30 00:00:00',5,NULL,11,1,NULL),(18,11,'2015-11-30 00:00:00',4,NULL,1,0,NULL),(19,12,'2015-11-30 00:00:00',4,NULL,5,1,NULL),(20,13,'2015-11-30 00:00:00',4,NULL,1,0,NULL),(21,14,'2015-11-30 00:00:00',4,NULL,4,1,NULL),(22,15,'2015-11-30 00:00:00',4,NULL,1,0,NULL),(23,16,'2015-11-30 00:00:00',4,NULL,2,0,NULL),(24,17,'2015-11-30 00:00:00',4,NULL,1,0,NULL),(25,18,'2015-11-30 00:00:00',4,NULL,1,0,NULL),(26,19,'2015-11-30 00:00:00',4,NULL,7,1,NULL),(27,1,'2015-11-29 00:00:00',4,NULL,1,0,NULL),(28,2,'2015-11-29 00:00:00',4,NULL,5,0,NULL),(29,3,'2015-11-29 00:00:00',4,NULL,1,0,NULL),(30,4,'2015-11-29 00:00:00',4,NULL,7,1,NULL),(31,5,'2015-11-29 00:00:00',4,NULL,10,1,NULL),(32,1,'2015-12-01 00:00:00',1,NULL,5,1,NULL),(33,2,'2015-12-01 00:00:00',1,NULL,1,0,NULL),(34,3,'2015-12-01 00:00:00',1,NULL,8,0,NULL),(35,4,'2015-12-01 00:00:00',1,NULL,4,1,NULL),(36,5,'2015-12-01 00:00:00',1,NULL,1,0,NULL),(37,6,'2015-12-01 00:00:00',1,NULL,1,0,NULL),(38,7,'2015-12-01 00:00:00',1,NULL,1,0,NULL),(39,8,'2015-12-01 00:00:00',1,NULL,1,0,NULL),(40,9,'2015-12-01 00:00:00',1,NULL,1,0,NULL);
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
  `PR_vigente` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`PR_id`),
  UNIQUE KEY `PR_nombre` (`PR_nombre`),
  KEY `PR_tipo_producto` (`PR_tipo_producto`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`PR_tipo_producto`) REFERENCES `tipo_producto` (`TP_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Carne',10,NULL,1,NULL),(2,'Carne Criolla',12,NULL,1,NULL),(3,'Carne Picante',12,NULL,1,NULL),(4,'Choclo',11,NULL,1,NULL),(5,'Humita',15,NULL,1,NULL),(6,'Jamon y Queso',10,NULL,1,NULL),(7,'Pollo',12,NULL,1,NULL),(8,'Pollo al Verdeo',14,NULL,1,NULL),(9,'Queso y Cebolla',12,NULL,1,NULL),(10,'Verduras',10,NULL,1,NULL),(11,'4 quesos',70,NULL,2,NULL),(12,'Anchoas',66,NULL,2,NULL),(13,'Calabresa',62,NULL,2,NULL),(14,'Cochina',65,NULL,2,NULL),(15,'Fugazzeta',60,NULL,2,NULL),(16,'Hawaiana',70,NULL,2,NULL),(17,'Jamón y anana',63,NULL,2,NULL),(18,'Jamón y morrones',55,NULL,2,NULL),(19,'Margarita',63,NULL,2,NULL),(20,'Muzzarella',50,NULL,2,NULL),(21,'Napolitana',60,NULL,2,NULL),(22,'Napolitana Esp.',85,NULL,2,NULL),(23,'Palmitos',65,NULL,2,NULL),(24,'Panceta y huevo',67,NULL,2,NULL),(25,'Peperoni',62,NULL,2,NULL),(26,'Ranchera',64,NULL,2,NULL),(27,'Salamin',65,NULL,2,NULL),(28,'Vegetariana',70,NULL,2,NULL),(29,'7up',15,NULL,3,NULL),(30,'Agua gasificada',12,NULL,3,NULL),(31,'Agua mineral',10,NULL,3,NULL),(32,'Brahma',20,NULL,3,NULL),(33,'Budweiser',20,NULL,3,NULL),(34,'Coca-Cola',15,NULL,3,NULL),(35,'Fanta',15,NULL,3,NULL),(36,'Heineken',25,NULL,3,NULL),(37,'Iguana',15,NULL,3,NULL),(38,'Imperial',20,NULL,3,NULL),(39,'Isenbeck',20,NULL,3,NULL),(40,'Jugo',12,NULL,3,NULL),(41,'Manaos',9,NULL,3,NULL),(42,'Pepsi',15,NULL,3,NULL),(43,'Quilmes',18,NULL,3,NULL),(44,'Schneider',20,NULL,3,NULL),(45,'Sprite',15,NULL,3,NULL),(46,'Stella Artois',20,NULL,3,NULL),(47,'Atun',20,NULL,5,NULL),(48,'Berenjenas',20,NULL,5,NULL),(49,'Brocoli',20,NULL,5,NULL),(50,'calabaza',20,NULL,5,NULL),(51,'Panceta',20,NULL,5,NULL),(52,'Pollo+verdura',20,NULL,5,NULL),(53,'Quiche de queso',20,NULL,5,NULL),(54,'Verdura',20,NULL,5,NULL),(55,'Jamon+queso+tomate',25,NULL,6,NULL),(56,'Milanesa carne',25,NULL,6,NULL),(57,'Milanesa pollo',25,NULL,6,NULL),(58,'Pollo+lechuga',25,NULL,6,NULL),(59,'Vegetariano',25,NULL,6,NULL),(60,'Completo',25,NULL,7,NULL),(61,'Jamon+queso',25,NULL,7,NULL),(62,'Simple',25,NULL,7,NULL),(63,'Papas fritas',15,NULL,8,NULL),(64,'Muzza + Coca',60,NULL,4,NULL),(65,'12 Emp. Carne + 1 muzza',145,NULL,4,NULL),(66,'Hamburguesa con fritas',35,NULL,4,NULL),(67,'Napolitana+Heineken',80,NULL,4,NULL),(68,'Fugazzeta+Imperial',75,NULL,4,NULL),(69,'Mila pollo+Fritas',35,NULL,4,NULL),(70,'Mila Carne+Fritas',35,NULL,4,NULL);
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
  `PP_observacion` varchar(300) DEFAULT NULL,
  `PP_precio` double DEFAULT NULL,
  KEY `PP_pedidoid` (`PP_pedidoid`),
  KEY `PP_productoid` (`PP_productoid`),
  CONSTRAINT `producto_pedidos_ibfk_1` FOREIGN KEY (`PP_pedidoid`) REFERENCES `pedido` (`PD_id`),
  CONSTRAINT `producto_pedidos_ibfk_2` FOREIGN KEY (`PP_productoid`) REFERENCES `producto` (`PR_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_pedidos`
--

LOCK TABLES `producto_pedidos` WRITE;
/*!40000 ALTER TABLE `producto_pedidos` DISABLE KEYS */;
INSERT INTO `producto_pedidos` VALUES (3,33,1,' ',20),(3,67,1,' ',80),(3,63,1,' ',15),(3,60,1,' ',25),(3,11,1,' ',70),(3,25,3,' ',62),(3,55,1,' ',25),(3,50,3,' ',20),(6,64,1,'  ',60),(6,3,1,'  ',12),(6,4,1,'  ',11),(6,60,1,'  ',25),(6,61,1,'  ',25),(6,62,1,'  ',25),(6,11,1,'  ',70),(6,55,3,'  ',25),(6,47,1,'  ',20),(6,52,1,'  ',20),(1,1,6,'  ',10),(1,11,3,'  ',70),(1,35,2,'  ',15),(1,44,1,'  ',20),(1,54,1,'  ',20),(1,68,1,' ',75),(1,55,1,' ',25),(1,47,5,' ',20),(1,43,6,' ',18),(1,60,3,' ',25),(4,49,1,'  ',20),(4,51,1,'  ',20),(4,63,1,'  ',15),(4,68,1,'  ',75),(4,64,1,'  ',60),(2,4,12,'  ',11),(2,66,2,' ',35),(2,11,4,' ',70),(2,17,1,' ',63),(2,63,4,' ',15),(2,47,1,' ',20),(2,32,3,' ',20),(5,69,1,'   ',35),(5,63,1,'   ',15),(5,29,4,' ',15),(7,29,1,' ',15),(7,26,1,' ',64),(7,58,4,' ',25),(7,3,4,' ',12),(7,47,2,' ',20),(9,11,1,' ',70),(9,64,1,' ',60),(9,47,1,' ',20),(9,60,2,' ',25),(8,11,1,' ',70),(11,47,1,'',20),(11,55,1,'',25),(13,18,1,'',55),(15,51,1,'',20),(18,60,1,'',25),(20,29,1,'',15),(22,29,1,'',15),(24,47,1,'',20),(25,55,1,'',25),(14,26,1,' ',64),(23,47,1,' ',20),(17,60,1,'  ',25),(16,29,1,'  ',15),(16,61,1,'  ',25),(10,29,2,'   ',15),(10,64,1,'   ',60),(10,1,5,'   ',10),(10,60,2,'   ',25),(10,62,3,'   ',25),(10,13,5,'   ',62),(10,14,3,'   ',65),(10,17,3,'   ',63),(10,26,6,'   ',64),(26,60,1,'  ',25),(19,55,1,'  ',25),(21,64,1,'  ',60),(12,31,1,'  ',10),(12,11,1,'  ',70),(12,52,1,'  ',20),(27,1,1,'',10),(29,64,1,'',60),(28,11,1,' ',70),(31,55,1,'  ',25),(30,64,1,'  ',60),(33,47,1,'',20),(33,11,1,'',70),(36,63,1,'',15),(36,55,1,'',25),(36,58,1,'',25),(36,1,1,'',10),(36,29,1,'',15),(38,55,1,'',25),(39,29,1,'',15),(39,47,1,'',20),(35,55,1,'  ',25),(35,60,1,'  ',25),(34,55,1,'  ',25),(34,11,1,'  ',70),(34,5,1,'  ',15),(34,15,4,'  ',60),(34,32,7,'  ',20),(37,3,1,'  ',12),(37,45,1,'  ',15),(40,60,1,'   ',25),(32,60,1,'  ',25),(32,29,1,'  ',15);
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
  `PV_vigente` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`PV_id`),
  UNIQUE KEY `PV_nombre` (`PV_nombre`)
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
-- Table structure for table `proveedor_categoria`
--

DROP TABLE IF EXISTS `proveedor_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor_categoria` (
  `PC_proveedor_id` int(11) DEFAULT NULL,
  `PC_categoria_id` int(11) DEFAULT NULL,
  KEY `PC_proveedor_id` (`PC_proveedor_id`),
  KEY `PC_categoria_id` (`PC_categoria_id`),
  CONSTRAINT `proveedor_categoria_ibfk_1` FOREIGN KEY (`PC_proveedor_id`) REFERENCES `proveedor` (`PV_id`),
  CONSTRAINT `proveedor_categoria_ibfk_2` FOREIGN KEY (`PC_categoria_id`) REFERENCES `categoria_mp` (`CA_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor_categoria`
--

LOCK TABLES `proveedor_categoria` WRITE;
/*!40000 ALTER TABLE `proveedor_categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor_categoria` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repartidor`
--

LOCK TABLES `repartidor` WRITE;
/*!40000 ALTER TABLE `repartidor` DISABLE KEYS */;
INSERT INTO `repartidor` VALUES (1,'Jorge Torres','moto'),(2,'Emiliano Gomez','Moto'),(3,'Elias Braun','Auto'),(4,'Pedro Vara','Moto');
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
  `SD_precio` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_estado`
--

LOCK TABLES `solicitud_estado` WRITE;
/*!40000 ALTER TABLE `solicitud_estado` DISABLE KEYS */;
INSERT INTO `solicitud_estado` VALUES (4,'Cancelada'),(2,'Enviada'),(3,'Pagada'),(1,'Pendiente');
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
  `TP_vigente` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`TP_id`),
  UNIQUE KEY `TP_nombre` (`TP_nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'Empanada',NULL),(2,'Pizza',NULL),(3,'Bebida',NULL),(4,'Combo',NULL),(5,'Tarta',NULL),(6,'Sandwich',NULL),(7,'Hamburguesa',NULL),(8,'Guarnición',NULL);
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

-- Dump completed on 2015-12-01 16:18:14
