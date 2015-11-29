DROP DATABASE IF EXISTS wildsoft;

CREATE DATABASE wildsoft;
USE wildsoft;
SET NAMES utf8;

CREATE TABLE Tipo_producto ( 
						 TP_id int  not null AUTO_INCREMENT ,  
						 TP_nombre varchar(30) , 
                         TP_vigente boolean,
						 unique (TP_nombre),
						 primary key(TP_id)
);


CREATE TABLE Categoria_MP(
					     CA_id int AUTO_INCREMENT ,  
						 CA_nombre varchar(50) , 
						 CA_vigente boolean,
						 unique (CA_nombre),
						 primary key(CA_id)
);


CREATE TABLE PE_estado (
						PEST_id int AUTO_INCREMENT,
						PEST_nombre varchar (30),
						unique (PEST_nombre),
						primary key (PEST_id)
);


CREATE TABLE Materia_Prima (	
							MP_id int AUTO_INCREMENT, 
							MP_nombre varchar(50), 
							MP_fecha_vencimiento date, 
							MP_categoria int,
							MP_vigente boolean,
							FOREIGN KEY (MP_categoria) REFERENCES Categoria_MP(CA_id),
							PRIMARY KEY (MP_id),
							unique (MP_nombre)
);


CREATE TABLE Proveedor(
						PV_id int AUTO_INCREMENT,
						PV_nombre varchar (30),
						PV_direccion varchar (50),
						PV_mail varchar(30),
						PV_telefono varchar (30),
						PV_vigente boolean,
						UNIQUE (PV_nombre),
						PRIMARY KEY (PV_id)		
);		


CREATE TABLE Solicitud_estado (
						SEST_id int AUTO_INCREMENT,
						SEST_nombre varchar (30),
						unique (SEST_nombre),
						primary key (SEST_id)
					
);


CREATE TABLE Solicitud_compra (
						SD_id int AUTO_INCREMENT,
						SD_estado int,
						SD_proveedor int,
						SD_fecha datetime,
						SD_precio int,
						foreign key (SD_proveedor) references Proveedor (PV_id),
						foreign key (SD_estado) references  Solicitud_estado (SEST_id),
						primary key (SD_id)
);


CREATE TABLE Producto ( 
						 PR_id int AUTO_INCREMENT ,  
						 PR_nombre varchar(30) , 
						 PR_precio double, 
						 PR_observacion varchar (300),
						 PR_tipo_producto int,
						 PR_vigente boolean	,
						 FOREIGN KEY (PR_tipo_producto) REFERENCES Tipo_producto(TP_ID) , 
						 unique (PR_nombre),
						 primary key (PR_id) 
);


CREATE TABLE Cliente (
					   CL_id int AUTO_INCREMENT ,
					   CL_direccion varchar (50),
					   CL_telefono varchar (50),
					   CL_nombre varchar (50),
                       CL_detalle varchar (100),
					   CL_vigente boolean,
					   UNIQUE (CL_direccion, CL_telefono),
					   PRIMARY KEY (CL_id)
);


CREATE TABLE Repartidor (
						RE_id int AUTO_INCREMENT , 
						RE_nombre varchar(50),
						RE_vehiculo varchar (50),
						UNIQUE (RE_nombre, RE_id),
						PRIMARY KEY (RE_id)
);


CREATE TABLE Entrega(
						ENT_id int AUTO_INCREMENT,
						ENT_fecha_salida datetime,
						ENT_repartidor int,
						foreign key (ENT_repartidor) REFERENCES Repartidor(RE_id),
						primary key (ENT_id)
);	
		

CREATE TABLE Pedido (
					PD_id int AUTO_INCREMENT, 
					PD_numero int,
					PD_fecha_pedido datetime, /*automsatico*/
					PD_estado int , 
					Pd_entrega int,
					PD_cliente int,
                    PD_Delivery boolean,
					PD_precio double,
					FOREIGN KEY (PD_entrega) REFERENCES Entrega(ENT_id),
					FOREIGN KEY (PD_estado) REFERENCES PE_estado(PEST_id),
					FOREIGN KEY (PD_cliente) REFERENCES Cliente(CL_id),
					primary key (pd_id)
);


CREATE TABLE Combo (
					CO_id int AUTO_INCREMENT,
					CO_nombre varchar (200),
					CO_precio double,
					CO_vigente boolean,
					primary key (CO_id),
					UNIQUE (CO_nombre)
);


CREATE TABLE Producto_Pedidos(
					PP_pedidoid int,
					PP_productoid int,
                    PP_producto_cantidad int,
					PP_observacion varchar(300),
					PP_precio double,
					foreign key (pp_pedidoid) references Pedido(PD_id),
					foreign key (pp_productoid) references Producto(PR_id)
); 


CREATE TABLE Combo_productos(
					COPRO_combo_id int,
					COPRO_producto_id int,
                    COPRO_cantidad int,
					COPRO_Precio double,
					COPRO_observacion varchar (30),
					foreign key (COPRO_combo_id) references Combo(CO_id),
					foreign key (COPRO_producto_id) references Producto(PR_id)
					
);


CREATE TABLE Compra_MateriaPrima(
						CM_compra int,
						CM_materia_prima int,
						CM_cantidad_mp int,
						FOREIGN KEY (CM_compra) REFERENCES Solicitud_compra (SD_id),
						FOREIGN KEY (CM_materia_prima) REFERENCES Materia_prima(MP_id)
);


CREATE TABLE Proveedor_categoria (
								PC_proveedor_id int,
								PC_categoria_id int,
								foreign key (PC_proveedor_id) references Proveedor (PV_id),
								foreign key (PC_categoria_id) references Categoria_MP (CA_id)


);


CREATE TABLE Entrega_pedido(
							EP_entrega_id int,
							EP_pedido_id int,
							foreign key (EP_entrega_id) references Entrega (ENT_ID),
							foreign key (EP_pedido_id) references Pedido (PD_id)


);

-- INSERTS INICIALES OBLIGATORIOS --
INSERT INTO PE_estado (PEST_nombre) VALUES ('Pendiente');
INSERT INTO PE_estado (PEST_nombre) VALUES ('Preparado');
INSERT INTO PE_estado (PEST_nombre) VALUES ('Enviado');
INSERT INTO PE_estado (PEST_nombre) VALUES ('Cobrado');
INSERT INTO PE_estado (PEST_nombre) VALUES ('Cancelado');

INSERT INTO Solicitud_estado (SEST_nombre) VALUES ('Pendiente');
INSERT INTO Solicitud_estado (SEST_nombre) VALUES ('Enviada');
INSERT INTO Solicitud_estado (SEST_nombre) VALUES ('Pagada');
INSERT INTO Solicitud_estado (SEST_nombre) VALUES ('Cancelada');

INSERT INTO Tipo_producto(TP_nombre) VALUES ('Empanada');
INSERT INTO Tipo_producto(TP_nombre) VALUES ('Pizza');
INSERT INTO Tipo_producto(TP_nombre) VALUES ('Bebida');
INSERT INTO Tipo_producto(TP_nombre) VALUES ('Combo');

INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre, CL_detalle)VALUES (null, null, "","");

