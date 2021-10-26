package es.florida.AE2_NEO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.print("El ordenador tiene "+cores+" cores"+"\n");
		double posicionNeo;
		double velocidadNeo;
		
		try {
			long tiempoInicio=System.nanoTime();
			Neo neo = new Neo();
			DecimalFormat df = new DecimalFormat("#.00");
			FileReader fr = new FileReader("NEOs.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			
			while(linea != null) {
			String[] arreglo =linea.split(",");
			System.out.print("------------------------------------"+"\n");
			System.out.println("Nombre del meteorito: "+arreglo[0]);
			System.out.println("Posicion relativa a la Tierra: "+arreglo[1]);
			System.out.println("Velocidad en km/s relativa al Sol: "+arreglo[2]);
			posicionNeo=Double.parseDouble(arreglo[1]);
			velocidadNeo=Double.parseDouble(arreglo[2]);
			System.out.println("La probabilidad de colision es: "+df.format(neo.probabilidadColision(posicionNeo,velocidadNeo))+"%");
			if(neo.probabilidadColision(posicionNeo, velocidadNeo)>=10) {
				System.out.print("ALERTA MUNDIAL POSIBLE COLISION"+"\n");
			}else {
				System.out.print("Tranquilidad, no hay de que preocuparse."+"\n");
			}
			
			long tiempoFin = System.nanoTime();
			long duracion = (tiempoFin - tiempoInicio)/1000000;
			System.out.println("Tiempo de ejecucion: "+duracion+" ms"+"\n");
			System.out.print("------------------------------------"+"\n");
			linea = br.readLine();
			Thread.sleep(100);
			}
			fr.close();
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void calcularProb(double posicionNeo, double velocidadNeo) {
		String clase = "es.florida.AE2_NEO.Neo";
		try {
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome+File.separator+"bin"+File.separator+"java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;
			
			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(Double.toString(posicionNeo));
			command.add(Double.toString(velocidadNeo));
			
			ProcessBuilder builder = new ProcessBuilder(command);
			Process process = builder.inheritIO().start();
			process.waitFor();
			System.out.println(process.exitValue());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
