package risky;

import java.util.ArrayList;


public class Country {
	private String name = "";
	private Player owner = null;
	private int resources;
	
    private ArrayList<Spot> spotsInCountry;
    
    public Country(String setName, int initialResources){
    	this.name = setName;
    	
    	// initialize the array list of spots for this country
    	this.setSpotsInCountry(new ArrayList<Spot>());
    	
    	this.setResources(initialResources);
    	
    }

//--Getters Start-------------------------------------------------------------

    public String getName(){
    	return this.name;
    }
    
    public Player getOwner(){
    	return this.owner;
    }
    
	public ArrayList<Spot> getSpotsInCountry() {
		return spotsInCountry;
	}
	
	public int getResources() {
		return resources;
	}
//--Getters End---------------------------------------------------------------    
//--Setters Start-------------------------------------------------------------
	
	public void setResources(int resourceCount){
		this.resources = resourceCount;
	}

//--Getters End---------------------------------------------------------------  
//--Game Related Functions Start--------------------------------------------------------------- 
    
    public void setOwner(Player setOwner){
    	this.owner = setOwner;
    }
    
    public void updateResources(int newResourceCount){
    	this.resources += newResourceCount;
    }

	public void setSpotsInCountry(ArrayList<Spot> spotsInCountry) {
		this.spotsInCountry = spotsInCountry;
	}
	
}
