/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import buscaminasproyecto.Casilla;
import buscaminasproyecto.Cola;
import buscaminasproyecto.Cola;
import buscaminasproyecto.Lista;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta clase define objetos de tipo JFrame que se encargan de ejecutar toda la lógica del proyecto y hacer posible la visualización e interacción con el usuario 
 * @author Luis Peña
 * @colaboradores Fabiana Rodriguez y Drexler Hurtado
 */
public class Interfaz extends javax.swing.JFrame {
    //Atributos de la clase
    /**
     * El JFrame está conformado por un JPanel de tipo BorderLayout, el cual a a su vez está conformado por un FlowLayout (PAGE_START) y tres GridLayout (LINE_START, CENTER y LINE_END)
     */
    //ATRIBUTOS DEL PAGE_START
    private JLabel introduccion; 
    //ATRIBUTOS DEL LINE_START
    private JLabel texto1; 
    private JTextField introdFilas; 
    private JLabel texto2; 
    private JTextField introdColumnas; 
    private JLabel texto3; 
    private JTextField introdMinas; 
    private JLabel espacioBlanco; //Espacio en blanco para rellenar hueco y alinear el botón "Generar" con la columna 2
    private JButton generar; 
    //ATRIBUTOS DEL LINE_END
    private JLabel texto4; 
    private JButton barrer; 
    private JButton ponerBandera; 
    private JButton quitarBandera; 
    private JLabel texto5; 
    private JRadioButton dfs; //Abreviacón de Depth-first search
    private JButton cargar;
    private JButton guardar; 
    private JButton ganar;
    //ATRIBUTOS DEL CENTER
    private Casilla[][] tablero;  
    private Casilla casillaSeleccionada; 
    private int banderasPuestas; 
    private Lista listaMinas;
    private JPanel center; 

    /**
     * Constructor del JFrame
     */
    public Interfaz() {
        //Inicializa el JFrame con el formato BorderLayout()
        setTitle("Busca Minas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //Generación del PAGE_START
        JPanel pageStart = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
        introduccion = new JLabel("Rellene los campos vacíos y luego presione el botón 'Generar' para generar el tablero.");
        introduccion.setFont(new Font("Arial",Font.PLAIN, 14));
        pageStart.add(introduccion);
        add(pageStart, BorderLayout.PAGE_START);
        
        //Generación del LINE_START
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
        
        //Generación del LINE_END
        JPanel lineEnd = new JPanel(new GridLayout(9,1));
        lineEnd.add(texto4 = new JLabel("Opciones para la casilla: "));
        lineEnd.add(barrer = new JButton("Barrer"));
        lineEnd.add(ponerBandera = new JButton("Poner Bandera"));
        lineEnd.add(quitarBandera = new JButton("Quitar Bandera"));
        lineEnd.add(texto5 = new JLabel("Cambiar método de barrido: "));
        lineEnd.add(dfs = new JRadioButton("Depth-first search"));
        lineEnd.add(cargar = new JButton("Cargar"));
        lineEnd.add(guardar = new JButton("Guardar"));
        lineEnd.add(ganar = new JButton("Ganar"));
        add(lineEnd, BorderLayout.LINE_END);
        
        pack(); //Ajusta el tamaño del JFrame a su mínima expresión pero sin dejar ningún componente por fuera.

        generar.addActionListener(new ActionListener() {
            @Override
            /**
             * Método encargado de verificar los datos introducidos en los TextField y de llamar al método GenerarTablero()
             */
            public void actionPerformed(ActionEvent e) {
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
                    
                    introdFilas.setEditable(false);
                    introdColumnas.setEditable(false);
                    introdMinas.setEditable(false);
                    GenerarTablero(numFilas, numColumnas, numMinas);
                    banderasPuestas = 0;

                }catch (Exception i){
                    JOptionPane.showMessageDialog(null, "El número de filas, columnas y minas debe ser un n° natural.\nLas filas y columnas deben ser >=3 y <=10.\nEl número de minas no puede ser mayor al de casillas.");
                }
                
            }
        });//Cierre del método
        
        ganar.addActionListener(new ActionListener(){
            @Override
            /**
             * Llama al método Ganar().
             */
            public void actionPerformed(ActionEvent e) {
                Ganar();
            }
        });
     
        barrer.addActionListener(new ActionListener() {
            @Override
            /**
             * Método que dada una casilla seleccionada la barre. En caso de tener la casilla una mina el jugador pierde y sino bloquea la casilla e indica con un número la cantidad de minas adyacentes.
             */
            public void actionPerformed(ActionEvent e) {
                if(casillaSeleccionada == null || casillaSeleccionada.isSelected() == false){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una casilla para poder barrerla.");
                    
                }else if(casillaSeleccionada.getTieneBandera()){
                    JOptionPane.showMessageDialog(null, "No puedes barrer una casilla con bandera.");
                    casillaSeleccionada.setSelected(false);
                }else if(casillaSeleccionada.getTieneMina()){
                    casillaSeleccionada.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "HAS PERDIDO!\nBarriste una casilla con mina.");
                    System.exit(0);
                    
                }else{
                    if(dfs.isSelected()){
                        DFS(casillaSeleccionada);
                    }else{
                        BFS(casillaSeleccionada);
                    }
                }
            }
        });//Cierre del método
        
