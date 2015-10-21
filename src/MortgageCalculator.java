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
	
    public static void main(String[] args) {
        int[] loanTerms = {7, 15, 30};
        double[] loanRates = new double[loanTerms.length];

        System.out.println("Welcome to Mortgage Calculator Program\n");

        // set the rate for each loan term
        getCurrentRates(loanTerms, loanRates);
        // get the index of the loan term and rate the user wants
        int index = getTermInformation(loanTerms, loanRates);

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Principle Amount: ");

        double principle = input.nextDouble();

        // print summary of loan details
        displayLoanSummary(principle, loanTerms[index], loanRates[index]);
        // go month by month and print principle, interest, and balance
        displayAmortTable(principle, loanTerms[index], loanRates[index]);

    }

    /*
        This method will input the interest rates for each of the 7, 15 and 30 year terms of loans
        and assign specific values to the elements of the rates array
     */
    public static void getCurrentRates(int[] loanTerms, double[] loanRates){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        for(int i = 0; i < loanTerms.length; i++){
            System.out.print("Enter current rate of " + loanTerms[i] + " of years: ");
            // accepts both 5.75 and 5,75 as 5.75
            // Double.parseDouble() only can convert strings with .
            // so replace all , with .
            loanRates[i] = Double.parseDouble(input.nextLine().replaceAll(",","."));
        }
    }

    /*
        This method will display all the 3 choice and ask user to select an option from the 3
        choice
        it will then return the index of the option selected
     */
    public static int getTermInformation(int[] loanTerms, double[] loanRates){
        int choice;
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("\nWelcome to XYZ Bank!");
            for(int i = 0; i < loanTerms.length; i++){
                System.out.printf("%d: %2d years - %.2f%% \n", (i+1), loanTerms[i], loanRates[i]);
            }
            System.out.println("\n");
            System.out.print("Select (1, 2, or 3) an option for the loan:");
            choice = input.nextInt();
            if(choice != 1 && choice != 2 && choice != 3){
                System.out.println("Your choice is not a valid option.");
            } else {
                break;
            }
        }
        return choice - 1;
    }

    /*
        This method will display the Summary of the loan including principle amount, the interest
        rate and term of the loan
     */
    public static void displayLoanSummary(double principle, int loanTerm, double loanRate){
        System.out.println("\nSummary of Loan");
        System.out.printf("Principle Amount: $%.2f\n", principle);
        System.out.printf("Int. Rate(%%): %.2f\n", loanRate);
        System.out.printf("Term of Loan (Months): %d\n", loanTerm*12);
    }

    /*
        This method will display the amortization table to show the monthly activity of the loan
     */
    public static void displayAmortTable(double principle, int loanTerm, double loanRate){

        int termOfLoanMonth = loanTerm * 12;
        double monthlyInterestRates = loanRate / (double)(12 * 100);
        double monthlyMortgage = principle * (monthlyInterestRates / (1 - Math.pow((1+monthlyInterestRates), -(termOfLoanMonth))));
        double totalMortgage = monthlyMortgage * termOfLoanMonth;

        double totalInterest = totalMortgage - principle;
        double totalAmountDue = principle;
        double interestPaid;
        double principlePaid;

        System.out.printf("\n%-10s%-16s%-16s%-16s\n", "Payment", "Principle", "Interest", "Balance");

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
            System.out.printf("%-10d$%-15.2f$%-15.2f$%-15.2f\n", (i+1), principlePaid, interestPaid, totalAmountDue);
        }

    }
}
