package gameClient;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.GraphListener;
import dataStructure.Robot;
import dataStructure.edge_data;
import dataStructure.edgedata;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.nodedata;
import gui.GraphGUIstddraw;
import gui.Graph_GUI;
import oop_dataStructure.oop_graph;
import utils.KML;
import utils.Point3D;


/**
 * This class represents a graphical appearance, showing the user the game he had chosen to play.
 * 
 * 
 */

public class MyGameGUI extends JFrame implements ActionListener, MouseListener, GraphListener{

	public DGraph gg;
	public static LinkedList<Fruits> fruits;
	public static LinkedList<Robot> robots;
	public static game_service game;
	private algo cal;
	private boolean gamemanual;
	public static boolean insert=true;
	public static boolean move=false;
	

	public void setGamemanual(boolean gamemanual) {
		this.gamemanual = gamemanual;
	}

	public void setGg(DGraph gg) {
		this.gg = gg;
	}

	public DGraph getGg() {
		return gg;
	}

	public static LinkedList<Robot> getRobots() {
		return robots;
	}

	public  game_service getGame() {
		return game;
	}

	public algo getCal() {
		return cal;
	}

	public boolean isGamemanual() {
		return gamemanual;
	}

	public static long getStart() {
		return start;
	}

	public static boolean isTwo() {
		return two;
	}

	public static Point3D getSrc() {
		return src;
	}

	/**
	 * 
	 * @param data  denote some data to be scaled
	 * @param r_min the minimum of the range of our data
	 * @param r_max the maximum of the range of our data
	 * @param t_min the minimum of the range of our desired target scaling
	 * @param t_max the maximum of the range of our desired target scaling
	 * @return
	 */
	private static double scale(double data, double r_min, double r_max, // 12/1
			double t_min, double t_max) {

		double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;
		return res;

	} // 12/1
	//	public MyGameGUI() {
	//		this.gg = null;// new DGraph((DGraph)other) ;
	//		this.fruits = new LinkedList<Fruits>();
	//		this.robots = new LinkedList<Robot>();
	//		//this.gg.addListener(this); // also for fruits
	//		this.setSize(1000, 1000);
	//		ImageIcon us = new ImageIcon("C:/Users/dalia/Desktop/mazeofwazepicture.jpg");
	//		
	//	}

	public MyGameGUI(game_service game) {
		this.gg = null;// new DGraph((DGraph)other) ;
		this.fruits = new LinkedList<Fruits>();
		this.robots = new LinkedList<Robot>();
		this.game=game;
		//this.gg.addListener(this); // also for fruits
		initGUI( );
	}

