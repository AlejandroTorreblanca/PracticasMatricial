/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica1;

/**
 *
 * @author fenix
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Al compilar esta clase aparecen errores sobre tipos de n’umeros.
// C’omo se corrigen?
        float x;
        int y;
        double z;
        x = 3.5f;//hay que ponerlo como float y por eso ponemos f
        y = (int)x;//Hacemos casting a enteros del float
        z = x;
        System.out.println("y=" + y);
        System.out.println("z=" + z);
    }

}
