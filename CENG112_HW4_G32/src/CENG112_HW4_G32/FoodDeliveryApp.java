package CENG112_HW4_G32;

public class FoodDeliveryApp {
	
	private static FileIO fileIO = new FileIO();
	private static BinarySearchTreeInterface<Restaurant> binarySearchTreeForRestaurant = fileIO.ReadRestaurant();
	private static BinarySearchTreeInterface<Food> binarySearchTreeForFood = fileIO.ReadFood();
	private static BinaryNode<Restaurant> rootNode1 = binarySearchTreeForRestaurant.getRoot();
	private static BinaryNode<Food> rootNode2 = binarySearchTreeForFood.getRoot();
	
	public static void main(String[] args) {
		
		System.out.println("----------------- 1 ------------------");
		displayDescending(rootNode1);
		System.out.println("\n----------------- 2 ------------------");
		displayAscending(rootNode2);
		System.out.println("\n----------------- 3 ------------------");
		shortestDeliveryTimeForPizza(rootNode1);
		printStatue(deliveryTime, restaurantName);
		System.out.println("\n----------------- 4 ------------------");
		displayHighestAmountOfStockForCoffee(rootNode2);
		printStatue(amountStock, name);
		System.out.println("\n----------------- 5 ------------------");
		removeFood(rootNode2);
		System.out.println("\n----------------- 6 ------------------");
		removeRestaurant(rootNode1);
		System.out.println("\n----------------- 7 ------------------");
		updatePrice(rootNode2);
		System.out.println("Prices in FoodBSTs are updated.");
		System.out.println("\n----------------- 8 ------------------");
		updateStock(rootNode2);
		System.out.println("Stocks in FoodBSTs are updated.");
		System.out.println("\n----------------- 9 ------------------");
		displayDescending(rootNode1);
		System.out.println("\n----------------- 10 ------------------");
		displayAscending(rootNode2);
		
	}
	
	public static void displayDescending(BinaryNode<Restaurant> node) {
		if(node != null) {
			displayDescending(node.getRightChild());
			System.out.println(arrangeLine(node, 35));
			displayDescending(node.getLeftChild());
		}
	}
	
	public static void displayAscending(BinaryNode<Food> node) {
		if(node != null) {
			displayAscending(node.getLeftChild());
			System.out.println(arrangeLen(node, 35));
			displayAscending(node.getRightChild());
		}
	}
	
	private static int deliveryTime = 999999;
	private static String restaurantName = "";
	public static void shortestDeliveryTimeForPizza(BinaryNode<Restaurant> node) {
		if(node!=null) {
			shortestDeliveryTimeForPizza(node.getRightChild());
			if(node.getData().getCuisine().equals(" Pizza")  && node.getData().getDeliveryTime() < deliveryTime) {
				deliveryTime = node.getData().getDeliveryTime();
				restaurantName = node.getData().getName();
			}
			shortestDeliveryTimeForPizza(node.getLeftChild());
		}
	}
	
	static int amountStock = 0;
	static String name = "" ;
	public static void displayHighestAmountOfStockForCoffee(BinaryNode<Food> node) {
		if(node!=null) {
			displayHighestAmountOfStockForCoffee(node.getRightChild());
			if((node.getData().getName().equals("Americano") || node.getData().getName().equals("Latte") || node.getData().getName().equals("Cappuccino")) && node.getData().getStock() > amountStock) {
				amountStock = node.getData().getStock();
				name = node.getData().getName();
			}
			displayHighestAmountOfStockForCoffee(node.getLeftChild());
		}
	}
	
	public static void removeFood(BinaryNode<Food> node) {
		if(node != null) {
			removeFood(node.getLeftChild());
			if (node.getData().getPrice() > 80) {
				binarySearchTreeForFood.removeEntry(rootNode2, node.getData());
				System.out.println(arrangeLine2(node, 23));
			}
			removeFood(node.getRightChild());
		}
	}
	
	public static void removeRestaurant(BinaryNode<Restaurant> node) {
		if(node != null) {
			removeRestaurant(node.getLeftChild());
			if (node.getData().getRating() < 8.0) {
				binarySearchTreeForRestaurant.removeEntry(rootNode1, node.getData()); 
				System.out.println(arrangeLine(node, 23) + "     Removed");
			}
			removeRestaurant(node.getRightChild());
		}
	}
	
	public static void updatePrice(BinaryNode<Food> node) {
		if(node != null) {
			updatePrice(node.getLeftChild());
			double amountOfIncrease = node.getData().getPrice() * 0.2;
			node.getData().updatePrice(amountOfIncrease);
			updatePrice(node.getRightChild());
		}
	}
	
	public static void updateStock(BinaryNode<Food> node) {
		if(node != null) {
			updateStock(node.getLeftChild());
			int amountOfDecrease = -1 * (node.getData().getStock() / 2);
			node.getData().updateStock(amountOfDecrease);
			updateStock(node.getRightChild());
		}
	}
	
	public static String arrangeLine(BinaryNode<Restaurant> node, int len) {
		String newStr = node.getData().getName();
		while (newStr.length() < len) {
			newStr += " ";
		}
		newStr += node.getData().getRating();
		return newStr;
	}
	
	public static String arrangeLine2(BinaryNode<Food> node, int len) {
		String newStr = node.getData().getName();
		while (newStr.length() < len) {
			newStr += " ";
		}
		newStr += String.format("%.2f", node.getData().getPrice());
		while (newStr.length() < 31) {
			newStr += " ";
		}
		newStr += "Removed";
		return newStr;
	}
	
	public static String arrangeLen(BinaryNode<Food> node, int len) {
		String newStr = node.getData().getName();
		while (newStr.length() < 25) {
			newStr += " ";
		}
		newStr += String.format("%.2f", node.getData().getPrice());
		while (newStr.length() < len) {
			newStr += " ";
		}
		newStr += node.getData().getStock();
		return newStr;
	}

	public static void printStatue(int value, String name) {
		while (name.length() < 34) {
			name += " ";
		}
		System.out.println(name + value);
	}
	
}
