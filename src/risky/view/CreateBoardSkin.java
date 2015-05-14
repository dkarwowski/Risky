package risky.view;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import risky.controller.CreateBoardController;
import risky.model.game.Board;
import risky.model.game.Spot;

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
        // TODO: move this out properly once finished
        int[] dim = board.get().getDimensions();
        Canvas boardView = new Canvas(45 * dim[0] + 35, 52 * dim[1] + 46);
        // get the context to draw to the canvas
        GraphicsContext context = boardView.getGraphicsContext2D();
        Spot[] spots = board.get().getSpots(); // TODO: see about using arraylist
        for (int y = 0; y < dim[1]; y++) {
            for (int x = 0; x < dim[0]; x++) {
                Spot spot = spots[x + y * dim[0]]; // TODO: see about using Coords
                int xOffset = 10;
                int yOffset = x % 2 == 0 ? 36 : 62;

                if (spot == null) {
                    context.setFill(Color.color(0.2f, 0.2f, 0.7f, 0.9f));
                    context.setEffect(new GaussianBlur(1f));
                    context.fillPolygon(
                            new double[]{
                                    xOffset + 44.5 * x,
                                    xOffset + 44.5 * x + 15,
                                    xOffset + 44.5 * x + 45,
                                    xOffset + 44.5 * x + 60,
                                    xOffset + 44.5 * x + 45,
                                    xOffset + 44.5 * x + 15
                            },
                            new double[]{
                                    yOffset + 52 * y,
                                    yOffset + 52 * y - 26,
                                    yOffset + 52 * y - 26,
                                    yOffset + 52 * y,
                                    yOffset + 52 * y + 26,
                                    yOffset + 52 * y + 26
                            },
                            6
                    );
                    context.setStroke(Color.color(0.4f, 0.4f, 1.0f, 0.3f));
                    context.setLineCap(StrokeLineCap.ROUND);
                    context.setLineWidth(2);
                    context.strokePolyline(
                            new double[]{
                                    xOffset + 44.5 * x + 1,
                                    xOffset + 44.5 * x + 16,
                                    xOffset + 44.5 * x + 44
                            },
                            new double[]{
                                    yOffset + 52 * y,
                                    yOffset + 52 * y - 25,
                                    yOffset + 52 * y - 25
                            },
                            3
                    );
                    context.setLineWidth(1);
                    context.strokeLine(
                            xOffset + 44.5 * x + 44,
                            yOffset + 52 * y - 25,
                            xOffset + 44.5 * x + 59,
                            yOffset + 52 * y + 1
                    );
                    context.setStroke(Color.color(0.1f, 0.1f, 0.5f, 0.3f));
                    context.setLineCap(StrokeLineCap.ROUND);
                    context.setLineWidth(2);
                    context.strokePolyline(
                            new double[]{
                                    xOffset + 44.5 * x + 59,
                                    xOffset + 44.5 * x + 44,
                                    xOffset + 44.5 * x + 16
                            },
                            new double[]{
                                    yOffset + 52 * y - 1,
                                    yOffset + 52 * y + 25,
                                    yOffset + 52 * y + 25
                            },
                            3
                    );
                    context.setLineWidth(1);
                    context.strokeLine(
                            xOffset + 44.5 * x + 16,
                            yOffset + 52 * y + 26,
                            xOffset + 44.5 * x + 1,
                            yOffset + 52 * y
                    );
                }
            }
        }

        this.setMinSize(boardView.getWidth(), boardView.getHeight());
        this.setMaxSize(boardView.getWidth(), boardView.getHeight());
        this.setPrefSize(boardView.getWidth(), boardView.getHeight());
        this.getChildren().addAll(boardView);

        // throw boardview on grid
        // throw buttons on grid
    }
}
