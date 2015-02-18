package risky.common;

import java.util.ArrayList;


public class Country {
    private String name = "";
    private Player owner = null;
    private int resources;

    private ArrayList<Spot> spotsInCountry;

    public Country(String setName){
        this.name = setName;

        // initialize the array list of spots for this country
        this.spotsInCountry = new ArrayList<Spot>();

        // initial resources the country has
        this.resources = 5;

    }

//--Getters Start------------------------------------------------------------

    public String getName(){
        return this.name;
    }

    public Player getOwner(){
        return this.owner;
    }

    public ArrayList<Spot> getSpotsInCountry() {
        return spotsInCountry;
    }

    public int getResources(){
        return this.resources;
    }

//--Getters End---------------------------------------------------------------     
//--Game Related Functions Start----------------------------------------------

    public void setSpotsInCountry(Spot spot) {
        this.spotsInCountry.add(spot);
    }

    public void claimCountry(){
        if(doesPlayerOwnAllSpots() == true){
            this.owner = spotsInCountry.get(0).getPlayer();
            // once turns are in place, add resource distribution
        }
    }

    public boolean doesPlayerOwnAllSpots(){
        Player player = spotsInCountry.get(0).getPlayer();
        int counter = 0;
        for(int i = 0; i < spotsInCountry.size(); i++){
            if(spotsInCountry.get(i).getPlayer() == player){
                counter++;
            }else{
                counter = counter + 0;
            }
        }
        if(counter == spotsInCountry.size()){
            return true;
        }else{
            return false;
        }
    }
//--Game Related Functions End------------------------------------------------ 
}
