package puertos.entidades;

import javax.persistence.Entity;

/**
 * Un barco deportivo, que lleva pasajeros
 * @author Sandra V. Hurtado
 * @version 3.5
 */
@Entity
public class Velero extends Barco
{
	private int pasajeros;

	/**
	 * Constructor por defecto. Se tiene para poder usar el API de persistencia JPA
	 */
	public Velero() {	
	}
	
	/**
	 * Constructor de un objeto velero
	 * @param matricula	el número de matrícula del barco, que lo identifica
	 * @param nacionalidad	la nacionalidad del barco (dada por el país de origen)
	 * @param volumen	el espacio total del barco, en m3
	 * @param pasajeros	la cantidad de pasajeros que lleva el barco
	 */
	public Velero(String matricula, String nacionalidad, double volumen, int pasajeros) {
		super(matricula, nacionalidad, volumen);
		this.pasajeros = pasajeros;
	}
	
	/**
	 * Calcula la capacidad de carga del barco.
	 * @return	La capacidad de carga, en metros cúbicos
	 */
	@Override
	public double calcularCapacidad() {
		double capacidad = getVolumen() * 0.5;
		if (capacidad > 20 && pasajeros > 10) {
			capacidad-=10;
		}
		return capacidad;
	}	
	
	public int getPasajeros() {
		return pasajeros;
	}

	/******
	 * Métodos set para los atributos. Se tienen para poder cumplir con lo
	 * requerido por el API de persistencia JPA 
	 */
	public void setPasajeros(int pasajeros) {
		this.pasajeros = pasajeros;
	}
	
}
