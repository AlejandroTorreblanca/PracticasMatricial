/*
 * PanelDibujo.java
 *
 * Creado el 14 de mayo de 2006, 19:53
 * modificado el 17 de octubre de 2010
 */
package auxiliar;

/* Los siguientes paquetes se necesitan para usar el potencial grafico de JAVA */
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

/* Este paquete se necesita para hacer uso del objeto ArryList 
 * (coleccion ordenada de objetos). 
 */
import java.util.*;

/**
 * El objetivo de esta clase es dise~nar una ventana en la que deseamos pintar
 * unos ejes coordenados y una lista de curvas planas con sus colores
 * respectivos. Cada curva estara representada por una familia finita de puntos,
 * y por "pintar la curva" se entiende conectar puntos consecutivos mediante
 * segmentos. Como no sabemos a priori cuantas curvas tenemos que pintar haremos
 * uso de dos objetos ArrayList, listaCurvas y listaColoresCurvas: cada componente
 * del ArrayList listaCurvas sera una curva (es decir, una matriz bidimensional nx2)
 * y la componente correspondiente del ArrayList listaColoresCurvas el color (una
 * variable Color ) con el que la pintaremos.
 *
 * @author Victor Jimenez y Antonio Pallares
 *
 * METODOS NUMERICOS
 * LICENCIATURA y GRADO EN MATEMATICAS
 * UNIVERSIDAD DE MURCIA
 *
 */
public class PanelDibujo extends JFrame {

    ArrayList listaCurvas = new ArrayList();
    ArrayList listaColoresCurvas = new ArrayList();
    ArrayList listaPuntos = new ArrayList();
    ArrayList listaColoresPuntos = new ArrayList();
    ArrayList listaGruesoPuntos = new ArrayList();
    ArrayList listaEtiquetas = new ArrayList();
    ArrayList listaEtiquetasPosicion = new ArrayList();
    ArrayList listaEtiquetasColor = new ArrayList();
    ArrayList listaArcos = new ArrayList();
    ArrayList listaArcosColor = new ArrayList();
    
    /**
     * En las variables siguientes guardaremos las coordenadas enteras <xsi,ysi> 
     * del pixel situado en el extremo superior izquierdo de la ventana y 
     * las coordenadas <xid,yid> del pixel situado en el extremos inferior 
     * derecho de la ventana. Estas coordenadas por defecto podran 
     * modificarse luego. Los  pixels se miden con dos coordenadas enteras 
     * que aumentan, respectivamente, segun nos desplazamos a la derecha 
     * y hacia abajo (no hacia arriba, como ocurre con las coordenadas usuales).
     * El pixel correspondiente al extremo superior izquierdo de la pantalla 
     * del ordenador es <0,0>.
     *
     * El rango de las coordenadas de los pixeles depende de la resolucion grafica de la pantalla.
     * Asi en una pantalla con resolucion de 800x600 puntos las coordenadas horizontales varian entre 0
     * 800 puntos de izquierda a derecha y las verticales entre 0 y 600 puntos de arriba a abajo.
     * 
     * Otra observacion importante es que el tama~no de las ventanas en la pantalla depende de la resolucion
     * de la misma. Una ventana de 800x600 puntos llenara toda la pantalla si esta tiene esa resolucion y solo
     * parte de la misma si la resolucion es mayor.
     */
    int xsi = 0;
    int ysi = 0;
    int xid = 800;
    int yid = 600;
    /**
     * Las variables siguientes nos dicen las dimensiones en coordenadas reales de la ventana y establecen
     * un sistema de referencia cartesiano, para ello se dimensionan los ejes 
     * coordenados que utilizaremos por defecto: de -5 a 5 para el eje x, 
     * de -4 a 4 para el eje y; elegiremos (0,0) como punto de cruce de ambos 
     * ejes (centro de coordenadas), y fijaremos en 1 el tama~no de la cuadricula tanto en horizontal 
     * como en vertical. Al fijar ejescoordenados como false estamos diciendo 
     * que, salvo orden expresa, los ejes no se pintaran en la ventana.
     */
    double eix = - 5;
    double esx = 5;
    double eiy = - 4;
    double esy = 4;
    double cx = 0;
    double cy = 0;
    double mx = 1;
    double my = 1;
    boolean ejescoordenados = false;

