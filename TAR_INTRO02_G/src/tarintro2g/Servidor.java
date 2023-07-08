package tarintro2g;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Servidor extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    static ServerSocket servidor;
    static final int PUERTO = 40404;// puerto por el que escucho
    static int CONEXIONES = 0; //Nº de exámenes recibidos

    static JTextField mensaje = new JTextField("");
    static JTextField mensaje2 = new JTextField("");
    private JScrollPane scrollpane1;
    static JTextArea textarea;
    JButton salir = new JButton("Salir");

    public Servidor() {
        super(" VENTANA DEL PROFESOR ");
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        mensaje.setEditable(false);

        mensaje2.setBounds(10, 348, 400, 30);
        add(mensaje2);
        mensaje2.setEditable(false);

        textarea = new JTextArea();
        scrollpane1 = new JScrollPane(textarea);

        scrollpane1.setBounds(10, 50, 400, 300);
        add(scrollpane1);
        salir.setBounds(420, 10, 100, 30);
        add(salir);

        textarea.setEditable(false);
        salir.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Función para recibir conexiones desde los alumnos. El servidor se
     * mantiene continuamente a la espera de nuevas conexiones y, cuando acepta
     * alguna, crea un HiloServidor para ella, lo lanza y aumenta el valor de
     * CONEXIONES.
     */
    private static void recibirConexiones() {
   
    }

    public static void main(String[] args) {
        System.out.println("Servidor iniciado...");
        Servidor pantalla = new Servidor();
        pantalla.setBounds(0, 0, 540, 400);
        pantalla.setVisible(true);
        mensaje.setText("NUMERO DE EXAMENES RECIBIDOS: " + 0);

        recibirConexiones();

        if (!servidor.isClosed()) {
            try {
                mensaje2.setForeground(Color.red);
                mensaje2.setText("NUMERO DE EXAMENES RECIBIDOS: " + CONEXIONES);
                servidor.close();
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
        System.out.println("Servidor finalizado...");
    }

    // accion cuando pulsamos boton salir
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salir) { // SE PULSA SALIR
            try {
                servidor.close(); // cierro el servidor
            } catch (IOException e1) {
                System.out.println("IOException: " + e1.getMessage());
            }
            System.exit(0);
        }
    }
}
