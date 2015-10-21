import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MortgageCalculator {
	
	double principle;
	int loanTerm;
	double loanRate;
	
	public MortgageCalculator(double principle, int loanTerm, double loanRate){
		this.principle = principle;
		this.loanTerm = loanTerm;
		this.loanRate = loanRate;
	}
	
	public double getMonthlyPayment(){
		int termOfLoanMonth = loanTerm * 12;
        double monthlyInterestRates = loanRate / (double)(12 * 100);
		double monthlyMortgage = principle * (monthlyInterestRates / (1 - Math.pow((1+monthlyInterestRates), -(termOfLoanMonth))));
		return monthlyMortgage;
	}
	
	public String[] getColumns(){
		String[] array = {"Payment", "Principle", "Interest", "Balance"};
		return array;
	}
	
	public String[][] getAmortizationData(double extraMonthly, double extraYearly, int month, double oneTime, int oneTimeMonth, int oneTimeYear){
		int termOfLoanMonth = loanTerm * 12;
		String[][] data = new String[termOfLoanMonth][4];
        double monthlyInterestRates = loanRate / (double)(12 * 100);
        double monthlyMortgage = principle * (monthlyInterestRates / (1 - Math.pow((1+monthlyInterestRates), -(termOfLoanMonth))));
        double totalMortgage = monthlyMortgage * termOfLoanMonth;

        double totalInterest = totalMortgage - principle;
        double totalAmountDue = principle;
        double interestPaid;
        double principlePaid;

        for(int i = 0; i < termOfLoanMonth; i++){
            interestPaid = totalAmountDue * monthlyInterestRates;
            if(interestPaid <= 0){
                interestPaid = 0;
            }
            principlePaid = monthlyMortgage - interestPaid;
            totalAmountDue = totalAmountDue - principlePaid;
            if(totalAmountDue < 0){
                totalAmountDue = 0;
            }
            
            String[] curr = data[i];
            curr[0] = (i+1) + "";
            curr[1] = (Math.round(principlePaid*100.0)/100.0)+"";
            curr[2] = (Math.round(interestPaid *100.0)/100.0)+"";
            curr[3] = (Math.round(totalAmountDue *100.0)/100.0)+"";
        }
        return data;
	}
	
}
