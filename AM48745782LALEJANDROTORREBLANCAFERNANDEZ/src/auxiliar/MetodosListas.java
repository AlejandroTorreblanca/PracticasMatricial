/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.util.*;

/**
 *
 * @author Alejo Junior
 */
public class MetodosListas {

    public static String toString(double[] lista) {
        String mensaje = "{";
        for (int i = 0; i < lista.length; i++) {
            mensaje = mensaje + lista[i] + ", ";
        }
        return mensaje + "}";
    }

    public static String toString(double[][] lista) {
        String mensaje = "{\n";
        for (int i = 0; i < lista.length; i++) {
            mensaje = mensaje + toString(lista[i]) + "\n";
        }
        return mensaje;
    }

    /* Clase Orden1columna para construir comparadores de los numeros
    *  que están en la primera columna de vectores
     */
    public static class Orden1columna implements Comparator<double[]> {

        @Override
        public int compare(double[] x, double[] y) {

            if (x[0] > y[0]) {
                return +1;
            } else if (x[0] < y[0]) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /* Metodo para reordenar las filas de una tabla de datos
    *  utilizando los números de la primera columna.
     */
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static double[][] Ordenada1columna(double[][] xy) {
        Orden1columna Orden1 = new Orden1columna();

        int n = xy.length;
        List<double[]> lista = new ArrayList<double[]>();

        for (int i = 0; i < xy.length; i++) {
            lista.add(xy[i]);
        }
        Collections.sort(lista, Orden1);
        double[][] ordenada = new double[n][];
        for (int i = 0; i < ordenada.length; i++) {
            ordenada[i] = lista.get(i);

        }
        return ordenada;

    }
}
