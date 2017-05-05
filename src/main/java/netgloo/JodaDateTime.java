package netgloo;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class JodaDateTime {
public static void main(String[] args) {
	
	DateTime dateTime = new DateTime(DateTimeZone.UTC);
	Date tomorrow=new Date();
	Calendar cal=Calendar.getInstance();
	cal.setTime(tomorrow);
	cal.add(Calendar.DATE, 1);
	DateTime tomorrowdateTime = new DateTime(cal.getTime()).withZone(DateTimeZone.UTC);

	DateTime dateTimeIndia = new DateTime(DateTimeZone.UTC);
	System.out.println("UTC="+dateTime);
	System.out.println("India="+dateTimeIndia);
	System.out.println("tomorrow="+tomorrowdateTime);
	System.out.println(dateTime.isAfter(tomorrowdateTime));
	System.out.println(dateTime.isBefore(tomorrowdateTime));
	System.out.println(dateTime.isAfter(dateTimeIndia.toDateTime(DateTimeZone.UTC)));
	System.out.println(dateTime.isBefore(dateTimeIndia.toDateTime(DateTimeZone.UTC)));
	System.out.println(dateTime.equals(dateTimeIndia.toDateTime(DateTimeZone.UTC)));
	Date date = new Date();
	DateTime dateTimeFromdate=new DateTime(date);
	System.out.println(dateTimeFromdate);
	System.out.println(dateTimeFromdate.withZone(DateTimeZone.UTC).toDate());
}

}
