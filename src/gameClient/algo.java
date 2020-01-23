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
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.Robot;
import dataStructure.edge_data;
import dataStructure.edgedata;
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

	public void putRobot(game_service game, int count,LinkedList<Robot> robots,int level) {


		Point3D p = new Point3D(0,0,0);
		if(level==23) {
			game.addRobot(33);
			Robot r = new Robot(33,p, 1,0,0,0);
			robots.add(r);
			game.addRobot(30);
			Robot r2 = new Robot(30,p, 1,0,0,0);
			robots.add(r2);
			game.addRobot(5);
			Robot r3= new Robot(5,p, 1,0,0,0);
			robots.add(r3);
			return;
			
		}
		if(level==20) {
			game.addRobot(20);
			Robot r = new Robot(20,p, 1,0,0,0);
			robots.add(r);
			game.addRobot(32);
			Robot r2 = new Robot(32,p, 1,0,0,0);
			robots.add(r2);
			game.addRobot(5);
			Robot r3= new Robot(5,p, 1,0,0,0);
			robots.add(r3);
			return;
			
		}
		if(level==16) {
			game.addRobot(6);
			Robot r = new Robot(6,p, 1,0,0,0);
			robots.add(r);
			game.addRobot(10);
			Robot r2 = new Robot(10,p, 1,0,0,0);
			robots.add(r2);
			return;
		}
		if(level==13) {
			game.addRobot(11);
			Robot r = new Robot(11,p, 1,0,0,0);
			robots.add(r);
			game.addRobot(32);
			Robot r2 = new Robot(32,p, 1,0,0,0);
			robots.add(r2);
			return;

		}
		if(level==5) {
			game.addRobot(3);
			Robot r = new Robot(3,p, 1,0,0,0);
			robots.add(r);
			return;

		}
		if(level==9) {
			game.addRobot(9);
			Robot r = new Robot(9,p, 1,0,0,0);
			robots.add(r);
			return;
		}

		Fruits temp=this.fruits.get(count);
		edgedata w=temp.wherefruit(temp.getPos(),game,this.fruits,this.gg);
		game.addRobot(w.getSrc());
		Robot r = new Robot(w.getSrc(),p, 1,0,0,0);
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

	public boolean countedge89=false;
	public static boolean b=true;

	public void moveRobots(game_service game, DGraph gg,int level) {
		//System.out.println("move robot");
		if (level ==13|| level==20 ||level==-31 ||level==23 ||level==21|| level==22) {
			level13( game, gg,level);
			return;
		}
		algo cal2=new algo();
		List<String> log = game.getRobots();
		int numrobot=cal2.numrobot(game.toString());
		algo cal=new algo(this.gg,fruits,robots, game, numrobot);
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
						//System.out.println("dest is -1");
						Fruits ff= new Fruits(this.gg);
						this.fruits.clear();
						this.fruits=(LinkedList<Fruits>)ff.initf(game.getFruits().toString(),this.gg);

						dest = cal.nextNode2(gg, src,this.fruits);    //Ex4


						edgedata destedge=cal.nextedge(gg, src,this.fruits);


						Graph_Algo shortpass= new Graph_Algo(gg);

						System.out.println("list shoreted pass between"+src+"---"+dest);
						List<node_data> a=shortpass.shortestPath(src,dest);   //a new algo
                        

						Iterator<node_data> y=a.iterator();
						

						System.out.println("shorted pass");
						while(y.hasNext()) {
							node_data temp=y.next();
							game.chooseNextEdge(rid, temp.getKey());

						}

						System.out.println("end shorted pass");
						System.out.println("edge of fruit="+destedge.getSrc()+","+destedge.getDest());
						game.chooseNextEdge(rid,destedge.getDest()); 

						this.fruits.clear();
						this.fruits=(LinkedList<Fruits>)ff.initf(game.getFruits().toString(),this.gg);
						//						if(a.size()>1) {
						//							node_data newdes =a.get(1);
						//							System.out.println( "goint to on the way to dest="+newdes.getKey());
						//							game.chooseNextEdge(rid, newdes.getKey());
						//							this.fruits.clear();
						//							this.fruits=(LinkedList<Fruits>)ff.initf(game.getFruits().toString(),this.gg);
						//							game.move();
						//							if(newdes.getKey()==dest) {
						//								if(destedge.getSrc()==dest)
						//
						//									game.chooseNextEdge(rid,destedge.getDest()); 
						//
						//								if(destedge.getDest()==dest)
						//									game.chooseNextEdge(rid,destedge.getSrc()); 
						//								this.fruits.clear();
						//								this.fruits=(LinkedList<Fruits>)ff.initf(game.getFruits().toString(),this.gg);
						//
						//							}
						//						}
						//						else {
						//							
						//
						//														game.chooseNextEdge(rid,destedge.getDest());
						//														game.chooseNextEdge(rid,destedge.getSrc()); 
						//						}



						Robot yy=new Robot();
						robots= yy.initr(game.getRobots().toString());
						this.fruits.clear();
						this.fruits=(LinkedList<Fruits>)ff.initf(game.getFruits().toString(),this.gg);

					}
				}




				catch (JSONException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}
		}

	}




	private void level13(game_service game2, DGraph gg2, int level) {

		// TODO Auto-generated method stub
		algo cal2=new algo();
		List<String> log = game.getRobots();
		int numrobot=cal2.numrobot(game.toString());
		algo cal=new algo(this.gg,fruits,robots, game, numrobot);
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
						//System.out.println("dest is -1");
						Fruits ff= new Fruits(this.gg);
						this.fruits.clear();
						this.fruits=(LinkedList<Fruits>)ff.initf(game.getFruits().toString(),this.gg);

						dest = cal.nextNode2(gg, src,this.fruits);    //Ex4


						edgedata destedge=cal.nextedge(gg, src,this.fruits);


						Graph_Algo shortpass= new Graph_Algo(gg);

						System.out.println("list shoreted pass between"+src+"---"+dest);
						if(shortpass.shortestPathDist(src, dest)==-1){
							int temp =src;
							//while(game.isRunning()) {
							int des=cal.nextNode(this.gg, temp);
							game.chooseNextEdge(rid,des); 
							src=des;
							return;
							//}
						}
					
						List<node_data> a=shortpass.shortestPath(src,dest);   //a new algo
						
						int size=a.size();
						Iterator<node_data> y=a.iterator();
						System.out.println("shorted pass");
						y.next();
						if(size==1)
							game.chooseNextEdge(rid,destedge.getDest()); 
						else {
							node_data temp=y.next();
							game.chooseNextEdge(rid, temp.getKey());
						}

						System.out.println("end shorted pass");
						System.out.println("edge of fruit="+destedge.getSrc()+","+destedge.getDest());

						this.fruits.clear();
						this.fruits=(LinkedList<Fruits>)ff.initf(game.getFruits().toString(),this.gg);
						Robot yy=new Robot();
						robots= yy.initr(game.getRobots().toString());
						this.fruits.clear();
						this.fruits=(LinkedList<Fruits>)ff.initf(game.getFruits().toString(),this.gg);

					}
				}




				catch (JSONException e) {
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
		//System.out.println("in function");
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
				//game.move();
				robots= y.initr(game.getRobots().toString());


			}
			src=null;
			des=null;


		}

	}

	public  int nextNode2(DGraph g, int src,LinkedList<Fruits> fruits) {
		//to do int(tsp) to each fruit 
		// ans then to to tsp that returns olis and to go by it
		Graph_Algo calculate= new  Graph_Algo(this.gg);
		algo cal=new algo();
		double min=10000;
		int place=0;
		//		double xscale= scale(g.getHashnodes().get(src).getLocation().x(),g.getMinx(),g.getMaxx(),50,950);
		//		double yscale= scale(g.getHashnodes().get(src).getLocation().y(),g.getMiny(),g.getMaxy(),70,930);
		//
		//		System.out.println("scale of node="+ xscale+","+yscale);
		//
		//		Point3D p=new Point3D(xscale,yscale,0);

		Iterator<Fruits> I=fruits.iterator();
		while(I.hasNext()) {
			Fruits f=(Fruits) I.next();

			edgedata edgefruit= f.wherefruit(f.getPos(),this.game,this.fruits,this.gg);
			double shortpass=calculate.shortestPathDist(src, edgefruit.getSrc());

			if(shortpass<min) {

				place=edgefruit.getSrc();
				min=shortpass;
			}
		}


		System.out.println("place="+place);
		return place;




	}
	public  edgedata nextedge (DGraph g, int src,LinkedList<Fruits> fruits) {
		//to do int(tsp) to each fruit 
		// ans then to to tsp that returns olis and to go by it
		Graph_Algo calculate= new  Graph_Algo(this.gg);
		algo cal=new algo();
		double min=10000;
		edgedata place=new edgedata();
		//			double xscale= scale(g.getHashnodes().get(src).getLocation().x(),g.getMinx(),g.getMaxx(),50,950);
		//			double yscale= scale(g.getHashnodes().get(src).getLocation().y(),g.getMiny(),g.getMaxy(),70,930);
		//
		//			System.out.println("scale of node="+ xscale+","+yscale);
		//
		//			Point3D p=new Point3D(xscale,yscale,0);

		Iterator<Fruits> I=fruits.iterator();
		while(I.hasNext()) {
			Fruits f=(Fruits) I.next();
			edgedata edgefruit= f.wherefruit(f.getPos(),this.game,this.fruits,this.gg);
			double shortpass=calculate.shortestPathDist(src, edgefruit.getSrc());

			if(shortpass<min ) {

				place=new edgedata(edgefruit);
				min=shortpass;
			}
		}



		//	
		//temp.initf(this.game.getFruits().toString(), g);

		//		int[] answer=new int [2];
		//		answer[0]=edgefruit.getSrc();
		//		answer[1]=edgefruit.getDest();

		return place;






	}

	public  Fruits nextedgefruits (DGraph g, int src,LinkedList<Fruits> fruits) {
		//to do int(tsp) to each fruit 
		// ans then to to tsp that returns olis and to go by it
		Graph_Algo calculate= new  Graph_Algo(this.gg);
		algo cal=new algo();
		double min=10000;
		Fruits place=new Fruits(this.gg); 

		Iterator<Fruits> I=fruits.iterator();
		while(I.hasNext()) {
			Fruits f=(Fruits) I.next();
			edgedata edgefruit= f.wherefruit(f.getPos(),this.game,this.fruits,this.gg);
			double shortpass=calculate.shortestPathDist(src, edgefruit.getSrc());

			if(shortpass<min ) {

				place=new Fruits(f);
				min=shortpass;
			}
		}



		//temp.initf(this.game.getFruits().toString(), g);

		//		int[] answer=new int [2];
		//		answer[0]=edgefruit.getSrc();
		//		answer[1]=edgefruit.getDest();
		return place;




	}


}


