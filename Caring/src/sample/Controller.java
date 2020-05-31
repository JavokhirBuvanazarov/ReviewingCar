package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DBConnection.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox checkbox;

    @FXML
    private ImageView progress;

    @FXML
    private Button forgotPassword;

    @FXML
    private Button signUp;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    private static Controller instance;

    public Controller() {
        instance = this;
    }

    public static Controller getInstance() {
        return instance;
    }

    public String username(){
        return username.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        progress.setVisible(false);

        handler = new DBHandler();
    }
    @FXML
    public void loginAction(ActionEvent e)  {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(event -> {
            //Retrieving data from database
            connection = handler.getConnection();
            String q1 = "SELECT * from CarReview where names=? and password=?";

            try {
                pst = connection.prepareStatement(q1);
                pst.setString(1,username.getText());
                pst.setString(2,password.getText());

                ResultSet rs = pst.executeQuery();

                int count = 0;

                while (rs.next()){
                    count++;
                }
                if (count == 1) {
                    login.getScene().getWindow().hide();

                    Stage home = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                    Scene scene = new Scene(root,943,600);
                    home.setScene(scene);
                    home.show();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Username or Password Is Not Correct");
                    alert.show();
                    progress.setVisible(false);
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException el) {
                    el.printStackTrace();
                }
            }
        });
        pt.play();



    }

    @FXML
    public void signUpAction(ActionEvent el) throws IOException {
        login.getScene().getWindow().hide();

        Stage signUp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        Scene scene = new Scene(root);
        signUp.setScene(scene);
        signUp.show();
        signUp.setResizable(false);
    }

}
