package gameClient;

import java.util.Iterator;
import java.util.LinkedList;

import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;

/**
 * This class represents an automatic game:
 * In this class we have one function: "automatic"
 * This function gets the list of robot,list of fruits,game and the graph.
 * checks how much robots does the game has,used the function "puts robot" to 
 * place all the robot of the game and starting the game.
 * after the game is starting the function uses the function "moveRobots" until the end 
 * of the game.
 * 
 * @param  LinkedList<Fruits> fruits
 * @param  LinkedList<Robot> robots 
 * @param  game_service game  -the game that is going to be played.
 * @param  DGraph gg - the graph of the game
 *
 */

public class AutomaticGame {
	public  void automatic( LinkedList<Fruits> fruits, LinkedList<Robot> robots,game_service game,DGraph gg) {
		// TODO Auto-generated method stub
		algo cal=new algo();
		Fruits ff=new Fruits();

		//	System.out.println(game.getFruits().toString());
		fruits=(LinkedList<Fruits>) ff.initf(game.getFruits().toString(),gg);

		Iterator w=fruits.iterator();
		while(w.hasNext()) {
			Fruits c=(Fruits) w.next();
			//.out.println("type of fruit="+c.getType());
		}


		int numrobot=cal.numrobot(game.toString());
		int count=0;
		while(count!=numrobot) {
			cal.putRobot(game , count,robots);
			count++;
		}
		Robot y=new Robot();
		robots= y.initr(game.getRobots().toString());
		//this.setVisible(true);
		game.startGame();

		while(game.isRunning()) {

			cal.moveRobots(game, gg);
			
		}



		//System.out.println(game.toString());
		
		System.exit(0);


	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
