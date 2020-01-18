package Tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.nodedata;
import utils.Point3D;

class DGraph_test {

	@Test
	public void testAddNode() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(0, 1, 2);
		Point3D p3 = new Point3D(1, 2, 0);
		nodedata n1 = new nodedata(1,p1,0,0);
		nodedata n2 = new nodedata(2,p2,0,0);
		nodedata n3 = new nodedata(3,p3,0,0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		if (g.nodeSize()!=3) { 
			fail(); }
	}

	@Test
	public   void testConnect() {
		Point3D p1 = new Point3D(1, 5, 0);
		Point3D p2 = new Point3D(4, 4, 0);
		Point3D p3 = new Point3D(2, 2, 0);
		nodedata n1 = new nodedata(1,p1,0,0);
		nodedata n2 = new nodedata(2,p2,0,0);
		nodedata n3 = new nodedata(3,p3,0,0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);

		if (g.edgeSize()!=2) {
			fail();

		}
		if (g.getEdge(n2.getKey(), n3.getKey()).getWeight()!=3) {
			fail(); 
		}
	}

	@Test
	public void testRemoveNode() {
		Point3D p1 = new Point3D(1, 5, 0);
		Point3D p2 = new Point3D(4, 4, 0);
		Point3D p3 = new Point3D(2, 2, 0);
		nodedata n1 = new nodedata(1,p1,0,0);
		nodedata n2 = new nodedata(2,p2,0,0);
		nodedata n3 = new nodedata(3,p3,0,0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);

		g.removeNode(n2.getKey());
		if (g.edgeSize()!=0) { fail(); }
		try {
			if (g.getEdge(n2.getKey(), n3.getKey())!=null)
				fail();
		}
		catch (Exception e) {
			;
			}
		try {
			if(g.getEdge(n1.getKey(), n2.getKey())!=null)
				fail();
		}catch (Exception e)
		{
			;
		}
	}

	@Test
	public  void testRemoveEdge() {
		Point3D p1 = new Point3D(1, 5, 0);
		Point3D p2 = new Point3D(4, 4, 0);
		Point3D p3 = new Point3D(2, 2, 0);
		nodedata n1 = new nodedata(1,p1,0,0);
		nodedata n2 = new nodedata(2,p2,0,0);
		nodedata n3 = new nodedata(3,p3,0,0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		//  System.out.println(g.edgeSize());
		g.removeEdge(n2.getKey(), n3.getKey());

		if (g.edgeSize()!=1) { fail(); }
		if (g.getEdge(n2.getKey(), n3.getKey()) != null) { fail();}
		if (g.getEdge(n1.getKey(), n2.getKey()) == null) { fail();}
	}
	
	@Test
	public void testMC() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(0, 1, 2);
		Point3D p3 = new Point3D(1, 2, 0);
		Point3D p4 = new Point3D(5, 6, 0);
		nodedata n1 = new nodedata(1,p1,0,0);
		nodedata n2 = new nodedata(2,p2,0,0);
		nodedata n3 = new nodedata(3,p3,0,0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(1, 2, 10);
		int mc1=g.getMC();
		g.connect(1, 3, 10);
		int mc2=g.getMC();
		if(mc1==mc2)
			fail();
		g.removeEdge(1, 3);
		int mc3=g.getMC();
		if(mc2==mc3)
			fail();
		g.removeNode(1);
		int mc4=g.getMC();
		if(mc3==mc4)
			fail();
		g.addNode(new nodedata(4,p4,5,0));
		int mc5=g.getMC();
		if(mc4==mc5)
			fail();
		
	}


}
