import java.util.concurrent.Semaphore;

public class Monitor extends Thread{
    private boolean duerme;
    private Semaphore sillaMonitor; 
	private Semaphore silla1;
	private Semaphore silla2; 
	private Semaphore silla3; 
    private boolean running = true;
	
	// Constructor de la clase - Inicializa todos los datos requeridos
	public Monitor(Semaphore sillaMonitor, Semaphore silla1, Semaphore silla2, Semaphore silla3) {
		super();
        this.duerme=true;
        this.silla1=silla1;
        this.silla2=silla2;
        this.silla3=silla3;
        this.sillaMonitor=sillaMonitor;
	}

    @Override
	public void run() {
        boolean flag = false;
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


    public void stopThread(){
        running = false;
    }
}
