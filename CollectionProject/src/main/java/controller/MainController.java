/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import start.App;

/**
 *
 * @author evelinsteiger
 */
public class MainController {

    @FXML
    private Button btnLogout;
    @FXML
    private Button btnAccount;

    @FXML
    private void onLogout(ActionEvent event) throws IOException {
        App.setRoot("welcome");
    }
}
