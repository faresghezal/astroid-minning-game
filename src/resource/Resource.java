package resource;
import javax.swing.JLabel;

import neighbour.*;
import views.InventoryItemView;


public abstract class Resource {
	
	protected String type;
	protected JLabel L;
	
	public abstract void exposed(Asteroid a);
	
	public String getType()
	{
		return type;
	}
	public String toString()
    {
        return  type ;
    }
	public void setView(JLabel l) {
		L = l;
		return;
	}
	
	public JLabel getView() {
		return L;
	}
}