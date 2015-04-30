package risky;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Player;
import risky.common.Spot;
import risky.common.StateContext;
import risky.common.StatePlayer;
import risky.common.Statelike;
import risky.ui.RiskyGUI;

public class Risky {
    // TODO(david): remove this
    private RiskyGUI guiTest;

    private Board board;
    private StateContext stateContext;

    private Statelike[] playerStates;
    private int currentState;

    private Player playerWon = null;

    // Random number generation for combat
    private Random rand;

    //TODO(david): remove this
    private Scanner in;

    /**
     * Initialize the game and several of its variables
     * @param console Whether the game should be started with console or GUI
     */
    public Risky(boolean console) {
        this.rand = new Random();
        if(console)
            this.consoleInit();
        else
            this.guiInit(); // replace this with riskygui when working
    }

    /**
     * Initialize the game with a predetermined board. May be useful for game loading?
     * TODO(david): remove this if unused
     * @param _board the loaded board
     * @param _playerStates the game's player states
     */
    public Risky(Board _board, Statelike[] _playerStates) {
        this.board = _board;
        this.playerStates = _playerStates;
        this.stateContext = new StateContext(this.playerStates[0]);
        this.currentState = 0;
    }

    /**
     * Check if the game is over (a single player owns everything)
     * @return True or false, if there is a player who has won
     */
    public boolean checkPlayerWon() {
        playerWon = this.board.playerOwnsAll();
        return (playerWon != null);
    }

    /**
     * Get the winner if there is one
     * @return Player
     */
    public Player getWinner() {
        return playerWon;
    }

    /**
     * Get the state context from the game
     * @return StateContext instance
     */
    public StateContext getStateContext() {
        return (this.stateContext);
    }

    @Override
    public String toString() {
        String result = "";
        result += board.toString() + "\n";
        return (result);
    }

    /**
     * Load a specific board
     * TODO(david): generalize this to load a random board/player chosen board
     * TODO(david): create spots that connect intercontinentally
     */
    private void loadBoard() {
        String boardName;
        int width, height;
        String[] board;

        // TODO(david): consolidate the board loops
        try {
            // TODO(david): make more options
            Scanner loadBoard = new Scanner(new File("data/test3.map"));
            boardName = loadBoard.next();
            width = loadBoard.nextInt();
            height = loadBoard.nextInt();

            board = new String[width * height];
            int i = 0;
            while (loadBoard.hasNext())
                board[i++] = loadBoard.nextLine();
            while (i < width * height)
                board[i++] = null;

            loadBoard.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.in.close();
            return;
        }

        this.board = new Board(boardName, width, height);

        // TODO(david): clean this up a bit
        ArrayList<Country> countries = new ArrayList<Country>();
        for (int i = 0; i < width * height; ++i) {
            if (board[i] == null)
                break;
            if (board[i].length() < 1)
                continue;
            String[] pieces = board[i].split(" ");
            int xCart = Integer.parseInt(pieces[0]);
            int yCart = Integer.parseInt(pieces[1]);
            String countryName = pieces[2];
            Country thisCountry = new Country(countryName);

            boolean switched = false;
            for (Country country : countries) {
                if (country.equals(thisCountry)) {
                    thisCountry = country;
                    switched = true;
                    break;
                }
            }

            if (!switched)
                countries.add(thisCountry);

            Spot spot = new Spot(thisCountry, new Coords(xCart, yCart, false));
            thisCountry.addSpot(spot);

            this.board.setSpot(spot);
        }
    }

    /**
     * Initializes the GUI, by creating the various information it needs to run
     * TODO(david): fix this to work with other gui
     */
    public void guiInit() {
        this.loadBoard();

        this.guiTest = new RiskyGUI(this);
        if (this.guiTest.createPlayers()) {
        // TODO(david): make this dynamic?
            this.loadBoard();
            this.guiTest.setVisible(true);
        }
        else {
            this.guiTest.actionPerformed(new ActionEvent(
                        this.guiTest, 
                        ActionEvent.ACTION_PERFORMED, 
                        "userCommandQuit"));
        }
    }

    /**
     * Initialize the game through the console, everything runs in here
     */
    public void consoleInit() {
        this.in = new Scanner(System.in);

        System.out.print("Enter Player 1 Name: ");
        Player p1 = new Player(this.in.next(), 0);
        System.out.print("Enter Player 2 Name: ");
        Player p2 = new Player(this.in.next(), 1);

        this.playerStates = new Statelike[2];
        this.playerStates[0] = new StatePlayer(p1);
        this.playerStates[1] = new StatePlayer(p2);

        this.stateContext = new StateContext(this.playerStates[0]);
        this.currentState = 0;

        this.loadBoard();

        in.nextLine();
    }

    /**
     * Add any pre-made players to the game
     * @param players Array of players of unknown size
     */
    public void createPlayers(Player... players) {
        if (players instanceof Player[]) {
            this.playerStates = new Statelike[players.length];
            for (int i = 0; i < players.length; ++i)
                this.playerStates[i] = new StatePlayer(players[i]);
        }
        else {
            this.playerStates = new Statelike[1];
            this.playerStates[0] = new StatePlayer(players[0]);
        }

        this.stateContext = new StateContext(this.playerStates[0]);
        this.currentState = 0;
    }

