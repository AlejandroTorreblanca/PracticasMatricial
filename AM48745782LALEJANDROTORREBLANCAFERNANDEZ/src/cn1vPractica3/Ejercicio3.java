/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica3;

import auxiliar.PanelDibujo;
import auxiliar.Polinomio;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author socrates
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long time_start, time_end;
time_start = System.currentTimeMillis();

        double[][] xys={{1.5,0},{2.7,1},{3.1,-0.5},{-2.1,1},{-6.6,0.5},{11,0}};
        Polinomio interpolador =Polinomio.interpoladorLagrange(xys);
        PanelDibujo pd=new PanelDibujo("Ejercicio 3 práctica 3");
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
        double a=-7;
        double b=12;
        pd.addEjesCoordenados(true,a, b, -10,10);
     pd.addListaPuntos(Color.blue, xys, 5);
        pd.addCurva(Color.red, interpolador, 37, a, b);
        pd.repaint();
        time_end = System.currentTimeMillis();
System.out.println("La tarea duró "+ ( time_end - time_start ) +" milisegundos");
     
    }
    }
    

