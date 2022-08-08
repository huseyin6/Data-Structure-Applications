package CENG112_HW2_G32;

import java.util.Arrays;

public class WareHouse<T> {
	
	private T[] furnitures;  // Array of stack entries
	private int topIndex;    // Index of top entry
	private boolean isInitiliazed = false;
	private static final int DEFAULT_CAPACITY = 100;
	private static final int MAX_CAPACITY = 1000;
	
	public WareHouse() {
		this(DEFAULT_CAPACITY);	
	} // end default constructor

	@SuppressWarnings("unchecked")
	public WareHouse(int initialCapacity) {
		furnitures = (T[]) new Object[initialCapacity];
		isInitiliazed = true;
		topIndex = -1;
	}
	
	public void  push(T product) throws MaxCapacityException, NotInitializedException {
		checkInitialization();
		ensureCapacity();
		furnitures[topIndex + 1] = product;
		topIndex++;
	} // end push
	
	private void ensureCapacity() throws MaxCapacityException {
		if(topIndex == furnitures.length - 1) {
			int newLength = 2 * furnitures.length; // If array is full, double its size
			checkCapacity(newLength);
			furnitures = Arrays.copyOf(furnitures, newLength);
		}
	} 
	
	public T pop() throws NotInitializedException, EmptyDataException {
		checkInitialization();
		if(isEmpty()) {
			throw new EmptyDataException("The Warehouse is empty");
		}
		else {
			T removedProduct = furnitures[topIndex];
			furnitures[topIndex] = null;
			topIndex--;
			return removedProduct;
		}
	}
	
	public T peek() throws EmptyDataException, NotInitializedException {
		checkInitialization();
		if(isEmpty()) {
			throw new EmptyDataException("The Warehouse is empty");	
		}else {
			return furnitures[topIndex];
		}
	}
	
	public boolean isEmpty() {
			return topIndex == -1;
	}
	
	public boolean isFull() {
			return topIndex == furnitures.length - 1;
	}
	
	public void clear() {
		for (int i = 0; i <= topIndex; i++) {
			furnitures[i] = null;
			topIndex = -1;
		}
	}
	
	private void checkCapacity(int newCapacity) throws MaxCapacityException {
		if (newCapacity>MAX_CAPACITY) {
			throw new MaxCapacityException("Max Capacity is exceeded.");
		}
	}
	
	private void checkInitialization() throws NotInitializedException {
		if (isInitiliazed==false) {
			throw new NotInitializedException("Warehouse is not initialized.");
		}
	}

}