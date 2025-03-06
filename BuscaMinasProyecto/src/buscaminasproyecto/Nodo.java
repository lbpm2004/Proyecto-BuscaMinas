/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

import javax.swing.JToggleButton;

/**
 * @author Luis Peña
 * @colaboradores 
 */

public class Nodo {
    private JToggleButton toggleButton;
    int fila;
    char columna;
    boolean tieneMina;
    Nodo next;
    
    //Recibe como parámetro un entero para la columna, pero debe traducirse a un char.
    public Nodo(int fila, int columna) {
        this.toggleButton = new JToggleButton();
        this.fila = fila;
        this.columna = IntToChar(columna); //Retorna el char correspondiente al n° de la columna
        this.tieneMina = false;
        this.next = null;
    }
    
    public char IntToChar(int entero){
        //Se crea un array con la cantidad de letras máximas que puede tener el tablero
        char[] chars = {'A','B','C','D','E','F','G','H','I','J'};
        //Inicializamos la variable con '\0' (valor nulo)
        char caracter = '\0';
        for(int i=0; i < 10; i++){ 
            if(i == entero){
                caracter = chars[i];
                break;
            }
        }
        return caracter;
    }
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(char columna) {
        this.columna = columna;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }    
}
