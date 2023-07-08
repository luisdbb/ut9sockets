package tarintro2d;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class ClienteMulticast  {

    public static void main(String[] args) {
    	String nombre = "";
    	Scanner in  = new Scanner(System.in);
    	System.out.println("Dime tu nombre de cliente:");
    	nombre = in.nextLine();
    	
        byte[] mensajeBytes = new byte[1024];
        try {
            String direccionGrupoMulticast = "225.8.8.8";
            InetAddress grupoMulticast = InetAddress.getByName(direccionGrupoMulticast);
            int puertoGrupoMulticast = 35000;
            MulticastSocket ms = new MulticastSocket(puertoGrupoMulticast);
            ms.joinGroup(grupoMulticast);
            System.out.println("Se ha unido correctamente al grupo multicast.\nEsperando mensaje broadcast...");
            DatagramPacket datagrama = new DatagramPacket(mensajeBytes,mensajeBytes.length);
            ms.receive(datagrama);
            System.out.println("\t " + nombre + " - Recibido mensaje: " + new String(datagrama.getData()));
            System.out.println("Se abandona el grupo multicast.");
            ms.leaveGroup(grupoMulticast);
            ms.close();
        } catch (IOException ex) {
        	System.out.println("Se ha producido una IOException: " + ex.getMessage());
        	ex.printStackTrace();
        }
    }
}
