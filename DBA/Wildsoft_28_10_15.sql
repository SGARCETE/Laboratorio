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
INSERT INTO Tipo_producto(TP_nombre) VALUES ('Combo');




create table Categoria_MP(
					     CA_id int AUTO_INCREMENT ,  
						 CA_nombre varchar(50) , 
						 unique (CA_nombre),
						 primary key(CA_id)
);

INSERT INTO Categoria_MP (CA_nombre) VALUES ('Verdulería');
INSERT INTO Categoria_MP (CA_nombre) VALUES ('Carnicería');
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



create table Materia_Prima (	
							MP_id int AUTO_INCREMENT, 
							MP_nombre varchar(50), 
							MP_fecha_vencimiento date, 
							MP_categoria int,
							FOREIGN KEY (MP_categoria) REFERENCES Categoria_MP(CA_id),
							PRIMARY KEY (MP_id),
							unique (MP_nombre)
);

INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Harina",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Queso",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Tomates",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Tomate en lata",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Cebolla",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Morrones",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("especias",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Huevos",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Anchoas",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Albahaca",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Levadura",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Aceituna",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Tapas de empanada",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Carne",'2015-10-10',2);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Pollo",'2015-10-10',2);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Salsa",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Choclos en lata",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria)  VALUES ("Jamón",'2015-10-10',3);



create table Proveedor(
						PV_id int AUTO_INCREMENT,
						PV_nombre varchar (30),
						PV_direccion varchar (50),
						PV_mail varchar(30),
						PV_telefono varchar (30),
						
						
						UNIQUE (PV_nombre),
						PRIMARY KEY (PV_id)		
);		


INSERT INTO Proveedor (PV_nombre, PV_direccion, PV_mail, PV_telefono) VALUES ("Las cañuelas","Rosa 2356- CABA",null, "2453-6798");
INSERT INTO Proveedor (PV_nombre, PV_direccion, PV_mail, PV_telefono) VALUES ("Los amigos","Roca 2743- CABA",null, "9750-6799");
INSERT INTO Proveedor (PV_nombre, PV_direccion, PV_mail, PV_telefono) VALUES ("Los pollos hermanos","Roca 25643- CABA",null, "9750-6799");

/*drop table solicitud_estado; */
create table Solicitud_estado (
						SEST_id int AUTO_INCREMENT,
						SEST_nombre varchar (30),
						unique (SEST_nombre),
						primary key (SEST_id)
					
);


INSERT INTO Solicitud_estado (SEST_nombre) VALUES ('Pendiente');
INSERT INTO Solicitud_estado (SEST_nombre) VALUES ('Enviada');
INSERT INTO Solicitud_estado (SEST_nombre) VALUES ('Pagada');
INSERT INTO Solicitud_estado (SEST_nombre) VALUES ('Cancelada');


create table Solicitud_compra (
						SD_id int AUTO_INCREMENT,
						SD_estado int,
						SD_proveedor int,
						SD_fecha datetime,
						SD_precio int,
						foreign key (SD_proveedor) references Proveedor (PV_id),
						foreign key (SD_estado) references  Solicitud_estado (SEST_id),
						primary key (SD_id)
);

INSERT INTO Solicitud_compra (SD_estado, SD_proveedor, SD_fecha) VALUES (4,3,"2015-10-10");
INSERT INTO Solicitud_compra (SD_estado, SD_proveedor, SD_fecha) VALUES (4,1,"2015-10-10");
INSERT INTO Solicitud_compra (SD_estado, SD_proveedor, SD_fecha) VALUES (4,2,"2015-10-10");

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
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('1 muzza, 1 coca', 150,4);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('12 empanadas, 1 muzza', 160,4);
INSERT INTO Producto (PR_nombre, PR_precio, PR_tipo_producto) VALUES ('Cumpleaños', 1200,4);




create table Cliente (
					   CL_id int AUTO_INCREMENT ,
					   CL_direccion varchar (50),
					   CL_telefono varchar (50),
					   CL_nombre varchar (50),
					   UNIQUE (CL_direccion, CL_telefono),
					   PRIMARY KEY (CL_id)
);


INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre) VALUES (null, null, "");
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

insert into Repartidor (RE_nombre, Re_vehiculo) values ('Javier Pérez', 'moto');
insert into Repartidor (RE_nombre, Re_vehiculo) values ('Nicolás Gomez', 'auto');
insert into Repartidor (RE_nombre, Re_vehiculo) values ('Nahuel Palacios', 'auto');



create table Entrega(
						ENT_id int AUTO_INCREMENT,
						ENT_fecha_salida datetime,
						ENT_repartidor int,
						foreign key (ENT_repartidor) REFERENCES Repartidor(RE_id),
						primary key (ENT_id)
);	
		

