import java.util.InputMismatchException;

/*
 * Waleed Khalid Swaileh
 * 1160060
 * Section 6
 */
public class Seated extends PizzaOrder {

	private double serviceCharge;

	public Seated() {

	}

	public Seated(String customerName, int pizzaSize, int numberOfToppings, double toppingPrice, double serviceCharge) throws InputMismatchException{
		super(customerName, pizzaSize, numberOfToppings, toppingPrice);
		setServiceCharge(serviceCharge);
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) throws InputMismatchException{
		if (serviceCharge >= 0)
			this.serviceCharge = serviceCharge;
		else
			throw new InputMismatchException("Service Charge Must Be Positive, Set Another Value, Current Value Is Zero");
	}

	@Override
	public String toString() {
		return "Seated [serviceCharge=" + serviceCharge + ", getCustomerName()=" + getCustomerName()
				+ ", getDateOrdered()=" + getDateOrdered() + ", getPizzaSize()=" + getPizzaSize()
				+ ", getPizzaSizeString()=" + getPizzaSizeString() + ", getNumberOfToppings()=" + getNumberOfToppings()
				+ ", getToppingPrice()=" + getToppingPrice() + ", calculateOrderPrice()=" + calculateOrderPrice() + "]";
	}

	public void printOrderReport() {
		System.out.println("Customer Name : " + getCustomerName() + "\nDate Ordered : " + getDateOrdered() + "\n"
				+ "Pizza Size : " + getPizzaSizeString() + "\nNumber OF Toppings : " + getNumberOfToppings()
				+ "\nToppings Price : " + getToppingPrice() + "\nOrder Price : " + calculateOrderPrice()
				+ "\nService Charge : " + getServiceCharge());
	}

	@Override
	public double calculateOrderPrice() {
		super.setOrderPrice(  super.calculateOrderPrice() + getServiceCharge()    );
		return super.getOrderPrice();
	}

}
