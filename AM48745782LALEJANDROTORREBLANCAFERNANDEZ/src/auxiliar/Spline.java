/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

/**
 *
 * @author antoniopallaresruiz
 */
public class Spline implements Funcion {

    int n = 1; // numero de puntos para interpolar.
    double[] x = {0., 0.}; //nodos
    //coeficientes de polinomios a_i+b_i*(x-x_i)+c_i*(x-x_i)^2+d_i*(x-x_i)^3
    double[] a = {0.}; // coeficientes a_i
    double[] b = {0.}; // coeficientes b_i
    double[] c = {0.};  // ....
    double[] d = {0.};  // ....

    /** Metodo para construir el Spline natural
     *
     * datos de entrada: lista de puntos xy[]={x[i],a[i]}
     *
     * @param xy[]
     *
     */
    public Spline(double[][] xy0) {
        double[][] xy=MetodosListas.Ordenada1columna(xy0);
        n = xy.length - 1;
        x = new double[n + 1];
        a = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            x[i] = xy[i][0];
            a[i] = xy[i][1];

        }
        b = new double[n + 1];
        c = new double[n + 1];
        d = new double[n + 1];
        double[] h = new double[n];
        double[] ds = new double[n];
        double[] l = new double[n + 1];
        double[] u = new double[n];
        double[] z = new double[n];

        for (int i = 0; i < n; i++) {
            h[i] = x[i + 1] - x[i];
            ds[i] = a[i + 1] - a[i];
        }
        l[0] = 1.;
        u[0] = 0;
        z[0] = 0;
        for (int i = 1; i < n; i++) {
            l[i] = 2 * (h[i] + h[i - 1]) - h[i - 1] * u[i - 1];
            u[i] = h[i] / l[i];
            z[i] = ((3 * ds[i] / h[i] - 3 * ds[i - 1] / h[i - 1]) - h[i - 1] * z[i - 1]) / l[i];
        }
        l[n] = 1;
        c[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            c[i] = z[i] - u[i] * c[i + 1];
            b[i] = ds[i] / h[i] - h[i] * (c[i + 1] + 2 * c[i]) / 3;
            d[i] = (c[i + 1] - c[i]) / (3 * h[i]);
        }


    }

     /** Metodo para construir el Spline sujeto
     *
     * datos de entrada: lista de puntos xy[]={x[i],a[i]}
     *                   s'(x[0])= tanini
     *                   s'(x[n])= tanfin
     *
     * @param xy[]
     *
     */
    public Spline(double[][] xy0, double tanini, double tanfin) {
        n = xy0.length - 1;
        x = new double[n + 1];
        a = new double[n + 1];
        
        double[][] xy=MetodosListas.Ordenada1columna(xy0);
        
        for (int i = 0; i <= n; i++) {
            x[i] = xy[i][0];
            a[i] = xy[i][1];
            
           // System.out.println("x " + x[i] + "a "+ a[i] );

        }
        b = new double[n + 1];
        c = new double[n + 1];
        d = new double[n + 1];
        double[] h = new double[n];
        double[] ds = new double[n];
        double[] l = new double[n + 1];
        double[] u = new double[n];
        double[] z = new double[n+1];

        for (int i = 0; i < n; i++) {
            h[i] = x[i + 1] - x[i];
            ds[i] = a[i + 1] - a[i];
        }
        l[0] = 2*h[0]; //cambio relativo a splin natural
        u[0] = 0.5; //cambio relativo a splin natural
        z[0] = (3 * ds[0]/h[0] - 3 * tanini)/l[0]; //cambio relativo a splin natural
        for (int i = 1; i < n; i++) {
            l[i] = 2 * (h[i] + h[i - 1]) - h[i - 1] * u[i - 1];
            u[i] = h[i] / l[i];
            z[i] = ((3 * ds[i] / h[i] - 3 * ds[i - 1] / h[i - 1]) - h[i - 1] * z[i - 1]) / l[i];
        }
        l[n] = h[n-1]*(2- u[n-1]); //cambio relativo a splin natural
        z[n] = ((3 * tanfin - 3* ds[n-1]/h[n-1]) - h[n-1]*z[n-1])/l[n];  //cambio relativo a splin natural
        c[n] = z[n]; //cambio relativo a splin natural
        for (int i = n - 1; i >= 0; i--) {
            c[i] = z[i] - u[i] * c[i + 1];
            b[i] = ds[i] / h[i] - h[i] * (c[i + 1] + 2 * c[i]) / 3;
            d[i] = (c[i + 1] - c[i]) / (3 * h[i]);
        }


    }

 

    @Override
    public double eval(double y) {
        if (y < x[0]) {
         
            return 0;
        }
        for (int i = 0; i < n; i++) {
            if (y <= x[i + 1]) {
                return a[i] + (y - x[i]) * (b[i] + (y - x[i]) * (c[i] + (y - x[i]) * d[i]));
            }

        }
        return 0.;
    }
}