/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

import javax.swing.JToggleButton;

/**
 * Esta clase define objetos de tipo Nodo que heredan de la clase JToggleButton 
 * @author Luis Peña
 */

public class Casilla extends JToggleButton {
    //Atributos de la clase
    private int fila;
    private int columna;
    private boolean tieneMina;
    private boolean tieneBandera;
    private Casilla next; 
    private Lista casillasAdyacentes;

    /**
     * Constructor para la casilla
     * @param fila Guarda la fila de la casilla como un entero
     * @param columna Guarda la columna de la casilla como un entero
     */
    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.tieneMina = false; 
        this.tieneBandera = false; 
        this.next = null; 
        this.casillasAdyacentes = new Lista(); 
    }//Cierre del constructor
    
    /*
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
    }*/
    
    /**
     * Getters y Setters de los atributos de la clase
     */
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
}//Cierre de la clase

