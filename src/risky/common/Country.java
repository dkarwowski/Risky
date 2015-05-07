package risky.common;

import java.util.ArrayList;


public class Country {
    private String name = "";
    private Player owner = null;
    private int resources;

    private ArrayList<Spot> spotsInCountry;

    /**
     * Initialize the Country with just a name.
     * TODO: create country creator that specifies resources
     * @param setName String with the Country's name
     */
    public Country(String setName){
        this.name = setName;

        // initialize the array list of spots for this country
        this.spotsInCountry = new ArrayList<Spot>();

        // initial resources the country has
        this.resources = 5;

    }

    //--Getters Start------------------------------------------------------------

    /**
     * Get the name of the country
     * @return String with the name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the player who owns the country (if any)
     * @return Player object or null
     */
    public Player getOwner(){
        return this.owner;
    }

    /**
     * Get the Spots found in the country
     * @return ArrayList of all spots
     */
    public ArrayList<Spot> getSpotsInCountry() {
        return spotsInCountry;
    }

    /**
     * Get the number of resources this country can grant per turn
     * @return integer with resource
     */
    public int getResources(){
        return this.resources;
    }

    //--Getters End---------------------------------------------------------------     
    //--Game Related Functions Start----------------------------------------------

    /**
     * Add a spot to the country
     * @param spot Spot instance to add
     */
    public void addSpot(Spot spot) {
        this.spotsInCountry.add(spot);
    }

    /**
     * Check and set if the country is owned by someone
     */
    public void claimCountry(){
        if(this.doesPlayerOwnAllSpots() == true){
            this.owner = this.spotsInCountry.get(0).getPlayer();
            this.spotsInCountry.get(0).getPlayer().addCountry(this);
        }
    }

    /**
     * Go through the country's spots and check if a single player owns them all
     * @return boolean regarding the player owning all
     */
    public boolean doesPlayerOwnAllSpots(){
        Player player = spotsInCountry.get(0).getPlayer();
        if (player == null) return (false); // fixes problem with claiming
        
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

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (!(object instanceof Country))
            return false;
        Country other = (Country)object;
        return (this.getName().equals(other.getName()));
    }
}
