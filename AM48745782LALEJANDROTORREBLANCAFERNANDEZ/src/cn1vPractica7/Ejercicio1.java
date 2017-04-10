/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica7;

import auxiliar.Funcion;
import auxiliar.FuncionDeriv;
import auxiliar.MetodosEcuacionNoLineal;
import auxiliar.MetodosFunciones;

/**
 *
 * @author fenix
 */
public class Ejercicio1 {

    public static class F implements FuncionDeriv{
        public double eval(double x){
            double y=Math.exp(x)-x*x-x-1;
            return y*y;
        }
        public double derivada(double x){
            double y=Math.exp(x)-x*x-x-1;
            double dy= 2*y*(Math.exp(x)-2*x-1);
            return dy;
        }
    }
    public static class G1 implements Funcion{
        F f=new F();
        public double eval(double x){
            return x- f.eval(x)/f.derivada(x);
        }
    }
    public static class G2 implements Funcion{
        F f=new F();
        public double eval(double x){
            return x+f.eval(x);
        }
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //1) newton empezando en x0=0.5;
        F f= new F();
        double x0=0.5;
        double prec=0.5E-8;
        int nmaxit=100;
        try{
            System.out.println("NEWTON SIMPLE");
            double x=MetodosEcuacionNoLineal.newton(f, x0, prec, nmaxit);
            System.out.println("cero (newton) en "+ x + "\n");         
        }catch(MetodosEcuacionNoLineal.ERROR nerr){
        }
        try{
            //secante
             System.out.println("SECANTE SIMPLE");
            double x1=1;
            double x=MetodosEcuacionNoLineal.secante(f, x0, x1, prec, nmaxit);
            System.out.println("cero (secante) en "+ x+ "\n");
        }catch(MetodosEcuacionNoLineal.ERROR nerr2){
        }
        //steffensen
        G1 g1=new G1();
        G2 g2=new G2();
        try{
            //steffensen g1
            System.out.println("Iterada Punto fijo (Steffensen para función iterativa Newton Simple)");
            double x=MetodosEcuacionNoLineal.steffensenPF(g1, x0, prec, nmaxit);
            System.out.println("cero (steffensen g1) en "+ x +"\n");
            
        }catch(MetodosEcuacionNoLineal.ERROR nerr3){
        }
        try{
            //steffensen g2
             System.out.println("Iterada Punto fijo (Steffensen para función iterativa Steffensen)");
            double x=MetodosEcuacionNoLineal.steffensenPF(g2, x0, prec, nmaxit);
            System.out.println("cero (steffensen g2) en "+ x+ "\n");
            
        }catch(MetodosEcuacionNoLineal.ERROR nerr3){
        }
        //NewtonMultiple
        try{
            System.out.println("Newton Multiple");
            double x=MetodosEcuacionNoLineal.newtonRaizMultiple(f, x0, prec, nmaxit);
            System.out.println("cero (newton multiple) en "+ x);
        }catch(MetodosEcuacionNoLineal.ERROR nerr4){
        }
        
    }
    
}