INSERT INTO Entrega (ENT_fecha_salida, ENT_repartidor) VALUES (now(),2);
INSERT INTO Entrega (ENT_fecha_salida, ENT_repartidor) VALUES (now(),2);
INSERT INTO Entrega (ENT_fecha_salida, ENT_repartidor) VALUES (now(),2);
INSERT INTO Entrega (ENT_fecha_salida, ENT_repartidor) VALUES (now(),1);


create table Pedido (
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


Insert into Pedido (PD_numero, PD_fecha_pedido, PD_estado, PD_entrega, PD_cliente, PD_Delivery) values (1,"2015-10-10",1,null, null,false);
Insert into Pedido (PD_numero, PD_fecha_pedido, PD_estado,PD_entrega, PD_cliente, PD_Delivery) values (2,"2015-10-10",1,null,1,true);
Insert into Pedido (PD_numero, PD_fecha_pedido, PD_estado,PD_entrega, PD_cliente, PD_Delivery) values (3,"2015-10-10",1,null, 3,true);

CREATE TABLE Combo (
						CO_id int AUTO_INCREMENT,
						CO_nombre varchar (200),
						primary key (CO_id),
						UNIQUE (CO_nombre)
);

INSERT INTO Combo (CO_nombre) values ('1 muzza, 1 coca');
INSERT INTO Combo (CO_nombre) values ('12 empanadas, 1 muzza');
INSERT INTO Combo (CO_nombre) values ('Cumpleaños');





create table Producto_Pedidos(
					PP_pedidoid int,
					PP_productoid int,
                    PP_producto_cantidad int,
					PP_observacion varchar(300),
					PP_precio double,
					foreign key (pp_pedidoid) references Pedido(PD_id),
					foreign key (pp_productoid) references Pedido(PD_id)
); 

INSERT INTO Producto_Pedidos (PP_pedidoid, PP_productoid, PP_producto_cantidad, PP_precio) values (1,2,5,10);
INSERT INTO Producto_Pedidos (PP_pedidoid, PP_productoid, PP_producto_cantidad, PP_precio) values (1,2,6,2);
INSERT INTO Producto_Pedidos (PP_pedidoid, PP_productoid, PP_producto_cantidad, PP_precio) values (2,2,6,5);
INSERT INTO Producto_Pedidos (PP_pedidoid, PP_productoid, PP_producto_cantidad, PP_precio) values (2,2,15,1);

CREATE TABLE Combo_productos(
					COPRO_combo_id int,
					COPRO_producto_id int,
                    COPRO_cantidad int,
					foreign key (COPRO_combo_id) references Combo(CO_id),
					foreign key (COPRO_producto_id) references Producto(PR_id)
					
);

INSERT INTO Combo_productos (COPRO_combo_id, COPRO_producto_id, COPRO_cantidad) VALUES (1,5,1);
INSERT INTO Combo_productos (COPRO_combo_id, COPRO_producto_id, COPRO_cantidad) VALUES (1,9,1);
INSERT INTO Combo_productos (COPRO_combo_id, COPRO_producto_id, COPRO_cantidad) VALUES (2,1,12);
INSERT INTO Combo_productos (COPRO_combo_id, COPRO_producto_id, COPRO_cantidad) VALUES (2,5,1);
INSERT INTO Combo_productos (COPRO_combo_id, COPRO_producto_id, COPRO_cantidad) VALUES (3,7,12);
INSERT INTO Combo_productos (COPRO_combo_id, COPRO_producto_id, COPRO_cantidad) VALUES (3,14,4);

create table Compra_MateriaPrima(
						CM_compra int,
						CM_materia_prima int,
						CM_cantidad_mp int,
						FOREIGN KEY (CM_compra) REFERENCES Solicitud_compra (SD_id),
						FOREIGN KEY (CM_materia_prima) REFERENCES Materia_prima(MP_id)
);

INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (1, 3, 2);
INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (1, 4, 3);
INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (2, 14, 1);
INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (2, 15, 2);	

create table Proveedor_categoria (
								PC_proveedor_id int,
								PC_categoria_id int,
								foreign key (PC_proveedor_id) references Proveedor (PV_id),
								foreign key (PC_categoria_id) references Categoria_MP (CA_id)


);

Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (1,2);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (1,1);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (1,3);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (2,2);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (2,3);

create table Entrega_pedido(
							EP_entrega_id int,
							EP_pedido_id int,
							foreign key (EP_entrega_id) references Entrega (ENT_ID),
							foreign key (EP_pedido_id) references Entrega (ENT_ID)


);

insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values (1,1);
insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values (2,2);
insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values (2,3);
insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values (3,4);