        ponerBandera.addActionListener(new ActionListener() {
            @Override
            /**
             * Método que dada una casilla seleccionada actualiza su texto poniendo una bandera siempre y cuando la cantidad de banderas colocadas no supere al de las minas.
             */
            public void actionPerformed(ActionEvent e) {
                
                if(banderasPuestas >= getIntrodMinas()){
                    JOptionPane.showMessageDialog(null, "La cantidad de banderas no puede exceder al de las minas.");
                    casillaSeleccionada.setSelected(false);
                }else{
                    //Verificamos que la variable no sea null o al menos una casilla esté seleccionada 
                    if (casillaSeleccionada == null || casillaSeleccionada.isSelected() == false){
                        JOptionPane.showMessageDialog(null, "Debes seleccionar una casilla antes de presionar este botón.");
                    //Verificamos que no tenga bandera la casilla seleccionada
                    }else if(casillaSeleccionada.getTieneBandera()){
                        JOptionPane.showMessageDialog(null, "Esta casilla ya tiene una bandera colocada.");
                    //Después de todas las verificaciones, solo queda poner la bandera
                    }else{
                        casillaSeleccionada.setTieneBandera(true);
                        casillaSeleccionada.setText("🚩");
                        casillaSeleccionada.setSelected(false);
                        banderasPuestas++;
                        revalidate();
                        repaint();    
                    }
                }
            }
        });//Cierre del método
        
        quitarBandera.addActionListener(new ActionListener() {
            @Override
            /**
             * Método que dada una casilla seleccionada quita la bandera en ella siempre y cuando la casilla tenga bandera y las banderas colocadas no sean menor a 0.
             */
            public void actionPerformed(ActionEvent e) {
                if(banderasPuestas <= 0){
                    JOptionPane.showMessageDialog(null, "No hay banderas para quitar.");
                }else{
                    //Verificamos que la variable no sea null o al menos una casilla esté seleccionada 
                    if (casillaSeleccionada == null || casillaSeleccionada.isSelected() == false){
                        JOptionPane.showMessageDialog(null, "Debes seleccionar una casilla antes de presionar este botón.");
                    //Verificamos que no tenga bandera la casilla seleccionada
                    }else if(casillaSeleccionada.getTieneBandera() == false){
                        JOptionPane.showMessageDialog(null, "Esta casilla no tiene una bandera colocada.");
                    //Después de todas las verificaciones, solo queda poner la bandera
                    }else{
                        casillaSeleccionada.setTieneBandera(false);
                        casillaSeleccionada.setText("");
                        casillaSeleccionada.setSelected(false);
                        banderasPuestas--;
                        revalidate();
                        repaint();  
                    }
                }
            }
        });//Cierre del método
        
        guardar.addActionListener(new ActionListener() {
            @Override
            /**
             * Método que guarda el estado actual del tablero.
             */
            public void actionPerformed(ActionEvent e) {
                GuardarEstadoTablero();
            }
        });//Cierre del método
        
