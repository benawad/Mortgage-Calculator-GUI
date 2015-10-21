import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

	public Controller(){
		View frame = new View();
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
				frame.setMonthlyPayments(mc.getMonthlyPayment());
				mc.getAmortizationData(frame.getAdd1(), frame.getAdd2(), 
						frame.getAdd2Month(), frame.getAdd3(), frame.getAdd3Month(), frame.getAdd3Year(), frame.getMonth(), frame.getYear());
				String date = frame.getStartMonthString(mc.getFinishedMonth()) + ", " + frame.getDay() + " " + mc.getFinishedYear();
				frame.setFinishedDate(date);
			}
		});
		
		frame.addRecalculateTable(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int principle = frame.getMortgageAmount();
				int loanTerm = frame.getMortgageTermYears();
				double interestRate = frame.getInterestRate();
				MortgageCalculator mc = new MortgageCalculator(principle, loanTerm, interestRate);
				AmortizationTable at = new AmortizationTable(mc.getColumns(), mc.getAmortizationData(frame.getAdd1(), frame.getAdd2(), 
						frame.getAdd2Month(), frame.getAdd3(), frame.getAdd3Month(), frame.getAdd3Year(), frame.getMonth(), frame.getYear()));
				at.setVisible(true);
				// set the date the mortgage is finished
				String date = frame.getStartMonthString(mc.getFinishedMonth()) + ", " + frame.getDay() + " " + mc.getFinishedYear();
				frame.setFinishedDate(date);
			}
		});
	}
	
	public static void main(String[] args){
		Controller c = new Controller();
	}
	
}
