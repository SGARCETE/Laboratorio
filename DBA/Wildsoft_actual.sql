CREATE DATABASE Wildsoft;

use Wildsoft;

show tables;
CREATE  TABLE Tipo_producto ( 
						 TP_id int  not null AUTO_INCREMENT ,  
						 TP_nombre varchar(30) , 
						 unique (TP_nombre),
						 primary key(TP_id)
);

/* INSERT INTO Tipo_producto(TPcomboclienteCL_idCL_id_nombre) VALUES ('Bebida');
INSERT INTO Tipo_producto VALUES ('Comida'); */

create table Solicitud_estado (
						SEST_id int AUTO_INCREMENT,
						SEST_nombre varchar (30),
						unique (SEST_nombre),
						primary key (SEST_id)
);

/* INSERT INTO Solicitud_estado VALUES (1, 'Guardada');
INSERT INTO Solicitud_estado VALUES (2, 'Enviada'); */


create table Categoria_MP(
					     CA_id int AUTO_INCREMENT ,  
						 CA_nombre varchar(50) , 
						 unique (CA_nombre),
						 primary key(CA_id)
);

/* INSERT INTO Categoria_MP VALUES (1, 'Verdulería');
INSERT INTO Categoria_MP VALUES (2, 'canicería');
INSERT INTO Categoria_MP VALUES (3, 'Almacen'); */

create table PE_estado (
						PEST_id int AUTO_INCREMENT,
						PEST_nombre varchar (30),
						unique (PEST_nombre),
						primary key (PEST_id)
);

/* INSERT INTO PE_estado VALUES (1, 'Pendiente');
INSERT INTO PE_estado VALUES (3, 'Preparado');
INSERT INTO PE_estado VALUES (4, 'Enviado');
INSERT INTO PE_estado VALUES (5, 'Cobrado');
INSERT INTO PE_estado VALUES (6, 'Cancelado'); */





create table ENT_estado (
						ENTE_id int AUTO_INCREMENT,
						ENTE_nombre varchar (30),
						unique (ENTE_nombre),
						primary key (ENTE_id)
);

/*INSERT INTO ENT_estado VALUES (1,'Pendiente');
INSERT INTO ENT_estado VALUES (2,'En curso');
INSERT INTO ENT_estado VALUES (3,'Finalizada'); */								

CREATE  TABLE Producto ( 
						 PR_id int AUTO_INCREMENT ,  
						 PR_nombre varchar(30) , 
						 PR_precio int, 
						 PR_tipo_producto int,
						 FOREIGN KEY (PR_tipo_producto) REFERENCES Tipo_producto(TP_ID) , 
						 unique (PR_nombre),
						 primary key (PR_id) 
);

/* INSERT INTO Producto VALUES (1, 'Empanada de carne', 10, 2);
INSERT INTO Producto VALUES (2, 'Empanada de verdura',10,2);
INSERT INTO Producto VALUES (3, 'Empanada de humita',10,2);
INSERT INTO Producto VALUES (4, 'Empanada de pollo',10,2);
INSERT INTO Producto VALUES (5, 'Pizza de muzzarella', 70,2);
INSERT INTO Producto VALUES (6, 'Pizza de jamón y queso', 85,2);
INSERT INTO Producto VALUES (7, 'Pizza de jamón y morrones',105,2);
INSERT INTO Producto VALUES (8, 'Pizza cochina', 120,2);
INSERT INTO Producto VALUES (9, 'Coca Cola', 40,1); */


CREATE TABLE Combo (
						CO_id int AUTO_INCREMENT,
						CO_nombre varchar (200),
						CO_precio int,
						primary key (CO_id),
						UNIQUE (CO_nombre)
);

/* INSERT INTO Combo VALUES (1,'Pizza de muzzarella más docena de empanadas', 260);
INSERT INTO Combo VALUES (2,'Pizza de muzzarella más 1/2 docena de empanadas', 215); */



