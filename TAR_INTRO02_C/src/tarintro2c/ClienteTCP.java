package tarintro2c;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Esta clase implementa un hilo que establece conexión TCP con un servidor, le envía un objeto Coche a
 * dicho servidor, recibe dicho objeto modificado del mismo servidor y muestra el objeto Coche modificado
 * por consola
*/
public class ClienteTCP extends Thread {
    
    String nombre;
    
    public ClienteTCP(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Método que implementa el comportamiento del hilo
    */
    @Override
    public void run() {
        try {
            System.out.println("\t ClienteTCP " + nombre + " - abre un socket en el cliente, y dicho socket establece "
                    + "una conexión con el socket del servidor situado en el puerto 30500 de la 127.0.0.1");
            String equipoServidor = "127.0.0.1";
            int puertoServidor = 30500;
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);
            gestionarComunicacion(socketCliente);
            
            socketCliente.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Método que implementa el diálogo con el cliente
     * @param socketCliente Socket que se usa para realizar la comunicación con el servidor
    */
    public void gestionarComunicacion (Socket socketCliente) {
        Coche coche = new Coche("2323JGD","Ford","Escort",5);
        System.out.println("\t ClienteTCP " + nombre + " - construye el objeto " + coche.toString());
        try {
            System.out.println("\t ClienteTCP " + nombre + " - envía el objeto al servidor");
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(coche);
            System.out.println("\t ClienteTCP " + nombre + " -  ha recibido un objeto del servidor");
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            coche = (Coche) ois.readObject();
            System.out.println("\t ClienteTCP " + nombre + " -> Objeto recibido del Servidor: " + coche.toString());
            ois.close();
            oos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
