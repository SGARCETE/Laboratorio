package MetAux;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import Negocio.Modelo.Producto;

public class MetAux {

	/*------------------------------------------------------------------------------*/
	public static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
	
	public static boolean isNumericDouble(String cadena){
    	try {
    		Double.parseDouble(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
	
	/*------------------------------------------------------------------------------*/
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
	    if (cal1 == null || cal2 == null)
	        return false;
	    return (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
	            && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) 
	            && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH));
	}

	/*------------------------------------------------------------------------------*/
	/** Ordena un arreglo segun la orden de viaje.**/
	public static ArrayList<Producto> mergeSort(ArrayList<Producto> Array) {
	    if (Array.size() <= 1) {
	    	return Array;
	    }
	    ArrayList<Producto> firstHalf  = new ArrayList<Producto>();
	    ArrayList<Producto> secondHalf = new ArrayList<Producto>();
	    for (int i = 0; i < Array.size() / 2; i++) {
	      firstHalf.add(Array.get(i));
	    }
	    for (int i = Array.size() / 2; i < Array.size(); i++) {
	      secondHalf.add(Array.get(i));
	    }
	    return merge(mergeSort(firstHalf), mergeSort(secondHalf));
	  }
	
	/*------------------------------------------------------------------------------*/
	private static ArrayList<Producto> merge(ArrayList<Producto> l1, ArrayList<Producto> l2) {
	    if (l1.size() == 0) {
	      return l2;
	    }
	    if (l2.size() == 0) {
	      return l1;
	    }
	    ArrayList<Producto> result = new ArrayList<Producto>();
	    Producto nextElement;
	    if (l1.get(0).getPR_tipo_producto() < l2.get(0).getPR_tipo_producto()) {// <<<<<<<<<<<<<<<<<----------------
	      nextElement = l1.get(0);
	      l1.remove(0);
	    } else {
	      nextElement = l2.get(0);
	      l2.remove(0);
	    }
	    result.add(nextElement);
	    result.addAll(merge(l1,l2));
	    return result;
	 }
	
	/*------------------------------------------------------------------------------*/
	/** Convierte de Calendar a Date
	 * @param cal recibe un Calendar */
	public static Date toDate(Calendar cal) {
		cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	/*------------------------------------------------------------------------------*/
	public static boolean comprobarEmail(String email) {
    	Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    	Matcher mat = pat.matcher(email);
    	if (mat.matches())
    		return true;
    	return false;
	}
	
	/*------------------------------------------------------------------------------*/
	public static void SoloNumEnteros(final JTextField JTF) {
		JTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if(JTF.getText().length()>=8) evt.consume();
				if(car<'0' || car>'9') 
					evt.consume();
			}
		});
	}
	
	/*------------------------------------------------------------------------------*/
	public static void SoloNumEnteros(final JTextField JTF, final Integer cant) {
		JTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if(JTF.getText().length()>=cant) evt.consume();
				if(car<'0' || car>'9') 
					evt.consume();
			}
		});
	}
	
	/*------------------------------------------------------------------------------*/
	public static void SoloNumerosDecimales(final JTextField JTF) {
		JTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if(car!='.' && (car<'0' || car>'9')) 
					evt.consume();
			}
		});		
		CastToDouble(JTF);
	}
	
	/*------------------------------------------------------------------------------*/
	public static void CastToDouble(final JTextField JTF) {
		JTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (JTF.getText().contains(".") && '.'==e.getKeyChar()) {
					e.consume();
				}
			}
		});
		JTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(JTF.getText().isEmpty())
					JTF.setText("0.0");
				String TEXTO = JTF.getText().trim();
				if(!JTF.getText().equals("")){
					double value = Double.parseDouble(TEXTO);
					JTF.setText(""+value);
				}
			}
		});
	}
	
	/*------------------------------------------------------------------------------*/
	public static void Limitar_caracteres(final JTextField JTF, final Integer cant) {
		JTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if(JTF.getText().length()>=cant) 
					evt.consume();
			}
		});
	}
	
	/*------------------------------------------------------------------------------*/
	
}//---> FIN
