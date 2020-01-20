package Tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Server.Game_Server;
import Server.game_service;
import gameClient.algo;

class algo_test {

	@Test
	public void numofrobot() {
		 game_service game = Game_Server.getServer(5);
		 game.addRobot(0);
		 game.startGame();
		 algo cal=new algo();
		// System.out.println(game.toString());
		 int answer=cal.numrobot(game.toString());
		// System.out.println(answer);
		 if(answer!=1)
			 fail();
	}
	@Test
	public void numofrobot2() {
		 game_service game = Game_Server.getServer(23);
		 game.addRobot(0);
		 game.startGame();
		 algo cal=new algo();
		// System.out.println(game.toString());
		 int answer=cal.numrobot(game.toString());
		// System.out.println(answer);
		 if(answer!=3)
			 fail();
	}

}
