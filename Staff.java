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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Staff {
    public PasswordField password;
    public TextField username;
    private Connection con;
    private Statement st;
    private ResultSet rs;


    public Staff(){
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
            String qr = "select e_username,e_password from employee where e_username = '"+ username.getText() + "'"; //retrieve data from the data base
            rs = st.executeQuery(qr);

            if ((rs.next())&&(password.getText().equals(rs.getString("e_password")))) { //verify the username and password
                //set the scene if the username and password are correct
                Parent root = FXMLLoader.load(getClass().getResource("staffRequirement.fxml"));
                Scene samp = new Scene(root);
                Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
                step.setScene(samp);
                step.show();
                //give a warning if the password is incorrect
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
}
