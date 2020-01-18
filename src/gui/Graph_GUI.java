package gui;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
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

import dataStructure.DGraph;
import dataStructure.Fruits;
import dataStructure.GraphListener;
import dataStructure.Robot;
import dataStructure.edge_data;
import dataStructure.edgedata;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.nodedata;

import utils.Point3D;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import algorithms.Graph_Algo;

public class Graph_GUI extends JFrame implements ActionListener, MouseListener, GraphListener {

	private DGraph gg;
	LinkedList<Fruits> fruits;
	List<Robot> robots;
	//private static DecimalFormat df2 = new DecimalFormat("#.##");

	/**
	 * 
	 * @param data  denote some data to be scaled
	 * @param r_min the minimum of the range of your data
	 * @param r_max the maximum of the range of your data
	 * @param t_min the minimum of the range of your desired target scaling
	 * @param t_max the maximum of the range of your desired target scaling
	 * @return
	 */
	private double scale(double data, double r_min, double r_max, // 12/1
			double t_min, double t_max) {

		double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;
		return res;

	} // 12/1

	public Graph_GUI(graph other, LinkedList<Fruits> fruits, List<Robot> robots) {
		this.gg = (DGraph) other;// new DGraph((DGraph)other) ;
		this.fruits = fruits;
		this.robots = robots;
		this.gg.addListener(this); // also for fruits
		initGUI(this.gg, this.fruits, this.robots);
	}
	// public Graph_GUI()
	// {
	// initGUI(this.gg);
	// }

