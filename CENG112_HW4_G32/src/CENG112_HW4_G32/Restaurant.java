package CENG112_HW4_G32;

public class Restaurant implements IRestaurant {
	
	private String name;
	private double rating;
	private String cuisine;
	private int deliveryTime; // in minutes
	
	public Restaurant(String name, double rating, String cuisine, int deliveryTime) {
		this.name = name;
		this.rating = rating;
		this.cuisine = cuisine;
		this.deliveryTime = deliveryTime;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getRating() {
		return rating;
	}
	
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	
	public String getCuisine() {
		return cuisine;
	}
	
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime=deliveryTime;
	}
	
	public int getDeliveryTime() {
		return deliveryTime;
	}
	
	public void updateRating(double score) {
		this.rating += score;
	}
	
	public void updateDeliveryTime(int deliveryTime) {
		this.deliveryTime += deliveryTime;
	}
	
	public void updateCuisine(String category) {
		this.cuisine = category;
	}
	
	@Override
	public int compareTo(IRestaurant restaurant) {
		if (this.getRating() == restaurant.getRating()) {
			return 0;
		}else if (this.getRating() > restaurant.getRating()) {
			return 1;
		}else {
			return -1;
		}
	}

}
