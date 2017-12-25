package sample.Controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Model.DBInfoAboutUser;
import sample.Main;
import sample.Model.EmailSenderModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactController implements Initializable {



    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXTextArea message;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Main.fadeTrans(mainPane);

    }

    DBInfoAboutUser currentUser=DBInfoAboutUser.getInstance();


    @FXML
    public void goBackk(){


        try {
            Main.fadeTrans(mainPane);
            Main.changeScene("View/loginView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitProgram(MouseEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Window owner = ((Node) event.getTarget()).getScene().getWindow();
        exitAlert.setContentText("Czy napewno chcesz wyjść?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(owner);
        exitAlert.showAndWait();

        if (exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        } else {
            exitAlert.close();
        }
    }

    public void sendMessage(){
        String fullMessage=message.getText();
        String from="Wiadomosc od uzytkownika o loginie " + currentUser.getCurrentUser()+".\n";
        String[] to = {"javamit@gmail.com","kacperkapela1@wp.pl"};
        if(EmailSenderModel.sendMail("userOfOurApp@gmail.com","jajeczka18",from+fullMessage,to)){
            //System.out.println("email sent successfully");
        Image img=new Image("sample/images/tick.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Wysyłanie zakończone pomyślnie")
                .text("Twoja wiadomość została wysłana, dziękujemy!")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        }
        else System.out.println("blad w wysylaniu maila");
    }
}

