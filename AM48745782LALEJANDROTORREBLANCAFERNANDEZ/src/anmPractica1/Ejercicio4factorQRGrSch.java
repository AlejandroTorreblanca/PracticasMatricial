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
public class Ejercicio4factorQRGrSch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // TODO code application logic here
        
        double[][] B= {{1,3,-5,2,8,2},{5,-2,-1,8,1,-3},
                       {4,3,-16,23,19,-4},{9,2,-1,-1,7,4}};
        double[][] A=Matrices.traspuesta(B);
        
        double[][][] QR=Matrices.QRGramSchmidt(A);
        double[][] Q=QR[0];
        double[][] R=QR[1];
        
        System.out.println("A = "+ Matrices.toString(A));
        System.out.println("R = "+ Matrices.toString(R));
        System.out.println("Q = "+ Matrices.toString(Q));
        
        double[][] resto=Matrices.resta(A, Matrices.producto(Q, R));
        
        System.out.println("||A-QR|| = "+ Matrices.norma1(resto));
        
        // ¿Q es ortogonal?
        double[][] test=Matrices.producto(Matrices.traspuesta(Q), Q);
        System.out.println("Test Q^tQ = "+ Matrices.toString(test));
        
        
    }
    
}
