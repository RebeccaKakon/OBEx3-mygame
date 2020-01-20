package dataStructure;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.Point3D;



public class Fruits  {

	double value;
	int type;
	Point3D pos;
	//edgedata onedge;
	DGraph g;



	public Fruits() {
		this.value=0;
		this.type=-1;
		this.pos=new Point3D(0,0,0);
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
	 * it will use the method distance to get to the closest edge.
	 * @param pos
	 * @return
	 */

	public edgedata wherefruit(Point3D pos) {
		// TODO Auto-generated method stub
		Collection<node_data> nodes=g.getV();
		Iterator<node_data> I=nodes.iterator();
		edgedata answer=null;
		double mindistance=1000;
		while(I.hasNext()) {
			nodedata current=(nodedata) I.next();
			Collection<edge_data> edges=this.g.getE(current.getKey());
			Iterator<edge_data> w=edges.iterator();
			while(w.hasNext()) {
				edgedata currentedge=(edgedata) w.next();
				double dis_s_f=(distance(currentedge.getSource().getLocation(),pos));
				double dis_f_d=(distance(pos,currentedge.getDes().getLocation()));
				double dis_s_d=(distance(currentedge.getSource().getLocation(),currentedge.getDes().getLocation()));
				if(dis_s_f+dis_f_d-dis_s_d<0.001 && dis_s_f+dis_f_d-dis_s_d>0) {

						if(dis_s_f+dis_f_d-dis_s_d<mindistance) {
							answer=(currentedge);
							mindistance=dis_s_f+dis_f_d-dis_s_d;
							
						}
				}

			}
		}
		edgedata toreturn;
		nodedata min;
		nodedata max;
		if(answer.getSrc()<answer.getDest()) {

			min=answer.getSource();
			max=answer.getDes();
		}
		else {

			max=answer.getSource();
			min=answer.getDes();
		}


		if(this.type==1) {
			toreturn=new edgedata(min,max,-1,0); 
		}
		else
			toreturn=new edgedata(max,min,-1,0); 
		//System.out.println("the edge of the fruit"+toreturn.getSrc()+","+toreturn.getDest());

		return toreturn;
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
