/*
* Javier Abellan, 9 Dic 2003
*
* Programa Cliente de un socket INET, que se conectar� con un servidor java.
*/
#include <stdio.h>
#include <Socket_Cliente.h>
#include <Socket.h>

main () {
	/*
	* Descriptor del socket y buffer para datos
	*/
	int Socket_Con_Servidor;
    int Longitud_Cadena = 0;
    int Aux;
	char Cadena[100];

	/*
	* Se abre la conexion con el servidor, pasando el nombre del ordenador
	* y el servicio solicitado.
	* "localhost" corresponde al nombre del mismo ordenador en el que
	* estamos corriendo. Esta dado de alta en /etc/hosts
	* "cpp_java" es un servicio dado de alta en /etc/services.
   * El servicio debe ser 35557 que es el puerto que va a atender el servidor
   * java.
	*/
	Socket_Con_Servidor = Abre_Conexion_Inet ("localhost", "cpp_java");
	if (Socket_Con_Servidor == 1){
		printf ("No puedo establecer conexion con el servidor\n");
		exit (-1);
	}

   /* Se lee un entero con la longitud de la cadena, incluido el \0 */
   Lee_Socket (Socket_Con_Servidor, (char *)&Aux, sizeof(int));
   Longitud_Cadena = ntohl (Aux);
   printf ("Cliente C: Recibido %d\n", Longitud_Cadena-1);

   /* Se lee la cadena de la longitud indicada */
   Lee_Socket (Socket_Con_Servidor, Cadena, Longitud_Cadena);
   printf ("Cliente C: Recibido %s\n", Cadena);
   
	/*
    * Se va a enviar una cadena de 6 caracteres, incluido el \0. Previamente se
    * env�a un entero con el 6.
	*/
	strcpy (Cadena, "Adios");
    Longitud_Cadena = 6;

   /* Antes de enviar el entero hay que transformalo a formato red */
   Aux = htonl (Longitud_Cadena);
   Escribe_Socket (Socket_Con_Servidor, (char *)&Aux, sizeof(Longitud_Cadena));
   printf ("Cliente C: Enviado %d\n", Longitud_Cadena-1);

   /* Se env�a la cadena */
   Escribe_Socket (Socket_Con_Servidor, Cadena, Longitud_Cadena);
   printf ("Cliente C: Enviado %s\n", Cadena);

	/*
	* Se cierra el socket con el servidor
	*/
	close (Socket_Con_Servidor);
}
