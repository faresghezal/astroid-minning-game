package controller;

import java.nio.file.FileSystemNotFoundException;
import java.util.*;

import javax.swing.JFrame;

import game.Game;
import neighbour.*;
import resource.*;
import sun.*;
import traveller.*;
import views.AsteroidView;
import views.InventoryItemView;
import views.MainWindow;
import views.SettlerView;

public class Controller {

	private MainWindow mainFrame;
	private Game game;
	private Sun sun;
	private List<Asteroid> asts;
	private List<Settler> settlers;
	private Iterator<Settler> it;
	private Gate g1, g2;
	private Scanner in;
	int input;
	private Settler currentSettler;
	private Asteroid currentAsteroid;
	public int[] targetAsteroid = new int[] {-1, -1};
	
	public List<Asteroid> getAsts()
	{
		return asts;
	}
	
	public List<Settler> getSettlers()
	{
		return settlers;
	}
	//updates the game according to the user commands
	public Controller(MainWindow main) {
		mainFrame = main;
		game = new Game();
		in = new Scanner(System.in);
		System.out.println("Welcome to the game! Choose one integer");
		System.out.println("1. Create a new game");
		System.out.println("2. Load a game");
		System.out.println("3. Exit the game");
		input = Integer.parseInt(in.nextLine());
		while (!(input == 1 || input == 2)) {
			if (input == 3)
				System.out.println("Exit from the application");
			System.exit(0);
			System.out.println("Invalid option! Try again!");
			input = Integer.parseInt(in.nextLine());
		}
		game.startGame();
		sun = game.getSun();
		settlers = new ArrayList<Settler>();
		asts = new ArrayList<Asteroid>();
		
		if (input == 1) {
		System.out.print("Create How many Asteroids? ");
		input = Integer.parseInt(in.nextLine());
		System.out.println(input + " Asteroids created");
		for (int i = 0; i < input; i++) {
			asts.add(new Asteroid()); //adds a new asteroid to the list
			System.out.println("Coordinates: " + i + ", " + i);
			asts.get(i).setView(mainFrame.createAsteroidView(i, i)); //sets the view
			System.out.print("Set Resource ");
			switch (in.nextLine()) {
			case "Iron":
				Iron ir = new Iron();
				ir.setView(mainFrame.label_iron); // changes the view
				asts.get(i).addResource(ir); // add the resource to the asteroid
				break;
			case "Water":
				Water w = new Water();
				w.setView(mainFrame.label_water);
				asts.get(i).addResource(w);
				break;
			case "Uranium":
				Uranium u = new Uranium();
				u.setView(mainFrame.label_uranium);
				asts.get(i).addResource(u);
				break;
			case "Carbon":
				Carbon c = new Carbon();
				c.setView(mainFrame.label_carbon);
				asts.get(i).addResource(c);
				break;
			default:
				System.out.println("Resource could not be added!");
			}
			System.out.print("Set Depth ");
			asts.get(i).setDepth(Integer.parseInt(in.nextLine())); // set the asteroid depth according to the user command
		}
		
		sun.addAsteroids(asts);

		System.out.println("Set asteroid neighbours");
		for (int i = 0; i < asts.size(); i++) {
			System.out.println("Finish with -1. Add neighbours to Asteroid " + i);
			input = Integer.parseInt(in.nextLine());
			// Adds a new neighbour to the asteroid i 
			while (input != -1) {
				if (input < asts.size())
					asts.get(i).addNeighbour(asts.get(input));
				else
					System.out.println("Invalid index");
				input = Integer.parseInt(in.nextLine());
			}
			System.out.println("Neighbours of " + i);
			for (int j = 0; j < asts.get(i).getNeighbours().size(); j++)
				System.out.println(asts.indexOf(asts.get(i).getNeighbours().get(j)) + " "); // traverse the asteroid list and prints all the neighbpurs
		}

		System.out.print("Create Settlers ");
		input = Integer.parseInt(in.nextLine());
		
		for (int i = 0; i < input; i++) {
			settlers.add(new Settler()); // Creates the number of settlers according to the user input
			game.addSettler(settlers.get(i));
			asts.get(0).placeTraveller(settlers.get(i)); // Sets the settlers to the appropriate asteroid
			settlers.get(i).setView(mainFrame.createSettlerView(i+1)); 
		}
		}
		
		
		
		
		if (input == 2) {
			for (int i = 0; i < 3; i++) {
				asts.add(new Asteroid());
				asts.get(i).setView(mainFrame.createAsteroidView(i, i));
				switch (i) {
				case 0:
					Iron ir = new Iron();
					ir.setView(mainFrame.label_iron);
					asts.get(i).addResource(ir);
					break;
				case 1:
					Water w = new Water();
					w.setView(mainFrame.label_water);
					asts.get(i).addResource(w);
					break;
				case 2:
					Uranium u = new Uranium();
					u.setView(mainFrame.label_uranium);
					asts.get(i).addResource(u);
					break;
				case 3:
					Carbon c = new Carbon();
					c.setView(mainFrame.label_carbon);
					asts.get(i).addResource(c);
					break;
				}
				asts.get(i).setDepth(2);
			}
			
			sun.addAsteroids(asts);
			for (int i = 0; i < asts.size(); i++) {
				if (i != 2)
					asts.get(i).addNeighbour(asts.get(i+1));
				if (i == 0)
					asts.get(i).addNeighbour(asts.get(i+2));
			}

			for (int i = 0; i < 2; i++) {
				settlers.add(new Settler());
				game.addSettler(settlers.get(i));
				asts.get(0).placeTraveller(settlers.get(i));
				settlers.get(i).setView(mainFrame.createSettlerView(i+1));
			}
		}

		System.out.println(input + " Settlers created");
		
		System.out.println("All set!");
		it = settlers.iterator();
		
		g1 = new Gate(); g2 = new Gate();
		g1.setPair(g2);
		g2.setPair(g1);
		g1.addNeighbour(asts.get(0));
		g2.addNeighbour(asts.get(1));
		
	}
	//writes the current settler's index to the console and sets the current Asteroid
	public void gameTurns() {
		currentSettler = it.next();
		currentSettler.setCurrent(true);
		System.out.println("It is the turn of settler " + settlers.indexOf(currentSettler));
		currentAsteroid = currentSettler.getAsteroid();
	}
	
