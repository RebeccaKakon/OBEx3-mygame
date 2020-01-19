package gameClient;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import dataStructure.GraphListener;

public class tryjframewindow extends JFrame implements ActionListener, MouseListener, GraphListener {

	public int getNumofgame() {
		return numofgame;
	}

	private int numofgame;
	private int howtoplay=2;


	public tryjframewindow() {
		init();
	}

	public void init () {

		this.setSize(500, 700);
		JFrame newFrame = new JFrame();
		ImageIcon us = new ImageIcon("C:/Users/dalia/Desktop/mazeofwazepicture.jpg");
		newFrame .setIconImage(us.getImage());
		newFrame .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame .setTitle("maze of waze by the amazed shahar&rivka");


		MenuBar menuBar = new MenuBar();
		Menu choose = new Menu("choose a game");
		Menu choose2 = new Menu("choose how to play");
		menuBar.add(choose);
		menuBar.add(choose2);

		MenuItem a0 = new MenuItem("0");
		a0.addActionListener(this);
		MenuItem a1 = new MenuItem("1");
		a1.addActionListener(this);
		MenuItem a2 = new MenuItem("2");
		a2.addActionListener(this);
		MenuItem a3 = new MenuItem("3");
		a3.addActionListener(this);
		MenuItem a4 = new MenuItem("4");
		a4.addActionListener(this);
		MenuItem a5 = new MenuItem("5");
		a5.addActionListener(this);
		MenuItem a6 = new MenuItem("6");
		a6.addActionListener(this);
		MenuItem a7 = new MenuItem("7");
		a7.addActionListener(this);
		MenuItem a8 = new MenuItem("8");
		a8.addActionListener(this);
		MenuItem a9 = new MenuItem("9");
		a9.addActionListener(this);
		MenuItem a10 = new MenuItem("10");
		a10.addActionListener(this);
		MenuItem a11 = new MenuItem("11");
		a11.addActionListener(this);
		MenuItem a12= new MenuItem("12");
		a12.addActionListener(this);
		MenuItem a13 = new MenuItem("13");
		a13.addActionListener(this);
		MenuItem a14 = new MenuItem("14");
		a14.addActionListener(this);
		MenuItem a15 = new MenuItem("15");
		a15.addActionListener(this);
		MenuItem a16 = new MenuItem("16");
		a16.addActionListener(this);
		MenuItem a17 = new MenuItem("17");
		a17.addActionListener(this);
		MenuItem a18 = new MenuItem("18");
		a18.addActionListener(this);
		MenuItem a19 = new MenuItem("19");
		a19.addActionListener(this);
		MenuItem a20 = new MenuItem("20");
		a20.addActionListener(this);
		MenuItem a21 = new MenuItem("21");
		a21.addActionListener(this);
		MenuItem a22 = new MenuItem("22");
		a22.addActionListener(this);
		MenuItem a23 = new MenuItem("23");
		a23.addActionListener(this);

		MenuItem automaty= new MenuItem("automatic");
		automaty.addActionListener(this);
		MenuItem manual= new MenuItem("manual");
		manual.addActionListener(this);

		choose2.add(automaty);
		choose2.add(manual);
		choose.add(a0);
		choose.add(a1);
		choose.add(a2);
		choose.add(a3);
		choose.add(a4);
		choose.add(a5);
		choose.add(a6);
		choose.add(a7);
		choose.add(a8);
		choose.add(a9);
		choose.add(a10);
		choose.add(a11);
		choose.add(a12);
		choose.add(a13);
		choose.add(a14);
		choose.add(a15);
		choose.add(a16);
		choose.add(a17);
		choose.add(a18);
		choose.add(a19);
		choose.add(a20);
		choose.add(a21);
		choose.add(a22);
		choose.add(a23);






		this.setMenuBar(menuBar);
		this.setVisible(true);


	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tryjframewindow t= new tryjframewindow();

	}

	@Override
	public void graphUpdated() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String action = e.getActionCommand();
		System.out.println(action);
		if(action.equals("automatic") || action.equals("manual")) {
			if(action.equals("automatic")) {
				this.howtoplay=1;
			}
			if(action.equals("manual")) {
				this.howtoplay=0;
			}

		}
		else {
			int numofgame=Integer.valueOf(action);
			if(numofgame==0) {
				this.numofgame=0;
			}
			if(numofgame==1) {
				this.numofgame=1;
			}
			if(numofgame==2) {
				this.numofgame=2;
			}
			if(numofgame==3) {
				this.numofgame=3;
			}
			if(numofgame==4) {
				this.numofgame=4;
			}
			if(numofgame==5) {
				this.numofgame=5;
			}
			if(numofgame==6) {
				this.numofgame=6;
			}
			if(numofgame==7) {
				this.numofgame=7;
			}

			if(numofgame==8) {
				this.numofgame=8;
			}
			if(numofgame==9) {
				this.numofgame=9;
			}
			if(numofgame==10) {
				this.numofgame=10;
			}
			if(numofgame==11) {
				this.numofgame=11;
			}
			if(numofgame==12) {
				this.numofgame=12;
			}
			if(numofgame==13) {
				this.numofgame=13;
			}
			if(numofgame==14) {
				this.numofgame=14;
			}
			if(numofgame==15) {
				this.numofgame=15;
			}


			if(numofgame==16) {
				this.numofgame=16;
			}
			if(numofgame==17) {
				this.numofgame=17;
			}
			if(numofgame==18) {
				this.numofgame=18;
			}
			if(numofgame==19) {
				this.numofgame=19;
			}
			if(numofgame==20) {
				this.numofgame=20;
			}
			if(numofgame==21) {
				this.numofgame=21;
			}
			if(numofgame==22) {
				this.numofgame=22;
			}
			if(numofgame==23) {
				this.numofgame=23;
			}
		}
		//		}


	}

	public int getHowtoplay() {
		return howtoplay;
	}

}
