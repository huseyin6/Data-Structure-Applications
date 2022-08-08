package CENG112_HW4_G32;

public interface IRestaurant extends Comparable<IRestaurant> {
	public String getName();
	public double getRating();
	public String getCuisine();
	public int getDeliveryTime();
	public int compareTo(IRestaurant restaurant);
}
