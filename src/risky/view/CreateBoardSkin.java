package risky.view;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import risky.controller.CreateBoardController;
import risky.model.game.Board;

/**
 * Displays the board and catches user input
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class CreateBoardSkin extends StackPane {
    private CreateBoardController controller;

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
        Label widthLabel = new Label("Dim: 15x7");
        GridPane.setConstraints(widthLabel, 0, 1);
        // Board Size Chooser
        Slider widthSlider = new Slider();
        widthSlider.setMin(15);
        widthSlider.setMax(25);
        widthSlider.setBlockIncrement(1);
        widthSlider.setMajorTickUnit(5);
        widthSlider.setMinorTickCount(4);
        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);
        widthSlider.setSnapToTicks(true);
        widthSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.controller.setWidth(newValue.intValue());
                    this.controller.setHeight(newValue.intValue() / 2);
                    widthLabel.setText(
                            String.format("Dim: %dx%d", newValue.intValue(), newValue.intValue()/2)
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

    public void switchView(ReadOnlyObjectProperty<Board> board) {
        // remove previous objects
        this.getChildren().clear();
        // create a board view
        BoardView boardView = new BoardView(board.get(), 30);
        boardView.drawBoard();

        // throw boardview on grid
        this.setPrefSize(boardView.getWidth(), boardView.getHeight());
        this.getChildren().addAll(boardView);

        // throw buttons on grid
    }
}
