/*
 * Waleed Khalid Swaileh
 * 1160060
 * Section 6
 */
import java.util.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class PizzaOrder implements Comparable<PizzaOrder> {

	public static final int SMALL_SIZE = 1, MEDIUM_SIZE = 2, LARGE_SIZE = 3;
	private String customerName;
	private Date dateOrdered;
	private int pizzaSize;
	private int numberOfToppings;
	private double toppingPrice;
	private double orderPrice;
	public PizzaOrder() {
	}

	public PizzaOrder(String customerName, int pizzaSize, int numberOfToppings, double toppingPrice) throws InputMismatchException {
		this.customerName = customerName;
		dateOrdered = new Date();
		setPizzaSize(pizzaSize);
		setNumberOfToppings(numberOfToppings);
		setToppingPrice(toppingPrice);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public int getPizzaSize() {
		return pizzaSize;
	}

	public String getPizzaSizeString() {   //Returns the size of the pizza as a string , useful when printing order's info / report
		if 	(pizzaSize == 1)
			return "Small Size";
		else if (pizzaSize == 2)
			return "Medium Size";
		else
			return "Large Size";
	}

	public void setPizzaSize(int pizzaSize) {
		if (pizzaSize == 1 || pizzaSize == 2 || pizzaSize == 3)
			this.pizzaSize = pizzaSize;
		else
			System.err.println("Size Invalid , Please Enter A Number between 1 and 3");
	}

	public int getNumberOfToppings() {
		return numberOfToppings;
	}

	public void setNumberOfToppings(int numberOfToppings) throws InputMismatchException  {
		if (numberOfToppings > 0)
			this.numberOfToppings = numberOfToppings;
		else{
			throw new InputMismatchException("Please Choose at least one Topping");
		}
	}

	public double getToppingPrice() {
		return toppingPrice;
	}

	public void setToppingPrice(double toppingPrice) throws InputMismatchException{
		if (toppingPrice > 0)
			this.toppingPrice=toppingPrice;
		else
			throw new InputMismatchException("Toppings Price Must Be Positive!");
	}

	@Override
	public String toString() {
		return "PizzaOrder [customerName=" + customerName + ", dateOrdered=" + dateOrdered + ", pizzaSize=" + pizzaSize
				+ ", numberOfToppings=" + numberOfToppings + ", toppingPrice=" + toppingPrice + "]";
	}

	public double calculateOrderPrice() {		//to calculate final price of the order
		orderPrice = (numberOfToppings * toppingPrice) * pizzaSize ;
		return orderPrice;
	}

	public void printOrderReport() {	//to print full info about the order
		System.out.println("Customer Name : " + getCustomerName() + "\nDate Ordered : " + getDateOrdered() + "\n"
				+ "Pizza Size : " + getPizzaSizeString() + "\nNumber OF Toppings : " + getNumberOfToppings()
				+ "\nToppings Price : " + getToppingPrice() + "\nOrder Price : " + calculateOrderPrice());
	}

	public void printOrderInfo() {  //to print some info about the order
		System.out.println("Customer Name : " + getCustomerName() + "    Total Price : " + calculateOrderPrice());
	}

	
	
	public double getOrderPrice() {
		return orderPrice;
	}

	
	
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Override
	public int compareTo(PizzaOrder o) {	//to compare 2 orders depending on their price	
		if (calculateOrderPrice() > o.calculateOrderPrice())
			return 1;
		else if (calculateOrderPrice() < o.calculateOrderPrice())
			return -1;
		else
			return 0;
	}

}
