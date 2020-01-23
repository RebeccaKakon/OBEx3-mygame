package gameClient;

//import com.sun.java.util.jar.pack.Instruction;

import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.INACTIVE;
import utils.StdDraw;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * This class represents a simple example of using MySQL Data-Base.
 * Use this example for writing solution.
 *
 * @author shahar & rivka
 */
public class SimpleDB {

	public static HashMap<Integer, Integer> scores= new HashMap<Integer, Integer>();
	public static final String jdbcUrl = "jdbc:mysql://db-mysql-ams3-67328-do-user-4468260-0.db.ondigitalocean.com:25060/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
	public static final String jdbcUser = "student";
	public static final String jdbcUserPassword = "OOP2020student";


	/**
	 * Simple main for demonstrating the use of the Data-base
	 *
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		int id1 = 206713141; 
		int level = 0;
		printLog();
		String kml = Playingthegame.kmlstring.toString();
		System.out.println("* KML file example: **");
		System.out.println(kml);
	}


	static int CountGame = 0;
	static int Level = 0;
	static int Grade = 0;
	static int placeInClass = 0;
	static int Move = 0;

	/**
	 *  prints all the games as played by the users (in the database).
	 */

	public static void printLog() throws InterruptedException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection =
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			Statement statement = connection.createStatement();
			String allCustomersQuery = "SELECT * FROM Logs;";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			switch (Playingthegame.numofgame) {
			case 0: {
				Move= 290;
				break;
			}
			case 1: {
				Move=580;
				break;
			}
			case 3: {
				Move=580;
				break;
			}
			case 5: {
				Move=500;
				break;
			}
			case 9: {
				Move=580;
				break;
			}
			case 11: {
				Move=580;
				break;
			}
			case 13: {
				Move=580;
				break;
			}
			case 16: {
				Move=290;
				break;
			}
			case 19: {
				Move=580;
				break;
			}
			case 20: {
				Move=290;
				break;
			}
			case 23: {
				Move=1140;
				break;
			}

			}
			while (resultSet.next()) {

				if (Playingthegame.numofgame == resultSet.getInt("levelID")) {

					int id = resultSet.getInt("UserID");
					if (id == Playingthegame.id) {

						CountGame++;

						Level = resultSet.getInt("levelID");
						int score = resultSet.getInt("score");
						if (Grade<score) {
							Grade = score;
						}


					}
				}

			}
			Class.forName("com.mysql.jdbc.Driver");
			connection =
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			statement = connection.createStatement();
			allCustomersQuery = "SELECT * FROM Logs;";
			resultSet = statement.executeQuery(allCustomersQuery);
			while (resultSet.next()) {
				int score = resultSet.getInt("score");
				int moves = resultSet.getInt("moves");
				int Userid = resultSet.getInt("UserID");
				int levelID = resultSet.getInt("levelID");

				if (Grade < score && moves <=  Move && levelID == Playingthegame.numofgame) {
					if (scores.get(Userid) == null) {
						scores.put(Userid, score);
						placeInClass++;
						System.out.println("Id: " + resultSet.getInt("UserID") + ", Level: " + resultSet.getInt("levelID") + ",Move: " + resultSet.getInt("moves") + ",Time: "
								+ resultSet.getDate("time") + ",Score: " + resultSet.getInt("score"));
					}

				}

			}

			JOptionPane.showMessageDialog(null,"Game number: " + Level + "\n How much times this game is played: " 
					+ CountGame +"\n Place in class:  " +  placeInClass+"\n High value we got:  "  + Grade  );
			
			JOptionPane.showMessageDialog(null, "level= 0 , score= 147"+"\n level= 1 , score= 578"
			+"\nlevel= 3 , score= 650"+ "\nlevel= 5 , score= 672"+"\nlevel=9  , score= 511"+"\nlevel=11  , score= 1106"
			+"\nlevel=16  , score= 246" +"\nlevel=19  , score= 300"	+"\nlevel=20  , score= 200" +"\nlevel=23  , score= 1068");
			
			
			String kml=getKML(Playingthegame.id,Playingthegame.numofgame);
			System.out.println(kml);
			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this function returns the KML string as stored in the database (userID, level);
	 *
	 * @param id
	 * @param level
	 * @return
	 */

	public static String getKML(int id, int level) {
		String ans = null;
		String allCustomersQuery = "SELECT * FROM Users where userID=" + id + ";";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection =
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			if (resultSet != null && resultSet.next()) {
				ans = resultSet.getString("kml_" + level);
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ans;
	}

	public static int allUsers() {
		int ans = 0;
		String allCustomersQuery = "SELECT * FROM Users;";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection =
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			while (resultSet.next()) {
				System.out.println("Id: " + resultSet.getInt("UserID"));
				ans++;
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ans;
	}
}