package com.gwccnet.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import com.gwccnet.utility.ToolBox;

public class CalendarUtilities {
	
	private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd-HH.mm.ss";

	/**
	 * compares dates disregarding time
	 * @param date	 
	 * @param otherDate
	 * @return returns 0 if the dates are equal, 1 if date is > other date,
	 * 		   and -1 if date < otherDate
	 */
	public static int compareDates(Calendar date, Calendar otherDate) {
		if(date == null && otherDate == null) {
			return 0; 
		}
		if(date == null) {
			return -1;
		}
		if(otherDate == null){
			return 1;
		}
		if(date.get(Calendar.YEAR) < otherDate.get(Calendar.YEAR)){
			return -1;
		}else if(date.get(Calendar.YEAR) > otherDate.get(Calendar.YEAR)){
			return 1;
		} 
		// the same year
		if(date.get(Calendar.MONTH) < otherDate.get(Calendar.MONTH)){
			return -1;
		}else if(date.get(Calendar.MONTH) > otherDate.get(Calendar.MONTH)){
			return 1;
		} 
		// the same month
		if(date.get(Calendar.DAY_OF_MONTH) < otherDate.get(Calendar.DAY_OF_MONTH)){
			return -1;
		} else if(date.get(Calendar.DAY_OF_MONTH) > otherDate.get(Calendar.DAY_OF_MONTH)){
			return 1;
		} 
		//the same day
		return 0;
	}
	public static Calendar getLatestDate(Calendar cal1, Calendar cal2)
	{
		return (cal1.compareTo(cal2) < 0)?cal2:cal1;
	}
	/**
	 * @param date to copy from
	 * @return
	 */
	public static Calendar copyDate(Calendar date) {
		Calendar dateCopy = new GregorianCalendar(
				date.get(Calendar.YEAR),
				date.get(Calendar.MONTH),
				date.get(Calendar.DAY_OF_MONTH)); 
		return dateCopy;
	}
	public static int CalculateRoundedMonthsInterval(Calendar start, Calendar end)
	{
		Period period = new Period(start.getTimeInMillis(),end.getTimeInMillis(),PeriodType.yearMonthDay());
		int months = 0;
		months = (period.getYears() * 12) + (period.getMonths()) + ((period.getDays() > 15)?1:0);
		return months;
	}
	
