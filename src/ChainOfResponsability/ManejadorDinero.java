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

public class ManejadorDinero implements Manejador{
    
    private Manejador next;
    protected int cantidad;
    protected double denominacion;

    
    public ManejadorDinero(int n, double denominacion) {
        this.cantidad = n;
        this.denominacion = denominacion;
    }

    
    @Override
    public void setNext(Manejador manejador) {
        this.next = manejador;
    }

    @Override
    public boolean retirar(double monto) {
        if(monto>0)
            return true;
        else 
            return false;
        
    }

    @Override
    public boolean depositar(int n, double denominacion) {
        return false; 
    }

    public double getDenominacion() {
        return denominacion;
    }

    public int getCantidad() {
        return cantidad;
    }
    public Manejador getNext() {
        return next;
    }
    
}
