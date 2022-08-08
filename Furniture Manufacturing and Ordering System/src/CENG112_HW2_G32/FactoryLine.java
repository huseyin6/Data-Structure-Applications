package CENG112_HW2_G32;

public class FactoryLine<T> {
	
	private T[] furnitures; // Circular array of queue entries and one used location
	private int frontIndex;
	private int backIndex;
	private boolean isInitialized = false;
	private static final int DEFAULT_CAPACITY = 100;
	private static final int MAX_CAPACITY=1000;
	
	public FactoryLine() {
		this(DEFAULT_CAPACITY);
	} // end default constructor
	
	@SuppressWarnings("unchecked")
	public FactoryLine(int capacity) {
		furnitures = (T[]) new Object[capacity + 1];
		frontIndex = 0;
		backIndex = furnitures.length - 1;
		isInitialized = true;
	}
	
	public void enqueue(T newProduct) throws NotInitializedException, MaxCapacityException {
		checkInitialization();
		ensureCapacity();
		backIndex = (backIndex + 1) % furnitures.length;
		furnitures[backIndex] = newProduct;
	} // end enqueue
	
	public T dequeue() throws NotInitializedException {
		checkInitialization();
		T front =  null;
		if(!isEmpty()) {
			front = furnitures[frontIndex];
			furnitures[frontIndex] = null;
			frontIndex = (frontIndex + 1) % furnitures.length;
		}
		return front;
	}
	
	public T getFront() throws EmptyDataException, NotInitializedException {
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyDataException("Queue is empty.");
		}else {
			return furnitures[frontIndex];
		}
	}
	
	public boolean isFull() {
		return frontIndex == (backIndex + 2) % furnitures.length;
	}
	
	public boolean isEmpty() {
		return frontIndex == (backIndex + 1) % furnitures.length;
	}
	
	public void clear() {
		while (true){
			furnitures[frontIndex] = null;
			if (frontIndex == backIndex) {
				break;
			}
			frontIndex = (frontIndex + 1) % furnitures.length;
		}
	}
	
	public void displayProducts() {
		for (int i=0; i<furnitures.length; i++) {
			if (furnitures[i] != null) {
				System.out.println(furnitures[i].toString());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity() throws MaxCapacityException {
		if (isFull()) {
			T[] oldQueue = furnitures;
			int oldSize = oldQueue.length;
			int newSize = 2 * oldSize;
			checkCapacity(newSize);
			T[] tempQueue = (T[]) new Object[newSize];
			furnitures = tempQueue;
			for (int index=0; index < oldSize - 1; index++) {
				furnitures[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			}
			frontIndex = 0;
			backIndex = oldSize - 2;
		}
	}
	
	private void  checkInitialization() throws NotInitializedException {
		if (isInitialized == false) {
			throw new NotInitializedException("Factory Line is not initialized!");
		}
	}
	
	private void checkCapacity(int newCapacity) throws MaxCapacityException {
		if (newCapacity > MAX_CAPACITY) {
			throw new MaxCapacityException("Max Capacity is exceeded!");
		}
	} 

}
