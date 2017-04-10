/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica3;

import ORG.netlib.math.complex.Complex;
import auxiliar.PanelDibujo;
import auxiliar.Polinomio;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author socrates
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double [] coefRe={1,-2,3,-4,0,5};
        Polinomio p=new Polinomio(coefRe);
        System.out.println("p(x) = "+p);
        double x=1;
        System.out.println("p(x) = "+ p.eval(x));
         Complex z= new Complex(0,1);
         System.out.println("p(z) = "+ p.eval(z));
        PanelDibujo pd=new PanelDibujo("Ejercicio 1");
         /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */


        pd.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        
        double a=-3;
        double b=3;
        pd.addEjesCoordenados(true, a, b,-10,10,0,0);        
        pd.addCurva(Color.red, p, 37, a, b);
        pd.repaint();
    }
    
}
