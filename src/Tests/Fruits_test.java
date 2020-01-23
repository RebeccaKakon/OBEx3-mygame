package Tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;
import dataStructure.edgedata;
import dataStructure.nodedata;
import utils.Point3D;

class Fruits_test {

	@Test
	public void getPos() {
		DGraph a= new DGraph();
		Point3D p1 = new Point3D(1, 2, 0);
		Point3D p2 = new Point3D(2, 4, 0);
		Point3D p3 = new Point3D(5, 6, 0);
		Fruits f1 = new Fruits(a);
		Fruits f2 = new Fruits(a);
		Fruits f3 = new Fruits(a);

		f1.setPos(p1);
		f2.setPos(p2);
		f3.setPos(p3);
		if(!p1.equals(f1.getPos()))
			fail();
		if(!p2.equals(f2.getPos()))
			fail();
		if(!p3.equals(f3.getPos()))
			fail();
		
		
	}

	@Test
	public void setPos() {
		DGraph a= new DGraph();
		Fruits f1 = new Fruits(a);
		Fruits f2 = new Fruits(a);
		Fruits f3 = new Fruits(a);
		Point3D p1 = new Point3D(1, 2, 0);
		Point3D p2 = new Point3D(2, 4, 0);
		Point3D p3 = new Point3D(5, 6, 0);
		f1.setPos(p1);
		f2.setPos(p2);
		f3.setPos(p3);
		if(!p1.equals(f1.getPos()))
			fail();
		if(!p2.equals(f2.getPos()))
			fail();
		if(!p3.equals(f3.getPos()))
			fail();
		
	}



}
