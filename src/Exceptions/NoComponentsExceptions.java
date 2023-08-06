package Exceptions;

import Model.Dish;

public class NoComponentsExceptions extends Exception{

	public NoComponentsExceptions(Dish dish) {
		super("The dish "+ dish + " is not include components!");
		
	}
	
}
