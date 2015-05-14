package risky.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import risky.controller.CreateBoardController;

/**
 * Displays the board and catches user input
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class CreateBoardSkin extends GridPane {
    private CreateBoardController controller;

    public CreateBoardSkin(CreateBoardController controller) {
        this.controller = controller;

        // General settings
        this.setHgap(5);
        this.setVgap(5);
        this.setPadding(new Insets(10, 10, 10, 10));

        // Label
        Label boardSizeLabel = new Label("Select Board Size");
        setConstraints(boardSizeLabel, 0, 0);
        Label widthLabel = new Label("Width:  10");
        setConstraints(widthLabel, 0, 1);
        Label heightLabel = new Label("Height:  10");
        setConstraints(heightLabel, 0, 2);
        // Board Size Chooser
        Slider widthSlider = new Slider();
        widthSlider.setMin(10);
        widthSlider.setMax(30);
        widthSlider.setBlockIncrement(1);
        widthSlider.setMajorTickUnit(5);
        widthSlider.setMinorTickCount(4);
        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);
        widthSlider.setSnapToTicks(true);
        widthSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.controller.setWidth(newValue.intValue());
                    widthLabel.setText(String.format("Width:  %d", newValue.intValue()));
                }
        );
        setConstraints(widthSlider, 1, 1, 2, 1);
        Slider heightSlider = new Slider();
        heightSlider.setMin(10);
        heightSlider.setMax(30);
        heightSlider.setBlockIncrement(1);
        heightSlider.setMajorTickUnit(5);
        heightSlider.setMinorTickCount(4);
        heightSlider.setShowTickLabels(true);
        heightSlider.setShowTickMarks(true);
        heightSlider.setSnapToTicks(true);
        heightSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.controller.setHeight(newValue.intValue());
                    heightLabel.setText(String.format("Height:  %d", newValue.intValue()));
                }
        );
        setConstraints(heightSlider, 1, 2, 2, 1);
        // Buttons
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> this.controller.sizeChosen());
        setConstraints(continueButton, 1, 3);
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> this.controller.quitToMenu());
        setConstraints(cancelButton, 2, 3);

        this.getChildren().addAll(
                boardSizeLabel,
                heightLabel,
                widthLabel,
                widthSlider,
                heightSlider,
                continueButton,
                cancelButton
        );
    }

    public void switchView() {
        // grab the generated board
        // create a board view
        // throw boardview on grid
        // throw buttons on grid
    }
}
