package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HOME implements Initializable {
    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private JFXButton benz;
    @FXML
    private AnchorPane home;

    @FXML
    private JFXButton tesla;

    @FXML
    private JFXButton bmw;

    @FXML
    private JFXButton rr;
    HomePage homePage;
    AnchorPane u;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void benzAction(ActionEvent actionEvent) throws IOException {
        HomePage.getInstance().createPage(home,"Merz.fxml");
    }
    @FXML
    public void teslaAction(ActionEvent actionEvent) throws IOException {
        HomePage.getInstance().createPage(home,"Tesla.fxml");
    }
    @FXML
    void bmwAction(ActionEvent event) throws IOException {
        HomePage.getInstance().createPage(home,"BMW.fxml");

    }

    @FXML
    void rrAction(ActionEvent event) throws IOException {
        HomePage.getInstance().createPage(home,"RR.fxml");
    }
}
