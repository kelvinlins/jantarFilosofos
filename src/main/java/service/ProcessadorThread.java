package service;
import java.util.concurrent.Semaphore;

public class ProcessadorThread extends Thread {
    public int idThread;
    public Semaphore semaforo;

    public ProcessadorThread(int id, Semaphore semaphore) {
        this.idThread = id;
        this.semaforo = semaphore;
    }

    private void comer() {
        try {
            long tempIni = System.currentTimeMillis();
            System.out.println("FILOSOFO #" + idThread + " ESTÁ COMENDO");
            Thread.sleep((long) (Math.random() * 10000));
            long tempFim = System.currentTimeMillis();
            long tempTot = tempFim - tempIni;
            System.out.println("TEMPO COMENDO DO FILOSO #" + idThread + " É DE " + tempTot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pensar() {
        System.out.println("FILOSOFO #" + idThread + " ESTÁ PENSANDO");
    }

    private void ficandoComFome() {
        System.out.println("FILOSOFO #" + idThread + " ESTÁ FICANDO COM FOME");
        System.out.println("FILOSOFO #" + idThread + " ESTÁ COM FOME");
    }

    public void run() {

        ficandoComFome();
        try {
            ficandoComFome();
            comer();
            pensar();
        } finally {
            semaforo.release();
        }
    }

}