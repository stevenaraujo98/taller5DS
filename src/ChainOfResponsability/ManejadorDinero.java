/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfResponsability;

/**
 *
 * @author bryan
 */
public class ManejadorDinero implements Manejador {
    
    
    private Manejador next;
    protected int cantidad;
    protected double denominacion;

    public ManejadorDinero(int cantidad, double denominacion) {
        this.cantidad = cantidad;
        this.denominacion = denominacion;
    }
    
    
    @Override
    public void setNext(Manejador m) {
        this.next = m;
    }

    @Override
    public boolean retirar(double monto) {
        if(monto>0){
            double cantidadR= monto/this.denominacion;
            if(this.denominacion>0){
                int nCantidad = this.cantidad-(int)cantidadR;
                if(nCantidad>0){
                    this.cantidad = nCantidad;
                    System.out.println("Se ha retirado: "+(int)cantidadR+" con denominacion: "+this.denominacion+" su cantidad es: "+this.cantidad);
                    double m = monto-(int)cantidadR * this.denominacion;
                    if(m== 0.0)
                        return false;
                    else
                        next.retirar(monto-(int)cantidadR * this.denominacion);
                }
                else if(nCantidad<0){
                    int cont = 0;
                    for(int i = nCantidad*(-1);this.cantidad>0;i--){
                        this.cantidad -= 1;
                        cont ++;
                    }
                    System.out.println("Se ha retirado: "+cont+" con denominacion: "+this.denominacion+" su cantidad es: "+this.cantidad);
                    double m = monto-(cont * this.denominacion);
                    if(m == 0.0)
                        return false;
                    else
                        next.retirar(monto-(cont * this.denominacion));
                }
            }
            else
                next.retirar(monto);
        }
        return false;
    }

    @Override
    public boolean depositar(int n, double denominacion) {
        double comprobarDenomi = denominacion % this.denominacion;
        if((int) comprobarDenomi == 0){
            if(n>0){
                if(denominacion == this.denominacion){
                    this.cantidad = this.cantidad + n;
                    System.out.println("Se ha depositado: "+(int)n+" con denominacion: "+this.denominacion+" su cantidad es: "+this.cantidad);
                    return true; 
                }
                else
                    next.depositar(n, denominacion); 
            } 
        }
        return false;
    }

    //=====================================================
    @Override
    public double getDinero(){
        return cantidad * denominacion;
    }
    
    @Override
    public double getDenominacion(){
        return denominacion;
    }

    @Override
    public Manejador getNext() {
        return next;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }
    
}
