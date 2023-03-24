package controller;

import java.util.concurrent.Semaphore;

public class CompradoresThread extends Thread {
	private static int qtdIngressos = 100;
	private Semaphore semaforo = new Semaphore(1);
	private int idComprador;
	
	public CompradoresThread(int idComprador, Semaphore semaforo) {
		this.idComprador = idComprador;
		this.semaforo = semaforo;
	}
	
	@Override
    public void run() {
        realizaLogin();
        escolherQuantidade();
        //validarCompra();
    }

	private void realizaLogin() {
		try {
            int tempo = (int) (Math.random() * 1950 + 50);
            Thread.sleep(tempo);
                System.out.println("Comprador# "+ idComprador + " fez login em " + tempo + " ms.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	private void escolherQuantidade() {
		
        int quantidade = (int) (Math.random() * 4 + 1);
        System.out.println("Comprador# " + idComprador + " escolheu " + quantidade + " ingresso(s).");
		validarCompra(quantidade);
	}
	
	private void validarCompra(int quantidade) {
		try {
            semaforo.acquire();
            if (qtdIngressos > quantidade) {
                qtdIngressos = qtdIngressos - quantidade;
                System.out.println("Comprador# " + idComprador + " comprou " + quantidade +" ingresso(s). Ingressos disponíveis: " + qtdIngressos);
            } else {
                System.out.println("Comprador# "+idComprador + " não conseguiu realizar a compra. Ingressos disponíveis: " + qtdIngressos);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }	


}
		