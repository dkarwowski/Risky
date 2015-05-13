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
    private CreateBoardSkin createBoardSkin;
    private CreateBoard createBoard;

    public CreateBoardController(Risky app) {
        this.app = app;
        this.createBoardSkin = new CreateBoardSkin(this);
        this.createBoard = new CreateBoard(this.createBoardSkin);
        this.scene = new Scene(this.createBoardSkin);
    }

    public Scene getScene() {
        return this.scene;
    }
}
