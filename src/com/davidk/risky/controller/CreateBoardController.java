package com.davidk.risky.controller;

import com.davidk.risky.Risky;
import com.davidk.risky.model.CreateBoard;
import com.davidk.risky.view.CreateBoardSkin;
import javafx.scene.Scene;

/**
 * Controls the user input for creating a new board, handles calls to the Board
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class CreateBoardController {
    private final Risky app;
    private final Scene scene;
    private final CreateBoardSkin createBoardSkin;
    private final CreateBoard createBoard;

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
     * Callback from Board Skin
     *
     * @param x indexed x
     * @param y indexed y
     */
    public void mouseClicked(int x, int y) {
        this.createBoard.switchSpot(x, y);
    }

    /**
     * Sets the mode to be selecting exits for a spot, should limit clicks?
     * TODO: implement all features
     *
     * @param x indexed x
     * @param y indexed y
     */
    public void setSelectExits(int x, int y) {
        this.createBoard.setSelected(x, y);
        this.createBoard.setSetExits(true);
        System.out.println("SetSelected"); // test to ensure working
    }

    /**
     * Callback from Skin, continue button hit
     */
    public void sizeChosen() {
        // create a blank board
        this.createBoard.generateBoard();
        // set up the new boardskin
        this.createBoardSkin.switchView(this.createBoard.getBoard());
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
