import java.util.concurrent.Semaphore;  // Importar la clase Semaphore

/**
 * Clase que representa un monitor
 */
public class Monitor extends Thread{
    private boolean duerme;             // Indica si el monitor está durmiendo
    private Semaphore sillaMonitor;     // Silla del monitor
	private Semaphore silla1;           // Silla 1 de la monitoria
	private Semaphore silla2;           // Silla 2 de la monitoria
	private Semaphore silla3;           // Silla 3 de la monitoria
    private boolean running = true;     // Indica si el monitor está corriendo
	
	// Constructor de la clase - Inicializa todos los datos requeridos
	public Monitor(Semaphore sillaMonitor, Semaphore silla1, Semaphore silla2, Semaphore silla3) {
		super();
        this.duerme=true;
        this.silla1=silla1;
        this.silla2=silla2;
        this.silla3=silla3;
        this.sillaMonitor=sillaMonitor;
	}

    // Método principal del hilo
    @Override
	public void run() {
        boolean flag = false;
        // Correr continuamente hasta que se interrumpa de forma externa
		while (running) {
                if(silla1.availablePermits()==1 && silla2.availablePermits()==1 && silla3.availablePermits()==1 && sillaMonitor.availablePermits()==1){
                    if(duerme){
                        System.out.println("---------------[ Monitor durmiendo ]---------------");
                        duerme=false;
                        flag = true;
                    }
                    
                } else {
                    if(flag){
                        System.out.println("¡El estudiante despierta al monitor!");
                        flag=false;
                    }

                    duerme=true;
                }
			}
		}

    // Método para detener el hilo
    public void stopThread(){
        running = false;
    }
}
