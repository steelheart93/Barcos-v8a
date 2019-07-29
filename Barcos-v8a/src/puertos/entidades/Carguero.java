package puertos.entidades;

import javax.persistence.Entity;

/**
 * Un barco que transporta carga entre puertos
 * @author Sandra V. Hurtado
 * @version 3.5
 */
@Entity
public class Carguero extends Barco {
	
	private boolean liquidos;
	
	/**
	 * Constructor por defecto. Se tiene para poder usar el API de persistencia JPA
	 */
	public Carguero() {	
	}
	
	/**
	 * Constructor de objetos Cargueros
	 * @param matricula	el número de matrícula del barco, que lo identifica
	 * @param nacionalidad	la nacionalidad del barco (dada por el país de origen)
	 * @param volumen	el espacio total del barco, en m3
	 * @param liquidos	indicación (true/false) de si puede llevar líquidos o no
	 */
	public Carguero(String matricula, String nacionalidad, double volumen, boolean liquidos) {
		super(matricula, nacionalidad, volumen);
		this.liquidos = liquidos;
	}
	
	/**
	 * Calcula la capacidad de carga del barco.
	 * @return	La capacidad de carga, en metros cúbicos
	 */
	@Override
	public double calcularCapacidad() {
		double capacidad = getVolumen() * 0.8;
		if (capacidad > 100 && liquidos) {
			capacidad-=50;
		}
		return capacidad;
	}
	
	public boolean getLiquidos() {
		return liquidos;
	}
	
	/******
	 * Métodos set para los atributos. Se tienen para poder cumplir con lo
	 * requerido por el API de persistencia JPA 
	 */
	public void setLiquidos(boolean liquidos) {
		this.liquidos = liquidos;
	}
	
}