        cargar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CargarEstadoTablero();
        }
        });
    }//Cierre del constructor    
    
    /**
     * Método que genera el tablero, pone las minas, guardar las casillas adyacentes en una lista y lo añade todo al CENTER (GirdLayout).
     * @param filas entero que indica la cantidad de filas que debe tener el tablero
     * @param columnas entero que indica la cantidad de columnas que debe tener el tablero
     * @param minas entero que indica la cantidad de minas que debe tener el tablero
     */
    public void GenerarTablero(int filas, int columnas, int minas){
        JPanel center = new JPanel(new GridLayout(filas, columnas));
        tablero = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new Casilla(i, j);
                tablero[i][j].setPreferredSize(new Dimension(1,1)); //Aunque esto no afecta al tamaño de la casilla por el método pack() igual genera un pequeño espacio entre el CENTER con respecto al Line_Start y Line_End que resultan agradable.
                tablero[i][j].setText("");
                center.add(tablero[i][j]);
            }
        }
        PonerMinas(tablero, filas, columnas, minas);
        
        
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                Casilla casilla = tablero[fila][columna];
                casilla.addActionListener(e -> {
                    casillaSeleccionada = casilla;
                });
                //Con este ciclo anidado recorremos cada casilla adyacente haciendo que la fila y columna de nuestra casilla le sumemos -1, 0 y 1
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int filaAdyacente = fila + i;
                        int columnaAdyacente = columna + j;
                        //Las primeras 4 condiciones son para que no busque fuera del rango del tablero. La última condición es para garantizar que la casilla no se considere a sí misma una casilla adyacente. Por último se añaden las casillas adyacentes a la lista de la casilla.
                        if (filaAdyacente >= 0 && filaAdyacente < filas && columnaAdyacente >= 0 && columnaAdyacente < columnas && !(i == 0 && j == 0)) {
                            casilla.getCasillasAdyacentes().InsertarAlFinal(tablero[filaAdyacente][columnaAdyacente]);
                        }
                    }
                }
            }
        }
        
        
        add(center, BorderLayout.CENTER);   
        revalidate(); //Cuando se modifica un componente se llama a este método para actualizar el estado del contenedor
        repaint(); //Fuerza a repintar el contenedor (útil para mostrar cambios inmediatos)
    }//Cierre método
    
    /**
     * Método que usa la librería java.util.random para modificar aleatoriamente el valor booleano del atributo tieneMina de una casilla y además guardar la casilla en una lista.
     * @param tablero Recibe la matriz de casillas generadas en el método GenerarTablero
     * @param filas Guarda la cantidad de filas de la matriz
     * @param columnas Guarda la cantidad de columnas de la matriz
     * @param minas Guarda la cantidad de minas de la matriz
     */    
    private void PonerMinas(Casilla[][] tablero, int filas, int columnas, int minas) {
        Random random = new Random();
        listaMinas = new Lista();
        int minasPuestas = 0;  
        while (minasPuestas < minas) { 
            int fila = random.nextInt(filas); 
            int columna = random.nextInt(columnas); 
            if (tablero[fila][columna].getTieneMina() == false) { 
                tablero[fila][columna].setTieneMina(true); 
                listaMinas.InsertarAlFinal(tablero[fila][columna]);
                minasPuestas++; 
            }
        }
    }//Cierre del método
    
    /**
     * Método que busca si hay minas adyacentes y desabilita la casilla.
     * @param casilla
     */
    private void BFS(Casilla casilla) {
        Cola cola = new Cola();
        cola.encolar(casilla);

        while (!cola.estaVacia()) {
            Casilla actual = cola.desencolar();
                int minasAdyacentes = contarMinasAdyacentes(actual);
                actual.setText(String.valueOf(minasAdyacentes));
                actual.setEnabled(false);

                if (minasAdyacentes == 0) {
                    Lista adyacentes = actual.getCasillasAdyacentes();
                    Casilla adyacente = adyacentes.getFirst();
                    while (adyacente != null) {
                        if (adyacente.isEnabled()) {
                            cola.encolar(adyacente);
                        }
                        adyacente = adyacente.getNext();
                    }
                }
        }
    }//Cierre método
    
    /**
     * Método que busca minas adyacentes y desabilita la casilla.
     * @param casilla
     */
    private void DFS(Casilla casilla) {
        int minasAdyacentes = contarMinasAdyacentes(casilla);
        casilla.setText(String.valueOf(minasAdyacentes));
        casilla.setEnabled(false);

        if (minasAdyacentes == 0) {
            Lista adyacentes = casilla.getCasillasAdyacentes();
            Casilla actual = adyacentes.getFirst();
            while (actual != null) {
                if (actual.isEnabled() && !actual.getText().equals("0")) {
                //if (actual.isEnabled()) {
                    DFS(actual);
                }
                actual = actual.getNext();
            }
        }
    }//Cierre método
    
    /**
     * Método que guarda la cantidad de minas adyacentes en la variable minas.
     * @param casilla
     * @return La cantidad de minas adyacentes.
     */
    private int contarMinasAdyacentes(Casilla casilla) {
        int minas = 0;
        Lista adyacentes = casilla.getCasillasAdyacentes();
        Casilla aux = adyacentes.getFirst();

        while (aux != null) {
            if (aux.getTieneMina()) {
                minas++;
            }
            aux = aux.getNext();
        }
        return minas;
    }//Cierre método
    
    /**
     * Método que incrementar el valor de una variable y luego verifica que sea igual al tamaño de la lista ListaMinas.
     */
    public void Ganar(){
        int condicionParaGanar = 0;
        Casilla aux = listaMinas.getFirst();
        for(int i=0; i < listaMinas.getTamaño(); i++){
            if(aux.getTieneBandera()){
                condicionParaGanar++;
                aux = aux.getNext();
            }
        }
                
        if(condicionParaGanar == listaMinas.getTamaño()){
            JOptionPane.showMessageDialog(null, "FELICIDADES!\nHas ganado el juego.");
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null, "Aún no has marcado todas las casillas que tienen mina.");
        }
    }//Cierre método
    
    /**
    * Guarda el estado actual del tablero en un archivo CSV.
    * El usuario puede seleccionar la ubicación y el nombre del archivo usando un JFileChooser.
    * El archivo CSV incluirá las dimensiones del tablero, así como las casillas barridas, marcadas y con minas.
    */
    private void GuardarEstadoTablero(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar estado del tablero");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                // Escribir dimensiones del tablero
                writer.write(tablero.length + "," + tablero[0].length + "\n");

                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[i].length; j++) {
                        Casilla casilla = tablero[i][j];
                        String estado = casilla.isEnabled() ? "No barrida" : "Barrida";
                        String resultado = casilla.getText().isEmpty() ? " " : casilla.getText();
                        writer.write(String.format("%d,%d,%b,%b,%s,%s\n",
                                casilla.getFila(),
                                casilla.getColumna(),
                                casilla.getTieneMina(),
                                casilla.getTieneBandera(),
                                estado,
                                resultado
                        ));
                    }
                }

                JOptionPane.showMessageDialog(this, "El estado del tablero se ha guardado correctamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage());
            }
        }
    }//Cierre método
    
    /**
    * Carga el estado del tablero desde un archivo CSV seleccionado por el usuario.
    * El archivo CSV debe contener las dimensiones del tablero y el estado de cada casilla.
    * Se actualiza la interfaz para mostrar el tablero cargado.
    */
    private void CargarEstadoTablero(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Cargar estado del tablero");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileToLoad))) {
                String line = reader.readLine(); // Leer la línea que contiene las dimensiones del tablero
                if (line != null) {
                    String[] dimensiones = line.split(",");
                    int filas = Integer.parseInt(dimensiones[0]);
                    int columnas = Integer.parseInt(dimensiones[1]);
                    // Inicializar la matriz tablero
                    tablero = new Casilla[filas][columnas];
                    if (center != null) {
                        remove(center);
                    }
                    center = new JPanel(new GridLayout(filas, columnas));
                }

                // Leer el estado del tablero
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        String[] data = line.split(",");
                        int fila = Integer.parseInt(data[0]);
                        int columna = Integer.parseInt(data[1]);
                        boolean tieneMina = Boolean.parseBoolean(data[2]);
                        boolean tieneBandera = Boolean.parseBoolean(data[3]);
                        String estado = data[4];
                        String resultado = data[5];

                        // Crear una nueva casilla y asignarla a la matriz
                        Casilla casilla = new Casilla(fila, columna);
                        casilla.addActionListener(e -> {
                            casillaSeleccionada = casilla;
                        });
                        casilla.setTieneMina(tieneMina);
                        casilla.setTieneBandera(tieneBandera);
                        if (!estado.equals("No barrida")) {
                            casilla.setText(resultado);
                            casilla.setEnabled(false);
                        }
                        tablero[fila][columna] = casilla;
                        center.add(tablero[fila][columna]);
                    }
                }
                
                add(center, BorderLayout.CENTER);
                revalidate(); 
                repaint(); 
                JOptionPane.showMessageDialog(this, "El estado del tablero se ha cargado correctamente.");
            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + ex.getMessage());
            }
        }
    }//Cierre método
    
    /**
     * Método que devuelve el valor ingresado en el TextField "introdMinas"
     * @return numMinas Un String convertido a Integer.
     */
    public int getIntrodMinas() {
        int numMinas = Integer.parseInt(introdMinas.getText());
        return numMinas;
    }//Cierre método
    
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

}//Cierre de la clase


