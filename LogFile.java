package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LogFile {

    public DatePicker dop;
    public Label label;
    public TextField fname;
    public TextField lname;
    public Label labelCustomer;
    public Label highest;
    public DatePicker selectedDOP;
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public LogFile(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); //load the driver

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff","root","");//connect to data base
            st = con.createStatement(); //creae st variable to write and execute queries

        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }


    //creating an object to call methods
    SearchingAndSorting obj = new SearchingAndSorting(); //creating the object
    String highestQuntity =""; //initialize the highestQuantity variable
    String msg1 =""; //initialize the  msg1 variable

    @FXML
    public void purchases(ActionEvent event){
        String dop = (selectedDOP.getEditor()).getText(); //creating a variable to hold the picked date

        //creating the message to add to the label
        msg1 = "First name   Last name   Username                 Description              Code      Size      Cost      Quantity      Date of purchase\n";

        try{
            //writing quary to retrieve data according to the date picked by the owner
            String qr = "SELECT c_fname,c_lname,username,description,code,size,cost,quantity,dop FROM customer INNER JOIN purchase ON customer.c_mail = purchase.username where dop = '"+dop+"'";
            rs = st.executeQuery(qr); //store result set in a veriable

            //add retrieved data to msd1 variable
            while (rs.next()){
                msg1 += rs.getString("c_fname")+"  "+rs.getString("c_lname")+"  "+rs.getString("username")+"   "+rs.getString("description")+"         "+rs.getString("code")+"          "+rs.getString("size")+"           "+rs.getString("cost")+"           "+rs.getString("quantity")+"                  "+rs.getString("dop")+"\n";
            }
            //set the text in label
            label.setText(msg1);

        }catch (Exception e){
            System.out.println(e);
        }

    }

    //initialize msg2 variable
    String msg2 = "";

    //declaring array
    ArrayList<Integer> arr = new ArrayList <Integer>();

    public void customerPurchases(ActionEvent event){
        //create message to add to the label
        msg2 = "First name   Last name         Username              Description      Code     Size     Cost     Quantity      Date of purchase\n";
        highestQuntity = "Highest quantity the user selected is ";

        try{
            //write the query to retrieve data from data base according to the customer name entered by owner
            String qr = "SELECT c_fname,c_lname,username,description,code,size,cost,quantity,dop FROM customer INNER JOIN purchase ON customer.c_mail = purchase.username where c_fname ='"+fname.getText()+"' AND c_lname ='"+lname.getText()+"'";
            rs = st.executeQuery(qr);

            int i = 0;
            while (rs.next()){
                //store retrieved data in the msg2 variable
                msg2 += rs.getString("c_fname")+"  "+rs.getString("c_lname")+"  "+rs.getString("username")+"          "+rs.getString("description")+"       "+rs.getString("code")+"      "+rs.getString("size")+"      "+rs.getString("cost")+"      "+rs.getString("quantity")+"      "+rs.getString("dop")+"\n";
                //Store quantities retrieved from data base in an array
                arr.add(rs.getInt("quantity"));
                i++;
            }

            //add the msg2 to label
            labelCustomer.setText(msg2);

            //call the sorting method using obj object and store the sorted array in a new array
            ArrayList<Integer> sortedArray =obj.selectionSort(arr);
            int key= sortedArray.get(sortedArray.size() - 1);
            //call for the searchning method using obj and store the return value in a variable
            int indexFoundKey=obj.binarySearch(sortedArray,key);
            //add the value of the found index into highestQuantity variable
            highestQuntity += sortedArray.get(indexFoundKey);

            //add the highestQuantity variable to label
            highest.setText(highestQuntity);

        }catch (Exception e){
            System.out.println(e);
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
