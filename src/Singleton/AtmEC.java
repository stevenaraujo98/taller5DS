/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;
import Adapter.Cuenta;
import ChainOfResponsability.Manejador;
import Patrones.Account;
import java.util.Currency;
import java.util.Scanner;

/**
 *
 * @author SSAM
 */
public class AtmEC {
    private static AtmEC instance;
    private Currency moneda;
    private double dinero = 0;
    private Manejador manejador;
    private static Scanner in = new Scanner(System.in);
    
    private AtmEC(){
        //manejador = new Manejador();
    }
    
    public static AtmEC getInstance(){
        if(instance == null){
            instance = new AtmEC();
            System.out.println("Creado con exito.");
        }else
            System.out.println("Ya existe el objeto.");
        return instance;
    }
    
    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }
    
    public boolean sacarDinero(double dinero) {
        this.dinero -= dinero;
        return manejador.retirar(dinero);
    }

    public boolean ingresarDinero(int n, double denominacion) {
        this.dinero += dinero;
        return manejador.depositar(n, denominacion);
    }

    public void addManejador(Manejador m){
        dinero += m.getDinero();
        
        if(manejador == null){
            manejador = m;
        }else{
            Manejador copia = manejador;
            while(copia.getNext() != null)
                copia = copia.getNext();
            copia.setNext(m);
        }
    }
    
    public Manejador removeManejador(double d){
        if(manejador == null){
            return null;
        }
        
        Manejador copia = manejador;
        if(manejador.getDenominacion() == d){
            dinero -= manejador.getDinero();
            if(manejador.getNext() == null)
                manejador = null;
            else{
                manejador = manejador.getNext();
                copia.setNext(null);
            }
            return copia;
        }
        Manejador copiaRetorno;
        while(copia.getNext() != null){
            if(copia.getNext().getDenominacion() == d){
                copiaRetorno = copia.getNext();
                copia.setNext(copiaRetorno.getNext());
                copiaRetorno.setNext(null);
                dinero -= copiaRetorno.getDinero();
                return copiaRetorno;
            }
            copia = copia.getNext();
        }
        return null;
    }

    //Dentro de las transacciones se debe llamar al ATM para hacer el retiro o deposito de la cuenta correspondiente
    public static void transaction(Cuenta cuenta){
        // here is where most of the work is
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        
        choice = in.nextInt();
        switch(choice){
            case 1:
                float amount = 0; 
                System.out.println("Please enter amount to withdraw: "); 
                amount = in.nextFloat();
                if(amount > cuenta.balance() || amount == 0){
                    System.out.println("You have insufficient funds\n\n"); 
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    // Todo: verificar que se puede realizar el retiro del atm
                    if(instance.dinero >= amount){
                        // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                        // cuenta.retirar(amount);
                        // AtmUK.sacarDinero(amount);
                        cuenta.Retirar(amount);
                        instance.manejador.retirar(amount);
                        System.out.println("You have withdrawn "+amount+" and your new balance is "+cuenta.balance());
                        // Todo: Mostrar resumen de transacción o error
                        // "You have withdrawn "+amount+" and your new balance is "+balance;
                    }else{
                        // Todo: Mostrar resumen de transacción o error
                        System.out.println("Error al retirar");
                    }
                    anotherTransaction(cuenta); 
                }
            break; 
            case 2:
                    // option number 2 is depositing 
                    int deposit; 
                    System.out.println("Please enter amount you would wish to deposit: "); 
                    deposit = in.nextInt();
                    
                    System.out.println("Please currency denomination : "); 
                    double denominacion = in.nextDouble();
                    // Todo: actualizar tanto la cuenta como el atm
                    cuenta.Depositar(deposit, denominacion);
                    instance.ingresarDinero(deposit, denominacion);
                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    System.out.println("You to deposit "+deposit+" and your new balance is "+cuenta.balance());
                    anotherTransaction(cuenta);
            break; 
            case 3:
                    // Todo: mostrar el balance de la cuenta
                    // "Your balance is "+balance
                    System.out.println("Your balance is "+cuenta.balance());
                    anotherTransaction(cuenta); 
            break;
            case 4:
                    // Todo: mostrar el balance del ATM con los billetes en cada manejador
                    if(instance.manejador == null){
                        System.out.println("No hay billetes!");
                    }else{
                        Manejador copia = instance.manejador;
                        while(copia.getNext() != null){
                            System.out.println("Un manjador es con denominacion: "+copia.getDenominacion()+" y cantidad: "+copia.getCantidad());
                            copia = copia.getNext();
                        }
                        System.out.println("Un manjador es con denominacion: "+copia.getDenominacion()+" y cantidad: "+copia.getCantidad());
                    }
                    anotherTransaction(cuenta); 
            break;
            default:
                    System.out.println("Invalid option:\n\n"); 
                    anotherTransaction(cuenta);
            break;
        }
    }
    public static void anotherTransaction(Cuenta cuenta){
        System.out.println("Do you want another transaction?\n\nPress 1 for another transaction\n2 To exit");
        
        int anotherTransaction = in.nextInt();
        if(anotherTransaction == 1){
            transaction(cuenta); // call transaction method
        } else if(anotherTransaction == 2){
            System.out.println("Thanks for choosing us. Good Bye!");
        } else {
            System.out.println("Invalid choice\n\n");
            anotherTransaction(cuenta);
        }        
    }
}
