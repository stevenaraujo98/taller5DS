/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import Patrones.Account;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author LuisEduardo
 */
public class CuentaAdapter implements Cuenta{
    protected Account cuenta;
    protected Currency moneda;
    
    public CuentaAdapter(int id, double amount){
        cuenta=new Account(id,amount);
        moneda=Currency.getInstance(Locale.US);
    }

    @Override
    public double balance() {
        return cuenta.getAmount();
    }

    @Override
    public boolean Retirar(double monto) {
        String ver=cuenta.withdraw(monto);
        if(ver.contains("Error:")){
            int pos=ver.indexOf(" ");
            String parcial=ver.substring(pos);
            String sfinal= "Error:"+this.moneda.getCurrencyCode()+ parcial;
            System.out.println(sfinal);
            return false;
        }else{
            int pos=ver.indexOf(" ");
            String parcial=ver.substring(pos);
            String sfinal= this.moneda.getCurrencyCode()+ parcial;
            System.out.println(sfinal);
            return true;
        }
    }

    @Override
    public boolean Depositar(double n, double denominacion) {
        String ver=cuenta.deposit(n*denominacion);
      //  System.out.println(ver);
        int pos=ver.indexOf(" ");
        String parcial=ver.substring(pos);
        String sfinal= this.moneda.getCurrencyCode()+ parcial;
        System.out.println(sfinal);
        return true;
    }
    
    
    
    
}
