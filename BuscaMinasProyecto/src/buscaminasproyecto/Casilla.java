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
public class Casilla extends JToggleButton {
    private int fila;
    private int columna;
    private boolean tieneMina;

    // Constructor que recibe si la celda tiene mina o no
    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.tieneMina = false;
    }

    // Método getter para saber si la celda tiene mina
    public boolean getTieneMina() {
        return tieneMina;
    }

    // Método setter para (si fuera necesario) modificar si la celda tiene mina
    public void setTieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
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
    
    
}

