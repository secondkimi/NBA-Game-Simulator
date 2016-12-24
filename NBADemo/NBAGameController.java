package NBADemo;

import objectdraw.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import NBADemo.NBAGame;
import NBADemo.TEAMString;

public class NBAGameController extends WindowController implements ActionListener, KeyListener, MouseListener {

	// the width and the height of the frame
	private static final int FRAME_WIDTH = 1600;
	private static final int FRAME_HEIGHT = 800;
	// the width and height buffer. Nothing will be shown within the buffer range
	private static final int WIDTH_BUFFER = 100;
	private static final int HEIGHT_BUFFER = 50;

	// the width/height of small team logo / large team logo (before the game starts)
	private static final int SMALL_TEAM_LOGO_SIZE = 200; 
	private static final int LARGE_TEAM_LOGO_SIZE = 250;

	private static final String WARRIORS = "Warriors";
	private static final String CAVALIERS = "Cavaliers";
	private static final String SPURS = "Spurs";
	private static final String THUNDER = "Thunder";
	private static final String CLIPPERS = "Clippers";
	private static final String ROCKETS = "Rockets";
	private static final String HEAT = "Heat";
	private static final String CELTICS = "Celtics";
	private static final String RAPTORS = "Raptors";
	private static final String LAKERS = "Lakers";

	// the size of all Text
	private static final int PAUSE_TEXT_SIZE = 50;
	private static final int CAPITAL_HOME_TEXT_SIZE = 50;
	private static final int CAPITAL_AWAY_TEXT_SIZE = CAPITAL_HOME_TEXT_SIZE;
	private static final int TIMER_SIZE = 40;
	private static final int AT_TEXT_SIZE = 50;
	private static final int GAME_STREAM_TEXT_SIZE = 25;

	private static final int PLAYER_NAME_TEXT_SIZE = 20; 
	private static final int PLAYER_SCORE_TEXT_SIZE = 15;

	// the location of player profiles
	// before the Game Start
	private static final double PREGAME_AWAY_PLAYER_IMG_STARTX = 960; 
	private static final double PREGAME_AWAY_PLAYER_IMG_STARTY = 100;	
	private static final double PREGAME_HOME_PLAYER_IMG_STARTX = 580;
	private static final double PREGAME_HOME_PLAYER_IMG_STARTY = PREGAME_AWAY_PLAYER_IMG_STARTY;
	private static final double PREGAME_HOME_TEAM_TEXT_STARTX = 200;
	private static final double PREGAME_HOME_TEAM_TEXT_STARTY = 720;
	private static final double PREGAME_AWAY_TEAM_TEXT_STARTX = 1200;
	private static final double PREGAME_AWAY_TEAM_TEXT_STARTY = 720;	
		// After the Game Start
	private static final double GAME_AWAY_PLAYER_IMG_STARTX = 1300; 
	private static final double GAME_AWAY_PLAYER_IMG_STARTY = 100;	
	private static final double GAME_HOME_PLAYER_IMG_STARTX = 200; 
	private static final double GAME_HOME_PLAYER_IMG_STARTY = GAME_AWAY_PLAYER_IMG_STARTY;
	private static final double GAME_HOME_TEAM_TEXT_STARTX = 350;
	private static final double GAME_HOME_TEAM_TEXT_STARTY = 100;
	private static final double GAME_AWAY_TEAM_TEXT_STARTX = 1060;
	private static final double GAME_AWAY_TEAM_TEXT_STARTY = 100;
	private static final double QUARTER_TEXT_STARTX = 700;
	private static final double QUARTER_TEXT_STARTY = 40;
	private static final double TIMER_TEXT_STARTX = 800;
	private static final double TIMER_TEXT_STARTY = QUARTER_TEXT_STARTY;
	private static final double HOME_SCORE_TEXT_X =  620;
	private static final double AWAY_SCORE_TEXT_X = 900;
	// location of game stream text
	private static final double GAME_STREAM_TEXT_STARTX = 350;
	private static final double GAME_STREAM_TEXT_STARTY = TIMER_TEXT_STARTY + 130;
	private static final double GAME_STREAM_TEXT_BUFFER = 57;
	// location of team off/def/overall index before the game started
	private static final Location[] homeawayTeamIdx = { new Location(PREGAME_HOME_PLAYER_IMG_STARTX,730),
		new Location(PREGAME_HOME_PLAYER_IMG_STARTX,750),new Location(PREGAME_HOME_PLAYER_IMG_STARTX,770),
		new Location(PREGAME_AWAY_PLAYER_IMG_STARTX,730),new Location(PREGAME_AWAY_PLAYER_IMG_STARTX,750),
		new Location(PREGAME_AWAY_PLAYER_IMG_STARTX,770)
	};
	// location of player name, score, reb text.
	private static final double HOME_PLAYER_NAME_TEXT_STARTX = 10;
	private static final double AWAY_PLAYER_NAME_TEXT_STARTX = 1420;
	private static final double PLAYER_NAME_TEXT_STARTY = GAME_HOME_PLAYER_IMG_STARTY;
	private static final double PLAYER_NAME_TEXT_BUFFER = 130;

	private static final double HOME_PLAYER_INFO_TEXT_STARTX = HOME_PLAYER_NAME_TEXT_STARTX+20;
	private static final double AWAY_PLAYER_INFO_TEXT_STARTX = AWAY_PLAYER_NAME_TEXT_STARTX+20;
	private static final double PLAYER_INFO_TEXT_STARTY = PLAYER_NAME_TEXT_STARTY + 30;
	private static final double PLAYER_INFO_TEXT_BUFFER = PLAYER_NAME_TEXT_BUFFER;

	private static final double HOME_PLAYER_SCORE_TEXT_STARTX = HOME_PLAYER_INFO_TEXT_STARTX+20;
	private static final double AWAY_PLAYER_SCORE_TEXT_STARTX = AWAY_PLAYER_INFO_TEXT_STARTX+20;
	private static final double PLAYER_SCORE_TEXT_STARTY = PLAYER_INFO_TEXT_STARTY + 30;
	private static final double PLAYER_SCORE_TEXT_BUFFER = PLAYER_NAME_TEXT_BUFFER;


	private static final double PAUSE_X = 700;
	private static final double PAUSE_Y = 730;


	private static final double PLAYER_IMG_SIZE = 100;
	private static final double PLAYER_IMG_BUFFER = 30 + PLAYER_IMG_SIZE;	

	// the color of the text
	private static final Color HL_COLOR = new Color(255,0,0);
	// the head text
	private static final Color GAME_STREAM_TEXT_COLOR1 = new Color(255,0,0);
	// the texts that are not head
	private static final Color GAME_STREAM_TEXT_COLOR2 = new Color(100,0,255);
	private static final Color PAUSE_COLOR = new Color(255,0,0);

	// the controller of the game
	private NBAGameController controller;
	private TEAMString teamString;

	// the game thread 
	private NBAGame game;

	// Images for player profiles
	// position 1 to 5: PG to C
	private VisibleImage[] homePlayerImg = new VisibleImage[5];
	private VisibleImage[] awayPlayerImg = new VisibleImage[5];

	// Images for team Logo
	private VisibleImage warriorsLogoHome;
	private VisibleImage cavaliersLogoHome;
	private VisibleImage spursLogoHome;
	private VisibleImage thunderLogoHome;
	private VisibleImage raptorsLogoHome;
	private VisibleImage lakersLogoHome;
	private VisibleImage heatLogoHome;
	private VisibleImage clippersLogoHome;
	private VisibleImage rocketsLogoHome;
	private VisibleImage celticsLogoHome;

