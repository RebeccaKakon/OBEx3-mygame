package dataStructure;

import java.util.LinkedList;

import Server.game_service;
import utils.Point3D;

/**
 * This interface represents the fruit.
 * 
 */

public interface Fruit {
	
	/**
	 * This function returns the edge where the fruit is.
	 * 
	 */
	   public edgedata wherefruit(Point3D pos,game_service game, LinkedList<Fruits> fruits,DGraph g );

	   
	   /**
		 * init all the fruits to a LinkedList
		 * 
		 */
	   
	   public LinkedList <Fruits> initf (String string,DGraph g);

	   
	}


