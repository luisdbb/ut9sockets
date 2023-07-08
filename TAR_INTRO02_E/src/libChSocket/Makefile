#
# Javier Abell�n. 9 Dic 2003
#
# Makefile para la construcci�n del cliente y servidor que conectan con un servidor
# y cliente de java.
#
all : Servidor Cliente

# Coloca aqui el path donde te hayas descargado y compilado la mini-librer�a de sockets
PATH_CHSOCKET= /home/luis/libchsocket 

CFLAGS = -g -I. -I$(PATH_CHSOCKET)

Servidor : Servidor.o
	cc Servidor.o -L$(PATH_CHSOCKET) -lChSocket -o Servidor

Cliente : Cliente.o
	cc Cliente.o -L$(PATH_CHSOCKET) -lChSocket -o Cliente

clean :
	rm *.o Cliente Servidor
