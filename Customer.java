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
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {

    //create variables for get the connection to data base
        private Connection con;
        private Statement st;
        private ResultSet rs;

        //get the connection to data base using constructor
        public Customer(){
            try{
                Class.forName("com.mysql.jdbc.Driver"); //loading the driver class

                //con variable helps to connect to the data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff","root","");
                //st variable helps to execute quaries
                st = con.createStatement();

            }catch (Exception e){
                System.out.println("Error "+e);  //to catch exceptions
            }
        }

        //creating variables for javafx file
        @FXML private TextField fname;
    @FXML private TextField lname;
    @FXML private TextField add;
    @FXML private TextField mail;
    @FXML private PasswordField password;
    @FXML private TextField username;
    @FXML private PasswordField loginPassword;


    @FXML
    public void registration(ActionEvent event) throws IOException {
        // Store the inputs got from the user in variables
        String namef, namel,address, maila, pass;
        namef = fname.getText();
        namel = lname.getText();
        address = add.getText();
        maila = mail.getText();
        pass = password.getText();

        //validate the password using Pattern class
        Pattern p = Pattern.compile("^(?=.*?[a-zA-Z])(?=.*?[^a-zA-Z]{2,}).{8,}$"); //validate password to have 8 characters and 2 of the characters should be non-alphabetical
        Matcher m = p.matcher(pass); //pass the password that user enter to the Matcher class to match the password with the pattern

        //save the data into database if the password validation pass
        if (m.matches()) {
            pass = pass;
            Connection con = null;
            PreparedStatement pst = null;

            try {
                Class.forName("com.mysql.jdbc.Driver"); //load the driver class

                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", ""); //get the connection to the data base
                pst = con.prepareStatement("insert into customer(c_fname,c_lname,c_address,c_mail,c_password) values(?,?,?,?,?)"); //writing query using prepared statements
                //add values using prepared statements
                pst.setString(1, namef);
                pst.setString(2, namel);
                pst.setString(3,address);
                pst.setString(4,maila);
                pst.setString(5,pass);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }

            //if the customer registered view the log in formto the customer
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene samp = new Scene(root);
            Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
            step.setScene(samp);
            step.show();

        } else {
            //If the registration fails display an error message
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Validate you're password");
            al.setHeaderText(null);
            al.setContentText("The password must contain eight characters or more and must contain at least two non-alphabetic characters");
            al.showAndWait();
        }
    }


    @FXML
    public void verify(ActionEvent event) throws IOException {

        try {
            //writing the query to retrieve data from data base
            String qr = "select c_mail,c_password from customer where c_mail = '" + username.getText() + "'";
            //rs variable will hold all the results achieved when retrieving data
            rs = st.executeQuery(qr);
            if ((rs.next())&&(loginPassword.getText().equals(rs.getString("c_password")))) {
                Parent root = FXMLLoader.load(getClass().getResource("requirement.fxml"));
                Scene samp = new Scene(root);
                Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
                step.setScene(samp);
                step.show();
            } else {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Error");
                al.setHeaderText(null);
                al.setContentText("You're password is incorrect");
                al.showAndWait();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @FXML
    public void purchase(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("products.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }


    //if the customer selects edit details option change the scene
    public void editDetails(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("editDetails.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }



    //if the customer selects email enquiries option change the scene
    public void enquiries(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("enquiries.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }

}
