package puertos.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import puertos.entidades.Barco;
import puertos.entidades.Carguero;
import puertos.entidades.Velero;

/**
 * Usa JPA como repositorio de los datos de los barcos, y ofrece los servicios
 * definidos en IRepositorioBarcos.
 * Usa dos tablas (una para cada una de las clases hijas)
 * 
 * @author Oscar Franco Bedoya - Sandra V. Hurtado
 * @version 1.0-a  (alterno)
 */
public class OrmBarcos implements RepositorioBarcos {
	
	private EntityManager entityManager;

	public OrmBarcos() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Barcos8a");
		entityManager = fabrica.createEntityManager();
	}

	/**
	 * Adiciona un barco a la base de datos
	 * @param barco el objeto barco que se adicionará
	 * @return valor booleano indicando si se pudo guardar en la base de datos o no
	 */
	@Override
	public boolean adicionarBarco(Barco barco) {
		try	{
			entityManager.getTransaction().begin();
			entityManager.persist(barco);
			entityManager.getTransaction().commit();
		}
		catch (Exception errorCrear)	{
			return false;
		}
		return true;
	}

	/**
	 * Buscar un barco en la base de datos a partir de su matrícula
	 * @param matricula	número de matrícula del barco que se desea buscar,
	 * 	  				debe ser diferente de null
	 * @return	el objeto barco con la matrícula buscada, o null si no se encuentra.
	 */
	@Override
	public Barco buscarBarco(String matricula) {
		
		Barco barco = entityManager.find(Carguero.class, matricula);
		if (barco == null) {
			barco = entityManager.find(Velero.class, matricula);
		}
		return barco;
	}

	/**
	 * Consulta toda la lista de los barcos que hay
	 * @return	la lista (List) con los barcos
	 */
	@Override
	public List<Barco> consultarBarcos() {

		List<Barco> listaBarcos = new ArrayList<Barco>();
		
		List<Carguero> listaCargueros = consultar(Carguero.class);
		listaBarcos.addAll(listaCargueros);
		
		List<Velero> listaVeleros = consultar(Velero.class);
		listaBarcos.addAll(listaVeleros);
		
		return listaBarcos;
	}

	/**
	 * Elimina un barco de la base de datos
	 * @param barco	el objeto barco que se eliminará
	 * @return valor booleano indicando si se pudo borrar o no
	 * 				(porque no se encontró el barco en el repositorio).
	 */
	@Override
	public boolean borrarBarco(Barco barco) {
		try	{
			entityManager.getTransaction().begin();
			entityManager.remove(barco);
			entityManager.getTransaction().commit();
		}
		catch (Exception errorBorrar)	{
			return false;
		}
		return true;
	}
	
	/**
	 * Consulta todos los registros de una tabla (clase)
	 * @param tipoBarco el objeto class a partir del cual se realizará la consulta
	 * @return	una lista de objetos consultados
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> consultar(Class<T> tipoBarco) {
		Query query = entityManager.createQuery("select b from "+tipoBarco.getName()+" b");
		return query.getResultList();
	}
	
}