	/**
	 * Checks to see if date is between date1 and date2.
	 * The actual comparison is date1 <= date < date2
	 * @param date
	 * @param effectiveDate
	 * @param expirationDate
	 * @return
	 */
	public static boolean isBetween(Calendar date, Calendar date1, Calendar date2)
	{
		if (date != null && date1 != null && date2 != null &&
			compareDates(date, date1) >= 0 && compareDates(date, date2) < 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if date is between date1 and date2.
	 * Similar to the isBetween method, but the upper bound is included in the range.
	 * The actual comparison is date1 <= date <= date2
	 * @param date
	 * @param effectiveDate
	 * @param expirationDate
	 * @return
	 */
	public static boolean isBetweenInclusive(Calendar date, Calendar date1, Calendar date2)
	{
		if (date != null && date1 != null && date2 != null &&
			compareDates(date, date1) >= 0 && compareDates(date, date2) <= 0)
		{
			return true;
		}
		return false;
	}
	/**
	 * Calculates the number of days between two calendar days in a manner
	 * which is independent of the Calendar type used.
	 *
	 * @param d1    The first date.
	 * @param d2    The second date.
	 *
	 * @return      The number of days between the two dates.  Zero is
	 *              returned if the dates are the same, one if the dates are
	 *              adjacent, etc.  The order of the dates
	 *              does not matter, the value returned is always >= 0.
	 *              If Calendar types of d1 and d2
	 *              are different, the result may not be accurate.
	 */
	public static int getDaysBetween (java.util.Calendar d1, java.util.Calendar d2) {
	    if (d1.after(d2)) {  // swap dates so that d1 is start and d2 is end
	        java.util.Calendar swap = d1;
	        d1 = d2;
	        d2 = swap;
	    }
	    int days = d2.get(java.util.Calendar.DAY_OF_YEAR) -
	               d1.get(java.util.Calendar.DAY_OF_YEAR);
	    int y2 = d2.get(java.util.Calendar.YEAR);
	    if (d1.get(java.util.Calendar.YEAR) != y2) {
	        d1 = (java.util.Calendar) d1.clone();
	        do {
	            days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
	            d1.add(java.util.Calendar.YEAR, 1);
	        } while (d1.get(java.util.Calendar.YEAR) != y2);
	    }
	    return days;
	} // getDaysBetween()

	public static Calendar getFirstDateOfMonth(Calendar inCal)
	{
		DateMidnight date = new DateMidnight(inCal.getTimeInMillis()).withDayOfMonth(1);
		Calendar outCal = Calendar.getInstance();
		outCal.setTimeInMillis(date.getMillis());
		
		return outCal;
	}
	
	public static Calendar getLastDateOfMonth(Calendar inCal)
	{
		DateMidnight date = new DateMidnight(inCal.getTimeInMillis()).withDayOfMonth(1).plusMonths(1).minusDays(1);
		Calendar outCal = Calendar.getInstance();
		outCal.setTimeInMillis(date.getMillis());
		
		return outCal;
	}
	
	public static Calendar getFirstDateOfNextMonth(Calendar inCal)
	{
		DateMidnight date = new DateMidnight(inCal.getTimeInMillis()).withDayOfMonth(1).plusMonths(1);
		Calendar outCal = Calendar.getInstance();
		outCal.setTimeInMillis(date.getMillis());
		
		return outCal;
	}
	
	public static int getDaysInMonth(Calendar inCal)
	{
		DateTime dt = new DateTime(inCal.getTimeInMillis()).dayOfMonth().withMaximumValue();
		
		return dt.getDayOfMonth();
	}
	
	public static Calendar getMin(Calendar cal1, Calendar cal2)
	{
		if(CalendarUtilities.compareDates(cal1, cal2) <= 0)
		{
			return cal1;
		}
		else
		{
			return cal2;
		}
	}
	
	public static Calendar getMax(Calendar cal1, Calendar cal2)
	{
		if(CalendarUtilities.compareDates(cal1, cal2) >= 0)
		{
			return cal1;
		}
		else
		{
			return cal2;
		}
	}
	
	public static boolean doIntervalsOverlap(Calendar interval1Start, Calendar interval1End, Calendar interval2Start, Calendar interval2End)
	{
		Interval interval1 = new Interval(interval1Start.getTimeInMillis(), interval1End.getTimeInMillis());
		Interval interval2 = new Interval(interval2Start.getTimeInMillis(), interval2End.getTimeInMillis());
		
		Interval overlap = interval1.overlap(interval2);
		
		if(overlap == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static int dateDiffInDays(Calendar beginDate, Calendar endDate)
	{
		int days = Days.daysBetween(new DateTime(beginDate), new DateTime(endDate)).getDays();
		return days;
	}
	
	/**
	 * Converts the passed Calendar object to an XMLGregorianCalendar
	 * @param cal
	 * @return
	 */
	public static XMLGregorianCalendar calendarToXmlGregorianCalendar(Calendar cal){
		if(cal==null){
			return null;
		}
		try{
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(cal.getTime());
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Converts the passed XMLGregorianCalendar to Calendar
	 * @param xmlCal
	 * @return
	 */
	public static Calendar xmlGregorianToCalendar(XMLGregorianCalendar xmlCal){
		if(xmlCal==null){
			return null;
		}
		try{
			return ToolBox.dateToCalendar(xmlCal.toGregorianCalendar().getTime());
		}catch(Exception e){
		}
		return null;
	}
	
	/**
	 * Returns a calendar object representing the date on the passed calendar (without time)
	 * @param cal
	 * @return
	 */
	public static void removeTimeFromDate(Calendar cal){
		try{
			String stringDate = 
				cal.get(Calendar.MONTH)+1 + "/" + 
				cal.get(Calendar.DATE) + "/" + 
				cal.get(Calendar.YEAR);
			Calendar cal2 = ToolBox.stringToCalendar(stringDate);
			cal.setTime(cal2.getTime());
		}catch(Exception e){
		}
	}
	
	/**
	 * Returns a calendar representing the passed DB2 timestamp string
	 * @param timestamp
	 * @return
	 */
	public static Calendar calendarFromDB2Timestamp(String timestamp)
	{
		Calendar cal = null;
		if(timestamp!=null){
			SimpleDateFormat df = new SimpleDateFormat(TIMESTAMP_FORMAT);
			try
			{
				Date d1 = df.parse(timestamp);
				cal = Calendar.getInstance();
				cal.setTime(d1);
			} catch (ParseException e)
			{}
		}
		return cal;
	}
}
