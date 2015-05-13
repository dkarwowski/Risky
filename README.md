# Risky

An unofficial Risk-like game where players place Resources/Troops on spaces on a map, attempting to gain control of Countries in order to end up in control of the entire Map.

## Currently Working

* main menu
    * can create a new game
    * can quit app
* setup
    * can choose map
    * can choose number of players
    * continue and cancel working
* game
    * init framework set
    * Board
        * current necessary function present
    * Player
        * current necessary function present
        
## Goals

- [ ] User board creation
    - [ ] display a board on the screen
    - [ ] gather input from clicking on board
    - [ ] field user input to make new spots on a board
    - [ ] create the board from the user input
        - [ ] countries exist
        - [ ] spots have coordinates
        - [ ] spots have exits
        - [ ] ensure spots are fully connected
    - [ ] write information to a file
        - [ ] board gets named
        - [ ] board spots are written
        - [ ] spots write exits
- [ ] Get a board loaded
    - [x] create a basic spot
    - [x] create a basic coordinate system
    - [x] create a basic country
    - [ ] load board from .map files
        - [ ] load spots
        - [ ] load countries
    - [ ] create graph system for spots
        - [ ] implement exits for each spot
        - [ ] create algorithm to find paths
- [ ] Get a basic player
    - [ ] create a resource system
- [ ] Display a simple board
    - [ ] display spots
    - [ ] display country borders
    - [ ] display spot connection overseas
- [ ] Display buttons and information on board
    - [ ] end turn button
    - [ ] cancel move button (clear selections)
    - [ ] current player tooltip
- [ ] Implement user input management
    - [ ] spot selection for placing resources
    - [ ] spot selection for attacking
        - [ ] select origin
        - [ ] select destination
        - [ ] path verification for spots far away
    - [ ] cancel button working
    - [ ] end turn user switching
- [ ] Implement game logic
    - [ ] initializing resources
    - [ ] board setup
        - [ ] place resources
            - [ ] choose how many resources to place
            - [ ] put them down on empty spots
        - [ ] determine when setup finished
    - [ ] claim country
        - [ ] adjust player resources
    - [ ] moving resources
        - [ ] between YOUR spots
    - [ ] attacking
        - [ ] limit resources used
        - [ ] proper algorithm for removing resources
        - [ ] claim spot
        - [ ] check country claims
    - [ ] end turn
        - [ ] button press ends turn prematurely
        - [ ] ends turn when no moves possible
    - [ ] end game
        - [ ] forfeit from other players
            - [ ] redistribute resources when forfeit
            - [ ] determine win state
        - [ ] one player owns all
- [ ] Implement in-game settings menu
    - [ ] remove player from game (forfeit)
    - [ ] reset the game
        - [ ] Implement reset functions
    - [ ] change player colors
    - [ ] change player names

### Extra functionality

* Menu
    - [ ] Settings
        - [ ] color schemes
        - [ ] map types
        - [ ] hints
* Setup
    - [ ] players choose their own names
    - [ ] board preview shows
* Game
    - [ ] Tooltips/Hints
    - [ ] AI to play against
    - [ ] networking (add whole todo list if implemented)
