package tarintro2g;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {

    ObjectInputStream fentrada; //el hilo servidor lee objetos de tipo Examen
    DataOutputStream fsalida; //el hilo servidor escribe enteros con las puntuaciones
    Socket socket = null;
    private final String[] solucion = {"1", "2", "2", "3", "1", "2", "2", "3", "1", "1"};

    /**
     * *
     * Cada HiloServidor recibe como parámetro el socket para comunicarse con el
     * alumno recién conectado
     *
     * @param s Socket para la comunicación propia con cada alumno
     */
    public HiloServidor(Socket s) {
        socket = s;
        try {
            // CREO FLUJOS DE entrada/salida para leer los Exams y mandar la puntuación
            fentrada = new ObjectInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    /**
     * El HiloServidor queda continuamente a la espera de leer un ojeto de tipo
     * Examen para proceder a continuación con su corrección y mandar la
     * puntuación al alumno
     */
    @Override
    public void run() {
        Servidor.mensaje.setText("NUMERO DE EXAMENES RECIBIDOS: " + Servidor.CONEXIONES);
        while (true) {
            try {
                Examen exam = (Examen) fentrada.readObject();
                int puntuacion = corregir(exam);
                Servidor.textarea.append(exam.getNombreAlumno() + " envió su exámen desde la IP: " + this.socket.getInetAddress() + " y sacó un " + puntuacion + ".\n");
                fsalida.writeInt(puntuacion);
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                break;
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException: " + e.getMessage());
                break;
            }
        }
    }

    /**
     * Función que recibe un Examen y lo corrige de acuerdo a la solución,
     * devolviendo un entero con la puntuación obtenida
     *
     * @param examen Examen para ser corregido
     * @return entero con la puntuación tras la corrección
     */
    private int corregir(Examen examen) {
        int puntuacion = 0;
        for (int i = 0; i <= solucion.length - 1; i++) {
            String respAl = examen.getExamen().get(Integer.toString(i + 1));
            if (respAl != null && respAl.equals(solucion[i])) {
                puntuacion++;
            }
        }
        return puntuacion;
    }

}
