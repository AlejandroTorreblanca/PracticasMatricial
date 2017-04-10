/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica1;

import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio1_Cramer1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // Apartados 1 y 2
        double[][] A={{10,7,8,7},{7,5,6,5},{8,6,10,9},{7,5,9,10}};
        System.out.println("A= "+ Matrices.toString(A));
        double[][] invA={{25,-41,10,-6},{-41,68,-17,10},{10,-17,5,-3},{-6,10,-3,2}};
        System.out.println("invA= "+Matrices.toString(invA));
        double[][] prod=Matrices.producto(A, invA);
        System.out.println("producto = "+ Matrices.toString(prod));
        double detA=Matrices.determinanteMenores(A);
        double detInvA=Matrices.determinanteMenores(invA);
        System.out.println("det(A)= "+detA+ "\t det(invA)= "+ detInvA + "producto = "+
                (detA*detInvA));
    
        // Apartado 3
        double[] b={32,23,33,31};
        
        double[] x=Matrices.Cramer(A, b);
        
        System.out.println("solución x= "+Matrices.toString(x));
        System.out.println("||Ax-b||= "+ Matrices.normaResidual(A, x, b));
        
        // Apartado 4
        double epsilon=0.01;
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
        
        
        
        
    }
    
}
