package hcurse.engine;

import java.util.Date;

public class DatePrinter extends Thread {

	int minute;
	Date date;

	public DatePrinter(int minute) {
		this.minute = minute;
	}

	public void run() {

		for (int i = 0; i < minute; i++) {

		}
	}
}
