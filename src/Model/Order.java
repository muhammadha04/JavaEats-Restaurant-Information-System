package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Utils.MyFileLogWriter;

public class Order implements Comparable<Order>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 480858837115296091L;
	private static int idCounter = 1;
	private Integer id;
	private Customer customer;
	private ArrayList<Dish> dishes;
	private Delivery delivery;
	private double price;
	private String dishesString;
	private Integer cusid;
	private Integer delid;
	
	// constructors 
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getCusid() {
		return cusid;
	}

	public void setCusid(Integer cusID) {
		this.cusid = cusID;
	}

	public Integer getDelid() {
		return delid;
	}

	public void setDelid(Integer delID) {
		this.delid = delID;
	}

	public Order(Customer customer, ArrayList<Dish> dishes, Delivery delivery) {
		super();
		this.id = idCounter++;
		this.customer = customer;
		this.dishes = dishes;
		this.delivery = delivery;
		price = 0.0;
		this.cusid = customer.getId();
		this.delid = 0;
		for(Dish d : dishes) {
			price += d.getPrice();
		}
		dishesString = dishes.toString();
	}

	public String getDishesString() {
		return dishesString;
	}

	public void setDishesString(String dishesString) {
		this.dishesString = dishesString;
	}

	public Order(int id) {
		this.dishes = new ArrayList<Dish>();
		this.id = id;
	}
	
	// getters setters
	
	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Order.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.cusid = customer.getId();
		this.customer = customer;
	}

	public List<Dish> getDishes() {
		return Collections.unmodifiableList(dishes);
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;

		this.delid = delivery.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + "]";
	}
	
	// methods

	public double calcOrderRevenue() {
		double revenue = 0.0;
		for(Dish d : getDishes()) {
			double price = d.calcDishPrice();
			double cost = 0.0;
			for(Component c : d.getComponenets()) {
				cost += c.getPrice();
			}
			revenue += (price - cost);
		}
		MyFileLogWriter.println("Order Revenue = "+revenue);
		return revenue;
	}
	
	public int orderWaitingTime(DeliveryArea da) {
		int time = 0;
		time += da.getDeliverTime();
		for(Dish d : getDishes()) {
			time += d.getTimeToMake();
		}
		MyFileLogWriter.println("Time for order: "+this+" is "+time+" minutes");
		return time;
	}

	
	public boolean addDish(Dish d) {
		return dishes.add(d);
	}
	
	public void setDishes(ArrayList<Dish> dishes) {
		this.dishes = dishes;
	}

	public boolean removeDish(Dish d) {
		return dishes.remove(d);
	}
	
	@Override
	public int compareTo(Order o) {
		return this.id.compareTo(o.getId());
	}

}
