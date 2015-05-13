package risky.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import risky.Risky;

/**
 * Handles the view of the first splash screen
 * Created by davidkarwowski on 5/11/15.
 */
public class MenuScene extends GridPane {
    public MenuScene(Risky app) {
        // TODO: add stylesheets
        Label nameLabel = new Label("Risky");
        nameLabel.setFont(new Font("Arial", 20));
        setConstraints(nameLabel, 0, 0, 3, 1);

        Button newGameButton = new Button("New Game");
        newGameButton.setDefaultButton(true);
        newGameButton.setOnAction(event -> app.newGame());
        setConstraints(newGameButton, 0, 1);

        // TODO: create a load app feature

        // TODO: see about moving this to settings menu
        Button createBoardButton = new Button("Create Board");
        createBoardButton.setOnAction(event -> app.createBoard());
        setConstraints(createBoardButton, 1, 1);

        Button quitButton = new Button("Quit");
        quitButton.setCancelButton(true);
        quitButton.setOnAction(event -> app.quitAll());
        setConstraints(quitButton, 2, 1);

        setHgap(5);
        setVgap(5);
        setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().addAll(nameLabel, newGameButton, quitButton);
    }
}
