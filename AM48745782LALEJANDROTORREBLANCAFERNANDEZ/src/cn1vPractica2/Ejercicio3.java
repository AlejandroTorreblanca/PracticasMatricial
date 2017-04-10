/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica2;

import auxiliar.Funcion;
import auxiliar.MetodosFunciones;
import auxiliar.PanelDibujo;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejo Junior
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    // apartado1
        F1 f=new F1();
        F2 g=new F2();
        
        MetodosFunciones.suma sum=new MetodosFunciones.suma(f, g);
        
        
        PanelDibujo pd=new PanelDibujo("apartado 1 del Ejercicio 3");
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
        
        double xi=-6;
        double xs=6;
        double yi=-1.5;
        double ys=14;
        
        double[][] tabla=MetodosFunciones.tablaGrafica(f, 11, -5, 5);
        
        pd.addEjesCoordenados(true, xi, xs, yi, ys,0,0);
        
        pd.addCurva(Color.BLACK, f, 121, -5, 5);
        pd.addCurva(Color.red, g, 121, -5, 5);
        pd.addCurva(Color.green, sum, 121, -5, 5);
        
        pd.addListaPuntos(Color.BLUE, tabla, 5);
        
        double[] posicionEtiqueta={2,3};
        double[] posicionEtiqueta2={2,10};
        double[] posicionEtiqueta3={2,11};
        pd.addEtiqueta(posicionEtiqueta, "función f ", Color.BLACK);
        pd.addEtiqueta(posicionEtiqueta2, "función g ", Color.red);
        pd.addEtiqueta(posicionEtiqueta3, "función suma", Color.GREEN);
        
        pd.repaint();
        
        PanelDibujo pd1=new PanelDibujo("apartado 3 ", 550, 50, 1350, 650);
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
        
        double xi1=-1.5;
        double xs1=1.5;
        double yi1=-2;
        double ys1=3;
        
        pd1.addEjesCoordenados(true, xi1, xs1, yi1, ys1);
        int ngraf=10;
        Color[] color=new Color[ngraf];
        for (int i = 0; i < ngraf; i++) {
            float red=(i+1.f)/(ngraf+1);
            float green =((13*i%(ngraf+1)+1.f))/(ngraf+1);
            float blue  =((11*i%(ngraf+1)+1.f))/(ngraf+1);
            color[i]= new Color(red, green, blue);
            F3 funF3=new F3(i+1);
            pd1.addCurva(color[i],funF3 , 121, -1, 1);
        }
        
        
    }
    
        public static class F1 implements Funcion{
        public F1(){
            
        }
        public double eval(double x){
            if(x >= 0) return 1+ Math.cos(x*x*Math.exp(x));
            return Math.exp(-0.1*x)+Math.cos(x*x*Math.exp(-x));
        }
        
    }
        public static class F2 implements Funcion{
        public F2(){
            
        }
        public double eval(double x){
            return Math.PI+Math.sqrt(1+x*x);
        }
    }
         public static class F3 implements Funcion{
        int n=1;
        public F3(int n){
            this.n=n;
        }
        public double eval(double x){
            double pot=1;
            for (int i = 0; i < n; i++) {
                pot *= x;
            }
            return pot*Math.exp(x);
        }
        
    }
}
