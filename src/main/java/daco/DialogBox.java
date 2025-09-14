package daco;
import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private Circle avatarBorder;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        //Used ChatGPT to generate text wrapping
        dialog.setWrapText(true);
        dialog.setMaxWidth(320);
        dialog.setMinHeight(Region.USE_PREF_SIZE);

        //Used ChatGPT to generate code for a circular picture frame
        if (displayPicture.getFitWidth() == 0 || displayPicture.getFitHeight() == 0) {
            displayPicture.setFitWidth(50);
            displayPicture.setFitHeight(50);
            displayPicture.setPreserveRatio(true);
            displayPicture.setSmooth(true);
        }

        double r = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2.0;
        Circle clip = new Circle(r, r, r);
        displayPicture.setClip(clip);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        WritableImage rounded = displayPicture.snapshot(params, null);
        displayPicture.setClip(null);
        displayPicture.setImage(rounded);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
    //Used ChatGPT to format User Dialogue Box
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #d8f4c7; -fx-background-radius: 15; -fx-padding: 10;");
        return db;
    }
    //Used ChatGPT to format Daco Dialogue Box
    public static DialogBox getDacoDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15; -fx-padding: 10;");
        return db;
    }
}
