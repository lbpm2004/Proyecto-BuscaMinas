/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hogar
 */
public class Juego {
    private Tablero tablero; //Tablero tiene el estado del tablero del juego (tiene la logica para manejar lad minas y el conteo de las adyacentes)
    private int minasMarcadas; //minas marcadas por el jugador
    
    public Juego(int filas, int columnas, int minas) { //Recibe filas, columnas y minas
        this.tablero = new Tablero(filas, columnas, minas); //Inicializar el tablero con los parametros indicados (dimensiontes y cantidad de minas)
        this.minas marcadas = 0; //Inicializar el contador de minas marcadas para saber cuantas minas ha marcado el jugador
    }
    
    public boolean encontarCasilla(int fila, int columna) { //Tratar de encontrar casillas (recibe fila y columna de la casilla que se quiere encontrar)
        return tablero.encontrarCasilla(fila, columna); //Llama el metodo de encontrarCasilla de la clase Tablero y retorna el resultado
        //Si se encuentra una mina el metodo retornara false
    }
    
    public void marcarCasilla(int fila, int columna) { //Deja que el jugador marque la casilla
        if (minasMarcadas < tablero.getFilas() * tablero.getColumnas()) { //Ver si el jugador puede marcar más minas (tiene una cantidad de movimientos que no puede sobrepasar)
            minasMarcadas++; //Aumenta el contador de minas para saber cuantas ha marcado el jugador
        }
    }
    
    public void quitarmarcaCasilla(int Fila, int columna) { //El jugador puede quitar la marca de una casilla que ya habia marcado
        minasMarcadas--; //Disminuye el contador de minas marcadas por el jugador porque quioto una marca
    }
    
    public boolean Ganar() { //Ver si se gano el juego
        return false;
    }
    
    public Tablero getTablero() {
        return tablero; //Devuelve la instancia del tablero (para la interfaz grafica)
    }
}
