package neighbour;
import traveller.*;

public interface INeighbour {
	public void placeTraveller(Traveller t);
	public void removeTraveller(Traveller t);
	public void addNeighbour(INeighbour n);
	public void removeNeighbour(INeighbour n);
}
