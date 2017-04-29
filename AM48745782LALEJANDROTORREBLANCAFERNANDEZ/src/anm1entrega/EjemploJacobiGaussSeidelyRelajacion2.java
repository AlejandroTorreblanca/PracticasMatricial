/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anm1entrega;

import auxiliar.Matrices;

/**
 *
 * @author MILANOP02
 */
public class EjemploJacobiGaussSeidelyRelajacion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n = 10;
        int nmaxiter = 100000;
        double tol = 0.5E-6;
        double[][] A = matrizCoef(n);
        double[] b = new double[n];
        double[] xo = new double[n];

        b[0] = 100;
        b[1] = 100;
        for (int i = 2; i < n; i++) {
            b[i] = 2 * Math.abs(49 - i);

        }

        System.out.println("A = " + Matrices.toString(A));
        //System.out.println("b = "+ Matrices.toString(b));

        double[] sol = new double[n];

        try {
//            //sol = Matrices.solveGaussP(A, b);
//            // System.out.println("matrizmatriz: ;"+'\n'+Matrices.toString(A));
//            // System.out.println("sol Gauss a A x =b es x "+ Matrices.toString(sol) );
//
//            // sol= Matrices.Cramer(A, b);
//            // System.out.println("sol Cramer a A x =b es x "+ Matrices.toString(sol) );
//
//            // double[] error = Matrices.residual(A, sol, b);
//            // System.out.println("tama~no del error Gauss = " + Matrices.norma(error));
            xo[0] = 1;
            double[] xiter = Matrices.iterJacobi(A, b, tol, nmaxiter, xo, true);
            double[] error = Matrices.residual(A, xiter, b);
            System.out.println("Jacobi tama~no del error = " + Matrices.norma(error));
        } catch (Matrices.ErrorMatrices errr) {
        }
        try {
            double[] xiter = Matrices.iterGaussSeidel(A, b, tol, nmaxiter, xo, false);
            double[] error = Matrices.residual(A, xiter, b);
            System.out.println("Gauss-Seidel tama~no del error = " + Matrices.norma(error));
        } catch (Matrices.ErrorMatrices errr) {
        }
        try {
            double[][] J = matrizCoefJ(n);
            double w = Matrices.aproxRadioEspectral(J);
            System.out.println("rho(J) aprox= " + w);
            w = 2 / (1 + Math.sqrt(1 - w * w));
            System.out.println("w= " + w);
            double[] xiter = Matrices.iterrelajacion(A, b, w, tol, nmaxiter, xo, false);
            double[] error = Matrices.residual(A, xiter, b);
            System.out.println("tama~no del error = " + Matrices.norma(error) + "\n");

            w = 1.27;
            System.out.println("w= " + w);
            xiter = Matrices.iterrelajacion(A, b, w, tol, nmaxiter, xo, false);
            error = Matrices.residual(A, xiter, b);
            System.out.println("tama~no del error = " + Matrices.norma(error) + "\n");

            w = 0.83;

            System.out.println("w aprox = " + w);

            xiter = Matrices.iterrelajacion(A, b, w, tol, nmaxiter, xo, false);
            error = Matrices.residual(A, xiter, b);
            System.out.println("tama~no del error = " + Matrices.norma(error) + "\n");

        } catch (Matrices.ErrorMatrices errr) {
        }

    }

    public static double[][] matrizCoef(int n) {
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) {
            A[i][i] = 4;
            if (i < n - 1) {
                A[i][i + 1] = -1;
            }
            if (i > 1) {
                A[i][i - 2] = -1;
            }
            if (i > 0) {
                A[i][i - 1] = -1;
            }
            if (i < n - 2) {
                A[i][i + 2] = -1;
            }
        }

        return A;
    }

    public static double[][] matrizCoefJ(int n) {
        double[][] J = new double[n][n];
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                J[i][i + 1] = -1. / 4;
            }
            if (i > 0) {
                J[i][i - 1] = -1. / 4;
            }
            if (i > 1) {
                J[i][i - 1] = -1. / 4;
            }
            if (i < n - 2) {
                J[i][i + 2] = -1. / 4;
            }
        }

        return J;
    }
// 0.3489065502000021 , 0.39562620080000843 , 0.2335982530000315
// 0.3489065502000021 , 0.39562620080000843 , 0.2335982530000315
}
