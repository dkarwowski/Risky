package risky.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import risky.controller.SetupController;


/**
 * Has the Player setup number of players, player names, and board choosing
 * Created by davidkarwowski on 5/11/15.
 */
public class SetupScene extends GridPane {
    private final SetupController setupController;

    public SetupScene(SetupController controller) {
        setupController = controller;

        // General settings
        setHgap(5);
        setVgap(5);

        // Board Selection -
        Label boardLabel = new Label("Select Map");
        setConstraints(boardLabel, 0, 0);
        // Board Selection - Chooser
        ComboBox<String> mapSelection = new ComboBox<>();
        mapSelection.getItems().addAll("test.map", "test2.map", "test3.map");
        mapSelection.valueProperty().addListener(
                (observable, oldValue, newValue) -> setupController.setMapSelect(newValue)
        );
        setConstraints(mapSelection, 0, 1, 1, 1);
        // TODO: automate map selection
        // TODO: preview of map?

        // Player Selection -
        Label playerLabel = new Label("Players");
        setConstraints(playerLabel, 1, 0, 1, 1);
        // Player Selection - Labels
        Label numberPlayersLabel = new Label("Number of Players: ");
        setConstraints(numberPlayersLabel, 1, 1, 1, 1);
        // Player Selection - Choices
        Slider numberPlayersSlider = new Slider();
        numberPlayersSlider.setMin(2);
        numberPlayersSlider.setMax(6);
        numberPlayersSlider.setBlockIncrement(1);
        numberPlayersSlider.setMajorTickUnit(1);
        numberPlayersSlider.setMinorTickCount(0);
        numberPlayersSlider.setShowTickLabels(true);
        numberPlayersSlider.setShowTickMarks(true);
        numberPlayersSlider.setSnapToTicks(true);
        numberPlayersSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    setupController.setNumberOfPlayers(newValue.intValue());
                }
        );
        setConstraints(numberPlayersSlider, 2, 1, 1, 1);

        // Move on or return to main menu
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> setupController.setSetupFinished());
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> setupController.quitToMenu());

        HBox hBoxButtons = new HBox();
        hBoxButtons.setAlignment(Pos.CENTER_RIGHT);
        hBoxButtons.setSpacing(5);
        hBoxButtons.getChildren().addAll(continueButton, cancelButton);
        setConstraints(hBoxButtons, 1, 2, 2, 1);

        // Add everything to setup view
        this.getChildren().addAll(
                boardLabel,
                mapSelection,
                playerLabel,
                numberPlayersLabel,
                numberPlayersSlider,
                hBoxButtons
        );
    }
}
