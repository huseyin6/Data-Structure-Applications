package CENG112_HW3_G32;
import java.util.Random;

public class Main {
	private static Computation[] arrayForFirstSim = new Computation[3];
	private static Computation[] arrayForSecondSim = new Computation[5];
	private static Computation[] arrayForThirdSim = new Computation[10];
	private static ComputationQueue queueForFirstSim= new ComputationQueue();
	private static ComputationQueue queueForSecondSim= new ComputationQueue();
	private static ComputationQueue queueForThirdSim= new ComputationQueue();
	private static ComputationQueueList queueList = new ComputationQueueList();
	
	public static void main(String[] args) {
		generateSimulation(3, arrayForFirstSim, queueForFirstSim);
		generateSimulation(5, arrayForSecondSim, queueForSecondSim);
		generateSimulation(10, arrayForThirdSim, queueForThirdSim);
		reportSimulation(3, arrayForFirstSim, queueForFirstSim);
		reportSimulation(5, arrayForSecondSim, queueForSecondSim);
		reportSimulation(10, arrayForThirdSim , queueForThirdSim );
	}
	
	public static void generateSimulation(int processNum, Computation[] array, ComputationQueue queue) {
		if (processNum == 3) {
			array = arrayForFirstSim;
			queue = queueForFirstSim;
		}else if (processNum == 5) {
			array = arrayForSecondSim;
			queue = queueForSecondSim;
		}else {
			array = arrayForThirdSim;
			queue = queueForThirdSim;
		}
		String[] types = {"High", "Normal" , "Low"};
		Random randomNumber = new Random();
		while (processNum > 0) {
			int randomType = randomNumber.nextInt(3);
			int randomOccupationTime = randomNumber.nextInt(10) + 1;
			IProcess newProcess = new Process(types[randomType]);
			Computation newSimulation = new Computation(processNum, newProcess, randomOccupationTime);
			simulationToArray(array, newSimulation);
			processNum--;
		}
		sort(array);  // For priority
		arrayToQueue(array,queue); 
		queueList.append(queue); // Append the queue to the ComputationQueueList
	}
	
	public static void reportSimulation(int compQueue, Computation[] array, ComputationQueue queue) {
		ComputationQueue firstComputation = queueList.getHead();
		Computation firstComp = firstComputation.getHead();
		int totalTimeForHigh = 0;
		int totalTimeForNormal = 0;
		int totalTimeForLow = 0;
		
		int simulationNumber;
		if (compQueue == 3) {
			simulationNumber = 1;
		}else if (compQueue == 5) {
			simulationNumber = 2;
		}else {
			simulationNumber = 3; 
		}
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("Simulation Number: " + simulationNumber);
		System.out.print("Computation Queue: ");
		int i = 0;
		while (i < compQueue) {
			System.out.print(array[i]);
			i++;
			if (i == compQueue) {
				System.out.println();
			}
		}
		
		System.out.println("Total number of computations: " + compQueue);
		System.out.println();
		System.out.println("Total waiting time: " + queue.calculateWaiting());
		int averageWaitingTime = queue.calculateWaiting() / queue.getQueueLength();
		System.out.println("Average waiting time: " + averageWaitingTime);
		System.out.println();
		queue.countTypes();
		System.out.println("Total number of computations for High: " + queue.getCountHigh());
		System.out.println("Total number of computations for Normal: " + queue.getCountNormal());
		System.out.println("Total number of computations for Low: " + queue.getCountLow());
		System.out.println();
		
		boolean isThereNormalProcess = false;
		boolean isThereLowProcces = false;
		for (int j = 0; j < queue.getQueueLength() - 1; j++) {
			if (firstComp.getNext().getProcess().getType() == "High") {
				totalTimeForHigh += firstComp.getOccupation();
			}else if (firstComp.getNext().getProcess().getType() == "Normal") {
				totalTimeForNormal += firstComp.getOccupation();
				isThereNormalProcess = true;
			}else {
				totalTimeForLow += firstComp.getOccupation();
				isThereLowProcces = true;
				
			}
			firstComp = firstComp.getNext();
		}
		if (isThereLowProcces) {
			totalTimeForLow += totalTimeForHigh + totalTimeForNormal;
		}
		if (isThereNormalProcess) {
			totalTimeForNormal += totalTimeForHigh;
		}
		System.out.println("Total waiting time for High: " + totalTimeForHigh);
		if (totalTimeForHigh != 0) {
			System.out.println("Average waiting time for High: " + (totalTimeForHigh / queue.getCountHigh()));
		}else {
			System.out.println("Average waiting time for High: " + 0);
		}
		System.out.println();
		System.out.println("Total waiting time for Normal: " + totalTimeForNormal);
		if (totalTimeForNormal != 0) {
			System.out.println("Average waiting time for Normal: " + (totalTimeForNormal / queue.getCountNormal()));
		}else {
			System.out.println("Average waiting time for Normal: " + 0);
		}
		System.out.println();
		System.out.println("Total waiting time for Low: " + totalTimeForLow);
		if (totalTimeForLow != 0) {
			System.out.println("Average waiting time for Low: " + (totalTimeForLow / queue.getCountLow()));
		}else {
			System.out.println("Average waiting time for Low: " + 0);
		}
		queueList.removeCQ(); // Remove the computation queue from the list
	}
	

	
	public static void simulationToArray(Computation[] compArr, Computation newComp) {
		for (int i=0; i < compArr.length; i++) {
			if (compArr[i] == null) {
				compArr[i] = newComp;
				break;
			}
		}
	}
	
	public static void sort(Computation[] arr) {
		int numberOfEntries=0;
		for (int i=0; i < arr.length; i++) {
			if (arr[i] != null) {
				numberOfEntries += 1;
			}else {
				break;
			}
		}
		
        Computation temp = null;
        for(int i=0; i < numberOfEntries; i++){
        	for(int j=1; j < (numberOfEntries-i); j++){
        		if(arr[j-1].getProcess().getPriority() > arr[j].getProcess().getPriority()){  
        			 //swap elements  
        			 temp = arr[j-1];  
        			 arr[j-1] = arr[j];  
        			 arr[j] = temp;  
        		}
        	}
        }
        for(int i=0; i < numberOfEntries; i++){
        	for(int j=1; j < (numberOfEntries-i); j++){
        		if (arr[j-1].getProcess().getPriority() == arr[j].getProcess().getPriority()) { 
        			if (arr[j].getOccupation() > arr[j-1].getOccupation()) {
              			 //swap elements  
              			 temp = arr[j-1];  
              			 arr[j-1] = arr[j];  
              			 arr[j] = temp; 
        			}
     
        		}
        	}
        }
    }
	
	public static void arrayToQueue(Computation[] arrComp, ComputationQueue queueComp) {
		for(int i=0; i < (arrComp.length); i++) {
			if(arrComp[i] != null) {
				queueComp.insert(arrComp[i]);
			}
		}
	}
	
}
