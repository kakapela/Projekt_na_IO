package sample.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Main;
import sample.Model.DBConnection;
import sample.Model.DBInfoAboutUser;
import sample.Model.GitquizModel;
import sample.Model.SQLquizModel;

import java.awt.image.SampleModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Git_quizController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private RadioButton ODPA1;
    @FXML
    private RadioButton ODPA2;
    @FXML
    private RadioButton ODPA3;
    @FXML
    private RadioButton ODPA4;
    @FXML
    private RadioButton ODPA5;

    @FXML
    private RadioButton ODPB1;
    @FXML
    private RadioButton ODPB2;
    @FXML
    private RadioButton ODPB3;
    @FXML
    private RadioButton ODPB4;
    @FXML
    private RadioButton ODPB5;

    @FXML
    private RadioButton ODPC1;
    @FXML
    private RadioButton ODPC2;
    @FXML
    private RadioButton ODPC3;
    @FXML
    private RadioButton ODPC4;
    @FXML
    private RadioButton ODPC5;
    @FXML
    private RadioButton ODPD1;
    @FXML
    private RadioButton ODPD2;
    @FXML
    private RadioButton ODPD3;
    @FXML
    private RadioButton ODPD4;
    @FXML
    private RadioButton ODPD5;
    @FXML
    private Button wyslij;


    int punkty=0;
    @FXML
    private void quiz_Git()
    {
        if (ODPC1.isSelected())
        {
            punkty++;
        }
        if (ODPA2.isSelected())
        {
            punkty++;
        }
        if (ODPD3.isSelected())
        {
            punkty++;
        }
        if (ODPB4.isSelected())
        {
            punkty++;
        }
        if (ODPB5.isSelected())
        {
            punkty++;
        }

        GitquizModel.connectDB(punkty);




       /* Connection connection = null;
        try {
            connection= DBConnection.ConnectingToDB();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBInfoAboutUser currentUser=DBInfoAboutUser.getInstance();

        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE USERS SET wynik_git =? WHERE login=?");
            stmt.setInt(1, punkty);
            stmt.setString(2, currentUser.getCurrentUser());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
       }*/



        Image img=new Image("sample/images/tick.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Skończyłeś quiz")
                .text("Liczba punktów: " + punkty)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        try {
            Main.changeScene("View/loginView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        punkty=0;

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
    @FXML
    public void goBackk(){


        try {
            //Main.fadeTrans(mainPane);
            Main.changeScene("View/loginView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.fadeTrans(mainPane);
        final ToggleGroup pytanie1 = new ToggleGroup();
        final ToggleGroup pytanie2 = new ToggleGroup();
        final ToggleGroup pytanie3 = new ToggleGroup();
        final ToggleGroup pytanie4 = new ToggleGroup();
        final ToggleGroup pytanie5 = new ToggleGroup();
        ODPA1.setSelected(true);
        ODPA2.setSelected(true);
        ODPA3.setSelected(true);
        ODPA4.setSelected(true);
        ODPA5.setSelected(true);


        ODPA1.setToggleGroup(pytanie1);
        ODPB1.setToggleGroup(pytanie1);
        ODPC1.setToggleGroup(pytanie1);
        ODPD1.setToggleGroup(pytanie1);

        ODPA2.setToggleGroup(pytanie2);
        ODPB2.setToggleGroup(pytanie2);
        ODPC2.setToggleGroup(pytanie2);
        ODPD2.setToggleGroup(pytanie2);

        ODPA3.setToggleGroup(pytanie3);
        ODPB3.setToggleGroup(pytanie3);
        ODPC3.setToggleGroup(pytanie3);
        ODPD3.setToggleGroup(pytanie3);

        ODPA4.setToggleGroup(pytanie4);
        ODPB4.setToggleGroup(pytanie4);
        ODPC4.setToggleGroup(pytanie4);
        ODPD4.setToggleGroup(pytanie4);

        ODPA5.setToggleGroup(pytanie5);
        ODPB5.setToggleGroup(pytanie5);
        ODPC5.setToggleGroup(pytanie5);
        ODPD5.setToggleGroup(pytanie5);


    }
}
