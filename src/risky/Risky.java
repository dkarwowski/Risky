package risky;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Player;
import risky.common.Spot;
import risky.common.StateContext;
import risky.common.StatePlayer;
import risky.common.Statelike;

public class Risky {
    private Board board;
    private StateContext stateContext;

    private Statelike[] playerStates;
    private int currentState;

    private Player playerWon = null;
    
    //TODO(david): remove this
    private Scanner in;

    public Risky(boolean console) {
        if(console)
            this.consoleInit();
        else
            System.out.println("GUI Not Enabled\n");
    }

    public Risky(Board _board, Statelike[] _playerStates) {
        this.board = _board;
        this.playerStates = _playerStates;
        this.stateContext = new StateContext(this.playerStates[0]);
        this.currentState = 0;
    }

    public boolean checkPlayerWon() {
        playerWon = this.board.playerOwnsAll();
        return (playerWon != null);
    }

    public Player getWinner() {
        return playerWon;
    }

    @Override
    public String toString() {
        String result = "";
        result += board.toString() + "\n";
        return (result);
    }

    public void consoleInit() {
        this.in = new Scanner(System.in);

        // TODO(david): replace this with UI version
        System.out.print("Enter Player 1 Name: ");
        Player p1 = new Player(this.in.next(), 0);
        System.out.print("Enter Player 2 Name: ");
        Player p2 = new Player(this.in.next(), 1);

        this.playerStates = new Statelike[2];
        this.playerStates[0] = new StatePlayer(p1);
        this.playerStates[1] = new StatePlayer(p2);

        this.stateContext = new StateContext(this.playerStates[0]);
        this.currentState = 0;

        // TODO(david): streamline this. not necessary in general init
        String boardName;
        int width, height;
        String[] board;
        try {
            // TODO(david): make more options
            Scanner loadBoard = new Scanner(new File("data/test.map"));
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
            // TODO(david): Auto-generated catch block
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

            // TODO: have spots update countries they're attached to
            Spot spot = new Spot(thisCountry, new Coords(xCart, yCart, false));
            thisCountry.addSpot(spot);

            this.board.setSpot(spot);
        }
        
        in.nextLine();
    }

    public void consoleRun() throws IOException {
        // TODO(david); find a way to clean this out after tests
        in = new Scanner(System.in);
        System.out.println(this.toString());

        boolean thisRunning = true;
        while (thisRunning) {
            // TODO: make first move unique for both players
            // TODO: make moves only possible to consecutive spots
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

            // TODO: deal with combat
            if (!this.board.spotFree(c))
                continue;

            // TODO: make player lose resources
            // TODO: give player resources every turn
            this.board.claimSpot(this.stateContext.getPlayer(), c, r);
            this.board.claimCountries();

            // TODO: test that this works
            if (this.checkPlayerWon())
                thisRunning = false;

            System.out.println(this.toString());
            this.currentState = (this.currentState + 1)
                    % this.playerStates.length;
            this.stateContext.setState(this.playerStates[this.currentState]);
        }
    }
    
    //TODO(david): remove this temporary testing functions
    public Board getBoard() {
        return this.board;
    }

    public static void main(String[] args) throws IOException {
        // Replace with actually working version
        Risky game = new Risky(true);
        game.consoleRun();
    }
}
