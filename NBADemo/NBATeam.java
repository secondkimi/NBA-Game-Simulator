package NBADemo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.*;
/**  
 * @author Tianyi Wang
 * @version 1.0
 * @since 02-03-16
 */


/** 
 *
 *
 * This class store a team's data
 * all the team values are based ONLY ON 
 * start players, so if we change the players on the court, 
 * the team's data will be changed.
 */

public class NBATeam {
	private int twoPointShooting;
	private int threePointShooting;
	private int athleticism;
	private int rebound;
	private int defense;
	private int offense;
	private int playmaking;
	// the overall value of a team, depends on the position.
	private int overAll;
	// true if the team has exactly one star player. affect team overall
	private boolean hasOnlyOneStarPlayer;
	// true if the player has two star players . affect team overall
	private boolean hasTwoStarPlayer;
	// the name of the team
	private String name;

	// the offense/defense style of a team. "balance","outside" and "inside"
	// the style affects the team's overAll
	private String offenseStyle;
	private String defenseStyle;
	// the whole roster of the team 
	// to search a player's data, just use the key, which is the player's whole name
	private MyMap<String, NBAPlayer> roster;
	// put five players on the court into the list. 
	// must be the sequence {PG SG SF PF C} so that we can get the position of the 
	// player by looking at the index of the list 
	private ArrayList<NBAPlayer> starter;

	/** Constructor */
	public NBATeam() {
		this.name = "Team001";
		System.out.println("Please speficy your team name");
		starter = new ArrayList<NBAPlayer>();
		roster = new MyMap<String,NBAPlayer>();
	}

	/** pass a string of the player's name */
	public NBATeam( String tName ) {
		this.name = tName;
		starter = new ArrayList<NBAPlayer>();
		roster = new MyMap<String,NBAPlayer>();
		readFile( tName );

	}

	private void readFile( String tName ) {
		String fname = "NBADemo/Team Data/"+tName+".txt";
		String line;
		BufferedReader reader;
		StringTokenizer st;
		try { 
			reader = new BufferedReader( new FileReader(fname) );
			line = reader.readLine();
			st = new StringTokenizer( line );
			if ( line == null ) {
				System.out.println("No content in the file, please check your file again!");
			}
			else if ( !st.nextToken().equals(tName) ) {
				System.out.println("File Name and the team name does not match, please check your file again!");
			}
			else {
				offenseStyle = st.nextToken();
				defenseStyle = st.nextToken();
				while ( (line = reader.readLine())!=null ) {
					st = new StringTokenizer( line );
					String pName = st.nextToken()+" "+st.nextToken();
					NBAPlayer p = new NBAPlayer(pName);
					// add all the players in the roster to the list
					roster.put(pName, p);
					// put starters to the array list
					if ( st.hasMoreTokens() && starter.size() < 5 ) {
						starter.add(p);
					}
				}
			}
			reSetAllData();

		}

		catch (IOException e) {
			System.out.println( e.toString() );
			System.out.println("could not found file "+fname);
		}
	}
		// this method returns the name of the player who made the shot. 
	// we calculate this by random. p = player.playmaking / team.playmaking
	// if the player is a star/role, p will increase/decrease
	// params: boolean bl: yes if it is final two mins

	public int shootingPlayer(boolean bl) {

		int toReturn = 0;
		int temp = 0;
		int[] bound = new int[5]; // 0 ~ bound[0]; bound[0] ~bound[1] ... 
		for (int i = 0; i <5; i++ ) {
			NBAPlayer player = starter.get(i);
			temp += player.getPlaymaking();
			bound[i] = temp;

			if ( player.getisStarPlayer() ) {
				if ( !bl ) {
					// not final two min
					bound[i] += 16;
				}
				else {
					bound[i] += 45;
				}
			}
			else if ( player.getisRolePlayer() ) {
				if ( !bl ) {
					// not final two min
					bound[i] -= 16;
				}
				else {
					bound[i] -= 45;
				}
			}
		}
		Random rd = new Random();
		int hit = rd.nextInt( temp );
		for ( int j = 0; j<5; j++ ) {
			if ( hit <= bound[j] ) {
				return j;
			}
		}

		return toReturn;

	}

