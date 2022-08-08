package CENG112_HW4_G32;

public interface Orderable extends Comparable<Food> {
	public void updatePrice(double price);
	public void updateStock(int stock);
	public int compareTo(Food food);
}
