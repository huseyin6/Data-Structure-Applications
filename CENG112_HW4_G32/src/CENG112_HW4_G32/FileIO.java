package CENG112_HW4_G32;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
	public BinarySearchTreeInterface<Restaurant> ReadRestaurant(){
		
		BufferedReader reader;
		BinarySearchTreeInterface<Restaurant> binarySearchTreeForRestaurant = new BinarySearchTree<>();
		
		try {
			reader = new BufferedReader(new FileReader("src/CENG112_HW4_G32/CENG112_HW4.csv"));
			String line = reader.readLine();
			line = reader.readLine();
			
			while (line != null) {
				Restaurant newRestaurant = null;
				String[] Arr = line.split(",");

				String restaurantName= Arr[3];
				double rating = Double.parseDouble(Arr[4]);
				String cuisine = Arr[5];
				int deliveryTime = Integer.parseInt(Arr[6]);
				newRestaurant = new Restaurant(restaurantName,rating,cuisine,deliveryTime);
				
				binarySearchTreeForRestaurant.add(newRestaurant);
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return binarySearchTreeForRestaurant;
	}
	
	public BinarySearchTreeInterface<Food> ReadFood() {
		
		BufferedReader reader;
		BinarySearchTreeInterface<Food> binarySearchTreeForFood = new BinarySearchTree<>();
		
		try {
			reader = new BufferedReader(new FileReader("src/CENG112_HW4_G32/CENG112_HW4.csv"));
			String line = reader.readLine();
			line = reader.readLine();
			
			while (line != null) {
				Food newFood = null;
				String[] Arr = line.split(",");
				
				String foodName = Arr[0];
				double price = Double.parseDouble(Arr[1]);
				int stock = Integer.parseInt(Arr[2]);
				newFood = new Food(foodName,price,stock);
	
				binarySearchTreeForFood.add(newFood);
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return binarySearchTreeForFood;
	}
	
}
