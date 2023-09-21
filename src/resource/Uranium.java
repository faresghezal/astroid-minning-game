package resource;
import neighbour.*;

public class Uranium extends Resource{

	public Uranium()  {
		type = "Uranium";
	}
	
	//gets an Asteorid as a parameter and makes it explode, R10, R11, 
	@Override
	public void exposed(Asteroid a)
	{
		System.out.println("Uranium exposed");
		a.explode();	
	}
}
