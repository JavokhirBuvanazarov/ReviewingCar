package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class HomePage implements Initializable {
    @FXML AnchorPane holderPane;
    AnchorPane home;
    @FXML
    private Pane borderPane;

    @FXML
    private Label dashboard;

    @FXML
    private Button homeButton;

    @FXML
    private Button myCars;

    @FXML
    private Button about;

    @FXML
    private Button contactUs;

    @FXML
    private Label welcome;

    @FXML
    private ImageView tripleButton;

    @FXML
    private Label lblMenu;

    @FXML
    private HBox toolbarRight;
    @FXML
    private VBox overflowContainer;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton exit;
    private static HomePage instance;
    public HomePage() {
        instance = this;
    }
    public static HomePage getInstance(){
        return instance;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXRippler jfxRippler = new JFXRippler(lblMenu);
        jfxRippler.setMaskType(JFXRippler.RipplerMask.RECT);
        toolbarRight.getChildren().add(jfxRippler);

        openMenus();
        createPage(holderPane,"HOME.fxml");
        setUserName(Controller.getInstance().username());
    }

    public void setUserName(String user) {
        this.welcome.setText("Welcome, " + user);
    }

    private void openMenus() {
        JFXPopup pop = new JFXPopup();
        pop.setPopupContent(overflowContainer);
        lblMenu.setOnMouseClicked(e -> {
//            pop.show(JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.RIGHT,-1,42);
            pop.show(lblMenu,JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.RIGHT,-9,50);
        });
    }

    private void setNode(Node node){
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1500));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
    }

    public void createPage(AnchorPane homeN, String loc) {
        try {
            homeN = FXMLLoader.load(getClass().getResource(loc));
            setNode(homeN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void homeBtn() {
        setNode(home);
    }

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        home.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
}
