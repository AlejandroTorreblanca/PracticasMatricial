/**
 * Polinomio.java
 *
 * Modificado el 16 de diciembre de 2004, 12:35 Modificado el 14 de enero de
 * 2005, 22:15 Modificado en enero/febrero de 2006 Modificado en abril de 2007
 * Modificado en abril de 2008 Modificado en Marzo de 2009 Modificado en octubre
 * de 2009 VERSION INICIAL para practica 2 (2009-2010).
 */
package auxiliar;

import ORG.netlib.math.complex.Complex;
import java.util.ArrayList;

//import java.util.*;   
/**
 *
 * @author Alumnos de "Metodos Numericos" desde 2002-2003, profesores: Victor
 * Jimenez y Antonio Pallares.
 *
 * utiliza la clase Complex creada por Alexander Anderson Copyright (c) 1997 -
 * 2001, depositada en NETLIB
 *
 * METODOS NUMERICOS LICENCIATURA DE MATEMATICAS UNIVERSIDAD DE MURCIA
 */
/**
 * Cada objeto polinomio p(x)=a0 + a1 x + a2 x^2 + ... + ak x^k tiene como
 * atributo una sucesion de numeros complejos con sus coeficientes
 * {a0,a1,...,ak}
 */
public class Polinomio implements Funcion {

    public Complex[] coef;
    static double precision = 5E-16;

