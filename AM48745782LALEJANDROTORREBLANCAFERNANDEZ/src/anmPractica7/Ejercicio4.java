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
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static class F implements FuncionVV{
            public double[] eval(double[] X){
                double x=X[0];
                double y=X[1];
                double z=X[2];
                double w=X[3];
                double a=4*x-y+z-x*w;
                double b=-x+3*y-2*z-y*w;
                double c= x- 2*y +3*z-z*w;
                double d= x*x+y*y+z*z-1;
                double[] f={a,b,c,d};
                return f;
            }
        }
    
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // TODO code application logic here
        
        //solución visto el sistema como (A-wI)x=0 
        double[][] A={{4,-1,1},{-1,3,-2}
                ,{1,-2,3}};
        double tol=0.5E-14;
        int nmax=1000;
        
        double[][] valYVecPropios=Matrices.jacobiPropios(A, tol, nmax);
        
        System.out.println("Soluciones \n " + Matrices.toString(valYVecPropios));
        
        F f=new F();
        
        for (int i = 0; i < valYVecPropios.length; i++) {
            System.out.println("F(X["+i+"] = "+ 
                    Matrices.toString(f.eval(valYVecPropios[i])));
            
        }
        
        try{
            double paso=0.001;
            double[] x={1,0,-1,6};
            double[] soluc=MetodosEcuacionNoLineal.newtonVectAprox(f, x, tol, paso, nmax, false);
            MetodosFunciones.fobjJaprox g=new MetodosFunciones.fobjJaprox(f, paso);
            soluc=MetodosEcuacionNoLineal.descensoRapido(g, x, tol, nmax);
            System.out.println("sol descenso = "+ Matrices.toString(soluc));
            soluc=MetodosEcuacionNoLineal.newtonVectAprox(f, soluc, tol, paso, nmax, false);
            
        }catch(MetodosEcuacionNoLineal.ERROR err){
            
        }
        
        
        }
    }
    
