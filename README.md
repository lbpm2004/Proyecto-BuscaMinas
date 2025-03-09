# Proyecto-BuscaMinas
Proyecto que busca crear un juego basado en el Buscaminas.
Este es un proyecto que consiste de un programa que genera un juego comúnmente conocido como Buscaminas el cual tiene como objetivo encontrar las minas ocultas que se encuentran dentro del tablero, esto se hace seleccionando las casillas del tablero que se quieren barrer, luego de esto se muestran cuantas minas hay en las casillas adyacentes a la casilla seleccionada que son las que están justo abajo, arriba, a la izquierda, la derecha y las cuatro diagonales. Cuando el jugador piensa que consiguió una casilla que contiene una mina, esta se marca con una bandera y si logra encontrar todas las minas en el tablero, el jugador gana pero si el jugador selecciona una casilla con una mina para barrerla, este pierde el juego.
Este programa cuenta con una clase tablero la cual contiene el código necesario para que se cree el tablero con la cantidad de filas y columnas que sean indicadas (esas deben estar entre 3 y 10) y también se indican la cantidad de minas que se quieren en el tablero (debe haber mínimo 1 mina y el máximo es el número de casillas que hay en el tablero), para hacer esto se utilizaron listas de listas. Cada casilla se identifica con la letra de la columna en la que se encuentra que puede ir de la A a la J y el número de la fila en la que se encuentra por lo que la primera casilla del tablero es la casilla A1. 
Luego de que el tablero es creado las minas son ubicadas aleatoriamente en el tablero y después se calcula la cantidad de minas adyacentes que tiene cada casilla en el tablero para luego poder implementar el código de barrer casillas el cual puede devolver información sobre las minas que la casilla seleccionada tiene a su alrededor, que el jugador pierda por haber barrido una casilla con una mina o que la casilla este vacía y por ende el programa barra las casillas adyacentes mostrando las otras que también estén vacías.
Después el programa permite a través de la clase juego marcar las casillas en las que creen que hay una mina y el jugador tiene un límite de movimientos el cual corresponde a la cantidad de minas que hay en el tablero y también se pueden desmarcar las casillas seleccionadas sin importar si tiene una mina o no para de esta manera no superar el limite de movimientos que se tienen.
