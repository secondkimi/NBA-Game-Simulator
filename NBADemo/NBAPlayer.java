package NBADemo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**  
 * @author Tianyi Wang
 * @version 1.0
 * @since 02-03-16
 */


/** This class store a player's data */
public class NBAPlayer {
	private int twoPointShooting;
	private int threePointShooting;
	private int athleticism;
	private int rebound;
	private int defense;
	private int offense;
	private int playmaking;
	// the overall value of a player, depends on the position.
	private int overAll;
	// 1 is PG and 5 is Center
	private int position;
	// true if the player is a star player
	private boolean isStarPlayer;
	// true if the player is a role player
	private boolean isRolePlayer;
	// the name of a player
	private String name;

	/** Constructor */
	public NBAPlayer() {

	}

	/** pass a string of the player's name */
	public NBAPlayer( String pName ) {
		this.name = pName;
		readFile( pName );
		setOverAll();
	}

	private void readFile( String pName ) {
		String fname = "NBADemo/Player Data/"+pName+".txt";
		String line;
		BufferedReader reader;
		StringTokenizer st;
		try { 
			reader = new BufferedReader(new FileReader(fname));
			line = reader.readLine();
			if ( line == null ) {
				System.out.println("No content in the file, please check your file again!");
			}
			else {
				st = new StringTokenizer(line);
				if (!this.name.equalsIgnoreCase(st.nextToken()+" "+st.nextToken()) ) {
					System.out.println("The file name does not match the player name in the file, please check the file again");
				}
				else {
					position = Integer.parseInt(st.nextToken());
					twoPointShooting = Integer.parseInt(st.nextToken());
					threePointShooting = Integer.parseInt(st.nextToken());
					athleticism = Integer.parseInt(st.nextToken());
					rebound = Integer.parseInt(st.nextToken());
					defense = Integer.parseInt(st.nextToken());
					playmaking = Integer.parseInt(st.nextToken());
					isStarPlayer = "y".equalsIgnoreCase(st.nextToken());
					isRolePlayer = "y".equalsIgnoreCase(st.nextToken());
				}
			}

		}

		catch (IOException e) {
			System.out.println( e.toString() );
			System.out.println("could not found file "+fname);
		}
	}


	// the name of the player
	public String getName() {
		return name;
	}
	// the position of a player
	public int getPosition() {
		return position;
	}

	// the value of twoPointShooting
	public int getTwoPointShooting() {
		return twoPointShooting;
	}


	// the value of threePointShooting
	public int getThreePointShooting() {
		return threePointShooting;
	} 

	// the value of athleticism
	public int getAthleticism() {
		return athleticism;
	}

	// the value of rebound
	public int getRebound() {
		return rebound;
	} 	
	// the value of defense
	public int getDefense() {
		return defense;
	}

	// the value of offense
	public int getOffense() {
		setOffense();
		return offense;
	}

	// the value of palymaking
	public int getPlaymaking() {
		return playmaking;
	}
	// set the position of a player
	public void setPosition(int s) {
		position = s;
	}

	// set value of twoPointShooting
	public void setTwoPointShooting(int s) {
		twoPointShooting = s;
	}

	// set the value of threePointShooting
	public void setThreePointShooting(int s) {
		threePointShooting = s;
	} 

	// set the value of athleticism
	public void setAthleticism(int s) {
		athleticism = s;
	}

	// set the value of rebound
	public void setRebound(int s) {
		rebound = s;
	} 	
	// set the value of defense
	public void setDefense( int s ) {
		defense = s;
	}

	// set the value of offense
	public void setOffense() {
		// Point Guard
		if ( getPosition() == 1 ) {
			double total = twoPointShooting * 1.0 + threePointShooting * 1.3 + athleticism * 0.6 
				+ playmaking * 1.3; // +0.3

			offense = (int) total / 4;
			if ( offense > 99 ) 
				offense = 99; 
		}
		// Shooting guard
		else if ( getPosition() == 2 ) {
			double tot = twoPointShooting * 1.1 + threePointShooting * 1.2 + athleticism * 0.8 
				+ playmaking * 1.1; // +0.3
			offense = (int) tot/4;

				if ( offense > 99 ) 
					offense = 99;
		}
		// Small forward
		else if ( getPosition() == 3 ) {
			offense = (int) (twoPointShooting * 1.1 + threePointShooting * 1.0 + athleticism * 1.1 
				+ playmaking * 1.0 )/4; // +0.3
				if ( offense > 99 ) 
					offense = 99;
		}
		// power forward
		else if ( getPosition() == 4 ) {
			offense = (int) (twoPointShooting * 1.4 + threePointShooting * 0.6 + athleticism * 1.1 
				+ playmaking * 1.1)/4; // +0.3
				if ( offense > 99 ) 
					offense = 99;
		}
		// Center
		else {
			offense = (int) (twoPointShooting * 1.6 + threePointShooting * 0.4 + athleticism * 1.2 
				+ playmaking * 1.0)/4; // +0.3
				if ( offense > 99 ) 
					offense = 99;
		}
	}


	// set the value of palymaking
	public void setPlaymaking( int s ) {
		playmaking = s;
	}
	// set the name of a player
	public void setName( String s ) {
		name = s;
	}

    /** set a player's overall ability according to their position */
	private void setOverAll() {
		// Point Guard
		if ( getPosition() == 1 ) {
			double total = twoPointShooting * 1.3 + threePointShooting * 1.5 + athleticism * 0.8 
				+ defense * 0.7 + rebound * 0.4 + playmaking * 1.6; // +0.3

			overAll = (int) total / 6;
			if ( overAll > 99 ) 
				overAll = 99; 
		}
		// Shooting guard
		else if ( getPosition() == 2 ) {
			double tot = twoPointShooting * 1.4 + threePointShooting * 1.5 + athleticism * 0.9 
				+ defense * 0.8 + rebound * 0.5 + playmaking * 1.2; // +0.3
			overAll = (int) tot/6;

				if ( overAll > 99 ) 
					overAll = 99;
		}
		// Small forward
		else if ( getPosition() == 3 ) {
			overAll = (int) (twoPointShooting * 1.2 + threePointShooting * 1.1 + athleticism * 1.2 
				+ defense + rebound * 0.7 + playmaking * 1.1 )/6; // +0.3
				if ( overAll > 99 ) 
					overAll = 99;
		}
		// power forward
		else if ( getPosition() == 4 ) {
			overAll = (int) (twoPointShooting * 1.4 + threePointShooting * 0.6 + athleticism 
				+ defense * 1.1 + rebound * 1.4 + playmaking * 0.8)/6; // +0.3
				if ( overAll > 99 ) 
					overAll = 99;
		}
		// Center
		else {
			overAll = (int) (twoPointShooting * 1.3 + threePointShooting * 0.4 + athleticism * 1.3 
				+ defense * 1.3 + rebound * 1.4 + playmaking * 0.6)/6; // +0.3
				if ( overAll > 99 ) 
					overAll = 99;
		}
	}
	public int getOverAll() {
		return overAll;
	}

	public boolean getisStarPlayer() {
		return isStarPlayer;
	}

	public boolean getisRolePlayer() {
		return isRolePlayer;
	}

	public String printPosition() {
		if (getPosition() == 1) {
			return "PG";
		}
		else if ( getPosition() == 2 ) {
			return "SG";
		}
		else if (getPosition() == 3) {
			return "SF";
		}
		else if ( getPosition() == 4 ) {
			return "PF";
		}
		else if ( getPosition() == 5 ) {
			return "C";
		}	
		return "UNKNOWN";	
	}
}