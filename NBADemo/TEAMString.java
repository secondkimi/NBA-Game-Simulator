package NBADemo;

import objectdraw.*;

public class TEAMString{
  private static final String WARRIORS = "Warriors";
  private static final String CAVALIERS = "Cavaliers";
  private static final String PISTONS = "Pistons";
  private static final String BULLS = "Bulls";
  private static final String PACERS = "Pacers";
  private static final String BUCKS = "Bucks";
  private static final String SPURS = "Spurs";
  private static final String THUNDER = "Thunder";
  private static final String NUGGETS = "Nuggets";
  private static final String TIMBERWOLVES = "Timberwolves";
  private static final String BLAZERS = "Blazers";
  private static final String JAZZ = "Jazz";
  private static final String CLIPPERS = "Clippers";
  private static final String ROCKETS = "Rockets";
  private static final String MAVERICKS = "Mavericks";
  private static final String GRIZZLIES = "Grizzlies";
  private static final String PELICANS = "Pelicans";
  private static final String HEAT = "Heat";
  private static final String HAWKS = "Hawks";
  private static final String HORNETS = "Hornets";
  private static final String MAGIC = "Magic";
  private static final String WIZARDS = "Wizards";
  private static final String CELTICS = "Celtics";
  private static final String RAPTORS = "Raptors";
  private static final String LAKERS = "Lakers";
  private static final String SUNS = "Suns"; 
  private static final String KINGS = "Kings";
  private static final String NETS = "Nets";
  private static final String KNICKS = "Knicks";
  private static final String SEVEN_SIXERS = "76ers";
  private static final String SOUTHWEST[] = {MAVERICKS,ROCKETS,GRIZZLIES,PELICANS,SPURS};
  private static final String ATLANTIC[] = {CELTICS,NETS,KNICKS,SEVEN_SIXERS,RAPTORS};
  private static final String CENTRAL[] = {BULLS,CAVALIERS,PISTONS,PACERS,BUCKS};
  private static final String NORTHWEST[] = {NUGGETS,TIMBERWOLVES,THUNDER,BLAZERS,JAZZ};
  private static final String PACIFIC[] = {WARRIORS,CLIPPERS,LAKERS,KINGS,SUNS};
  private static final String SOUTHEAST[] = {MAGIC,HAWKS,HORNETS,HEAT,WIZARDS};
  public static String[][] team = new String[6][5];
  public static Location[][] homeLogoLoc = new Location[6][5];
  public static Location[][] awayLogoLoc = new Location[6][5];

  public TEAMString() {

  	team[0][0] = "Raptors";
  	team[0][1] = "Celtics";
  	team[0][2] = "Knicks";
  	team[0][3] = "Nets";
  	team[0][4] = "76ers";

  	team[1][0] = "Warriors";
  	team[1][1] = "Clippers";
  	team[1][2] = "Kings";
  	team[1][3] = "Suns";
  	team[1][4] = "Lakers";

  	team[2][0] = "Cavaliers";
  	team[2][1] = "Pacers";
  	team[2][2] = "Pistons";
  	team[2][3] = "Bulls";
  	team[2][4] = "Bucks";

  	team[3][0] = "Spurs";
  	team[3][1] = "Mavericks";
  	team[3][2] = "Grizzlies";
  	team[3][3] = "Rockets";
  	team[3][4] = "Pelicans";

  	team[4][0] = "Heats";
  	team[4][1] = "Hawks";
  	team[4][2] = "Hornets";
  	team[4][3] = "Wizards";
  	team[4][4] = "Magic";

  	team[5][0] = "Thunders";
  	team[5][1] = "Blazers";
  	team[5][2] = "Jazz";
  	team[5][3] = "Nuggets";
  	team[5][4] = "Timberwolves";
  }

}