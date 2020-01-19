package gameClient;

import java.util.LinkedList;

import Server.game_service;
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
	public boolean exit=true;



	public timethread(MyGameGUI gui,game_service game,LinkedList<Robot>Robots) {
		// TODO Auto-generated constructor stub
		this.gui=gui;
		this.game=game;
		this.Robots=Robots;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * repaint 
	 * 
	 */

	public void run() {


		while(this.isAlive() && this.game.isRunning() &&this.exit==true && this.game.timeToEnd()>0.1) {
			try {
				timethread.sleep(100);

				if(this.game!=null)

					if(this.game.isRunning()) {
						this.game.move();
						this.gui.repaint();
					}



				Robot y=new Robot();
				this.Robots=y.initr(game.getRobots().toString());
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//System.out.println("i draw");
		}
		return;



	}

}
