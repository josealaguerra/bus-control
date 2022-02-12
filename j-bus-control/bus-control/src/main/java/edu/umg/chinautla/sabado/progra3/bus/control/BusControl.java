/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.umg.chinautla.sabado.progra3.bus.control;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author jag
 */
public class BusControl {
    //Array estatico de 20 posiciones
    private static String[] seats = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    private static int numberSeat=0;
    private static Scanner scanner = new Scanner(System.in);
    private static int option;
    //Constantes
    private static String cnAsignado="Asignado";
    private static String cnLiberado="Libre";
    private static String cnCOVID="No usar";


    /*** establecer espacios libres */
    private static void setFreeSpaces(){
        numberSeat=0;
        while(numberSeat < 20){
            seats[numberSeat] = numberSeat + 1 + "-"+cnLiberado;
            numberSeat++;
        }
    }


    /*** establecer espacios por protocolo de covid */
    private static void setCovidSpaces(){
        numberSeat=0;
        while(numberSeat < 20){
            if(numberSeat % 2 != 0){
                seats[numberSeat] = numberSeat + 1 + "-"+cnCOVID;
            }
            numberSeat++;
        }
    }
    

    /*** imprime menu */
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Escoge tu opcion: ");
    }


    /*** asignar asiento */
    public static void asignSeat(){

        System.out.print("\n\n\nSelecciona un numero de asiento para asignar");
        try{
            numberSeat = scanner.nextInt();                
            if((numberSeat>=1) && (numberSeat<=20)){
                numberSeat--;
                if(seats[numberSeat].contains(cnCOVID)){
                    System.out.print("\n\nAsiento no se puede asignar, por protocolos de COVID");
                }else if(seats[numberSeat].contains(cnAsignado)){
                    System.out.print("\n\nAsiento no se puede asignar, ya fue asignado previamente");
                }else if(seats[numberSeat].contains(cnLiberado)){
                    System.out.print("\n\nAsiento asignado correctamente");
                    seats[numberSeat] = numberSeat + 1 + "-"+cnAsignado;
                    listSeats();
                }else{
                    System.out.print("\n\nError al asignar asiento");
                }                  
            }else{
                System.out.print("Asiento debe estar entre 1 y 20");
            }
        }catch(Exception e){
            System.out.print("asignSeat, "+e.toString());
        }
    }


    /*** liberar asiento */
    public static void freeSeat(){
        System.out.print("\n\n\nSelecciona un numero de asiento para liberar");
        try{
            numberSeat = scanner.nextInt();
            if((numberSeat>=1) && (numberSeat<=20)){
                numberSeat--;
                if(seats[numberSeat].contains(cnCOVID)){
                    System.out.print("\n\nAsiento no se puede liberar, por protocolos de COVID");
                }else if(seats[numberSeat].contains(cnAsignado)){
                    System.out.print("\n\nAsiento liberado correctamente");
                    seats[numberSeat] = numberSeat + 1 + "-"+cnLiberado;
                    listSeats();
                }else if(seats[numberSeat].contains(cnLiberado)){
                    System.out.print("\n\nAsiento no se puede liberar, ya fue liberado previamente");
                }else{
                    System.out.print("\n\nError al liberar asiento");
                }                  
            }else{
                System.out.print("\n\nAsiento debe estar entre 1 y 20");
            }
        }catch(Exception e){
            System.out.print("freeSeat, "+e.toString());
        }
    }


    /*** listar asiento */
    public static void listSeats(){
        System.out.println( "\n\nListar estados de los asientos: "+Arrays.toString(seats) +"\n");
    }
    

    /*** proceso principal */
    public static void main (String [ ] args) {

        String[] options = {"1- Asignar", "2- Liberar", "3- Listar", "4- Salir", };
        setFreeSpaces();
        setCovidSpaces();
        listSeats();
        scanner = new Scanner(System.in);
        while (true){
            option = 0;
            printMenu(options);
            option = scanner.nextInt();
            switch(option){
                case 1:
                    asignSeat();
                    break;
                case 2:
                    freeSeat();
                    break;
                case 3:
                    listSeats();
                 
                break;
                default:
                    System.exit(1);
                    break;
            }
        }

    } 
}
