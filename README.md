          ___                       ___           ___           ___     
         /\  \          ___        /\  \         /\__\         |\__\    
        /::\  \        /\  \      /::\  \       /:/  /         |:|  |   
       /:/\:\  \       \:\  \    /:/\ \  \     /:/__/          |:|  |   
      /::\~\:\  \      /::\__\  _\:\~\ \  \   /::\__\____      |:|__|__ 
     /:/\:\ \:\__\  __/:/\/__/ /\ \:\ \ \__\ /:/\:::::\__\     /::::\__\
     \/_|::\/:/  / /\/:/  /    \:\ \:\ \/__/ \/_|:|~~|~       /:/~~/~   
        |:|::/  /  \::/__/      \:\ \:\__\      |:|  |       /:/  /     
        |:|\/__/    \:\__\       \:\/:/  /      |:|  |       \/__/      
        |:|  |       \/__/        \::/  /       |:|  |                  
         \|__|                     \/__/         \|__|   
    

An unofficial Risk-like game where players place Resources/Troops on spaces on a map, attempting to gain control of Countries in order to end up in control of the entire Map.


## Currently working


 * Player
    - basic information
    - can add spots, countries, and resources
 * Country
    - basic information
    - can claim country
    - checks for a player who owns all spaces in the country
 * Board
    - basic information
 * Spot
    - basic information
 * States
 	- basic state design pattern implemented
 * Risky
    - Holds the very simple game loop
    - Needs a lot of work
 * UI
    - Polygon map generation
    - Basic UI
    - Game loop integration

## TODOs

    Risky.java                  |23 col 8| // TODO(david): remove this
    Risky.java                  |37 col 8| // TODO(david): see about moving this out
    Risky.java                  |46 col 7| //TODO(david): remove this
    Risky.java                  |64 col 8| * TODO(david): remove this if unused
    Risky.java                  |101 col 8| * TODO(david): generalize this to load a random board/player chosen board
    Risky.java                  |102 col 8| * TODO(david): create spots that connect intercontinentally
    Risky.java                  |109 col 12| // TODO(david): consolidate the board loops
    Risky.java                  |111 col 16| // TODO(david): make more options
    Risky.java                  |133 col 12| // TODO(david): clean this up a bit
    Risky.java                  |167 col 8| * TODO(david): fix this to work with other gui
    Risky.java                  |174 col 12| // TODO(david): make this dynamic?
    Risky.java                  |177 col 12| // TODO(david): find a better solution for player stages
    Risky.java                  |228 col 12| // TODO(david); find a way to clean this out after tests
    Risky.java                  |234 col 16| // TODO: determine how to connect disjointed countries
    Risky.java                  |253 col 15| //TODO(david): add more cases to remove wrong input
    Risky.java                  |268 col 16| // TODO: implement combat from UI
    Risky.java                  |272 col 16| // TODO: implement resource management from UI
    Risky.java                  |276 col 16| // TODO: test that winning works
    Risky.java                  |413 col 8| * TODO(david): is this a duplicate function?
    Risky.java                  |438 col 8| * TODO(david): see if this is really necessary
    common/Board.java           |76 col 8| * TODO(david): make this able to work with setting a position to empty
    common/Country.java         |15 col 8| * TODO: create country creator that specifies resources
    common/PerlinNoise.java     |8 col 4| * TODO(add proper risk map generation)
    common/PerlinNoise.java     |9 col 4| * TODO(comment all functions)
    common/Player.java          |22 col 12| // TODO(david): set initial resources to be based on number of players
    common/Player.java          |85 col 8| * TODO: check if we need to keep track of this here
    common/Player.java          |94 col 8| * TODO: check if we need to keep track of this here
    common/Spot.java            |129 col 8| * TODO: see if this is used, remove it properly
    common/Spot.java            |138 col 8| * TODO: see if this is used, remove it properly
    common/Spot.java            |147 col 8| * TODO: see if this is used, remove is properly
    common/Spot.java            |222 col 8| * TODO: check if used anywhere and remove properly
    common/Spot.java            |247 col 12| // TODO(david): fix this. currently assumes the coords are a spot on the board
    ui/BoardPanel.java          |63 col 20| // TODO: outline current players spots in some color
    ui/BoardPanel.java          |64 col 20| // TODO: show resources as number on spots
    ui/BoardPanel.java          |158 col 8| * TODO: see if this can fully replace the above function
    ui/PlayerPanel.java         |19 col 8| * TODO: add action listener
    ui/RiskyGUI.java            |41 col 12| // TODO(david): have board selection here?
    ui/RiskyGUI.java            |86 col 12| // TODO(david): check if this is necessary for the GUI
    ui/RiskyGUI.java            |93 col 16| // TODO(david): find proper way to close
    ui/RiskyGUI.java            |106 col 16| // TODO(david): simplify this call?
    ui/RiskyGUI.java            |139 col 28| // TODO(david): end turn if not enough resources in spots
    ui/RiskyGUI.java            |156 col 16| // TODO(david): move these back to their own function?
    ui/RiskyGUI.java            |166 col 16| // TODO(david): reset player's choices
    ui/RiskyGUI.java            |197 col 24| // TODO(david): limit resources properly in attack mode
    ui/RiskyGUI.java            |242 col 12| // TODO(david): ensure you can't select without enough resources!
    ui/RiskyGUI.java            |256 col 12| // TODO(david): have each spot display number of resources on it?
    ui/menu/MenuGUI.java        |12 col 4| // TODO(adam): Legit everything. Why did you even push this?
    ui/menu/MenuGUI.java        |51 col 16| // TODO(adam): save menu selections
    
Future Aspects
--------------


 * Map generation via Perlin/Simplex Noise
 * All of those damn TODOs in Risky.java
 * Swag.jpg
