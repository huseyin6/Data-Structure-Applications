package CENG112_HW3_G32;

public class Process implements IProcess {
	
	private String type;
	private int priority;
	
	public Process(String type) {
		
		this.type=type;
		
		if (type == "High") {
			this.priority = 1;
		}else if (type == "Normal") {
			this.priority = 2;
		}else if (type == "Low") {
			this.priority = 3;
		}
	}
	
	public String getType() {
		return type;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public String toString() {
		return type + "," + priority;
	}
	
}
