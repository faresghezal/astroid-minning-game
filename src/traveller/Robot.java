package traveller;

import java.util.*;
import neighbour.*;
import game.*;

public class Robot extends Traveller{
    //for when asteroid explode
    @Override
    public void underExplosion(){
    	//robot will travel to the first neighbour
        this.travel();
    }
    
    //robot dies
    @Override
	public void die() {
    	currentAsteroid.removeTraveller(this);
        super.game.removeRobot(this);
        System.out.println("Robot died");
    }
    public void travel(){ //Requirement R40, R39
        int i = 0;
        Asteroid a2 = (Asteroid) currentAsteroid.getNeighbour(i);
        currentAsteroid.removeTraveller(this);
        a2.placeTraveller(this);
        System.out.println("Robot travelled");
    }
}

