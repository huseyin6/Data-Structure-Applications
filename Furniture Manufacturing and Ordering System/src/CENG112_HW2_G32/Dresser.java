package CENG112_HW2_G32;

public class Dresser implements IProduct {
	
	private boolean isManufactured = false;
	private boolean isStored = false;
	private boolean isSold = false;
	private int amountInFactoryLine = 0;
	private int amountInWareHouse = 0;
	private int amountSold = 0;
	
	public boolean isManufactured() {
		amountInFactoryLine++;
		isManufactured = true;
		return isManufactured;
	}

	public boolean isStored() {
		amountInFactoryLine--;
		amountInWareHouse++;
		isStored = true;
		return isStored;
	}

	public boolean isSold() {
		amountInWareHouse--;
		amountSold++;
		isSold = true;
		return isSold;
	}
	
	public String toString() {
		return "Dresser";
	}
	
	public int AmountInFactory() {
		return amountInFactoryLine;
	}
	
	public int AmountInWareHouse() {
		return amountInWareHouse;
	}
	
	public int AmountSold() {
		return amountSold;
	}	
}