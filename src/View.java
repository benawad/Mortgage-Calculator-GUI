import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
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

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Mortgage Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(240, 243, 250));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel top = new JPanel();
		top.setBackground(new Color(240, 243, 250));
		top.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(216,226,253)));
		contentPane.add(top);
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 243, 250));
		top.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		JLabel mortgageAmountLabel = new JLabel("Mortgage amount:  $");
		panel.add(mortgageAmountLabel);
		
		mortgageAmountField = new JTextField();
		mortgageAmountField.setColumns(10);
		panel.add(mortgageAmountField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		top.add(panel_1);
		
		JLabel mortgageTermLabel = new JLabel("Mortgage term:         ");
		panel_1.add(mortgageTermLabel);
		
		mortgageTermYearsField = new JTextField();
		mortgageTermYearsField.setColumns(10);
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
		mortgageTermMonthsField.setColumns(10);
		panel_1.add(mortgageTermMonthsField);
//		mortgageTermMonthsField.getDocument().addDocumentListener(new DocumentListener() {
//			
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				updateYears();
//			}
//			
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				updateYears();
//			}
//			
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				updateYears();
//			}
//			
//			public void updateYears(){
//				try{
//					int months = Integer.parseInt(mortgageTermMonthsField.getText());
//					if(months > -1){
//						mortgageTermYearsField.setText((months/12)+"");
//					} else {
//						new Exception();
//					}
//				} catch (Exception e){
//					mortgageTermYearsField.setText("Error");
//				}
//			}
//		});
		
		JLabel monthsLabel = new JLabel("months");
		panel_1.add(monthsLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		top.add(panel_2);
		
		JLabel interestRateLabel = new JLabel("Interest rate:             ");
		panel_2.add(interestRateLabel);
		
		interestRateField = new JTextField();
		interestRateField.setColumns(10);
		panel_2.add(interestRateField);
		
		JLabel perYearLabel = new JLabel("% per year");
		panel_2.add(perYearLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		top.add(panel_3);
		
		JLabel startDateLabel = new JLabel("Mortgage start date:");
		panel_3.add(startDateLabel);
		
		// get all the names of months in an array
		// for some reason it adds an empty string to the end of array
		String[] months = (new DateFormatSymbols()).getShortMonths();
		// create new array without the blank spot
		String[] newMonths = new String[months.length-1];
		System.arraycopy(months, 0, newMonths, 0, months.length-1);
		
		monthComboBox = new JComboBox(newMonths);
		panel_3.add(monthComboBox);

		dayComboBox = new JComboBox();
		panel_3.add(dayComboBox);

		// allow user to input +-30 years from current year
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		String[] years = new String[61];
		int count = 0;
		for(int i = 30; i >= 0; i--){
			years[count] = (year-i) + "";
			count++;
		}
		for(int i = 1; i <= 30; i++){
			years[i+30] = (year+i) + "";
		}

		yearComboBox = new JComboBox(years);
		panel_3.add(yearComboBox);
		// set selected to the current year
		yearComboBox.setSelectedIndex(30);
		
		// figure out how many days are in the selected month
		int daysInMonth = calcDays(getMonth(), getYear());
		setComboBoxDays(daysInMonth);

		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setVgap(0);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		top.add(panel_4);
		
		JLabel monthlyPayLabel = new JLabel("Monthly Payments: $ ");
		panel_4.add(monthlyPayLabel);
		
		monthlyPaymentsField = new JTextField();
		monthlyPaymentsField.setColumns(10);
		panel_4.add(monthlyPaymentsField);
		

		calculateButton = new JButton("Calculate");
		calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		top.add(calculateButton);
		
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(240, 243, 250));
		contentPane.add(bottom);
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_5);
		
		JLabel extraLabel = new JLabel("Extra payments");
		panel_5.add(extraLabel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_5 = (FlowLayout) panel_6.getLayout();
		flowLayout_5.setVgap(0);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_6);
		
		JLabel add1 = new JLabel("Adding:     $");
		panel_6.add(add1);
		
		add1Field = new JTextField();
		add1Field.setText("0");
		panel_6.add(add1Field);
		add1Field.setColumns(10);
		
		JLabel descAdd1 = new JLabel("to your monthly mortgage payment");
		panel_6.add(descAdd1);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_6 = (FlowLayout) panel_7.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_7);
		
		JLabel add2 = new JLabel("Adding:     $");
		panel_7.add(add2);
		
		add2Field = new JTextField();
		add2Field.setText("0");
		add2Field.setColumns(10);
		panel_7.add(add2Field);
		
		JLabel descAdd2 = new JLabel("as an extra yearly mortgage payment every");
		panel_7.add(descAdd2);
		
		extraMonthComboBox = new JComboBox();
		panel_7.add(extraMonthComboBox);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_7 = (FlowLayout) panel_8.getLayout();
		flowLayout_7.setVgap(0);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_8);
		
		JLabel add3 = new JLabel("Adding:     $");
		panel_8.add(add3);
		
		add3Field = new JTextField();
		add3Field.setText("0");
		add3Field.setColumns(10);
		panel_8.add(add3Field);
		
		JLabel descAdd3 = new JLabel("as a one-time payment in");
		panel_8.add(descAdd3);
		
		oneTimeMonthComboBox = new JComboBox();
		panel_8.add(oneTimeMonthComboBox);
		
		oneTimeYearComboBox = new JComboBox();
		panel_8.add(oneTimeYearComboBox);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_8 = (FlowLayout) panel_9.getLayout();
		flowLayout_8.setVgap(0);
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_9);
		
		JLabel paidOffDateLabel = new JLabel("Changes paid off date to:");
		panel_9.add(paidOffDateLabel);
		
		paidOffField = new JTextField();
		panel_9.add(paidOffField);
		paidOffField.setColumns(10);
		
		tableButton = new JButton("Show/Recalculate Amortization Table\n");
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
		// 2 digits
		double rounded = Math.round(amount*100.0)/100.0;
		monthlyPaymentsField.setText(rounded+"");
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
}
