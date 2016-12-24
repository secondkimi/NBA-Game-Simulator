package NBADemo;
/*
 * The main class the run the NBAGame thread
 */


/*
 * @(#)Main.java
 *
 * This work is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * This work is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * Copyright (c) 2003 Per Cederberg. All rights reserved.
 */
import java.io.*;
import sun.audio.*;
import java.util.*;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The main class of the Tetris game. This class contains the
 * necessary methods to run the game either as a stand-alone
 * application or as an applet inside a web page.
 *
 * @version  1.2
 * @author   Per Cederberg, per@percederberg.net
 */
public class Main extends Applet 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5138623139734386524L;

	/**
     * The applet parameter information structure.
     */
    private static final String PARAMETER[][] = 
    {
        { "tetris.color.background", "color",  
            "The overall background color." },
        { "tetris.color.label", "color", 
            "The text color of the labels." },
        { "tetris.color.button", "color", 
            "The start and pause button bolor." }, 
        { "tetris.color.board.background", "color", 
            "The background game board color." },
        { "tetris.color.board.message", "color", 
            "The game board message color." },
        { "tetris.color.figure.square", "color", 
            "The color of the square figure." },
        { "tetris.color.figure.line", "color", 
            "The color of the line figure." },
        { "tetris.color.figure.s", "color", 
            "The color of the 's' curved figure." },
        { "tetris.color.figure.z", "color", 
            "The color of the 'z' curved figure." },
        { "tetris.color.figure.right", "color", 
            "The color of the right angle figure." },
        { "tetris.color.figure.left", "color", 
            "The color of the left angle figure." },
        { "tetris.color.figure.triangle", "color", 
            "The color of the triangle figure." }
    };

    /**
     * The Tetris game being played (in applet mode).
     */
    // private Game game = null;

    /**
     * The stand-alone main routine.
     * 
     * @param args      the command-line arguments
     */
    public static void main(String[] args) throws Exception 
    {
        // open the sound file as a Java input stream
        String gongFile = "C:\\Users\\secondkimi\\yes.au";
        InputStream in = new FileInputStream(gongFile);
 
        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);
 
        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);        
        Frame  frame = new Frame("Tetris");
        /* Game   game = new Game();

        // Set up frame
        frame.add(game.getComponent());
        frame.pack();*/

        // Add frame window listener
        frame.addWindowListener( new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        } 
        );

        frame.addWindowListener( new WindowAdapter() 
        {
            public void windowOpened (WindowEvent e) {
  
            }

        }    
        );

        // Show frame (and start game)
        frame.setVisible(true);
    }


    /**
     * Returns information about the parameters that are understood by 
     * this applet.
     * 
     * @return an array describing the parameters to this applet
     */
    @Override
    public String[][] getParameterInfo() 
    {
        return PARAMETER;
    }

    /**
     * Initializes the game in applet mode.
     */
    @Override
    public void init() 
    {

    }

    /**
     * Stops the game in applet mode.
     */
    @Override
    public void stop() 
    {

    }
    
    
    /**
     * A dummy COM object wrapper. This class has been created only to
     * avoid the erroneous HTTP lookup for it when the Tetris game is
     * run as an applet in some browsers.
     * 
     * @version  1.0
     * @author   Per Cederberg, per@percederberg.net
     */
    public static class COMClassObject extends Object 
    {
    
    }
}
