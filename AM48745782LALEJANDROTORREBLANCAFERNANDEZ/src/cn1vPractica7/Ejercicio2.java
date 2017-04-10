/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica7;

import auxiliar.PanelDibujo;
import auxiliar.Polinomio;
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
    public static void main(String[] args) throws Polinomio.ErrorPolinomios {
        // TODO code application logic here
        int n=11;
        Polinomio[] L=Polinomio.legengre(n);
        for (int i = 0; i <= n; i++) {
            System.out.println("L["+i+"]= "+ L[i].escribe());
        }
    
    PanelDibujo pd = new PanelDibujo("Ejercicio 2 Practica 7 ");
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
        pd.addEjesCoordenados(true, -1.5, 1.5, -5, 5);
        pd.addCurva(Color.blue, L[n], 101, -1, 1);
        pd.repaint();
        
        //
        double x0=1;
        double prec=0.5E-6;
        int nmaxiter=100;
        double[] raices=new double[n];
        double[][] ceros=new double[n][2];
        Polinomio defla=new Polinomio(L[n]);
        for (int i = 0; i < n; i++) {
            raices[i]=defla.newton(x0, prec, nmaxiter);
            raices[i]=L[n].newton(raices[i], prec, nmaxiter);
            ceros[i][0]=raices[i];
            pd.addPunto(Color.red, ceros[i],5);
            defla=defla.deflacion(raices[i]);
        }
        pd.repaint();
        for (int i = 0; i < ceros.length; i++) {
            System.out.println("raiz["+i+"] = "+ raices[i]);
        }
        
        
        
        
    }
}
