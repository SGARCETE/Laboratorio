package mail_sender;

import java.util.ArrayList;

import Negocio.Modelo.Solicitud_compra;

public class Email_Manager {

	private String Correo = "server.wildsoft@gmail.com";
	private String Password = "wildsoft1234";
	
	private String ASUNTO_MENSAJE = "";
	private String MENSAJE_HTML = "";
	private String RUTA_ADJUNTO = "";
	private Solicitud_compra solicitud_compra;
	private boolean EXITO = false;
	private ArrayList<String> Destinatarios = new ArrayList<String>();

	public boolean ENVIAR_SOLICITUD_DE_COMPRA(Solicitud_compra Solicitud) {
		solicitud_compra = Solicitud;
		
		//	Destinatarios = destinatarios;

		
		Destinatarios.add(Solicitud.getProveedor().getMail());
		
		// Armar correo con informacion adicional	
		Mensaje_HTML(solicitud_compra);
		RUTA_ADJUNTO = solicitud_compra.getSolicitudPDF().getAbsolutePath();
		System.out.println("ENVIAR_SOLICITUD_DE_COMPRA:\nADJUNTO RUTA: "+RUTA_ADJUNTO);
		// Enviar datos del viaje al cliente (lo envia en otro hilo para que el programa no se cuelgue)
//        Thread t = new Thread(){  
//            public void run(){  
            	EnvioMail e = new EnvioMail(Correo, Password, RUTA_ADJUNTO, solicitud_compra.getSolicitudPDF().getName(), Destinatarios, ASUNTO_MENSAJE, MENSAJE_HTML);
            	EXITO = e.sendMailHTML(true);
//            }  
//        };  
//        t.start();
        return EXITO;
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
