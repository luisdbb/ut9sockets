package tarintro2g;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.table.TableModel;

public class Alumno extends JFrame implements ActionListener, Runnable {

    private javax.swing.JButton BSolicitar;
    private javax.swing.JButton BDar;
    private javax.swing.JButton BEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextSolicitar;
    private javax.swing.JTextField jTextDarPreg;
    private javax.swing.JTextField jTextDarResp;

    private static final long serialVersionUID = 1L;

    static MulticastSocket ms = null;
    static byte[] buf = new byte[1000];
    static InetAddress grupo = null;
    static int puerto = 12345;// puerto multicast

    boolean repetir = true;
    String nombre; //nombre del alumno
    Examen examen; //examen del alumno

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre:");
        if (!nombre.trim().equals("")) {
            establecerConexion();
            Alumno alumno = new Alumno(nombre);
            alumno.setBounds(0, 0, 490, 495);
            alumno.setVisible(true);
            new Thread(alumno).start();
        } else {
            System.out.println("El nombre esta vacio....");
        }
    }

    public Alumno(String nom) {
        super(" VENTANA DE: " + nom);
        this.nombre = nom;
        initComponents();
    }

    /**
     * Función que crea el socket multicast y se une al grupo
     */
    public static void establecerConexion() {
        
    }

    /**
     * Función que crea un String con el texto: "NOMBRE solicita la pregunta
     * PREG" y lo envía al grupo multicast a través de un DatagramPacket
     *
     * @param pregSolicitada pregunta que solicita el
     */
    private void solicitarPregunta(String pregSolicitada) {
       
    }

    /**
     * Función que crea un String con el texto: "NOMBRE dice: pregunta PREG ->
     * respuesta RESP" y lo envía al grupo multicast a través de un
     * DatagramPacket
     *
     * @param preg pregunta que responde el alumno
     * @param resp respuesta que da el alumno para la pregunta PREG
     */
    private void responderPregunta(String preg, String resp) {
        
    }

    /**
     * *
     * Función que realiza la conexión TCP con el servidor, enviando el objeto
     * Examen que se pasa como parámetro y recibiendo la puntuación obtenida a
     * través de un int
     *
     * @param examen que se enviará al servidor para ser puntuado
     * @return entero con la puntuación del examen (recibida desde el servidor)
     * o -1 en caso de error
     */
    private int enviarExamen(Examen examen) {
        return -1;
    }

    /**
     * Función para abandonar el grupo y cerrar el socket multicast
     */
    private void terminarConexion() {
      
    }

    // accion cuando pulsamos botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BSolicitar) { // SE PULSA EL SOLICITAR
            String pregSolicitada = this.jTextSolicitar.getText();
            if (!pregSolicitada.isEmpty()) {
                solicitarPregunta(pregSolicitada);
                this.jTextSolicitar.setText("");
            }
        }
        if (e.getSource() == BDar) { // SE PULSA BDar
            String pregDada = this.jTextDarPreg.getText();
            String respDada = this.jTextDarResp.getText();
            if (!pregDada.isEmpty() && !respDada.isEmpty()) {
                responderPregunta(pregDada, respDada);
                this.jTextDarPreg.setText("");
                this.jTextDarResp.setText("");
            }
        }
        if (e.getSource() == BEnviar) { // SE PULSA BEnviar
            int resp = JOptionPane.showConfirmDialog(this, "¿Desea enviar la prueba para corregir?");
            if (resp == JOptionPane.YES_OPTION) {
                jTable1.clearSelection();
                jTable1.editCellAt(0, NORMAL);
                TableModel modelo2 = jTable1.getModel();
                this.examen = new Examen(modelo2, this.nombre);
                int puntuacion = enviarExamen(this.examen);
                JOptionPane.showMessageDialog(this, "Ha sacado un " + puntuacion + " en el examen.");
                terminarConexion();
            }
        }
    }

    // DESDE EL METODO RUN SE RECIBEN LOS MENSAJES Y SE PINTAN EN LA PANTALLA
    public void run() {
        while (repetir) {
            try {
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                ms.receive(p);
                String texto = new String(p.getData(), 0, p.getLength());
                jTextArea1.append(texto + "\n");
            } catch (SocketException s) {
                System.out.println(s.getMessage());
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
        System.exit(0);
    }

    private void initComponents() {
        BSolicitar = new javax.swing.JButton();
        BDar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextSolicitar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextDarPreg = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextDarResp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        BEnviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        BSolicitar.setText("Solicitar");
        BSolicitar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        BSolicitar.setName("botonSolicitar"); 
        BSolicitar.addActionListener(this);

        BDar.setText("Dar respuesta");
        BDar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        BDar.setName("botonDar"); 
        BDar.addActionListener(this);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        jLabel1.setText("Chat:");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("textChat");
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        jLabel2.setText("Solicitar respuesta:");

        jLabel3.setText("Por favor, que alguien me diga la respuesta de la pregunta");

        jTextSolicitar.setName("textSolicitar"); 

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        jLabel4.setText("Dar respuesta al resto:");

        jLabel5.setText("Creo que a la pregunta");

        jTextDarPreg.setName("textDarPreg"); 

        jLabel6.setText("es la respuesta");

        jTextDarResp.setName("textDarResp"); 

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        jLabel7.setText("Mi exámen:");

        BEnviar.setText("Enviar");
        BEnviar.setName("BotonEnviar"); 
        BEnviar.addActionListener(this);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"1", null},
                    {"2", null},
                    {"3", null},
                    {"4", null},
                    {"5", null},
                    {"6", null},
                    {"7", null},
                    {"8", null},
                    {"9", null},
                    {"10", null}
                },
                new String[]{
                    "Pregunta", "Respuesta"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.setRowSelectionAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel3)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jTextSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(BSolicitar))
                                                                .addComponent(jLabel7)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jLabel4)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel5)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jTextDarPreg, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel6)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTextDarResp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(24, 24, 24)
                                                                .addComponent(BDar))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(BEnviar)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel2)
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(BSolicitar)
                                        .addComponent(jLabel3)
                                        .addComponent(jTextSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jTextDarPreg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addComponent(jTextDarResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BDar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(81, 81, 81)
                                                .addComponent(BEnviar)))
                                .addContainerGap(63, Short.MAX_VALUE))
        );
        pack();
    }
}
