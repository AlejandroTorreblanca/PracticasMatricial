/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANM2entrega;

import auxiliar.Matrices;

/**
 *
 * @author Alejandro
 */
public class Ejercicio22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\t\t****Apartado I****\n\n");
        int n=6;
        double[][] A={
                        {1,2,3,4,5,6},
                        {2,13,4,5,6,7},
                        {3,4,25,6,7,8},
                        {4,5,6,37,8,9},
                        {5,6,7,8,49,10},
                        {6,7,8,9,10,51}
                      };
        double[][][] T=Matrices.QRTriDiag(A);
        System.out.println("La matriz tridiagonal calculada es: \n"+Matrices.toString(T[0]));
        try {
            double[][] Qt=Matrices.traspuesta(T[1]);
            double[][] A2=Matrices.producto(T[1], Matrices.producto(A, Qt));
            System.out.println("\nEl cambio de base utilizado (Q):"+Matrices.toString(T[1]));
            
            System.out.println("Si multiplicamos Q*TriD*Qt : "+Matrices.toString(A2));
        } catch (Matrices.ErrorMatrices ex) {
        }
        
        
        System.out.println("\n\n\t\t****Apartado II****\n\n");
        double[][] T1=T[0];
        double[] dP=new double[n];
        double[] dI=new double[n];
        double[] dS=new double[n];
        for (int i = 0; i < T1.length; i++) {
                for (int j = 0; j < T1.length; j++) {
                    if(i==j)
                       dP[i]=T1[i][j];
                    else if (i==j+1)
                    {
                        dI[j]=T1[i][j];
                        dS[j]=T1[i][j];
                        
                    }
                }
            }
        Matrices.Tridiagonal T2=new Matrices.Tridiagonal(dP, dI, dS);
        try {
            double[][] C=Matrices.TriDiagPropiosQR(T2, 0.0001, 100000);
            System.out.println("La matriz obtenida es:\n"+Matrices.toString(C));
        } catch (Matrices.ErrorMatrices ex) {
        }
        
        
    }
}
