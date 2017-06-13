package auxiliar;

/**
 * <p>
 * Title: Ejemplos MetodosEcuacionesLineales</p>
 * <p>
 * Description: </p>
 * <p>
 * Copyright: Copyright (c) 2013</p>
 * <p>
 * Company: </p>
 *
 * @author APALL
 * @version 2009.0
 */
public class MetodosEcuacionNoLineal {

    /**
     * Metodo para aproximar ceros de una funcion f tal que f(a)*f(b)<0.
     *
     * @param f
     * @param a
     * @param b
     * @param precision para condicion de parada
     * @param nmaxiter numero maximo de iteradas
     * @return una aproximacion de un cero de f o un mensaje de error si se
     * excede el numero maximo de iteraciones
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR
     */
    public static double biseccion(Funcion f, double a, double b, double precision,
            int nmaxiter) throws ERROR {
        double aa = a;
        double bb = b;
        double h=bb-aa;
        double u = f.eval(aa);
        double v = f.eval(bb);
        double c;
        double w;

        if (Math.abs(u) < precision) {
            System.out.println("BISECCION: raiz en a=" + a + " f(a)=" + u + " en " + 0 + " pasos");
            return a;
        }
        if (Math.abs(v) < precision) {
            System.out.println("BISECCION: raiz en b=" + b + " f(b)=" + v + " en " + 0 + " pasos");
            return b;
        }

        if (u * v > 0) {
            System.out.println("Biseccion: no hay cambio de signo de f");
            throw new ERROR();
        }
        for (int k = 1; k <= nmaxiter; k++) {
            System.out.println("k="+k+"   aa="+aa+"   bb="+bb+"   f(aa)="+u+"   f(bb)="+v+"   h="+h);
            h = 0.5 * (bb - aa);
            c = aa + h;
            w = f.eval(c);
            if ((h < precision) || (Math.abs(w) < precision)) { 
                System.out.println("BISECCION: raiz en c=" + c + " f(c)=" + w + " en " + k + " pasos");
                return c;
            }
            if (w * u > 0) {
                aa = c;
                u = w;
            } else {
                bb = c;
                v = w;
            }
        }
        System.out.println("Bolzano: No hay convergencia en " + nmaxiter + " iteraciones");
        throw new ERROR();
    }

    /*
    
    */
     public static double regulaFalsi(Funcion f, double a, double b, double precision,
     int nmaxiter) throws ERROR {
     double aa = a;
     double bb = b;
     double h;
     double u = f.eval(aa);
     double v = f.eval(bb);
     double c;
     double w;

     if (u * v >= 0) {
     System.out.println("regula Falsi: no hay cambio de signo de f");
     throw new ERROR();
     }

     for (int k = 1; k <= nmaxiter; k++) {
     h = -u * (bb - aa) / (v - u);
     c = aa + h;
      System.out.println("k= "+k+"  aa= "+aa+"  bb= "+bb+  "  u="+ u +"  v="+v+"  h="+h);
     w = f.eval(c);
     if ((h < precision) || (Math.abs(w) < precision)) {
     System.out.println("REGULA FALSI: raiz en c=" + c + " f(c)=" + w + " en " + k + " pasos");
     return c;
     }
     if (w * u > 0) {
     aa = c;
     u = w;
     } else {
     bb = c;
     v = w;
     }
     }
     System.out.println("Regula Falsi: No hay convergencia en " + nmaxiter + " iteraciones");
     throw new ERROR();
     }
    