    /** 
     * El constructor de la clase PanelDibujo determina, por defecto, el 
     * titulo de la ventana ("Dibujo de curvas en el plano"), las coordenadas 
     * del pixel situado en el extremo izquierdo superior de la vectana (<0,0>)
     * la anchura de la ventana (800) y la altura de la ventana (600). 
     * La instruccion show() hace que la ventana se abra cuando instanciamos 
     * desde nuestra aplicacion un objeto PanelDibujo.
     *
     * Los metodos utilizados son heredados de JFrame
     */
    public PanelDibujo() {
        super("Dibujo de curvas en el plano");
        setBounds(0, 0, 800, 600);
        setVisible(true);
    }

    /**
     * Esta sobrecarga del constructor de PanelDibujo nos permite elegir 
     * el titulo de la ventana.
     */
    public PanelDibujo(String etiqueta) {
        super(etiqueta);
        setBounds(0, 0, 800, 600);
        setVisible(true);
    }
    //acaba el constructor de PanelDibujo (primera sobrecarga)

    /**
     * Esta sobrecarga del constructor de PanelDibujo nos permite elegir el 
     * titulo de la ventana su posicion y sus dimensiones en pixeles (coordenadas
     * enteras.
     */
    public PanelDibujo(String etiqueta, int xsupizda, int ysupizda,
            int xinfdcha, int yinfdcha) {
        super(etiqueta);
        setBounds(xsupizda, ysupizda, xinfdcha - xsupizda, yinfdcha - ysupizda);
        xsi = xsupizda;
        ysi = ysupizda;
        xid = xinfdcha;
        yid = yinfdcha;
        setVisible(true);
    } // acaba el constructor de PanelDibujo (segunda sobrecarga)

    /**
     * El metodo addEjesCoordenados permite modificar el valor de la variable
     * ejescoordenados para que se puedan pintar los ejes.
     */
    public void addEjesCoordenados(boolean dibuja) {
        ejescoordenados = dibuja;
    } // acaba el metodo addEjesCoordenados

    /**
     * Esta sobrecarga del metodo addEjesCoordenados permite ademas elegir los
     * extremos de los ejes.
     */
    public void addEjesCoordenados(boolean dibuja, double extinfx,
            double extsupx, double extinfy, double extsupy) {
        ejescoordenados = dibuja;
        eix = extinfx;
        esx = extsupx;
        eiy = extinfy;
        esy = extsupy;
        cx = (extinfx + extsupx) / 2;
        cy = (extinfy + extsupy) / 2;
        mx = (extsupx - extinfx) / 10;
        my = (extsupy - extinfy) / 8;
    } // acaba el metodo addEjesCoordenados (primera sobrecarga)

    /**
     * Esta sobrecarga del metodo addEjesCoordenados permite ademas elegir 
     * el punto de cruce de los ejes.
     */
    public void addEjesCoordenados(boolean dibuja, double extinfx,
            double extsupx, double extinfy, double extsupy, double centrox,
            double centroy) {
        ejescoordenados = dibuja;
        eix = extinfx;
        esx = extsupx;
        eiy = extinfy;
        esy = extsupy;
        cx = centrox;
        cy = centroy;
        mx = (extsupx - extinfx) / 10;
        my = (extsupy - extinfy) / 8;
    } // acaba el metodo addEjesCoordenados (segunda sobrecarga)

    /**
     * Esta sobrecarga del metodo addEjesCoordenados permite ademas elegir 
     * el tama~no de la cuadricula.
     */
    public void addEjesCoordenados(boolean dibuja, double extinfx,
            double extsupx, double extinfy, double extsupy, double centrox,
            double centroy, double mallax, double mallay) {
        ejescoordenados = dibuja;
        eix = extinfx;
        esx = extsupx;
        eiy = extinfy;
        esy = extsupy;
        cx = centrox;
        cy = centroy;
        mx = mallax;
        my = mallay;
    } // acaba el metodo addEjesCoordenados (tercera sobrecarga)

