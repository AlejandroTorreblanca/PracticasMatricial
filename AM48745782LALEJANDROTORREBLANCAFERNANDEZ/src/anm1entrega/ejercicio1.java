/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anm1entrega;

import auxiliar.Matrices;

/**
 *
 * @author Alejandro
 */
public class ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {

        int n=1024;
        double[][] A=auxiliar.Matrices.MatrizRango(n,n,n);
        double[][] B=auxiliar.Matrices.MatrizRango(n,n,n);
        long t0=System.currentTimeMillis();
        double[][] C=auxiliar.Matrices.productoStrassen(A, B);
        long t1=System.currentTimeMillis();
        double[][] D=auxiliar.Matrices.producto(A, B);
        long t2=System.currentTimeMillis();
        System.out.println("Tiempo en calcular el producto mediante Strassen de dos matrices de tamaño "+n+": "+(t1-t0)+" milisegundos" );
        //System.out.println("\n"+Matrices.toString(C));
        System.out.println("Tiempo en calcular el producto normal de dos matrices de tamaño "+n+": "+(t2-t1)+" milisegundos" );
        //System.out.println("B \n"+Matrices.toString(D));
        System.out.println("norma1: "+Matrices.norma1(C)+"\nnorma2: "+ Matrices.norma1(D));
        


    }
    
}
