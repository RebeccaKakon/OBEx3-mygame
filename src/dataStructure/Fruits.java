package dataStructure;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Server.game_service;
import utils.Point3D;

/**
 * This class represent a Fruit 
 * A fruit has: value, type, pos and graph
 * @author dalia
 *
 */


public class Fruits implements  Fruit  {

	


	double value;
	int type;
	Point3D pos;
	//edgedata onedge;
	DGraph g;
	
	




	public Fruits(DGraph g) {
		this.value=0;
		this.type=-1;
		this.pos=new Point3D(0,0,0);
		this.g=g;
		//this.onedge=null;
	}
	public Fruits(double value,int type,Point3D pos,DGraph g) {
		this.value=value;
		this.type=type;
		this.pos=new Point3D(pos);
		this.g=g;
		//this.onedge=wherefruit(pos);

	}
	/**
	 * this method will search on witch edge  we have the fruit.
	 * it will use the method distance3D to get to the closest edge.
	 * @param pos
	 * @return
	 */

	public edgedata wherefruit(Point3D pos,game_service game, LinkedList<Fruits> fruits,DGraph g ) {

		{
			edge_data edgeFruit = new edgedata(); 
			
			Point3D Fruit = pos;
			
			boolean Edge=false;

			Iterator<node_data> itN = g.getV().iterator(); 
			while (itN.hasNext() && !Edge) 
			{
				
				
				node_data nd = itN.next();
				Point3D pNodeSrc = nd.getLocation(); 
				Iterator<edge_data> itE = g.getE(nd.getKey()).iterator(); 
				while (itE.hasNext() && !Edge) 
				{
					edge_data ed = itE.next();
					Point3D Node = g.getNode(ed.getDest()).getLocation();
					double srcToF= pNodeSrc.distance3D(Fruit);
					double destToF= Node .distance3D(Fruit);
					double srcToDest= pNodeSrc.distance3D(Node );
					double abs= Math.abs((srcToF+destToF)-srcToDest);
					double eps=0.0000001;
					if (abs<=eps)
					{
						Edge=true;
						edgeFruit=ed;
					}
				}			
			}
			
			
			nodedata min,max;
			edgedata toreturn=(edgedata) edgeFruit;
			if(toreturn.getSrc()<toreturn.getDest()) {
				min=toreturn .getSource();
				max=toreturn.getDes();

			}
			else {

				max=toreturn .getSource();
				min=toreturn.getDes();
			}


			if(this.type==1) {
				toreturn=new edgedata(min,max,-1,0);
			}
			else
				toreturn=new edgedata(max,min,-1,0); 

			System.out.println("answer to return of edge"+toreturn.getSrc()+","+toreturn.getDest());


			return toreturn;
			
		}








	}
	private double distance(Point3D p1, Point3D p2) {
		// TODO Auto-generated method stub

		double a=Math.pow(p2.x()-p1.x(),2)+Math.pow(p2.y()-p1.y(),2);
		return Math.sqrt(a);


	}
	public Fruits(Fruits other) {
		this.value=other.value;
		this.type=other.type;
		this.pos=new Point3D(other.pos);

	}



	public Point3D getPos() {
		return pos;
	}
	public void setPos(Point3D pos) {

		this.pos = pos;

	}
	public double getValue() {
		return value;
	}
	public int getType() {
		return type;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}
	
	/**
	 * This function init our fruits from a String and returns a LinkedList of Fruits
	 */


	public LinkedList <Fruits> initf(String string,DGraph g) {
		// TODO Auto-generated method stub


		//.out.println(string);
		LinkedList <Fruits>answer=new LinkedList <Fruits>();


		JsonParser jp = new JsonParser();
		//	FileReader fr = new FileReader(g);
		JsonArray a = (JsonArray) jp.parse(string);
		//	System.out.println(a.toString());
		//	System.out.println("***");


		//		 Object obj = null ;
		//		 try{
		//			
		//			JsonParser jp = new JsonParser();
		//		//	FileReader fr = new FileReader(g);
		//			obj = jp.parse(string);
		//		 }
		//		 catch() {
		//			 
		//		 }

		//		JsonObject jo = (JsonObject) obj; 
		//		JsonElement fruits = jo.get("Fruits"); 
		//		JsonArray a=fruits.getAsJsonArray();
		//
		//		String f=fruits.toString();


		//System.out.println("&&&");

		for(int i=0;i<a.size();i++) {

			JsonObject n=(JsonObject)a.get(i);
			JsonElement fr=n.get("Fruit");
			JsonObject obj=fr.getAsJsonObject();
			//System.out.println(fr.toString());

			JsonElement valuee=obj.get("value");
			double value=valuee.getAsDouble();

			JsonElement type=obj.get("type");
			int t=type.getAsInt();


			JsonElement pos=obj.get("pos");
			String p=pos.getAsString();
			String[] range=p.split(",");
			double x=Double.valueOf((range[0]));
			double y=Double.valueOf(range[1]);

			Point3D point=new Point3D(x,y,0); 



			Fruits id=new Fruits(value,t,point,g);
			answer.add(id);
		}
		Iterator w=answer.iterator();
		while(w.hasNext()) {
			Fruits c=(Fruits) w.next();
			//System.out.println("type of fruit="+c.getType());
		}











		return answer;
	}


}
