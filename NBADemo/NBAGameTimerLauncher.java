package NBADemo;
import java.util.Random;

public class NBAGameTimerLauncher {

	public static void main(String[] args) {
		NBAGameTimer timer = new NBAGameTimer();
		Random rd = new Random();
		int t = 0;
		timer.timeEclipse(20);
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(30);
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(11);
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 10:00
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 09:01 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 08:02 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(58); // 07:04 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 06:05 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 05:06 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 04:07 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 03:08 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 02:09 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 01:10 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(10); // 01:00 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(45); // 00:15 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(8); // 00:07 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 00:00 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());
		timer.timeEclipse(59); // 11:01 
		System.out.println(timer.refreshQuarterString()+" "+timer.refreshTimeString());

	}
}