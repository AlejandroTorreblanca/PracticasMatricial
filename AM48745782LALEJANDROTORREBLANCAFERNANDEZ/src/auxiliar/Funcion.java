/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

/** Esta "interface" Funcion es un compromiso de que cualquier
 *  "clase" que la implemente va a tener un metodo
 *
 *  public double eval(double x)
 *
 *  para evaluar las funciones que se puedan instanciar en el real x
 *    f.eval(x)  devolvera f(x).
 *
 *
 * @author antoniopallaresruiz
 */




public interface Funcion {

   /**
    *  public double eval(double x)
    *
    *  para evaluar las funciones en el real x
    *
    * f.eval(x)  devolvera f(x).
    */
    public double eval(double x);

}
