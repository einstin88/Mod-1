package demo;

public class SimpleThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread executing..." + Thread.currentThread().getName());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }

        System.out.println("Thread stopped execution" + Thread.currentThread().getName());
    }
    
}
