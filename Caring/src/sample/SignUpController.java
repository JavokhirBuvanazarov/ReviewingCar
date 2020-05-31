package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DBConnection.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button login;
    @FXML
    private Button signUp;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextField location;
    @FXML
    private AnchorPane parentPane;
    @FXML
    private ImageView progress;

    private Connection connection;
    private DBHandler dbHandler;
    private PreparedStatement preparedStatement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progress.setVisible(false);

        dbHandler = new DBHandler();
    }

    @FXML
    public void signUpAction(ActionEvent el) throws SQLException {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished( e -> {
            System.out.println("Sign up Successfully!");
        });
        pt.play();

        //Saving data to the database
        String insert = "INSERT INTO CarReview(names, password, gender ,location)" + "VALUES(?,?,?,?)";
        connection = dbHandler.getConnection();
        preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1,username.getText());
        preparedStatement.setString(2,password.getText());
        preparedStatement.setString(3,getGender());
        preparedStatement.setString(4,location.getText());

        preparedStatement.executeUpdate();
    }
    @FXML
    public void loginAction(ActionEvent e) throws IOException {
        signUp.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    public String getGender(){
        String gen = "";

        if (male.isSelected()){
            gen = "Male";
        } else if (female.isSelected()){
            gen = "Female";
        }

        return gen;
    }
}
