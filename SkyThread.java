import javax.swing.*;

public class SkyThread implements Runnable {
    JFrame frame;
    @Override
    public void run() {
        frame = new JFrame();
        Sky sky = new Sky(80);
        frame.getContentPane().add(sky);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setVisible(true);

        while (true) {  //Любое число итераций для проверки
            frame.repaint();
            sky.setStep();
            try {
                Thread.sleep(80);
            } catch (Exception a) {a.printStackTrace();}
        }
    }
}
