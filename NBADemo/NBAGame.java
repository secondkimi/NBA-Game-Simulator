package NBADemo;

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import NBADemo.NBAGameController;
import NBADemo.MyQueue;

/*
 * This class create a NBA Game.
 */
public class NBAGame extends ActiveObject implements KeyListener {

	private NBATeam homeTeam;
	private NBATeam awayTeam;
	// the data of the players
	private MyMap<String,PlayerData> homeTeamData;
	private MyMap<String,PlayerData> awayTeamData;
	private PlayerData[] homeStarterData = new PlayerData[5];
	private PlayerData[] awayStarterData = new PlayerData[5];

	private int homeScore = 0;
	private int awayScore = 0;
	// controller of the game
	private NBAGameController controller;
	private DrawingCanvas canvas;
	public boolean isGameStringUpdated;
	private MyQueue<String> gameString;
	private int gameStringCapacity;

	private NBAGameTimer timer;

	private NBAGameString gameStrCollector;

	private int[] homePlayerScore = new int[15];
	private int[] awayPlayerScore = new int[15];
	// 0 to 4  pg to center

	// some important boolean flags

	// true if the game has started.
	private boolean isGameStarted = false;
	// true if the game is over 
	private boolean	isGameOver = false;
	// true if the game is paused
	private boolean isGamePaused = false;

	// true if the home team possess the ball
	private boolean isHomeBall;

	private static final double DEFAULT_PAUSE_TIME = 1300;
	// larger the buffer value, the harder to score. Away team is 
	// harder to score so the buffer is larger
	private static final int HOME_TWO_BUFFER = 6;
	private static final int HOME_THREE_BUFFER = 15;  
	private static final int AWAY_TWO_BUFFER = HOME_TWO_BUFFER + 2;
	private static final int AWAY_THREE_BUFFER = HOME_THREE_BUFFER + 2;

	private double pauseTime = DEFAULT_PAUSE_TIME;

	/** Constructor */
	public NBAGame(NBAGameController ctrl, DrawingCanvas c ) {
		// default set up: home: Warriors away: Cavaliers
		homeTeam = new NBATeam("Warriors");
		awayTeam = new NBATeam("Cavaliers");
		homeTeamData = new MyMap<String,PlayerData>();
		awayTeamData = new MyMap<String,PlayerData>();
		homeStarterData = new PlayerData[5];
		awayStarterData = new PlayerData[5];
		for (int j = 0; j<5;j++) {
			homeTeamData.put(homeTeam.getStartPlayer(j),new PlayerData(homeTeam.getStartPlayer(j)));
			awayTeamData.put(awayTeam.getStartPlayer(j),new PlayerData(awayTeam.getStartPlayer(j)));
			homeStarterData[j] = new PlayerData(homeTeam.getStartPlayer(j));
			awayStarterData[j] = new PlayerData(awayTeam.getStartPlayer(j));
		}
		gameStringCapacity = ctrl.GAMESTREAMTEXT_LENGTH;
		gameString = new MyQueue<String>(gameStringCapacity);
		for (int i = 0; i<gameStringCapacity;i++) {
			gameString.add("");
		}
		gameStrCollector = new NBAGameString();
		isGameStringUpdated = true;
		controller = ctrl;
		canvas = c;
		canvas.addKeyListener( this );
		timer = new NBAGameTimer();
		start();
	}

	/** Constructor with two parameter: home team and away team */
	public NBAGame( String home, String away, NBAGameController ctrl, DrawingCanvas c ) {
		homeTeam = new NBATeam(home);
		awayTeam = new NBATeam(away);
		homeTeamData = new MyMap<String,PlayerData>();
		awayTeamData = new MyMap<String,PlayerData>();
		homeStarterData = new PlayerData[5];
		awayStarterData = new PlayerData[5];
		for (int j = 0; j<5;j++) {
			homeTeamData.put(homeTeam.getStartPlayer(j),new PlayerData(homeTeam.getStartPlayer(j)));
			awayTeamData.put(awayTeam.getStartPlayer(j),new PlayerData(awayTeam.getStartPlayer(j)));
			homeStarterData[j] = new PlayerData(homeTeam.getStartPlayer(j));
			awayStarterData[j] = new PlayerData(awayTeam.getStartPlayer(j));
		}
		gameStringCapacity = ctrl.GAMESTREAMTEXT_LENGTH;
		gameString = new MyQueue<String>(gameStringCapacity);
		for (int i = 0; i<gameStringCapacity;i++) {
			gameString.add("");
		}
		gameStrCollector = new NBAGameString();
		isGameStringUpdated = true;
		controller = ctrl;
		canvas = c;
		canvas.addKeyListener( this );
		timer = new NBAGameTimer();		
		start();
	}

	/**
	 * Helper method. change the home team
	 * @param String teamName - the name home team
	 */
	public synchronized void setHomeTeam(String home) {
		homeTeam = new NBATeam(home);
	}

	/**
	 * Helper method. change the away team
	 * @param String teamName - the name home team
	 */
	public synchronized void setAwayTeam(String away) {
		awayTeam = new NBATeam(away);
	}

