/*
 * Matrices.java
 *
 * Creado el 31 de enero de 2011, 11:00
 */
package auxiliar;

import ORG.netlib.math.complex.Complex;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Matrices {

    /**
     * Variable precision utilizada para considerar nulos los numeros de menor
     * tamaño
     */
    static double precision = 5E-16;

    /**
     * El metodo nos dice si la matriz es o no una matriz cuadrada
     */
    public static boolean ifCuadrada(double[][] A) {
        int n = A.length;
        boolean respuesta = true;
        for (int i = 0; i < n; i++) {
            if (A[i].length != n) {
                respuesta = false;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Comprueba que la lista doble introducida es una matriz.
     * @param A
     * @return 
     */
    public static boolean ifMatriz(double[][] A) {
        int n = A.length;
        int m = A[0].length;
        boolean respuesta = true;

        for (int i = 0; i < n; i++) {
            if (A[i].length != m) {
                respuesta = false;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Metodo que devuelve una copia del vector v
     *
     * @param v
     * @return
     */
    public static double[] copia(double[] v) {
        int n = v.length;
        double[] cv = new double[n];
        System.arraycopy(v, 0, cv, 0, n);
        return cv;
    }

    public static Complex[] copia(Complex[] v) {
        int n = v.length;
        Complex[] cv = new Complex[n];
        System.arraycopy(v, 0, cv, 0, n);
        return cv;
    }

    /**
     * Metodo que devuelve una copia de la matriz A
     *
     * @param A
     * @return
     */
    public static double[][] copia(double[][] A) {
        int n = A.length;
        double[][] cA = new double[n][];
        for (int i = 0; i < n; i++) {
            cA[i] = copia(A[i]);
        }
        return cA;
    }

    public static Complex[][] copia(Complex[][] A) {
        int n = A.length;
        Complex[][] cA = new Complex[n][];
        for (int i = 0; i < n; i++) {
            cA[i] = copia(A[i]);
        }
        return cA;
    }

    /**
     * Metodo que devuelve la matriz de una fila definida por el vector v
     *
     * @param v
     * @return
     */
    public static double[][] matriz1Fila(double[] v) {
        int n = v.length;
        double[][] fila = new double[1][n];
        for (int i = 0; i < n; i++) {
            fila[0][i] = v[i];
        }
        return fila;
    }

    /**
     * Metodo que devuelve la matriz de una columna definida por el vector v
     *
     * @param v
     * @return
     */
    public static double[][] matriz1Columna(double[] v) {
        int n = v.length;
        double[][] columna = new double[n][1];
        for (int i = 0; i < n; i++) {
            columna[i][0] = v[i];
        }
        return columna;
    }

    /**
     * DEVUELVE la matriz traspuesta de una matriz REAL
     */
    public static double[][] traspuesta(double[][] A)
            throws ErrorMatrices {
        int n;
        int m;
        double[][] At;

        if (!ifMatriz(A)) {
            System.out.println("Error: matriz mal definida");
            throw new ErrorMatrices();
        }

        n = A.length;
        m = A[0].length;
        At = new double[m][n];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                At[j][i] = A[i][j];
            }
        }
        return At;
    }

    
    
    
    /**
     * Devuelve la matriz adjunta de una matriz COMPLEJA
     */
    public static Complex[][] adjunta(Complex[][] A)
            throws ErrorMatrices {
        int n = A.length;
        int m = A[0].length;
        Complex[][] At;

        boolean cuadrada = true;
        for (int i = 1; i < n; i++) {
            if (A[i].length != m) {
                cuadrada = false;
                break;
            }
        }

        if (!cuadrada) {
            System.out.println("Error: matriz mal definida");
            throw new ErrorMatrices();
        }

        m = A[0].length;
        At = new Complex[m][n];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                At[j][i] = A[i][j].conj();
            }
        }
        return At;
    }

    /**
     * El metodo realiza la suma de dos matrices
     */
    public static double[][] suma(double[][] A, double[][] B)
            throws ErrorMatrices {
        int n;
        int m;
        double[][] AmasB;

        if (!ifMatriz(A)) { // ifmatriz(A) == false
            System.out.println("Error: matriz izquierda mal definida");
            throw new ErrorMatrices();
        }
        if (!ifMatriz(B)) {
            System.out.println("Error: matriz derecha mal definida");
            throw new ErrorMatrices();
        }
        n = B.length; // filas de B
        m = B[0].length; // columnas de B
        if ((n != A.length) || (m != A[0].length)) { // comparar con A
            System.out.println("Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }

        AmasB = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                AmasB[i][j] = A[i][j] + B[i][j];
            }
        }
        return AmasB;
    }

    /**
     * El metodo nos da la suma de dos vectores
     */
    public static double[] suma(double[] u, double[] v)
            throws ErrorMatrices {

        return suma(matriz1Fila(u), matriz1Fila(v))[0];
    }

    /**
     * Resta de dos matrices
     */
    public static double[][] resta(double[][] A, double[][] B)
            throws ErrorMatrices {
        double[][] menosB = producto(-1, B);

        return suma(A, menosB);
    }

    /**
     * Resta de dos vetores
     */
    public static double[] resta(double[] u, double[] v)
            throws ErrorMatrices {
        double[] menosv = producto(-1, v);

        return suma(u, menosv);
    }

    /**
     * Producto de dos matrices
     */
    public static double[][] producto(double[][] A, double[][] B)
            throws ErrorMatrices {
        int n; // filas A
        int m; // columnas A y filas B
        int p; // columnas B
        double[][] AporB;

        if (!ifMatriz(A)) { // ifmatriz(A) == false
            System.out.println("Error: matriz izquierda mal definida");
            throw new ErrorMatrices();
        }
        if (!ifMatriz(B)) {
            System.out.println("Error: matriz derecha mal definida");
            throw new ErrorMatrices();
        }

        if ((A[0].length != B.length)) { // comparar con A
            System.out.println("Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }

        n = A.length; // filas de A
        m = A[0].length; // (B.length) columnas de A (o filas de B)
        p = B[0].length; // columnas de B

        AporB = new double[n][p];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < p; k++) {
                for (int j = 0; j < m; j++) {
                    AporB[i][k] = AporB[i][k] + A[i][j] * B[j][k];
                }
            }
        }
        return AporB;
    }

    /**
     * Calcula el vector residual v=Ax-b.
     * @param A
     * @param x
     * @param b
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[] residual(double[][] A, double[] x, double[] b)
            throws ErrorMatrices {
        return resta(producto(A, x), b);
    }

    /**
     * Calcula la norma del vector residual ||Ax-b||.
     * @param A
     * @param x
     * @param b
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double normaResidual(double[][] A, double[] x, double[] b)
            throws ErrorMatrices {
        return norma(residual(A, x, b));
    }

    /**
     * Producto de dos vectores
     *
     * @param u
     * @param v
     * @return (u,v)
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double producto(double[] u, double[] v)
            throws ErrorMatrices {
        return producto(matriz1Fila(u), matriz1Columna(v))[0][0];
    }

    /**
     * Producto de un escalar por un vector
     *
     * @param c escalar
     * @param u vector
     * @return c.u
     */
    public static double[] producto(double c, double[] u) {
        int n = u.length;
        double[] v = new double[n];
        for (int i = 0; i < n; i++) {
            v[i] = c * u[i];
        }

        return v;
    }

    /**
     * Producto de una matriz por un escalar
     */
    public static double[][] producto(double c, double[][] A) {

        int n = A.length;
        double[][] cA = new double[n][];
        for (int i = 0; i < n; i++) {
            cA[i] = producto(c, A[i]);
        }
        return cA;
    }

    /**
     * Metodo que devuelve el producto de una matriz A por un vector v
     *
     * @param A matriz double[][] de dimensión mxn
     * @param v lista double[] de dimensión n
     * @return el vector producto de A por el vector columna v representado en
     * una lista double[]
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[] producto(double[][] A, double[] v)
            throws ErrorMatrices {

        if (!ifMatriz(A)) {
            System.out.println("Error: matriz mal definida");
            throw new ErrorMatrices();
        }
        int n = A[0].length;
        int m = v.length;
        if (n != m) {
            System.out.println("Error: dimensiones incompatible");
            throw new ErrorMatrices();
        }

        return traspuesta(producto(A, matriz1Columna(v)))[0];

    }

    /**
     * Convierte una matriz en String
     */
    public static String toString(double[][] A) {
        return MetodosListas.toString(A);
    }

    /**
     * Convierte un vector a String
     */
    public static String toString(double[] u) {
        return MetodosListas.toString(u);
    }

    /**
     * Escribe una matriz dada
     */
    public static void escribe(double[][] A) {

        System.out.println(MetodosListas.toString(A));

    }

    /**
     * Escribe un vector
     */
    public static void escribe(double[] u) {
        System.out.println(MetodosListas.toString(u));
    }

    /**
     * Calcula la norma euclidea de un vector
     */
    public static double norma(double[] u) {
        double norm = 0;

        for (int i = 0; i < u.length; i++) {
            norm = norm + u[i] * u[i];
        }
        return Math.sqrt(norm);
    }

    /**
     * Calcula la norma uno de un vector
     */
    public static double norma1(double[] u) {
        double norm = 0;

        for (int i = 0; i < u.length; i++) {
            norm = norm + Math.abs(u[i]);
        }
        return norm;
    }

    /**
     * Calcula la norma del supremo de un vector
     */
    public static double normaInfinito(double[] u) {
        double norm = 0;

        for (int i = 0; i < u.length; i++) {
            double x = Math.abs(u[i]);
            if (norm < x) {
                norm = x;
            }
        }
        return norm;
    }

    /**
     * norma subordinada a la norma 1 de R^n
     *
     * @param A
     * @return
     */
    public static double norma1(double[][] A) {
        double norma = 0;
        for (int j = 0; j < A[0].length; j++) {
            double aux = 0;
            for (int i = 0; i < A.length; i++) {
                aux += Math.abs(A[i][j]);
            }
            if (norma < aux) {
                norma = aux;
            }
        }
        return norma;
    }

    public static double normaInfinito(double[][] A) {
        double norma = 0;
        for (int i = 0; i < A.length; i++) {
            double aux = 0;
            for (int j = 0; j < A[0].length; j++) {
                aux += Math.abs(A[i][j]);
            }
            if (norma < aux) {
                norma = aux;
            }
        }
        return norma;
    }

    /**
     * Metodo que proporciona el menor de la matriz A al eliminar la fila i y la
     * columna j
     *
     */
    public static double[][] menor(double[][] A, int i, int j)
            throws ErrorMatrices {

        if (!ifCuadrada(A)) {
            System.out.println("Error: matriz no cuadrada");
            throw new ErrorMatrices();
        }

        int n = A.length;
        if ((i >= n) || (i < 0) || (j >= n) || (j < 0)) {
            System.out.println("Error: indices no compatibles");
            throw new ErrorMatrices();
        }

        if (n == 1) {
            return new double[1][1];
        } else {
            double[][] Aux = new double[n - 1][n - 1];

            for (int m = 0; m < i; m++) {
                for (int k = 0; k < j; k++) {
                    Aux[m][k] = A[m][k];
                }
                for (int k = j; k < n - 1; k++) {
                    Aux[m][k] = A[m][k + 1];
                }
            }
            for (int m = i; m < n - 1; m++) {
                for (int k = 0; k < j; k++) {
                    Aux[m][k] = A[m + 1][k];
                }
                for (int k = j; k < n - 1; k++) {
                    Aux[m][k] = A[m + 1][k + 1];
                }
            }

            return Aux;
        }
    }

    /**
     * Método para calcular el determinante desarrollando por filas.
     *
     * @param A = matriz cuadrada
     * @return determinante
     * @throws auxiliar.Matrices.ErrorMatrices si no es cuadrada
     */
    public static double determinanteMenores(double[][] A) throws ErrorMatrices {
        double determinante = 0;
        if (ifCuadrada(A) == false) {
            System.out.println("Error: Matriz no cuadrada");
            throw new ErrorMatrices();
        }
        double signo = 1;
        if (A.length == 1) {
            return A[0][0];
        }
        for (int j = 0; j < A.length; j++) {
            double[][] menor = menor(A, 0, j);
            determinante = determinante + signo * determinanteMenores(menor) * A[0][j];
            signo = -1 * signo;
        }

        return determinante;
    }

    /**
     * Método de Cramer para resolver Ax=b
     *
     * @param A
     * @param b
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[] Cramer(double[][] A, double[] b) throws ErrorMatrices {
        int n = A.length;
        double determinante = determinanteMenores(A);
        if (determinante == 0) {
            System.out.println("La matriz es singular");
            throw new ErrorMatrices();
        }
        double[] solucion = new double[n];
        for (int i = 0; i < n; i++) {
            double[][] Aux = new double[n][n];
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    if (j == i) {
                        Aux[k][j] = b[k];
                    } else {
                        Aux[k][j] = A[k][j];
                    }

                }

            }
            solucion[i] = determinanteMenores(Aux) / determinante;

        }

        return solucion;
    }

    /**
     * devuelve la factorización A=QR, Q=QRGramSchmidt[0] es una matriz formada
     * por vectores ortogonales dos a dos de norma 1. R=QRGramSchmidt[1] es una
     * matriz triangular superior (R[i][j]=0 si i>j) que contiene las
     * coordenadas de los vectores columna de A con respecto a los vectores
     * columna de Q e.d A^j = Sum_{i=0,...,j} R[i][j] V^{i}
     *
     * @param A
     * @return QR[2][][]
     */
    public static double[][][] QRGramSchmidt(double[][] A) throws ErrorMatrices {
        double[][][] QR = new double[2][][];
        // suponemos A está bien definida es una matriz rectangular
        // if( ifMatriz(A)! )throw Error;
        int m = A.length;
        int n = A[0].length;
        double[][] V = traspuesta(A);
        int nq = 0; //numero de columnas de Q
        int j = 0; //indice para recorrer las columnas de A = filas de V
        double[][] tQ = new double[n][m]; //traspuesta de Q (provisional)
        double[][] Rtmp = new double[n][n]; // R (provisional)
        while (j < n) {
            double[] aux = copia(V[j]);
            for (int k = 0; k < nq; k++) {
                Rtmp[k][j] = producto(V[j], tQ[k]);
                aux = resta(aux, producto(Rtmp[k][j], tQ[k]));
            }
            double norma = norma(aux);
            if (norma > 1000 * precision) {
                tQ[nq] = producto(1. / norma, aux);
                Rtmp[nq][j] = norma;
                nq++;

            }
            j++;
        }
        double[][] R = new double[nq][n];
        double[][] tQr = new double[nq][m];
        for (int i = 0; i < nq; i++) {
            R[i] = copia(Rtmp[i]);
            tQr[i] = copia(tQ[i]);
        }
        QR[0] = traspuesta(tQr);
        QR[1] = R;
        return QR;
    }

    /**
     * Método para construir una matriz identidad de  dimensión n.
     * @param n
     * @return 
     */
    public static double[][] Id(int n) {
        double[][] Id = new double[n][n];
        for (int i = 0; i < Id.length; i++) {
            Id[i][i] = 1;
        }
        return Id;
    }

    public static double aproxRadioEspectral(double[][] A) throws
            ErrorMatrices {
        double[][] B = copia(A); // B= A^n
        double aprox = norma1(A);
        for (int i = 0; i < 50; i++) {
            B = producto(A, B);
            double norma = norma1(B);
            // System.out.println("i = " + i + "\t norma_1(B)= " + norma);
            double aux = Math.pow(norma, 1. / (i + 2));
            // System.out.println("n = " + (i + 2) + "\t aproxRadioEs= " + aux);
            if (Double.isInfinite(aux)) {
                return aprox;
            }
            if (Double.isNaN(aux)) {
                return aprox;
            }
            if (Math.abs(aprox - aux) < 0.1) {
                return aprox;
            }
            aprox = aux;
        }
        System.out.println("No hay convergencia en la aproximación");
        return aprox;
    }

    /**
     * Metodo para resolver sistemas con matriz de coeficientes triangular
     * inferior L
     *
     * @param L
     * @param b
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[] solveDescendente(double[][] L, double[] b)
            throws ErrorMatrices {
        int n = L.length; //se puede ver que L sea cuadrada.
        if (b.length != n) {
            System.out.println("Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            if (Math.abs(L[i][i]) < precision) {
                System.out.println("Error: matriz singular");
                throw new ErrorMatrices();
            }
            x[i] = b[i];
            for (int j = 0; j < i; j++) {
                x[i] -= L[i][j] * x[j];
            }
            x[i] = x[i] / L[i][i];

        }
        return x;
    }

    /**
     * Metodo para resolver sistemas con matriz de coeficientes triangular
     * superior U
     *
     * @param U
     * @param b
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[] solveAscendente(double[][] U, double[] b) throws ErrorMatrices {
        int n = U.length; //se puede ver que U sea cuadrada.
        if (b.length != n) {
            System.out.println("Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(U[i][i]) < precision) {
                System.out.println("Error: matriz singular");
                throw new ErrorMatrices();
            }
            x[i] = b[i];
            for (int j = i + 1; j < n; j++) {
                x[i] -= U[i][j] * x[j];
            }
            x[i] = x[i] / U[i][i];

        }
        return x;
    }

    /**
     * si A=QR[0]QR[1] con QR[0] ortogonal y QR[1] triangular superior devuelve
     * la solucion de Ax=b como solucion de QR[1]x=QR[0]^t b.
     *
     * @param QR
     * @param b
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[] solveQR(double[][][] QR, double[] b)
            throws ErrorMatrices {
        double[] nb = Matrices.producto(traspuesta(QR[0]), b);
        return solveAscendente(QR[1], nb);
    }

    public static double[][] inversaL(double[][] L) throws ErrorMatrices {
        int n = L.length;
        double[][] invL = new double[n][n];
        double[][] I = Id(n);
        for (int i = 0; i < n; i++) {
            invL[i] = solveDescendente(L, I[i]);

        }
        return traspuesta(invL);
    }

    public static double[][] inversaU(double[][] U) throws ErrorMatrices {
        int n = U.length;
        double[][] invU = new double[n][n];
        double[][] I = Id(n);
        for (int i = 0; i < n; i++) {
            invU[i] = solveAscendente(U, I[i]);

        }
        return traspuesta(invU);
    }

    /**
     * Métpodo de Doolittle para obtener la descomposición A=LU.
     * @param A
     * @return L en la primera posición, U en la segunda.
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[][][] LUdootlittle(double[][] A) throws ErrorMatrices {
        if (!ifCuadrada(A)) {
            System.out.println("Error: matriz no cuadrada");
            throw new ErrorMatrices();
        }
        int n = A.length;
        double[][] L = new double[n][n], U = new double[n][n];
        for (int k = 0; k < n; k++) {
            double aux = A[k][k];
            for (int s = 0; s < k; s++) {
                aux -= L[k][s] * U[s][k];
            }
            if (Math.abs(aux) < precision) {
                System.out.println("Error: no existe factorizacion LU");
                throw new ErrorMatrices();
            }
            L[k][k] = 1;
            U[k][k] = aux;
            for (int j = k + 1; j < n; j++) {
                aux = A[k][j];
                for (int s = 0; s < k; s++) {
                    aux -= L[k][s] * U[s][j];
                }
                U[k][j] = aux;
            }
            for (int i = k + 1; i < n; i++) {
                aux = A[i][k];
                for (int s = 0; s < k; s++) {
                    aux -= L[i][s] * U[s][k];
                }
                L[i][k] = aux / U[k][k];
            }
        }
        double[][][] LU = {L, U};
        return LU;
    }

    /**
     * Para matrices simétricas A devuelve una lista LD[][][] donde L=L[0] es
     * triangular inferior con unos en la diagonal. D=LD[1] es una matriz
     * diagonal y A = L D L^t
     *
     * @param A
     * @return LD[][][]
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[][][] FactorLDR(double[][] A) throws ErrorMatrices {
        if (!ifSimetrica(A)) {
            System.out.println("Error: matriz no es simétrica");
            throw new ErrorMatrices();
        }
        int n = A.length;
        double[][] L = new double[n][n], D = new double[n][n];
        for (int k = 0; k < n; k++) {
            double aux = A[k][k];
            for (int s = 0; s < k; s++) {
                aux -= L[k][s] * D[s][s] * L[k][s];
            }
            if (Math.abs(aux) < precision) {
                System.out.println("Error: no existe factorizacion LU");
                throw new ErrorMatrices();
            }
            L[k][k] = 1;
            D[k][k] = aux;
            for (int i = k + 1; i < n; i++) {
                aux = A[i][k];
                for (int s = 0; s < k; s++) {
                    aux -= L[i][s] * D[s][s] * L[k][s];
                }
                L[i][k] = aux / D[k][k];
            }
        }
        double[][][] LD = {L, D};
        return LD;
    }

    /**
     * Para matrices simétricas A definidas positivas devuelve una L es
     * triangular inferior tal que A = L L^t
     *
     * @param A
     * @return L[][]
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[][] factorLdeCholeski(double[][] A) throws ErrorMatrices {
        if (!ifSimetrica(A)) {
            System.out.println("Error: matriz no es simétrica");
            throw new ErrorMatrices();
        }
        int n = A.length;
        double[][] L = new double[n][n];
        for (int k = 0; k < n; k++) {
            double aux = A[k][k];
            for (int s = 0; s < k; s++) {
                aux -= L[k][s] * L[k][s];
            }
            if (Math.abs(aux) < precision) {
                System.out.println("Error: no existe factorizacion LU");
                throw new ErrorMatrices();
            }
            if (aux < -precision) {
                System.out.println("Error: Matriz no es definida positiva");
                throw new ErrorMatrices();
            }
            L[k][k] = Math.sqrt(aux);

            for (int i = k + 1; i < n; i++) {
                aux = A[i][k];
                for (int s = 0; s < k; s++) {
                    aux -= L[i][s] * L[k][s];
                }
                L[i][k] = aux / L[k][k];
            }
        }
        return L;
    }

    /**
     * Metodo que devuelve la solucion de A x= b, cuando A = L L^t es simétrica
     * definida positiva
     *
     * @param A
     * @param b
     * @return x=solveAscendente(L^t,solveDescendente(L,b))
     */
    public static double[] solveCholeski(double[][] A, double[] b) throws ErrorMatrices {
        double[][] L = factorLdeCholeski(A);
        return solveAscendente(traspuesta(L), solveDescendente(L, b));
    }

    /**
     * Método que devuelve el determinante de una matriz previamente factorizada LU[][][].
     * @param LU
     * @return 
     */
    public static double determinanteLU(double[][][] LU) {
        int n = LU[0].length;
        double det = 1;
        for (int i = 0; i < n; i++) {
            det *= LU[0][i][i] * LU[1][i][i];

        }
        return det;
    }

    public static double[] solLU(double[][][] LU, double[] b) throws ErrorMatrices {

        return solveAscendente(LU[1], solveDescendente(LU[0], b));

    }

    /**
     * devuelve la solución de Ax=b usando el método de Gauss parcial en el que
     * se utiliza como pivote en cada columna el elemento de la fila bajo la
     * diagonal en esa columna que tiene mayor valor absoluto
     *
     * @param A
     * @param b
     * @return
     */
    public static double[] solveGaussP(double[][] A, double[] b) throws ErrorMatrices {
        if (!ifCuadrada(A)) {
            System.out.println("Error: matriz de coeficientes no cuadrada");
            throw new ErrorMatrices();
        }
        int n = A.length;
        if (b.length != n) {
            System.out.println("Error: dimensiones de A y b distintas");
            throw new ErrorMatrices();
        }
        int[] fila = new int[n];
        double[][] B = copia(A);
        double[] v = copia(b);
        double[] x = new double[n];
        // Flujo
        for (int i = 0; i < n; i++) {
            fila[i] = i;
        }
        //Eliminación de Gauss o triangulación
        for (int k = 0; k < n; k++) { // se puede poner n-1
            //busqueda pivote
            int p = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(B[i][k]) > Math.abs(B[p][k])) {
                    p = i;
                }
            }
            //itercambio de filas
            if (p != k) {
                int m = fila[k];
                fila[k] = fila[p];
                fila[p] = m;
            }
            //eliminación
            if (Math.abs(B[fila[k]][k]) < precision) {
                System.out.println("Error: A es singular");
                throw new ErrorMatrices();
            }
            for (int i = k + 1; i < n; i++) {
                double mult = B[fila[i]][k] / B[fila[k]][k];
                B[fila[i]][k] = 0;
                for (int j = k + 1; j < n; j++) {
                    B[fila[i]][j] -= mult * B[fila[k]][j];
                }
                v[fila[i]] -= mult * v[fila[k]];
            }
        }
        //Metodo ascendente
        for (int i = n - 1; i >= 0; i--) {
            x[i] = v[fila[i]];
            for (int j = i + 1; j < n; j++) {
                x[i] -= B[fila[i]][j] * x[j];
            }
            x[i] = x[i] / B[fila[i]][i];
        }

        return x;
    }

    /**
     * Metodo para representar la matriz B "triangular?" que se construye en
     * Gauss indicando la posición de cada fila en una primera columna
     *
     * @param A
     * @param b
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static void showGaussP(double[][] A) throws ErrorMatrices {
        if (!ifCuadrada(A)) {
            System.out.println("Error: matriz de coeficientes no cuadrada");
            throw new ErrorMatrices();
        }
        int n = A.length;

        int[] fila = new int[n];
        double[][] B = copia(A);

        // Flujo
        for (int i = 0; i < n; i++) {
            fila[i] = i;
        }
        //Eliminación de Gauss o triangulación
        for (int k = 0; k < n; k++) { // se puede poner n-1
            //busqueda pivote
            int p = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(B[i][k]) > Math.abs(B[p][k])) {
                    p = i;
                }
            }
            //itercambio de filas
            if (p != k) {
                int m = fila[k];
                fila[k] = fila[p];
                fila[p] = m;
            }
            //eliminación
            if (Math.abs(B[fila[k]][k]) < precision) {
                System.out.println("Error: A es singular");
                throw new ErrorMatrices();
            }
            for (int i = k + 1; i < n; i++) {
                double mult = B[fila[i]][k] / B[fila[k]][k];
                B[fila[i]][k] = 0;
                for (int j = k + 1; j < n; j++) {
                    B[fila[i]][j] -= mult * B[fila[k]][j];
                }

            }
        }
        //Imprimimos la matriz
        System.out.println("B = " + Matrices.toString(B));

        // Inversa del puntero "fila"
        int[] filaInv = new int[n];
        for (int i = 0; i < n; i++) {
            int k = 0;
            while (fila[k] != i) {
                k++;
            }
            filaInv[i] = k;

        }
        double[][] Bamp = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            Bamp[i][0] = filaInv[i];
            System.arraycopy(B[i], 0, Bamp[i], 1, n);

        }
        System.out.println("Bamp = " + Matrices.toString(Bamp));

    }

    /**
     * Método de Gauss-Jordan para invertir una matriz
     *
     * @param A
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[][] inversaGauss(double[][] A) throws ErrorMatrices {
        if (!ifCuadrada(A)) {
            System.out.println("Error: matriz de coeficientes no cuadrada");
            throw new ErrorMatrices();
        }
        int n = A.length;
        int[] fila = new int[n];
        double[][] B = copia(A);

        double[][] invF = Id(n);

        // Flujo
        for (int i = 0; i < n; i++) {
            fila[i] = i;
        }
        //Eliminación de Gauss o triangulación
        for (int k = 0; k < n; k++) { // se puede poner n-1
            //busqueda pivote
            int p = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(B[i][k]) > Math.abs(B[p][k])) {
                    p = i;
                }
            }
            //itercambio de filas
            if (p != k) {
                int m = fila[k];
                fila[k] = fila[p];
                fila[p] = m;
            }
            //eliminación
            if (Math.abs(B[fila[k]][k]) < precision) {
                System.out.println("Error: A es singular");
                throw new ErrorMatrices();
            }
            for (int i = k + 1; i < n; i++) {
                double mult = B[fila[i]][k] / B[fila[k]][k];
                B[fila[i]][k] = 0;
                for (int j = k + 1; j < n; j++) {
                    B[fila[i]][j] -= mult * B[fila[k]][j];
                }
                for (int j = 0; j < n; j++) {
                    invF[fila[i]][j] -= mult * invF[fila[k]][j];

                }
            }
            for (int i = 0; i < k; i++) {
                double mult = B[fila[i]][k] / B[fila[k]][k];
                B[fila[i]][k] = 0;
                for (int j = k + 1; j < n; j++) {
                    B[fila[i]][j] -= mult * B[fila[k]][j];
                }
                for (int j = 0; j < n; j++) {
                    invF[fila[i]][j] -= mult * invF[fila[k]][j];

                }
            }
        }
        // reordenamos las filas y hacemos que B sea la identidad 
        double[][] inv = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(
                    Matrices.producto(1. / B[fila[i]][i], invF[fila[i]]), 0, inv[i], 0, n);

        }

        return inv;
    }

    /**
     * Devuelve el determinante calculado con la triangulación de Gauss
     *
     * @param A
     * @param b
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double determinanteGaussP(double[][] A) throws ErrorMatrices {
        if (!ifCuadrada(A)) {
            System.out.println("Error: matriz de coeficientes no cuadrada");
            throw new ErrorMatrices();
        }
        int n = A.length;

        int[] fila = new int[n];
        double[][] B = copia(A);
        double det = 1;
        // Flujo
        for (int i = 0; i < n; i++) {
            fila[i] = i;
        }
        //Eliminación de Gauss o triangulación
        for (int k = 0; k < n; k++) { // se puede poner n-1
            //busqueda pivote
            int p = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(B[i][k]) > Math.abs(B[p][k])) {
                    p = i;
                }
            }
            //itercambio de filas
            if (p != k) {
                int m = fila[k];
                fila[k] = fila[p];
                fila[p] = m;
                det = det * (-1);
            }
            det = det * B[fila[k]][k];
            //eliminación
            if (Math.abs(B[fila[k]][k]) < precision) {
                System.out.println("Atención: A es singular");
                return 0;
            }
            for (int i = k + 1; i < n; i++) {
                double mult = B[fila[i]][k] / B[fila[k]][k];
                B[fila[i]][k] = 0;
                for (int j = k + 1; j < n; j++) {
                    B[fila[i]][j] -= mult * B[fila[k]][j];
                }
            }
        }
        return det;
    }

    /**
     * Excepcion ErrorMatrices para ser devuelta por los metodos de la clase
     */
    public static class ErrorMatrices extends Exception {

        public ErrorMatrices() {
            super("Error: Matrices ");
        }
    }

    public interface MatrizAbstracta {

        public int dim();

        public double[] producto(double[] x) throws Matrices.ErrorMatrices;

        public double[][] resta(double[][] x);
    }

    public static class Matriz implements MatrizAbstracta {

        int n = 1;
        double[][] A;

        public Matriz(double[][] A) {
            this.n = A.length;
            this.A = Matrices.copia(A);
        }

        public int dim() {
            return n;
        }
        
        public double[][] resta(double[][] x)
        {
            return null;
        }
        
        public double[] producto(double[] x) throws
                Matrices.ErrorMatrices {
            return Matrices.producto(A, x);
        }
    }

    public interface MatrizEspecial extends MatrizAbstracta {

        public MatrizEspecial traspuesta() throws ErrorMatrices;

        public double[] solve(double[] b) throws Matrices.ErrorMatrices;
    }

    public static class Diagonal implements MatrizEspecial {

        int n = 1;
        double precision = 0.5E-15;
        double[] D = new double[1];

        public Diagonal(double[] D) {
            this.n = D.length;
            this.D = Matrices.copia(D);
        }

        public int dim() {
            return n;
        }
        public double[][] resta(double[][] x)
        {
            return null;
        }
        
        public double[] producto(double[] x) throws Matrices.ErrorMatrices {
            if (x.length != n) {
                System.out.println("Error: dimensión");
                throw new Matrices.ErrorMatrices();
            }
            double[] prod = new double[n];
            for (int i = 0; i < prod.length; i++) {
                prod[i] = D[i] * x[i];

            }
            return prod;
        }

        public MatrizEspecial traspuesta() {
            return this;
        }

        public double[] solve(double[] b) throws Matrices.ErrorMatrices {
            if (b.length != n) {
                System.out.println("Error dimensiones incompatibles");
                throw new Matrices.ErrorMatrices();
            }
            double[] x = new double[n];
            for (int i = 0; i < x.length; i++) {
                if (Math.abs(D[i]) < precision) {
                    System.out.println("Error Diagonal con ceros");
                    throw new Matrices.ErrorMatrices();
                }
                x[i] = b[i] / D[i];
            }
            return x;
        }
    }

    public static class TriangularL implements MatrizEspecial {

        double[][] L = new double[1][1];

        public TriangularL(double[][] L) {
            this.L = copia(L);
        }

        public int dim() {
            return this.L.length;
        }
        public double[][] resta(double[][] x)
        {
            return null;
        }

        public double[] producto(double[] x) throws ErrorMatrices {
            return Matrices.producto(this.L, x);
        }

        public double[] solve(double[] b) throws ErrorMatrices {
            return solveDescendente(this.L, b);
        }

        public TriangularU traspuesta() throws ErrorMatrices {
            return new TriangularU(Matrices.traspuesta(this.L));
        }

    }

    public static class TriangularU implements MatrizEspecial {

        double[][] U = new double[1][1];

        public TriangularU(double[][] U) {
            this.U = copia(U);
        }

        public int dim() {
            return this.U.length;
        }
        public double[][] resta(double[][] x)
        {
            return null;
        }

        public double[] producto(double[] x) throws ErrorMatrices {
            return Matrices.producto(this.U, x);
        }

        public double[] solve(double[] b) throws ErrorMatrices {
            return solveDescendente(U, b); //OJO escribir el metodo Ascendente
        }

        public TriangularL traspuesta() throws ErrorMatrices {
            return new TriangularL(Matrices.traspuesta(this.U));
        }

    }

    public static boolean ifSimetrica(double[][] A) {
        if (!ifCuadrada(A)) {
            System.out.println("Atencion: matriz no cuadrada");
            return false;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (Math.abs(A[i][j] - A[j][i]) > 10 * precision) {
                    System.out.println("Atención: matriz no simétrica");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Dada una matriz rectangular mxn A, devuelve la matriz R de la
     * factorización de Householder A=QR
     *
     * @param A
     * @return R
     *
     *
     */
    public static double[][] RHouseholder(double[][] A) throws ErrorMatrices {
        if (!ifMatriz(A)) {
            System.out.println("A no es matriz");
            throw new ErrorMatrices();
        }
        int m = A.length; // numero de filas de A
        int n = A[0].length; // numero de columnas de A
        int p = Math.min(m, n); //numero de etapas para triangular
        double[] v = new double[m];

        double[][] R = copia(A);

        for (int k = 0; k < p; k++) {
            if (k == m - 1) {  // se para el bucle si no quedan filas debajo de la diagonal
                continue;
            }
            // r_k =(R[k][k],....,R[m-1][k])^t vector para el teorema
            double norma = R[k + 1][k] * R[k + 1][k];
            for (int i = k + 2; i < m; i++) {
                norma += R[i][k] * R[i][k];
            }
            if (Math.sqrt(norma) < 100 * precision) {
                continue; //ya es cero bajo la diagonal
            }
            norma += R[k][k] * R[k][k];
            norma = Math.sqrt(norma);
//             double signo=1;
//             if(Math.abs(R[k][k])>0){
//                 signo= R[k][k]/Math.abs(R[k][k]);
//             }
            double signo = Math.signum(R[k][k]);
            //v para Householder
            v[k] = R[k][k] + norma * signo;
            for (int i = k + 1; i < m; i++) {
                v[i] = R[i][k];
            }
            double norma2v = 2 * norma * norma + 2 * norma * signo * R[k][k];
            //accion H(v) sobre R[][k]
            R[k][k] = -norma * signo;
            for (int i = k + 1; i < m; i++) {
                R[i][k] = 0;
            }
            //accion de H(v) sobre R[][j] j=k+1,...n-1
            for (int j = k + 1; j < n; j++) {
                double aux = v[k] * R[k][j];
                for (int i = k + 1; i < m; i++) {
                    aux += v[i] * R[i][j];
                } //aux = prod escalar v por R[][j]
                aux = 2 * aux / norma2v;
                for (int i = k; i < m; i++) {
                    R[i][j] -= aux * v[i];
                }
            }
        }
        if (m > p) {
            double[][] R1 = new double[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(R[i], 0, R1[i], 0, n);
            }
            return R1;
        }

        return R;

    }

    /**
     * dada una matriz rectangular mxn A, devuelve la de la factorización de
     * Householder A=QR
     *
     * @param A
     * @return QR[2][][] QR[0]=Q y QR[1]=R
     *
     *
     */
    public static double[][][] factorQR(double[][] A) throws ErrorMatrices {
        if (!ifMatriz(A)) {
            System.out.println("A no es matriz");
            throw new ErrorMatrices();
        }
        int m = A.length; // numero de filas de A
        int n = A[0].length; // numero de columnas de A
        int p = Math.min(m, n); //numero de etapas para triangular
        double[] v = new double[m];

        double[][] R = copia(A);

        double[][] Qt = Id(m);

        for (int k = 0; k < p; k++) {
            if (k == m - 1) {  // se para el bucle si no quedan filas debajo de la diagonal
                continue;
            }
            // r_k =(R[k][k],....,R[m-1][k])^t vector para el teorema
            double norma = R[k + 1][k] * R[k + 1][k];
            for (int i = k + 2; i < m; i++) {
                norma += R[i][k] * R[i][k];
            }
            if (Math.sqrt(norma) < 100 * precision) {
                continue; //ya es cero bajo la diagonal
            }
            norma += R[k][k] * R[k][k];
            norma = Math.sqrt(norma);
//             double signo=1;
//             if(Math.abs(R[k][k])>0){
//                 signo= R[k][k]/Math.abs(R[k][k]);
//             }
            double signo = Math.signum(R[k][k]);
            //v para Householder
            v[k] = R[k][k] + norma * signo;
            for (int i = k + 1; i < m; i++) {
                v[i] = R[i][k];
            }
            double norma2v = 2 * norma * norma + 2 * norma * signo * R[k][k];
            //accion H(v) sobre R[][k]
            R[k][k] = -norma * signo;
            for (int i = k + 1; i < m; i++) {
                R[i][k] = 0;
            }
            //accion de H(v) sobre R[][j] j=k+1,...n-1
            for (int j = k + 1; j < n; j++) {
                double aux = v[k] * R[k][j];
                for (int i = k + 1; i < m; i++) {
                    aux += v[i] * R[i][j];
                } //aux = prod escalar v por R[][j]
                aux = 2 * aux / norma2v;
                for (int i = k; i < m; i++) {
                    R[i][j] -= aux * v[i];
                }
            }
            //accion de H(v) sobre Qt[][j] j=0,...m-1
            for (int j = 0; j < m; j++) {
                double aux = v[k] * Qt[k][j];
                for (int i = k + 1; i < m; i++) {
                    aux += v[i] * Qt[i][j];
                } //aux = prod escalar v por Qt[][j]
                aux = 2 * aux / norma2v;
                for (int i = k; i < m; i++) {
                    Qt[i][j] -= aux * v[i];
                }
            }
        }

        double[][][] QR = new double[2][][];
        QR[0] = traspuesta(Qt);
        QR[1] = R;
        return QR;

    }

    /**
     * Resuelve el sistema Ax=b usando la factorización de Householder A=QR y
     * resolviendo Rx = Qt b por el metodo ascendente
     *
     * @param A
     * @return R
     *
     *
     */
    public static double[] solveFactorQR(double[][] A, double[] b) throws ErrorMatrices {
        if (!ifCuadrada(A)) {
            System.out.println("A no es cuadrada");
            throw new ErrorMatrices();
        }
        int n = A.length; // dimensión de A
        if (b.length != n) {
            System.out.println("Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] v = new double[n];

        double[][] R = copia(A);

        double[] w = copia(b);

        for (int k = 0; k < n - 1; k++) {
            // r_k =(R[k][k],....,R[m-1][k])^t vector para el teorema
            double norma = R[k + 1][k] * R[k + 1][k];
            for (int i = k + 2; i < n; i++) {
                norma += R[i][k] * R[i][k];
            }
            if (Math.sqrt(norma) < 100 * precision) {
                continue; //ya es cero bajo la diagonal
            }
            norma += R[k][k] * R[k][k];
            norma = Math.sqrt(norma);
//             double signo=1;
//             if(Math.abs(R[k][k])>0){
//                 signo= R[k][k]/Math.abs(R[k][k]);
//             }
            double signo = Math.signum(R[k][k]);
            //v para Householder
            v[k] = R[k][k] + norma * signo;
            for (int i = k + 1; i < n; i++) {
                v[i] = R[i][k];
            }
            double norma2v = 2 * norma * norma + 2 * norma * signo * R[k][k];
            //accion H(v) sobre R[][k]
            R[k][k] = -norma * signo;
            for (int i = k + 1; i < n; i++) {
                R[i][k] = 0;
            }
            //accion de H(v) sobre R[][j] j=k+1,...n-1
            for (int j = k + 1; j < n; j++) {
                double aux = v[k] * R[k][j];
                for (int i = k + 1; i < n; i++) {
                    aux += v[i] * R[i][j];
                } //aux = prod escalar v por R[][j]
                aux = 2 * aux / norma2v;
                for (int i = k; i < n; i++) {
                    R[i][j] -= aux * v[i];
                }
            }
            // acción de H(v) sobre w;
            double aux = v[k] * w[k];
            for (int i = k + 1; i < n; i++) {
                aux += v[i] * w[i];
            } //aux = prod escalar v por w
            aux = 2 * aux / norma2v;
            for (int i = k; i < n; i++) {
                w[i] -= aux * v[i];

            }

        }
        // resolver por el metodo ascendente R x = w

        return solveAscendente(R, w);

    }

    /**
     * Metodo para construir de forma aleatoria matrices A de dimensión fila x
     * colu y de rango rang.
     *
     * @param fila
     * @param colu
     * @param rang
     * @return la matriz A
     */
    public static double[][] MatrizRango(int fila, int colu, int rang)
            throws ErrorMatrices {
        if (rang > Math.min(fila, colu)) {
            System.out.println("rango mayor que el minimo de fila y colu");
            throw new ErrorMatrices();
        }
        double[][] R = new double[fila][colu];
        double[][] Q = new double[fila][fila];
        //R
        for (int i = 0; i < rang; i++) {
            while (Math.abs(R[i][i]) < 100 * precision) {
                R[i][i] = 100 * (2 * Math.random() - 1);
            }
            for (int j = i + 1; j < colu; j++) {
                R[i][j] = 100 * (2 * Math.random() - 1);

            }
        }
        //System.out.println("R = \n "+ toString(R));
        //Q
        for (int i = 0; i < fila; i++) {
            double norma = 0;
            while (norma < 100 * precision) {
                Q[i] = vectorAleatorio(fila);
                for (int j = 0; j < i; j++) {
                 Q[i] = resta(Q[i], producto(producto(Q[i], Q[j]), Q[j]));
                }
                norma=norma(Q[i]);
            }
            Q[i]=producto(1/norma,Q[i]);
        }
        return producto(Q, R);
    }

    public static double[] vectorAleatorio(int n) {
        double[] va = new double[n];
        for (int i = 0; i < va.length; i++) {
            va[i] = 100 * (2 * Math.random() - 1);
        }
        return va;
    }
    
    /**
     * Aproximación por mínimos cuadrados a soluciones de sistemas sobre-determinados
     * Ax=b, donde A es m x n de rango máximo y B es de dimensión m
     * @param A
     * @param b
     * @return  x que minimiza ||Ax -b||_2 o error
     */
    
    public static double[] solveMinimosCuadradosQR(double[][] A,double[] b) 
            throws ErrorMatrices{
        if(!ifMatriz(A)){
            System.out.println("A no es rectangular ");
            throw new ErrorMatrices();
        }
        int m=A.length;
        int n=A[0].length;
        if(n>m){
            System.out.println("sistema infra-determinado: más incognitas "
                    + "que ecuaciones");
            throw new ErrorMatrices();
        }
        if(b.length !=m){
            System.out.println("Dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] v=producto(traspuesta(A), b);
        //QR 
        double[][] R=RHouseholder(A);
        if(Math.abs(R[n-1][n-1])<1000*precision){
            System.out.println("A no tiene rango máximo");
            throw new ErrorMatrices();
        }
        // resolvemos Rt R x = v;  (1) Rt y =v (descendente)
        // (2) resolvemos R x = y (ascendente)
        double[] y=solveDescendente(traspuesta(R), v);
        return solveAscendente(R, y);
        
    }
    /**
     * Aproximación por mínimos cuadrados a soluciones de sistemas sobre-determinados
     * Ax=b, donde A es m x n de rango máximo y B es de dimensión m
     * @param A
     * @param b
     * @return  x que minimiza ||Ax -b||_2 o error
     */
    
    public static double[] solveMinimosCuadradosCh(double[][] A,double[] b) 
            throws ErrorMatrices{
        if(!ifMatriz(A)){
            System.out.println("A no es rectangular ");
            throw new ErrorMatrices();
        }
        int m=A.length;
        int n=A[0].length;
        if(n>m){
            System.out.println("sistema infra-determinado: más incognitas "
                    + "que ecuaciones");
            throw new ErrorMatrices();
        }
        if(b.length !=m){
            System.out.println("Dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] v=producto(traspuesta(A), b);
        //QR 
        double[][] AtA=producto(traspuesta(A), A);
        
        double[][] L=factorLdeCholeski(AtA);
        
        // resolvemos Rt R x = v;  (1) Rt y =v (descendente)
        // (2) resolvemos R x = y (ascendente)
        double[] y=solveDescendente(L, v);
        return solveAscendente(traspuesta(L), y);
        
    }
    
    /**
     * Dada una matriz A de dimensión par n = 2m devuelva una lista múltiple double[2][2][m][m] que contiene
        cada uno de los menores que resultan al descomponer A en cuatro matrices de dimensión m.
     * @param A matriz que se desea descomponer
     * @return lista con los menores de A de dimensión m.
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[][][][] division4(double[][] A)throws ErrorMatrices
    {
        int n;
        n = A[0].length;
        double[][][][] QR = new double[2][2][n/2][n/2];
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                QR[0][0][i][j]=A[i][j];
                QR[1][0][i][j]=A[i+n/2][j];
                QR[0][1][i][j]=A[i][j+n/2];
                QR[1][1][i][j]=A[i+n/2][j+n/2];
            }
        }
        return QR;
        
    }
    
    /**
     * Dada una lista múltiple double[2][2][m][m] con cuatro matrices de dimensión m, devuelve la matriz A de dimensión
     * n = 2m..
     * @param Ad lista múltiple de la que se desea obtener la matriz formada por los menores.
     * @return Matriz resultado de juntar los menores de dimensión m.
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[][] reune4(double[][][][] Ad)throws ErrorMatrices
    {
        int n;
        n = Ad[0][0].length;
        double[][] A=new double[2*n][2*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j]=Ad[0][0][i][j];
                A[i][j+n]=Ad[0][1][i][j];
                A[i+n][j]=Ad[1][0][i][j];
                A[i+n][j+n]=Ad[1][1][i][j];
            }
        }
        return A;
    }
    
    /**
     * Dadas dos listas múltiples double[2][2][m][m] A y B devuelve una lista múltiple double[2][2][m][m]
        C construida con el algoritmo de Strassen.
     * @param A 
     * @param B
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[][][][] unpasoStressen(double[][][][] A,double[][][][] B) throws ErrorMatrices
    {
        int n;
        n = A[0][0].length;
        double[][][][] C = new double[2][2][n][n];
        double[][][] M=new double[7][n/2][n/2];
        
        M[0]=productoStrassen(suma(A[0][0],A[1][1]), suma(B[0][0],B[1][1]));
        M[1]=productoStrassen(suma(A[1][0], A[1][1]), B[0][0]);
        M[2]=productoStrassen(A[0][0], resta(B[0][1], B[1][1]));
        M[3]=productoStrassen(A[1][1], resta(B[1][0], B[0][0]));
        M[4]=productoStrassen(suma(A[0][0],A[0][1]), B[1][1]);
        M[5]=productoStrassen(resta(A[1][0],A[0][0]), suma(B[0][0],B[0][1]));
        M[6]=productoStrassen(resta(A[0][1],A[1][1]), suma(B[1][0],B[1][1]));
        
        C[0][0]=resta(suma(suma(M[0],M[3]),M[6]),M[4]);
        C[0][1]=suma(M[2],M[4]);
        C[1][0]=suma(M[1],M[3]);
        C[1][1]=suma(suma(resta(M[0],M[1]),M[2]),M[5]);
        
        return C;
    }

    /**
     * devuelve el producto de dos matrices de dimensión N = 2 utilizando el producto normal si N<60 o
     *  haciendo la división en 4 matrices de A y de B para usarlas como argumentos del método unpasoStrassen.
     * @param A
     * @param B
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[][] productoStrassen(double[][] A,double[][] B) throws ErrorMatrices
    {
        int n;
        n = A[0].length;
        double[][][][] auxC=new double[2][2][n][n];
        double[][][][] auxA=division4(A);
        double[][][][] auxB=division4(B);
        if(n<60)
        {
            auxC[0][0]=suma(producto(auxA[0][0], auxB[0][0]), producto(auxA[0][1], auxB[1][0]));
            auxC[1][0]=suma(producto(auxA[1][0], auxB[0][0]), producto(auxA[1][1], auxB[1][0]));
            auxC[0][1]=suma(producto(auxA[0][0], auxB[0][1]), producto(auxA[0][1], auxB[1][1]));
            auxC[1][1]=suma(producto(auxA[1][0], auxB[0][1]), producto(auxA[1][1], auxB[1][1]));
            return reune4(auxC);
        }
        else
        {
            auxC=unpasoStressen(auxA, auxB);
            return reune4(auxC);
        }
    }
    
    /**
     * Método para calcular la solución de un sistema Ax=b mediante descenso en la dirección del gradiente.
     * @param A
     * @param b
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[] solveGradienteDescenso(double[][] A, double[] b) throws ErrorMatrices
    {
        //Comprobamos que sea simétrica.
        if(!ifSimetrica(A))
        {
            System.err.println("La matriz introducida en el método solveGradienteDescenso no es simétrica.");
            throw new ErrorMatrices();      
        }
        
        //Comprobamos que sea definida positiva.
        double[][][] S=FactorLDR(A);
        double[][] T=S[1];
        int n = A[0].length;
        for (int i = 0; i < n; i++) {
            if(T[i][i]<0)
            {
                System.err.println("La matriz introducida en el método solveGradienteDescenso no es definida positiva.");
                throw new ErrorMatrices(); 
            }
        }
        
        double tol=1E-8;
        int MAXITER=500;
        int iteracion=0;
        double[] x=new double[n];
        
        //Realizamos la primera aproximación:
        double[] r=resta(producto(A, x),b);
        double aux1=producto(r, r);
        double aux2=producto(producto(A, r), r);
        double t=-aux1/aux2;
        x=suma(x, producto(t, r));
        //Mientras que no alcancemos el numero máximo de iteraciones o lleguemos a una precisión aceptable seguimos iterando.
        while ((iteracion<MAXITER) && (norma(r)>tol)) 
        {            
            r=resta(producto(A, x),b);
            aux1=producto(r, r);
            aux2=producto(producto(A, r), r);
            t=-aux1/aux2;
            x=suma(x, producto(t, r));
            iteracion++;
        }
       
        if(iteracion<MAXITER)
        {
            System.out.println("GradienteDescenso converge en "+iteracion+" iteraciones.");
            return x;
        }
        else
        {
            System.err.println("Superado el número máximo de iteraciones en el método solveGradienteDescenso");
            throw new ErrorMatrices();
        }
    }
    
    public static double[] iterJacobi(Tridiagonal T, double[] b, double tol, int nmaxit, 
            double[] x0,boolean print)throws ErrorMatrices{
         if(((T.dPrinc.length-T.dInf.length)!=1) || ((T.dPrinc.length-T.dSup.length)!=1)){//Cambiamos la condición de que sea cuadrada.
            System.out.println(" Error: Matriz no cuadrada");
            throw new ErrorMatrices();
        }
        int n=T.dim();
        if(b.length != n || x0.length != n ){
            System.out.println(" Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] xa=copia(x0);
        //double[] xb=copia(x0);
        double erroradmisible= tol * tol * norma(b) * norma(b);
        
        for (int k = 0; k < nmaxit; k++) {
            double norma=0;
            double[] residual=new double[n];
            for (int i = 0; i < n; i++) {
                residual[i]=-b[i];
                if(i==0){   //Las optimizaciones las podemos hacer aqui ya que en cada fila solo tres casillas son distintas de 0.
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dSup[i]*xa[i+1];
                }
                else if(i==n-1){
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dInf[i-1]*xa[i-1];
                }
                else
                {
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dSup[i]*xa[i+1];
                    residual[i]+=T.dInf[i-1]*xa[i-1];
                }
                norma += residual[i]*residual[i];
            
            
            }
            if(norma < erroradmisible){
                System.out.println("Jacobi converge en "+ k + " iteraciones");
                System.out.println("|| A x - b || = "+ Math.sqrt(norma));
                return xa;
            }
            if(print){
            System.out.println("k= " + k + "\t  || A x_k - b || = "+ Math.sqrt(norma));
            }
            for (int i = 0; i < n; i++) {
                xa[i] -= 1. / T.dPrinc[i] * residual[i];
            }
        }
        System.out.println("Error Jacobi: No hay convergencia en "+ nmaxit +
                " iteraciones");
        throw new ErrorMatrices();
    }
    
    /**
     * Metodo de Jacobi para aproximar la solución de la ecuación Ax = b 
     * @param A
     * @param b
     * @param tol    tolerancia para condición de parada ( ||Ax-b|| < tolerancia )
     * @param nmaxit    numero máximo de iteraciones 
     * @param x0     aproximación inicial
     * @param print  imprime las distintas iteraciones
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices por inconsitencia en los datos, porque haya ceros
     * en la diagonal de A o porque no haya convergencia en nmax iteraciones
     */
    public static double[] iterJacobi(double[][] A, double[] b, double tol, int nmaxit, 
            double[] x0,boolean print)throws ErrorMatrices{
        if(!ifCuadrada(A)){
            System.out.println(" Error: Matriz no cuadrada");
            throw new ErrorMatrices();
        }
        int n=A.length;
        if(b.length != n || x0.length != n ){
            System.out.println(" Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] xa=copia(x0);
        //double[] xb=copia(x0);
        double erroradmisible= tol * tol * norma(b) * norma(b);
        
        for (int k = 0; k < nmaxit; k++) {
            double norma=0;
            double[] residual=new double[n];
            for (int i = 0; i < n; i++) {
                residual[i]=-b[i];
                for (int j = 0; j < n; j++) {
                    residual[i] += A[i][j]*xa[j];   
                }
                norma += residual[i]*residual[i];
            }
            if(norma < erroradmisible){
                System.out.println("Jacobi converge en "+ k + " iteraciones");
                System.out.println("|| A x - b || = "+ Math.sqrt(norma));
                return xa;
            }
            if(print){
            System.out.println("k= " + k + "\t  || A x_k - b || = "+ Math.sqrt(norma));
            }
            for (int i = 0; i < n; i++) {
                xa[i] -= 1. / A[i][i] * residual[i];
            }
        }
        System.out.println("Error Jacobi: No hay convergencia en "+ nmaxit +
                " iteraciones");
        throw new ErrorMatrices();
        
    }
    
    public static double[] iterGaussSeidel(Tridiagonal T, double[] b, double tol, int nmaxit, 
            double[] x0,boolean print)throws ErrorMatrices{
        if(((T.dPrinc.length-T.dInf.length)!=1) || ((T.dPrinc.length-T.dSup.length)!=1)){
            System.out.println(" Error: Matriz no cuadrada");
            throw new ErrorMatrices();
        }
        int n=T.dim();
        if(b.length != n || x0.length != n ){
            System.out.println(" Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] xa=copia(x0);
        //double[] xb=copia(x0);
        double erroradmisible= tol * tol * norma(b) * norma(b);
        
        for (int k = 0; k < nmaxit; k++) {
            double norma=0;
            double[] residual=new double[n];
            for (int i = 0; i < n; i++) {
                residual[i]=-b[i];
                
                if(i==0){   //Las optimizaciones las podemos hacer aqui ya que en cada fila solo tres casillas son distintas de 0.
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dSup[i]*xa[i+1];
                }
                else if(i==n-1){
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dInf[i-1]*xa[i-1];
                }
                else
                {
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dSup[i]*xa[i+1];
                    residual[i]+=T.dInf[i-1]*xa[i-1];
                }
                
                norma += residual[i]*residual[i];
                xa[i] -= 1. / T.dPrinc[i] * residual[i];
            }
            if(norma < erroradmisible){
                System.out.println("Gauss-Seidel converge en "+ k + " iteraciones");
                System.out.println("|| A x - b || = "+ Math.sqrt(norma));
                return xa;
            }
            if(print){
            System.out.println("k= " + k + "\t  || A x_k - b || = "+ Math.sqrt(norma));
            }
            
        }
        System.out.println("Error Gauss-Seidel: No hay convergencia en "+ nmaxit +
                " iteraciones");
        throw new ErrorMatrices();
        
    }
    
        /**
     * Metodo de Gauss-Seidel para aproximar la solución de la ecuación Ax = b 
     * @param A
     * @param b
     * @param tol    tolerancia para condición de parada ( ||Ax-b|| < tolerancia )
     * @param nmaxit    numero máximo de iteraciones 
     * @param x0     aproximación inicial
     * @param print  imprime las distintas iteraciones
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices por inconsitencia en los datos, porque haya ceros
     * en la diagonal de A o porque no haya convergencia en nmax iteraciones
     */
    public static double[] iterGaussSeidel(double[][] A, double[] b, double tol, int nmaxit, 
            double[] x0,boolean print)throws ErrorMatrices{
        if(!ifCuadrada(A)){
            System.out.println(" Error: Matriz no cuadrada");
            throw new ErrorMatrices();
        }
        int n=A.length;
        if(b.length != n || x0.length != n ){
            System.out.println(" Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] xa=copia(x0);
        //double[] xb=copia(x0);
        double erroradmisible= tol * tol * norma(b) * norma(b);
        
        for (int k = 0; k < nmaxit; k++) {
            double norma=0;
            double[] residual=new double[n];
            for (int i = 0; i < n; i++) {
                residual[i]=-b[i];
                for (int j = 0; j < n; j++) {
                    residual[i] += A[i][j]*xa[j];   
                }
                norma += residual[i]*residual[i];
                xa[i] -= 1. / A[i][i] * residual[i];
            }
            if(norma < erroradmisible){
                System.out.println("Gauss-Seidel converge en "+ k + " iteraciones");
                System.out.println("|| A x - b || = "+ Math.sqrt(norma));
                return xa;
            }
            if(print){
            System.out.println("k= " + k + "\t  || A x_k - b || = "+ Math.sqrt(norma));
            }
            
        }
        System.out.println("Error Gauss-Seidel: No hay convergencia en "+ nmaxit +
                " iteraciones");
        throw new ErrorMatrices();
        
    }
    
    public static double[] iterrelajacion(Tridiagonal T, double[] b, 
            double w,double tol, int nmaxit, 
            double[] x0,boolean print)throws ErrorMatrices{
        if(((T.dPrinc.length-T.dInf.length)!=1) || ((T.dPrinc.length-T.dSup.length)!=1)){
            System.out.println(" Error: Matriz no cuadrada");
            throw new ErrorMatrices();
        }
        int n=T.dim();
        if(b.length != n || x0.length != n ){
            System.out.println(" Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] xa=copia(x0);
        //double[] xb=copia(x0);
        double erroradmisible= tol * tol * norma(b) * norma(b);
        
        for (int k = 0; k < nmaxit; k++) {
            double norma=0;
            double[] residual=new double[n];
            for (int i = 0; i < n; i++) {
                residual[i]=-b[i];
                if(i==0){   //Las optimizaciones las podemos hacer aqui ya que en cada fila solo tres casillas son distintas de 0.
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dSup[i]*xa[i+1];
                }
                else if(i==n-1){
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dInf[i-1]*xa[i-1];
                }
                else
                {
                    residual[i]+=T.dPrinc[i]*xa[i];
                    residual[i]+=T.dSup[i]*xa[i+1];
                    residual[i]+=T.dInf[i-1]*xa[i-1];
                }
                norma += residual[i]*residual[i];
                xa[i] -= w / T.dPrinc[i] * residual[i];
            }
            if(norma < erroradmisible){
                System.out.println("relajación converge en "+ k + 
                        " iteraciones, para el peso w = "+ w);
                System.out.println("|| A x - b || = "+ Math.sqrt(norma));
                return xa;
            }
            if(print){
            System.out.println("k= " + k + "\t  || A x_k - b || = "+Math.sqrt(norma));
            }
            
        }
        System.out.println("Error relajación: No hay convergencia en "+ nmaxit +
                " iteraciones con el peso w = "+ w);
        throw new ErrorMatrices();
        
    }
    
        /**
     * Metodo de relajacion para aproximar la solución de la ecuación Ax = b 
     * @param A
     * @param b
     * @param w peso de relajación
     * @param tol    tolerancia para condición de parada ( ||Ax-b|| < tolerancia )
     * @param nmaxit    numero máximo de iteraciones 
     * @param x0     aproximación inicial
     * @param print  imprime las distintas iteraciones
     * @return
     * @throws auxiliar.Matrices.ErrorMatrices por inconsitencia en los datos, porque haya ceros
     * en la diagonal de A o porque no haya convergencia en nmax iteraciones
     */
    public static double[] iterrelajacion(double[][] A, double[] b, 
            double w,double tol, int nmaxit, 
            double[] x0,boolean print)throws ErrorMatrices{
        if(!ifCuadrada(A)){
            System.out.println(" Error: Matriz no cuadrada");
            throw new ErrorMatrices();
        }
        int n=A.length;
        if(b.length != n || x0.length != n ){
            System.out.println(" Error: dimensiones incompatibles");
            throw new ErrorMatrices();
        }
        double[] xa=copia(x0);
        //double[] xb=copia(x0);
        double erroradmisible= tol * tol * norma(b) * norma(b);
        
        for (int k = 0; k < nmaxit; k++) {
            double norma=0;
            double[] residual=new double[n];
            for (int i = 0; i < n; i++) {
                residual[i]=-b[i];
                for (int j = 0; j < n; j++) {
                    residual[i] += A[i][j]*xa[j];   
                }
                norma += residual[i]*residual[i];
                xa[i] -= w / A[i][i] * residual[i];
            }
            if(norma < erroradmisible){
                System.out.println("relajación converge en "+ k + 
                        " iteraciones, para el peso w = "+ w);
                System.out.println("|| A x - b || = "+ Math.sqrt(norma));
                return xa;
            }
            if(print){
            System.out.println("k= " + k + "\t  || A x_k - b || = "+Math.sqrt(norma));
            }
            
        }
        System.out.println("Error relajación: No hay convergencia en "+ nmaxit +
                " iteraciones con el peso w = "+ w);
        throw new ErrorMatrices();
        
    }
    
//    public static double[] iterrelajacion(Tridiagonal A, double[] b, 
//            double w,double tol, int nmaxit, 
//            double[] x0,boolean print)throws ErrorMatrices{
//        
//        int n=A.dim();
//        double[][] p=new double[n][n];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if(i==j)
//                        p[i][j]=A.dPrinc[i];
//                    else if (i==j-1)
//                        p[i][j]=A.dSup[i];
//                    else if (i==j+1)
//                        p[i][j]=A.dInf[j];
//                }
//            }
//        return iterrelajacion(p, b, w, tol, nmaxit, x0, print);
//    }
    
    /**
     * Metodo del gradiente conjugado con precondicionamiento para resolver Ax=b considerando en su lugar C^{-1}AC^{-t}X=C{-1}b
     * con X=C^tx.
     * @param A es una matriz simetrica definida positiva.
     * @param C es una matrizEspecial.
     * @param b
     * @param x0
     * @param tol
     * @return x o excepcion por errores en la ejecucion.
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[] gradienteConjugadoPrecondicionado(MatrizAbstracta A, MatrizEspecial C, double[] b, double[] x0, double tol)throws ErrorMatrices
    {
        int n=A.dim();
        double errorAdmisible=tol*norma(b); //para error relativo
        //A
        double[] x=copia(x0);
        double[] r=resta(b, A.producto(x));
        if(norma(r)<errorAdmisible)
        {
            System.out.println("Gradiente conjugado, paso 0 ");
            System.out.println("||Ax-b|| = "+ norma(r));
            System.out.println("||Ax-b||/||b|| = "+ norma(r)/norma(b));
            return x;
        }
        
        double[] z=C.traspuesta().solve(C.solve(r));
        double[] v=copia(z);
        double gamma=producto(r, z);
        //B
        for (int i = 1; i <= n; i++) {
            double[] y=A.producto(v);
            double nAv=producto(y, v);
            double t=gamma/nAv;
            x=suma(x, producto(t, v));
            r=resta(r, producto(t, y));
            if(norma(r)<errorAdmisible)
            {
                System.out.println("Gradiente conjugado, paso "+i+" de un máximo de "+n);
                System.out.println("||Ax-b|| = "+ norma(r));
                System.out.println("||Ax-b||/||b|| = "+ norma(r)/norma(b));
                return x;
            }
            z=C.traspuesta().solve(C.solve(r));
            double beta=producto(r, z);
            double s=beta/gamma;
            gamma=beta;
            v=suma(z, producto(s, v));
        }
        System.err.println("WARNING: Posible inestabilidad en calculos del gradiente conjugado.");
        System.out.println("Gradiente conjugado, de un máximo de "+n);
        System.out.println("||Ax-b|| = "+ norma(r));
        System.out.println("||Ax-b||/||b|| = "+ norma(r)/norma(b));
        return x;
    }
    
    public static double[] gradienteConjugadoPrecondicionado(double[][] A, MatrizEspecial C, double[] b, double[] x0, double tol) throws ErrorMatrices
    {
        Matriz nA=new Matriz(A);
        return gradienteConjugadoPrecondicionado(nA, C, b, x0, tol);
    }
    
    public static double[] gradienteConjugado(MatrizAbstracta A,double[] b, double[] x0, double tol) throws ErrorMatrices
    {
        int n=A.dim();
        double[] I=new double[n];
        for(int i=0;i<n;i++) I[i]=1;
        return gradienteConjugadoPrecondicionado(A, new Diagonal(I), b, x0, tol);
    }

    public static class Tridiagonal implements MatrizAbstracta{
        double[] dPrinc=new double[2];
        double[] dInf=new double[1];
        double[] dSup=new double[1];
        int n=2;
        public Tridiagonal(double[] dP,double[] dI,double[] dS )
        {
            this.dPrinc=copia(dP);
            this.dInf=copia(dI);
            this.dSup=copia(dS);
            this.n=dPrinc.length;
//            double[][] p=new double[n][n];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if(i==j)
//                        p[i][j]=dPrinc[i];
//                    else if (i==j-1)
//                        p[i][j]=dSup[i];
//                    else if (i==j+1)
//                        p[i][j]=dInf[j];
//                                        
//                }
//            }
//            System.out.println("tridiagonal: " + Matrices.toString(p));
        }
        public int dim()
        {
            return this.n;
        }
        public double[] producto(double[] x)
        {
            double[] prod=new double[n];
            for (int i = 0; i < n; i++) {
                prod[i]=dPrinc[i]*x[i];
                if(i>0)
                    prod[i]+=dInf[i-1]*x[i-1];
                if(i<n-1)
                    prod[i]+=dSup[i]*x[i+1];
            }
            return prod;
        }
        public double[][] resta(double[][] x)
        {
            double[][] resta=new double[n][n];   
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < resta.length; j++) {
                    if(i==j)
                        resta[i][j]=dPrinc[i]-x[i][j];
                    else if (i==j+1)
                        resta[i][j]=dSup[j];
                    else if (i==j-1)
                        resta[i][j]=dInf[i];
                                       
                }
            }
            return resta;
        }
        
    }
    
     /**
     * Metodo de la potencia para aproximar el valor propio dominante y un
     * vector propio asociado.
     *
     * @param A matriz
     * @param tol tolerancia para controlar error
     * @param nmax numero maximo de iteraciones
     * @param v vector inicial
     * @param y direccion busqueda fija
     * @return un vector de dimension n + 1 donde las n primeras coordenadas son
     * las del vector propio y la última coordenada es el valor propio
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[] potencia(MatrizAbstracta A, double tol,
            int nmax, double[] x0, double[] y) throws ErrorMatrices {
        
        int n = A.dim();
        double[] solucion = new double[n + 1];
        double[] Yini = copia(x0); // Para guardar Y_k
        double[] Yfin;  // Para guardar (A Y_k) e (Y_{k+1})
        double rini = 0; // Para guardar r_k
        double rfin; // Para guardar r_{k+1}
        
        Yini = producto(1. / norma(Yini), Yini);  // Y_0= x0/||x0||
        double prod1=producto(y, Yini);
        for (int m = 1; m <= nmax; m++) {           
            if (Math.abs(prod1) < precision) {
                System.out.println("Error: el metodo no aplica y perp a x0");
                throw new ErrorMatrices();
            }
            Yfin = A.producto(Yini);  // Yfin= A Y_k
            if (norma(Yfin) < precision) {
                System.out.println("Error: el metodo no aplica: valor propio nulo");
                throw new ErrorMatrices();
            }
            double prod2=producto(Yfin, y);
            rfin = prod2 / prod1; //rfin=r_{k+1}  candidato a valor propio
            
            prod1= prod2 /norma(Yfin);
            
            Yfin = producto(1. / norma(Yfin), Yfin); // Yfin= Y_{k+1} candidato a vector propio
            double[] control = resta(producto(rfin, Yfin), A.producto(Yfin)); // ||A Y_{k+1} - rfin Y_{k+1}||
           // System.out.println("control "+rfin);
            if (Math.abs(rfin - rini) < tol && norma(control) < tol) { 
                //System.out.println("Método de la potencia converge en "+ m + " pasos ");
                //System.out.println("valor propio (potencia) en " + rfin);
                //System.out.println("vector propio en x="+ Matrices.toString(Yfin));
                solucion[n] = rfin;
                System.arraycopy(Yfin, 0, solucion, 0, n);
                return solucion;
            }
            rini = rfin;
            Yini = copia(Yfin);
           

        }
        System.out.println("Error: no hay convergencia en "
                + nmax + " iteraciones ");
        throw new ErrorMatrices();
    }

     /**
     * Metodo de la potencia para aproximar el valor propio dominante y un
     * vector propio asociado.
     *
     * @param A matriz
     * @param tol tolerancia para controlar error
     * @param nmax numero maximo de iteraciones
     * @param v vector inicial
     * @param y direccion busqueda fija
     * @return un vector de dimension n + 1 donde las n primeras coordenadas son
     * las del vector propio y la última coordenada es el valor propio
     * @throws auxiliar.Matrices.ErrorMatrices
     */
    public static double[] potencia(double[][] A, double tol,
            int nmax, double[] v, double[] y) throws ErrorMatrices {
        Matriz nA=new Matriz(A);
        return potencia(nA, tol, nmax, v, y);
    }


    /**
     * metodo de la potencia inversa con desplazamiento para aproximar
     * el valor propio de A más próximo a mu.
     * @param A
     * @param mu
     * @param tol
     * @param nmax
     * @param v
     * @param y
     * @return un vector donde en las primeras coordenadas están las del vector
     * propio y en la última el valor propio más próximo a mu
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[] potenciaInversaDesplazada(MatrizAbstracta A, double mu, double tol,
            int nmax, double[] v, double[] y) throws ErrorMatrices {
        int n=A.dim();
        double[][] nuId=producto(mu, Id(n));
        double[][] inversaDesplazada=inversaGauss(A.resta(nuId));
        double[] vecYvalPropio=potencia(inversaDesplazada, tol, nmax, v, y);
        vecYvalPropio[n]=mu+1./vecYvalPropio[n];
        //System.out.println("valor propio (mu) = "+vecYvalPropio[n]);
        return vecYvalPropio;
    }
    


    /**
     * metodo de la potencia inversa con desplazamiento para aproximar
     * el valor propio de A más próximo a mu.
     * @param A
     * @param mu
     * @param tol
     * @param nmax
     * @param v
     * @param y
     * @return un vector donde en las primeras coordenadas están las del vector
     * propio y en la última el valor propio más próximo a mu
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[] potenciaInversaDesplazada(double[][] A, double mu, double tol,
            int nmax, double[] v, double[] y) throws ErrorMatrices {
        int n=A.length;
        double[][] nuId=producto(mu, Id(n));
        double[][] inversaDesplazada=inversaGauss(resta(A,nuId));
        double[] vecYvalPropio=potencia(inversaDesplazada, tol, nmax, v, y);
        vecYvalPropio[n]=mu+1./vecYvalPropio[n];
        System.out.println("valor propio (mu) = "+vecYvalPropio[n]);
        return vecYvalPropio;
    }



    /**
     * Método de Jacobi para aproximar simultáneamente los valores y vectores 
     * propios de una matriz simétrica A
     * @param A es la matriz simétrica
     * @param tol
     * @param nmaxit
     * @return una matriz donde en cada fila tendremos un vector propio en las 
     * primeras coordenadas y el valor propio asociado en la última coordenada
     * @throws auxiliar.Matrices.ErrorMatrices  si A no es simétrica o se sobrepasa 
     * el número máximo de etapas.
     */
    public static double[][] jacobiPropios(double[][] A, double tol, int nmaxit)
            throws ErrorMatrices{
        if(!ifSimetrica(A)){
            System.out.println("JacobiPropios error : Matriz no simétrica");
            throw new ErrorMatrices();
        }
        int n=A.length;
        double[][] O=Id(n);
        double[][] B=copia(A);
        for (int k = 0; k < nmaxit; k++) {
           int p=0, q=0;
           double aux=0;
            for (int i = 0; i <n; i++) {
                for (int j = i+1; j < B.length; j++) {
                    if(Math.abs(B[i][j])> aux){
                        p=i;
                        q=j;
                        aux=Math.abs(B[i][j]);
                    }
                }
            }
            if(n*(n-1)*aux < 100*precision){
                for (int i = 0; i <n; i++) {
                    for (int j = i+1; j < n; j++) {
                        if(Math.abs(B[i][i]-B[j][j])<100*precision){
                            System.out.println("JacobiPropios "
                                    + "ADVERTENCIA : Posible error en base de"
                                    + "vectores propios");
                        }
                    }
                }
                double[][] Sol=new double[n][n+1];
                double[][] Ot=traspuesta(O);
                for (int i = 0; i < n; i++) {
                   Sol[i][n]=B[i][i];
                   System.arraycopy(Ot[i], 0, Sol[i], 0, n);
                    //System.out.println("|| A v - lambda v || "+ normaresidualPropios(A, Sol[i]));
                }
                //System.out.println("JacobiPropios: converge en "
                //        + k + " etapas");
                return Sol;
            }
            double x=(B[q][q]-B[p][p])/(2*B[p][q]);
            double t;
            if(x>=0){
                t=-x+Math.sqrt(1+x*x);
            }else{
                t=-x-Math.sqrt(1+x*x);
            }
            double c=1./Math.sqrt(1+t*t);
            double s=t/Math.sqrt(1+t*t);
            //giro en O por la derecha
            for (int i = 0; i < n; i++) {
                aux=O[i][p];
                O[i][p]=c*aux - s*O[i][q];
                O[i][q]=s*aux + c*O[i][q];
            }
            // giros en B por la izda y la derecha
            B[p][p] -= t*B[p][q];
            B[q][q] += t*B[p][q];
            B[p][q]=0;
            B[q][p]=0;
            for (int i = 0; i < n; i++) {
                if((i!=p) && (i!=q)){
                    aux = B[i][p];
                    B[i][p]=c*aux -s *B[i][q];
                    B[p][i]=B[i][p];
                    B[i][q]=s*aux + c*B[i][q];
                    B[q][i]=B[i][q];
                }           
            } 
           //escribe(B);
        }
        System.out.println("JacobiPropios error : no hay convergencia en"
                + nmaxit + " etapas");
        throw new ErrorMatrices();
        
    }

   public static double normaresidualPropios(double[][] A,double[] VectYValPropio) throws ErrorMatrices{
        int n=VectYValPropio.length-1;
        double[] vectprop=new double[n];
        System.arraycopy(VectYValPropio, 0,vectprop, 0, n);
        double[] residualPropio=producto(A, vectprop);
        residualPropio=resta(residualPropio, 
                producto(VectYValPropio[n], vectprop));
        return norma(residualPropio);
    }
    
    public static double[][] QRpropios(double[][] A, double prec, double precDesplazamiento,
            int nmax)throws ErrorMatrices{
        int n=A.length;
        double[][] QRpropios=new double[n][n+1];
        double[][] B=copia(A);
        double[][][] QRB=new double[2][n][n];
        QRB[0]=Id(n);
        for (int k = 0; k < nmax; k++) {
            double sub=0;// suma de los modulos de los terminos bajo diagonal
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                   sub += B[i][j]*B[i][j];
                }
            }
            if(sub < prec){
                System.out.println("QR propios converge en "+ k + " etapas");
                // TRASPONEMOS  Q para que los vectores fila sean buenas
                // direcciones en la busqueda de vectores propios
                QRB[0]=traspuesta(QRB[0]);
                for (int i = 0; i < n; i++) {
                   QRpropios[i]=potenciaInversaDesplazada(A, B[i][i]+precDesplazamiento
                           , prec, nmax, QRB[0][i], QRB[0][i]);
                    System.out.println("||A v_k - lambda_k v_k || = "
                         + normaresidualPropios(A, QRpropios[i])); 
                }
                return QRpropios;
            }
            QRB=factorQR(B);
            B=producto(QRB[1],QRB[0]);
            
        }
        System.out.println("QR propios: no converge en "+ nmax + " etapas");
        throw new ErrorMatrices();
    }
    
     /**
     * Metodo QR para aproximar los valores propios de una matriz tridiagonal simétrica
     * 
     * @param T Objeto de la clase tridiagonal
     * @param tol tolerancia para condición de parada (no es necesario que sea muy fino)
     * @param nmax número máximo de iteraciones
     * @return un vector a con las aproximaciones a todos los valores propios.
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[] TriDiagPropiosQR1(Tridiagonal T, double tol, int nmax)
            throws ErrorMatrices{
        int n=T.dim();
        double[] a=copia(T.dPrinc); //diagonal principal de A_k=R_k Q_k que es tridiagonal
        double[] b=copia(T.dInf); //diagonal inferior (y superior) de A_k
        double[] x=new double[n];//para pasos intermedios en calculo de R_k (diagonal pal)
        double[] y=new double[n];//para pasos intermedios en calculo de R_k (diagonal sup)
        double[] z=new double[n];//para la diagonal de R_k
        double[] q=new double[n-1];//para la primera diagonal superior de R_k
       // double[] r=new double[n-2];//  para los valores de la segunda diagonal superior 
                                   //de R_k pero no se necesitan usar por la simetría de A_k
        double[] c=new double[n]; //para los cosenos de los giros
        double[] s=new double[n]; //para los senos de los giros
        
        for (int k = 0; k < nmax; k++) {
            x[0]=a[0];
            y[0]=b[0];
            for (int i = 0; i < n-1; i++) {
                z[i]=Math.sqrt(x[i]*x[i]+b[i]*b[i]);
                if(z[i]<precision){
                    c[i]=1;
                    s[i]=0;
                }else{
                    c[i]=x[i]/z[i];
                    s[i]=b[i]/z[i];
                }
                q[i]=c[i]*y[i]+s[i]*a[i+1];
                x[i+1]=-s[i]*y[i]+c[i]*a[i+1];
                if(i<n-2){
                   // r[i]=s[i]*b[i+1];
                    y[i+1]=c[i]*b[i+1];
                } 
            }
            z[n-1]=x[n-1]; //hasta aquí calculamos R_k
            //Ahora hacemos R_k Q_k
            a[0]=s[0]*q[0]+c[0]*z[0];
            b[0]=s[0]*z[1];
            double normasub=b[0]*b[0];
            for (int i = 1; i < n-1; i++) {
                a[i]=s[i]*q[i]+c[i]*c[i-1]*z[i];
                b[i]=s[i]*z[i+1];
                normasub +=b[i]*b[i];
            }
            a[n-1]=c[n-2]*z[n-1];
            if(Math.sqrt(normasub)< tol){
                //System.out.println(" "+ toString(a));
                return a;
            } 
            //System.out.println("a + "+ toString(a));
            //System.out.println("b + "+ toString(b));
        }
       
        System.out.println("QRpropios (tridiagonal) no converge en "
                + nmax + "  iteraciones");
        throw new ErrorMatrices();
        
    }
    
    /**
     * Metodo QR para aproximar los valores y vectores propios de una matriz tridiagonal simétrica
     * 
     * @param T Objeto de la clase tridiagonal
     * @param tol tolerancia para condición de parada (no es necesario que sea muy fino)
     * @param nmax número máximo de iteraciones
     * @return un vector a con las aproximaciones a todos los valores propios.
     * @throws auxiliar.Matrices.ErrorMatrices 
     */
    public static double[][] TriDiagPropiosQR(Tridiagonal T, double tol, int nmax)
            throws ErrorMatrices{
        int n=T.dim();
        double[] a=copia(T.dPrinc); //diagonal principal de A_k=R_k Q_k que es tridiagonal
        double[] b=copia(T.dInf); //diagonal inferior (y superior) de A_k
        double[] x=new double[n];//para pasos intermedios en calculo de R_k (diagonal pal)
        double[] y=new double[n];//para pasos intermedios en calculo de R_k (diagonal sup)
        double[] z=new double[n];//para la diagonal de R_k
        double[] q=new double[n-1];//para la primera diagonal superior de R_k
       // double[] r=new double[n-2];//  para los valores de la segunda diagonal superior 
                                   //de R_k pero no se necesitan usar por la simetría de A_k
        double[] c=new double[n]; //para los cosenos de los giros
        double[] s=new double[n]; //para los senos de los giros
        
        for (int k = 0; k < nmax; k++) {
            x[0]=a[0];
            y[0]=b[0];
            for (int i = 0; i < n-1; i++) {
                z[i]=Math.sqrt(x[i]*x[i]+b[i]*b[i]);
                if(z[i]<precision){
                    c[i]=1;
                    s[i]=0;
                }else{
                    c[i]=x[i]/z[i];
                    s[i]=b[i]/z[i];
                }
                q[i]=c[i]*y[i]+s[i]*a[i+1];
                x[i+1]=-s[i]*y[i]+c[i]*a[i+1];
                if(i<n-2){
                   // r[i]=s[i]*b[i+1];
                    y[i+1]=c[i]*b[i+1];
                } 
            }
            z[n-1]=x[n-1]; //hasta aquí calculamos R_k
            //Ahora hacemos R_k Q_k
            a[0]=s[0]*q[0]+c[0]*z[0];
            b[0]=s[0]*z[1];
            double normasub=b[0]*b[0];
            for (int i = 1; i < n-1; i++) {
                a[i]=s[i]*q[i]+c[i]*c[i-1]*z[i];
                b[i]=s[i]*z[i+1];
                normasub +=b[i]*b[i];
            }
            a[n-1]=c[n-2]*z[n-1];
            if(Math.sqrt(normasub)< tol){
                System.out.println("Valores propios: "+ toString(a));
                double[] y0=new double[n];
                y0[0]=1;
                double[][] r= new double[n][n+1];
                for (int i = 0; i < a.length; i++) {
                    r[i]=potenciaInversaDesplazada(T, a[i]-0.001, tol, nmax, y0, y0);
                }
                return r;
            } 
            //System.out.println("a + "+ toString(a));
            //System.out.println("b + "+ toString(b));
        }
       
        System.out.println("QRpropios (tridiagonal) no converge en "
                + nmax + "  iteraciones");
        throw new ErrorMatrices();
        
    }
    
    /**
     * Método para obtener una  matriz tridiagonal simétrica a partir de una matriz simétrica A.
     * Se ha seguido le algoritmo de la sección 9.3 de Burden-Faires.
     * @param A
     * @return Matriz tridiagonal R y su cambio de base Q.
     */
    public static double[][][] QRTriDiag(double[][] A)
    {
        int n=A.length;
        double alpha;
        double[][] P=Matrices.Id(n);
        
    //Paso 1
        for (int k = 0; k < n-2; k++) {
            double[][] Pk=Matrices.Id(n);
            double q=0;
            
        //Paso 2    
            for (int j = k+1; j < n; j++) {
                q+=A[j][k]*A[j][k];
            }
            
        //Paso 3    
            if(A[k+1][k]==0)
                alpha=-1*Math.sqrt(q);
            else
                alpha=-1*(Math.sqrt(q)*A[k+1][k])/Math.abs(A[k+1][k]);
            
        //Paso 4    
            double RSQ=alpha*alpha-alpha*A[k+1][k];
            double[] v=new double[n];
            double[] w=new double[n];
            
        //Paso 5
            v[k+1]=A[k+1][k]-alpha;
            for (int j = k+2; j < n; j++) {
                v[j]=A[j][k];
            }
            for (int j = k+1; j < n; j++) { //Este paso nos sirve para calcular la matriz de cambio de base P.
                w[j]=v[j]/(Math.sqrt(2*RSQ));

            }
            
        //Paso 6    
            double[] u=new double[n];
            for (int j = k; j < n; j++) {
                double aux=0;
                for (int i = k+1; i < n; i++) {
                    aux+=A[j][i]*v[i];
                    
                }
                u[j]=(aux/RSQ);
            }
            
        //Paso 7    
            double PROD=0;
            for (int i = k+1; i < n; i++) {
                PROD+=v[i]*u[i];
            }
           
        //Paso 8    
            double[] z=new double[n];
            for (int j = k; j < n; j++) {
                z[j]=u[j]-(PROD/(2*RSQ))*v[j];
            }
            
        //Paso 9    
            for (int l = k+1; l < n-1; l++) {
            //Paso 10    
                for (int j = l+1; j < n; j++) {
                    A[j][l]=A[j][l]-v[l]*z[j]-v[j]*z[l];
                    A[l][j]=A[j][l];
                }
            //Paso 11
                A[l][l]=A[l][l]-2*v[l]*z[l];
            }
            
        //Paso 12    
            A[n-1][n-1]=A[n-1][n-1]-2*v[n-1]*z[n-1];
            
        //Paso 13    
            for (int j = k+2; j < n; j++) {
                A[k][j]=0;
                A[j][k]=0;
            }
            
        //Paso 14
            A[k+1][k]=A[k+1][k]-v[k+1]*z[k];
            A[k][k+1]=A[k+1][k];
            double[][] M=new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    M[i][j]=w[i]*w[j]*2;
                }
            }
            try {
                Pk=Matrices.resta(Pk, M);
                P=Matrices.producto(P, Pk);
            } catch (ErrorMatrices ex) {
            }
        }
        double[][][] resultado=new double[2][n][n];
        resultado[0]=A;
        resultado[1]=P;
        return resultado;
        
    }
}
