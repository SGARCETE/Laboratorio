package mail_sender;

import java.util.ArrayList;

import Negocio.Modelo.Solicitud_compra;
import Negocio.Servicios.Principal_Negocio_Interfaz;

public class Email_Manager {

	private String Correo = "wildsoft1234";
	private String Password = "server.wildosft@gmail.com";
	private String SERVIDOR = "smtp.gmail.com";
	private String PUERTO = "587";
	
	private String ASUNTO_MENSAJE = "";
	private String MENSAJE_HTML = "";

	private Solicitud_compra solicitud_compra;
	private boolean EXITO = false;
	private ArrayList<String> Destinatarios;
	private Principal_Negocio_Interfaz PNI;

	public Email_Manager(Principal_Negocio_Interfaz pni){
		PNI = pni;
	}

	public void ENVIAR_SOLICITUD_DE_COMPRA(String destinatarios, Solicitud_compra Solicitud) {
		
		Destinatarios.add(destinatarios);
		
		solicitud_compra = Solicitud;
		//		Destinatarios = destinatarios;
		
		// armar correo con informacion adicional	
		Mensaje_HTML(Solicitud);
		
		// enviar datos del viaje al cliente
        Thread t = new Thread(){  
            public void run(){  
            	EnvioMail e = new EnvioMail(Correo, Password, solicitud_compra.getSolicitudPDF().getAbsolutePath(), solicitud_compra.getSolicitudPDF().getName(), Destinatarios, ASUNTO_MENSAJE, MENSAJE_HTML);
            	EXITO = e.sendMailHTML(true,SERVIDOR,PUERTO);
            	if(EXITO){
            		// paso 6: guardar que se envio
            		PNI.getSvSolicitudCompra().Registrar_Envio_Solicitud(solicitud_compra.getId());
            	}
            }  
        };  
        t.start();
	}
	
	
	
	private void Mensaje_HTML(Solicitud_compra solicitud_compra){
		ASUNTO_MENSAJE = "Solicitud de Compra Nº"+solicitud_compra.getId()+" - Pizzeria WILDSOFT";
		
		MENSAJE_HTML = 	"<p>Estimados proveedor,</p>"+
					
						"<p><strong>a continuacion se adjunta un listado de productos</strong></p>"+
						
						"<p><strong>para que pueda realizarnos el presupuesto</strong></p>"+
						
						"<p>Muchas gracias!</p>"+
						
						"<p><em>Atte Pizzeria Wildsoft</em></p>";
	}


	
	
}//---> FIN CLASE
