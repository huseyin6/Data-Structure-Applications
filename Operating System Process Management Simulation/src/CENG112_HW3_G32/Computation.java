package CENG112_HW3_G32;

public class Computation {
	
	private int id;  // unique computation id in [1,1000]
	private IProcess process;   // the process that makes computation request
	private int occupation; // needed time for the computation
	private Computation next;
	
	public Computation (int id, IProcess process, int occupation) { 
		this.id=id;
		this.process=process;
		this.occupation=occupation;
	}
	
	public String toString() {
		return "P"+ id + "," + process.getType() + "," + occupation + "ns" + " <- ";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public IProcess getProcess() {
		return process;
	}
	
	public void setpProcess(IProcess process) {
		this.process = process;
	}
	
	public int getOccupation() {
		return occupation;
	}
	
	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}
	
	public Computation getNext() {
		return next;
	}
	
	public void setNext(Computation next) {
		this.next = next;
	}
	
	
}
