package daco;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Daco daco;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dacoImage = new Image(this.getClass().getResourceAsStream("/images/Daco.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDacoDialog(
                " ____                  \n"
                + "|  _ \\  __ _  ___ ___  \n"
                + "| | | |/ _` |/ __/ _ \\ \n"
                + "| |_| | (_| | (_| (_) |\n"
                + "|____/ \\__,_|\\___\\___/ \n \n", dacoImage));
    }

    /** Injects the Duke instance */
    public void setDaco(Daco d) {
        daco = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = daco.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDacoDialog(response, dacoImage)
        );
        userInput.clear();
    }
}

