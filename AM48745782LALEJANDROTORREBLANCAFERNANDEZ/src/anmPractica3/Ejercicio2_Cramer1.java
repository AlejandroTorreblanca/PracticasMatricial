/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica3;

import anmPractica2.*;
import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio2_Cramer1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
    
        double[][] A={{10,7,8,7},{7,5,6,5},{8,6,10,9},{7,5,9,10}};
        System.out.println("A= "+ Matrices.toString(A));
      
        double detA=Matrices.determinanteMenores(A);
       
        System.out.println("det(A)= "+detA+ "\t det(invA)= ");
    
        // Apartado 3
        double[] b={32,23,33,31};
        
        double[] x=Matrices.Cramer(A, b);
        System.out.println("  \n solución x= "+Matrices.toString(x));
        System.out.println("||Ax-b|| (Cramer)= "+ Matrices.normaResidual(A, x, b));
        
        x=Matrices.solveGaussP(A, b);
        System.out.println("  \n solución x= "+Matrices.toString(x));
        System.out.println("||Ax-b|| (GaussP)= "+ Matrices.normaResidual(A, x, b));
        
        
        // Apartado 4
        double epsilon=1;
        double[][] DeltaA={{0,0,0.1,0.2},{0.08,0.04,0,0},{0,-0.02,-0.11,0},
            {-0.01,-0.01,0,-0.02}};
        DeltaA = Matrices.producto(epsilon, DeltaA);
        System.out.println("Delta(A)= "+ Matrices.toString( DeltaA ));
        double[][] Apert=Matrices.suma(A,DeltaA);
        double[] x1p=Matrices.Cramer(Apert, b);
        System.out.println("solución x1p= "+Matrices.toString(x1p));
        System.out.println("||Apert x-b||= "+ Matrices.normaResidual(Apert, x1p, b));
        
        double[] bP={32+0.1*epsilon,23-0.1*epsilon,33+0.1*epsilon,31-0.1*epsilon};
        
        double[] x2p=Matrices.Cramer(A, bP);
        System.out.println("solución x2p= "+Matrices.toString(x2p));
        System.out.println("||A x-bP||= "+ Matrices.normaResidual(A, x2p, bP));
        System.out.println("\n det(A) (gauss) = "+ Matrices.determinanteGaussP(A));
        System.out.println("\n \n");
        
        //Ejercicio 2.4
        double[][][] QR=Matrices.QRGramSchmidt(A);
        
        double[][] Q=Matrices.copia(QR[0]);
        double[][] R=Matrices.copia(QR[1]);
        double control=Matrices.norma1(Matrices.resta(A, Matrices.producto(Q, R)));
        System.out.println("Control factorizacion = "+ control);
                
        
        x=Matrices.solveQR(QR, b);
        
        System.out.println("(con fac QR Gram-Smidth) solución x= "+Matrices.toString(x));
        System.out.println("||Ax-b||= "+ Matrices.normaResidual(A, x, b));
        
        //Ejercicio 2 practica 4
        x=Matrices.solveFactorQR(A, b);
        
        System.out.println("(con fac QR Householder) solución x= "+Matrices.toString(x));
        System.out.println("||Ax-b||= "+ Matrices.normaResidual(A, x, b));
        
        
        
        // Ejercicio 5
        
        double[][][] LU=Matrices.LUdootlittle(A);
        
        double[][] L=Matrices.copia(LU[0]);
        double[][] U=Matrices.copia(LU[1]);
        control=Matrices.norma1(Matrices.resta(A, Matrices.producto(L, U)));
        System.out.println("Control factorizacion LU = "+ control);
        
        System.out.println("detLU (A) = "+ Matrices.determinanteLU(LU));
        
        x=Matrices.solLU(LU, b);
        
        System.out.println("(con fac LU) solución x= "+Matrices.toString(x));
        System.out.println("||Ax-b||= "+ Matrices.normaResidual(A, x, b));
        
        
        //Ejercicio 3.2
        
        Matrices.showGaussP(A);
        
        double[][] inversaG=Matrices.inversaGauss(A);
        
        System.out.println("|| Id - A inversaG || = "+
                Matrices.norma1(Matrices.resta(Matrices.Id(4),
                        Matrices.producto(A, inversaG))));
        
        
                }
    
    
       
    
}
