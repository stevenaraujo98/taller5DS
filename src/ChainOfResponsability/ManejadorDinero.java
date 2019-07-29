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
        if(monto>0 /*&& cantidad>0*/){ // ¿Validar que la cantidad de la denominacion sea mayor a cero?
            int vuelto= (int) (monto%denominacion); //Devuelve el residuo del dinero retirado
            int nbilletes=(int) (monto/denominacion); //Devuelve la cantidad de billetes de esta denominacion
            cantidad=(int) (cantidad-nbilletes); //Disminuimos la cantidad de esta denominación
            if(nbilletes>0 && cantidad>0) System.out.println(nbilletes+" x "+denominacion+" $");  //Validamos 
            if(vuelto>0) next.retirar(vuelto); //Enviamos la cantidad faltante al sgte manejador
            return true;
        }
        System.out.println("Se acabo la cantidad");
        return false;
        
    }

    @Override
    public boolean depositar(int n, double denominacion) {
        if(n<0 && denominacion<0 && (denominacion!=20 || denominacion!=10 || denominacion!=0.50 || denominacion!=0.25 || denominacion!=0.05)){
            System.out.println("Valores incorrectos!");
            return false;
        }         
        cantidad+=n;
        System.out.println("-Agregado "+n+" a la denominacion "+denominacion);
        return  true;
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
