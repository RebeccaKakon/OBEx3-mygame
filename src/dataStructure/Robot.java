package dataStructure;

import java.util.Iterator;

import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.Point3D;

/**
 * This class represent a Robot
 * @author shahar & rivka 
 *
 */

public class Robot implements Robots{

	int id;
	double value;
	int src;
	int dest;
	double speed;
	Point3D Pos;

	public Robot(int id,Point3D pos,double speed,int dest,int src, double value) {
		this.Pos=new Point3D (pos);
		this.speed=speed;
		this.dest=dest;
		this.src=src;
		this.value=value;
		this.id=id;
	}



	public Robot() {
		// TODO Auto-generated constructor stub
		 id=0;
		 value=0;
		 src=0;
		 dest=-1;
	     speed=0;
		  Pos=new Point3D(0,0,0);
		
	}



	

	public Robot(Robot next) {
		// TODO Auto-generated constructor stub
		id=next.id;
		 value=next.value;
		 src=next.src;
		 dest=next.dest;
	     speed=next.speed;
		  Pos=new Point3D(next.Pos);
	}



	public Point3D getPos() {
		return Pos;
	}

    
	/**
	 * init all the robots to a LinkedList of robots
	 * 
	 */

	public LinkedList <Robot> initr(String string) {

		LinkedList <Robot>answer=new LinkedList <Robot>();
		JsonParser jp = new JsonParser();
		// FileReader fr = new FileReader(g);
		JsonArray a = (JsonArray) jp.parse(string);
		//JsonObject a = (JsonObject) jp.parse(string);
		//System.out.println("robor in gui=" + a);
		for(int i=0;i<a.size();i++) {

			JsonObject xx=(JsonObject)a.get(i);

			JsonElement n = xx.get("Robot");
			JsonObject nn = (JsonObject) jp.parse(n.toString());

			JsonElement id = nn.get("id");
			int ID = id.getAsInt();

			JsonElement value = nn.get("value");
			double VALUE = value.getAsDouble();

			JsonElement src = nn.get("src");
			int SRC = src.getAsInt();

			JsonElement des = nn.get("dest");
			int DES = des.getAsInt();

			JsonElement speed = nn.get("speed");
			double SPEED =speed.getAsDouble();

			JsonElement pos = nn.get("pos");
			String p = pos.getAsString();

			String[] range = p.split(",");
			double x = Double.valueOf((range[0]));
			double y = Double.valueOf(range[1]);

			Pos=new Point3D(x,y,0);
			Robot current=new Robot(ID,Pos,SPEED,DES,SRC,VALUE);
			//System.out.println("in the init="+ID+","+VALUE+","+SRC+","+DES+","+SPEED+","+Pos+"");
			answer.add(current);
		}
		
		return answer;



		//whithout scale
	}

	public int getId() {
		return id;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
