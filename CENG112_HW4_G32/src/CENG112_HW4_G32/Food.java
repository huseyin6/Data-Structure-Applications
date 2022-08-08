package CENG112_HW4_G32;

public class Food implements Orderable  {
	
	private String name;
	private double price;
	private int stock;
	private Restaurant restaurant;
	
	public Food(String name, double price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	
	public void updatePrice(double price) {
		this.price += price;
	}
	
	public void updateStock(int stock) {
		this.stock += stock;
	}
	
	@Override
	public int compareTo(Food food) {
		if (this.getPrice() == food.getPrice()) {
			return 0;
		}else if (this.getPrice() > food.getPrice()) {
			return 1;
		}else {
			return -1;
		}
	}

}
