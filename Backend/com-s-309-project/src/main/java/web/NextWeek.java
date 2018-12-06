package web;

import java.util.Date;
import java.util.Calendar;


/**
 * The Class NextWeek.
 */
public class NextWeek {

	/** The next seven days. */
	public String[] nextSevenDays = new String[7];
	
	/** The corresponding month. */
	public String[] correspondingMonth = new String[7];
	
	/** The corresponding year. */
	public String[] correspondingYear = new String[7];
	
	/** The together. */
	public String[] together = new String[7];
	
	/**
	 * Instantiates a new next week.
	 */
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
