package CENG112_HW3_G32;

public class ComputationQueue {
	
	private Computation head;
	private int queueLength;
	private int totalWaitingTime;
	private ComputationQueue next;
	private int countHigh = 0;
	private int countNormal = 0;
	private int countLow = 0;
	
	public ComputationQueue() {
		head = null;
		queueLength = 0;
		totalWaitingTime = 0;
		next=null;
	}
	
	public ComputationQueue(Computation head) {
		this.head = head;
		queueLength = 0;
		totalWaitingTime = 0;
		next = null;
	}
	
	public void insert(Computation newProcess) {
		if (head == null) {
			head = newProcess;
			queueLength += 1;
			totalWaitingTime = newProcess.getOccupation();
		}else {
			Computation lastComputation = getLast();
			lastComputation.setNext(newProcess);
			queueLength += 1;
			totalWaitingTime += newProcess.getOccupation();
		}
	}
	
	public Computation remove() {
		Computation tempHead = head;
		head = head.getNext();
		queueLength -= 1;
		totalWaitingTime -= tempHead.getOccupation();
		return tempHead;
	}
	
	public boolean isEmpty() {
		return queueLength == 0;
	}
	
	public boolean clear() {
		head = null;
		queueLength = 0;
		totalWaitingTime = 0;
		return true;
	}
	
	public Computation getLast() {
		Computation nextComputation = head;
		for(int i=0; i<queueLength-1; i++) {
			nextComputation = nextComputation.getNext();
		}
		return nextComputation;
	}
	
	public Computation getHead() {
		return head;
	}
	
	public void setHead(Computation head) {
		this.head = head;
	}
	
	public int getQueueLength() {
		return queueLength;
	}
	
	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}
	
	public int getTotalWaitingTime() {
		return totalWaitingTime;
	}
	
	public void setTotalWaitingTime(int totalWaitingTime) {
		this.totalWaitingTime = totalWaitingTime;
	}
	
	public ComputationQueue getNext() {
		return next;
	}
	
	public void setNext(ComputationQueue next) {
		this.next = next;
	}
	
	public void countTypes() {
		Computation tempComputation = head;
		for (int i=0; i < queueLength; i++) {
			if (tempComputation.getProcess().getType().equals("High")) {
				countHigh++;
			}else if (tempComputation.getProcess().getType().equals("Normal")) {
				countNormal++;
			}else {
				countLow++;
			}
			tempComputation = tempComputation.getNext();
		}
	}
	
	public int getCountHigh() {
		return countHigh;
	}
	
	public int getCountNormal() {
		return countNormal;
	}
	
	public int getCountLow() {
		return countLow;
	}
	
	
	public int calculateWaiting() {
		int waitingTime = 0;
		Computation tempComputation = head;
		for (int i=0; i<queueLength; i++) {
			if (i == queueLength - 1) {
				break;
			}
			waitingTime += tempComputation.getOccupation();
			tempComputation = tempComputation.getNext();
		}
		return waitingTime;
	}
	
}
