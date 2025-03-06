/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

/**
 * @author Luis Peña
 * @colaboradores Fabiana Rodriguez
 */

import buscaminasproyecto.Casilla;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Interfaz extends javax.swing.JFrame {
    //ATRIBUTOS DEL PAGE_START
    private JLabel introduccion; //Instrucción para el jugador.
    //ATRIBUTOS DEL LINE_START
    private JLabel texto1; // texto = Cant. Filas
    private JTextField introdFilas; // Campo para introducir la cantidad
    private JLabel texto2; // texto = Cant. Columnas
    private JTextField introdColumnas; // Campo para introducir la cantidad
    private JLabel texto3; // texto = Cant. Minas
    private JTextField introdMinas; // Campo para introducir la cantidad
    private JLabel espacioBlanco; //Espacio en blanco para rellenar hueco y alinear el botón "Generar" con la columna 2
    private JButton generar; // Botón para obtener los datos de los TextField
    //ATRIBUTOS DEL LINE_END
    private JLabel texto4; // Texto = Opciones para la casilla
    private JButton barrer; //Al presionar este botón después de seleccionar una casilla se ejecutará un método
    private JButton ponerBandera; //Al presionar este botón después de seleccionar una casilla se ejecutará un método
    private JButton quitarBandera; //Al presionar este botón después de seleccionar una casilla se ejecutará un método
    private JLabel texto5; //texto = Marque el método para barrer las casillas
    private JRadioButton bfs; //Abreviacón de Breadth-first search
    private JRadioButton dfs; //Abreviacón de Depth-first search
    private JButton guardar; //Presionar este botón antes de cerrar el juego para guardar el estado actual del mismo
    //ATRIBUTOS DEL CENTER
    private Casilla[][] tablero; //Matriz de objetos Casilla, y estos son a su vez JToggleButton 
    private JPanel center; //Panel central que se actualizará al generar el tablero

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        //Inicializa el JFrame con el formato BorderLayout()
        setTitle("Busca Minas"); //Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //Para cada sección del BorderLayout usada se crea un JPanel (menos el Center porque se creará después)y, se inicializan y agregan los componentes
        //Generación del Page_Start
        JPanel pageStart = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
        introduccion = new JLabel("Rellene los campos vacíos y luego presione el botón 'Generar' para generar el tablero.");
        introduccion.setFont(new Font("Arial",Font.PLAIN, 14));
        pageStart.add(introduccion);
        add(pageStart, BorderLayout.PAGE_START);
        
        //Generación del Line_Start
        JPanel lineStart = new JPanel(new GridLayout(4,2));
        lineStart.add(texto1 = new JLabel("Cant. Filas: "));
        lineStart.add(introdFilas = new JTextField(""));
        lineStart.add(texto2 = new JLabel("Cant. Columnas: "));
        lineStart.add(introdColumnas = new JTextField(""));
        lineStart.add(texto3 = new JLabel("Cant. Minas: "));
        lineStart.add(introdMinas = new JTextField(""));
        lineStart.add(espacioBlanco = new JLabel(""));
        lineStart.add(generar = new JButton("Generar"));
        add(lineStart, BorderLayout.LINE_START);
        
        //Generación del Line_End
        JPanel lineEnd = new JPanel(new GridLayout(8,1));
        lineEnd.add(texto4 = new JLabel("Opciones para la casilla: "));
        lineEnd.add(barrer = new JButton("Barrer"));
        lineEnd.add(ponerBandera = new JButton("Poner Bandera"));
        lineEnd.add(quitarBandera = new JButton("Quitar Bandera"));
        lineEnd.add(texto5 = new JLabel("Marque el método de barrido: "));
        lineEnd.add(bfs = new JRadioButton("Breadth-first search"));
        lineEnd.add(dfs = new JRadioButton("Depth-first search"));
        add(lineEnd, BorderLayout.LINE_END);
        
        pack(); //Ajusta el tamaño del JFrame a su mínima expresión sin dejar ningún componente por fuera. Por eso no se define un tamaño del JFrame antes.

        generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el texto de los JTextFields, además de validar lo que ingresa el usuario
                try{
                    int numFilas = Integer.parseInt(introdFilas.getText());
                    int numColumnas = Integer.parseInt(introdColumnas.getText());
                    int numMinas = Integer.parseInt(introdMinas.getText());

                    if(numFilas > 10 || numFilas < 3){
                    throw new Exception();    
                    }
                    if(numColumnas > 10 || numColumnas < 3){
                    throw new Exception();    
                    }
                    if(numMinas < 1 || numMinas > numFilas*numColumnas){
                    throw new Exception();    
                    }
                    if(center != null){
                        remove(center);
                    }
                    
                    generarTablero(numFilas, numColumnas, numMinas);

                }catch (Exception i){
                    JOptionPane.showMessageDialog(null, "El número de filas, columnas y minas debe ser un n° natural.\nLas filas y columnas deben ser >=3 y <=10.\nEl número de minas no puede ser mayor al de casillas.");
                }
                
            }
        });
     
            
    }
    
    
    //Generación del Center del BorderLayout y el tablero con las minas colocadas
        public void generarTablero(int filas, int columnas, int minas){
        JPanel center = new JPanel(new GridLayout(filas, columnas));
        tablero = new Casilla[filas][columnas]; //Inicializamos el tablero como una matriz de Casillas vacías
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new Casilla(i, j); //En cada bloque del tablero se inicializa una instancia de Casilla
                tablero[i][j].setPreferredSize(new Dimension(1,1)); //Aunque esto no afecta al tamaño de la casilla por el método pack() igual genera un pequeño espacio entre Line_Start y Line_End que resultan agradables.
                tablero[i][j].addActionListener(new CasillaListener()); //Para que la casilla pueda hacer una acción
                tablero[i][j].setText(""); //Para que la casilla se muestre vacía
                center.add(tablero[i][j]); //Añadimos la casilla al JPanel
            }
        }
        
        ponerMinas(tablero, filas, columnas, minas); //Llama al método y pone las minas aleatoriamente
        
        add(center, BorderLayout.CENTER);   
        revalidate(); //Cuando se modifica un componente en un Border o GridLayout es útil llamar a este método porque actualiza el diseño del contenedor
        repaint(); //Fuerza a repintar el contenedor (útil para mostrar cambios inmediatos)
    }
    
        private void ponerMinas(Casilla[][] tablero, int filas, int columnas, int minas) {
            Random random = new Random(); //generador de numeros aleatorios
            int minasPuestas = 0; //cantidad de minas puestas en el tablero
                while (minasPuestas < minas) { //mientras que minasPuestas sea menor que las minas en el tablero
                    int fila = random.nextInt(filas); //Se crea una fila aleatoria
                    int columna = random.nextInt(columnas); //Se crea una columna aleatoria
                    if (tablero[fila][columna].getTieneMina() == false) { //Ver si no hay minas en esa posicion
                        tablero[fila][columna].setTieneMina(true); //Pone la mina
                        minasPuestas++; //aumenta la cantidad de minas puestas en el tablero
                    }
                }       
        }
        
        
        
        
        
        
    private class CasillaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Casilla casilla = (Casilla) e.getSource();
            if(casilla.isSelected()){
                if(casilla.getTieneMina()){
                    casilla.setText("Mina");
                    revalidate();
                    repaint();
                    
                }
                
            }
        }
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}

