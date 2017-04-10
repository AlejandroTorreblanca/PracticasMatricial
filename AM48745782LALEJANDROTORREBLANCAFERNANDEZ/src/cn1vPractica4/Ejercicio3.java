/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica4;

import auxiliar.Funcion;
import auxiliar.MetodosFunciones;
import auxiliar.PanelDibujo;
import auxiliar.PolinomioInterpolador;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author fenix
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static class FRunge implements Funcion{
        public double eval(double x){
            return 1./(1+25*x*x);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        //Apartado 1
        FRunge f=new FRunge();
        int N=15;
        double a=-1;
        double b=1;
        double[][] equiTabla=MetodosFunciones.tablaGrafica(f, N, a,b );
        PolinomioInterpolador p= new PolinomioInterpolador(equiTabla);
        PanelDibujo pd = new PanelDibujo("Ejercicio 3 práctica 4 interpolacion equi-espaciada");
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
        pd.addEjesCoordenados(true, a-0.5, b+0.5,-1, 2,0,0);
        pd.addListaPuntos(Color.blue, equiTabla, 3);
        pd.addCurva(Color.red, p, 111, a, b);
        pd.repaint();
    //Apartado 2
        double [][] nodCheby= MetodosFunciones.tablaGraficaTchev(f, N, a, b);
        PolinomioInterpolador q =new PolinomioInterpolador(nodCheby);
        PanelDibujo pd1 = new PanelDibujo("Ejercicio 3 práctica 4 interpolacion Chebychev"
                + "",700,0,1500,600);
        /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */

        pd1.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        pd1.addEjesCoordenados(true, a-0.5, b+0.5,-1, 2,0,0);
        pd1.addListaPuntos(Color.blue, nodCheby, 3);
        pd1.addCurva(Color.red, q, 111, a, b);
        pd1.repaint();
        //Apartado 3
        double [][] xHerm= new double [N][2];
        xHerm[0]=new double[6];
        xHerm[N-1]=new double [6];
        for (int i = 0; i < xHerm.length; i++) {
            xHerm[i][0]=a+i*(b-a)/(N-1);
            xHerm[i][1]=f.eval(xHerm[i][0]);
        }
        PolinomioInterpolador r=new PolinomioInterpolador(xHerm);
        PanelDibujo pd2 = new PanelDibujo("Ejercicio 3 práctica 4 interpolacion Hermite"
        ,400,600,1200,1200);
        /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */

        pd2.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        pd2.addEjesCoordenados(true, a-0.5, b+0.5,-1, 2,0,0);
        pd2.addListaPuntos(Color.blue, xHerm, 3);
        pd2.addCurva(Color.red, r, 111, a, b);
        pd2.repaint();
    }
    
}
