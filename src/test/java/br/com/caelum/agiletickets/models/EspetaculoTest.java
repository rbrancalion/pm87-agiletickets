package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void criaEspetaculoDiario() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		DateTime dataInicio = new DateTime(2010,01,01,14,00,00);
		DateTime dataFim = dataInicio.plusDays(3);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio.toLocalDate(), dataFim.toLocalDate(), dataInicio.toLocalTime(), Periodicidade.DIARIA);
		int dias = Days.daysBetween(dataInicio, dataFim).getDays();
		
		Assert.assertEquals(dias, sessoes.size());
		
	}
	@Test
	public void criaEspetaculoSemanalComMaisDeSeteDias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		DateTime dataInicio = new DateTime(2010,01,01,14,00,00);
		DateTime dataFim = dataInicio.plusMonths(1);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio.toLocalDate(), dataFim.toLocalDate(), dataInicio.toLocalTime(), Periodicidade.SEMANAL);
		int apresentacoes = 5;
		
		Assert.assertEquals(apresentacoes, sessoes.size());
	}
	
	@Test
	public void criaEspetaculoComFimMenorQueInicio() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		DateTime dataInicio = new DateTime(2010,01,01,14,00,00);
		DateTime dataFim = dataInicio.plusMonths(-1);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio.toLocalDate(), dataFim.toLocalDate(), dataInicio.toLocalTime(), Periodicidade.SEMANAL);
		int apresentacoes = 0;
		
		Assert.assertEquals(apresentacoes, sessoes.size());
		
	}
	
	@Test
	public void criaEspetaculoComInicioIgaulAoFim() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		DateTime dataInicio = new DateTime(2010,01,01,14,00,00);
		DateTime dataFim = dataInicio;
		
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio.toLocalDate(), dataFim.toLocalDate(), dataInicio.toLocalTime(), Periodicidade.DIARIA);
		int dias = 1;
		
		Assert.assertEquals(dias, sessoes.size());
		
	}
	
	@Test
	public void criaEspetaculoSemanalComMenosDeSeteDias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		DateTime dataInicio = new DateTime(2010,01,01,14,00,00);
		DateTime dataFim = dataInicio.plusDays(5);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio.toLocalDate(), dataFim.toLocalDate(), dataInicio.toLocalTime(), Periodicidade.SEMANAL);
		int apresentacoes = 1;
		
		Assert.assertEquals(apresentacoes, sessoes.size());
	}
	
	@Test
	public void criaEspetaculoSemanalComMesmoInicioEFim() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		DateTime dataInicio = new DateTime(2010,01,01,14,00,00);
		DateTime dataFim = dataInicio;
		
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio.toLocalDate(), dataFim.toLocalDate(), dataInicio.toLocalTime(), Periodicidade.SEMANAL);
		int apresentacoes = 1;
		
		Assert.assertEquals(apresentacoes, sessoes.size());
	}
}