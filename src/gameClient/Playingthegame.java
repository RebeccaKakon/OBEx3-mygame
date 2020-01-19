package gameClient;

import java.util.LinkedList;
import java.util.Scanner;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;
import utils.KML;

/**
 * In this class the player choose the game he wants to play- from [0-23]
 * chose if it is an automatic game or a manual game. 
 * if it is an automatic game- the player could see how the game actually works   
 * and if it is a manual game the player will have the options to choose thing by himself- where are the robots and where  
 * will each robot will go.
 * 
 */

public class Playingthegame {

	public static KML kmlstring;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//today
		System.out.println("Enter a game you want to play");
		System.out.println("Enter 1 for automatic, 0 to yourself");
//		tryjframewindow trynewgui=new tryjframewindow();
//		trynewgui.setVisible(true);
//		
//
//		int numofgame=100;
//		int choose=2;
//		
//		while(trynewgui.getNumofgame()==100 || trynewgui.getHowtoplay()==2) {
//			 numofgame= trynewgui.getNumofgame();
//			 choose = trynewgui.getHowtoplay();
//			
//		}
//		
//		System.out.println("num of game="+numofgame);
//		System.out.println("howto play="+choose);
		
		Scanner in=new Scanner(System.in);
		int b=in.nextInt();
		int numofgame=b; 
		
		Scanner in2=new Scanner(System.in);
		int b2=in.nextInt();
		int choose=b2;
		
		
		



		kmlstring=new KML (numofgame);
		game_service game=Game_Server.getServer(numofgame);
		System.out.println(game.toString());


	//	trynewgui.setVisible(false);

		//System.out.println(trynewgui.getNumofgame()+","+trynewgui.getHowtoplay());
		MyGameGUI mygame=new MyGameGUI(game);
		mygame.setGg(new DGraph());
		String g=game.getGraph();
		mygame.gg.init (g);

		String gameinfo=game.toString();


		if(choose ==1) {
			timethread a=new timethread(mygame,game,mygame.getRobots());
			//a.start();
			AutomaticGame temp=new AutomaticGame();
			temp.automatic(mygame.getFruits(), mygame.getRobots(), mygame.getGame(), mygame.getGg(), a);
			a.exit=false;
			kmlstring.kmlfinishgame();
			//game.stopGame();


		}
		else
			if(choose ==0) {
				//			timethread a=new timethread(mygame,game,mygame.getRobots());
				//			a.start();
				mygame.setGamemanual(true);
				mygame.manual(mygame.getGame());
				
				
				



			}
		
		



	}

}
