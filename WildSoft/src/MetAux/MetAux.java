package MetAux;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Negocio.Modelo.Producto;

public class MetAux {

	
	public static boolean isSameDay2(Calendar cal1, Calendar cal2) {
	    if (cal1 == null || cal2 == null)
	        return false;
	    return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
	            && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) 
	            && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}
	
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
	
	
	
	/** Convierte de Calendar a Date
	 * @param cal recibe un Calendar */
	public static Date toDate(Calendar cal) {
		cal = Calendar.getInstance();
		return cal.getTime();
	}
	/*------------------------------------------------------------------------------*/
	
	
	
}
