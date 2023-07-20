package models;

public class EntrePreneur implements Comparable<EntrePreneur>{
/*
 *Entrepreneur node class
 */
	private String entretID;
	private String name;
	private String business;
	private int profit;
	private Positions pos;
	
	public EntrePreneur(String entreID,String name,String business, int profit,Positions pos) {
		this.entretID = entreID;
		this.name = name;
		this.business = business;
		this.profit = profit;
		this.pos = pos;
	} 
	
	//getters
	public String getEntreID() {
		return entretID;
	}
	public String getName() {
		return name;
	}
	
	
	public String getBusiness() {
		return business;
	}
	
	public int getProfit() {
		return profit;
	}
	public Positions getPos() {
		return pos;
	}
	

	@Override

	public String toString() {
		String id = "";
		id = entretID;
		return id;
	}

	public int compareTo(EntrePreneur s) {
		// TODO Auto-generated method stub
		return 0;
	}
}
