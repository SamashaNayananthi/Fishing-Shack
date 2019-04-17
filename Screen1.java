package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Screen1 {
    @FXML
    public void changetoRegister(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }

    public void changetoLogin(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }

    public void changetoOwnerLogin(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ownerLogin.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }

    public void changetoStaffLogin(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("staffLogin.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }
}
