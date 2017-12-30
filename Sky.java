import javax.swing.*;
import java.awt.*;

public class Sky extends JPanel {
    int[] x0; // Точки начала координат, и координаты прямой.
    int[] y0;
    int[] x, X;
    int[] y, Y;
    int[] k;     // Коэффициент функции прямой
    int[] randStep; // Флаг для Х
    private int quan;
    int[] diameter;

    public Sky(){
        this(2);  // Объект по-умолчанию
    }

    public Sky(int quantity){    //Не защищён от отр. значений
        quan = quantity;
        x0 = new int[quan];
        y0 = new int[quan];
        x = new int[quan];
        y = new int[quan];
        X = new int[quan];
        Y = new int[quan];
        k = new int[quan];
        randStep = new int[quan];
        diameter = new int[quan];

        for (int i=0; i<quan; i++) {
            x0[i] = (int) ((Math.random() * 650) + 30);
            y0[i] = (int) ((Math.random() * 350) + 30);  // Генерируем точку начала координат
            k[i] = (int) ((Math.random() * 10) - 5);    // C коэффициентом надо разобраться. Добиться попадания в [-10..10]
            //System.out.println(x0 + " " + k);
            randStep[i] = (int) (Math.random() * 2);
            diameter[i] = (int) (Math.random() * 10);
        }
    }

    public void paintComponent(Graphics a) {

        a.setColor(Color.orange);
        Graphics2D a2 = (Graphics2D) a;

        //Получаем X и Y - для удобства
        for (int i=0; i<quan; i++) {
            X[i] = x[i] + x0[i];
            Y[i] = y[i] + y0[i];
        }
        //Рисуем
        for (int i=0; i<quan; i++) {
            a.fillOval(X[i], Y[i], diameter[i], diameter[i]);  //Круг
            for (int j=0; j<quan; j++){
                double distance = Math.sqrt((X[j]-X[i])*(X[j]-X[i]) + (Y[j]-Y[i])*(Y[j] - Y[i])); // Расстояние отрезка
                if(distance < 100) {
                    if(distance > 90) a2.setStroke(new BasicStroke(1.0f));  // толщина равна 1,5
                    else if(distance > 70) a2.setStroke(new BasicStroke(1.1f));
                    else if(distance > 50) a2.setStroke(new BasicStroke(1.4f));
                    else if(distance > 30) a2.setStroke(new BasicStroke(1.6f));
                    else if(distance > 10) a2.setStroke(new BasicStroke(1.8f));
                    else if(distance >=0 ) a2.setStroke(new BasicStroke(2.0f));

                    a2.drawLine(X[i], Y[i], X[j], Y[j]);  //Линия
                }
            }
        }
    }

    //Движение звёздочки
    public void setStep() {
        for (int i=0; i<quan; i++){

               if (randStep[i] == 1) {
                   if ((Y[i] > 430) && (X[i] < 730)) {x0[i] = X[i]; x[i] = 0; y0[i] = 0; y[i] = 0;} //Сброс коорд. и смещение оси
                    else if ((Y[i] < 430) && (X[i] > 730)) {x0[i] = 0; x[i] = 0; y0[i] = Y[i]; y[i] = 0;}
                   // else if ((Y[i] > 430) && (X[i] > 730)) {x0[i] = 0; x[i] = 0; y0[i] = 0; y[i] = 0;}
                   x[i]++;
               } else {
                   if ((Y[i] < -30) && (X[i] < 730)) {x0[i] = X[i]; x[i] = 0; y0[i] = 430; y[i] = 0;}
                   else if ((Y[i] < 430) && (X[i] < -30)) {x0[i] = 730; x[i] = 0; y[0] = Y[i]; y[i] = 0;}
                   else if ((Y[i] > 430) && (X[i] > -30)) {x0[i] = X[i]; x[i] = 0; y0[i] = 0; y[i] = 0;}
                   x[i]--;
               }

            y[i] = k[i] * x[i];
            System.out.println(X[i] + " " + Y[i]);
        }
    } // Конец setStep
}
