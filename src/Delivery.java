import java.util.InputMismatchException;

/*
 * Waleed Khalid Swaileh
 * 1160060
 * Section 6
 */
public class Delivery extends PizzaOrder {

	private double tipRate;
	
	public Delivery() {
	}

	public Delivery(String customerName, int pizzaSize, int numberOfToppings, double toppingPrice, double tipRate) throws InputMismatchException{
		super(customerName, pizzaSize, numberOfToppings, toppingPrice);
		setTipRate(tipRate);
	}

	public double getTipRate() {
		return tipRate;
	}

	public void setTipRate(double tipRate) throws InputMismatchException {
		if (tipRate >= 0)
			this.tipRate = tipRate;
		else
			throw new InputMismatchException("Tip Rate Must Be Positive!");
	}

	@Override
	public String toString() {
		return "Delivery [tipRate=" + tipRate + ", getCustomerName()=" + getCustomerName() + ", getDateOrdered()="
				+ getDateOrdered() + ", getPizzaSize()=" + getPizzaSize() + ", getPizzaSizeString()="
				+ getPizzaSizeString() + ", getNumberOfToppings()=" + getNumberOfToppings() + ", getToppingPrice()="
				+ getToppingPrice() + ", calculateOrderPrice()=" + calculateOrderPrice() + "]";
	}

	@Override
	public void printOrderReport() {
		System.out.println("Customer Name : " + getCustomerName() + "\nDate Ordered : " + getDateOrdered() + "\n"
				+ "Pizza Size : " + getPizzaSizeString() + "\nNumber OF Toppings : " + getNumberOfToppings()
				+ "\nToppings Price : " + getToppingPrice() + "\nOrder Price : " + calculateOrderPrice()
				+ " Tip Rate : " + getTipRate());
	}

	@Override
	public double calculateOrderPrice() {
		super.setOrderPrice((super.calculateOrderPrice() * (getTipRate() / 100)) + super.calculateOrderPrice());
		return super.getOrderPrice();
	}

}
