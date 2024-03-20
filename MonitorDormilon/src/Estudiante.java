import java.util.Random;
import java.util.concurrent.Semaphore;

public class Estudiante extends Thread{
    private Semaphore silla1;
    private Semaphore silla2;    
	private Semaphore silla3; 
    private Semaphore sillaMonitor; 
    private String nombre;      
	
	// Constructor de la clase. Inicializa todos los datos requeridos
	public Estudiante(Semaphore sillaMonitor, Semaphore silla1, Semaphore silla2, Semaphore silla3, String nombre) {
        this.silla1=silla1; 
        this.silla2=silla2; 
        this.silla3=silla3;
        this.sillaMonitor=sillaMonitor; 
        this.nombre=nombre;
	}
	
	// Método principal del hilo
	public void run() {
		// Correr continuamente
		while (true) {
			try {
                //System.out.println("El estudiante " + nombre + " ha llegado a la oficina");
                if(silla3.availablePermits() == 0){
                    System.out.println("Estudiante "+nombre+": se fue a programar y volverá pronto.");
                    Thread.sleep(new Random().nextInt(2000) + 10000);
                } else{
                    silla3.acquire();
                    //Thread.sleep(new Random().nextInt(200) + 200);
                    
                    
                    silla2.acquire();
                    silla3.release();
                    //Thread.sleep(new Random().nextInt(200) + 200);

                    silla1.acquire();
                    silla2.release();
                    //Thread.sleep(new Random().nextInt(200) + 200);
                    
                    

                    if(sillaMonitor.availablePermits() == 0){
                        System.out.println("Estudiante " + nombre + ": Acaba de llegar y está esperando en una silla");
                    }
                    
                    sillaMonitor.acquire();
                    silla1.release();
                    
                    System.out.println("Estudiante "+ nombre + ": Recibiendo ayuda del monitor");
                    Thread.sleep(new Random().nextInt(2000) + 0);
                    System.out.println("> [EXIT] Estudiante " + nombre + ": Ya ha terminado. Se va pa su casa");
                    sillaMonitor.release();
                    break;
                }

			} catch (InterruptedException e) {
			}
		}
	}
}