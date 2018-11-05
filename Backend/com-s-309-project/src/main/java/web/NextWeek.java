package web;

import java.util.Date;
import java.util.Calendar;

public class NextWeek {

	public String[] nextSevenDays = new String[7];
	public String[] correspondingMonth = new String[7];
	public String[] correspondingYear = new String[7];
	public String[] together = new String[7];
	public NextWeek() {
		
		Calendar c = Calendar.getInstance();
		for (int i=0; i < 7;i++) {
			
			nextSevenDays[i] = Integer.toString(c.get(Calendar.DATE));
			correspondingMonth[i] = Integer.toString(c.get(Calendar.MONTH)+1);
			correspondingYear[i] = Integer.toString(c.get(Calendar.YEAR));
			together[i] = nextSevenDays[i] + "/" + correspondingMonth[i];
			c.add(Calendar.DATE, 1);
			
		}
		
	}
	
	
	
	
}
