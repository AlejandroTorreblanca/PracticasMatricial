/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica4;

import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // Matriz A
        double[][] A={{1,3,5},{2,1,3},{0,1,0}};
        
        // Matriz B
        double[][] B={
            {18,6,-3,4,5},
            {6,25,-3,-5,-2},
            {-3,-3,11,-2,3},
            {4,-5,-2,11,5},
            {5,-2,3,5,22},
            {8,6,-3,4,5},
            {6,15,-3,-5,-2},
            {-3,-3,10,-2,3},
            {4,-5,-2,8,5},
            {5,-2,3,5,19}};
        
        // Comprobamos R
        double[][] Ra=Matrices.RHouseholder(A);
        double[][] Rb=Matrices.RHouseholder(B);
        
        System.out.println("Ra = "+ Matrices.toString(Ra));
        double[][] testRa=Matrices.resta(
                Matrices.producto(Matrices.traspuesta(A), A), 
                Matrices.producto(Matrices.traspuesta(Ra), Ra));
        System.out.println("||At A - Rt R || = " + Matrices.norma1(testRa));
        
        double[][][] QRa=Matrices.factorQR(A);
        double[][] testQRa=Matrices.resta(A, 
                Matrices.producto(QRa[0],QRa[1]));
        System.out.println("|| A - QR || = "+ Matrices.norma1(testQRa));
        
        
        
        
        System.out.println("Rb = "+ Matrices.toString(Rb));
        double[][] testRb=Matrices.resta(
                Matrices.producto(Matrices.traspuesta(B), B), 
                Matrices.producto(Matrices.traspuesta(Rb), Rb));
        System.out.println("||At A - Rt R || = " + Matrices.norma1(testRb));
        
        double[][][] QRb=Matrices.factorQR(B);
        double[][] testQRb=Matrices.resta(B, 
                Matrices.producto(QRb[0],QRb[1]));
        System.out.println("|| B - QR || = "+ Matrices.norma1(testQRb));
        
        System.out.println("Qb = "+ Matrices.toString(QRb[0]));
        System.out.println("Rb = "+ Matrices.toString(QRb[1]));
        
        //prueba de solveQR en ejercicio 2
        
        
        
    }
    
}