	private VisibleImage warriorsLogoAway;
	private VisibleImage cavaliersLogoAway;
	private VisibleImage spursLogoAway;
	private VisibleImage thunderLogoAway;
	private VisibleImage raptorsLogoAway;
	private VisibleImage lakersLogoAway;
	private VisibleImage heatLogoAway;
	private VisibleImage clippersLogoAway;
	private VisibleImage rocketsLogoAway;
	private VisibleImage celticsLogoAway;

	private VisibleImage warriorsLogoHomeLarge;
	private VisibleImage cavaliersLogoHomeLarge;
	private VisibleImage spursLogoHomeLarge;
	private VisibleImage thunderLogoHomeLarge;
	private VisibleImage raptorsLogoHomeLarge;
	private VisibleImage lakersLogoHomeLarge;
	private VisibleImage heatLogoHomeLarge;
	private VisibleImage clippersLogoHomeLarge;
	private VisibleImage rocketsLogoHomeLarge;
	private VisibleImage celticsLogoHomeLarge;

	private VisibleImage warriorsLogoAwayLarge;
	private VisibleImage cavaliersLogoAwayLarge;
	private VisibleImage spursLogoAwayLarge;
	private VisibleImage thunderLogoAwayLarge;
	private VisibleImage raptorsLogoAwayLarge;
	private VisibleImage lakersLogoAwayLarge;
	private VisibleImage heatLogoAwayLarge;
	private VisibleImage clippersLogoAwayLarge;
	private VisibleImage rocketsLogoAwayLarge;
	private VisibleImage celticsLogoAwayLarge;

	private VisibleImage court;

	private VisibleImage cover;

	// framed rectangle as highlight
	private FramedRect homeUpperLeftHL1;
	private FramedRect homeUpperLeftHL2;
	private FramedRect homeUpperLeftHL3;
	private FramedRect homeUpperRightHL1;
	private FramedRect homeUpperRightHL2;
	private FramedRect homeUpperRightHL3;
	private FramedRect homeLowerLeftHL1;
	private FramedRect homeLowerLeftHL2;
	private FramedRect homeLowerLeftHL3;
	private FramedRect homeLowerRightHL1;
	private FramedRect homeLowerRightHL2;
	private FramedRect homeLowerRightHL3;

	private FramedRect awayUpperLeftHL1;
	private FramedRect awayUpperLeftHL2;
	private FramedRect awayUpperLeftHL3;
	private FramedRect awayUpperRightHL1;
	private FramedRect awayUpperRightHL2;
	private FramedRect awayUpperRightHL3;
	private FramedRect awayLowerLeftHL1;
	private FramedRect awayLowerLeftHL2;
	private FramedRect awayLowerLeftHL3;
	private FramedRect awayLowerRightHL1;
	private FramedRect awayLowerRightHL2;
	private FramedRect awayLowerRightHL3;

	// the position of small team logos
	private Location homeUpperLeft;
	private Location homeUpperRight;
	private Location homeLowerLeft;
	private Location homeLowerRight;
	private Location awayUpperLeft;
	private Location awayUpperRight;
	private Location awayLowerLeft;
	private Location awayLowerRight;

	// some TEXT 

	// the upperLeft Home text
	private Text capitalHome;
	// the upperLeft Away text;
	private Text capitalAway;
	// at text before the game starts.
	private Text atText;

	private Text[] homeawayTeamIdxText = new Text[6];

	private Text startText;
	private Text homeTeamText;
	private Text awayTeamText;
	private Text pauseText;

	// the score text
	private Text homeScoreLabel;
	private Text awayScoreLabel;
	// the time and quarter text
	private Text timerText;
	private Text quarterText;

	// the game stream text.
	public static final int GAMESTREAMTEXT_LENGTH = 10;
	private Text[] gameStreamText = new Text[GAMESTREAMTEXT_LENGTH];

	// Text for player names
	private Text[] homePlayerNameText = new Text[5];
	private Text[] awayPlayerNameText = new Text[5];
	private Text[] homePlayerScoreText = new Text[5];
	private Text[] awayPlayerScoreText = new Text[5];
	private Text[] homePlayerRebText = new Text[5];
	private Text[] awayPlayerRebText = new Text[5];

	private Text[] homePlayerInfoText = new Text[5];
	private Text[] awayPlayerInfoText = new Text[5];

	// scores of the game
	private int homeScore = 0;
	private int awayScore = 0;

	// some important boolean flags

	// true if the game has started.
	private boolean isGameStarted = false;
	// true if the game is over 
	private boolean	isGameOver = false;
	// true if the game is paused
	private boolean isGamePaused = false;


	/** Constructor */
	public NBAGameController() {

	}