    /**
     * Los metodos pixelx y pixely calculan el pixel que corresponde a un punto
     * de coordenadas (x,y). Recordad que el pixel <0,0> corresponde a la 
     * esquina superior izquierda de la ventana, y que aumentan segun nos 
     * desplazamos a la derecha y hacia abajo.
     */
    public int pixelx(double x) {
        return (int) Math.rint((xid - xsi) * (x - eix) / (esx - eix));
    } // acaba el metodo pixelx

    public int pixely(double y) {
        return (int) Math.rint((yid - ysi) * (esy - y) / (esy - eiy));
    } // acaba el metodo pixely

    /**
     * Un objeto Graphics es, por asi decir, un "pincel" con el que se pueden 
     * dibujar cosas diversas. En el metodo dibujaEjes usamos los siguientes 
     * metodos asociados a este objeto:
     * 
     * setColor elige el color con el que vamos a pintar;
     * drawLine(p1, p2, q1, q2) dibuja la recta que conecta los pixels <p1,p2> y
     *    <q1,q2>;
     * drawString("xxx", p1, p2) situa la cadena de caracteres xxx en el pixel
     *    <p1,p2>.
     * El objetivo del metodo dibujaEjes es dibujar unos ejes coordenados con el
     * objeto Graphics g a partir de la informacion recogida en 
     * addEjesCoordenados. Notese que combinando Double.toString (transforma 
     * una variable Double en String) y Math.rint (que redondea un valor double 
     * al valor int mas cercano) conseguimos que los numeros con los que 
     * etiquetamos los ejes tengan a lo sumo tres cifras decimales.
     */
    public void dibujaEjes(Graphics g) {

        if (ejescoordenados == true) {
            g.setColor(Color.black);
            g.drawLine(pixelx(eix), pixely(cy), pixelx(esx), pixely(cy));
            g.drawLine(pixelx(cx), pixely(eiy), pixelx(cx), pixely(esy));
            for (int j = 1; cx + j * mx <= esx; j++) {
                g.drawLine(pixelx(cx + j * mx), pixely(cy) - 8,
                        pixelx(cx + j * mx), pixely(cy) + 8);
                g.drawString(Double.toString(
                        Math.rint(1000 * (cx + j * mx)) / 1000),
                        pixelx(cx + j * mx), pixely(cy) + 24);
            }
            for (int j = - 1; cx + j * mx >= eix; j--) {
                g.drawLine(pixelx(cx + j * mx), pixely(cy) - 8,
                        pixelx(cx + j * mx), pixely(cy) + 8);
                g.drawString(Double.toString(
                        Math.rint(1000 * (cx + j * mx)) / 1000),
                        pixelx(cx + j * mx), pixely(cy) + 24);
            }
            for (int j = 1; cy + j * my <= esy; j++) {
                g.drawLine(pixelx(cx) - 8, pixely(cy + j * my),
                        pixelx(cx) + 8, pixely(cy + j * my));
                g.drawString(Double.toString(
                        Math.rint(1000 * (cy + j * my)) / 1000),
                        pixelx(cx) - 40, pixely(cy + j * my));
            }
            for (int j = - 1; cy + j * my >= eiy; j--) {
                g.drawLine(pixelx(cx) - 8, pixely(cy + j * my),
                        pixelx(cx) + 8, pixely(cy + j * my));
                g.drawString(Double.toString(
                        Math.rint(1000 * (cy + j * my)) / 1000),
                        pixelx(cx) - 40, pixely(cy + j * my));
            }
        }
    } // acaba el metodo dibujaEjes

    /**
     * El metodo addCurva a~nade al ArrayList listaCurvas la familia de 
     * puntos xy[][] y al ArrayList listaColoresCurvas el Color que hemos 
     * elegido para pintar la poligonal que los conecta.
     */
    public void addCurva(Color color, double[][] xy) {
        listaCurvas.add(xy);
        listaColoresCurvas.add(color);
    } // acaba el metodo addCurva

