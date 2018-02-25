package hcurse.engine;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Clock {
	private Date date;
	private DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

	public Clock() {

		date = new Date();
	}

	public String toString() {
		return (shortDateFormat.format(date));
	}

	public void addMinute(int nbMinute) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, nbMinute);
		date = cal.getTime();
	}

}
