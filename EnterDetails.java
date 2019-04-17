package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EnterDetails {
    public TextField fname;
    public TextField lname;
    public TextField username;
    public TextField password;

    public void enterDetails(){
        //store inputs from the user in variables
        String firstName = fname.getText();
        String lastName = lname.getText();
        String userName = username.getText();
        String pass = password.getText();

        pass = pass;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.jdbc.Driver"); //load the driver

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", ""); //connect to data base
            pst = con.prepareStatement("insert into employee(e_fname,e_lname,e_username,e_password) values(?,?,?,?)"); //write prepared statements
            //insert data using prepared statements
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3,userName);
            pst.setString(4,pass);
            //execute the query
            pst.executeUpdate();


            //display a message saying successfully enter the details
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Confirm");
            al.setHeaderText(null);
            al.setContentText("You have successfully entered employee details");
            al.showAndWait();

        } catch (Exception e) {
            System.out.println("Error " + e);
        }

    }


    //load the previous page
    public void previousPageLoad(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ownerRequirement.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }
}
