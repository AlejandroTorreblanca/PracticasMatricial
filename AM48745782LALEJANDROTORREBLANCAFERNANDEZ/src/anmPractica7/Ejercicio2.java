/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica7;

import auxiliar.FuncionVV;
import auxiliar.Matrices;
import auxiliar.MetodosEcuacionNoLineal;
import auxiliar.MetodosFunciones;

/**
 *
 * @author fenix
 */
public class Ejercicio2 {
    public static class sistema1 implements FuncionVV{
        public double[] eval(double[] X){
            double x=X[0];
            double y=X[1];
            double a= x*y*y +x*x*y+x*x*x*x-3;
            double b=x*x*x*y*y*y*y*y-2*x*x*x*x*x*y-x*x +2;
            double[] f={a,b};
            return f;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // TODO code application logic here
        double[] X0={1,1};
        sistema1 f=new sistema1();
        double[] fX=f.eval(X0);
        System.out.println("x = "+ Matrices.toString(X0));
        System.out.println("f(x) = "+ Matrices.toString(fX));
        double prec=0.5E-8;
        double paso=0.001;
        int nmax=100;
        MetodosFunciones.fobjJaprox g=new MetodosFunciones.fobjJaprox(f, paso);
        try{
            X0[0]= -1;
            X0[1]= 8;
            
            double[] solucion=MetodosEcuacionNoLineal.newtonVectAprox(f, X0, 
                    prec, paso, nmax, false);
            
            solucion=MetodosEcuacionNoLineal.descensoRapido(g, X0, prec, nmax);
            System.out.println("descenso rápido \n x= "+ Matrices.toString(solucion));
            
            
        }catch(MetodosEcuacionNoLineal.ERROR err){
            
        }
        
         try{
            X0[0]= -1;
            X0[1]= -8;
            
            double[] solucion=MetodosEcuacionNoLineal.newtonVectAprox(f, X0, 
                    prec, paso, nmax, false);
            
            solucion=MetodosEcuacionNoLineal.descensoRapido(g, X0, prec, nmax);
            System.out.println("descenso rápido \n x= "+ Matrices.toString(solucion));
            
        }catch(MetodosEcuacionNoLineal.ERROR err){
            
        }
          try{
            X0[0]= 1;
            X0[1]= -1;
            
            double[] solucion=MetodosEcuacionNoLineal.newtonVectAprox(f, X0, 
                    prec, paso, nmax, false);
            
            solucion=MetodosEcuacionNoLineal.descensoRapido(g, X0, prec, nmax);
            System.out.println("descenso rápido \n x= "+ Matrices.toString(solucion));
            
        }catch(MetodosEcuacionNoLineal.ERROR err){
            
        }
          
           try{
            X0[0]= 2;
            X0[1]= 1;
            
            double[] solucion=MetodosEcuacionNoLineal.newtonVectAprox(f, X0, 
                    prec, paso, nmax, false);
            solucion=MetodosEcuacionNoLineal.descensoRapido(g, X0, prec, nmax);
            System.out.println("descenso rápido \n x= "+ Matrices.toString(solucion));
            
        }catch(MetodosEcuacionNoLineal.ERROR err){
            
        }
        
    }
    
}
