package dataStructure;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.Point3D;

import java.io.FileNotFoundException;

/**
 * A graph builts by points and edges. 
 * Each graph contain a collection of node and a collectiom of edges, 
 * Our information of nodes and edges is saves on a HashMaps to helps us gets information effectively
 * @author shahar&rivka
 *
 */



public class DGraph implements graph,Serializable{

	private HashMap<Integer,node_data> hashnodes;
	private HashMap<Integer,HashMap<Integer,edge_data>> hashedges;
	private GraphListener Lis;

	private int countedgeg=0;
	private int MC=0;
	private double maxx=0;    //12/1
	private double minx=1000;   //12/1
	private double maxy=0;    //12/1
	private double miny=1000;   //12/1

	public void addListener(GraphListener Lis) {
		this.Lis=Lis;
	}



	public DGraph(HashMap<Integer,node_data> hashnodes,HashMap<Integer,HashMap<Integer,edge_data>> hashedges,int countedgeg) {
		this.hashnodes=new HashMap<Integer,node_data>() ;
		this.hashnodes.putAll(hashnodes);
		this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.hashedges.putAll(hashedges);
		this.countedgeg=countedgeg;

	}

	public DGraph() {
		this.hashnodes=new HashMap<Integer,node_data>() ;
		this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>();
	}
	public DGraph(DGraph other) {
		this.hashnodes=new HashMap<Integer,node_data>();
		hashnodes.putAll(other.hashnodes);

		this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.hashedges.putAll(other.hashedges);
		//this.hashnodes=new HashMap<Integer,node_data>(other.hashnodes) ;
		//this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>(other.hashedges);
		this.countedgeg=other.countedgeg;

	}


	@Override
	public node_data getNode(int key) {
		// TODO Auto-generated method stub
		if(this.getHashnodes().get(key)==null) return null;
		else
			return hashnodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		// TODO Auto-generated method stub

		if( hashnodes.get(src)!=null&& hashedges.get(src).get(dest)!=null)   //to check if its doesnt connect if it returns null{

		{
			double weight=hashedges.get(src).get(dest).getWeight();
			int tag=hashedges.get(src).get(dest).getTag();
			nodedata source= (nodedata)hashnodes.get(src);
			nodedata des=(nodedata)hashnodes.get(dest);
			edgedata edg=new edgedata(source,des,weight,tag);
			return edg;
		}
		return null;

	}
	/**
	 * This method will add a node to the graph, if this node is allready exist 
	 * it want add.
	 */

	@Override
	public void addNode(node_data n) {  //check
		// TODO Auto-generated method stub
		if(hashnodes.get(n.getKey())!=null) return;
		hashnodes.put(n.getKey(), (nodedata)n);
		HashMap<Integer,edge_data> value= new HashMap<Integer,edge_data>();
		hashedges.put(n.getKey(), value);
		if(n.getLocation().x()>this.maxx)     //12/1
			maxx=n.getLocation().x();
		if(n.getLocation().x()<this.minx)
			minx=n.getLocation().x();
		if(n.getLocation().y()>this.maxy)
			maxy=n.getLocation().y();
		if(n.getLocation().y()<this.miny)
			miny=n.getLocation().y();         //12/1
		MC++;
		updateListener();
	}




	public double getMinx() {
		return minx;
	}



	public double getMaxx() {
		return maxx;
	}



	public double getMaxy() {
		return maxy;
	}



	public double getMiny() {
		return miny;
	}



	private void updateListener() 
	{
		if(Lis != null)
		{
			Lis.graphUpdated();
		}

	}

/**
 * This method connect between 2 node(src,des), in this way we have our new edges on the graph
 */

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub

