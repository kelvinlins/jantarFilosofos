    package service;

    import java.util.concurrent.Semaphore;

    public class FilosoService {

        public void mainService() throws InterruptedException {

            int filosofosComendoAoMesmoTempo = 2, numeroFilosofos = 5;

            Semaphore semaphore = new Semaphore(filosofosComendoAoMesmoTempo);
            ProcessadorThread[] processos = new ProcessadorThread[numeroFilosofos];

            int loop = 0;
            for (int i = 0; i < numeroFilosofos; i++) {
                processos[i] = new ProcessadorThread(i, semaphore);
            }

            while (true){
                loop++;
                if(loop<=1){
                    for(int i=0;i<processos.length;i++){
                        this.fazerFilosoComer(processos[i], 1);
                        processos[i].start();
                        processos[i].stop();
                    }
                }else{
                    for(int i=0;i<processos.length;i++){
                        this.fazerFilosoComer(processos[i], 1);
                    }
                }
                System.out.println("LOOP: " + loop);
            }

        }

        public void fazerFilosoComer(ProcessadorThread processadorThread, int loop) throws InterruptedException {
           try {
               processadorThread.semaforo.acquireUninterruptibly(2);
               processadorThread.run();
               processadorThread.semaforo.release();
           }catch (Exception e){
                System.out.println("DEADLOCK: " + loop);
           }
           finally {
               System.out.println(Thread.currentThread().getName() + " released " + processadorThread.semaforo.toString());
           }

        }

    }