    /*
    
    */
     public static double regulaFalsiModificado(Funcion f, double a, double b, double precision,
     int nmaxiter) throws ERROR {
     double aa = a;
     double bb = b;
     double h;
     double u = f.eval(aa);
     double v = f.eval(bb);
//      Modificacion: guardamos memoria del valor de f ultimo punto de corte, empezando con aa
     double w1 = u;
     double c2;
     double w2;

     if (u * v >= 0) {
     System.out.println("regula Falsi Modificado: no hay cambio de signo de f");
     throw new ERROR();
     }

     for (int k = 1; k <= nmaxiter; k++) {
     h = -u * (bb - aa) / (v - u);
     c2 = aa + h;
       System.out.println("k="+k+ " c="+c2+ " u="+ u +" v="+v);
     w2 = f.eval(c2);
     if ((h < precision) || (Math.abs(w2) < precision)) {
     System.out.println("REGULA FALSI MODIFICADO: raiz en c=" + c2 + " f(c)=" + w2 + " en " + k + " pasos");
     return c2;
     }
     if (w2 * u > 0) {
     aa = c2;
     u = w2;
     if (w1 * w2 > 0) {
     v = 0.5 * v;
     }
     w1 = w2;
     } else {
     bb = c2;
     v = w2;
     if (w1 * w2 > 0) {
     u = 0.5 * u;
     }
     w1 = w2;
     }
     }
     System.out.println("Regula Falsi Modificado: No hay convergencia en " + nmaxiter + " iteraciones");
     throw new ERROR();
     }
     
    
    /**
     * Metodo de Newton para aproximar ceros de la funcion f empezando con
     * una aproximacion inicial x
     * @param f  Funcion derivable
     * @param x  aproximacion inicial
     * @param precision para condicion de parada
     * @param nmaxiter numero maximo de iteraciones
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR 
     */
    public static double newton(FuncionDeriv f, double x, double precision,
            int nmaxiter) throws ERROR {
        double xx = x;
        double h=1;
        double u = f.eval(xx);
        double du = f.derivada(xx);

        for (int k = 1; k <= nmaxiter; k++) {
            if (Math.abs(u) < precision && Math.abs(h)<precision) {
                System.out.println("NEWTON: raiz en c=" + xx + " f(c)=" + u + " en " + k + " pasos");
                return xx;
            }
            if (Math.abs(du) < precision) {
                System.out.println("Newton: Derivada nula en x");
                throw new ERROR();
            }            
            h = -u / du;
            xx = xx + h;
            u = f.eval(xx);
            du = f.derivada(xx);
            System.out.println(" k= "+k+"  xx= "+xx+"  u="+ u +"  h="+h);
            if (Math.abs(h) < precision) {
                System.out.println("NEWTON: raiz en c=" + xx + " f(c)=" + u + " en " + k + " pasos");
                return xx;
            }
        }
        System.out.println("Newton: No hay convergencia en " + nmaxiter + " iteraciones");
        throw new ERROR();
    }


    public static double newtonRaizMultiple(Funcion f, double x, double prec,int nmaxit) 
            throws ERROR {
        double xx = x;
        double h;
        double aux;
        double v; 
        MetodosFunciones.derivada3puntos dv = new MetodosFunciones.derivada3puntos(f, Math.pow(prec, 0.5));
        MetodosFunciones.derivadaSegunda3puntos ddv=new MetodosFunciones.derivadaSegunda3puntos(f, Math.pow(prec, 0.5));
        for (int k=1;k<=nmaxit;k++){
            v=f.eval(xx);
            if (Math.abs(v)<prec) {
                System.out.println("Newton con raíz múltiple: raíz en c="+ xx +" en "+ (k-1) + "pasos");
                System.out.println("f(c)="+v);
                return xx;
            }
        aux=dv.eval(xx)*dv.eval(xx)-v*ddv.eval(xx);    
        h=-(v*dv.eval(xx))/aux;
        xx=xx+h;
        if (Math.abs(h)<prec) {
            System.out.println("Newton con raíz múltiple: raíz en c="+ (xx+h) +" en "+ k + "pasos");
                System.out.println("h="+h+"     f(c)="+f.eval(xx));
                return xx;
        }
        }
        System.out.println("Newton con raíz múltiple: No hay convergencia en " + nmaxit + " iteraciones");
        throw new ERROR();
    }
    
