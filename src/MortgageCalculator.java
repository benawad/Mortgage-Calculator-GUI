import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class MortgageCalculator {

	double principle;
	int loanTerm;
	double loanRate;
	int finishedMonth;
	int finishedYear;

	public MortgageCalculator(double principle, int loanTerm, double loanRate) {
		this.principle = principle;
		this.loanTerm = loanTerm;
		this.loanRate = loanRate;
	}

	public double getMonthlyPayment() {
		int termOfLoanMonth = loanTerm * 12;
		double monthlyInterestRates = loanRate / (double) (12 * 100);
		double monthlyMortgage = principle
				* (monthlyInterestRates / (1 - Math.pow((1 + monthlyInterestRates), -(termOfLoanMonth))));
		return monthlyMortgage;
	}

	public String[] getColumns() {
		String[] array = { "Date", "Payment", "Principle", "Interest", "Total Interest", "Balance" };
		return array;
	}

	public String[][] getAmortizationData(double extraMonthly, double extraYearly, int month, double oneTime,
			int oneTimeMonth, int oneTimeYear, int currentMonth, int currentYear) {
		int termOfLoanMonth = loanTerm * 12;
		List<String[]> data = new ArrayList<>();
		double monthlyInterestRates = loanRate / (double) (12 * 100);
		double monthlyMortgage = principle
				* (monthlyInterestRates / (1 - Math.pow((1 + monthlyInterestRates), -(termOfLoanMonth))));
		monthlyMortgage += extraMonthly;
		double totalMortgage = monthlyMortgage * termOfLoanMonth;

		double totalInterest = 0;
		double totalAmountDue = principle;
		double interestPaid;
		double principlePaid;
		String sTotalAmountDue;
		int i = 0;
		double lastPrinciplePaid = 0;
		// start at the next month
		if(currentMonth == 11){
			currentMonth = 0;
			currentYear++;
		} else {
			currentMonth++;
		}
		do {
			interestPaid = totalAmountDue * monthlyInterestRates;
			if (interestPaid <= 0) {
				interestPaid = 0;
			}
			totalInterest += interestPaid;
			principlePaid = monthlyMortgage - interestPaid;
			// increase principle paid once a year
			// except for the first month
			if (month == currentMonth) {
				principlePaid += extraYearly;
			}
			// add the one-time payment
			if (oneTimeYear == currentYear && oneTimeMonth == currentMonth) {
				principlePaid += oneTime;
			}
			if (principlePaid > totalAmountDue) {
				lastPrinciplePaid = totalAmountDue;
			}
			totalAmountDue = totalAmountDue - principlePaid;
			if (totalAmountDue < 0) {
				principlePaid = lastPrinciplePaid;
				totalAmountDue = 0;
			}

			String[] curr = new String[6];
			String[] months = (new DateFormatSymbols()).getShortMonths();
			curr[0] = String.format("%s. %d", months[currentMonth], currentYear);
			curr[1] = String.format("$%.2f", monthlyMortgage);
			curr[2] = String.format("$%.2f", principlePaid);
			curr[3] = String.format("$%.2f", interestPaid);
			sTotalAmountDue = String.format("$%.2f", totalAmountDue);
			curr[4] = String.format("$%.2f", totalInterest);
			curr[5] = sTotalAmountDue;
			data.add(curr);
			i++;

			// a month has gone by so increase month
			currentMonth++;
			// check to see if new year
			if (currentMonth == 12) {
				currentMonth = 0;
				currentYear++;
				// don't increment year on last payment
				if (sTotalAmountDue.equals("$0.00")) {
					System.out.println("Called");
					currentYear--;
				}
			}
		} while (!sTotalAmountDue.equals("$0.00"));

		// you end on the last payment month
		if (currentMonth == 0) {
			currentMonth = 11;
		} else {
			currentMonth--;
		}

		String[][] dataArray = new String[data.size()][4];
		for (int j = 0; j < data.size(); j++) {
			dataArray[j] = data.get(j);
		}

		// set the month and year the mortgaged is paid off
		finishedMonth = currentMonth;
		finishedYear = currentYear;

		return dataArray;
	}

	public int getFinishedMonth() {
		return finishedMonth;
	}

	public int getFinishedYear() {
		return finishedYear;
	}

}
