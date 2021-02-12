package unittest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DemoDeliveryProject {
	@Parameter(0)
	public int p1;
	@Parameter(1)
	public int year;
	@Parameter(2)
	public int month;
	@Parameter(3)
	public int date;
	@Parameter(4)
	public int hours;
	@Parameter(5)
	public int minutes;
	
	@Parameter(6)
	public String p2; 
	
	@Parameters
	public static Collection<Object[]> data(){
		 Object[][] mydata=new Object[][]
		{{600, 2020, 02, 02, 15, 00, "03-02-2020 15:00"},{700,2017, 2, 14, 1,0,"15-02-2017 10:00"},{650, 2020, 01, 11, 12, 00, "12-01-2020 13:00"},{550, 2020, 1, 2, 8, 00, "02-01-2020 15:00"},{700,2017, 2, 14, 14,0,"15-02-2017 16:00"},{700,2017, 2, 14, 17,0,"16-02-2017 10:00"},{500,2017, 2, 14, 8,0,"14-02-2017 15:00"},{700,2017, 2, 14, 8,0,"15-02-2017 10:00"},};
		 return Arrays.asList(mydata);
		 } 
	DeliveryProject d=null;
	@Before
	public void callmain() {
		d=new DeliveryProject();
	
	}
	
	@Test
	public void test() throws Exception  {
		assertEquals(p2,d.CallMain(p1,year,month,date,hours,minutes));
	}

}
class DeliveryProject {

	public  static String CallMain(int TravelDistance,int year,int month,int date,int hour,int minutes) throws Exception {
		DeliverProduct factory=new FlipkartDelivery(year,month,date,hour,minutes);
		int s=factory.CourierBoyTravelTime(TravelDistance);
		System.out.println("Total Hours To Deliver:"+s+"hrs");
		int ExceptedtimeTaken=factory.totalHoursTaken(s);
		String finalresult=factory.ExpectedDeliveryDate(ExceptedtimeTaken);
		return finalresult;
	}
}


interface Delivery{
	int Travelhrs=8;
	int speed=70;
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	public int CourierBoyTravelTime(int distance);
	public int totalHoursTaken(int hours);
	public String ExpectedDeliveryDate(int ExceptedtimeTaken);

}
abstract class DeliverProduct implements Delivery{
	
	}
class FlipkartDelivery extends DeliverProduct{
	LocalDateTime CTime;
	@Override
	public int CourierBoyTravelTime(int distance) {
		// TODO Auto-generated method stub
		
		return distance/speed;
	}
	FlipkartDelivery(int year,int month,int date,int hour,int minutes){
		LocalDateTime currentDateTime = LocalDateTime.of(year, month, date, hour,minutes);
		//LocalDateTime currentDateTime=LocalDateTime.now();
		String formatDateTime =currentDateTime .format(format);  
		System.out.println("Current Date: " + formatDateTime);  

	    CTime=currentDateTime;
	}
	@Override
	public int totalHoursTaken(int hours) {
		int remainingTime=0;
		// TODO Auto-generated method stub
		if(CTime.getHour()>8 && CTime.getHour()<16) {
			remainingTime=16-CTime.getHour();
			
		}
		if(CTime.getHour()<8) {
			int value=8-CTime.getHour();
			CTime=CTime.plusHours(value);

		}
		CTime=CTime.plusHours(remainingTime);

		return hours-remainingTime;
	}
	@Override
	public String ExpectedDeliveryDate(int ExceptedtimeTaken) {
		// TODO Auto-generated method stub
		int nextdaytime1=0;
		int nextday=0;
		int flag=0;
		int totalhours=ExceptedtimeTaken%Travelhrs;
		int totaldays=ExceptedtimeTaken/Travelhrs;
		System.out.println("Hours and days"+totalhours+":"+totaldays);
		LocalDateTime ExpectedDeliveryDate=CTime.plusDays((totaldays));
		if(totaldays==0) {
			flag=1;
			ExpectedDeliveryDate=CTime.plusHours((totalhours));
			if(ExpectedDeliveryDate.getHour()>16) {
				nextday=24-ExpectedDeliveryDate.getHour();
				nextdaytime1=ExpectedDeliveryDate.getHour()-16;
				
				ExpectedDeliveryDate=ExpectedDeliveryDate.plusHours(nextday+8);
				ExpectedDeliveryDate=ExpectedDeliveryDate.plusHours(nextdaytime1);
				
			}
		}
		if(ExpectedDeliveryDate.getHour()>16) {
			nextday=24-ExpectedDeliveryDate.getHour();
			ExpectedDeliveryDate=ExpectedDeliveryDate.plusHours(nextday+8);
			
		}
		
		LocalDateTime ExpectedDeliveryTime=ExpectedDeliveryDate.plusHours(totalhours); 
		if(ExpectedDeliveryTime.getHour()>16) {
			nextday=24-ExpectedDeliveryTime.getHour();
			ExpectedDeliveryTime=ExpectedDeliveryTime.plusHours(nextday+8);
			ExpectedDeliveryTime=ExpectedDeliveryTime.plusHours(totalhours);
		}
		
		if(flag==1) {
			ExpectedDeliveryTime=ExpectedDeliveryDate;
		}else
		{	
			ExpectedDeliveryTime=ExpectedDeliveryDate.plusHours(totalhours); 
		}
		String formatDateTime =ExpectedDeliveryTime .format(format);  
	    System.out.println("Expected Date: " + formatDateTime); 
	    return formatDateTime;
		
	}
	
}