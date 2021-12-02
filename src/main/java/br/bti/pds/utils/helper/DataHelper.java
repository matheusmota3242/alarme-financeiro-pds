package br.bti.pds.utils.helper;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class DataHelper {

	public static Calendar receberCalendarAtual() {
		Calendar calendario = new GregorianCalendar();
		calendario.setTime(new Date());
		return calendario;
	}
	
	public static Calendar receberPrimeiroDia(Integer intervalo) {
		Calendar calendario = receberCalendarAtual();
		calendario.add(Calendar.DAY_OF_MONTH, intervalo);
		return calendario;
	}
	
	
}
