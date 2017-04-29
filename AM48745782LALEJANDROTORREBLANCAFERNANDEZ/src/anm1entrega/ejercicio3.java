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
public class ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        int n=80;
        System.out.println("Para el tamaño de matriz "+n+":\n");
        double[][] A=new double[n][n];
            //Construimos la matriz del sistema de ecuaciones que queremos resolver con tamaño n=10.
            for (int i = 0; i < n; i++) {
                A[i][i]=8;
                if((i+2)<(n-2))
                    A[i][i+2]=1;
                if((i-2)>=0)
                    A[i][i-2]=1;
                if((i+4)<(n-4))
                    A[i][i+4]=2;
                if((i-4)>=0)
                    A[i][i-4]=2;
            }
        double[][] auxb=Matrices.MatrizRango(1, n, 1);
        double[] b=Matrices.copia(auxb[0]);
        double[] x=Matrices.solveGradienteDescenso(A, b);
        double[] xo = new double[n];
        
        
        System.out.println("xGradienteDescenso = "+ Matrices.toString(x));
        double[] error = Matrices.residual(A,  x, b);
        System.out.println("tamaño del error GradienteDescenso = " + Matrices.norma(error)+"\n");
        
        try {
        xo[0] = 1;
                xo[1] = -1;
                xo[2] = 2;
                xo[5]=3;
                
                int nmaxiter=200;
                double tol = 0.5E-9;

                //Método de Jacobi
                double[] xiter = Matrices.iterJacobi(A, b, tol, nmaxiter, xo, false);
                System.out.println("xJacobi = "+ Matrices.toString(xiter));
                error = Matrices.residual(A, xiter, b);
                System.out.println("tamaño del error J  = " + Matrices.norma(error)+"\n");

                //Método de Gauss Seidel
                xiter = Matrices.iterGaussSeidel(A, b, tol, nmaxiter, xo, false);
                System.out.println("xGaussSeidel = "+ Matrices.toString(xiter));
                error = Matrices.residual(A,  xiter, b);
                System.out.println("tamaño del error GS = " + Matrices.norma(error)+"\n");
                
                //Método de Relajación
                double w = 1.27;
                System.out.println("w= " + w);
                xiter = Matrices.iterrelajacion(A, b, w, tol, nmaxiter, xo, false);
                System.out.println("xRelajacion = "+ Matrices.toString(xiter));
                error = Matrices.residual(A, xiter, b);
                System.out.println("tamaño del error = " + Matrices.norma(error) + "\n");

             }catch (Matrices.ErrorMatrices errr) {
        }
    }
}   

