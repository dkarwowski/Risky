package risky.controller;

import javafx.scene.Scene;
import risky.Risky;
import risky.model.CreateBoard;
import risky.view.CreateBoardSkin;

/**
 * Controls the user input for creating a new board, handles calls to the Board
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class CreateBoardController {
    private Risky app;
    private Scene scene;
    private CreateBoardSkin boardSkin;
    private CreateBoard board;

    public CreateBoardController(Risky app) {
        this.app = app;
        this.boardSkin = new CreateBoardSkin();
        this.board = new CreateBoard();
        this.scene = new Scene(this.boardSkin);
    }

    public Scene getScene() {
        return this.scene;
    }
}
