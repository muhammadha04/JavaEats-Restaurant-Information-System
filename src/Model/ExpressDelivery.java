package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class ExpressDelivery extends Delivery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8624885291852504198L;
	private Order order;
	private double postage;
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

	public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered , Order order , double postage, LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.order = order;
		this.postage = postage;
		this.dpid=deliveryPerson.getId();
		this.daid = area.getId();
		this.orderString = order.toString();
	}
	
	public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered , Order order, LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered,deliveredDate);
		this.order = order;
		this.dpid=deliveryPerson.getId();
		this.daid = area.getId();
		this.orderString = order.toString();
		this.postage = 5.0;
	}
	
	public ExpressDelivery(int id) {
		super(id);
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public double getPostage() {
		return postage;
	}
	
	public void setPostage(double postage) {
		this.postage = postage;
	}
	
	@Override
	public String toString() {
		return "Express delivery [id=" + this.getId() + ", deliveryPerson=" + this.getDeliveryPerson() + ", area=" + this.getArea() + ", isDelivered="
				+ this.getIsDelivered()  + " postage=" + postage + "]";
	}	
}
