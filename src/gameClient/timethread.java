package gameClient;

import java.util.LinkedList;

import Server.game_service;
import dataStructure.Robot;
import gui.Graph_GUI;

public class timethread extends Thread  {

	MyGameGUI gui;
	game_service game;
	LinkedList<Robot>Robots;


	public timethread(MyGameGUI gui,game_service game,LinkedList<Robot>Robots) {
		// TODO Auto-generated constructor stub
		this.gui=gui;
		this.game=game;
		this.Robots=Robots;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void run() {
		
			while(this.isAlive()) {
				try {
					timethread.sleep(100);
					this.gui.repaint();
					this.game.move();
					Robot y=new Robot();
					this.Robots=y.initr(game.getRobots().toString());
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

				//System.out.println("i draw");
			}
		
		
	}

}
