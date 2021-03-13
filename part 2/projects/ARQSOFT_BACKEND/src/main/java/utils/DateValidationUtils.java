package utils;

import java.util.Calendar;
import java.util.Date;

import springboot.exception.ResourceNotFoundException;

public class DateValidationUtils {

	/**
	 * Checks if a date is between an interval of dates defined by a configuration
	 * file
	 * 
	 * @param date to verified if is between two other dates
	 * @return true if is between and false otherwise
	 */
	public static boolean isBetweenDates(Date date) {
		Date dateFrom = getDateFromDaysApart(
				Integer.parseInt(PropertiesObtain.getPropertiesValue("encomenda.dateMin")) - 1);
		Date dateTo = getDateFromDaysApart(Integer.parseInt(PropertiesObtain.getPropertiesValue("encomenda.dateMax")));

		return dateFrom.compareTo(date) * date.compareTo(dateTo) >= 0;
	}

	/**
	 * Gets the date days apart from now
	 * 
	 * @param days apart from today
	 * @return the date days apart from today
	 */
	public static Date getDateFromDaysApart(int daysApart) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, daysApart);
		return calendar.getTime();
	}
	
	/**
	 * Determines if an hour is between to other hours defined in a configuration file
	 * 
	 * @param horaRequisitada hour to be checked
	 * @return true if hour is between the other two dates
	 */
	public static boolean isHourBetweenInterval(String horaRequisitada) {
		String horaInicial = PropertiesObtain.getPropertiesValue("entrega.horaInicial");
		String horaFinal = PropertiesObtain.getPropertiesValue("entrega.horaFinal");

		if (horaRequisitada != null) {
			
			String[] parts = horaInicial.split(":");
			Calendar calInicial = Calendar.getInstance();
			calInicial.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
			calInicial.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
			calInicial.add(Calendar.MINUTE, -2);
			
			parts = horaFinal.split(":");
			Calendar calFinal = Calendar.getInstance();
			calFinal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
			calFinal.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
			
			parts = horaRequisitada.split(":");
			Calendar calRequisitada = Calendar.getInstance();
			calRequisitada.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
			calRequisitada.set(Calendar.MINUTE, Integer.parseInt(parts[1]));

			if (calRequisitada.before(calFinal) && calRequisitada.after(calInicial)) {
				return true;
			} else {
				throw new ResourceNotFoundException("A hora da encomenda deve ser feita entre as " + horaInicial
						+ " e as " + horaFinal + " horas!");
			}
		} else {
			throw new ResourceNotFoundException("Deve ser definida uma hora para a encomenda!");
		}
	}

}