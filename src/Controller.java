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
	}
	
	
	public static void main(String[] args){
		Controller c = new Controller();
		
	}
	
}