	private void initGUI(graph g, LinkedList<Fruits> fruits, List<Robot> robots) {
		this.gg = (DGraph) g;
		this.fruits = fruits;
		this.robots=robots;
		// this.setBounds(0, 0, 500, 500);
		// gg.addListener(this);

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
		//this.paint(getGraphics());
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

					String txt = Double.toString(edg.getWeight());
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
			System.out.println("size=" + fruits.size());
			Iterator<Fruits> I = fruits.iterator();

			while (I.hasNext()) {
				JLayeredPane EverythingButPlayer = new JLayeredPane();
				Fruits current = new Fruits(I.next());
				int xx = (int) scale(current.getPos().x(), this.gg.getMinx(), this.gg.getMaxx(), 50,
						this.getWidth() - 50);
				int yy = (int) scale(current.getPos().y(), this.gg.getMiny(), this.gg.getMaxy(), 70,
						this.getHeight() - 70);
				System.out.println("xx=" + xx + "yy=" + yy);
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
		}

		if (this.robots != null) {
//			Iterator<String> I = robots.iterator();
//			Object r = null;
//			while (I.hasNext()) {
//				r = I.next();
//
//				JsonParser jp = new JsonParser();
//				// FileReader fr = new FileReader(g);
//				JsonObject a = (JsonObject) jp.parse(r.toString());
//				System.out.println("robor in gui=" + a);
//
//				JsonElement n = a.get("Robot");
//				JsonObject nn = (JsonObject) jp.parse(n.toString());
//
//				JsonElement pos = nn.get("pos");
//				String p = pos.getAsString();
//
//				String[] range = p.split(",");
//				double x = Double.valueOf((range[0]));
//				double y = Double.valueOf(range[1]);
//
//				int xx = (int) scale(x, this.gg.getMinx(), this.gg.getMaxx(), 50, this.getWidth() - 50);
//				int yy = (int) scale(y, this.gg.getMiny(), this.gg.getMaxy(), 70, this.getHeight() - 70);
//
//	
//
//				Point3D point = new Point3D(x, y, 0);
//				g.setColor(Color.green);
//				g.drawString("$$$$$$4$$$$$$$$$$", (int) xx, (int) yy);

			
			Iterator<Robot> I = robots.iterator();
			Robot r = null;
			while (I.hasNext()) {
				r = new Robot(I.next());

				Point3D draw=r.getPos();

				int xx = (int) scale(draw.x(), this.gg.getMinx(), this.gg.getMaxx(), 50, this.getWidth() - 50);
				int yy = (int) scale(draw.y(), this.gg.getMiny(), this.gg.getMaxy(), 70, this.getHeight() - 70);

	

				Point3D point = new Point3D(xx, yy, 0);
				g.setColor(Color.green);
				g.drawString("$$$$$$4$$$$$$$$$$", (int) xx, (int) yy);
				this.setVisible(true);
				
			}

		}
		Graphics2D g2dComponent = (Graphics2D) f;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//
//		Point3D p1 = new Point3D(100, 150, 0);
//		Point3D p2 = new Point3D(250, 360, 0);
//		Point3D p3 = new Point3D(900, 400, 0);
//		Point3D p4 = new Point3D(250, 560, 0);
//		Point3D p5 = new Point3D(800, 100, 0);
//		Point3D p6 = new Point3D(50, 90, 0);
//
//		nodedata a = new nodedata(1, p1, 0, 0);
//		nodedata b = new nodedata(2, p2, 0, 0);
//		nodedata c = new nodedata(3, p3, 0, 0);
//		nodedata d = new nodedata(4, p4, 0, 0);
//		nodedata e = new nodedata(5, p5, 0, 0);
//		nodedata f = new nodedata(6, p6, 0, 0);
//
//		DGraph x = new DGraph();
//
//		//
//		x.addNode(a);
//		x.addNode(b);
//		x.addNode(c);
//		x.addNode(d);
//		x.addNode(e);
//		x.addNode(f);
//
//		x.connect(a.getKey(), b.getKey(), 80);
//		x.connect(b.getKey(), d.getKey(), 10);
//		x.connect(c.getKey(), a.getKey(), 10);
//		x.connect(a.getKey(), e.getKey(), 10);
//		x.connect(d.getKey(), e.getKey(), 90);
//		x.connect(b.getKey(), d.getKey(), 30);
//		x.connect(b.getKey(), a.getKey(), 80);
//
//		x.connect(e.getKey(), b.getKey(), 10);
//		// x.connect(f.getKey(), c.getKey(), 10);
//		// x.connect(d.getKey(), f.getKey(), 40);
//		// x.connect(c.getKey(), d.getKey(), 120);
//		// x.connect(e.getKey(), c.getKey(), 50);
//		// x.connect(c.getKey(), b.getKey(), 5);
//		// x.connect(b.getKey(), d.getKey(), 30);
//
//		Graph_GUI g = new Graph_GUI(x, null, null);
//		// x.addListener(g);
//
//		g.setVisible(true);
//		// Graph_Algo algo=new Graph_Algo(x);
//		// List <Integer>pass=new LinkedList();
//		// System.out.println("enter all the keys you want to pass and then press -1 ");
//		// Scanner scant=new Scanner(System.in);
//		// while(scant.hasNext()) {
//		// int key=scant.nextInt();
//		// if(key!=-1)
//		// pass.add(key);
//		// else
//		// break;
//		// }
//		// System.out.println("***");
//		// Collection tspanswer=algo.TSP(pass);
//		// //tspanswer=algo.TSPifnotconnect(x,pass);
//		// System.out.println("**");
//		// Iterator t=tspanswer.iterator();
//		// while(t.hasNext()) {
//		// nodedata nn=(nodedata) t.next();
//		// System.out.println(nn.getKey());
//		// }
//		//
//		x.connect(c.getKey(), d.getKey(), 120);
//		x.connect(e.getKey(), c.getKey(), 50);
//
//		// x.connect(c.getKey(), b.getKey(), 30);
//		// Graph_Algo test=new Graph_Algo(x);
//
//		// JFrame frame= new JFrame();
//		// frame.setVisible(true);
//		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int src;
		int dest;
		Graph_Algo grafgui = new Graph_Algo();
		grafgui.setG(gg);
		String action = e.getActionCommand();
		if (action.equals("repaint")) {

			this.setResizable(true);
		}

		if (action.equals("load")) {
			grafgui = new Graph_Algo();
			JFileChooser folder = new JFileChooser(FileSystemView.getFileSystemView());
			int s = folder.showOpenDialog(null);
			if (s == JFileChooser.APPROVE_OPTION) {
				grafgui.init(folder.getSelectedFile().getAbsolutePath());
				this.gg = new DGraph(grafgui.getG());
				initGUI(gg, null, null);
			}

		}

		if (action.equals("save")) {

			JFileChooser folder = new JFileChooser(FileSystemView.getFileSystemView());
			int s = folder.showOpenDialog(null);
			if (s == JFileChooser.APPROVE_OPTION) {
				grafgui.save(folder.getSelectedFile().getAbsolutePath());
			}

		}
		if (action.equals("Is connect")) {
			if (grafgui.getG() == null)
				System.out.println("doesnt have a graph");
			else
				System.out.println("Is the graph connect ? " + grafgui.isConnected());
		}

		if (action.equals("short pass")) {
			if (grafgui.getG() == null)
				System.out.println("doesnt have a graph");
			else {
				System.out.println("enter src and dest");
				Scanner scanIn = new Scanner(System.in);
				src = scanIn.nextInt();
				dest = scanIn.nextInt();
				Collection nodes = grafgui.shortestPath(src, dest);
				Iterator I = nodes.iterator();
				while (I.hasNext()) {
					nodedata n = new nodedata((nodedata) I.next());
					// System.out.print(n.getKey());
				}
			}
		}
		if (action.equals("remove node")) {
			if (grafgui.getG() == null)
				System.out.println("doesnt have a graph");
			else {
				System.out.println("enter key of removing");
				Scanner scan = new Scanner(System.in);
				int delete = scan.nextInt();
				grafgui.getG().removeNode(delete);

			}

		}
		if (action.equals("remove edge")) {
			if (grafgui.getG() == null)
				System.out.println("doesnt have a graph");
			else {
				System.out.println("enter src and dest");
				Scanner scanf = new Scanner(System.in);
				src = scanf.nextInt();
				dest = scanf.nextInt();
				grafgui.getG().removeEdge(src, dest);

			}
		}
		if (action.equals("Tsp")) {
			if (grafgui.getG() == null)
				System.out.println("doesnt have a graph");
			else {
				List<Integer> pass = new LinkedList();
				System.out.println("enter all the keys you want to pass and then press -1 ");
				Scanner scant = new Scanner(System.in);
				while (scant.hasNext()) {
					int key = scant.nextInt();
					if (key != -1)
						pass.add(key);
					else
						break;
				}
				grafgui.TSP(pass);

			}

		}
		if (action.equals("connect between")) {
			if (grafgui.getG() == null)
				System.out.println("doesnt have a graph");
			else {
				System.out.println("enter src,dest and weight");
				Scanner scann = new Scanner(System.in);
				src = scann.nextInt();
				dest = scann.nextInt();
				int weight = scann.nextInt();
				grafgui.getG().connect(src, dest, weight);

			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static double placeontheline(double x1, double y1, double x2, double y2, double x0) { // return the f(x0)
		double m = (y2 - y1) / (x2 - x1);
		return m * (x0 - x2) + y2;

	}

	// public static double placeontheline(Point3D p1,Point3D p2,double x0) {
	// //return the f(x0)
	// double m=((p2.y()-p1.y())/(p2.ix()-p1.x()));
	// return m*(x0-p2.x())+p2.y();
	//
	// }

	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// JFrame frame= new JFrame();
	// frame.setTitle("Scanning");
	//
	// // Panel to define the layout. We are using GridBagLayout
	// JPanel mainPanel = new JPanel();
	// mainPanel.setLayout((LayoutManager) new BoxLayout(mainPanel,
	// BoxLayout.Y_AXIS));
	//
	// JPanel headingPanel = new JPanel();
	// JLabel headingLabel = new JLabel(" ");
	// headingPanel.add(headingLabel);
	//
	// // Panel to define the layout. We are using GridBagLayout
	// JPanel panel = new JPanel(new GridBagLayout());
	// // Constraints for the layout
	// GridBagConstraints constr = new GridBagConstraints();
	// constr.insets = new Insets(5, 5, 5, 5);
	// constr.anchor = GridBagConstraints.WEST;
	//
	// // Set the initial grid values to 0,0
	// constr.gridx=0;
	// constr.gridy=0;
	//
	// // Declare the required Labels
	// JLabel userNameLabel = new JLabel("Enter src :");
	// JLabel pwdLabel = new JLabel("Enter dest :");
	//
	//
	// // Declare Text fields
	// JTextField userNameTxt = new JTextField(20);
	// JPasswordField pwdTxt = new JPasswordField(20);
	//
	// panel.add(userNameLabel, constr);
	// constr.gridx=1;
	// panel.add(userNameTxt, constr);
	// constr.gridx=0; constr.gridy=1;
	//
	// panel.add(pwdLabel, constr);
	// constr.gridx=1;
	// panel.add(pwdTxt, constr);
	// constr.gridx=0; constr.gridy=2;
	//
	//
	//
	// constr.gridwidth = 2;
	// constr.anchor = GridBagConstraints.CENTER;
	//
	// // Button with text "Register"
	// JButton button = new JButton("Enter");
	// // add a listener to button
	// button.addActionListener(new ActionListener()
	// {
	// public void actionPerformed(ActionEvent e)
	// {
	// headingLabel.setText("");
	// userNameTxt.setText("");
	// pwdTxt.setText("");
	//
	// }
	// });
	//
	// // Add label and button to panel
	// panel.add(button, constr);
	//
	// mainPanel.add(headingPanel);
	// mainPanel.add(panel);
	//
	// // Add panel to frame
	// frame.add(mainPanel);
	// frame.pack();
	// frame.setSize(400, 400);
	// frame.setLocationRelativeTo(null);
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setVisible(true);
	//
	// }
	@Override
	public void graphUpdated() {

		this.repaint();
	}

}
