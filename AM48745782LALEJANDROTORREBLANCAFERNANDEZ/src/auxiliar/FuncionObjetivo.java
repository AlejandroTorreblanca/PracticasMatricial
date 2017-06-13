/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

/**
 *
 * @author fenix
 */
public interface FuncionObjetivo {
    public double objetivo(double[] x);
    public double[] gradiente(double[] x);
    
}