create table Cliente (
					   CL_id int AUTO_INCREMENT ,
					   CL_direccion varchar (50),
					   CL_telefono varchar (50),
					   CL_nombre varchar (50),
					   UNIQUE (CL_direccion, CL_telefono),
					   PRIMARY KEY (CL_id)
);


/* INSERT INTO Cliente VALUES (1,'Vergara 1567', "4456-9874");
INSERT INTO Cliente VALUES (2,'Belgrano 100', "4456-9292");
INSERT INTO Cliente VALUES (3,'Zapiola 1455', "4555-2356");
INSERT INTO Cliente VALUES (4,'Zapiola 1454', "4555-2356"); */





create table Repartidor (
							RE_id int AUTO_INCREMENT , 
							RE_nombre varchar(50),
							RE_vehiculo varchar (50),
							UNIQUE (RE_nombre, RE_id),
							PRIMARY KEY (RE_id)
);

/* INSERT INTO Repartidor VALUES (1,'Lucas Tolosa', false);
INSERT INTO repartidor VALUES (2,'Ernesto Carranza', true); */

	

create table Entrega(
						ENT_fecha_salida datetime,
						ENT_estado int,
						ENT_repartidor int,
						ENT_id int AUTO_INCREMENT,
						foreign key (ENT_estado) REFERENCES ENT_estado(ENTE_ID),
						foreign key (ENT_repartidor) REFERENCES Repartidor(RE_id),
						primary key (ENT_id)
);			

/* INSERT INTO Entrega VALUES (now(),1,2,1 );
INSERT INTO Entrega VALUES (now(),2,2,2 );
INSERT INTO Entrega VALUES (now(),3,2,3 ); */


create table Proveedor(
						PV_id int AUTO_INCREMENT,
						PV_nombre varchar (30),
						PV_direccion varchar (50),
						PV_mail varchar(30),
						PV_telefono varchar (30),
						PV_categoria int,
						FOREIGN KEY (PV_categoria) REFERENCES Categoria_MP (CA_id), 	
						UNIQUE (PV_nombre,PV_categoria),
						PRIMARY KEY (PV_id)		
);		


/* INSERT INTO Proveedor VALUES (1,"Verduleria 'Las cañuelas' ","Rosa 2356- CABA",null, "2453-6798", 1 );
INSERT INTO Proveedor VALUES (2,"Carniceria 'Los amigos' ","Roca 2743- CABA",null, "9750-6799", 2 );
INSERT INTO Proveedor VALUES (3,"Almacen 'Los pollos hermanos' ","Roca 25643- CABA",null, "9750-6799", 3 );
*/



create table Materia_Prima (	
							MP_id int AUTO_INCREMENT, 
							MP_nombre varchar(50), 
							MP_fecha_vencimiento date, 
							MP_categoria int,
							FOREIGN KEY (MP_categoria) REFERENCES Categoria_MP(CA_id),
							PRIMARY KEY (MP_id),
							unique (MP_nombre)
);

/* INSERT INTO Materia_Prima VALUES (1,"Harina",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (2,"Queso",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (3,"Tomates",'2015-10-10',1, 1);
INSERT INTO Materia_Prima VALUES (4,"Tomate en lata",'2015-10-10',1, 1);
INSERT INTO Materia_Prima VALUES (5,"Cebolla",'2015-10-10',1, 1);
INSERT INTO Materia_Prima VALUES (6,"Morrones",'2015-10-10',1, 1);
INSERT INTO Materia_Prima VALUES (7,"especias",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (8,"Huevos",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (9,"Anchoas",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (10,"Albahaca",'2015-10-10',1, 1);
INSERT INTO Materia_Prima VALUES (11,"Levadura",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (12,"Aceituna",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (13,"Tapas de empanada",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (14,"Carne",'2015-10-10',2, 2);
INSERT INTO Materia_Prima VALUES (15,"Pollo",'2015-10-10',2, 2);
INSERT INTO Materia_Prima VALUES (16,"Salsa",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (17,"Choclos en lata",'2015-10-10',3, 3);
INSERT INTO Materia_Prima VALUES (18,"Jamón",'2015-10-10',3, 3);

*/
										