    /**
     * Metodo de la secante para aproximar ceros de funciones a partir de dos aproximaciones iniciales
     * @param f
     * @param x0 aproximacion inicial
     * @param x1 aproximacion inicial
     * @param precision
     * @param nmaxiter
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR 
     */
    public static double secante(Funcion f, double x0, double x1,
            double precision, int nmaxiter) throws ERROR {
        double xx0 = x0;
        double xx1 = x1;
        double h;
        double u = f.eval(xx0);
        double v = f.eval(xx1);
        if (Math.abs(u) < precision) {
            System.out.println("Raiz en c = " + xx0 + "; f(c) = " + u);
            return xx0;
        }
        if (Math.abs(v) < precision) {
            System.out.println("SECANTE: Raiz en c = " + xx1 + "; f(c) = " + v);
            return xx1;
        }
        if (Math.abs(xx0 - xx1) < precision) {
            System.out.println(
                    "Secante: error en las condiciones iniciales.");
            throw new ERROR();
        }
        for (int k = 1; k <= nmaxiter; k++) {
            if (Math.abs(u - v) < precision) {
                System.out.println(
                        "Secante: secante horizontal en el paso " + k + ".");
                throw new ERROR();
            }
            h = -v * (xx1 - xx0) / (v - u);
            xx0 = xx1;
            u = v;
            xx1 = xx1 + h;
            v = f.eval(xx1);
            System.out.println(" k= "+k+"  xx= "+xx1+"  v="+ v +"  h="+h);
            if (Math.abs(v) < precision && Math.abs(h)< precision) {
                System.out.println("SECANTE: Raiz en c = " + xx1 + "; f(c) = " + v + " en " + k + " pasos.");
                return xx1;
            }
        }
        System.out.println("Secante: no hay convergencia en " + nmaxiter + " iteraciones.");
        throw new ERROR();
    }

    /**
     * Metodo de Muller para aproximar ceros de funciones a partir de tres aproximaciones iniciales
     * 
     * @param f
     * @param x0
     * @param x1
     * @param x2
     * @param precision
     * @param nmaxiter
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR 
     */
    public static double Muller(Funcion f, double x0, double x1, double x2,
            double precision, int nmaxiter) throws ERROR {
        double xx0 = x0, xx1 = x1, xx2 = x2;
        double f0 = f.eval(x0), f1 = f.eval(x1), f2 = f.eval(x2);
        if ((Math.abs(x0 - x1) < precision) || (Math.abs(x0 - x2) < precision) || (Math.abs(x1 - x2) < precision)) {
            System.out.println("Parada Muller: nodos iniciales iguales");
            throw new ERROR();
        }
        double f01 = (f0 - f1) / (xx0 - xx1);
        double f02, f12;
        double h = 1. + precision;
        for (int i = 0; i < nmaxiter; i++) {
            if ((Math.abs(f2) < precision) || (Math.abs(h) < precision)) {
                System.out.println("MULLER:  raiz en " + xx2 + " con f(" + xx2 + ")= " + f2 + " en  " + i + " iteraciones");
                return xx2;
            }
            f02 = (f0 - f2) / (xx0 - xx2);
            f12 = (f1 - f2) / (xx1 - xx2);
            double f012 = (f01 - f12) / (xx0 - xx2);
            double W = f12 + f02 - f01;
            double aux = W * W - 4 * f2 * f012;
            if (aux < 0) {
                System.out.println("Parada Muller: Salimos de la recta real");
                throw new ERROR();
            }
            aux = Math.sqrt(aux);
            if ((Math.abs(W) < precision) && (Math.abs(f012) < precision)) {
                System.out.println("Parada Muller: polinomio interpolador constante");
                throw new ERROR();
            }
            double denom1 = W + aux;
            double denom2 = W - aux;
            if (Math.abs(denom1) > Math.abs(denom2)) {
                h = -2 * f2 / denom1;
            } else {
                h = -2 * f2 / denom2;
            }
            xx0 = xx1;
            f0 = f1;
            xx1 = xx2;
            f1 = f2;
            f01 = f12;
            xx2 = xx2 + h;
            f2 = f.eval(xx2);
//            System.out.println(" i= "+i+"  xx2= "+xx2+"  v="+ f2 +"  h="+h);
        }

        System.out.println("Parada Muller: no hay convergencia en " + nmaxiter + "iteraciones");
        throw new ERROR();
    }

         


