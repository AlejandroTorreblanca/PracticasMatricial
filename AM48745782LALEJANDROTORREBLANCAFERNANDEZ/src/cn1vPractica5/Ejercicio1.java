/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica5;

import auxiliar.PanelDibujo;
import auxiliar.Spline;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author fenix
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double [][] nodos={{0,1},{3,0},{2,2},{1,4}};
        Spline s=new Spline(nodos);
         PanelDibujo pd = new PanelDibujo("Ejercicio 1 Practica 5 (Spline)");
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
        
        double xinf=-1;
        double xsup=4;
        double yinf=-1;
        double ysup=5;
        
        pd.addEjesCoordenados(true, xinf, xsup, yinf, ysup,0,0);
        pd.addCurva(Color.red, s,57,0,3);
        pd.addListaPuntos(Color.blue, nodos, 5);
        //pd.repaint();
        double a=-5;
        double b=5;
        Spline ss=new Spline(nodos,a,b);
        pd.addCurva(Color.magenta, ss, 115, 0, 3);
        pd.repaint();
        
        
    }
    
    
}
