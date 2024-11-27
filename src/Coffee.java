import javax.swing.JOptionPane;

public class Coffee extends Beverage 
{
	private boolean milk; 
    
	public Coffee(String size,int amount,BeverageOrderForm lbl) 
	{
		
		super(size, amount);
                milk = JOptionPane.showConfirmDialog(lbl, "Would you like milk?","Milk",JOptionPane.YES_NO_OPTION)==0;

        double price;
        price = switch(size)
		{
			case "small" -> 3.5;
			case "Medium" -> 5.0;
			default -> 6.5;
		};
		if(milk) price *= 1.5;
		setPrice(price);

    }

	@Override
	public String toString() {
		if(milk) return super.toString()+" Coffee with milk";
		else return super.toString()+" Coffee";
	}
}