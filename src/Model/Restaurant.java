package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import Exceptions.ConvertToExpressException;
import Exceptions.IllegalCustomerException;
import Exceptions.NoComponentsExceptions;
import Exceptions.SensitiveException;
import Utils.Expertise;
import Utils.Gender;
import Utils.MyFileLogWriter;
import Utils.Neighberhood;

public class Restaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 987425141848016180L;

	private static Restaurant restaurant = null;

	private HashMap<Integer, Cook> cooks;
	private HashMap<Integer, DeliveryPerson> deliveryPersons;
	private HashMap<Integer, Customer> customers;
	private HashMap<Integer, Dish> dishes;
	private HashMap<Integer, Component> componenets;
	private HashMap<Integer, Order> orders;
	private HashMap<Integer, Delivery> deliveries;
	private HashMap<Integer, DeliveryArea> areas;
	private HashMap<String, String> userPass;
	public Customer currentCus;
	/* NEW */
	private HashMap<Customer, TreeSet<Order>> orderByCustomer;
	private HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea;
	private HashSet<Customer> blackList;
	private boolean isAdmin;
	private HashMap<Integer, Dish> dishesCart;
	private HashMap<Integer, ArrayList<Component>> dishComps;
	// ADD HASHMAP DISH,ARRAYLIST<COMPS>

	public HashMap<Integer, ArrayList<Component>> getDishComps() {
		return dishComps;
	}

	public void setDishComps(HashMap<Integer, ArrayList<Component>> dishComps) {
		this.dishComps = dishComps;
	}

	// create userpass -> makes a list of all the users and passwords
	public void createUserPass() {
		for (Customer c : this.customers.values()) {
			userPass.put(c.getUsername(), c.getPassword());
		}
	}

	public static Restaurant getInstance() {
		if (restaurant == null)
			restaurant = new Restaurant();
		return restaurant;
	}

	public Customer getCurrCustomer() {
		return currentCus;
	}

	public void setCurrCustomer(Customer currentCus) {
		this.currentCus = currentCus;
	}

	private Restaurant() {
		cooks = new HashMap<>();
		deliveryPersons = new HashMap<>();
		customers = new HashMap<>();
		dishes = new HashMap<>();
		componenets = new HashMap<>();
		orders = new HashMap<>();
		deliveries = new HashMap<>();
		areas = new HashMap<>();
		orderByCustomer = new HashMap<>();
		orderByDeliveryArea = new HashMap<>();
		blackList = new HashSet<>();
		userPass = new HashMap<>();
		dishesCart = new HashMap<>();
		dishComps = new HashMap<>();
		Customer admin = new Customer("ADMIN", "ADMIN", null, Gender.Male, Neighberhood.Bat_Galim, false, true, "ADMIN",
				"ADMIN");
		admin.setId(0);
		customers.put(0, admin);
	}

	public HashMap<Integer, Dish> getOrdersCart() {
		return dishesCart;
	}

	public void setOrdersCart(HashMap<Integer, Dish> ordersCart) {
		this.dishesCart = ordersCart;
	}

	public HashMap<Integer, Cook> getCooks() {
		return cooks;
	}

	public void setCooks(HashMap<Integer, Cook> cooks) {
		this.cooks = cooks;
	}

	public HashMap<Integer, DeliveryPerson> getDeliveryPersons() {
		return deliveryPersons;
	}

	public void setDeliveryPersons(HashMap<Integer, DeliveryPerson> deliveryPersons) {
		this.deliveryPersons = deliveryPersons;
	}

	public HashMap<Integer, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<Integer, Customer> customers) {
		this.customers = customers;
	}

	public HashMap<Integer, Dish> getDishes() {
		return dishes;
	}

	public void setDishes(HashMap<Integer, Dish> dishes) {
		this.dishes = dishes;
	}

	public HashMap<Integer, Component> getComponenets() {
		return componenets;
	}

	public void setComponenets(HashMap<Integer, Component> componenets) {
		this.componenets = componenets;
	}

	public HashMap<Integer, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Integer, Order> orders) {
		this.orders = orders;
	}

	public HashMap<Integer, Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(HashMap<Integer, Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public HashMap<Integer, DeliveryArea> getAreas() {
		return areas;
	}

	public void setAreas(HashMap<Integer, DeliveryArea> areas) {
		this.areas = areas;
	}

	public HashMap<Customer, TreeSet<Order>> getOrderByCustomer() {
		return orderByCustomer;
	}

	public void setOrderByCustomer(HashMap<Customer, TreeSet<Order>> orderByCustomer) {
		this.orderByCustomer = orderByCustomer;
	}

	public HashMap<DeliveryArea, HashSet<Order>> getOrderByDeliveryArea() {
		return orderByDeliveryArea;
	}

	public void setOrderByDeliveryArea(HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea) {
		this.orderByDeliveryArea = orderByDeliveryArea;
	}

	public HashSet<Customer> getBlackList() {
		return blackList;
	}

	public void setBlackList(HashSet<Customer> blackList) {
		this.blackList = blackList;
	}

	public boolean addCook(Cook cook) {
		if (cook == null || getCooks().containsKey(cook.getId()))
			return false;

		return getCooks().put(cook.getId(), cook) == null;
	}

	public boolean addDeliveryPerson(DeliveryPerson dp, DeliveryArea da) {
		if (dp == null || getDeliveryPersons().containsKey(dp.getId()) || !getAreas().containsKey(da.getId()))
			return false;

		DeliveryArea da1 = getAreas().get(dp.getArea().getId());
		getAreas().remove(da1.getId());
		da1.addDelPerson(dp);
		getAreas().put(da1.getId(), da1);
		return deliveryPersons.put(dp.getId(), dp) == null;
	}

	public boolean addCustomer(Customer cust) {

		if (cust == null || getCustomers().containsKey(cust.getId()))
			return false;
		return (getCustomers().put(cust.getId(), cust) == null
				&& getUsers().put(cust.getUsername(), cust.getPassword()) == null);
	}

	public boolean addDish(Dish dish) {
		if (dish == null || getDishes().containsKey(dish.getId()))
			return false;

		ArrayList<Component> comps = new ArrayList<Component>();
		for(Component c : dish.getComponenets())
			comps.add(c);
		getDishComps().put(dish.getId(), comps);

		return getDishes().put(dish.getId(), dish) == null;
	}

	public boolean addComponent(Component comp) {
		if (comp == null || getComponenets().containsKey(comp.getId()))
			return false;

		return getComponenets().put(comp.getId(), comp) == null;
	}

	public boolean addToCart(Dish dish) {
		if (dish == null || getOrdersCart().containsKey(dish.getId()))
			return false;
//		for(Component c : dish.getComponenets()) {
//			if(!getComponenets().containsKey(c.getId()))
//				return false;
//		}

		return getOrdersCart().put(dish.getId(), dish) == null;
	}

	public boolean addOrder(Order order) {
		try {
			if (order == null || getOrders().containsKey(order.getId()))
				return false;
			if (order.getCustomer() == null || !getCustomers().containsKey(order.getCustomer().getId()))
				return false;
			if (getBlackList().contains(order.getCustomer())) {
				throw new IllegalCustomerException();
			}
			for (Dish d : order.getDishes()) {
				if (!getDishes().containsKey(d.getId()))
					return false;
				for (Component c : d.getComponenets()) {
					if (order.getCustomer().getIsSensitiveToGluten() && c.isHasGluten()) {
						throw new SensitiveException(
								order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName(),
								d.getDishName());
					} else if (order.getCustomer().getIsSensitiveToLactose() && c.isHasLactose()) {
						throw new SensitiveException(
								order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName(),
								d.getDishName());
					}
				}
			}

			return getOrders().put(order.getId(), order) == null;

		} catch (SensitiveException e) {
			Utils.MyFileLogWriter.println(e.getMessage());
			return false;
		} catch (IllegalCustomerException e) {
			Utils.MyFileLogWriter.println(e.getMessage());
			return false;
		}
	}

//	public boolean addDelivery(Delivery delivery) {
//		try {
//			if (delivery == null || getDeliveries().containsKey(delivery.getId())
//					|| !getDeliveryPersons().containsKey(delivery.getDeliveryPerson().getId())) {
//				return false;
//			}
//			if (delivery instanceof RegularDelivery) {
//				RegularDelivery rd = (RegularDelivery) delivery;
//				for (Order o : rd.getOrders()) {
//					if (!getOrders().containsKey(o.getId()))
//						return false;
//					o.setDelivery(delivery);
//				}
//				if (rd.getOrders().size() == 1) {
//					throw new ConvertToExpressException();
//				}
//				if (rd.getOrders().isEmpty())
//					return false;
//			} else {
//				ExpressDelivery ed = (ExpressDelivery) delivery;
//				if (!getOrders().containsKey(ed.getOrder().getId()))
//					return false;
//				ed.getOrder().setDelivery(delivery);
//			}
//		} catch (ConvertToExpressException e) {
//			Utils.MyFileLogWriter.println(e.getMessage());
//			RegularDelivery rd = (RegularDelivery) delivery;
//			delivery = new ExpressDelivery(rd.getDeliveryPerson(), rd.getArea(), rd.isDelivered(),
//					rd.getOrders().first(), rd.getDeliveredDate());
//		} finally {
//			delivery.getArea().addDelivery(delivery);
//			if (delivery instanceof RegularDelivery) {
//				RegularDelivery rg = (RegularDelivery) delivery;
//				for (Order o : rg.getOrders()) {
//					TreeSet<Order> orders = orderByCustomer.get(o);
//					if (orders == null)
//						orders = new TreeSet<>(new Comparator<Order>() {
//
//							@Override
//							public int compare(Order o1, Order o2) {
//								return o1.getDelivery().getDeliveredDate()
//										.compareTo(o2.getDelivery().getDeliveredDate());
//							}
//						});
//					orders.add(o);
//					orderByCustomer.put(o.getCustomer(), orders);
//				}
//			} else {
//				ExpressDelivery ex = (ExpressDelivery) delivery;
//				TreeSet<Order> orders = orderByCustomer.get(ex.getOrder());
//				if (orders == null)
//					orders = new TreeSet<>(new Comparator<Order>() {
//
//						@Override
//						public int compare(Order o1, Order o2) {
//							return o1.getDelivery().getDeliveredDate().compareTo(o2.getDelivery().getDeliveredDate());
//						}
//					});
//				orders.add(ex.getOrder());
//				orderByCustomer.put(ex.getOrder().getCustomer(), orders);
//			}
//		}
//		return getDeliveries().put(delivery.getId(), delivery) == null;
//	}
	public boolean addDelivery(Delivery delivery) {
		int id;
		if (delivery == null || getDeliveries().containsKey(delivery.getId())
				|| !getDeliveryPersons().containsKey(delivery.getDeliveryPerson().getId()))
			return false;

		// if the delviery is express
		if (delivery instanceof ExpressDelivery) {
			id = ((ExpressDelivery) delivery).getOrder().getId();

			if (!this.orders.containsKey(id)) {

				return false;
			} else
				((ExpressDelivery) delivery).getOrder().setDelivery(delivery);

			// regular delivery
		} else if (delivery instanceof RegularDelivery) {
			for (Order o : ((RegularDelivery) delivery).getOrders()) {
				if (!getOrders().containsKey(o.getId()))
					return false;
				o.setDelivery(delivery);
			}

		}

		try {// exception 1 order in regular delivery
			if (delivery instanceof RegularDelivery) {
				if (((RegularDelivery) delivery).getOrders().size() == 1)
					throw new ConvertToExpressException();
			}
		} catch (Exception e) {
			MyFileLogWriter.println(e.getMessage());
			return false;
		}

		delivery.getArea().addDelivery(delivery);
		if (deliveries.put(delivery.getId(), delivery) == null)
			return true;
		return false;
	}

	public boolean addDeliveryArea(DeliveryArea da) {
		if (da == null || getAreas().containsKey(da.getId()))
			return false;
		return getAreas().put(da.getId(), da) == null;
	}

	public boolean addCustomerToBlackList(Customer c) {
		if (c == null)
			return false;
		return getBlackList().add(c);
	}

	public boolean removeCook(Cook cook) {
		if (cook == null || !getCooks().containsKey(cook.getId()))
			return false;
		return getCooks().remove(cook.getId()) != null;
	}

	public boolean removeDeliveryPerson(DeliveryPerson dp) {
		if (dp == null || !getDeliveryPersons().containsKey(dp.getId()))
			return false;
		for (Delivery d : getDeliveries().values()) {
			if (d.getDeliveryPerson().equals(dp)) {
				d.setDeliveryPerson(null);
			}
		}
		return getDeliveryPersons().remove(dp.getId()) != null && dp.getArea().removeDelPerson(dp);
	}

	public boolean removeCustomer(Customer cust) {
		if (cust == null || !getCustomers().containsKey(cust.getId()))
			return false;
		return getCustomers().remove(cust.getId()) != null;
	}

	public boolean replaceCartDish(Dish dish1, Dish dish2) {
		if (dish2 == null || !getDishes().containsKey(dish1.getId()))
			return false;
		getOrdersCart().remove(dish1.getId());
		getOrdersCart().put(dish2.getId(), dish2);
		return true;

	}

	public boolean removeDish(Dish dish) {
		if (dish == null || !getDishes().containsKey(dish.getId()))
			return false;
		for (Delivery d : deliveries.values()) {
			if (!d.getIsDelivered()) {
				if (d instanceof RegularDelivery) {
					RegularDelivery rd = (RegularDelivery) d;
					for (Order o : rd.getOrders()) {
						o.removeDish(dish);
					}
				} else {
					ExpressDelivery ed = (ExpressDelivery) d;
					ed.getOrder().removeDish(dish);
				}
			}
		}
		return getDishes().remove(dish.getId()) != null;
	}

	public boolean removeComponent(Component comp) {
		Dish removeDish = null;
		try {
			if (comp == null || !getComponenets().containsKey(comp.getId()))
				return false;
			for (Dish d : getDishes().values()) {
				d.removeComponent(comp);
				if (d.getComponenets().isEmpty()) {
					removeDish = d;
					throw new NoComponentsExceptions(d);
				}
			}
		} catch (NoComponentsExceptions e) {
			Utils.MyFileLogWriter.println(e.getMessage());
			removeDish(removeDish);
		}
		return getComponenets().remove(comp.getId()) != null;
	}

	public boolean removeOrder(Order order) {
		if (order == null || !getOrders().containsKey(order.getId()))
			return false;
		if (getOrders().remove(order.getId()) != null) {
			if (order.getDelivery() instanceof RegularDelivery) {
				RegularDelivery rd = (RegularDelivery) order.getDelivery();
				return rd.removeOrder(order);
			} else {
				ExpressDelivery ed = (ExpressDelivery) order.getDelivery();
				ed.setOrder(null);
				return true;
			}
		}
		return false;
	}

	public boolean removeDelivery(Delivery delivery) {
		if (delivery == null || !getDeliveries().containsKey(delivery.getId()))
			return false;
		if (delivery instanceof RegularDelivery) {
			RegularDelivery rd = (RegularDelivery) delivery;
			for (Order o : rd.getOrders()) {
				o.setDelivery(null);
			}
		} else {
			ExpressDelivery ed = (ExpressDelivery) delivery;
			ed.getOrder().setDelivery(null);
		}
		return getDeliveries().remove(delivery.getId()) != null && delivery.getArea().removeDelivery(delivery);
	}

	public Customer getCusFromUser(String s) {
		for (Customer cus : this.getCustomers().values()) {
			if (cus.getUsername().equals(s))
				return cus;
		}
		return null;
	}

	
	public boolean removeDeliveryArea(DeliveryArea oldArea, DeliveryArea newArea) {
		if (oldArea == null || newArea == null || !getAreas().containsKey(oldArea.getId())
				|| !getAreas().containsKey(newArea.getId()))
			return false;
		for (Neighberhood n : oldArea.getNeighberhoods()) {
			newArea.addNeighberhood(n);
		}
		for (Delivery d : oldArea.getDelivers()) {
			newArea.addDelivery(d);
		}
		for (DeliveryPerson dp : oldArea.getDelPersons()) {
			newArea.addDelPerson(dp);
		}
		for (DeliveryPerson dp : oldArea.getDelPersons()) {
			dp.setArea(newArea);
		}
		for (Delivery d : oldArea.getDelivers()) {
			d.setArea(newArea);
		}
		return (getAreas().remove(oldArea.getId()) != null && getAreas().put(newArea.getId(), newArea)!= null );
	}

	public Cook getRealCook(int id) {
		return getCooks().get(id);
	}

	public DeliveryPerson getRealDeliveryPerson(int id) {
		return getDeliveryPersons().get(id);
	}

	public Customer getRealCustomer(int id) {
		return getCustomers().get(id);
	}

	public Dish getRealDish(int id) {
		return getDishes().get(id);
	}

	public Component getRealComponent(int id) {
		return getComponenets().get(id);
	}

	public Order getRealOrder(int id) {
		return getOrders().get(id);
	}

	public Delivery getRealDelivery(int id) {
		return getDeliveries().get(id);
	}

	public DeliveryArea getRealDeliveryArea(int id) {
		return getAreas().get(id);
	}

	public Collection<Dish> getReleventDishList(Customer c) {
		ArrayList<Dish> dishList = new ArrayList<>();
		if (!c.getIsSensitiveToGluten() && !c.getIsSensitiveToLactose())
			return getDishes().values();
		for (Dish d : getDishes().values()) {
			boolean isValid = true;
			for (Component comp : d.getComponenets()) {
				if (c.getIsSensitiveToGluten() && c.getIsSensitiveToLactose()) {
					if (comp.isHasGluten() || comp.isHasLactose())
						isValid = false;
				} else if (c.getIsSensitiveToGluten() && comp.isHasGluten()) {
					isValid = false;
				} else if (c.getIsSensitiveToLactose() && comp.isHasLactose()) {
					isValid = false;
				}
			}
			if (isValid)
				dishList.add(d);
		}
		return dishList;
	}

	public void deliver(Delivery d) {
		d.setIsDelivered(true);
		if (d instanceof RegularDelivery) {
			RegularDelivery rd = (RegularDelivery) d;
			for (Order o : rd.getOrders()) {
				System.out.println(o + " had reached to Customer " + o.getCustomer());
			}
		} else {
			ExpressDelivery ed = (ExpressDelivery) d;
			System.out.println(ed.getOrder() + " had reached to Customer " + ed.getOrder().getCustomer());
		}

	}

	public ArrayList<Cook> GetCooksByExpertise(Expertise e) {
		ArrayList<Cook> cooks = new ArrayList<>();
		for (Cook c : getCooks().values()) {
			if (c.getExpert().equals(e))
				cooks.add(c);
		}
		return cooks;
	}

	/* NEW QUERIES */
	public TreeSet<Delivery> getDeliveriesByPerson(DeliveryPerson dp, int month) {
		TreeSet<Delivery> delivered = new TreeSet<>(new Comparator<Delivery>() {

			@Override
			public int compare(Delivery o1, Delivery o2) {
				if (o1.getDeliveredDate().getDayOfMonth() > o2.getDeliveredDate().getDayOfMonth())
					return 1;
				if (o1.getDeliveredDate().getDayOfMonth() < o2.getDeliveredDate().getDayOfMonth())
					return -1;
				return o1.getId() > o2.getId() ? 1 : -1;
			}
		});
		for (Delivery d : getDeliveries().values()) {
			if (d.getDeliveryPerson().equals(dp) && d.getDeliveredDate().getMonthValue() == month)
				delivered.add(d);
		}
		return delivered;
	}

	public HashMap<String, Integer> getNumberOfDeliveriesPerType() {
		HashMap<String, Integer> deliveriesPerType = new HashMap<>();
		deliveriesPerType.put("Regular delivery", 0);
		deliveriesPerType.put("Express delivery", 0);
		int amount;
		for (Delivery d : getDeliveries().values()) {
			LocalDate today = LocalDate.now();
			if (d instanceof RegularDelivery && d.getDeliveredDate().getYear() == today.getYear()) {
				amount = deliveriesPerType.get("Regular delivery");
				deliveriesPerType.put("Regular delivery", amount + 1);
			} else {
				if (d.getDeliveredDate().getYear() == today.getYear()) {
					amount = deliveriesPerType.get("Express delivery");
					deliveriesPerType.put("Express delivery", amount + 1);
				}
			}
		}
		return deliveriesPerType;
	}

	public double revenueFromExpressDeliveries() {
		HashSet<Customer> customers = new HashSet<>();
		double amountOfPostages = 0;
		for (Delivery d : getDeliveries().values()) {
			if (d instanceof ExpressDelivery) {
				ExpressDelivery ed = (ExpressDelivery) d;
				amountOfPostages += ed.getPostage();
				customers.add(ed.getOrder().getCustomer());
			}
		}
		amountOfPostages += (30 * customers.size());
		return amountOfPostages;
	}

	public LinkedList<Component> getPopularComponents() {
		HashMap<Component, Integer> componentsandAmount = new HashMap<>();
		for (Dish d : getDishes().values()) {
			for (Component c : getDishComps().get(d.getId())) {


				Integer numOfComponent = componentsandAmount.get(c);
				if (numOfComponent == null)
					numOfComponent = 0;
				componentsandAmount.put(c, numOfComponent + 1);
			}
		}
		LinkedList<Component> popularComponents = new LinkedList<>();
		for (Component c : componentsandAmount.keySet()) {
			popularComponents.add(c);
		}
		popularComponents.sort(new Comparator<Component>() {

			@Override
			public int compare(Component o1, Component o2) {
				int result = -1 * componentsandAmount.get(o1).compareTo(componentsandAmount.get(o2));
				if (result != 0)
					return result;
				if (o1.getId() > o2.getId())
					return -1;
				return 1;
			}
		});
		return popularComponents;
	}

	public TreeSet<Dish> getProfitRelation() {
		TreeSet<Dish> profit = new TreeSet<Dish>((Dish o1, Dish o2) -> {
			if ((o2.getPrice() / o2.getTimeToMake()) > (o1.getPrice() / o1.getTimeToMake()))
				return 1;
			else if ((o2.getPrice() / o2.getTimeToMake()) < (o1.getPrice() / o1.getTimeToMake()))
				return -1;
			return -1 * o1.getId().compareTo(o2.getId());
		});
		for (Dish d : getDishes().values()) {
			profit.add(d);
		}
		return profit;
	}

	public TreeSet<Delivery> createAIMacine(DeliveryPerson dp, DeliveryArea da, TreeSet<Order> orders) {
		TreeSet<Delivery> AIDecision = new TreeSet<>(new Comparator<Delivery>() {

			@Override
			public int compare(Delivery o1, Delivery o2) {
				if (o2 instanceof RegularDelivery && o1 instanceof ExpressDelivery)
					return -1;
				if (o2 instanceof ExpressDelivery && o1 instanceof RegularDelivery)
					return 1;
				return o2.getId() > o1.getId() ? -1 : 1;
			}
		});
		TreeSet<Order> toRegularDelivery = new TreeSet<>();
		if (orders.size() <= 2) {
			for (Order o : orders) {
				ExpressDelivery ed = new ExpressDelivery(dp, da, false, o, LocalDate.of(2021, 1, 1));
				AIDecision.add(ed);
			}
		} else {
			for (Order o : orders) {
				if (o.getCustomer().getIsSensitiveToGluten() || o.getCustomer().getIsSensitiveToLactose()) {
					ExpressDelivery ed = new ExpressDelivery(dp, da, false, o, LocalDate.of(2021, 1, 1));
					AIDecision.add(ed);
				} else
					toRegularDelivery.add(o);
			}
			if (toRegularDelivery.size() < 2) {
				ExpressDelivery ed = new ExpressDelivery(dp, da, false, toRegularDelivery.first(),
						LocalDate.of(2021, 1, 1));
				AIDecision.add(ed);
			} else {
				RegularDelivery rd = new RegularDelivery(toRegularDelivery, dp, da, false, LocalDate.of(2021, 1, 1));
				AIDecision.add(rd);
			}
		}
		return AIDecision;
	}

	public HashMap<String, String> getUsers() {
		// TODO Auto-generated method stub
		return this.userPass;
	}

	public void setAdmin(boolean b) {
		this.isAdmin = b;

	}

	public boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void deleteUser(String username) {
		this.userPass.remove(username);

	}
}
