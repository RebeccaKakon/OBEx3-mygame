package gameClient;

import java.util.LinkedList;
import java.util.Scanner;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;

public class Playingthegame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("choose tour game");
		Scanner choosenumgame=new Scanner(System.in);
		int numofgame= choosenumgame.nextInt();
		game_service game=Game_Server.getServer(numofgame);
		System.out.println(game.toString());
		MyGameGUI mygame=new MyGameGUI(game);
		mygame.setGg(new DGraph());
		String g=game.getGraph();
		mygame.gg.init (g);

		String gameinfo=game.toString();
		System.out.println("1 for automatic, 0 to yourself");
		Scanner choosegame=new Scanner(System.in);
		int choose = choosegame.nextInt();

		if(choose ==1) {
			timethread a=new timethread(mygame,game,mygame.getRobots());
			a.start();
			AutomaticGame temp=new AutomaticGame();
			temp.automatic(mygame.getFruits(), mygame.getRobots(), mygame.getGame(), mygame.getGg());

		}
		else
		if(choose ==0) {
			timethread a=new timethread(mygame,game,mygame.getRobots());
			a.start();
			mygame.setGamemanual(true);
			mygame.manual();

		}


	}

}
