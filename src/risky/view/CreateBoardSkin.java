package risky.view;

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
    }
}