    /**
     * Run the game through the console
     * @throws IOException if there is an issue reading from StdIn
     */
    public void consoleRun() throws IOException {
        // TODO(david); find a way to clean this out after tests
        in = new Scanner(System.in);
        System.out.println(this.toString());

        boolean thisRunning = true;
        while (thisRunning) {
            // TODO: determine how to connect disjointed countries
            System.out.println("Free resources: " + this.stateContext.getPlayer().getAvailableResources());
            System.out .print("Player "
                    + this.stateContext.getPlayer().getID()
                    + ": Enter Coordinates to take over with resources [enter as '1 1 10']: ");
            boolean getInput = true;
            String[] split = new String[1];

            // Need to loop input in case an error comes up
            while (getInput) {
                try {
                    split = in.nextLine().split(" ");
                    getInput = false;
                }
                catch (NoSuchElementException e) {
                    getInput = true;
                }
            }

            //TODO(david): add more cases to remove wrong input
            if (split.length == 1)
                if (split[0].equals("q"))
                    break;

            // simple variables, could use better names
            // account for axial x, y coords and #resources for the spot
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int r = Integer.parseInt(split[2]);
            Coords c = new Coords(x, y);

            if (!this.board.containsSpot(c))
                continue;

            // TODO: implement combat from UI
            if (!this.board.spotFree(c))
                continue;

            // TODO: implement resource management from UI
            this.board.claimSpot(this.stateContext.getPlayer(), c, r);
            this.board.claimCountries();

            // TODO: test that winning works
            if (this.checkPlayerWon())
                thisRunning = false;

            System.out.println(this.toString());

            this.switchPlayer();
        }
    }

    /**
     * Get the board from the game
     * @return
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Get the player whose turn it currently is
     * @return Player who is going
     */
    public Player getCurrentPlayer() {
        return (this.stateContext.getPlayer());
    }

    /**
     * Move on to the next player state
     */
    public void switchPlayer() {
        this.currentState = (this.currentState + 1) % this.playerStates.length;
        this.stateContext.setState(this.playerStates[this.currentState]);
    }

    /**
     * Complete a move based on selected coordinates
     * @param src The player's coordinates
     * @param dest The coordinates where the player is moving/attacking
     * @param resources the number of resources being used
     * @return the new board after applying all moves
     */
    public Board makeMove(Coords src, Coords dest, int resources) {
        if (this.stateContext.gameStateEquals(StateContext.SETUP_BOARD) 
                || this.stateContext.gameStateEquals(StateContext.PLAY_PUTS)) {
            // assume dest is null
            if (this.board.spotFree(src)) {
                this.board.claimSpot(this.stateContext.getPlayer(), src, 1);
                this.board.claimCountries();
                this.stateContext.removeResources(1);
            }
            else {
                this.board.getSpot(src).addResources(resources);
                this.stateContext.removeResources(resources);
            }
        }
        else {
            if (this.board.getSpot(src).getPlayer().equals(
                        this.board.getSpot(dest).getPlayer())) {
                this.board.getSpot(src).addResources(-resources);
                this.board.getSpot(dest).addResources(resources);
            }
            else if (this.board.getSpot(dest).getPlayer() != null) {
                int srcLost = this.rand.nextInt(resources + 1);
                int destLost = this.rand.nextInt(resources + 1);
                int destResources = this.board.getSpot(dest).getResources();
                if (destLost < destResources) {
                    this.board.getSpot(src).addResources(-srcLost);
                    this.board.getSpot(dest).addResources(-destLost);
                }
                else {
                    this.board.getSpot(src).addResources(-srcLost);
                    this.board.getSpot(dest).setPlayer(this.stateContext.getPlayer());
                    this.board.getSpot(dest).setResources(1);
                }
            }
            else {
                // player placing of his own accord
                this.stateContext.removeResources(resources);
                this.board.getSpot(src).addResources(resources);
            }
        }

        return (this.board);
    }

    /**
     * Check if the setup is done and move on if possible
     */
    public void checkSetup() {
        if (this.board.isSetupDone() && this.playersFinishedSetup())
            this.guiTest.progressGame();
    }

    /**
     * Check if the setup stage is finished
     * TODO(david): is this a duplicate function?
     * @return boolean regarding setup being finished
     */
    public boolean playersFinishedSetup() {
        boolean result = true;
        for (Statelike state : this.playerStates)
            result = result && (state.getPlayer().getAvailableResources() == 0);
        return result;
    }

    /**
     * Add resources to the state, calculates inside of function
     */
    public void addStateResources() {
        int numSpots = this.stateContext.getPlayer().getSpotsOwned().size();
        int numResources = numSpots / 3;
        numResources = (numResources > 1) ? numResources : 1;
        for (Country c : this.stateContext.getPlayer().getCountriesOwned())
            numResources += c.getResources();

        this.stateContext.getPlayer().addResources(numResources);
    }

    /**
     * Interface with statecontext
     * @param compare comparing int
     * @return boolean that matches
     */
    public boolean gameStateEquals(int compare) {
        return (this.stateContext.gameStateEquals(compare));
    }

    /**
     * Interface with statecontext
     * @return game state
     */
    public int getGameState() {
        return (this.stateContext.getPlayerState());
    }

    /**
     * Interface with game state
     */
    public void progressGame() {
        this.stateContext.progressGame();
    }

    /**
     * Start a new game by initializing the GUI again
     * TODO(david): see if this is really necessary
     */
    public void newGame() {
        this.guiInit();
    }

    public static void main(String[] args) throws IOException {
        // Replace with actually working version
        if (args.length == 2) {
            Risky game = new Risky(Boolean.parseBoolean(args[1]));
            // change this to work with regular run?
            game.consoleRun();
        }
        else {
            new Risky(false);
//            game.consoleRun();
        }
    }
}

