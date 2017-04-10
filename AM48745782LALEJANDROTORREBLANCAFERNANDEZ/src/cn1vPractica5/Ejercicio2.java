/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica5;

import auxiliar.Funcion;
import auxiliar.PanelDibujo;
import auxiliar.Spline;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author fenix
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //apartado 1

        double a = 0;
        double b = 4 * Math.PI;

        int n = 20;
        double[][] puntos = new double[20][2];
        double paso = (b - a) / (n - 1);
        for (int i = 0; i < n; i++) {
            double theta = a + paso * i;

            puntos[i][0] = 2 * theta * Math.cos(theta);
            puntos[i][1] = 2 * theta * Math.sin(theta);
        }

        PanelDibujo pd = new PanelDibujo("Ejercicio 2 Practica 5 (Spline)");
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

        double xinf = -28;
        double xsup = 28;
        double yinf = -24;
        double ysup = 19;
        pd.addEjesCoordenados(true, xinf, xsup, yinf, ysup, 0, 0);
        pd.addListaPuntos(Color.red, puntos, 3);

        //apartado 2
        double[][] nodosx = new double[n][2];
        double[][] nodosy = new double[n][2];

        for (int i = 0; i < n; i++) {
            nodosx[i][0] = a + paso * i;
            nodosy[i][0] = nodosx[i][0];
            nodosx[i][1] = puntos[i][0];
            nodosy[i][1] = puntos[i][1];

        }
        Spline sx = new Spline(nodosx);
        Spline sy = new Spline(nodosy);

        //apartado 3
        Espiralx xt=new Espiralx();
        Espiraly yt=new Espiraly();
        
        pd.addCurva(Color.green, xt, yt, 115, a, b);
        
        pd.addCurva(Color.blue, sx, sy, 115, a, b);
        
        //apartado 4
       
        Spline sxs = new Spline(nodosx,2,2);
        Spline sys = new Spline(nodosy,0,8*Math.PI);

        pd.addCurva(Color.black, sxs, sys, 115, a, b);
        
        pd.repaint();
    }

    public static class Espiralx implements Funcion {

        public double eval(double t) {
            return 2 * t * Math.cos(t);
        }
    }

    public static class Espiraly implements Funcion {

        public double eval(double t) {
            return 2 * t * Math.sin(t);
        }
    }

}