    /**
     * Implementacion del metodo de iteracion funcional para aproximar
     * puntos fijos de funciones
     * @param f
     * @param x
     * @param precision
     * @param nmaxiter
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR 
     */
    public static double iteracionPF(Funcion f, double x, double precision, int nmaxiter)
            throws ERROR {
        double xx = x;
        double fx = f.eval(xx);
        for (int k = 1; k <= nmaxiter; k++) {
            if (Math.abs(fx - xx) < precision) {
                System.out.println("punto fijo en c=" + xx + " f(c)=" + fx + " en " + k + " pasos");
                return xx;
            }
            xx = fx;
            fx = f.eval(xx);
            System.out.println("x="+xx+"   f(x)="+fx);
        }
        System.out.println("PuntoFijo: No hay convergencia en " + nmaxiter + " iteraciones");
        throw new ERROR();
    }

    
    /**
     * Metodo de iteracion de punto fijo con aceleracion de Aitken
     * @param f
     * @param x
     * @param precision
     * @param nmaxiter
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR 
     */
    public static double iteracionPFacelerada(Funcion f, double x, double precision, int nmaxiter)
            throws ERROR {
        double xx = x;
        double fx = f.eval(xx);
        double ffx;
        double xxacelerado;
        for (int k = 1; k <= nmaxiter; k++) {
            if (Math.abs(fx - xx) < precision) {
                System.out.println("punto fijo en c=" + xx + " f(c)=" + fx + " en " + k + " pasos");
                return xx;
            }
            ffx = f.eval(fx);
            xxacelerado = xx - ((fx - xx) * (fx - xx)) / (ffx - 2 * fx + xx);
            if (Math.abs(f.eval(xxacelerado) - xxacelerado) < precision) {
                System.out.println("punto fijo en c=" + xxacelerado + " f(c)=" + f.eval(xxacelerado) + " en " + k + " pasos");
                return xxacelerado;
            }
            xx = fx;
            fx = f.eval(xx);
        }
        System.out.println("PuntoFijo: No hay convergencia en " + nmaxiter + " iteraciones");
        throw new ERROR();
    }

    /**
     * Metodo de iteracion de Steffensen para aproximar Puntos Fijos de funciones
     * @param f
     * @param x
     * @param precision
     * @param nmaxiter
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR 
     */
    public static double steffensenPF(Funcion f, double x, double precision,
            int nmaxiter) throws ERROR {
        double xx = x;
        double xprev;
        double fx = f.eval(xx);
        double ffx = f.eval(fx);

        for (int k = 1; k <= nmaxiter; k++) {
            if (Math.abs(fx - xx) < precision) {
                System.out.println("Punto fijo en c = " + xx + "; f(c) = " + fx
                        + " en " + k + " pasos.");
                return xx;
            }
            xprev=xx;
            xx = xx - ((fx - xx) * (fx - xx)) / (ffx - 2 * fx + xx);
            fx = f.eval(xx);
            ffx = f.eval(fx);
        }
        System.out.println("Steffensen: no hay convergencia en " + nmaxiter
                + " iteraciones.");
        throw new ERROR();
    }


    
    /**
     * Implementacion del metodo de iteracion funcional para aproximar puntos
     * fijos de funciones
     *
     * @param f
     * @param x
     * @param precision
     * @param nmaxiter
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR
     */
    public static double[] iteracionPFV(FuncionVV f, double[] x, double precision, int nmaxiter)
            throws ERROR, Matrices.ErrorMatrices{
        double[] xx = Matrices.copia(x);
        double[] fx = f.eval(xx);
        for (int k = 1; k <= nmaxiter; k++) {
            if (Matrices.norma(Matrices.resta(fx, xx)) < precision) {
                System.out.println("punto fijo en \n \t c=" + Matrices.toString(xx)
                        + " \n \t f(c)=" + Matrices.toString(fx) +
                        " \n \t en " + k + " pasos");
                return xx;
            }
            xx = Matrices.copia(fx);
            fx = f.eval(xx);
        }
        System.out.println("PuntoFijo: No hay convergencia en " + nmaxiter + " iteraciones");
        throw new ERROR();
    }


