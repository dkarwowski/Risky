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
Risky.java                  |81 col 8| // TODO(david): generalize this to load a random board/player chosen board
Risky.java                  |82 col 8| // TODO(david): create spots that connect intercontinentally
Risky.java                  |88 col 12| // TODO(david): consolidate the board loops
Risky.java                  |90 col 16| // TODO(david): make more options
Risky.java                  |105 col 16| // TODO(david): Auto-generated catch block
Risky.java                  |113 col 12| // TODO(david): clean this up a bit
Risky.java                  |145 col 8| // TODO(david): fix this to work with other gui
Risky.java                  |151 col 12| // TODO(david): make this dynamic?
Risky.java                  |154 col 12| // TODO(david): find a better solution
Risky.java                  |161 col 12| // TODO(david): replace this with UI version
Risky.java                  |179 col 8| // TODO(david): move this properly.
Risky.java                  |196 col 12| // TODO(david); find a way to clean this out after tests
Risky.java                  |202 col 16| // TODO: make first move unique for both players
Risky.java                  |203 col 16| // TODO: make moves only possible to consecutive spots
Risky.java                  |204 col 16| // TODO: determine how to connect disjointed countries
Risky.java                  |223 col 15| //TODO(david): add more cases to remove wrong input
Risky.java                  |238 col 16| // TODO: deal with combat
Risky.java                  |242 col 16| // TODO: make player lose resources
Risky.java                  |243 col 16| // TODO: give player resources every turn
Risky.java                  |247 col 16| // TODO: test that this works
Risky.java                  |255 col 16| // TODO(david): remove this
Risky.java                  |260 col 7| //TODO(david): remove this temporary testing functions
Risky.java                  |265 col 8| // TODO(david): move this properly
Risky.java                  |270 col 8| // TODO(david): move this properly
Risky.java                  |280 col 8| // TODO(david): make player change a setting?
Risky.java                  |281 col 8| // TODO(david): ensure this is actually changing the board
Risky.java                  |379 col 46| Risky game = new Risky(false);// TODO(david): testing gui
common/Board.java           |229 col 8| * TODO(david): deal with combat
common/PerlinNoise.java     |8 col 4| * TODO(add proper risk map generation)
common/PerlinNoise.java     |9 col 4| * TODO(comment all functions)
common/Player.java          |22 col 12| // TODO(david): set initial resources to be based on number of players
common/Spot.java            |165 col 12| // TODO(david): fix this. currently assumes the coords are a spot on the board
ui/BoardPanel.java          |57 col 20| // TODO(david): make squares highlight per player properly
ui/BoardPanel.java          |58 col 20| // TODO(david): highlight squares based on player's colors
ui/BoardPanel.java          |85 col 12| // TODO(david): see about moving polygon drawing elsewhere
ui/BoardPanel.java          |99 col 8| // TODO(david): clean this out with proper board functionality?
ui/BoardPanel.java          |107 col 8| // TODO(david): move this properly?
ui/InfoPanel.java           |27 col 12| // TODO(david): add proper button listeners
ui/RiskyGUI.java            |41 col 12| // TODO(david): have board selection here?
ui/RiskyGUI.java            |60 col 8| // TODO(david): create players with colors?
ui/RiskyGUI.java            |87 col 12| // TODO(david): start using this properly again
ui/RiskyGUI.java            |94 col 16| // TODO(david): find proper way to close
ui/RiskyGUI.java            |107 col 16| // TODO(david): simplify this call?
ui/RiskyGUI.java            |140 col 28| // TODO(david): end turn if not enough resources in spots
ui/RiskyGUI.java            |157 col 16| // TODO(david): move these back to their own function?
ui/RiskyGUI.java            |167 col 16| // TODO(david): reset player's choices
ui/RiskyGUI.java            |172 col 8| // TODO(david): place this properly
ui/RiskyGUI.java            |199 col 24| // TODO(david): limit resources properly in attack mode
ui/RiskyGUI.java            |244 col 12| // TODO(david): ensure you can't select without enough resources!
ui/RiskyGUI.java            |258 col 12| // TODO(david): have each spot display number of resources on it?
ui/menu/MenuGUI.java        |12 col 4| // TODO(adam): Legit everything. Why did you even push this?
ui/menu/MenuGUI.java        |51 col 16| // TODO(adam): save menu selections
    
## TODOS
    
    
Future Aspects
--------------


 * Map generation via Perlin/Simplex Noise
 * All of those damn TODOs in Risky.java
 * Swag.jpg
