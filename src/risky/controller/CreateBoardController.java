package risky.controller;

import javafx.scene.Scene;
import risky.Risky;
import risky.model.CreateBoard;
import risky.model.game.Board;
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

    /**
     * Instantiate new controller
     *
     * @param app the callback application
     */
    public CreateBoardController(Risky app) {
        this.app = app;
        this.createBoardSkin = new CreateBoardSkin(this);
        this.createBoard = new CreateBoard(this.createBoardSkin);
        this.scene = new Scene(this.createBoardSkin);
    }

    /**
     * Set the width of the new board
     *
     * @param width integer width
     */
    public void setWidth(int width) {
        this.createBoard.setWidth(width);
    }

    /**
     * Set the height of the new board
     *
     * @param height integer height
     */
    public void setHeight(int height) {
        this.createBoard.setHeight(height);
    }

    /**
     * Callback from Skin, continue button hit
     */
    public void sizeChosen() {
        // create a blank board
        this.createBoard.generateBoard();
        // set up the new boardskin
        this.createBoardSkin.switchView(this.createBoard.getBoardProperty());
        this.app.resize();
    }

    /**
     * Callback from Skin, cancel button hit
     */
    public void quitToMenu() {
        this.app.menu();
    }

    /**
     * Get the scene for the app itself
     *
     * @return Scene to be used
     */
    public Scene getScene() {
        return this.scene;
    }
}
