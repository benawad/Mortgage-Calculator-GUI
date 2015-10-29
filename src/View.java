import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField mortgageAmountField;
	private JTextField mortgageTermYearsField;
	private JTextField mortgageTermMonthsField;
	private JTextField interestRateField;
	private JTextField monthlyPaymentsField;
	private JTextField add1Field;
	private JTextField add2Field;
	private JTextField add3Field;
	private JTextField paidOffField;
	private JComboBox monthComboBox;
	private JComboBox dayComboBox;
	private JComboBox yearComboBox;
	private JComboBox extraMonthComboBox;
	private JComboBox oneTimeMonthComboBox;
	private JComboBox oneTimeYearComboBox;
	private JButton calculateButton;
	private JButton tableButton;
	private String[] sMonths;
	private List<String> years;
	private int numOfYears = 40;

	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(216,226,253)));
		contentPane.setBackground(new Color(240, 243, 250));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		FlowLayout flowLayout_9 = (FlowLayout) titlePanel.getLayout();
		flowLayout_9.setVgap(0);
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		contentPane.add(titlePanel);
		JLabel title = new JLabel("<html><font color='#37639e'><strong>Mortgage Calculator</strong></font></html>");
		title.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		titlePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(216,226,253)));
		titlePanel.add(title);
		
		JPanel top = new JPanel();
		top.setBackground(new Color(240, 243, 250));
		top.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(216,226,253)));
		contentPane.add(top);
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 243, 250));
		top.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 4));
		
		JLabel mortgageAmountLabel = new JLabel("Mortgage amount:  $");
		panel.add(mortgageAmountLabel);
		
		mortgageAmountField = new JTextField();
		mortgageAmountField.setText("165000");
		mortgageAmountField.setColumns(8);
		panel.add(mortgageAmountField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(4);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		top.add(panel_1);
		
		JLabel mortgageTermLabel = new JLabel("Mortgage term:         ");
		panel_1.add(mortgageTermLabel);
		
		mortgageTermYearsField = new JTextField();
		mortgageTermYearsField.setText("30");
		mortgageTermYearsField.setColumns(8);
		panel_1.add(mortgageTermYearsField);
		mortgageTermYearsField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateMonths();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateMonths();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateMonths();
			}
			
			public void updateMonths(){
				try{
					int years = Integer.parseInt(mortgageTermYearsField.getText());
					if(years > -1){
						mortgageTermMonthsField.setText((years*12)+"");
					} else {
						new Exception();
					}
				} catch (Exception e){
					mortgageTermMonthsField.setText("");
				}
			}
			
		});
		
		JLabel yearsLabel = new JLabel("years or");
		panel_1.add(yearsLabel);
		
		mortgageTermMonthsField = new JTextField();
		mortgageTermMonthsField.setText("360");
		mortgageTermMonthsField.setColumns(8);
		panel_1.add(mortgageTermMonthsField);
		
		JLabel monthsLabel = new JLabel("months");
		panel_1.add(monthsLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setVgap(4);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		top.add(panel_2);
		
		JLabel interestRateLabel = new JLabel("Interest rate:             ");
		panel_2.add(interestRateLabel);
		
		interestRateField = new JTextField();
		interestRateField.setText("4.50");
		interestRateField.setColumns(8);
		panel_2.add(interestRateField);
		
		JLabel perYearLabel = new JLabel("% per year");
		panel_2.add(perYearLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setVgap(4);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		top.add(panel_3);
		
		JLabel startDateLabel = new JLabel("Mortgage start date:");
		panel_3.add(startDateLabel);
		
		// get all the names of months in an array
		// for some reason it adds an empty string to the end of array
		String[] months = (new DateFormatSymbols()).getShortMonths();
		// create new array without the blank spot
		sMonths = new String[months.length-1];
		System.arraycopy(months, 0, sMonths, 0, months.length-1);
		
		Calendar cal = Calendar.getInstance();
		monthComboBox = new JComboBox(sMonths);
		// set current month
		monthComboBox.setSelectedIndex(cal.get(Calendar.MONTH));
		panel_3.add(monthComboBox);

		dayComboBox = new JComboBox();
		panel_3.add(dayComboBox);

		// allow user to input +-30 years from current year
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		years = new ArrayList<>();
		int count = 0;
		for(int i = numOfYears; i >= 0; i--){
			years.add((year-i) + "");
			count++;
		}
		for(int i = 1; i <= numOfYears; i++){
			years.add((year+i) + "");
		}

		yearComboBox = new JComboBox(years.toArray());
		panel_3.add(yearComboBox);
		// set selected to the current year
		yearComboBox.setSelectedIndex(numOfYears);
		
		// figure out how many days are in the selected month
		int daysInMonth = calcDays(getMonth(), getYear());
		setComboBoxDays(daysInMonth);
		
		// set current day
		dayComboBox.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH)-1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setVgap(4);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		top.add(panel_4);
		
		JLabel monthlyPayLabel = new JLabel("<html><font color='#37639e'><strong>Monthly Payments:</strong></font>   $ </html>");
		monthlyPayLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		panel_4.add(monthlyPayLabel);
		
		monthlyPaymentsField = new JTextField();
		monthlyPaymentsField.setText("836.03");
		monthlyPaymentsField.setColumns(8);
		panel_4.add(monthlyPaymentsField);
		

		calculateButton = new JButton("   Calculate   ");
		calculateButton.setForeground(Color.WHITE);
		calculateButton.setBackground(new Color(50,90,175));
		calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		calculateButton.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(new Color(50,90,175), 1),
	               BorderFactory.createLineBorder(Color.WHITE, 1)));
		calculateButton.setOpaque(true);
		top.add(calculateButton);
		
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(240, 243, 250));
		contentPane.add(bottom);
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_5);
		
		JLabel extraLabel = new JLabel("<html><font color='#37639e'>Extra payments</font></html>");
		extraLabel.setFont(new Font("Arial", Font.BOLD, 13));
		panel_5.add(extraLabel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_5 = (FlowLayout) panel_6.getLayout();
		flowLayout_5.setVgap(3);
		flowLayout_5.setHgap(10);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_6);
		
		JLabel add1 = new JLabel("Adding:     $");
		panel_6.add(add1);
		
		add1Field = new JTextField();
		add1Field.setText("0");
		panel_6.add(add1Field);
		add1Field.setColumns(8);
		
		JLabel descAdd1 = new JLabel("to your monthly mortgage payment");
		panel_6.add(descAdd1);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_6 = (FlowLayout) panel_7.getLayout();
		flowLayout_6.setVgap(3);
		flowLayout_6.setHgap(10);
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_7);
		
		JLabel add2 = new JLabel("Adding:     $");
		panel_7.add(add2);
		
		add2Field = new JTextField();
		add2Field.setText("0");
		add2Field.setColumns(8);
		panel_7.add(add2Field);
		
		JLabel descAdd2 = new JLabel("as an extra yearly mortgage payment every");
		panel_7.add(descAdd2);
		
		extraMonthComboBox = new JComboBox(sMonths);
		extraMonthComboBox.setSelectedIndex(cal.get(Calendar.MONTH));
		panel_7.add(extraMonthComboBox);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_7 = (FlowLayout) panel_8.getLayout();
		flowLayout_7.setVgap(3);
		flowLayout_7.setHgap(10);
		flowLayout_7.setAlignOnBaseline(true);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_8);
		
		JLabel add3 = new JLabel("Adding:     $");
		panel_8.add(add3);
		
		add3Field = new JTextField();
		add3Field.setText("0");
		add3Field.setColumns(8);
		panel_8.add(add3Field);
		
		JLabel descAdd3 = new JLabel("as a one-time payment in");
		panel_8.add(descAdd3);
		
		int year1 = cal.get(Calendar.YEAR);
		oneTimeMonthComboBox = new JComboBox(sMonths);
		// next month
		cal.add(Calendar.MONTH, 1);
		oneTimeMonthComboBox.setSelectedIndex(cal.get(Calendar.MONTH));
		panel_8.add(oneTimeMonthComboBox);
		
		oneTimeYearComboBox = new JComboBox(years.toArray());
		// check if month increased the year
		int year2 = cal.get(Calendar.YEAR);
		if(year1 != year2){
			oneTimeYearComboBox.setSelectedIndex(numOfYears+1);
		} else {
			oneTimeYearComboBox.setSelectedIndex(numOfYears);
		}
		panel_8.add(oneTimeYearComboBox);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_8 = (FlowLayout) panel_9.getLayout();
		flowLayout_8.setHgap(10);
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_9);
		
		JLabel paidOffDateLabel = new JLabel("<html><font color='#37639e'>Changes paid off date to:</font></html>");
		paidOffDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
		panel_9.add(paidOffDateLabel);
		
		paidOffField = new JTextField();
		panel_9.add(paidOffField);
		paidOffField.setColumns(8);
		
		tableButton = new JButton("     Show/Recalculate Amortization Table     \n");
		tableButton.setForeground(Color.WHITE);
		tableButton.setBackground(new Color(50,90,175));
		tableButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableButton.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(new Color(50,90,175), 1),
	               BorderFactory.createLineBorder(Color.WHITE, 1)));
		tableButton.setOpaque(true);

		tableButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottom.add(tableButton);

	}
	
	public void addMonthYearComboBoxActionListener(ActionListener listener){
		monthComboBox.addActionListener(listener);
		yearComboBox.addActionListener(listener);
	}
	
	public void addCalculateActionListener(ActionListener listener){
		calculateButton.addActionListener(listener);
	}
	
	public void addRecalculateTable(ActionListener listener){
		tableButton.addActionListener(listener);
	}
	
	public int calcDays(int month, int year){
		int day = 1;
		Calendar cal = new GregorianCalendar(year, month, day);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public void setComboBoxDays(int daysInMonth){
		if(daysInMonth != dayComboBox.getItemCount()){
			dayComboBox.removeAllItems();
			for(int i = 1; i <= daysInMonth; i++){
				dayComboBox.addItem(i);
			}
		}
	}
	
	public void updateOneTime(int day, int month, int year){
		Calendar cal = new GregorianCalendar(year, month, day);
		cal.add(Calendar.MONTH, 1);
		oneTimeMonthComboBox.setSelectedIndex(cal.get(Calendar.MONTH));
		String sYear = "" + cal.get(Calendar.YEAR);
		oneTimeYearComboBox.setSelectedIndex(years.indexOf(sYear));
	}
	
	public int getYear(){
		return Integer.parseInt((String) yearComboBox.getSelectedItem());
	}
	
	public int getMonth(){
		return monthComboBox.getSelectedIndex();
	}
	
	public int getDay(){
		return dayComboBox.getSelectedIndex()+1;
	}
	
	public int getMortgageAmount(){
		return Integer.parseInt(mortgageAmountField.getText());
	}
	
	public int getMortgageTermYears(){
		return Integer.parseInt(mortgageTermYearsField.getText());
	}
	
	public int getMortgageTermMonths(){
		return Integer.parseInt(mortgageTermMonthsField.getText());
	}
	
	public double getInterestRate(){
		return Double.parseDouble(interestRateField.getText());
	}
	
	public double getMonthlyPayments(){
		return Double.parseDouble(monthlyPaymentsField.getText());
	}
	
	public void setMonthlyPayments(double amount){
		// round double to 2 digits
		monthlyPaymentsField.setText(String.format("%.2f", amount));
	}
	
	public double getAdd1(){
		return Double.parseDouble(add1Field.getText());
	}

	public double getAdd2(){
		return Double.parseDouble(add2Field.getText());
	}
	
	public double getAdd3(){
		return Double.parseDouble(add3Field.getText());
	}
	
	public int getAdd2Month(){
		return extraMonthComboBox.getSelectedIndex();
	}
	
	public int getAdd3Month(){
		return oneTimeMonthComboBox.getSelectedIndex();
	}
	
	public int getAdd3Year(){
		return Integer.parseInt((String)oneTimeYearComboBox.getSelectedItem());
	}
	
	public void setFinishedDate(String date){
		paidOffField.setText(date);
	}
	
	public String getStartMonthString(int index){
		return	(String)monthComboBox.getItemAt(index); 
	}
	
}
