package netgloo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class App {

	public static void main(String[] args) {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try {
			 System.out.println(dateFormatLocal.parse(dateFormatGmt.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		 
		 SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 f.setTimeZone(TimeZone.getTimeZone("UTC"));
		 System.out.println(f.format(new Date()));
	}

}
