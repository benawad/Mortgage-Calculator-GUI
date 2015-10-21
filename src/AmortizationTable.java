import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;

public class AmortizationTable extends JFrame{
	private JTable table;
	
	public AmortizationTable(String[] columns, String[][] data){
		setTitle("Amortization Table");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 410);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		table = new JTable(data, columns);
		table.setShowGrid(true);
		table.setGridColor(Color.BLUE);
		scrollPane.setViewportView(table);
	}
	
}
