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

        int n=0;
        double[][] A;
        double[][] B;
        double[][] C;
        double[][] D;
        long t0,t1,t2;       

        for (int i = 0; i < 3; i++) {
        switch(i){
            case 0: n=32;
                break;
            case 1: n=128;
                break;
            case 2: n=1024;
                break;
        }  
        A=auxiliar.Matrices.MatrizRango(n,n,n);
        B=auxiliar.Matrices.MatrizRango(n,n,n);
        t0=System.currentTimeMillis();
        C=auxiliar.Matrices.productoStrassen(A, B);
        t1=System.currentTimeMillis();
        D=auxiliar.Matrices.producto(A, B);

        t2=System.currentTimeMillis();
        System.out.println("Tiempo en calcular el producto mediante Strassen de dos matrices de tamaño "+n+": "+(t1-t0)+" milisegundos" );
        System.out.println("Tiempo en calcular el producto normal de dos matrices de tamaño "+n+": "+(t2-t1)+" milisegundos" );
        System.out.println("norma1 producto Strassen: "+Matrices.norma1(C)+"\nnorma2 producto normal: "+ Matrices.norma1(D)+"\n\n");
        }
        
        /* 
        Se puede observar que las normas de las matrices siempre coinciden lo que indica que el metodo funciona correctamente.
        También vemos que al pasar a un tamaño de n=1024 se tardan varios segundos en obtener el resultado pero mediante Strassen 
        se ejecuta el producto mucho más rápido.
        */


    }
    
}
