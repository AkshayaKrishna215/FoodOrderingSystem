import javax.swing.JOptionPane;

public class Water extends Beverage {
	private boolean iced;

	public Water(String size,int amount,BeverageOrderForm lbl) {
		super(size,amount);
		iced = JOptionPane.showConfirmDialog(lbl, "Would you like ice?","Ice",JOptionPane.YES_NO_OPTION)==0;
		
		double price;
		price = switch(size){
			case "small" -> 1.0;
			case "Medium" ->2.0;
			default -> 3.0;
		};
		if(iced) price *= 1.25; 
		setPrice(price);
	}

    @Override
	public String toString() {
		if(iced) return super.toString()+"Water with ice";
		else return super.toString() + "Water";
	}
}