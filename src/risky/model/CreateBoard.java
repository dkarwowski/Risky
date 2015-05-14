package risky.model;

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

    public CreateBoard(CreateBoardSkin skin) {
        this.skin = skin;
        this.dimensions = new int[]{10, 10}; // the minimum size, TODO: make dynamic
    }

    public void setWidth(int width) {
        this.dimensions[1] = width;
    }

    public void setHeight(int height) {
        this.dimensions[0] = height;
    }
}
