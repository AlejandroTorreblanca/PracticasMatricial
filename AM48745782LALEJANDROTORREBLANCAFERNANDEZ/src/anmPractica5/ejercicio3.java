/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica5;

import auxiliar.Matrices;

/**
 *
 * @author Alejandro
 */
public class ejercicio3 {

    public static void main(String[] args) throws Matrices.ErrorMatrices {

        int n=10;
        double[] dP=new double[n];
        double[] dI=new double[n];
        double[] b=new double[n];
        double[] dC=new double[n];
        for (int i = 0; i < n-1; i++) {
            dI[i]=-1;
            dP[i]=2*(i+1);
            b[i]=1.5*(i+1)-6;
            dC[i]=Math.sqrt(2*(i+1));
        }
        dP[n-1]=2*n;
        b[n-1]=1.5*n-6;
        dC[n-1]=Math.sqrt(2*n);
        auxiliar.Matrices.tridiagonal td=new auxiliar.Matrices.tridiagonal(dP, dI, dI);
        auxiliar.Matrices.Diagonal C=new auxiliar.Matrices.Diagonal(dC);
        System.out.println("n = "+ n);
        double tol=1E-13;
        double[] x=auxiliar.Matrices.gradienteConjugadoPrecondicionado(td, C, b, new double[n], tol);
        System.out.println("sin precondicionamiento ");
        x=auxiliar.Matrices.gradienteConjugado(td, b, new double[n], tol);
    }
    
}
