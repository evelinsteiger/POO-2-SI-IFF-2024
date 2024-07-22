/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Produto;

/**
 * FXML Controller class
 *
 * @author evelinsteiger
 */
public class MainController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtQtde;
    @FXML
    private TextField txtPreco;
    @FXML
    private Button btnStore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    @FXML
    public void store() {
        Produto input = new Produto(
                Integer.parseInt(txtId.getText()),
                txtNome.getText(),
                Integer.parseInt(txtQtde.getText()),
                Float.parseFloat(txtPreco.getText()));
        
        System.out.println(input);
    }

}
