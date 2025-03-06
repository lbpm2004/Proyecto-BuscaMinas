/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

import javax.swing.JToggleButton;

/**
 *
 * @author Personal
 */

//Creamos una clase Casilla que hereda de JToggleButton (nos permite agregar parámetros como tieneMinas)
public class Casilla extends JToggleButton {
    private int fila;
    private int columna;
    private boolean tieneMina;
    private boolean tieneBandera;
    private Casilla next; //Apuntador al siguente objeto casilla
    private Lista casillasAdyacentes; //Lista de objetos casillas adyacentes

    // Constructor que recibe filas y columnas, y deja false por defecto si una casilla tiene mina
    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.tieneMina = false; //Por defecto la casilla no tiene mina
        this.tieneBandera = false; //Por defecto la casilla no tiene bandera
        this.next = null; //Por defecto la casilla apuntará a null
        this.casillasAdyacentes = new Lista(); //Creamos una lista vacía para almacenar las casillas adyacentes
    }
    
    //Posible eliminación depediendo del desarrollo del proyecto
    public char IntToChar(int entero){
        //Se crea un array con la cantidad de letras máximas que puede tener el tablero
        char[] chars = {'A','B','C','D','E','F','G','H','I','J'};
        //Inicializamos la variable con '\0' (estandar para valor considerado nulo)
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

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    public boolean getTieneMina() {
        return tieneMina;
    }

    public void setTieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
    }
    public boolean getTieneBandera() {
        return tieneBandera;
    }
    public void setTieneBandera(boolean tieneBandera) {
        this.tieneBandera = tieneBandera;
    }
    public Casilla getNext() {
        return next;
    }

    public void setNext(Casilla next) {
        this.next = next;
    }
    
    public Lista getCasillasAdyacentes() {
        return casillasAdyacentes;
    }
}

