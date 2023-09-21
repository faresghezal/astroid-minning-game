package traveller;
import java.util.*;
import neighbour.*;
import game.*;

public class Traveller {
	protected Asteroid currentAsteroid;
	protected Game game;
	
	public Traveller(){
		currentAsteroid = new Asteroid();
		game = new Game(); 
	}
	public void drill() { // R20, R41, R31, traveller drills 
		this.currentAsteroid.decreaseDepth();
		System.out.println("The remaining rock depth is " + currentAsteroid.getDepth());
	}
	public void hide(Asteroid a) { // R29, R32, R45, traveller hides
		System.out.println("hide(a)");
		currentAsteroid.setHollow(false);
		System.out.println("Traveller has been hidden");
	}
	public void teleport(Gate g) { // traveller teleports
		g.underUse(this);
	}
	public void setAsteroid(Asteroid a) { //R05
		if (a != null) {
			this.currentAsteroid=a;
			System.out.println("Asteroid set");
		}
	}
	public Asteroid getAsteroid() {
		//System.out.println("getAsteroid()");
		return (this.currentAsteroid);
	}
	
	public void setGame(Game g){
		game = g;
	}
	
	public void underExplosion(){System.out.println("Traveller under explosion");} // R34, R35,  traveller is under exlosion
	public void die() {System.out.println("Travelled dies");} // traveller dies
}
