package gameClient;

import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;
import utils.KML_Logger;
import utils.StdDraw;

/**
 * In this class the player choose the game he wants to play- from [0-23]
 * chose if it is an automatic game or a manual game. 
 * if it is an automatic game- the player could see how the game actually works   
 * and if it is a manual game the player will have the options to choose thing by himself- where are the robots and where  
 * will each robot will go.
 * 
 */

public class Playingthegame {

	public static KML_Logger kmlstring;
	public static int id;
	public static int numofgame;
	public static int numtowait;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("Enter a game you want to play");
		System.out.println("Enter 1 for automatic, 0 to yourself");
		
		String s = JOptionPane.showInputDialog(null, "Choose a fild game");
		numofgame = Integer.parseInt(s);


		String [] p = {"automatic","manual"};
		int choose;
		Object game_select = JOptionPane.showInputDialog(null, "Choose a mood", "Note", JOptionPane.INFORMATION_MESSAGE,null, p, p[0]);
		if(game_select==p[0]) {
			choose=1;
		}
		else
			choose =0;


		kmlstring=new KML_Logger (numofgame);
		id=206713141;       
		Game_Server.login(id);    
		game_service game=Game_Server.getServer(numofgame);
		System.out.println(game.toString());


		MyGameGUI mygame=new MyGameGUI(game);
		mygame.setGg(new DGraph());
		String g=game.getGraph();
		mygame.gg.init (g);

		String gameinfo=game.toString();


		if(choose ==1) {
			timethread a=new timethread(mygame,game,mygame.getRobots(),mygame.gg);
			
			AutomaticGame temp=new AutomaticGame();
			temp.automatic(mygame.getFruits(), mygame.getRobots(), mygame.getGame(), mygame.getGg(), a, numofgame);
			a.exit=false;
			kmlstring.kmlfinishgame();
			


		}
		else
			if(choose ==0) {
				
				mygame.setGamemanual(true);
				mygame.manual(mygame.getGame());






			}





	}

}
