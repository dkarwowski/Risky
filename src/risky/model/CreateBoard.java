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

    public CreateBoard(CreateBoardSkin skin) {
        this.skin = skin;
    }
}
