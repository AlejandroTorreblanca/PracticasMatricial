/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamenMatricialJunio;
import auxiliar.*;
/**
 *
 * @author Alejandro
 */
public class Ejercicio2 {

    public static class sistema1 implements FuncionVV{
        public double[] eval(double[] X){
            double x=X[0];
            double y=X[1];
            double z=X[2];
            double a=x+Math.cos(x*y*z)-1;
            double b=Math.pow(1-x, 0.25)+y+0.05*z*z+0.15*z-1;
            double c=-x*x-0.1*y*y+0.01*y+z-1;
            double[] f={a,b,c};
            return f;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {

        double[] X0={1,1,1};
        double[] X1={-1,8,1};
        sistema1 f=new sistema1();
        
        double prec=0.5E-5;
        double paso=0.001;
        int nmax=100;
        MetodosFunciones.fobjJaprox g=new MetodosFunciones.fobjJaprox(f, paso);
        try{
            //Aplicamos el método de Newton
            double[] solucion=MetodosEcuacionNoLineal.newtonVectAprox(f, X1, 
                    prec, paso, nmax, false);
            double relativo=Matrices.norma(Matrices.resta(f.eval(solucion), X0))/Matrices.norma(X0);
            System.out.println("Error relativo en Newton: "+relativo);
            System.out.println("\nDescenso Rápido");
            
            //Aplicamos descenso rápido.
            solucion=MetodosEcuacionNoLineal.descensoRapido(g, X1, prec, nmax);
            double[] fX=f.eval(solucion);
            System.out.println("Solución con descenso rápido:  x= "+ Matrices.toString(solucion));
            System.out.println("f(x)="+Matrices.toString(fX));
            relativo=Matrices.norma(Matrices.resta(fX, X0))/Matrices.norma(X0);
            System.out.println("Error relativo en Descenso rápido: "+relativo);
            
            //Aplicamos Beoyden.
            System.out.println("\nBroyden");
            solucion=MetodosEcuacionNoLineal.cuasiNewton_Broyden(f, X1, prec, nmax, true);
            relativo=Matrices.norma(Matrices.resta(f.eval(solucion), X0))/Matrices.norma(X0);
            System.out.println("Error relativo en Descenso Broyden: "+relativo);
            
        }catch(MetodosEcuacionNoLineal.ERROR err){
            
        }
    }
    
}
