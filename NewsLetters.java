package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class NewsLetters {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public NewsLetters(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); //load the driver

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff","root",""); //connect to data base
            st = con.createStatement();

        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }

    public TextField subject;
    public TextArea newsLetter;

    public void mailSending() {
        //set the mail address and password to send mail
        final String usernam = "samashanayananthi@gmail.com";
        final String password = "dinithsamasha";

        //store the user input in variables
        String sub = subject.getText();
        String body = newsLetter.getText();

        Properties props = new Properties();
        //get the authentication
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

        try {
            Message msg = new MimeMessage(ses);
            //retrieve mail addresses for customers from data base
            String qr = "select c_mail from customer";
            //execute the query
            rs = st.executeQuery(qr);

            while (rs.next()) {
                msg.setFrom(new InternetAddress("samashanayananthi@gmail.com"));//from mail address
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rs.getString("c_mail")));//to mail address
                msg.setSubject(sub);//set the mail subject
                msg.setContent(body, "text/html; charset=utf-8"); //set the content of the mail
                Transport.send(msg); //send the mail
            }

            //display a message saying mails are sent successfully
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Successful");
            al.setHeaderText(null);
            al.setContentText("You have successfully sent NEWS LETTERS to the customers.");
            al.showAndWait();
        } catch (MessagingException | SQLException e) {
            e.printStackTrace();
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
