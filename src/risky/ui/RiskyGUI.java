package risky.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Spot;

public class RiskyGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Risky");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Risky");
        
        // find a way to do some of this in Risky
        Board board = RiskyGUI.loadBoard();
        BoardPanel boardPanel = new BoardPanel(board);
        frame.add(boardPanel, BorderLayout.CENTER);
        
        InfoPanel infoPanel = new InfoPanel();
        frame.add(infoPanel, BorderLayout.SOUTH);
        
        int testWidth = boardPanel.getPanelDimension().width;
        int testHeight = boardPanel.getPanelDimension().height + infoPanel.getHeight(); // width and height and padding
        frame.setSize(testWidth, testHeight);
        frame.setVisible(true);
    }
    
    /**
     * Load board function taken from Risky.java
     * @return the board that will be created
     */
    private static Board loadBoard() {
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
            return (null);
        }

        Board setBoard = new Board(boardName, width, height);

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

            setBoard.setSpot(spot);
        }
        
        return (setBoard);
    }
}
