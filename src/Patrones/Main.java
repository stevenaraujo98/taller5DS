/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import ChainOfResponsability.ManejadorDinero;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05
        
        ManejadorDinero b20 = new ManejadorDinero(100, 20);
        ManejadorDinero b10 = new ManejadorDinero(100, 10);
        ManejadorDinero b_50 = new ManejadorDinero(10, 0.50);
        ManejadorDinero b_25 = new ManejadorDinero(10, 0.25);
        ManejadorDinero b_05 = new ManejadorDinero(1000, 0.05);        
        b20.setNext(b10);
        b10.setNext(b_50);
        b_50.setNext(b_25);
        b_25.setNext(b_05);
        
        /*while(true){
            Scanner r = new Scanner(System.in);
            System.out.print("Ingrese valor: ");
            double n= r.nextDouble();
            System.out.println("Tu retiro: ");
            b20.retirar(n);
        }*/
        
        /*while(true){
            Scanner r = new Scanner(System.in);            
            System.out.print("Ingrese denominacion a depositar: ");
            double d= r.nextDouble();
            System.out.print("Ingrese cantidad de billete a depositar: ");
            int n= r.nextInt();
            b20.depositar(n, d);
        }*/
        
        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        
        // Mostrar el menú para realizar transacciones en el cajero automático
    }

    
}
