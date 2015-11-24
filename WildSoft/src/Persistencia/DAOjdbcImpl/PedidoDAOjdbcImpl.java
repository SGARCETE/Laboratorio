package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import MetAux.MetAux;
import Negocio.Modelo.Cliente;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.PedidoDAO;

public class PedidoDAOjdbcImpl implements PedidoDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	private SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    ComboDAOjdbcImpl svCombo= new ComboDAOjdbcImpl();
    
    
	public boolean AGREGAR_PEDIDO(Pedido p) {
		
		// Calcula la ID DIARIA
		int idDiaria = 1;
		Pedido pedidoAnterior = OBTENER_PEDIDO(ObtenerUltimoPedido());
		Calendar Fecha_HOY = new GregorianCalendar();
		Calendar Pedido_Anterior = Calendar.getInstance();
		Pedido_Anterior.setTime(pedidoAnterior.getFecha_Hora_Pedido());
		if(MetAux.isSameDay(Pedido_Anterior,Fecha_HOY)){
			idDiaria = pedidoAnterior.getID_DIARIO() + 1;
		}

		// Se fija si tiene un cliente asociado, de lo contrario se ingresa el cliente 1
		String CLIENTE = "NULL";
		CLIENTE = (p.getCliente()!=null && p.getCliente().getID_Cliente()!=0) ? p.getCliente().getID_Cliente().toString() : "1";

		// Entrega por defecto en NULL
		String ENTREGA = "NULL";
		
		String SentenciaSQL_PEDIDO = "INSERT INTO PEDIDO(PD_fecha_pedido, PD_estado, PD_cliente, PD_entrega, PD_Delivery, PD_numero) VALUES ("+
				"'"+	formato_yyyyMMdd.format(p.getFecha_Hora_Pedido())	+"',"+
				""+		1													+","+	/*Estado por defecto 1 = 'Pendiente'*/
				""+		CLIENTE												+","+
				""+		ENTREGA												+","+									
				""+ 	p.getEs_Delivery()									+","+
				""+ 	idDiaria											+");";
		
		boolean Exito_al_Ingresar_pedido = conex.Insertar(SentenciaSQL_PEDIDO);
		
		// Si se pudo crear el pedido, entonces se asocian sus productos
		if(Exito_al_Ingresar_pedido){
			Integer PEDIDO_ID = ObtenerUltimoPedido();
			
			for (int i = 0; i < p.getLista_Productos().size(); i++) {	
				Integer PRODUCTO_ID = p.getLista_Productos().get(i).getPR_id();
				Double PRECIO_ACTUAL = p.getLista_Productos().get(i).getPR_precio();
				String OBSERVACION =  p.getLista_Productos().get(i).getPR_Observacion();
				String SentenciaSQL_producto_pedidos = "INSERT INTO producto_pedidos (PP_pedidoid, PP_productoid, PP_producto_cantidad, PP_Observacion, PP_precio)"
						+ "VALUES ("+
						""+	 PEDIDO_ID									+","+	// INTEGER
						""+	 PRODUCTO_ID								+","+	// INTEGER
						""+	 p.getLista_Productos().get(i).getCantidad()+","+	// INTEGER
						"'"+ OBSERVACION								+"',"+	// STRING
						""+  PRECIO_ACTUAL								+ ")";	// DOUBLE
				boolean Asociar_producto = conex.Insertar(SentenciaSQL_producto_pedidos);
				
				if(!Asociar_producto){
					System.out.println("Se creo el pedido, pero no se pudo asociar los productos de ese pedido :(");
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Integer ObtenerUltimoPedido() {
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT PD_id FROM PEDIDO WHERE PD_id = (select max(PD_id) from PEDIDO)");				
			rs.first();
			int ID = rs.getInt("PD_id");
			conex.cerrarConexion();
			return ID;
		} catch (SQLException SQLE) {
			JOptionPane.showMessageDialog(null,"No se puede dar la fila solicitada! \n ERROR : " + SQLE.getMessage());
		}
		return 0;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public ArrayList<Pedido> getLISTA_PEDIDOS_PREPARADOS(Calendar Fecha_mostrar) {
			if(Fecha_mostrar==null)
				return null;
			ArrayList<Pedido> Arreglo = new ArrayList<Pedido>();
			try {
				conex.connectToMySQL();// Conectar base
				Statement st = conex.conexion.createStatement();
				String FECHA_FILTRO = formato_yyyyMMdd.format(Fecha_mostrar.getTime());
				String Query = "select P.PD_Delivery, P.PD_id, P.PD_numero,  P.PD_fecha_pedido, EST.PEST_nombre, C.CL_nombre, C.CL_direccion, C.CL_telefono,SUM(PP.PP_precio * PP.PP_producto_cantidad) as Precio "
						+" from  Pedido P join producto_pedidos PP join Pe_estado EST join Cliente C  on C.cl_id= P.PD_cliente and  P.PD_id= PP.PP_pedidoid and P.PD_fecha_pedido='"+ FECHA_FILTRO +"' and P.PD_estado= EST.Pest_id"
						 +" and P.PD_estado= 2  group by P.PD_id";
				st.executeQuery(Query);
				
				
				ResultSet Fila = st.getResultSet();
				while (Fila.next()) {
					Pedido P = new Pedido();
					P.setID_DIARIO(Fila.getInt("PD_numero"));
					P.setNumero_Pedido(Fila.getInt("PD_ID"));
					P.setFecha_Hora_Pedido(Fila.getDate("PD_fecha_pedido"));
					P.setESTADO(Fila.getString("PEST_nombre"));
					P.setCliente(new Cliente (Fila.getString("CL_nombre")));
					P.getCliente().setDomicilio(Fila.getString("CL_direccion"));
					P.getCliente().setTelefono_Fijo(Fila.getString("CL_telefono"));
					P.setTotal(Fila.getDouble("Precio"));
					Boolean delivery = false;
					if(Fila.getInt("PD_Delivery")==1){
						delivery = true;
					}
					P.setEs_Delivery(delivery);
					P.setLista_Productos(getLista_Productos(P));
					Arreglo.add(P);

				}
				conex.cerrarConexion();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
			}
			return Arreglo;
		}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public ArrayList<Pedido> getLISTA_PEDIDOS(Calendar Fecha_mostrar) {
		if(Fecha_mostrar==null)
			return null;
		ArrayList<Pedido> Arreglo = new ArrayList<Pedido>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String FECHA_FILTRO = formato_yyyyMMdd.format(Fecha_mostrar.getTime());
			String Query = "select P.PD_Delivery, P.PD_id, P.PD_numero,  P.PD_fecha_pedido, EST.PEST_nombre, C.CL_nombre, C.CL_direccion, C.CL_telefono,SUM(PP.PP_precio * PP.PP_producto_cantidad) as Precio "
					+" from  Pedido P join producto_pedidos PP join Pe_estado EST join Cliente C  on C.cl_id= P.PD_cliente and  P.PD_id= PP.PP_pedidoid and P.PD_fecha_pedido='"+ FECHA_FILTRO +"' and P.PD_estado= EST.Pest_id"
					 +"  group by P.PD_id";
			st.executeQuery(Query);
			
			
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Pedido P = new Pedido();
				P.setID_DIARIO(Fila.getInt("PD_numero"));
				P.setNumero_Pedido(Fila.getInt("PD_ID"));
				P.setFecha_Hora_Pedido(Fila.getDate("PD_fecha_pedido"));
				P.setESTADO(Fila.getString("PEST_nombre"));
				P.setCliente(new Cliente (Fila.getString("CL_nombre")));
				P.getCliente().setDomicilio(Fila.getString("CL_direccion"));
				P.getCliente().setTelefono_Fijo(Fila.getString("CL_telefono"));
				P.setTotal(Fila.getDouble("Precio"));
				Boolean delivery = false;
				if(Fila.getInt("PD_Delivery")==1){
					delivery = true;
				}
				P.setEs_Delivery(delivery);
				P.setLista_Productos(getLista_Productos(P));
				Arreglo.add(P);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private ArrayList<Producto> getLista_Productos(Pedido P) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String Query = "select PR.PR_id, P.PD_id, PR.Pr_nombre, PP.PP_producto_cantidad, PP.PP_Observacion, PP.PP_precio, T.TP_id, T.TP_nombre from "+ 
			"Producto PR join Producto_pedidos PP join Pedido P join tipo_producto T "+ 
			"on T.TP_id= PR.PR_tipo_producto and PR.Pr_id=PP.PP_productoid and P.PD_id=PP.PP_pedidoid and P.PD_id=" + 
			        P.getNumero_Pedido();

			st.executeQuery(Query);
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Producto Prod = new Producto();
				Prod.setPR_id(Fila.getInt("PR_id"));
				Prod.setPR_nombre(Fila.getString("PR_nombre"));
				Prod.setPR_precio(Fila.getDouble("PP_precio"));
				Prod.setPR_Observacion(Fila.getString("PP_Observacion"));
				Prod.setPR_TIPO_PRODUCTO_STRING(Fila.getString("TP_nombre"));
				Prod.setCantidad(Fila.getInt("PP_producto_cantidad"));
				Prod.setPR_tipo_producto(Fila.getInt("TP_id"));
				Arreglo.add(Prod);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		private ArrayList<Producto> getLista_Productos_Cocina(Integer ID_Pedido) {
			ArrayList<Producto> Arreglo = new ArrayList<Producto>();
			try {
				conex.connectToMySQL();// Conectar base
				Statement st = conex.conexion.createStatement();
				
				String Query = "select PR.PR_id, P.PD_id, PR.Pr_nombre, PP.PP_producto_cantidad, PP.PP_Observacion, PP.PP_precio, T.TP_id, T.TP_nombre from "+ 
				"Producto PR join Producto_pedidos PP join Pedido P join tipo_producto T "+ 
				"on T.TP_id= PR.PR_tipo_producto and PR.Pr_id=PP.PP_productoid and P.PD_id=PP.PP_pedidoid and P.PD_id=" + ID_Pedido;

				st.executeQuery(Query);
				ResultSet Fila = st.getResultSet();
				while (Fila.next()) {
					if (Fila.getInt("TP_id")!= 4){	// 4 = TIPO 'COMBO'
						Producto Prod = new Producto();
						Prod.setPR_id(Fila.getInt("PR_id"));
						Prod.setPR_nombre(Fila.getString("PR_nombre"));
						Prod.setPR_precio(Fila.getDouble("PP_precio"));
						Prod.setPR_Observacion(Fila.getString("PP_Observacion"));
						Prod.setPR_TIPO_PRODUCTO_STRING(Fila.getString("TP_nombre"));
						Prod.setCantidad(Fila.getInt("PP_producto_cantidad"));
						Prod.setPR_tipo_producto(Fila.getInt("TP_id"));
						
						boolean esta = Arreglo.contains(Prod);
						if(esta){
							Integer index = Arreglo.indexOf(Prod);
							Integer nueva_cant = Arreglo.get(index).getCantidad() + Prod.getCantidad();
							if(!Prod.getPR_Observacion().isEmpty()){
								String Nueva_observacion = Prod.getPR_Observacion() +" "+ Arreglo.get(index).getPR_Observacion();
								Arreglo.get(index).setPR_Observacion(Nueva_observacion);
							}
							Arreglo.get(index).setCantidad(nueva_cant);
							
						}
						else
							Arreglo.add(Prod);
					}else{
						ArrayList<Producto> combo_producto =  svCombo.getLista_Productos(Fila.getString("PR_nombre"));
						for (int i =0;i<combo_producto.size(); i++){ 
							// si existe el ID del productoCombo, entonces sumale prcombo.getCantidad a el ID del arreglo
							
							Producto PR_Combo = combo_producto.get(i);
							
							boolean esta = Arreglo.contains(PR_Combo);
							if(esta){
								Integer index = Arreglo.indexOf(PR_Combo);

								Integer nueva_cant = Arreglo.get(index).getCantidad() + PR_Combo.getCantidad();
								if(!PR_Combo.getPR_Observacion().isEmpty()){
									String Nueva_observacion = Arreglo.get(index).getPR_Observacion() +" "+ PR_Combo.getPR_Observacion();
									Arreglo.get(index).setPR_Observacion(Nueva_observacion);
								}
								Arreglo.get(index).setCantidad(nueva_cant);
								
							}
							else{
								PR_Combo.setCantidad(PR_Combo.getCantidad()*Fila.getInt("PP_producto_cantidad"));
								Arreglo.add(PR_Combo);
							}
						}
					}	

				}
				conex.cerrarConexion();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
			}
			return Arreglo;
		}
	
		
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		public ArrayList<Pedido> getLISTA_PEDIDOS_COCINA(Calendar Fecha_mostrar) {
			if(Fecha_mostrar==null)
				return null;
			ArrayList<Pedido> Arreglo = new ArrayList<Pedido>();
			try {
				conex.connectToMySQL();// Conectar base
				Statement st = conex.conexion.createStatement();
				String FECHA_FILTRO = formato_yyyyMMdd.format(Fecha_mostrar.getTime());
				String Query = "select P.PD_Delivery, P.PD_id, P.PD_numero,  P.PD_fecha_pedido, EST.PEST_nombre, C.CL_nombre, C.CL_direccion, C.CL_telefono,SUM(PP.PP_precio * PP.PP_producto_cantidad) as Precio "
						+" from  Pedido P join producto_pedidos PP join Pe_estado EST join Cliente C  on C.cl_id= P.PD_cliente and  P.PD_id= PP.PP_pedidoid and P.PD_fecha_pedido='"+ FECHA_FILTRO +"' and P.PD_estado= EST.Pest_id"
						 +"  group by P.PD_id";
				st.executeQuery(Query);
				
				
				ResultSet Fila = st.getResultSet();
				while (Fila.next()) {
					Pedido P = new Pedido();
					P.setID_DIARIO(Fila.getInt("PD_numero"));
					P.setNumero_Pedido(Fila.getInt("PD_ID"));
					P.setFecha_Hora_Pedido(Fila.getDate("PD_fecha_pedido"));
					P.setESTADO(Fila.getString("PEST_nombre"));
					P.setCliente(new Cliente (Fila.getString("CL_nombre")));
					P.getCliente().setDomicilio(Fila.getString("CL_direccion"));
					P.getCliente().setTelefono_Fijo(Fila.getString("CL_telefono"));
					P.setTotal(Fila.getDouble("Precio"));
					Boolean delivery = false;
					if(Fila.getInt("PD_Delivery")==1){
						delivery = true;
					}
					P.setEs_Delivery(delivery);
					P.setLista_Productos(getLista_Productos_Cocina(P.getNumero_Pedido()));
					Arreglo.add(P);

				}
				conex.cerrarConexion();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
			}
			return Arreglo;
		}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean ELIMINAR_PEDIDO(Pedido P) {
		String SentenciaSQL = "UPDATE Pedido SET PD_estado = 5 where PD_id= " +P.getNumero_Pedido() ;
		return conex.Insertar(SentenciaSQL);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean MODIFICAR_ESTADO(Pedido P, Integer ID_ESTADO) {
		String SentenciaSQL = "UPDATE Pedido SET PD_estado = "+ ID_ESTADO+ " where PD_id= " +P.getNumero_Pedido() ;
		System.out.println(SentenciaSQL);
		return conex.Insertar(SentenciaSQL);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean MODIFICAR_PEDIDO(Pedido p) {
		
		int estado = obtenerEstado(p.getESTADO());
		Integer cliente = 1;
		if(p.getCliente()!=null)
			cliente = p.getCliente().getID_Cliente();
		boolean delivery = p.getEs_Delivery();
		String Fecha = formato_yyyyMMdd.format(p.getFecha_Hora_Pedido());
		String SentenciaSQL = "UPDATE Pedido SET "
				+ "PD_fecha_pedido = '" + Fecha + "', "
				+ "PD_estado = " + estado + ", " 
				+ "PD_cliente = " + cliente + ","
				+ "PD_Delivery = " + delivery
				+ " WHERE Pedido.PD_id=" + p.getNumero_Pedido() + ";";
		return conex.Insertar(SentenciaSQL);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Pedido OBTENER_PEDIDO(Integer Numero_Pedido) {
		Pedido P = null;
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String SentenciaSQL = "select  *,SUM(PP.PP_precio) as Precio" +
			" from  Pedido P join producto_pedidos PP join Pe_estado EST  on P.PD_id= PP.PP_pedidoid and P.PD_estado= EST.Pest_id AND " +
			"P.PD_ID = "+ Numero_Pedido;
			Integer ID_Cliente = null;
			st.executeQuery(SentenciaSQL);
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){
				P = new Pedido();
				P.setID_DIARIO(Fila.getInt("PD_numero"));
				P.setNumero_Pedido(Fila.getInt("PD_id"));
				P.setFecha_Hora_Pedido(Fila.getDate("PD_fecha_pedido"));
				P.setESTADO(Fila.getString("PEST_nombre"));
				Boolean delivery = false;
				if(Fila.getInt("PD_Delivery")==1){
					delivery = true;
				}
				P.setEs_Delivery(delivery);
				ID_Cliente = Fila.getInt("PD_CLIENTE");
			}
			conex.cerrarConexion();
			
			// Obtiene la lista de productos asociado a ese pedido
			P.setLista_Productos(getLista_Productos(P));
			
			ArrayList<Producto> lista = P.getLista_Productos();
			
			for (int i = 0; i < lista.size(); i++){
				P.setTotal(P.getTotal() + (lista.get(i).getCantidad()*lista.get(i).getPR_precio()));
			}
			
			// Obtiene datos del cliente si este no es NULL
			if(ID_Cliente!=null)
				P.setCliente(getCliente(ID_Cliente));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return P;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private Cliente getCliente(Integer ID_Cliente) {
		Cliente cliente = new Cliente();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Cliente WHERE CL_id = "+ID_Cliente);
			ResultSet Fila = st.getResultSet();
			Fila.first();
			cliente.setID_Cliente(Fila.getInt("CL_id"));
			cliente.setNombre(Fila.getString("CL_nombre"));
			cliente.setDomicilio(Fila.getString("CL_direccion"));
			cliente.setTelefono_Fijo(Fila.getString("CL_telefono"));
			cliente.setDetalle(Fila.getString("CL_Detalle"));
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return cliente;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private int obtenerEstado(String estado){
		int resultado = 1;
		conex.connectToMySQL();// Conectar base
		Statement st;
		try {
			st = conex.conexion.createStatement();
			String SentenciaSQL = "select P.PEST_id from PE_estado P where P.PEST_nombre= '"+ estado    +"'";
			ResultSet Fila = st.executeQuery(SentenciaSQL);
			
			while (Fila.next()){	
				resultado = Fila.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Object[] getTODOS_LOS_ESTADOS() {
		@SuppressWarnings("unused")
		EstadoDAOjdbcImpl estado = new EstadoDAOjdbcImpl();
		return new Object[] { "Pendiente", "Preparado", "Enviado","Cobrado","Cancelado"};
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean ELIMINAR_PRODUCTOS_DEL_PEDIDO(Pedido P) {
		String SentenciaSQL = "delete from producto_pedidos where producto_pedidos.PP_pedidoid = " +P.getNumero_Pedido() ;
		return conex.Insertar(SentenciaSQL);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean AGREGAR_PRODUCTO_PEDIDO(Pedido p){
		boolean resultado = false;
		Integer PEDIDO_ID = p.getNumero_Pedido();
		for (int i = 0; i < p.getLista_Productos().size(); i++) {
			
			Integer PRODUCTO_ID = p.getLista_Productos().get(i).getPR_id();
			Double PRECIO_ACTUAL = p.getLista_Productos().get(i).getPR_precio();
			String OBSERVACION =  p.getLista_Productos().get(i).getPR_Observacion();
			String SentenciaSQL_producto_pedidos = "INSERT INTO producto_pedidos (PP_pedidoid, PP_productoid, PP_producto_cantidad, PP_Observacion, PP_precio)"
					+ "VALUES ("+
					""+	 PEDIDO_ID									+","+	// INTEGER
					""+	 PRODUCTO_ID								+","+	// INTEGER
					""+	 p.getLista_Productos().get(i).getCantidad()+","+	// INTEGER
					"'"+ OBSERVACION								+" ',"+	// STRING
					""+  PRECIO_ACTUAL								+ ")";	// DOUBLE
			resultado = conex.Insertar(SentenciaSQL_producto_pedidos);
		}
		return resultado;
		
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// SOLO PARA TICKET
	public Pedido OBTENER_PEDIDO_COMBOS_DESP(Integer Numero_Pedido) {
		Pedido P = null;
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String SentenciaSQL = "SELECT "+
					"*,SUM(PP.PP_precio) as Precio "+ 
					"FROM "+
					"Pedido P "+ 
					"JOIN "+
					"producto_pedidos PP "+ 
					"JOIN "+
					"Cliente C "+ 
					"on "+ 
					"P.PD_id=PP.PP_pedidoid "+ 
					"AND "+
					"P.PD_cliente=C.CL_id "+ 
					"AND "+
					"P.PD_ID = "+ Numero_Pedido;
			
			Integer ID_Cliente = null;
			st.executeQuery(SentenciaSQL);
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){
				P = new Pedido();
				P.setID_DIARIO(Fila.getInt("PD_numero"));
				P.setNumero_Pedido(Fila.getInt("PD_id"));
				P.setFecha_Hora_Pedido(Fila.getDate("PD_fecha_pedido"));
				P.setTotal(Fila.getDouble("Precio"));
				P.setLista_Productos(getLista_Productos_DESP(P.getNumero_Pedido()));
				Boolean delivery = false;
				if(Fila.getInt("PD_Delivery")==1){
					delivery = true;
				}
				P.setEs_Delivery(delivery);
				
				ID_Cliente = Fila.getInt("PD_CLIENTE");
				P.setCliente(new Cliente (Fila.getString("CL_nombre")));
				P.getCliente().setDomicilio(Fila.getString("CL_direccion"));
				P.getCliente().setTelefono_Fijo(Fila.getString("CL_telefono"));

			}
			conex.cerrarConexion();
						
			// Obtiene datos del cliente si este no es NULL
			if(ID_Cliente!=null)
				P.setCliente(getCliente(ID_Cliente));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return P;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// SOLO PARA TICKET
	private ArrayList<Producto> getLista_Productos_DESP(Integer ID_Pedido) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String Query = "select PR.PR_id, P.PD_id, PR.Pr_nombre, PP.PP_producto_cantidad, PP.PP_Observacion, PP.PP_precio, T.TP_id, T.TP_nombre from "+ 
			"Producto PR join Producto_pedidos PP join Pedido P join tipo_producto T "+ 
			"on T.TP_id= PR.PR_tipo_producto and PR.Pr_id=PP.PP_productoid and P.PD_id=PP.PP_pedidoid and P.PD_id=" + ID_Pedido;

			st.executeQuery(Query);
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				if (Fila.getInt("TP_id")!= 4){	// 4 = TIPO 'COMBO'
					Producto Prod = new Producto();
					Prod.setPR_id(Fila.getInt("PR_id"));
					Prod.setPR_nombre(Fila.getString("PR_nombre"));
					Prod.setPR_precio(Fila.getDouble("PP_precio"));
					Prod.setPR_Observacion(Fila.getString("PP_Observacion"));
					Prod.setPR_TIPO_PRODUCTO_STRING(Fila.getString("TP_nombre"));
					Prod.setCantidad(Fila.getInt("PP_producto_cantidad"));
					Prod.setPR_tipo_producto(Fila.getInt("TP_id"));
					
					boolean esta = Arreglo.contains(Prod);
					if(esta){
						Integer index = Arreglo.indexOf(Prod);
						Integer nueva_cant = Arreglo.get(index).getCantidad() + Prod.getCantidad();
						if(!Prod.getPR_Observacion().isEmpty()){
							String Nueva_observacion = Prod.getPR_Observacion() +" "+ Arreglo.get(index).getPR_Observacion();
							Arreglo.get(index).setPR_Observacion(Nueva_observacion);
						}
						Arreglo.get(index).setCantidad(nueva_cant);
						
					}
					else
						Arreglo.add(Prod);
				}else{	// SI ES UN COMBO:
					String NombreCombo = Fila.getString("PR_nombre");

					// Agrego el combo con su nombre precio y cantidad
					Producto COMBO = new Producto();
					COMBO.setPR_id(0);
					COMBO.setPR_nombre(NombreCombo);
					COMBO.setPR_Observacion(Fila.getString("PP_Observacion"));
					COMBO.setPR_precio(Fila.getDouble("PP_precio"));
					COMBO.setCantidad(Fila.getInt("PP_producto_cantidad"));
					COMBO.setPR_TIPO_PRODUCTO_STRING("COMBO");
					COMBO.setPR_tipo_producto(Fila.getInt("TP_id"));
					Arreglo.add(COMBO);
					
					ArrayList<Producto> combo_producto =  svCombo.getLista_Productos(NombreCombo);
					
					// Se agrega solo los productos de los combos, pero estos NO incluyen precio
					for (int i =0;i<combo_producto.size(); i++){ 
						// si existe el ID del productoCombo, entonces sumale prcombo.getCantidad a el ID del arreglo
						Producto PR_Combo = combo_producto.get(i);
						PR_Combo.setCantidad(PR_Combo.getCantidad()*Fila.getInt("PP_producto_cantidad"));
						PR_Combo.setPR_precio(0.0);
						PR_Combo.setPR_tipo_producto(Fila.getInt("TP_id"));
						PR_Combo.setPR_nombre("-"+PR_Combo.getPR_nombre());
						PR_Combo.setPR_TIPO_PRODUCTO_STRING("  "+PR_Combo.getPR_TIPO_PRODUCTO_STRING());
						Arreglo.add(PR_Combo);

					}
				}	

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
}//---> FIN
