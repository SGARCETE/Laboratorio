SET NAMES utf8;
INSERT INTO Categoria_MP (CA_nombre) VALUES ('Verduleria');
INSERT INTO Categoria_MP (CA_nombre) VALUES ('Carniceria');
INSERT INTO Categoria_MP (CA_nombre) VALUES ('Almacen');


INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Harina",'2016-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Queso",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Tomates",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Tomate en lata",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Cebolla",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Morrones",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("especias",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Huevos",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Anchoas",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Albahaca",'2015-10-10',1);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Levadura",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Aceituna",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Tapas de empanada",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Carne",'2015-10-10',2);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Pollo",'2015-10-10',2);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Salsa",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Choclos en lata",'2015-10-10',3);
INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES ("Jamon",'2015-10-10',3);

INSERT INTO Proveedor (PV_nombre, PV_direccion, PV_mail, PV_telefono) VALUES ("La gran carniceria","Rosa 2356- CABA","santi101093@hotmail.com.ar", "2453-6798");
INSERT INTO Proveedor (PV_nombre, PV_direccion, PV_mail, PV_telefono) VALUES ("Los amigos","Roca 2743- CABA","santi101093@hotmail.com.ar", "9750-6799");
INSERT INTO Proveedor (PV_nombre, PV_direccion, PV_mail, PV_telefono) VALUES ("Los pollos hermanos","Roca 25643- CABA","santi101093@hotmail.com.ar", "9750-6799");

INSERT INTO Solicitud_compra (SD_estado, SD_proveedor, SD_fecha, SD_precio) VALUES (3,3,"2015-12-01",100);
INSERT INTO Solicitud_compra (SD_estado, SD_proveedor, SD_fecha, SD_precio) VALUES (3,1,"2015-12-01",80);
INSERT INTO Solicitud_compra (SD_estado, SD_proveedor, SD_fecha, SD_precio) VALUES (3,2,"2015-12-01",300);

INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre, CL_detalle)VALUES ('Belgrano 100', "4888-0748", "Santiago Garcete","No anda timbre, golpear");
INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre, CL_detalle)VALUES ('Dell Eva 2211', "4777-2680", "Nicolas Cabral","Timbre de arriba, 2do piso");
INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre, CL_detalle)VALUES ('Zapiola 1454', "4666-2356", "Federico Vara","Timbre A1 planta baja");
INSERT INTO Cliente (CL_direccion, CL_telefono, CL_nombre, CL_detalle)VALUES ('Villegas 1454', "4999-5456", "Lucas Tolosa","Es una empresa, dejar en recepcion");

INSERT INTO Entrega (ENT_fecha_salida, ENT_repartidor) VALUES (now(),2);
INSERT INTO Entrega (ENT_fecha_salida, ENT_repartidor) VALUES (now(),2);
INSERT INTO Entrega (ENT_fecha_salida, ENT_repartidor) VALUES (now(),2);
INSERT INTO Entrega (ENT_fecha_salida, ENT_repartidor) VALUES (now(),1);

INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (1, 3, 2);
INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (1, 4, 3);
INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (2, 14, 1);
INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (2, 15, 2);
INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (3, 14, 2);
INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES (3, 15, 5);

Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (1,2);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (1,1);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (1,3);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (2,2);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (2,3);
Insert into Proveedor_categoria (PC_proveedor_id, PC_categoria_id) values (3,2);

insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values (1,1);
insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values (2,2);
insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values (2,3);
insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values (3,4);

insert into Repartidor (RE_nombre, Re_vehiculo) values ('Javier', 'moto');
insert into Repartidor (RE_nombre, Re_vehiculo) values ('Nicolas Gomez', 'auto');
insert into Repartidor (RE_nombre, Re_vehiculo) values ('Nahuel Palacios', 'auto');


