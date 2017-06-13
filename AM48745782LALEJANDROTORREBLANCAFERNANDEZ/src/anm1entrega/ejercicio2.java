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
public class ejercicio2 {

    public static void main(String[] args) throws Matrices.ErrorMatrices {

        for (int n = 10; n < 90; n=n+70) //la variable n toma los valores 10 y 80 que son los tamaños de las matrices.
        { 
            double[][] A=new double[n][n];
            //Construimos la matriz del sistema de ecuaciones que queremos resolver con tamaño n.
            for (int i = 0; i < n; i++) {
                A[i][i]=8;
                if((i+2)<n)
                    A[i][i+2]=1;
                if((i-2)>=0)
                    A[i][i-2]=1;
                if((i+4)<n)
                    A[i][i+4]=2;
                if((i-4)>=0)
                    A[i][i-4]=2;
            }

            double[][] auxb=Matrices.MatrizRango(1, n, 1);
            double[] b=Matrices.copia(auxb[0]);
            double[] xo = new double[n];
            double[] error = new double[n]; 

            System.out.println("Tamaño de la matriz "+n+"x"+n+":");
            try {
            //Descomentar si se desea resolver mediante Gauss o Cramer:
//                double[] sol = Matrices.solveGaussP(A, b);
//                //System.out.println("sol Gauss a A x =b es x "+ Matrices.toString(sol) );
//                error = Matrices.residual(A, sol, b);
//                System.out.println("tamaño del error Gauss = " + Matrices.norma(error));
//                System.out.println("");
//                
//                sol= Matrices.Cramer(A, b);
//                //System.out.println("sol Cramer a A x =b es x "+ Matrices.toString(sol) );
//                error = Matrices.residual(A, sol, b);
//                System.out.println("tamaño del error Cramer = " + Matrices.norma(error));
//                System.out.println("");

                //Aproximación inicial:
                xo[0] = 1;
                xo[1] = -1;
                xo[2] = 2;
                xo[5]=3;
                
                int nmaxiter=200;
                double tol = 0.5E-8;

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
                double w = 1.27; //w debe tomar valores entre 0 y 2.
                System.out.println("w= " + w);
                xiter = Matrices.iterrelajacion(A, b, w, tol, nmaxiter, xo, false);
                System.out.println("xRelajacion = "+ Matrices.toString(xiter));
                error = Matrices.residual(A, xiter, b);
                System.out.println("tamaño del error = " + Matrices.norma(error) + "\n");

            } catch (Matrices.ErrorMatrices errr) {
            }
        }
    }
}
