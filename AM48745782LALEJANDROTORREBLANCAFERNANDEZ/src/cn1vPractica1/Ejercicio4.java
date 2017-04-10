/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author fenix
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int precision=4;
        MathContext mc=new MathContext(precision,RoundingMode.HALF_EVEN);//Redondeo a la paridad, precision en digitos
        System.out.println("Math.PI="+Math.PI);
        BigDecimal a=new BigDecimal(Math.PI,mc);
        System.out.println("BigDecimal a ="+a);
        System.out.println("Math.PI - BigDecimal a= "+(Math.PI - a.doubleValue()));//double value para paasar de bigdecimal a double
        //Apartado 1
        BigDecimal x=new BigDecimal(0.6688,mc);
        BigDecimal y=new BigDecimal(0.3334,mc);
        BigDecimal suma=x.add(y,mc);
        System.out.println(x+" + "+y+" = "+suma);
        double sumareal=0.6688+0.3334;
        double errorAbs=Math.abs(sumareal-suma.doubleValue());
        double errorRel=(errorAbs/sumareal);
        System.out.println("error relativo = "+errorRel);
        System.out.println("error absoluto = "+errorAbs);
        //apartado 2
        a =new BigDecimal(55560,mc);
        BigDecimal b= new BigDecimal(5555,mc);
        BigDecimal c=new BigDecimal(5555,mc);
        BigDecimal suma1=(a.add(b,mc)).add(c,mc);
        BigDecimal suma2= a.add((b.add(c,mc)),mc);
        System.out.println("(a+b)+c = "+suma1);
        System.out.println("a+(b+c) = "+suma2);
        System.out.println("suma1 == suam2 = "+(suma2.compareTo(suma1)==0));//0 si son iguales,-1 si suma 1 menor que suma 2 y al reves
        //apartado 3 d=9999, e=9998
        BigDecimal d=new BigDecimal(9999,mc);
        BigDecimal e=new BigDecimal(9998,mc);
        BigDecimal division1 = d.divide(d.multiply(d,mc),mc);
        BigDecimal division2= e.divide(e.multiply(e,mc),mc);
        System.out.println("1/d = "+division1);
        System.out.println("1/e = "+division2);
     System.out.println("division1 == division2 = "+(division2.compareTo(division1)==0));
    //Apartado 4
    suma1=(a.multiply(b.add(c,mc),mc));
    suma2= (a.multiply(b,mc)).add(a.multiply(c,mc),mc);
    System.out.println("a*(b+c) = "+suma1);
    System.out.println("(a*b)+(a*c) = "+suma2);
    System.out.println("suma1 == suam2 = "+(suma2.compareTo(suma1)==0));
    //Apartado 5
    //Hacer con la raiz cuadrada de abajo recordar
//Ejercicio 5
 
//Apartado 3
   precision=1000;
   mc=new MathContext(precision,RoundingMode.HALF_EVEN);
    BigDecimal dos=new BigDecimal(2,mc);
    BigDecimal raiz2=sqrt(dos,mc);
        System.out.println("raiz2 = "+raiz2);
        System.out.println( "(2== raiz2*raiz2)"+(dos.compareTo(raiz2.multiply(raiz2,mc))==0));
    }
    public static BigDecimal sqrt(BigDecimal a, MathContext mc){
        int cont=0;
        BigDecimal unmedio=new BigDecimal(0.5,mc);
        BigDecimal raiz=new BigDecimal(a.toString());
        BigDecimal oldRaiz=new BigDecimal(0,mc);
        while((raiz.compareTo(oldRaiz)!=0)&&(cont < 1000)){
        cont++;
        oldRaiz=new BigDecimal(raiz.toString());
        raiz = unmedio.multiply(raiz.add(a.divide(raiz,mc),mc),mc);
    }
        System.out.println("numero de etapas en hacerlo = "+cont);
        return raiz;
    }
    
}
