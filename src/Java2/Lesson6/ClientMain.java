package Java2.Lesson6;

public class ClientMain {
    public static void main(String[] args) {
        Thread cl = new Thread(new Client());
        cl.start();
        try {
            cl.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
