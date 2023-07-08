package tarintro2c;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Esta clase implementa un hilo que establece conexión UDP con un servidor, le envía un objeto Coche a
 * dicho servidor, recibe dicho objeto modificado del mismo servidor y muestra el objeto Coche modificado
 * por consola
*/
public class ClienteUDP extends Thread {
    
    String nombre;
    
    public ClienteUDP(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Método que implementa el comportamiento del hilo
    */
    @Override
    public void run() {
        try {
            DatagramPacket datagrama;
            byte[] mensajeBytes = new byte[1024];
            System.out.println("\t ClienteUDP "+nombre+" abre un socket UDP en el puerto 45000 de la máquina local");
            int puertoCliente = 45000;
            DatagramSocket socketDatagrama = new DatagramSocket(puertoCliente);

            InetAddress servidor = InetAddress.getLocalHost();
            int puertoServidor = 30500;
            
            System.out.println("\t ClienteUDP "+nombre+" crea el objeto Coche");
            Coche coche = new Coche("1234QWE","Ford","Escort",4);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(coche);
            mensajeBytes = baos.toByteArray();
            System.out.println("\t ClienteUDP "+nombre+" construyen el datagrama, con el objeto incluido en dicho datagrama");
            datagrama = new DatagramPacket(mensajeBytes,mensajeBytes.length,servidor,puertoServidor);
            socketDatagrama.send(datagrama);
            
            System.out.println("\t ClienteUDP "+nombre+" reciben datagrama y extrae el objeto adjunto.");
            mensajeBytes = new byte[1024];
            datagrama = new DatagramPacket(mensajeBytes,mensajeBytes.length);
            socketDatagrama.receive(datagrama);
            ByteArrayInputStream bais = new ByteArrayInputStream(mensajeBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            coche = (Coche) ois.readObject();
            System.out.println("\t ClienteUDP "+nombre+" -> Objeto recibido: " + coche.toString());
            System.out.println("\t ClienteUDP "+nombre+": cerrando datagrama y streams");
            socketDatagrama.close();
            oos.close();
            baos.close();
            ois.close();
            bais.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

}
