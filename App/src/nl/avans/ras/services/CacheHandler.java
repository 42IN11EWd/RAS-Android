package nl.avans.ras.services;

import java.util.Date;

public class CacheHandler {

	private static final long TWENTY_FOUR_HOUR_DIFFERENCE = 86400000;
	
	public static boolean updateCache(Date checkDate, Date todaysDate) {
    	// Create a new date with an additional 24 hours
    	Date refreshLimit = new Date(checkDate.getTime() + TWENTY_FOUR_HOUR_DIFFERENCE);
    	boolean later = false;
    	
    	// Check if todays date is greater then the refresh limit.
    	if (refreshLimit.before(todaysDate)) {
    		later = true;
    	}
    	
    	return later;
    }
}
