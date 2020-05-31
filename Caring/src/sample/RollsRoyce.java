package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RollsRoyce implements Initializable {
    @FXML
    private JFXButton back;
    AnchorPane CarInfo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void backAction(ActionEvent event) {
        HomePage.getInstance().createPage(CarInfo,"HOME.fxml");
    }
}
