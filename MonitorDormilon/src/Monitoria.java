//MONITOR DORMILON 
/*
	Autores: 
	Luis Fernando Botero, 
	Juan Camilo Gonzalez, 
	Gabriel Restrepo. 
*/
import java.util.concurrent.Semaphore;
/**
 * Clase principal que ejecuta el programa
 */
public class Monitoria{
	/**
	 * Método principal del programa
	 * @param args Argumentos de la línea de comandos
	 */
	public static void main(String[] args) {

		// Declaración de variables
		Semaphore silla1;
		Semaphore silla2;
		Semaphore silla3;
		Semaphore sillaMonitor;

		// Creación de objetos
		Monitor monitor;
		silla1 = new Semaphore(1, true);
		silla2 = new Semaphore(1, true);
		silla3 = new Semaphore(1, true);
		sillaMonitor = new Semaphore(1, true);
		
		// Creación de estudiantes
		int numeroEstudiantes=7;
		Estudiante estudiantes[]=new Estudiante[numeroEstudiantes];

		// Creación de monitor
		monitor = new Monitor(sillaMonitor, silla1, silla2, silla3);

		// Inicialización de hilos
		monitor.start();
		for(int i=1; i<=numeroEstudiantes; i++){
			estudiantes[i-1]=new Estudiante(sillaMonitor, silla1, silla2, silla3, i+"");
			estudiantes[i-1].start();
			try{
				Thread.sleep(500);
			} catch(Exception e) {}
		}

		// Esperar a que todos los estudiantes terminen
		for (int i=numeroEstudiantes-1; i>=0; i--){
			while(estudiantes[i].isAlive()){
				;
			}
		}

		// Detener el monitor
		monitor.stopThread();

	}
}
