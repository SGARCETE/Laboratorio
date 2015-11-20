package Negocio.Servicios;

import java.awt.EventQueue;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;

//import javax.swing.JOptionPane;

public class Ejecutar_WILDSOFT {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//					try {
//						JOptionPane.showMessageDialog(null, "Espere un momento, se esta iniciando el sistema","Iniciando", JOptionPane.INFORMATION_MESSAGE);
//						Process p = Runtime.getRuntime().exec("C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql -u root -proot test");
//						OutputStream os = p.getOutputStream();
//						FileInputStream fis = new FileInputStream("../WildSoft/init.sql");
//						byte buffer[] = new byte[1024];
//						int leido = fis.read(buffer);
//						while (leido > 0) {
//							System.out.println(leido);
//							os.write(buffer, 0, leido);
//							leido = fis.read(buffer);
//						}
//						os.close();
//						fis.close();
//						JOptionPane.showMessageDialog(null, "Bienvenido","", JOptionPane.INFORMATION_MESSAGE);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
				new Principal_Negocio_Interfaz();
			}
		});
	}
}
