package NBADemo;
import java.util.Random;
/**
 * this class gives a bunch of strings for printing in the window
 *
 */
public class NBAGameString {
	// some strings for getting the rebound
	private static final String REBOUND[] = {" gets the rebound. "," 's rebound. "," gets the bounce ball. ",
		"takes the rebound."};
	// some strings for getting two points
	private static final String TWO_POINT[] = {" hits a jumper. "," adds two to his team. "," knocks down a two-point jumper.",
		" easily makes the lay up. "," makes a two-point shot. "," slums the ball into the rim! What a dunk!"};
	// somr strings for getting three points
	private static final String THREE_POINT[] ={ " hits a three from downtown!"," splashes a three from the corner. ",
		" makes a 3-point jumper from downtown. "," adds three for his team. "," knocks down a three."};

	private static final String MISS_TWO_POINT[] = { " missed a jumper."," could not make a layup"," lost the ball"," missed a shot",
	" hits the rim and the ball bounce out"," 's two-point shot is in and out. "};
	private static final String MISS_THREE_POINT[] = { " missed a three point shot."," missed a three.","'s three point shot bounced out."," tried a three but no splash"};

	private static final String DEFENSE_REBOUND[] = {" grabs an offensive rebound. ", " gets the offensive rebound. ", " snatches"+ 
		" an offensive rebound from the defense."};
	private static final String OFFENSE_REBOUND[] = {" protects the rebound.", " gets the defensive rebound", "'s rebound", " gets the rebound"};

	public String getTwoPoint(NBATeam team, String player) {
		String front = team.getName()+": "+player;
		Random rd = new Random();
		int i = rd.nextInt(6);
		return front + TWO_POINT[i];
	}

	public String getThreePoint(NBATeam team, String player) {
		String front = team.getName()+": "+player;
		Random rd = new Random();
		int i = rd.nextInt(5);
		return front + THREE_POINT[i];
	}

	public String missTwoPoint(NBATeam team, String player) {
		String front = team.getName()+": "+player;
		Random rd = new Random();
		int i = rd.nextInt(6);
		return front + MISS_TWO_POINT[i];	
	}

	public String missThreePoint( NBATeam team, String player ) {
		String front = team.getName()+": "+player;
		Random rd = new Random();
		int i = rd.nextInt(4);
		return front + MISS_THREE_POINT[i];	
	}

	public String getOffRebound(NBATeam team, String player) {
		String front = team.getName()+": "+player;
		Random rd = new Random();
		int i = rd.nextInt(3);
		return front + OFFENSE_REBOUND[i];
	}

	public String getDefRebound(NBATeam team, String player) {
		String front = team.getName()+": "+player;
		Random rd = new Random();
		int i = rd.nextInt(4);
		return front + DEFENSE_REBOUND[i];
	}

	
}