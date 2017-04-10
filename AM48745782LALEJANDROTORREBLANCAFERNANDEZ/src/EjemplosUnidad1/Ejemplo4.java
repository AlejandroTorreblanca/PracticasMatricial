/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemplosUnidad1;

/**
 *
 * @author apall
 */
public class Ejemplo4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double y = Math.E - 1;
        for (int i = 0; i < 200; i++) {
            System.out.println(" n = " + i + " y(n) = " + y);
            y = (i + 1) * y - 1;

        }

    }
}
