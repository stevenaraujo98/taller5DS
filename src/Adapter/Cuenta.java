/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

/**
 *
 * @author LuisEduardo
 */
public interface Cuenta {
    double balance();
    boolean Retirar(double monto);
    boolean Depositar(double n, double denominacion);
}
