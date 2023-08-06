package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class RegularDelivery extends Delivery  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8300298997461887790L;
	private TreeSet<Order> orders;
	private String orderString;
	private int dpid;
	private int daid;

	public String getOrderString() {
		return orderString;
	}

	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}


	public int getDpid() {
		return dpid;
	}

	public void setDpid(int dpid) {
		this.dpid = dpid;
	}

	
	
	public int getDaid() {
		return daid;
	}

	public void setDaid(int daid) {
		this.daid = daid;
	}
	public RegularDelivery(DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered,LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.orders = new TreeSet<>();
		this.dpid=deliveryPerson.getId();
		this.daid = area.getId();
		this.orderString = orders.toString();
	}

	public RegularDelivery(TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered,LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.dpid=deliveryPerson.getId();
		this.daid = area.getId();
		this.orderString = orders.toString();
		this.orders = orders;
	}
	
	public RegularDelivery(int id) {
		super(id);
	}

	public SortedSet<Order> getOrders() {
		return Collections.unmodifiableSortedSet(orders);
	}
	
	public void setOrders(TreeSet<Order> orders) {
		this.orders = orders;
	}
	
	//Methods
	
	public boolean addOrder(Order o) {
		return orders.add(o);
	}
	
	public boolean removeOrder(Order o) {
		return orders.remove(o);
	}

}