	//updates the game according to the user commands
	public void takeAction(String action) {
		gameTurns();
		switch(action) {
		  case "travel" :
			  if (targetAsteroid[0] == -1 || targetAsteroid[1] == -1)
					  System.out.println("First choose the target asteroid and try again!");
			  else
			  {
				  if (currentAsteroid.getNeighbours().contains(asts.get(targetAsteroid[0])))
					  currentSettler.travel(asts.get(input)); // Settlers travels to the target asteroid
					  
				  else if (currentAsteroid.equals(asts.get(targetAsteroid[0])))
					  System.out.println("This is the current asteroid!");
				  else
					  System.out.println("Cannot travel because not a neighbour!");
				  asts.get(targetAsteroid[0]).untarget();
				  targetAsteroid = new int[]{-1, -1};  
			  }
			  break;
		  
		  case "drill" : 
			  currentSettler.drill();
			  break;
		
		  case "mine" :
			  currentSettler.mine();
			  break;
		  
		  case "pick up" :
			  currentSettler.pickUpResource();
			  break;
		  
		  case "drop" :
			  input = Integer.parseInt(in.nextLine()); //index of the resource to drop
			  currentSettler.removeResource(currentSettler.getResources().get(input)); //possible failures are called in removeResource()
			  break;
		  
		  case "settler hide" :
			  currentSettler.hide(currentAsteroid);
			  break;
		  
		  case "build robot" :
			  currentSettler.createRobot();
			  break;
		  
		  case "build gate" :
			  currentSettler.createGate();
			  break;
		  
		  case "deploy gate" :
			  currentSettler.deployGate();
			  break;
		  
		  case "robot teleport" :
			  Robot r1 = new Robot();
			  game.addRobot(r1);
			  asts.get(0).placeTraveller(r1);
			  r1.teleport(g1);
			  break;
		  
		  case "settler teleport" :
			  currentSettler.setAsteroid(asts.get(0));
			  currentSettler.teleport(g1);
			  break;
		  case "check win" :
			  game.winGame();
			  break;
		  case "check lose" : 
			  game.loseGame();
			  break;
		  case "remove settler" :
			  game.removeSettler(currentSettler);
			  break;
		  case "sunstorm" :
			  sun.sunstorm();
			  break;
		  case "view inventory":
			  for (Resource res : currentSettler.getResources())
			  { if (res != null)
				  System.out.println(res.getType()); }
			  break;
		  
		  case "uranium explodes" :
			  Robot r = new Robot();
			  game.addRobot(r);
			  currentAsteroid.placeTraveller(r);
			  Uranium u1 = new Uranium();
			  currentAsteroid.removeResource();
			  currentAsteroid.addResource(u1);
			  currentAsteroid.setDepth(1);
			  currentAsteroid.setPerihelion(true);
			  currentSettler.drill();
			  break;
		  
		  case "water evaporates" : 
			  Water w = new Water();
			  currentAsteroid.removeResource();
			  currentAsteroid.addResource(w);
			  currentAsteroid.setDepth(1);
			  currentAsteroid.setPerihelion(true);
			  currentSettler.drill();
			  break;
		  
		  default:
			  System.out.println("The command is invalid!"); 
			  break;
			}  
		if (!it.hasNext()) {
			it = settlers.iterator();
		}
		currentSettler.setCurrent(false);
	}
	
	protected void finalize()
	{
		in.close();
	}
}
