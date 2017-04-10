/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemplosUnidad3;

import auxiliar.*;
import java.awt.Color;
import java.util.Random;
import javax.swing.WindowConstants;

/**
 *
 * @author antoniopallaresruiz
 */
public class EjemploMonteCarloNumPi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PanelDibujo pd = new PanelDibujo("Aproximacion de PI, MonteCarlo",
                0, 0, 750, 600);
        pd.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pd.setVisible(true);


        pd.addEjesCoordenados(true, -0.2, 1.8, -0.2, 1.4, 0, 0);

        double[] arco = {0, 0, 1, 0, 90};
        pd.addArco(arco, Color.yellow);
        double[][] cuadrado = {{0, 0}, {0, 1}, {1, 1}, {1, 0}, {0, 0}};
        pd.addCurva(Color.blue, cuadrado);

        int numelec = 50000;

        double suma = 0;
        double aproxpi = 0;

        Random xrnd = new Random();
        Random yrnd = new Random();

        for (int i = 1; i < numelec; i++) {


            double x = xrnd.nextDouble();
            double y = yrnd.nextDouble();
            if ((x * x + y * y) <= 1) {
                suma = suma + 1;
            }
            String pi = "       pi = " + Math.PI;
            aproxpi = 4 * suma / i;
            String apr = " aprox = " + aproxpi;

            double[] punto = new double[2];
            punto[0] = x;
            punto[1] = y;
            pd.addPunto(Color.BLUE, punto, 2);

            double[] posicion1 = {1.1, 1.1};
            pd.addEtiqueta(posicion1, pi, Color.blue);
            double[] posicion2 = {1.1, 1.15};
            pd.addEtiqueta(posicion2, apr, Color.blue);
            double[] posicion3 = {1.1, 1.25};
            pd.addEtiqueta(posicion3, "numelc = " + i, Color.red);



            if(i%10 == 0){
            pd.repaint();
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pd.removeEtiquetas();

        }

        System.out.println(" pi  = "+ Math.PI);
        System.out.println(" app = "+ aproxpi);
        System.out.println(" nelec = "+ numelec);
        





    }
}
