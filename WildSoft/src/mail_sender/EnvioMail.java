package mail_sender;

import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class EnvioMail {
	private String usuarioCorreo;
	private String password;
	private String rutaArchivo;
	private String nombreArchivo;
	@SuppressWarnings("unused")
	private String destinatario;
	@SuppressWarnings("unused")
	private ArrayList<String> Destinatarios, arrayAdjuntos;
	private String asunto;
	private String mensaje;
	private InformeDeEnvio Informe = new InformeDeEnvio();
    
    public EnvioMail(String usuarioCorreo, String password, String rutaArchivo, String nombreArchivo, ArrayList<String> destinatarios, String asunto,String mensaje) {
        this.usuarioCorreo = usuarioCorreo;
        this.password = password;
        this.rutaArchivo = rutaArchivo;
        this.nombreArchivo = nombreArchivo;
//        this.destinatario = destinatario;
        this.Destinatarios = destinatarios;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }
    
    public EnvioMail(String usuarioCorreo, String password, ArrayList<String> rutaArchivos, String nombreArchivo, ArrayList<String> destinatarios, String asunto,String mensaje) {
        this.usuarioCorreo = usuarioCorreo;
        this.password = password;
        this.arrayAdjuntos = rutaArchivos;
        this.nombreArchivo = nombreArchivo;
//        this.destinatario = destinatario;
        this.Destinatarios = destinatarios;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }
    
    public EnvioMail(String usuarioCorreo, String password, String rutaArchivos, String nombreArchivo, String destinatario, String asunto,String mensaje) {
        this.usuarioCorreo = usuarioCorreo;
        this.password = password;
//        this.arrayAdjuntos = rutaArchivos;
        this.nombreArchivo = nombreArchivo;
        this.destinatario = destinatario;
//        this.Destinatarios = destinatarios;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public EnvioMail(String usuarioCorre, String password, ArrayList<String> destinatarios,String mensaje){
        this(usuarioCorre,password,"","",destinatarios,"",mensaje);
    }
    
    public EnvioMail(String usuarioCorre, String password, ArrayList<String> destinatarios,String asunto,String mensaje){
        this(usuarioCorre,password,"","",destinatarios,asunto,mensaje);
    }    
    
	public boolean sendMail(boolean MostrarInforme){
    	if(MostrarInforme){
	    	Informe.setVisible(true);
    	}
    	try{
    		Informe.SetPreparando();
    		Properties props = new Properties();         
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", usuarioCorreo);
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);

            BodyPart adjunto = new MimeBodyPart();
            if (!rutaArchivo.equals("")){
                 adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaArchivo)));
                 adjunto.setFileName(nombreArchivo);                
            }

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);

            if (!rutaArchivo.equals("")){
                multiParte.addBodyPart(adjunto);
            }

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuarioCorreo));
          
			// Destinatarios varios
			for (int i = 0; i < Destinatarios.size(); i++) {
				message.addRecipient(
			    Message.RecipientType.TO,
			    new InternetAddress(Destinatarios.get(i)));
			    message.setSubject(asunto);
			}
         	message.setContent(multiParte);
			
            Transport t = session.getTransport("smtp");
			Informe.SetConectando();
            t.connect(usuarioCorreo, password);
			Informe.SetEnviando();
            t.sendMessage(message, message.getAllRecipients());
            t.close();
			Informe.SetEnviado(true);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            Informe.SetEnviado(false);
            return false;
        }        
    }
    
    /**
     * @param MostrarInforme Abre una ventana con el informe del email
     * @param SERVIDOR ejemplo: para cuentas de google "smtp.gmail.com", otro ejemplo para otro servidor "wo06.wiroos.com" (Depende de lo que dice despues del arroba --> @DEESTODEPENDE.com) 
     * @param PUERTO   ejemplo "587". Depende del servidor que se elija
     */
    public boolean sendMailHTML(boolean MostrarInforme,String SERVIDOR, String PUERTO){
    	if(MostrarInforme){
	    	Informe.setVisible(true);
    	}
    	try{
    		Informe.setTitulo("Destino: "+Destinatarios.get(0));
    		Properties props = new Properties();
            props.put("mail.smtp.host", SERVIDOR);//"wo06.wiroos.com" o "smtp.gmail.com"
            props.setProperty("mail.smtp.starttls.enable", "true");
//          props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.port", PUERTO);//"587");
            props.setProperty("mail.smtp.user", usuarioCorreo);
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);

            BodyPart adjunto = new MimeBodyPart();
            if (!rutaArchivo.equals("")){
                 adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaArchivo)));
                 adjunto.setFileName(nombreArchivo);                
            }

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            if (!rutaArchivo.equals("")){
                multiParte.addBodyPart(adjunto);
            }

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuarioCorreo));
          
			// Destinatarios varios
			for (int i = 0; i < Destinatarios.size(); i++) {
				message.addRecipient(
			    Message.RecipientType.TO,
			    new InternetAddress(Destinatarios.get(i)));
			    message.setSubject(asunto);
			}
			message.setContent(mensaje,"text/html");
			Informe.SetConectando();
            Transport t = session.getTransport("smtp");
            t.connect(usuarioCorreo, password);
            Informe.SetEnviando();
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            Informe.SetEnviado(true);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            Informe.SetEnviado(false);
            return false;
        }        
    }
  
    /**	TEST	ESTO ES SOLO PARA PROBAR!	**/
    public static void main(String[] args){
		String clave = "wildsoft1234";
		String Correo_del_que_envia = "server.wildsoft@gmail.com";
		ArrayList<String> ListaDeReceptores = new ArrayList<String>();
		//ArrayList<String> AdjuntosVarios = new  ArrayList<String>();
		String Ruta_adjunto = "D:\\Users\\Lukas\\Desktop\\14746.jpg";
		String Nombre_Adjunto = "Adjunto.jpg";
		
		ListaDeReceptores.add("tolosa.lmt@gmail.com");
		ListaDeReceptores.add("federico.evf@gmail.com");
		ListaDeReceptores.add("santi101093@hotmail.com.ar");
		ListaDeReceptores.add("nicolas_cabral21@hotmail.com");
		
		//AdjuntosVarios.add("D:\\Users\\Lukas\\Desktop\\14746.jpg");

		//"D:\\imagenes PNG\\1_gohan.png"
		//"D:\\Documents\\Downloads\\wordfe.docx"
		
		EnvioMail e = new EnvioMail(Correo_del_que_envia, clave ,Ruta_adjunto, Nombre_Adjunto ,ListaDeReceptores ,"ESTO ES UNA PRUEBA, NO TE ASUSTES", "HOLA PROBANDO PROBANDO HOLA 1 2 3 4 HOLA PROBANDOOO PROBANDOOOOOO bue");
		System.out.println("enviando...");
		if (e.sendMail(false)){
		    JOptionPane.showMessageDialog(null,"El email se mando correctamente");
		}else{
		    JOptionPane.showMessageDialog(null,"El email no se mando correctamente");
		}
    }
    
    

}