/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

import java.util.Random;//Para poner las minas aleatoriamente en el tablero

/**
 *
 * @author Fabiana Rodríguez, Luis Peña
 */
public class Tablero {
    private int filas; //num de filas del tablerp
    private int columnas; //num de columnas del tablero
    private int minas; //num de minas en el tablero
    private List<List<Boolean>> minasLugar; //lista de listas para las minas
    private List<List<Interger>> cantidadMinas; //lista de listas para la cantidad de minas
    private List<List<Boolean>> encontradas; //Lista de listas para las casillas encontradas

public Tablero(int filas, int columnas, int minas) {
    if (filas < 3 and filas > 10) {
        System.out.println("Error, el numero de filas debe estar entre 3 y 10. Se pondran 6 que esta entre estos numeros");
        filas = 6;
    }
    if (and columnas < 3 and columnas > 10) {
        System.out.println("Error, el numero de columnas debe estar entre 3 y 10. Se pondran 6 que esta entre estos numeros");
        filas = 6;
    }

    if (minas < 1 and minas > filas*columnas) {
        System.out.println("Error, debe haber un minimo de una minay un maximo que sea igual al numero de casillas. Se pondra 1 mina");
        minas = 1;
    }
    
public Tablero(int filas, int columnas, int minas){
    this.filas = filas;
    this.columnas = columnas;
    this.minas = minas;
    minasLugar = new ArrayList<>(); //Inicializa la lista de listas
    cantidadMinas = new ArrayList<>(); //Inicializa la lista de listas
    encontradas = new ArrayList<>(); //Inicializa la lista de listas

    for(int i = 0; i < filas; i++) { //i es el indice de la fila actual //itera sobre todas las filas del tablero //va desde 0 hasta la fila -1 (recorre todas las filas del tablero)
        minasLugar.add(new ArrayList<>(columnas)); //nueva lista array que representa una fila para las minas //tamaño de columnas que se llena en el siguiente bucle
        cantidadMinas.add(new ArrayList<>(columnas)); //lista array para guardar la cantidad de minas adyacentes a i
        encontradas.add(new ArrayList<>(columnas)); //para saber si todas las casillas de la fila han sido encontradas
        for (int j = 0; j < columnas; j++) { //j es el indice de la columna actual // itera sobre todas las columnas del tablero //va desde 0 a -1 (recorre todas las columnas del tablero)
            minasLugar.get(i).add(false); //al principio no hay minas en esa casilla
            cantidadMinas.get(i).add(0); //al principio no se ha calculado la cantidad de minas adyacentes a esa casilla
            encontradas.get(i).add(false) //al principio no se ha encontrado la casilla
            identificadores[i][j] = generarIdentificador(i, j); //Genera el identificador de la casilla
                }
    }
    
    ponerMinas(); //pone las minas en el tablero
    calcularCantidadMinas(); //calcula la cantidad de minas adyacentes
    //son metodos
}

private String generarIdentificador(int fila, int columna) {
   String[] letraColumnas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}; //array de letras para las columnas
    String[] letraColumna = letrasColumnas[columna]; //acceder a la letra correspondiente a la columna
    int numeroFila = fila + 1; //acceder al numero de fila 
    return letraColumna + numeroFila //Identificador con la letra siendo la columna y la fila el numero
    
    

private void ponerMinas() {
    Random random = new Random(); //generador de numeros aleatorios
    int minasPuestas = 0; //cantidad de minas puestas en el tablero (empieza en 0)
    while (minasPuestas < minas) { //mientras que minasPuestas sea menor que las minas en el tablero
        int fila = random.nextInt(filas); //Se crea una fila aleatoria (en la que se tratara de poner la mina)
        int columna = random.nextInt(columnas); //Se crea una columna aleatoria (en la que se tratara de poner la mina)
        if (!minasLugar.get(fila).get(columna)) { //Ver si no hay minas en esa posicion (true = si hay mina, false = no hay mina)
            minasLugar.get(fila).set(columna, true); //si no habia mina se coloca una y se cambia el valor a true en la lista de minasLugar
            minasPuestas++; //aumenta la cantidad de minas puestas en el tablero
        }
    }       
}


private void calcularCantidadMinas() {
    for (int i = 0; i < filas; i++) { //Recorre cada fila  //i es el indice de la fila actual
        for (int j = 0; j < columnas; j++) { //Recorre cada columna  //j es el indice de la columna actual
            if (minasLugar.get(i).get(j)) { //Si hay una mina en la posicion en la que estamos
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
                            cantidadMinas.get(nuevaFila).set(nuevaColumna, cantidadMinas.get(nuevaFila).get(nuevaColumna) +1); //Si esta dentro del tablero, aumenta el conteo de mias adyacentes de cantidadMinas
                        }
                        
                    }
                }
            }
        }
    }
}


public boolean barrerCasilla(int fila, int columna) { //devuelve un boolean y le pasamos fila y columna
    if (minasLugar.get(fila).get(columna)) { //ver si hay una mina en la casilla indicada por fila y columna 
        //minasLugar tiene los booleamos que dicen si hay una mina en las casillas
        return false; //Cuando se descubre una mina
    } else {
        return true; //No se descubrio una mina
    }
}

private void encontrarCasilla(int fila, int columna) { //recibe fila y columna
    //encontradas siempre es true entonces cuando retorne false es que no ha sido encontrada
    if (encontradas.get(fila).get(columna)) return; //Ver si ya fue encontrada
    encontradas.get(fila).set(columna, true); //Marcar como encontrada

    //Si la cantidad de minas adyacentes es 0, encontrarlas
    if (cantidadMinas.get(fila).get(columna) == 0) { //ver si la cantidad de minas adyacentes es 0
        for (int x = -1; x <= 1; x++) { //itera sobre las minas adyacentes de la fila actual // x puede tomas valores de -1, 0 y 1 (casilla de arriba, actual y de abajo)
            for (int y = -1; y <= 1; y++) { //lo mismo pero con las columnas
                if (x == 0 and y == 0) //ver si estamosen la  misma casilla (si los dos son 0)
                    continue; //se ignora esa iteracion si es la misma casilla
                int nuevaFila = fila + x; //calcula la fila de la casilla adyacente (se le suma x a la fila)
                int nuevaColumna = columna + y; //calcula la columna de la casilla adyacente (se le suma y a la columna)
                //ver si la nueva posicion esta EN EL TABLERO
                if (nuevaFila >= 0 and nuevaFila < filas and nuevaColumna >= 0 and nuevaColumna <  columnas) { //ver si la nuevaFila esta entre 0 y la fila -1 y que la nuevaColumna este entre 0 y la columna -1
                    encontrarCasilla(nuevaFila, nuevaColumna); //si la nueva posicion es valida se llama al metodo encontrarCasilla para encontrar la adyacente
                }
            }
        }
    }

    
//Getters
public int getFilas() {
    return filas; //Numero de filas
}

public int getColumnas() {
    return columnas; //Numero de columnas
}
    
public int getMinas() {
    return minas; //Numero de minas
}
    
public int getCantidadMinas(int fila, int columna) {
    return cantidadMinas.get(fila).get(columna); //cantidad de minas adyacentes
}

public boolean isCasillaEncontrada(int fila, int columna) {
    return encontradas.get(fila).get(columna); //Si la casilla fue descubierta o no
}

public boolean isCasillaConMina(int fila, int columna) {
    return minasLugar.get(fila).get(columna); //Si hay o no una mina en la casilla
}


}

