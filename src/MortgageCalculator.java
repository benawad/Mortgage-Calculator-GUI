import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MortgageCalculator {
	
	double principle;
	int loanTerm;
	double loanRate;
	int finishedMonth;
	int finishedYear;
	public MortgageCalculator(double principle, int loanTerm, double loanRate){
		this.principle = principle;
		this.loanTerm = loanTerm;
		this.loanRate = loanRate;
	}
	
	public double getMonthlyPayment(double extra){
		int termOfLoanMonth = loanTerm * 12;
        double monthlyInterestRates = loanRate / (double)(12 * 100);
		double monthlyMortgage = principle * (monthlyInterestRates / (1 - Math.pow((1+monthlyInterestRates), -(termOfLoanMonth))));
		return monthlyMortgage + extra;
	}
	
	public String[] getColumns(){
		String[] array = {"Payment", "Principle", "Interest", "Balance"};
		return array;
	}
	
	public String[][] getAmortizationData(double extraMonthly, double extraYearly, int month, double oneTime, int oneTimeMonth, int oneTimeYear, int currentMonth, int currentYear){
		int termOfLoanMonth = loanTerm * 12;
		List<String[]> data = new ArrayList<>();
        double monthlyInterestRates = loanRate / (double)(12 * 100);
        double monthlyMortgage = principle * (monthlyInterestRates / (1 - Math.pow((1+monthlyInterestRates), -(termOfLoanMonth))));
        monthlyMortgage += extraMonthly;
        double totalMortgage = monthlyMortgage * termOfLoanMonth;

        double totalInterest = totalMortgage - principle;
        double totalAmountDue = principle;
        double interestPaid;
        double principlePaid;
        String sTotalAmountDue;
        int i = 0;
        do{
            interestPaid = totalAmountDue * monthlyInterestRates;
            if(interestPaid <= 0){
                interestPaid = 0;
            }
            principlePaid = monthlyMortgage - interestPaid;
            // increase principle paid once a year
            if(month == currentMonth){
            	principlePaid += extraYearly;
            }
            // add the one-time payment
            if(oneTimeYear == currentYear && oneTimeMonth == currentMonth){
            	principlePaid += oneTime;
            }
            totalAmountDue = totalAmountDue - principlePaid;
            if(totalAmountDue < 0){
                totalAmountDue = 0;
            }
            
            
            String[] curr = new String[4];
            curr[0] = (i+1) + "";
            curr[1] = String.format("$%.2f", principlePaid);
            curr[2] = String.format("$%.2f", interestPaid);
            sTotalAmountDue = String.format("$%.2f", totalAmountDue);
            curr[3] = sTotalAmountDue;
            data.add(curr);
            i++;
            
            // a month has gone by so increase month
            currentMonth++;
            // check to see if new year
            if(currentMonth == 13){
            	currentMonth = 1;
            	currentYear++;
            }
            
        } while (!sTotalAmountDue.equals("$0.00"));
        
        String[][] dataArray = new String[data.size()][4];
        for(int j = 0; j < data.size(); j++){
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
