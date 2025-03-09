/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

/**
 *
 * @author Luis Peña, Fabiana Rodriguez, Leon
 */
public class NodoCola {
    private Casilla casilla;
    private NodoCola siguiente;

    /**
     * Constructor para inicializar un nodo de la cola
     * @param casilla la casilla a almacenar en el nodo
     */
    public NodoCola(Casilla casilla) {
        this.casilla = casilla;
        this.siguiente = null;
    }

    /**
     * Método para obtener la casilla del nodo
     * @return la casilla almacenada en el nodo
     */
    public Casilla getCasilla() {
        return casilla;
    }

    /**
     * Método para obtener el siguiente nodo
     * @return el siguiente nodo
     */
    public NodoCola getSiguiente() {
        return siguiente;
    }

    /**
     * Método para establecer el siguiente nodo
     * @param siguiente el nodo a establecer como siguiente
     */
    public void setSiguiente(NodoCola siguiente) {
        this.siguiente = siguiente;
    }
}