	private void initGUI() {

		this.setSize(1000, 1000);
		ImageIcon us = new ImageIcon("C:/Users/dalia/Desktop/mazeofwazepicture.jpg");
		this.setIconImage(us.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("maze of waze by the amazed shahar&rivka");
		this.setResizable(true);

		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File");
		menuBar.add(file);

		MenuItem save = new MenuItem("save");
		save.addActionListener(this);
		MenuItem load = new MenuItem("load");
		load.addActionListener(this);

		file.add(save);
		file.add(load);

		Menu function = new Menu("Function");
		MenuItem is = new MenuItem("Is connect");
		MenuItem repaint = new MenuItem("repaint");
		MenuItem shortpass = new MenuItem("short pass");
		MenuItem Tsp = new MenuItem("Tsp");
		MenuItem rn = new MenuItem("remove node");
		MenuItem re = new MenuItem("remove edge");
		MenuItem con = new MenuItem("connect between");
		is.addActionListener(this);
		repaint.addActionListener(this);
		shortpass.addActionListener(this);
		Tsp.addActionListener(this);
		rn.addActionListener(this);
		re.addActionListener(this);

		con.addActionListener(this);

		function.add(is);
		function.add(repaint);
		function.add(shortpass);
		function.add(Tsp);
		function.add(rn);
		function.add(re);

		function.add(con);

		menuBar.add(function);
		this.setMenuBar(menuBar);
		this.setVisible(true);
		//this.paint(getGraphics());
		this.addMouseListener(this);
	}

	@Override
	public void paint(Graphics f) {
		// System.out.println("9999999");
		 
		
	   
		
		
		BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bufferedImage.createGraphics();

		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 1000, 1000);

		if (this.gg != null) {

			Collection<node_data> nodes = this.gg.getV();
			Iterator I = nodes.iterator();
			while (I.hasNext()) {
				node_data n = new nodedata((nodedata) I.next());
				int x = (int) scale(n.getLocation().x(), this.gg.getMinx(), this.gg.getMaxx(), 50,
						this.getWidth() - 50);
				int y = (int) scale(n.getLocation().y(), this.gg.getMiny(), this.gg.getMaxy(), 70,
						this.getHeight() - 70);
				g.setColor(Color.BLACK);
				Point3D p = new Point3D(x, y, 0); // /12/1
				// Point3D p=n.getLocation();
				g.fillOval(x, y, 15, 15);
				// g.fillOval((int)(p.x()), (int)(p.y()+10), 15, 15);
				g.setColor(Color.RED);

				String txt = Integer.toString(n.getKey());
				g.drawString(txt, p.ix(), p.iy() + 1);
				g.setColor(Color.BLACK);

			}

			g.setColor(Color.PINK);
			Collection<node_data> nodess = this.gg.getV();
			Iterator<node_data> II = nodess.iterator();
			// Iterator<node_data> II=this.gg.getV().iterator();

			while (II.hasNext()) {
				// HashMap<Integer,edge_data> current=new
				// HashMap<Integer,edge_data>((HashMap<Integer,edge_data>)I.next());
				node_data B = new nodedata((nodedata) II.next());
				HashMap<Integer, edge_data> edg2 = new HashMap<Integer, edge_data>(
						this.gg.getHashedges().get(B.getKey()));
				Collection<edge_data> edges = edg2.values();
				Iterator<edge_data> j = edges.iterator();
				while (j.hasNext()) {
					edgedata edg = new edgedata((edgedata) j.next());
					int edgsx = (int) scale(edg.getSource().getLocation().x(), this.gg.getMinx(), this.gg.getMaxx(), 50,
							this.getWidth() - 50);
					int edgsy = (int) scale(edg.getSource().getLocation().y(), this.gg.getMiny(), this.gg.getMaxy(), 70,
							this.getHeight() - 70);
					int edgdx = (int) scale(edg.getDes().getLocation().x(), this.gg.getMinx(), this.gg.getMaxx(), 50,
							this.getWidth() - 50);
					int edgdy = (int) scale(edg.getDes().getLocation().y(), this.gg.getMiny(), this.gg.getMaxy(), 70,
							this.getHeight() - 70);
					g.drawLine(edgsx + 5, edgsy + 15, edgdx + 5, edgdy + 15);
					double l = edg.getWeight();
					String m = String.format("%.2f", l);
					String txt = m;
					double x_txt = (Math.abs(edgdx) + Math.abs(edgsx)) / 2;
					double y_txt = placeontheline(edgsx, edgsy, edgdx, edgdy, x_txt);

					g.setColor(Color.BLUE);
					g.drawString(txt, (int) x_txt, (int) y_txt + 18);
					double distance = (edgdx) - (edgsx);
					double xx = edgdx - distance / (10);
					double yy = placeontheline(edgsx, edgsy, edgdx, edgdy, xx);
					// Point3D vector=new
					// Point3D(edg.getDes().getLocation().x()-edg.getSource().getLocation().x(),edg.getDes().getLocation().y()-edg.getSource().getLocation().y(),0);
					Point3D yellow = new Point3D(xx, yy, 0);
					g.setColor(Color.YELLOW);
					g.fillOval(yellow.ix(), yellow.iy() + 9, 12, 12);

					g.setColor(Color.pink);
				}
			}
		}
		//today
		g.setColor(Color.BLACK);
		String a=" ";
		String gameinfo=game.toString();
		System.out.println(gameinfo);
		JSONObject line;
		try {
			line = new JSONObject(gameinfo);
			JSONObject ttt = line.getJSONObject("GameServer");
			a=ttt.toString();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		g.drawString(a, this.getWidth()-550, this.getHeight()-180);
		//today
		

		if (this.fruits != null) {
			fruits.clear();

		}
		else {
			fruits=new LinkedList<Fruits>();
		}

		Fruits temp=new Fruits();
		fruits=temp.initf(game.getFruits().toString(),this.gg);



		Iterator<Fruits> I = fruits.iterator();

		while (I.hasNext()) {
			JLayeredPane EverythingButPlayer = new JLayeredPane();
			Fruits current = new Fruits(I.next());
			int xx = (int) scale(current.getPos().x(), this.gg.getMinx(), this.gg.getMaxx(), 50,
					this.getWidth() - 50);
			int yy = (int) scale(current.getPos().y(), this.gg.getMiny(), this.gg.getMaxy(), 70,
					this.getHeight() - 70);
			//System.out.println("xx=" + xx + "yy=" + yy);
			BufferedImage img = null;
			if (current.getType() == 1) {
				try {
					img = ImageIO.read(new File("apple.png"));
					Playingthegame.kmlstring.Place_Mark("food_apple", current.getPos().toString());
				} catch (IOException e) {
					System.out.println("cant find");
				}
				// Graphics d = img.getGraphics();
				Image newimg = img.getScaledInstance(30, 30, 30);
				g.drawImage(newimg, xx, yy, EverythingButPlayer);

			}
			if (current.getType() == -1) {
				try {
					img = ImageIO.read(new File("donat.jpg"));
					Playingthegame.kmlstring.Place_Mark("food_donat", current.getPos().toString());
				} catch (IOException e) {
					System.out.println("cant find");
				}
				// Graphics d = img.getGraphics();
				Image newimg = img.getScaledInstance(30, 30, 30);
				g.drawImage(newimg, xx, yy, EverythingButPlayer);

			}
		}


		if (this.robots != null) {
			robots.clear();

		}
		else {
			robots=new LinkedList<Robot>();
		}

		Robot temp2=new Robot();
		robots=temp2.initr(game.getRobots().toString());
		//System.out.println("where the robot is="+game.getRobots().toString());


		Iterator<Robot> w = robots.iterator();
		Robot r = null;
		while (w.hasNext()) {
			r = (w.next());

			Point3D draw=r.getPos();

			int xx = (int) scale(draw.x(), this.gg.getMinx(), this.gg.getMaxx(), 50, this.getWidth() - 50);
			int yy = (int) scale(draw.y(), this.gg.getMiny(), this.gg.getMaxy(), 70, this.getHeight() - 70);



			Point3D point = new Point3D(xx, yy, 0);
			g.setColor(Color.green);
			//g.drawString("&&&&&&&&&&", (int) xx, (int) yy);
			//
			BufferedImage img = null;
			JLayeredPane EverythingButPlayer = new JLayeredPane();

			try {
				img = ImageIO.read(new File("robot.jpg"));
			} catch (IOException e) {
				System.out.println("cant find");
			}
			// Graphics d = img.getGraphics();
			Image newimg = img.getScaledInstance(30, 30, 30);
			Playingthegame.kmlstring.Place_Mark("robot",r.getPos().toString());
			g.drawImage(newimg, (int) xx, (int) yy, EverythingButPlayer);
			//
			this.setVisible(true);

		}
		Graphics2D g2dComponent = (Graphics2D) f;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);
		this.setVisible(true);


	}




