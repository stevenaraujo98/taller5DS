/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import Adapter.Cuenta;
import Adapter.CuentaAdapter;
import ChainOfResponsability.ManejadorDinero;
import Singleton.AtmEC;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05
        
        /*ManejadorDinero b20 = new ManejadorDinero(100, 20);
        ManejadorDinero b10 = new ManejadorDinero(100, 10);
        ManejadorDinero b_50 = new ManejadorDinero(10, 0.50);
        ManejadorDinero b_25 = new ManejadorDinero(10, 0.25);
        ManejadorDinero b_05 = new ManejadorDinero(1000, 0.05);        
        
        b20.setNext(b10);
        b10.setNext(b_50);
        b_50.setNext(b_25);
        b_25.setNext(b_05);
        
        Scanner r = new Scanner(System.in);
        double n,n1,d;
        String b,d1,n2;
        do{
            System.out.print("Ingrese valor: ");
            b = r.nextLine();
        }while(b20.retirar(Double.parseDouble(b))!=false);
        
        do{           
            System.out.print("Ingrese denominacion a depositar: ");
            d1= r.nextLine();
            System.out.print("Ingrese cantidad de billete a depositar: ");
            n1= r.nextInt();
        }while(b20.depositar((int) n1, Double.parseDouble(d1))!=false);*/
        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        
        
        Cuenta[] lc=new Cuenta[10]; 
        for(int i=0;i<10;i++){
            double random =(int) (Math.random() * 1001 + 100);
            Cuenta c=new CuentaAdapter(i,random);
            lc[i]=c;
        }
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        Scanner sc=new Scanner(System.in);
        Cuenta c=null;
        System.out.println("Ingrese el id de la cuenta ");
        int id=sc.nextInt();
        if(id<0 || id>9){
            System.out.println("Ese id no existe");
        }else{
            c=lc[id];
            // Mostrar el menú para realizar transacciones en el cajero automático
            ManejadorDinero b20 = new ManejadorDinero(100, 20);
            ManejadorDinero b10 = new ManejadorDinero(100, 10);
            ManejadorDinero b_50 = new ManejadorDinero(10, 0.50);
            ManejadorDinero b_25 = new ManejadorDinero(10, 0.25);
            ManejadorDinero b_05 = new ManejadorDinero(1000, 0.05); 
            AtmEC atm = AtmEC.getInstance();
            System.out.println("Ingresando manejadores");
            atm.addManejador(b20);
            atm.addManejador(b10);
            atm.addManejador(b_50);
            atm.addManejador(b_25);
            atm.addManejador(b_05);
            AtmEC.transaction(c);
        }
        
        /*
        ManejadorDinero b20 = new ManejadorDinero(100, 20);
        ManejadorDinero b10 = new ManejadorDinero(100, 10);
        ManejadorDinero b_50 = new ManejadorDinero(10, 0.50);
        ManejadorDinero b_25 = new ManejadorDinero(10, 0.25);
        ManejadorDinero b_05 = new ManejadorDinero(1000, 0.05); 
        ManejadorDinero b_01 = new ManejadorDinero(10000, 0.01);
        AtmEC atm = AtmEC.getInstance();
        System.out.println("Ingresando manejadores");
        atm.addManejador(b20);
        atm.addManejador(b10);
        atm.addManejador(b_50);
        atm.addManejador(b_25);
        atm.addManejador(b_05);
        atm.addManejador(b_01);
        CuentaAdapter cuenta = new CuentaAdapter(1,5000);
        atm.transaction(cuenta);
        */
    }    
}