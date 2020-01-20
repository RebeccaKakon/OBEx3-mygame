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

/**
 * This class represents the algorithms that are being used at our games:
 * placing the robots, moving the robot,how much robots we have, scale that helps us with our Gui,
 * add robot by click, and a function for moving the manual game.
 *
 */


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
//		Thread a=new Thread();
//		a.start();
	}
	
	/**
	 * This function helps us placing the robots of the automatic game on different nodes,
	 * the function gets a param name count, witch represent how much robots we had placed till this function.
	 * This function adds a robot to our game and adds this robot to our list of robot. 
	 *
	 */
	
	public void putRobot(game_service game, int count,LinkedList<Robot> robots) {
		Point3D p = new Point3D(0,0,0);

		game.addRobot(count);

		Robot r = new Robot(count,p, 1,0,0,0);
		robots.add(r);
		//		Iterator<Robot> y=robots.iterator();
		//		while(y.hasNext()) {
		//			Robot temp=y.next();
		//			System.out.println(temp.getId());
		//		}

	}
	
	/**
	 * This function helps moving our robots in the automatic game.
	 * In this function each time we will move a different robot, and update 
	 * our lists.
	 * @param game, gg
	 * 
	 */

	public void moveRobots(game_service game, DGraph gg) {
		algo cal=new algo();
//		long t ;
//		if(game!=null)
//		t = game.timeToEnd();
//		List<String> log=null;   //20/1
//		if(game!=null)   //20/1
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




	/**
	 * This function helps us decide in each move where should our robot will go. 
	 * The function gets the game that we are playing, and the src that our robot is on
	 * and decide what will be the dest of this robot.
	 * @param graph game
	 * @param src
	 */


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
	
	/**
	 * This function helps calculate how many robots we have in our game.
	 * This is a function that being used in moveRobots function and with the number that
	 * this function returns, the moveRobots function will add and move this robots.
	 * @param String gameinfo - Information about the current game
	 */
	
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
	
	/**
	 * This function helps calculate the distance between two points 
	 * @param Point3D p1 
	 * @param Point3D p2 
	 */
	
	public double distance(Point3D p1, Point3D p2) {
		// TODO Auto-generated method stub

		double a=Math.pow(p2.x()-p1.x(),2)+Math.pow(p2.y()-p1.y(),2);
		return Math.sqrt(a);


	}
	
	/**
	 * This function helps calculate the place we need to draw our lists of the game,
	 * this function is being uses in our Gui class also.
	 * 
	 * @param double r_min - the min node of our nodes
	 * @param double r_max - the max node of our nodes
	 * @param double t_min -our window min size
	 * @param double t_max -our window max size
	 * @return- the correct scale by our Gui.
	 */
	
	private static double scale(double data, double r_min, double r_max, // 12/1
			double t_min, double t_max) {

		double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;
		return res;

	} 
	
	/**
	 * This function helps in our manual game, placing the robots 
	 * where the player choosed he wants to put the robots.
	 * 
	 * @param MouseEvent e - the click that our player did.
	 * @param int width - of our Gui
	 * @param int height -of our Gui
	 * @param int idrobot -the robot that suppose to be placed now
	 * Our function going all over our nodes of the graph and finding the 
	 * closest node to the Point that the player had clicked- this is the node that our function is placing the robot.
	 * @return- true if succeeded to place the robot.
	 */

	public boolean addrobotbyclick(MouseEvent e,int width, int height,int idrobot) {
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
		Robot r = new Robot(idrobot,p, 1,0,0,0);
		this.robots.add(r);
		return true;

	}

	public static Point3D src=null; 
	public static Point3D des=null;
	
	/**
	 * This function represents the movement of a manual game:
	 * The function gets a Point that represent the src or the dest of the robot,
	 * saves that point until this function gets another point.
	 * after this function has the src and the des of the robot the player wants to move, 
	 * we will pass all over our robot and see witch robot is the closest to the place where the player 
	 * had clicked- this is the robot we will going to move.
	 * we will pass all over our nodes and see witch nodes is the closest to the place where the player 
	 * had clicked- this is the place we will send the robot.
	 * 
	 * @param click -the Point represent the src or the dest.
	 * @param int width - of our Gui
	 * @param int height -of our Gui
	 * 
	 *
	 */
	
	
	public void movemanual(int width, int height, Point3D click) {
		// TODO Auto-generated method stub
//		
//		Iterator<Robot> yy=robots.iterator();
//		System.out.println("the robot in my movemanual=");
//		while(yy.hasNext()) {
//			Robot temp2=yy.next();
//			System.out.println(temp2.getId());
//		}
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
				double robotry=scale(r.getPos().y(),this.gg.getMiny(),this.gg.getMaxy(), 70,height-70);
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
				if(distance(current,des)<100) {
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


