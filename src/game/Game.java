package game;

import java.util.ArrayList;
import java.util.Scanner;

import traveller.Robot;
import sun.Sun;
import traveller.Settler;
import neighbour.Asteroid;
import resource.*;

public class Game {
	private ArrayList<Robot> robots;
	private ArrayList<Settler> settlers;
	private Sun sun;

	// constructor
	public Game() {
		robots =new ArrayList<Robot>();
		settlers = new ArrayList<Settler>();
	}

	public ArrayList<Robot> getRobots() {
		return this.robots;
    }
	public void addRobot(Robot r) {
		robots.add(r);
	}
	public void removeRobot(Robot r) {
		robots.remove(r);
	}

	public ArrayList<Settler> getSettlers() {
		return this.settlers;
    }
	public void addSettler(Settler s) {
		settlers.add(s);
	}
	public void removeSettler(Settler s) {
		settlers.remove(s);
	}
	
	public Sun getSun() {
		return sun;
	}
	
	/*Method startGame() adds asteroids and positions them on the playing field.
	  Then fills each asteroid with a resource. Settlers are positioned on 
	  arbitrary asteroids by method addTraveller()*/
	public void startGame() {
		//adding asteroids
		System.out.println("The game has started!");
		this.sun = new Sun();
		
	}

	//Requirement R58
	public void winGame() {
		int ironCount = 0;
    	int waterCount = 0;
    	int uraniumCount = 0;
    	int carbonCount = 0;
    	for (Settler s: settlers)
	    	for ( Resource r : s.getResources())
	    	{
	    		if (r instanceof Iron) ironCount++;
	    		if (r instanceof Water) waterCount++;
	    		if (r instanceof Uranium) uraniumCount++;
	    		if (r instanceof Carbon) carbonCount++;
	    	}
		if (ironCount == 3 && waterCount == 3 && uraniumCount == 3 && carbonCount == 3)
			System.out.println("Game won");
		else 
			System.out.println("Game not won yet\r\n");
	}

	//Requirement R56
	public void loseGame() {
		if (settlers.size() == 0) {
			System.out.println("Game lost");
			System.exit(0);
		}
			
		else 
			System.out.println("Game not lost yet");
	}
}