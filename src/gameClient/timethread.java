package gameClient;

import java.util.LinkedList;

import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;
import gui.Graph_GUI;

/**
 * This class is a thread that helps us repaint while the game is running,
 * it lets us see the game and the changes of the robots and fruits places.
 *
 *
 *
 */

public class timethread extends Thread  {

	MyGameGUI gui;
	game_service game;
	LinkedList<Robot>Robots;
	DGraph gg;
	public boolean exit=true;
	



	public timethread(MyGameGUI gui,game_service game,LinkedList<Robot>Robots,DGraph gg) {
		// TODO Auto-generated constructor stub
		this.gui=gui;
		this.game=game;
		this.Robots=Robots;
		this.gg=gg;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * repaint 
	 * 
	 */

	public void run() {

		try {
			
			while(this.isAlive() && this.game.isRunning() &&this.exit==true && this.game.timeToEnd()>0.1) {
               
				timethread.sleep(100);   //game0 -time= 137



				if(this.game!=null && game.timeToEnd()>=1) {

					//if(this.game.isRunning()) {
					this.game.move();
					this.gui.repaint();
					Fruits f=new Fruits(this.gg);
					f.initf(this.game.getFruits().toString(), gg);
					
					//}
				}



				Robot y=new Robot();
				this.Robots=y.initr(game.getRobots().toString());
			}
		} 

		catch (Exception e) {
			// TODO Auto-generated catch block
			

		}	

		//System.out.println("i draw");

		return;



	}

}
