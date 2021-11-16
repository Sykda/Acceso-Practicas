package Ejercicio_4;


public class InformacionDeMaquina {

	public static void main(String[] args) {

		Runtime runtime = Runtime.getRuntime();
		
		int megas = 1024 * 1024;

		System.out.println("Información del pc:\n");
		System.out.println("\tEl número de procesadores es : " + runtime.availableProcessors());
		System.out.println("\tMemoria máxima: " + runtime.maxMemory()/megas+ " MB");
		System.out.println("\tMemoria total: " + runtime.totalMemory()/megas + " MB");
		System.out.println("\tMemoria libre: " + runtime.freeMemory()/megas + " MB");
		System.out.println("\tMemoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) /megas+ " MB");

	}

}
