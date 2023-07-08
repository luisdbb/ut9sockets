package tarintro2c;

import java.io.Serializable;

/**
 * Clase que implementa un objeto coche (matrícula, marca, modelo y cantidad de puertas)
*/
public class Coche implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String matricula;
    String marca;
    String modelo;
    int cantidadPuertas;

    /**
     * Constructor de la clase
     * @param matricula Matrícula del coche
     * @param marca Marca del coche
     * @param modelo Modelo del coche
     * @param cantidadPuertas Cantidad de puertas del coche
    */
    public Coche(String matricula, String marca, String modelo, int cantidadPuertas) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.cantidadPuertas = cantidadPuertas;
    }

    /**
     * Método getter del atributo matricula
     * @return Matrícula del coche
    */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Método setter del atributo matricula
     * @param matricula Matrícula del coche
    */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Método getter del atributo marca
     * @return Marca del coche
    */
    public String getMarca() {
        return marca;
    }

    /**
     * Método setter del atributo marca
     * @param marca Marca del coche
    */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Método getter del atributo modelo
     * @return Modelo del coche
    */
    public String getModelo() {
        return modelo;
    }

    /**
     * Método setter del atributo modelo
     * @param modelo Modelo del coche
    */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Método getter del atributo cantidadPuertas
     * @return Cantidad de puertas del coche
    */
    public int getCantidadPuertas() {
        return cantidadPuertas;
    }

    /**
     * Método setter del atributo cantidadPuertas
     * @param cantidadPuertas Cantidad de puertas del coche
    */
    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }
    
    /**
     * Método toString de la clase
     * @return Los datos del coche
    */
    @Override
    public String toString() {
        return matricula + " " + marca + " " + modelo + " (" + cantidadPuertas + " puertas)";
    }

}
