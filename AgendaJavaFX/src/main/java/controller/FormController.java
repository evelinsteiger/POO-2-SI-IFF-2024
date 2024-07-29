/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Contato;
import model.dao.ContatoDaoJdbc;
import model.dao.DaoFactory;
import start.App;


/**
 * FXML Controller class
 *
 * @author evelinsteiger
 */
public class FormController implements Initializable {

    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onNavigate() {
        try {
            App.setRoot("main");    
        } catch (IOException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    
    @FXML
    private void onSubmit(ActionEvent event) throws Exception {
        try {
            ContatoDaoJdbc daoCreate = DaoFactory.newContatoDao();
            Contato createContato = new Contato();

            createContato.setNome(txtNome.getText());
            createContato.setEmail(txtEmail.getText());
            createContato.setTelefone(txtTelefone.getText());

            daoCreate.create(createContato);
            
            App.setRoot("main");
        } catch (IOException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    
    @FXML
    private void onCancel(ActionEvent event) {
        try {
            App.setRoot("main");    
        } catch (IOException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

}
