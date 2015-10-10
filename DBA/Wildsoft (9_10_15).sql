 drop database  if exists wildsoft;

create database wildsoft;
use wildsoft;


CREATE  TABLE Tipo_producto ( 
						 TP_id int  not null AUTO_INCREMENT ,  
						 TP_nombre varchar(30) , 
						 unique (TP_nombre),
						 primary key(TP_id)
);

INSERT INTO Tipo_producto(TP_nombre) VALUES ('Empanada');
INSERT INTO Tipo_producto(TP_nombre) VALUES ('Pizza');
INSERT INTO Tipo_producto(TP_nombre) VALUES ('Bebida');





create table Categoria_MP(
					     CA_id int AUTO_INCREMENT ,  
						 CA_nombre varchar(50) , 
						 unique (CA_nombre),
						 primary key(CA_id)
);

INSERT INTO Categoria_MP (CA_nombre) VALUES ('Verdulería');
INSERT INTO Categoria_MP (CA_nombre) VALUES ('canicería');
INSERT INTO Categoria_MP (CA_nombre) VALUES ('Almacen');


create table PE_estado (
						PEST_id int AUTO_INCREMENT,
						PEST_nombre varchar (30),
						unique (PEST_nombre),
						primary key (PEST_id)
);

INSERT INTO PE_estado (PEST_nombre) VALUES  ('Pendiente');
INSERT INTO PE_estado (PEST_nombre) VALUES  ('Preparado');
INSERT INTO PE_estado (PEST_nombre) VALUES ('Enviado');
INSERT INTO PE_estado (PEST_nombre) VALUES   ('Cobrado');
INSERT INTO PE_estado (PEST_nombre) VALUES ('Cancelado');


CREATE  TABLE Producto ( 
						 PR_id int AUTO_INCREMENT ,  
						 PR_nombre varchar(30) , 
						 PR_precio double, 
						 PR_observacion varchar (300),
						 PR_tipo_producto int,
						 FOREIGN KEY (PR_tipo_producto) REFERENCES Tipo_producto(TP_ID) , 
						 unique (PR_nombre),
						 primary key (PR_id) 
);

INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Carne', 10, 1);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Verdura',10,1);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Humita',10,1);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Pollo',10,1);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Muzzarella', 70,2);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Jamón y Queso', 85,2);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Jamón y Morrones',105,2);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Cochina', 120,2);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Coca Cola', 40,3);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Manaos', 40,3);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Pepsi', 40,3);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('7UP', 40,3);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Sprite', 40,3);






create table Cliente (
					   CL_id int AUTO_INCREMENT ,
					   CL_direccion varchar (50),
					   CL_telefono varchar (50),
					   CL_nombre varchar (50),
					   UNIQUE (CL_direccion, CL_telefono),
					   PRIMARY KEY (CL_id)
);


INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre) VALUES (null, null, "vacio");
INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre)VALUES ('Belgrano 100', "4459-0748", "Santiago Garcete");
INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre)VALUES ('Zapiola 1455', "4555-2356", "Nicolás Cabral");
INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre)VALUES ('Zapiola 1454', "4555-2356", "Federico Vara");



create table Repartidor (
							RE_id int AUTO_INCREMENT , 
							RE_nombre varchar(50),
							RE_vehiculo varchar (50),
							UNIQUE (RE_nombre, RE_id),
							PRIMARY KEY (RE_id)
);




create table ENT_estado (
						ENTE_id int AUTO_INCREMENT,
						ENTE_nombre varchar (30),
						unique (ENTE_nombre),
						primary key (ENTE_id)
);

create table Entrega(
						ENT_id int AUTO_INCREMENT,
						ENT_fecha_salida datetime,
						ENT_estado int,
						ENT_repartidor int,
						foreign key (ENT_estado) REFERENCES ENT_estado(ENTE_ID),
						foreign key (ENT_repartidor) REFERENCES Repartidor(RE_id),
						primary key (ENT_id)
);			

create table Pedido (
					PD_id int AUTO_INCREMENT, 
					PD_fecha_pedido datetime, /*automsatico*/
					PD_estado int , 
					PD_cliente int,
					PD_entrega int,
                    PD_Delivery boolean,
					PD_precio double,
					FOREIGN KEY (PD_entrega) REFERENCES Entrega(ENT_id),
					FOREIGN KEY (PD_estado) REFERENCES PE_estado(PEST_id),
					FOREIGN KEY (PD_cliente) REFERENCES Cliente(CL_id),
					primary key (pd_id)
);

create table Producto_Pedidos(
					PP_pedidoid int,
					PP_productoid int,
                    PP_producto_cantidad int,
					PP_observacion varchar(300),
					PP_precio double,
					foreign key (pp_pedidoid) references Pedido(PD_id),
					foreign key (pp_productoid) references Pedido(PD_id)
); 