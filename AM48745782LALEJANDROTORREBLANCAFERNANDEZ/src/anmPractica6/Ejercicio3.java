/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica6;

import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // TODO code application logic here
        double[][] A={
            {15,-85,225,-274,120},
            {1,0,0,0,0},
            {0,1,0,0,0},
            {0,0,1,0,0},
            {0,0,0,1,0}
        };
        double prec=0.5E-8;
        double precDesp=0.1;
        int nmax=1000;
        double[][] QRpropiosA=Matrices.QRpropios(A, prec, precDesp, nmax);
        
        System.out.println("\n  \n  \n \n  B");
        double[][] B={{2,0,0,0},{0,3,0,0},{0,0,1,0},{0,0,0,5}};
        double[][] S={{1,-1,0,1},{1,1,-1,0},{0,1,1,1},{1,0,1,-1}};
        S=Matrices.producto(1/Math.sqrt(3), S);
        B=Matrices.producto(Matrices.traspuesta(S), Matrices.producto(B, S));
        
        double[][] QRpropiosB=Matrices.QRpropios(B, prec, precDesp, nmax);
        
         // B = Q^t Diag Q , Diag= espectro, Q columnas son vectores propios
         // Buscamos raiz cuadrada de B   raizB= Q^t raizDiag Q
         double[][] raizDiag=new double[4][4];
         double[][] Q= new double[4][4];
         for (int i = 0; i < Q.length; i++) {
           raizDiag[i][i]=Math.sqrt(QRpropiosB[i][4]);
           System.arraycopy(QRpropiosB[i], 0, Q[i], 0, 4);
        }
        double[][] raizB=Matrices.producto(Q, raizDiag);
         Q=Matrices.traspuesta(Q);
         raizB=Matrices.producto(raizB, Q);
        
        // raizB raizB =B
        System.out.println(" test "+
                Matrices.norma1(Matrices.resta(Matrices.producto(raizB, raizB), B)));
        
    }
    
}
