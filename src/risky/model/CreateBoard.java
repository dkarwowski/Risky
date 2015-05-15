package risky.model;

import risky.model.game.Board;
import risky.view.CreateBoardSkin;

/**
 * Takes calls from the Controller and manages them to create new boards
 * Writes board information to a file
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class CreateBoard {
    private CreateBoardSkin skin;
    private int[] dimensions;       // dimensions of height, width
    private Board board;

    /**
     * Instantiate the new creator
     *
     * @param skin view being manipulated
     */
    public CreateBoard(CreateBoardSkin skin) {
        this.skin = skin;
        this.dimensions = new int[]{7, 15}; // the minimum size, TODO: make dynamic
        this.board = new Board();
    }

    /**
     * Width changed on the slider
     *
     * @param width integer width
     */
    public void setWidth(int width) {
        this.dimensions[1] = width;
    }

    /**
     * Height changed on the slider
     *
     * @param height integer height
     */
    public void setHeight(int height) {
        this.dimensions[0] = height;
    }

    /**
     * Generate a board given the selected dimensions
     */
    public void generateBoard() {
        this.board = new Board(this.dimensions[1], this.dimensions[0]);
    }

    /**
     * Create or remove spots as the player clicks on them
     *
     * @param x x index for the spot
     * @param y y index for the spot
     */
    public void switchSpot(int x, int y) {
        if (this.board.getSpot(x, y) == null)
            this.board.createSpot(x, y);
        else
            this.board.removeSpot(x, y);

        this.skin.updateBoard(this.board);
    }

    /**
     * Get the read only property for the board
     *
     * @return read only property for binding
     */
    public Board getBoard() {
        return this.board;
    }
}