	static long start = System.currentTimeMillis();

	//	public static void main(String[] args) {
	//		// TODO Auto-generated method stub
	//
	//		System.out.println("choose tour game");
	//		Scanner choosenumgame=new Scanner(System.in);
	//		int numofgame= choosenumgame.nextInt();
	//		game_service game=Game_Server.getServer(numofgame);
	//		System.out.println(game.toString());
	//		MyGameGUI mygame=new MyGameGUI(game);
	//		mygame.gg=new DGraph();
	//		String g=game.getGraph();
	//		mygame.gg.init (g);
	//
	//		String gameinfo=game.toString();
	//		System.out.println("1 for automatic, 0 to yourself");
	//		Scanner choosegame=new Scanner(System.in);
	//		int choose = choosegame.nextInt();
	//
	//		if(choose ==1) {
	//			timethread a=new timethread(mygame,game,mygame.robots);
	//			a.start();
	//			mygame.automatic();
	//
	//		}
	//		else
	//		if(choose ==0) {
	//			timethread a=new timethread(mygame,game,mygame.robots);
	//			a.start();
	//			mygame.gamemanual=true;
	//			mygame.manual();
	//
	//		}
	//
	//
	//
	//	}
	
	/**
	 * This function represents the manual game.
	 * 
	 * 
	 */
	


	public void manual(game_service game) {
		// TODO Auto-generated method stub
		
		this.setVisible(true);
		Fruits ff=new Fruits();
		this.fruits=(LinkedList<Fruits>) ff.initf(game.getFruits().toString(),gg);
		this.gg.init(game.getGraph().toString());
		this.repaint();
		
		
		


	}

	//	public  void automatic() {
	//		// TODO Auto-generated method stub
	//		algo cal=new algo();
	//		Fruits ff=new Fruits();
	//
	//		//	System.out.println(game.getFruits().toString());
	//		fruits=(LinkedList<Fruits>) ff.initf(game.getFruits().toString(),gg);
	//
	//		Iterator w=fruits.iterator();
	//		while(w.hasNext()) {
	//			Fruits c=(Fruits) w.next();
	//			//.out.println("type of fruit="+c.getType());
	//		}
	//
	//
	//		int numrobot=cal.numrobot(game.toString());
	//		int count=0;
	//		while(count!=numrobot) {
	//			this.putRobot(game , count);
	//			count++;
	//		}
	//		Robot y=new Robot();
	//		this.robots= y.initr(game.getRobots().toString());
	//		this.setVisible(true);
	//		game.startGame();
	//
	//		while(game.isRunning()) {
	//
	//			cal.moveRobots(game, gg);
	//			
	//		}
	//
	//
	//
	//		//System.out.println(game.toString());
	//		System.exit(0);
	//
	//
	//	}


