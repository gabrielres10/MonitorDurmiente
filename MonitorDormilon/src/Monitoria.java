//MONITOR DORMILON 
//autores: Luis Fernando Botero, Juan Camilo Gonzalez, Gabriel Restrepo. 

import java.util.concurrent.Semaphore;

public class Monitoria{
	public static void main(String[] args) {


		// Instanciar los semáforos requeridos
		Semaphore silla1;
		Semaphore silla2;
		Semaphore silla3;
		Semaphore sillaMonitor;
		Monitor monitor;
		int numeroEstudiantes=7;
		Estudiante estudiantes[]=new Estudiante[numeroEstudiantes];
		
		silla1 = new Semaphore(1, true);
		silla2 = new Semaphore(1, true);
		silla3 = new Semaphore(1, true);
		sillaMonitor = new Semaphore(1, true);
		
		monitor = new Monitor(sillaMonitor, silla1, silla2, silla3);
		monitor.start();
		for(int i=1; i<=numeroEstudiantes; i++){
			estudiantes[i-1]=new Estudiante(sillaMonitor, silla1, silla2, silla3, i+"");
			estudiantes[i-1].start();
			try{
				Thread.sleep(500);
			} catch(Exception e) {}
		}
		while(true){
			if(!estudiantes[estudiantes.length-1].isAlive()){
				break;
			}
		}
		monitor.stopThread();
	}
}
