package risky.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
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
    private final CreateBoardController controller;
    private BoardView boardView;

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
                    int[] square = this.boardView.getHex(event.getX(), event.getY());
                    this.controller.mouseClicked(square[0], square[1]);
                }
        );

        // throw boardview on grid
        this.setPrefSize(this.boardView.getWidth(), this.boardView.getHeight());
        this.getChildren().addAll(this.boardView);

        // throw buttons on grid
    }

    /**
     * Update the Board View
     *
     * @param board the board itself
     */
    public void updateBoard(Board board) {
        this.boardView.drawBoard(board);
    }
}
