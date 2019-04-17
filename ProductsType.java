package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ProductsType {
    public CheckBox hook2,hook4,hook6;
    public CheckBox rod3,rod6,rod9;
    public CheckBox reelS,reelM,reelL;
    public TextField hook2amount,hook4amount,hook6amount,rod3amount,rod6amount,rod9amount,reelSamount,reelMamount,reelLamount,username;
    public DatePicker dop;
    public String invoice;
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public ProductsType(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); //load the driver

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff","root",""); //connect to data base
            st = con.createStatement();

        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }



    public void order(javafx.event.ActionEvent event) throws IOException {
        //declaring variables
        String user,description,code,size,date,name,address;
        int qnty,cost,amnt,total,id;

        //declaring linked list with products type
        List<Products> list = new LinkedList<Products>();

        Connection con = null;
        PreparedStatement pst = null;

        //store inputs in variables
        user = username.getText();
        date = dop.getEditor().getText();

        //initialize total
        total =0;

        if (hook2.isSelected()){
            //if hook size #2 is selected initialize variables
            description = "Fishing Hooks";
            code = "FH";
            size = "#2";
            cost = 2;
            qnty = Integer.parseInt(hook2amount.getText());
            //calculate amount
            amnt = qnty * cost;
            //add the amount to the total price
            total += amnt;
            //create a new product
            Products h2 = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(h2);

            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        if (hook4.isSelected()){
            //if hook size #4 is selected initialize variables
            description = "Fishing Hooks";
            code = "FH";
            size = "#4";
            cost = 4;
            qnty = Integer.parseInt(hook4amount.getText());
            //calculate amount
            amnt = qnty * cost;
            total += amnt; //add the amount to the total price
            //create a new product
            Products h4 = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(h4);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        if (hook6.isSelected()){
            //if hook size #6 is selected initialize variables
            description = "Fishing Hooks";
            code = "FH";
            size = "#6";
            cost = 6;
            qnty = Integer.parseInt(hook6amount.getText());
            //calculate amount
            amnt = qnty * cost;
            total += amnt; //add the amount to the total price
            //create a new product
            Products h6 = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(h6);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }


        if (rod3.isSelected()){
            //if rod size 3m is selected initialize variables
            description = "Fishing Rod";
            code = "RO";
            size = "3m";
            cost = 100;
            qnty = Integer.parseInt(rod3amount.getText());
            //calculate amount
            amnt = qnty * cost;
            total += amnt; //add the amount to the total price
            //create a new product
            Products r3 = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(r3);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        if (rod6.isSelected()){
            //if rod size 6m is selected initialize variables
            description = "Fishing Rod";
            code = "RO";
            size = "6m";
            cost = 200;
            qnty = Integer.parseInt(rod6amount.getText());
            //calculate amount
            amnt = qnty * cost;
            total += amnt; //add the amount to the total price
            //create a new product
            Products r6 = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(r6);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        if (rod9.isSelected()){
            //if rod size 9m is selected initialize variables
            description = "Fishing Rod";
            code = "RO";
            size = "9m";
            cost = 300;
            qnty = Integer.parseInt(rod9amount.getText());
            //calculate amount
            amnt = qnty * cost;
            total += amnt; //add the amount to the total price
            //create a new product
            Products r9 = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(r9);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }


        if (reelS.isSelected()){
            //if reel size small is selected initialize variables
            description = "Fishing Reel";
            code = "FR";
            size = "S";
            cost = 1;
            qnty = Integer.parseInt(reelSamount.getText());
            //calculate amount
            amnt = qnty * cost;
            total += amnt; //add the amount to the total price
            //create a new product
            Products rs = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(rs);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        if (reelM.isSelected()){
            //if reel size medium is selected initialize variables
            description = "Fishing Reel";
            code = "FR";
            size = "M";
            cost = 2;
            qnty = Integer.parseInt(reelMamount.getText());
            //calculate amount
            amnt = qnty * cost;
            total += amnt; //add the amount to the total price
            //create a new product
            Products rm = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(rm);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        if (reelL.isSelected()){
            //if reel size large is selected initialize variables
            description = "Fishing Reel";
            code = "FR";
            size = "L";
            cost = 3;
            qnty = Integer.parseInt(reelLamount.getText());
            //calculate amount
            amnt = qnty * cost;
            total += amnt; //add the amount to the total price
            //create a new product
            Products rl = new Products(description,code,size,cost,qnty,amnt);
            //add the product to the linked list
            list.add(rl);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //insert data into data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
                pst = con.prepareStatement("insert into purchase(username,code,description,size,cost,quantity,dop) values(?,?,?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, code);
                pst.setString(3,description);
                pst.setString(4,size);
                pst.setString(5, String.valueOf(cost));
                pst.setString(6, String.valueOf(qnty));
                pst.setString(7, date);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error " + e);
            }

        }

        //initialize variables
        int count=1;
        invoice = "";

        //print the linked list using advanced for loop
        for(Products p:list){
            invoice += count+"     "+p.desc+"      "+p.code+"          "+p.size+"          "+p.cost+"           "+p.qnty+"           "+p.amnt+"<br>";
            count ++;
        }


        //set mail address and password to send tax invoice
        final String usernam = "samashanayananthi@gmail.com";
        final String password = "dinithsamasha";

        //set the properties
        Properties props = new Properties();
        //set the authentication
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        //connect to the host
        props.put("mail.smtp.host","smtp.gmail.com");
        //set the port
        props.put("mail.smtp.port","587");

        Session ses = Session.getInstance(props,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(usernam,password); //to athenticate my gmail account
            }
        });


        //initializing variables
        name = "";
        address = "";
        id = 0;

        try {
            //retrieve data from data base to print receipt
                String qr = "SELECT c_id,c_fname,c_lname,c_address FROM customer INNER JOIN purchase ON customer.c_mail = purchase.username";
                rs = st.executeQuery(qr);

                while (rs.next()){
                    name = rs.getString("c_fname")+" "+rs.getString("c_lname");
                    address = rs.getString("c_address");
                    id = rs.getInt("c_id");
                }

            Message msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress("samashanayananthi@gmail.com"));//from mail address
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user));//to mail address
            msg.setSubject("Tax Invoice");//set the mail subject
            //creating mail body using html
            msg.setContent("<h:body style=background-color:white;font-family:verdana;>"+"<h1 style=\"padding-left:400px\"><b>Jeff's Fishing Shack</b></h1>"
                    +"<h2 style=\"padding-left:470px\"><b>Tax Invoice</b></h2>"+"<p style=\"padding-left:80px\">Jeff’s Fishing Shack<br>Trading as: Octopus Pty Ltd<br>Shop 4, Hillarys Boat Harbour<br>Hillarys, WA, 6025<br>T: 08 9402 2222<br>Email:\tSales@JFS.com.au</p>\n"
                    + "<p style=\"padding-left:80px\">Reciepts#:  </p> <p style=\"padding-left:800px\">Date :"+date+"</p>\n"+"<p style=\"padding-left:80px\">Customer : "+name+"<br>"+address+"</p>\n"+"<p style=\"padding-left:80px\">Customer# : "+id+"</p>\n"
                    +"<p style=\"padding-left:80px\">Customer email :"+user+"</p>\n"+"<p style=\"padding-left:80px\">Purchases</p>\n"
                    +"<span style=\"padding-left:200px\">No.</span><span style=\"padding-left:80px\">Desc.</span><span style=\"padding-left:80px\">Code</span><span style=\"padding-left:80px\">Size</span><span style=\"padding-left:80px\">Cost</span><span style=\"padding-left:80px\">Qty</span><span style=\"padding-left:80px\">Amount</span>\n"
                    + "<div style=\"padding-left:200px\"><pre style=\"font-size:16px\">"+invoice+"</pre></div><p style=\"padding-left:800px\">Total paid : $ "+total+"</p>\n"
                    +"<p style=\"padding-left:700px\">Thank you for your business.<br>Jeff’s - where the real fishermen shop.</p>\n"
                    + "</body>", "text/html; charset=utf-8");

            //send the message
            Transport.send(msg);

            //display a message saying successfullr placed the order
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Successful");
            al.setHeaderText(null);
            al.setContentText("You have successfully placed you're order \n and you're tax invoice has been mailed to you're mail address");
            al.showAndWait();
        }
        catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //load the previous page
    public void previousPageLoad(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("requirement.fxml"));
        Scene samp = new Scene(root);
        Stage step = (Stage)((Node)event.getSource()).getScene().getWindow();
        step.setScene(samp);
        step.show();
    }
}
