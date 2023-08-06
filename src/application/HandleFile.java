package application;

import java.io.File;
import Model.*;

public class HandleFile {

	public static void autoSaveData() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run(){
				
				Restaurant RestaurantData=Restaurant.getInstance();
				RestaurantData.getCooks().putAll(Restaurant.getInstance().getCooks());
				RestaurantData.getDeliveryPersons().putAll(Restaurant.getInstance().getDeliveryPersons());
				RestaurantData.getCustomers().putAll(Restaurant.getInstance().getCustomers());
				RestaurantData.getDishes().putAll(Restaurant.getInstance().getDishes());
				RestaurantData.getComponenets().putAll(Restaurant.getInstance().getComponenets());
				RestaurantData.getOrders().putAll(Restaurant.getInstance().getOrders());
				RestaurantData.getDeliveries().putAll(Restaurant.getInstance().getDeliveries());
				RestaurantData.getAreas().putAll(Restaurant.getInstance().getAreas());
				RestaurantData.getOrderByCustomer().putAll(Restaurant.getInstance().getOrderByCustomer());
				RestaurantData.getOrderByDeliveryArea().putAll(Restaurant.getInstance().getOrderByDeliveryArea());
				RestaurantData.getBlackList().addAll(Restaurant.getInstance().getBlackList());
				RestaurantData.getUsers().putAll(Restaurant.getInstance().getUsers());
				RestaurantData.getOrderByCustomer().putAll(Restaurant.getInstance().getOrderByCustomer());
				RestaurantData.getDishComps().putAll(Restaurant.getInstance().getDishComps());

				try {
					ResourceManager.save(RestaurantData,"res.ser");
				} catch (Exception e) {
					System.out.println("couldnt save");
				}	            System.out.println("Exiting");
			}
		});
	}
	
	
	public static void loadData() {
		try { 
			File newFile = new File("res.ser");
			if(newFile.length()!=0)
			{
				Restaurant RestaurantData=(Restaurant)ResourceManager.load("res.ser");
				//load
				Restaurant.getInstance().getCooks().putAll(RestaurantData.getCooks());
				Restaurant.getInstance().getDeliveryPersons().putAll(RestaurantData.getDeliveryPersons());
				Restaurant.getInstance().getCustomers().putAll(RestaurantData.getCustomers());
				Restaurant.getInstance().getDishes().putAll(RestaurantData.getDishes());
				Restaurant.getInstance().getComponenets().putAll(RestaurantData.getComponenets());
				Restaurant.getInstance().getOrders().putAll(RestaurantData.getOrders());
				Restaurant.getInstance().getDeliveries().putAll(RestaurantData.getDeliveries());
				Restaurant.getInstance().getAreas().putAll(RestaurantData.getAreas());
				Restaurant.getInstance().getOrderByCustomer().putAll(RestaurantData.getOrderByCustomer());
				Restaurant.getInstance().getOrderByDeliveryArea().putAll(RestaurantData.getOrderByDeliveryArea());
				Restaurant.getInstance().getBlackList().addAll(RestaurantData.getBlackList());
				Restaurant.getInstance().getUsers().putAll((RestaurantData.getUsers()));
				Restaurant.getInstance().getOrderByCustomer().putAll(RestaurantData.getOrderByCustomer());
				Restaurant.getInstance().getDishComps().putAll(RestaurantData.getDishComps());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
