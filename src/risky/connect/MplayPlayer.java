package risky.connect;
import java.util.ArrayList;

import risky.common.*;
import risky.common.Player;
public class MplayPlayer implements Runnable {

	private int id; 
	private String name; 
	private int availResources; 
	private ArrayList<Spot> spotsOwned; 
	private ArrayList<Country> countriesOwned; 

	private String connectedFrom;


	public MplayPlayer(int ID, String Name, int Resources, ArrayList<Spot> OwnedSpots, ArrayList<Country> OwnedCountries){
		this.id = ID;
		this.name = Name;
		this.availResources = Resources;
		this.spotsOwned = OwnedSpots;
		this.countriesOwned = OwnedCountries;
	}
 
	public void run() {
		// update to tie player with packet types, server and cliuent

	}

}
