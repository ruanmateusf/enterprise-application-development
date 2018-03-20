package br.com.fiap.jpa.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.jpa.dao.NotaFiscalDAO;
import br.com.fiap.jpa.dao.PedidoDAO;
import br.com.fiap.jpa.dao.impl.NotaFiscalDAOImpl;
import br.com.fiap.jpa.dao.impl.PedidoDAOImpl;
import br.com.fiap.jpa.entity.NotaFiscal;
import br.com.fiap.jpa.entity.Pedido;
import br.com.fiap.jpa.exception.CommitException;
import br.com.fiap.jpa.singleton.EntityManagerFactorySingleton;

public class CadastroTeste {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = 
			EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		NotaFiscalDAO notaDao = new NotaFiscalDAOImpl(em);
		PedidoDAO pedidoDao = new PedidoDAOImpl(em);
		
		Pedido pedido = new Pedido(Calendar.getInstance(),
				"10 pizzas de portuguesa");
		NotaFiscal nota = new NotaFiscal(
				new GregorianCalendar(2018, Calendar.MARCH, 16),
				400, pedido);
		
		try {
			//pedidoDao.inserir(pedido);
			notaDao.inserir(nota);
			notaDao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
		}
		
		em.close();
		fabrica.close();
	}
	
}
