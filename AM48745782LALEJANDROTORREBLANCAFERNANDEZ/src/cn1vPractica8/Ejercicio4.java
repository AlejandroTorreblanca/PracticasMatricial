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
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MetodosEcuacionNoLineal.ERROR, InterruptedException {
        // TODO code application logic here
        F f = new F();
        G g = new G();
        J j = new J();
        double prec=0.5E-15;
        double raiz;
        
        PanelDibujo win=new PanelDibujo();
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.addEjesCoordenados(true, -1., 6., -1, 20, 0, 0);
        h1 uno = new h1();
        h2 dos = new h2();
        win.addCurva(Color.red, uno, 200, -1, 5.5);
        win.addCurva(Color.blue, dos, 200, -1, 5.5); 
        win.repaint();
        win.revalidate();
        Thread.sleep(3000);
        
        raiz=MetodosEcuacionNoLineal.iteracionPF(f, -1., prec, 100);
        System.out.println("raiz="+raiz);
        System.out.println("Derivada en la raiz="+f.derivada(raiz));
        
        System.out.println("\n\n Segundo punto fijo. Cambiando la función");
        raiz=MetodosEcuacionNoLineal.iteracionPF(g, 2., prec, 100);
        System.out.println("raiz="+raiz);
        System.out.println("Derivada en la raiz="+g.derivada(raiz));
        System.out.println("\n\n Segundo punto fijo.Steffensen");
        raiz=MetodosEcuacionNoLineal.steffensenPF(f, 4, prec, 100);
        System.out.println("raiz="+raiz); 
        System.out.println("Derivada en la raiz="+f.derivada(raiz));
         
       
        System.out.println("\n\n Con Newton");
        raiz=MetodosEcuacionNoLineal.newton(j, 2., prec, 100);
        win.addPunto(Color.black, new double[] {raiz,uno.eval(raiz)});
        System.out.println("raiz="+raiz);
        raiz=MetodosEcuacionNoLineal.newton(j, 7., prec, 100);
        win.addPunto(Color.black, new double[] {raiz,uno.eval(raiz)});
        System.out.println("raiz="+raiz);
        win.repaint();
        
    }
    
    
    public static class F implements FuncionDeriv{
        
        public F(){}
        
        public double eval(double x){
            return Math.exp(x/2.)/2.-1.;
        }
        public double derivada(double x){
            return Math.exp(x/2.)/4.;
        }
    }
    public static class G implements FuncionDeriv{
        
        public G(){}
        
        public double eval(double x){
            return 2*(Math.log(2.)+Math.log(1+x));
        }
        public double derivada(double x){
            return 2./(1+x);
        }
    }

        public static class J implements FuncionDeriv{
        
        public J(){}
        
        public double eval(double x){
            return Math.exp(x/2)-2.*x-2.;
        }
        public double derivada(double x){
            return Math.exp(x/2)/2.-2.;
        }
    }
        
        public static class h1 implements Funcion{
        
        public h1(){}
        
        public double eval(double x){
            return Math.exp(x/2);
        }        
    }
        public static class h2 implements Funcion{
        
        public h2(){}
        
        public double eval(double x){
            return 2*x+2;
        }        
    }
}
