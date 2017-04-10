/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica3;

import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio1 {

    /**
     *  El ejercicio lo complementamos en los ejercicios de la practica 2
     *  con las matrices de Hilbert
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
       //Apartado 1
       double[][] A={{18,6,-3,4,5},{6,25,-3,-5,-2},{-3,-3,11,-2,3},
           {4,-5,-2,11,5},{5,-2,3,5,22}};
       
        System.out.println("ifSimetrica(A) = "+ Matrices.ifSimetrica(A));
        
        double[][][] LDa=Matrices.FactorLDR(A);
        double[][] La=LDa[0];
        double[][] Da=LDa[1];
        double[][] testLDRa=
                Matrices.producto(La, Matrices.producto(Da, Matrices.traspuesta(La)));
        System.out.println("|| A - LDR || = "+ Matrices.norma1(Matrices.resta(A, testLDRa)));
        
        double[][] Lch=Matrices.factorLdeCholeski(A);
        double[][] testLch=
                 Matrices.producto(Lch,Matrices.traspuesta(Lch));
        System.out.println("|| A - L L^t || = "+ Matrices.norma1(Matrices.resta(A, testLch)));
        
        //Apartado 3
        double[] b={15,0,1000,2,-34};
        
        double[] x=Matrices.solveCholeski(A, b);
        System.out.println("|| Ax -b || = "+ Matrices.normaResidual(A, x, b));
                
        
      
        
        
        
        double[][] B={{8,6,-3,4,5},{6,15,-3,-5,-2},{-3,-3,10,-2,3},
           {4,-5,-2,8,5},{5,-2,3,5,19}};
        double[][][] LDb=Matrices.FactorLDR(B);
        double[][] Lb=LDb[0];
        double[][] Db=LDb[1];
        double[][] testLDRb=
                Matrices.producto(Lb, Matrices.producto(Db, Matrices.traspuesta(Lb)));
        System.out.println("|| B - LDR || = "+ Matrices.norma1(Matrices.resta(B, testLDRb)));
        
        System.out.println("Diagonal en B=LDR  es \n " + Matrices.toString(Db));
        
        try{
        double[][] Lchb=Matrices.factorLdeCholeski(B);
        double[][] testLchb=
                 Matrices.producto(Lchb,Matrices.traspuesta(Lchb));
        System.out.println("|| B - L L^t || = "+ Matrices.norma1(Matrices.resta(B, testLchb)));
        }catch(Matrices.ErrorMatrices err){
            
        }
    }
    
}
