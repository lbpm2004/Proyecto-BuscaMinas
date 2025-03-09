/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

/**
 *
 * @author Personal
 */
public class Cola {
    // Atributos de la clase
    private NodoCola frente; // Frente de la cola
    private NodoCola fondo;  // Fondo de la cola
    private int tamaño;

    /**
     * Constructor para inicializar una cola vacía
     */
    public Cola() {
        this.frente = null;
        this.fondo = null;
        this.tamaño = 0;
    }

    /**
     * Método para verificar si la cola está vacía
     * @return true si la cola está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Método para encolar un elemento
     * @param casilla la casilla a encolar
     */
    public void encolar(Casilla casilla) {
        NodoCola nuevoNodo = new NodoCola(casilla);
        if (estaVacia()) {
            frente = fondo = nuevoNodo;
        } else {
            fondo.setSiguiente(nuevoNodo);
            fondo = nuevoNodo;
        }
        tamaño++;
    }

    /**
     * Método para desencolar un elemento
     * @return la casilla desencolada
     */
    public Casilla desencolar() {
        if (estaVacia()) {
            return null;
        }
        Casilla casilla = frente.getCasilla();
        frente = frente.getSiguiente();
        if (frente == null) {
            fondo = null;
        }
        tamaño--;
        return casilla;
    }

    /**
     * Método para obtener el tamaño de la cola
     * @return el tamaño de la cola
     */
    public int getTamaño() {
        return tamaño;
    }
}
