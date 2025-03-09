/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

/**
 * Esta clase define obejtos de tipo lista enlazada simple
 * @author Luis Peña
 * @colaboradores 
 */

public class Lista {
    //Atributos de la clase
    Casilla first;
    Casilla last;
    int tamaño;
    
    /**
     * Consructor de una instancia de lista vacía
     */
    public Lista() {
        this.first = null;
        this.last = null;
        this.tamaño = 0;
    }//Cierre del constructor
    
    /**
     * Método que devuelve un valor booleano al comprobar si el primer nodo de la lista es nulo
     * @return Valor booleano
     */
    public boolean EsVacía(){
        return getFirst() == null;
    }//Cierre del método
    
    /**
     * Método que iguala el primer y último nodo a null para dejarla vacía
     */
    public void VaciarLista(){
        first=last=null;
        tamaño = 0;
    }//Cierre del método
    
    /**
     * Método que recibe una instancia de Casilla y la introduce al final de la lista
     * @param nuevoNodo Define una instancia de Casilla que será introducida en la lista
     */
    public void InsertarAlFinal(Casilla nuevoNodo){
        
        if (this.EsVacía()){
            first=last=nuevoNodo;
        }else{
        last.setNext(nuevoNodo);
        last = nuevoNodo; 
        }
        tamaño++;
    }//Cierre del método
    
    /**
     * Método que elimina el primer nodo de la lista
     */
    public void EliminarAlInicio(){
        
        if(!this.EsVacía()){
            if(tamaño == 1){
                this.VaciarLista();
            }else{
                first = first.getNext();
            }
            tamaño--;
        } 
    }//Cierre del método
    
    /**
     * Getters y Setters de los atributos de la clase
     */
    public Casilla getFirst() {
        return first;
    }

    public void setFirst(Casilla first) {
        this.first = first;
    }

    public Casilla getLast() {
        return last;
    }

    public void setLast(Casilla last) {
        this.last = last;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    } 
}
