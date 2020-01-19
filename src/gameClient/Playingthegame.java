package gameClient;

import java.util.LinkedList;
import java.util.Scanner;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;

/**
 * In this class the player choose the game he wants to play- from [0-23]
 * chose if it is an automatic game or a manual game. 
 * if it is an automatic game- the player could see how the game actually works   
 * and if it is a manual game the player will have the options to choose thing by himself- where are the robots and where  
 * will each robot will go.
 * 
 */

public class Playingthegame {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//today
		tryjframewindow trynewgui=new tryjframewindow();
		int numofgame= trynewgui.getNumofgame();
		
		System.out.println("1 for automatic, 0 to yourself");
	  //  Scanner choosegame=new Scanner(System.in);
		boolean ww=false;
		int choose=1;
		while(ww==false) {
		 choose = trynewgui.getHowtoplay();
		 System.out.println();
		if(choose!=2)
			ww=true;
		}
		
		//today 
//		System.out.println("choose tour game");
//		Scanner choosenumgame=new Scanner(System.in);
//		int numofgame= choosenumgame.nextInt();
		game_service game=Game_Server.getServer(numofgame);
		System.out.println(game.toString());
		
		
		trynewgui.setVisible(false);
		
		//System.out.println(trynewgui.getNumofgame()+","+trynewgui.getHowtoplay());
		MyGameGUI mygame=new MyGameGUI(game);
		mygame.setGg(new DGraph());
		String g=game.getGraph();
		mygame.gg.init (g);

		String gameinfo=game.toString();
		

		if(choose ==1) {
			timethread a=new timethread(mygame,game,mygame.getRobots());
			a.start();
			AutomaticGame temp=new AutomaticGame();
			temp.automatic(mygame.getFruits(), mygame.getRobots(), mygame.getGame(), mygame.getGg());
			//game.stopGame();
		
			//a.stop();

		}
		else
		if(choose ==0) {
			timethread a=new timethread(mygame,game,mygame.getRobots());
			a.start();
			mygame.setGamemanual(true);
			mygame.manual();
			//a.stop();

		}
	    


	}

}
