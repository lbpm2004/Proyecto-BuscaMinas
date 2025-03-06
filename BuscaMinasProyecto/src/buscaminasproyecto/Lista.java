/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

/**
 * @author Luis Peña
 * @colaboradores 
 */

public class Lista {
    Casilla first;
    Casilla last;
    int tamaño;

    public Lista() {
        this.first = null;
        this.last = null;
        this.tamaño = 0;
    }
    
    public boolean EsVacía(){
        return getFirst() == null;
    }
    
    public void VaciarLista(){
        first=last=null;
        tamaño = 0;
    }
    
    public void InsertarAlFinal(Casilla nuevoNodo){
        
        if (this.EsVacía()){
            first=last=nuevoNodo;
        }else{
        last.setNext(nuevoNodo);
        last = nuevoNodo; 
        }
        tamaño++;
    }
    
    public void EliminarAlInicio(){
        
        if(!this.EsVacía()){
            if(tamaño == 1){
                this.VaciarLista();
            }else{
                first = first.getNext();
            }
            tamaño--;
        } 
    }
    
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
