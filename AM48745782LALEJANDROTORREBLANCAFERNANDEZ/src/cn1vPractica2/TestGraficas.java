/*
 * TestGraficas.java
 *
 * Creado el 14 de mayo de 2006, 20:40
 * modificado el 14 de octubre de 2009
 */
package cn1vPractica2;

/* Los siguientes paquetes se necesitan para usar el potencial grafico de JAVA */
import auxiliar.*;
//import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 * El objetivo de esta aplicacion es usar un panel de dibujo (instanciando
 * un objeto de la clase PanelDibujo) para pintar la grafica de la funcion seno,
 * y una elipse centrada en el origen con
 * semiejes tambien definidos por el usuario junto a las circunferencias inscrita
 * y circunscrita en la misma.
 *
 * @author Victor Jimenez
 *         Antonio Pallares
 *
 * METODOS NUMERICOS
 * LICENCIATURA DE MATEMATICAS
 * UNIVERSIDAD DE MURCIA
 */
public class TestGraficas {

    public static void main(String args[]) {

        /*
         * Creamos el objeto pd de la clase PanelDibujo y el objeto 
         * seno de la clase Funcion1 y f de la clase Funcion2 (ambas 
         * implementan la interfaz Funcion). Definimos unas variables 
         * auxiliares que necesitaremos en el programa. Durante la ejecucion 
         * de la aplicacion, tras creal el panel de dibujo pd, se abre inmediatamente en 
         * pantalla una ventana en la que iremos haciendo nuestros dibujos.
         */

        PanelDibujo pd = new PanelDibujo();


        /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */


        pd.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });





        /*
         * En este punto de la ejecucion de la aplicacion aparece una ventana 
         * vacia con el titulo "Dibujo de curvas en el plano".
         */

        Sin seno = new Sin();
        double a;
        double b;
        int n;
        double[][] xy0;
        double[][] xy1;
        double[][] xy2;

        /*
         * El metodo addEjesCoordenados hace que la ventana "sepa" que deben 
         * pintarse unos ejes coordenados; con repaint se "da la orden" de que 
         * se pinten. La primera instruccion no tiene nada que ver con las 
         * siguientes: simplemente abre en pantalla una ventana independiente 
         * de la anterior, que nos manda un mensaje informativo y se cierra 
         * tras pulsar OK.
         */

        JOptionPane.showMessageDialog(null, "Pulsa OK para empezar");
        pd.addEjesCoordenados(true);
        pd.repaint();

        /**
         * El siguiente bloque de instrucciones sirve para algunos datos 
         * que necesita la aplicacion (los extremos izquierdo a y derecho b del 
         * intervalo en el que queremos representar la funcion seno y el numero
         * n de puntos que vamos a utilizar). Los metodos verificarDouble y
         * verificarInt (construidos al final de la clase) reciben los
         * datos que necesitamos como String y los traducen a double e int
         * respectivamente.
         */
        a = 0;
        b = 0;
        n = 0;
        while ((b <= a) || (n < 2)) {
            JOptionPane.showMessageDialog(null,
                    "Introduce datos para pintar la funcion seno");
            a = verificarDouble("Extremo izquierdo del intervalo:");
            b = verificarDouble("Extremo derecho del intervalo:");
            n = verificarInt("Numero de puntos de la grafica:");
            if ((b <= a) || (n < 2)) {
                JOptionPane.showMessageDialog(null,
                        "Error: datos no consistentes");
            }
        }

        /**
         * La siguiente instruccion "envia" la funcion seno a la ventana
         * para que la almacene y pinte posteriormente, de color verde, 
         * la poligonal que conecta a los n puntos de su grafica cuyas 
         * primeras coordenadas estan igualmente espaciados en el 
         * intervalo [a, b]. Si esta cantidad de puntos es suficientemente 
         * grande, la poligonal se asemejar� bastante a la grafica de la 
         * funcion seno en dicho intervalo.
         */
        JOptionPane.showMessageDialog(null,
                "Pulsa OK para pintar la funcion seno");
        pd.addCurva(Color.green, seno, n, a, b);
        pd.repaint();




        /**
         * A continuacion mandamos un mensaje a la ventana para que borre
         * la curva seno pintada.
         */
        JOptionPane.showMessageDialog(null, "Pulsa OK para borrar la curva");
        pd.removeCurvas();
        pd.repaint();

        /* Pedimos los datos necesarios para pintar la elipse */
        a = 0;
        b = 0;
        n = 0;
        while ((a <= 0) || (b <= 0) || (n < 2)) {
            JOptionPane.showMessageDialog(null,
                    "Introduce datos para pintar la elipse y las "
                    + "circunferencias");
            a = verificarDouble("Semieje horizontal de la elipse");
            b = verificarDouble("Semieje vertical de la elipse:");
            n = verificarInt("Numero de puntos de cada grafica:");
            if ((a <= 0) || (b <= 0) || (n < 2)) {
                JOptionPane.showMessageDialog(null,
                        "Error: datos no consistentes");
            }
        }

        /**
         * En la matriz xy0 guardamos una lista de n puntos de la elipse
         * de semiejes a y b centrada en el origen de coordenadas, y en las
         * matrices xy1 y xy2 hacemos lo propio para las circunferencias de
         * radios a y b. Para ello usamos las parametrizaciones habituales, 
         * espaciando los puntos calculados en funcion del paramatro t.
         */
        xy0 = new double[n][2];
        xy1 = new double[n][2];
        xy2 = new double[n][2];
        for (int t = 0; t < n; t++) {
            xy0[t][0] = a * Math.cos(2 * Math.PI * t / (n - 1));
            xy0[t][1] = b * Math.sin(2 * Math.PI * t / (n - 1));
            xy1[t][0] = a * Math.cos(2 * Math.PI * t / (n - 1));
            xy1[t][1] = a * Math.sin(2 * Math.PI * t / (n - 1));
            xy2[t][0] = b * Math.cos(2 * Math.PI * t / (n - 1));
            xy2[t][1] = b * Math.sin(2 * Math.PI * t / (n - 1));
        }

        /**
         * Se envian las listas de puntos a la ventana indicando los colores que 
         * queremos usar para pintarlas.
         */
        pd.addCurva(Color.red, xy0);
        pd.addCurva(Color.blue, xy1);
        pd.addCurva(Color.black, xy2);

        /*
         * La instruccion siguiente hace que se pinten en la ventana las curvas
         * que se le han mandado.
         */

        pd.repaint();

        /*
         * Finalmente enviamos una orden para que la ventana abierta se 
         * pueda cerrar correctamente y asi detener la aplicacion.
         */

        JOptionPane.showMessageDialog(null,
                "Pulsa OK y cierra la ventana para acabar la aplicacion");


    } // acaba el metodo main de la aplicacion TestGraficas

    /**
     * El metodo verificarInt recibe la respuesta a la "pregunta"
     * como un String  (cadena de caracteres) y los traduce a un
     * numero entero int.
     */
    public static int verificarInt(String pregunta) {
        boolean datosErroneos = true;
        String aux;
        int n = 0;

        while (datosErroneos) {
            try {
                datosErroneos = false;
                aux = JOptionPane.showInputDialog(pregunta);
                n = Integer.parseInt(aux);
            } catch (Throwable errDatos) {
                datosErroneos = true;
                JOptionPane.showMessageDialog(null,
                        "Error: el dato introducido no es entero");
            }
        }
        return n;
    }

    /**
     * El metodo verificarDouble recibe la respuesta a la "pregunta"
     * como un String  (cadena de caracteres) y los traduce a un
     * numero real double.
     */
    public static double verificarDouble(String pregunta) {
        boolean datosErroneos = true;
        String aux;
        double a = 0;

        while (datosErroneos) {
            try {
                datosErroneos = false;
                aux = JOptionPane.showInputDialog(pregunta);
                a = Double.parseDouble(aux);
            } catch (Throwable errDatos) {
                datosErroneos = true;
                JOptionPane.showMessageDialog(null,
                        "Error: el dato introducido no es real");
            }
        }
        return a;
    }

    public static class Sin implements Funcion {
        public Sin(){
            
        }
        @Override
        public double eval(double x){
            return Math.sin(x);
        }
    }

    
} // acaba la aplicacion TestGraficas

