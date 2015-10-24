import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

	View frame;

	public Controller() {
		frame = new View();
		frame.setVisible(true);

		frame.addMonthYearComboBoxActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// update the number of days that are in the new month
				int daysInMonth = frame.calcDays(frame.getMonth(), frame.getYear());
				frame.setComboBoxDays(daysInMonth);
			}
		});

		frame.addCalculateActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int principle = frame.getMortgageAmount();
				int loanTerm = frame.getMortgageTermYears();
				double interestRate = frame.getInterestRate();
				MortgageCalculator mc = new MortgageCalculator(principle, loanTerm, interestRate);
				frame.setMonthlyPayments(mc.getMonthlyPayment(frame.getAdd1()));
				mc.getAmortizationData(frame.getAdd1(), frame.getAdd2(), frame.getAdd2Month(), frame.getAdd3(),
						frame.getAdd3Month(), frame.getAdd3Year(), frame.getMonth() + 1, frame.getYear());
				frame.setFinishedDate(getStringDate(mc.getFinishedMonth(), frame.getDay(), mc.getFinishedYear()));
			}
		});

		frame.addRecalculateTable(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int principle = frame.getMortgageAmount();
				int loanTerm = frame.getMortgageTermYears();
				double interestRate = frame.getInterestRate();
				MortgageCalculator mc = new MortgageCalculator(principle, loanTerm, interestRate);
				AmortizationTable at = new AmortizationTable(mc.getColumns(),
						mc.getAmortizationData(frame.getAdd1(), frame.getAdd2(), frame.getAdd2Month(), frame.getAdd3(),
								frame.getAdd3Month(), frame.getAdd3Year(), frame.getMonth() + 1, frame.getYear()));
				at.setVisible(true);
				// set the date the mortgage is finished
				frame.setFinishedDate(getStringDate(mc.getFinishedMonth(), frame.getDay(), mc.getFinishedYear()));
			}
		});
	}

	private String getStringDate(int month, int day, int year) {
		// check for non leap year
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					// leap year
				} else {
					// not leap year
					if (day == 29 && month == 2) {
						month++;
						day = 1;
					}
				}
			} else {
				// the year is a leap year and no change needs to be made
			}
		} else {
			// not leap year
			if (day == 29 && month == 2) {
				month++;
				day = 1;
			}
		}

		String date = frame.getStartMonthString(month) + ", " + day + " " + year;
		return date;
	}

	public static void main(String[] args) {
		Controller c = new Controller();
	}

}
