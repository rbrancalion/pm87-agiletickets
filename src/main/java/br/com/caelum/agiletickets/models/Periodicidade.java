package br.com.caelum.agiletickets.models;

import org.joda.time.LocalDate;

public enum Periodicidade {
	DIARIA(1) ,
	SEMANAL(7) ;
	
	private int dias;
	
	public LocalDate ProximoDia(LocalDate data){
		return data.plusDays(this.dias);
	}
	Periodicidade(int dia){
		this.dias = dia;
	}
}