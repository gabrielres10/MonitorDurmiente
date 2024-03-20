import java.util.Random;                // Importar la clase Random
import java.util.concurrent.Semaphore;  // Importar la clase Semaphore

/**
 * Clase que representa un estudiante
 */
public class Estudiante extends Thread{
    private Semaphore silla1;           // Silla 1 de la monitoria
    private Semaphore silla2;           // Silla 2 de la monitoria
	private Semaphore silla3;           // Silla 3 de la monitoria
    private Semaphore sillaMonitor;     // Silla del monitor
    private String nombre;              // Nombre del estudiante
	
	// Constructor de la clase. Inicializa todos los datos requeridos
	public Estudiante(Semaphore sillaMonitor, Semaphore silla1, Semaphore silla2, Semaphore silla3, String nombre) {
        this.silla1=silla1; 
        this.silla2=silla2; 
        this.silla3=silla3;
        this.sillaMonitor=sillaMonitor; 
        this.nombre=nombre;
	}
	
	// Método principal del hilo
    @Override
	public void run() {
		// Correr continuamente
		while (true) {
			try {
                if(silla3.availablePermits() == 0){
                    System.out.println("Estudiante "+nombre+": se fue a programar y volverá pronto.");
                    Thread.sleep(new Random().nextInt(2000) + 10000);
                } else{
                    // Gestionar las sillas
                    silla3.acquire();
                    
                    silla2.acquire();
                    silla3.release();

                    silla1.acquire();
                    silla2.release();

                    // Si no hay sillas disponibles, esperar
                    if(sillaMonitor.availablePermits() == 0){
                        System.out.println("Estudiante " + nombre + ": Ha llegado y está esperando en una silla");
                    }
                    
                    sillaMonitor.acquire();
                    silla1.release();
                    Thread.sleep(100); // Tiempo para que el monitor se de cuenta de que el estudiante está en la silla
                    // Recibir ayuda del monitor y liberar la silla
                    System.out.println("--help-- Estudiante "+ nombre + ": Recibiendo ayuda del monitor");
                    Thread.sleep(new Random().nextInt(2000) + 0);
                    System.out.println(" > [EXIT] Estudiante " + nombre + ": Ya ha terminado. Se va a casa.");
                    sillaMonitor.release();
                    break;
                }

			} catch (InterruptedException e) {
			}
		}
	}
}