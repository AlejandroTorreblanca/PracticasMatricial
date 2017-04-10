/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica2;

import anmPractica1.*;
import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio1_Cramer2 {
    
    public static double Dconv(int n, int m){
        double dconv=1.;
        for (int i = 1; i <= m; i++) {
            dconv=dconv*(n-i+1.)/i;
        }  
        return dconv;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        int n=17;
        double[][][] H=new double[n][][];
        for (int k = 0; k < n; k++) {
            H[k]=new double[k+1][k+1];
            for (int i = 0; i <= k; i++) {
                for (int j = 0; j <= k; j++) {
                    H[k][i][j]=1./(i+j+1);
                }
            }
        }
      double[][][] invH=new double[n][][];
        for (int k = 1; k <= n; k++) {
            invH[k-1]=new double[k][k];
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= k; j++) {
                    invH[k-1][i-1][j-1]=Math.pow(-1, i+j)*(i+j-1)*
                            Dconv(k+i-1,k-j)*
                            Dconv(k+j-1, k-i)*
                            Math.pow(Dconv(i+j-2, i-1),2);
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
//        //Apartado 1 Ejercicio 1.1
//        for (int k = 0; k <9; k++) {
//           double[] sol=Matrices.Cramer(H[k], b[k]);
//            System.out.println("k =" +(k+1) +" sol = "+ Matrices.toString(sol));
//            System.out.println("||H_k sol -b_k|| = "+ Matrices.normaResidual(H[k], sol, b[k]));
//        }
        //Apartado 4 Ejercicio 2.1
        for (int k = 0; k < n; k++) {
           double[][] test= Matrices.producto(H[k], invH[k]);
//            System.out.println(" "+ Matrices.toString(test));
           double[][] testId=Matrices.resta(Matrices.Id(k+1),test);
            System.out.println("\n k = "+ (k+1)+ "\t testId = "+ Matrices.norma1(testId));
            
            double cond_1=Matrices.norma1(H[k])*Matrices.norma1(invH[k]);
            System.out.println("Numer cond H["+k+"]= "+ cond_1);
            System.out.println("cond_1 / e^{3.5 n} = " + (cond_1/Math.exp(3.5*(k+1))));
            
            System.out.println("aprox Rho(H) "+ Matrices.aproxRadioEspectral(H[k]));
            System.out.println("aprox Rho(invH) "+ Matrices.aproxRadioEspectral(invH[k]));
            
            if(k<10){
                double[] sol = Matrices.Cramer(H[k], b[k]);
                System.out.println("k =" + (k + 1) + "\n"
                        + " ||Hx-b||( Cramer) = " + 
                        Matrices.normaResidual(H[k], sol, b[k]));

            }
            
            double[] sol=Matrices.solveCholeski(H[k], b[k]);
            System.out.println("k =" + (k + 1) + "\n"
                        + " ||Hx-b||( Choleski ) = " + 
                        Matrices.normaResidual(H[k], sol, b[k]));
        }
        
        
    }
    
}
