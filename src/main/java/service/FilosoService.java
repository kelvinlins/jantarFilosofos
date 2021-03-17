    package service;

    import java.util.concurrent.Semaphore;

    public class FilosoService {

        public void mainService() throws InterruptedException {

            int filosofosComendoAoMesmoTempo = 2, numeroFilosofos = 5;

            Semaphore semaphore = new Semaphore(filosofosComendoAoMesmoTempo);
            ProcessadorThread[] processos = new ProcessadorThread[numeroFilosofos];

            for (int i = 0; i < numeroFilosofos; i++) {
                processos[i] = new ProcessadorThread(i, semaphore);
                //processos[i].start();
            }
            int loop = 0;

            while (true){
                loop++;
                for(int i=0;i<processos.length;i++){
                    this.fazerFilosoComer(processos[i], loop);
                    processos[i].start();
                }
                System.out.println("LOOP: " + loop);
            }

        }

        public void fazerFilosoComer(ProcessadorThread processadorThread, int loop) throws InterruptedException {
           try {
               processadorThread.semaforo.acquire(1);
               processadorThread.run();
           }catch (Exception e){
                System.out.println("DEADLOCK: " + loop);
           }
           finally {
               processadorThread.semaforo.release();
           }

        }

    }
