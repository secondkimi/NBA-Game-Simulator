package NBADemo;
/**
 * this class tracks a player's performance in a game.
 * we track a player's score, rebounds, assists .. in a SINGLE GAME
 */
public class PlayerData {
	private NBAPlayer player;
	private int score;
	private int rebound;
	private String name;

	/** Cosntrcutor */
	public PlayerData(String playerName ) {
		player = new NBAPlayer(playerName);
		name = playerName;
	}

	public PlayerData(NBAPlayer player) {
		this.player = player;
		name = player.getName();
	}

	public int getRebound() {
		return rebound;
	}

	public void setRebound( int reb ) {
		rebound = reb;
	}

	public int getScore() {
		return score;
	}

	public void setScore ( int sc ) {
		score = sc;
	}

	public String getName() {
		return name;
	}

}