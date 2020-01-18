package gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.edgedata;
import dataStructure.node_data;
import dataStructure.nodedata;
import utils.Point3D;
import utils.StdDraw;

public class guilearn implements ActionListener {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
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
		x.connect(b.getKey(), e.getKey(), 10);
		x.connect(e.getKey(), b.getKey(), 10);
		x.connect(f.getKey(), c.getKey(), 10);

		//		HashMap<Integer, node_data> hashnodes=new HashMap<Integer, node_data> ();
		//		hashnodes.put(a.getKey(), a);
		//		hashnodes.put(b.getKey(), b);
		//		hashnodes.put(c.getKey(), c);
		//		hashnodes.put(d.getKey(), d);
		//		hashnodes.put(e.getKey(), e);
		//		hashnodes.put(f.getKey(), f);
		//		
		//		edge_data aa=new edgedata(a,b,10,0);
		//		edge_data bb=new edgedata(b,c,20,0);
		//		edge_data cc=new edgedata(c,a,30,0);
		//		edge_data dd=new edgedata(a,d,40,0);
		//		edge_data eb=new edgedata(e,b,8,0);
		//		edge_data fe=new edgedata(f,e,5,0);
		//		
		//		HashMap<Integer, edge_data>hash_a=new HashMap<Integer, edge_data>();
		//		HashMap<Integer, edge_data>hash_b=new HashMap<Integer, edge_data>();
		//		HashMap<Integer, edge_data>hash_c=new HashMap<Integer, edge_data>();
		//		HashMap<Integer, edge_data>hash_d=new HashMap<Integer, edge_data>();
		//		HashMap<Integer, edge_data>hash_e=new HashMap<Integer, edge_data>();
		//		HashMap<Integer, edge_data>hash_f=new HashMap<Integer, edge_data>();
		//		
		//		hash_a.put(aa.getDest(), aa);
		//		hash_b.put(bb.getDest(), bb);
		//		hash_c.put(cc.getDest(), cc);
		//		hash_a.put(dd.getDest(), dd);
		//		hash_e.put(eb.getDest(), eb);
		//		hash_e.put(fe.getDest(), fe);
		//		
		//		
		//		DGraph x=new DGraph();
		//		HashMap<Integer, HashMap<Integer, edge_data>>hashedges=new HashMap<Integer, HashMap<Integer, edge_data>>();
		//		
		//		hashedges.put(a.getKey(), hash_a);
		//		hashedges.put(b.getKey(),hash_b);
		//		hashedges.put(c.getKey(),hash_c);
		//		hashedges.put(d.getKey(),hash_d);
		//		hashedges.put(e.getKey(),hash_e);
		//		hashedges.put(f.getKey(),hash_f);
		//		x.setHashnodes(hashnodes);
		//		x.setHashedges(hashedges);
		 
		Graph_Algo test=new Graph_Algo(x);
		System.out.println(x.getCountedgeg());
		GraphGUIstddraw g=new GraphGUIstddraw();
		//g.drawFunctions(x);
		//System.out.println(x.removeNode(6));
		System.out.println(x.removeEdge(3, 6));
		x.connect(4, 6, 20);
		System.out.println(test.isConnected());
		System.out.println(test.shortestPathDist(1,6));
		System.out.println(test.shortestPath(1,6));

		//g.drawFunctions(x);
		//x.removeEdge(6, 3);
		x.removeEdge(4, 6);
		System.out.println(test.shortestPath(1,6));
		//x.removeNode(2);
		g.drawFunctions(x);
		System.out.println("done");
        
		
		



	}
	public void drawFunctions(DGraph x) {
		StdDraw.setCanvasSize(1000,1000);
		
		drawpoints(x);
		drawedges(x);
		
	}
	
	public  void drawpoints(DGraph x) {
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.05);
		StdDraw.setScale(-100, 100);
		Iterator<node_data> I= x.getV().iterator();
		while(I.hasNext()) {
			//System.out.println("did");
			node_data current=new nodedata((nodedata)I.next());
			Point3D p=new Point3D(current.getLocation());
			//System.out.println(p);
			StdDraw.point(p.x(), p.y());
			StdDraw.setPenColor(Color.RED);
			StdDraw.setPenRadius(0.5);
			String txt=Integer.toString(current.getKey());
			StdDraw.text(p.x(), p.y()+4, txt);
			
			StdDraw.setPenRadius(0.05);
			StdDraw.setPenColor(Color.BLACK);
		}
		
		
		
	}
	public void drawedges(DGraph x) {
		StdDraw.setPenColor(Color.pink);
		StdDraw.setPenRadius(0.01);
		Iterator<node_data> I=x.getV().iterator();
		//Iterator<HashMap<Integer,edge_data>> I= (Iterator<HashMap<Integer, edge_data>>) x.getHashedges().entrySet();
		while(I.hasNext()) {
			//HashMap<Integer,edge_data> current=new HashMap<Integer,edge_data>((HashMap<Integer,edge_data>)I.next());
			node_data B=new nodedata((nodedata)I.next());
			HashMap<Integer,edge_data>edg2=new HashMap<Integer,edge_data>(x.getHashedges().get(B.getKey()));
			Collection<edge_data> edges=edg2.values();
			Iterator <edge_data> j=edges.iterator();
			while(j.hasNext()) {
				edgedata edg=new edgedata((edgedata)j.next()); 
				StdDraw.line(edg.getSource().getLocation().x(),edg.getSource().getLocation().y(),
						edg.getDes().getLocation().x(),edg.getDes().getLocation().y());
				String txt=Double.toString(edg.getWeight());
				double x_txt=(Math.abs(edg.getDes().getLocation().x())+Math.abs(edg.getSource().getLocation().x()))/2;
				double y_txt=placeontheline(edg.getSource().getLocation(),edg.getDes().getLocation(),x_txt);
				StdDraw.setPenColor(Color.BLUE);
				StdDraw.text( x_txt,  y_txt,txt);
				double distance=(edg.getDes().getLocation().x())-(edg.getSource().getLocation().x());
				double xx=edg.getDes().getLocation().x()-distance/(10);
				double yy=placeontheline(edg.getSource().getLocation(),edg.getDes().getLocation(),xx);
				//Point3D vector=new Point3D(edg.getDes().getLocation().x()-edg.getSource().getLocation().x(),edg.getDes().getLocation().y()-edg.getSource().getLocation().y(),0);  
				Point3D yellow=new Point3D(xx,yy,0);
				StdDraw.setPenColor(Color.YELLOW);
				StdDraw.setPenRadius(0.03);
				StdDraw.point(yellow.x(), yellow.y());
				StdDraw.setPenRadius(0.01);
				StdDraw.setPenColor(Color.pink);
				
			}
			
			
		}
		
		
	}
	
	public static double placeontheline(Point3D p1,Point3D p2,double x0) {    //return the f(x0)
		double m=((p2.y()-p1.y())/(p2.ix()-p1.x()));
		return m*(x0-p2.x())+p2.y();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String action=e.getActionCommand();
		if(action.equals("short pass")) {
			
		}
		
	}

}