		//if your trying to connect something that is allready connected
		if(hashnodes.get(src)==null||hashnodes.get(dest)==null) {
			System.out.println("not exsist");
		}
		else {
			
			edgedata newedge=new edgedata((nodedata)hashnodes.get(src),(nodedata)hashnodes.get(dest),w,0);
			hashedges.get(src).put(dest, newedge);		
			countedgeg++;
		}
		MC++;
		updateListener();



	}

	public HashMap<Integer, node_data> getHashnodes() {
		return hashnodes;
	}
	public void setHashnodes(HashMap<Integer, node_data> hashnodes) {
		this.hashnodes = hashnodes;
		MC++;
		updateListener();
	}
	public HashMap<Integer, HashMap<Integer, edge_data>> getHashedges() {
		return hashedges;
	}
	public void setHashedges(HashMap<Integer, HashMap<Integer, edge_data>> hashedges) {
		this.hashedges = hashedges;
		MC++;
		updateListener();
	}
	public int getCountedgeg() {
		return countedgeg;
	}
	public void setCountedgeg(int countedgeg) {
		this.countedgeg = countedgeg;
		MC++;
		updateListener();
	}
	/**
	 * This method will give us the collection of nodes in the graph
	 */
	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return hashnodes.values();   
	}
	/**
	 * This method will give us the collection of edges in the graph
	 */

	@Override
	public Collection<edge_data> getE(int node_id) {

		// TODO Auto-generated method stub
		if(this.getHashnodes().get(node_id)==null) return null;
		else
			return hashedges.get(node_id).values();


	}
	/**
	 * This method will delete a certain node from the graph ,
	 *  with each node that we are deleting we will also delete all of the nodes that are connectes to it.
	 *  To do so we will first delete the specific node from our collection of nodes (from the HashMap), 
	 *  and then we will pass on all HashMaps of edges and delete all the edges that connect to this node.
	 */

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		node_data remove=new nodedata();
		if(hashnodes.get(key)==null) {
			System.out.println("do not exsist");
		}
		else {
			remove=new nodedata((nodedata)hashnodes.get(key));
			hashnodes.remove(key);
			hashedges.remove(key);
			Collection<node_data> node=this.getV();
			Iterator J=node.iterator();
			while(J.hasNext()) {
				nodedata current=(nodedata) J.next();			
				HashMap<Integer,edge_data> hashcurrent=this.getHashedges().get(current.getKey());
				hashcurrent.remove(key);
				this.countedgeg=this.countedgeg-1;

			}
		}
		MC++;
		updateListener();
		return remove;

	}

    /**
     * This method delete an edge from the graph
     */


	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub

		edge_data remove=new edgedata();
		if(hashnodes.get(src)==null||hashnodes.get(dest)==null) {
			System.out.println("there is no posibility to remove");
		}

		else {
			remove=(edgedata)hashedges.get(src).get(dest);
			hashedges.get(src).remove(dest);

		}
		countedgeg--;
		updateListener();
		MC++;
		return remove; 
	}


	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return hashnodes.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return countedgeg;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return MC;
	}



//	public void init(String g)  {
//		// TODO Auto-generated method stub
//
//		 Object obj = null ;
//		try {
//			JsonParser jp = new JsonParser();
//			FileReader fr = new FileReader(g);
//			obj = jp.parse(fr);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//
//		JsonObject jo = (JsonObject) obj; 
//		JsonElement edges = jo.get("Edges"); 
//		JsonArray a=edges.getAsJsonArray();
//		JsonElement nodes =  jo.get("Nodes"); 
//		JsonArray b=nodes.getAsJsonArray();
//		
//		String edge=edges.toString();
//		String node=nodes.toString();
//		for(int i=0;i<b.size();i++) {
//			
//			JsonObject n=(JsonObject)b.get(i);
//			String location=n.get("pos").getAsString();
//			String[] range=location.split(",");
//			double x=Double.valueOf((range[0]));
//			double y=Double.valueOf(range[1]);
//			
//			Point3D p=new Point3D(x,y,0); 
//			
//			JsonElement current2=n.get("id");
//			int name=current2.getAsInt();
//	
//			nodedata id=new nodedata(name,p,0,0);
//			this.addNode(id);
//		
//		
//		}
//		
//		for(int i=0;i<a.size();i++) {
//			JsonObject n=(JsonObject)a.get(i);
//		
//			JsonElement current=n.get("src");
//		
//			JsonElement current2=n.get("w");
//		
//			
//			double ww=current2.getAsDouble();
//			
//			JsonElement current3=n.get("dest");
//		
//			
//			this.connect(current.getAsInt(), current3.getAsInt(), current2.getAsDouble());
//			
//		}
//		
//		
//	}
	/** 
	 * This function init our graph from a String that represent our grpah. 
	 * @param g
	 * 
	 */
	
	
	public void init (String g)  {
		// TODO Auto-generated method stub

		 Object obj = null ;
	
			JsonParser jp = new JsonParser();
		//	FileReader fr = new FileReader(g);
			obj = jp.parse(g);

		JsonObject jo = (JsonObject) obj; 
		JsonElement edges = jo.get("Edges"); 
		JsonArray a=edges.getAsJsonArray();
		JsonElement nodes =  jo.get("Nodes"); 
		JsonArray b=nodes.getAsJsonArray();
		
		String edge=edges.toString();
		String node=nodes.toString();
		for(int i=0;i<b.size();i++) {
			
			JsonObject n=(JsonObject)b.get(i);
			String location=n.get("pos").getAsString();
			String[] range=location.split(",");
			double x=Double.valueOf((range[0]));
			double y=Double.valueOf(range[1]);
			
			Point3D p=new Point3D(x,y,0); 
			
			JsonElement current2=n.get("id");
			int name=current2.getAsInt();
	
			nodedata id=new nodedata(name,p,0,0);
			this.addNode(id);
		
		
		}
		
		for(int i=0;i<a.size();i++) {
			JsonObject n=(JsonObject)a.get(i);
		
			JsonElement current=n.get("src");
		
			JsonElement current2=n.get("w");
		
			
			double ww=current2.getAsDouble();
			
			JsonElement current3=n.get("dest");
		
			
			this.connect(current.getAsInt(), current3.getAsInt(), current2.getAsDouble());
			
		}
		
		
	}
}
