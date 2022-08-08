package CENG112_HW2_G32;

public interface IProduct  {
	
	public boolean isManufactured();
	public boolean isStored();
	public boolean isSold();
	public int AmountInFactory();
	public int AmountInWareHouse();
	public int AmountSold();

}
