/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasproyecto;

/**
 *
 * @author Personal
 */
public class Lista {
    Nodo first;
    Nodo last;
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
    
    public void InsertarAlFinal(Nodo nuevoNodo){
        
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
    
    public Nodo getFirst() {
        return first;
    }

    public void setFirst(Nodo first) {
        this.first = first;
    }

    public Nodo getLast() {
        return last;
    }

    public void setLast(Nodo last) {
        this.last = last;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    } 
}
