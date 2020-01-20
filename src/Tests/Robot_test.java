package Tests;

import static org.junit.Assert.fail;


import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Server.Game_Server;
import Server.game_service;

import dataStructure.Robot;
import utils.Point3D;

class Robot_test {

	@Test
	public void initr() {
		 game_service game = Game_Server.getServer(5);
		 game.addRobot(1);
		 game.startGame();
		 System.out.println(game.getRobots());
		 Point3D pos=new Point3D(35.20319591121872,32.10318254621849,0.0);
		 Robot a1=new  Robot(1, pos,1,-1,1, 0.0);
		 Robot r=new Robot();
		 LinkedList<Robot> robots= r.initr(game.getRobots().toString());
		 System.out.println(a1.getPos());
		 System.out.println(robots.get(0).getPos());
		 if(!a1.getPos().equals(robots.get(0).getPos()))
			 fail();
		
	}
	
}
