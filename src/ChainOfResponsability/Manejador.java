/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfResponsability;

/**
 *
 * @author CltControl
 */
public interface Manejador {

    //Manejador manejador (int n, double denominacion);
    void setNext(Manejador m);
    boolean retirar(double monto);
    boolean depositar(int n, double denominacion);
    

}