    /**
     * Metodo de iteracion de punto fijo con aceleracion de Aitken
     * @param f
     * @param x
     * @param precision
     * @param nmaxiter
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR 
     */
    public static double[] iteracionPFacelerada(FuncionVV f, double[] x, double precision, int nmaxiter)
            throws ERROR, Matrices.ErrorMatrices {
        
        double[] xx =Matrices.copia(x);
        double[] fx = f.eval(xx);
        double[] ffx;
        int n=x.length;
        double[] xxacelerado=new double[n];
        for (int k = 1; k <= nmaxiter; k++) {
            if (Matrices.norma(Matrices.resta(fx,xx)) < precision) {
                System.out.println("punto fijo (Aitken) en c=" + Matrices.toString(xx) + 
                        " f(c)=" + Matrices.toString(fx) + " en " + k + " pasos");
                return xx;
            }
            ffx = f.eval(fx);
            for (int i = 0; i < n; i++) {
                 xxacelerado[i] = xx[i] - ((fx[i] - xx[i]) * (fx[i] - xx[i])) / (ffx[i] - 2 * fx[i] + xx[i]);
            }
            if (Matrices.norma(Matrices.resta(f.eval(xxacelerado),xxacelerado)) < precision) {
                System.out.println("punto fijo en \n \t c=" + 
                        Matrices.toString(xxacelerado) + " \n \t f(c)=" +
                        Matrices.toString(f.eval(xxacelerado)) + " \n \t en " + k + " pasos");
                return xxacelerado;
            }
            xx = fx;
            fx = f.eval(xx);
        }
        System.out.println("PuntoFijoAcelerado: No hay convergencia en " + nmaxiter + " iteraciones");
        throw new ERROR();
    }

    /**
     * Metodo de Newton para aproximar ceros de funciones vectoriales de
     * variable vectorial que son diferenciables .
     *
     * @param f FuncionVVDif que tiene definido el jacobiano.
     * @param x
     * @param prec
     * @param paso
     * @param nmax
     * @param imp
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR
     */
    public static double[] newtonVect(FuncionVVDif f, double[] x, double prec,
            int nmax, boolean imp)
            throws ERROR {
        int n = x.length;
        double[] xi = Matrices.copia(x);

        double[] u = f.eval(xi);
        if (Matrices.norma(u) < prec) {
            System.out.println("La solucion es x=" + Matrices.toString(x));
            System.out.println("            f(x)=" + Matrices.toString(u));
            return xi;
        }

        double[] xf = new double[n];
        double[] incr = new double[n];

        for (int k = 1; k < nmax; k++) {
            try {
                incr = Matrices.solveGaussP(f.jacobiano(xi), u);
                xf = Matrices.resta(xi, incr);
                u = f.eval(xf);
                if (imp == true) {
                    System.out.println("en el paso " + k + " x =" + Matrices.toString(xf));
                    System.out.println("y f(x)=" + Matrices.toString(u));
                }
            } catch (Matrices.ErrorMatrices error) {
                System.out.println("Error en paso de Newton");
            }

            if ((Matrices.norma(incr) < prec) && (Matrices.norma(u)) < prec) {
                System.out.println(" Newton con Jacobiano \n La solucion es x =" + Matrices.toString(xf));
                System.out.println("             f(x)=" + Matrices.toString(u));
                System.out.println("   El numero de pasos es=" + k);
                return xf;
            }
            xi = Matrices.copia(xf);
        }
        System.out.println("No hay convergencia en " + nmax + "pasos");
        throw new ERROR();
    }

