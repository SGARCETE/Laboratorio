package Negocio.Servicios;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Negocio.Servicios.Servicio_Clientes;
import mail_sender.Email_Manager;
//import mail_sender.Email_Manager;
import Interfaz.Interfaz_Principal;

public class Principal_Negocio_Interfaz {

	/** ESTA CLASE COMUNICA TODAS LAS INTERFACES, CON LA LOGICA DE NEGOCIO, LA INTERFAZ SOLO PUEDE
	 * COMUNICARSE CON EL NEGOCIO POR MEDIO DE ESTA CLASE
	 */
	/* Interfaz Principal*/
	private Interfaz_Principal window;
	/* INICIALIZADOR DE SERVICIOS 	[NOTA: SOLO SE INICIALIZAN LOS SERVICIOS UNA Y SOLO UNA VEZ, Y ES ACA] */
	private Servicio_Pedidos 			SvPedidos = new Servicio_Pedidos();
	private Servicio_Productos 			SvProductos = new Servicio_Productos();
	private Servicio_Clientes  			SvClientes = new Servicio_Clientes();
	private Servicio_Repartidores 		SvRepartidores = new Servicio_Repartidores();
	private Servicio_Proveedores 		SvProveedores = new Servicio_Proveedores();
	private Servicio_Materia_Prima  	SvMateria_Prima = new Servicio_Materia_Prima();
	private Servicio_Solicitud_compra	SvSolicitudCompra = new Servicio_Solicitud_compra();
	private Servicio_entrega			svEntrega = new Servicio_entrega();
	private Email_Manager 				email_manager = new Email_Manager(this);
	
	/**############################################################################**/
	/**############################################################################**/
	/**				Crea una e inicializa una la Interfaz_Principal					**/
	public Principal_Negocio_Interfaz(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");	// LookAndFeel por defecto: Nimbus
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		window = new Interfaz_Principal(this);			/* Creo una instancia de Interfaz_principal*/
		window.frmWildsoft.setVisible(true);
	    SwingUtilities.updateComponentTreeUI(window.frmWildsoft);
	}
	/**############################################################################**/
	/**############################################################################**/
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Interfaz_Principal getInstancia_Interfaz_Principal() {
		return window;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Servicio_Pedidos getSvPedidos() {
		return SvPedidos;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Servicio_Productos getSvProductos() {
		return SvProductos;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Servicio_Clientes getSvClientes() {
		return SvClientes;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Servicio_Repartidores getSvRepartidores() {
		return SvRepartidores;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Servicio_Proveedores getSvProveedores() {
		return SvProveedores;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Servicio_Materia_Prima getSvMateriaPrima() {
		return SvMateria_Prima;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Servicio_Solicitud_compra getSvSolicitudCompra() {
		return SvSolicitudCompra;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Servicio_entrega getSvEntrega() {
		return svEntrega;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	public Email_Manager getEmail_manager() {
		return email_manager;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}//---> FIN CLASE
