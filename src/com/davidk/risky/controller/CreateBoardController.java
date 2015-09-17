package com.davidk.risky.controller;

import com.davidk.risky.Risky;
import com.davidk.risky.model.CreateBoard;
import com.davidk.risky.model.game.Country;
import com.davidk.risky.view.CreateBoardSkin;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

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
     * Set the country of a specific spot to be a selected country
     *
     * @param x       offset x coordinate of the spot
     * @param y       offset y coordinate of the spot
     * @param country unique country on the board
     */
    public void setCountry(int x, int y, Country country) throws Exception {
        System.out.println("SetCountry");
        if (country == null) // TODO: remove this shit because it's a test
            return;

        this.createBoard.setCountry(x, y, country);
    }

    /**
     * Create a new country on the board
     *
     * @return the created country
     */
    public Country createCountryDialog() {
        // TODO: implement country creation
        this.createBoardSkin.createCountryDialog();
        System.out.println("CreateCountry");
        return null;
    }

    /**
     * Simply tells the create board to modify and create the country
     *
     * @param cName name of the country to be added
     * @param color color of the country
     */
    public void createCountry(String cName, Color color) {
        this.createBoard.createCountry(cName, color);
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
