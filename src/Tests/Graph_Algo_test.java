package Tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.nodedata;
import gui.GraphGUIstddraw;
import gui.Graph_GUI;
import utils.Point3D;

class Graph_Algo_test {

	//	@BeforeAll
	//	public void BeforeAll() {
	//		Point3D p1=new Point3D(10,15,0);
	//		Point3D p2=new Point3D(50,60,0);
	//		Point3D p3=new Point3D(90,40,0);
	//		Point3D p4=new Point3D(20,50,0);
	//		Point3D p5=new Point3D(80,10,0);
	//		Point3D p6=new Point3D(5,90,0);
	//		
	//
	//		nodedata a=new nodedata(1,p1,0,0);
	//		nodedata b=new nodedata(2,p2,0,0);
	//		nodedata c=new nodedata(3,p3,0,0);
	//		nodedata d=new nodedata(4,p4,0,0);
	//		nodedata e=new nodedata(5,p5,0,0);
	//		nodedata f=new nodedata(6,p6,0,0);
	//		
	//		DGraph x=new DGraph();
	//		
	//		x.addNode(a);
	//		x.addNode(b);
	//		x.addNode(c);
	//		x.addNode(d);
	//		x.addNode(e);
	//		x.addNode(f);
	//		
	//		x.connect(a.getKey(), b.getKey(), 80);
	//		x.connect(b.getKey(), d.getKey(), 10);
	//		x.connect(c.getKey(), a.getKey(), 10);
	//		x.connect(a.getKey(), e.getKey(), 10);
	//		
	//		x.connect(e.getKey(), b.getKey(), 10);
	//		x.connect(f.getKey(), c.getKey(), 10);
	//		x.connect(d.getKey(), f.getKey(), 40);
	//		x.connect(e.getKey(), c.getKey(), 50);
	//		x.connect(c.getKey(), b.getKey(), 5);
	//		Graph_Algo test=new Graph_Algo(x);
	//	}
	//	