	/**
	 * This method will be called when: 
	 * 1, we read the file because the team's data can be changed in the txt
	 * 2, we replace players during the game. ALL THE DATA are based on the starter 
	 * players, that is, players stored in the ArrayList
	 */
	public void reSetAllData() {
		// set up the team's data
		setPlaymaking();
		setTwoPointShooting();
		setThreePointShooting();
		setAthleticism();
		setRebound();
		setDefense();
		setOffense();
		setOverAll();
	}


	// the name of the team
	public String getName() {
		return name;
	}

	/** 
	 * the value of twoPointShooting
	 * AFFECTED BY OFFENSE STYLE
	 */
	public int getTwoPointShooting() {
		return twoPointShooting;
	}


	/** the value of threePointShooting
	 * NOT AFFECTED BY ANY STYLE
	 */
	public int getThreePointShooting() {
		return threePointShooting;
	} 

	// the value of athleticism
	// NOT AFFECTED BY ANY STYLE
	public int getAthleticism() {
		return athleticism;
	}

	// the value of rebound
	// NOT AFFECTED BY ANY STYLE
	public int getRebound() {
		return rebound;
	} 	

	// the value of defense
    // AFFECTED BY DEFENSE STYLE
	public int getDefense() {
		return defense;
	}

	// the value of offense
    // AFFECTED BY OFFENSE STYLE
	public int getOffense() {
		return offense;
	}

	// the value of playmaking
	// AFFECTED BY OFFENSE STYLE
	public int getPlaymaking() {
		return playmaking;
	}

