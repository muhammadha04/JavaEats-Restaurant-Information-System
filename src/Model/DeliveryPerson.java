package Model;

import java.io.Serializable;
import java.time.LocalDate;

import Utils.Gender;
import Utils.Vehicle;

public class DeliveryPerson extends Person  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8297205657898934973L;
	private static int idCounter = 1;
	private Vehicle vehicle;
	private DeliveryArea area;
	private String areast;
	
	public String getAreast() {
		return areast;
	}

	public void setAreast(String areast) {
		this.areast = areast;
	}

	public DeliveryPerson(String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
			DeliveryArea area) {
		super(idCounter++, firstName, lastName, birthDay, gender);
		this.vehicle = vehicle;
		this.area = area;
		if(area!=null)
		areast =area.toString();
		else areast= " 123";
	}
	
	public DeliveryPerson(int id) {
		super(id);
	}
	
	public static int getIdCounter() {
		return idCounter;
	}
	public static void setIdCounter(int idCounter) {
		DeliveryPerson.idCounter = idCounter;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public DeliveryArea getArea() {
		return area;
	}
	public void setArea(DeliveryArea area) {
		this.area = area;
		areast =area.toString();
	}
	@Override
	public String toString() {
		return super.toString()+" DeliveryPerson [vehicle=" + vehicle + "]";
	}

}