    /**
     * Constructor de un objeto Polinomio.
     * <p>
     * Por defecto es el polinomio nulo.
     */
    public Polinomio() {
        coef = new Complex[1];
        coef[0] = new Complex();
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio.
     * <p>
     * Polinomio(coeficientes) crea el polinomio cuyos coeficientes son los
     * reales de la lista "coeficientes".
     */
    public Polinomio(double[] coeficientes) {
        int n = coeficientes.length;
        coef = new Complex[n];

        /**
         * Comenzamos eliminando los ceros superfluos de la sucesion de
         * coeficientes
         */
        for (int j = n - 1; j > 0; j--) {
            if (Math.abs(coeficientes[j]) < precision) {
                n = n - 1;
            } else {
                break;
            }
        }
        coef = new Complex[n];
        for (int i = 0; i < n; i++) {
            coef[i] = new Complex(coeficientes[i]);
        }
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio.
     * <p>
     * Polinomio(coeficientes) crea el polinomio cuyos coeficientes son los
     * complejos de la lista "coeficientes".
     */
    public Polinomio(Complex[] coeficientes) {
        int n = coeficientes.length;

        for (int j = n - 1; j > 0; j--) {
            if (coeficientes[j].abs() < precision) {
                n = n - 1;
            } else {
                break;
            }
        }
        coef = new Complex[n];
        for (int i = 0; i < n; i++) {
            coef[i] = new Complex(coeficientes[i]);
        }
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio.
     * <p>
     * Polinomio(q) crea un polinomio con los mismos coeficientes que q.
     */
    public Polinomio(Polinomio q) {
        int n = q.coef.length;
        coef = new Complex[n];

        for (int i = 0; i < n; i++) {
            coef[i] = new Complex(q.coef[i]);
        }
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio.
     * <p>
     * Polinomio(z) crea un polinomio constante igual al complejo z.
     */
    public Polinomio(Complex z) {
        coef = new Complex[1];

        coef[0] = z;
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio.
     * <p>
     * Polinomio(x) crea un polinomio constante igual al real x.
     */
    public Polinomio(double x) {
        coef = new Complex[1];

        coef[0] = new Complex(x, 0);
    }

    /**
     * Metodo para obtener el grado del polinomio
     * <p>
     *
     * USO p.grado()
     */
    public int grado() {
        return coef.length - 1;
    }

    public Complex[] coeficientes() {
        return coef;
    }

    /**
     * Método para escribir un polinomio.
     * <p>
     * Construye una cadena de caracteres (String) en la que aparece escrito el
     * polinomio.
     *
     * USO p.escribe()
     */
    public String escribe() {
        Complex[] y = coef;
        int m = y.length;
        String cadena = "( ";
        String renglon = "";
        String aux;

        for (int k = 0; k < m; k++) {
            if ((m > 1) && (y[k].abs() < precision)) {
                continue; // evita escribir sumandos nulos
            }
            aux = "";
            if (k == 0) {
                aux = ""; // término independiente
            } else if (k == 1) {
                aux = " x"; // primera potencia
            } else {
                aux = " x^" + k; // resto de potencias
            }
            if (k == m - 1) {
                aux = aux + " )";
            } else {
                aux = aux + " + ";
            }
            aux = y[k].toString() + aux;
            if ((renglon + aux).length() > 90) {
                cadena = cadena + renglon + "\n  ";
                renglon = aux;
            } else {
                renglon = renglon + aux;
            }
        }
        return cadena + renglon;
    }

    /**
     * Sobrecarga del método toString para que pueda escribirse directamente el
     * polinomio
     */
    public String toString() {
        return escribe();
    }

    /**
     * Método suma para sumar dos polinomios
     *
     * DATOS DE ENTRADA Polinomio q
     *
     * USO p.suma(q) proporciona el Polinomio p+q
     */
    public Polinomio suma(Polinomio q) {
        Complex[] suma;
        int n = coef.length;
        int m = q.coef.length;
        int k = Math.max(n, m);
        suma = new Complex[k];

        if (n < k) {
            for (int i = 0; i < n; i++) {
                suma[i] = coef[i].add(q.coef[i]);
            }
            for (int i = n; i < k; i++) {
                suma[i] = q.coef[i];
            }
        } else {
            for (int i = 0; i < m; i++) {
                suma[i] = coef[i].add(q.coef[i]);
            }
            for (int i = m; i < k; i++) {
                suma[i] = coef[i];
            }
        }
        return new Polinomio(suma);
    }

    /**
     * Sobrecarga el metodo suma como metodo estatico que define el operador
     * binario suma
     *
     * @param p
     * @param q
     * @return p.suma(q)
     *
     * Uso Polinomio.suma(p,q) que equivale a
     */
    public static Polinomio suma(Polinomio p, Polinomio q) {

        return p.suma(q);
    }

    /**
     * Metodo resta para restar dos polinomios
     *
     * DATOS DE ENTRADA Polinomio q
     *
     * USO p.resta(q) proporciona el Polinomio p-q
     */
    public Polinomio resta(Polinomio q) {

        return new Polinomio(suma(q.producto(-1)));
    }

    /**
     * Método para multiplicar dos polinomios
     * <p>
     *
     * DATOS DE ENTRADA
     * <p>
     * Polinomio q
     * <p>
     *
     * USO
     * <p>
     * p.producto(q) proporciona el Polinomio pq
     */
    public Polinomio producto(Polinomio q) {
        Complex[] pq;
        int n = coef.length;
        int m = q.coef.length;
        int k = m + n - 1;
        pq = new Complex[k];

        for (int i = 0; i < k; i++) {
            pq[i] = new Complex(0);
            for (int j = 0; j <= i; j++) {
                if (j >= n) {
                    continue;
                }
                if ((i - j) >= m) {
                    continue;
                }
                pq[i] = pq[i].add(coef[j].mul(q.coef[i - j]));
            }
        }
        return new Polinomio(pq);
    }

    /**
     * Sobrecarga del método producto para multiplicar un polinomio por un
     * escalar
     * <p>
     *
     * DATOS DE ENTRADA
     * <p>
     * Complex z
     * <p>
     *
     * USO
     * <p>
     * p.producto(z) proporciona el Polinomio pq
     */
    public Polinomio producto(Complex z) {
        Complex[] pq;
        int n = coef.length;
        pq = new Complex[n];

        for (int i = 0; i < n; i++) {
            pq[i] = z.mul(coef[i]);
        }
        return new Polinomio(pq);
    }

    /**
     * Sobrecarga del método producto para multiplicar un polinomio por un
     * escalar real
     */
    public Polinomio producto(double x) {
        Complex z = new Complex(x, 0);

        return producto(z);
    }

    /* Metodo para calcular el polinomio derivada */
    public Polinomio derivada() {
        Complex[] dp;
        int n = coef.length;

        if (n == 1) {
            return new Polinomio();
        } else {
            dp = new Complex[n - 1];
            for (int i = 0; i < n - 1; i++) {
                dp[i] = coef[i + 1].mul(new Complex(i + 1));
            }
            return new Polinomio(dp);
        }
    }

    /**
     * Metodo para dividir dos polinomios
     * <p>
     *
     * DATOS DE ENTRADA
     * <p>
     * Polinomio q
     * <p>
     *
     * UTILIZACION
     * <p>
     * p.div(q) proporciona el resto y el cociente de p/q
     */
    public Polinomio[] div(Polinomio divisor) throws ErrorPolinomios {
        Polinomio[] div = new Polinomio[2]; // div[0] contendra el resto
        // div[1] contendra el cociente
        int n = coef.length; // grado del dividendo + 1
        int m = divisor.coef.length; // grado del divisor + 1
        int k = n - m + 1;
        Complex[] aux = new Complex[n];
        Complex[] resto = new Complex[n]; // lista para guardar los restos
        Complex[] cociente = new Complex[k]; // lista para guardar los cocientes

        if (divisor.coef[m - 1].abs() == 0) {
            throw new ErrorPolinomios(); // el divisor es nulo
        }
        if (m > n) {
            div[1] = new Polinomio(); // el cociente es cero
            div[0] = new Polinomio(coef);  // el resto es el dividendo
            return div;
        }
        if (m == 1) {
            div[0] = new Polinomio(); // el resto es 0
            for (int j = 0; j < n; j++) {
                aux[j] = coef[j].div(divisor.coef[0]);
            }
            div[1] = new Polinomio(aux);
            return div;
        }
        for (int j = 0; j <= n - 1; j++) {
            resto[j] = coef[j];
        }
        for (int j = n - m; j >= 0; j--) {
            cociente[j] = resto[m + j - 1].div(divisor.coef[m - 1]);
            resto[m + j - 1] = new Complex(0);
            for (int h = m - 2; h >= 0; h--) {
                resto[j + h]
                        = resto[j + h].sub(divisor.coef[h].mul(cociente[j]));
            }
        }
        div[0] = new Polinomio(resto);
        div[1] = new Polinomio(cociente);
        return div;
    }

    /**
     * Metodo de evaluacion de polinomios con coeficientes y variable compleja
     */
    public Complex eval(Complex z) {

        // falta determinar n=grado del polinomio
        int n = this.grado();

        Complex z2 = new Complex(coef[n]); //para devolver el valor del polinomio en z
        for (int i = n - 1; i >= 0; i--) {
            z2 = z2.mul(z).add(coef[i]);
        }

        return z2;
    }

    /**
     * Sobrecarga del metodo eval para evaluar polinomios reales en variables
     * reales devolviendo el número real con el valor del polinomio
     */
    public double eval(double x) {
        return this.eval(Complex.real(x)).re();
    }

    /**
     * Metodo para construir la lista de los factores de lagrange
     * correspondientes a una lista de numeros reales
     *
     * @param xs
     * @return
     */
    public static Polinomio[] facLagrange(double[] xs) {
        int n = xs.length;
        Polinomio[] factores = new Polinomio[n];
        for (int i = 0; i < factores.length; i++) {
            factores[i] = new Polinomio(1);
            for (int j = 0; j < n; j++) {
                double[] coef = {-xs[j], 1};
                if (j != i) {
                    Polinomio factor = new Polinomio(coef).producto(1. / (xs[i] - xs[j]));
                    factores[i] = factores[i].producto(factor);
                }
            }
        }
        return factores;
    }

    public static Polinomio interpoladorLagrange(double[][] xys) {
        int n = xys.length;
        double[] xs = new double[n];
        double[] ys = new double[n];
        for (int i = 0; i < ys.length; i++) {
            xs[i] = xys[i][0];
            ys[i] = xys[i][1];

        }
        Polinomio[] L = facLagrange(xs);
        Polinomio interpol = new Polinomio();
        for (int i = 0; i < L.length; i++) {
            interpol = interpol.suma(L[i].producto(ys[i]));
        }
        return interpol;
    }

    /* Exception para ser lanzada por los distintos metodos */
    public static class ErrorPolinomios extends Exception {

        ErrorPolinomios() {
            super("Error: polinomios");
        }
    }

    public Complex[] evalDerivadas(Complex x, int n) {

        Complex[] D = new Complex[n + 1];
        int m = grado();

        for (int i = 0; i <= Math.min(n, m); i++) {
            D[i] = new Complex(this.coef[m]);
        }
        int p = Math.min(m, n);
        for (int c = m - 1; c >= 0; c--) {
            D[0] = this.coef[c].add(D[0].mul(x));
            if (c < p) {
                p--;
            }
            for (int i = 1; i <= p; i++) {
                D[i] = D[i - 1].add(D[i].mul(x));
            }

        }
        /* quedaba multiplicar por los factoriales */
        for (int i = 1; i <= Math.min(m, n); i++) {
            for (int j = i; j <= Math.min(m, n); j++) {
                D[j]=D[j].scale(i);    
            }
            
        }

        return D;
    }
     public Polinomio deflacion(Complex z){
        Polinomio pdefl;
        int n=coef.length-1;
        Complex [] defl=new Complex[n];
        defl[n-1]=coef[n];
        for(int k=n-2;k>=0;k--){
            defl[k]=z.mul(defl[k+1]).add(coef[k+1]);
        }
        pdefl=new Polinomio(defl);
        return pdefl;
    }
    
    public Polinomio deflacion(double x){
        Complex z=new Complex(x);
        return deflacion(z);
    }
    
    public static Polinomio Construido(Complex an, Complex[] raices){
        Polinomio pol=new Polinomio(an);
        Polinomio[] monom=new Polinomio[raices.length];
        Complex[] cm = new Complex[2];
        cm[1]=new Complex(1.);
        for (int k=0;k<raices.length;k++){
            cm[0]=raices[k].neg();
            monom[k]=new Polinomio(cm);    
        }
        for(int k=0;k<raices.length;k++){
            pol=pol.producto(monom[k]);
        }
        return pol;
    }
    
    public static Polinomio Construido(double an,double[] raices){
        Polinomio pol;
        Complex[] raicesc=new Complex[raices.length];
        Complex anc=new Complex(an);
        for(int k=0;k<raices.length;k++){
            raicesc[k]=new Complex(raices[k]);
        }
        pol=Construido(anc,raicesc);
        return pol;
    }
          /**
     * implementacion del metodo de Newton para aproximar raices 
     * del polinomio al que se le aplica
     * @param z0  aproximacion inicial
     * @param e   precision admisible para condiciones de parada
     * @param nmax numero maximo de iteraciones
     * @return  devuelve la aproximacion de una raiz del polinomio o un
     * error para su captura
     * @throws auxiliar.Polinomio.ErrorPolinomios 
     */
    public Complex newton(Complex z0, double e, int nmax) throws ErrorPolinomios {
        Complex xi = new Complex(z0);
        int n = grado();
        for (int j = 0; j < nmax; j++) {
            Complex b = new Complex(coef[n]);
            Complex c = new Complex(b);
            for (int k = n - 1; k >= 1; k--) {
                b = b.mul(xi).add(coef[k]);
                c = c.mul(xi).add(b);
            }
            b = b.mul(xi).add(coef[0]);
            if (c.abs() < e) {
                System.out.println("Error newton: derivada (casi) nula");
                throw new ErrorPolinomios();
            }
            Complex h = b.scale(-1).div(c);
            double paso = h.abs();
	     xi = xi.add(h);
            if (b.abs() < e || paso < e) {
                System.out.println("Raiz en z = " + xi + " con error menor que " +
                        (n * paso) + " en " + j + " iteraciones");
                return xi;
            }
        }
        System.out.println("Error newton polinomio: no hay convergencia en " +
                nmax + " iteraciones ");
        throw new ErrorPolinomios();
    }

    
    public double newton(double x,double prec,int nmaxit) throws ErrorPolinomios {
        Complex z=new Complex(x);
        z=newton(z,prec,nmaxit);
        return z.re();
    }
    
    
           /**
     * MÃ©todo que devuelve una lista de las raÃ­ces del polinomio sobre el que se
     * aplica utilizando el mÃ©todo de aproximaciÃ³n de newton en un punto inicial
     * aleatorio, con un nÃºmero mÃ¡ximo de intentos.
     * @param precision precisiÃ³n con la que se quiere acercar  cada raÃ­z.
     * @param nmax nÃºmero mÃ¡ximo de iteraciones permitido al mÃ©todo de Newton.
     * @param nintentos nÃºmero mÃ¡ximo de intentos para encontrar las raÃ­ces.
     * @return Complex[] Lista de las raÃ­ces.
     * @throws auxiliar.Polinomio.ErrorPolinomios 
     */
    public Complex[] buscaRaicesAleatorio(double precision, int nmax, int nintentos)
            throws ErrorPolinomios{
        Polinomio pd=new Polinomio(this);//Para aplicarle las deflaciones.
        int intentos=0;
        Complex[] raices=new Complex[this.grado()];
        double radio=this.radioRaices();
        double precG=Math.pow(precision, 1./3);
        for(int i=0;i<raices.length;i++){
            intentos++;
            try{
                Complex ptoInicial=Complex.polar(radio*Math.random(),2*Math.PI*Math.random());
                raices[i]=pd.newton(ptoInicial, precG, nmax);
                raices[i]=this.newton(raices[i], precision, nmax);
                pd=pd.deflacion(raices[i]);
                if(Math.abs(raices[i].im())>precision){
                    i++;
                    raices[i]=raices[i-1].conj();
                    pd=pd.deflacion(raices[i]);
                }
            }catch(ErrorPolinomios error){
                i=i-1;
            }
            if(intentos>nintentos){
                System.out.println("Sobrepasa el nÃºmero de intentos.");
                throw new ErrorPolinomios();
            }          
        }
        System.out.println("Las "+ this.grado() + " raÃ­ces han sido encontradas en " +
                intentos + " intentos.");
        return raices;
    }
        /**
     * MÃ©todo que devuelve una lista de las raÃ­ces del polinomio CON COEFICIENTES REALES
     * sobre el que se
     * aplica utilizando el mÃ©todo de aproximaciÃ³n de newton en un punto inicial
     * aleatorio, con un nÃºmero mÃ¡ximo de intentos.
     * Si encuentra una raÃ­z que no es real toma tambiÃ©n la conjugada
     * @param precision precisiÃ³n con la que se quiere acercar  cada raÃ­z.
     * @param nmax nÃºmero mÃ¡ximo de iteraciones permitido al mÃ©todo de Newton.
     * @param nintentos nÃºmero mÃ¡ximo de intentos para encontrar las raÃ­ces.
     * @return Complex[] Lista de las raÃ­ces.
     * @throws auxiliar.Polinomio.ErrorPolinomios 
     */
    public Complex[] buscaRaicesAleatorioPCR(double precision, int nmax, int nintentos)
            throws ErrorPolinomios{
        Polinomio pd=new Polinomio(this);//Para aplicarle las deflaciones.
        int intentos=0;
        Complex[] raices=new Complex[this.grado()];
        double radio=this.radioRaices();
        double precG=Math.pow(precision, 1./3);
        for(int i=0;i<raices.length;i++){
            intentos++;
            try{
                Complex ptoInicial=Complex.polar(radio*Math.random(),2*Math.PI*Math.random());
                raices[i]=pd.newton(ptoInicial, precG, nmax);
                raices[i]=this.newton(raices[i], precision, nmax);
                pd=pd.deflacion(raices[i]);
                if(Math.abs(raices[i].im())>precision){
                    i++;
                    raices[i]=raices[i-1].conj();
                    pd=pd.deflacion(raices[i]);
                }
            }catch(ErrorPolinomios error){
                i=i-1;
            }
            if(intentos>nintentos){
                System.out.println("Sobrepasa el nÃºmero de intentos.");
                throw new ErrorPolinomios();
            }          
        }
        System.out.println("Las "+ this.grado() + " raÃ­ces han sido encontradas en " +
                intentos + " intentos.");
        return raices;
    }
    // Ejercicio 3 practica7
    public double radioRaices(){
        double radio=1;
        int n=grado();
        double max=coef[n].abs();
        for (int i = n-1; i >=0; i--) {
            if(coef[i].abs()>max) max=coef[i].abs();          
        }      
        return radio=radio+max/coef[n].abs();
    }
    //Ejercicio2 practica 7
    public static Polinomio[] legengre(int n){
        Polinomio[] L=new Polinomio[n+1];
        L[0]=new Polinomio(1);
        if(n==0)return L;
        double[] ls={0,1};
        L[1]=new Polinomio(ls);
        if(n==1)return L;
        for (int i = 1; i <=n-1; i++) {
            L[i+1]=
L[i].producto(L[1]).producto(2*i+1).resta(L[i-1].producto(i)).producto(1./(i+1));
        }
        return L;
    }
    
    /**
     * M'etodo que devuelve una raiz aproximada del polinomio utilizando el
     * algoritmo de Laguerre, enmpezando en una aproximaci'on inical  -z- ,
     * con una determinada -precision- y en
     * un m'aximo de -nmax- iteraciones,
     *
     * tambien puede devolver un ErrorPolinomios */
    public Complex laguerre(Complex z, double precision, int nmax)
            throws ErrorPolinomios {

        Complex xini = new Complex(z);
        Complex xfin;
        Complex b;
        Complex c;
        Complex d;
        Complex aux1;
        Complex aux2;
        Complex Cmax;
        int n = coef.length;

        if (n == 1) {
            System.out.println("Polinomio constante");
            throw new ErrorPolinomios();
        }
        if (n == 2) {
            Complex r = coef[0].neg().div(coef[1]);
            return r;
        }

        for (int j = 0; j < nmax; j++) {
            b = new Complex(coef[n - 1]);
            c = new Complex(b);
            d = new Complex(b);

            for (int k = n - 2; k >= 2; k--) {
                b = b.mul(xini).add(coef[k]);
                c = c.mul(xini).add(b);
                d = d.mul(xini).add(c);
            }
            d = d.scale(2);
            b = b.mul(xini).add(coef[1]);
            c = c.mul(xini).add(b);
            b = b.mul(xini).add(coef[0]);
            if (b.abs() < precision) {
                System.out.println("Laguerre converge en :" + j + " iteraciones");
                return xini;
            } else {
                aux1 = c.mul(c).scale((n - 1) * (n - 1)).sub(b.mul(d).scale(n * (n - 1)));
                aux2 = new Complex(aux1);
                b = Complex.pow(b.scale(n), -1);
                aux1 = c.add(aux1.sqrt());
                aux2 = c.sub(aux2.sqrt());
                aux1 = b.mul(aux1);
                aux2 = b.mul(aux2);
                if (aux1.abs() >= aux2.abs()) {
                    Cmax = new Complex(aux1);
                } else {
                    Cmax = new Complex(aux2);
                }
            }
            if (Cmax.abs() == 0) {
                System.out.println("Error: primera y segunda derivada nulas");
                throw new ErrorPolinomios();
            } else {
                xfin = xini.sub(Complex.pow(Cmax, -1));
            }
            if (xfin.sub(xini).abs() < precision) {
                System.out.println("Laguerre converge en :" + j + " iteraciones");
                return xini;
            } else {
                xini = xfin;
            }

        }
        System.out.println("No hay convergencia en " + precision + " iteraciones.");
        throw new ErrorPolinomios();
    }
    
        /**
     * M'etodo que devuelve el n'umero de cambios de signo en la lista de numeros
     * reales ignorando los ceros
     *
     */
    public static int cambioSigno(double[] lista) {

        int numCambios = 0;
        int ini = 0;
        int sig = 0;
        int m = lista.length;

        double pini = lista[ini];
        double psig = 0;

        for (int i = 0; ini < m && pini == 0; i++) {
            ini = i + 1;
            pini = lista[ini];
            if (ini == m - 1) {
                return numCambios;
            }
        }

        while (ini < m - 1) {

            sig = ini + 1;

            psig = lista[sig];

            while (sig < m && psig == 0) {
                sig = sig + 1;
                psig = lista[sig];
            }

            if (Math.signum(pini) != Math.signum(psig)) {
                numCambios = numCambios + 1;
            }
            ini = sig;
            pini = psig;


        }



        return numCambios;
    }

        /** M'etodo que devuelve la sucesi'on de polinomios de Sturm
     * asociada al polinomio p
     *
     * Uso:   p.sucesionSturm()
     */
    public Polinomio[] sucesionSturm() {
        int n = coef.length;
        Polinomio[] sturm = new Polinomio[n];
        sturm[0] = new Polinomio(coef);
        if (n == 1) {
            return sturm;
        }
        sturm[1] = sturm[0].derivada();
        if (n == 2) {
            return sturm;
        }

        try {
            for (int k = 2; k < n; k++) {
                Polinomio auxiliar = sturm[k - 2].div(sturm[k - 1])[0];
                if (auxiliar.grado() == 0) {
                    if (auxiliar.coef[0].abs() < precision) {
                        Polinomio[] sturmN = new Polinomio[k - 1];
                        for (int i = 0; i < sturmN.length; i++) {
                            sturmN[i] = sturm[i].div(sturm[k - 1])[1];

                        }
                        return sturmN;
                    }
                    sturm[k] = new Polinomio(auxiliar).producto(-1);
                    Polinomio[] sturmN = new Polinomio[k + 1];
                    for (int i = 0; i < sturmN.length; i++) {
                        sturmN[i] = new Polinomio(sturm[i]);

                    }
                    return sturmN;

                }
                sturm[k] = new Polinomio(auxiliar).producto(-1);

            }

        } catch (ErrorPolinomios errordivision) {

            System.out.println("AAAA");


        }
        return sturm;




    }

    /** Metodo que devuelve una lista con las ra'ices reales del polinomio
     * en el intervalo [a,b].
     *
     *  Utiliza el m'etodo de la bisecci'on de Sturm que subdivide el intervalo
     *  [a,b].
     *
     */
    public double[] bisecSturm(double precision, double a, double b) {

        ArrayList sturmraices = new ArrayList();
        Polinomio[] sucsturm = this.sucesionSturm();
        this.biseccionSturm(precision, sturmraices, sucsturm, a, b);


        int m = sturmraices.size();
        double[] raicesreales = new double[m];

        for (int i = 0; i < m; i++) {
            double[] intervalo = (double[]) sturmraices.get(i);
            System.out.println("a="+intervalo[0]+"  b="+intervalo[1]);
            raicesreales[i] = intervalo[0] + 0.5 * (intervalo[1] - intervalo[0]);
        }
        return raicesreales;

    }

    /** M'etodo que genera la lista "intervalos" con los subintervalos
     *    que proporciona el m'etodo de la bisecci'on de Sturm y
     *    contienen una raiz real con longitud menor que "precision"
     *
     */
    public void biseccionSturm(double precision, ArrayList intervalos,
            Polinomio[] sturm,
            double a, double b) {
        int m = sturm.length;
        double[] sturmb = new double[m];
        double[] sturma = new double[m];
        for (int i = 0; i < m; i++) {
            sturma[i] = sturm[i].eval(a);
            sturmb[i] = sturm[i].eval(b);

        }
        int n = cambioSigno(sturma) - cambioSigno(sturmb);
        if (n > 0) {
            if ((n == 1) && (Math.abs(a - b) < precision)) {
                double[] intraiz = {a, b};
                intervalos.add(intraiz);
                
            } else {
                double c = a + 0.5 * (b - a);
                biseccionSturm(precision, intervalos, sturm, a, c);
                biseccionSturm(precision, intervalos, sturm, c, b);
            }
        }
    }
    /**
     * a partir de una tabla de puntos xy de dimensi´on m con abscisas xy[i][0],
     * devuelva el polinomio de grado n 
     * que mejor aproxima a los puntos (xy[i][0]; xy[i][1]) 
     * en el sentido de que minimiza la suma de los cuadrados de
     * las diferencias p(xy[i][0])- xy[i][1]. 
     * @param xy
     * @param n
     * @return p
     */
    public static Polinomio minimosCuadrados(double[][] xy, int n) 
            throws Matrices.ErrorMatrices{
        int m=xy.length;
        if(m<=(n+1)){
            PolinomioInterpolador p=new PolinomioInterpolador(xy);
            return p;
        }
        double[][] A=new double[m][n+1];
        double[] b=new double[m];
        for (int i = 0; i < m; i++) {
            A[i][0]=1;
            for (int j = 1; j <= n; j++) {
               A[i][j]=A[i][j-1]*xy[i][0];   
            }
            b[i]=xy[i][1];
        }
        double[] coefP=Matrices.solveMinimosCuadradosCh(A, b);
        return new Polinomio(coefP);
    }
    
}
