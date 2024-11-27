import javax.swing.JOptionPane;

public class Tea extends Beverage {
	private boolean sugar;

	public Tea(String size,int amount,BeverageOrderForm lbl) {
		super(size,amount);
		     sugar = JOptionPane.showConfirmDialog(lbl, "Would you like sugar?","Sugar",JOptionPane.YES_NO_OPTION)==0;
		
		double price;
        price = switch (size) 
		{
			case "small"->3.0;
			case "Medium"->4.0;
			default ->5.0;
		};
			
		if(sugar) price*=1.25;
		setPrice(price);
	}
	
	@Override
	public String toString() {
		if(sugar) return super.toString()+"Tea with sugar";
		else return super.toString() + "Tea";
	}
}