     /**
     * Metodo de Newton para aproximar ceros de funciones vectoriales de
     * variable vectorial. Utiliza el metodo MetodosFunciones.jacobiano para
     * aproximar el jacobiano.
     *
     * @param f
     * @param x
     * @param prec
     * @param paso
     * @param nmax
     * @param imp
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR
     */
    public static double[] newtonVectAprox(FuncionVV f, double[] x, double prec,
            double paso, int nmax, boolean imp)
            throws ERROR {
        int n = x.length;
        double[] xi = Matrices.copia(x);

        double[] u = f.eval(xi);
        if (Matrices.norma(u) < prec) {
            System.out.println("La solucion es x=" + Matrices.toString(x));
            System.out.println("            f(x)=" + Matrices.toString(u));
            return xi;
        }

        double[] xf = new double[n];
        double[] incr = new double[n];

        for (int k = 1; k < nmax; k++) {
            try {
                incr = Matrices.solveGaussP(MetodosFunciones.jacobianoApp3(f, xi, paso), u);
                xf = Matrices.resta(xi, incr);
                u = f.eval(xf);
                if (imp == true) {
                    System.out.println("en el paso " + k + " x =" + Matrices.toString(xf));
                    System.out.println("y f(x)=" + Matrices.toString(u));
                }
            } catch (Matrices.ErrorMatrices error) {
                System.out.println("Error en paso de Newton");
                throw new ERROR();
            }

            if ((Matrices.norma(incr) < prec) && (Matrices.norma(u)) < prec) {
                System.out.println("Newton (con jacobiano approximado) \n La solucion es x =" + Matrices.toString(xf));
                System.out.println("             f(x)=" + Matrices.toString(u));
                System.out.println("   El numero de pasos es=" + k);
                return xf;
            }
            xi = Matrices.copia(xf);
        }
        System.out.println("No hay convergencia en " + nmax + "pasos");
        throw new ERROR();
    }

    /**
     * Metodo de Broyden (cuasi-Newton) para aproximar ceros de funciones
     * vectoriales de variable vectorial. Utiliza el metodo
     * MetodosFunciones.jacobiano para aproximar el jacobiano en la primera
     * etapa.
     *
     * @param f
     * @param x
     * @param prec
     * @param paso
     * @param nmax
     * @param imprime
     * @return
     * @throws auxiliar.MetodosEcuacionNoLineal.ERROR
     */
    public static double[] cuasiNewton_Broyden(FuncionVV f, double[] x0, double prec, int nmaxit,
            boolean imprime) throws ERROR {
        int n = x0.length;
        double[] xi = Matrices.copia(x0); //para guardar x_{k-1}
        double[] xf = new double[n]; // para guardar x_k
        double[] y = new double[n]; // para guardar los incrementos x_k - x_{k-1}
        double[] x = new double[n]; // vector auxilar para definir A^(-1)
        double[][] A = MetodosFunciones.jacobianoApp3(f, x0, 100 * prec);
        try {
            A = Matrices.inversaGauss(A); //Se invierte el jacobiano
        } catch (Exception error1) {
            System.out.println("Error al invertir 1a vez");
            throw new ERROR();
        }
        double[] v = f.eval(xi);
        for (int k = 0; k < nmaxit; k++) {
            try {
                y = Matrices.producto(A, Matrices.producto(-1., v)); // O(n^2) operaciones
                xf = Matrices.suma(xi, y); // O(n^2) operaciones
                v = f.eval(xf);

                if (imprime == true) {
                   // System.out.println("en el paso " + k + " x =" + Matrices.toString(xf));
                    //System.out.println("y f(x)=" + Matrices.toString(v));
                }
                if ((Matrices.norma(y) < prec) && (Matrices.norma(v)) < prec) {
                    System.out.println("Broyden (cuasi-Newton)\n La solucion es x =" + Matrices.toString(xf));
                    System.out.println("             f(x)=" + Matrices.toString(v));
                    System.out.println("   El numero de pasos es=" + k);
                    return xf;
                }

                double[][] yt = new double[1][n];
                for (int i = 0; i < n; i++) {
                    yt[0][i] = y[i];
                }
                double[] z = Matrices.producto(A, v);   // O(n^2) operaciones
                double den = Matrices.producto(y, Matrices.suma(y, z)); // O(n^2) operaciones
                double[][] vv = new double[n][1];
                for (int i = 0; i < n; i++) {
                    vv[i][0] = v[i];
                }
                double[][] aux = Matrices.producto(A, vv); // O(n^2) operaciones
                aux = Matrices.producto(aux, Matrices.producto(yt, A)); // O(n^2) operaciones
                aux = Matrices.producto(1. / den, aux);  // O(n^2) operaciones
                A = Matrices.resta(A, aux); // O(n^2) operaciones
                xi = xf;
            } catch (Matrices.ErrorMatrices error2) {
                System.out.println("Error dimensiones matrices ");
                throw new ERROR();
            }


        }

        System.out.println("No hay convergencia en " + nmaxit + " iteraciones");
        throw new ERROR();
    }


