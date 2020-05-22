import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;



public class Main {

	public static SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
	public static int getDifference(String prev, String cur) throws ParseException{
        Date date1 = dates.parse(prev);
        Date date2 = dates.parse(cur);
        long difference = Math.abs(date1.getTime() - date2.getTime());
        long differenceDates = difference / (24 * 60 * 60 * 1000);
		return (int)differenceDates;
	}
	public static TreeMap<String,Integer> findMissingValues(TreeMap<String,Integer> data) throws ParseException{
		Iterator<String> iter = data.keySet().iterator();
		TreeMap<String,Integer> result = new TreeMap<String,Integer>();
		//if no entries return empty dictionary
		if(!iter.hasNext())
			return result;
		String prevDate = iter.next();
		int prevValue = data.get(prevDate);
		result.put(prevDate, prevValue);
		Calendar calendar = Calendar.getInstance();
		while(iter.hasNext()){
			String currDate = iter.next();
			int currValue = data.get(currDate);
                          int numberOfDays = getDifference(prevDate, currDate);
		         int diff = currValue - prevValue;		
			int d = diff/numberOfDays;
			while(numberOfDays-->0){
				calendar.setTime(dates.parse(prevDate));
				calendar.add(Calendar.DAY_OF_YEAR,1);
				prevDate = dates.format(calendar.getTime());
				prevValue += d;
				result.put(prevDate, prevValue);
			}
		}
		return result;
	}
	public static void main(String[] args) throws ParseException{
		TreeMap<String, Integer> data = new TreeMap<String,Integer>();
		System.out.println("Enter number of entries:");
		Scanner sc = new Scanner(System.in);
		int entries = sc.nextInt();
		//Taking data from user
		for(int i = 0; i < entries; i++){
			System.out.print("Enter the date:");
			String date = sc.next();
			System.out.print("Enter the value:");
			int value = sc.nextInt();
			data.put(date, value);
		}
		System.out.println(findMissingValues(data));
		
	}
}
