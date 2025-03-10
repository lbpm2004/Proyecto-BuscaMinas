/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buscaminasproyecto;

import GUI.Interfaz;

/**
 * Esta clase se encarga de crear y manejar las instancias de la clase Interfaz.
 * @author Luis Peña
 * @colaboradores Fabiana Rodriguez y Drexler Hurtado
 */

public class BuscaMinasProyecto {
    
    /**
     * Método que crea una instancia de Interfaz y la maneja
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        Interfaz app = new Interfaz(); //Craeción de una instancia de la clase interfaz
        app.setLocationRelativeTo(null); //método para que la interfaz apareczca centrada
        app.setVisible(true); //Permite que la interfaz aparezca
        
    }
}
