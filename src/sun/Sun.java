package sun;
import java.util.*;

import neighbour.*;


public class Sun {
	private List<Asteroid> asteroids;
	public Sun() {
		asteroids = new ArrayList<Asteroid>();
	}
	public void sunstorm()  //R28
	{
		for (Asteroid a: asteroids)
			a.underStorm();
		System.out.println("Sunstorm occured");
	}
	
	public void addAsteroids(List<Asteroid> toAdd)
	{
		for (Asteroid a: toAdd) {
			asteroids.add(a);
		}
		System.out.println("Asteroids added");
	}
	
	public List<Asteroid> getAsteroids()
	{
		return asteroids;
	}
}
