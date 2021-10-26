package es.florida.AE2_NEO;


public class Neo {
	
	public double probabilidadColision(double posicionNeo, double velocidadNeo) {
		double posicionTierra =1;
		double velocidadTierra =100;
		for(int i =0;i<(50*365*24*60*60);i++) {
			posicionNeo = posicionNeo + velocidadNeo*i;
			posicionTierra = posicionTierra + velocidadTierra *i;
		}
		double resultado = 100 * Math.random() * Math.pow( ((posicionNeo-posicionTierra)/(posicionNeo+posicionTierra)), 2);
		return resultado;
	}
	
	public static void main(String[] args) {
		Neo neo = new Neo();
		double posicionNeo=Double.parseDouble(args[0]);
		double velocidadNeo=Double.parseDouble(args[1]);
		double resultado = neo.probabilidadColision(posicionNeo, velocidadNeo);
		System.out.print("La probabilidad de colision es del: "+resultado+" %");
	}
	
	
}
