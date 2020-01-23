package algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import javax.xml.soap.Node;

import java.util.Queue;
import java.util.Stack;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.edgedata;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.nodedata;
import gui.GraphGUIstddraw;
import gui.Graph_GUI;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{

	private DGraph g; 
	public Graph_Algo() {
		this.g=null; 
	}
	public Graph_Algo(graph g) {
		this.g=(DGraph) g;
	}

	public Graph_Algo(DGraph g) {
		this.g=new DGraph(g); 
	}
	public void setG(DGraph g) {
		this.g = g;
	}

	@Override
	public void init(graph g) {
		// TODO Auto-generated method stub
		this.g= ((DGraph) g);

	}
	/**
	 * This method will get a graph from the user or from a file and create the graph
	 */

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		try
		{
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file); 
			g= (DGraph)in.readObject();
			in.close();
			file.close();
			System.out.println("Object has been deserialized");
		}

		catch(IOException ex)
		{
			System.out.println(ex);
		}

		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException is caught");
		}

	}



   /**
    *  This method saves the graph as a file.
    */

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

		try
		{
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(this.g);

			out.close();
			file.close();

			System.out.println("Object has been serialized");
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
		}




	} 

     /**
      * This method will check if the graph is connect.
      *  A connect graph is a graph witch each node can get to all other node in the graph
      */


	@Override
	public boolean isConnected() {

		// TODO Auto-generated method stub
		if(this.getG().getV().size()==0) return true;
		LinkedList<node_data> c=new LinkedList<node_data>( this.g.getV());
		Iterator<node_data> p=c.iterator();
		while(p.hasNext()) {
			nodedata node=((nodedata) p.next());
			node.setTag(0);;
		}

		Queue <nodedata>q=new LinkedList<>(); 
		int count=0;
		//this.g.getHashnodes().get(1);  //check if there is a place like this
		q.add((nodedata) this.g.getHashnodes().get(c.getFirst().getKey()));
		while(!q.isEmpty()) {
			nodedata nodeq=new nodedata(q.peek());
			//System.out.println(nodeq.getKey());
			Collection<edge_data> nabers=this.g.getHashedges().get(nodeq.getKey()).values();
			if(nabers.size()==0) return false;
			Iterator<edge_data> I=nabers.iterator();
			while(I.hasNext()) {
				edgedata edge=(edgedata) I.next();
				if(edge.getDes().getTag()!=1) {
					q.add(edge.getDes());
				}
			}
			if(count!=0)
				q.peek().setTag(1);
			count=1;	
			q.poll();

		}
		Collection<node_data> nodesingraf=this.g.getV();
		Iterator<node_data> J=nodesingraf.iterator();
		while(J.hasNext()) {
			nodedata node=new nodedata((nodedata) J.next());
			if(node.getTag()==0)
				return false;
		}
		return true;	

	}
	/**
	 * This method returns the length of the shortest path between src to dest
	 */

	@Override
	public double shortestPathDist(int src, int dest) {

		// TODO Auto-generated method stub
		
		Collection<node_data> c= this.g.getV();
		Iterator<node_data> I=c.iterator();
		while(I.hasNext()) {
			nodedata node=((nodedata) I.next());
			node.setWeight(Double.POSITIVE_INFINITY);
		}
		this.g.getHashnodes().get(src).setWeight(0);

		Queue <nodedata>q=new LinkedList<>(); 
		q.add((nodedata) this.g.getHashnodes().get(src));
		int count=0;
		boolean falg=true;
		while(count!=100000&&falg==true) {
			//f(q.peek().getTag()==0) {
			if(this.g.getHashedges().get(q.peek().getKey()).isEmpty())
				falg=false;
			Collection<edge_data> edges=this.g.getHashedges().get(q.peek().getKey()).values();

			Iterator<edge_data> E=edges.iterator();
			while(E.hasNext()) {
				edgedata ed=(edgedata) E.next();
				double wed=this.g.getHashnodes().get(ed.getDest()).getWeight();
				//ouble wed=ed.getDes().getWeight();
				if(q.peek().getWeight()+ed.getWeight()<wed) {

					this.g.getHashnodes().get(ed.getDest()).setWeight(q.peek().getWeight()+ed.getWeight());
					//System.out.println("int to:"+ed.getDest() +"enter"+this.g.getHashnodes().get(ed.getDest()).getWeight());

					//d.getDes().setWeight(q.peek().getWeight()+ed.getWeight());
					ed.getDes().setWay(Integer.toString(q.peek().getKey()));

				}
				//f(ed.getDes().getTag()==0)
				q.add(ed.getDes());
			}	
			//
			//q.peek().setTag(1);//////29/12
			q.poll();
			count++;


		}
		if(this.g.getHashnodes().get(dest).getWeight()==Double.POSITIVE_INFINITY)
			return -1;

		return this.g.getHashnodes().get(dest).getWeight();
	}
	
	

	
	
	
	/**
	 * with this method we can have the shortest pass between 2 node (src,des).
	 */
	
	
	
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		System.out.println("shortestPathDist=" +shortestPathDist(src, dest));
		if(
				shortestPathDist(src, dest)==-1) return null;
		
		LinkedList<node_data> shortt=new LinkedList<node_data>();
		nodedata move=(nodedata) this.g.getHashnodes().get(dest);
		while(move!=this.g.getHashnodes().get(src)) {
			shortt.push(((nodedata) this.g.getHashnodes().get((Integer.valueOf(move.getKey())))));
			move=(nodedata) this.g.getHashnodes().get((Integer.valueOf(move.getWay())));
		}
		shortt.push((nodedata) this.g.getHashnodes().get(src));
        Iterator print =shortt.iterator();
        System.out.println("in short pass function");
        while(print.hasNext()) {
        	nodedata temp=(nodedata) print.next();
        	System.out.println(temp.getKey());
        }
		return shortt;
	}

	/**
	 * This method will get a list of nodes and will find a path that will
	 * visits all those node on the list. We will send the graph to the IsConnect function,
	 * if the graph is not connected we have a function that will copy our graph,
	 * remove all the nodes that aren’t on the list, 
	 * and then if the graph is conneded- we will send back the new graph to TSP function,
	 * if not we will return null (because TSP is working only on a connecnted list. 
	 * If the graph is connect the Tsp function works as follows: we go over the list of targets that we got,
	 * with 2 pointers, one pointer that stands on the “src” node and one function that stands 
	 * on the “dest” node. each time we will call the Shortpass function and mark this node(tag) 
	 * as a node that we already visit it. and then delete this node that we marked from the return list, 
	 * in that way we will go over all the targets that we got.
	 */


	@Override
	public List<node_data> TSP(List<Integer> targets) {

		// TODO Auto-generated method stub
		if(!this.isConnected())
			return TSPifnotconnect(this.g, targets);
		Collection<node_data> p= this.g.getV();
		Iterator zerocolor=p.iterator();
		while(zerocolor.hasNext()) {
			nodedata node=((nodedata) zerocolor.next());
			node.setTag(0);
		}
		LinkedList<node_data> way = new LinkedList<node_data>();
		//DGraph gnew=new DGraph(this.g);
		int num = 0;
		boolean flag=false;
		Iterator<Integer> I=targets.iterator();
		Integer start=I.next();
		while(I.hasNext()) {
			flag=false;
			//Integer start=I.next();
			//System.out.println("start="+start);
			this.g.getHashnodes().get(start).setTag(1);
			Iterator<Integer> end=targets.iterator();

			while(end.hasNext()&&flag==false) {
				Integer number=(Integer) end.next();
				//System.out.println("number=="+number);
				while(this.g.getHashnodes().get(number).getTag()==1&&end.hasNext()) {
					number= end.next();

					//					System.out.println("number="+number);
					//					System.out.println("*");

				}
				this.g.getHashnodes().get(number).setTag(1);
				num=number;
				flag=true;


			}


			//System.out.println("start="+start+", num="+num);
			List<node_data> pass=shortestPath(start,num);
			Iterator<node_data> c=pass.iterator();
			while(c.hasNext()) {
				node_data tocolor=(node_data) c.next();
				this.g.getHashnodes().get(tocolor.getKey()).setTag(1);
				way.add(tocolor);

			}	
			while(start!=num) {
				start=I.next();
			}

		}

		LinkedList<node_data> nontwice=new LinkedList<node_data>();
		Iterator non =way.iterator();
		while(non.hasNext()) {
			nodedata c=(nodedata) non.next();
			if(c!=nontwice.peekLast()) {
				nontwice.add(c);
			}
		}



		return nontwice;

	}

	public List<node_data> TSPifnotconnect(DGraph g,List<Integer> targets) {


		DGraph graflist= new DGraph( g);

		Collection<node_data> c=new LinkedList<node_data>( graflist.getV());
		Iterator<node_data> p=c.iterator();
		System.out.println(c.size());
		int i=0;
		while(p.hasNext()) {
			System.out.println("#");
			nodedata node=((nodedata) p.next());
			System.out.println(node.getKey());
			if(!targets.contains(node.getKey())) {
				graflist.removeNode(node.getKey());
				System.out.println("remove");
			}
			else
				System.out.println("didnt remove");

		}
		//		Collection<node_data> nodes=graflist.getV();
		//		Iterator<node_data> I=nodes.iterator();
		//		while(I.hasNext()) {
		//			node_data current= (nodedata) I.next();
		//			if(!targets.contains(current.getKey())) {
		//				graflist.removeNode(current.getKey());
		//			}
		//		}
		//		Graph_GUI gg=new Graph_GUI(graflist);
		//		gg.setVisible(true);

		Graph_Algo algo=new Graph_Algo(graflist);
		if(algo.isConnected()==true) {
			System.out.println("the new g is connect");
			return algo.TSP(targets);
		}
		else {
			System.out.println("not connect");
			return null;

		}


	}


	@Override
	public graph copy() {
		// TODO Auto-generated method stub

		graph returnn =  new DGraph();

		Iterator<node_data> I =  this.g.getV().iterator();
		while (I.hasNext()){
			node_data temp = (node_data) I.next();
			returnn.addNode(temp);
			//  System.out.println(temp.getKey());
			Collection <edge_data> edges = new LinkedList<edge_data>();
			edges =  this.g.getE(temp.getKey());
			if (edges!=null) {
				Iterator<edge_data> J = edges.iterator();
				while (J.hasNext()) {
					edge_data edge = (edge_data) J.next();
					returnn.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
				}
			}
		}
		return returnn;
	}


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



		HashMap<Integer, node_data> hashnodes=new HashMap<Integer, node_data> ();
		hashnodes.put(a.getKey(), a);
		hashnodes.put(b.getKey(), b);
		hashnodes.put(c.getKey(), c);


		edge_data aa=new edgedata(a,b,40,0);

		edge_data cc=new edgedata(b,c,20,0);
		edge_data dd=new edgedata(a,c,80,0);


		HashMap<Integer, edge_data>hash_a=new HashMap<Integer, edge_data>();
		HashMap<Integer, edge_data>hash_b=new HashMap<Integer, edge_data>();
		HashMap<Integer, edge_data>hash_c=new HashMap<Integer, edge_data>();

		hash_a.put(aa.getDest(), aa);

		hash_b.put(cc.getDest(), cc);
		hash_a.put(dd.getDest(), dd);



		DGraph x=new DGraph();
		HashMap<Integer, HashMap<Integer, edge_data>>hashedges=new HashMap<Integer, HashMap<Integer, edge_data>>();

		hashedges.put(a.getKey(), hash_a);
		hashedges.put(b.getKey(),hash_b);
		hashedges.put(c.getKey(),hash_c);

		x.setHashnodes(hashnodes);
		x.setHashedges(hashedges);



		Graph_Algo test=new Graph_Algo(x);

		
		 System.out.println(test.shortestPathDist(1, 1));
		 List<node_data> qq=test.shortestPath(1, 1);
		 Iterator<node_data> I=qq.iterator();
		 while(I.hasNext()) {
			 System.out.println(I.next().getKey());
		 }
		 
		




	}

	public DGraph getG() {
		return g;
	}


}
