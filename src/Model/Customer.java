package Model;

import java.io.Serializable;
import java.time.LocalDate;

import Utils.Gender;
import Utils.Neighberhood;

public class Customer extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5978508012805700896L;
	private static int idCounter = 1;
	private Neighberhood neighberhood;
	private boolean isSensitiveToLactose;
	private boolean isSensitiveToGluten;
	private String username;
	private String password;
	private boolean isBlacked;
	private String picStream;

	public String getPicStream() {
		return picStream;
	}

	public void setPicStream(String picStream) {
		this.picStream = picStream;
	}

	public boolean getIsBlacked() {
		return isBlacked;
	}

	public void setIsBlacked(boolean isBlacked) {
		this.isBlacked = isBlacked;
	}

	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender, Neighberhood neighberhood,
			boolean isSensitiveToLactose, boolean isSensitiveToGluten, String username, String password) {
		super(idCounter++, firstName, lastName, birthDay, gender);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
		this.username = username;
		this.password = password;
		this.picStream = "";
		this.isBlacked = false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer(int id) {
		super(id);
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Customer.idCounter = idCounter;
	}

	public Neighberhood getNeighberhood() {
		return neighberhood;
	}

	public void setNeighberhood(Neighberhood neighberhood) {
		this.neighberhood = neighberhood;
	}

	public boolean getIsSensitiveToLactose() {
		return isSensitiveToLactose;
	}

	public void setSensitiveToLactose(boolean isSensitiveToLactose) {
		this.isSensitiveToLactose = isSensitiveToLactose;
	}

	public boolean getIsSensitiveToGluten() {
		return isSensitiveToGluten;
	}

	public void setSensitiveToGluten(boolean isSensitiveToGluten) {
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	@Override
	public String toString() {
		return super.toString() + " Customer [isSensitiveToLactose=" + isSensitiveToLactose + ", isSensitiveToGluten="
				+ isSensitiveToGluten + "]";
	}
}
