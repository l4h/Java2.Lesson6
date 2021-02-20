package Java2.Lesson6;

public class ServerMain {

    public static void main(String[] args) {
	    Thread server = new Thread(new Server(),"Server");
	    server.start();
        try {
            server.join();
            System.out.println("Server stoped");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