    /**
     * metodo del descenso rapido para aproximar puntos donde g tiene un 
     * mÃ­nimo (relativo)
     * @param g
     * @param X0
     * @param TOL
     * @param nmaxit
     * @return un mÃ­nimo o un error si no hay convergencia
     */
    
    public static double[] descensoRapido(FuncionObjetivo g,double[] X0,
            double TOL, int nmaxit)throws ERROR, Matrices.ErrorMatrices{
        double[] x=Matrices.copia(X0);
        double g1=g.objetivo(x);
        for (int k = 0; k < nmaxit; k++) {
            double[] z=g.gradiente(x);
            double z0=Matrices.norma(z);
            if(z0 < TOL/1000){
                System.out.println("Warning: Punto estacionario, posible"
                        + " minimo en x con f(x)= "+ g1);
                return x;
            }
            z=Matrices.producto(1/z0, z);
            double a1=0;
            double a3=1;
            double[] x3=Matrices.resta(x, Matrices.producto(a3, z));
            double g3=g.objetivo(x3);
            while(g3 >= g1){
                a3=a3/2;
                if(a3<TOL/2){
                   System.out.println("Warning: posible"
                        + " minimo en x con f(x)= "+ g3);
                return x; 
                }
                x3=Matrices.resta(x, Matrices.producto(a3, z));
                g3=g.objetivo(x3);
            }
            double a2=a3/2;
            double[] x2=Matrices.resta(x, Matrices.producto(a2, z));
            double g2=g.objetivo(x2);
            
            //interpolamos con la parabola que pasa por (a_i,g_i)
            // diferencias divididas de Newton
            
            double d1=(g2-g1)/a2;
            double d2=(g3-g2)/a2;
            double d12=(d2-d1)/a3;
            // p= g1+ d1 a + d12 a (a-a2)
            double a0=0.5*(a2- d1/d12); //vertice de la parabola
            double[] x0=Matrices.resta(x, Matrices.producto(a0, z));
            double g0=g.objetivo(x0);
            double gA;
            if(g0 < g3){
                x=Matrices.copia(x0);
                gA=g0;
            }else{
                x=Matrices.copia(x3);
                gA=g3;
            }
            if(Math.abs(gA-g1)<TOL){
                System.out.println("minimo en x "+
                        " con g(x) = "+ gA + 
                        " en "+ k + " iteraciones");
                return x;
            }
            g1=gA;
        }
        System.out.println("Error en Descenso en Rapido: "
                + "No hay convergencia en "+ nmaxit + " iteraciones");
        throw new ERROR();
        
    }
    
    

    // Excepcion ERROR para ser devuelta por los metodos de la clase
    public static class ERROR extends Exception {

        ERROR() {
            super("Error al aproximar raices");
        }
    }
}
