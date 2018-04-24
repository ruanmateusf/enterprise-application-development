package br.com.fiap.jpa.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import br.com.fiap.jpa.dao.ConsultaDAO;
import br.com.fiap.jpa.dao.impl.ConsultaDAOImpl;
import br.com.fiap.jpa.entity.Consulta;
import br.com.fiap.jpa.entity.Medico;
import br.com.fiap.jpa.entity.Paciente;
import br.com.fiap.jpa.singleton.EntityManagerFactorySingleton;

public class ChaveCompostaTeste {
	
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		Medico medico = new Medico(12345, "Drauzio Varella", "Geral");
		Paciente paciente = new Paciente("Gabriel", new GregorianCalendar(2014, Calendar.MAY, 16));
		Consulta consulta = new Consulta(medico, paciente, Calendar.getInstance(), "10", true);
		
		ConsultaDAO dao = new ConsultaDAOImpl(em);
		
		try {
			dao.inserir(consulta);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		em.close();
		fabrica.close();
	}
}
