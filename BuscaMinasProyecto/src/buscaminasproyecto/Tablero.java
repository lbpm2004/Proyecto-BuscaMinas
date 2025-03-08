/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

import java.util.Random;//Para poner las minas aleatoriamente en el tablero

/**
 *
 * @author Personal
 */
public class Tablero {
    private int filas; //num de filas del tablerp
    private int columnas; //num de columnas del tablero
    private int minas; //num de minas en el tablero
    private boolean[][] minasLugar; //matriz para saber si hay una mina en las casillas
    private int[][] cantidadMinas; //matriz con el conteo de minas adyacentes de las casillas
    private boolean[][] encontradas; //matriz que dice si una casilla fue descubierta
    
public Tablero(int filas, int columnas, int minas){
    this.filas = filas;
    this.columnas = columnas;
    this.minas = minas;
    this.minasLugar = new boolean[filas][columnas]; //Inicializa la matriz
    this.cantidadMinas = new int [filas][columnas]; //Inicializa la matriz
    this.encontradas = new boolean [filas][columnas]; //Inicializa la matriz
    this.ponerMinas(); //pone las minas en el tablero
    //this.calcularCantidadMinas(); //calcula la cantidad de minas adyacentes
}

private void ponerMinas() {
    Random random = new Random(); //generador de numeros aleatorios
    int minasPuestas = 0; //cantidad de minas puestas en el tablero
    while (minasPuestas < minas) { //mientras que minasPuestas sea menor que las minas en el tablero
        int fila = random.nextInt(filas); //Se crea una fila aleatoria
        int columna = random.nextInt(columnas); //Se crea una columna aleatoria
        if (!minasLugar[fila][columna]) { //Ver si no hay minas en esa posicion
            minasLugar[fila][columna] = true; //Pone la mina
            minasPuestas++; //aumenta la cantidad de minas puestas en el tablero
        }
    }       
}

/* El método tiene fallas en las líneas 53, 56 y 59 (revisarlo)
private void calcularCantidadMinas() {
    for (int i = 0; i < filas; i++) { //Recorre cada fila  //i es el indice de la fila actual
        for (int j = 0; j < columnas; j++) { //Recorre cada columna  //j es el indice de la columna actual
            if (minasLugar[i][j]) { //Si hay una mina en la posicion en la que estamos
                for (int x = -1; x <= 1; x++) { //Recorre las filas adyacentes de la que estamos 
                    //x puede tomar valores de -1, 0 y 1 y asi puedes ver la casilla de arriba, la actual y la de abajo
                    for (int y = -1; y <= 1; y++) { //Lo mismo pero con las columnas  //puedes acceder a la de la izquierda, la actual y la de la derecha
                        if (x == 0 and y == 0) {
                        continue;
                        } //Si x y y son iguales estamos en la misma casilla entonces continua
                        int nuevaFila = i + x; //calcula la nueva fila (calcula la fila de la casilla adyacente sumandole x a i)
                        int nuevaColumna = j + y; //calcula la nueva columna (calcula la columna de la casilla adyacente sumandole y a j)
                        //VERIFICAR QUE ESTE DENTRO DEL TABLERO
                        if (nuevaFila >= 0 and nuevaFila < filas and nuevaColumna >= 0 and nuevaColumna < columnas) {
                            cantidadMinas[nuevaFila][nuevaColumna]++; //Si esta dentro del tablero, aumenta el conteo de mias adyacentes de cantidadMinas
                        }
                        
                    }
                }
            }
        }
    }
}
*/

public boolean barrerCasilla(int fila, int columna) { //devuelve un boolean y le pasamos fila y columna
    if (minasLugar[fila][columna]) { //ver si hay una mina en la casilla indicada por fila y columna 
        //minasLugar tiene los booleamos que dicen si hay una mina en las casillas
        return false; //Cuando se descubre una mina
    } else {
        return true; //No se descubrio una mina
    }
}

private void encontrarCasilla(int fila, int columna) { //recibe fila y columna
    //encontradas siempre es true entonces cuando retorne false es que no ha sido encontrada
    if (encontradas[fila][columna]) return; //Ver si ya fue encontrada
    encontradas[fila][columna] = true; //Marcar como encontrada
}

//Getters
public int getCantidadMinas(int fila, int columna) {
    return cantidadMinas[fila][columna]; //cantidad de minas adyacentes
}

public boolean isCasillaEncontrada(int fila, int columna) {
    return encontradas[fila][columna]; //Si la casilla fue descubierta o no
}

public boolean isCasillaConMina(int fila, int columna) {
    return minasLugar[fila][columna]; //Si hay o no una mina en la casilla
}

public int getFilas() {
    return filas; //Numero de filas
}

public int getColumnas() {
    return columnas; //Numero de columnas
}
}