	public static LinkedList<Fruits> getFruits() {
		return fruits;
	}

	//	private  void putRobot(game_service game, int count) {
	//		Point3D p = new Point3D(0,0,0);
	//
	//		game.addRobot(count);
	//
	//		Robot r = new Robot(count,p, 1,0,0,0);
	//		this.robots.add(r);
	//
	//	}


	private static int nextNode2(DGraph g, int src,LinkedList<Fruits> fruits) {
		//to do int(tsp) to each fruit 
		// ans then to to tsp that returns olis and to go by it
		algo cal=new algo();
		double min=1000;
		Point3D place=null;
		double xscale= scale(g.getHashnodes().get(src).getLocation().x(),g.getMinx(),g.getMaxx(),50,950);
		double yscale= scale(g.getHashnodes().get(src).getLocation().y(),g.getMiny(),g.getMaxy(),70,930);
		Point3D p=new Point3D(xscale,yscale,0);
		Iterator<Fruits> I=fruits.iterator();
		while(I.hasNext()) {
			Fruits f=(Fruits) I.next();
			if(cal.distance(p,f.getPos())<min) {
				place=new Point3D(f.getPos());
				min=cal.distance(p,f.getPos());
			}
		}
		Fruits temp=new Fruits();
		edgedata edgefruit= temp.wherefruit(place);
		//		int[] answer=new int [2];
		//		answer[0]=edgefruit.getSrc();
		//		answer[1]=edgefruit.getDest();
		return edgefruit.getDest();




	}


	@Override
	public void graphUpdated() {
		// TODO Auto-generated method stub

	}

	/**
	 * This function is a function that helps our "paint" function,
	 * to now where the text need to be, where the yellow dots.
	 * @param double x1 - x of first point
	 * @param double y1 -y of first point
	 * @param double x2 - x of second point
	 * @param double y2- y of second point
	 * @param double x0 -the value for calculating f(x0)
	 * @return the f(x)
	 * 
	 * 
	 */

	public static double placeontheline(double x1, double y1, double x2, double y2, double x0) { // return the f(x0)
		double m = (y2 - y1) / (x2 - x1);
		return m * (x0 - x2) + y2;

	}
	public static boolean two =false;
	public static Point3D src=null;
	public static int count=0;
	
	/**
	 * This function helps us play the manual game,
	 * The function is using the "addrobotbyclick" function,
	 * and after placing the robot the game is starting and the function is 
	 * using the "movemanual" function to play the game 
	 * @param MouseEvent e- the mouse click that the player had clicked.
	 * 
	 * 
	 */

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(gamemanual == true) {
			

			algo cal2=new algo();
			int numofrobot=cal2.numrobot(game.toString());
			//System.out.println("numofrobot"+numofrobot);
			algo cal=new algo(this.gg,this.fruits,this.robots,this.game,numofrobot);


			if (insert==true) {   //e.getClickCount() == 1
				System.out.println("i clickes="+e.getX()+","+e.getY());
				if(count!=numofrobot){

					//System.out.println("add robot by click");
					boolean added=cal.addrobotbyclick(e,this.getWidth(),this.getHeight(),count);
					Robot y=new Robot();
					this.robots= y.initr(game.getRobots().toString());
					System.out.println(added);
					if(added==true)
						count++;

				}
				else {
					insert=false;
					game.startGame();
					timethread a=new timethread(this,game,this.getRobots());
					a.start();
					
					
					
					
					move=true;
				}



			}


			int x=e.getX();
			int y=e.getY();

			if (move==true ) {
				Robot temp=new Robot();
				this.robots=temp.initr(game.getRobots().toString());

				src=new Point3D(x,y,0);
				System.out.println("i clickes="+src.x()+","+src.y());
				cal.movemanual(this.getWidth(),this.getHeight(), src);
				Iterator<Robot> yy=robots.iterator();
				System.out.println("the robot in my linkedlist=");
				while(yy.hasNext()) {
					Robot temp2=yy.next();
					System.out.println(temp2.getId());
				}



			}


		}
	}






	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		while(this.game.isRunning()) {
//			try {
//				timethread.sleep(100);
//				
//				this.repaint();
//				this.game.move();
//				
//				Robot y=new Robot();
//				this.robots=y.initr(game.getRobots().toString());
//			} 
//			catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//
//			//System.out.println("i draw");
//		}
//	}








}