    /**
     * Esta sobrecarga del metodo addCurva calcula una malla de n puntos de la
     * grafica de la funcion f en el intervalo [extinf,extsup] y luego la a~nade 
     * al ArrayList listaCurvas (y al ArrayList listaColoresCurvas el color elegido).
     */
    public void addCurva(Color color, Funcion f, int n, double extinf,
            double extsup) {
        double[][] xy = MetodosFunciones.tablaGrafica(f, n, extinf, extsup);
        listaCurvas.add(xy);
        listaColoresCurvas.add(color);

    }
    public void addCurva(Color color, Funcion x,Funcion y, int n, double tinf,
            double tsup) {
        double[][] xy = new double [n][2];
        double paso=(tsup-tinf)/(n-1);
        for (int i = 0; i < n; i++) {
            double t =tinf+paso*i;
            xy [i][0]=x.eval(t);
            xy [i][1]=y.eval(t);
            
        }
        listaCurvas.add(xy);
        listaColoresCurvas.add(color);

    }
// acaba el metodo addCurva (sobrecarga)

    /**
     * El metodo removeCurvas borra todos los elementos de listaCurvas y
     * listaColoresCurvas.
     */
    public void removeCurvas() {
        listaCurvas.clear();
        listaColoresCurvas.clear();
    } // acaba el metodo removeCurvas

    /**
     * El metodo removeCurva borra los elementos n (es decir, los n+1-esimos) 
     * de listaCurvas y listaColoresCurvas. No se hace uso de el en la 
     * aplicacion TestGraficos.
     */
    public void removeCurva(int n) {
        listaCurvas.remove(n);
        listaColoresCurvas.remove(n);
    } // acaba el metodo removeCurva

    /**
     * El metodo dibujaCurvas pinta con el objeto Graphics g las poligonales
     * correspondientes a las listas de puntos del ArrayList listaCurvas y con los
     * colores de listaColoresCurvas. Notese la sintaxis precisa que permite 
     * extraer el contenido de los vectores para poder manipularlo.
     */
    public void dibujaCurvas(Graphics g) {
        double[][] xy;
        Color color;
        for (int i = 0, n = listaCurvas.size(); i < n; i++) {
            xy = (double[][]) listaCurvas.get(i);
            color = (Color) listaColoresCurvas.get(i);
            g.setColor(color);
            for (int j = 1; j < xy.length; j++) {
                g.drawLine(pixelx(xy[j - 1][0]), pixely(xy[j - 1][1]),
                        pixelx(xy[j][0]), pixely(xy[j][1])); ArrayList listaEtiquetas = new ArrayList();
    ArrayList listaEtiquetasPosicion = new ArrayList();
    ArrayList listaEtiquetasColor = new ArrayList();

    ArrayList listaArcos = new ArrayList();
    ArrayList listaArcosColor = new ArrayList();
    
            }
        }
    } // acaba el metodo dibujaCurvas
    // Para incluir en PanelDibujo.java

// en la declaracion de variables junto a las listas de Curvas y Puntos

    
 //  Antes del metodo paint incluir
 
     /**
     * El metodo addPunto a~nade al ArrayList listaPuntos el punto xy
     * al ArrayList listaColoresPuntos el Color que hemos 
     * elegido para pintar el punto que los conecta, y a la lista listaGruesoPuntos
     * el grosor.
     */
    public void addPunto(Color color, double[] xy, int grosor) {
        listaPuntos.add(xy);
        listaColoresPuntos.add(color);
        listaGruesoPuntos.add(grosor);
    }

    public void addPunto(Color color, double[] xy) {
        addPunto(color, xy, 3);
    }

    public void addListaPuntos(Color color, double[][] lista, int grosor) {
        for (int i = 0; i < lista.length; i++) {
            addPunto(color, lista[i], grosor);

        }
    }   
    
    public void removePuntos() {
        listaPuntos.clear();
        listaColoresPuntos.clear();
        listaGruesoPuntos.clear();
    }

   public void removePuntos(int n) {
        listaPuntos.remove(n);
        listaColoresPuntos.remove(n);
        listaGruesoPuntos.remove(n);
    }
    
