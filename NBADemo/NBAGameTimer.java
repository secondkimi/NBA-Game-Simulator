package NBADemo;
import java.util.Random;

/**
 * this class defines the game timer to trace the time left for each quarter
 */
public class NBAGameTimer {
	// the current quarter
	private int quarter = 0;
	// how many minutes left in the quarter
	private int minutes = 12;
	// how many seconds left in the quarter
	private int seconds = 0;

	// the string of timer, for example, "12 : 00"
	private String timerString;
	// the string to indicate the quarter
	private String quarterString; 

	// true if it is the final two minutes in the fourth quarter or the overtime.
	private boolean isFinalTwoMin;

	// if the time is over, that is minutes = seconds = 0 
	private boolean isQuarterTimeOver = false; 



	// true if all the time has run out.
	/** Constructor */
	public NBAGameTimer() {
		resetTime();
	}

    /**
	 * when time t has passed, change the current minutes and seconds
	 */
	public void timeEclipse() {
		Random rd = new Random();
		// from 5 to 24
		int t = rd.nextInt(20)+5;
		// from 0 to 2
		int b = rd.nextInt(3);
		if ( t < 8 && b != 0 ) {
			t += 10;
		}
		else if ( t >= 8 && t<12 && b == 0 ) {
			t += 7; 
		}
		else if ( t>11 && t<15 && b == 0) {
			t += 6;
		}
		else if ( t>20 && t<25 && b == 0) {
			t -= 4;
		}

		if ( getisQuarterTimeOver() ) {
			resetTime();
		}

		if ( seconds < t ) {
			if (minutes == 0) {
				seconds = 0;
				setisQuarterTimeOver(true);
			}
			else if ( minutes > 0 ) {
				seconds = 60 + seconds - t;
				minutes--;
			}
		}
		// seconds >= t
		else {
			seconds = seconds - t;
		}
	}

	/**
	 * when time t has passed, change the current minutes and seconds
	 * @param int t - the time that is eclipsed in the game
	 */
	public void timeEclipse( int t ) {
		if ( getisQuarterTimeOver() ) {
			resetTime();
		}

		if ( seconds < t ) {
			if (minutes == 0) {
				seconds = 0;
				setisQuarterTimeOver(true);
			}
			else if ( minutes > 0 ) {
				seconds = 60 + seconds - t;
				minutes--;
			}
		}
		// seconds >= t
		else {
			seconds = seconds - t;
		}
	}


	/**
	 * reset the timer to 12:00 and increment the quarter 
	 */
	public void resetTime() {
		quarter++;
		if ( quarter < 5 ) {
			minutes = 12;
		}
		else {
			minutes = 5;
		}

		seconds = 0;
		refreshTimeString();
		refreshQuarterString();
		setisQuarterTimeOver(false);
	}

	/**
	 * refresh the timer string based on the current time
	 * @return the updated timer string
	 */
	public String refreshTimeString() {
		if ( getSeconds() < 10 && getMinutes() >= 10 ) {
			timerString = getMinutes()+" : 0"+getSeconds();
		}
		else if ( getSeconds() < 10 && getMinutes() < 10 ) {
			timerString = "0"+getMinutes()+" : 0"+getSeconds();
		}
		else if (getMinutes() < 10 && getSeconds() >= 10 ) {
			timerString = "0"+getMinutes()+" : "+getSeconds();
		}
		else {
			timerString = getMinutes()+" : "+getSeconds();
		}
		return timerString;
	}

	/**
	 * refresh the quarter string based on the quarter the game is in.
	 * @return the updated quarter string
	 */
	public String refreshQuarterString() {
		if ( quarter < 5 ) {
			quarterString = "Q"+getQuarter();
		}
		// overtime
		else {
			quarterString = "OT"+(getQuarter()-4);
		}
		return quarterString;
	}



	public boolean getisQuarterTimeOver() {
		return isQuarterTimeOver;
	}

	public void setisQuarterTimeOver(boolean b) {
		isQuarterTimeOver = b;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setMinutes(int t) {
		minutes = t;
	}
	public int getMinutes() {
		return minutes;
	}

	public void setQuarter(int t) {
		quarter = t;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int t) {
		seconds = t;
	}

	public boolean getisFinalTwoMin() {
		if ( getQuarter() > 3 && getMinutes() < 2 ) {
			isFinalTwoMin = true;
		} 
		else {
			isFinalTwoMin = false;
		}
		return isFinalTwoMin;
	}


}