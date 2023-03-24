package view;

import java.util.concurrent.Semaphore;
import controller.CompradoresThread;

public class main {
	
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 1; i <= 300; i++) {
			Thread tcomprador = new CompradoresThread(i, semaforo);
			tcomprador.start();
		}
	}
}



