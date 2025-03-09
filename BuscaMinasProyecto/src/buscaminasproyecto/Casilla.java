/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

import javax.swing.JToggleButton;

/**
 * Esta clase define objetos de tipo Nodo que heredan de la clase JToggleButton 
 * @author Luis Peña, Fabiana Rodriguez , Drexler Hurtado
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
    
    /**
     * Obtiene la fila de la casilla.
     * @return La fila de la casilla.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Establece la fila de la casilla.
     * @param fila La nueva fila de la casilla.
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Obtiene la columna de la casilla.
     * @return La columna de la casilla.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Establece la columna de la casilla.
     * @param columna La nueva columna de la casilla.
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    /**
     * Verifica si la casilla tiene una mina.
     * @return True si la casilla tiene una mina, false en caso contrario.
     */
    public boolean getTieneMina() {
        return tieneMina;
    }

    /**
     * Establece si la casilla tiene una mina.
     * @param tieneMina True si la casilla tendrá una mina, false en caso contrario.
     */
    public void setTieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
    }

    /**
     * Verifica si la casilla tiene una bandera.
     * @return True si la casilla tiene una bandera, false en caso contrario.
     */
    public boolean getTieneBandera() {
        return tieneBandera;
    }

    /**
     * Establece si la casilla tiene una bandera.
     * @param tieneBandera True si la casilla tendrá una bandera, false en caso contrario.
     */
    public void setTieneBandera(boolean tieneBandera) {
        this.tieneBandera = tieneBandera;
    }

    /**
     * Obtiene la siguiente casilla en la lista.
     * @return La siguiente casilla.
     */
    public Casilla getNext() {
        return next;
    }

    /**
     * Establece la siguiente casilla en la lista.
     * @param next La nueva casilla siguiente.
     */
    public void setNext(Casilla next) {
        this.next = next;
    }
    
    /**
     * Obtiene la lista de casillas adyacentes.
     * @return La lista de casillas adyacentes.
     */
    public Lista getCasillasAdyacentes() {
        return casillasAdyacentes;
    }
}

