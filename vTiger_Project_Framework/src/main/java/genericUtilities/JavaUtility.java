package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	SimpleDateFormat sdf;

	/**
	 * This method is used to generate random number
	 * 
	 * @return
	 */
	public int togetRandomNumber() {
		Random random = new Random();
		int randomNum = random.nextInt(1000);
		return randomNum;
	}

	/**
	 * This method is used to get the current date
	 * 
	 * @return
	 */
	public String togetSystemDateYYYYMMDD() {
		Date dateObj = new Date();

		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		return date;
	}

	/**
	 * This method is used to generate date according to our input
	 * 
	 * @param days
	 * @return
	 */
	public String togetRequiredDate(int days) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = sdf.getCalendar();
		         cal.add(Calendar.DAY_OF_MONTH, -30);
		String reqDate = sdf.format(cal.getTime());
		return reqDate;
	}
}
