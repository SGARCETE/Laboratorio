package MetAux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class ManejoArchivos {

	/*----------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile) throws IOException {//java 6 String NombreArchivo 
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }
	    FileChannel origen = null;
	    FileChannel destino = null;
	    
	    try {
//	    	destFile = new File(destFile.getPath()+"\\"+NombreArchivo);
	        origen = new FileInputStream(sourceFile).getChannel();
	        destino = new FileOutputStream(destFile).getChannel();
	        long count = 0;
	        long size = origen.size();             
	        while((count += destino.transferFrom(origen, count, size-count))<size);
	    }
	    finally {
	        if(origen != null) {
	            origen.close();
	        }
	        if(destino != null) {
	            destino.close();
	        }
	    }
	}
	

	@SuppressWarnings("resource")
	public static void moveFile(File sourceFile, File destFile) throws IOException {//java 6
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }
	    FileChannel origen = null;
	    FileChannel destino = null;
	    try {
	        origen = new FileInputStream(sourceFile).getChannel();
	        destino = new FileOutputStream(destFile).getChannel();
	        long count = 0;
	        long size = origen.size();             
	        while((count += destino.transferFrom(origen, count, size-count))<size);
	    }
	    finally {
	        if(origen != null) {
	            origen.close();
	            sourceFile.delete();
	        }
	        if(destino != null) {
	            destino.close();
	        }
	    }
	}
	
    public static void copyFile_Java7(String origen, String destino) throws IOException { //java7
        Path FROM = Paths.get(origen);
        Path TO = Paths.get(destino);
        //sobreescribir el fichero de destino, si existe, y copiar los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{
	        StandardCopyOption.REPLACE_EXISTING,
	        StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(FROM, TO, options);
    }
	
	/** Hay que espeficicar el formato , hay qe arreglarlo, te edita un fichero que no vas a usar....**/

    public boolean rename_fichero(String Original, String renombrar) {
        File fichero = new File(Original);
        File fichero2 = new File(renombrar);
        //EJEMPLO
        //File fichero = new File("datos.txt");
        //File fichero2 = new File("datos.csv");
        return fichero.renameTo(fichero2);
    }
}