    /**
     * @return the home team
     */
	public synchronized NBATeam getHomeTeam() {
		return homeTeam;
	}
	/**
	 * @return the away team
	 */
	public synchronized NBATeam getAwayTeam() {
		return awayTeam;
	}

	/**
	 * get the element at index idx of the gameString queue
	 */
	public synchronized String getGameString( int idx ) {
		if ( idx >= gameStringCapacity) 
			return null;
		else {
			return gameString.getItem(idx);
		}
	}

	/**
	 * Starts the animation of the Game
	 */
	public void run() {
		while ( true ) {
			setisGameStringUpdated(true);

			// the game starts 
			if ( getisGameStarted() && ! getisGameOver() && !getisGamePaused() ) {
				// how many seconds this play takes
				// basically from 8 seconds to 24 seconds
				timer.timeEclipse();
				Random rd = new Random();
				int s = rd.nextInt(6);
				// home ball
				if ( getisHomeBall() ) {

					int t = homeTeam.shootingPlayer(timer.getisFinalTwoMin()); // the player at position t made a shot
					int choice = 2; // 2 if go for 2 and 3 if go for 3
					NBAPlayer shooter = new NBAPlayer(homeTeam.getStartPlayer(t));
					NBAPlayer defender = new NBAPlayer(awayTeam.getStartPlayer(t));
					int totalDenfenseIndex = defender.getAthleticism()/3 + (defender.getDefense()+awayTeam.getDefense())/2;
					if ( shooter.getThreePointShooting() > 55 ) {
						// this player has three ability greater than 50, so he can make three
						int cutOff = shooter.getTwoPointShooting();
						int num = 0;
						if ( shooter.getTwoPointShooting() <= shooter.getThreePointShooting() ) {
							// this player is more plausible to make three 
							num = rd.nextInt(cutOff+(int) (shooter.getThreePointShooting()*0.5));
						}
						else {
							num = rd.nextInt(cutOff + (int) (shooter.getThreePointShooting()*0.4 )); 
						}
						if ( num >= cutOff ) {
							choice = 3;
						}
					}

					if ( choice == 2 ) {
						int totalOffIndex = shooter.getTwoPointShooting() + shooter.getAthleticism()/3;
						int h = rd.nextInt(totalOffIndex+totalDenfenseIndex+HOME_TWO_BUFFER);
						if ( h < totalOffIndex ) {
							// get two points
							int currScore = getHomeScore();
							setHomeScore(currScore+2);
							int pos = rd.nextInt(5);

							homeStarterData[t].setScore( homeStarterData[t].getScore() + 2 );
							gameString.add(timer.refreshQuarterString()+" "+timer.refreshTimeString()+" "+
							gameStrCollector.getTwoPoint(homeTeam,homeTeam.getStartPlayer(t))+"("+getHomeScore()+":"+getAwayScore()+")" );
						}
						else {
							// tried for two but did not make it
							gameString.add(timer.refreshQuarterString()+" "+timer.refreshTimeString()+" "+
							gameStrCollector.missTwoPoint(homeTeam,homeTeam.getStartPlayer(t))+"("+getHomeScore()+":"+getAwayScore()+")" );
						}

					}

					else if ( choice == 3 ) {
						int totalOffIndex = shooter.getThreePointShooting() + shooter.getAthleticism()/3;
						int h = rd.nextInt( totalOffIndex + totalDenfenseIndex+HOME_THREE_BUFFER );
						if ( h < totalOffIndex ) {
							int currScore = getHomeScore();
							setHomeScore(currScore+3);
							homeStarterData[t].setScore( homeStarterData[t].getScore() + 3 );
							gameString.add(timer.refreshQuarterString()+" "+timer.refreshTimeString()+" "+
							gameStrCollector.getThreePoint(homeTeam,homeTeam.getStartPlayer(t))+"("+getHomeScore()+":"+getAwayScore()+")" );
						}
						else {
							// tried for three but did not make it
							gameString.add(timer.refreshQuarterString()+" "+timer.refreshTimeString()+" "+
							gameStrCollector.missThreePoint(homeTeam,homeTeam.getStartPlayer(t))+"("+getHomeScore()+":"+getAwayScore()+")" );
						}
					}
					flipisHomeBall();
				}
				
				// away ball 
				else {
					int t = awayTeam.shootingPlayer(timer.getisFinalTwoMin()); // the player at position t made a shot
					int choice = 2; // 2 if go for 2 and 3 if go for 3
					NBAPlayer shooter = new NBAPlayer(awayTeam.getStartPlayer(t));
					NBAPlayer defender = new NBAPlayer(homeTeam.getStartPlayer(t));
					int totalDenfenseIndex = defender.getAthleticism()/3 + (defender.getDefense()+homeTeam.getDefense())/2;
					if ( shooter.getThreePointShooting() > 50 ) {
						// this player has three ability greater than 50, so he can make three
						int cutOff = shooter.getTwoPointShooting();
						int num = 0;
						if ( shooter.getTwoPointShooting() <= shooter.getThreePointShooting() ) {
							// this player is more plausible to make three 
							num = rd.nextInt(cutOff+(int) (shooter.getThreePointShooting()*0.5));
						}
						else {
							num = rd.nextInt(cutOff + (int) (shooter.getThreePointShooting()*0.4 ) ); 
						}
						if ( num >= cutOff ) {
							choice = 3;
						}
					}

					if ( choice == 2 ) {
						int totalOffIndex = shooter.getTwoPointShooting() + shooter.getAthleticism()/3;
						int h = rd.nextInt( totalOffIndex + totalDenfenseIndex+AWAY_TWO_BUFFER );
						if ( h < totalOffIndex ) {
							int currScore = getAwayScore();
							setAwayScore(currScore+2);
							awayStarterData[t].setScore( awayStarterData[t].getScore() + 2 );
							gameString.add(timer.refreshQuarterString()+" "+timer.refreshTimeString()+" "+
							gameStrCollector.getTwoPoint(awayTeam,awayTeam.getStartPlayer(t))+"("+getHomeScore()+":"+getAwayScore()+")" );
						}
						else {
							gameString.add(timer.refreshQuarterString()+" "+timer.refreshTimeString()+" "+
							gameStrCollector.missTwoPoint(awayTeam,awayTeam.getStartPlayer(t))+"("+getHomeScore()+":"+getAwayScore()+")" );
						}
					}
					else if ( choice == 3 ) {
						int totalOffIndex = shooter.getThreePointShooting() + shooter.getAthleticism()/3;
						int h = rd.nextInt( totalOffIndex + totalDenfenseIndex+AWAY_THREE_BUFFER );
						if ( h <  totalOffIndex ) {
							int currScore = getAwayScore();
							setAwayScore(currScore+3);
							awayStarterData[t].setScore( awayStarterData[t].getScore() + 3 );
							gameString.add( timer.refreshQuarterString()+" "+timer.refreshTimeString()+" "+
							gameStrCollector.getThreePoint(awayTeam,awayTeam.getStartPlayer(t))+"("+getHomeScore()+":"+getAwayScore()+")" );
						}
						else {
							gameString.add(timer.refreshQuarterString()+" "+timer.refreshTimeString()+" "+
							gameStrCollector.missThreePoint(awayTeam,awayTeam.getStartPlayer(t))+"("+getHomeScore()+":"+getAwayScore()+")" );
						}
					}
					flipisHomeBall();

				}
			} // ends the game starts
			setisGameStringUpdated(false);
			// the game is over if the 4 quarters run out are two team's scores are different
			if ( timer.getisQuarterTimeOver()  && timer.getQuarter() >= 4 && getHomeScore() != getAwayScore() ) {
				setisGameOver(true);
			}

			else if ( getisGameOver() || !getisGameStarted() ) {
				pauseTime = DEFAULT_PAUSE_TIME;
			}
			pause( getPauseTime() );
		}
	}

