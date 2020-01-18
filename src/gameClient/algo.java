package gameClient;

import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.nodedata;
import utils.Point3D;

public class algo {

	private DGraph gg;
	LinkedList<Fruits> fruits;
	LinkedList<Robot> robots;
	game_service game;
	int numrobot;

	public algo() {

	}

	public algo( DGraph gg,LinkedList<Fruits> fruits,LinkedList<Robot> robots,game_service game,int numrobot){
		this.gg=gg;
		this.fruits=fruits;
		this.robots=robots;
		this.game=game;
		this.numrobot=numrobot;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread a=new Thread();
		a.start();
	}

	public void moveRobots(game_service game, DGraph gg) {
		algo cal=new algo();
		long t = game.timeToEnd();
		List<String> log = game.move();
		int numrobot=cal.numrobot(game.toString());
		if(log!=null) {
			for(int i=0;i<numrobot;i++) {
				String robot_json = log.get(i);   
				JSONObject line;
				try {
					line = new JSONObject(robot_json);
					JSONObject ttt = line.getJSONObject("Robot");
					int rid = ttt.getInt("id");
					int src = ttt.getInt("src");
					int dest = ttt.getInt("dest");
					if(dest==-1) {	
						dest = cal.nextNode(gg, src);
						//    dest=nextNode2( mygame.gg, src, mygame.fruits);
						game.chooseNextEdge(rid, dest);
						//						game.chooseNextEdge(rid, answer[0]);
						//						
						//						game.chooseNextEdge(rid, answer[1]);


						game.move();
						Robot y=new Robot();
						robots= y.initr(game.getRobots().toString());
						Fruits f=new Fruits();
						this.fruits=(LinkedList<Fruits>)f.initf(game.getFruits().toString(),this.gg);

					}



				} catch (JSONException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}
		}

	}






	public  int nextNode(graph g, int src) {
		int ans = -1;
		Collection<edge_data> ee = g.getE(src);
		Iterator<edge_data> itr = ee.iterator();
		int s = ee.size();
		int r = (int)(Math.random()*s);
		int i=0;
		while(i<r) {itr.next();i++;}
		ans = itr.next().getDest();
		return ans;
	}
	public  int numrobot(String gameinfo) {
		// TODO Auto-generated method stub

		Object obj = null ;

		JsonParser jp = new JsonParser();
		//	FileReader fr = new FileReader(g);
		obj = jp.parse(gameinfo);

		JsonObject jo = (JsonObject) obj; 
		JsonElement GameServer = jo.get("GameServer"); 

		JsonObject b=GameServer.getAsJsonObject();
		//System.out.println("b="+b);


		JsonElement n=b.get("robots");

		int numofrobot=n.getAsInt();

		return numofrobot;


	}
	public double distance(Point3D p1, Point3D p2) {
		// TODO Auto-generated method stub

		double a=Math.pow(p2.x()-p1.x(),2)+Math.pow(p2.y()-p1.y(),2);
		return Math.sqrt(a);


	}
	private static double scale(double data, double r_min, double r_max, // 12/1
			double t_min, double t_max) {

		double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;
		return res;

	} 

	public boolean addrobotbyclick(MouseEvent e,int width, int height) {
		// TODO Auto-generated method stub
		System.out.println("in function");
		int x = e.getX();
		int y = e.getY();
		Point3D p = new Point3D(x,y,0);  //robot add by the user

		double mindes=1000;
		int srcnode=0;
		//System.out.println("p="+p.ix()+","+p.y());
		Collection<node_data> nodes=this.gg.getV();
		Iterator<node_data> I=nodes.iterator();
		while(I.hasNext()) {
			nodedata current= (nodedata) I.next();
			double xnode=scale(current.getLocation().x(),this.gg.getMinx(),this.gg.getMaxx(), 50,width- 50);
			double ynode=scale(current.getLocation().y(),this.gg.getMiny(),this.gg.getMaxy(), 70,height-70);
			Point3D currentnew=new Point3D( xnode,ynode,0);
			//System.out.println(currentnew.x()+","+currentnew.y());
			double distance=distance(currentnew,p);
			//System.out.println(distance);
			if(distance<mindes) {
				if(distance<100) {
					mindes=distance;
					srcnode=current.getKey();
				}

			}
		}
		if(mindes==1000) {
			return false;
		}
		game.addRobot(srcnode);
		Robot t=new Robot();
		this.robots=t.initr(game.getRobots().toString());
		Robot r = new Robot(srcnode,p, 1,0,0,0);
		return true;

	}

	public static Point3D src=null; 
	public static Point3D des=null;
	public void movemanual(int width, int height, Point3D click) {
		// TODO Auto-generated method stub
		if(src==null) {
			src=click;
		}
		else
			if(src!=null && des==null) {
				des=click;
			}
		
		

		if(src !=null && des != null) {
			System.out.println("src="+src.x()+","+src.y());
			System.out.println("des="+des.x()+","+des.y());
			System.out.println("im in the function");
			double min=1000;
			double mindes=1000;
			Robot answer=null;
			Iterator<Robot> I =this.robots.iterator();
			while(I.hasNext()) {
				Robot r=I.next();
				System.out.println("sll robot="+r.getId());
				System.out.println("sll robot="+r.getPos().x()+","+r.getPos().y());
				double robotrx=scale(r.getPos().x(),this.gg.getMinx(),this.gg.getMaxx(), 50,width- 50);
				double robotry=scale(r.getPos().x(),this.gg.getMinx(),this.gg.getMaxx(), 50,width- 50);
				Point3D rob=new Point3D(robotrx,robotry,0);
				
				System.out.println("robot is in ="+rob.x()+","+rob.y());
				if(distance(rob,src)<min) {
					//if(distance(rob,src)<100) {
						System.out.println("i found robot close to src");
						min=distance(rob,src);
						System.out.println("r="+r.getId());
						answer=r;
					//}


				}
			}
			nodedata answer2=null;
			
			Iterator<node_data> W=this.gg.getHashnodes().values().iterator();
			while(W.hasNext()) {
				nodedata currentnew=(nodedata) W.next();
				double xnode=scale(currentnew.getLocation().x(),this.gg.getMinx(),this.gg.getMaxx(), 50,width- 50);
				double ynode=scale(currentnew.getLocation().y(),this.gg.getMiny(),this.gg.getMaxy(), 70,height-70);
				Point3D current=new Point3D( xnode,ynode,0);
				if(distance(current,des)<30) {
					if(distance(current,des)<mindes) {
						System.out.println("i found node close to des");
						mindes=distance(current,des);
						answer2=currentnew;

					}

				}
			}
			if(min!=1000 && mindes!=1000) {
				System.out.println("not 1000");
				System.out.println(answer.getId());
				System.out.println(answer2.getKey());
				game.chooseNextEdge(answer.getId(), answer2.getKey());
				Robot y=new Robot();
				robots= y.initr(game.getRobots().toString());
				game.move();
				robots= y.initr(game.getRobots().toString());
				
				
			}
			src=null;
			des=null;


		}
		
	}
}


