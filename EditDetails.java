package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
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

public class EditDetails {
    public TextField NewAddress;
    public TextField NewMail;
    public PasswordField password;
    public TextField username;

    private Statement st;
    private  ResultSet rs;
    
    public void editDetails(ActionEvent event){

        Connection con = null;
        PreparedStatement pst = null;

        try{
            Class.forName("com.mysql.jdbc.Driver"); //load the driver

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff","root",""); //connect to data base
            st = con.createStatement(); //create variable to execute quiries

            String qr = "select c_mail,c_password from customer where c_mail = '" + username.getText() + "'";
            rs = st.executeQuery(qr); //rs variable will hold the result set

            //verify the password
            if ((rs.next())&&(password.getText().equals(rs.getString("c_password")))) {
                //if address field is empty only update the mail address
                if (NewAddress.getText().equals("")) {
                    pst = con.prepareStatement("update customer set c_mail='" + NewMail.getText() + "' where c_password='" + password.getText() + "'");
                    pst.executeUpdate();
                    //display a message to inform successfully edited the data base
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setTitle("Confirm");
                    al.setHeaderText(null);
                    al.setContentText("You have successfully update you're Email address");
                    al.showAndWait();
                    //if mail address field is empty only update the address
                } else if (NewMail.getText().equals("")) {
                    pst = con.prepareStatement("update customer set c_address='" + NewAddress.getText() + "' where c_password='" + password.getText() + "'");
                    pst.executeUpdate();
                    //display a message to inform successfully edited the data base
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setTitle("Confirm");
                    al.setHeaderText(null);
                    al.setContentText("You have successfully update you're address");
                    al.showAndWait();
                    //update both fields
                } else {
                    pst = con.prepareStatement("update customer set c_address='" + NewAddress.getText() + "',c_mail='" + NewMail.getText() + "' where c_password='" + password.getText() + "'");
                    pst.executeUpdate();
                    //display a message to inform successfully edited the data base
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setTitle("Confirm");
                    al.setHeaderText(null);
                    al.setContentText("You have successfully update you're Email address & you're address");
                    al.showAndWait();
                }
            }
            //Display an error if the password is incorrect
            else {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Error");
                al.setHeaderText(null);
                al.setContentText("Please enter you're username & password correctly");
                al.showAndWait();
            }
        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }


    //to load the previous page
    public void previousPageLoad(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("requirement.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }
}
