import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField mortgageAmountField;
	private JTextField mortgageTermYearsField;
	private JTextField mortageTermMonthsField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		JLabel yearsLabel = new JLabel("years or");
		panel_1.add(yearsLabel);
		
		mortageTermMonthsField = new JTextField();
		mortageTermMonthsField.setColumns(10);
		panel_1.add(mortageTermMonthsField);
		
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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_2.add(textField_3);
		
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
		
		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_3.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		panel_3.add(comboBox_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setVgap(0);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		top.add(panel_4);
		
		JLabel monthlyPayLabel = new JLabel("Monthly Payments: $ ");
		panel_4.add(monthlyPayLabel);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_4.add(textField_4);
		
		JButton calculateButton = new JButton("Calculate");
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
		
		textField_5 = new JTextField();
		panel_6.add(textField_5);
		textField_5.setColumns(10);
		
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
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		panel_7.add(textField_6);
		
		JLabel descAdd2 = new JLabel("as an extra yearly mortgage payment every");
		panel_7.add(descAdd2);
		
		JComboBox comboBox_3 = new JComboBox();
		panel_7.add(comboBox_3);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_7 = (FlowLayout) panel_8.getLayout();
		flowLayout_7.setVgap(0);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_8);
		
		JLabel add3 = new JLabel("Adding:     $");
		panel_8.add(add3);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		panel_8.add(textField_7);
		
		JLabel descAdd3 = new JLabel("as a one-time payment in");
		panel_8.add(descAdd3);
		
		JComboBox comboBox_4 = new JComboBox();
		panel_8.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		panel_8.add(comboBox_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 243, 250));
		FlowLayout flowLayout_8 = (FlowLayout) panel_9.getLayout();
		flowLayout_8.setVgap(0);
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		bottom.add(panel_9);
		
		JLabel paidOffDateLabel = new JLabel("Changes paid off date to:");
		panel_9.add(paidOffDateLabel);
		
		textField_8 = new JTextField();
		panel_9.add(textField_8);
		textField_8.setColumns(10);
		
		JButton tableButton = new JButton("Show/Recalculate Amortization Table\n");
		tableButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottom.add(tableButton);

	}

}
