package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Owner {
    public PasswordField password;
    public TextField username;
    private Connection con;
    private Statement st;
    private ResultSet rs;


    public Owner(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); //load the driver

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff","root",""); //connect to data base
            st = con.createStatement();

        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }

    @FXML

    public void verifyLogin(ActionEvent event) {

        try {
            String qr = "select e_username,e_password from employee where e_fname = 'Jeff'"; //write the query
            rs = st.executeQuery(qr); //execute the query
            //only if the password match with the username load the owner requirement page
            if ((rs.next())&&(username.getText().equals(rs.getString("e_username")))&&(password.getText().equals(rs.getString("e_password")))) {
                Parent root = FXMLLoader.load(getClass().getResource("ownerRequirement.fxml"));
                Scene samp = new Scene(root);
                Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
                step.setScene(samp);
                step.show();
                //if the password is incorrect display an logging error
            } else {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Error");
                al.setHeaderText(null);
                al.setContentText("You're username or password is incorrect");
                al.showAndWait();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //change the scene to enter details
    @FXML
    public void changetoEnterDetails(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("enterDetails.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }



    //change the scene to news letter
    @FXML
    public void changetoNewsLetters(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newsLetters.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }



    //change the scene to all purchases
    @FXML
    public void changetoAllPurchases(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("allPurchases.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }


    //change the scene to customer purchases
    @FXML
    public void changetoCutomersPurchases(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customerPurchases.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }

}
