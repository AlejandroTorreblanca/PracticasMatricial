/*
 * Matrices.java
 *
 * Creado el 31 de enero de 2011, 11:00
 */
package auxiliar;

import ORG.netlib.math.complex.Complex;

/**
 *
 * @author Antonio Pallares
 *
 * @version 2015.0 (inicial)
 *
 *
 * ANALISIS NUMERICO MATRICIAL - GRADO EN MATEMATICAS - UNIVERSIDAD DE MURCIA
 *
 *
 */
public class Matrices {

    /**
     * Variable precision utilizada para considerar nulos los numeros de menor
     * tama~no
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
     * El metodo comprueba si es o no una matriz
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

    public static double[] residual(double[][] A, double[] x, double[] b)
            throws ErrorMatrices {
        return resta(producto(A, x), b);
    }

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
     * dada una matriz rectangular mxn A, devuelve la matriz R de la
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
    
    public static double[][][][] unpasoStressen(double[][][][] A,double[][][][] B) throws ErrorMatrices
    {
        int n;
        n = A[0][0].length;
        double[][][][] C = new double[2][2][n][n];
        double[][][] M=new double[7][n/2][n/2];
        
        M[0]=Matrices.producto(Matrices.suma(A[0][0],A[1][1]), Matrices.suma(B[0][0],B[1][1]));
        M[1]=Matrices.producto(Matrices.suma(A[1][0], A[1][1]), B[0][0]);
        M[2]=Matrices.producto(A[0][0], Matrices.resta(B[0][1], B[1][1]));
        M[3]=Matrices.producto(A[1][1], Matrices.resta(B[1][0], B[0][0]));
        M[4]=Matrices.producto(Matrices.suma(A[0][0],A[0][1]), B[1][1]);
        M[5]=Matrices.producto(Matrices.resta(A[1][0],A[0][0]), Matrices.suma(B[0][0],B[0][1]));
        M[6]=Matrices.producto(Matrices.resta(A[0][1],A[1][1]), Matrices.suma(B[1][0],B[1][1]));

        C[0][0]=Matrices.resta(Matrices.suma(Matrices.suma(M[0],M[3]),M[6]),M[4]);
        C[0][1]=Matrices.suma(M[2],M[4]);
        C[1][0]=Matrices.suma(M[1],M[3]);
        C[1][1]=Matrices.suma(Matrices.suma(Matrices.resta(M[0],M[1]),M[2]),M[5]);
        
        return C;
    }

    public static double[][] productoStrassen(double[][] A,double[][] B) throws ErrorMatrices
    {
        int n;
        n = A[0].length;
        double[][][][] auxC=new double[2][2][n][n];
        double[][][][] auxA=division4(A);
        double[][][][] auxB=division4(B);
        if(n<2)
        {
            auxC[0][0]=Matrices.suma(Matrices.producto(auxA[0][0], auxB[0][0]), Matrices.producto(auxA[0][1], auxB[1][0]));
            auxC[1][0]=Matrices.suma(Matrices.producto(auxA[1][0], auxB[0][0]), Matrices.producto(auxA[1][1], auxB[1][0]));
            auxC[0][1]=Matrices.suma(Matrices.producto(auxA[0][1], auxB[0][1]), Matrices.producto(auxA[0][1], auxB[1][1]));
            auxC[1][1]=Matrices.suma(Matrices.producto(auxA[1][0], auxB[0][1]), Matrices.producto(auxA[1][1], auxB[1][1]));
            return reune4(auxC);
        }
        else
        {
            auxC=unpasoStressen(auxA, auxB);
            return reune4(auxC);
        }
    }
    
}

