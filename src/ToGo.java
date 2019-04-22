import java.util.InputMismatchException;

/*
 * Waleed Khalid Swaileh
 * 1160060
 * Section 6
 */
public class ToGo extends PizzaOrder {

	public ToGo() {

	}

	public ToGo(String customerName, int pizzaSize, int numberOfToppings, double toppingPrice)throws InputMismatchException {
		super(customerName, pizzaSize, numberOfToppings, toppingPrice);
	}

}
