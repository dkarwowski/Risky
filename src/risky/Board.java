package risky;

public class Board {

	private String name = "Untitled Board";
	private int territories = 42; // default number of territories is 42
	
	public Board(String setName, int setTerritories){
		this.name = setName;
		this.territories = setTerritories;
	}
	
//--Getters Start----------------------------------------------------------
	
	public String getName(){
		return this.name;
	}
	public int getTerritories(){
		return this.territories;
	}
	
//--Getters End------------------------------------------------------------

}