    public void dibujaPuntos(Graphics g) {
        double[] punto;
        Color color;
        int grosor;
        for (int i = 0, n = listaPuntos.size(); i < n; i++) {
            punto = (double[]) listaPuntos.get(i);
            color = (Color) listaColoresPuntos.get(i);
            grosor = (Integer) listaGruesoPuntos.get(i);
            g.setColor(color);
            g.fillArc(pixelx(punto[0]) - grosor, pixely(punto[1]) - grosor,
                    2 * grosor, 2 * grosor, 0, 360);

        }
    }
// UNA ETIQUETA EN NUESTROS PANELES DE DIBUJO ES UNA CADENA DE CARACTERES "String"
// posicionada en un punto de coordenadas "reales" posicion={x,y} QUE SE ESCRIBIRA
// USANDO UN color de la clase "Color".

    public void addEtiqueta(double[] posicion, String etiqueta, Color color) {
        listaEtiquetas.add(etiqueta);
        listaEtiquetasPosicion.add(posicion);
        listaEtiquetasColor.add(color);
    }

    public void removeEtiquetas() {
        listaEtiquetas.clear();
        listaEtiquetasPosicion.clear();
        listaEtiquetasColor.clear();
    }

    public void removeEtiqueta(int n) {
        listaEtiquetas.remove(n);
        listaEtiquetasPosicion.remove(n);
        listaEtiquetasColor.remove(n);
    }

    /**
     * El metodo dibujaEtiquetas escribe con el objeto Graphics g las etiquetas
     * correspondientes a la lista listaParametricas y con los
     * colores de listaEtiquetasColor en las posiciones de la lista
     * listEtiquetasPosicion. Notese la sintaxis precisa que permite
     * extraer el contenido de los vectores para poder manipularlo.
     */
    public void dibujaEtiquetas(Graphics g) {
        double[] xy;
        Color color;
        String etiqueta;

        for (int i = 0, n = listaEtiquetas.size(); i < n; i++) {
            xy = (double[]) listaEtiquetasPosicion.get(i);
            color = (Color) listaEtiquetasColor.get(i);
            etiqueta = (String) listaEtiquetas.get(i);
            for (int j = 1; j < xy.length; j++) {
                g.setColor(color);
                g.drawString(etiqueta, pixelx(xy[0]), pixely(xy[1]));
            }
        }
    }

   // UN Arco EN NUESTROS PANELES DE DIBUJO ES lista de numeros arco[]
   // (arco[0],arco[1]) son las coordenadas del centro de la circunferencia
   //  arco[2] es el radio
   //  arco[3] y arco[4] son los angulos inicial y final del arco (en grados sexagesimales)
   // USANDO UN color de la clase "Color".
    public void addArco(double[] arco, Color color) {
        listaArcos.add(arco);
        listaArcosColor.add(color);
    }

    public void removeArcos() {
        listaArcos.clear();
        listaArcosColor.clear();
    }

    public void removeArco(int n) {
        listaArcos.remove(n);
        listaArcosColor.remove(n);
    }

    public void dibujaArcos(Graphics g) {
        double[] xy;
        Color color;
        for (int i = 0, n = listaArcos.size(); i < n; i++) {
            xy = (double[]) listaArcos.get(i);
            color = (Color) listaArcosColor.get(i);
            g.setColor(color);
            g.fillArc(pixelx(xy[0]) - pixelx(xy[2]) + pixelx(0) + 1, pixely(xy[1]) - pixely(-xy[2]) + pixely(0) - 1,
                    2 * (pixelx(xy[2]) - pixelx(0)) - 1,
                    2 * (pixely(-xy[2]) - pixely(0)) - 1, (int) xy[3], (int) xy[4]);
        }
    } 
    
// Por ultimo sustituye el método paint por este nuevo
/**
     * Cada vez que el metodo paint sea invocado (mediante repaint) desde la
     * aplicacion limpiara la ventana, pintara los ejes coordenados (salvo 
     * que la variable ejescoordenados tenga el valor false) y pintara todas 
     * las curvas almacenadas en listaCurvas con los colores de 
     * listaColoresCurvas.
     */
    public void paint(Graphics g) {
        g.clearRect(0, 0, xid - xsi, yid - ysi);
        dibujaEjes(g);
        dibujaCurvas(g);
        dibujaArcos(g);
        dibujaPuntos(g);
        dibujaEtiquetas(g);
    } // acaba el metodo paint    
    

    
    
    
    
    
    
} // acaba la clase PanelDibujo

