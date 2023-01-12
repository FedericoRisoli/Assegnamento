package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController extends MyController {

    Stage stage;
    Scene scene;
    Parent root;

    Data data=Data.getInstance();

    @FXML
    private Label errorText;

    //@FXML
    //private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void OnClickOpenRegister(ActionEvent event) {


        try {

            Parent root = FXMLLoader.load(HelloApplication.class.getResource("register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registrazione Cliente");
            stage.setScene(new Scene(root));

            //blocca finestra prima
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) throws SQLException, IOException {
        //qui funzione_login()

        ResultSet r = DBHelper.query("SELECT `username` FROM `utenti`WHERE `username` LIKE \""+username.getText()+"\" AND `password` LIKE \""+password.getText()+"\"");
        ResultSet c = DBHelper.query("SELECT `ruolo` FROM `utenti` WHERE `username` LIKE \""+username.getText()+"\""); //role selection

        //in caso di errore
        if (!r.next()||username.getText().isBlank()||password.getText().isBlank())
        {
            errorText.setOpacity(1);//QUESTO GENERA ERRORE FIXAMI
            return;
        }

        int id = DBHelper.idgetter(username);
        //cambio scena
        if(c.next())
        {
            String role = c.getString("ruolo");

            if(role.equals("client"))
            {
                sendMessage(role+id);
                data.Setid(id);//setto l 'íd da usare in un altra scena es modifypsw
                data.Setusername(username.getText()); //setto lúsername da usare in una altra scena
                data.SetRole(role); //setto il ruolo da poter recuperare in un altra scena
                data.SetPromo(true);
                root = FXMLLoader.load(getClass().getResource("usermainpage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                sendMessage(role+id);
                data.Setid(id);//setto l 'íd da usare in un altra scena es modifypsw
                data.Setusername(username.getText()); //setto lúsername da usare in una altra scena
                data.SetRole(role); //setto il ruolo da poter recuperare in un altra scena
                data.SetPromo(false);
                root = FXMLLoader.load(getClass().getResource("personnel.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
    }

    @FXML
    public void initialize(){
        createTask(server.getSocket(),this);
    }

}