	@Test
	void testconnect() {
		Point3D p1=new Point3D(10,15,0);
		Point3D p2=new Point3D(50,60,0);
		Point3D p3=new Point3D(90,40,0);
		Point3D p4=new Point3D(20,50,0);
		Point3D p5=new Point3D(80,10,0);
		Point3D p6=new Point3D(5,90,0);


		nodedata a=new nodedata(1,p1,0,0);
		nodedata b=new nodedata(2,p2,0,0);
		nodedata c=new nodedata(3,p3,0,0);
		nodedata d=new nodedata(4,p4,0,0);
		nodedata e=new nodedata(5,p5,0,0);
		nodedata f=new nodedata(6,p6,0,0);

		DGraph x=new DGraph();

		x.addNode(a);
		x.addNode(b);
		x.addNode(c);
		x.addNode(d);
		x.addNode(e);
		x.addNode(f);

		x.connect(a.getKey(), b.getKey(), 80);
		x.connect(b.getKey(), d.getKey(), 10);
		x.connect(c.getKey(), a.getKey(), 10);
		x.connect(a.getKey(), e.getKey(), 10);

		x.connect(e.getKey(), b.getKey(), 10);
		x.connect(f.getKey(), c.getKey(), 10);
		x.connect(d.getKey(), f.getKey(), 40);
		x.connect(e.getKey(), c.getKey(), 50);
		x.connect(c.getKey(), b.getKey(), 5);
		Graph_Algo test=new Graph_Algo(x);
		boolean before=test.isConnected();
		x.removeEdge(2,4);
		boolean after=test.isConnected();
		if(before==after)
			fail("not supose to be the same");
	}
	@Test
	void testinitsave() {
		Point3D p1=new Point3D(10,15,0);
		Point3D p2=new Point3D(50,60,0);
		Point3D p3=new Point3D(90,40,0);
		Point3D p4=new Point3D(20,50,0);
		Point3D p5=new Point3D(80,10,0);
		Point3D p6=new Point3D(5,90,0);


		nodedata a=new nodedata(1,p1,0,0);
		nodedata b=new nodedata(2,p2,0,0);
		nodedata c=new nodedata(3,p3,0,0);
		nodedata d=new nodedata(4,p4,0,0);
		nodedata e=new nodedata(5,p5,0,0);
		nodedata f=new nodedata(6,p6,0,0);

		DGraph x=new DGraph();

		x.addNode(a);
		x.addNode(b);
		x.addNode(c);
		x.addNode(d);
		x.addNode(e);
		x.addNode(f);

		x.connect(a.getKey(), b.getKey(), 80);
		x.connect(b.getKey(), d.getKey(), 10);
		x.connect(c.getKey(), a.getKey(), 10);
		x.connect(a.getKey(), e.getKey(), 10);

		x.connect(e.getKey(), b.getKey(), 10);
		x.connect(f.getKey(), c.getKey(), 10);
		x.connect(d.getKey(), f.getKey(), 40);
		x.connect(e.getKey(), c.getKey(), 50);
		x.connect(c.getKey(), b.getKey(), 5);
		Graph_Algo test=new Graph_Algo(x);
		try {
			test.save("testout");

		}
		catch(Exception e1) {
			fail();
		}
		try {
			Graph_Algo testin=new Graph_Algo();
			testin.init("testout");
			Graph_GUI draw=new Graph_GUI(testin.getG());
			draw.setVisible(true);

		}
		catch(Exception e1) {
			fail();
		}

	}
	@Test
	public void testshortestPathDist() {
		Graph_Algo g = new Graph_Algo();
		DGraph DG = new DGraph();
		nodedata node1 = new nodedata(1,new Point3D (100,200,3),0.0,0);
		nodedata node2 = new nodedata(2,new Point3D (150,200,3),0,0);
		nodedata node3 = new nodedata(3, new Point3D (300,450,3),0,0);
		nodedata node4 = new nodedata(4,new Point3D (450,500,3),0,0);
		nodedata node5 = new nodedata(5,new Point3D (320,600,3),0,0);
		nodedata node6 = new nodedata(6,new Point3D (226,260,3),0,0);
		DG.addNode(node1);
		DG.addNode(node2);
		DG.addNode(node3);
		DG.addNode(node4);
		DG.addNode(node5);
		DG.addNode(node6);

		DG.connect(1,2,5);
		DG.connect(2,3,5);
		DG.connect(3,4,5);
		DG.connect(4,5,5);
		DG.connect(5,6,5);
		DG.connect(6,1,5);
		g.init(DG);
		boolean flag = 25 == g.shortestPathDist(1,6);
		assertEquals(true,flag);



	}
	@Test
	void shortestPath() {
		Graph_Algo g = new Graph_Algo();
		DGraph DG = new DGraph();
		nodedata node1 = new nodedata(1,new Point3D (100,200,3),0.0,0);
		nodedata node2 = new nodedata(2,new Point3D (150,200,3),0,0);
		nodedata node3 = new nodedata(3, new Point3D (300,450,3),0,0);
		nodedata node4 = new nodedata(4,new Point3D (450,500,3),0,0);
		nodedata node5 = new nodedata(5,new Point3D (320,600,3),0,0);
		nodedata node6 = new nodedata(6,new Point3D (226,260,3),0,0);
		DG.addNode(node1);
		DG.addNode(node2);
		DG.addNode(node3);
		DG.addNode(node4);
		DG.addNode(node5);
		DG.addNode(node6);

		DG.connect(1,2,5);
		DG.connect(2,3,5);
		DG.connect(3,4,5);
		DG.connect(4,5,5);
		DG.connect(5,6,5);
		DG.connect(6,1,5);

		g.init(DG);
		//System.out.println("g is connect"+g.isConnected());
		if(null== g.shortestPath(5, 3))
			fail();
		if(null== g.shortestPath(1, 2))
			fail();
		if(null== g.shortestPath(3, 6))
			fail();
	}
	@Test
	void testcopy() {
		Point3D p1=new Point3D(10,15,0);
		Point3D p2=new Point3D(50,60,0);
		Point3D p3=new Point3D(90,40,0);
		Point3D p4=new Point3D(20,50,0);
		Point3D p5=new Point3D(80,10,0);
		Point3D p6=new Point3D(5,90,0);


		nodedata a=new nodedata(1,p1,0,0);
		nodedata b=new nodedata(2,p2,0,0);
		nodedata c=new nodedata(3,p3,0,0);
		nodedata d=new nodedata(4,p4,0,0);
		nodedata e=new nodedata(5,p5,0,0);
		nodedata f=new nodedata(6,p6,0,0);

		DGraph x=new DGraph();

		x.addNode(a);
		x.addNode(b);
		x.addNode(c);
		x.addNode(d);
		x.addNode(e);
		x.addNode(f);

		x.connect(a.getKey(), b.getKey(), 80);
		x.connect(b.getKey(), d.getKey(), 10);
		x.connect(c.getKey(), a.getKey(), 10);
		x.connect(a.getKey(), e.getKey(), 10);

		x.connect(e.getKey(), b.getKey(), 10);
		x.connect(f.getKey(), c.getKey(), 10);
		x.connect(d.getKey(), f.getKey(), 40);
		x.connect(e.getKey(), c.getKey(), 50);
		x.connect(c.getKey(), b.getKey(), 5);


		Graph_Algo test=new Graph_Algo(x);

		graph currGraph = (graph) test.copy();

		if(currGraph.nodeSize() !=  test.getG().nodeSize()) {
			//			System.out.println(currGraph.nodeSize());
			//			System.out.println(test.getG().getV().size());
			fail("not "+currGraph.nodeSize()+ "!!!"+test.getG().getV().size());
			//     }
			if( (currGraph.edgeSize() !=  test.getG().getCountedgeg()))
				fail();
			if(currGraph != test)
				fail();
		}

	}
	@Test
	void testTSP() {
		Point3D p1=new Point3D(100,150,0);
		Point3D p2=new Point3D(250,360,0);
		Point3D p3=new Point3D(900,400,0);
		Point3D p4=new Point3D(250,560,0);
		Point3D p5=new Point3D(800,100,0);
		Point3D p6=new Point3D(50,90,0);


		nodedata a=new nodedata(1,p1,0,0);
		nodedata b=new nodedata(2,p2,0,0);
		nodedata c=new nodedata(3,p3,0,0);
		nodedata d=new nodedata(4,p4,0,0);
		nodedata e=new nodedata(5,p5,0,0);
		nodedata f=new nodedata(6,p6,0,0);

		DGraph x=new DGraph();
		//
		x.addNode(a);
		x.addNode(b);
		x.addNode(c);
		x.addNode(d);
		x.addNode(e);
		x.addNode(f);

		x.connect(a.getKey(), b.getKey(), 80);
		x.connect(b.getKey(), d.getKey(), 10);
		x.connect(c.getKey(), a.getKey(), 10);
		x.connect(a.getKey(), e.getKey(), 10);
		x.connect(d.getKey(), e.getKey(), 90);
		x.connect(b.getKey(), d.getKey(), 30);
		x.connect(b.getKey(), a.getKey(), 80);

		x.connect(e.getKey(), b.getKey(), 10);


		Graph_Algo test=new Graph_Algo(x);
		LinkedList<Integer> targets = new LinkedList<Integer>();
		targets.add(1);
		targets.add(5);
		targets.add(2);

		if(null==test.TSP(targets))
			fail();

		targets = new LinkedList<>();
		targets.add(2);
		targets.add(6);
		if(null!=test.TSP(targets))
			fail();
	}

}