	public synchronized void setHomeScore(int score) {
		homeScore = score;
	}

	public synchronized int getHomeScore() {
		return homeScore;
	}
	public synchronized void setAwayScore(int score) {
		awayScore = score;
	}

	public synchronized int getAwayScore() {
		return awayScore;
	}

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
  }

  public synchronized boolean getisHomeBall() {
  	return isHomeBall;
  }

  public synchronized void setisHomeBall(boolean b) {
  	isHomeBall = b;
  }
  // flip the boolean value of isHomeBall
  public synchronized void flipisHomeBall() {
  	boolean flag = !getisHomeBall();
  	setisHomeBall(flag);
  }
  public synchronized boolean getisGameStringUpdated() {
  	return isGameStringUpdated;
  }

  public synchronized void setisGameStringUpdated(boolean b) {
  	isGameStringUpdated = b;
  }

  public synchronized NBAGameTimer getTimer() {
  	return timer;
  }

  public synchronized String getTimerString() {
  	return timer.refreshTimeString();
  }

  public synchronized String getQuarterString() {
  	return timer.refreshQuarterString();
  }

  public synchronized PlayerData getHomeStarterData(int i) {
  	if ( i>=0 && i<5) {
  		return homeStarterData[i];
  	}
  	return null;
  }
  public synchronized PlayerData getAwayStarterData(int i) {
  	if ( i>=0 && i<5) {
  		return awayStarterData[i];
  	}
  	return null;
  }

  public synchronized double getPauseTime() {
  	return pauseTime;
  }

  public synchronized void speedUp() {
  	if ( getPauseTime() > 200 ) {
  		pauseTime -= 150;
  	}
  }

  public synchronized void slowDown() {
  	if ( getPauseTime() < 3000 ) {
  		pauseTime += 300;
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
  	// adjust the speed of the game. speed up.
    if ( !getisGameOver() && getisGameStarted() && !getisGamePaused() ) {
  		if ( e.getKeyCode() == KeyEvent.VK_UP ) {
  			speedUp();
  		}
  	}
  	// adjust the speed of the game. slow down
  	if ( !getisGameOver() && getisGameStarted() && !getisGamePaused() ) {
  		if ( e.getKeyCode() == KeyEvent.VK_DOWN ) {
  			slowDown();
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
}