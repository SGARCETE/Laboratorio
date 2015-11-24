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
				new Principal_Negocio_Interfaz();
			}
		});
	}
}
