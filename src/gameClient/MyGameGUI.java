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
import utils.Point3D;

public class MyGameGUI extends JFrame implements ActionListener, MouseListener, GraphListener {

	private DGraph gg;
	LinkedList<Fruits> fruits;
	LinkedList<Robot> robots;
	game_service game;
	algo cal;
	boolean gamemanual;

	/**
	 * 
	 * @param data  denote some data to be scaled
	 * @param r_min the minimum of the range of your data
	 * @param r_max the maximum of the range of your data
	 * @param t_min the minimum of the range of your desired target scaling
	 * @param t_max the maximum of the range of your desired target scaling
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
				} catch (IOException e) {
					System.out.println("cant find");
				}
				// Graphics d = img.getGraphics();
				Image newimg = img.getScaledInstance(30, 30, 30);
				g.drawImage(newimg, xx, yy, EverythingButPlayer);

			}
			if (current.getType() == -1) {
				try {
					img = ImageIO.read(new File("apple.png"));
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
			g.drawImage(newimg, (int) xx, (int) yy, EverythingButPlayer);
			//
			this.setVisible(true);

		}
		Graphics2D g2dComponent = (Graphics2D) f;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);
		this.setVisible(true);


	}




	static long start = System.currentTimeMillis();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("choose tour game");
		Scanner choosenumgame=new Scanner(System.in);
		int numofgame= choosenumgame.nextInt();
		game_service game=Game_Server.getServer(numofgame);
		System.out.println(game.toString());
		MyGameGUI mygame=new MyGameGUI(game);
		mygame.gg=new DGraph();
		String g=game.getGraph();
		mygame.gg.init (g);

		String gameinfo=game.toString();
		System.out.println("1 for automatic, 0 to yourself");
		Scanner choosegame=new Scanner(System.in);
		int choose = choosegame.nextInt();

		if(choose ==1) {
			timethread a=new timethread(mygame,game,mygame.robots);
			a.start();
			mygame.automatic();

		}
		else
		if(choose ==0) {
			timethread a=new timethread(mygame,game,mygame.robots);
			a.start();
			mygame.gamemanual=true;
			mygame.manual();

		}



	}


	private  void manual() {
		// TODO Auto-generated method stub
		Fruits ff=new Fruits();
		fruits=(LinkedList<Fruits>) ff.initf(game.getFruits().toString(),gg);
		this.setVisible(true);
		

	}

	private   void automatic() {
		// TODO Auto-generated method stub
		algo cal=new algo();
		Fruits ff=new Fruits();

		//	System.out.println(game.getFruits().toString());
		fruits=(LinkedList<Fruits>) ff.initf(game.getFruits().toString(),gg);

		Iterator w=fruits.iterator();
		while(w.hasNext()) {
			Fruits c=(Fruits) w.next();
			//.out.println("type of fruit="+c.getType());
		}


		int numrobot=cal.numrobot(game.toString());
		int count=0;
		while(count!=numrobot) {
			this.putRobot(game , count);
			count++;
		}
		Robot y=new Robot();
		this.robots= y.initr(game.getRobots().toString());
		this.setVisible(true);
		game.startGame();

		while(game.isRunning()) {

			cal.moveRobots(game, gg);
			
		}



		//System.out.println(game.toString());
		System.exit(0);


	}


	public LinkedList<Fruits> getFruits() {
		return fruits;
	}

	private  void putRobot(game_service game, int count) {
		Point3D p = new Point3D(0,0,0);

		game.addRobot(count);

		Robot r = new Robot(count,p, 1,0,0,0);
		this.robots.add(r);

	}


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



	public static double placeontheline(double x1, double y1, double x2, double y2, double x0) { // return the f(x0)
		double m = (y2 - y1) / (x2 - x1);
		return m * (x0 - x2) + y2;

	}
	public static boolean two =false;
	public static Point3D src=null;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(gamemanual == true) {
			int count=0;
			algo cal2=new algo();
			int numofrobot=cal2.numrobot(game.toString());
			//System.out.println("numofrobot"+numofrobot);
			algo cal=new algo(this.gg,this.fruits,this.robots,this.game,numofrobot);

			if (e.getClickCount() == 1) {
				System.out.println("i clickes="+e.getX()+","+e.getY());
				if(count!=numofrobot){

					//System.out.println("add robot by click");
					boolean added=cal.addrobotbyclick(e,this.getWidth(),this.getHeight());
					Robot y=new Robot();
					this.robots= y.initr(game.getRobots().toString());
					System.out.println(added);
					if(added==true)
						count++;

				}

			}
			
           
            int x=e.getX();
			int y=e.getY();
			if (e.getClickCount() == 2 ) {
				game.startGame();
				
				
					src=new Point3D(x,y,0);
					System.out.println("i clickes="+src.x()+","+src.y());
					
			
				cal.movemanual(this.getWidth(),this.getHeight(), src);
				

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








}
