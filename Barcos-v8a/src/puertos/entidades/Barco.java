package puertos.entidades;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Información de un barco que llega a un puerto
 * @author Sandra V. Hurtado
 * @version 4.0-a  (alterna)
 */
@MappedSuperclass
public abstract class Barco {
	@Id
	private String matricula;
	private String nacionalidad;
	private double volumen;

	/**
	 * Constructor por defecto. Se tiene para poder usar el API de persistencia JPA
	 */
	public Barco() {	
	}
	
	/**
	 * Constructor de objetos Barco, que será usado por las clases hijas
	 * @param matricula	el número de matrícula del barco, que lo identifica
	 * @param nacionalidad	la nacionalidad del barco (dada por el país de origen)
	 * @param volumen	el espacio total del barco, en m3
	 */
	public Barco(String matricula, String nacionalidad, double volumen) {
		this.matricula = matricula;
		this.nacionalidad = nacionalidad;
		this.volumen = volumen;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public double getVolumen() {
		return volumen;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	/******
	 * Métodos set para los atributos. Se tienen para poder cumplir con lo
	 * requerido por el API de persistencia JPA 
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}	
	
	/**
	 * Calcula la capacidad de carga del barco.
	 * @return	La capacidad de carga, en metros cúbicos
	 */
	public abstract double calcularCapacidad();
	
}
