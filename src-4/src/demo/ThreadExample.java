package demo;

public class ThreadExample {
    public static void main(String[] args) {
        System.out.println("Main Thread");
        
        SimpleThread t1 = new SimpleThread();
        t1.start();

        SimpleThread t2 = new SimpleThread();
        t2.start();

        System.out.println("Finished execution");
    }

}
