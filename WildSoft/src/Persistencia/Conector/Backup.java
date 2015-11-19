package Persistencia.Conector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Backup {

	/**
	 * Genera un archivo .sql con la base de datos del sistema
	 * @param directorio Es el directorio donde se guardara el .sql generado
	 */
	public static void backup(String directorio) {
		try {
			Runtime runtime = Runtime.getRuntime();
			File backupFile = new File(directorio,"BackUp.sql");
			FileWriter fw = new FileWriter(backupFile);
			Process child = runtime.exec("C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump --opt --password=root --user=root --databases wildsoft");
			InputStreamReader irs = new InputStreamReader(child.getInputStream());
			BufferedReader br = new BufferedReader(irs);
			String line;
			while ((line = br.readLine()) != null) {
				fw.write(line + "\n");
			}
			fw.close();
			irs.close();
			br.close();
			JOptionPane.showMessageDialog(null, "Se ha generado un respaldo de la base de datos","", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"No se pudo generar la copia de seguridad" + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Restaura la base de datos a partir de un archivo .sql
	 * @param archivo Es el archivo .sql que contiene el backup
	 */
	public static void Restore(File archivo) {
		try {
			JOptionPane.showMessageDialog(null, "Espere un momento, se esta llevando a cabo la restauracion","Restaurando", JOptionPane.INFORMATION_MESSAGE);
			Process p = Runtime.getRuntime().exec("C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql -u root -proot wildsoft");
			InputStream es = p.getErrorStream();
			muestraSalidaDeError(es);
			OutputStream os = p.getOutputStream();
			FileInputStream fis = new FileInputStream(archivo);
			byte buffer[] = new byte[1024];
			int leido = fis.read(buffer);
			while (leido > 0) {
				System.out.println(leido);
				os.write(buffer, 0, leido);
				leido = fis.read(buffer);
			}
			os.close();
			fis.close();
			
			JOptionPane.showMessageDialog(null, "Se ha actualizado la base de datos","Verificar", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Se saca por pantalla la salida de errores del comando, por si acaso.
	 * @param input Es el InputStream de donde leer los errores del comando mysql.
	 */
	private static void muestraSalidaDeError(final InputStream input) {
		Thread hiloError = new Thread() {
			public void run() {
				try {
					byte[] buffer = new byte[1024];
					int leido = input.read(buffer);
					while (leido > 0) {
						System.out.println(new String(buffer, 0, leido));
						leido = input.read(buffer);
					}
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		hiloError.start();
	}
	
	public String Get_usuario(String ruta){
		File f = new File(ruta);
		Scanner s;
		String usuario= "";
		try {
			s = new Scanner(f);
			int i=0;
			
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*,\\s*");
				if (i ==0){
					usuario=  sl.next().toString().replace("Usuario: ", "");
				}
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public String Get_contraseņa(String ruta){
		File f = new File(ruta);
		Scanner s;
		String usuario= "";
		try {
			s = new Scanner(f);
			int i=0;
			
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*,\\s*");
				if (i ==1){
					usuario=  sl.next().toString().replace("Contraseņa: ", "");
				}
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
