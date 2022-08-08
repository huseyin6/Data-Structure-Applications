package CENG112_HW3_G32;

public class ComputationQueueList {
	private ComputationQueue head;
	private int listLength;
	
	public ComputationQueueList() {
		head = null;
		listLength = 0;
	}
	
	public ComputationQueueList(ComputationQueue head) {
		this.head = head;
		listLength = 0;
	}
	
	public ComputationQueue getHead() {
		return head;
	}
	
	public void setHead(ComputationQueue headCompQ) {
		head = headCompQ;
	}

	public void append(ComputationQueue CQ) {
		if (head == null) {
			head = CQ;
			listLength += 1;
		}else {
			ComputationQueue lastComputationQueue = getLast();
			lastComputationQueue.setNext(CQ);
			listLength+=1;
		}
	}
	
	public int getLength() {
		return listLength;
	}
	
	public ComputationQueue getLast() {
		ComputationQueue nextComputationQueue = head;
		for(int i=0; i < listLength-1; i++) {
			nextComputationQueue = nextComputationQueue.getNext();
		}
		return nextComputationQueue;
	}
	
	public ComputationQueue removeCQ() {
		ComputationQueue removedCQ = null;
		head = head.getNext();
		listLength -= 1;
		return removedCQ;
	}
	
	public boolean isEmpty() {
		return listLength == 0;
	}
	
	public boolean clear() {
		head = null;
		return true;
	}
	
	public String toString() {
		String str = "";
		ComputationQueue tempHead = head;
		for (int i=0; i<listLength; i++) {
			str += tempHead.toString() + "\n";
			tempHead = tempHead.getNext();
		}
		return str;
	}
	
}
