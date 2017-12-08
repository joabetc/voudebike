package br.com.scopus.treinamento;

public class Util {
	
	public static boolean distanciaValida(double lat1, double lat2, double lon1, double lon2){
		
		final double DISTPERMITIDA = 5000;
		double distAtual = calculaDistancia(lat1,lat2,lon1,lon2, 0, 0);
					
		if(distAtual <= DISTPERMITIDA){
			return true;
		}			
		
		return false;		
		
	}
	
	/* Calculate distance between two points in latitude 
	and longitude taking into account height difference. 
	If you are not interested in height difference pass 0.0.
	 Uses Haversine method as its base. lat1, lon1 Start point 
	lat2, lon2 End point el1 Start altitude in meters el2 End altitude 
	in meters */
	private static double calculaDistancia(double lat1, double lat2, double lon1, double lon2,
	        double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    Double latDistance = deg2rad(lat2 - lat1);
	    Double lonDistance = deg2rad(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;
	    distance = Math.pow(distance, 2) + Math.pow(height, 2);
	    return Math.sqrt(distance);
	}

	private static double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}

}