create table Solicitud_compra (
						SD_id int AUTO_INCREMENT,
						SD_estado int,
						SD_proveedor int,
						SD_fecha datetime,
						foreign key (SD_proveedor) references Proveedor (PV_id),
						foreign key (SD_estado) references  Solicitud_estado (SEST_id),
						primary key (SD_id)
);

/* INSERT INTO Solicitud_compra VALUES (1, 2,3,"2015-10-10");
INSERT INTO Solicitud_compra VALUES (2, 2,1,"2015-10-10");
INSERT INTO Solicitud_compra VALUES (3, 1,2,"2015-10-10"); */
					
create table Compra_MateriaPrima(
						CM_compra int,
						CM_materia_prima int,
						FOREIGN KEY (CM_compra) REFERENCES Solicitud_compra (SD_id),
						FOREIGN KEY (CM_materia_prima) REFERENCES Materia_prima(MP_id)
);

/* INSERT INTO Compra_MateriaPrima VALUES (1, 1);
INSERT INTO Compra_MateriaPrima VALUES (1, 2);
INSERT INTO Compra_MateriaPrima VALUES (1, 16);
INSERT INTO Compra_MateriaPrima VALUES (1, 17);	
INSERT INTO Compra_MateriaPrima VALUES (1, 18);
INSERT INTO Compra_MateriaPrima VALUES (1, 2);
INSERT INTO Compra_MateriaPrima VALUES (2, 3);
INSERT INTO Compra_MateriaPrima VALUES (2, 4); */
	
create table Proveedor_Materia_prima(
						PM_Proveedor int,
						PM_materia_prima int,
						FOREIGN KEY (PM_proveedor) REFERENCES Proveedor (PV_id),
						FOREIGN KEY (PM_materia_prima) REFERENCES Materia_prima(MP_id)
);




create table Pedido (
					PD_id int AUTO_INCREMENT, 
					PD_fecha_pedido datetime, 
					PD_observacion varchar(250), 
					PD_estado int , 
					PD_cliente int,
					PD_entrega int,
					FOREIGN KEY (PD_entrega) REFERENCES Entrega(ENT_id),
					FOREIGN KEY (PD_estado) REFERENCES PE_estado(PEST_id),
					FOREIGN KEY (PD_cliente) REFERENCES Cliente(CL_id),
					primary key (pd_id)
);

/* INSERT INTO Pedido VALUES (1,null,null,now(),null,1,1,null );
INSERT INTO Pedido VALUES (2,null,null,now(),null,2,2,1 );
INSERT INTO Pedido VALUES (3,null,null,now(),null,4,4,2 );
INSERT INTO Pedido VALUES (4,null,null,now(),null,5,3,3 );
INSERT INTO Pedido VALUES (5,null,null,now(),null,5,3,3 );
*/


create table Producto_Pedidos(
					PP_pedidoid int AUTO_INCREMENT,
					PP_productoid int,
                    PP_producto_cantidad int,
					foreign key (pp_pedidoid) references Pedido(PD_id),
					foreign key (pp_productoid) references Pedido(PD_id)
); 
			
/* INSERT INTO Producto_Pedidos VALUES (1,1);
INSERT INTO Producto_Pedidos VALUES (2,1);
INSERT INTO Producto_Pedidos VALUES (3,1);
INSERT INTO Producto_Pedidos VALUES (4,2);
INSERT INTO Producto_Pedidos VALUES (4,3);
INSERT INTO Producto_Pedidos VALUES (5,1);
*/

		
create table Combo_pedido (
					CP_pedidoid int AUTO_INCREMENT,
					CP_comboid int,
					foreign key (CP_pedidoid) references Pedido(PD_id),
					foreign key (CP_comboid) references Combo(CO_id)
);

/*INSERT INTO Combo_pedido VALUES (1,1);
INSERT INTO Combo_pedido VALUES (4,2);
*/

