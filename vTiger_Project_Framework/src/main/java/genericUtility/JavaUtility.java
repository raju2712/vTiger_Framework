package genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	/**
	 * This method is used to generate random number
	 * @return
	 */
	public int togetRandomNumber() {
		Random random = new Random();
		int randomNum = random.nextInt(1000);
		return randomNum;
	}
	
	public String togetSystemDateYYYYMMDD() {
		Date dateObj = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String date= sdf.format(dateObj);
		
		return date;
	}
	
	public String togetRequiredDate(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sdf.format(cal.getTime());
		
		return reqDate;
		
	}
}
