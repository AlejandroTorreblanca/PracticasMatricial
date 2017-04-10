/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica4;

import auxiliar.Matrices;
import cn1vPractica3.*;
import auxiliar.PanelDibujo;
import auxiliar.Polinomio;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author socrates
 */
public class Ejercicio6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // TODO code application logic here
        long time_start, time_end;
time_start = System.currentTimeMillis();

        double[][] xys={{1,2.1},{2,3.3},{3,3.9},{4,4.4},{5,4.6},{6,4.8},
            {7,4.6},{8,2},{9,3.4}};
       
        int n=1;
        
        Polinomio p=Polinomio.minimosCuadrados(xys, n);
                
        
        
        
        PanelDibujo pd=new PanelDibujo("Ejercicio 6 práctica 4");
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
        double a=-5;
        double b=12;
        pd.addEjesCoordenados(true,a, b, -10,10);
     pd.addListaPuntos(Color.blue, xys, 5);
        pd.addCurva(Color.red, p, 37, a, b);
        pd.repaint();
        time_end = System.currentTimeMillis();
System.out.println("La tarea duró "+ ( time_end - time_start ) +" milisegundos");
     
    }
    }
    

