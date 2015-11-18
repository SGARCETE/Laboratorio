package Persistencia.Conector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class Backup {

	public static void backup(String directorio) {
		try {
			Runtime runtime = Runtime.getRuntime();
			File backupFile = new File(directorio,"BackUp.sql");
			FileWriter fw = new FileWriter(backupFile);
			Process child = runtime.exec("C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump --opt --password=root --user=root --databases wildsoft");
			InputStreamReader irs = new InputStreamReader(child.getInputStream());
			BufferedReader br = new BufferedReader(irs);
			String line;
			while ((line = br.readLine()) != null) {
				fw.write(line + "\n");
			}
			fw.close();
			irs.close();
			br.close();
			JOptionPane.showMessageDialog(null, "Se ha generado un respaldo de la base de datos","Verificar", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error no se genero el archivo por el siguiente motivo:"
							+ e.getMessage(), "Verificar",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void restore() {
		try {
			// Ejecucion del cliente mysql
			Process p = Runtime.getRuntime().exec("C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql -u root -proot wildsoft");

			// Lectura de la salida de error y se muestra por pantalla.
			InputStream es = p.getErrorStream();
			muestraSalidaDeError(es);

			// Lectura del fichero de backup y redireccion a la entrada estandar
			// de mysql.
			OutputStream os = p.getOutputStream();
			FileInputStream fis = new FileInputStream("../WildSoft/BackUp.sql");

			byte buffer[] = new byte[1024];
			int leido = fis.read(buffer);
			while (leido > 0) {
				System.out.println(leido);
				os.write(buffer, 0, leido);
				leido = fis.read(buffer);
			}
			os.close();
			fis.close();
			JOptionPane.showMessageDialog(null, "Se ha actualizado la base de datos",
					"Verificar", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Se saca por pantalla la salida de errores del comando, por si acaso.
	 * 
	 * @param es
	 *            El InputStream de donde leer los errores del comando mysql.
	 */
	private static void muestraSalidaDeError(final InputStream es) {
		Thread hiloError = new Thread() {
			public void run() {
				try {
					byte[] buffer = new byte[1024];
					int leido = es.read(buffer);
					while (leido > 0) {
						System.out.println(new String(buffer, 0, leido));
						leido = es.read(buffer);
					}
					es.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		hiloError.start();
	}

}
