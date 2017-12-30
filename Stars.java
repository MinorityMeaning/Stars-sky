
public class Stars {

    public static void main(String[] args) {
        new Stars().start();
    }

    void start() {

        Runnable background = new SkyThread();
        Thread skyThread = new Thread(background);
        skyThread.start();

        System.out.println("Конец");
    }
}
