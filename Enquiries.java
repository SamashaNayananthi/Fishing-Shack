package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class Enquiries {

    public PasswordField password;
    public TextField mail;
    public TextArea text;

    public void enquiries() {
        //get user mail address and password and create final variables
        final String MAIL_ADDRESS = mail.getText();
        final String MAIL_PASSWORD = password.getText();

        //store the enquiry in a variable
        String enquiries = text.getText();

        //set properties to send email
        Properties props = new Properties();
        //get the authentication
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //connect to the host
        props.put("mail.smtp.host", "smtp.gmail.com");
        //set the port
        props.put("mail.smtp.port", "587");

        Session ses = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_ADDRESS, MAIL_PASSWORD); //to authenticate my g-mail account
            }
        });

        try {
            Message msg = new MimeMessage(ses);

            msg.setFrom(new InternetAddress(MAIL_ADDRESS));//from mail address
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("samashanayananthi@gmail.com"));//to mail address
            msg.setSubject("Enquiry");//set the mail subject
            msg.setContent(enquiries, "text/html; charset=utf-8");
            Transport.send(msg);

            //Display a message that the enquiry has sent successfully
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Successful");
            al.setHeaderText(null);
            al.setContentText("You have successfully sent you're mail");
            al.showAndWait();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
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
