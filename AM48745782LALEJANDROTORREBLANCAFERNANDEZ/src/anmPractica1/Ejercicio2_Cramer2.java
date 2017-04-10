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
public class Ejercicio2_Cramer2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        int n=11;
        double[][][] H=new double[n][][];
        for (int k = 0; k < n; k++) {
            H[k]=new double[k+1][k+1];
            for (int i = 0; i <= k; i++) {
                for (int j = 0; j <= k; j++) {
                    H[k][i][j]=1./(i+j+1);
                }
            }
        }
        double[][] b=new double[n][];
        for (int k = 0; k < n; k++) {
           b[k]=new double[k+1];
            for (int i = 0; i <=k; i++) {
               b[k][i]=0;
                for (int j = 0; j <=k; j++) {
                   b[k][i] += H[k][i][j];
                }
            }
        }
        //Apartado 1
        for (int k = 0; k <n; k++) {
           double[] sol=Matrices.Cramer(H[k], b[k]);
            System.out.println("k =" +(k+1) +" sol = "+ Matrices.toString(sol));
            System.out.println("||H_k sol -b_k|| = "+ Matrices.normaResidual(H[k], sol, b[k]));
        }
        
    }
    
}
