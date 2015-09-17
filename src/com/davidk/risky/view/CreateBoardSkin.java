package com.davidk.risky.view;

import com.davidk.risky.controller.CreateBoardController;
import com.davidk.risky.model.game.Board;
import com.davidk.risky.model.game.Country;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Displays the board and catches user input
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class CreateBoardSkin extends StackPane {
    private final CreateBoardController controller;
    private BoardView boardView;
    private ContextMenu contextMenu;

    /**
     * Initialize the controller, start with a settings view
     *
     * @param controller the controller for the skin
     */
    public CreateBoardSkin(CreateBoardController controller) {
        this.controller = controller;

        GridPane settingsPane = new GridPane();

        // General settings
        settingsPane.setHgap(5);
        settingsPane.setVgap(5);
        this.setPadding(new Insets(10, 10, 10, 10));

        // Label
        Label boardSizeLabel = new Label("Select Board Size");
        GridPane.setConstraints(boardSizeLabel, 0, 0);
        Label widthLabel = new Label("Dim: 10x7");
        GridPane.setConstraints(widthLabel, 0, 1);
        // Board Size Chooser
        Slider widthSlider = new Slider();
        widthSlider.setMin(10);
        widthSlider.setMax(20);
        widthSlider.setBlockIncrement(1);
        widthSlider.setMajorTickUnit(5);
        widthSlider.setMinorTickCount(4);
        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);
        widthSlider.setSnapToTicks(true);
        widthSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.controller.setWidth(newValue.intValue());
                    this.controller.setHeight(newValue.intValue() * 3 / 4);
                    widthLabel.setText(
                            String.format("Dim: %dx%d", newValue.intValue(), newValue.intValue() * 3 / 4)
                    );
                }
        );
        GridPane.setConstraints(widthSlider, 1, 1, 2, 1);
        // Buttons
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> this.controller.sizeChosen());
        GridPane.setConstraints(continueButton, 1, 3);
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> this.controller.quitToMenu());
        GridPane.setConstraints(cancelButton, 2, 3);

        settingsPane.getChildren().addAll(
                boardSizeLabel,
                widthLabel,
                widthSlider,
                continueButton,
                cancelButton
        );

        this.getChildren().addAll(settingsPane);
    }

    /**
     * Switch views to a board view from settings view
     *
     * @param board the board to use
     */
    public void switchView(Board board) {
        // remove previous objects
        this.getChildren().clear();
        // create a board view
        this.boardView = new BoardView(board, 30);
        this.boardView.drawBoard();
        this.boardView.addEventFilter(MouseEvent.MOUSE_CLICKED,
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        int[] square = this.boardView.getHex(event.getX(), event.getY());
                        if (square == null)
                            return;

                        // hide the context menu on a click outside
                        if (this.contextMenu != null && this.contextMenu.isShowing())
                            this.contextMenu.hide();
                        else
                            this.controller.mouseClicked(square[0], square[1]);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        int[] square = this.boardView.getHex(event.getX(), event.getY());
                        if (square == null)
                            return;

                        this.createContextMenu(event, square);
                        // tell the board view to add a highlight?
                    }
                }
        );

        // throw boardview on grid
        this.setMinSize(this.boardView.getWidth(), this.boardView.getHeight());
        this.setMaxSize(this.boardView.getWidth(), this.boardView.getHeight());
        this.setPrefSize(this.boardView.getWidth(), this.boardView.getHeight());
        StackPane.setAlignment(this.boardView, Pos.TOP_LEFT);
        this.getChildren().addAll(this.boardView);

        // throw buttons on grid
    }

    /**
     * Create a context menu for the spot selected
     *
     * @param mouseEvent the mouse event for positioning
     * @param square     the hex being used
     */
    private void createContextMenu(MouseEvent mouseEvent, int[] square) {
        if (this.contextMenu != null)
            this.contextMenu.hide();

        this.contextMenu = new ContextMenu();
        MenuItem setExits = new MenuItem("Set Exits");
        setExits.setOnAction(
                event -> this.controller.setSelectExits(square[0], square[1])
        );
        MenuItem switchSpot = new MenuItem("Switch Type");
        switchSpot.setOnAction(
                event -> this.controller.mouseClicked(square[0], square[1]) // TODO: rename the function
        );
        Menu countryMenu = new Menu("Set Country");
        final ToggleGroup countries = new ToggleGroup();
        for (Country country : this.boardView.getCountries()) {
            RadioMenuItem item = new RadioMenuItem(country.getName());
            item.setOnAction(
                    event -> {
                        try {
                            this.controller.setCountry(square[0], square[1], country);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
            item.setToggleGroup(countries);
            countryMenu.getItems().add(item);
        }
        MenuItem addCountryToMenu = new MenuItem("Add Country...");
        addCountryToMenu.setOnAction(
                event -> {
                    try {
                        this.controller.setCountry(square[0], square[1], this.controller.createCountryDialog());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        countryMenu.getItems().add(addCountryToMenu);

        this.contextMenu.getItems().addAll(switchSpot, setExits, countryMenu);
        this.contextMenu.show(this, mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }

    /**
     * Update the Board View
     *
     * @param board the board itself
     */
    public void updateBoard(Board board) {
        this.boardView.drawBoard(board);
    }

    /**
     * Popup Dialog for the user to make a country
     */
    public void createCountryDialog() {
        // created a base stack gridpane to fill the screen with blank space
        GridPane stackFiller = new GridPane();
        // country pane will hold the actual input
        GridPane countryPane = new GridPane();
        countryPane.setHgap(5);
        countryPane.setVgap(5);
        countryPane.setPadding(new Insets(10, 10, 10, 10));

        // create the input fields
        TextField countryName = new TextField("Name");
        Button submit = new Button("Submit");
        submit.setOnAction(
                event -> {
                    this.controller.createCountry(countryName.getText());
                    this.getChildren().remove(stackFiller);
                }
        );
        Button exit = new Button("Cancel");
        exit.setOnAction(
                event -> this.getChildren().remove(stackFiller)
        );

        // set the placement
        GridPane.setConstraints(countryName, 0, 0);
        GridPane.setConstraints(submit, 1, 0);
        GridPane.setConstraints(exit, 2, 0);
        countryPane.getChildren().addAll(countryName, submit, exit);
        // TODO: remove this style
        countryPane.setStyle("-fx-background-color: aqua");
        stackFiller.getChildren().add(countryPane);
        stackFiller.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().add(stackFiller);
    }
}
