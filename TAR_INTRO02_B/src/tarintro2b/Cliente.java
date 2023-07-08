package tarintro2b;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Esta clase implementa un hilo que establece conexión con un servidor, le envía un mensaje a dicho
 * servidor, recibe un mensaje del mismo servidor y muestra este mensaje por consola
*/
public class Cliente extends Thread {
    
    String nombre;
    
    public Cliente(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Método que implementa el comportamiento del hilo
    */
    @Override
    public void run() {
        try {
            System.out.println("\t Cliente.Consola " + nombre + " - Se abre un socket en el cliente, y dicho socket establece "
                    + "una conexión con el socket del servidor situado en el puerto 30500 de localhost");
            InetAddress equipoServidor = InetAddress.getLocalHost();
            int puertoServidor = 30500;
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);
            
            gestionarComunicacion(socketCliente);
            
            socketCliente.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Cliente "+ nombre + " FIN");
    }
    
    /**
     * Método que implementa el envío del mensaje al servidor, la recepción del mensaje de dicho
     * servidor y la muestra del mensaje recibido por consola
     * @param socketCliente Socket que se usa para realizar la comunicación con el servidor
    */
    public void gestionarComunicacion (Socket socketCliente) {
        try {
            System.out.println("\t Cliente.Consola " + nombre + " - El cliente construye el mensaje y lo envía al servidor");
            OutputStream os = socketCliente.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("SOY " + nombre + " ... HOLA SERVIDOR");
            
            InputStream is = socketCliente.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            
            //El cliente ha recibido un mensaje del servidor y procede a mostrarlo por consola
            System.out.println("\t Cliente.Consola " + nombre + " - Mensaje recibido del Servidor: \"" + dis.readUTF()+"\"");
            is.close();
            dis.close();
            os.close();
            dos.close();
        } catch (IOException e) {
        	System.out.println("Se ha producido una IOException: "+ e.getMessage());
            e.printStackTrace();
        }
    }

}
