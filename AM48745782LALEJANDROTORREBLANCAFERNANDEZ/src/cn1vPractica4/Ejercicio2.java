/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica4;

import auxiliar.Funcion;
import auxiliar.MetodosFunciones;
import auxiliar.PanelDibujo;
import auxiliar.Polinomio;
import auxiliar.PolinomioInterpolador;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author fenix
 */
public class Ejercicio2 {

    public static class F1 implements Funcion {

        public double eval(double x) {
            return Math.sin(Math.PI * x * x);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int numPuntos = 100;
        F1 f = new F1();
        double a = 0;
        double b = 1;
        double[][] xys = MetodosFunciones.tablaGrafica(f, numPuntos, a, b);
        long hora_inicio, hora_final;
        hora_inicio = System.currentTimeMillis();
        PolinomioInterpolador p = new PolinomioInterpolador(xys);
        int numEval = 1000;
        double[][] tabla = MetodosFunciones.tablaGrafica(p, numEval, a, b);
        hora_final = System.currentTimeMillis();
        System.out.println("Tiempo de construcción del polinomio interpolador en "
                + numPuntos + " y la evaluación en " + numEval + " \n tiempo = "
                + (hora_final - hora_inicio));
        PanelDibujo pd = new PanelDibujo("Ejercicio 3 práctica 3");
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
        
        pd.addEjesCoordenados(true, a-0.5, b+0.5, -2, 2);
        pd.addListaPuntos(Color.blue, xys, 5);
        pd.addCurva(Color.red, p, numEval, a, b);
        pd.repaint();
    }

}