	// set value of twoPointShooting
	public void setTwoPointShooting() {
		double count = 0;
		if ( offenseStyle.equalsIgnoreCase("balance") ) {
			// the team is balance, so the shooting ability of 
			// every players are equally considered.

			// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getTwoPointShooting() * 0.96;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getTwoPointShooting() * 1.06;
			} 

		}
		else if ( offenseStyle.equalsIgnoreCase("outside") ) {
			// the team focus on outside, so the shooting ability of 
			// inside players(1,2,3) are more significant
						// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getTwoPointShooting() * 1.1;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getTwoPointShooting() * 0.85;
			} 
		}
		else if ( offenseStyle.equalsIgnoreCase("inside") ){
			// the team focus on inside, so the shooting ability of 
			// inside players(4,5) are more significant

			// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getTwoPointShooting() * 0.82;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getTwoPointShooting() * 1.27;
			} 			
		}
		twoPointShooting = 5+((int) count / 5);
	}


	// set the value of threePointShooting
	public void setThreePointShooting() {
		double count = 0;
		count += starter.get(0).getThreePointShooting() * 1.3;
		count += starter.get(1).getThreePointShooting() * 1.3;		
		count += starter.get(2).getThreePointShooting() * 1.2;
		count += starter.get(3).getThreePointShooting() * 0.8;
		count += starter.get(4).getThreePointShooting() * 0.4;				
		threePointShooting = ((int) count / 5) + 5;

	} 

	// set the value of athleticism
	public void setAthleticism() {
		double count = 0;
		for (int i=0; i<5;i++) {
			count += starter.get(i).getAthleticism();
		}
		athleticism = 5+((int) count / 5);
	}

	// set the value of rebound
	public void setRebound() {
		double count = 0;
		count += starter.get(0).getRebound() * 0.05;
		count += starter.get(1).getRebound() * 0.1;
		count += starter.get(2).getRebound() * 0.2;
		count += starter.get(3).getRebound() * 0.35;
		count += starter.get(4).getRebound() * 0.4;
		rebound = (int) count + 1;
	} 	

	//set the value of defense
	//TODO
	private void setOffense() {
		double count = 0;
		if ( offenseStyle.equalsIgnoreCase("balance") ) {
			// the team is balance, so the shooting ability of 
			// every players are equally considered.

			// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getOffense();
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getOffense() * 1.15;
			} 

		}
		else if ( offenseStyle.equalsIgnoreCase("outside") ) {
			// the team focus on outside, so the shooting ability of 
			// inside players(1,2,3) are more significant
						// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getOffense() * 1.16;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getOffense() * 0.91;
			} 
		}
		else if ( offenseStyle.equalsIgnoreCase("inside") ){
			// the team focus on inside, so the shooting ability of 
			// inside players(4,5) are more significant

			// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getOffense() * 0.9;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getOffense() * 1.3;
			} 			
		}
		offense = ((int) count / 5);
	}

	// set the value of defense
	public void setDefense() {
		double count = 0;
		if ( defenseStyle.equalsIgnoreCase("balance") ) {
			// the team is balance, so the shooting ability of 
			// every players are equally considered.

			// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getDefense();
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getDefense() * 1.15;
			} 

		}
		else if ( defenseStyle.equalsIgnoreCase("outside") ) {
			// the team focus on outside, so the shooting ability of 
			// inside players(1,2,3) are more significant
						// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getDefense() * 1.16;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getDefense() * 0.91;
			} 
		}
		else if ( defenseStyle.equalsIgnoreCase("inside") ){
			// the team focus on inside, so the shooting ability of 
			// inside players(4,5) are more significant

			// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getDefense() * 0.9;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getDefense() * 1.3;
			} 			
		}
		defense = ((int) count / 5);		
	}

	// set the value of playmaking
	public void setPlaymaking() {
		double count = 0;
		if ( offenseStyle.equalsIgnoreCase("balance") ) {
			// the team is balance, so the shooting ability of 
			// every players are equally considered.

			// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getPlaymaking();
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getPlaymaking();
			} 

		}
		else if ( offenseStyle.equalsIgnoreCase("outside") ) {
			// the team focus on outside, so the shooting ability of 
			// inside players(1,2,3) are more significant
						// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getPlaymaking() * 1.2;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getPlaymaking() * 0.7;
			} 
		}
		else if ( offenseStyle.equalsIgnoreCase("inside") ){
			// the team focus on inside, so the shooting ability of 
			// inside players(4,5) are more significant

			// 1,2,3 players
			for( int i=0; i<3;i++){
				count += starter.get(i).getPlaymaking() * 0.8;
			}
			// 4,5 players
			for (int j=3; j<5;j++) {
				count += starter.get(j).getPlaymaking() * 1.3;
			} 			
		}
		playmaking = 5 + ((int) count / 5);
	}
	// set the name of a player
	public void setName( String s ) {
		name = s;
	}

    /** set the team's overall ability according to their position */
	private void setOverAll() {
		double all = getOffense() + getDefense();
		overAll = (int) (all / 2);
	}

	public int getOverAll() {
		return overAll;
	}

	public void setOffStyle(String s) {
		if ( s.equalsIgnoreCase("balance") || s.equalsIgnoreCase("inside") || s.equalsIgnoreCase("outside") ) 
			offenseStyle = s;
	}

	public String getOffStyle() {
		return offenseStyle;
	}

	public void setDefStyle(String s) {
		if ( s.equalsIgnoreCase("balance") || s.equalsIgnoreCase("inside") || s.equalsIgnoreCase("outside") ) 
			defenseStyle = s;
	}

	public String getDefStyle() {
		return defenseStyle;
	}

	/**
	 * return the number of star player.
	 * return value can be 0, 1, 2.
	 */
	public int starPlayer() {
		readStarPlayer();
		if ( hasTwoStarPlayer ) {
			return 2;
		}
		else if ( hasOnlyOneStarPlayer ) {
			return 1;
		}
		return 0;
	}

	/**
	 * This method reads the star player ON THE COURT, 
	 * There can be only two star players on the court
	 */
	public void readStarPlayer() {
		int count = 0;
		for (int i=0; i<5; i++) {
			// search for star players
			if ( starter.get(i).getisStarPlayer() ) {
				count++;
			}
		}
		if ( count == 2 ) {
			hasTwoStarPlayer = true;
			hasOnlyOneStarPlayer = false;
		}
		else if ( count == 1 ) {
			hasTwoStarPlayer = false;
			hasOnlyOneStarPlayer = true;
		}
		else {
			hasTwoStarPlayer = false;
			hasOnlyOneStarPlayer = false;
		}

	}

	/** 
	 * get the player Name at position i in the starter arraylist
	 * @return String player name in the i position
	 */ 
	public String getStartPlayer(int i) {
		return starter.get(i).getName();
	}

	public NBAPlayer getStarterPlayerObj(int i) {
		return starter.get(i);
	}

	/**
	 * substitute the player for position x with a new player in the roster
	 * @param int position - the position to be substituted 0 - 4: pg - center
	 * @param String player - the new player name
	 * @return String - the substituted player
	 */ 
	public String substitution(int position, String player) {
		if ( position >= 0 && position < 5 ) {
			String replaced = starter.remove(position).getName();
			NBAPlayer newPlayer = new NBAPlayer(player);
			starter.add(position,newPlayer);
			return replaced;
		}
		return null;
	}

}