    /**
     * Method Name: begin
     * This method begins the game and assign values 
     * to many objects when the game get started
     */
	public void begin() {


    	// add key and mouse listener on the canvas
    	this.addMouseListener( this );
        canvas.addMouseListener( this );
        this.addKeyListener( this );
    	canvas.addKeyListener( this );

        // default match up is cav at gs
        controller = new NBAGameController();				
		game = new NBAGame(WARRIORS,CAVALIERS, controller,canvas);
		teamString = new TEAMString();

		//set up team logo. Show the logo  before the game starts.
		//  Warriors Cavaliers
		//  Spurs    thunder
		final int LOGO_LOGO_BUFFER = SMALL_TEAM_LOGO_SIZE + 6;
		homeUpperLeft = new Location(WIDTH_BUFFER, 100);
		homeUpperRight = new Location(WIDTH_BUFFER+LOGO_LOGO_BUFFER,100);
		homeLowerLeft = new Location(WIDTH_BUFFER,100+LOGO_LOGO_BUFFER);
		homeLowerRight = new Location(WIDTH_BUFFER+LOGO_LOGO_BUFFER,100+LOGO_LOGO_BUFFER);

		court = new VisibleImage(getImage("Team Court/Warriors Court.png"),200,100,1200,700,canvas);
		court.sendToBack();

		spursLogoHome = new VisibleImage(getImage("Team Logo/"+"Spurs_logo.GIF"),
			homeUpperLeft,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		spursLogoHome.show();

		cavaliersLogoHome = new VisibleImage(getImage("Team Logo/"+"Cavaliers_logo.png"),
			homeUpperRight,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		cavaliersLogoHome.show();

		warriorsLogoHome = new VisibleImage(getImage("Team Logo/"+"Warriors_logo.png"),
			homeLowerLeft,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		warriorsLogoHome.show();

		thunderLogoHome = new VisibleImage(getImage("Team Logo/"+"thunder_logo.GIF"),
			homeLowerRight,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		thunderLogoHome.show();

		clippersLogoHome = new VisibleImage(getImage("Team Logo/"+"Clippers_logo.jpg"),
			homeLowerRight.getX(), homeLowerRight.getY()+SMALL_TEAM_LOGO_SIZE,
			SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		clippersLogoHome.show();

		heatLogoHome = new VisibleImage(getImage("Team Logo/"+"heat_logo.jpg"),
			homeLowerLeft.getX(), homeLowerLeft.getY()+SMALL_TEAM_LOGO_SIZE,
			SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		heatLogoHome.show();


		awayUpperLeft = new Location(canvas.getWidth() - WIDTH_BUFFER - 2 * LOGO_LOGO_BUFFER, 100);
		awayUpperRight = new Location(canvas.getWidth() - WIDTH_BUFFER - LOGO_LOGO_BUFFER,100);
		awayLowerLeft = new Location(canvas.getWidth() - WIDTH_BUFFER - 2*LOGO_LOGO_BUFFER,100+LOGO_LOGO_BUFFER);
		awayLowerRight = new Location(canvas.getWidth() - WIDTH_BUFFER - LOGO_LOGO_BUFFER, 100+LOGO_LOGO_BUFFER);

		spursLogoAway = new VisibleImage(getImage("Team Logo/"+"Spurs_logo.GIF"),
			awayUpperLeft,SMALL_TEAM_LOGO_SIZE,
			SMALL_TEAM_LOGO_SIZE,canvas);
		spursLogoAway.show();

		cavaliersLogoAway = new VisibleImage(getImage("Team Logo/"+"Cavaliers_logo.png"),
			awayUpperRight,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,
			canvas);
		cavaliersLogoAway.show();

		warriorsLogoAway = new VisibleImage(getImage("Team Logo/"+"Warriors_logo.png"),
			awayLowerLeft, SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		warriorsLogoAway.show();

		thunderLogoAway = new VisibleImage(getImage("Team Logo/"+"thunder_logo.GIF"),
			awayLowerRight,SMALL_TEAM_LOGO_SIZE, 
			SMALL_TEAM_LOGO_SIZE,canvas);
		thunderLogoAway.show();

		clippersLogoAway = new VisibleImage(getImage("Team Logo/"+"Clippers_logo.jpg"),
			awayLowerRight.getX(), awayLowerRight.getY()+SMALL_TEAM_LOGO_SIZE,
			SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		clippersLogoAway.show();

		heatLogoAway = new VisibleImage(getImage("Team Logo/"+"heat_logo.jpg"),
			awayLowerLeft.getX(), awayLowerLeft.getY()+SMALL_TEAM_LOGO_SIZE,
			SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		heatLogoAway.show();

		// the x and y pos of the upper-left of the large logo
		final int LARGE_AWAY_LOGO_WIDTH_POS = canvas.getWidth()/2 - LOGO_LOGO_BUFFER/2;
		final int LARGE_AWAY_LOGO_HEIGHT_POS = 100;
		final int LARGE_HOME_LOGO_WIDTH_POS = LARGE_AWAY_LOGO_WIDTH_POS;
		final int LARGE_HOME_LOGO_HEIGHT_POS = canvas.getHeight() - LARGE_AWAY_LOGO_HEIGHT_POS - LARGE_TEAM_LOGO_SIZE;

		warriorsLogoHomeLarge = new VisibleImage(getImage("Team Logo/"+"Warriors_logo.png"),
			LARGE_HOME_LOGO_WIDTH_POS,LARGE_HOME_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		warriorsLogoAwayLarge = new VisibleImage(getImage("Team Logo/"+"Warriors_logo.png"),
			LARGE_AWAY_LOGO_WIDTH_POS,LARGE_AWAY_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);

		cavaliersLogoHomeLarge = new VisibleImage(getImage("Team Logo/"+"Cavaliers_logo.png"),
			LARGE_HOME_LOGO_WIDTH_POS,LARGE_HOME_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		cavaliersLogoAwayLarge = new VisibleImage(getImage("Team Logo/"+"Cavaliers_logo.png"),
			LARGE_AWAY_LOGO_WIDTH_POS,LARGE_AWAY_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		spursLogoHomeLarge = new VisibleImage(getImage("Team Logo/"+"Spurs_logo.png"),
			LARGE_HOME_LOGO_WIDTH_POS,LARGE_HOME_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		spursLogoAwayLarge = new VisibleImage(getImage("Team Logo/"+"Spurs_logo.png"),
			LARGE_AWAY_LOGO_WIDTH_POS,LARGE_AWAY_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		thunderLogoHomeLarge = new VisibleImage(getImage("Team Logo/"+"thunder_logo.png"),
			LARGE_HOME_LOGO_WIDTH_POS,LARGE_HOME_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		thunderLogoAwayLarge = new VisibleImage(getImage("Team Logo/"+"thunder_logo.png"),
			LARGE_AWAY_LOGO_WIDTH_POS,LARGE_AWAY_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);

		clippersLogoHomeLarge = new VisibleImage(getImage("Team Logo/"+"Clippers_logo.jpg"),
			LARGE_HOME_LOGO_WIDTH_POS,LARGE_HOME_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		clippersLogoAwayLarge = new VisibleImage(getImage("Team Logo/"+"Clippers_logo.jpg"),
			LARGE_AWAY_LOGO_WIDTH_POS,LARGE_AWAY_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		heatLogoHomeLarge = new VisibleImage(getImage("Team Logo/"+"Heat_logo.jpg"),
			LARGE_HOME_LOGO_WIDTH_POS,LARGE_HOME_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		heatLogoAwayLarge = new VisibleImage(getImage("Team Logo/"+"Heat_logo.jpg"),
			LARGE_AWAY_LOGO_WIDTH_POS,LARGE_AWAY_LOGO_HEIGHT_POS,
			LARGE_TEAM_LOGO_SIZE,LARGE_TEAM_LOGO_SIZE,canvas);
		// set up all the highlights
		setUpAllHighlights();
		

		// we don't hide cav's away logo and gs's home logo since it is the default match up
		thunderLogoAwayLarge.hide();
		thunderLogoHomeLarge.hide();					
		spursLogoAwayLarge.hide();
		spursLogoHomeLarge.hide();
		heatLogoAwayLarge.hide();
		heatLogoHomeLarge.hide();
		clippersLogoAwayLarge.hide();
		clippersLogoHomeLarge.hide();
		cavaliersLogoHomeLarge.hide();
		warriorsLogoAwayLarge.hide();

		homeawayTeamIdxText[0] = new Text("Offense: "+game.getHomeTeam().getOffense(), homeawayTeamIdx[0], canvas);
		homeawayTeamIdxText[1] = new Text("Defense: "+game.getHomeTeam().getDefense(), homeawayTeamIdx[1], canvas);
		homeawayTeamIdxText[2] = new Text("Overall: "+game.getHomeTeam().getOverAll(), homeawayTeamIdx[2], canvas);
		homeawayTeamIdxText[3] = new Text("Offense: "+game.getAwayTeam().getOffense(), homeawayTeamIdx[3], canvas);
		homeawayTeamIdxText[4] = new Text("Defense: "+game.getAwayTeam().getDefense(), homeawayTeamIdx[4], canvas);
		homeawayTeamIdxText[5] = new Text("Overall: "+game.getAwayTeam().getOverAll(), homeawayTeamIdx[5], canvas);

        atText = new Text("AT", canvas.getWidth()/2 + 2,canvas.getHeight()/2 -30, canvas);
        atText.setFontSize(AT_TEXT_SIZE);
        pauseText = new Text("PAUSED", PAUSE_X,PAUSE_Y,canvas);
        pauseText.setFontSize( PAUSE_TEXT_SIZE );
        pauseText.setColor(PAUSE_COLOR);
        pauseText.hide();
        capitalHome = new Text("HOME TEAM", 50,2,canvas );
        capitalHome.setFontSize( CAPITAL_HOME_TEXT_SIZE );
        capitalAway = new Text("AWAY TEAM", awayUpperRight.getX()-50,2,canvas);
        capitalAway.setFontSize(CAPITAL_AWAY_TEXT_SIZE);
        capitalAway.show();
        capitalAway.show();
        homeTeamText = new Text(WARRIORS,PREGAME_HOME_TEAM_TEXT_STARTX,PREGAME_HOME_TEAM_TEXT_STARTY,canvas);
        homeTeamText.setFontSize(CAPITAL_HOME_TEXT_SIZE);
        awayTeamText = new Text(CAVALIERS,PREGAME_AWAY_TEAM_TEXT_STARTX,PREGAME_AWAY_TEAM_TEXT_STARTY,canvas);
        awayTeamText.setFontSize(CAPITAL_AWAY_TEXT_SIZE);


        homeScoreLabel = new Text(" 0 ", HOME_SCORE_TEXT_X, GAME_HOME_TEAM_TEXT_STARTY, canvas);
        awayScoreLabel = new Text(" 0 ", AWAY_SCORE_TEXT_X, GAME_AWAY_TEAM_TEXT_STARTY, canvas);
        homeScoreLabel.setFontSize(CAPITAL_HOME_TEXT_SIZE);
        awayScoreLabel.setFontSize(CAPITAL_AWAY_TEXT_SIZE);

        // timer
        timerText = new Text(game.getTimer().refreshTimeString(),TIMER_TEXT_STARTX,TIMER_TEXT_STARTY,canvas );
        quarterText = new Text(game.getTimer().refreshQuarterString(),QUARTER_TEXT_STARTX,QUARTER_TEXT_STARTY,canvas);
        timerText.setFontSize(TIMER_SIZE);
        quarterText.setFontSize(TIMER_SIZE);
        quarterText.hide();
        timerText.hide();
        // game live stream
        for (int i = 0; i < GAMESTREAMTEXT_LENGTH; i++) {
        	gameStreamText[i] = new Text("",GAME_STREAM_TEXT_STARTX, 
        		GAME_STREAM_TEXT_STARTY + GAME_STREAM_TEXT_BUFFER*i, canvas);
        	gameStreamText[i].setFontSize(GAME_STREAM_TEXT_SIZE);
        	if (i == 0 ) {
        		gameStreamText[i].setColor(GAME_STREAM_TEXT_COLOR1);
        	}
        	else {
        		gameStreamText[i].setColor(GAME_STREAM_TEXT_COLOR2);
        	}
        	gameStreamText[i].hide();
        }
        // player name, score and rebound
        for (int k = 0; k<5;k++) {
        	homePlayerNameText[k] = new Text(game.getHomeTeam().getStartPlayer(k),HOME_PLAYER_NAME_TEXT_STARTX,
        		PLAYER_NAME_TEXT_STARTY+k*PLAYER_NAME_TEXT_BUFFER, canvas);
        	awayPlayerNameText[k] = new Text(game.getAwayTeam().getStartPlayer(k), AWAY_PLAYER_NAME_TEXT_STARTX,
        		PLAYER_NAME_TEXT_STARTY+k*PLAYER_NAME_TEXT_BUFFER,canvas);
        	homePlayerNameText[k].setFontSize(PLAYER_NAME_TEXT_SIZE);
        	awayPlayerNameText[k].setFontSize(PLAYER_NAME_TEXT_SIZE);
        	homePlayerNameText[k].hide();
        	awayPlayerNameText[k].hide();

        	homePlayerScoreText[k] = new Text("Score: "+game.getHomeStarterData(k).getScore(), HOME_PLAYER_SCORE_TEXT_STARTX,
        		PLAYER_SCORE_TEXT_STARTY + k*PLAYER_SCORE_TEXT_BUFFER,canvas );
        	awayPlayerScoreText[k] = new Text("Score: "+game.getAwayStarterData(k).getScore(), AWAY_PLAYER_SCORE_TEXT_STARTX,
        		PLAYER_SCORE_TEXT_STARTY + k*PLAYER_SCORE_TEXT_BUFFER,canvas  );
        	homePlayerScoreText[k].setFontSize(PLAYER_SCORE_TEXT_SIZE);
        	awayPlayerScoreText[k].setFontSize(PLAYER_SCORE_TEXT_SIZE);
        	homePlayerScoreText[k].hide();
        	awayPlayerScoreText[k].hide();

        	homePlayerInfoText[k] = new Text(game.getHomeTeam().getStarterPlayerObj(k).printPosition()+
        		"  OVERALL: "+game.getHomeTeam().getStarterPlayerObj(k).getOverAll(), HOME_PLAYER_INFO_TEXT_STARTX,
        		PLAYER_INFO_TEXT_STARTY+ k*PLAYER_INFO_TEXT_BUFFER, canvas);
        	awayPlayerInfoText[k] = new Text(game.getAwayTeam().getStarterPlayerObj(k).printPosition()+
        		"  OVERALL: "+game.getAwayTeam().getStarterPlayerObj(k).getOverAll(), AWAY_PLAYER_INFO_TEXT_STARTX,
        		PLAYER_INFO_TEXT_STARTY+ k*PLAYER_INFO_TEXT_BUFFER, canvas);
        	homePlayerInfoText[k].hide();
        	awayPlayerInfoText[k].hide();
        }

		// Content shown when the game starts
		for (int q=0;q<5;q++) {
        	homePlayerImg[q] = new VisibleImage( getImage("Player Profiles/"+game.getHomeTeam().getName()+"/"+
        		game.getHomeTeam().getStartPlayer(q)+".png"),PREGAME_HOME_PLAYER_IMG_STARTX,
        			PREGAME_HOME_PLAYER_IMG_STARTY + q*PLAYER_IMG_BUFFER,PLAYER_IMG_SIZE,PLAYER_IMG_SIZE,canvas);
    	}

    	for (int p=0;p<5;p++) {
    		awayPlayerImg[p] = new VisibleImage( getImage("Player Profiles/"+game.getAwayTeam().getName()+"/"+
        		game.getAwayTeam().getStartPlayer(p)+".png"),PREGAME_AWAY_PLAYER_IMG_STARTX,
    				PREGAME_AWAY_PLAYER_IMG_STARTY + p*PLAYER_IMG_BUFFER,PLAYER_IMG_SIZE,PLAYER_IMG_SIZE,canvas);
    	}


    	hideALLGameContent();
    	canvas.requestFocusInWindow();

    	while (true) {

    		if ( game.getisGameStarted() && !game.getisGamePaused() ) {
    			// now the game is going.
    			updateScoreLabel();
    			if ( !game.getisGameStringUpdated() ) {
    				timerText.setText( game.getTimerString() );
    				quarterText.setText( game.getQuarterString() );
    				refreshGameStreamText();
    				// update player score, rebound
    				for (int j = 0; j<5;j++) {
    					homePlayerScoreText[j].setText( "Score: "+game.getHomeStarterData(j).getScore() );
  						awayPlayerScoreText[j].setText( "Score: "+game.getAwayStarterData(j).getScore() );
    				}
    				game.setisGameStringUpdated(true);
    			}
    		}
    	}

	}
  /** 
   * implements KeyListener
   * This method handles the key typed event. 
   * We leave this method empty
   * 
   */
  public void keyTyped( KeyEvent e ) {

  }


  /** 
   * implements KeyListener
   * This method handles the key pressed event. 
   */

  public void keyPressed( KeyEvent e ) {

  	// press enter to start the game. used only when
  	// the game has not been started/ restarted yet.
  	if ( !game.getisGameOver() && !game.getisGameStarted() ) {
  		if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
  			startGame();
  		}
  	}

  	// press space bar the pause the game. we can pause the game only
  	// when the game starts
  	if ( !game.getisGameOver() && game.getisGameStarted() ) {
  		if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
  			boolean flag = ! game.getisGamePaused();
  			showpauseText(flag);
  			game.setisGamePaused( flag );
  		}  		
  	}

  	// type 1 to restart the game. we can restart teh game when we paused
  	// the game or the game is over.
	if ( game.getisGameOver() || game.getisGamePaused() ) {
  		if ( e.getKeyCode() == KeyEvent.VK_1 ) {

  			reStart();
  		}
  	}

  	// press BACK_SPACE to exit the game. we can exit the game when we paused
  	// the game or the game is over
  	if ( game.getisGameOver() || game.getisGamePaused() ) {
  		if ( e.getKeyCode() == KeyEvent.VK_BACK_SPACE ) {
  			System.exit(0);
  		}
  	}
	
  }

  /** 
   * implements KeyListener
   * This method handles the key released event. 
   * we leave this method empty
   */
  public void keyReleased( KeyEvent e ) {
        
  }	

  public void actionPerformed( ActionEvent e ) {
  	    canvas.requestFocusInWindow();
  } 

  /**
   * implements MouseListener
   * mouse Click
   */
  public void mouseClicked( MouseEvent e ) {

  	// only in set up stage
  	if ( !game.getisGameOver() && !game.getisGameStarted() ) {

  		// shows spurs home large logo
  		if ( e.getX()>homeUpperLeft.getX() && e.getX() < homeUpperLeft.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>homeUpperLeft.getY() && e.getY()< homeUpperLeft.getY()+SMALL_TEAM_LOGO_SIZE ) {
  			hideAllHomeLargeLogo();
  			spursLogoHomeLarge.show();
  			homeUpperLeftHL1.show();
  			homeUpperLeftHL2.show();
  			homeUpperLeftHL3.show();
  			court.setImage(getImage("Team Court/spurs court.jpg"));
  			game.setHomeTeam(SPURS);
  			resetAllHomeTeamInfo();
  		}
  		// shows cav's home large logo
  		else if ( e.getX()>homeUpperRight.getX() && e.getX() < homeUpperRight.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>homeUpperRight.getY() && e.getY()< homeUpperRight.getY()+SMALL_TEAM_LOGO_SIZE ) {
  			hideAllHomeLargeLogo();
  			cavaliersLogoHomeLarge.show();
  			homeUpperRightHL1.show();
  			homeUpperRightHL2.show();
  			homeUpperRightHL3.show();
  			court.setImage(getImage("Team Court/Cavaliers court.png"));
  			game.setHomeTeam(CAVALIERS);
  			resetAllHomeTeamInfo();
  		}
  		// shows heat's home large logo
  		else if ( e.getX()>homeLowerLeft.getX() && e.getX() < homeLowerLeft.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>homeLowerLeft.getY()+SMALL_TEAM_LOGO_SIZE && e.getY()< homeLowerLeft.getY()+SMALL_TEAM_LOGO_SIZE*2 ) {
  			hideAllHomeLargeLogo();
  			heatLogoHomeLarge.show();
			game.setHomeTeam(HEAT);
  			/*
  			homeUpperRightHL1.show();
  			homeUpperRightHL2.show();
  			homeUpperRightHL3.show();*/
  			court.setImage(getImage("Team Court/Heat court.png"));
  			resetAllHomeTeamInfo();
  		}
  		// shows clippers's home large logo
  		else if ( e.getX()>homeLowerRight.getX() && e.getX() < homeLowerRight.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>homeLowerRight.getY()+SMALL_TEAM_LOGO_SIZE && e.getY()< homeLowerRight.getY()+SMALL_TEAM_LOGO_SIZE*2 ) {
  			hideAllHomeLargeLogo();
  			clippersLogoHomeLarge.show();
  			game.setHomeTeam(CLIPPERS);
  			/*
  			homeUpperRightHL1.show();
  			homeUpperRightHL2.show();
  			homeUpperRightHL3.show();*/
  			court.setImage(getImage("Team Court/Clippers court.gif"));
  			resetAllHomeTeamInfo();
  		}

  		// shows gs's home large logo
  		else if ( e.getX()>homeLowerLeft.getX() && e.getX() < homeLowerLeft.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>homeLowerLeft.getY() && e.getY()< homeLowerLeft.getY()+SMALL_TEAM_LOGO_SIZE ) {
  			hideAllHomeLargeLogo();
  			warriorsLogoHomeLarge.show();
  			homeLowerLeftHL1.show();
  			homeLowerLeftHL2.show();
  			homeLowerLeftHL3.show();
  			court.setImage(getImage("Team Court/Warriors court.png"));
  			game.setHomeTeam(WARRIORS);
  			resetAllHomeTeamInfo();
  		}
  		// shows okc's home large logo
  		else if ( e.getX()>homeLowerRight.getX() && e.getX() < homeLowerRight.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>homeLowerRight.getY() && e.getY()< homeLowerRight.getY()+SMALL_TEAM_LOGO_SIZE ) {
  			hideAllHomeLargeLogo();
  			thunderLogoHomeLarge.show();
  			homeLowerRightHL1.show();
  			homeLowerRightHL2.show();
  			homeLowerRightHL3.show();
  			court.setImage(getImage("Team Court/thunder court.png"));
  			game.setHomeTeam(THUNDER);
  			resetAllHomeTeamInfo();
  		}  		
  		// shows spurs away large logo
  		else if ( e.getX()>awayUpperLeft.getX() && e.getX() < awayUpperLeft.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>awayUpperLeft.getY() && e.getY()< awayUpperLeft.getY()+SMALL_TEAM_LOGO_SIZE ) {
  			hideAllAwayLargeLogo();
  			spursLogoAwayLarge.show();
  			awayUpperLeftHL1.show();
  			awayUpperLeftHL2.show();
  			awayUpperLeftHL3.show();
  			game.setAwayTeam(SPURS);
  			resetAllAwayTeamInfo();
  		}
  		// shows cav's away large logo
  		else if ( e.getX()>awayUpperRight.getX() && e.getX() < awayUpperRight.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>awayUpperRight.getY() && e.getY()< awayUpperRight.getY()+SMALL_TEAM_LOGO_SIZE ) {
  			hideAllAwayLargeLogo();
  			cavaliersLogoAwayLarge.show();
  			awayUpperRightHL1.show();
  			awayUpperRightHL2.show();
  			awayUpperRightHL3.show();
  			game.setAwayTeam(CAVALIERS);
  			resetAllAwayTeamInfo();
  		}
  		// shows gs's away large logo
  		else if ( e.getX()>awayLowerLeft.getX() && e.getX() < awayLowerLeft.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>awayLowerLeft.getY() && e.getY()< awayLowerLeft.getY()+SMALL_TEAM_LOGO_SIZE ) {
  			hideAllAwayLargeLogo();
  			warriorsLogoAwayLarge.show();
  			awayLowerLeftHL1.show();
  			awayLowerLeftHL2.show();
  			awayLowerLeftHL3.show();
  			game.setAwayTeam(WARRIORS);
  			resetAllAwayTeamInfo();
  		}
  		// shows okc's away large logo
  		else if ( e.getX()>awayLowerRight.getX() && e.getX() < awayLowerRight.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>awayLowerRight.getY() && e.getY()< awayLowerRight.getY()+SMALL_TEAM_LOGO_SIZE ) {
  			hideAllAwayLargeLogo();
  			thunderLogoAwayLarge.show();
  			game.setAwayTeam(THUNDER);  	
  			awayLowerRightHL1.show();
  			awayLowerRightHL2.show();
  			awayLowerRightHL3.show();	
  			resetAllAwayTeamInfo();	
  		}  	
  		// shows heat's away large logo
  		else if ( e.getX()>awayLowerLeft.getX() && e.getX() < awayLowerLeft.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>awayLowerLeft.getY()+SMALL_TEAM_LOGO_SIZE && e.getY()< awayLowerLeft.getY()+SMALL_TEAM_LOGO_SIZE*2 ) {
  			hideAllAwayLargeLogo();
  			heatLogoAwayLarge.show();
			game.setAwayTeam(HEAT);
  			/*
  			homeUpperRightHL1.show();
  			homeUpperRightHL2.show();
  			homeUpperRightHL3.show();*/
  			resetAllAwayTeamInfo();
  		}
  		// shows clippers's away large logo
  		else if ( e.getX()>awayLowerRight.getX() && e.getX() < awayLowerRight.getX() + SMALL_TEAM_LOGO_SIZE
  			&& e.getY()>awayLowerRight.getY()+SMALL_TEAM_LOGO_SIZE && e.getY()< awayLowerRight.getY()+SMALL_TEAM_LOGO_SIZE*2 ) {
  			hideAllAwayLargeLogo();
  			clippersLogoAwayLarge.show();
			game.setAwayTeam(CLIPPERS);
  			/*
  			homeUpperRightHL1.show();
  			homeUpperRightHL2.show();
  			homeUpperRightHL3.show();*/
  			resetAllAwayTeamInfo();
  		}	
  	}

  }

  /**
   * implements MouseListener
   * mouse press
   */
  public void mousePressed( MouseEvent e ) {

  }

  /**
   * implements MouseListener
   * mouse released
   */
  public void mouseReleased( MouseEvent e ) {

  } 

  /**
   * implements MouseListener
   * mouse entered
   */
  public void mouseEntered( MouseEvent e ) {

  }

  /**
   * implements MouseListener
   * mouse exited
   */
  public void mouseExited( MouseEvent e ) {

  }

  /** 
   * update the home team score label
   * @param the new score of the home team
   */
  public synchronized void updateScoreLabel() {
  	homeScoreLabel.setText(" "+game.getHomeScore()+" ");
  	awayScoreLabel.setText(" "+game.getAwayScore()+" " );
  }
  /** 
   * update the away team score label
   * @param the new score of the away team
   */
  public synchronized void updateawayScoreLabel() {
  	awayScoreLabel.setText(" "+game.getAwayScore()+" ");
  }

/*
  public synchronized void setisGameOver(boolean b) {
  	isGameOver = b;
  }

  public synchronized boolean getisGameOver() {
  	return isGameOver;
  }

  public synchronized void setisGameStarted (boolean b) {
  	isGameStarted = b;
  }

  public synchronized boolean getisGameStarted() {
  	return isGameStarted;
  }
  public synchronized void setisGamePaused (boolean b) {
  	isGamePaused = b;
  }

  public synchronized boolean getisGamePaused() {
  	return isGamePaused;
  }*/

  /** the total score of the home team */
  public synchronized int gethomeScore() {
  	return homeScore;
  }
  /** set the home team score */
  public synchronized void sethomeScore(int i) {
  	homeScore = i;
  }
  /** the total score of the away team */
  public synchronized int getawayScore() {
  	return awayScore;
  }
  /** set the away team score */
  public synchronized void setawayScore(int i) {
  	awayScore = i;
  }



  /**
   * reset the away team info including player profiles, away team string when we change the away team
   */
  public void resetAllAwayTeamInfo() {
  	for (int i =0;i<5;i++) {
  		awayPlayerImg[i].setImage(getImage("Player Profiles/"+game.getAwayTeam().getName()+"/"+
        		game.getAwayTeam().getStartPlayer(i)+".png"));
  	}
  	awayTeamText.setText(game.getAwayTeam().getName());
  	homeawayTeamIdxText[3].setText("Offense: "+game.getAwayTeam().getOffense());
  	homeawayTeamIdxText[4].setText("Defense: "+game.getAwayTeam().getDefense());
  	homeawayTeamIdxText[5].setText("OverAll: "+game.getAwayTeam().getOverAll());
  }

  /**
   * reset the home team info including player profiles, home team string when we change the home team
   */
  public void resetAllHomeTeamInfo() {
  	for (int i =0;i<5;i++) {
  		homePlayerImg[i].setImage(getImage("Player Profiles/"+game.getHomeTeam().getName()+"/"+
        		game.getHomeTeam().getStartPlayer(i)+".png"));
  	}
  	homeTeamText.setText(game.getHomeTeam().getName());
  	homeawayTeamIdxText[0].setText("Offense: "+game.getHomeTeam().getOffense());
  	homeawayTeamIdxText[1].setText("Defense: "+game.getHomeTeam().getDefense());
  	homeawayTeamIdxText[2].setText("OverAll: "+game.getHomeTeam().getOverAll());
  	
  }

  /** Set up all the high lights for team choice */
  public void setUpAllHighlights() {

		// home upperleft
		homeUpperLeftHL1 = new FramedRect(homeUpperLeft,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		homeUpperLeftHL1.setColor(HL_COLOR);
		homeUpperLeftHL2 = new FramedRect(homeUpperLeft.getX()+1,homeUpperLeft.getY()+1,
			SMALL_TEAM_LOGO_SIZE-2,SMALL_TEAM_LOGO_SIZE-2,canvas);
		homeUpperLeftHL3 = new FramedRect(homeUpperLeft.getX()-1,homeUpperLeft.getY()-1,
			SMALL_TEAM_LOGO_SIZE+2,SMALL_TEAM_LOGO_SIZE+2,canvas);		
		homeUpperLeftHL3.setColor(HL_COLOR);
		homeUpperLeftHL2.setColor(HL_COLOR);
		homeUpperLeftHL2.hide();
		homeUpperLeftHL3.hide();
		homeUpperLeftHL1.hide();

		// home upper right
		homeUpperRightHL1 = new FramedRect(homeUpperRight,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		homeUpperRightHL1.setColor(HL_COLOR);
		homeUpperRightHL2 = new FramedRect(homeUpperRight.getX()+1,homeUpperRight.getY()+1,
			SMALL_TEAM_LOGO_SIZE-2,SMALL_TEAM_LOGO_SIZE-2,canvas);
		homeUpperRightHL3 = new FramedRect(homeUpperRight.getX()-1,homeUpperRight.getY()-1,
			SMALL_TEAM_LOGO_SIZE+2,SMALL_TEAM_LOGO_SIZE+2,canvas);		
		homeUpperRightHL3.setColor(HL_COLOR);
		homeUpperRightHL2.setColor(HL_COLOR);
		homeUpperRightHL2.hide();
		homeUpperRightHL3.hide();
		homeUpperRightHL1.hide();

		// home lower left
		homeLowerLeftHL1 = new FramedRect(homeLowerLeft,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		homeLowerLeftHL1.setColor(HL_COLOR);
		homeLowerLeftHL2 = new FramedRect(homeLowerLeft.getX()+1,homeLowerLeft.getY()+1,
			SMALL_TEAM_LOGO_SIZE-2,SMALL_TEAM_LOGO_SIZE-2,canvas);
		homeLowerLeftHL3 = new FramedRect(homeLowerLeft.getX()-1,homeLowerLeft.getY()-1,
			SMALL_TEAM_LOGO_SIZE+2,SMALL_TEAM_LOGO_SIZE+2,canvas);		
		homeLowerLeftHL3.setColor(HL_COLOR);
		homeLowerLeftHL2.setColor(HL_COLOR);

		//home lower right
		homeLowerRightHL1 = new FramedRect(homeLowerRight,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		homeLowerRightHL1.setColor(HL_COLOR);
		homeLowerRightHL2 = new FramedRect(homeLowerRight.getX()+1,homeLowerRight.getY()+1,
			SMALL_TEAM_LOGO_SIZE-2,SMALL_TEAM_LOGO_SIZE-2,canvas);
		homeLowerRightHL3 = new FramedRect(homeLowerRight.getX()-1,homeLowerRight.getY()-1,
			SMALL_TEAM_LOGO_SIZE+2,SMALL_TEAM_LOGO_SIZE+2,canvas);		
		homeLowerRightHL3.setColor(HL_COLOR);
		homeLowerRightHL2.setColor(HL_COLOR);
		homeLowerRightHL2.hide();
		homeLowerRightHL3.hide();
		homeLowerRightHL1.hide();


		// away upperleft
		awayUpperLeftHL1 = new FramedRect(awayUpperLeft,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		awayUpperLeftHL1.setColor(HL_COLOR);
		awayUpperLeftHL2 = new FramedRect(awayUpperLeft.getX()+1,awayUpperLeft.getY()+1,
			SMALL_TEAM_LOGO_SIZE-2,SMALL_TEAM_LOGO_SIZE-2,canvas);
		awayUpperLeftHL3 = new FramedRect(awayUpperLeft.getX()-1,awayUpperLeft.getY()-1,
			SMALL_TEAM_LOGO_SIZE+2,SMALL_TEAM_LOGO_SIZE+2,canvas);		
		awayUpperLeftHL3.setColor(HL_COLOR);
		awayUpperLeftHL2.setColor(HL_COLOR);
		awayUpperLeftHL2.hide();
		awayUpperLeftHL3.hide();
		awayUpperLeftHL1.hide();

		// away upper right
		awayUpperRightHL1 = new FramedRect(awayUpperRight,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		awayUpperRightHL1.setColor(HL_COLOR);
		awayUpperRightHL2 = new FramedRect(awayUpperRight.getX()+1,awayUpperRight.getY()+1,
			SMALL_TEAM_LOGO_SIZE-2,SMALL_TEAM_LOGO_SIZE-2,canvas);
		awayUpperRightHL3 = new FramedRect(awayUpperRight.getX()-1,awayUpperRight.getY()-1,
			SMALL_TEAM_LOGO_SIZE+2,SMALL_TEAM_LOGO_SIZE+2,canvas);		
		awayUpperRightHL3.setColor(HL_COLOR);
		awayUpperRightHL2.setColor(HL_COLOR);


		// away lower left
		awayLowerLeftHL1 = new FramedRect(awayLowerLeft,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		awayLowerLeftHL1.setColor(HL_COLOR);
		awayLowerLeftHL2 = new FramedRect(awayLowerLeft.getX()+1,awayLowerLeft.getY()+1,
			SMALL_TEAM_LOGO_SIZE-2,SMALL_TEAM_LOGO_SIZE-2,canvas);
		awayLowerLeftHL3 = new FramedRect(awayLowerLeft.getX()-1,awayLowerLeft.getY()-1,
			SMALL_TEAM_LOGO_SIZE+2,SMALL_TEAM_LOGO_SIZE+2,canvas);		
		awayLowerLeftHL3.setColor(HL_COLOR);
		awayLowerLeftHL2.setColor(HL_COLOR);
		awayLowerLeftHL2.hide();
		awayLowerLeftHL3.hide();
		awayLowerLeftHL1.hide();
		//away lower right
		awayLowerRightHL1 = new FramedRect(awayLowerRight,SMALL_TEAM_LOGO_SIZE,SMALL_TEAM_LOGO_SIZE,canvas);
		awayLowerRightHL1.setColor(HL_COLOR);
		awayLowerRightHL2 = new FramedRect(awayLowerRight.getX()+1,awayLowerRight.getY()+1,
			SMALL_TEAM_LOGO_SIZE-2,SMALL_TEAM_LOGO_SIZE-2,canvas);
		awayLowerRightHL3 = new FramedRect(awayLowerRight.getX()-1,awayLowerRight.getY()-1,
			SMALL_TEAM_LOGO_SIZE+2,SMALL_TEAM_LOGO_SIZE+2,canvas);		
		awayLowerRightHL3.setColor(HL_COLOR);
		awayLowerRightHL2.setColor(HL_COLOR);
		awayLowerRightHL2.hide();
		awayLowerRightHL3.hide();
		awayLowerRightHL1.hide();		  	
  }


  /**
   * hide all the large home logo 
   * used in mouse click event before setUp 
   */
  public void hideAllHomeLargeLogo() {
  	spursLogoHomeLarge.hide();
  	warriorsLogoHomeLarge.hide();
  	cavaliersLogoHomeLarge.hide();
  	thunderLogoHomeLarge.hide();
  	clippersLogoHomeLarge.hide();
	heatLogoHomeLarge.hide();

  	homeUpperLeftHL1.hide();
  	homeUpperLeftHL2.hide();
  	homeUpperLeftHL3.hide();
  	homeUpperRightHL3.hide();
  	homeUpperRightHL2.hide();
  	homeUpperRightHL1.hide();
  	homeLowerRightHL3.hide();
  	homeLowerRightHL2.hide();
  	homeLowerRightHL1.hide();
  	homeLowerLeftHL3.hide();
  	homeLowerLeftHL2.hide();
  	homeLowerLeftHL1.hide();
  }

  /**
   * hide all the large away logo 
   * used in mouse click event before setUp 
   */
  public void hideAllAwayLargeLogo() {
  	spursLogoAwayLarge.hide();
  	warriorsLogoAwayLarge.hide();
  	cavaliersLogoAwayLarge.hide();
  	thunderLogoAwayLarge.hide();
  	heatLogoAwayLarge.hide();
	clippersLogoAwayLarge.hide();

  	awayUpperLeftHL1.hide();
  	awayUpperLeftHL2.hide();
  	awayUpperLeftHL3.hide();
  	awayUpperRightHL3.hide();
  	awayUpperRightHL2.hide();
  	awayUpperRightHL1.hide();
  	awayLowerRightHL3.hide();
  	awayLowerRightHL2.hide();
  	awayLowerRightHL1.hide();
  	awayLowerLeftHL3.hide();
  	awayLowerLeftHL2.hide();
  	awayLowerLeftHL1.hide();  	
  }

  /**
   * hide all the content before the game starts
   */
  public void hideALLPreGameContent() {
  	spursLogoAway.hide();
  	spursLogoHome.hide();
  	spursLogoAwayLarge.hide();
  	spursLogoHomeLarge.hide();
  	for (int i = 0; i<6; i++) {
  		homeawayTeamIdxText[i].hide();
  	}

  	cavaliersLogoAway.hide();
  	cavaliersLogoHome.hide();
  	cavaliersLogoAwayLarge.hide();
  	cavaliersLogoHomeLarge.hide();

  	warriorsLogoAway.hide();
  	warriorsLogoHome.hide();
  	warriorsLogoAwayLarge.hide();
  	warriorsLogoHomeLarge.hide();

  	thunderLogoAway.hide();
  	thunderLogoHome.hide();
  	thunderLogoAwayLarge.hide();
  	thunderLogoHomeLarge.hide();

	clippersLogoAway.hide();
  	clippersLogoHome.hide();
  	clippersLogoAwayLarge.hide();
  	clippersLogoHomeLarge.hide(); 

  	heatLogoAway.hide();
  	heatLogoHome.hide();
  	heatLogoAwayLarge.hide();
  	heatLogoHomeLarge.hide(); 
  	// hide all the highlights

  	homeUpperLeftHL1.hide();
  	homeUpperLeftHL2.hide();
  	homeUpperLeftHL3.hide();
  	homeUpperRightHL3.hide();
  	homeUpperRightHL2.hide();
  	homeUpperRightHL1.hide();
  	homeLowerRightHL3.hide();
  	homeLowerRightHL2.hide();
  	homeLowerRightHL1.hide();
  	homeLowerLeftHL3.hide();
  	homeLowerLeftHL2.hide();
  	homeLowerLeftHL1.hide();
  	awayUpperLeftHL1.hide();
  	awayUpperLeftHL2.hide();
  	awayUpperLeftHL3.hide();
  	awayUpperRightHL3.hide();
  	awayUpperRightHL2.hide();
  	awayUpperRightHL1.hide();
  	awayLowerRightHL3.hide();
  	awayLowerRightHL2.hide();
  	awayLowerRightHL1.hide();
  	awayLowerLeftHL3.hide();
  	awayLowerLeftHL2.hide();
  	awayLowerLeftHL1.hide();  	

  	// hide all the text
  	atText.hide();
  }

  /**
   * Call this method the jump to setup stage to match stage
   * the start get started here
   */
  public void startGame() {
  	game.setisGameStarted(true);
  	hideALLPreGameContent();
  	showALLGameContent();
  	for (int i = 0; i<5; i++) {
  		homePlayerImg[i].moveTo(GAME_HOME_PLAYER_IMG_STARTX,GAME_AWAY_PLAYER_IMG_STARTY+i*PLAYER_IMG_BUFFER);
  		awayPlayerImg[i].moveTo(GAME_AWAY_PLAYER_IMG_STARTX,GAME_AWAY_PLAYER_IMG_STARTY+i*PLAYER_IMG_BUFFER);
  	}
  	homeTeamText.moveTo(GAME_HOME_TEAM_TEXT_STARTX,GAME_HOME_TEAM_TEXT_STARTY);
  	awayTeamText.moveTo(GAME_AWAY_TEAM_TEXT_STARTX,GAME_AWAY_TEAM_TEXT_STARTY);
  	// reset all the player name, score, reb text
  	for ( int j = 0;j<5;j++) {
  		homePlayerNameText[j].setText( game.getHomeTeam().getStartPlayer(j) );
  		awayPlayerNameText[j].setText( game.getAwayTeam().getStartPlayer(j) );
  		homePlayerScoreText[j].setText( "Score: "+game.getHomeStarterData(j).getScore() );
  		awayPlayerScoreText[j].setText( "Score: "+game.getAwayStarterData(j).getScore() );
  		homePlayerInfoText[j].setText(game.getHomeTeam().getStarterPlayerObj(j).printPosition()+
        		"  OVERALL: "+game.getHomeTeam().getStarterPlayerObj(j).getOverAll() );
  		awayPlayerInfoText[j].setText(game.getAwayTeam().getStarterPlayerObj(j).printPosition()+
        		"  OVERALL: "+game.getAwayTeam().getStarterPlayerObj(j).getOverAll() );
  	}
  }

  /**
   * restarts the game
   * restore everything in default setup
   */
  public void reStart() {
  	game.setisGameStarted(false);
  	game.setisGameOver(false);
  	game.setisGamePaused(false);
  	game = new NBAGame(WARRIORS,CAVALIERS,controller,canvas);
  	resetAllHomeTeamInfo();
  	resetAllAwayTeamInfo();
  	showALLPreGameContent();
  	hideALLGameContent();
  	hideAllAwayLargeLogo();
  	hideAllHomeLargeLogo();
  	homeTeamText.setText(WARRIORS);
  	awayTeamText.setText(CAVALIERS);
  	court.setImage(getImage("team court/warriors court.png"));
  	warriorsLogoHomeLarge.show();
  	cavaliersLogoAwayLarge.show();
  	homeLowerLeftHL2.show();
  	homeLowerLeftHL3.show();
  	homeLowerLeftHL1.show();
  	awayUpperRightHL3.show();
  	awayUpperRightHL2.show();
  	awayUpperRightHL1.show();
  	for (int i = 0; i<5; i++) {
  		homePlayerImg[i].moveTo(PREGAME_HOME_PLAYER_IMG_STARTX,PREGAME_AWAY_PLAYER_IMG_STARTY+i*PLAYER_IMG_BUFFER);
  		awayPlayerImg[i].moveTo(PREGAME_AWAY_PLAYER_IMG_STARTX,PREGAME_AWAY_PLAYER_IMG_STARTY+i*PLAYER_IMG_BUFFER);
  	}
  	homeTeamText.moveTo(PREGAME_HOME_TEAM_TEXT_STARTX,PREGAME_HOME_TEAM_TEXT_STARTY);
  	awayTeamText.moveTo(PREGAME_AWAY_TEAM_TEXT_STARTX,PREGAME_AWAY_TEAM_TEXT_STARTY);

  }

  /**
   * show all the content after the game starts
   */
  public void showALLGameContent() {
  	court.show();
  	homeScoreLabel.show();
  	awayScoreLabel.show();
  	quarterText.show();
  	timerText.show();
  	if (game.getHomeTeam().getName().equals(SPURS) || game.getHomeTeam().getName().equals(HEAT)) {
  		homeScoreLabel.setColor(new Color(255,255,0));
  		awayScoreLabel.setColor(new Color(255,255,0));
  		homeTeamText.setColor(new Color(255,255,0));
  		awayTeamText.setColor(new Color(255,255,0));
  	}
  	for ( int i = 0; i<GAMESTREAMTEXT_LENGTH;i++) {
  		gameStreamText[i].show();
  	}
  	for ( int j = 0;j<5;j++) {
  		homePlayerNameText[j].show();
  		awayPlayerNameText[j].show();
  		homePlayerScoreText[j].show();
  		awayPlayerScoreText[j].show();
  		homePlayerInfoText[j].show();
  		awayPlayerInfoText[j].show();  		
  	}
  }

  /**
   * hide all the content after the game starts
   */
  public void hideALLGameContent() {
  	court.hide();
  	pauseText.hide();
  	homeScoreLabel.hide();
  	awayScoreLabel.hide();
  	quarterText.hide();
  	timerText.hide();
  	homeScoreLabel.setColor(new Color(0,0,0));
  	awayScoreLabel.setColor(new Color(0,0,0));
  	homeTeamText.setColor(new Color(0,0,0));
  	awayTeamText.setColor(new Color(0,0,0));
  	for ( int i = 0; i<GAMESTREAMTEXT_LENGTH;i++) {
  		gameStreamText[i].hide();
  	}
  	for ( int j = 0;j<5;j++) {
  		homePlayerNameText[j].hide();
  		awayPlayerNameText[j].hide();
  		homePlayerScoreText[j].hide();
  		awayPlayerScoreText[j].hide();  		
  		homePlayerInfoText[j].hide();
  		awayPlayerInfoText[j].hide();
  	}
  }

  /**
   * show all the content before the game starts
   */
  public void showALLPreGameContent() {
  	spursLogoAway.show();
  	spursLogoHome.show();
	clippersLogoAway.show();
  	clippersLogoHome.show();
    heatLogoAway.show();
  	heatLogoHome.show();
  	for (int i=0; i<6; i++) {
  		homeawayTeamIdxText[i].show();
  	}

  	cavaliersLogoAway.show();
  	cavaliersLogoHome.show();
  	cavaliersLogoAwayLarge.show();

  	warriorsLogoAway.show();
  	warriorsLogoHome.show();
  	warriorsLogoHomeLarge.show();

  	thunderLogoAway.show();
  	thunderLogoHome.show();

  	// show texts
  	atText.show();

  	// show the default highlights
  	homeLowerLeftHL2.show();
  	homeLowerLeftHL3.show();
  	homeLowerLeftHL1.show();
  	awayUpperRightHL3.show();
  	awayUpperRightHL2.show();
  	awayUpperRightHL1.show();

  }

  // this method refreshed the game stream text
  public void refreshGameStreamText() {
  	for (int i = 0; i< GAMESTREAMTEXT_LENGTH; i++) {
  		gameStreamText[i].setText( game.getGameString(i) );
  	}

  }

  /**
   * show/hide the pause Text when the game paused/continued
   * @param flag to indicate the status of the game
   */
  public void showpauseText(boolean flag) {
  	if (flag) {
  		pauseText.show();
  	}
  	else {
  		pauseText.hide();
  	}
  }

	public static void main (String[] args) {

    	new Acme.MainFrame( new NBAGameController(), args,
	      FRAME_WIDTH, FRAME_HEIGHT);

	}


}