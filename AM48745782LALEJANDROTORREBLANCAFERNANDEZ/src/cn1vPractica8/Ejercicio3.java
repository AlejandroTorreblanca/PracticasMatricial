/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica8;

import auxiliar.Funcion;
import auxiliar.FuncionDeriv;
import auxiliar.MetodosEcuacionNoLineal;
import auxiliar.PanelDibujo;
import java.awt.Color;
import javax.swing.WindowConstants;

/**
 *
 * @author Salvador Sánchez-Pedreño
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MetodosEcuacionNoLineal.ERROR, InterruptedException {
        // TODO code application logic here
      PanelDibujo win=new PanelDibujo();
      win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      F1 f= new F1();
      F2 g = new F2();
      H h = new H();
      double prec=1E-15;
      int max=30;
      
      win.addEjesCoordenados(true, -0.2, 10.2, -0.3, 1, 0, 0);
      win.addCurva(Color.red, f, 200, 0, 10);
      win.addCurva(Color.blue, g, 200, 0, 10);
      win.addCurva(Color.green, h, 200, 0, 10);
      win.repaint();
      win.revalidate();
      Thread.sleep(5000);

      double[] raices=new double[4];
      raices[0]=MetodosEcuacionNoLineal.newton(h, 0., prec, max);
        System.out.println("raiz="+raices[0]);
      raices[1]=MetodosEcuacionNoLineal.newton(h,2.1,prec,max);
        System.out.println("raiz="+raices[1]);
      raices[2]=MetodosEcuacionNoLineal.newton(h,2*Math.PI,prec,max);
        System.out.println("raiz="+raices[2]);
      raices[3]=MetodosEcuacionNoLineal.newton(h,3*Math.PI,prec,max);
        System.out.println("raiz="+raices[3]);
      for (int i=0;i<raices.length;i++){
          win.addPunto(Color.black, new double[] {raices[i],f.eval(raices[i])},3);
      } 
      win.removeCurva(2);
      win.repaint();
    }
    
    public static class F1 implements Funcion{
        
        public F1(){};
        
        public double eval(double x){
            return Math.PI*Math.sin(x)/(2*(1+x*x));
        }
    }
        
    public static class F2 implements Funcion{
        
        public F2(){};
        
        public double eval(double x){
            return Math.exp(-x);
        }    
    }
        
    // La diferencia entre las dos funciones y sus derivada:
    public static class H implements FuncionDeriv{
        public H(){}
        
        public double eval(double x){
            return Math.PI*Math.sin(x)/(2*(1+x*x)) - Math.exp(-x);
        }
        public double derivada(double x){
            double aux=1+x*x;
            return Math.PI*(2*aux*Math.cos(x)-4*x*Math.sin(x))/(4*aux*aux)+Math.exp(-x);
        }
    }
}
